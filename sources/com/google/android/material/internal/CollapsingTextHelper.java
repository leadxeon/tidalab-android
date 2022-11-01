package com.google.android.material.internal;

import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import androidx.core.text.TextDirectionHeuristicsCompat;
import androidx.core.view.ViewCompat;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.StaticLayoutBuilderCompat;
import com.google.android.material.resources.CancelableFontCallback;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public final class CollapsingTextHelper {
    public boolean boundsChanged;
    public float collapsedDrawX;
    public float collapsedDrawY;
    public CancelableFontCallback collapsedFontCallback;
    public float collapsedLetterSpacing;
    public ColorStateList collapsedShadowColor;
    public float collapsedShadowDx;
    public float collapsedShadowDy;
    public float collapsedShadowRadius;
    public float collapsedTextBlend;
    public ColorStateList collapsedTextColor;
    public Typeface collapsedTypeface;
    public float currentDrawX;
    public float currentDrawY;
    public float currentTextSize;
    public Typeface currentTypeface;
    public boolean drawTitle;
    public float expandedDrawX;
    public float expandedDrawY;
    public float expandedFirstLineDrawX;
    public float expandedFraction;
    public float expandedTextBlend;
    public ColorStateList expandedTextColor;
    public Bitmap expandedTitleTexture;
    public Typeface expandedTypeface;
    public boolean isRtl;
    public TimeInterpolator positionInterpolator;
    public float scale;
    public int[] state;
    public CharSequence text;
    public StaticLayout textLayout;
    public final TextPaint textPaint;
    public TimeInterpolator textSizeInterpolator;
    public CharSequence textToDraw;
    public CharSequence textToDrawCollapsed;
    public final TextPaint tmpPaint;
    public final View view;
    public int expandedTextGravity = 16;
    public int collapsedTextGravity = 16;
    public float expandedTextSize = 15.0f;
    public float collapsedTextSize = 15.0f;
    public final Rect collapsedBounds = new Rect();
    public final Rect expandedBounds = new Rect();
    public final RectF currentBounds = new RectF();

    /* renamed from: com.google.android.material.internal.CollapsingTextHelper$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements CancelableFontCallback.ApplyFont {
        public AnonymousClass1() {
        }
    }

    public CollapsingTextHelper(View view) {
        this.view = view;
        TextPaint textPaint = new TextPaint(129);
        this.textPaint = textPaint;
        this.tmpPaint = new TextPaint(textPaint);
    }

    public static int blendColors(int i, int i2, float f) {
        float f2 = 1.0f - f;
        return Color.argb((int) ((Color.alpha(i2) * f) + (Color.alpha(i) * f2)), (int) ((Color.red(i2) * f) + (Color.red(i) * f2)), (int) ((Color.green(i2) * f) + (Color.green(i) * f2)), (int) ((Color.blue(i2) * f) + (Color.blue(i) * f2)));
    }

    public static float lerp(float f, float f2, float f3, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f3 = timeInterpolator.getInterpolation(f3);
        }
        TimeInterpolator timeInterpolator2 = AnimationUtils.LINEAR_INTERPOLATOR;
        return GeneratedOutlineSupport.outline0(f2, f, f3, f);
    }

    public static boolean rectEquals(Rect rect, int i, int i2, int i3, int i4) {
        return rect.left == i && rect.top == i2 && rect.right == i3 && rect.bottom == i4;
    }

    public float calculateCollapsedTextWidth() {
        if (this.text == null) {
            return 0.0f;
        }
        TextPaint textPaint = this.tmpPaint;
        textPaint.setTextSize(this.collapsedTextSize);
        textPaint.setTypeface(this.collapsedTypeface);
        textPaint.setLetterSpacing(this.collapsedLetterSpacing);
        TextPaint textPaint2 = this.tmpPaint;
        CharSequence charSequence = this.text;
        return textPaint2.measureText(charSequence, 0, charSequence.length());
    }

    public final boolean calculateIsRtl(CharSequence charSequence) {
        View view = this.view;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        boolean z = true;
        if (view.getLayoutDirection() != 1) {
            z = false;
        }
        return ((TextDirectionHeuristicsCompat.TextDirectionHeuristicImpl) (z ? TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL : TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR)).isRtl(charSequence, 0, charSequence.length());
    }

    public final void calculateOffsets(float f) {
        this.currentBounds.left = lerp(this.expandedBounds.left, this.collapsedBounds.left, f, this.positionInterpolator);
        this.currentBounds.top = lerp(this.expandedDrawY, this.collapsedDrawY, f, this.positionInterpolator);
        this.currentBounds.right = lerp(this.expandedBounds.right, this.collapsedBounds.right, f, this.positionInterpolator);
        this.currentBounds.bottom = lerp(this.expandedBounds.bottom, this.collapsedBounds.bottom, f, this.positionInterpolator);
        this.currentDrawX = lerp(this.expandedDrawX, this.collapsedDrawX, f, this.positionInterpolator);
        this.currentDrawY = lerp(this.expandedDrawY, this.collapsedDrawY, f, this.positionInterpolator);
        setInterpolatedTextSize(lerp(this.expandedTextSize, this.collapsedTextSize, f, this.textSizeInterpolator));
        TimeInterpolator timeInterpolator = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
        this.collapsedTextBlend = 1.0f - lerp(0.0f, 1.0f, 1.0f - f, timeInterpolator);
        View view = this.view;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        view.postInvalidateOnAnimation();
        this.expandedTextBlend = lerp(1.0f, 0.0f, f, timeInterpolator);
        this.view.postInvalidateOnAnimation();
        ColorStateList colorStateList = this.collapsedTextColor;
        ColorStateList colorStateList2 = this.expandedTextColor;
        if (colorStateList != colorStateList2) {
            this.textPaint.setColor(blendColors(getCurrentColor(colorStateList2), getCurrentCollapsedTextColor(), f));
        } else {
            this.textPaint.setColor(getCurrentCollapsedTextColor());
        }
        float f2 = this.collapsedLetterSpacing;
        if (f2 != 0.0f) {
            this.textPaint.setLetterSpacing(lerp(0.0f, f2, f, timeInterpolator));
        } else {
            this.textPaint.setLetterSpacing(f2);
        }
        this.textPaint.setShadowLayer(lerp(0.0f, this.collapsedShadowRadius, f, null), lerp(0.0f, this.collapsedShadowDx, f, null), lerp(0.0f, this.collapsedShadowDy, f, null), blendColors(getCurrentColor(null), getCurrentColor(this.collapsedShadowColor), f));
        this.view.postInvalidateOnAnimation();
    }

    public final void calculateUsingTextSize(float f) {
        float f2;
        boolean z;
        StaticLayout staticLayout;
        if (this.text != null) {
            float width = this.collapsedBounds.width();
            float width2 = this.expandedBounds.width();
            if (Math.abs(f - this.collapsedTextSize) < 0.001f) {
                f2 = this.collapsedTextSize;
                this.scale = 1.0f;
                Typeface typeface = this.currentTypeface;
                Typeface typeface2 = this.collapsedTypeface;
                if (typeface != typeface2) {
                    this.currentTypeface = typeface2;
                    z = true;
                } else {
                    z = false;
                }
            } else {
                float f3 = this.expandedTextSize;
                Typeface typeface3 = this.currentTypeface;
                Typeface typeface4 = this.expandedTypeface;
                if (typeface3 != typeface4) {
                    this.currentTypeface = typeface4;
                    z = true;
                } else {
                    z = false;
                }
                if (Math.abs(f - f3) < 0.001f) {
                    this.scale = 1.0f;
                } else {
                    this.scale = f / this.expandedTextSize;
                }
                float f4 = this.collapsedTextSize / this.expandedTextSize;
                width = width2 * f4 > width ? Math.min(width / f4, width2) : width2;
                f2 = f3;
            }
            if (width > 0.0f) {
                z = this.currentTextSize != f2 || this.boundsChanged || z;
                this.currentTextSize = f2;
                this.boundsChanged = false;
            }
            if (this.textToDraw == null || z) {
                this.textPaint.setTextSize(this.currentTextSize);
                this.textPaint.setTypeface(this.currentTypeface);
                this.textPaint.setLinearText(this.scale != 1.0f);
                boolean calculateIsRtl = calculateIsRtl(this.text);
                this.isRtl = calculateIsRtl;
                try {
                    StaticLayoutBuilderCompat staticLayoutBuilderCompat = new StaticLayoutBuilderCompat(this.text, this.textPaint, (int) width);
                    staticLayoutBuilderCompat.ellipsize = TextUtils.TruncateAt.END;
                    staticLayoutBuilderCompat.isRtl = calculateIsRtl;
                    staticLayoutBuilderCompat.alignment = Layout.Alignment.ALIGN_NORMAL;
                    staticLayoutBuilderCompat.includePad = false;
                    staticLayoutBuilderCompat.maxLines = 1;
                    staticLayout = staticLayoutBuilderCompat.build();
                } catch (StaticLayoutBuilderCompat.StaticLayoutBuilderCompatException e) {
                    Log.e("CollapsingTextHelper", e.getCause().getMessage(), e);
                    staticLayout = null;
                }
                Objects.requireNonNull(staticLayout);
                this.textLayout = staticLayout;
                this.textToDraw = staticLayout.getText();
            }
        }
    }

    public float getCollapsedTextHeight() {
        TextPaint textPaint = this.tmpPaint;
        textPaint.setTextSize(this.collapsedTextSize);
        textPaint.setTypeface(this.collapsedTypeface);
        textPaint.setLetterSpacing(this.collapsedLetterSpacing);
        return -this.tmpPaint.ascent();
    }

    public int getCurrentCollapsedTextColor() {
        return getCurrentColor(this.collapsedTextColor);
    }

    public final int getCurrentColor(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.state;
        if (iArr != null) {
            return colorStateList.getColorForState(iArr, 0);
        }
        return colorStateList.getDefaultColor();
    }

    public void onBoundsChanged() {
        this.drawTitle = this.collapsedBounds.width() > 0 && this.collapsedBounds.height() > 0 && this.expandedBounds.width() > 0 && this.expandedBounds.height() > 0;
    }

    public void recalculate() {
        StaticLayout staticLayout;
        if (this.view.getHeight() > 0 && this.view.getWidth() > 0) {
            float f = this.currentTextSize;
            calculateUsingTextSize(this.collapsedTextSize);
            CharSequence charSequence = this.textToDraw;
            if (!(charSequence == null || (staticLayout = this.textLayout) == null)) {
                this.textToDrawCollapsed = TextUtils.ellipsize(charSequence, this.textPaint, staticLayout.getWidth(), TextUtils.TruncateAt.END);
            }
            CharSequence charSequence2 = this.textToDrawCollapsed;
            float f2 = 0.0f;
            float measureText = charSequence2 != null ? this.textPaint.measureText(charSequence2, 0, charSequence2.length()) : 0.0f;
            int absoluteGravity = Gravity.getAbsoluteGravity(this.collapsedTextGravity, this.isRtl ? 1 : 0);
            int i = absoluteGravity & 112;
            if (i == 48) {
                this.collapsedDrawY = this.collapsedBounds.top;
            } else if (i != 80) {
                this.collapsedDrawY = this.collapsedBounds.centerY() - ((this.textPaint.descent() - this.textPaint.ascent()) / 2.0f);
            } else {
                this.collapsedDrawY = this.textPaint.ascent() + this.collapsedBounds.bottom;
            }
            int i2 = absoluteGravity & 8388615;
            if (i2 == 1) {
                this.collapsedDrawX = this.collapsedBounds.centerX() - (measureText / 2.0f);
            } else if (i2 != 5) {
                this.collapsedDrawX = this.collapsedBounds.left;
            } else {
                this.collapsedDrawX = this.collapsedBounds.right - measureText;
            }
            calculateUsingTextSize(this.expandedTextSize);
            StaticLayout staticLayout2 = this.textLayout;
            float height = staticLayout2 != null ? staticLayout2.getHeight() : 0.0f;
            CharSequence charSequence3 = this.textToDraw;
            float measureText2 = charSequence3 != null ? this.textPaint.measureText(charSequence3, 0, charSequence3.length()) : 0.0f;
            StaticLayout staticLayout3 = this.textLayout;
            if (staticLayout3 != null) {
                f2 = staticLayout3.getLineLeft(0);
            }
            this.expandedFirstLineDrawX = f2;
            int absoluteGravity2 = Gravity.getAbsoluteGravity(this.expandedTextGravity, this.isRtl ? 1 : 0);
            int i3 = absoluteGravity2 & 112;
            if (i3 == 48) {
                this.expandedDrawY = this.expandedBounds.top;
            } else if (i3 != 80) {
                this.expandedDrawY = this.expandedBounds.centerY() - (height / 2.0f);
            } else {
                this.expandedDrawY = this.textPaint.descent() + (this.expandedBounds.bottom - height);
            }
            int i4 = absoluteGravity2 & 8388615;
            if (i4 == 1) {
                this.expandedDrawX = this.expandedBounds.centerX() - (measureText2 / 2.0f);
            } else if (i4 != 5) {
                this.expandedDrawX = this.expandedBounds.left;
            } else {
                this.expandedDrawX = this.expandedBounds.right - measureText2;
            }
            Bitmap bitmap = this.expandedTitleTexture;
            if (bitmap != null) {
                bitmap.recycle();
                this.expandedTitleTexture = null;
            }
            setInterpolatedTextSize(f);
            calculateOffsets(this.expandedFraction);
        }
    }

    public void setCollapsedTextColor(ColorStateList colorStateList) {
        if (this.collapsedTextColor != colorStateList) {
            this.collapsedTextColor = colorStateList;
            recalculate();
        }
    }

    public void setCollapsedTextGravity(int i) {
        if (this.collapsedTextGravity != i) {
            this.collapsedTextGravity = i;
            recalculate();
        }
    }

    public void setExpansionFraction(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        } else if (f > 1.0f) {
            f = 1.0f;
        }
        if (f != this.expandedFraction) {
            this.expandedFraction = f;
            calculateOffsets(f);
        }
    }

    public final void setInterpolatedTextSize(float f) {
        calculateUsingTextSize(f);
        View view = this.view;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        view.postInvalidateOnAnimation();
    }

    public void setTypefaces(Typeface typeface) {
        boolean z;
        CancelableFontCallback cancelableFontCallback = this.collapsedFontCallback;
        boolean z2 = true;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.cancelled = true;
        }
        if (this.collapsedTypeface != typeface) {
            this.collapsedTypeface = typeface;
            z = true;
        } else {
            z = false;
        }
        if (this.expandedTypeface != typeface) {
            this.expandedTypeface = typeface;
        } else {
            z2 = false;
        }
        if (z || z2) {
            recalculate();
        }
    }
}
