package com.facebook.drawee.debug;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.debug.listener.ImageLoadingTimeListener;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import java.util.HashMap;
/* loaded from: classes.dex */
public class DebugControllerOverlayDrawable extends Drawable implements ImageLoadingTimeListener {
    public String mControllerId;
    public int mCurrentTextXPx;
    public int mCurrentTextYPx;
    public long mFinalImageTimeMs;
    public int mFrameCount;
    public int mHeightPx;
    public int mImageSizeBytes;
    public int mLineIncrementPx;
    public int mLoopCount;
    public String mOrigin;
    public ScalingUtils$ScaleType mScaleType;
    public int mStartTextXPx;
    public int mStartTextYPx;
    public int mWidthPx;
    public HashMap<String, String> mAdditionalData = new HashMap<>();
    public int mTextGravity = 80;
    public final Paint mPaint = new Paint(1);
    public final Matrix mMatrix = new Matrix();
    public final Rect mRect = new Rect();
    public final RectF mRectF = new RectF();

    public DebugControllerOverlayDrawable() {
        reset();
    }

    public final void addDebugText(Canvas canvas, String str, Object... objArr) {
        canvas.drawText(String.format(str, objArr), this.mCurrentTextXPx, this.mCurrentTextYPx, this.mPaint);
        this.mCurrentTextYPx += this.mLineIncrementPx;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x01cd A[LOOP:0: B:35:0x01c7->B:37:0x01cd, LOOP_END] */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void draw(android.graphics.Canvas r21) {
        /*
            Method dump skipped, instructions count: 488
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.drawee.debug.DebugControllerOverlayDrawable.draw(android.graphics.Canvas):void");
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        int min = Math.min(40, Math.max(10, Math.min(rect.width() / 8, rect.height() / 9)));
        this.mPaint.setTextSize(min);
        int i = min + 8;
        this.mLineIncrementPx = i;
        int i2 = this.mTextGravity;
        if (i2 == 80) {
            this.mLineIncrementPx = i * (-1);
        }
        this.mStartTextXPx = rect.left + 10;
        this.mStartTextYPx = i2 == 80 ? rect.bottom - 10 : rect.top + 10 + 10;
    }

    public void reset() {
        this.mWidthPx = -1;
        this.mHeightPx = -1;
        this.mImageSizeBytes = -1;
        this.mAdditionalData = new HashMap<>();
        this.mFrameCount = -1;
        this.mLoopCount = -1;
        this.mControllerId = "none";
        invalidateSelf();
        this.mFinalImageTimeMs = -1L;
        this.mOrigin = null;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }
}
