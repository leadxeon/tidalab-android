package kotlinx.coroutines;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import kotlinx.coroutines.internal.ConcurrentKt;
/* compiled from: Executors.kt */
/* loaded from: classes.dex */
public final class ExecutorCoroutineDispatcherImpl extends ExecutorCoroutineDispatcherBase {
    public final Executor executor;

    public ExecutorCoroutineDispatcherImpl(Executor executor) {
        Method method;
        this.executor = executor;
        Executor executor2 = getExecutor();
        Method method2 = ConcurrentKt.REMOVE_FUTURE_ON_CANCEL;
        boolean z = false;
        try {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = executor2 instanceof ScheduledThreadPoolExecutor ? (ScheduledThreadPoolExecutor) executor2 : null;
            if (!(scheduledThreadPoolExecutor == null || (method = ConcurrentKt.REMOVE_FUTURE_ON_CANCEL) == null)) {
                method.invoke(scheduledThreadPoolExecutor, Boolean.TRUE);
                z = true;
            }
        } catch (Throwable unused) {
        }
        this.removesFutureOnCancellation = z;
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher
    public Executor getExecutor() {
        return this.executor;
    }
}
