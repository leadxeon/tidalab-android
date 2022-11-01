package com.facebook.cache.disk;

import com.facebook.cache.common.CacheKey;
/* loaded from: classes.dex */
public class SettableCacheEvent {
    public static final Object RECYCLER_LOCK = new Object();
    public static SettableCacheEvent sFirstRecycledEvent;
    public static int sRecycledCount;
    public CacheKey mCacheKey;
    public SettableCacheEvent mNextRecycledEvent;

    public static SettableCacheEvent obtain() {
        synchronized (RECYCLER_LOCK) {
            SettableCacheEvent settableCacheEvent = sFirstRecycledEvent;
            if (settableCacheEvent == null) {
                return new SettableCacheEvent();
            }
            sFirstRecycledEvent = settableCacheEvent.mNextRecycledEvent;
            settableCacheEvent.mNextRecycledEvent = null;
            sRecycledCount--;
            return settableCacheEvent;
        }
    }

    public void recycle() {
        synchronized (RECYCLER_LOCK) {
            int i = sRecycledCount;
            if (i < 5) {
                sRecycledCount = i + 1;
                SettableCacheEvent settableCacheEvent = sFirstRecycledEvent;
                if (settableCacheEvent != null) {
                    this.mNextRecycledEvent = settableCacheEvent;
                }
                sFirstRecycledEvent = this;
            }
        }
    }
}
