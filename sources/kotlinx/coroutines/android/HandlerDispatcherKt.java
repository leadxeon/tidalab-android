package kotlinx.coroutines.android;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import java.util.Objects;
import kotlin.Result;
/* compiled from: HandlerDispatcher.kt */
/* loaded from: classes.dex */
public final class HandlerDispatcherKt {
    private static volatile Choreographer choreographer;

    static {
        Object obj;
        Object obj2 = null;
        try {
            obj = new HandlerContext(asHandler(Looper.getMainLooper(), true), null, false);
        } catch (Throwable th) {
            obj = new Result.Failure(th);
        }
        if (!(obj instanceof Result.Failure)) {
            obj2 = obj;
        }
        HandlerDispatcher handlerDispatcher = (HandlerDispatcher) obj2;
    }

    public static final Handler asHandler(Looper looper, boolean z) {
        if (!z) {
            return new Handler(looper);
        }
        if (Build.VERSION.SDK_INT >= 28) {
            Object invoke = Handler.class.getDeclaredMethod("createAsync", Looper.class).invoke(null, looper);
            Objects.requireNonNull(invoke, "null cannot be cast to non-null type android.os.Handler");
            return (Handler) invoke;
        }
        try {
            return (Handler) Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(looper, null, Boolean.TRUE);
        } catch (NoSuchMethodException unused) {
            return new Handler(looper);
        }
    }
}
