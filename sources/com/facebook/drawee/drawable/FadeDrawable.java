package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import androidx.recyclerview.R$dimen;
import java.util.Arrays;
/* loaded from: classes.dex */
public class FadeDrawable extends ArrayDrawable {
    public int mAlpha;
    public int[] mAlphas;
    public int mDurationMs;
    public boolean[] mIsLayerOn;
    public final Drawable[] mLayers;
    public int mPreventInvalidateCount;
    public int[] mStartAlphas;
    public long mStartTimeMs;
    public int mTransitionState;

    public FadeDrawable(Drawable[] drawableArr) {
        super(drawableArr);
        R$dimen.checkState(drawableArr.length >= 1, "At least one layer required!");
        this.mLayers = drawableArr;
        int[] iArr = new int[drawableArr.length];
        this.mStartAlphas = iArr;
        this.mAlphas = new int[drawableArr.length];
        this.mAlpha = 255;
        this.mIsLayerOn = new boolean[drawableArr.length];
        this.mPreventInvalidateCount = 0;
        this.mTransitionState = 2;
        Arrays.fill(iArr, 0);
        this.mStartAlphas[0] = 255;
        Arrays.fill(this.mAlphas, 0);
        this.mAlphas[0] = 255;
        Arrays.fill(this.mIsLayerOn, false);
        this.mIsLayerOn[0] = true;
    }

    public void beginBatchMode() {
        this.mPreventInvalidateCount++;
    }

    @Override // com.facebook.drawee.drawable.ArrayDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        boolean z;
        int i = this.mTransitionState;
        int i2 = 0;
        int i3 = 2;
        if (i == 0) {
            System.arraycopy(this.mAlphas, 0, this.mStartAlphas, 0, this.mLayers.length);
            this.mStartTimeMs = SystemClock.uptimeMillis();
            z = updateAlphas(this.mDurationMs == 0 ? 1.0f : 0.0f);
            if (!z) {
                i3 = 1;
            }
            this.mTransitionState = i3;
        } else if (i != 1) {
            z = true;
        } else {
            R$dimen.checkState(this.mDurationMs > 0);
            z = updateAlphas(((float) (SystemClock.uptimeMillis() - this.mStartTimeMs)) / this.mDurationMs);
            if (!z) {
                i3 = 1;
            }
            this.mTransitionState = i3;
        }
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (i2 >= drawableArr.length) {
                break;
            }
            Drawable drawable = drawableArr[i2];
            int i4 = (this.mAlphas[i2] * this.mAlpha) / 255;
            if (drawable != null && i4 > 0) {
                this.mPreventInvalidateCount++;
                drawable.mutate().setAlpha(i4);
                this.mPreventInvalidateCount--;
                drawable.draw(canvas);
            }
            i2++;
        }
        if (!z) {
            invalidateSelf();
        }
    }

    public void endBatchMode() {
        this.mPreventInvalidateCount--;
        invalidateSelf();
    }

    public void finishTransitionImmediately() {
        this.mTransitionState = 2;
        for (int i = 0; i < this.mLayers.length; i++) {
            this.mAlphas[i] = this.mIsLayerOn[i] ? 255 : 0;
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        if (this.mPreventInvalidateCount == 0) {
            super.invalidateSelf();
        }
    }

    @Override // com.facebook.drawee.drawable.ArrayDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (this.mAlpha != i) {
            this.mAlpha = i;
            invalidateSelf();
        }
    }

    public final boolean updateAlphas(float f) {
        boolean z = true;
        for (int i = 0; i < this.mLayers.length; i++) {
            boolean[] zArr = this.mIsLayerOn;
            int i2 = zArr[i] ? 1 : -1;
            int[] iArr = this.mAlphas;
            iArr[i] = (int) ((i2 * 255 * f) + this.mStartAlphas[i]);
            if (iArr[i] < 0) {
                iArr[i] = 0;
            }
            if (iArr[i] > 255) {
                iArr[i] = 255;
            }
            if (zArr[i] && iArr[i] < 255) {
                z = false;
            }
            if (!zArr[i] && iArr[i] > 0) {
                z = false;
            }
        }
        return z;
    }
}
