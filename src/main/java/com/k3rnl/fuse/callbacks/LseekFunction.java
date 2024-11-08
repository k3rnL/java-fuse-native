package com.k3rnl.fuse.callbacks;

import com.k3rnl.fuse.fuse.FuseFileInfo;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.CCharPointer;

/**
 * Callback for lseek
 */
public interface LseekFunction extends CFunctionPointer {
    @InvokeCFunctionPointer
    long invoke(CCharPointer path, long offset, int whence, FuseFileInfo fi);
}