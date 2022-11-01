package com.facebook.imagepipeline.core;

import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.SharedReference;
import com.facebook.imagepipeline.debug.NoOpCloseableReferenceLeakTracker;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class CloseableReferenceFactory {
    public final CloseableReference.LeakHandler mLeakHandler;

    public CloseableReferenceFactory(final NoOpCloseableReferenceLeakTracker noOpCloseableReferenceLeakTracker) {
        this.mLeakHandler = new CloseableReference.LeakHandler(this) { // from class: com.facebook.imagepipeline.core.CloseableReferenceFactory.1
            @Override // com.facebook.common.references.CloseableReference.LeakHandler
            public void reportLeak(SharedReference<Object> sharedReference, Throwable th) {
                String str;
                Objects.requireNonNull(noOpCloseableReferenceLeakTracker);
                Object[] objArr = new Object[4];
                objArr[0] = Integer.valueOf(System.identityHashCode(this));
                objArr[1] = Integer.valueOf(System.identityHashCode(sharedReference));
                objArr[2] = sharedReference.get().getClass().getName();
                if (th == null) {
                    str = HttpUrl.FRAGMENT_ENCODE_SET;
                } else {
                    StringWriter stringWriter = new StringWriter();
                    th.printStackTrace(new PrintWriter(stringWriter));
                    str = stringWriter.toString();
                }
                objArr[3] = str;
                FLog.w("Fresco", "Finalized without closing: %x %x (type = %s).\nStack:\n%s", objArr);
            }

            @Override // com.facebook.common.references.CloseableReference.LeakHandler
            public boolean requiresStacktrace() {
                Objects.requireNonNull(noOpCloseableReferenceLeakTracker);
                return false;
            }
        };
    }
}
