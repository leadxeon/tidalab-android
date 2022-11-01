package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
/* loaded from: classes.dex */
public class OpacityAnimation extends Animation {
    public final float mDeltaOpacity;
    public final float mStartOpacity;
    public final View mView;

    /* loaded from: classes.dex */
    public static class OpacityAnimationListener implements Animation.AnimationListener {
        public boolean mLayerTypeChanged = false;
        public final View mView;

        public OpacityAnimationListener(View view) {
            this.mView = view;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            if (this.mLayerTypeChanged) {
                this.mView.setLayerType(0, null);
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            if (this.mView.hasOverlappingRendering() && this.mView.getLayerType() == 0) {
                this.mLayerTypeChanged = true;
                this.mView.setLayerType(2, null);
            }
        }
    }

    public OpacityAnimation(View view, float f, float f2) {
        this.mView = view;
        this.mStartOpacity = f;
        this.mDeltaOpacity = f2 - f;
        setAnimationListener(new OpacityAnimationListener(view));
    }

    @Override // android.view.animation.Animation
    public void applyTransformation(float f, Transformation transformation) {
        this.mView.setAlpha((this.mDeltaOpacity * f) + this.mStartOpacity);
    }

    @Override // android.view.animation.Animation
    public boolean willChangeBounds() {
        return false;
    }
}
