package com.facebook.datasource;

import android.util.Pair;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public abstract class AbstractDataSource<T> implements DataSource<T> {
    public T mResult = null;
    public Throwable mFailureThrowable = null;
    public float mProgress = 0.0f;
    public boolean mIsClosed = false;
    public int mDataSourceStatus = 1;
    public final ConcurrentLinkedQueue<Pair<DataSubscriber<T>, Executor>> mSubscribers = new ConcurrentLinkedQueue<>();

    /* renamed from: com.facebook.datasource.AbstractDataSource$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements Runnable {
        public final /* synthetic */ DataSubscriber val$dataSubscriber;
        public final /* synthetic */ boolean val$isCancellation;
        public final /* synthetic */ boolean val$isFailure;

        public AnonymousClass1(boolean z, DataSubscriber dataSubscriber, boolean z2) {
            this.val$isFailure = z;
            this.val$dataSubscriber = dataSubscriber;
            this.val$isCancellation = z2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.val$isFailure) {
                this.val$dataSubscriber.onFailure(AbstractDataSource.this);
            } else if (this.val$isCancellation) {
                this.val$dataSubscriber.onCancellation(AbstractDataSource.this);
            } else {
                this.val$dataSubscriber.onNewResult(AbstractDataSource.this);
            }
        }
    }

    @Override // com.facebook.datasource.DataSource
    public boolean close() {
        synchronized (this) {
            if (this.mIsClosed) {
                return false;
            }
            this.mIsClosed = true;
            T t = this.mResult;
            this.mResult = null;
            if (t != null) {
                closeResult(t);
            }
            if (!isFinished()) {
                notifyDataSubscribers();
            }
            synchronized (this) {
                this.mSubscribers.clear();
            }
            return true;
        }
    }

    public void closeResult(T t) {
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized Throwable getFailureCause() {
        return this.mFailureThrowable;
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized float getProgress() {
        return this.mProgress;
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized T getResult() {
        return this.mResult;
    }

    public synchronized boolean hasFailed() {
        return this.mDataSourceStatus == 3;
    }

    @Override // com.facebook.datasource.DataSource
    public boolean hasMultipleResults() {
        return false;
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized boolean hasResult() {
        return this.mResult != null;
    }

    public synchronized boolean isClosed() {
        return this.mIsClosed;
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized boolean isFinished() {
        boolean z;
        z = true;
        if (this.mDataSourceStatus == 1) {
            z = false;
        }
        return z;
    }

    public final void notifyDataSubscribers() {
        boolean hasFailed = hasFailed();
        boolean wasCancelled = wasCancelled();
        Iterator<Pair<DataSubscriber<T>, Executor>> it = this.mSubscribers.iterator();
        while (it.hasNext()) {
            Pair<DataSubscriber<T>, Executor> next = it.next();
            ((Executor) next.second).execute(new AnonymousClass1(hasFailed, (DataSubscriber) next.first, wasCancelled));
        }
    }

    public boolean setFailure(Throwable th) {
        boolean z;
        synchronized (this) {
            z = true;
            if (!this.mIsClosed && this.mDataSourceStatus == 1) {
                this.mDataSourceStatus = 3;
                this.mFailureThrowable = th;
            }
            z = false;
        }
        if (z) {
            notifyDataSubscribers();
        }
        return z;
    }

    public boolean setProgress(float f) {
        boolean z;
        synchronized (this) {
            z = false;
            if (!this.mIsClosed && this.mDataSourceStatus == 1) {
                if (f >= this.mProgress) {
                    this.mProgress = f;
                    z = true;
                }
            }
        }
        if (z) {
            Iterator<Pair<DataSubscriber<T>, Executor>> it = this.mSubscribers.iterator();
            while (it.hasNext()) {
                Pair<DataSubscriber<T>, Executor> next = it.next();
                final DataSubscriber dataSubscriber = (DataSubscriber) next.first;
                ((Executor) next.second).execute(new Runnable() { // from class: com.facebook.datasource.AbstractDataSource.2
                    @Override // java.lang.Runnable
                    public void run() {
                        dataSubscriber.onProgressUpdate(AbstractDataSource.this);
                    }
                });
            }
        }
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0026, code lost:
        if (r5 != null) goto L_0x0028;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean setResult(T r5, boolean r6) {
        /*
            r4 = this;
            r0 = 0
            monitor-enter(r4)     // Catch: all -> 0x0040
            boolean r1 = r4.mIsClosed     // Catch: all -> 0x0037
            r2 = 1
            if (r1 != 0) goto L_0x0024
            int r1 = r4.mDataSourceStatus     // Catch: all -> 0x0037
            if (r1 == r2) goto L_0x000c
            goto L_0x0024
        L_0x000c:
            if (r6 == 0) goto L_0x0015
            r6 = 2
            r4.mDataSourceStatus = r6     // Catch: all -> 0x0037
            r6 = 1065353216(0x3f800000, float:1.0)
            r4.mProgress = r6     // Catch: all -> 0x0037
        L_0x0015:
            T r6 = r4.mResult     // Catch: all -> 0x0037
            if (r6 == r5) goto L_0x001f
            r4.mResult = r5     // Catch: all -> 0x001d
            r5 = r6
            goto L_0x0020
        L_0x001d:
            r5 = move-exception
            goto L_0x0035
        L_0x001f:
            r5 = r0
        L_0x0020:
            monitor-exit(r4)     // Catch: all -> 0x0031
            if (r5 == 0) goto L_0x002b
            goto L_0x0028
        L_0x0024:
            r2 = 0
            monitor-exit(r4)     // Catch: all -> 0x0031
            if (r5 == 0) goto L_0x002b
        L_0x0028:
            r4.closeResult(r5)
        L_0x002b:
            if (r2 == 0) goto L_0x0030
            r4.notifyDataSubscribers()
        L_0x0030:
            return r2
        L_0x0031:
            r6 = move-exception
            r3 = r6
            r6 = r5
            r5 = r3
        L_0x0035:
            r0 = r6
            goto L_0x0038
        L_0x0037:
            r5 = move-exception
        L_0x0038:
            r6 = r0
        L_0x0039:
            monitor-exit(r4)     // Catch: all -> 0x003e
            throw r5     // Catch: all -> 0x003b
        L_0x003b:
            r5 = move-exception
            r0 = r6
            goto L_0x0041
        L_0x003e:
            r5 = move-exception
            goto L_0x0039
        L_0x0040:
            r5 = move-exception
        L_0x0041:
            if (r0 == 0) goto L_0x0046
            r4.closeResult(r0)
        L_0x0046:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.setResult(java.lang.Object, boolean):boolean");
    }

    @Override // com.facebook.datasource.DataSource
    public void subscribe(DataSubscriber<T> dataSubscriber, Executor executor) {
        Objects.requireNonNull(executor);
        synchronized (this) {
            if (!this.mIsClosed) {
                boolean z = true;
                if (this.mDataSourceStatus == 1) {
                    this.mSubscribers.add(Pair.create(dataSubscriber, executor));
                }
                if (!hasResult() && !isFinished() && !wasCancelled()) {
                    z = false;
                }
                if (z) {
                    executor.execute(new AnonymousClass1(hasFailed(), dataSubscriber, wasCancelled()));
                }
            }
        }
    }

    public final synchronized boolean wasCancelled() {
        boolean z;
        if (isClosed()) {
            if (!isFinished()) {
                z = true;
            }
        }
        z = false;
        return z;
    }
}
