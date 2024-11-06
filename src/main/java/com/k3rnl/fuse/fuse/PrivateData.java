package com.k3rnl.fuse.fuse;

import com.oracle.svm.core.c.struct.PinnedObjectField;
import org.graalvm.nativeimage.Isolate;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.c.struct.RawField;
import org.graalvm.nativeimage.c.struct.RawStructure;
import org.graalvm.word.PointerBase;

/**
 * The {@code PrivateData} interface represents a raw structure that holds private data in a FUSE-based
 * filesystem, specifically using GraalVM's native image features. This structure allows for the storage
 * and retrieval of data associated with the FUSE isolate and Java objects.
 */
@RawStructure
public interface PrivateData extends PointerBase {

    /**
     * @return the {@link Isolate} associated with the current GraalVM native image, allowing
     *         for isolation of the Java runtime within the FUSE context.
     */
    @RawField
    Isolate isolate();

    /**
     * Sets the {@link Isolate} for the FUSE context.
     *
     * @param value the isolate to associate with this structure.
     */
    @RawField
    void isolate(Isolate value);

    /**
     * @return the {@link ObjectHandle} representing a reference to a Java object
     *         stored in the native structure. This handle can be used to access or modify
     *         Java data associated with the filesystem.
     */
    @RawField
    ObjectHandle javaData();

    /**
     * Sets the {@link ObjectHandle} representing a Java object in the native structure.
     *
     * @param value the Java object handle to set in the structure.
     */
    @RawField
    void javaData(ObjectHandle value);
}
