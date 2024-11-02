package com.k3rnl.fuse.fuse;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CBitfield;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;

@CContext(FuseLibrary.Directives.class)
@CStruct(value = "fuse_file_info", addStructKeyword = true)
public interface FuseFileInfo extends PointerBase {

    @CField("flags")
    int flags();   // Open flags

    @CField("flags")
    void flags(int value);

    @CField("fh")
    long fh();   // File handle

    @CField("fh")
    void fh(long value);

    @CBitfield("writepage")
    int writepage();   // Writepage operation indicator

    @CBitfield("writepage")
    void writepage(int value);

    // Bitfields are treated as booleans in Java, since each bitfield in C is typically used as a boolean flag
    @CBitfield("direct_io")
    boolean direct_io();   // Direct I/O

    @CBitfield("direct_io")
    void direct_io(boolean value);

    @CBitfield("keep_cache")
    boolean keep_cache();   // Keep cache

    @CBitfield("keep_cache")
    void keep_cache(boolean value);

    @CBitfield("flush")
    boolean flush();   // Flush flag

    @CBitfield("flush")
    void flush(boolean value);

    @CBitfield("nonseekable")
    boolean nonseekable();   // Non-seekable file

    @CBitfield("nonseekable")
    void nonseekable(boolean value);

    @CBitfield("flock_release")
    boolean flock_release();   // Flock release

    @CBitfield("flock_release")
    void flock_release(boolean value);

//    @CField("cache_readdir")
//    boolean cache_readdir();   // Cache readdir entries
//
//    @CField("cache_readdir")
//    void cache_readdir(boolean value);

    @CField("lock_owner")
    long lock_owner();   // Lock owner

    @CField("lock_owner")
    void lock_owner(long value);
}
