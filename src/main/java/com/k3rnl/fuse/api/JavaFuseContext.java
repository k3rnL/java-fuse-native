package com.k3rnl.fuse.api;

import org.graalvm.nativeimage.Isolate;

public class JavaFuseContext {

    Isolate isolate;
    JavaFuseOperations operations;

    public JavaFuseContext(Isolate isolate, JavaFuseOperations operations) {
        this.isolate = isolate;
        this.operations = operations;
    }

}
