package com.reactnativecommunity.asyncstorage;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class SerialExecutor implements Executor {
    public final Executor executor;
    public Runnable mActive;
    public final ArrayDeque<Runnable> mTasks = new ArrayDeque<>();

    public SerialExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override // java.util.concurrent.Executor
    public synchronized void execute(final Runnable runnable) {
        this.mTasks.offer(new Runnable() { // from class: com.reactnativecommunity.asyncstorage.SerialExecutor.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    runnable.run();
                } finally {
                    SerialExecutor.this.scheduleNext();
                }
            }
        });
        if (this.mActive == null) {
            scheduleNext();
        }
    }

    public synchronized void scheduleNext() {
        Runnable poll = this.mTasks.poll();
        this.mActive = poll;
        if (poll != null) {
            this.executor.execute(poll);
        }
    }
}
