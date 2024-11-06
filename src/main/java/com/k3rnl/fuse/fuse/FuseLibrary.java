package com.k3rnl.fuse.fuse;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.function.CLibrary;
import org.graalvm.nativeimage.c.struct.SizeOf;
import org.graalvm.nativeimage.c.type.CCharPointerPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;
import org.graalvm.nativeimage.c.type.VoidPointer;

import java.util.List;

/**
 * The {@code FuseLibrary} class provides access to key FUSE (Filesystem in Userspace) functions
 * and configurations, enabling integration between FUSE and Java using GraalVM.
 */
@CContext(FuseLibrary.Directives.class)
public class FuseLibrary {

    /**
     * Calls the native `fuse_main_real` function, which initializes and mounts the filesystem.
     *
     * @param argc       the argument count, representing the number of command-line arguments.
     * @param argv       the arguments for the FUSE filesystem.
     * @param op         the FUSE operations interface that defines filesystem behavior.
     * @param op_size    the size of the {@code FuseOperations} structure.
     * @param user_data  user-defined data passed to each operation.
     * @return an integer status code (0 on success, non-zero on failure).
     */
    @CFunction(value = "fuse_main_real")
    public static native int fuse_main_real(int argc, CCharPointerPointer argv, FuseOperations op, int op_size, VoidPointer user_data);

    /**
     * A higher-level method for initializing and mounting the FUSE filesystem.
     * Converts a list of Java strings to native strings for the `fuse_main_real` function.
     *
     * @param args       the command-line arguments for FUSE.
     * @param op         the FUSE operations interface that defines filesystem behavior.
     * @param user_data  user-defined data passed to each operation.
     * @return an integer status code (0 on success, non-zero on failure).
     */
    public static int fuseMain(List<String> args, FuseOperations op, VoidPointer user_data) {
        try (var cargs = CTypeConversion.toCStrings(args.toArray(new String[0]))) {
            return fuse_main_real(args.size(), cargs.get(), op, SizeOf.get(FuseOperations.class), user_data);
        }
    }

    /**
     * Retrieves the current FUSE context, containing information about the calling process and other details.
     *
     * @return the {@code FuseContext} associated with the current FUSE request.
     */
    @CFunction(value = "fuse_get_context", transition = CFunction.Transition.NO_TRANSITION)
    public static native FuseContext fuse_get_context();

    /**
     * The {@code Directives} class provides the necessary directives for GraalVM to locate and configure
     * the FUSE C library headers, libraries, and compilation options.
     */
    public static class Directives implements CContext.Directives {

        /**
         * @return the list of required C header files for FUSE.
         */
        @Override
        public List<String> getHeaderFiles() {
            return List.of("<fuse3/fuse.h>");
        }

        /**
         * @return the list of libraries to link with, in this case, the FUSE library.
         */
        @Override
        public List<String> getLibraries() {
            return List.of("fuse3");
        }

        /**
         * @return the compilation options required for compatibility with FUSE.
         */
        @Override
        public List<String> getOptions() {
            return List.of("-D_FILE_OFFSET_BITS=64", "-DFUSE_USE_VERSION=30");
        }
    }
}
