package com.k3rnl.fuse;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The {@code NotImplemented} annotation is used to mark methods or classes that
 * are currently not implemented. It serves as a placeholder and can help developers
 * identify areas of the codebase that require further work.
 *
 * <p>This annotation is retained at runtime, making it accessible via reflection.</p>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface NotImplemented {
}
