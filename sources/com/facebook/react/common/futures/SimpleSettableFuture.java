package com.facebook.react.common.futures;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes.dex */
public class SimpleSettableFuture<T> implements Future<T> {
    public Exception mException;
    public final CountDownLatch mReadyLatch = new CountDownLatch(1);
    public T mResult;

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        throw new UnsupportedOperationException();
    }

    public final void checkNotSet() {
        if (this.mReadyLatch.getCount() == 0) {
            throw new RuntimeException("Result has already been set!");
        }
    }

    @Override // java.util.concurrent.Future
    public T get() throws InterruptedException, ExecutionException {
        this.mReadyLatch.await();
        if (this.mException == null) {
            return this.mResult;
        }
        throw new ExecutionException(this.mException);
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.mReadyLatch.getCount() == 0;
    }

    @Override // java.util.concurrent.Future
    public T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        if (!this.mReadyLatch.await(j, timeUnit)) {
            throw new TimeoutException("Timed out waiting for result");
        } else if (this.mException == null) {
            return this.mResult;
        } else {
            throw new ExecutionException(this.mException);
        }
    }
}
