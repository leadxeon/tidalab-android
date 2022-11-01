package com.facebook.imagepipeline.memory;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import android.util.SparseIntArray;
import androidx.recyclerview.R$dimen;
import com.facebook.common.internal.Throwables;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.Pool;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class BasePool<V> implements Pool<V> {
    public final Class<?> TAG = getClass();
    public boolean mAllowNewBuckets;
    public final SparseArray<Bucket<V>> mBuckets;
    public final Counter mFree;
    public final Set<V> mInUseValues;
    public final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    public final PoolParams mPoolParams;
    public final PoolStatsTracker mPoolStatsTracker;
    public final Counter mUsed;

    /* loaded from: classes.dex */
    public static class Counter {
        public int mCount;
        public int mNumBytes;

        public void decrement(int i) {
            int i2;
            int i3 = this.mNumBytes;
            if (i3 < i || (i2 = this.mCount) <= 0) {
                FLog.wtf("com.facebook.imagepipeline.memory.BasePool.Counter", "Unexpected decrement of %d. Current numBytes = %d, count = %d", Integer.valueOf(i), Integer.valueOf(this.mNumBytes), Integer.valueOf(this.mCount));
                return;
            }
            this.mCount = i2 - 1;
            this.mNumBytes = i3 - i;
        }

        public void increment(int i) {
            this.mCount++;
            this.mNumBytes += i;
        }
    }

    /* loaded from: classes.dex */
    public static class InvalidSizeException extends RuntimeException {
        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public InvalidSizeException(java.lang.Object r2) {
            /*
                r1 = this;
                java.lang.String r0 = "Invalid size: "
                java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport.outline13(r0)
                java.lang.String r2 = r2.toString()
                r0.append(r2)
                java.lang.String r2 = r0.toString()
                r1.<init>(r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BasePool.InvalidSizeException.<init>(java.lang.Object):void");
        }
    }

    /* loaded from: classes.dex */
    public static class PoolSizeViolationException extends RuntimeException {
        public PoolSizeViolationException(int i, int i2, int i3, int i4) {
            super("Pool hard cap violation? Hard cap = " + i + " Used size = " + i2 + " Free size = " + i3 + " Request size = " + i4);
        }
    }

    public BasePool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        Objects.requireNonNull(memoryTrimmableRegistry);
        this.mMemoryTrimmableRegistry = memoryTrimmableRegistry;
        Objects.requireNonNull(poolParams);
        this.mPoolParams = poolParams;
        Objects.requireNonNull(poolStatsTracker);
        this.mPoolStatsTracker = poolStatsTracker;
        SparseArray<Bucket<V>> sparseArray = new SparseArray<>();
        this.mBuckets = sparseArray;
        SparseIntArray sparseIntArray = new SparseIntArray(0);
        synchronized (this) {
            sparseArray.clear();
            SparseIntArray sparseIntArray2 = poolParams.bucketSizes;
            if (sparseIntArray2 != null) {
                for (int i = 0; i < sparseIntArray2.size(); i++) {
                    int keyAt = sparseIntArray2.keyAt(i);
                    int valueAt = sparseIntArray2.valueAt(i);
                    int i2 = sparseIntArray.get(keyAt, 0);
                    SparseArray<Bucket<V>> sparseArray2 = this.mBuckets;
                    int sizeInBytes = getSizeInBytes(keyAt);
                    Objects.requireNonNull(this.mPoolParams);
                    sparseArray2.put(keyAt, new Bucket<>(sizeInBytes, valueAt, i2, false));
                }
                this.mAllowNewBuckets = false;
            } else {
                this.mAllowNewBuckets = true;
            }
        }
        this.mInUseValues = Collections.newSetFromMap(new IdentityHashMap());
        this.mFree = new Counter();
        this.mUsed = new Counter();
    }

    public abstract V alloc(int i);

    public synchronized boolean canAllocate(int i) {
        PoolParams poolParams = this.mPoolParams;
        int i2 = poolParams.maxSizeHardCap;
        int i3 = this.mUsed.mNumBytes;
        if (i > i2 - i3) {
            this.mPoolStatsTracker.onHardCapReached();
            return false;
        }
        int i4 = poolParams.maxSizeSoftCap;
        if (i > i4 - (i3 + this.mFree.mNumBytes)) {
            trimToSize(i4 - i);
        }
        if (i <= i2 - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
            return true;
        }
        this.mPoolStatsTracker.onHardCapReached();
        return false;
    }

    public abstract void free(V v);

    @Override // com.facebook.common.memory.Pool
    public V get(int i) {
        boolean z;
        V value;
        synchronized (this) {
            if (isMaxSizeSoftCapExceeded() && this.mFree.mNumBytes != 0) {
                z = false;
                R$dimen.checkState(z);
            }
            z = true;
            R$dimen.checkState(z);
        }
        int bucketedSize = getBucketedSize(i);
        synchronized (this) {
            Bucket<V> bucket = getBucket(bucketedSize);
            if (bucket == null || (value = getValue(bucket)) == null) {
                int sizeInBytes = getSizeInBytes(bucketedSize);
                if (canAllocate(sizeInBytes)) {
                    this.mUsed.increment(sizeInBytes);
                    if (bucket != null) {
                        bucket.mInUseLength++;
                    }
                    V v = null;
                    try {
                        v = alloc(bucketedSize);
                    } catch (Throwable th) {
                        synchronized (this) {
                            this.mUsed.decrement(sizeInBytes);
                            Bucket<V> bucket2 = getBucket(bucketedSize);
                            if (bucket2 != null) {
                                bucket2.decrementInUseCount();
                            }
                            Throwables.propagateIfPossible(th);
                        }
                    }
                    synchronized (this) {
                        R$dimen.checkState(this.mInUseValues.add(v));
                        synchronized (this) {
                            if (isMaxSizeSoftCapExceeded()) {
                                trimToSize(this.mPoolParams.maxSizeSoftCap);
                            }
                        }
                        return v;
                    }
                    this.mPoolStatsTracker.onAlloc(sizeInBytes);
                    logStats();
                    if (FLog.isLoggable(2)) {
                        System.identityHashCode(v);
                    }
                    return v;
                }
                throw new PoolSizeViolationException(this.mPoolParams.maxSizeHardCap, this.mUsed.mNumBytes, this.mFree.mNumBytes, sizeInBytes);
            }
            R$dimen.checkState(this.mInUseValues.add(value));
            int sizeInBytes2 = getSizeInBytes(getBucketedSizeForValue(value));
            this.mUsed.increment(sizeInBytes2);
            this.mFree.decrement(sizeInBytes2);
            this.mPoolStatsTracker.onValueReuse(sizeInBytes2);
            logStats();
            if (FLog.isLoggable(2)) {
                System.identityHashCode(value);
            }
            return value;
        }
    }

    public synchronized Bucket<V> getBucket(int i) {
        Bucket<V> bucket = this.mBuckets.get(i);
        if (bucket == null && this.mAllowNewBuckets) {
            FLog.isLoggable(2);
            Bucket<V> newBucket = newBucket(i);
            this.mBuckets.put(i, newBucket);
            return newBucket;
        }
        return bucket;
    }

    public abstract int getBucketedSize(int i);

    public abstract int getBucketedSizeForValue(V v);

    public abstract int getSizeInBytes(int i);

    public synchronized V getValue(Bucket<V> bucket) {
        V pop;
        pop = bucket.pop();
        if (pop != null) {
            bucket.mInUseLength++;
        }
        return pop;
    }

    public void initialize() {
        this.mMemoryTrimmableRegistry.registerMemoryTrimmable(this);
        this.mPoolStatsTracker.setBasePool(this);
    }

    public synchronized boolean isMaxSizeSoftCapExceeded() {
        boolean z;
        z = this.mUsed.mNumBytes + this.mFree.mNumBytes > this.mPoolParams.maxSizeSoftCap;
        if (z) {
            this.mPoolStatsTracker.onSoftCapReached();
        }
        return z;
    }

    public boolean isReusable(V v) {
        return true;
    }

    @SuppressLint({"InvalidAccessToGuardedField"})
    public final void logStats() {
        if (FLog.isLoggable(2)) {
            Counter counter = this.mUsed;
            int i = counter.mCount;
            int i2 = counter.mNumBytes;
            Counter counter2 = this.mFree;
            int i3 = counter2.mCount;
            int i4 = counter2.mNumBytes;
        }
    }

    public Bucket<V> newBucket(int i) {
        int sizeInBytes = getSizeInBytes(i);
        Objects.requireNonNull(this.mPoolParams);
        return new Bucket<>(sizeInBytes, Integer.MAX_VALUE, 0, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0081, code lost:
        r2.decrementInUseCount();
     */
    @Override // com.facebook.common.memory.Pool, com.facebook.common.references.ResourceReleaser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void release(V r9) {
        /*
            r8 = this;
            java.util.Objects.requireNonNull(r9)
            int r0 = r8.getBucketedSizeForValue(r9)
            int r1 = r8.getSizeInBytes(r0)
            monitor-enter(r8)
            monitor-enter(r8)     // Catch: all -> 0x009f
            android.util.SparseArray<com.facebook.imagepipeline.memory.Bucket<V>> r2 = r8.mBuckets     // Catch: all -> 0x00a1
            java.lang.Object r2 = r2.get(r0)     // Catch: all -> 0x00a1
            com.facebook.imagepipeline.memory.Bucket r2 = (com.facebook.imagepipeline.memory.Bucket) r2     // Catch: all -> 0x00a1
            monitor-exit(r8)     // Catch: all -> 0x009f
            java.util.Set<V> r3 = r8.mInUseValues     // Catch: all -> 0x009f
            boolean r3 = r3.remove(r9)     // Catch: all -> 0x009f
            r4 = 1
            r5 = 0
            r6 = 2
            if (r3 != 0) goto L_0x0043
            java.lang.Class<?> r2 = r8.TAG     // Catch: all -> 0x009f
            java.lang.String r3 = "release (free, value unrecognized) (object, size) = (%x, %s)"
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: all -> 0x009f
            int r7 = java.lang.System.identityHashCode(r9)     // Catch: all -> 0x009f
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch: all -> 0x009f
            r6[r5] = r7     // Catch: all -> 0x009f
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: all -> 0x009f
            r6[r4] = r0     // Catch: all -> 0x009f
            com.facebook.common.logging.FLog.e(r2, r3, r6)     // Catch: all -> 0x009f
            r8.free(r9)     // Catch: all -> 0x009f
            com.facebook.imagepipeline.memory.PoolStatsTracker r9 = r8.mPoolStatsTracker     // Catch: all -> 0x009f
            r9.onFree(r1)     // Catch: all -> 0x009f
            goto L_0x009a
        L_0x0043:
            if (r2 == 0) goto L_0x007f
            int r0 = r2.mInUseLength     // Catch: all -> 0x009f
            java.util.Queue r3 = r2.mFreeList     // Catch: all -> 0x009f
            int r3 = r3.size()     // Catch: all -> 0x009f
            int r0 = r0 + r3
            int r3 = r2.mMaxLength     // Catch: all -> 0x009f
            if (r0 <= r3) goto L_0x0053
            goto L_0x0054
        L_0x0053:
            r4 = 0
        L_0x0054:
            if (r4 != 0) goto L_0x007f
            boolean r0 = r8.isMaxSizeSoftCapExceeded()     // Catch: all -> 0x009f
            if (r0 != 0) goto L_0x007f
            boolean r0 = r8.isReusable(r9)     // Catch: all -> 0x009f
            if (r0 != 0) goto L_0x0063
            goto L_0x007f
        L_0x0063:
            r2.release(r9)     // Catch: all -> 0x009f
            com.facebook.imagepipeline.memory.BasePool$Counter r0 = r8.mFree     // Catch: all -> 0x009f
            r0.increment(r1)     // Catch: all -> 0x009f
            com.facebook.imagepipeline.memory.BasePool$Counter r0 = r8.mUsed     // Catch: all -> 0x009f
            r0.decrement(r1)     // Catch: all -> 0x009f
            com.facebook.imagepipeline.memory.PoolStatsTracker r0 = r8.mPoolStatsTracker     // Catch: all -> 0x009f
            r0.onValueRelease(r1)     // Catch: all -> 0x009f
            boolean r0 = com.facebook.common.logging.FLog.isLoggable(r6)     // Catch: all -> 0x009f
            if (r0 == 0) goto L_0x009a
            java.lang.System.identityHashCode(r9)     // Catch: all -> 0x009f
            goto L_0x009a
        L_0x007f:
            if (r2 == 0) goto L_0x0084
            r2.decrementInUseCount()     // Catch: all -> 0x009f
        L_0x0084:
            boolean r0 = com.facebook.common.logging.FLog.isLoggable(r6)     // Catch: all -> 0x009f
            if (r0 == 0) goto L_0x008d
            java.lang.System.identityHashCode(r9)     // Catch: all -> 0x009f
        L_0x008d:
            r8.free(r9)     // Catch: all -> 0x009f
            com.facebook.imagepipeline.memory.BasePool$Counter r9 = r8.mUsed     // Catch: all -> 0x009f
            r9.decrement(r1)     // Catch: all -> 0x009f
            com.facebook.imagepipeline.memory.PoolStatsTracker r9 = r8.mPoolStatsTracker     // Catch: all -> 0x009f
            r9.onFree(r1)     // Catch: all -> 0x009f
        L_0x009a:
            r8.logStats()     // Catch: all -> 0x009f
            monitor-exit(r8)     // Catch: all -> 0x009f
            return
        L_0x009f:
            r9 = move-exception
            goto L_0x00a4
        L_0x00a1:
            r9 = move-exception
            monitor-exit(r8)     // Catch: all -> 0x009f
            throw r9     // Catch: all -> 0x009f
        L_0x00a4:
            monitor-exit(r8)     // Catch: all -> 0x009f
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BasePool.release(java.lang.Object):void");
    }

    public synchronized void trimToSize(int i) {
        int i2 = this.mUsed.mNumBytes;
        int i3 = this.mFree.mNumBytes;
        int min = Math.min((i2 + i3) - i, i3);
        if (min > 0) {
            if (FLog.isLoggable(2)) {
                FLog.v(this.TAG, "trimToSize: TargetSize = %d; Initial Size = %d; Bytes to free = %d", Integer.valueOf(i), Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes), Integer.valueOf(min));
            }
            logStats();
            for (int i4 = 0; i4 < this.mBuckets.size() && min > 0; i4++) {
                Bucket<V> valueAt = this.mBuckets.valueAt(i4);
                while (min > 0) {
                    V pop = valueAt.pop();
                    if (pop == null) {
                        break;
                    }
                    free(pop);
                    int i5 = valueAt.mItemSize;
                    min -= i5;
                    this.mFree.decrement(i5);
                }
            }
            logStats();
            if (FLog.isLoggable(2)) {
                int i6 = this.mUsed.mNumBytes;
                int i7 = this.mFree.mNumBytes;
            }
        }
    }
}
