package com.k3rnl.fuse.libc;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;

@CContext(LibC.Directives.class)
public class FlockFlags {

    @CConstant("F_GETLK")
    public static native int F_GETLK();

    @CConstant("F_SETLK")
    public static native int F_SETLK();

    @CConstant("F_SETLKW")
    public static native int F_SETLKW();

    @CConstant("F_RDLCK")
    public static native int F_RDLCK();

    @CConstant("F_WRLCK")
    public static native int F_WRLCK();

    @CConstant("F_UNLCK")
    public static native int F_UNLCK();

    @CConstant("SEEK_SET")
    public static native int SEEK_SET();

    @CConstant("SEEK_CUR")
    public static native int SEEK_CUR();

    @CConstant("SEEK_END")
    public static native int SEEK_END();

}
