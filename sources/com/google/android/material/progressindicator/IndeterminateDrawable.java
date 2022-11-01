package com.google.android.material.progressindicator;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;
import java.util.Objects;
/* loaded from: classes.dex */
public final class IndeterminateDrawable<S extends BaseProgressIndicatorSpec> extends DrawableWithAnimatedVisibilityChange {
    public IndeterminateAnimatorDelegate<ObjectAnimator> animatorDelegate;
    public DrawingDelegate<S> drawingDelegate;

    public IndeterminateDrawable(Context context, BaseProgressIndicatorSpec baseProgressIndicatorSpec, DrawingDelegate<S> drawingDelegate, IndeterminateAnimatorDelegate<ObjectAnimator> indeterminateAnimatorDelegate) {
        super(context, baseProgressIndicatorSpec);
        this.drawingDelegate = drawingDelegate;
        drawingDelegate.drawable = this;
        this.animatorDelegate = indeterminateAnimatorDelegate;
        indeterminateAnimatorDelegate.drawable = this;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect rect = new Rect();
        if (!getBounds().isEmpty() && isVisible() && canvas.getClipBounds(rect)) {
            canvas.save();
            this.drawingDelegate.validateSpecAndAdjustCanvas(canvas, getGrowFraction());
            this.drawingDelegate.fillTrack(canvas, this.paint);
            int i = 0;
            while (true) {
                IndeterminateAnimatorDelegate<ObjectAnimator> indeterminateAnimatorDelegate = this.animatorDelegate;
                int[] iArr = indeterminateAnimatorDelegate.segmentColors;
                if (i < iArr.length) {
                    DrawingDelegate<S> drawingDelegate = this.drawingDelegate;
                    Paint paint = this.paint;
                    float[] fArr = indeterminateAnimatorDelegate.segmentPositions;
                    int i2 = i * 2;
                    drawingDelegate.fillIndicator(canvas, paint, fArr[i2], fArr[i2 + 1], iArr[i]);
                    i++;
                } else {
                    canvas.restore();
                    return;
                }
            }
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

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public boolean setVisibleInternal(boolean z, boolean z2, boolean z3) {
        boolean visibleInternal = super.setVisibleInternal(z, z2, z3);
        if (!isRunning()) {
            this.animatorDelegate.cancelAnimatorImmediately();
        }
        float systemAnimatorDurationScale = this.animatorDurationScaleProvider.getSystemAnimatorDurationScale(this.context.getContentResolver());
        if (z && (z3 || (Build.VERSION.SDK_INT <= 21 && systemAnimatorDurationScale > 0.0f))) {
            this.animatorDelegate.startAnimator();
        }
        return visibleInternal;
    }
}
