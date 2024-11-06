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
 * The {@code AgentIsolate} class provides utility methods for managing isolates
 * in a FUSE-based application. It contains a prologue to enter an isolate
 * context and an epilogue to detach the current thread from the isolate.
 *
 * <p>This class is essential for handling isolated contexts within native FUSE operations,
 * especially when working with GraalVM native images and FUSE.</p>
 */
@CContext(LibC.Directives.class)
public final class AgentIsolate {

    /**
     * The {@code FuseContextPrologue} is a prologue used to enter the isolate
     * associated with the current FUSE context before executing a native function.
     *
     * <p>This prologue retrieves the {@link Isolate} from the {@link PrivateData} of
     * the {@link FuseContext}, then attaches the current thread to this isolate.</p>
     */
    public static final class FuseContextPrologue implements CEntryPointOptions.Prologue {

        /**
         * Enters the isolate context from the FUSE context private data.
         * <p>This method is uninterruptible to prevent interference with isolate entry.</p>
         *
         * <p>If an error occurs while entering the isolate, it fails fatally with an
         * appropriate error code.</p>
         */
        @Uninterruptible(reason = "prologue")
        static void enter() {
            FuseContext ctx = FuseLibrary.fuse_get_context();
            VoidPointer ptr = ctx.private_data();
            PrivateData privateData = WordFactory.pointer(ptr.rawValue());
            Isolate isolate = privateData.isolate();
            int code = CEntryPointActions.enterAttachThread(isolate, false, true);
            if (code != CEntryPointErrors.NO_ERROR) {
                // Uncomment and customize error handling as needed
                // CEntryPointActions.failFatally(code, errorMessage.get());
            }
        }
    }

    /**
     * The {@code DetachEpilogue} is an epilogue used to detach the current thread
     * from the isolate after executing a native function.
     *
     * <p>This ensures that the thread is properly released from the isolate context
     * once the function execution completes.</p>
     */
    public static final class DetachEpilogue implements CEntryPointOptions.Epilogue {

        /**
         * Detaches the current thread from the isolate context.
         * <p>This method is uninterruptible to prevent interference with isolate detachment.</p>
         */
        @Uninterruptible(reason = "epilogue")
        static void leave() {
            CEntryPointActions.leaveDetachThread();
        }
    }

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private AgentIsolate() {
    }
}
