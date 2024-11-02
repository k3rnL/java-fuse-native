package com.k3rnl.fuse.callbacks;

import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.CCharPointer;

public interface RenameFunction extends CFunctionPointer {
    @InvokeCFunctionPointer
    int invoke(CCharPointer path, CCharPointer newPath, int flags);
}