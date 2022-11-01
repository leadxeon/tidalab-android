package com.facebook.common.references;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public class SharedReference<T> {
    public static final Map<Object, Integer> sLiveObjects = new IdentityHashMap();
    public int mRefCount = 1;
    public final ResourceReleaser<T> mResourceReleaser;
    public T mValue;

    /* loaded from: classes.dex */
    public static class NullReferenceException extends RuntimeException {
        public NullReferenceException() {
            super("Null shared reference");
        }
    }

    public SharedReference(T t, ResourceReleaser<T> resourceReleaser) {
        Objects.requireNonNull(t);
        this.mValue = t;
        Objects.requireNonNull(resourceReleaser);
        this.mResourceReleaser = resourceReleaser;
        Map<Object, Integer> map = sLiveObjects;
        synchronized (map) {
            Integer num = map.get(t);
            if (num == null) {
                map.put(t, 1);
            } else {
                map.put(t, Integer.valueOf(num.intValue() + 1));
            }
        }
    }

    public final void ensureValid() {
        boolean z;
        boolean z2;
        synchronized (this) {
            z = false;
            z2 = this.mRefCount > 0;
        }
        if (z2) {
            z = true;
        }
        if (!z) {
            throw new NullReferenceException();
        }
    }

    public synchronized T get() {
        return this.mValue;
    }
}
