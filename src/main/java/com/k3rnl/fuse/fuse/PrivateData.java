package com.k3rnl.fuse.fuse;

import com.oracle.svm.core.c.struct.PinnedObjectField;
import org.graalvm.nativeimage.Isolate;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.c.struct.RawField;
import org.graalvm.nativeimage.c.struct.RawStructure;
import org.graalvm.word.PointerBase;

@RawStructure()
public interface PrivateData extends PointerBase {

    @RawField
    Isolate isolate();
    @RawField
    void isolate(Isolate value);

    @RawField
    ObjectHandle javaData();
    @RawField
    void javaData(ObjectHandle value);

}
