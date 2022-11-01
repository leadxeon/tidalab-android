package com.facebook.common.references;

import androidx.recyclerview.R$dimen;
import com.facebook.common.internal.Closeables;
import com.facebook.common.logging.FLog;
import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public final class CloseableReference<T> implements Cloneable, Closeable {
    public boolean mIsClosed = false;
    public final LeakHandler mLeakHandler;
    public final SharedReference<T> mSharedReference;
    public final Throwable mStacktrace;
    public static Class<CloseableReference> TAG = CloseableReference.class;
    public static final ResourceReleaser<Closeable> DEFAULT_CLOSEABLE_RELEASER = new ResourceReleaser<Closeable>() { // from class: com.facebook.common.references.CloseableReference.1
        @Override // com.facebook.common.references.ResourceReleaser
        public void release(Closeable closeable) {
            try {
                Closeables.close(closeable, true);
            } catch (IOException unused) {
            }
        }
    };
    public static final LeakHandler DEFAULT_LEAK_HANDLER = new LeakHandler() { // from class: com.facebook.common.references.CloseableReference.2
        @Override // com.facebook.common.references.CloseableReference.LeakHandler
        public void reportLeak(SharedReference<Object> sharedReference, Throwable th) {
            Class<CloseableReference> cls = CloseableReference.TAG;
            FLog.w(CloseableReference.TAG, "Finalized without closing: %x %x (type = %s)", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(System.identityHashCode(sharedReference)), sharedReference.get().getClass().getName());
        }

        @Override // com.facebook.common.references.CloseableReference.LeakHandler
        public boolean requiresStacktrace() {
            return false;
        }
    };

    /* loaded from: classes.dex */
    public interface LeakHandler {
        void reportLeak(SharedReference<Object> sharedReference, Throwable th);

        boolean requiresStacktrace();
    }

    public CloseableReference(SharedReference<T> sharedReference, LeakHandler leakHandler, Throwable th) {
        Objects.requireNonNull(sharedReference);
        this.mSharedReference = sharedReference;
        synchronized (sharedReference) {
            sharedReference.ensureValid();
            sharedReference.mRefCount++;
        }
        this.mLeakHandler = leakHandler;
        this.mStacktrace = th;
    }

    /* JADX WARN: Incorrect types in method signature: <T::Ljava/io/Closeable;>(TT;)Lcom/facebook/common/references/CloseableReference<TT;>; */
    public static CloseableReference of(Closeable closeable) {
        return of(closeable, DEFAULT_CLOSEABLE_RELEASER);
    }

    public synchronized CloseableReference<T> cloneOrNull() {
        if (!isValid()) {
            return null;
        }
        return clone();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        int i;
        T t;
        synchronized (this) {
            if (!this.mIsClosed) {
                this.mIsClosed = true;
                SharedReference<T> sharedReference = this.mSharedReference;
                synchronized (sharedReference) {
                    sharedReference.ensureValid();
                    R$dimen.checkArgument(sharedReference.mRefCount > 0);
                    i = sharedReference.mRefCount - 1;
                    sharedReference.mRefCount = i;
                }
                if (i == 0) {
                    synchronized (sharedReference) {
                        t = sharedReference.mValue;
                        sharedReference.mValue = null;
                    }
                    sharedReference.mResourceReleaser.release(t);
                    Map<Object, Integer> map = SharedReference.sLiveObjects;
                    synchronized (map) {
                        Integer num = map.get(t);
                        if (num == null) {
                            FLog.wtf("SharedReference", "No entry in sLiveObjects for value of type %s", t.getClass());
                        } else if (num.intValue() == 1) {
                            map.remove(t);
                        } else {
                            map.put(t, Integer.valueOf(num.intValue() - 1));
                        }
                    }
                }
            }
        }
    }

    public void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (!this.mIsClosed) {
                    this.mLeakHandler.reportLeak(this.mSharedReference, this.mStacktrace);
                    close();
                }
            }
        } finally {
            super.finalize();
        }
    }

    public synchronized T get() {
        R$dimen.checkState(!this.mIsClosed);
        return this.mSharedReference.get();
    }

    public synchronized boolean isValid() {
        return !this.mIsClosed;
    }

    public static boolean isValid(CloseableReference<?> closeableReference) {
        return closeableReference != null && closeableReference.isValid();
    }

    public static <T> CloseableReference<T> of(T t, ResourceReleaser<T> resourceReleaser) {
        return of(t, resourceReleaser, DEFAULT_LEAK_HANDLER);
    }

    public synchronized CloseableReference<T> clone() {
        R$dimen.checkState(isValid());
        return new CloseableReference<>(this.mSharedReference, this.mLeakHandler, this.mStacktrace);
    }

    public static <T> CloseableReference<T> of(T t, ResourceReleaser<T> resourceReleaser, LeakHandler leakHandler) {
        Throwable th = null;
        if (t == null) {
            return null;
        }
        if (leakHandler.requiresStacktrace()) {
            th = new Throwable();
        }
        return new CloseableReference<>(t, resourceReleaser, leakHandler, th);
    }

    public static <T> CloseableReference<T> cloneOrNull(CloseableReference<T> closeableReference) {
        if (closeableReference != null) {
            return closeableReference.cloneOrNull();
        }
        return null;
    }

    public CloseableReference(T t, ResourceReleaser<T> resourceReleaser, LeakHandler leakHandler, Throwable th) {
        this.mSharedReference = new SharedReference<>(t, resourceReleaser);
        this.mLeakHandler = leakHandler;
        this.mStacktrace = th;
    }
}
