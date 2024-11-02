package com.k3rnl.fuse.fuse;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CFieldAddress;
import org.graalvm.nativeimage.c.struct.CPointerTo;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;

@CContext(FuseLibrary.Directives.class)
@CStruct(value = "fuse_bufvec", addStructKeyword = true)
public interface FuseBufVec extends PointerBase {

    @CPointerTo(FuseBufVec.class)
    public interface FuseBufVecPointer extends PointerBase {
        FuseBufVec read(int index);
        void write(int index, FuseBufVec value);
    }

    @CField("count")
    long count();

    @CField("idx")
    long idx();

    @CField("off")
    long off();

    @CFieldAddress("buf")
    FuseBuf buf();

}
