package com.google.android.material.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.tidalab.v2board.clash.foss.R;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class MaterialButton extends AppCompatButton implements Checkable, Shapeable {
    public static final int[] CHECKABLE_STATE_SET = {16842911};
    public static final int[] CHECKED_STATE_SET = {16842912};
    public Drawable icon;
    public int iconGravity;
    public int iconLeft;
    public int iconPadding;
    public int iconSize;
    public ColorStateList iconTint;
    public PorterDuff.Mode iconTintMode;
    public int iconTop;
    public final MaterialButtonHelper materialButtonHelper;
    public OnPressedChangeListener onPressedChangeListenerInternal;
    public final LinkedHashSet<OnCheckedChangeListener> onCheckedChangeListeners = new LinkedHashSet<>();
    public boolean checked = false;
    public boolean broadcasting = false;

    /* loaded from: classes.dex */
    public interface OnCheckedChangeListener {
        void onCheckedChanged(MaterialButton materialButton, boolean z);
    }

    /* loaded from: classes.dex */
    public interface OnPressedChangeListener {
    }

    /* loaded from: classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.button.MaterialButton.SavedState.1
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            public Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        };
        public boolean checked;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mSuperState, i);
            parcel.writeInt(this.checked ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            if (classLoader == null) {
                SavedState.class.getClassLoader();
            }
            this.checked = parcel.readInt() != 1 ? false : true;
        }
    }

    public MaterialButton(Context context, AttributeSet attributeSet) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, R.attr.materialButtonStyle, 2131886737), attributeSet, R.attr.materialButtonStyle);
        boolean z = false;
        Context context2 = getContext();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R$styleable.MaterialButton, R.attr.materialButtonStyle, 2131886737, new int[0]);
        this.iconPadding = obtainStyledAttributes.getDimensionPixelSize(12, 0);
        this.iconTintMode = R$style.parseTintMode(obtainStyledAttributes.getInt(15, -1), PorterDuff.Mode.SRC_IN);
        this.iconTint = R$style.getColorStateList(getContext(), obtainStyledAttributes, 14);
        this.icon = R$style.getDrawable(getContext(), obtainStyledAttributes, 10);
        this.iconGravity = obtainStyledAttributes.getInteger(11, 1);
        this.iconSize = obtainStyledAttributes.getDimensionPixelSize(13, 0);
        MaterialButtonHelper materialButtonHelper = new MaterialButtonHelper(this, ShapeAppearanceModel.builder(context2, attributeSet, R.attr.materialButtonStyle, 2131886737, new AbsoluteCornerSize(0)).build());
        this.materialButtonHelper = materialButtonHelper;
        materialButtonHelper.insetLeft = obtainStyledAttributes.getDimensionPixelOffset(1, 0);
        materialButtonHelper.insetRight = obtainStyledAttributes.getDimensionPixelOffset(2, 0);
        materialButtonHelper.insetTop = obtainStyledAttributes.getDimensionPixelOffset(3, 0);
        materialButtonHelper.insetBottom = obtainStyledAttributes.getDimensionPixelOffset(4, 0);
        if (obtainStyledAttributes.hasValue(8)) {
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(8, -1);
            materialButtonHelper.cornerRadius = dimensionPixelSize;
            materialButtonHelper.setShapeAppearanceModel(materialButtonHelper.shapeAppearanceModel.withCornerSize(dimensionPixelSize));
            materialButtonHelper.cornerRadiusSet = true;
        }
        materialButtonHelper.strokeWidth = obtainStyledAttributes.getDimensionPixelSize(20, 0);
        materialButtonHelper.backgroundTintMode = R$style.parseTintMode(obtainStyledAttributes.getInt(7, -1), PorterDuff.Mode.SRC_IN);
        materialButtonHelper.backgroundTint = R$style.getColorStateList(getContext(), obtainStyledAttributes, 6);
        materialButtonHelper.strokeColor = R$style.getColorStateList(getContext(), obtainStyledAttributes, 19);
        materialButtonHelper.rippleColor = R$style.getColorStateList(getContext(), obtainStyledAttributes, 16);
        materialButtonHelper.checkable = obtainStyledAttributes.getBoolean(5, false);
        materialButtonHelper.elevation = obtainStyledAttributes.getDimensionPixelSize(9, 0);
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        int paddingStart = getPaddingStart();
        int paddingTop = getPaddingTop();
        int paddingEnd = getPaddingEnd();
        int paddingBottom = getPaddingBottom();
        if (obtainStyledAttributes.hasValue(0)) {
            materialButtonHelper.backgroundOverwritten = true;
            setSupportBackgroundTintList(materialButtonHelper.backgroundTint);
            setSupportBackgroundTintMode(materialButtonHelper.backgroundTintMode);
        } else {
            materialButtonHelper.updateBackground();
        }
        setPaddingRelative(paddingStart + materialButtonHelper.insetLeft, paddingTop + materialButtonHelper.insetTop, paddingEnd + materialButtonHelper.insetRight, paddingBottom + materialButtonHelper.insetBottom);
        obtainStyledAttributes.recycle();
        setCompoundDrawablePadding(this.iconPadding);
        updateIcon(this.icon != null ? true : z);
    }

    private String getA11yClassName() {
        return (isCheckable() ? CompoundButton.class : Button.class).getName();
    }

    private int getTextHeight() {
        TextPaint paint = getPaint();
        String charSequence = getText().toString();
        if (getTransformationMethod() != null) {
            charSequence = getTransformationMethod().getTransformation(charSequence, this).toString();
        }
        Rect rect = new Rect();
        paint.getTextBounds(charSequence, 0, charSequence.length(), rect);
        return Math.min(rect.height(), getLayout().getHeight());
    }

    private int getTextWidth() {
        TextPaint paint = getPaint();
        String charSequence = getText().toString();
        if (getTransformationMethod() != null) {
            charSequence = getTransformationMethod().getTransformation(charSequence, this).toString();
        }
        return Math.min((int) paint.measureText(charSequence), getLayout().getEllipsizedWidth());
    }

    @Override // android.view.View
    public ColorStateList getBackgroundTintList() {
        return getSupportBackgroundTintList();
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return getSupportBackgroundTintMode();
    }

    public int getCornerRadius() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.cornerRadius;
        }
        return 0;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public int getIconGravity() {
        return this.iconGravity;
    }

    public int getIconPadding() {
        return this.iconPadding;
    }

    public int getIconSize() {
        return this.iconSize;
    }

    public ColorStateList getIconTint() {
        return this.iconTint;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.iconTintMode;
    }

    public int getInsetBottom() {
        return this.materialButtonHelper.insetBottom;
    }

    public int getInsetTop() {
        return this.materialButtonHelper.insetTop;
    }

    public ColorStateList getRippleColor() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.rippleColor;
        }
        return null;
    }

    public ShapeAppearanceModel getShapeAppearanceModel() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.shapeAppearanceModel;
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public ColorStateList getStrokeColor() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.strokeColor;
        }
        return null;
    }

    public int getStrokeWidth() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.strokeWidth;
        }
        return 0;
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public ColorStateList getSupportBackgroundTintList() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.backgroundTint;
        }
        return super.getSupportBackgroundTintList();
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.backgroundTintMode;
        }
        return super.getSupportBackgroundTintMode();
    }

    public boolean isCheckable() {
        MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
        return materialButtonHelper != null && materialButtonHelper.checkable;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.checked;
    }

    public final boolean isIconEnd() {
        int i = this.iconGravity;
        return i == 3 || i == 4;
    }

    public final boolean isIconStart() {
        int i = this.iconGravity;
        return i == 1 || i == 2;
    }

    public final boolean isIconTop() {
        int i = this.iconGravity;
        return i == 16 || i == 32;
    }

    public final boolean isUsingOriginalBackground() {
        MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
        return materialButtonHelper != null && !materialButtonHelper.backgroundOverwritten;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isUsingOriginalBackground()) {
            R$style.setParentAbsoluteElevation(this, this.materialButtonHelper.getMaterialShapeDrawable());
        }
    }

    @Override // android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (isCheckable()) {
            Button.mergeDrawableStates(onCreateDrawableState, CHECKABLE_STATE_SET);
        }
        if (isChecked()) {
            Button.mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(getA11yClassName());
        accessibilityEvent.setChecked(isChecked());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getA11yClassName());
        accessibilityNodeInfo.setCheckable(isCheckable());
        accessibilityNodeInfo.setChecked(isChecked());
        accessibilityNodeInfo.setClickable(isClickable());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        MaterialButtonHelper materialButtonHelper;
        super.onLayout(z, i, i2, i3, i4);
        if (Build.VERSION.SDK_INT == 21 && (materialButtonHelper = this.materialButtonHelper) != null) {
            int i5 = i4 - i2;
            int i6 = i3 - i;
            Drawable drawable = materialButtonHelper.maskDrawable;
            if (drawable != null) {
                drawable.setBounds(materialButtonHelper.insetLeft, materialButtonHelper.insetTop, i6 - materialButtonHelper.insetRight, i5 - materialButtonHelper.insetBottom);
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.mSuperState);
        setChecked(savedState.checked);
    }

    @Override // android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.checked = this.checked;
        return savedState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        updateIconPosition(i, i2);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        updateIconPosition(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public boolean performClick() {
        toggle();
        return super.performClick();
    }

    public final void resetIconDrawable() {
        if (isIconStart()) {
            setCompoundDrawablesRelative(this.icon, null, null, null);
        } else if (isIconEnd()) {
            setCompoundDrawablesRelative(null, null, this.icon, null);
        } else if (isIconTop()) {
            setCompoundDrawablesRelative(null, this.icon, null, null);
        }
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (isUsingOriginalBackground()) {
            MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
            if (materialButtonHelper.getMaterialShapeDrawable() != null) {
                materialButtonHelper.getMaterialShapeDrawable().setTint(i);
                return;
            }
            return;
        }
        super.setBackgroundColor(i);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (!isUsingOriginalBackground()) {
            super.setBackgroundDrawable(drawable);
        } else if (drawable != getBackground()) {
            Log.w("MaterialButton", "MaterialButton manages its own background to control elevation, shape, color and states. Consider using backgroundTint, shapeAppearance and other attributes where available. A custom background will ignore these attributes and you should consider handling interaction states such as pressed, focused and disabled");
            MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
            materialButtonHelper.backgroundOverwritten = true;
            materialButtonHelper.materialButton.setSupportBackgroundTintList(materialButtonHelper.backgroundTint);
            materialButtonHelper.materialButton.setSupportBackgroundTintMode(materialButtonHelper.backgroundTintMode);
            super.setBackgroundDrawable(drawable);
        } else {
            getBackground().setState(drawable.getState());
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundResource(int i) {
        setBackgroundDrawable(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : null);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        setSupportBackgroundTintList(colorStateList);
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        setSupportBackgroundTintMode(mode);
    }

    public void setCheckable(boolean z) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.checkable = z;
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (isCheckable() && isEnabled() && this.checked != z) {
            this.checked = z;
            refreshDrawableState();
            if (!this.broadcasting) {
                this.broadcasting = true;
                Iterator<OnCheckedChangeListener> it = this.onCheckedChangeListeners.iterator();
                while (it.hasNext()) {
                    it.next().onCheckedChanged(this, this.checked);
                }
                this.broadcasting = false;
            }
        }
    }

    public void setCornerRadius(int i) {
        if (isUsingOriginalBackground()) {
            MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
            if (!materialButtonHelper.cornerRadiusSet || materialButtonHelper.cornerRadius != i) {
                materialButtonHelper.cornerRadius = i;
                materialButtonHelper.cornerRadiusSet = true;
                materialButtonHelper.setShapeAppearanceModel(materialButtonHelper.shapeAppearanceModel.withCornerSize(i));
            }
        }
    }

    public void setCornerRadiusResource(int i) {
        if (isUsingOriginalBackground()) {
            setCornerRadius(getResources().getDimensionPixelSize(i));
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        if (isUsingOriginalBackground()) {
            MaterialShapeDrawable materialShapeDrawable = this.materialButtonHelper.getMaterialShapeDrawable();
            MaterialShapeDrawable.MaterialShapeDrawableState materialShapeDrawableState = materialShapeDrawable.drawableState;
            if (materialShapeDrawableState.elevation != f) {
                materialShapeDrawableState.elevation = f;
                materialShapeDrawable.updateZ();
            }
        }
    }

    public void setIcon(Drawable drawable) {
        if (this.icon != drawable) {
            this.icon = drawable;
            updateIcon(true);
            updateIconPosition(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconGravity(int i) {
        if (this.iconGravity != i) {
            this.iconGravity = i;
            updateIconPosition(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconPadding(int i) {
        if (this.iconPadding != i) {
            this.iconPadding = i;
            setCompoundDrawablePadding(i);
        }
    }

    public void setIconResource(int i) {
        setIcon(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : null);
    }

    public void setIconSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        } else if (this.iconSize != i) {
            this.iconSize = i;
            updateIcon(true);
        }
    }

    public void setIconTint(ColorStateList colorStateList) {
        if (this.iconTint != colorStateList) {
            this.iconTint = colorStateList;
            updateIcon(false);
        }
    }

    public void setIconTintMode(PorterDuff.Mode mode) {
        if (this.iconTintMode != mode) {
            this.iconTintMode = mode;
            updateIcon(false);
        }
    }

    public void setIconTintResource(int i) {
        setIconTint(AppCompatResources.getColorStateList(getContext(), i));
    }

    public void setInsetBottom(int i) {
        MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
        materialButtonHelper.setVerticalInsets(materialButtonHelper.insetTop, i);
    }

    public void setInsetTop(int i) {
        MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
        materialButtonHelper.setVerticalInsets(i, materialButtonHelper.insetBottom);
    }

    public void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public void setOnPressedChangeListenerInternal(OnPressedChangeListener onPressedChangeListener) {
        this.onPressedChangeListenerInternal = onPressedChangeListener;
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        OnPressedChangeListener onPressedChangeListener = this.onPressedChangeListenerInternal;
        if (onPressedChangeListener != null) {
            MaterialButtonToggleGroup.this.invalidate();
        }
        super.setPressed(z);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (isUsingOriginalBackground()) {
            MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
            if (materialButtonHelper.rippleColor != colorStateList) {
                materialButtonHelper.rippleColor = colorStateList;
                if (materialButtonHelper.materialButton.getBackground() instanceof RippleDrawable) {
                    ((RippleDrawable) materialButtonHelper.materialButton.getBackground()).setColor(RippleUtils.sanitizeRippleDrawableColor(colorStateList));
                }
            }
        }
    }

    public void setRippleColorResource(int i) {
        if (isUsingOriginalBackground()) {
            setRippleColor(AppCompatResources.getColorStateList(getContext(), i));
        }
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setShapeAppearanceModel(shapeAppearanceModel);
            return;
        }
        throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
    }

    public void setShouldDrawSurfaceColorStroke(boolean z) {
        if (isUsingOriginalBackground()) {
            MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
            materialButtonHelper.shouldDrawSurfaceColorStroke = z;
            materialButtonHelper.updateStroke();
        }
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        if (isUsingOriginalBackground()) {
            MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
            if (materialButtonHelper.strokeColor != colorStateList) {
                materialButtonHelper.strokeColor = colorStateList;
                materialButtonHelper.updateStroke();
            }
        }
    }

    public void setStrokeColorResource(int i) {
        if (isUsingOriginalBackground()) {
            setStrokeColor(AppCompatResources.getColorStateList(getContext(), i));
        }
    }

    public void setStrokeWidth(int i) {
        if (isUsingOriginalBackground()) {
            MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
            if (materialButtonHelper.strokeWidth != i) {
                materialButtonHelper.strokeWidth = i;
                materialButtonHelper.updateStroke();
            }
        }
    }

    public void setStrokeWidthResource(int i) {
        if (isUsingOriginalBackground()) {
            setStrokeWidth(getResources().getDimensionPixelSize(i));
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (isUsingOriginalBackground()) {
            MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
            if (materialButtonHelper.backgroundTint != colorStateList) {
                materialButtonHelper.backgroundTint = colorStateList;
                if (materialButtonHelper.getMaterialShapeDrawable() != null) {
                    materialButtonHelper.getMaterialShapeDrawable().setTintList(materialButtonHelper.backgroundTint);
                    return;
                }
                return;
            }
            return;
        }
        super.setSupportBackgroundTintList(colorStateList);
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (isUsingOriginalBackground()) {
            MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
            if (materialButtonHelper.backgroundTintMode != mode) {
                materialButtonHelper.backgroundTintMode = mode;
                if (materialButtonHelper.getMaterialShapeDrawable() != null && materialButtonHelper.backgroundTintMode != null) {
                    materialButtonHelper.getMaterialShapeDrawable().setTintMode(materialButtonHelper.backgroundTintMode);
                    return;
                }
                return;
            }
            return;
        }
        super.setSupportBackgroundTintMode(mode);
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.checked);
    }

    public final void updateIcon(boolean z) {
        Drawable drawable = this.icon;
        if (drawable != null) {
            Drawable mutate = AppOpsManagerCompat.wrap(drawable).mutate();
            this.icon = mutate;
            mutate.setTintList(this.iconTint);
            PorterDuff.Mode mode = this.iconTintMode;
            if (mode != null) {
                this.icon.setTintMode(mode);
            }
            int i = this.iconSize;
            if (i == 0) {
                i = this.icon.getIntrinsicWidth();
            }
            int i2 = this.iconSize;
            if (i2 == 0) {
                i2 = this.icon.getIntrinsicHeight();
            }
            Drawable drawable2 = this.icon;
            int i3 = this.iconLeft;
            int i4 = this.iconTop;
            drawable2.setBounds(i3, i4, i + i3, i2 + i4);
        }
        if (z) {
            resetIconDrawable();
            return;
        }
        Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
        boolean z2 = false;
        Drawable drawable3 = compoundDrawablesRelative[0];
        Drawable drawable4 = compoundDrawablesRelative[1];
        Drawable drawable5 = compoundDrawablesRelative[2];
        if ((isIconStart() && drawable3 != this.icon) || ((isIconEnd() && drawable5 != this.icon) || (isIconTop() && drawable4 != this.icon))) {
            z2 = true;
        }
        if (z2) {
            resetIconDrawable();
        }
    }

    public final void updateIconPosition(int i, int i2) {
        if (this.icon != null && getLayout() != null) {
            if (isIconStart() || isIconEnd()) {
                this.iconTop = 0;
                int i3 = this.iconGravity;
                boolean z = true;
                if (i3 == 1 || i3 == 3) {
                    this.iconLeft = 0;
                    updateIcon(false);
                    return;
                }
                int i4 = this.iconSize;
                if (i4 == 0) {
                    i4 = this.icon.getIntrinsicWidth();
                }
                int textWidth = i - getTextWidth();
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                int paddingEnd = ((((textWidth - getPaddingEnd()) - i4) - this.iconPadding) - getPaddingStart()) / 2;
                boolean z2 = getLayoutDirection() == 1;
                if (this.iconGravity != 4) {
                    z = false;
                }
                if (z2 != z) {
                    paddingEnd = -paddingEnd;
                }
                if (this.iconLeft != paddingEnd) {
                    this.iconLeft = paddingEnd;
                    updateIcon(false);
                }
            } else if (isIconTop()) {
                this.iconLeft = 0;
                if (this.iconGravity == 16) {
                    this.iconTop = 0;
                    updateIcon(false);
                    return;
                }
                int i5 = this.iconSize;
                if (i5 == 0) {
                    i5 = this.icon.getIntrinsicHeight();
                }
                int textHeight = (((((i2 - getTextHeight()) - getPaddingTop()) - i5) - this.iconPadding) - getPaddingBottom()) / 2;
                if (this.iconTop != textHeight) {
                    this.iconTop = textHeight;
                    updateIcon(false);
                }
            }
        }
    }
}
