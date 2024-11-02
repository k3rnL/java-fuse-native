package com.k3rnl.fuse.fuse;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.PointerBase;

@CContext(FuseLibrary.Directives.class)
@CStruct(value = "fuse_buf", addStructKeyword = true)
public interface FuseBuf extends PointerBase {

    @CField("size")
    long size();

    @CField("flags")
    int flags();

    @CField("mem")
    VoidPointer mem();

    @CField("fd")
    int fd();

    @CField("pos")
    long pos();

}
