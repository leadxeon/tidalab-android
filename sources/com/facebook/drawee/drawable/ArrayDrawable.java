package com.facebook.drawee.drawable;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.recyclerview.R$dimen;
import java.util.Objects;
/* loaded from: classes.dex */
public class ArrayDrawable extends Drawable implements Drawable.Callback, TransformCallback, TransformAwareDrawable {
    public final DrawableParent[] mDrawableParents;
    public final Drawable[] mLayers;
    public TransformCallback mTransformCallback;
    public final DrawableProperties mDrawableProperties = new DrawableProperties();
    public final Rect mTmpRect = new Rect();
    public boolean mIsStateful = false;
    public boolean mIsStatefulCalculated = false;
    public boolean mIsMutated = false;

    public ArrayDrawable(Drawable[] drawableArr) {
        int i = 0;
        Objects.requireNonNull(drawableArr);
        this.mLayers = drawableArr;
        while (true) {
            Drawable[] drawableArr2 = this.mLayers;
            if (i < drawableArr2.length) {
                R$dimen.setCallbacks(drawableArr2[i], this, this);
                i++;
            } else {
                this.mDrawableParents = new DrawableParent[drawableArr2.length];
                return;
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.draw(canvas);
                }
                i++;
            } else {
                return;
            }
        }
    }

    public Drawable getDrawable(int i) {
        boolean z = true;
        R$dimen.checkArgument(i >= 0);
        if (i >= this.mLayers.length) {
            z = false;
        }
        R$dimen.checkArgument(z);
        return this.mLayers[i];
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        int i = 0;
        int i2 = -1;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (i >= drawableArr.length) {
                break;
            }
            Drawable drawable = drawableArr[i];
            if (drawable != null) {
                i2 = Math.max(i2, drawable.getIntrinsicHeight());
            }
            i++;
        }
        if (i2 > 0) {
            return i2;
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        int i = 0;
        int i2 = -1;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (i >= drawableArr.length) {
                break;
            }
            Drawable drawable = drawableArr[i];
            if (drawable != null) {
                i2 = Math.max(i2, drawable.getIntrinsicWidth());
            }
            i++;
        }
        if (i2 > 0) {
            return i2;
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        if (this.mLayers.length == 0) {
            return -2;
        }
        int i = -1;
        int i2 = 1;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (i2 >= drawableArr.length) {
                return i;
            }
            Drawable drawable = drawableArr[i2];
            if (drawable != null) {
                i = Drawable.resolveOpacity(i, drawable.getOpacity());
            }
            i2++;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        int i = 0;
        rect.left = 0;
        rect.top = 0;
        rect.right = 0;
        rect.bottom = 0;
        Rect rect2 = this.mTmpRect;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (i >= drawableArr.length) {
                return true;
            }
            Drawable drawable = drawableArr[i];
            if (drawable != null) {
                drawable.getPadding(rect2);
                rect.left = Math.max(rect.left, rect2.left);
                rect.top = Math.max(rect.top, rect2.top);
                rect.right = Math.max(rect.right, rect2.right);
                rect.bottom = Math.max(rect.bottom, rect2.bottom);
            }
            i++;
        }
    }

    @Override // com.facebook.drawee.drawable.TransformCallback
    public void getRootBounds(RectF rectF) {
        TransformCallback transformCallback = this.mTransformCallback;
        if (transformCallback != null) {
            transformCallback.getRootBounds(rectF);
        } else {
            rectF.set(getBounds());
        }
    }

    @Override // com.facebook.drawee.drawable.TransformCallback
    public void getTransform(Matrix matrix) {
        TransformCallback transformCallback = this.mTransformCallback;
        if (transformCallback != null) {
            transformCallback.getTransform(matrix);
        } else {
            matrix.reset();
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        if (!this.mIsStatefulCalculated) {
            this.mIsStateful = false;
            int i = 0;
            while (true) {
                Drawable[] drawableArr = this.mLayers;
                boolean z = true;
                if (i >= drawableArr.length) {
                    break;
                }
                Drawable drawable = drawableArr[i];
                boolean z2 = this.mIsStateful;
                if (drawable == null || !drawable.isStateful()) {
                    z = false;
                }
                this.mIsStateful = z2 | z;
                i++;
            }
            this.mIsStatefulCalculated = true;
        }
        return this.mIsStateful;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.mutate();
                }
                i++;
            } else {
                this.mIsMutated = true;
                return this;
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.setBounds(rect);
                }
                i++;
            } else {
                return;
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i) {
        int i2 = 0;
        boolean z = false;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (i2 >= drawableArr.length) {
                return z;
            }
            Drawable drawable = drawableArr[i2];
            if (drawable != null && drawable.setLevel(i)) {
                z = true;
            }
            i2++;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        int i = 0;
        boolean z = false;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (i >= drawableArr.length) {
                return z;
            }
            Drawable drawable = drawableArr[i];
            if (drawable != null && drawable.setState(iArr)) {
                z = true;
            }
            i++;
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mDrawableProperties.mAlpha = i;
        int i2 = 0;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (i2 < drawableArr.length) {
                Drawable drawable = drawableArr[i2];
                if (drawable != null) {
                    drawable.setAlpha(i);
                }
                i2++;
            } else {
                return;
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        DrawableProperties drawableProperties = this.mDrawableProperties;
        drawableProperties.mColorFilter = colorFilter;
        drawableProperties.mIsSetColorFilter = true;
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.setColorFilter(colorFilter);
                }
                i++;
            } else {
                return;
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        this.mDrawableProperties.mDither = z ? 1 : 0;
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.setDither(z);
                }
                i++;
            } else {
                return;
            }
        }
    }

    public Drawable setDrawable(int i, Drawable drawable) {
        boolean z = true;
        R$dimen.checkArgument(i >= 0);
        if (i >= this.mLayers.length) {
            z = false;
        }
        R$dimen.checkArgument(z);
        Drawable drawable2 = this.mLayers[i];
        if (drawable != drawable2) {
            if (drawable != null && this.mIsMutated) {
                drawable.mutate();
            }
            R$dimen.setCallbacks(this.mLayers[i], null, null);
            R$dimen.setCallbacks(drawable, null, null);
            R$dimen.setDrawableProperties(drawable, this.mDrawableProperties);
            R$dimen.copyProperties(drawable, this);
            R$dimen.setCallbacks(drawable, this, this);
            this.mIsStatefulCalculated = false;
            this.mLayers[i] = drawable;
            invalidateSelf();
        }
        return drawable2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.mDrawableProperties.mFilterBitmap = z ? 1 : 0;
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.setFilterBitmap(z);
                }
                i++;
            } else {
                return;
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void setHotspot(float f, float f2) {
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (i < drawableArr.length) {
                Drawable drawable = drawableArr[i];
                if (drawable != null) {
                    drawable.setHotspot(f, f2);
                }
                i++;
            } else {
                return;
            }
        }
    }

    @Override // com.facebook.drawee.drawable.TransformAwareDrawable
    public void setTransformCallback(TransformCallback transformCallback) {
        this.mTransformCallback = transformCallback;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        int i = 0;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (i >= drawableArr.length) {
                return visible;
            }
            Drawable drawable = drawableArr[i];
            if (drawable != null) {
                drawable.setVisible(z, z2);
            }
            i++;
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
