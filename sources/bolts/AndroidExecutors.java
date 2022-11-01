package bolts;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class AndroidExecutors {
    public static final int CORE_POOL_SIZE;
    public static final int CPU_COUNT;
    public static final AndroidExecutors INSTANCE = new AndroidExecutors();
    public static final int MAX_POOL_SIZE;
    public final Executor uiThread = new UIThreadExecutor(null);

    /* loaded from: classes.dex */
    public static class UIThreadExecutor implements Executor {
        public UIThreadExecutor(AnonymousClass1 r1) {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        CPU_COUNT = availableProcessors;
        CORE_POOL_SIZE = availableProcessors + 1;
        MAX_POOL_SIZE = (availableProcessors * 2) + 1;
    }
}
