package com.facebook.common.executors;

import android.os.Handler;
import android.os.Looper;
/* loaded from: classes.dex */
public class UiThreadImmediateExecutorService extends HandlerExecutorServiceImpl {
    public static UiThreadImmediateExecutorService sInstance;

    public UiThreadImmediateExecutorService() {
        super(new Handler(Looper.getMainLooper()));
    }

    @Override // com.facebook.common.executors.HandlerExecutorServiceImpl, java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (Thread.currentThread() == this.mHandler.getLooper().getThread()) {
            runnable.run();
        } else {
            this.mHandler.post(runnable);
        }
    }
}
