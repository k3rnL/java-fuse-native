package com.k3rnl.fuse.callbacks;

import com.k3rnl.fuse.fuse.FuseFileInfo;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.CCharPointer;

/**
 * Callback for truncate
 */
public interface TruncateFunction extends CFunctionPointer {
    @InvokeCFunctionPointer
    int invoke(CCharPointer path, long offset, FuseFileInfo fi);
}