package com.k3rnl.fuse.examples;

import com.k3rnl.fuse.FuseNative;
import com.k3rnl.fuse.api.FillDir;
import com.k3rnl.fuse.api.JavaFuseOperations;
import com.k3rnl.fuse.fuse.*;
import com.k3rnl.fuse.libc.*;
import org.graalvm.nativeimage.StackValue;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.WordFactory;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryFSOperations extends JavaFuseOperations {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: memory-fs <mountpoint> [options]");
            System.exit(1);
        }

        MemoryFSOperations memoryFSOperations = new MemoryFSOperations();

        FuseNative fuse = new FuseNative(memoryFSOperations);

        fuse.mount(args[0], Arrays.asList(args).subList(1, args.length));
    }

    private static final int CHUNK_SIZE = 4096 * 4096; // 16MB

    static abstract class Node {
        final String name;
        Instant atime;
        Instant mtime;

        int uid;
        int gid;
        int permission;

        public Node(String name, int uid, int gid) {
            this.name = name;
            this.atime = Instant.now();
            this.mtime = Instant.now();
            this.permission = 0755;
            this.uid = uid;
            this.gid = gid;
        }
    }

    static class Folder extends Node {
        final List<Node> children = new ArrayList<>();

        public Folder(String name, int uid, int gid) {
            super(name, uid, gid);
        }
    }

    static class File extends Node {
        List<byte[]> data;
        long size;

        public File(String name, int uid, int gid) {
            super(name, uid, gid);
            this.data = new ArrayList<>(512);
            this.size = 0;
        }
    }

    private final Map<String, Node> nodes = new HashMap<>();

    private final Map<Long, File> openFiles = new HashMap<>();
    private final AtomicLong nextFileHandle = new AtomicLong(0);

    private void fillFileStat(Node node, FileStat stat) {
        stat.st_atime().tv_sec(node.atime.getEpochSecond());
        stat.st_atime().tv_nsec(node.atime.getNano());
        stat.st_mtime().tv_sec(node.mtime.getEpochSecond());
        stat.st_mtime().tv_nsec(node.mtime.getNano());
        stat.st_uid(node.uid);
        stat.st_gid(node.gid);
        if (node instanceof File file) {
            stat.st_size(file.size);
            stat.st_mode(FileStatFlags.S_IFREG | node.permission);
        } else {
            stat.st_size(0);
            stat.st_mode(FileStatFlags.S_IFDIR | node.permission);
        }
        stat.st_nlink(1);
    }

    @Override
    public VoidPointer init(VoidPointer conn, FuseConfig config) {
        // get default uid/gid
        int uid = FuseLibrary.fuse_get_context().uid();
        int gid = FuseLibrary.fuse_get_context().gid();
        nodes.put("/", new Folder("/", uid, gid));

        return WordFactory.nullPointer();
    }

    @Override
    public int getattr(String path, FileStat stat, FuseFileInfo fi) {
        Node node = nodes.get(path);
        if (node == null) {
            return -Errno.ENOENT();
        }
        fillFileStat(node, stat);
        return 0;
    }

    @Override
    public int readdir(String path, VoidPointer buf, FillDir filler, long offset, FuseFileInfo fi, FuseReaddirFlags flags) {
        Node node = nodes.get(path);
        if (node == null) {
            return Errno.ENOENT();
        } else if (node instanceof File) {
            return Errno.ENOTDIR();
        }
        Folder folder = (Folder) node;
        if (flags == FuseReaddirFlags.FUSE_READDIR_PLUS) {
            FileStat stat = StackValue.get(FileStat.class);
            fillFileStat(folder, stat);
            filler.apply(buf, ".", stat, 0, FuseFillDirFlags.FUSE_FILL_DIR_PLUS);
            filler.apply(buf, "..", stat, 0, FuseFillDirFlags.NONE);
            for (Node child : folder.children) {
                fillFileStat(child, stat);
                filler.apply(buf, child.name, stat, 0, FuseFillDirFlags.FUSE_FILL_DIR_PLUS);
            }
        } else {
            filler.apply(buf, ".", WordFactory.nullPointer(), 0, null);
            filler.apply(buf, "..", WordFactory.nullPointer(), 0, null);
            for (Node child : folder.children) {
                filler.apply(buf, child.name, WordFactory.nullPointer(), 0, null);
            }
        }
        return 0;
    }

    @Override
    public int mkdir(String path, int mode) {
        String[] parts = path.split("/");
        String parentPath = getParentPath(path);
        Node parent = nodes.get(parentPath);
        if (!(parent instanceof Folder)) {
            return -Errno.ENOENT();
        }
        int uid = FuseLibrary.fuse_get_context().uid();
        int gid = FuseLibrary.fuse_get_context().gid();
        Folder folder = new Folder(parts[parts.length - 1], uid, gid);
        folder.permission = mode & 0777;
        nodes.put(path, folder);
        ((Folder) parent).children.add(folder);
        return 0;
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

    @Override
    public int open(String path, FuseFileInfo fi) {
        Node node = nodes.get(path);
        if (node == null) {
            return -Errno.ENOENT();
        } else if (node instanceof Folder) {
            return -Errno.EISDIR();
        }
        File file = (File) node;
        int openFlags = fi.flags();
        if (OpenFlags.isReadWrite(openFlags) || OpenFlags.isWriteOnly(openFlags)) {
            if (OpenFlags.isCreat(openFlags) || !OpenFlags.isAppend(openFlags)) {
                file.data = new ArrayList<>();
                file.size = 0;
            }
        }
        long fileHandle = nextFileHandle.getAndIncrement();
        openFiles.put(fileHandle, file);
        fi.fh(fileHandle);
        return 0;
    }

    @Override
    public int read(String path, byte[] buf, long size, long offset, FuseFileInfo fi) {
        long fileHandle = fi.fh();
        File file = openFiles.get(fileHandle);

        int bytesRead = 0;
        int bufOffset = 0;

        while (bytesRead < size && offset + bytesRead < (long) file.data.size() * CHUNK_SIZE) {
            int chunkIndex = (int) ((offset + bytesRead) / CHUNK_SIZE);
            int chunkOffset = (int) ((offset + bytesRead) % CHUNK_SIZE);

            byte[] chunk = file.data.get(chunkIndex);
            int bytesToRead = (int) Math.min(size - bytesRead, CHUNK_SIZE - chunkOffset);

            // Copy data from the chunk to buf
            System.arraycopy(chunk, chunkOffset, buf, bufOffset, bytesToRead);

            bytesRead += bytesToRead;
            bufOffset += bytesToRead;
        }

        return bytesRead;
    }

    @Override
    public int write(String path, byte[] buf, long size, long offset, FuseFileInfo fi) {
        long fileHandle = fi.fh();
        File file = openFiles.get(fileHandle);

        int bytesWritten = 0;
        int bufOffset = 0;

        while (bytesWritten < size) {
            int chunkIndex = (int) ((offset + bytesWritten) / CHUNK_SIZE);
            int chunkOffset = (int) ((offset + bytesWritten) % CHUNK_SIZE);


            // Ensure the chunk exists in the file
            while (file.data.size() <= chunkIndex) {
                file.data.add(new byte[CHUNK_SIZE]); // Create a new chunk if needed
            }

            byte[] chunk = file.data.get(chunkIndex);
            int bytesToWrite = (int) Math.min(size - bytesWritten, CHUNK_SIZE - chunkOffset);

            // Copy data from buf to the chunk
            System.arraycopy(buf, bufOffset, chunk, chunkOffset, bytesToWrite);

            bytesWritten += bytesToWrite;
            bufOffset += bytesToWrite;
        }

        file.size = Math.max(file.size, offset + bytesWritten);

        return (int) size;
    }

    @Override
    public int release(String path, FuseFileInfo fi) {
        long fileHandle = fi.fh();
        openFiles.remove(fileHandle);
        return 0;
    }

    @Override
    public int mknod(String path, int mode, int rdev) {
        String[] parts = path.split("/");
        String parentPath = getParentPath(path);
        Node parent = nodes.get(parentPath);
        if (!(parent instanceof Folder)) {
            return -Errno.ENOENT();
        }
        int uid = FuseLibrary.fuse_get_context().uid();
        int gid = FuseLibrary.fuse_get_context().gid();
        File file = new File(parts[parts.length - 1], uid, gid);
        file.permission = mode & 0777;
        nodes.put(path, file);
        ((Folder) parent).children.add(file);
        return 0;
    }

    @Override
    public int truncate(String path, long size, FuseFileInfo fi) {
        Node node = nodes.get(path);
        if (node == null) {
            return -Errno.ENOENT();
        } else if (node instanceof Folder) {
            return -Errno.EISDIR();
        }

        File file = (File) node;

        if (size == file.size) {
            return 0;
        } else if (size < file.size) {
            // Truncate by removing chunks as needed
            int lastChunkIndex = (int) (size / CHUNK_SIZE);
            int lastChunkOffset = (int) (size % CHUNK_SIZE);

            // Remove chunks beyond the last chunk we need
            file.data = file.data.subList(0, lastChunkIndex + 1);

            // Zero out the remainder of the last chunk if size lands within a chunk
            if (lastChunkIndex < file.data.size()) {
                byte[] lastChunk = file.data.get(lastChunkIndex);
                Arrays.fill(lastChunk, lastChunkOffset, lastChunk.length, (byte) 0);
            }

            file.size = size;
        } else {
            // Expand the file by adding chunks if necessary
            while (file.size < size) {
                // Add a new chunk
                file.data.add(new byte[CHUNK_SIZE]);

                file.size = Math.min(file.size + CHUNK_SIZE, size);
            }
        }

        return 0;
    }

    @Override
    public int utimens(String path, TimeSpec[] tv, FuseFileInfo fi) {
        Node node = nodes.get(path);
        if (node == null) {
            return -Errno.ENOENT();
        }
        Instant atime = Instant.ofEpochSecond(tv[0].tv_sec(), tv[0].tv_nsec());
        Instant mtime = Instant.ofEpochSecond(tv[1].tv_sec(), tv[1].tv_nsec());
        node.atime = atime;
        node.mtime = mtime;
        return 0;
    }

    @Override
    public int unlink(String path) {
        String parentPath = getParentPath(path);
        Node parent = nodes.get(parentPath);
        if (!(parent instanceof Folder)) {
            return -Errno.ENOENT();
        }
        Folder folder = (Folder) parent;
        Node node = nodes.get(path);
        if (node == null) {
            return -Errno.ENOENT();
        }
        folder.children.remove(node);
        nodes.remove(path);
        return 0;
    }

    @Override
    public int rmdir(String path) {
        String parentPath = getParentPath(path);
        Node parent = nodes.get(parentPath);
        if (!(parent instanceof Folder folder)) {
            return -Errno.ENOENT();
        }
        Node node = nodes.get(path);
        if (node == null) {
            return -Errno.ENOENT();
        } else if (!(node instanceof Folder)) {
            return -Errno.ENOTDIR();
        }
        folder.children.remove(node);
        nodes.remove(path);
        return 0;
    }

    @Override
    public int create(String path, long mode, FuseFileInfo fi) {
        int code = mknod(path, (int) mode, 0);
        if (code != 0)
            return code;
        return open(path, fi);
    }

    @Override
    public int chmod(String path, long mode, FuseFileInfo fi) {
        Node node = nodes.get(path);
        if (node == null) {
            return -Errno.ENOENT();
        }
        node.permission = (int) mode;
        return 0;
    }

    @Override
    public int chown(String path, long uid, long gid, FuseFileInfo fi) {
        Node node = nodes.get(path);
        if (node == null) {
            return -Errno.ENOENT();
        }
        node.uid = (int) uid;
        node.gid = (int) gid;
        return 0;
    }
}
