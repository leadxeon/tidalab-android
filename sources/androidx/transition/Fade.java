package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class Fade extends Visibility {

    /* loaded from: classes.dex */
    public static class FadeAnimatorListener extends AnimatorListenerAdapter {
        public boolean mLayerTypeChanged = false;
        public final View mView;

        public FadeAnimatorListener(View view) {
            this.mView = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ViewUtils.IMPL.setTransitionAlpha(this.mView, 1.0f);
            if (this.mLayerTypeChanged) {
                this.mView.setLayerType(0, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            View view = this.mView;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            if (view.hasOverlappingRendering() && this.mView.getLayerType() == 0) {
                this.mLayerTypeChanged = true;
                this.mView.setLayerType(2, null);
            }
        }
    }

    public Fade(int i) {
        if ((i & (-4)) == 0) {
            this.mMode = i;
            return;
        }
        throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
        transitionValues.values.put("android:fade:transitionAlpha", Float.valueOf(ViewUtils.getTransitionAlpha(transitionValues.view)));
    }

    public final Animator createAnimation(final View view, float f, float f2) {
        if (f == f2) {
            return null;
        }
        ViewUtils.IMPL.setTransitionAlpha(view, f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, ViewUtils.TRANSITION_ALPHA, f2);
        ofFloat.addListener(new FadeAnimatorListener(view));
        addListener(new TransitionListenerAdapter(this) { // from class: androidx.transition.Fade.1
            @Override // androidx.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition transition) {
                View view2 = view;
                ViewUtilsBase viewUtilsBase = ViewUtils.IMPL;
                viewUtilsBase.setTransitionAlpha(view2, 1.0f);
                viewUtilsBase.clearNonTransitionAlpha(view);
                transition.removeListener(this);
            }
        });
        return ofFloat;
    }

    @Override // androidx.transition.Visibility
    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ViewUtils.IMPL.saveNonTransitionAlpha(view);
        Float f = (Float) transitionValues.values.get("android:fade:transitionAlpha");
        return createAnimation(view, f != null ? f.floatValue() : 1.0f, 0.0f);
    }
}
