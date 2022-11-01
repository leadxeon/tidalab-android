package com.facebook.react.modules.core;

import android.os.SystemClock;
import android.util.SparseArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.jstasks.HeadlessJsTaskContext;
import com.facebook.react.modules.core.ChoreographerCompat;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public class JavaTimerManager {
    public IdleCallbackRunnable mCurrentIdleCallbackRunnable;
    public final DevSupportManager mDevSupportManager;
    public final JavaScriptTimerManager mJavaScriptTimerManager;
    public final ReactApplicationContext mReactApplicationContext;
    public final ReactChoreographer mReactChoreographer;
    public final Object mTimerGuard = new Object();
    public final Object mIdleCallbackGuard = new Object();
    public final AtomicBoolean isPaused = new AtomicBoolean(true);
    public final AtomicBoolean isRunningTasks = new AtomicBoolean(false);
    public final TimerFrameCallback mTimerFrameCallback = new TimerFrameCallback(null);
    public final IdleFrameCallback mIdleFrameCallback = new IdleFrameCallback(null);
    public boolean mFrameCallbackPosted = false;
    public boolean mFrameIdleCallbackPosted = false;
    public boolean mSendIdleEvents = false;
    public final PriorityQueue<Timer> mTimers = new PriorityQueue<>(11, new Comparator<Timer>(this) { // from class: com.facebook.react.modules.core.JavaTimerManager.1
        @Override // java.util.Comparator
        public int compare(Timer timer, Timer timer2) {
            int i = ((timer.mTargetTime - timer2.mTargetTime) > 0L ? 1 : ((timer.mTargetTime - timer2.mTargetTime) == 0L ? 0 : -1));
            if (i == 0) {
                return 0;
            }
            return i < 0 ? -1 : 1;
        }
    });
    public final SparseArray<Timer> mTimerIdsToTimers = new SparseArray<>();

    /* loaded from: classes.dex */
    public class IdleCallbackRunnable implements Runnable {
        public volatile boolean mCancelled = false;
        public final long mFrameStartTime;

        public IdleCallbackRunnable(long j) {
            this.mFrameStartTime = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            JavaTimerManager javaTimerManager;
            boolean z;
            ReactApplicationContext reactApplicationContextIfActiveOrWarn;
            if (!this.mCancelled) {
                long uptimeMillis = SystemClock.uptimeMillis() - (this.mFrameStartTime / 1000000);
                long currentTimeMillis = System.currentTimeMillis() - uptimeMillis;
                if (16.666666f - ((float) uptimeMillis) >= 1.0f) {
                    synchronized (JavaTimerManager.this.mIdleCallbackGuard) {
                        javaTimerManager = JavaTimerManager.this;
                        z = javaTimerManager.mSendIdleEvents;
                    }
                    if (z) {
                        double d = currentTimeMillis;
                        reactApplicationContextIfActiveOrWarn = TimingModule.this.getReactApplicationContextIfActiveOrWarn();
                        if (reactApplicationContextIfActiveOrWarn != null) {
                            ((JSTimers) reactApplicationContextIfActiveOrWarn.getJSModule(JSTimers.class)).callIdleCallbacks(d);
                        }
                    }
                    JavaTimerManager.this.mCurrentIdleCallbackRunnable = null;
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class IdleFrameCallback extends ChoreographerCompat.FrameCallback {
        public IdleFrameCallback(AnonymousClass1 r2) {
        }

        @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
        public void doFrame(long j) {
            if (!JavaTimerManager.this.isPaused.get() || JavaTimerManager.this.isRunningTasks.get()) {
                IdleCallbackRunnable idleCallbackRunnable = JavaTimerManager.this.mCurrentIdleCallbackRunnable;
                if (idleCallbackRunnable != null) {
                    idleCallbackRunnable.mCancelled = true;
                }
                JavaTimerManager javaTimerManager = JavaTimerManager.this;
                IdleCallbackRunnable idleCallbackRunnable2 = new IdleCallbackRunnable(j);
                javaTimerManager.mCurrentIdleCallbackRunnable = idleCallbackRunnable2;
                javaTimerManager.mReactApplicationContext.runOnJSQueueThread(idleCallbackRunnable2);
                JavaTimerManager.this.mReactChoreographer.postFrameCallback$enumunboxing$(5, this);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class Timer {
        public final int mCallbackID;
        public final int mInterval;
        public final boolean mRepeat;
        public long mTargetTime;

        public Timer(int i, long j, int i2, boolean z, AnonymousClass1 r6) {
            this.mCallbackID = i;
            this.mTargetTime = j;
            this.mInterval = i2;
            this.mRepeat = z;
        }
    }

    /* loaded from: classes.dex */
    public class TimerFrameCallback extends ChoreographerCompat.FrameCallback {
        public WritableArray mTimersToCall = null;

        public TimerFrameCallback(AnonymousClass1 r2) {
        }

        @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
        public void doFrame(long j) {
            if (!JavaTimerManager.this.isPaused.get() || JavaTimerManager.this.isRunningTasks.get()) {
                long j2 = j / 1000000;
                synchronized (JavaTimerManager.this.mTimerGuard) {
                    while (!JavaTimerManager.this.mTimers.isEmpty() && JavaTimerManager.this.mTimers.peek().mTargetTime < j2) {
                        Timer poll = JavaTimerManager.this.mTimers.poll();
                        if (this.mTimersToCall == null) {
                            this.mTimersToCall = Arguments.createArray();
                        }
                        this.mTimersToCall.pushInt(poll.mCallbackID);
                        if (poll.mRepeat) {
                            poll.mTargetTime = poll.mInterval + j2;
                            JavaTimerManager.this.mTimers.add(poll);
                        } else {
                            JavaTimerManager.this.mTimerIdsToTimers.remove(poll.mCallbackID);
                        }
                    }
                }
                WritableArray writableArray = this.mTimersToCall;
                if (writableArray != null) {
                    ReactApplicationContext reactApplicationContextIfActiveOrWarn = TimingModule.this.getReactApplicationContextIfActiveOrWarn();
                    if (reactApplicationContextIfActiveOrWarn != null) {
                        ((JSTimers) reactApplicationContextIfActiveOrWarn.getJSModule(JSTimers.class)).callTimers(writableArray);
                    }
                    this.mTimersToCall = null;
                }
                JavaTimerManager.this.mReactChoreographer.postFrameCallback$enumunboxing$(4, this);
            }
        }
    }

    public JavaTimerManager(ReactApplicationContext reactApplicationContext, JavaScriptTimerManager javaScriptTimerManager, ReactChoreographer reactChoreographer, DevSupportManager devSupportManager) {
        this.mReactApplicationContext = reactApplicationContext;
        this.mJavaScriptTimerManager = javaScriptTimerManager;
        this.mReactChoreographer = reactChoreographer;
        this.mDevSupportManager = devSupportManager;
    }

    public final void clearFrameCallback() {
        HeadlessJsTaskContext instance = HeadlessJsTaskContext.getInstance(this.mReactApplicationContext);
        if (this.mFrameCallbackPosted && this.isPaused.get()) {
            if (!(instance.mActiveTasks.size() > 0)) {
                this.mReactChoreographer.removeFrameCallback$enumunboxing$(4, this.mTimerFrameCallback);
                this.mFrameCallbackPosted = false;
            }
        }
    }

    public final void maybeIdleCallback() {
        if (this.isPaused.get() && !this.isRunningTasks.get()) {
            clearFrameCallback();
        }
    }

    public final void maybeSetChoreographerIdleCallback() {
        synchronized (this.mIdleCallbackGuard) {
            if (this.mSendIdleEvents && !this.mFrameIdleCallbackPosted) {
                this.mReactChoreographer.postFrameCallback$enumunboxing$(5, this.mIdleFrameCallback);
                this.mFrameIdleCallbackPosted = true;
            }
        }
    }
}
