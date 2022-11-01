package kotlinx.coroutines.scheduling;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.internal.SystemPropsKt__SystemPropsKt;
/* compiled from: Dispatcher.kt */
/* loaded from: classes.dex */
public final class DefaultScheduler extends ExperimentalCoroutineDispatcher {
    public static final DefaultScheduler INSTANCE;
    public static final CoroutineDispatcher IO;

    static {
        DefaultScheduler defaultScheduler = new DefaultScheduler();
        INSTANCE = defaultScheduler;
        int i = SystemPropsKt__SystemPropsKt.AVAILABLE_PROCESSORS;
        IO = new LimitingDispatcher(defaultScheduler, InputKt.systemProp$default("kotlinx.coroutines.io.parallelism", 64 < i ? i : 64, 0, 0, 12, (Object) null), "Dispatchers.IO", 1);
    }

    public DefaultScheduler() {
        super(0, 0, null, 7);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new UnsupportedOperationException("Dispatchers.Default cannot be closed");
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        return "Dispatchers.Default";
    }
}
