package com.google.android.material.chip;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.graphics.drawable.TintAwareDrawable;
import com.google.android.material.R$style;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class ChipDrawable extends MaterialShapeDrawable implements TintAwareDrawable, Drawable.Callback, TextDrawableHelper.TextDrawableDelegate {
    public static final int[] DEFAULT_STATE = {16842910};
    public static final ShapeDrawable closeIconRippleMask = new ShapeDrawable(new OvalShape());
    public boolean checkable;
    public Drawable checkedIcon;
    public ColorStateList checkedIconTint;
    public boolean checkedIconVisible;
    public ColorStateList chipBackgroundColor;
    public float chipEndPadding;
    public Drawable chipIcon;
    public float chipIconSize;
    public ColorStateList chipIconTint;
    public boolean chipIconVisible;
    public float chipMinHeight;
    public float chipStartPadding;
    public ColorStateList chipStrokeColor;
    public float chipStrokeWidth;
    public ColorStateList chipSurfaceColor;
    public Drawable closeIcon;
    public CharSequence closeIconContentDescription;
    public float closeIconEndPadding;
    public Drawable closeIconRipple;
    public float closeIconSize;
    public float closeIconStartPadding;
    public int[] closeIconStateSet;
    public ColorStateList closeIconTint;
    public boolean closeIconVisible;
    public ColorFilter colorFilter;
    public ColorStateList compatRippleColor;
    public final Context context;
    public boolean currentChecked;
    public int currentChipBackgroundColor;
    public int currentChipStrokeColor;
    public int currentChipSurfaceColor;
    public int currentCompatRippleColor;
    public int currentCompositeSurfaceBackgroundColor;
    public int currentTextColor;
    public int currentTint;
    public boolean hasChipIconTint;
    public MotionSpec hideMotionSpec;
    public float iconEndPadding;
    public float iconStartPadding;
    public boolean isShapeThemingEnabled;
    public int maxWidth;
    public ColorStateList rippleColor;
    public MotionSpec showMotionSpec;
    public final TextDrawableHelper textDrawableHelper;
    public float textEndPadding;
    public float textStartPadding;
    public ColorStateList tint;
    public PorterDuffColorFilter tintFilter;
    public TextUtils.TruncateAt truncateAt;
    public boolean useCompatRipple;
    public float chipCornerRadius = -1.0f;
    public final Paint chipPaint = new Paint(1);
    public final Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
    public final RectF rectF = new RectF();
    public final PointF pointF = new PointF();
    public final Path shapePath = new Path();
    public int alpha = 255;
    public PorterDuff.Mode tintMode = PorterDuff.Mode.SRC_IN;
    public WeakReference<Delegate> delegate = new WeakReference<>(null);
    public CharSequence text = HttpUrl.FRAGMENT_ENCODE_SET;
    public boolean shouldDrawText = true;

    /* loaded from: classes.dex */
    public interface Delegate {
        void onChipDrawableSizeChange();
    }

    public ChipDrawable(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.drawableState.elevationOverlayProvider = new ElevationOverlayProvider(context);
        updateZ();
        this.context = context;
        TextDrawableHelper textDrawableHelper = new TextDrawableHelper(this);
        this.textDrawableHelper = textDrawableHelper;
        textDrawableHelper.textPaint.density = context.getResources().getDisplayMetrics().density;
        int[] iArr = DEFAULT_STATE;
        setState(iArr);
        setCloseIconState(iArr);
        int[] iArr2 = RippleUtils.PRESSED_STATE_SET;
        closeIconRippleMask.setTint(-1);
    }

    public final void applyChildDrawable(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(this);
            AppOpsManagerCompat.setLayoutDirection(drawable, AppOpsManagerCompat.getLayoutDirection(this));
            drawable.setLevel(getLevel());
            drawable.setVisible(isVisible(), false);
            if (drawable == this.closeIcon) {
                if (drawable.isStateful()) {
                    drawable.setState(this.closeIconStateSet);
                }
                drawable.setTintList(this.closeIconTint);
                return;
            }
            if (drawable.isStateful()) {
                drawable.setState(getState());
            }
            Drawable drawable2 = this.chipIcon;
            if (drawable == drawable2 && this.hasChipIconTint) {
                drawable2.setTintList(this.chipIconTint);
            }
        }
    }

    public final void calculateChipIconBounds(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (showsChipIcon() || showsCheckedIcon()) {
            float f = this.chipStartPadding + this.iconStartPadding;
            float currentChipIconWidth = getCurrentChipIconWidth();
            if (AppOpsManagerCompat.getLayoutDirection(this) == 0) {
                float f2 = rect.left + f;
                rectF.left = f2;
                rectF.right = f2 + currentChipIconWidth;
            } else {
                float f3 = rect.right - f;
                rectF.right = f3;
                rectF.left = f3 - currentChipIconWidth;
            }
            Drawable drawable = this.currentChecked ? this.checkedIcon : this.chipIcon;
            float f4 = this.chipIconSize;
            if (f4 <= 0.0f && drawable != null) {
                f4 = (float) Math.ceil(R$style.dpToPx(this.context, 24));
                if (drawable.getIntrinsicHeight() <= f4) {
                    f4 = drawable.getIntrinsicHeight();
                    float exactCenterY = rect.exactCenterY() - (f4 / 2.0f);
                    rectF.top = exactCenterY;
                    rectF.bottom = exactCenterY + f4;
                }
            }
            float exactCenterY2 = rect.exactCenterY() - (f4 / 2.0f);
            rectF.top = exactCenterY2;
            rectF.bottom = exactCenterY2 + f4;
        }
    }

    public float calculateChipIconWidth() {
        if (!showsChipIcon() && !showsCheckedIcon()) {
            return 0.0f;
        }
        return getCurrentChipIconWidth() + this.iconStartPadding + this.iconEndPadding;
    }

    public final void calculateCloseIconBounds(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (showsCloseIcon()) {
            float f = this.chipEndPadding + this.closeIconEndPadding;
            if (AppOpsManagerCompat.getLayoutDirection(this) == 0) {
                float f2 = rect.right - f;
                rectF.right = f2;
                rectF.left = f2 - this.closeIconSize;
            } else {
                float f3 = rect.left + f;
                rectF.left = f3;
                rectF.right = f3 + this.closeIconSize;
            }
            float exactCenterY = rect.exactCenterY();
            float f4 = this.closeIconSize;
            float f5 = exactCenterY - (f4 / 2.0f);
            rectF.top = f5;
            rectF.bottom = f5 + f4;
        }
    }

    public final void calculateCloseIconTouchBounds(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (showsCloseIcon()) {
            float f = this.chipEndPadding + this.closeIconEndPadding + this.closeIconSize + this.closeIconStartPadding + this.textEndPadding;
            if (AppOpsManagerCompat.getLayoutDirection(this) == 0) {
                float f2 = rect.right;
                rectF.right = f2;
                rectF.left = f2 - f;
            } else {
                int i = rect.left;
                rectF.left = i;
                rectF.right = i + f;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    public float calculateCloseIconWidth() {
        if (showsCloseIcon()) {
            return this.closeIconStartPadding + this.closeIconSize + this.closeIconEndPadding;
        }
        return 0.0f;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        Rect bounds = getBounds();
        if (!bounds.isEmpty() && (i = this.alpha) != 0) {
            if (i < 255) {
                float f = bounds.left;
                float f2 = bounds.top;
                float f3 = bounds.right;
                float f4 = bounds.bottom;
                if (Build.VERSION.SDK_INT > 21) {
                    i2 = canvas.saveLayerAlpha(f, f2, f3, f4, i);
                } else {
                    i2 = canvas.saveLayerAlpha(f, f2, f3, f4, i, 31);
                }
            } else {
                i2 = 0;
            }
            if (!this.isShapeThemingEnabled) {
                this.chipPaint.setColor(this.currentChipSurfaceColor);
                this.chipPaint.setStyle(Paint.Style.FILL);
                this.rectF.set(bounds);
                canvas.drawRoundRect(this.rectF, getChipCornerRadius(), getChipCornerRadius(), this.chipPaint);
            }
            if (!this.isShapeThemingEnabled) {
                this.chipPaint.setColor(this.currentChipBackgroundColor);
                this.chipPaint.setStyle(Paint.Style.FILL);
                Paint paint = this.chipPaint;
                ColorFilter colorFilter = this.colorFilter;
                if (colorFilter == null) {
                    colorFilter = this.tintFilter;
                }
                paint.setColorFilter(colorFilter);
                this.rectF.set(bounds);
                canvas.drawRoundRect(this.rectF, getChipCornerRadius(), getChipCornerRadius(), this.chipPaint);
            }
            if (this.isShapeThemingEnabled) {
                super.draw(canvas);
            }
            if (this.chipStrokeWidth > 0.0f && !this.isShapeThemingEnabled) {
                this.chipPaint.setColor(this.currentChipStrokeColor);
                this.chipPaint.setStyle(Paint.Style.STROKE);
                if (!this.isShapeThemingEnabled) {
                    Paint paint2 = this.chipPaint;
                    ColorFilter colorFilter2 = this.colorFilter;
                    if (colorFilter2 == null) {
                        colorFilter2 = this.tintFilter;
                    }
                    paint2.setColorFilter(colorFilter2);
                }
                RectF rectF = this.rectF;
                float f5 = this.chipStrokeWidth / 2.0f;
                rectF.set(bounds.left + f5, bounds.top + f5, bounds.right - f5, bounds.bottom - f5);
                float f6 = this.chipCornerRadius - (this.chipStrokeWidth / 2.0f);
                canvas.drawRoundRect(this.rectF, f6, f6, this.chipPaint);
            }
            this.chipPaint.setColor(this.currentCompatRippleColor);
            this.chipPaint.setStyle(Paint.Style.FILL);
            this.rectF.set(bounds);
            if (!this.isShapeThemingEnabled) {
                canvas.drawRoundRect(this.rectF, getChipCornerRadius(), getChipCornerRadius(), this.chipPaint);
                i3 = 0;
            } else {
                calculatePathForSize(new RectF(bounds), this.shapePath);
                i3 = 0;
                drawShape(canvas, this.chipPaint, this.shapePath, this.drawableState.shapeAppearanceModel, getBoundsAsRectF());
            }
            if (showsChipIcon()) {
                calculateChipIconBounds(bounds, this.rectF);
                RectF rectF2 = this.rectF;
                float f7 = rectF2.left;
                float f8 = rectF2.top;
                canvas.translate(f7, f8);
                this.chipIcon.setBounds(i3, i3, (int) this.rectF.width(), (int) this.rectF.height());
                this.chipIcon.draw(canvas);
                canvas.translate(-f7, -f8);
            }
            if (showsCheckedIcon()) {
                calculateChipIconBounds(bounds, this.rectF);
                RectF rectF3 = this.rectF;
                float f9 = rectF3.left;
                float f10 = rectF3.top;
                canvas.translate(f9, f10);
                this.checkedIcon.setBounds(i3, i3, (int) this.rectF.width(), (int) this.rectF.height());
                this.checkedIcon.draw(canvas);
                canvas.translate(-f9, -f10);
            }
            if (!this.shouldDrawText || this.text == null) {
                i6 = i2;
                i5 = 255;
                i4 = 0;
            } else {
                PointF pointF = this.pointF;
                pointF.set(0.0f, 0.0f);
                Paint.Align align = Paint.Align.LEFT;
                if (this.text != null) {
                    float calculateChipIconWidth = calculateChipIconWidth() + this.chipStartPadding + this.textStartPadding;
                    if (AppOpsManagerCompat.getLayoutDirection(this) == 0) {
                        pointF.x = bounds.left + calculateChipIconWidth;
                        align = Paint.Align.LEFT;
                    } else {
                        pointF.x = bounds.right - calculateChipIconWidth;
                        align = Paint.Align.RIGHT;
                    }
                    this.textDrawableHelper.textPaint.getFontMetrics(this.fontMetrics);
                    Paint.FontMetrics fontMetrics = this.fontMetrics;
                    pointF.y = bounds.centerY() - ((fontMetrics.descent + fontMetrics.ascent) / 2.0f);
                }
                RectF rectF4 = this.rectF;
                rectF4.setEmpty();
                if (this.text != null) {
                    float calculateChipIconWidth2 = calculateChipIconWidth() + this.chipStartPadding + this.textStartPadding;
                    float calculateCloseIconWidth = calculateCloseIconWidth() + this.chipEndPadding + this.textEndPadding;
                    if (AppOpsManagerCompat.getLayoutDirection(this) == 0) {
                        rectF4.left = bounds.left + calculateChipIconWidth2;
                        rectF4.right = bounds.right - calculateCloseIconWidth;
                    } else {
                        rectF4.left = bounds.left + calculateCloseIconWidth;
                        rectF4.right = bounds.right - calculateChipIconWidth2;
                    }
                    rectF4.top = bounds.top;
                    rectF4.bottom = bounds.bottom;
                }
                TextDrawableHelper textDrawableHelper = this.textDrawableHelper;
                if (textDrawableHelper.textAppearance != null) {
                    textDrawableHelper.textPaint.drawableState = getState();
                    TextDrawableHelper textDrawableHelper2 = this.textDrawableHelper;
                    textDrawableHelper2.textAppearance.updateDrawState(this.context, textDrawableHelper2.textPaint, textDrawableHelper2.fontCallback);
                }
                this.textDrawableHelper.textPaint.setTextAlign(align);
                boolean z = Math.round(this.textDrawableHelper.getTextWidth(this.text.toString())) > Math.round(this.rectF.width());
                if (z) {
                    i7 = canvas.save();
                    canvas.clipRect(this.rectF);
                } else {
                    i7 = 0;
                }
                CharSequence charSequence = this.text;
                if (z && this.truncateAt != null) {
                    charSequence = TextUtils.ellipsize(charSequence, this.textDrawableHelper.textPaint, this.rectF.width(), this.truncateAt);
                }
                int length = charSequence.length();
                PointF pointF2 = this.pointF;
                i4 = 0;
                i5 = 255;
                i6 = i2;
                canvas.drawText(charSequence, 0, length, pointF2.x, pointF2.y, this.textDrawableHelper.textPaint);
                if (z) {
                    canvas.restoreToCount(i7);
                }
            }
            if (showsCloseIcon()) {
                calculateCloseIconBounds(bounds, this.rectF);
                RectF rectF5 = this.rectF;
                float f11 = rectF5.left;
                float f12 = rectF5.top;
                canvas.translate(f11, f12);
                this.closeIcon.setBounds(i4, i4, (int) this.rectF.width(), (int) this.rectF.height());
                int[] iArr = RippleUtils.PRESSED_STATE_SET;
                this.closeIconRipple.setBounds(this.closeIcon.getBounds());
                this.closeIconRipple.jumpToCurrentState();
                this.closeIconRipple.draw(canvas);
                canvas.translate(-f11, -f12);
            }
            if (this.alpha < i5) {
                canvas.restoreToCount(i6);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.alpha;
    }

    public float getChipCornerRadius() {
        return this.isShapeThemingEnabled ? getTopLeftCornerResolvedSize() : this.chipCornerRadius;
    }

    public Drawable getCloseIcon() {
        Drawable drawable = this.closeIcon;
        if (drawable != null) {
            return AppOpsManagerCompat.unwrap(drawable);
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    public final float getCurrentChipIconWidth() {
        Drawable drawable = this.currentChecked ? this.checkedIcon : this.chipIcon;
        float f = this.chipIconSize;
        return (f > 0.0f || drawable == null) ? f : drawable.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.chipMinHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return Math.min(Math.round(calculateCloseIconWidth() + this.textDrawableHelper.getTextWidth(this.text.toString()) + calculateChipIconWidth() + this.chipStartPadding + this.textStartPadding + this.textEndPadding + this.chipEndPadding), this.maxWidth);
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.isShapeThemingEnabled) {
            super.getOutline(outline);
            return;
        }
        Rect bounds = getBounds();
        if (!bounds.isEmpty()) {
            outline.setRoundRect(bounds, this.chipCornerRadius);
        } else {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), (int) this.chipMinHeight, this.chipCornerRadius);
        }
        outline.setAlpha(this.alpha / 255.0f);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        if (isStateful(this.chipSurfaceColor) || isStateful(this.chipBackgroundColor) || isStateful(this.chipStrokeColor)) {
            return true;
        }
        if (this.useCompatRipple && isStateful(this.compatRippleColor)) {
            return true;
        }
        TextAppearance textAppearance = this.textDrawableHelper.textAppearance;
        if ((textAppearance == null || (colorStateList = textAppearance.textColor) == null || !colorStateList.isStateful()) ? false : true) {
            return true;
        }
        return (this.checkedIconVisible && this.checkedIcon != null && this.checkable) || isStateful(this.chipIcon) || isStateful(this.checkedIcon) || isStateful(this.tint);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i) {
        boolean onLayoutDirectionChanged = super.onLayoutDirectionChanged(i);
        if (showsChipIcon()) {
            onLayoutDirectionChanged |= AppOpsManagerCompat.setLayoutDirection(this.chipIcon, i);
        }
        if (showsCheckedIcon()) {
            onLayoutDirectionChanged |= AppOpsManagerCompat.setLayoutDirection(this.checkedIcon, i);
        }
        if (showsCloseIcon()) {
            onLayoutDirectionChanged |= AppOpsManagerCompat.setLayoutDirection(this.closeIcon, i);
        }
        if (!onLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i) {
        boolean onLevelChange = super.onLevelChange(i);
        if (showsChipIcon()) {
            onLevelChange |= this.chipIcon.setLevel(i);
        }
        if (showsCheckedIcon()) {
            onLevelChange |= this.checkedIcon.setLevel(i);
        }
        if (showsCloseIcon()) {
            onLevelChange |= this.closeIcon.setLevel(i);
        }
        if (onLevelChange) {
            invalidateSelf();
        }
        return onLevelChange;
    }

    public void onSizeChange() {
        Delegate delegate = this.delegate.get();
        if (delegate != null) {
            delegate.onChipDrawableSizeChange();
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public boolean onStateChange(int[] iArr) {
        if (this.isShapeThemingEnabled) {
            super.onStateChange(iArr);
        }
        return onStateChange(iArr, this.closeIconStateSet);
    }

    @Override // com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public void onTextSizeChange() {
        onSizeChange();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (this.alpha != i) {
            this.alpha = i;
            invalidateSelf();
        }
    }

    public void setCheckable(boolean z) {
        if (this.checkable != z) {
            this.checkable = z;
            float calculateChipIconWidth = calculateChipIconWidth();
            if (!z && this.currentChecked) {
                this.currentChecked = false;
            }
            float calculateChipIconWidth2 = calculateChipIconWidth();
            invalidateSelf();
            if (calculateChipIconWidth != calculateChipIconWidth2) {
                onSizeChange();
            }
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        if (this.checkedIcon != drawable) {
            float calculateChipIconWidth = calculateChipIconWidth();
            this.checkedIcon = drawable;
            float calculateChipIconWidth2 = calculateChipIconWidth();
            unapplyChildDrawable(this.checkedIcon);
            applyChildDrawable(this.checkedIcon);
            invalidateSelf();
            if (calculateChipIconWidth != calculateChipIconWidth2) {
                onSizeChange();
            }
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        if (this.checkedIconTint != colorStateList) {
            this.checkedIconTint = colorStateList;
            if (this.checkedIconVisible && this.checkedIcon != null && this.checkable) {
                this.checkedIcon.setTintList(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void setCheckedIconVisible(boolean z) {
        if (this.checkedIconVisible != z) {
            boolean showsCheckedIcon = showsCheckedIcon();
            this.checkedIconVisible = z;
            boolean showsCheckedIcon2 = showsCheckedIcon();
            if (showsCheckedIcon != showsCheckedIcon2) {
                if (showsCheckedIcon2) {
                    applyChildDrawable(this.checkedIcon);
                } else {
                    unapplyChildDrawable(this.checkedIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        if (this.chipBackgroundColor != colorStateList) {
            this.chipBackgroundColor = colorStateList;
            onStateChange(getState());
        }
    }

    @Deprecated
    public void setChipCornerRadius(float f) {
        if (this.chipCornerRadius != f) {
            this.chipCornerRadius = f;
            this.drawableState.shapeAppearanceModel = this.drawableState.shapeAppearanceModel.withCornerSize(f);
            invalidateSelf();
        }
    }

    public void setChipEndPadding(float f) {
        if (this.chipEndPadding != f) {
            this.chipEndPadding = f;
            invalidateSelf();
            onSizeChange();
        }
    }

    public void setChipIcon(Drawable drawable) {
        Drawable drawable2 = this.chipIcon;
        Drawable drawable3 = null;
        Drawable unwrap = drawable2 != null ? AppOpsManagerCompat.unwrap(drawable2) : null;
        if (unwrap != drawable) {
            float calculateChipIconWidth = calculateChipIconWidth();
            if (drawable != null) {
                drawable3 = AppOpsManagerCompat.wrap(drawable).mutate();
            }
            this.chipIcon = drawable3;
            float calculateChipIconWidth2 = calculateChipIconWidth();
            unapplyChildDrawable(unwrap);
            if (showsChipIcon()) {
                applyChildDrawable(this.chipIcon);
            }
            invalidateSelf();
            if (calculateChipIconWidth != calculateChipIconWidth2) {
                onSizeChange();
            }
        }
    }

    public void setChipIconSize(float f) {
        if (this.chipIconSize != f) {
            float calculateChipIconWidth = calculateChipIconWidth();
            this.chipIconSize = f;
            float calculateChipIconWidth2 = calculateChipIconWidth();
            invalidateSelf();
            if (calculateChipIconWidth != calculateChipIconWidth2) {
                onSizeChange();
            }
        }
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        this.hasChipIconTint = true;
        if (this.chipIconTint != colorStateList) {
            this.chipIconTint = colorStateList;
            if (showsChipIcon()) {
                this.chipIcon.setTintList(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void setChipIconVisible(boolean z) {
        if (this.chipIconVisible != z) {
            boolean showsChipIcon = showsChipIcon();
            this.chipIconVisible = z;
            boolean showsChipIcon2 = showsChipIcon();
            if (showsChipIcon != showsChipIcon2) {
                if (showsChipIcon2) {
                    applyChildDrawable(this.chipIcon);
                } else {
                    unapplyChildDrawable(this.chipIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    public void setChipMinHeight(float f) {
        if (this.chipMinHeight != f) {
            this.chipMinHeight = f;
            invalidateSelf();
            onSizeChange();
        }
    }

    public void setChipStartPadding(float f) {
        if (this.chipStartPadding != f) {
            this.chipStartPadding = f;
            invalidateSelf();
            onSizeChange();
        }
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        if (this.chipStrokeColor != colorStateList) {
            this.chipStrokeColor = colorStateList;
            if (this.isShapeThemingEnabled) {
                setStrokeColor(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void setChipStrokeWidth(float f) {
        if (this.chipStrokeWidth != f) {
            this.chipStrokeWidth = f;
            this.chipPaint.setStrokeWidth(f);
            if (this.isShapeThemingEnabled) {
                this.drawableState.strokeWidth = f;
                invalidateSelf();
            }
            invalidateSelf();
        }
    }

    public void setCloseIcon(Drawable drawable) {
        Drawable closeIcon = getCloseIcon();
        if (closeIcon != drawable) {
            float calculateCloseIconWidth = calculateCloseIconWidth();
            this.closeIcon = drawable != null ? AppOpsManagerCompat.wrap(drawable).mutate() : null;
            int[] iArr = RippleUtils.PRESSED_STATE_SET;
            this.closeIconRipple = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(this.rippleColor), this.closeIcon, closeIconRippleMask);
            float calculateCloseIconWidth2 = calculateCloseIconWidth();
            unapplyChildDrawable(closeIcon);
            if (showsCloseIcon()) {
                applyChildDrawable(this.closeIcon);
            }
            invalidateSelf();
            if (calculateCloseIconWidth != calculateCloseIconWidth2) {
                onSizeChange();
            }
        }
    }

    public void setCloseIconEndPadding(float f) {
        if (this.closeIconEndPadding != f) {
            this.closeIconEndPadding = f;
            invalidateSelf();
            if (showsCloseIcon()) {
                onSizeChange();
            }
        }
    }

    public void setCloseIconSize(float f) {
        if (this.closeIconSize != f) {
            this.closeIconSize = f;
            invalidateSelf();
            if (showsCloseIcon()) {
                onSizeChange();
            }
        }
    }

    public void setCloseIconStartPadding(float f) {
        if (this.closeIconStartPadding != f) {
            this.closeIconStartPadding = f;
            invalidateSelf();
            if (showsCloseIcon()) {
                onSizeChange();
            }
        }
    }

    public boolean setCloseIconState(int[] iArr) {
        if (Arrays.equals(this.closeIconStateSet, iArr)) {
            return false;
        }
        this.closeIconStateSet = iArr;
        if (showsCloseIcon()) {
            return onStateChange(getState(), iArr);
        }
        return false;
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        if (this.closeIconTint != colorStateList) {
            this.closeIconTint = colorStateList;
            if (showsCloseIcon()) {
                this.closeIcon.setTintList(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void setCloseIconVisible(boolean z) {
        if (this.closeIconVisible != z) {
            boolean showsCloseIcon = showsCloseIcon();
            this.closeIconVisible = z;
            boolean showsCloseIcon2 = showsCloseIcon();
            if (showsCloseIcon != showsCloseIcon2) {
                if (showsCloseIcon2) {
                    applyChildDrawable(this.closeIcon);
                } else {
                    unapplyChildDrawable(this.closeIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.colorFilter != colorFilter) {
            this.colorFilter = colorFilter;
            invalidateSelf();
        }
    }

    public void setIconEndPadding(float f) {
        if (this.iconEndPadding != f) {
            float calculateChipIconWidth = calculateChipIconWidth();
            this.iconEndPadding = f;
            float calculateChipIconWidth2 = calculateChipIconWidth();
            invalidateSelf();
            if (calculateChipIconWidth != calculateChipIconWidth2) {
                onSizeChange();
            }
        }
    }

    public void setIconStartPadding(float f) {
        if (this.iconStartPadding != f) {
            float calculateChipIconWidth = calculateChipIconWidth();
            this.iconStartPadding = f;
            float calculateChipIconWidth2 = calculateChipIconWidth();
            invalidateSelf();
            if (calculateChipIconWidth != calculateChipIconWidth2) {
                onSizeChange();
            }
        }
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (this.rippleColor != colorStateList) {
            this.rippleColor = colorStateList;
            this.compatRippleColor = this.useCompatRipple ? RippleUtils.sanitizeRippleDrawableColor(colorStateList) : null;
            onStateChange(getState());
        }
    }

    public void setText(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        if (!TextUtils.equals(this.text, charSequence)) {
            this.text = charSequence;
            this.textDrawableHelper.textWidthDirty = true;
            invalidateSelf();
            onSizeChange();
        }
    }

    public void setTextEndPadding(float f) {
        if (this.textEndPadding != f) {
            this.textEndPadding = f;
            invalidateSelf();
            onSizeChange();
        }
    }

    public void setTextStartPadding(float f) {
        if (this.textStartPadding != f) {
            this.textStartPadding = f;
            invalidateSelf();
            onSizeChange();
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        if (this.tint != colorStateList) {
            this.tint = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.tintMode != mode) {
            this.tintMode = mode;
            this.tintFilter = R$style.updateTintFilter(this, this.tint, mode);
            invalidateSelf();
        }
    }

    public void setUseCompatRipple(boolean z) {
        if (this.useCompatRipple != z) {
            this.useCompatRipple = z;
            this.compatRippleColor = z ? RippleUtils.sanitizeRippleDrawableColor(this.rippleColor) : null;
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (showsChipIcon()) {
            visible |= this.chipIcon.setVisible(z, z2);
        }
        if (showsCheckedIcon()) {
            visible |= this.checkedIcon.setVisible(z, z2);
        }
        if (showsCloseIcon()) {
            visible |= this.closeIcon.setVisible(z, z2);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    public final boolean showsCheckedIcon() {
        return this.checkedIconVisible && this.checkedIcon != null && this.currentChecked;
    }

    public final boolean showsChipIcon() {
        return this.chipIconVisible && this.chipIcon != null;
    }

    public final boolean showsCloseIcon() {
        return this.closeIconVisible && this.closeIcon != null;
    }

    public final void unapplyChildDrawable(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0148  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onStateChange(int[] r9, int[] r10) {
        /*
            Method dump skipped, instructions count: 337
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.ChipDrawable.onStateChange(int[], int[]):boolean");
    }

    public static boolean isStateful(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    public static boolean isStateful(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }
}
