package com.facebook.common.executors;

import android.os.Handler;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class HandlerExecutorServiceImpl extends AbstractExecutorService implements ScheduledExecutorService {
    public final Handler mHandler;

    public HandlerExecutorServiceImpl(Handler handler) {
        this.mHandler = handler;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        throw null;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return false;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return false;
    }

    @Override // java.util.concurrent.AbstractExecutorService
    public RunnableFuture newTaskFor(Runnable runnable, Object obj) {
        return new ScheduledFutureImpl(this.mHandler, runnable, obj);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        ScheduledFutureImpl scheduledFutureImpl = new ScheduledFutureImpl(this.mHandler, runnable, null);
        this.mHandler.postDelayed(scheduledFutureImpl, timeUnit.toMillis(j));
        return scheduledFutureImpl;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public Future submit(Runnable runnable) {
        Objects.requireNonNull(runnable);
        ScheduledFutureImpl scheduledFutureImpl = new ScheduledFutureImpl(this.mHandler, runnable, null);
        execute(scheduledFutureImpl);
        return scheduledFutureImpl;
    }

    @Override // java.util.concurrent.AbstractExecutorService
    public RunnableFuture newTaskFor(Callable callable) {
        return new ScheduledFutureImpl(this.mHandler, callable);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
        ScheduledFutureImpl scheduledFutureImpl = new ScheduledFutureImpl(this.mHandler, callable);
        this.mHandler.postDelayed(scheduledFutureImpl, timeUnit.toMillis(j));
        return scheduledFutureImpl;
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public Future submit(Runnable runnable, Object obj) {
        Objects.requireNonNull(runnable);
        ScheduledFutureImpl scheduledFutureImpl = new ScheduledFutureImpl(this.mHandler, runnable, obj);
        execute(scheduledFutureImpl);
        return scheduledFutureImpl;
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public Future submit(Callable callable) {
        Objects.requireNonNull(callable);
        ScheduledFutureImpl scheduledFutureImpl = new ScheduledFutureImpl(this.mHandler, callable);
        execute(scheduledFutureImpl);
        return scheduledFutureImpl;
    }
}
