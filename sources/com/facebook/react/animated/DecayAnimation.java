package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes.dex */
public class DecayAnimation extends AnimationDriver {
    public int mCurrentLoop;
    public double mDeceleration;
    public double mFromValue;
    public int mIterations;
    public double mLastValue;
    public long mStartFrameTimeMillis;
    public final double mVelocity;

    public DecayAnimation(ReadableMap readableMap) {
        this.mVelocity = readableMap.getDouble("velocity");
        resetConfig(readableMap);
    }

    @Override // com.facebook.react.animated.AnimationDriver
    public void resetConfig(ReadableMap readableMap) {
        this.mDeceleration = readableMap.getDouble("deceleration");
        boolean z = true;
        int i = readableMap.hasKey("iterations") ? readableMap.getInt("iterations") : 1;
        this.mIterations = i;
        this.mCurrentLoop = 1;
        if (i != 0) {
            z = false;
        }
        this.mHasFinished = z;
        this.mStartFrameTimeMillis = -1L;
        this.mFromValue = 0.0d;
        this.mLastValue = 0.0d;
    }

    @Override // com.facebook.react.animated.AnimationDriver
    public void runAnimationStep(long j) {
        long j2 = j / 1000000;
        if (this.mStartFrameTimeMillis == -1) {
            this.mStartFrameTimeMillis = j2 - 16;
            double d = this.mFromValue;
            if (d == this.mLastValue) {
                this.mFromValue = this.mAnimatedValue.mValue;
            } else {
                this.mAnimatedValue.mValue = d;
            }
            this.mLastValue = this.mAnimatedValue.mValue;
        }
        double d2 = this.mFromValue;
        double d3 = this.mVelocity;
        double d4 = this.mDeceleration;
        double exp = ((1.0d - Math.exp((-(1.0d - d4)) * (j2 - this.mStartFrameTimeMillis))) * (d3 / (1.0d - d4))) + d2;
        if (Math.abs(this.mLastValue - exp) < 0.1d) {
            int i = this.mIterations;
            if (i == -1 || this.mCurrentLoop < i) {
                this.mStartFrameTimeMillis = -1L;
                this.mCurrentLoop++;
            } else {
                this.mHasFinished = true;
                return;
            }
        }
        this.mLastValue = exp;
        this.mAnimatedValue.mValue = exp;
    }
}
