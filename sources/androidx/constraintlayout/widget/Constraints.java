package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.Objects;
/* loaded from: classes.dex */
public class Constraints extends ViewGroup {
    public ConstraintSet myConstraintSet;

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public ConstraintSet getConstraintSet() {
        if (this.myConstraintSet == null) {
            this.myConstraintSet = new ConstraintSet();
        }
        ConstraintSet constraintSet = this.myConstraintSet;
        Objects.requireNonNull(constraintSet);
        int childCount = getChildCount();
        constraintSet.mConstraints.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (!constraintSet.mForceId || id != -1) {
                if (!constraintSet.mConstraints.containsKey(Integer.valueOf(id))) {
                    constraintSet.mConstraints.put(Integer.valueOf(id), new ConstraintSet.Constraint());
                }
                ConstraintSet.Constraint constraint = constraintSet.mConstraints.get(Integer.valueOf(id));
                if (childAt instanceof ConstraintHelper) {
                    ConstraintHelper constraintHelper = (ConstraintHelper) childAt;
                    constraint.fillFromConstraints(id, layoutParams);
                    if (constraintHelper instanceof Barrier) {
                        ConstraintSet.Layout layout = constraint.layout;
                        layout.mHelperType = 1;
                        Barrier barrier = (Barrier) constraintHelper;
                        layout.mBarrierDirection = barrier.getType();
                        constraint.layout.mReferenceIds = barrier.getReferencedIds();
                        constraint.layout.mBarrierMargin = barrier.getMargin();
                    }
                }
                constraint.fillFromConstraints(id, layoutParams);
            } else {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
        }
        return this.myConstraintSet;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ConstraintLayout.LayoutParams(layoutParams);
    }

    /* loaded from: classes.dex */
    public static class LayoutParams extends ConstraintLayout.LayoutParams {
        public float alpha;
        public boolean applyElevation;
        public float elevation;
        public float rotation;
        public float rotationX;
        public float rotationY;
        public float scaleX;
        public float scaleY;
        public float transformPivotX;
        public float transformPivotY;
        public float translationX;
        public float translationY;
        public float translationZ;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.alpha = 1.0f;
            this.applyElevation = false;
            this.elevation = 0.0f;
            this.rotation = 0.0f;
            this.rotationX = 0.0f;
            this.rotationY = 0.0f;
            this.scaleX = 1.0f;
            this.scaleY = 1.0f;
            this.transformPivotX = 0.0f;
            this.transformPivotY = 0.0f;
            this.translationX = 0.0f;
            this.translationY = 0.0f;
            this.translationZ = 0.0f;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.alpha = 1.0f;
            this.applyElevation = false;
            this.elevation = 0.0f;
            this.rotation = 0.0f;
            this.rotationX = 0.0f;
            this.rotationY = 0.0f;
            this.scaleX = 1.0f;
            this.scaleY = 1.0f;
            this.transformPivotX = 0.0f;
            this.transformPivotY = 0.0f;
            this.translationX = 0.0f;
            this.translationY = 0.0f;
            this.translationZ = 0.0f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ConstraintSet);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == 15) {
                    this.alpha = obtainStyledAttributes.getFloat(index, this.alpha);
                } else if (index == 28) {
                    this.elevation = obtainStyledAttributes.getFloat(index, this.elevation);
                    this.applyElevation = true;
                } else if (index == 23) {
                    this.rotationX = obtainStyledAttributes.getFloat(index, this.rotationX);
                } else if (index == 24) {
                    this.rotationY = obtainStyledAttributes.getFloat(index, this.rotationY);
                } else if (index == 22) {
                    this.rotation = obtainStyledAttributes.getFloat(index, this.rotation);
                } else if (index == 20) {
                    this.scaleX = obtainStyledAttributes.getFloat(index, this.scaleX);
                } else if (index == 21) {
                    this.scaleY = obtainStyledAttributes.getFloat(index, this.scaleY);
                } else if (index == 16) {
                    this.transformPivotX = obtainStyledAttributes.getFloat(index, this.transformPivotX);
                } else if (index == 17) {
                    this.transformPivotY = obtainStyledAttributes.getFloat(index, this.transformPivotY);
                } else if (index == 18) {
                    this.translationX = obtainStyledAttributes.getFloat(index, this.translationX);
                } else if (index == 19) {
                    this.translationY = obtainStyledAttributes.getFloat(index, this.translationY);
                } else if (index == 27) {
                    this.translationZ = obtainStyledAttributes.getFloat(index, this.translationZ);
                }
            }
        }
    }
}
