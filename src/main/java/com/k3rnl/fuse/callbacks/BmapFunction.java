package com.k3rnl.fuse.callbacks;

import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.VoidPointer;

public interface BmapFunction extends CFunctionPointer {
    @InvokeCFunctionPointer
    int invoke(CCharPointer path, long blockSize, VoidPointer idx);
}