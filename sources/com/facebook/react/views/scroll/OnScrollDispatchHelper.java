package com.facebook.react.views.scroll;

import android.os.SystemClock;
/* loaded from: classes.dex */
public class OnScrollDispatchHelper {
    public int mPrevX = Integer.MIN_VALUE;
    public int mPrevY = Integer.MIN_VALUE;
    public float mXFlingVelocity = 0.0f;
    public float mYFlingVelocity = 0.0f;
    public long mLastScrollEventTimeMs = -11;

    public boolean onScrollChanged(int i, int i2) {
        long uptimeMillis = SystemClock.uptimeMillis();
        long j = this.mLastScrollEventTimeMs;
        boolean z = (uptimeMillis - j <= 10 && this.mPrevX == i && this.mPrevY == i2) ? false : true;
        if (uptimeMillis - j != 0) {
            this.mXFlingVelocity = (i - this.mPrevX) / ((float) (uptimeMillis - j));
            this.mYFlingVelocity = (i2 - this.mPrevY) / ((float) (uptimeMillis - j));
        }
        this.mLastScrollEventTimeMs = uptimeMillis;
        this.mPrevX = i;
        this.mPrevY = i2;
        return z;
    }
}
