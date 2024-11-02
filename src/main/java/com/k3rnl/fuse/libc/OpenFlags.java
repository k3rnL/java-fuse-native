package com.k3rnl.fuse.libc;

public class OpenFlags {

    // Open flags
    public static final int O_RDONLY = 0x0000;  // open for reading only
    public static final int O_WRONLY = 0x0001;  // open for writing only
    public static final int O_RDWR   = 0x0002;  // open for reading and writing
    public static final int O_CREAT  = 0x0100;  // create if non-existent
    public static final int O_EXCL   = 0x0200;  // error if file exists
    public static final int O_NOCTTY = 0x0400;  // do not assign controlling terminal
    public static final int O_TRUNC  = 0x0800;  // truncate size to 0
    public static final int O_APPEND = 0x1000;  // append on each write
    public static final int O_NONBLOCK = 0x2000; // non-blocking mode
    public static final int O_SYNC   = 0x4000;  // synchronous writes
    public static final int O_ACCMODE = O_RDONLY | O_WRONLY | O_RDWR; // mask for access modes

    // Utility methods
    public static boolean isReadOnly(int flags) {
        return (flags & O_RDONLY) == O_RDONLY;
    }

    public static boolean isWriteOnly(int flags) {
        return (flags & O_WRONLY) == O_WRONLY;
    }

    public static boolean isReadWrite(int flags) {
        return (flags & O_RDWR) == O_RDWR;
    }

    public static boolean isCreat(int flags) {
        return (flags & O_CREAT) == O_CREAT;
    }

    public static boolean isExcl(int flags) {
        return (flags & O_EXCL) == O_EXCL;
    }

    public static boolean isNoCtty(int flags) {
        return (flags & O_NOCTTY) == O_NOCTTY;
    }

    public static boolean isTrunc(int flags) {
        return (flags & O_TRUNC) == O_TRUNC;
    }

    public static boolean isAppend(int flags) {
        return (flags & O_APPEND) == O_APPEND;
    }

    public static boolean isNonBlocking(int flags) {
        return (flags & O_NONBLOCK) == O_NONBLOCK;
    }

    public static boolean isSync(int flags) {
        return (flags & O_SYNC) == O_SYNC;
    }
}
