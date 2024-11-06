package com.k3rnl.fuse.api;

import com.k3rnl.fuse.fuse.FuseFillDirFlags;
import com.k3rnl.fuse.libc.FileStat;
import org.graalvm.nativeimage.c.type.VoidPointer;

/**
 * The {@code FillDir} functional interface represents a callback used by the FUSE
 * {@code readdir} operation to fill directory entries in the directory listing buffer.
 *
 * <p>This interface is marked as a functional interface, allowing it to be used as the
 * assignment target for a lambda expression or method reference.</p>
 */
@FunctionalInterface
public interface FillDir {

    /**
     * Adds a directory entry to the buffer.
     *
     * @param buf     the buffer where directory entries are stored.
     * @param name    the name of the directory entry.
     * @param stat    the {@link FileStat} structure containing file attributes for the entry.
     * @param offset  the offset for the next directory entry.
     * @param flags   additional flags affecting the behavior of the directory filling, represented by {@link FuseFillDirFlags}.
     * @return an integer status code (0 for success, non-zero for failure).
     */
    int apply(VoidPointer buf, String name, FileStat stat, long offset, FuseFillDirFlags flags);
}
