package com.k3rnl.fuse.libc;

public class FileStatFlags {

    public static final int S_IFIFO = 0010000;  // named pipe (fifo)
    public static final int S_IFCHR = 0020000;  // character special
    public static final int S_IFDIR = 0040000;  // directory
    public static final int S_IFBLK = 0060000;  // block special
    public static final int S_IFREG = 0100000;  // regular
    public static final int S_IFLNK = 0120000;  // symbolic link
    public static final int S_IFSOCK = 0140000; // socket
    public static final int S_IFMT = 0170000;   // file mask for type checks
    public static final int S_ISUID = 0004000;  // set user id on execution
    public static final int S_ISGID = 0002000;  // set group id on execution
    public static final int S_ISVTX = 0001000;  // save swapped text even after use
    public static final int S_IRUSR = 0000400;  // read permission, owner
    public static final int S_IWUSR = 0000200;  // write permission, owner
    public static final int S_IXUSR = 0000100;  // execute/search permission, owner
    public static final int S_IRGRP = 0000040;  // read permission, group
    public static final int S_IWGRP = 0000020;  // write permission, group
    public static final int S_IXGRP = 0000010;  // execute/search permission, group
    public static final int S_IROTH = 0000004;  // read permission, other
    public static final int S_IWOTH = 0000002;  // write permission, other
    public static final int S_IXOTH = 0000001;  // execute permission, other

    public static final int ALL_READ = S_IRUSR | S_IRGRP | S_IROTH;
    public static final int ALL_WRITE = S_IWUSR | S_IWGRP | S_IWOTH;
    public static final int S_IXUGO = S_IXUSR | S_IXGRP | S_IXOTH;

    public static boolean S_ISTYPE(int mode, int mask) {
        return (mode & S_IFMT) == mask;
    }

    public static boolean S_ISDIR(int mode) {
        return S_ISTYPE(mode, S_IFDIR);
    }

    public static boolean S_ISCHR(int mode) {
        return S_ISTYPE(mode, S_IFCHR);
    }

    public static boolean S_ISBLK(int mode) {
        return S_ISTYPE(mode, S_IFBLK);
    }

    public static boolean S_ISREG(int mode) {
        return S_ISTYPE(mode, S_IFREG);
    }

    public static boolean S_ISFIFO(int mode) {
        return S_ISTYPE(mode, S_IFIFO);
    }

    public static boolean S_ISLNK(int mode) {
        return S_ISTYPE(mode, S_IFLNK);
    }
}