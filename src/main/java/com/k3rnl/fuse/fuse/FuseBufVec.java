package com.k3rnl.fuse.fuse;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CFieldAddress;
import org.graalvm.nativeimage.c.struct.CPointerTo;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;

/**
 * The {@code FuseBufVec} interface represents the native "fuse_bufvec" structure,
 * which is used to handle vectors of data buffers in the FUSE (Filesystem in Userspace) library.
 */
@CContext(FuseLibrary.Directives.class)
@CStruct(value = "fuse_bufvec", addStructKeyword = true)
public interface FuseBufVec extends PointerBase {

    /**
     * Pointer interface for {@code FuseBufVec}, allowing array-like access to multiple {@code FuseBufVec} instances.
     */
    @CPointerTo(FuseBufVec.class)
    public interface FuseBufVecPointer extends PointerBase {

        /**
         * Reads the {@code FuseBufVec} at the specified index.
         *
         * @param index the index of the {@code FuseBufVec} to read.
         * @return the {@code FuseBufVec} instance at the specified index.
         */
        FuseBufVec read(int index);

        /**
         * Writes a {@code FuseBufVec} value at the specified index.
         *
         * @param index the index to write the {@code FuseBufVec} to.
         * @param value the {@code FuseBufVec} instance to write.
         */
        void write(int index, FuseBufVec value);
    }

    /**
     * @return the number of buffers in the vector.
     */
    @CField("count")
    long count();

    /**
     * @return the index of the current buffer in the vector.
     */
    @CField("idx")
    long idx();

    /**
     * @return the offset within the current buffer.
     */
    @CField("off")
    long off();

    /**
     * @return a reference to the first {@code FuseBuf} in the vector.
     */
    @CFieldAddress("buf")
    FuseBuf buf();
}
