package com.google.android.material.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.animation.AnimationUtils;
/* loaded from: classes.dex */
public class HideBottomViewOnScrollBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public ViewPropertyAnimator currentAnimator;
    public int height = 0;
    public int currentState = 2;
    public int additionalHiddenOffsetY = 0;

    public HideBottomViewOnScrollBehavior() {
    }

    public final void animateChildTo(V v, int i, long j, TimeInterpolator timeInterpolator) {
        this.currentAnimator = v.animate().translationY(i).setInterpolator(timeInterpolator).setDuration(j).setListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.behavior.HideBottomViewOnScrollBehavior.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                HideBottomViewOnScrollBehavior.this.currentAnimator = null;
            }
        });
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i) {
        this.height = v.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).bottomMargin;
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        if (i2 > 0) {
            if (this.currentState != 1) {
                ViewPropertyAnimator viewPropertyAnimator = this.currentAnimator;
                if (viewPropertyAnimator != null) {
                    viewPropertyAnimator.cancel();
                    v.clearAnimation();
                }
                this.currentState = 1;
                animateChildTo(v, this.height + this.additionalHiddenOffsetY, 175L, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
            }
        } else if (i2 < 0 && this.currentState != 2) {
            ViewPropertyAnimator viewPropertyAnimator2 = this.currentAnimator;
            if (viewPropertyAnimator2 != null) {
                viewPropertyAnimator2.cancel();
                v.clearAnimation();
            }
            this.currentState = 2;
            animateChildTo(v, 0, 225L, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
        return i == 2;
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
