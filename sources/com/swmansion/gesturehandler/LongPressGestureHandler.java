package com.swmansion.gesturehandler;

import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
/* loaded from: classes.dex */
public class LongPressGestureHandler extends GestureHandler<LongPressGestureHandler> {
    public Handler mHandler;
    public float mMaxDistSq;
    public long mMinDurationMs = 500;
    public float mStartX;
    public float mStartY;

    public LongPressGestureHandler(Context context) {
        this.mShouldCancelWhenOutside = true;
        this.mMaxDistSq = 10.0f * context.getResources().getDisplayMetrics().density;
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    public void onHandle(MotionEvent motionEvent) {
        if (this.mState == 0) {
            begin();
            this.mStartX = motionEvent.getRawX();
            this.mStartY = motionEvent.getRawY();
            Handler handler = new Handler();
            this.mHandler = handler;
            long j = this.mMinDurationMs;
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i > 0) {
                handler.postDelayed(new Runnable() { // from class: com.swmansion.gesturehandler.LongPressGestureHandler.1
                    @Override // java.lang.Runnable
                    public void run() {
                        LongPressGestureHandler.this.activate();
                    }
                }, j);
            } else if (i == 0) {
                activate();
            }
        }
        if (motionEvent.getActionMasked() == 1) {
            Handler handler2 = this.mHandler;
            if (handler2 != null) {
                handler2.removeCallbacksAndMessages(null);
                this.mHandler = null;
            }
            if (this.mState == 4) {
                end();
            } else {
                fail();
            }
        } else {
            float rawX = motionEvent.getRawX() - this.mStartX;
            float rawY = motionEvent.getRawY() - this.mStartY;
            if ((rawY * rawY) + (rawX * rawX) <= this.mMaxDistSq) {
                return;
            }
            if (this.mState == 4) {
                cancel();
            } else {
                fail();
            }
        }
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    public void onStateChange(int i, int i2) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
    }
}
