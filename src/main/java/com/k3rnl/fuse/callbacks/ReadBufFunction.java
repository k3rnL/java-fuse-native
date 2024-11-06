package com.k3rnl.fuse.callbacks;

import com.k3rnl.fuse.fuse.FuseBufVec;
import com.k3rnl.fuse.fuse.FuseFileInfo;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.CCharPointer;

/**
 * Callback for read_buf
 */
public interface ReadBufFunction extends CFunctionPointer {
    @InvokeCFunctionPointer
    int invoke(CCharPointer path, FuseBufVec.FuseBufVecPointer buf, long size, long offset, FuseFileInfo fi);
}