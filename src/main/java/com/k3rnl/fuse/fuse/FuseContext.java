package com.k3rnl.fuse.fuse;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.PointerBase;

@CContext(FuseLibrary.Directives.class)
@CStruct(value = "fuse_context", addStructKeyword = true)
public interface FuseContext extends PointerBase {

    @CField
    VoidPointer private_data();

}
