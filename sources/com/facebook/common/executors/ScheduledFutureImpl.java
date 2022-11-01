package com.facebook.common.executors;

import android.os.Handler;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes.dex */
public class ScheduledFutureImpl<V> implements RunnableFuture<V>, ScheduledFuture<V> {
    public final FutureTask<V> mListenableFuture;

    public ScheduledFutureImpl(Handler handler, Callable<V> callable) {
        this.mListenableFuture = new FutureTask<>(callable);
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return this.mListenableFuture.cancel(z);
    }

    @Override // java.lang.Comparable
    public int compareTo(Delayed delayed) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.Future
    public V get() throws InterruptedException, ExecutionException {
        return this.mListenableFuture.get();
    }

    @Override // java.util.concurrent.Delayed
    public long getDelay(TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.mListenableFuture.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.mListenableFuture.isDone();
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public void run() {
        this.mListenableFuture.run();
    }

    @Override // java.util.concurrent.Future
    public V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.mListenableFuture.get(j, timeUnit);
    }

    public ScheduledFutureImpl(Handler handler, Runnable runnable, V v) {
        this.mListenableFuture = new FutureTask<>(runnable, v);
    }
}
