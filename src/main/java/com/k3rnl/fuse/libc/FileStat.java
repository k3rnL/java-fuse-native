package com.k3rnl.fuse.libc;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CFieldAddress;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;

/**
 * The {@code FileStat} interface represents the structure of the native "stat" structure,
 * providing fields to access file status information.
 */
@CContext(LibC.Directives.class)
@CStruct(value = "stat", addStructKeyword = true)
public interface FileStat extends PointerBase {

    /**
     * @return the device ID of the file.
     */
    @CField("st_dev")
    long st_dev();

    /**
     * Sets the device ID of the file.
     * @param value the device ID to set.
     */
    @CField("st_dev")
    void st_dev(long value);

    /**
     * @return the inode number of the file.
     */
    @CField("st_ino")
    long st_ino();

    /**
     * Sets the inode number of the file.
     * @param value the inode number to set.
     */
    @CField("st_ino")
    void st_ino(long value);

    /**
     * @return the file mode (permissions).
     */
    @CField("st_mode")
    int st_mode();

    /**
     * Sets the file mode (permissions).
     * @param value the file mode to set.
     */
    @CField("st_mode")
    void st_mode(int value);

    /**
     * @return the number of hard links to the file.
     */
    @CField("st_nlink")
    long st_nlink();

    /**
     * Sets the number of hard links to the file.
     * @param value the number of hard links to set.
     */
    @CField("st_nlink")
    void st_nlink(long value);

    /**
     * @return the user ID of the file owner.
     */
    @CField("st_uid")
    int st_uid();

    /**
     * Sets the user ID of the file owner.
     * @param value the user ID to set.
     */
    @CField("st_uid")
    void st_uid(int value);

    /**
     * @return the group ID of the file owner.
     */
    @CField("st_gid")
    int st_gid();

    /**
     * Sets the group ID of the file owner.
     * @param value the group ID to set.
     */
    @CField("st_gid")
    void st_gid(int value);

    /**
     * @return the device ID (if the file is a special file).
     */
    @CField("st_rdev")
    long st_rdev();

    /**
     * Sets the device ID (if the file is a special file).
     * @param value the device ID to set.
     */
    @CField("st_rdev")
    void st_rdev(long value);

    /**
     * @return the total size of the file, in bytes.
     */
    @CField("st_size")
    long st_size();

    /**
     * Sets the total size of the file, in bytes.
     * @param value the size in bytes to set.
     */
    @CField("st_size")
    void st_size(long value);

    /**
     * @return the block size for filesystem I/O.
     */
    @CField("st_blksize")
    long st_blksize();

    /**
     * Sets the block size for filesystem I/O.
     * @param value the block size to set.
     */
    @CField("st_blksize")
    void st_blksize(long value);

    /**
     * @return the number of 512B blocks allocated for the file.
     */
    @CField("st_blocks")
    long st_blocks();

    /**
     * Sets the number of 512B blocks allocated for the file.
     * @param value the number of blocks to set.
     */
    @CField("st_blocks")
    void st_blocks(long value);

    /**
     * @return a {@code TimeSpec} representing the time of the last access.
     */
    @CFieldAddress("st_atime")
    TimeSpec st_atime();

    /**
     * @return a {@code TimeSpec} representing the time of the last modification.
     */
    @CFieldAddress("st_mtime")
    TimeSpec st_mtime();

    /**
     * @return a {@code TimeSpec} representing the time of the last status change.
     */
    @CFieldAddress("st_ctime")
    TimeSpec st_ctime();
}
