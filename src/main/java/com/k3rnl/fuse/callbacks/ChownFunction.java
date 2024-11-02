package com.k3rnl.fuse.callbacks;

import com.k3rnl.fuse.fuse.FuseFileInfo;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.CCharPointer;

public interface ChownFunction extends CFunctionPointer {
    @InvokeCFunctionPointer
    int invoke(CCharPointer path, int uid, int gid, FuseFileInfo fi);
}