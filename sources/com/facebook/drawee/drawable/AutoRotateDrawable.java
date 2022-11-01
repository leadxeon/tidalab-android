package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
/* loaded from: classes.dex */
public class AutoRotateDrawable extends ForwardingDrawable implements Runnable {
    public int mInterval;
    public float mRotationAngle = 0.0f;
    public boolean mIsScheduled = false;
    public boolean mClockwise = true;

    public AutoRotateDrawable(Drawable drawable, int i) {
        super(drawable);
        this.mInterval = i;
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int save = canvas.save();
        Rect bounds = getBounds();
        int i = bounds.right;
        int i2 = bounds.left;
        int i3 = i - i2;
        int i4 = bounds.bottom;
        int i5 = bounds.top;
        int i6 = i4 - i5;
        float f = this.mRotationAngle;
        if (!this.mClockwise) {
            f = 360.0f - f;
        }
        canvas.rotate(f, (i3 / 2) + i2, (i6 / 2) + i5);
        Drawable drawable = this.mCurrentDelegate;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        canvas.restoreToCount(save);
        if (!this.mIsScheduled) {
            this.mIsScheduled = true;
            scheduleSelf(this, SystemClock.uptimeMillis() + 20);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        this.mIsScheduled = false;
        this.mRotationAngle += (int) ((20.0f / this.mInterval) * 360.0f);
        invalidateSelf();
    }
}
