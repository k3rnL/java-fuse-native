package com.k3rnl.fuse.callbacks;

import com.k3rnl.fuse.fuse.FuseFileInfo;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.CCharPointer;

/**
 * Callback for copy_file_range
 */
public interface CopyFileRangeFunction extends CFunctionPointer {
    @InvokeCFunctionPointer
    long invoke(CCharPointer pathIn, FuseFileInfo fiIn, long offIn,
                CCharPointer pathOut, FuseFileInfo fiOut, long offOut,
                long len, int flags);
}