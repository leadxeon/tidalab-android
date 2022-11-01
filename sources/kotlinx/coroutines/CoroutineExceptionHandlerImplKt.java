package kotlinx.coroutines;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.lang.Thread;
import java.util.List;
import kotlin.coroutines.CoroutineContext;
/* compiled from: CoroutineExceptionHandlerImpl.kt */
/* loaded from: classes.dex */
public final class CoroutineExceptionHandlerImplKt {
    public static final List<CoroutineExceptionHandler> handlers = InputKt.toList(InputKt.asSequence(C$$ServiceLoaderMethods.$load$32762()));

    public static final void handleCoroutineExceptionImpl(CoroutineContext coroutineContext, Throwable th) {
        Throwable th2;
        for (CoroutineExceptionHandler coroutineExceptionHandler : handlers) {
            try {
                coroutineExceptionHandler.handleException(coroutineContext, th);
            } catch (Throwable th3) {
                Thread currentThread = Thread.currentThread();
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler = currentThread.getUncaughtExceptionHandler();
                if (th == th3) {
                    th2 = th;
                } else {
                    th2 = new RuntimeException("Exception while trying to handle coroutine exception", th3);
                    InputKt.addSuppressed(th2, th);
                }
                uncaughtExceptionHandler.uncaughtException(currentThread, th2);
            }
        }
        Thread currentThread2 = Thread.currentThread();
        currentThread2.getUncaughtExceptionHandler().uncaughtException(currentThread2, th);
    }
}
