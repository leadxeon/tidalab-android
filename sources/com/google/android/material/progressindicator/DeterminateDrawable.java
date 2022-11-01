package com.google.android.material.progressindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Looper;
import android.util.AndroidRuntimeException;
import androidx.dynamicanimation.animation.AnimationHandler;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.google.android.material.R$style;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;
import java.util.Objects;
/* loaded from: classes.dex */
public final class DeterminateDrawable<S extends BaseProgressIndicatorSpec> extends DrawableWithAnimatedVisibilityChange {
    public static final FloatPropertyCompat<DeterminateDrawable> INDICATOR_LENGTH_IN_LEVEL = new FloatPropertyCompat<DeterminateDrawable>("indicatorLevel") { // from class: com.google.android.material.progressindicator.DeterminateDrawable.1
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(DeterminateDrawable determinateDrawable) {
            return determinateDrawable.indicatorFraction * 10000.0f;
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(DeterminateDrawable determinateDrawable, float f) {
            DeterminateDrawable determinateDrawable2 = determinateDrawable;
            determinateDrawable2.indicatorFraction = f / 10000.0f;
            determinateDrawable2.invalidateSelf();
        }
    };
    public DrawingDelegate<S> drawingDelegate;
    public float indicatorFraction;
    public boolean skipAnimationOnLevelChange = false;
    public final SpringAnimation springAnimator;
    public final SpringForce springForce;

    public DeterminateDrawable(Context context, BaseProgressIndicatorSpec baseProgressIndicatorSpec, DrawingDelegate<S> drawingDelegate) {
        super(context, baseProgressIndicatorSpec);
        this.drawingDelegate = drawingDelegate;
        drawingDelegate.drawable = this;
        SpringForce springForce = new SpringForce();
        this.springForce = springForce;
        springForce.mDampingRatio = 1.0f;
        springForce.mInitialized = false;
        springForce.setStiffness(50.0f);
        SpringAnimation springAnimation = new SpringAnimation(this, INDICATOR_LENGTH_IN_LEVEL);
        this.springAnimator = springAnimation;
        springAnimation.mSpring = springForce;
        if (this.growFraction != 1.0f) {
            this.growFraction = 1.0f;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect rect = new Rect();
        if (!getBounds().isEmpty() && isVisible() && canvas.getClipBounds(rect)) {
            canvas.save();
            this.drawingDelegate.validateSpecAndAdjustCanvas(canvas, getGrowFraction());
            this.drawingDelegate.fillTrack(canvas, this.paint);
            this.drawingDelegate.fillIndicator(canvas, this.paint, 0.0f, this.indicatorFraction, R$style.compositeARGBWithAlpha(this.baseSpec.indicatorColors[0], this.totalAlpha));
            canvas.restore();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.drawingDelegate.getPreferredHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Objects.requireNonNull(this.drawingDelegate);
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.springAnimator.cancel();
        this.indicatorFraction = getLevel() / 10000.0f;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i) {
        if (this.skipAnimationOnLevelChange) {
            this.springAnimator.cancel();
            this.indicatorFraction = i / 10000.0f;
            invalidateSelf();
        } else {
            SpringAnimation springAnimation = this.springAnimator;
            springAnimation.mValue = this.indicatorFraction * 10000.0f;
            springAnimation.mStartValueIsSet = true;
            float f = i;
            if (springAnimation.mRunning) {
                springAnimation.mPendingPosition = f;
            } else {
                if (springAnimation.mSpring == null) {
                    springAnimation.mSpring = new SpringForce(f);
                }
                SpringForce springForce = springAnimation.mSpring;
                double d = f;
                springForce.mFinalPosition = d;
                double d2 = (float) d;
                if (d2 > Float.MAX_VALUE) {
                    throw new UnsupportedOperationException("Final position of the spring cannot be greater than the max value.");
                } else if (d2 >= springAnimation.mMinValue) {
                    double abs = Math.abs(springAnimation.mMinVisibleChange * 0.75f);
                    springForce.mValueThreshold = abs;
                    springForce.mVelocityThreshold = abs * 62.5d;
                    if (Looper.myLooper() == Looper.getMainLooper()) {
                        boolean z = springAnimation.mRunning;
                        if (!z && !z) {
                            springAnimation.mRunning = true;
                            if (!springAnimation.mStartValueIsSet) {
                                springAnimation.mValue = springAnimation.mProperty.getValue(springAnimation.mTarget);
                            }
                            float f2 = springAnimation.mValue;
                            if (f2 > Float.MAX_VALUE || f2 < springAnimation.mMinValue) {
                                throw new IllegalArgumentException("Starting value need to be in between min value and max value");
                            }
                            AnimationHandler instance = AnimationHandler.getInstance();
                            if (instance.mAnimationCallbacks.size() == 0) {
                                if (instance.mProvider == null) {
                                    instance.mProvider = new AnimationHandler.FrameCallbackProvider16(instance.mCallbackDispatcher);
                                }
                                AnimationHandler.FrameCallbackProvider16 frameCallbackProvider16 = (AnimationHandler.FrameCallbackProvider16) instance.mProvider;
                                frameCallbackProvider16.mChoreographer.postFrameCallback(frameCallbackProvider16.mChoreographerCallback);
                            }
                            if (!instance.mAnimationCallbacks.contains(springAnimation)) {
                                instance.mAnimationCallbacks.add(springAnimation);
                            }
                        }
                    } else {
                        throw new AndroidRuntimeException("Animations may only be started on the main thread");
                    }
                } else {
                    throw new UnsupportedOperationException("Final position of the spring cannot be less than the min value.");
                }
            }
        }
        return true;
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public boolean setVisibleInternal(boolean z, boolean z2, boolean z3) {
        boolean visibleInternal = super.setVisibleInternal(z, z2, z3);
        float systemAnimatorDurationScale = this.animatorDurationScaleProvider.getSystemAnimatorDurationScale(this.context.getContentResolver());
        if (systemAnimatorDurationScale == 0.0f) {
            this.skipAnimationOnLevelChange = true;
        } else {
            this.skipAnimationOnLevelChange = false;
            this.springForce.setStiffness(50.0f / systemAnimatorDurationScale);
        }
        return visibleInternal;
    }
}
