package com.swmansion.gesturehandler;

import android.os.Handler;
/* loaded from: classes.dex */
public class TapGestureHandler extends GestureHandler<TapGestureHandler> {
    public Handler mHandler;
    public float mLastX;
    public float mLastY;
    public float mOffsetX;
    public float mOffsetY;
    public float mStartX;
    public float mStartY;
    public int mTapsSoFar;
    public float mMaxDeltaX = Float.MIN_VALUE;
    public float mMaxDeltaY = Float.MIN_VALUE;
    public float mMaxDistSq = Float.MIN_VALUE;
    public long mMaxDurationMs = 500;
    public long mMaxDelayMs = 500;
    public int mNumberOfTaps = 1;
    public int mMinNumberOfPointers = 1;
    public int mNumberOfPointers = 1;
    public final Runnable mFailDelayed = new Runnable() { // from class: com.swmansion.gesturehandler.TapGestureHandler.1
        @Override // java.lang.Runnable
        public void run() {
            TapGestureHandler.this.fail();
        }
    };

    public TapGestureHandler() {
        this.mShouldCancelWhenOutside = true;
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    public void onCancel() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ad  */
    @Override // com.swmansion.gesturehandler.GestureHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onHandle(android.view.MotionEvent r8) {
        /*
            Method dump skipped, instructions count: 243
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.TapGestureHandler.onHandle(android.view.MotionEvent):void");
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    public void onReset() {
        this.mTapsSoFar = 0;
        this.mNumberOfPointers = 0;
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public final void startTap() {
        Handler handler = this.mHandler;
        if (handler == null) {
            this.mHandler = new Handler();
        } else {
            handler.removeCallbacksAndMessages(null);
        }
        this.mHandler.postDelayed(this.mFailDelayed, this.mMaxDurationMs);
    }
}
