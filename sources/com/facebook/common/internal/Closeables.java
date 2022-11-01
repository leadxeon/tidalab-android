package com.facebook.common.internal;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes.dex */
public final class Closeables {
    public static final Logger logger = Logger.getLogger(Closeables.class.getName());

    public static void close(Closeable closeable, boolean z) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                if (z) {
                    logger.log(Level.WARNING, "IOException thrown while closing Closeable.", (Throwable) e);
                    return;
                }
                throw e;
            }
        }
    }

    public static void closeQuietly(InputStream inputStream) {
        try {
            close(inputStream, true);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
