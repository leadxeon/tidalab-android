package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility;
import androidx.recyclerview.R$dimen;
import java.util.Arrays;
/* loaded from: classes.dex */
public class RoundedCornersDrawable extends ForwardingDrawable implements Rounded {
    public RectF mInsideBorderBounds;
    public Matrix mInsideBorderTransform;
    public int mType = 1;
    public final RectF mBounds = new RectF();
    public final float[] mRadii = new float[8];
    public final float[] mBorderRadii = new float[8];
    public final Paint mPaint = new Paint(1);
    public boolean mIsCircle = false;
    public float mBorderWidth = 0.0f;
    public int mBorderColor = 0;
    public int mOverlayColor = 0;
    public float mPadding = 0.0f;
    public boolean mScaleDownInsideBorders = false;
    public boolean mPaintFilterBitmap = false;
    public final Path mPath = new Path();
    public final Path mBorderPath = new Path();
    public final RectF mTempRectangle = new RectF();

    public RoundedCornersDrawable(Drawable drawable) {
        super(drawable);
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.mBounds.set(getBounds());
        int $enumboxing$ordinal = SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(this.mType);
        if ($enumboxing$ordinal == 0) {
            if (this.mScaleDownInsideBorders) {
                RectF rectF = this.mInsideBorderBounds;
                if (rectF == null) {
                    this.mInsideBorderBounds = new RectF(this.mBounds);
                    this.mInsideBorderTransform = new Matrix();
                } else {
                    rectF.set(this.mBounds);
                }
                RectF rectF2 = this.mInsideBorderBounds;
                float f = this.mBorderWidth;
                rectF2.inset(f, f);
                this.mInsideBorderTransform.setRectToRect(this.mBounds, this.mInsideBorderBounds, Matrix.ScaleToFit.FILL);
                int save = canvas.save();
                canvas.clipRect(this.mBounds);
                canvas.concat(this.mInsideBorderTransform);
                Drawable drawable = this.mCurrentDelegate;
                if (drawable != null) {
                    drawable.draw(canvas);
                }
                canvas.restoreToCount(save);
            } else {
                Drawable drawable2 = this.mCurrentDelegate;
                if (drawable2 != null) {
                    drawable2.draw(canvas);
                }
            }
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(this.mOverlayColor);
            this.mPaint.setStrokeWidth(0.0f);
            this.mPaint.setFilterBitmap(this.mPaintFilterBitmap);
            this.mPath.setFillType(Path.FillType.EVEN_ODD);
            canvas.drawPath(this.mPath, this.mPaint);
            if (this.mIsCircle) {
                float width = ((this.mBounds.width() - this.mBounds.height()) + this.mBorderWidth) / 2.0f;
                float height = ((this.mBounds.height() - this.mBounds.width()) + this.mBorderWidth) / 2.0f;
                if (width > 0.0f) {
                    RectF rectF3 = this.mBounds;
                    float f2 = rectF3.left;
                    canvas.drawRect(f2, rectF3.top, f2 + width, rectF3.bottom, this.mPaint);
                    RectF rectF4 = this.mBounds;
                    float f3 = rectF4.right;
                    canvas.drawRect(f3 - width, rectF4.top, f3, rectF4.bottom, this.mPaint);
                }
                if (height > 0.0f) {
                    RectF rectF5 = this.mBounds;
                    float f4 = rectF5.left;
                    float f5 = rectF5.top;
                    canvas.drawRect(f4, f5, rectF5.right, f5 + height, this.mPaint);
                    RectF rectF6 = this.mBounds;
                    float f6 = rectF6.left;
                    float f7 = rectF6.bottom;
                    canvas.drawRect(f6, f7 - height, rectF6.right, f7, this.mPaint);
                }
            }
        } else if ($enumboxing$ordinal == 1) {
            int save2 = canvas.save();
            this.mPath.setFillType(Path.FillType.EVEN_ODD);
            canvas.clipPath(this.mPath);
            Drawable drawable3 = this.mCurrentDelegate;
            if (drawable3 != null) {
                drawable3.draw(canvas);
            }
            canvas.restoreToCount(save2);
        }
        if (this.mBorderColor != 0) {
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setColor(this.mBorderColor);
            this.mPaint.setStrokeWidth(this.mBorderWidth);
            this.mPath.setFillType(Path.FillType.EVEN_ODD);
            canvas.drawPath(this.mBorderPath, this.mPaint);
        }
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.mCurrentDelegate;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        updatePath();
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setBorder(int i, float f) {
        this.mBorderColor = i;
        this.mBorderWidth = f;
        updatePath();
        invalidateSelf();
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setCircle(boolean z) {
        this.mIsCircle = z;
        updatePath();
        invalidateSelf();
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setPadding(float f) {
        this.mPadding = f;
        updatePath();
        invalidateSelf();
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
        Arrays.fill(this.mRadii, f);
        updatePath();
        invalidateSelf();
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setScaleDownInsideBorders(boolean z) {
        this.mScaleDownInsideBorders = z;
        updatePath();
        invalidateSelf();
    }

    public final void updatePath() {
        float[] fArr;
        this.mPath.reset();
        this.mBorderPath.reset();
        this.mTempRectangle.set(getBounds());
        RectF rectF = this.mTempRectangle;
        float f = this.mPadding;
        rectF.inset(f, f);
        this.mPath.addRect(this.mTempRectangle, Path.Direction.CW);
        if (this.mIsCircle) {
            this.mPath.addCircle(this.mTempRectangle.centerX(), this.mTempRectangle.centerY(), Math.min(this.mTempRectangle.width(), this.mTempRectangle.height()) / 2.0f, Path.Direction.CW);
        } else {
            this.mPath.addRoundRect(this.mTempRectangle, this.mRadii, Path.Direction.CW);
        }
        RectF rectF2 = this.mTempRectangle;
        float f2 = this.mPadding;
        rectF2.inset(-f2, -f2);
        RectF rectF3 = this.mTempRectangle;
        float f3 = this.mBorderWidth;
        rectF3.inset(f3 / 2.0f, f3 / 2.0f);
        if (this.mIsCircle) {
            this.mBorderPath.addCircle(this.mTempRectangle.centerX(), this.mTempRectangle.centerY(), Math.min(this.mTempRectangle.width(), this.mTempRectangle.height()) / 2.0f, Path.Direction.CW);
        } else {
            int i = 0;
            while (true) {
                fArr = this.mBorderRadii;
                if (i >= fArr.length) {
                    break;
                }
                fArr[i] = (this.mRadii[i] + this.mPadding) - (this.mBorderWidth / 2.0f);
                i++;
            }
            this.mBorderPath.addRoundRect(this.mTempRectangle, fArr, Path.Direction.CW);
        }
        RectF rectF4 = this.mTempRectangle;
        float f4 = this.mBorderWidth;
        rectF4.inset((-f4) / 2.0f, (-f4) / 2.0f);
    }
}
