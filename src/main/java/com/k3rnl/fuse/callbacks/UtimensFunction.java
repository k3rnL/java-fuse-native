package com.k3rnl.fuse.callbacks;

import com.k3rnl.fuse.fuse.FuseFileInfo;
import com.k3rnl.fuse.libc.TimeSpec;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.CCharPointer;

public interface UtimensFunction extends CFunctionPointer {
    @InvokeCFunctionPointer
    int invoke(CCharPointer path, TimeSpec[] timespec, FuseFileInfo fi);
}