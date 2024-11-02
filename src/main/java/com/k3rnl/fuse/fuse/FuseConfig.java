package com.k3rnl.fuse.fuse;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;

@CContext(FuseLibrary.Directives.class)
@CStruct(value = "fuse_config", addStructKeyword = true)
public interface FuseConfig extends PointerBase {

    /**
     * If `set_gid` is non-zero, the st_gid attribute of each file
     * is overwritten with the value of `gid`.
     */
    @CField("set_gid")
    int set_gid();   // Set the gid
    @CField("set_gid")
    void set_gid(int value);

    @CField("gid")
    int gid();   // Group ID
    @CField("gid")
    void gid(int value);

    /**
     * If `set_uid` is non-zero, the st_uid attribute of each file
     * is overwritten with the value of `uid`.
     */
    @CField("set_uid")
    int set_uid();   // Set the uid
    @CField("set_uid")
    void set_uid(int value);

    @CField("uid")
    int uid();   // User ID
    @CField("uid")
    void uid(int value);

    /**
     * If `set_mode` is non-zero, the any permissions bits set in
     * `umask` are unset in the st_mode attribute of each file.
     */
    @CField("set_mode")
    int set_mode();   // Set the mode
    @CField("set_mode")
    void set_mode(int value);

    @CField("umask")
    int umask();   // Umask
    @CField("umask")
    void umask(int value);

    /**
     * The timeout in seconds for which name lookups will be
     * cached.
     */
    @CField("entry_timeout")
    long entry_timeout();   // Entry timeout
    @CField("entry_timeout")
    void entry_timeout(long value);

    /**
     * The timeout in seconds for which a negative lookup will be
     * cached. This means, that if file did not exist (lookup
     * returned ENOENT), the lookup will only be redone after the
     * timeout, and the file/directory will be assumed to not
     * exist until then. A value of zero means that negative
     * lookups are not cached.
     */
    @CField("negative_timeout")
    long negative_timeout();   // Negative timeout
    @CField("negative_timeout")
    void negative_timeout(long value);

    /**
     * The timeout in seconds for which file/directory attributes
     * (as returned by e.g. the `getattr` handler) are cached.
     */
    @CField("attr_timeout")
    long attr_timeout();   // Attribute timeout
    @CField("attr_timeout")
    void attr_timeout(long value);

    /**
     * Allow requests to be interrupted
     */
    @CField("intr")
    int intr();   // Interrupt
    @CField("intr")
    void intr(int value);

    /**
     * Specify which signal number to send to the filesystem when
     * a request is interrupted.  The default is hardcoded to
     * USR1.
     */
    @CField("intr_signal")
    int intr_signal();   // Interrupt signal
    @CField("intr_signal")
    void intr_signal(int value);

    /**
     * Normally, FUSE assigns inodes to paths only for as long as
     * the kernel is aware of them. With this option inodes are
     * instead remembered for at least this many seconds.  This
     * will require more memory, but may be necessary when using
     * applications that make use of inode numbers.
     *
     * A number of -1 means that inodes will be remembered for the
     * entire life-time of the file-system process.
     */
    @CField("remember")
    int remember();   // Remember
    @CField("remember")
    void remember(int value);

    /**
     * The default behavior is that if an open file is deleted,
     * the file is renamed to a hidden file (.fuse_hiddenXXX), and
     * only removed when the file is finally released.  This
     * relieves the filesystem implementation of having to deal
     * with this problem. This option disables the hiding
     * behavior, and files are removed immediately in an unlink
     * operation (or in a rename operation which overwrites an
     * existing file).
     *
     * It is recommended that you not use the hard_remove
     * option. When hard_remove is set, the following libc
     * functions fail on unlinked files (returning errno of
     * ENOENT): read(2), write(2), fsync(2), close(2), f*xattr(2),
     * ftruncate(2), fstat(2), fchmod(2), fchown(2)
     */
    @CField("hard_remove")
    int hard_remove();   // Hard remove
    @CField("hard_remove")
    void hard_remove(int value);

    /**
     * Honor the st_ino field in the functions getattr() and
     * fill_dir(). This value is used to fill in the st_ino field
     * in the stat(2), lstat(2), fstat(2) functions and the d_ino
     * field in the readdir(2) function. The filesystem does not
     * have to guarantee uniqueness, however some applications
     * rely on this value being unique for the whole filesystem.
     *
     * Note that this does *not* affect the inode that libfuse
     * and the kernel use internally (also called the "nodeid").
     */
    @CField("use_ino")
    int use_ino();   // Use inode
    @CField("use_ino")
    void use_ino(int value);

    /**
     * If use_ino option is not given, still try to fill in the
     * d_ino field in readdir(2). If the name was previously
     * looked up, and is still in the cache, the inode number
     * found there will be used.  Otherwise it will be set to -1.
     * If use_ino option is given, this option is ignored.
     */
    @CField("readdir_ino")
    int readdir_ino();   // Readdir inode
    @CField("readdir_ino")
    void readdir_ino(int value);

    /**
     * This option disables the use of page cache (file content cache)
     * in the kernel for this filesystem. This has several affects:
     *
     * 1. Each read(2) or write(2) system call will initiate one
     *    or more read or write operations, data will not be
     *    cached in the kernel.
     *
     * 2. The return value of the read() and write() system calls
     *    will correspond to the return values of the read and
     *    write operations. This is useful for example if the
     *    file size is not known in advance (before reading it).
     *
     * Internally, enabling this option causes fuse to set the
     * `direct_io` field of `struct fuse_file_info` - overwriting
     * any value that was put there by the file system.
     */
    @CField("direct_io")
    int direct_io();   // Direct I/O
    @CField("direct_io")
    void direct_io(int value);

    /**
     * This option disables flushing the cache of the file
     * contents on every open(2).  This should only be enabled on
     * filesystems where the file data is never changed
     * externally (not through the mounted FUSE filesystem).  Thus
     * it is not suitable for network filesystems and other
     * intermediate filesystems.
     *
     * NOTE: if this option is not specified (and neither
     * direct_io) data is still cached after the open(2), so a
     * read(2) system call will not always initiate a read
     * operation.
     *
     * Internally, enabling this option causes fuse to set the
     * `keep_cache` field of `struct fuse_file_info` - overwriting
     * any value that was put there by the file system.
     **/
    @CField("kernel_cache")
    int kernel_cache();   // Kernel cache
    @CField("kernel_cache")
    void kernel_cache(int value);

    /**
     * This option is an alternative to `kernel_cache`. Instead of
     * unconditionally keeping cached data, the cached data is
     * invalidated on open(2) if if the modification time or the
     * size of the file has changed since it was last opened.
     */
    @CField("auto_cache")
    int auto_cache();   // Auto cache
    @CField("auto_cache")
    void auto_cache(int value);

    /**
     * The timeout in seconds for which file attributes are cached
     * for the purpose of checking if auto_cache should flush the
     * file data on open.
     */
    @CField("ac_attr_timeout_set")
    int ac_attr_timeout_set();   // Attribute cache timeout set
    @CField("ac_attr_timeout_set")
    void ac_attr_timeout_set(int value);

    @CField("ac_attr_timeout")
    long ac_attr_timeout();   // Attribute cache timeout
    @CField("ac_attr_timeout")
    void ac_attr_timeout(long value);

    /**
     * If this option is given the file-system handlers for the
     * following operations will not receive path information:
     * read, write, flush, release, fallocate, fsync, readdir,
     * releasedir, fsyncdir, lock, ioctl and poll.
     *
     * For the truncate, getattr, chmod, chown and utimens
     * operations the path will be provided only if the struct
     * fuse_file_info argument is NULL.
     */
    @CField("nullpath_ok")
    int nullpath_ok();   // Null path OK
    @CField("nullpath_ok")
    void nullpath_ok(int value);



}
