package com.k3rnl.fuse.libc;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;


@CContext(LibC.Directives.class)
@CStruct(value = "stat", addStructKeyword = true)
public interface FileStat extends PointerBase {

    @CField("st_dev")
    long st_dev();   // Device ID

    @CField("st_dev")
    void st_dev(long value);

    @CField("st_ino")
    long st_ino();   // Inode number

    @CField("st_ino")
    void st_ino(long value);

    @CField("st_mode")
    int st_mode();   // File mode (permissions)

    @CField("st_mode")
    void st_mode(int value);

    @CField("st_nlink")
    long st_nlink();   // Number of hard links

    @CField("st_nlink")
    void st_nlink(long value);

    @CField("st_uid")
    int st_uid();   // User ID of the owner

    @CField("st_uid")
    void st_uid(int value);

    @CField("st_gid")
    int st_gid();   // Group ID of the owner

    @CField("st_gid")
    void st_gid(int value);

    @CField("st_rdev")
    long st_rdev();   // Device ID (if special file)

    @CField("st_rdev")
    void st_rdev(long value);

    @CField("st_size")
    long st_size();   // Total size, in bytes

    @CField("st_size")
    void st_size(long value);

    @CField("st_blksize")
    long st_blksize();   // Block size for filesystem I/O

    @CField("st_blksize")
    void st_blksize(long value);

    @CField("st_blocks")
    long st_blocks();   // Number of 512B blocks allocated

    @CField("st_blocks")
    void st_blocks(long value);

    @CField("st_atime")
    long st_atime();   // Time of last access

    @CField("st_atime")
    void st_atime(long value);

    @CField("st_mtime")
    long st_mtime();   // Time of last modification

    @CField("st_mtime")
    void st_mtime(long value);

    @CField("st_ctime")
    long st_ctime();   // Time of last status change

    @CField("st_ctime")
    void st_ctime(long value);
}