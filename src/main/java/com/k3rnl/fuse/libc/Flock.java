package com.k3rnl.fuse.libc;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;

/**
 * The {@code Flock} interface represents the native "flock" structure,
 * used to specify details for file locking in POSIX-compliant systems.
 */
@CContext(LibC.Directives.class)
@CStruct(value = "flock", addStructKeyword = true)
public interface Flock extends PointerBase {

    /**
     * @return the type of the lock, which can be one of {@code F_RDLCK} (read lock), {@code F_WRLCK} (write lock), or {@code F_UNLCK} (unlock).
     */
    @CField("l_type")
    short l_type();

    /**
     * Sets the type of the lock.
     * @param value the type of the lock to set, which can be one of {@code F_RDLCK}, {@code F_WRLCK}, or {@code F_UNLCK}.
     */
    @CField("l_type")
    void l_type(short value);

    /**
     * @return the starting point for the lock's offset, which corresponds to the {@code whence} argument in {@code fseek} or {@code lseek}.
     *         It specifies what the offset is relative to, with possible values being {@code SEEK_SET}, {@code SEEK_CUR}, or {@code SEEK_END}.
     */
    @CField("l_whence")
    short l_whence();

    /**
     * Sets the starting point for the lock's offset.
     * @param value the whence value, which can be {@code SEEK_SET}, {@code SEEK_CUR}, or {@code SEEK_END}.
     */
    @CField("l_whence")
    void l_whence(short value);

    /**
     * @return the offset of the start of the region to which the lock applies, in bytes relative to the position specified by {@code l_whence}.
     */
    @CField("l_start")
    long l_start();

    /**
     * Sets the offset of the start of the region to which the lock applies.
     * @param value the offset in bytes, relative to {@code l_whence}.
     */
    @CField("l_start")
    void l_start(long value);

    /**
     * @return the length of the region to be locked. A value of 0 means the lock extends to the end of the file.
     */
    @CField("l_len")
    long l_len();

    /**
     * Sets the length of the region to be locked.
     * @param value the length of the region in bytes, with 0 indicating the region extends to the end of the file.
     */
    @CField("l_len")
    void l_len(long value);

    /**
     * @return the process ID of the process holding the lock. This is filled in by a call to {@code fcntl} with the {@code F_GETLK} command.
     *         If the conflicting lock is an open file description lock, this field will be set to -1.
     */
    @CField("l_pid")
    int l_pid();

    /**
     * Sets the process ID for the lock holder.
     * @param value the process ID of the lock holder; ignored when setting a lock.
     */
    @CField("l_pid")
    void l_pid(int value);
}
