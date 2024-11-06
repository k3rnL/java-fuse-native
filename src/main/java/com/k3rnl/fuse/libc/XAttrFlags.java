package com.k3rnl.fuse.libc;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.constant.CEnum;

@CContext(LibC.Directives.class)
public class XAttrFlags {

    @CConstant("XATTR_CREATE")
    public static native int XATTR_CREATE();

    @CConstant("XATTR_REPLACE")
    public static native int XATTR_REPLACE();

}
