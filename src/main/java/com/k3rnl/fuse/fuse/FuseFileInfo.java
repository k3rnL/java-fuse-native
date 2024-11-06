package com.k3rnl.fuse.fuse;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CBitfield;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;

/**
 * The {@code FuseFileInfo} interface represents the native "fuse_file_info" structure,
 * which provides information about an open file in the FUSE (Filesystem in Userspace) library.
 */
@CContext(FuseLibrary.Directives.class)
@CStruct(value = "fuse_file_info", addStructKeyword = true)
public interface FuseFileInfo extends PointerBase {

    /**
     * @return the open flags associated with the file (e.g., read, write).
     */
    @CField("flags")
    int flags();

    /**
     * Sets the open flags for the file.
     * @param value the open flags to set.
     */
    @CField("flags")
    void flags(int value);

    /**
     * @return the file handle, a unique identifier for the open file.
     */
    @CField("fh")
    long fh();

    /**
     * Sets the file handle.
     * @param value the file handle to set.
     */
    @CField("fh")
    void fh(long value);

    /**
     * @return an indicator for the writepage operation.
     */
    @CBitfield("writepage")
    int writepage();

    /**
     * Sets the writepage operation indicator.
     * @param value the indicator value.
     */
    @CBitfield("writepage")
    void writepage(int value);

    /**
     * @return whether direct I/O is enabled, bypassing the kernel page cache.
     */
    @CBitfield("direct_io")
    boolean direct_io();

    /**
     * Sets the direct I/O option.
     * @param value {@code true} to enable direct I/O; {@code false} otherwise.
     */
    @CBitfield("direct_io")
    void direct_io(boolean value);

    /**
     * @return whether to keep the file data in the cache.
     */
    @CBitfield("keep_cache")
    boolean keep_cache();

    /**
     * Sets the keep cache option.
     * @param value {@code true} to keep cache; {@code false} otherwise.
     */
    @CBitfield("keep_cache")
    void keep_cache(boolean value);

    /**
     * @return the flush flag, indicating if data should be flushed to storage.
     */
    @CBitfield("flush")
    boolean flush();

    /**
     * Sets the flush flag.
     * @param value {@code true} to enable flush; {@code false} otherwise.
     */
    @CBitfield("flush")
    void flush(boolean value);

    /**
     * @return whether the file is non-seekable.
     */
    @CBitfield("nonseekable")
    boolean nonseekable();

    /**
     * Sets the non-seekable flag.
     * @param value {@code true} if the file is non-seekable; {@code false} otherwise.
     */
    @CBitfield("nonseekable")
    void nonseekable(boolean value);

    /**
     * @return whether the file should be released from the flock (file lock).
     */
    @CBitfield("flock_release")
    boolean flock_release();

    /**
     * Sets the flock release flag.
     * @param value {@code true} to release the file lock; {@code false} otherwise.
     */
    @CBitfield("flock_release")
    void flock_release(boolean value);

    /*
    @CField("cache_readdir")
    boolean cache_readdir();   // Cache readdir entries

    @CField("cache_readdir")
    void cache_readdir(boolean value);
    */

    /**
     * @return the lock owner ID, used for managing file locks.
     */
    @CField("lock_owner")
    long lock_owner();

    /**
     * Sets the lock owner ID.
     * @param value the lock owner ID.
     */
    @CField("lock_owner")
    void lock_owner(long value);
}
