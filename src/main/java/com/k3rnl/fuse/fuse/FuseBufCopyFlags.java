package com.k3rnl.fuse.fuse;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CEnum;
import org.graalvm.nativeimage.c.constant.CEnumLookup;
import org.graalvm.nativeimage.c.constant.CEnumValue;

@CContext(FuseLibrary.Directives.class)
@CEnum(value = "fuse_buf_copy_flags", addEnumKeyword = true)
public enum FuseBufCopyFlags {

    FUSE_BUF_NO_SPLICE,
    FUSE_BUF_FORCE_SPLICE,
    FUSE_BUF_SPLICE_MOVE,
    FUSE_BUF_SPLICE_NONBLOCK;

    @CEnumValue
    public native int getCValue();

    @CEnumLookup
    public static native FuseBufCopyFlags fromCValue(int cValue);

}
