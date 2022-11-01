package com.google.android.material.card;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import androidx.cardview.widget.CardView;
import androidx.cardview.widget.CardViewApi21Impl;
import androidx.core.app.AppOpsManagerCompat;
import com.google.android.material.R$styleable;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.CornerTreatment;
import com.google.android.material.shape.CutCornerTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RoundedCornerTreatment;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.tidalab.v2board.clash.foss.R;
import java.util.Objects;
/* loaded from: classes.dex */
public class MaterialCardViewHelper {
    public static final int[] CHECKED_STATE_SET = {16842912};
    public static final double COS_45 = Math.cos(Math.toRadians(45.0d));
    public final MaterialShapeDrawable bgDrawable;
    public boolean checkable;
    public Drawable checkedIcon;
    public int checkedIconMargin;
    public int checkedIconSize;
    public ColorStateList checkedIconTint;
    public LayerDrawable clickableForegroundDrawable;
    public MaterialShapeDrawable compatRippleDrawable;
    public Drawable fgDrawable;
    public final MaterialShapeDrawable foregroundContentDrawable;
    public MaterialShapeDrawable foregroundShapeDrawable;
    public final MaterialCardView materialCardView;
    public ColorStateList rippleColor;
    public Drawable rippleDrawable;
    public ShapeAppearanceModel shapeAppearanceModel;
    public ColorStateList strokeColor;
    public int strokeWidth;
    public final Rect userContentPadding = new Rect();
    public boolean isBackgroundOverwritten = false;

    public MaterialCardViewHelper(MaterialCardView materialCardView, AttributeSet attributeSet, int i, int i2) {
        this.materialCardView = materialCardView;
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(materialCardView.getContext(), attributeSet, i, i2);
        this.bgDrawable = materialShapeDrawable;
        materialShapeDrawable.initializeElevationOverlay(materialCardView.getContext());
        materialShapeDrawable.setShadowColor(-12303292);
        ShapeAppearanceModel shapeAppearanceModel = materialShapeDrawable.drawableState.shapeAppearanceModel;
        Objects.requireNonNull(shapeAppearanceModel);
        ShapeAppearanceModel.Builder builder = new ShapeAppearanceModel.Builder(shapeAppearanceModel);
        TypedArray obtainStyledAttributes = materialCardView.getContext().obtainStyledAttributes(attributeSet, R$styleable.CardView, i, R.style.CardView);
        if (obtainStyledAttributes.hasValue(3)) {
            builder.setAllCornerSizes(obtainStyledAttributes.getDimension(3, 0.0f));
        }
        this.foregroundContentDrawable = new MaterialShapeDrawable();
        setShapeAppearanceModel(builder.build());
        obtainStyledAttributes.recycle();
    }

    public final float calculateActualCornerPadding() {
        float calculateCornerPaddingForCornerTreatment = calculateCornerPaddingForCornerTreatment(this.shapeAppearanceModel.topLeftCorner, this.bgDrawable.getTopLeftCornerResolvedSize());
        CornerTreatment cornerTreatment = this.shapeAppearanceModel.topRightCorner;
        MaterialShapeDrawable materialShapeDrawable = this.bgDrawable;
        float max = Math.max(calculateCornerPaddingForCornerTreatment, calculateCornerPaddingForCornerTreatment(cornerTreatment, materialShapeDrawable.drawableState.shapeAppearanceModel.topRightCornerSize.getCornerSize(materialShapeDrawable.getBoundsAsRectF())));
        CornerTreatment cornerTreatment2 = this.shapeAppearanceModel.bottomRightCorner;
        MaterialShapeDrawable materialShapeDrawable2 = this.bgDrawable;
        float calculateCornerPaddingForCornerTreatment2 = calculateCornerPaddingForCornerTreatment(cornerTreatment2, materialShapeDrawable2.drawableState.shapeAppearanceModel.bottomRightCornerSize.getCornerSize(materialShapeDrawable2.getBoundsAsRectF()));
        CornerTreatment cornerTreatment3 = this.shapeAppearanceModel.bottomLeftCorner;
        MaterialShapeDrawable materialShapeDrawable3 = this.bgDrawable;
        return Math.max(max, Math.max(calculateCornerPaddingForCornerTreatment2, calculateCornerPaddingForCornerTreatment(cornerTreatment3, materialShapeDrawable3.drawableState.shapeAppearanceModel.bottomLeftCornerSize.getCornerSize(materialShapeDrawable3.getBoundsAsRectF()))));
    }

    public final float calculateCornerPaddingForCornerTreatment(CornerTreatment cornerTreatment, float f) {
        if (cornerTreatment instanceof RoundedCornerTreatment) {
            return (float) ((1.0d - COS_45) * f);
        }
        if (cornerTreatment instanceof CutCornerTreatment) {
            return f / 2.0f;
        }
        return 0.0f;
    }

    public final float calculateHorizontalBackgroundPadding() {
        return this.materialCardView.getMaxCardElevation() + (shouldAddCornerPaddingOutsideCardBackground() ? calculateActualCornerPadding() : 0.0f);
    }

    public final float calculateVerticalBackgroundPadding() {
        return (this.materialCardView.getMaxCardElevation() * 1.5f) + (shouldAddCornerPaddingOutsideCardBackground() ? calculateActualCornerPadding() : 0.0f);
    }

    public final Drawable getClickableForeground() {
        if (this.rippleDrawable == null) {
            int[] iArr = RippleUtils.PRESSED_STATE_SET;
            this.foregroundShapeDrawable = new MaterialShapeDrawable(this.shapeAppearanceModel);
            this.rippleDrawable = new RippleDrawable(this.rippleColor, null, this.foregroundShapeDrawable);
        }
        if (this.clickableForegroundDrawable == null) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            Drawable drawable = this.checkedIcon;
            if (drawable != null) {
                stateListDrawable.addState(CHECKED_STATE_SET, drawable);
            }
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.rippleDrawable, this.foregroundContentDrawable, stateListDrawable});
            this.clickableForegroundDrawable = layerDrawable;
            layerDrawable.setId(2, R.id.mtrl_card_checked_layer_id);
        }
        return this.clickableForegroundDrawable;
    }

    public final Drawable insetDrawable(Drawable drawable) {
        int i;
        int i2;
        if (this.materialCardView.getUseCompatPadding()) {
            i = (int) Math.ceil(calculateVerticalBackgroundPadding());
            i2 = (int) Math.ceil(calculateHorizontalBackgroundPadding());
        } else {
            i2 = 0;
            i = 0;
        }
        return new InsetDrawable(this, drawable, i2, i, i2, i) { // from class: com.google.android.material.card.MaterialCardViewHelper.1
            @Override // android.graphics.drawable.Drawable
            public int getMinimumHeight() {
                return -1;
            }

            @Override // android.graphics.drawable.Drawable
            public int getMinimumWidth() {
                return -1;
            }

            @Override // android.graphics.drawable.InsetDrawable, android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
            public boolean getPadding(Rect rect) {
                return false;
            }
        };
    }

    public void setCheckedIcon(Drawable drawable) {
        this.checkedIcon = drawable;
        if (drawable != null) {
            Drawable wrap = AppOpsManagerCompat.wrap(drawable.mutate());
            this.checkedIcon = wrap;
            wrap.setTintList(this.checkedIconTint);
        }
        if (this.clickableForegroundDrawable != null) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            Drawable drawable2 = this.checkedIcon;
            if (drawable2 != null) {
                stateListDrawable.addState(CHECKED_STATE_SET, drawable2);
            }
            this.clickableForegroundDrawable.setDrawableByLayerId(R.id.mtrl_card_checked_layer_id, stateListDrawable);
        }
    }

    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        this.shapeAppearanceModel = shapeAppearanceModel;
        MaterialShapeDrawable materialShapeDrawable = this.bgDrawable;
        materialShapeDrawable.drawableState.shapeAppearanceModel = shapeAppearanceModel;
        materialShapeDrawable.invalidateSelf();
        MaterialShapeDrawable materialShapeDrawable2 = this.bgDrawable;
        materialShapeDrawable2.shadowBitmapDrawingEnable = !materialShapeDrawable2.isRoundRect();
        MaterialShapeDrawable materialShapeDrawable3 = this.foregroundContentDrawable;
        if (materialShapeDrawable3 != null) {
            materialShapeDrawable3.drawableState.shapeAppearanceModel = shapeAppearanceModel;
            materialShapeDrawable3.invalidateSelf();
        }
        MaterialShapeDrawable materialShapeDrawable4 = this.foregroundShapeDrawable;
        if (materialShapeDrawable4 != null) {
            materialShapeDrawable4.drawableState.shapeAppearanceModel = shapeAppearanceModel;
            materialShapeDrawable4.invalidateSelf();
        }
        MaterialShapeDrawable materialShapeDrawable5 = this.compatRippleDrawable;
        if (materialShapeDrawable5 != null) {
            materialShapeDrawable5.drawableState.shapeAppearanceModel = shapeAppearanceModel;
            materialShapeDrawable5.invalidateSelf();
        }
    }

    public final boolean shouldAddCornerPaddingInsideCardBackground() {
        return this.materialCardView.getPreventCornerOverlap() && !this.bgDrawable.isRoundRect();
    }

    public final boolean shouldAddCornerPaddingOutsideCardBackground() {
        return this.materialCardView.getPreventCornerOverlap() && this.bgDrawable.isRoundRect() && this.materialCardView.getUseCompatPadding();
    }

    public void updateContentPadding() {
        float f = 0.0f;
        float calculateActualCornerPadding = shouldAddCornerPaddingInsideCardBackground() || shouldAddCornerPaddingOutsideCardBackground() ? calculateActualCornerPadding() : 0.0f;
        if (this.materialCardView.getPreventCornerOverlap() && this.materialCardView.getUseCompatPadding()) {
            f = (float) ((1.0d - COS_45) * this.materialCardView.getCardViewRadius());
        }
        int i = (int) (calculateActualCornerPadding - f);
        MaterialCardView materialCardView = this.materialCardView;
        Rect rect = this.userContentPadding;
        materialCardView.mContentPadding.set(rect.left + i, rect.top + i, rect.right + i, rect.bottom + i);
        ((CardViewApi21Impl) CardView.IMPL).updatePadding(materialCardView.mCardViewDelegate);
    }

    public void updateInsets() {
        if (!this.isBackgroundOverwritten) {
            this.materialCardView.setBackgroundInternal(insetDrawable(this.bgDrawable));
        }
        this.materialCardView.setForeground(insetDrawable(this.fgDrawable));
    }

    public final void updateRippleColor() {
        int[] iArr = RippleUtils.PRESSED_STATE_SET;
        Drawable drawable = this.rippleDrawable;
        if (drawable != null) {
            ((RippleDrawable) drawable).setColor(this.rippleColor);
            return;
        }
        MaterialShapeDrawable materialShapeDrawable = this.compatRippleDrawable;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setFillColor(this.rippleColor);
        }
    }

    public void updateStroke() {
        this.foregroundContentDrawable.setStroke(this.strokeWidth, this.strokeColor);
    }
}
