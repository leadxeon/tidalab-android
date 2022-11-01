package com.google.android.material.progressindicator;

import android.animation.Animator;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback;
/* loaded from: classes.dex */
public abstract class IndeterminateAnimatorDelegate<T extends Animator> {
    public IndeterminateDrawable drawable;
    public final int[] segmentColors;
    public final float[] segmentPositions;

    public IndeterminateAnimatorDelegate(int i) {
        this.segmentPositions = new float[i * 2];
        this.segmentColors = new int[i];
    }

    public abstract void cancelAnimatorImmediately();

    public abstract void invalidateSpecValues();

    public abstract void registerAnimatorsCompleteCallback(Animatable2Compat$AnimationCallback animatable2Compat$AnimationCallback);

    public abstract void requestCancelAnimatorAfterCurrentCycle();

    public abstract void startAnimator();

    public abstract void unregisterAnimatorsCompleteCallback();
}
