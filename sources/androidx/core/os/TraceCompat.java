package androidx.core.os;

import android.os.Build;
import android.os.Trace;
import android.util.Log;
@Deprecated
/* loaded from: classes.dex */
public final class TraceCompat {
    public static final /* synthetic */ int $r8$clinit = 0;

    static {
        if (Build.VERSION.SDK_INT < 29) {
            try {
                Trace.class.getField("TRACE_TAG_APP").getLong(null);
                Class cls = Long.TYPE;
                Trace.class.getMethod("isTagEnabled", cls);
                Class cls2 = Integer.TYPE;
                Trace.class.getMethod("asyncTraceBegin", cls, String.class, cls2);
                Trace.class.getMethod("asyncTraceEnd", cls, String.class, cls2);
                Trace.class.getMethod("traceCounter", cls, String.class, cls2);
            } catch (Exception e) {
                Log.i("TraceCompat", "Unable to initialize via reflection.", e);
            }
        }
    }
}
