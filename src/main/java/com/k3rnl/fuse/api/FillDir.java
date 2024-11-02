package com.k3rnl.fuse.api;

import org.graalvm.nativeimage.c.type.VoidPointer;

@FunctionalInterface
public interface FillDir {

    int apply(VoidPointer buf, String name, VoidPointer stat, long offset);

}
