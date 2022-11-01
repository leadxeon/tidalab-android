package bolts;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public final class BoltsExecutors {
    public static final BoltsExecutors INSTANCE = new BoltsExecutors();
    public final ExecutorService background;
    public final Executor immediate;

    /* loaded from: classes.dex */
    public static class ImmediateExecutor implements Executor {
        public ThreadLocal<Integer> executionDepth = new ThreadLocal<>();

        public ImmediateExecutor(AnonymousClass1 r1) {
        }

        public final int decrementDepth() {
            Integer num = this.executionDepth.get();
            if (num == null) {
                num = 0;
            }
            int intValue = num.intValue() - 1;
            if (intValue == 0) {
                this.executionDepth.remove();
            } else {
                this.executionDepth.set(Integer.valueOf(intValue));
            }
            return intValue;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            Integer num = this.executionDepth.get();
            if (num == null) {
                num = 0;
            }
            int intValue = num.intValue() + 1;
            this.executionDepth.set(Integer.valueOf(intValue));
            try {
                if (intValue <= 15) {
                    runnable.run();
                } else {
                    BoltsExecutors.INSTANCE.background.execute(runnable);
                }
            } finally {
                decrementDepth();
            }
        }
    }

    public BoltsExecutors() {
        ThreadPoolExecutor threadPoolExecutor;
        String property = System.getProperty("java.runtime.name");
        if (!(property == null ? false : property.toLowerCase(Locale.US).contains("android"))) {
            threadPoolExecutor = Executors.newCachedThreadPool();
        } else {
            AndroidExecutors androidExecutors = AndroidExecutors.INSTANCE;
            ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(AndroidExecutors.CORE_POOL_SIZE, AndroidExecutors.MAX_POOL_SIZE, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue());
            threadPoolExecutor2.allowCoreThreadTimeOut(true);
            threadPoolExecutor = threadPoolExecutor2;
        }
        this.background = threadPoolExecutor;
        Executors.newSingleThreadScheduledExecutor();
        this.immediate = new ImmediateExecutor(null);
    }
}
