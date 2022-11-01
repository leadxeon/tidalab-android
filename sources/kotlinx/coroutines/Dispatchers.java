package kotlinx.coroutines;

import java.util.Objects;
import kotlinx.coroutines.scheduling.DefaultScheduler;
/* compiled from: Dispatchers.kt */
/* loaded from: classes.dex */
public final class Dispatchers {
    public static final CoroutineDispatcher Default;
    public static final Dispatchers INSTANCE = null;
    public static final CoroutineDispatcher IO;
    public static final CoroutineDispatcher Unconfined;

    static {
        Default = CoroutineContextKt.useCoroutinesScheduler ? DefaultScheduler.INSTANCE : CommonPool.INSTANCE;
        Unconfined = Unconfined.INSTANCE;
        Objects.requireNonNull(DefaultScheduler.INSTANCE);
        IO = DefaultScheduler.IO;
    }
}
