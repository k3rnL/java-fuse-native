package com.k3rnl.fuse.fuse;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.PointerBase;

/**
 * The {@code FuseContext} interface represents the native "fuse_context" structure,
 * which provides context information for the FUSE (Filesystem in Userspace) library,
 * related to the current operation.
 */
@CContext(FuseLibrary.Directives.class)
@CStruct(value = "fuse_context", addStructKeyword = true)
public interface FuseContext extends PointerBase {

    /**
     * @return a pointer to the private data associated with the filesystem, often used to store custom data.
     */
    @CField
    VoidPointer private_data();

    /**
     * @return the umask of the calling process, which is used to set file creation permissions.
     */
    @CField
    int umask();

    /**
     * @return the process ID (PID) of the calling thread, which identifies the specific process making the request.
     */
    @CField
    int pid();

    /**
     * @return the user ID (UID) of the calling process.
     */
    @CField
    int uid();

    /**
     * @return the group ID (GID) of the calling process.
     */
    @CField
    int gid();
}
