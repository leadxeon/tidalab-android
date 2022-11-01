package com.facebook.react.modules.core;

import android.view.Choreographer;
/* loaded from: classes.dex */
public class ChoreographerCompat {
    public static ChoreographerCompat sInstance;
    public Choreographer mChoreographer = Choreographer.getInstance();

    /* loaded from: classes.dex */
    public static abstract class FrameCallback {
        public Choreographer.FrameCallback mFrameCallback;

        /* renamed from: com.facebook.react.modules.core.ChoreographerCompat$FrameCallback$1  reason: invalid class name */
        /* loaded from: classes.dex */
        public class AnonymousClass1 implements Choreographer.FrameCallback {
            public AnonymousClass1() {
            }

            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j) {
                FrameCallback.this.doFrame(j);
            }
        }

        public abstract void doFrame(long j);
    }

    public void postFrameCallback(FrameCallback frameCallback) {
        if (frameCallback.mFrameCallback == null) {
            frameCallback.mFrameCallback = new FrameCallback.AnonymousClass1();
        }
        this.mChoreographer.postFrameCallback(frameCallback.mFrameCallback);
    }
}
