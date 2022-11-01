package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;
/* loaded from: classes.dex */
public abstract class DrawingDelegate<S extends BaseProgressIndicatorSpec> {
    public DrawableWithAnimatedVisibilityChange drawable;
    public S spec;

    public DrawingDelegate(S s) {
        this.spec = s;
    }

    public abstract void fillIndicator(Canvas canvas, Paint paint, float f, float f2, int i);

    public abstract void fillTrack(Canvas canvas, Paint paint);

    public abstract int getPreferredHeight();

    public void validateSpecAndAdjustCanvas(Canvas canvas, float f) {
        this.spec.validateSpec();
        LinearDrawingDelegate linearDrawingDelegate = (LinearDrawingDelegate) this;
        Rect clipBounds = canvas.getClipBounds();
        linearDrawingDelegate.trackLength = clipBounds.width();
        float f2 = ((LinearProgressIndicatorSpec) linearDrawingDelegate.spec).trackThickness;
        canvas.translate(clipBounds.width() / 2.0f, Math.max(0.0f, (clipBounds.height() - ((LinearProgressIndicatorSpec) linearDrawingDelegate.spec).trackThickness) / 2.0f) + (clipBounds.height() / 2.0f));
        if (((LinearProgressIndicatorSpec) linearDrawingDelegate.spec).drawHorizontallyInverse) {
            canvas.scale(-1.0f, 1.0f);
        }
        if ((linearDrawingDelegate.drawable.isShowing() && ((LinearProgressIndicatorSpec) linearDrawingDelegate.spec).showAnimationBehavior == 1) || (linearDrawingDelegate.drawable.isHiding() && ((LinearProgressIndicatorSpec) linearDrawingDelegate.spec).hideAnimationBehavior == 2)) {
            canvas.scale(1.0f, -1.0f);
        }
        if (linearDrawingDelegate.drawable.isShowing() || linearDrawingDelegate.drawable.isHiding()) {
            canvas.translate(0.0f, ((f - 1.0f) * ((LinearProgressIndicatorSpec) linearDrawingDelegate.spec).trackThickness) / 2.0f);
        }
        float f3 = linearDrawingDelegate.trackLength;
        canvas.clipRect((-f3) / 2.0f, (-f2) / 2.0f, f3 / 2.0f, f2 / 2.0f);
        LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) linearDrawingDelegate.spec;
        linearDrawingDelegate.displayedTrackThickness = linearProgressIndicatorSpec.trackThickness * f;
        linearDrawingDelegate.displayedCornerRadius = linearProgressIndicatorSpec.trackCornerRadius * f;
    }
}
