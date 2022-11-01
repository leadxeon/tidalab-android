package kotlinx.coroutines.internal;

import java.lang.reflect.Method;
import java.util.concurrent.ScheduledThreadPoolExecutor;
/* compiled from: Concurrent.kt */
/* loaded from: classes.dex */
public final class ConcurrentKt {
    public static final Method REMOVE_FUTURE_ON_CANCEL;

    static {
        Method method;
        try {
            method = ScheduledThreadPoolExecutor.class.getMethod("setRemoveOnCancelPolicy", Boolean.TYPE);
        } catch (Throwable unused) {
            method = null;
        }
        REMOVE_FUTURE_ON_CANCEL = method;
    }
}
