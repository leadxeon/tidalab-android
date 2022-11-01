package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.widget.TextView;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public final class CalendarItemStyle {
    public final ColorStateList backgroundColor;
    public final Rect insets;
    public final ShapeAppearanceModel itemShape;
    public final ColorStateList strokeColor;
    public final int strokeWidth;
    public final ColorStateList textColor;

    public CalendarItemStyle(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i, ShapeAppearanceModel shapeAppearanceModel, Rect rect) {
        AppOpsManagerCompat.checkArgumentNonnegative(rect.left);
        AppOpsManagerCompat.checkArgumentNonnegative(rect.top);
        AppOpsManagerCompat.checkArgumentNonnegative(rect.right);
        AppOpsManagerCompat.checkArgumentNonnegative(rect.bottom);
        this.insets = rect;
        this.textColor = colorStateList2;
        this.backgroundColor = colorStateList;
        this.strokeColor = colorStateList3;
        this.strokeWidth = i;
        this.itemShape = shapeAppearanceModel;
    }

    public static CalendarItemStyle create(Context context, int i) {
        if (i != 0) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R$styleable.MaterialCalendarItem);
            Rect rect = new Rect(obtainStyledAttributes.getDimensionPixelOffset(0, 0), obtainStyledAttributes.getDimensionPixelOffset(2, 0), obtainStyledAttributes.getDimensionPixelOffset(1, 0), obtainStyledAttributes.getDimensionPixelOffset(3, 0));
            ColorStateList colorStateList = R$style.getColorStateList(context, obtainStyledAttributes, 4);
            ColorStateList colorStateList2 = R$style.getColorStateList(context, obtainStyledAttributes, 9);
            ColorStateList colorStateList3 = R$style.getColorStateList(context, obtainStyledAttributes, 7);
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(8, 0);
            ShapeAppearanceModel build = ShapeAppearanceModel.builder(context, obtainStyledAttributes.getResourceId(5, 0), obtainStyledAttributes.getResourceId(6, 0), new AbsoluteCornerSize(0)).build();
            obtainStyledAttributes.recycle();
            return new CalendarItemStyle(colorStateList, colorStateList2, colorStateList3, dimensionPixelSize, build, rect);
        }
        throw new IllegalArgumentException("Cannot create a CalendarItemStyle with a styleResId of 0");
    }

    public void styleItem(TextView textView) {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable();
        materialShapeDrawable.setShapeAppearanceModel(this.itemShape);
        materialShapeDrawable2.setShapeAppearanceModel(this.itemShape);
        materialShapeDrawable.setFillColor(this.backgroundColor);
        materialShapeDrawable.setStroke(this.strokeWidth, this.strokeColor);
        textView.setTextColor(this.textColor);
        RippleDrawable rippleDrawable = new RippleDrawable(this.textColor.withAlpha(30), materialShapeDrawable, materialShapeDrawable2);
        Rect rect = this.insets;
        InsetDrawable insetDrawable = new InsetDrawable((Drawable) rippleDrawable, rect.left, rect.top, rect.right, rect.bottom);
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        textView.setBackground(insetDrawable);
    }
}
