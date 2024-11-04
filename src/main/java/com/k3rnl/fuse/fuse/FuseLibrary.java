package com.k3rnl.fuse.fuse;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.function.CLibrary;
import org.graalvm.nativeimage.c.struct.SizeOf;
import org.graalvm.nativeimage.c.type.CCharPointerPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;
import org.graalvm.nativeimage.c.type.VoidPointer;

import java.util.List;

@CContext(FuseLibrary.Directives.class)
public class FuseLibrary {

    @CFunction(value = "fuse_main_real")
    public static native int fuse_main_real(int argc, CCharPointerPointer argv, FuseOperations op, int op_size, VoidPointer user_data);

    public static int fuseMain(List<String> args, FuseOperations op, VoidPointer user_data) {
        try (var cargs = CTypeConversion.toCStrings(args.toArray(new String[0]))) {
           return fuse_main_real(args.size(), cargs.get(), op, SizeOf.get(FuseOperations.class), user_data);
        }
    }

    @CFunction(value = "fuse_get_context", transition = CFunction.Transition.NO_TRANSITION)
    public static native FuseContext fuse_get_context();

    public static class Directives implements CContext.Directives {
        @Override
        public List<String> getHeaderFiles() {
            return List.of("<fuse3/fuse.h>");
        }

        @Override
        public List<String> getLibraries() {
            return List.of("fuse3");
        }

        @Override
        public List<String> getOptions() {
            return List.of("-D_FILE_OFFSET_BITS=64", "-DFUSE_USE_VERSION=30");
        }
    }

}