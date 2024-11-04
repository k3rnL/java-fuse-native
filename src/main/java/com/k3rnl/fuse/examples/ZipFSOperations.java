package com.k3rnl.fuse.examples;

import com.k3rnl.fuse.FuseNative;
import com.k3rnl.fuse.api.FillDir;
import com.k3rnl.fuse.api.JavaFuseOperations;
import com.k3rnl.fuse.fuse.FuseFileInfo;
import com.k3rnl.fuse.fuse.FuseLibrary;
import com.k3rnl.fuse.libc.Errno;
import com.k3rnl.fuse.libc.FileStat;
import com.k3rnl.fuse.libc.FileStatFlags;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.WordFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFSOperations extends JavaFuseOperations {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: ZipFSOperations <archive> [mountpoint] [options]");
            System.out.println("If no mountpoint is given, the archive is mounted on ./<archive>");
            System.exit(1);
        }

        String archive = args[0];
        String mountpoint;
        List<String> options;
        if (args.length > 1 && args[1].startsWith("-")) {
            mountpoint = archive.replace(".zip", "");
            options = Arrays.asList(args).subList(1, args.length);
        } else if (args.length > 1 && !args[1].startsWith("-")) {
            mountpoint = args[1];
            options = args.length > 2 ? Arrays.asList(args).subList(2, args.length) : Collections.emptyList();
        } else {
            mountpoint = archive.replace(".zip", "");
            options = Collections.emptyList();
        }

        // Create mountpoint if it doesn't exist
        try {
            Files.createDirectories(Paths.get(mountpoint));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ZipFSOperations zipFSOperations = new ZipFSOperations(archive);

        FuseNative fuseNative = new FuseNative(zipFSOperations);

        fuseNative.mount(mountpoint, options);

    }

    final String archive;
    final Map<String, Entry> entries = new HashMap<>();

    private static class Entry {
        final ZipEntry entry;
        final String name;

        public Entry(ZipEntry entry, String name) {
            this.entry = entry;
            this.name = name;
        }
    }

    private static class Folder extends Entry {
        final List<Entry> entries = new ArrayList<>();

        public Folder(ZipEntry entry, String name) {
            super(entry, name);
        }
    }

    private static class File extends Entry {
        final byte[] data;

        public File(ZipEntry entry, String name, byte[] data) {
            super(entry, name);
            this.data = data;
        }
    }

    public ZipFSOperations(String archive) {
        this.archive = archive;
        readZipEntries();
    }

    private void readZipEntries() {
        entries.put("/", new Folder(null, "/"));
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(archive))) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                String name = "/" + zipEntry.getName();
                if (name.endsWith("/"))
                    name = name.substring(0, name.length() - 1);
                Entry entry;
                if (zipEntry.isDirectory()) {
                    if (entries.containsKey(name))
                        continue;
                    entry = new Folder(zipEntry, name);
                } else {
                    byte[] data = zipInputStream.readAllBytes();
                    entry = new File(zipEntry, name, data);
                }
                entries.put(name, entry);
                System.out.println("Added entry: " + name);
                String parentPath = getParentPath(name);
                Folder parent = (Folder) entries.get(parentPath);
                if (parent != null) {
                    System.out.println("Adding entry " + name + " to parent: " + parentPath);
                    parent.entries.add(entry);
                } else {
                    Folder folder = new Folder(null, parentPath);
                    folder.entries.add(entry);
                    entries.put(parentPath, folder);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getParentPath(String path) {
        if ("/".equals(path)) {
            return "/"; // If it's the root path, return it as is
        }

        // Remove trailing slash if it exists
        path = path.endsWith("/") ? path.substring(0, path.length() - 1) : path;

        // Find the last '/' and take the substring up to that point
        int lastSlashIndex = path.lastIndexOf('/');
        return lastSlashIndex > 0 ? path.substring(0, lastSlashIndex) : "/";
    }

    void fillStat(ZipEntry entry, FileStat stat) {
        if (entry.isDirectory())
            stat.st_mode(FileStatFlags.S_IFDIR | FileStatFlags.ALL_READ | FileStatFlags.S_IXUGO);
        else
            stat.st_mode(FileStatFlags.S_IFREG | FileStatFlags.ALL_READ);
        stat.st_size(entry.getSize());
        stat.st_atime(entry.getLastAccessTime().toMillis() / 1000);
        stat.st_mtime(entry.getLastModifiedTime().toMillis() / 1000);
        stat.st_ctime(entry.getCreationTime().toMillis() / 1000);
        stat.st_nlink(1);
    }

    @Override
    public int getattr(String path, FileStat stat, FuseFileInfo fi) {
        if (path.equals("/")) {
            try {
                BasicFileAttributes attr = Files.readAttributes(Paths.get(archive).toAbsolutePath(), BasicFileAttributes.class);
                stat.st_mode(FileStatFlags.S_IFDIR | FileStatFlags.ALL_READ | FileStatFlags.S_IXUGO);
                stat.st_size(attr.size());
                stat.st_atime(attr.lastAccessTime().toMillis() / 1000);
                stat.st_mtime(attr.lastModifiedTime().toMillis() / 1000);
                stat.st_ctime(attr.creationTime().toMillis() / 1000);
                stat.st_nlink(1);
                return 0;
            } catch (IOException e) {
                return -Errno.ENOENT();
            }
        }
        Entry entry = entries.get(path);
        if (entry == null) {
            return -Errno.ENOENT();
        }
        fillStat(entry.entry, stat);
        return 0;
    }

    @Override
    public int readdir(String path, VoidPointer buf, FillDir filler, long offset, FuseFileInfo fi) {
        if (!(entries.get(path) instanceof Folder folder))
            return -Errno.ENOTDIR();

        filler.apply(buf, ".", WordFactory.nullPointer(), 0);
        filler.apply(buf, "..", WordFactory.nullPointer(), 0);

        for (Entry entry : folder.entries) {
            String fileName = entry.name.substring(entry.name.lastIndexOf('/') + 1);
            filler.apply(buf, fileName, WordFactory.nullPointer(), 0);
        }

        return 0;
    }

    @Override
    public int read(String path, byte[] buf, long size, long offset, FuseFileInfo fi) {
        Entry entry = entries.get(path);
        if (!(entry instanceof File file))
            return -Errno.ENOENT();
        if (offset >= file.data.length)
            return 0;
        int len = (int) Math.min(size, file.data.length - offset);
        System.arraycopy(file.data, (int) offset, buf, 0, len);
        return len;
    }
}
