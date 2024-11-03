package com.k3rnl.fuse;

import com.k3rnl.fuse.api.JavaFuseOperations;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeReflection;

import java.lang.reflect.Method;

public class FuseFeature implements Feature {

    @Override
    public void beforeAnalysis(BeforeAnalysisAccess access) {
        access.registerSubtypeReachabilityHandler((duringAnalysisAccess, aClass) -> {
            Method[] methods = aClass.getDeclaredMethods();
            for (Method method : methods) {
                RuntimeReflection.register(method);
                duringAnalysisAccess.isReachable(method);
            }
        }, JavaFuseOperations.class);
    }
}
