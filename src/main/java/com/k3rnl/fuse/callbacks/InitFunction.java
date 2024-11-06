package com.k3rnl.fuse.callbacks;

import com.k3rnl.fuse.fuse.FuseConfig;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.VoidPointer;

/**
 * Callback for init
 */
public interface InitFunction extends CFunctionPointer {
    @InvokeCFunctionPointer
    VoidPointer invoke(VoidPointer conn, FuseConfig config);
}