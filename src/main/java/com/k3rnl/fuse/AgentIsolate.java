package com.k3rnl.fuse;

import com.k3rnl.fuse.fuse.PrivateData;
import com.k3rnl.fuse.libc.LibC;
import com.oracle.svm.core.Uninterruptible;
import com.oracle.svm.core.c.function.CEntryPointActions;
import com.oracle.svm.core.c.function.CEntryPointErrors;
import com.oracle.svm.core.c.function.CEntryPointOptions;
import com.k3rnl.fuse.fuse.FuseContext;
import com.k3rnl.fuse.fuse.FuseLibrary;
import org.graalvm.nativeimage.Isolate;
import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.WordFactory;

/**
 * A utility class for managing isolate.
 */
@CContext(LibC.Directives.class)
public final class AgentIsolate {

//    public static Isolate isolate;

    public static final class FuseContextPrologue implements CEntryPointOptions.Prologue {
//        private static final CGlobalData<CCharPointer> errorMessage = CGlobalDataFactory.createCString(
//                "Failed to enter (or attach to) the global isolate in the current thread.");

        @Uninterruptible(reason = "prologue")
        static void enter() {
            FuseContext ctx = FuseLibrary.fuse_get_context();
            VoidPointer ptr = ctx.private_data();
            PrivateData privateData = WordFactory.pointer(ptr.rawValue());
            Isolate isolate = privateData.isolate();
            int code = CEntryPointActions.enterAttachThread(isolate, false, true);
            if (code != CEntryPointErrors.NO_ERROR) {
                // errorMessage.get() doesn't work, compilation fails with violation of @Uninterruptible usage, throwNoClassDefFoundError(String)
//                CEntryPointActions.failFatally(code, errorMessage.get());
            }
        }
    }

    public static final class DetachEpilogue implements CEntryPointOptions.Epilogue {
        @Uninterruptible(reason = "epilogue")
        static void leave() {
            CEntryPointActions.leaveDetachThread();
        }
    }

    private AgentIsolate() {
    }
}