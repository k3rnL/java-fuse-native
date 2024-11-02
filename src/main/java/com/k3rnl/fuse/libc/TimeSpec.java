package com.k3rnl.fuse.libc;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;

@CContext(LibC.Directives.class)
@CStruct(value = "timespec", addStructKeyword = true)
public interface TimeSpec extends PointerBase {

    TimeSpec addressOf(int index);

    @CField("tv_sec")
    long tv_sec();
    @CField("tv_sec")
    void tv_sec(long tv_sec);

    @CField("tv_nsec")
    long tv_nsec();
    @CField("tv_nsec")
    void tv_nsec(long tv_nsec);

}
