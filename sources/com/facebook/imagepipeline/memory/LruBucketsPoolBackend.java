package com.facebook.imagepipeline.memory;

import com.facebook.imagepipeline.memory.BucketMap;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class LruBucketsPoolBackend<T> {
    public final Set<T> mCurrentItems = new HashSet();
    public final BucketMap<T> mMap = new BucketMap<>();

    public abstract T get(int i);

    public abstract int getSize(T t);

    public T pop() {
        T t;
        BucketMap<T> bucketMap = this.mMap;
        synchronized (bucketMap) {
            BucketMap.LinkedEntry<T> linkedEntry = bucketMap.mTail;
            if (linkedEntry == null) {
                t = null;
            } else {
                t = linkedEntry.value.pollLast();
                if (linkedEntry.value.isEmpty()) {
                    bucketMap.prune(linkedEntry);
                    bucketMap.mMap.remove(linkedEntry.key);
                }
            }
        }
        if (t != null) {
            synchronized (this) {
                this.mCurrentItems.remove(t);
            }
        }
        return t;
    }
}
