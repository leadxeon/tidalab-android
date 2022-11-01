package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.google.android.material.R$style;
/* loaded from: classes.dex */
public final class LinearDrawingDelegate extends DrawingDelegate<LinearProgressIndicatorSpec> {
    public float displayedCornerRadius;
    public float displayedTrackThickness;
    public float trackLength = 300.0f;

    public LinearDrawingDelegate(LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(linearProgressIndicatorSpec);
    }

    public static void drawRoundedEnd(Canvas canvas, Paint paint, float f, float f2, float f3, boolean z, RectF rectF) {
        canvas.save();
        canvas.translate(f3, 0.0f);
        if (!z) {
            canvas.rotate(180.0f);
        }
        float f4 = ((-f) / 2.0f) + f2;
        float f5 = (f / 2.0f) - f2;
        canvas.drawRect(-f2, f4, 0.0f, f5, paint);
        canvas.save();
        canvas.translate(0.0f, f4);
        canvas.drawArc(rectF, 180.0f, 90.0f, true, paint);
        canvas.restore();
        canvas.translate(0.0f, f5);
        canvas.drawArc(rectF, 180.0f, -90.0f, true, paint);
        canvas.restore();
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void fillIndicator(Canvas canvas, Paint paint, float f, float f2, int i) {
        if (f != f2) {
            float f3 = this.trackLength;
            float f4 = this.displayedCornerRadius;
            float f5 = ((-f3) / 2.0f) + f4;
            float f6 = f3 - (f4 * 2.0f);
            float f7 = (f * f6) + f5;
            float f8 = (f6 * f2) + f5;
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            paint.setColor(i);
            float f9 = this.displayedTrackThickness;
            canvas.drawRect(f7, (-f9) / 2.0f, f8, f9 / 2.0f, paint);
            float f10 = this.displayedCornerRadius;
            float f11 = -f10;
            RectF rectF = new RectF(f11, f11, f10, f10);
            drawRoundedEnd(canvas, paint, this.displayedTrackThickness, this.displayedCornerRadius, f7, true, rectF);
            drawRoundedEnd(canvas, paint, this.displayedTrackThickness, this.displayedCornerRadius, f8, false, rectF);
        }
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void fillTrack(Canvas canvas, Paint paint) {
        int compositeARGBWithAlpha = R$style.compositeARGBWithAlpha(((LinearProgressIndicatorSpec) this.spec).trackColor, this.drawable.totalAlpha);
        float f = ((-this.trackLength) / 2.0f) + this.displayedCornerRadius;
        float f2 = -f;
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(compositeARGBWithAlpha);
        float f3 = this.displayedTrackThickness;
        canvas.drawRect(f, (-f3) / 2.0f, f2, f3 / 2.0f, paint);
        float f4 = this.displayedCornerRadius;
        float f5 = -f4;
        RectF rectF = new RectF(f5, f5, f4, f4);
        drawRoundedEnd(canvas, paint, this.displayedTrackThickness, this.displayedCornerRadius, f, true, rectF);
        drawRoundedEnd(canvas, paint, this.displayedTrackThickness, this.displayedCornerRadius, f2, false, rectF);
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public int getPreferredHeight() {
        return ((LinearProgressIndicatorSpec) this.spec).trackThickness;
    }
}
