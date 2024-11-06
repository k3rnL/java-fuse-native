package com.k3rnl.fuse.libc;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;

/**
 * The {@code TimeSpec} interface represents the native "timespec" structure,
 * which provides a way to specify time with nanosecond precision.
 */
@CContext(LibC.Directives.class)
@CStruct(value = "timespec", addStructKeyword = true)
public interface TimeSpec extends PointerBase {

    /**
     * Returns the address of a specific {@code TimeSpec} instance in an array.
     *
     * @param index the index of the {@code TimeSpec} instance in the array.
     * @return the address of the {@code TimeSpec} instance at the specified index.
     */
    TimeSpec addressOf(int index);

    /**
     * @return the number of seconds since the epoch (UNIX time).
     */
    @CField("tv_sec")
    long tv_sec();

    /**
     * Sets the number of seconds since the epoch (UNIX time).
     *
     * @param tv_sec the seconds to set.
     */
    @CField("tv_sec")
    void tv_sec(long tv_sec);

    /**
     * @return the number of nanoseconds, providing additional precision.
     */
    @CField("tv_nsec")
    long tv_nsec();

    /**
     * Sets the number of nanoseconds.
     *
     * @param tv_nsec the nanoseconds to set.
     */
    @CField("tv_nsec")
    void tv_nsec(long tv_nsec);
}
