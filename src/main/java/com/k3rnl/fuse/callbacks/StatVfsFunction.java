package com.k3rnl.fuse.callbacks;

import com.k3rnl.fuse.libc.StatVFS;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.CCharPointer;

public interface StatVfsFunction extends CFunctionPointer {
    @InvokeCFunctionPointer
    int invoke(CCharPointer path, StatVFS statVFS);
}