package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.recyclerview.R$dimen;
import java.util.Arrays;
/* loaded from: classes.dex */
public class RoundedColorDrawable extends Drawable implements Rounded {
    public int mColor;
    public float[] mInsideBorderRadii;
    public final float[] mRadii = new float[8];
    public final float[] mBorderRadii = new float[8];
    public final Paint mPaint = new Paint(1);
    public boolean mIsCircle = false;
    public float mBorderWidth = 0.0f;
    public float mPadding = 0.0f;
    public int mBorderColor = 0;
    public boolean mScaleDownInsideBorders = false;
    public boolean mPaintFilterBitmap = false;
    public final Path mPath = new Path();
    public final Path mBorderPath = new Path();
    public final RectF mTempRect = new RectF();
    public int mAlpha = 255;

    public RoundedColorDrawable(int i) {
        this.mColor = 0;
        if (this.mColor != i) {
            this.mColor = i;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.mPaint.setColor(R$dimen.multiplyColorAlpha(this.mColor, this.mAlpha));
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setFilterBitmap(this.mPaintFilterBitmap);
        canvas.drawPath(this.mPath, this.mPaint);
        if (this.mBorderWidth != 0.0f) {
            this.mPaint.setColor(R$dimen.multiplyColorAlpha(this.mBorderColor, this.mAlpha));
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setStrokeWidth(this.mBorderWidth);
            canvas.drawPath(this.mBorderPath, this.mPaint);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        int multiplyColorAlpha = R$dimen.multiplyColorAlpha(this.mColor, this.mAlpha) >>> 24;
        if (multiplyColorAlpha == 255) {
            return -1;
        }
        return multiplyColorAlpha == 0 ? -2 : -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        updatePath();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (i != this.mAlpha) {
            this.mAlpha = i;
            invalidateSelf();
        }
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setBorder(int i, float f) {
        if (this.mBorderColor != i) {
            this.mBorderColor = i;
            invalidateSelf();
        }
        if (this.mBorderWidth != f) {
            this.mBorderWidth = f;
            updatePath();
            invalidateSelf();
        }
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setCircle(boolean z) {
        this.mIsCircle = z;
        updatePath();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setPadding(float f) {
        if (this.mPadding != f) {
            this.mPadding = f;
            updatePath();
            invalidateSelf();
        }
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setPaintFilterBitmap(boolean z) {
        if (this.mPaintFilterBitmap != z) {
            this.mPaintFilterBitmap = z;
            invalidateSelf();
        }
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setRadii(float[] fArr) {
        if (fArr == null) {
            Arrays.fill(this.mRadii, 0.0f);
        } else {
            R$dimen.checkArgument(fArr.length == 8, "radii should have exactly 8 values");
            System.arraycopy(fArr, 0, this.mRadii, 0, 8);
        }
        updatePath();
        invalidateSelf();
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setRadius(float f) {
        R$dimen.checkArgument(f >= 0.0f, "radius should be non negative");
        Arrays.fill(this.mRadii, f);
        updatePath();
        invalidateSelf();
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setScaleDownInsideBorders(boolean z) {
        if (this.mScaleDownInsideBorders != z) {
            this.mScaleDownInsideBorders = z;
            updatePath();
            invalidateSelf();
        }
    }

    public final void updatePath() {
        float[] fArr;
        float[] fArr2;
        this.mPath.reset();
        this.mBorderPath.reset();
        this.mTempRect.set(getBounds());
        RectF rectF = this.mTempRect;
        float f = this.mBorderWidth;
        rectF.inset(f / 2.0f, f / 2.0f);
        int i = 0;
        if (this.mIsCircle) {
            this.mBorderPath.addCircle(this.mTempRect.centerX(), this.mTempRect.centerY(), Math.min(this.mTempRect.width(), this.mTempRect.height()) / 2.0f, Path.Direction.CW);
        } else {
            int i2 = 0;
            while (true) {
                fArr2 = this.mBorderRadii;
                if (i2 >= fArr2.length) {
                    break;
                }
                fArr2[i2] = (this.mRadii[i2] + this.mPadding) - (this.mBorderWidth / 2.0f);
                i2++;
            }
            this.mBorderPath.addRoundRect(this.mTempRect, fArr2, Path.Direction.CW);
        }
        RectF rectF2 = this.mTempRect;
        float f2 = this.mBorderWidth;
        rectF2.inset((-f2) / 2.0f, (-f2) / 2.0f);
        float f3 = this.mPadding + (this.mScaleDownInsideBorders ? this.mBorderWidth : 0.0f);
        this.mTempRect.inset(f3, f3);
        if (this.mIsCircle) {
            this.mPath.addCircle(this.mTempRect.centerX(), this.mTempRect.centerY(), Math.min(this.mTempRect.width(), this.mTempRect.height()) / 2.0f, Path.Direction.CW);
        } else if (this.mScaleDownInsideBorders) {
            if (this.mInsideBorderRadii == null) {
                this.mInsideBorderRadii = new float[8];
            }
            while (true) {
                fArr = this.mInsideBorderRadii;
                if (i >= fArr.length) {
                    break;
                }
                fArr[i] = this.mRadii[i] - this.mBorderWidth;
                i++;
            }
            this.mPath.addRoundRect(this.mTempRect, fArr, Path.Direction.CW);
        } else {
            this.mPath.addRoundRect(this.mTempRect, this.mRadii, Path.Direction.CW);
        }
        float f4 = -f3;
        this.mTempRect.inset(f4, f4);
    }
}
