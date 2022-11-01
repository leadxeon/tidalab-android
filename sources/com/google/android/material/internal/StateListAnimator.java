package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import java.util.ArrayList;
/* loaded from: classes.dex */
public final class StateListAnimator {
    public final ArrayList<Tuple> tuples = new ArrayList<>();
    public Tuple lastMatch = null;
    public ValueAnimator runningAnimator = null;
    public final Animator.AnimatorListener animationListener = new AnimatorListenerAdapter() { // from class: com.google.android.material.internal.StateListAnimator.1
        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            StateListAnimator stateListAnimator = StateListAnimator.this;
            if (stateListAnimator.runningAnimator == animator) {
                stateListAnimator.runningAnimator = null;
            }
        }
    };

    /* loaded from: classes.dex */
    public static class Tuple {
        public final ValueAnimator animator;
        public final int[] specs;

        public Tuple(int[] iArr, ValueAnimator valueAnimator) {
            this.specs = iArr;
            this.animator = valueAnimator;
        }
    }

    public void addState(int[] iArr, ValueAnimator valueAnimator) {
        Tuple tuple = new Tuple(iArr, valueAnimator);
        valueAnimator.addListener(this.animationListener);
        this.tuples.add(tuple);
    }
}
