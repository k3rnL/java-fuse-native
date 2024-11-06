package com.k3rnl.fuse.fuse;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;

/**
 * The {@code FuseConfig} interface represents the native "fuse_config" structure,
 * which is used to configure various options in the FUSE (Filesystem in Userspace) library.
 */
@CContext(FuseLibrary.Directives.class)
@CStruct(value = "fuse_config", addStructKeyword = true)
public interface FuseConfig extends PointerBase {

    /**
     * @return whether the group ID (gid) of each file is overwritten with the value of `gid`.
     */
    @CField("set_gid")
    int set_gid();

    /**
     * Sets the value of `set_gid`.
     * @param value non-zero to enable gid overwrite; zero to disable.
     */
    @CField("set_gid")
    void set_gid(int value);

    /**
     * @return the group ID to set if `set_gid` is enabled.
     */
    @CField("gid")
    int gid();

    /**
     * Sets the group ID.
     * @param value the group ID to set.
     */
    @CField("gid")
    void gid(int value);

    /**
     * @return whether the user ID (uid) of each file is overwritten with the value of `uid`.
     */
    @CField("set_uid")
    int set_uid();

    /**
     * Sets the value of `set_uid`.
     * @param value non-zero to enable uid overwrite; zero to disable.
     */
    @CField("set_uid")
    void set_uid(int value);

    /**
     * @return the user ID to set if `set_uid` is enabled.
     */
    @CField("uid")
    int uid();

    /**
     * Sets the user ID.
     * @param value the user ID to set.
     */
    @CField("uid")
    void uid(int value);

    /**
     * @return whether to unset any permissions bits set in `umask` in each file's permissions.
     */
    @CField("set_mode")
    int set_mode();

    /**
     * Sets the value of `set_mode`.
     * @param value non-zero to enable umask; zero to disable.
     */
    @CField("set_mode")
    void set_mode(int value);

    /**
     * @return the umask applied if `set_mode` is enabled.
     */
    @CField("umask")
    int umask();

    /**
     * Sets the umask.
     * @param value the umask to set.
     */
    @CField("umask")
    void umask(int value);

    /**
     * @return the timeout in seconds for name lookups to be cached.
     */
    @CField("entry_timeout")
    long entry_timeout();

    /**
     * Sets the entry timeout.
     * @param value the timeout in seconds.
     */
    @CField("entry_timeout")
    void entry_timeout(long value);

    /**
     * @return the timeout in seconds for negative lookups (when a file does not exist) to be cached.
     */
    @CField("negative_timeout")
    long negative_timeout();

    /**
     * Sets the negative lookup timeout.
     * @param value the timeout in seconds.
     */
    @CField("negative_timeout")
    void negative_timeout(long value);

    /**
     * @return the timeout in seconds for caching file/directory attributes.
     */
    @CField("attr_timeout")
    long attr_timeout();

    /**
     * Sets the attribute timeout.
     * @param value the timeout in seconds.
     */
    @CField("attr_timeout")
    void attr_timeout(long value);

    /**
     * @return whether requests are allowed to be interrupted.
     */
    @CField("intr")
    int intr();

    /**
     * Sets whether requests can be interrupted.
     * @param value non-zero to allow interruption; zero to disallow.
     */
    @CField("intr")
    void intr(int value);

    /**
     * @return the signal number to send to the filesystem when a request is interrupted.
     */
    @CField("intr_signal")
    int intr_signal();

    /**
     * Sets the signal for interrupted requests.
     * @param value the signal number.
     */
    @CField("intr_signal")
    void intr_signal(int value);

    /**
     * @return the number of seconds to remember inodes, requiring additional memory but supporting applications that use inode numbers.
     */
    @CField("remember")
    int remember();

    /**
     * Sets the inode retention time.
     * @param value seconds to remember inodes, or -1 for the lifetime of the filesystem process.
     */
    @CField("remember")
    void remember(int value);

    /**
     * @return whether files should be removed immediately instead of renamed and hidden.
     */
    @CField("hard_remove")
    int hard_remove();

    /**
     * Sets the hard remove option.
     * @param value non-zero to remove files immediately; zero to rename and hide.
     */
    @CField("hard_remove")
    void hard_remove(int value);

    /**
     * @return whether to use the inode numbers provided by `getattr()` and `fill_dir()`.
     */
    @CField("use_ino")
    int use_ino();

    /**
     * Sets whether to use provided inode numbers.
     * @param value non-zero to use provided inodes; zero to ignore.
     */
    @CField("use_ino")
    void use_ino(int value);

    /**
     * @return whether to attempt to fill `d_ino` field in `readdir()` for cache lookups.
     */
    @CField("readdir_ino")
    int readdir_ino();

    /**
     * Sets the readdir inode behavior.
     * @param value non-zero to attempt `d_ino` fill; zero otherwise.
     */
    @CField("readdir_ino")
    void readdir_ino(int value);

    /**
     * @return whether to disable the use of kernel page cache for file content.
     */
    @CField("direct_io")
    int direct_io();

    /**
     * Sets the direct I/O option.
     * @param value non-zero to disable page cache; zero to use cache.
     */
    @CField("direct_io")
    void direct_io(int value);

    /**
     * @return whether to disable cache flush on every open.
     */
    @CField("kernel_cache")
    int kernel_cache();

    /**
     * Sets the kernel cache option.
     * @param value non-zero to disable cache flush; zero otherwise.
     */
    @CField("kernel_cache")
    void kernel_cache(int value);

    /**
     * @return whether to invalidate cached data on open if file metadata has changed.
     */
    @CField("auto_cache")
    int auto_cache();

    /**
     * Sets the auto cache option.
     * @param value non-zero to enable; zero to disable.
     */
    @CField("auto_cache")
    void auto_cache(int value);

    /**
     * @return whether the attribute cache timeout is set.
     */
    @CField("ac_attr_timeout_set")
    int ac_attr_timeout_set();

    /**
     * Sets the attribute cache timeout flag.
     * @param value non-zero to enable timeout; zero to disable.
     */
    @CField("ac_attr_timeout_set")
    void ac_attr_timeout_set(int value);

    /**
     * @return the attribute cache timeout duration.
     */
    @CField("ac_attr_timeout")
    long ac_attr_timeout();

    /**
     * Sets the attribute cache timeout.
     * @param value the timeout duration.
     */
    @CField("ac_attr_timeout")
    void ac_attr_timeout(long value);

    /**
     * @return whether certain file operations can proceed without path information.
     */
    @CField("nullpath_ok")
    int nullpath_ok();

    /**
     * Sets the null path option.
     * @param value non-zero to allow certain operations without paths.
     */
    @CField("nullpath_ok")
    void nullpath_ok(int value);
}
