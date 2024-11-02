package com.k3rnl.fuse.callbacks;

import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.CCharPointer;

public interface AccessFunction extends CFunctionPointer {
    @InvokeCFunctionPointer
    int invoke(CCharPointer path, int mask);
}