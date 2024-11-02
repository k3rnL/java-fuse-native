package com.k3rnl.fuse.libc;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.PointerBase;
import org.graalvm.word.WordFactory;

import java.util.List;

@CContext(LibC.Directives.class)
public class LibC {

    public static class Directives implements CContext.Directives {
        @Override
        public List<String> getHeaderFiles() {
            return List.of(
                    "<string.h>", "<fcntl.h>", "<sys/stat.h>", "<sys/statvfs.h>"
            );
        }
    }

    @CFunction
    public static native int memset(VoidPointer dest, int value, long count);

    public static <R extends PointerBase> R autoCast(PointerBase ptr) {
        return WordFactory.pointer(ptr.rawValue());
    }

}
