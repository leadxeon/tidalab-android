package com.facebook.react.views.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility;
import com.facebook.react.R$style;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.Spacing;
import java.util.Arrays;
import java.util.Locale;
/* loaded from: classes.dex */
public class ReactViewBackgroundDrawable extends Drawable {
    public Spacing mBorderAlpha;
    public float[] mBorderCornerRadii;
    public Spacing mBorderRGB;
    public int mBorderStyle;
    public Spacing mBorderWidth;
    public Path mCenterDrawPath;
    public final Context mContext;
    public PointF mInnerBottomLeftCorner;
    public PointF mInnerBottomRightCorner;
    public Path mInnerClipPathForBorderRadius;
    public RectF mInnerClipTempRectForBorderRadius;
    public PointF mInnerTopLeftCorner;
    public PointF mInnerTopRightCorner;
    public int mLayoutDirection;
    public Path mOuterClipPathForBorderRadius;
    public RectF mOuterClipTempRectForBorderRadius;
    public PathEffect mPathEffectForBorderStyle;
    public Path mPathForBorder;
    public Path mPathForBorderRadiusOutline;
    public RectF mTempRectForBorderRadiusOutline;
    public RectF mTempRectForCenterDrawPath;
    public boolean mNeedUpdatePathForBorderRadius = false;
    public float mBorderRadius = Float.NaN;
    public final Paint mPaint = new Paint(1);
    public int mColor = 0;
    public int mAlpha = 255;

    public ReactViewBackgroundDrawable(Context context) {
        this.mContext = context;
    }

    public static void getEllipseIntersectionWithLine(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, PointF pointF) {
        double d9 = (d + d3) / 2.0d;
        double d10 = (d2 + d4) / 2.0d;
        double d11 = d5 - d9;
        double d12 = d6 - d10;
        double abs = Math.abs(d3 - d) / 2.0d;
        double abs2 = Math.abs(d4 - d2) / 2.0d;
        double d13 = ((d8 - d10) - d12) / ((d7 - d9) - d11);
        double d14 = d12 - (d11 * d13);
        double d15 = abs2 * abs2;
        double d16 = abs * abs;
        double d17 = (d16 * d13 * d13) + d15;
        double d18 = abs * 2.0d * abs * d14 * d13;
        double d19 = (-(d16 * ((d14 * d14) - d15))) / d17;
        double d20 = d17 * 2.0d;
        double sqrt = ((-d18) / d20) - Math.sqrt(Math.pow(d18 / d20, 2.0d) + d19);
        double d21 = sqrt + d9;
        double d22 = (d13 * sqrt) + d14 + d10;
        if (!Double.isNaN(d21) && !Double.isNaN(d22)) {
            pointF.x = (float) d21;
            pointF.y = (float) d22;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x004e  */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void draw(android.graphics.Canvas r27) {
        /*
            Method dump skipped, instructions count: 1018
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.view.ReactViewBackgroundDrawable.draw(android.graphics.Canvas):void");
    }

    public final void drawQuadrilateral(Canvas canvas, int i, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (i != 0) {
            if (this.mPathForBorder == null) {
                this.mPathForBorder = new Path();
            }
            this.mPaint.setColor(i);
            this.mPathForBorder.reset();
            this.mPathForBorder.moveTo(f, f2);
            this.mPathForBorder.lineTo(f3, f4);
            this.mPathForBorder.lineTo(f5, f6);
            this.mPathForBorder.lineTo(f7, f8);
            this.mPathForBorder.lineTo(f, f2);
            canvas.drawPath(this.mPathForBorder, this.mPaint);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mAlpha;
    }

    public final int getBorderColor(int i) {
        Spacing spacing = this.mBorderRGB;
        float f = spacing != null ? spacing.get(i) : 0.0f;
        Spacing spacing2 = this.mBorderAlpha;
        return ((((int) (spacing2 != null ? spacing2.get(i) : 255.0f)) << 24) & (-16777216)) | (((int) f) & 16777215);
    }

    public float getBorderRadiusOrDefaultTo$enumunboxing$(float f, int i) {
        float[] fArr = this.mBorderCornerRadii;
        if (fArr == null) {
            return f;
        }
        float f2 = fArr[SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(i)];
        return R$style.isUndefined(f2) ? f : f2;
    }

    public float getBorderWidthOrDefaultTo(float f, int i) {
        Spacing spacing = this.mBorderWidth;
        if (spacing == null) {
            return f;
        }
        float f2 = spacing.mSpacing[i];
        return R$style.isUndefined(f2) ? f : f2;
    }

    public RectF getDirectionAwareBorderInsets() {
        float borderWidthOrDefaultTo = getBorderWidthOrDefaultTo(0.0f, 8);
        boolean z = true;
        float borderWidthOrDefaultTo2 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 1);
        float borderWidthOrDefaultTo3 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 3);
        float borderWidthOrDefaultTo4 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 0);
        float borderWidthOrDefaultTo5 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 2);
        Spacing spacing = this.mBorderWidth;
        if (spacing != null) {
            if (this.mLayoutDirection != 1) {
                z = false;
            }
            float[] fArr = spacing.mSpacing;
            float f = fArr[4];
            float f2 = fArr[5];
            if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(this.mContext)) {
                if (!R$style.isUndefined(f)) {
                    borderWidthOrDefaultTo4 = f;
                }
                if (!R$style.isUndefined(f2)) {
                    borderWidthOrDefaultTo5 = f2;
                }
                borderWidthOrDefaultTo4 = z ? borderWidthOrDefaultTo5 : borderWidthOrDefaultTo4;
                if (z) {
                    borderWidthOrDefaultTo5 = borderWidthOrDefaultTo4;
                }
            } else {
                float f3 = z ? f2 : f;
                if (!z) {
                    f = f2;
                }
                if (!R$style.isUndefined(f3)) {
                    borderWidthOrDefaultTo4 = f3;
                }
                if (!R$style.isUndefined(f)) {
                    borderWidthOrDefaultTo5 = f;
                }
            }
        }
        return new RectF(borderWidthOrDefaultTo4, borderWidthOrDefaultTo2, borderWidthOrDefaultTo5, borderWidthOrDefaultTo3);
    }

    public float getFullBorderWidth() {
        Spacing spacing = this.mBorderWidth;
        if (spacing == null || R$style.isUndefined(spacing.mSpacing[8])) {
            return 0.0f;
        }
        return this.mBorderWidth.mSpacing[8];
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        int multiplyColorAlpha = R$style.multiplyColorAlpha(this.mColor, this.mAlpha) >>> 24;
        if (multiplyColorAlpha == 255) {
            return -1;
        }
        return multiplyColorAlpha == 0 ? -2 : -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        if ((R$style.isUndefined(this.mBorderRadius) || this.mBorderRadius <= 0.0f) && this.mBorderCornerRadii == null) {
            outline.setRect(getBounds());
            return;
        }
        updatePath();
        outline.setConvexPath(this.mPathForBorderRadiusOutline);
    }

    public boolean hasRoundedBorders() {
        if (!R$style.isUndefined(this.mBorderRadius) && this.mBorderRadius > 0.0f) {
            return true;
        }
        float[] fArr = this.mBorderCornerRadii;
        if (fArr != null) {
            for (float f : fArr) {
                if (!R$style.isUndefined(f) && f > 0.0f) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean isBorderColorDefined(int i) {
        Spacing spacing = this.mBorderRGB;
        float f = Float.NaN;
        float f2 = spacing != null ? spacing.get(i) : Float.NaN;
        Spacing spacing2 = this.mBorderAlpha;
        if (spacing2 != null) {
            f = spacing2.get(i);
        }
        return !R$style.isUndefined(f2) && !R$style.isUndefined(f);
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mNeedUpdatePathForBorderRadius = true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (i != this.mAlpha) {
            this.mAlpha = i;
            invalidateSelf();
        }
    }

    public void setBorderColor(int i, float f, float f2) {
        if (this.mBorderRGB == null) {
            this.mBorderRGB = new Spacing(0.0f);
        }
        if (!R$style.floatsEqual(this.mBorderRGB.mSpacing[i], f)) {
            this.mBorderRGB.set(i, f);
            invalidateSelf();
        }
        if (this.mBorderAlpha == null) {
            this.mBorderAlpha = new Spacing(255.0f);
        }
        if (!R$style.floatsEqual(this.mBorderAlpha.mSpacing[i], f2)) {
            this.mBorderAlpha.set(i, f2);
            invalidateSelf();
        }
    }

    public void setBorderStyle(String str) {
        int valueOfcom$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle = str == null ? 0 : SolverVariable$Type$r8$EnumUnboxingUtility.valueOfcom$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle(str.toUpperCase(Locale.US));
        if (this.mBorderStyle != valueOfcom$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle) {
            this.mBorderStyle = valueOfcom$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle;
            this.mNeedUpdatePathForBorderRadius = true;
            invalidateSelf();
        }
    }

    public void setBorderWidth(int i, float f) {
        if (this.mBorderWidth == null) {
            this.mBorderWidth = new Spacing(0.0f);
        }
        if (!R$style.floatsEqual(this.mBorderWidth.mSpacing[i], f)) {
            this.mBorderWidth.set(i, f);
            if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 8) {
                this.mNeedUpdatePathForBorderRadius = true;
            }
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void setRadius(float f, int i) {
        if (this.mBorderCornerRadii == null) {
            float[] fArr = new float[8];
            this.mBorderCornerRadii = fArr;
            Arrays.fill(fArr, Float.NaN);
        }
        if (!R$style.floatsEqual(this.mBorderCornerRadii[i], f)) {
            this.mBorderCornerRadii[i] = f;
            this.mNeedUpdatePathForBorderRadius = true;
            invalidateSelf();
        }
    }

    public final void updatePath() {
        float f;
        float f2;
        if (this.mNeedUpdatePathForBorderRadius) {
            this.mNeedUpdatePathForBorderRadius = false;
            if (this.mInnerClipPathForBorderRadius == null) {
                this.mInnerClipPathForBorderRadius = new Path();
            }
            if (this.mOuterClipPathForBorderRadius == null) {
                this.mOuterClipPathForBorderRadius = new Path();
            }
            if (this.mPathForBorderRadiusOutline == null) {
                this.mPathForBorderRadiusOutline = new Path();
            }
            if (this.mCenterDrawPath == null) {
                this.mCenterDrawPath = new Path();
            }
            if (this.mInnerClipTempRectForBorderRadius == null) {
                this.mInnerClipTempRectForBorderRadius = new RectF();
            }
            if (this.mOuterClipTempRectForBorderRadius == null) {
                this.mOuterClipTempRectForBorderRadius = new RectF();
            }
            if (this.mTempRectForBorderRadiusOutline == null) {
                this.mTempRectForBorderRadiusOutline = new RectF();
            }
            if (this.mTempRectForCenterDrawPath == null) {
                this.mTempRectForCenterDrawPath = new RectF();
            }
            this.mInnerClipPathForBorderRadius.reset();
            this.mOuterClipPathForBorderRadius.reset();
            this.mPathForBorderRadiusOutline.reset();
            this.mCenterDrawPath.reset();
            this.mInnerClipTempRectForBorderRadius.set(getBounds());
            this.mOuterClipTempRectForBorderRadius.set(getBounds());
            this.mTempRectForBorderRadiusOutline.set(getBounds());
            this.mTempRectForCenterDrawPath.set(getBounds());
            float fullBorderWidth = getFullBorderWidth();
            if (fullBorderWidth > 0.0f) {
                float f3 = fullBorderWidth * 0.5f;
                this.mTempRectForCenterDrawPath.inset(f3, f3);
            }
            RectF directionAwareBorderInsets = getDirectionAwareBorderInsets();
            RectF rectF = this.mInnerClipTempRectForBorderRadius;
            rectF.top += directionAwareBorderInsets.top;
            rectF.bottom -= directionAwareBorderInsets.bottom;
            rectF.left += directionAwareBorderInsets.left;
            rectF.right -= directionAwareBorderInsets.right;
            float f4 = R$style.isUndefined(this.mBorderRadius) ? 0.0f : this.mBorderRadius;
            float borderRadiusOrDefaultTo$enumunboxing$ = getBorderRadiusOrDefaultTo$enumunboxing$(f4, 1);
            float borderRadiusOrDefaultTo$enumunboxing$2 = getBorderRadiusOrDefaultTo$enumunboxing$(f4, 2);
            float borderRadiusOrDefaultTo$enumunboxing$3 = getBorderRadiusOrDefaultTo$enumunboxing$(f4, 4);
            float borderRadiusOrDefaultTo$enumunboxing$4 = getBorderRadiusOrDefaultTo$enumunboxing$(f4, 3);
            boolean z = this.mLayoutDirection == 1;
            float borderRadiusOrDefaultTo$enumunboxing$5 = getBorderRadiusOrDefaultTo$enumunboxing$(Float.NaN, 5);
            float borderRadiusOrDefaultTo$enumunboxing$6 = getBorderRadiusOrDefaultTo$enumunboxing$(Float.NaN, 6);
            float borderRadiusOrDefaultTo$enumunboxing$7 = getBorderRadiusOrDefaultTo$enumunboxing$(Float.NaN, 7);
            float borderRadiusOrDefaultTo$enumunboxing$8 = getBorderRadiusOrDefaultTo$enumunboxing$(Float.NaN, 8);
            if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(this.mContext)) {
                if (!R$style.isUndefined(borderRadiusOrDefaultTo$enumunboxing$5)) {
                    borderRadiusOrDefaultTo$enumunboxing$ = borderRadiusOrDefaultTo$enumunboxing$5;
                }
                if (!R$style.isUndefined(borderRadiusOrDefaultTo$enumunboxing$6)) {
                    borderRadiusOrDefaultTo$enumunboxing$2 = borderRadiusOrDefaultTo$enumunboxing$6;
                }
                if (!R$style.isUndefined(borderRadiusOrDefaultTo$enumunboxing$7)) {
                    borderRadiusOrDefaultTo$enumunboxing$3 = borderRadiusOrDefaultTo$enumunboxing$7;
                }
                if (!R$style.isUndefined(borderRadiusOrDefaultTo$enumunboxing$8)) {
                    borderRadiusOrDefaultTo$enumunboxing$4 = borderRadiusOrDefaultTo$enumunboxing$8;
                }
                f = z ? borderRadiusOrDefaultTo$enumunboxing$2 : borderRadiusOrDefaultTo$enumunboxing$;
                if (!z) {
                    borderRadiusOrDefaultTo$enumunboxing$ = borderRadiusOrDefaultTo$enumunboxing$2;
                }
                f2 = z ? borderRadiusOrDefaultTo$enumunboxing$4 : borderRadiusOrDefaultTo$enumunboxing$3;
                if (z) {
                    borderRadiusOrDefaultTo$enumunboxing$4 = borderRadiusOrDefaultTo$enumunboxing$3;
                }
            } else {
                float f5 = z ? borderRadiusOrDefaultTo$enumunboxing$6 : borderRadiusOrDefaultTo$enumunboxing$5;
                if (!z) {
                    borderRadiusOrDefaultTo$enumunboxing$5 = borderRadiusOrDefaultTo$enumunboxing$6;
                }
                float f6 = z ? borderRadiusOrDefaultTo$enumunboxing$8 : borderRadiusOrDefaultTo$enumunboxing$7;
                if (!z) {
                    borderRadiusOrDefaultTo$enumunboxing$7 = borderRadiusOrDefaultTo$enumunboxing$8;
                }
                if (!R$style.isUndefined(f5)) {
                    borderRadiusOrDefaultTo$enumunboxing$ = f5;
                }
                if (!R$style.isUndefined(borderRadiusOrDefaultTo$enumunboxing$5)) {
                    borderRadiusOrDefaultTo$enumunboxing$2 = borderRadiusOrDefaultTo$enumunboxing$5;
                }
                if (!R$style.isUndefined(f6)) {
                    borderRadiusOrDefaultTo$enumunboxing$3 = f6;
                }
                if (!R$style.isUndefined(borderRadiusOrDefaultTo$enumunboxing$7)) {
                    f = borderRadiusOrDefaultTo$enumunboxing$;
                    borderRadiusOrDefaultTo$enumunboxing$ = borderRadiusOrDefaultTo$enumunboxing$2;
                    f2 = borderRadiusOrDefaultTo$enumunboxing$3;
                    borderRadiusOrDefaultTo$enumunboxing$4 = borderRadiusOrDefaultTo$enumunboxing$7;
                } else {
                    f = borderRadiusOrDefaultTo$enumunboxing$;
                    borderRadiusOrDefaultTo$enumunboxing$ = borderRadiusOrDefaultTo$enumunboxing$2;
                    f2 = borderRadiusOrDefaultTo$enumunboxing$3;
                }
            }
            float max = Math.max(f - directionAwareBorderInsets.left, 0.0f);
            float max2 = Math.max(f - directionAwareBorderInsets.top, 0.0f);
            float max3 = Math.max(borderRadiusOrDefaultTo$enumunboxing$ - directionAwareBorderInsets.right, 0.0f);
            float max4 = Math.max(borderRadiusOrDefaultTo$enumunboxing$ - directionAwareBorderInsets.top, 0.0f);
            float max5 = Math.max(borderRadiusOrDefaultTo$enumunboxing$4 - directionAwareBorderInsets.right, 0.0f);
            float max6 = Math.max(borderRadiusOrDefaultTo$enumunboxing$4 - directionAwareBorderInsets.bottom, 0.0f);
            float max7 = Math.max(f2 - directionAwareBorderInsets.left, 0.0f);
            float max8 = Math.max(f2 - directionAwareBorderInsets.bottom, 0.0f);
            this.mInnerClipPathForBorderRadius.addRoundRect(this.mInnerClipTempRectForBorderRadius, new float[]{max, max2, max3, max4, max5, max6, max7, max8}, Path.Direction.CW);
            this.mOuterClipPathForBorderRadius.addRoundRect(this.mOuterClipTempRectForBorderRadius, new float[]{f, f, borderRadiusOrDefaultTo$enumunboxing$, borderRadiusOrDefaultTo$enumunboxing$, borderRadiusOrDefaultTo$enumunboxing$4, borderRadiusOrDefaultTo$enumunboxing$4, f2, f2}, Path.Direction.CW);
            Spacing spacing = this.mBorderWidth;
            float f7 = spacing != null ? spacing.get(8) / 2.0f : 0.0f;
            float f8 = f + f7;
            float f9 = borderRadiusOrDefaultTo$enumunboxing$ + f7;
            float f10 = borderRadiusOrDefaultTo$enumunboxing$4 + f7;
            float f11 = f2 + f7;
            this.mPathForBorderRadiusOutline.addRoundRect(this.mTempRectForBorderRadiusOutline, new float[]{f8, f8, f9, f9, f10, f10, f11, f11}, Path.Direction.CW);
            Path path = this.mCenterDrawPath;
            RectF rectF2 = this.mTempRectForCenterDrawPath;
            float[] fArr = new float[8];
            int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
            fArr[0] = max + (i > 0 ? f7 : 0.0f);
            fArr[1] = (i > 0 ? f7 : 0.0f) + max2;
            int i2 = (borderRadiusOrDefaultTo$enumunboxing$ > 0.0f ? 1 : (borderRadiusOrDefaultTo$enumunboxing$ == 0.0f ? 0 : -1));
            fArr[2] = (i2 > 0 ? f7 : 0.0f) + max3;
            fArr[3] = (i2 > 0 ? f7 : 0.0f) + max4;
            int i3 = (borderRadiusOrDefaultTo$enumunboxing$4 > 0.0f ? 1 : (borderRadiusOrDefaultTo$enumunboxing$4 == 0.0f ? 0 : -1));
            fArr[4] = (i3 > 0 ? f7 : 0.0f) + max5;
            fArr[5] = (i3 > 0 ? f7 : 0.0f) + max6;
            int i4 = (f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1));
            fArr[6] = (i4 > 0 ? f7 : 0.0f) + max7;
            if (i4 <= 0) {
                f7 = 0.0f;
            }
            fArr[7] = f7 + max8;
            path.addRoundRect(rectF2, fArr, Path.Direction.CW);
            if (this.mInnerTopLeftCorner == null) {
                this.mInnerTopLeftCorner = new PointF();
            }
            PointF pointF = this.mInnerTopLeftCorner;
            RectF rectF3 = this.mInnerClipTempRectForBorderRadius;
            float f12 = rectF3.left;
            pointF.x = f12;
            float f13 = rectF3.top;
            pointF.y = f13;
            double d = f12;
            double d2 = f13;
            RectF rectF4 = this.mOuterClipTempRectForBorderRadius;
            getEllipseIntersectionWithLine(d, d2, (max * 2.0f) + f12, (max2 * 2.0f) + f13, rectF4.left, rectF4.top, d, d2, pointF);
            if (this.mInnerBottomLeftCorner == null) {
                this.mInnerBottomLeftCorner = new PointF();
            }
            PointF pointF2 = this.mInnerBottomLeftCorner;
            RectF rectF5 = this.mInnerClipTempRectForBorderRadius;
            float f14 = rectF5.left;
            pointF2.x = f14;
            float f15 = rectF5.bottom;
            pointF2.y = f15;
            double d3 = f14;
            double d4 = f15;
            RectF rectF6 = this.mOuterClipTempRectForBorderRadius;
            getEllipseIntersectionWithLine(d3, f15 - (max8 * 2.0f), (max7 * 2.0f) + f14, d4, rectF6.left, rectF6.bottom, d3, d4, pointF2);
            if (this.mInnerTopRightCorner == null) {
                this.mInnerTopRightCorner = new PointF();
            }
            PointF pointF3 = this.mInnerTopRightCorner;
            RectF rectF7 = this.mInnerClipTempRectForBorderRadius;
            float f16 = rectF7.right;
            pointF3.x = f16;
            float f17 = rectF7.top;
            pointF3.y = f17;
            double d5 = f16 - (max3 * 2.0f);
            double d6 = f17;
            double d7 = f16;
            RectF rectF8 = this.mOuterClipTempRectForBorderRadius;
            getEllipseIntersectionWithLine(d5, d6, d7, (max4 * 2.0f) + f17, rectF8.right, rectF8.top, d7, d6, pointF3);
            if (this.mInnerBottomRightCorner == null) {
                this.mInnerBottomRightCorner = new PointF();
            }
            PointF pointF4 = this.mInnerBottomRightCorner;
            RectF rectF9 = this.mInnerClipTempRectForBorderRadius;
            float f18 = rectF9.right;
            pointF4.x = f18;
            float f19 = rectF9.bottom;
            pointF4.y = f19;
            double d8 = f18;
            double d9 = f19;
            RectF rectF10 = this.mOuterClipTempRectForBorderRadius;
            getEllipseIntersectionWithLine(f18 - (max5 * 2.0f), f19 - (max6 * 2.0f), d8, d9, rectF10.right, rectF10.bottom, d8, d9, pointF4);
        }
    }
}
