package com.k3rnl.fuse.callbacks;

import com.k3rnl.fuse.fuse.FuseFileInfo;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.VoidPointer;

/**
 * Callback for poll
 */
public interface PollFunction extends CFunctionPointer {
    @InvokeCFunctionPointer
    int invoke(CCharPointer path, FuseFileInfo fi, VoidPointer ph, VoidPointer reventsp);
}