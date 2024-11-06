package com.k3rnl.fuse.fuse;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.PointerBase;

/**
 * The {@code FuseBuf} interface represents the native "fuse_buf" structure,
 * which is used to handle data buffers in the FUSE (Filesystem in Userspace) library.
 */
@CContext(FuseLibrary.Directives.class)
@CStruct(value = "fuse_buf", addStructKeyword = true)
public interface FuseBuf extends PointerBase {

    /**
     * @return the size of the buffer in bytes.
     */
    @CField("size")
    long size();

    /**
     * @return the flags associated with the buffer.
     */
    @CField("flags")
    int flags();

    /**
     * @return a {@link VoidPointer} pointing to the memory address of the buffer.
     */
    @CField("mem")
    VoidPointer mem();

    /**
     * @return the file descriptor associated with this buffer.
     */
    @CField("fd")
    int fd();

    /**
     * @return the position within the file for this buffer, typically used with file operations.
     */
    @CField("pos")
    long pos();
}
