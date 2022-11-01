package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
/* loaded from: classes.dex */
public class MatrixDrawable extends ForwardingDrawable {
    public int mUnderlyingHeight;
    public int mUnderlyingWidth;

    public final void configureBounds() {
        Drawable drawable = this.mCurrentDelegate;
        Rect bounds = getBounds();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        this.mUnderlyingWidth = intrinsicWidth;
        int intrinsicHeight = drawable.getIntrinsicHeight();
        this.mUnderlyingHeight = intrinsicHeight;
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            drawable.setBounds(bounds);
        } else {
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        }
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (!(this.mUnderlyingWidth == this.mCurrentDelegate.getIntrinsicWidth() && this.mUnderlyingHeight == this.mCurrentDelegate.getIntrinsicHeight())) {
            configureBounds();
        }
        Drawable drawable = this.mCurrentDelegate;
        if (drawable != null) {
            drawable.draw(canvas);
        }
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, com.facebook.drawee.drawable.TransformCallback
    public void getTransform(Matrix matrix) {
        super.getTransform(matrix);
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.mCurrentDelegate;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        configureBounds();
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable
    public Drawable setCurrent(Drawable drawable) {
        Drawable current = super.setCurrent(drawable);
        configureBounds();
        return current;
    }
}
