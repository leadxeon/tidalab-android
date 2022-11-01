package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.Property;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback;
import com.google.android.material.animation.AnimationUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public abstract class DrawableWithAnimatedVisibilityChange extends Drawable implements Animatable {
    public static final Property<DrawableWithAnimatedVisibilityChange, Float> GROW_FRACTION = new Property<DrawableWithAnimatedVisibilityChange, Float>(Float.class, "growFraction") { // from class: com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange.3
        @Override // android.util.Property
        public Float get(DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange) {
            return Float.valueOf(drawableWithAnimatedVisibilityChange.getGrowFraction());
        }

        @Override // android.util.Property
        public void set(DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange, Float f) {
            DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange2 = drawableWithAnimatedVisibilityChange;
            float floatValue = f.floatValue();
            if (drawableWithAnimatedVisibilityChange2.growFraction != floatValue) {
                drawableWithAnimatedVisibilityChange2.growFraction = floatValue;
                drawableWithAnimatedVisibilityChange2.invalidateSelf();
            }
        }
    };
    public List<Animatable2Compat$AnimationCallback> animationCallbacks;
    public final BaseProgressIndicatorSpec baseSpec;
    public final Context context;
    public float growFraction;
    public ValueAnimator hideAnimator;
    public boolean ignoreCallbacks;
    public ValueAnimator showAnimator;
    public final Paint paint = new Paint();
    public AnimatorDurationScaleProvider animatorDurationScaleProvider = new AnimatorDurationScaleProvider();
    public int totalAlpha = 255;

    public DrawableWithAnimatedVisibilityChange(Context context, BaseProgressIndicatorSpec baseProgressIndicatorSpec) {
        this.context = context;
        this.baseSpec = baseProgressIndicatorSpec;
        invalidateSelf();
    }

    public final void endAnimatorWithoutCallbacks(ValueAnimator... valueAnimatorArr) {
        boolean z = this.ignoreCallbacks;
        this.ignoreCallbacks = true;
        for (ValueAnimator valueAnimator : valueAnimatorArr) {
            valueAnimator.end();
        }
        this.ignoreCallbacks = z;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.totalAlpha;
    }

    public float getGrowFraction() {
        BaseProgressIndicatorSpec baseProgressIndicatorSpec = this.baseSpec;
        boolean z = false;
        if (!(baseProgressIndicatorSpec.showAnimationBehavior != 0)) {
            if (baseProgressIndicatorSpec.hideAnimationBehavior != 0) {
                z = true;
            }
            if (!z) {
                return 1.0f;
            }
        }
        return this.growFraction;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public boolean hideNow() {
        return setVisible(false, false, false);
    }

    public boolean isHiding() {
        ValueAnimator valueAnimator = this.hideAnimator;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return isShowing() || isHiding();
    }

    public boolean isShowing() {
        ValueAnimator valueAnimator = this.showAnimator;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    public void registerAnimationCallback(Animatable2Compat$AnimationCallback animatable2Compat$AnimationCallback) {
        if (this.animationCallbacks == null) {
            this.animationCallbacks = new ArrayList();
        }
        if (!this.animationCallbacks.contains(animatable2Compat$AnimationCallback)) {
            this.animationCallbacks.add(animatable2Compat$AnimationCallback);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.totalAlpha = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        return setVisible(z, z2, true);
    }

    public boolean setVisibleInternal(boolean z, boolean z2, boolean z3) {
        if (this.showAnimator == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, GROW_FRACTION, 0.0f, 1.0f);
            this.showAnimator = ofFloat;
            ofFloat.setDuration(500L);
            this.showAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            ValueAnimator valueAnimator = this.showAnimator;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                this.showAnimator = valueAnimator;
                valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange.1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationStart(Animator animator) {
                        super.onAnimationStart(animator);
                        DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange = DrawableWithAnimatedVisibilityChange.this;
                        List<Animatable2Compat$AnimationCallback> list = drawableWithAnimatedVisibilityChange.animationCallbacks;
                        if (!(list == null || drawableWithAnimatedVisibilityChange.ignoreCallbacks)) {
                            for (Animatable2Compat$AnimationCallback animatable2Compat$AnimationCallback : list) {
                                Objects.requireNonNull(animatable2Compat$AnimationCallback);
                            }
                        }
                    }
                });
            } else {
                throw new IllegalArgumentException("Cannot set showAnimator while the current showAnimator is running.");
            }
        }
        if (this.hideAnimator == null) {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, GROW_FRACTION, 1.0f, 0.0f);
            this.hideAnimator = ofFloat2;
            ofFloat2.setDuration(500L);
            this.hideAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            ValueAnimator valueAnimator2 = this.hideAnimator;
            if (valueAnimator2 == null || !valueAnimator2.isRunning()) {
                this.hideAnimator = valueAnimator2;
                valueAnimator2.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange.2
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        DrawableWithAnimatedVisibilityChange.super.setVisible(false, false);
                        DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange = DrawableWithAnimatedVisibilityChange.this;
                        List<Animatable2Compat$AnimationCallback> list = drawableWithAnimatedVisibilityChange.animationCallbacks;
                        if (!(list == null || drawableWithAnimatedVisibilityChange.ignoreCallbacks)) {
                            for (Animatable2Compat$AnimationCallback animatable2Compat$AnimationCallback : list) {
                                animatable2Compat$AnimationCallback.onAnimationEnd(drawableWithAnimatedVisibilityChange);
                            }
                        }
                    }
                });
            } else {
                throw new IllegalArgumentException("Cannot set hideAnimator while the current hideAnimator is running.");
            }
        }
        if (!isVisible() && !z) {
            return false;
        }
        ValueAnimator valueAnimator3 = z ? this.showAnimator : this.hideAnimator;
        if (!z3) {
            if (valueAnimator3.isRunning()) {
                valueAnimator3.end();
            } else {
                endAnimatorWithoutCallbacks(valueAnimator3);
            }
            return super.setVisible(z, false);
        } else if (z3 && valueAnimator3.isRunning()) {
            return false;
        } else {
            boolean z4 = !z || super.setVisible(z, false);
            if (!(!z ? this.baseSpec.hideAnimationBehavior != 0 : this.baseSpec.showAnimationBehavior != 0)) {
                endAnimatorWithoutCallbacks(valueAnimator3);
                return z4;
            }
            if (z2 || !valueAnimator3.isPaused()) {
                valueAnimator3.start();
            } else {
                valueAnimator3.resume();
            }
            return z4;
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        setVisibleInternal(true, true, false);
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        setVisibleInternal(false, true, false);
    }

    public boolean unregisterAnimationCallback(Animatable2Compat$AnimationCallback animatable2Compat$AnimationCallback) {
        List<Animatable2Compat$AnimationCallback> list = this.animationCallbacks;
        if (list == null || !list.contains(animatable2Compat$AnimationCallback)) {
            return false;
        }
        this.animationCallbacks.remove(animatable2Compat$AnimationCallback);
        if (!this.animationCallbacks.isEmpty()) {
            return true;
        }
        this.animationCallbacks = null;
        return true;
    }

    public boolean setVisible(boolean z, boolean z2, boolean z3) {
        return setVisibleInternal(z, z2, z3 && this.animatorDurationScaleProvider.getSystemAnimatorDurationScale(this.context.getContentResolver()) > 0.0f);
    }
}
