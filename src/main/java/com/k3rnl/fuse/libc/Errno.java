package com.k3rnl.fuse.libc;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;

import java.util.List;

/**
 * The {@code Errno} class provides native error codes commonly used in POSIX-compliant operating systems.
 * These error codes are used to identify specific system errors.
 */
@CContext(Errno.Directives.class)
public class Errno {

    /**
     * Defines the directives for including necessary C headers.
     */
    static class Directives implements CContext.Directives {
        @Override
        public List<String> getHeaderFiles() {
            return List.of("<errno.h>");
        }
    }

    /**
     * @return the error code for "Operation not permitted"
     */
    @CConstant
    public static native int EPERM();

    /**
     * @return the error code for "No such file or directory"
     */
    @CConstant
    public static native int ENOENT();

    /**
     * @return the error code for "No such process"
     */
    @CConstant
    public static native int ESRCH();

    /**
     * @return the error code for "Interrupted system call"
     */
    @CConstant
    public static native int EINTR();

    /**
     * @return the error code for "Input/output error"
     */
    @CConstant
    public static native int EIO();

    /**
     * @return the error code for "No such device or address"
     */
    @CConstant
    public static native int ENXIO();

    /**
     * @return the error code for "Argument list too long"
     */
    @CConstant
    public static native int E2BIG();

    /**
     * @return the error code for "Exec format error"
     */
    @CConstant
    public static native int ENOEXEC();

    /**
     * @return the error code for "Bad file descriptor"
     */
    @CConstant
    public static native int EBADF();

    /**
     * @return the error code for "No child processes"
     */
    @CConstant
    public static native int ECHILD();

    /**
     * @return the error code for "Resource deadlock avoided"
     */
    @CConstant
    public static native int EDEADLK();

    /**
     * @return the error code for "Cannot allocate memory"
     */
    @CConstant
    public static native int ENOMEM();

    /**
     * @return the error code for "Permission denied"
     */
    @CConstant
    public static native int EACCES();

    /**
     * @return the error code for "Bad address"
     */
    @CConstant
    public static native int EFAULT();

    /**
     * @return the error code for "Block device required"
     */
    @CConstant
    public static native int ENOTBLK();

    /**
     * @return the error code for "Device or resource busy"
     */
    @CConstant
    public static native int EBUSY();

    /**
     * @return the error code for "File exists"
     */
    @CConstant
    public static native int EEXIST();

    /**
     * @return the error code for "Cross-device link"
     */
    @CConstant
    public static native int EXDEV();

    /**
     * @return the error code for "No such device"
     */
    @CConstant
    public static native int ENODEV();

    /**
     * @return the error code for "Not a directory"
     */
    @CConstant
    public static native int ENOTDIR();

    /**
     * @return the error code for "Is a directory"
     */
    @CConstant
    public static native int EISDIR();

    /**
     * @return the error code for "Invalid argument"
     */
    @CConstant
    public static native int EINVAL();

    /**
     * @return the error code for "File table overflow"
     */
    @CConstant
    public static native int ENFILE();

    /**
     * @return the error code for "Too many open files"
     */
    @CConstant
    public static native int EMFILE();

    /**
     * @return the error code for "Not a typewriter"
     */
    @CConstant
    public static native int ENOTTY();

    /**
     * @return the error code for "Text file busy"
     */
    @CConstant
    public static native int ETXTBSY();

    /**
     * @return the error code for "File too large"
     */
    @CConstant
    public static native int EFBIG();

    /**
     * @return the error code for "No space left on device"
     */
    @CConstant
    public static native int ENOSPC();

    /**
     * @return the error code for "Illegal seek"
     */
    @CConstant
    public static native int ESPIPE();

    /**
     * @return the error code for "Read-only file system"
     */
    @CConstant
    public static native int EROFS();

    /**
     * @return the error code for "Too many links"
     */
    @CConstant
    public static native int EMLINK();

    /**
     * @return the error code for "Broken pipe"
     */
    @CConstant
    public static native int EPIPE();

    /**
     * @return the error code for "Math argument out of domain of func"
     */
    @CConstant
    public static native int EDOM();

    /**
     * @return the error code for "Math result not representable"
     */
    @CConstant
    public static native int ERANGE();

    /**
     * @return the error code for "Resource temporarily unavailable"
     */
    @CConstant
    public static native int EAGAIN();

    /**
     * @return the error code for "Operation would block"
     */
    @CConstant
    public static native int EWOULDBLOCK();

    /**
     * @return the error code for "Operation now in progress"
     */
    @CConstant
    public static native int EINPROGRESS();

    /**
     * @return the error code for "Operation already in progress"
     */
    @CConstant
    public static native int EALREADY();

    /**
     * @return the error code for "Socket operation on non-socket"
     */
    @CConstant
    public static native int ENOTSOCK();

    /**
     * @return the error code for "Destination address required"
     */
    @CConstant
    public static native int EDESTADDRREQ();

    /**
     * @return the error code for "Message too long"
     */
    @CConstant
    public static native int EMSGSIZE();

    /**
     * @return the error code for "Protocol wrong type for socket"
     */
    @CConstant
    public static native int EPROTOTYPE();

    /**
     * @return the error code for "Protocol not available"
     */
    @CConstant
    public static native int ENOPROTOOPT();

    /**
     * @return the error code for "Protocol not supported"
     */
    @CConstant
    public static native int EPROTONOSUPPORT();

    /**
     * @return the error code for "Socket type not supported"
     */
    @CConstant
    public static native int ESOCKTNOSUPPORT();

    /**
     * @return the error code for "Operation not supported on socket"
     */
    @CConstant
    public static native int EOPNOTSUPP();

    /**
     * @return the error code for "Protocol family not supported"
     */
    @CConstant
    public static native int EPFNOSUPPORT();

    /**
     * @return the error code for "Address family not supported by protocol"
     */
    @CConstant
    public static native int EAFNOSUPPORT();

    /**
     * @return the error code for "Address already in use"
     */
    @CConstant
    public static native int EADDRINUSE();

    /**
     * @return the error code for "Cannot assign requested address"
     */
    @CConstant
    public static native int EADDRNOTAVAIL();

    /**
     * @return the error code for "Network is down"
     */
    @CConstant
    public static native int ENETDOWN();

    /**
     * @return the error code for "Network is unreachable"
     */
    @CConstant
    public static native int ENETUNREACH();

    /**
     * @return the error code for "Network dropped connection on reset"
     */
    @CConstant
    public static native int ENETRESET();

    /**
     * @return the error code for "Software caused connection abort"
     */
    @CConstant
    public static native int ECONNABORTED();

    /**
     * @return the error code for "Connection reset by peer"
     */
    @CConstant
    public static native int ECONNRESET();

    /**
     * @return the error code for "No buffer space available"
     */
    @CConstant
    public static native int ENOBUFS();

    /**
     * @return the error code for "Transport endpoint is already connected"
     */
    @CConstant
    public static native int EISCONN();

    /**
     * @return the error code for "Transport endpoint is not connected"
     */
    @CConstant
    public static native int ENOTCONN();

    /**
     * @return the error code for "Cannot send after transport endpoint shutdown"
     */
    @CConstant
    public static native int ESHUTDOWN();

    /**
     * @return the error code for "Connection timed out"
     */
    @CConstant
    public static native int ETIMEDOUT();

    /**
     * @return the error code for "Connection refused"
     */
    @CConstant
    public static native int ECONNREFUSED();

    /**
     * @return the error code for "Too many symbolic links encountered"
     */
    @CConstant
    public static native int ELOOP();

    /**
     * @return the error code for "File name too long"
     */
    @CConstant
    public static native int ENAMETOOLONG();

    /**
     * @return the error code for "Host is down"
     */
    @CConstant
    public static native int EHOSTDOWN();

    /**
     * @return the error code for "No route to host"
     */
    @CConstant
    public static native int EHOSTUNREACH();

    /**
     * @return the error code for "Directory not empty"
     */
    @CConstant
    public static native int ENOTEMPTY();

    /**
     * @return the error code for "Too many users"
     */
    @CConstant
    public static native int EUSERS();

    /**
     * @return the error code for "Disk quota exceeded"
     */
    @CConstant
    public static native int EDQUOT();

    /**
     * @return the error code for "Stale NFS file handle"
     */
    @CConstant
    public static native int ESTALE();

    /**
     * @return the error code for "Object is remote"
     */
    @CConstant
    public static native int EREMOTE();

}
