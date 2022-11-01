package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes.dex */
public class SpringAnimation extends AnimationDriver {
    public int mCurrentLoop;
    public final PhysicsState mCurrentState;
    public double mDisplacementFromRestThreshold;
    public double mEndValue;
    public double mInitialVelocity;
    public int mIterations;
    public long mLastTime;
    public double mOriginalValue;
    public boolean mOvershootClampingEnabled;
    public double mRestSpeedThreshold;
    public double mSpringDamping;
    public double mSpringMass;
    public boolean mSpringStarted;
    public double mSpringStiffness;
    public double mStartValue;
    public double mTimeAccumulator;

    /* loaded from: classes.dex */
    public static class PhysicsState {
        public double position;
        public double velocity;

        public PhysicsState(AnonymousClass1 r1) {
        }
    }

    public SpringAnimation(ReadableMap readableMap) {
        PhysicsState physicsState = new PhysicsState(null);
        this.mCurrentState = physicsState;
        physicsState.velocity = readableMap.getDouble("initialVelocity");
        resetConfig(readableMap);
    }

    public final boolean isAtRest() {
        if (Math.abs(this.mCurrentState.velocity) <= this.mRestSpeedThreshold) {
            if (Math.abs(this.mEndValue - this.mCurrentState.position) <= this.mDisplacementFromRestThreshold || this.mSpringStiffness == 0.0d) {
                return true;
            }
        }
        return false;
    }

    @Override // com.facebook.react.animated.AnimationDriver
    public void resetConfig(ReadableMap readableMap) {
        this.mSpringStiffness = readableMap.getDouble("stiffness");
        this.mSpringDamping = readableMap.getDouble("damping");
        this.mSpringMass = readableMap.getDouble("mass");
        this.mInitialVelocity = this.mCurrentState.velocity;
        this.mEndValue = readableMap.getDouble("toValue");
        this.mRestSpeedThreshold = readableMap.getDouble("restSpeedThreshold");
        this.mDisplacementFromRestThreshold = readableMap.getDouble("restDisplacementThreshold");
        this.mOvershootClampingEnabled = readableMap.getBoolean("overshootClamping");
        boolean z = true;
        int i = readableMap.hasKey("iterations") ? readableMap.getInt("iterations") : 1;
        this.mIterations = i;
        if (i != 0) {
            z = false;
        }
        this.mHasFinished = z;
        this.mCurrentLoop = 0;
        this.mTimeAccumulator = 0.0d;
        this.mSpringStarted = false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0133, code lost:
        if (r3 == false) goto L_0x0154;
     */
    @Override // com.facebook.react.animated.AnimationDriver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void runAnimationStep(long r25) {
        /*
            Method dump skipped, instructions count: 384
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.SpringAnimation.runAnimationStep(long):void");
    }
}
