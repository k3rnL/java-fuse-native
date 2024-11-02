package com.k3rnl.fuse.api;

import com.k3rnl.fuse.NotImplemented;
import com.k3rnl.fuse.fuse.FuseBufVec;
import com.k3rnl.fuse.fuse.FuseConfig;
import com.k3rnl.fuse.fuse.FuseFileInfo;
import com.k3rnl.fuse.libc.StatVFS;
import com.k3rnl.fuse.libc.FileStat;
import com.k3rnl.fuse.libc.Flock;
import com.k3rnl.fuse.libc.TimeSpec;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.WordFactory;

import java.util.HashMap;
import java.util.Map;

public class JavaFuseOperations {

    private final Map<String, Boolean> implementedMethods = new HashMap<>();

    public JavaFuseOperations() {
        var methods = getClass().getDeclaredMethods();
        System.out.println("Initializing JavaFuseOperations");
        System.out.println("Methods: " + methods.length);

        for (var method : methods) {
            if (method.isAnnotationPresent(NotImplemented.class)) {
                System.out.println("Method " + method.getName() + " is not implemented");
                implementedMethods.put(method.getName(), false);
            } else {
                System.out.println("Method " + method.getName() + " is implemented");
                implementedMethods.put(method.getName(), true);
            }
        }
    }

    public boolean isImplemented(String methodName) {
        System.out.println("Checking if " + methodName + " is implemented");
        System.out.println("Implemented methods: " + implementedMethods);
        return implementedMethods.getOrDefault(methodName, false);
    }


    /**
     * Get file attributes.
     * <p>
     * Similar to stat().  The 'st_dev' and 'st_blksize' fields are
     * ignored. The 'st_ino' field is ignored except if the 'use_ino'
     * mount option is given. In that case it is passed to userspace,
     * but libfuse and the kernel will still assign a different
     * inode for internal use (called the "nodeid").
     * <p>
     * `fi` will always be NULL if the file is not currently open, but
     * may also be NULL if the file is open.
     */
    @NotImplemented
    public int getattr(String path, FileStat stat, FuseFileInfo fi) {
        return -1;
    }

    /**
     * Read the target of a symbolic link
     * <p>
     * The buffer should be filled with a null terminated string.  The
     * buffer size argument includes the space for the terminating
     * null character.      If the linkname is too long to fit in the
     * buffer, it should be truncated.      The return value should be 0
     * for success.
     */
    @NotImplemented
    public int readlink(String path, byte[] buf) {
        return -1;
    }

    /**
     * Create a file node
     * <p>
     * This is called for creation of all non-directory, non-symlink
     * nodes. If the filesystem defines a create() method, then for
     * regular files that will be called instead.
     */
    @NotImplemented
    public int mknod(String path, int mode, int rdev) {
        return -1;
    }

    /**
     * Create a directory
     * <p>
     * Note that the mode argument may not have the type specification
     * bits set, i.e. S_ISDIR(mode) can be false.  To obtain the
     * correct directory type bits use mode|S_IFDIR
     */
    @NotImplemented
    public int mkdir(String path, int mode) {
        return -1;
    }

    /**
     * Remove a file
     */
    @NotImplemented
    public int unlink(String path) {
        return -1;
    }

    /**
     * Remove a directory
     */
    @NotImplemented
    public int rmdir(String path) {
        return -1;
    }

    /**
     * Create a symbolic link
     */
    @NotImplemented
    public int symlink(String from, String to) {
        return -1;
    }

    /**
     * Rename a file
     * <p>
     * *flags* may be `RENAME_EXCHANGE` or `RENAME_NOREPLACE`. If
     * RENAME_NOREPLACE is specified, the filesystem must not
     * overwrite *newname* if it exists and return an error
     * instead. If `RENAME_EXCHANGE` is specified, the filesystem
     * must atomically exchange the two files, i.e. both must
     * exist and neither may be deleted.
     */
    @NotImplemented
    public int rename(String from, String to, int flags) {
        return -1;
    }

    /**
     * Create a hard link to a file
     */
    @NotImplemented
    public int link(String from, String to) {
        return -1;
    }

    /**
     * Change the permission bits of a file
     * <p>
     * `fi` will always be NULL if the file is not currently open, but
     * may also be NULL if the file is open.
     */
    @NotImplemented
    public int chmod(String path, long mode, FuseFileInfo fi) {
        return -1;
    }

    /**
     * Change the owner and group of a file
     * <p>
     * `fi` will always be NULL if the file is not currently open, but
     * may also be NULL if the file is open.
     * <p>
     * Unless FUSE_CAP_HANDLE_KILLPRIV is disabled, this method is
     * expected to reset the setuid and setgid bits.
     */
    @NotImplemented
    public int chown(String path, long uid, long gid, FuseFileInfo fi) {
        return -1;
    }

    /**
     * Change the size of a file
     * <p>
     * `fi` will always be NULL if the file is not currently open, but
     * may also be NULL if the file is open.
     * <p>
     * Unless FUSE_CAP_HANDLE_KILLPRIV is disabled, this method is
     * expected to reset the setuid and setgid bits.
     */
    @NotImplemented
    public int truncate(String path, long size, FuseFileInfo fi) {
        return -1;
    }

    /**
     * Open a file
     * <p>
     * Open flags are available in fi->flags. The following rules
     * apply.
     * <p>
     * - Creation (O_CREAT, O_EXCL, O_NOCTTY) flags will be
     * filtered out / handled by the kernel.
     * <p>
     * - Access modes (O_RDONLY, O_WRONLY, O_RDWR, O_EXEC, O_SEARCH)
     * should be used by the filesystem to check if the operation is
     * permitted.  If the ``-o default_permissions`` mount option is
     * given, this check is already done by the kernel before calling
     * open() and may thus be omitted by the filesystem.
     * <p>
     * - When writeback caching is enabled, the kernel may send
     * read requests even for files opened with O_WRONLY. The
     * filesystem should be prepared to handle this.
     * <p>
     * - When writeback caching is disabled, the filesystem is
     * expected to properly handle the O_APPEND flag and ensure
     * that each write is appending to the end of the file.
     * <p>
     * - When writeback caching is enabled, the kernel will
     * handle O_APPEND. However, unless all changes to the file
     * come through the kernel this will not work reliably. The
     * filesystem should thus either ignore the O_APPEND flag
     * (and let the kernel handle it), or return an error
     * (indicating that reliably O_APPEND is not available).
     * <p>
     * Filesystem may store an arbitrary file handle (pointer,
     * index, etc) in fi->fh, and use this in other all other file
     * operations (read, write, flush, release, fsync).
     * <p>
     * Filesystem may also implement stateless file I/O and not store
     * anything in fi->fh.
     * <p>
     * There are also some flags (direct_io, keep_cache) which the
     * filesystem may set in fi, to change the way the file is opened.
     * See fuse_file_info structure in <fuse_common.h> for more details.
     * <p>
     * If this request is answered with an error code of ENOSYS
     * and FUSE_CAP_NO_OPEN_SUPPORT is set in
     * `fuse_conn_info.capable`, this is treated as success and
     * future calls to open will also succeed without being send
     * to the filesystem process.
     */
    @NotImplemented
    public int open(String path, FuseFileInfo fi) {
        return -1;
    }

    /**
     * Read data from an open file
     * <p>
     * Read should return exactly the number of bytes requested except
     * on EOF or error, otherwise the rest of the data will be
     * substituted with zeroes.      An exception to this is when the
     * 'direct_io' mount option is specified, in which case the return
     * value of the read system call will reflect the return value of
     * this operation.
     */
    @NotImplemented
    public int read(String path, byte[] buf, long size, long offset, FuseFileInfo fi) {
        return -1;
    }

    /**
     * Write data to an open file
     * <p>
     * Write should return exactly the number of bytes requested
     * except on error.      An exception to this is when the 'direct_io'
     * mount option is specified (see read operation).
     * <p>
     * Unless FUSE_CAP_HANDLE_KILLPRIV is disabled, this method is
     * expected to reset the setuid and setgid bits.
     */
    @NotImplemented
    public int write(String path, byte[] buf, long size, long offset, FuseFileInfo fi) {
        return -1;
    }

    /**
     * Get file system statistics
     * <p>
     * The 'f_favail', 'f_fsid' and 'f_flag' fields are ignored
     */
    @NotImplemented
    public int statfs(String path, StatVFS stat) {
        return -1;
    }

    /**
     * Possibly flush cached data
     * <p>
     * BIG NOTE: This is not equivalent to fsync().  It's not a
     * request to sync dirty data.
     * <p>
     * Flush is called on each close() of a file descriptor, as opposed to
     * release which is called on the close of the last file descriptor for
     * a file.  Under Linux, errors returned by flush() will be passed to
     * userspace as errors from close(), so flush() is a good place to write
     * back any cached dirty data. However, many applications ignore errors
     * on close(), and on non-Linux systems, close() may succeed even if flush()
     * returns an error. For these reasons, filesystems should not assume
     * that errors returned by flush will ever be noticed or even
     * delivered.
     * <p>
     * NOTE: The flush() method may be called more than once for each
     * open().  This happens if more than one file descriptor refers to an
     * open file handle, e.g. due to dup(), dup2() or fork() calls.  It is
     * not possible to determine if a flush is final, so each flush should
     * be treated equally.  Multiple write-flush sequences are relatively
     * rare, so this shouldn't be a problem.
     * <p>
     * Filesystems shouldn't assume that flush will be called at any
     * particular point.  It may be called more times than expected, or not
     * at all.
     * <p>
     * [close]: http://pubs.opengroup.org/onlinepubs/9699919799/functions/close.html
     */
    @NotImplemented
    public int flush(String path, FuseFileInfo fi) {
        return -1;
    }

    /**
     * Release an open file
     * <p>
     * Release is called when there are no more references to an open
     * file: all file descriptors are closed and all memory mappings
     * are unmapped.
     * <p>
     * For every open() call there will be exactly one release() call
     * with the same flags and file handle.  It is possible to
     * have a file opened more than once, in which case only the last
     * release will mean, that no more reads/writes will happen on the
     * file.  The return value of release is ignored.
     */
    @NotImplemented
    public int release(String path, FuseFileInfo fi) {
        return -1;
    }

    /**
     * Synchronize file contents
     * <p>
     * If the datasync parameter is non-zero, then only the user data
     * should be flushed, not the meta data.
     */
    @NotImplemented
    public int fsync(String path, int isdatasync, FuseFileInfo fi) {
        return -1;
    }

    /**
     * Set extended attributes
     * <p>
     * `size` may be 0, if the attribute contains no data
     * <p>
     * `flags` can be `XATTR_CREATE` (fail if attr already exists),
     * `XATTR_REPLACE` (fail if attr does not exist),
     * `XATTR_NOFOLLOW` (don't follow symlink),
     * `XATTR_CREATE | XATTR_REPLACE` (reserved for future use)
     */
    @NotImplemented
    public int setxattr(String path, String name, byte[] value, long size, int flags) {
        return -1;
    }

    /**
     * Get extended attributes
     * <p>
     * `size` should be used to return the size of the value, if the
     * buffer is too small. The size should not include the terminating
     * null character ('\0').
     */
    @NotImplemented
    public int getxattr(String path, String name, byte[] value, long size) {
        return -1;
    }

    /**
     * List extended attributes
     * <p>
     * `size` should be used to return the size of the value, if the
     * buffer is too small. The size should not include the terminating
     * null character ('\0').
     */
    @NotImplemented
    public int listxattr(String path, byte[] list, long size) {
        return -1;
    }

    /**
     * Remove extended attributes
     */
    @NotImplemented
    public int removexattr(String path, String name) {
        return -1;
    }

    /**
     * Open directory
     * <p>
     * Unless the 'default_permissions' mount option is given,
     * this method should check if opendir is permitted for this
     * directory. Optionally opendir may also return an arbitrary
     * filehandle in the fuse_file_info structure, which will be
     * passed to readdir, releasedir and fsyncdir.
     */
    @NotImplemented
    public int opendir(String path, FuseFileInfo fi) {
        return -1;
    }

    /**
     * Read directory
     * <p>
     * The filesystem may choose between two modes of operation:
     * <p>
     * 1) The readdir implementation ignores the offset parameter, and
     * passes zero to the filler function's offset.  The filler
     * function will not return '1' (unless an error happens), so the
     * whole directory is read in a single readdir operation.
     * <p>
     * 2) The readdir implementation keeps track of the offsets of the
     * directory entries.  It uses the offset parameter and always
     * passes non-zero offset to the filler function.  When the buffer
     * is full (or an error happens) the filler function will return
     * '1'.
     */
    @NotImplemented
    public int readdir(String path, VoidPointer buf, FillDir filler, long offset, FuseFileInfo fi) {
        return -1;
    }

    /**
     * Release directory
     * <p>
     * For every opendir call there will be exactly one releasedir call
     * with the same flags and file handle.
     */
    @NotImplemented
    public int releasedir(String path, FuseFileInfo fi) {
        return -1;
    }

    /**
     * Synchronize directory contents
     * <p>
     * If the datasync parameter is non-zero, then only the user data
     * should be flushed, not the meta data
     */
    @NotImplemented
    public int fsyncdir(String path, int isdatasync, FuseFileInfo fi) {
        return -1;
    }

    /**
     * Initialize filesystem
     * <p>
     * The return value will passed in the `private_data` field of
     * `struct fuse_context` to all file operations, and as a
     * parameter to the destroy() method. It overrides the initial
     * value provided to fuse_main() / fuse_new().
     */
    @NotImplemented
    public VoidPointer init(VoidPointer conn, FuseConfig config) {
        return WordFactory.nullPointer();
    }

    /**
     * Clean up filesystem
     * <p>
     * Called on filesystem exit.
     */
    @NotImplemented
    public void destroy(VoidPointer privateData) {
    }

    /**
     * Check file access permissions
     * <p>
     * This will be called for the access() system call.  If the
     * 'default_permissions' mount option is given, this method is not
     * called.
     * <p>
     * This method is not called under Linux kernel versions 2.4.x
     */
    @NotImplemented
    public int access(String path, int mask) {
        return -1;
    }

    /**
     * Create and open a file
     * <p>
     * If the file does not exist, first create it with the specified
     * mode, and then open it.
     * <p>
     * If this method is not implemented or under Linux kernel
     * versions earlier than 2.6.15, the mknod() and open() methods
     * will be called instead.
     */
    @NotImplemented
    public int create(String path, long mode, FuseFileInfo fi) {
        return -1;
    }

    /**
     * Perform POSIX file locking operation
     * <p>
     * The cmd argument will be either F_GETLK, F_SETLK or F_SETLKW.
     * <p>
     * For the meaning of fields in 'struct flock' see the man page
     * for fcntl(2).  The l_whence field will always be set to
     * SEEK_SET.
     * <p>
     * For checking lock ownership, the 'fuse_file_info->owner'
     * argument must be used.
     * <p>
     * For F_GETLK operation, the library will first check currently
     * held locks, and if a conflicting lock is found it will return
     * information without calling this method.      This ensures, that
     * for local locks the l_pid field is correctly filled in.      The
     * results may not be accurate in case of race conditions and in
     * the presence of hard links, but it's unlikely that an
     * application would rely on accurate GETLK results in these
     * cases.  If a conflicting lock is not found, this method will be
     * called, and the filesystem may fill out l_pid by a meaningful
     * value, or it may leave this field zero.
     * <p>
     * For F_SETLK and F_SETLKW the l_pid field will be set to the pid
     * of the process performing the locking operation.
     * <p>
     * Note: if this method is not implemented, the kernel will still
     * allow file locking to work locally.  Hence it is only
     * interesting for network filesystems and similar.
     */
    @NotImplemented
    public int lock(String path, FuseFileInfo fi, int cmd, Flock lock) {
        return -1;
    }

    /**
     * Change the access and modification times of a file with
     * nanosecond resolution
     * <p>
     * This supersedes the old utime() interface.  New applications
     * should use this.
     * <p>
     * `fi` will always be NULL if the file is not currently open, but
     * may also be NULL if the file is open.
     * <p>
     * See the utimensat(2) man page for details.
     */
    @NotImplemented
    public int utimens(String path, TimeSpec[] tv, FuseFileInfo fi) {
        return -1;
    }

    /**
     * Map block index within file to block index within device
     * <p>
     * Note: This makes sense only for block device backed filesystems
     * mounted with the 'blkdev' option
     */
    @NotImplemented
    public int bmap(String path, long blocksize, VoidPointer idx) {
        return -1;
    }

    /**
     * Ioctl
     * <p>
     * flags will have FUSE_IOCTL_COMPAT set for 32bit ioctls in
     * 64bit environment.  The size and direction of data is
     * determined by _IOC_*() decoding of cmd.  For _IOC_NONE,
     * data will be NULL, for _IOC_WRITE data is out area, for
     * _IOC_READ in area and if both are set in/out area.  In all
     * non-NULL cases, the area is of _IOC_SIZE(cmd) bytes.
     * <p>
     * If flags has FUSE_IOCTL_DIR then the fuse_file_info refers to a
     * directory file handle.
     * <p>
     * Note : the unsigned long request submitted by the application
     * is truncated to 32 bits.
     */
    @NotImplemented
    public int ioctl(String path, int cmd, VoidPointer arg, FuseFileInfo fi, long flags, VoidPointer data) {
        return -1;
    }

    /**
     * Poll for IO readiness events
     * <p>
     * Note: If ph is non-NULL, the client should notify
     * when IO readiness events occur by calling
     * fuse_notify_poll() with the specified ph.
     * <p>
     * Regardless of the number of times poll with a non-NULL ph
     * is received, single notification is enough to clear all.
     * Notifying more times incurs overhead but doesn't harm
     * correctness.
     * <p>
     * The callee is responsible for destroying ph with
     * fuse_pollhandle_destroy() when no longer in use.
     */
    @NotImplemented
    public int poll(String path, FuseFileInfo fi, VoidPointer ph, VoidPointer reventsp) {
        return -1;
    }

    /**
     * Write contents of buffer to an open file
     * <p>
     * Similar to the write() method, but data is supplied in a
     * generic buffer.  Use fuse_buf_copy() to transfer data to
     * the destination.
     * <p>
     * Unless FUSE_CAP_HANDLE_KILLPRIV is disabled, this method is
     * expected to reset the setuid and setgid bits.
     */
    @NotImplemented
    public int write_buf(String path, FuseBufVec buf, long off, FuseFileInfo fi) {
        return -1;
    }

    /**
     * Store data from an open file in a buffer
     * <p>
     * Similar to the read() method, but data is stored and
     * returned in a generic buffer.
     * <p>
     * No actual copying of data has to take place, the source
     * file descriptor may simply be stored in the buffer for
     * later data transfer.
     * <p>
     * The buffer must be allocated dynamically and stored at the
     * location pointed to by bufp.  If the buffer contains memory
     * regions, they too must be allocated using malloc().  The
     * allocated memory will be freed by the caller.
     */
    @NotImplemented
    public int read_buf(String path, FuseBufVec.FuseBufVecPointer buf, long size, long off, FuseFileInfo fi) {
        return -1;
    }

    /**
     * Perform BSD file locking operation
     * <p>
     * The op argument will be either LOCK_SH, LOCK_EX or LOCK_UN
     * <p>
     * Nonblocking requests will be indicated by ORing LOCK_NB to
     * the above operations
     * <p>
     * For more information see the flock(2) manual page.
     * <p>
     * Additionally fi->owner will be set to a value unique to
     * this open file.  This same value will be supplied to
     * ->release() when the file is released.
     * <p>
     * Note: if this method is not implemented, the kernel will still
     * allow file locking to work locally.  Hence it is only
     * interesting for network filesystems and similar.
     */
    @NotImplemented
    public int flock(String path, FuseFileInfo fi, int op) {
        return -1;
    }

    /**
     * Allocates space for an open file
     * <p>
     * This function ensures that required space is allocated for specified
     * file.  If this function returns success then any subsequent write
     * request to specified range is guaranteed not to fail because of lack
     * of space on the file system media.
     */
    @NotImplemented
    public int fallocate(String path, int mode, long off, long length, FuseFileInfo fi) {
        return -1;
    }

    /**
     * Copy a range of data from one file to another
     * <p>
     * Performs an optimized copy between two file descriptors without the
     * additional cost of transferring data through the FUSE kernel module
     * to user space (glibc) and then back into the FUSE filesystem again.
     * <p>
     * In case this method is not implemented, applications are expected to
     * fall back to a regular file copy.   (Some glibc versions did this
     * emulation automatically, but the emulation has been removed from all
     * glibc release branches.)
     */
    @NotImplemented
    public int copy_file_range(String path_in,
                               FuseFileInfo fi_in,
                               long off_in,
                               String path_out,
                               FuseFileInfo fi_out,
                               long off_out,
                               long len,
                               int flags) {
        return -1;
    }

    /**
     * Find next data or hole after the specified offset
     */
    @NotImplemented
    public int lseek(String path, long off, int whence, FuseFileInfo fi) {
        return -1;
    }
}
