package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.util.Property;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback;
import com.google.android.material.R$style;
import java.util.Arrays;
/* loaded from: classes.dex */
public final class LinearIndeterminateContiguousAnimatorDelegate extends IndeterminateAnimatorDelegate<ObjectAnimator> {
    public static final Property<LinearIndeterminateContiguousAnimatorDelegate, Float> ANIMATION_FRACTION = new Property<LinearIndeterminateContiguousAnimatorDelegate, Float>(Float.class, "animationFraction") { // from class: com.google.android.material.progressindicator.LinearIndeterminateContiguousAnimatorDelegate.2
        @Override // android.util.Property
        public Float get(LinearIndeterminateContiguousAnimatorDelegate linearIndeterminateContiguousAnimatorDelegate) {
            return Float.valueOf(linearIndeterminateContiguousAnimatorDelegate.animationFraction);
        }

        @Override // android.util.Property
        public void set(LinearIndeterminateContiguousAnimatorDelegate linearIndeterminateContiguousAnimatorDelegate, Float f) {
            LinearIndeterminateContiguousAnimatorDelegate linearIndeterminateContiguousAnimatorDelegate2 = linearIndeterminateContiguousAnimatorDelegate;
            float floatValue = f.floatValue();
            linearIndeterminateContiguousAnimatorDelegate2.animationFraction = floatValue;
            float[] fArr = linearIndeterminateContiguousAnimatorDelegate2.segmentPositions;
            fArr[0] = 0.0f;
            float f2 = (((int) (floatValue * 333.0f)) - 0) / 667;
            float interpolation = linearIndeterminateContiguousAnimatorDelegate2.interpolator.getInterpolation(f2);
            fArr[2] = interpolation;
            fArr[1] = interpolation;
            float[] fArr2 = linearIndeterminateContiguousAnimatorDelegate2.segmentPositions;
            float interpolation2 = linearIndeterminateContiguousAnimatorDelegate2.interpolator.getInterpolation(f2 + 0.49925038f);
            fArr2[4] = interpolation2;
            fArr2[3] = interpolation2;
            float[] fArr3 = linearIndeterminateContiguousAnimatorDelegate2.segmentPositions;
            fArr3[5] = 1.0f;
            if (linearIndeterminateContiguousAnimatorDelegate2.dirtyColors && fArr3[3] < 1.0f) {
                int[] iArr = linearIndeterminateContiguousAnimatorDelegate2.segmentColors;
                iArr[2] = iArr[1];
                iArr[1] = iArr[0];
                iArr[0] = R$style.compositeARGBWithAlpha(linearIndeterminateContiguousAnimatorDelegate2.baseSpec.indicatorColors[linearIndeterminateContiguousAnimatorDelegate2.newIndicatorColorIndex], linearIndeterminateContiguousAnimatorDelegate2.drawable.totalAlpha);
                linearIndeterminateContiguousAnimatorDelegate2.dirtyColors = false;
            }
            linearIndeterminateContiguousAnimatorDelegate2.drawable.invalidateSelf();
        }
    };
    public float animationFraction;
    public ObjectAnimator animator;
    public final BaseProgressIndicatorSpec baseSpec;
    public boolean dirtyColors;
    public int newIndicatorColorIndex = 1;
    public FastOutSlowInInterpolator interpolator = new FastOutSlowInInterpolator();

    public LinearIndeterminateContiguousAnimatorDelegate(LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(3);
        this.baseSpec = linearProgressIndicatorSpec;
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
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void requestCancelAnimatorAfterCurrentCycle() {
    }

    public void resetPropertiesForNewStart() {
        this.dirtyColors = true;
        this.newIndicatorColorIndex = 1;
        Arrays.fill(this.segmentColors, R$style.compositeARGBWithAlpha(this.baseSpec.indicatorColors[0], this.drawable.totalAlpha));
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void startAnimator() {
        if (this.animator == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, ANIMATION_FRACTION, 0.0f, 1.0f);
            this.animator = ofFloat;
            ofFloat.setDuration(333L);
            this.animator.setInterpolator(null);
            this.animator.setRepeatCount(-1);
            this.animator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.progressindicator.LinearIndeterminateContiguousAnimatorDelegate.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                    super.onAnimationRepeat(animator);
                    LinearIndeterminateContiguousAnimatorDelegate linearIndeterminateContiguousAnimatorDelegate = LinearIndeterminateContiguousAnimatorDelegate.this;
                    linearIndeterminateContiguousAnimatorDelegate.newIndicatorColorIndex = (linearIndeterminateContiguousAnimatorDelegate.newIndicatorColorIndex + 1) % linearIndeterminateContiguousAnimatorDelegate.baseSpec.indicatorColors.length;
                    linearIndeterminateContiguousAnimatorDelegate.dirtyColors = true;
                }
            });
        }
        resetPropertiesForNewStart();
        this.animator.start();
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void unregisterAnimatorsCompleteCallback() {
    }
}
