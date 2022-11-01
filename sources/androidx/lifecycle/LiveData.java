package androidx.lifecycle;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.Lifecycle;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class LiveData<T> {
    public static final Object NOT_SET = new Object();
    public boolean mChangingActiveState;
    public volatile Object mData;
    public boolean mDispatchInvalidated;
    public boolean mDispatchingValue;
    public volatile Object mPendingData;
    public final Object mDataLock = new Object();
    public SafeIterableMap<Observer<? super T>, LiveData<T>.ObserverWrapper> mObservers = new SafeIterableMap<>();
    public int mActiveCount = 0;
    public int mVersion = -1;

    /* loaded from: classes.dex */
    public class AlwaysActiveObserver extends LiveData<T>.ObserverWrapper {
        public AlwaysActiveObserver(LiveData liveData, Observer<? super T> observer) {
            super(observer);
        }

        @Override // androidx.lifecycle.LiveData.ObserverWrapper
        public boolean shouldBeActive() {
            return true;
        }
    }

    /* loaded from: classes.dex */
    public class LifecycleBoundObserver extends LiveData<T>.ObserverWrapper implements LifecycleEventObserver {
        public final LifecycleOwner mOwner;
        public final /* synthetic */ LiveData this$0;

        @Override // androidx.lifecycle.LiveData.ObserverWrapper
        public void detachObserver() {
            this.mOwner.getLifecycle().removeObserver(this);
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            Lifecycle.State currentState = this.mOwner.getLifecycle().getCurrentState();
            if (currentState == Lifecycle.State.DESTROYED) {
                this.this$0.removeObserver(this.mObserver);
                return;
            }
            for (Lifecycle.State state = null; state != currentState; state = currentState) {
                activeStateChanged(shouldBeActive());
                currentState = this.mOwner.getLifecycle().getCurrentState();
            }
        }

        @Override // androidx.lifecycle.LiveData.ObserverWrapper
        public boolean shouldBeActive() {
            return this.mOwner.getLifecycle().getCurrentState().compareTo(Lifecycle.State.STARTED) >= 0;
        }
    }

    /* loaded from: classes.dex */
    public abstract class ObserverWrapper {
        public boolean mActive;
        public int mLastVersion = -1;
        public final Observer<? super T> mObserver;

        public ObserverWrapper(Observer<? super T> observer) {
            this.mObserver = observer;
        }

        public void activeStateChanged(boolean z) {
            if (z != this.mActive) {
                this.mActive = z;
                LiveData liveData = LiveData.this;
                int i = z ? 1 : -1;
                int i2 = liveData.mActiveCount;
                liveData.mActiveCount = i + i2;
                if (!liveData.mChangingActiveState) {
                    liveData.mChangingActiveState = true;
                    while (true) {
                        try {
                            int i3 = liveData.mActiveCount;
                            if (i2 == i3) {
                                break;
                            }
                            boolean z2 = i2 == 0 && i3 > 0;
                            boolean z3 = i2 > 0 && i3 == 0;
                            if (z2) {
                                liveData.onActive();
                            } else if (z3) {
                                liveData.onInactive();
                            }
                            i2 = i3;
                        } finally {
                            liveData.mChangingActiveState = false;
                        }
                    }
                }
                if (this.mActive) {
                    LiveData.this.dispatchingValue(this);
                }
            }
        }

        public void detachObserver() {
        }

        public abstract boolean shouldBeActive();
    }

    public LiveData() {
        Object obj = NOT_SET;
        this.mPendingData = obj;
        this.mData = obj;
    }

    public static void assertMainThread(String str) {
        if (!ArchTaskExecutor.getInstance().isMainThread()) {
            throw new IllegalStateException(GeneratedOutlineSupport.outline9("Cannot invoke ", str, " on a background thread"));
        }
    }

    public final void considerNotify(LiveData<T>.ObserverWrapper observerWrapper) {
        if (observerWrapper.mActive) {
            if (!observerWrapper.shouldBeActive()) {
                observerWrapper.activeStateChanged(false);
                return;
            }
            int i = observerWrapper.mLastVersion;
            int i2 = this.mVersion;
            if (i < i2) {
                observerWrapper.mLastVersion = i2;
                observerWrapper.mObserver.onChanged((Object) this.mData);
            }
        }
    }

    public void dispatchingValue(LiveData<T>.ObserverWrapper observerWrapper) {
        if (this.mDispatchingValue) {
            this.mDispatchInvalidated = true;
            return;
        }
        this.mDispatchingValue = true;
        do {
            this.mDispatchInvalidated = false;
            if (observerWrapper == null) {
                SafeIterableMap<Observer<? super T>, LiveData<T>.ObserverWrapper>.IteratorWithAdditions iteratorWithAdditions = this.mObservers.iteratorWithAdditions();
                while (iteratorWithAdditions.hasNext()) {
                    considerNotify((ObserverWrapper) ((Map.Entry) iteratorWithAdditions.next()).getValue());
                    if (this.mDispatchInvalidated) {
                        break;
                    }
                }
            } else {
                considerNotify(observerWrapper);
                observerWrapper = null;
            }
        } while (this.mDispatchInvalidated);
        this.mDispatchingValue = false;
    }

    public void onActive() {
    }

    public void onInactive() {
    }

    public void removeObserver(Observer<? super T> observer) {
        assertMainThread("removeObserver");
        LiveData<T>.ObserverWrapper remove = this.mObservers.remove(observer);
        if (remove != null) {
            remove.detachObserver();
            remove.activeStateChanged(false);
        }
    }
}
