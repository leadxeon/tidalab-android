package com.google.android.material.card;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.FrameLayout;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.tidalab.v2board.clash.foss.R;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class MaterialCardView extends CardView implements Checkable, Shapeable {
    public static final int[] CHECKABLE_STATE_SET = {16842911};
    public static final int[] CHECKED_STATE_SET = {16842912};
    public static final int[] DRAGGED_STATE_SET = {R.attr.state_dragged};
    public final MaterialCardViewHelper cardViewHelper;
    public boolean checked;
    public boolean dragged;
    public boolean isParentCardViewDoneInitializing;
    public OnCheckedChangeListener onCheckedChangeListener;

    /* loaded from: classes.dex */
    public interface OnCheckedChangeListener {
        void onCheckedChanged(MaterialCardView materialCardView, boolean z);
    }

    public MaterialCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialCardViewStyle);
    }

    private RectF getBoundsAsRectF() {
        RectF rectF = new RectF();
        rectF.set(this.cardViewHelper.bgDrawable.getBounds());
        return rectF;
    }

    public final void forceRippleRedrawIfNeeded() {
        MaterialCardViewHelper materialCardViewHelper;
        Drawable drawable;
        if (Build.VERSION.SDK_INT > 26 && (drawable = (materialCardViewHelper = this.cardViewHelper).rippleDrawable) != null) {
            Rect bounds = drawable.getBounds();
            int i = bounds.bottom;
            materialCardViewHelper.rippleDrawable.setBounds(bounds.left, bounds.top, bounds.right, i - 1);
            materialCardViewHelper.rippleDrawable.setBounds(bounds.left, bounds.top, bounds.right, i);
        }
    }

    @Override // androidx.cardview.widget.CardView
    public ColorStateList getCardBackgroundColor() {
        return this.cardViewHelper.bgDrawable.drawableState.fillColor;
    }

    public ColorStateList getCardForegroundColor() {
        return this.cardViewHelper.foregroundContentDrawable.drawableState.fillColor;
    }

    public float getCardViewRadius() {
        return super.getRadius();
    }

    public Drawable getCheckedIcon() {
        return this.cardViewHelper.checkedIcon;
    }

    public int getCheckedIconMargin() {
        return this.cardViewHelper.checkedIconMargin;
    }

    public int getCheckedIconSize() {
        return this.cardViewHelper.checkedIconSize;
    }

    public ColorStateList getCheckedIconTint() {
        return this.cardViewHelper.checkedIconTint;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingBottom() {
        return this.cardViewHelper.userContentPadding.bottom;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingLeft() {
        return this.cardViewHelper.userContentPadding.left;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingRight() {
        return this.cardViewHelper.userContentPadding.right;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingTop() {
        return this.cardViewHelper.userContentPadding.top;
    }

    public float getProgress() {
        return this.cardViewHelper.bgDrawable.drawableState.interpolation;
    }

    @Override // androidx.cardview.widget.CardView
    public float getRadius() {
        return this.cardViewHelper.bgDrawable.getTopLeftCornerResolvedSize();
    }

    public ColorStateList getRippleColor() {
        return this.cardViewHelper.rippleColor;
    }

    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.cardViewHelper.shapeAppearanceModel;
    }

    @Deprecated
    public int getStrokeColor() {
        ColorStateList colorStateList = this.cardViewHelper.strokeColor;
        if (colorStateList == null) {
            return -1;
        }
        return colorStateList.getDefaultColor();
    }

    public ColorStateList getStrokeColorStateList() {
        return this.cardViewHelper.strokeColor;
    }

    public int getStrokeWidth() {
        return this.cardViewHelper.strokeWidth;
    }

    public boolean isCheckable() {
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        return materialCardViewHelper != null && materialCardViewHelper.checkable;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.checked;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        R$style.setParentAbsoluteElevation(this, this.cardViewHelper.bgDrawable);
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 3);
        if (isCheckable()) {
            FrameLayout.mergeDrawableStates(onCreateDrawableState, CHECKABLE_STATE_SET);
        }
        if (isChecked()) {
            FrameLayout.mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        if (this.dragged) {
            FrameLayout.mergeDrawableStates(onCreateDrawableState, DRAGGED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.cardview.widget.CardView");
        accessibilityEvent.setChecked(isChecked());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.cardview.widget.CardView");
        accessibilityNodeInfo.setCheckable(isCheckable());
        accessibilityNodeInfo.setClickable(isClickable());
        accessibilityNodeInfo.setChecked(isChecked());
    }

    @Override // androidx.cardview.widget.CardView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        super.onMeasure(i, i2);
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (materialCardViewHelper.clickableForegroundDrawable != null) {
            int i5 = materialCardViewHelper.checkedIconMargin;
            int i6 = materialCardViewHelper.checkedIconSize;
            int i7 = (measuredWidth - i5) - i6;
            int i8 = (measuredHeight - i5) - i6;
            if (materialCardViewHelper.materialCardView.getUseCompatPadding()) {
                i8 -= (int) Math.ceil(materialCardViewHelper.calculateVerticalBackgroundPadding() * 2.0f);
                i7 -= (int) Math.ceil(materialCardViewHelper.calculateHorizontalBackgroundPadding() * 2.0f);
            }
            int i9 = materialCardViewHelper.checkedIconMargin;
            MaterialCardView materialCardView = materialCardViewHelper.materialCardView;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            if (materialCardView.getLayoutDirection() == 1) {
                i3 = i7;
                i4 = i9;
            } else {
                i4 = i7;
                i3 = i9;
            }
            materialCardViewHelper.clickableForegroundDrawable.setLayerInset(2, i4, materialCardViewHelper.checkedIconMargin, i3, i8);
        }
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (this.isParentCardViewDoneInitializing) {
            if (!this.cardViewHelper.isBackgroundOverwritten) {
                Log.i("MaterialCardView", "Setting a custom background is not supported.");
                this.cardViewHelper.isBackgroundOverwritten = true;
            }
            super.setBackgroundDrawable(drawable);
        }
    }

    public void setBackgroundInternal(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(int i) {
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        materialCardViewHelper.bgDrawable.setFillColor(ColorStateList.valueOf(i));
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardElevation(float f) {
        super.setCardElevation(f);
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        materialCardViewHelper.bgDrawable.setElevation(materialCardViewHelper.materialCardView.getCardElevation());
    }

    public void setCardForegroundColor(ColorStateList colorStateList) {
        MaterialShapeDrawable materialShapeDrawable = this.cardViewHelper.foregroundContentDrawable;
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        materialShapeDrawable.setFillColor(colorStateList);
    }

    public void setCheckable(boolean z) {
        this.cardViewHelper.checkable = z;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.checked != z) {
            toggle();
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        this.cardViewHelper.setCheckedIcon(drawable);
    }

    public void setCheckedIconMargin(int i) {
        this.cardViewHelper.checkedIconMargin = i;
    }

    public void setCheckedIconMarginResource(int i) {
        if (i != -1) {
            this.cardViewHelper.checkedIconMargin = getResources().getDimensionPixelSize(i);
        }
    }

    public void setCheckedIconResource(int i) {
        this.cardViewHelper.setCheckedIcon(AppCompatResources.getDrawable(getContext(), i));
    }

    public void setCheckedIconSize(int i) {
        this.cardViewHelper.checkedIconSize = i;
    }

    public void setCheckedIconSizeResource(int i) {
        if (i != 0) {
            this.cardViewHelper.checkedIconSize = getResources().getDimensionPixelSize(i);
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        materialCardViewHelper.checkedIconTint = colorStateList;
        Drawable drawable = materialCardViewHelper.checkedIcon;
        if (drawable != null) {
            drawable.setTintList(colorStateList);
        }
    }

    @Override // android.view.View
    public void setClickable(boolean z) {
        super.setClickable(z);
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        if (materialCardViewHelper != null) {
            Drawable drawable = materialCardViewHelper.fgDrawable;
            Drawable clickableForeground = materialCardViewHelper.materialCardView.isClickable() ? materialCardViewHelper.getClickableForeground() : materialCardViewHelper.foregroundContentDrawable;
            materialCardViewHelper.fgDrawable = clickableForeground;
            if (drawable == clickableForeground) {
                return;
            }
            if (Build.VERSION.SDK_INT < 23 || !(materialCardViewHelper.materialCardView.getForeground() instanceof InsetDrawable)) {
                materialCardViewHelper.materialCardView.setForeground(materialCardViewHelper.insetDrawable(clickableForeground));
            } else {
                ((InsetDrawable) materialCardViewHelper.materialCardView.getForeground()).setDrawable(clickableForeground);
            }
        }
    }

    public void setDragged(boolean z) {
        if (this.dragged != z) {
            this.dragged = z;
            refreshDrawableState();
            forceRippleRedrawIfNeeded();
            invalidate();
        }
    }

    @Override // androidx.cardview.widget.CardView
    public void setMaxCardElevation(float f) {
        super.setMaxCardElevation(f);
        this.cardViewHelper.updateInsets();
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    @Override // androidx.cardview.widget.CardView
    public void setPreventCornerOverlap(boolean z) {
        super.setPreventCornerOverlap(z);
        this.cardViewHelper.updateInsets();
        this.cardViewHelper.updateContentPadding();
    }

    public void setProgress(float f) {
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        materialCardViewHelper.bgDrawable.setInterpolation(f);
        MaterialShapeDrawable materialShapeDrawable = materialCardViewHelper.foregroundContentDrawable;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setInterpolation(f);
        }
        MaterialShapeDrawable materialShapeDrawable2 = materialCardViewHelper.foregroundShapeDrawable;
        if (materialShapeDrawable2 != null) {
            materialShapeDrawable2.setInterpolation(f);
        }
    }

    @Override // androidx.cardview.widget.CardView
    public void setRadius(float f) {
        super.setRadius(f);
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        materialCardViewHelper.setShapeAppearanceModel(materialCardViewHelper.shapeAppearanceModel.withCornerSize(f));
        materialCardViewHelper.fgDrawable.invalidateSelf();
        if (materialCardViewHelper.shouldAddCornerPaddingOutsideCardBackground() || materialCardViewHelper.shouldAddCornerPaddingInsideCardBackground()) {
            materialCardViewHelper.updateContentPadding();
        }
        if (materialCardViewHelper.shouldAddCornerPaddingOutsideCardBackground()) {
            materialCardViewHelper.updateInsets();
        }
    }

    public void setRippleColor(ColorStateList colorStateList) {
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        materialCardViewHelper.rippleColor = colorStateList;
        materialCardViewHelper.updateRippleColor();
    }

    public void setRippleColorResource(int i) {
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        materialCardViewHelper.rippleColor = AppCompatResources.getColorStateList(getContext(), i);
        materialCardViewHelper.updateRippleColor();
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        setClipToOutline(shapeAppearanceModel.isRoundRect(getBoundsAsRectF()));
        this.cardViewHelper.setShapeAppearanceModel(shapeAppearanceModel);
    }

    public void setStrokeColor(int i) {
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        ColorStateList valueOf = ColorStateList.valueOf(i);
        if (materialCardViewHelper.strokeColor != valueOf) {
            materialCardViewHelper.strokeColor = valueOf;
            materialCardViewHelper.updateStroke();
        }
    }

    public void setStrokeWidth(int i) {
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        if (i != materialCardViewHelper.strokeWidth) {
            materialCardViewHelper.strokeWidth = i;
            materialCardViewHelper.updateStroke();
        }
    }

    @Override // androidx.cardview.widget.CardView
    public void setUseCompatPadding(boolean z) {
        super.setUseCompatPadding(z);
        this.cardViewHelper.updateInsets();
        this.cardViewHelper.updateContentPadding();
    }

    @Override // android.widget.Checkable
    public void toggle() {
        if (isCheckable() && isEnabled()) {
            this.checked = !this.checked;
            refreshDrawableState();
            forceRippleRedrawIfNeeded();
            OnCheckedChangeListener onCheckedChangeListener = this.onCheckedChangeListener;
            if (onCheckedChangeListener != null) {
                onCheckedChangeListener.onCheckedChanged(this, this.checked);
            }
        }
    }

    public MaterialCardView(Context context, AttributeSet attributeSet, int i) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i, 2131886749), attributeSet, i);
        this.checked = false;
        this.dragged = false;
        this.isParentCardViewDoneInitializing = true;
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(getContext(), attributeSet, R$styleable.MaterialCardView, i, 2131886749, new int[0]);
        MaterialCardViewHelper materialCardViewHelper = new MaterialCardViewHelper(this, attributeSet, i, 2131886749);
        this.cardViewHelper = materialCardViewHelper;
        materialCardViewHelper.bgDrawable.setFillColor(super.getCardBackgroundColor());
        materialCardViewHelper.userContentPadding.set(super.getContentPaddingLeft(), super.getContentPaddingTop(), super.getContentPaddingRight(), super.getContentPaddingBottom());
        materialCardViewHelper.updateContentPadding();
        ColorStateList colorStateList = R$style.getColorStateList(materialCardViewHelper.materialCardView.getContext(), obtainStyledAttributes, 10);
        materialCardViewHelper.strokeColor = colorStateList;
        if (colorStateList == null) {
            materialCardViewHelper.strokeColor = ColorStateList.valueOf(-1);
        }
        materialCardViewHelper.strokeWidth = obtainStyledAttributes.getDimensionPixelSize(11, 0);
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        materialCardViewHelper.checkable = z;
        materialCardViewHelper.materialCardView.setLongClickable(z);
        materialCardViewHelper.checkedIconTint = R$style.getColorStateList(materialCardViewHelper.materialCardView.getContext(), obtainStyledAttributes, 5);
        materialCardViewHelper.setCheckedIcon(R$style.getDrawable(materialCardViewHelper.materialCardView.getContext(), obtainStyledAttributes, 2));
        materialCardViewHelper.checkedIconSize = obtainStyledAttributes.getDimensionPixelSize(4, 0);
        materialCardViewHelper.checkedIconMargin = obtainStyledAttributes.getDimensionPixelSize(3, 0);
        ColorStateList colorStateList2 = R$style.getColorStateList(materialCardViewHelper.materialCardView.getContext(), obtainStyledAttributes, 6);
        materialCardViewHelper.rippleColor = colorStateList2;
        if (colorStateList2 == null) {
            materialCardViewHelper.rippleColor = ColorStateList.valueOf(R$style.getColor(materialCardViewHelper.materialCardView, R.attr.colorControlHighlight));
        }
        ColorStateList colorStateList3 = R$style.getColorStateList(materialCardViewHelper.materialCardView.getContext(), obtainStyledAttributes, 1);
        materialCardViewHelper.foregroundContentDrawable.setFillColor(colorStateList3 == null ? ColorStateList.valueOf(0) : colorStateList3);
        materialCardViewHelper.updateRippleColor();
        materialCardViewHelper.bgDrawable.setElevation(materialCardViewHelper.materialCardView.getCardElevation());
        materialCardViewHelper.updateStroke();
        materialCardViewHelper.materialCardView.setBackgroundInternal(materialCardViewHelper.insetDrawable(materialCardViewHelper.bgDrawable));
        Drawable clickableForeground = materialCardViewHelper.materialCardView.isClickable() ? materialCardViewHelper.getClickableForeground() : materialCardViewHelper.foregroundContentDrawable;
        materialCardViewHelper.fgDrawable = clickableForeground;
        materialCardViewHelper.materialCardView.setForeground(materialCardViewHelper.insetDrawable(clickableForeground));
        obtainStyledAttributes.recycle();
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(ColorStateList colorStateList) {
        this.cardViewHelper.bgDrawable.setFillColor(colorStateList);
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        if (materialCardViewHelper.strokeColor != colorStateList) {
            materialCardViewHelper.strokeColor = colorStateList;
            materialCardViewHelper.updateStroke();
        }
    }
}
