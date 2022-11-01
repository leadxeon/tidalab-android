package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Property;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback;
import com.google.android.material.R$style;
import com.reactnativecommunity.webview.RNCWebViewManager;
import com.tidalab.v2board.clash.foss.R;
import java.util.Arrays;
/* loaded from: classes.dex */
public final class LinearIndeterminateDisjointAnimatorDelegate extends IndeterminateAnimatorDelegate<ObjectAnimator> {
    public float animationFraction;
    public ObjectAnimator animator;
    public boolean animatorCompleteEndRequested;
    public final BaseProgressIndicatorSpec baseSpec;
    public boolean dirtyColors;
    public final Interpolator[] interpolatorArray;
    public static final int[] DURATION_TO_MOVE_SEGMENT_ENDS = {533, 567, 850, 750};
    public static final int[] DELAY_TO_MOVE_SEGMENT_ENDS = {1267, RNCWebViewManager.COMMAND_CLEAR_FORM_DATA, 333, 0};
    public static final Property<LinearIndeterminateDisjointAnimatorDelegate, Float> ANIMATION_FRACTION = new Property<LinearIndeterminateDisjointAnimatorDelegate, Float>(Float.class, "animationFraction") { // from class: com.google.android.material.progressindicator.LinearIndeterminateDisjointAnimatorDelegate.2
        @Override // android.util.Property
        public Float get(LinearIndeterminateDisjointAnimatorDelegate linearIndeterminateDisjointAnimatorDelegate) {
            return Float.valueOf(linearIndeterminateDisjointAnimatorDelegate.animationFraction);
        }

        @Override // android.util.Property
        public void set(LinearIndeterminateDisjointAnimatorDelegate linearIndeterminateDisjointAnimatorDelegate, Float f) {
            LinearIndeterminateDisjointAnimatorDelegate linearIndeterminateDisjointAnimatorDelegate2 = linearIndeterminateDisjointAnimatorDelegate;
            float floatValue = f.floatValue();
            linearIndeterminateDisjointAnimatorDelegate2.animationFraction = floatValue;
            int i = (int) (floatValue * 1800.0f);
            for (int i2 = 0; i2 < 4; i2++) {
                linearIndeterminateDisjointAnimatorDelegate2.segmentPositions[i2] = Math.max(0.0f, Math.min(1.0f, linearIndeterminateDisjointAnimatorDelegate2.interpolatorArray[i2].getInterpolation((i - LinearIndeterminateDisjointAnimatorDelegate.DELAY_TO_MOVE_SEGMENT_ENDS[i2]) / LinearIndeterminateDisjointAnimatorDelegate.DURATION_TO_MOVE_SEGMENT_ENDS[i2])));
            }
            if (linearIndeterminateDisjointAnimatorDelegate2.dirtyColors) {
                Arrays.fill(linearIndeterminateDisjointAnimatorDelegate2.segmentColors, R$style.compositeARGBWithAlpha(linearIndeterminateDisjointAnimatorDelegate2.baseSpec.indicatorColors[linearIndeterminateDisjointAnimatorDelegate2.indicatorColorIndex], linearIndeterminateDisjointAnimatorDelegate2.drawable.totalAlpha));
                linearIndeterminateDisjointAnimatorDelegate2.dirtyColors = false;
            }
            linearIndeterminateDisjointAnimatorDelegate2.drawable.invalidateSelf();
        }
    };
    public int indicatorColorIndex = 0;
    public Animatable2Compat$AnimationCallback animatorCompleteCallback = null;

    public LinearIndeterminateDisjointAnimatorDelegate(Context context, LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(2);
        this.baseSpec = linearProgressIndicatorSpec;
        this.interpolatorArray = new Interpolator[]{AnimationUtils.loadInterpolator(context, R.animator.linear_indeterminate_line1_head_interpolator), AnimationUtils.loadInterpolator(context, R.animator.linear_indeterminate_line1_tail_interpolator), AnimationUtils.loadInterpolator(context, R.animator.linear_indeterminate_line2_head_interpolator), AnimationUtils.loadInterpolator(context, R.animator.linear_indeterminate_line2_tail_interpolator)};
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void cancelAnimatorImmediately() {
        ObjectAnimator objectAnimator = this.animator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void invalidateSpecValues() {
        resetPropertiesForNewStart();
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void registerAnimatorsCompleteCallback(Animatable2Compat$AnimationCallback animatable2Compat$AnimationCallback) {
        this.animatorCompleteCallback = animatable2Compat$AnimationCallback;
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void requestCancelAnimatorAfterCurrentCycle() {
        if (this.drawable.isVisible()) {
            this.animatorCompleteEndRequested = true;
            this.animator.setRepeatCount(0);
            return;
        }
        ObjectAnimator objectAnimator = this.animator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    public void resetPropertiesForNewStart() {
        this.indicatorColorIndex = 0;
        int compositeARGBWithAlpha = R$style.compositeARGBWithAlpha(this.baseSpec.indicatorColors[0], this.drawable.totalAlpha);
        int[] iArr = this.segmentColors;
        iArr[0] = compositeARGBWithAlpha;
        iArr[1] = compositeARGBWithAlpha;
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void startAnimator() {
        if (this.animator == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, ANIMATION_FRACTION, 0.0f, 1.0f);
            this.animator = ofFloat;
            ofFloat.setDuration(1800L);
            this.animator.setInterpolator(null);
            this.animator.setRepeatCount(-1);
            this.animator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.progressindicator.LinearIndeterminateDisjointAnimatorDelegate.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    LinearIndeterminateDisjointAnimatorDelegate linearIndeterminateDisjointAnimatorDelegate = LinearIndeterminateDisjointAnimatorDelegate.this;
                    if (linearIndeterminateDisjointAnimatorDelegate.animatorCompleteEndRequested) {
                        linearIndeterminateDisjointAnimatorDelegate.animator.setRepeatCount(-1);
                        LinearIndeterminateDisjointAnimatorDelegate linearIndeterminateDisjointAnimatorDelegate2 = LinearIndeterminateDisjointAnimatorDelegate.this;
                        linearIndeterminateDisjointAnimatorDelegate2.animatorCompleteCallback.onAnimationEnd(linearIndeterminateDisjointAnimatorDelegate2.drawable);
                        LinearIndeterminateDisjointAnimatorDelegate.this.animatorCompleteEndRequested = false;
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                    super.onAnimationRepeat(animator);
                    LinearIndeterminateDisjointAnimatorDelegate linearIndeterminateDisjointAnimatorDelegate = LinearIndeterminateDisjointAnimatorDelegate.this;
                    linearIndeterminateDisjointAnimatorDelegate.indicatorColorIndex = (linearIndeterminateDisjointAnimatorDelegate.indicatorColorIndex + 1) % linearIndeterminateDisjointAnimatorDelegate.baseSpec.indicatorColors.length;
                    linearIndeterminateDisjointAnimatorDelegate.dirtyColors = true;
                }
            });
        }
        resetPropertiesForNewStart();
        this.animator.start();
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void unregisterAnimatorsCompleteCallback() {
        this.animatorCompleteCallback = null;
    }
}
