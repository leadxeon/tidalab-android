package kotlinx.coroutines;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__IndentKt;
/* compiled from: CommonPool.kt */
/* loaded from: classes.dex */
public final class CommonPool extends ExecutorCoroutineDispatcher {
    public static final CommonPool INSTANCE = new CommonPool();
    private static volatile Executor pool;
    public static final int requestedParallelism;

    static {
        String str;
        int i;
        try {
            str = System.getProperty("kotlinx.coroutines.default.parallelism");
        } catch (Throwable unused) {
            str = null;
        }
        if (str == null) {
            i = -1;
        } else {
            Integer intOrNull = StringsKt__IndentKt.toIntOrNull(str);
            if (intOrNull == null || intOrNull.intValue() < 1) {
                throw new IllegalStateException(Intrinsics.stringPlus("Expected positive number in kotlinx.coroutines.default.parallelism, but has ", str).toString());
            }
            i = intOrNull.intValue();
        }
        requestedParallelism = i;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new IllegalStateException("Close cannot be invoked on CommonPool".toString());
    }

    public final ExecutorService createPlainPool() {
        final AtomicInteger atomicInteger = new AtomicInteger();
        return Executors.newFixedThreadPool(getParallelism(), new ThreadFactory() { // from class: kotlinx.coroutines.-$$Lambda$CommonPool$ERvPtt6BNpepqyLHHf5J6mHx7SQ
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, Intrinsics.stringPlus("CommonPool-worker-", Integer.valueOf(atomicInteger.incrementAndGet())));
                thread.setDaemon(true);
                return thread;
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006d A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.concurrent.ExecutorService createPool() {
        /*
            r7 = this;
            java.lang.SecurityManager r0 = java.lang.System.getSecurityManager()
            if (r0 == 0) goto L_0x000b
            java.util.concurrent.ExecutorService r0 = r7.createPlainPool()
            return r0
        L_0x000b:
            r0 = 0
            java.lang.String r1 = "java.util.concurrent.ForkJoinPool"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: all -> 0x0013
            goto L_0x0014
        L_0x0013:
            r1 = r0
        L_0x0014:
            if (r1 != 0) goto L_0x001b
            java.util.concurrent.ExecutorService r0 = r7.createPlainPool()
            return r0
        L_0x001b:
            int r2 = kotlinx.coroutines.CommonPool.requestedParallelism
            r3 = 1
            r4 = 0
            if (r2 >= 0) goto L_0x006e
            java.lang.String r2 = "commonPool"
            java.lang.Class[] r5 = new java.lang.Class[r4]     // Catch: all -> 0x0036
            java.lang.reflect.Method r2 = r1.getMethod(r2, r5)     // Catch: all -> 0x0036
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch: all -> 0x0036
            java.lang.Object r2 = r2.invoke(r0, r5)     // Catch: all -> 0x0036
            boolean r5 = r2 instanceof java.util.concurrent.ExecutorService     // Catch: all -> 0x0036
            if (r5 == 0) goto L_0x0036
            java.util.concurrent.ExecutorService r2 = (java.util.concurrent.ExecutorService) r2     // Catch: all -> 0x0036
            goto L_0x0037
        L_0x0036:
            r2 = r0
        L_0x0037:
            if (r2 != 0) goto L_0x003a
            goto L_0x006e
        L_0x003a:
            kotlinx.coroutines.CommonPool r5 = kotlinx.coroutines.CommonPool.INSTANCE
            java.util.Objects.requireNonNull(r5)
            kotlinx.coroutines.-$$Lambda$CommonPool$36bgNy4lLwRHCWOZ-fm6LcwyUbo r5 = kotlinx.coroutines.$$Lambda$CommonPool$36bgNy4lLwRHCWOZfm6LcwyUbo.INSTANCE
            r2.submit(r5)
            java.lang.String r5 = "getPoolSize"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch: all -> 0x0059
            java.lang.reflect.Method r5 = r1.getMethod(r5, r6)     // Catch: all -> 0x0059
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch: all -> 0x0059
            java.lang.Object r5 = r5.invoke(r2, r6)     // Catch: all -> 0x0059
            boolean r6 = r5 instanceof java.lang.Integer     // Catch: all -> 0x0059
            if (r6 == 0) goto L_0x0059
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch: all -> 0x0059
            goto L_0x005a
        L_0x0059:
            r5 = r0
        L_0x005a:
            if (r5 != 0) goto L_0x005d
            goto L_0x0065
        L_0x005d:
            int r5 = r5.intValue()
            if (r5 < r3) goto L_0x0065
            r5 = 1
            goto L_0x0066
        L_0x0065:
            r5 = 0
        L_0x0066:
            if (r5 == 0) goto L_0x0069
            goto L_0x006a
        L_0x0069:
            r2 = r0
        L_0x006a:
            if (r2 != 0) goto L_0x006d
            goto L_0x006e
        L_0x006d:
            return r2
        L_0x006e:
            java.lang.Class[] r2 = new java.lang.Class[r3]     // Catch: all -> 0x0092
            java.lang.Class r5 = java.lang.Integer.TYPE     // Catch: all -> 0x0092
            r2[r4] = r5     // Catch: all -> 0x0092
            java.lang.reflect.Constructor r1 = r1.getConstructor(r2)     // Catch: all -> 0x0092
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch: all -> 0x0092
            kotlinx.coroutines.CommonPool r3 = kotlinx.coroutines.CommonPool.INSTANCE     // Catch: all -> 0x0092
            int r3 = r3.getParallelism()     // Catch: all -> 0x0092
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: all -> 0x0092
            r2[r4] = r3     // Catch: all -> 0x0092
            java.lang.Object r1 = r1.newInstance(r2)     // Catch: all -> 0x0092
            boolean r2 = r1 instanceof java.util.concurrent.ExecutorService     // Catch: all -> 0x0092
            if (r2 == 0) goto L_0x0093
            java.util.concurrent.ExecutorService r1 = (java.util.concurrent.ExecutorService) r1     // Catch: all -> 0x0092
            r0 = r1
            goto L_0x0093
        L_0x0092:
        L_0x0093:
            if (r0 != 0) goto L_0x0099
            java.util.concurrent.ExecutorService r0 = r7.createPlainPool()
        L_0x0099:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CommonPool.createPool():java.util.concurrent.ExecutorService");
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        try {
            Executor executor = pool;
            if (executor == null) {
                synchronized (this) {
                    executor = pool;
                    if (executor == null) {
                        executor = createPool();
                        pool = executor;
                    }
                }
            }
            executor.execute(runnable);
        } catch (RejectedExecutionException unused) {
            DefaultExecutor.INSTANCE.enqueue(runnable);
        }
    }

    public final int getParallelism() {
        Integer valueOf = Integer.valueOf(requestedParallelism);
        if (!(valueOf.intValue() > 0)) {
            valueOf = null;
        }
        if (valueOf != null) {
            return valueOf.intValue();
        }
        int availableProcessors = Runtime.getRuntime().availableProcessors() - 1;
        if (availableProcessors < 1) {
            return 1;
        }
        return availableProcessors;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        return "CommonPool";
    }
}
