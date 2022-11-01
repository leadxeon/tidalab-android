package com.google.android.material.floatingactionbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$styleable;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.DescendantOffsetUtils;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.AttachedBehavior {
    public static final /* synthetic */ int $r8$clinit = 0;
    public boolean animateShowBeforeLayout;

    static {
        new Property<View, Float>(Float.class, "width") { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.4
            @Override // android.util.Property
            public Float get(View view) {
                return Float.valueOf(view.getLayoutParams().width);
            }

            @Override // android.util.Property
            public void set(View view, Float f) {
                View view2 = view;
                view2.getLayoutParams().width = f.intValue();
                view2.requestLayout();
            }
        };
        new Property<View, Float>(Float.class, "height") { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.5
            @Override // android.util.Property
            public Float get(View view) {
                return Float.valueOf(view.getLayoutParams().height);
            }

            @Override // android.util.Property
            public void set(View view, Float f) {
                View view2 = view;
                view2.getLayoutParams().height = f.intValue();
                view2.requestLayout();
            }
        };
        new Property<View, Float>(Float.class, "paddingStart") { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.6
            @Override // android.util.Property
            public Float get(View view) {
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                return Float.valueOf(view.getPaddingStart());
            }

            @Override // android.util.Property
            public void set(View view, Float f) {
                View view2 = view;
                int intValue = f.intValue();
                int paddingTop = view2.getPaddingTop();
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                view2.setPaddingRelative(intValue, paddingTop, view2.getPaddingEnd(), view2.getPaddingBottom());
            }
        };
        new Property<View, Float>(Float.class, "paddingEnd") { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.7
            @Override // android.util.Property
            public Float get(View view) {
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                return Float.valueOf(view.getPaddingEnd());
            }

            @Override // android.util.Property
            public void set(View view, Float f) {
                View view2 = view;
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                view2.setPaddingRelative(view2.getPaddingStart(), view2.getPaddingTop(), f.intValue(), view2.getPaddingBottom());
            }
        };
    }

    public static void access$400(ExtendedFloatingActionButton extendedFloatingActionButton, MotionStrategy motionStrategy) {
        Objects.requireNonNull(extendedFloatingActionButton);
        throw null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    public CoordinatorLayout.Behavior<ExtendedFloatingActionButton> getBehavior() {
        return null;
    }

    public int getCollapsedPadding() {
        return (getCollapsedSize() - getIconSize()) / 2;
    }

    public int getCollapsedSize() {
        return 0;
    }

    public MotionSpec getExtendMotionSpec() {
        throw null;
    }

    public MotionSpec getHideMotionSpec() {
        throw null;
    }

    public MotionSpec getShowMotionSpec() {
        throw null;
    }

    public MotionSpec getShrinkMotionSpec() {
        throw null;
    }

    @Override // com.google.android.material.button.MaterialButton, android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void setAnimateShowBeforeLayout(boolean z) {
        this.animateShowBeforeLayout = z;
    }

    public void setExtendMotionSpec(MotionSpec motionSpec) {
        throw null;
    }

    public void setExtendMotionSpecResource(int i) {
        setExtendMotionSpec(MotionSpec.createFromResource(getContext(), i));
    }

    public void setExtended(boolean z) {
        if (z) {
            throw null;
        }
    }

    public void setHideMotionSpec(MotionSpec motionSpec) {
        throw null;
    }

    public void setHideMotionSpecResource(int i) {
        setHideMotionSpec(MotionSpec.createFromResource(getContext(), i));
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
    }

    @Override // android.widget.TextView, android.view.View
    public void setPaddingRelative(int i, int i2, int i3, int i4) {
        super.setPaddingRelative(i, i2, i3, i4);
    }

    public void setShowMotionSpec(MotionSpec motionSpec) {
        throw null;
    }

    public void setShowMotionSpecResource(int i) {
        setShowMotionSpec(MotionSpec.createFromResource(getContext(), i));
    }

    public void setShrinkMotionSpec(MotionSpec motionSpec) {
        throw null;
    }

    public void setShrinkMotionSpecResource(int i) {
        setShrinkMotionSpec(MotionSpec.createFromResource(getContext(), i));
    }

    @Override // android.widget.TextView
    public void setTextColor(int i) {
        super.setTextColor(i);
        getTextColors();
    }

    /* loaded from: classes.dex */
    public static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.Behavior<T> {
        public boolean autoHideEnabled;
        public boolean autoShrinkEnabled;
        public Rect tmpRect;

        public ExtendedFloatingActionButtonBehavior() {
            this.autoHideEnabled = false;
            this.autoShrinkEnabled = true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout, View view, Rect rect) {
            ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) view;
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
            if (layoutParams.dodgeInsetEdges == 0) {
                layoutParams.dodgeInsetEdges = 80;
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
            ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) view;
            if (view2 instanceof AppBarLayout) {
                updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view2, extendedFloatingActionButton);
            } else {
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                if (layoutParams instanceof CoordinatorLayout.LayoutParams ? ((CoordinatorLayout.LayoutParams) layoutParams).mBehavior instanceof BottomSheetBehavior : false) {
                    updateFabVisibilityForBottomSheet(view2, extendedFloatingActionButton);
                }
            }
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
            ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) view;
            List<View> dependencies = coordinatorLayout.getDependencies(extendedFloatingActionButton);
            int size = dependencies.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view2 = dependencies.get(i2);
                if (!(view2 instanceof AppBarLayout)) {
                    ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                    if ((layoutParams instanceof CoordinatorLayout.LayoutParams ? ((CoordinatorLayout.LayoutParams) layoutParams).mBehavior instanceof BottomSheetBehavior : false) && updateFabVisibilityForBottomSheet(view2, extendedFloatingActionButton)) {
                        break;
                    }
                } else if (updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view2, extendedFloatingActionButton)) {
                    break;
                }
            }
            coordinatorLayout.onLayoutChild(extendedFloatingActionButton, i);
            return true;
        }

        public final boolean shouldUpdateVisibility(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            return (this.autoHideEnabled || this.autoShrinkEnabled) && ((CoordinatorLayout.LayoutParams) extendedFloatingActionButton.getLayoutParams()).mAnchorId == view.getId();
        }

        public final boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!shouldUpdateVisibility(appBarLayout, extendedFloatingActionButton)) {
                return false;
            }
            if (this.tmpRect == null) {
                this.tmpRect = new Rect();
            }
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                if (this.autoShrinkEnabled) {
                    int i = ExtendedFloatingActionButton.$r8$clinit;
                    Objects.requireNonNull(extendedFloatingActionButton);
                } else {
                    int i2 = ExtendedFloatingActionButton.$r8$clinit;
                    Objects.requireNonNull(extendedFloatingActionButton);
                }
                ExtendedFloatingActionButton.access$400(extendedFloatingActionButton, null);
                return true;
            }
            if (this.autoShrinkEnabled) {
                int i3 = ExtendedFloatingActionButton.$r8$clinit;
                Objects.requireNonNull(extendedFloatingActionButton);
            } else {
                int i4 = ExtendedFloatingActionButton.$r8$clinit;
                Objects.requireNonNull(extendedFloatingActionButton);
            }
            ExtendedFloatingActionButton.access$400(extendedFloatingActionButton, null);
            return true;
        }

        public final boolean updateFabVisibilityForBottomSheet(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!shouldUpdateVisibility(view, extendedFloatingActionButton)) {
                return false;
            }
            if (view.getTop() < (extendedFloatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.LayoutParams) extendedFloatingActionButton.getLayoutParams())).topMargin) {
                if (this.autoShrinkEnabled) {
                    int i = ExtendedFloatingActionButton.$r8$clinit;
                } else {
                    int i2 = ExtendedFloatingActionButton.$r8$clinit;
                }
                ExtendedFloatingActionButton.access$400(extendedFloatingActionButton, null);
                return true;
            }
            if (this.autoShrinkEnabled) {
                int i3 = ExtendedFloatingActionButton.$r8$clinit;
            } else {
                int i4 = ExtendedFloatingActionButton.$r8$clinit;
            }
            ExtendedFloatingActionButton.access$400(extendedFloatingActionButton, null);
            return true;
        }

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.autoHideEnabled = obtainStyledAttributes.getBoolean(0, false);
            this.autoShrinkEnabled = obtainStyledAttributes.getBoolean(1, true);
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.widget.TextView
    public void setTextColor(ColorStateList colorStateList) {
        super.setTextColor(colorStateList);
        getTextColors();
    }
}
