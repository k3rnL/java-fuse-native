package com.k3rnl.fuse.libc;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;

import java.util.List;

@CContext(Errno.Directives.class)
public class Errno {

    static class Directives implements CContext.Directives {
        @Override
        public List<String> getHeaderFiles() {
            return List.of("<errno.h>");
        }
    }

    @CConstant
    public static native int EPERM();      // Operation not permitted
    @CConstant
    public static native int ENOENT();     // No such file or directory
    @CConstant
    public static native int ESRCH();      // No such process
    @CConstant
    public static native int EINTR();      // Interrupted system call
    @CConstant
    public static native int EIO();        // Input/output error
    @CConstant
    public static native int ENXIO();      // No such device or address
    @CConstant
    public static native int E2BIG();      // Argument list too long
    @CConstant
    public static native int ENOEXEC();    // Exec format error
    @CConstant
    public static native int EBADF();      // Bad file descriptor
    @CConstant
    public static native int ECHILD();     // No child processes
    @CConstant
    public static native int EDEADLK();    // Resource deadlock avoided
    @CConstant
    public static native int ENOMEM();     // Cannot allocate memory
    @CConstant
    public static native int EACCES();     // Permission denied
    @CConstant
    public static native int EFAULT();     // Bad address
    @CConstant
    public static native int ENOTBLK();    // Block device required
    @CConstant
    public static native int EBUSY();      // Device or resource busy
    @CConstant
    public static native int EEXIST();     // File exists
    @CConstant
    public static native int EXDEV();      // Cross-device link
    @CConstant
    public static native int ENODEV();     // No such device
    @CConstant
    public static native int ENOTDIR();    // Not a directory
    @CConstant
    public static native int EISDIR();     // Is a directory
    @CConstant
    public static native int EINVAL();     // Invalid argument
    @CConstant
    public static native int ENFILE();     // File table overflow
    @CConstant
    public static native int EMFILE();     // Too many open files
    @CConstant
    public static native int ENOTTY();     // Not a typewriter
    @CConstant
    public static native int ETXTBSY();    // Text file busy
    @CConstant
    public static native int EFBIG();      // File too large
    @CConstant
    public static native int ENOSPC();     // No space left on device
    @CConstant
    public static native int ESPIPE();     // Illegal seek
    @CConstant
    public static native int EROFS();      // Read-only file system
    @CConstant
    public static native int EMLINK();     // Too many links
    @CConstant
    public static native int EPIPE();      // Broken pipe
    @CConstant
    public static native int EDOM();       // Math argument out of domain of func
    @CConstant
    public static native int ERANGE();     // Math result not representable
    @CConstant
    public static native int EAGAIN();     // Resource temporarily unavailable
    @CConstant
    public static native int EWOULDBLOCK(); // Operation would block
    @CConstant
    public static native int EINPROGRESS(); // Operation now in progress
    @CConstant
    public static native int EALREADY();   // Operation already in progress
    @CConstant
    public static native int ENOTSOCK();   // Socket operation on non-socket
    @CConstant
    public static native int EDESTADDRREQ(); // Destination address required
    @CConstant
    public static native int EMSGSIZE();   // Message too long
    @CConstant
    public static native int EPROTOTYPE(); // Protocol wrong type for socket
    @CConstant
    public static native int ENOPROTOOPT();// Protocol not available
    @CConstant
    public static native int EPROTONOSUPPORT(); // Protocol not supported
    @CConstant
    public static native int ESOCKTNOSUPPORT(); // Socket type not supported
    @CConstant
    public static native int EOPNOTSUPP(); // Operation not supported on socket
    @CConstant
    public static native int EPFNOSUPPORT(); // Protocol family not supported
    @CConstant
    public static native int EAFNOSUPPORT(); // Address family not supported by protocol
    @CConstant
    public static native int EADDRINUSE(); // Address already in use
    @CConstant
    public static native int EADDRNOTAVAIL(); // Cannot assign requested address
    @CConstant
    public static native int ENETDOWN();   // Network is down
    @CConstant
    public static native int ENETUNREACH(); // Network is unreachable
    @CConstant
    public static native int ENETRESET();  // Network dropped connection on reset
    @CConstant
    public static native int ECONNABORTED(); // Software caused connection abort
    @CConstant
    public static native int ECONNRESET(); // Connection reset by peer
    @CConstant
    public static native int ENOBUFS();    // No buffer space available
    @CConstant
    public static native int EISCONN();    // Transport endpoint is already connected
    @CConstant
    public static native int ENOTCONN();   // Transport endpoint is not connected
    @CConstant
    public static native int ESHUTDOWN();  // Cannot send after transport endpoint shutdown
    @CConstant
    public static native int ETIMEDOUT();  // Connection timed out
    @CConstant
    public static native int ECONNREFUSED(); // Connection refused
    @CConstant
    public static native int ELOOP();      // Too many symbolic links encountered
    @CConstant
    public static native int ENAMETOOLONG(); // File name too long
    @CConstant
    public static native int EHOSTDOWN();  // Host is down
    @CConstant
    public static native int EHOSTUNREACH(); // No route to host
    @CConstant
    public static native int ENOTEMPTY();  // Directory not empty
    @CConstant
    public static native int EUSERS();     // Too many users
    @CConstant
    public static native int EDQUOT();     // Disk quota exceeded
    @CConstant
    public static native int ESTALE();     // Stale NFS file handle
    @CConstant
    public static native int EREMOTE();    // Object is remote
    // Add more error codes as needed
}