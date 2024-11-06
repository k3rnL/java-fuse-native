package com.k3rnl.fuse.libc;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;

/**
 * The {@code StatVFS} interface represents the native "statvfs" structure,
 * providing information about a filesystem.
 */
@CContext(LibC.Directives.class)
@CStruct(value = "statvfs", addStructKeyword = true)
public interface StatVFS extends PointerBase {

    /**
     * @return the file system block size.
     */
    @CField("f_bsize")
    long f_bsize();

    /**
     * Sets the file system block size.
     * @param value the block size to set.
     */
    @CField("f_bsize")
    void f_bsize(long value);

    /**
     * @return the fundamental file system block size.
     */
    @CField("f_frsize")
    long f_frsize();

    /**
     * Sets the fundamental file system block size.
     * @param value the fundamental block size to set.
     */
    @CField("f_frsize")
    void f_frsize(long value);

    /**
     * @return the total number of blocks on the file system, in units of {@code f_frsize}.
     */
    @CField("f_blocks")
    long f_blocks();

    /**
     * Sets the total number of blocks on the file system.
     * @param value the number of blocks to set, in units of {@code f_frsize}.
     */
    @CField("f_blocks")
    void f_blocks(long value);

    /**
     * @return the number of free blocks.
     */
    @CField("f_bfree")
    long f_bfree();

    /**
     * Sets the number of free blocks.
     * @param value the number of free blocks to set.
     */
    @CField("f_bfree")
    void f_bfree(long value);

    /**
     * @return the number of free blocks available to unprivileged users.
     */
    @CField("f_bavail")
    long f_bavail();

    /**
     * Sets the number of free blocks for unprivileged users.
     * @param value the number of available blocks to set.
     */
    @CField("f_bavail")
    void f_bavail(long value);

    /**
     * @return the total number of inodes.
     */
    @CField("f_files")
    long f_files();

    /**
     * Sets the total number of inodes.
     * @param value the number of inodes to set.
     */
    @CField("f_files")
    void f_files(long value);

    /**
     * @return the number of free inodes.
     */
    @CField("f_ffree")
    long f_ffree();

    /**
     * Sets the number of free inodes.
     * @param value the number of free inodes to set.
     */
    @CField("f_ffree")
    void f_ffree(long value);

    /**
     * @return the number of free inodes available to unprivileged users.
     */
    @CField("f_favail")
    long f_favail();

    /**
     * Sets the number of free inodes for unprivileged users.
     * @param value the number of available inodes to set.
     */
    @CField("f_favail")
    void f_favail(long value);

    /**
     * @return the filesystem ID.
     */
    @CField("f_fsid")
    long f_fsid();

    /**
     * Sets the filesystem ID.
     * @param value the filesystem ID to set.
     */
    @CField("f_fsid")
    void f_fsid(long value);

    /**
     * @return the mount flags.
     */
    @CField("f_flag")
    long f_flag();

    /**
     * Sets the mount flags.
     * @param value the mount flags to set.
     */
    @CField("f_flag")
    void f_flag(long value);

    /**
     * @return the maximum length of filenames.
     */
    @CField("f_namemax")
    long f_namemax();

    /**
     * Sets the maximum filename length.
     * @param value the maximum filename length to set.
     */
    @CField("f_namemax")
    void f_namemax(long value);
}
