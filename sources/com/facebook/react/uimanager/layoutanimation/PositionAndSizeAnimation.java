package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
/* loaded from: classes.dex */
public class PositionAndSizeAnimation extends Animation implements LayoutHandlingAnimation {
    public int mDeltaHeight;
    public int mDeltaWidth;
    public float mDeltaX;
    public float mDeltaY;
    public int mStartHeight;
    public int mStartWidth;
    public float mStartX;
    public float mStartY;
    public final View mView;

    public PositionAndSizeAnimation(View view, int i, int i2, int i3, int i4) {
        this.mView = view;
        calculateAnimation(i, i2, i3, i4);
    }

    @Override // android.view.animation.Animation
    public void applyTransformation(float f, Transformation transformation) {
        float f2 = (this.mDeltaX * f) + this.mStartX;
        float f3 = (this.mDeltaY * f) + this.mStartY;
        this.mView.layout(Math.round(f2), Math.round(f3), Math.round(f2 + (this.mDeltaWidth * f) + this.mStartWidth), Math.round(f3 + (this.mDeltaHeight * f) + this.mStartHeight));
    }

    public final void calculateAnimation(int i, int i2, int i3, int i4) {
        this.mStartX = this.mView.getX() - this.mView.getTranslationX();
        this.mStartY = this.mView.getY() - this.mView.getTranslationY();
        this.mStartWidth = this.mView.getWidth();
        int height = this.mView.getHeight();
        this.mStartHeight = height;
        this.mDeltaX = i - this.mStartX;
        this.mDeltaY = i2 - this.mStartY;
        this.mDeltaWidth = i3 - this.mStartWidth;
        this.mDeltaHeight = i4 - height;
    }

    @Override // com.facebook.react.uimanager.layoutanimation.LayoutHandlingAnimation
    public void onLayoutUpdate(int i, int i2, int i3, int i4) {
        calculateAnimation(i, i2, i3, i4);
    }

    @Override // android.view.animation.Animation
    public boolean willChangeBounds() {
        return true;
    }
}
