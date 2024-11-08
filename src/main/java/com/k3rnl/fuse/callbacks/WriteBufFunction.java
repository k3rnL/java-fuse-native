package com.k3rnl.fuse.callbacks;

import com.k3rnl.fuse.fuse.FuseBufVec;
import com.k3rnl.fuse.fuse.FuseFileInfo;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.CCharPointer;

/**
 * Callback for write_buf
 */
public interface WriteBufFunction extends CFunctionPointer {
    @InvokeCFunctionPointer
    int invoke(CCharPointer path, FuseBufVec buf, long offset, FuseFileInfo fi);
}