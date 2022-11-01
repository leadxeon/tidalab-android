package kotlinx.coroutines.android;

import android.os.Build;
import androidx.annotation.Keep;
import java.lang.Thread;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;
/* compiled from: AndroidExceptionPreHandler.kt */
@Keep
/* loaded from: classes.dex */
public final class AndroidExceptionPreHandler extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    private volatile Object _preHandler = this;

    public AndroidExceptionPreHandler() {
        super(CoroutineExceptionHandler.Key.$$INSTANCE);
    }

    private final Method preHandler() {
        Object obj = this._preHandler;
        if (obj != this) {
            return (Method) obj;
        }
        Method method = null;
        try {
            boolean z = false;
            Method declaredMethod = Thread.class.getDeclaredMethod("getUncaughtExceptionPreHandler", new Class[0]);
            if (Modifier.isPublic(declaredMethod.getModifiers())) {
                if (Modifier.isStatic(declaredMethod.getModifiers())) {
                    z = true;
                }
            }
            if (z) {
                method = declaredMethod;
            }
        } catch (Throwable unused) {
        }
        this._preHandler = method;
        return method;
    }

    @Override // kotlinx.coroutines.CoroutineExceptionHandler
    public void handleException(CoroutineContext coroutineContext, Throwable th) {
        Thread currentThread = Thread.currentThread();
        if (Build.VERSION.SDK_INT >= 28) {
            currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
            return;
        }
        Method preHandler = preHandler();
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = null;
        Object invoke = preHandler != null ? preHandler.invoke(null, new Object[0]) : null;
        if (invoke instanceof Thread.UncaughtExceptionHandler) {
            uncaughtExceptionHandler = invoke;
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = uncaughtExceptionHandler;
        if (uncaughtExceptionHandler2 != null) {
            uncaughtExceptionHandler2.uncaughtException(currentThread, th);
        }
    }
}
