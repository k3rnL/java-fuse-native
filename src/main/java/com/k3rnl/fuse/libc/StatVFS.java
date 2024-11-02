package com.k3rnl.fuse.libc;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;


@CContext(LibC.Directives.class)
@CStruct(value = "statvfs", addStructKeyword = true)
public interface StatVFS extends PointerBase {

    @CField("f_bsize")
    long f_bsize();   // File system block size.
    @CField("f_bsize")
    void f_bsize(long value);

    @CField("f_frsize")
    long f_frsize();   // Fundamental file system block size.
    @CField("f_frsize")
    void f_frsize(long value);

    @CField("f_blocks")
    long f_blocks();   // Total number of blocks on file system in units of f_frsize.
    @CField("f_blocks")
    void f_blocks(long value);

    @CField("f_bfree")
    long f_bfree();   // Number of free blocks
    @CField("f_bfree")
    void f_bfree(long value);

    @CField("f_bavail")
    long f_bavail();   // Number of free blocks for unprivileged users
    @CField("f_bavail")
    void f_bavail(long value);

    @CField("f_files")
    long f_files();   // Number of inodes
    @CField("f_files")
    void f_files(long value);

    @CField("f_ffree")
    long f_ffree();   // Number of free inodes
    @CField("f_ffree")
    void f_ffree(long value);

    @CField("f_favail")
    long f_favail();   // Number of free inodes for unprivileged users
    @CField("f_favail")
    void f_favail(long value);

    @CField("f_fsid")
    long f_fsid();   // Filesystem ID
    @CField("f_fsid")
    void f_fsid(long value);

    @CField("f_flag")
    long f_flag();   // Mount flags
    @CField("f_flag")
    void f_flag(long value);

    @CField("f_namemax")
    long f_namemax();   // Maximum filename length
    @CField("f_namemax")
    void f_namemax(long value);
}