package com.k3rnl.fuse.libc;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.PointerBase;
import org.graalvm.word.WordFactory;

import java.util.List;

/**
 * The {@code LibC} class provides access to native C library functions and utilities,
 * including memory operations and file manipulation functions.
 */
@CContext(LibC.Directives.class)
public class LibC {

    /**
     * Defines the directives for including necessary C headers.
     */
    public static class Directives implements CContext.Directives {
        @Override
        public List<String> getHeaderFiles() {
            return List.of(
                    "<string.h>", "<fcntl.h>", "<sys/stat.h>", "<sys/statvfs.h>"
            );
        }
    }

    /**
     * Sets a block of memory to a specified value.
     *
     * @param dest  the destination pointer where memory will be set.
     * @param value the value to set, typically interpreted as an unsigned char.
     * @param count the number of bytes to set to {@code value}.
     * @return an integer result from the native {@code memset} function (typically 0 on success).
     */
    @CFunction
    public static native int memset(VoidPointer dest, int value, long count);

    /**
     * Casts a {@link PointerBase} to a different pointer type.
     *
     * @param ptr the pointer to cast.
     * @param <R> the desired pointer type.
     * @return the casted pointer as the specified type.
     */
    public static <R extends PointerBase> R autoCast(PointerBase ptr) {
        return WordFactory.pointer(ptr.rawValue());
    }
}
