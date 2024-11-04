package com.k3rnl.fuse.callbacks;

import com.k3rnl.fuse.libc.FileStat;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.VoidPointer;

public interface FillDirFunction extends CFunctionPointer {
    @InvokeCFunctionPointer
    int invoke(VoidPointer buf, CCharPointer name, FileStat stat, long off);
}