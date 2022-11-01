package com.facebook.react.modules.core;

import androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility;
import androidx.recyclerview.R$dimen;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.modules.core.ChoreographerCompat;
import java.util.ArrayDeque;
import java.util.Objects;
/* loaded from: classes.dex */
public class ReactChoreographer {
    public static ReactChoreographer sInstance;
    public volatile ChoreographerCompat mChoreographer;
    public final Object mCallbackQueuesLock = new Object();
    public int mTotalCallbacks = 0;
    public boolean mHasPostedCallback = false;
    public final ReactChoreographerDispatcher mReactChoreographerDispatcher = new ReactChoreographerDispatcher(null);
    public final ArrayDeque<ChoreographerCompat.FrameCallback>[] mCallbackQueues = new ArrayDeque[5];

    /* renamed from: com.facebook.react.modules.core.ReactChoreographer$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass2 implements Runnable {
        public final /* synthetic */ Runnable val$runnable;

        public AnonymousClass2(Runnable runnable) {
            this.val$runnable = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (ReactChoreographer.class) {
                if (ReactChoreographer.this.mChoreographer == null) {
                    ReactChoreographer reactChoreographer = ReactChoreographer.this;
                    UiThreadUtil.assertOnUiThread();
                    if (ChoreographerCompat.sInstance == null) {
                        ChoreographerCompat.sInstance = new ChoreographerCompat();
                    }
                    reactChoreographer.mChoreographer = ChoreographerCompat.sInstance;
                }
            }
            Runnable runnable = this.val$runnable;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    /* loaded from: classes.dex */
    public class ReactChoreographerDispatcher extends ChoreographerCompat.FrameCallback {
        public ReactChoreographerDispatcher(AnonymousClass1 r2) {
        }

        @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
        public void doFrame(long j) {
            synchronized (ReactChoreographer.this.mCallbackQueuesLock) {
                ReactChoreographer.this.mHasPostedCallback = false;
                int i = 0;
                while (true) {
                    ReactChoreographer reactChoreographer = ReactChoreographer.this;
                    ArrayDeque<ChoreographerCompat.FrameCallback>[] arrayDequeArr = reactChoreographer.mCallbackQueues;
                    if (i < arrayDequeArr.length) {
                        ArrayDeque<ChoreographerCompat.FrameCallback> arrayDeque = arrayDequeArr[i];
                        int size = arrayDeque.size();
                        for (int i2 = 0; i2 < size; i2++) {
                            ChoreographerCompat.FrameCallback pollFirst = arrayDeque.pollFirst();
                            if (pollFirst != null) {
                                pollFirst.doFrame(j);
                                ReactChoreographer reactChoreographer2 = ReactChoreographer.this;
                                reactChoreographer2.mTotalCallbacks--;
                            } else {
                                FLog.e("ReactNative", "Tried to execute non-existent frame callback");
                            }
                        }
                        i++;
                    } else {
                        reactChoreographer.maybeRemoveFrameCallback();
                    }
                }
            }
        }
    }

    public ReactChoreographer() {
        int i = 0;
        SolverVariable$Type$r8$EnumUnboxingUtility.com$facebook$react$modules$core$ReactChoreographer$CallbackType$s$values();
        while (true) {
            ArrayDeque<ChoreographerCompat.FrameCallback>[] arrayDequeArr = this.mCallbackQueues;
            if (i < arrayDequeArr.length) {
                arrayDequeArr[i] = new ArrayDeque<>();
                i++;
            } else {
                UiThreadUtil.runOnUiThread(new AnonymousClass2(null));
                return;
            }
        }
    }

    public static ReactChoreographer getInstance() {
        R$dimen.assertNotNull(sInstance, "ReactChoreographer needs to be initialized.");
        return sInstance;
    }

    public final void maybeRemoveFrameCallback() {
        R$dimen.assertCondition(this.mTotalCallbacks >= 0);
        if (this.mTotalCallbacks == 0 && this.mHasPostedCallback) {
            if (this.mChoreographer != null) {
                ChoreographerCompat choreographerCompat = this.mChoreographer;
                ReactChoreographerDispatcher reactChoreographerDispatcher = this.mReactChoreographerDispatcher;
                Objects.requireNonNull(choreographerCompat);
                if (reactChoreographerDispatcher.mFrameCallback == null) {
                    reactChoreographerDispatcher.mFrameCallback = new ChoreographerCompat.FrameCallback.AnonymousClass1();
                }
                choreographerCompat.mChoreographer.removeFrameCallback(reactChoreographerDispatcher.mFrameCallback);
            }
            this.mHasPostedCallback = false;
        }
    }

    public void postFrameCallback$enumunboxing$(int i, ChoreographerCompat.FrameCallback frameCallback) {
        synchronized (this.mCallbackQueuesLock) {
            this.mCallbackQueues[SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(i)].addLast(frameCallback);
            int i2 = this.mTotalCallbacks + 1;
            this.mTotalCallbacks = i2;
            R$dimen.assertCondition(i2 > 0);
            if (!this.mHasPostedCallback) {
                if (this.mChoreographer == null) {
                    UiThreadUtil.runOnUiThread(new AnonymousClass2(new Runnable() { // from class: com.facebook.react.modules.core.ReactChoreographer.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ReactChoreographer reactChoreographer = ReactChoreographer.this;
                            reactChoreographer.mChoreographer.postFrameCallback(reactChoreographer.mReactChoreographerDispatcher);
                            reactChoreographer.mHasPostedCallback = true;
                        }
                    }));
                } else {
                    this.mChoreographer.postFrameCallback(this.mReactChoreographerDispatcher);
                    this.mHasPostedCallback = true;
                }
            }
        }
    }

    public void removeFrameCallback$enumunboxing$(int i, ChoreographerCompat.FrameCallback frameCallback) {
        synchronized (this.mCallbackQueuesLock) {
            if (this.mCallbackQueues[SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(i)].removeFirstOccurrence(frameCallback)) {
                this.mTotalCallbacks--;
                maybeRemoveFrameCallback();
            } else {
                FLog.e("ReactNative", "Tried to remove non-existent frame callback");
            }
        }
    }
}
