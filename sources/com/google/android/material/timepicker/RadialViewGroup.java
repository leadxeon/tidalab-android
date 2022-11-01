package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$styleable;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RelativeCornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.tidalab.v2board.clash.foss.R;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class RadialViewGroup extends ConstraintLayout {
    public MaterialShapeDrawable background;
    public int radius;
    public final Runnable updateLayoutParametersRunnable;

    public RadialViewGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (view.getId() == -1) {
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            view.setId(View.generateViewId());
        }
        Handler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacks(this.updateLayoutParametersRunnable);
            handler.post(this.updateLayoutParametersRunnable);
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        updateLayoutParams();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        Handler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacks(this.updateLayoutParametersRunnable);
            handler.post(this.updateLayoutParametersRunnable);
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.background.setFillColor(ColorStateList.valueOf(i));
    }

    public void updateLayoutParams() {
        int childCount = getChildCount();
        int i = 1;
        for (int i2 = 0; i2 < childCount; i2++) {
            if ("skip".equals(getChildAt(i2).getTag())) {
                i++;
            }
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);
        float f = 0.0f;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getId() != R.id.circle_center && !"skip".equals(childAt.getTag())) {
                int id = childAt.getId();
                int i4 = this.radius;
                if (!constraintSet.mConstraints.containsKey(Integer.valueOf(id))) {
                    constraintSet.mConstraints.put(Integer.valueOf(id), new ConstraintSet.Constraint());
                }
                ConstraintSet.Layout layout = constraintSet.mConstraints.get(Integer.valueOf(id)).layout;
                layout.circleConstraint = R.id.circle_center;
                layout.circleRadius = i4;
                layout.circleAngle = f;
                f = (360.0f / (childCount - i)) + f;
            }
        }
        constraintSet.applyToInternal(this, true);
        setConstraintSet(null);
        requestLayout();
    }

    public RadialViewGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(R.layout.material_radial_view_group, this);
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        this.background = materialShapeDrawable;
        RelativeCornerSize relativeCornerSize = new RelativeCornerSize(0.5f);
        ShapeAppearanceModel shapeAppearanceModel = materialShapeDrawable.drawableState.shapeAppearanceModel;
        Objects.requireNonNull(shapeAppearanceModel);
        ShapeAppearanceModel.Builder builder = new ShapeAppearanceModel.Builder(shapeAppearanceModel);
        builder.topLeftCornerSize = relativeCornerSize;
        builder.topRightCornerSize = relativeCornerSize;
        builder.bottomRightCornerSize = relativeCornerSize;
        builder.bottomLeftCornerSize = relativeCornerSize;
        materialShapeDrawable.drawableState.shapeAppearanceModel = builder.build();
        materialShapeDrawable.invalidateSelf();
        this.background.setFillColor(ColorStateList.valueOf(-1));
        MaterialShapeDrawable materialShapeDrawable2 = this.background;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        setBackground(materialShapeDrawable2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RadialViewGroup, i, 0);
        this.radius = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.updateLayoutParametersRunnable = new Runnable() { // from class: com.google.android.material.timepicker.RadialViewGroup.1
            @Override // java.lang.Runnable
            public void run() {
                RadialViewGroup.this.updateLayoutParams();
            }
        };
        obtainStyledAttributes.recycle();
    }
}
