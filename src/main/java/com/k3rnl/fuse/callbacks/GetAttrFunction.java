package com.k3rnl.fuse.callbacks;

import com.k3rnl.fuse.libc.FileStat;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.CCharPointer;

/**
 * Callback for getAttr
 */
public interface GetAttrFunction extends CFunctionPointer {
    @InvokeCFunctionPointer
    int invoke(CCharPointer path, FileStat stat);
}