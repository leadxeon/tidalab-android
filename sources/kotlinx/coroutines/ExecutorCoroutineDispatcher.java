package kotlinx.coroutines;

import java.io.Closeable;
import java.util.concurrent.Executor;
/* compiled from: Executors.kt */
/* loaded from: classes.dex */
public abstract class ExecutorCoroutineDispatcher extends CoroutineDispatcher implements Closeable {
    public abstract Executor getExecutor();
}
