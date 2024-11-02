package com.k3rnl.fuse.libc;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;

@CContext(LibC.Directives.class)
@CStruct(value = "flock", addStructKeyword = true)
public interface Flock extends PointerBase {

    @CField("l_type")
    short l_type();   // Specifies the type of the lock; one of F_RDLCK, F_WRLCK, or F_UNLCK.
    @CField("l_type")
    void l_type(short value);

    @CField("l_whence")
    short l_whence();   // This corresponds to the whence argument to fseek or lseek, and specifies what the offset is relative to. Its value can be one of SEEK_SET, SEEK_CUR, or SEEK_END.
    @CField("l_whence")
    void l_whence(short value);

    @CField("l_start")
    long l_start();   // This specifies the offset of the start of the region to which the lock applies, and is given in bytes relative to the point specified by the l_whence member.
    @CField("l_start")
    void l_start(long value);

    @CField("l_len")
    long l_len();   // This specifies the length of the region to be locked. A value of 0 is treated specially; it means the region extends to the end of the file.
    @CField("l_len")
    void l_len(long value);

    @CField("l_pid")
    int l_pid();   // This field is the process ID (see Process Creation Concepts) of the process holding the lock. It is filled in by calling fcntl with the F_GETLK command, but is ignored when making a lock.
    // If the conflicting lock is an open file description lock (see Open File Description Locks), then this field will be set to -1.
    @CField("l_pid")
    void l_pid(int value);

}
