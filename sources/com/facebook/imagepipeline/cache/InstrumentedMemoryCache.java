package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Predicate;
import com.facebook.common.references.CloseableReference;
/* loaded from: classes.dex */
public class InstrumentedMemoryCache<K, V> implements MemoryCache<K, V> {
    public final MemoryCache<K, V> mDelegate;
    public final MemoryCacheTracker mTracker;

    public InstrumentedMemoryCache(MemoryCache<K, V> memoryCache, MemoryCacheTracker memoryCacheTracker) {
        this.mDelegate = memoryCache;
        this.mTracker = memoryCacheTracker;
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public CloseableReference<V> cache(K k, CloseableReference<V> closeableReference) {
        this.mTracker.onCachePut();
        return this.mDelegate.cache(k, closeableReference);
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public boolean contains(Predicate<K> predicate) {
        return this.mDelegate.contains(predicate);
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public CloseableReference<V> get(K k) {
        CloseableReference<V> closeableReference = this.mDelegate.get(k);
        if (closeableReference == null) {
            this.mTracker.onCacheMiss();
        } else {
            this.mTracker.onCacheHit(k);
        }
        return closeableReference;
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public int removeAll(Predicate<K> predicate) {
        return this.mDelegate.removeAll(predicate);
    }
}
