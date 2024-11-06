package com.k3rnl.fuse.callbacks;

import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.VoidPointer;

/**
 * Callback for destroy
 */
public interface DestroyFunction extends CFunctionPointer {
    @InvokeCFunctionPointer
    int invoke(VoidPointer privateData);
}