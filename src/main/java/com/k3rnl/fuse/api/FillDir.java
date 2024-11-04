package com.k3rnl.fuse.api;

import com.k3rnl.fuse.libc.FileStat;
import org.graalvm.nativeimage.c.type.VoidPointer;

@FunctionalInterface
public interface FillDir {

    int apply(VoidPointer buf, String name, FileStat stat, long offset);

}
