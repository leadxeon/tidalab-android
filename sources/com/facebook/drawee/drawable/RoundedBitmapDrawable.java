package com.facebook.drawee.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import androidx.recyclerview.R$dimen;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public class RoundedBitmapDrawable extends RoundedDrawable {
    public final Bitmap mBitmap;
    public final Paint mBorderPaint;
    public WeakReference<Bitmap> mLastBitmap;
    public final Paint mPaint;

    public RoundedBitmapDrawable(Resources resources, Bitmap bitmap, Paint paint) {
        super(new BitmapDrawable(resources, bitmap));
        Paint paint2 = new Paint();
        this.mPaint = paint2;
        Paint paint3 = new Paint(1);
        this.mBorderPaint = paint3;
        this.mBitmap = bitmap;
        if (paint != null) {
            paint2.set(paint);
        }
        paint2.setFlags(1);
        paint3.setStyle(Paint.Style.STROKE);
    }

    @Override // com.facebook.drawee.drawable.RoundedDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        FrescoSystrace.isTracing();
        if (!(super.shouldRound() && this.mBitmap != null)) {
            super.draw(canvas);
            FrescoSystrace.isTracing();
            return;
        }
        updateTransform();
        updatePath();
        WeakReference<Bitmap> weakReference = this.mLastBitmap;
        if (weakReference == null || weakReference.get() != this.mBitmap) {
            this.mLastBitmap = new WeakReference<>(this.mBitmap);
            Paint paint = this.mPaint;
            Bitmap bitmap = this.mBitmap;
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            paint.setShader(new BitmapShader(bitmap, tileMode, tileMode));
            this.mIsShaderTransformDirty = true;
        }
        if (this.mIsShaderTransformDirty) {
            this.mPaint.getShader().setLocalMatrix(this.mTransform);
            this.mIsShaderTransformDirty = false;
        }
        this.mPaint.setFilterBitmap(this.mPaintFilterBitmap);
        int save = canvas.save();
        canvas.concat(this.mInverseParentTransform);
        canvas.drawPath(this.mPath, this.mPaint);
        float f = this.mBorderWidth;
        if (f > 0.0f) {
            this.mBorderPaint.setStrokeWidth(f);
            this.mBorderPaint.setColor(R$dimen.multiplyColorAlpha(this.mBorderColor, this.mPaint.getAlpha()));
            canvas.drawPath(this.mBorderPath, this.mBorderPaint);
        }
        canvas.restoreToCount(save);
        FrescoSystrace.isTracing();
    }

    @Override // com.facebook.drawee.drawable.RoundedDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mDelegate.setAlpha(i);
        if (i != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(i);
            this.mDelegate.setAlpha(i);
            invalidateSelf();
        }
    }

    @Override // com.facebook.drawee.drawable.RoundedDrawable, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mDelegate.setColorFilter(colorFilter);
        this.mPaint.setColorFilter(colorFilter);
    }

    @Override // com.facebook.drawee.drawable.RoundedDrawable
    public boolean shouldRound() {
        return super.shouldRound() && this.mBitmap != null;
    }
}
