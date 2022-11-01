package com.google.android.material.bottomappbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R$style;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tidalab.v2board.clash.foss.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class BottomAppBar extends Toolbar implements CoordinatorLayout.AttachedBehavior {
    public static final /* synthetic */ int $r8$clinit = 0;
    public int animatingModeChangeCounter;
    public Behavior behavior;
    public int fabAlignmentMode;
    public int fabAnimationMode;
    public boolean fabAttached;
    public boolean hideOnScroll;
    public Animator menuAnimator;
    public Animator modeAnimator;
    public int pendingMenuResId;

    /* loaded from: classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.bottomappbar.BottomAppBar.SavedState.1
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
        public int fabAlignmentMode;
        public boolean fabAttached;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mSuperState, i);
            parcel.writeInt(this.fabAlignmentMode);
            parcel.writeInt(this.fabAttached ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.fabAlignmentMode = parcel.readInt();
            this.fabAttached = parcel.readInt() != 0;
        }
    }

    public static void access$1600(BottomAppBar bottomAppBar) {
        bottomAppBar.animatingModeChangeCounter--;
    }

    private ActionMenuView getActionMenuView() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof ActionMenuView) {
                return (ActionMenuView) childAt;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getBottomInset() {
        return 0;
    }

    private float getFabTranslationY() {
        return -getTopEdgeTreatment().cradleVerticalOffset;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getLeftInset() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRightInset() {
        return 0;
    }

    private BottomAppBarTopEdgeTreatment getTopEdgeTreatment() {
        throw null;
    }

    public final FloatingActionButton findDependentFab() {
        View findDependentView = findDependentView();
        if (findDependentView instanceof FloatingActionButton) {
            return (FloatingActionButton) findDependentView;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View findDependentView() {
        /*
            r4 = this;
            android.view.ViewParent r0 = r4.getParent()
            boolean r0 = r0 instanceof androidx.coordinatorlayout.widget.CoordinatorLayout
            r1 = 0
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            android.view.ViewParent r0 = r4.getParent()
            androidx.coordinatorlayout.widget.CoordinatorLayout r0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r0
            java.util.List r0 = r0.getDependents(r4)
            java.util.Iterator r0 = r0.iterator()
        L_0x0018:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x002d
            java.lang.Object r2 = r0.next()
            android.view.View r2 = (android.view.View) r2
            boolean r3 = r2 instanceof com.google.android.material.floatingactionbutton.FloatingActionButton
            if (r3 != 0) goto L_0x002c
            boolean r3 = r2 instanceof com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
            if (r3 == 0) goto L_0x0018
        L_0x002c:
            return r2
        L_0x002d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomappbar.BottomAppBar.findDependentView():android.view.View");
    }

    public int getActionMenuViewTranslationX(ActionMenuView actionMenuView, int i, boolean z) {
        if (i != 1 || !z) {
            return 0;
        }
        boolean isLayoutRtl = R$style.isLayoutRtl(this);
        int measuredWidth = isLayoutRtl ? getMeasuredWidth() : 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if ((childAt.getLayoutParams() instanceof Toolbar.LayoutParams) && (((Toolbar.LayoutParams) childAt.getLayoutParams()).gravity & 8388615) == 8388611) {
                if (isLayoutRtl) {
                    measuredWidth = Math.min(measuredWidth, childAt.getLeft());
                } else {
                    measuredWidth = Math.max(measuredWidth, childAt.getRight());
                }
            }
        }
        return measuredWidth - ((isLayoutRtl ? actionMenuView.getRight() : actionMenuView.getLeft()) + 0);
    }

    public ColorStateList getBackgroundTint() {
        throw null;
    }

    public float getCradleVerticalOffset() {
        return getTopEdgeTreatment().cradleVerticalOffset;
    }

    public int getFabAlignmentMode() {
        return this.fabAlignmentMode;
    }

    public int getFabAnimationMode() {
        return this.fabAnimationMode;
    }

    public float getFabCradleMargin() {
        return getTopEdgeTreatment().fabMargin;
    }

    public float getFabCradleRoundedCornerRadius() {
        return getTopEdgeTreatment().roundedCornerRadius;
    }

    public final float getFabTranslationX(int i) {
        boolean isLayoutRtl = R$style.isLayoutRtl(this);
        int i2 = 1;
        if (i != 1) {
            return 0.0f;
        }
        int measuredWidth = (getMeasuredWidth() / 2) + 0;
        if (isLayoutRtl) {
            i2 = -1;
        }
        return measuredWidth * i2;
    }

    public boolean getHideOnScroll() {
        return this.hideOnScroll;
    }

    public final boolean isFabVisibleOrWillBeShown() {
        FloatingActionButton findDependentFab = findDependentFab();
        return findDependentFab != null && findDependentFab.isOrWillBeShown();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        R$style.setParentAbsoluteElevation(this, null);
        throw null;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            Animator animator = this.menuAnimator;
            if (animator != null) {
                animator.cancel();
            }
            Animator animator2 = this.modeAnimator;
            if (animator2 != null) {
                animator2.cancel();
            }
            setCutoutState();
            throw null;
        }
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView != null && this.menuAnimator == null) {
            actionMenuView.setAlpha(1.0f);
            if (!isFabVisibleOrWillBeShown()) {
                actionMenuView.setTranslationX(getActionMenuViewTranslationX(actionMenuView, 0, false));
            } else {
                actionMenuView.setTranslationX(getActionMenuViewTranslationX(actionMenuView, this.fabAlignmentMode, this.fabAttached));
            }
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.mSuperState);
        this.fabAlignmentMode = savedState.fabAlignmentMode;
        this.fabAttached = savedState.fabAttached;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.fabAlignmentMode = this.fabAlignmentMode;
        savedState.fabAttached = this.fabAttached;
        return savedState;
    }

    public void setBackgroundTint(ColorStateList colorStateList) {
        throw null;
    }

    public void setCradleVerticalOffset(float f) {
        if (f != getCradleVerticalOffset()) {
            BottomAppBarTopEdgeTreatment topEdgeTreatment = getTopEdgeTreatment();
            Objects.requireNonNull(topEdgeTreatment);
            if (f >= 0.0f) {
                topEdgeTreatment.cradleVerticalOffset = f;
                throw null;
            }
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
    }

    public final void setCutoutState() {
        getTopEdgeTreatment().horizontalOffset = getFabTranslationX();
        findDependentView();
        if (this.fabAttached) {
            isFabVisibleOrWillBeShown();
        }
        throw null;
    }

    @Override // android.view.View
    public void setElevation(float f) {
        throw null;
    }

    public void setFabAlignmentMode(final int i) {
        final int i2;
        this.pendingMenuResId = 0;
        final boolean z = this.fabAttached;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        if (!isLaidOut()) {
            int i3 = this.pendingMenuResId;
            if (i3 != 0) {
                this.pendingMenuResId = 0;
                getMenu().clear();
                inflateMenu(i3);
            }
        } else {
            Animator animator = this.menuAnimator;
            if (animator != null) {
                animator.cancel();
            }
            ArrayList arrayList = new ArrayList();
            if (!isFabVisibleOrWillBeShown()) {
                z = false;
                i2 = 0;
            } else {
                i2 = i;
            }
            final ActionMenuView actionMenuView = getActionMenuView();
            if (actionMenuView != null) {
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(actionMenuView, "alpha", 1.0f);
                if (Math.abs(actionMenuView.getTranslationX() - getActionMenuViewTranslationX(actionMenuView, i2, z)) > 1.0f) {
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(actionMenuView, "alpha", 0.0f);
                    ofFloat2.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.bottomappbar.BottomAppBar.7
                        public boolean cancelled;

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationCancel(Animator animator2) {
                            this.cancelled = true;
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator2) {
                            if (!this.cancelled) {
                                BottomAppBar bottomAppBar = BottomAppBar.this;
                                int i4 = bottomAppBar.pendingMenuResId;
                                boolean z2 = i4 != 0;
                                if (i4 != 0) {
                                    bottomAppBar.pendingMenuResId = 0;
                                    bottomAppBar.getMenu().clear();
                                    bottomAppBar.inflateMenu(i4);
                                }
                                final BottomAppBar bottomAppBar2 = BottomAppBar.this;
                                final ActionMenuView actionMenuView2 = actionMenuView;
                                final int i5 = i2;
                                final boolean z3 = z;
                                Objects.requireNonNull(bottomAppBar2);
                                Runnable runnable = new Runnable() { // from class: com.google.android.material.bottomappbar.BottomAppBar.8
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        ActionMenuView actionMenuView3 = actionMenuView2;
                                        actionMenuView3.setTranslationX(BottomAppBar.this.getActionMenuViewTranslationX(actionMenuView3, i5, z3));
                                    }
                                };
                                if (z2) {
                                    actionMenuView2.post(runnable);
                                } else {
                                    runnable.run();
                                }
                            }
                        }
                    });
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(150L);
                    animatorSet.playSequentially(ofFloat2, ofFloat);
                    arrayList.add(animatorSet);
                } else if (actionMenuView.getAlpha() < 1.0f) {
                    arrayList.add(ofFloat);
                }
            }
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.playTogether(arrayList);
            this.menuAnimator = animatorSet2;
            animatorSet2.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.bottomappbar.BottomAppBar.6
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator2) {
                    BottomAppBar.access$1600(BottomAppBar.this);
                    Objects.requireNonNull(BottomAppBar.this);
                    BottomAppBar.this.menuAnimator = null;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator2) {
                    BottomAppBar.this.animatingModeChangeCounter++;
                }
            });
            this.menuAnimator.start();
        }
        if (this.fabAlignmentMode != i && isLaidOut()) {
            Animator animator2 = this.modeAnimator;
            if (animator2 != null) {
                animator2.cancel();
            }
            ArrayList arrayList2 = new ArrayList();
            if (this.fabAnimationMode == 1) {
                ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(findDependentFab(), "translationX", getFabTranslationX(i));
                ofFloat3.setDuration(300L);
                arrayList2.add(ofFloat3);
            } else {
                FloatingActionButton findDependentFab = findDependentFab();
                if (findDependentFab != null && !findDependentFab.isOrWillBeHidden()) {
                    this.animatingModeChangeCounter++;
                    findDependentFab.hide(new FloatingActionButton.OnVisibilityChangedListener() { // from class: com.google.android.material.bottomappbar.BottomAppBar.5
                        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.OnVisibilityChangedListener
                        public void onHidden(FloatingActionButton floatingActionButton) {
                            floatingActionButton.setTranslationX(BottomAppBar.this.getFabTranslationX(i));
                            floatingActionButton.show(new FloatingActionButton.OnVisibilityChangedListener() { // from class: com.google.android.material.bottomappbar.BottomAppBar.5.1
                                @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.OnVisibilityChangedListener
                                public void onShown(FloatingActionButton floatingActionButton2) {
                                    BottomAppBar.access$1600(BottomAppBar.this);
                                }
                            }, true);
                        }
                    }, true);
                }
            }
            AnimatorSet animatorSet3 = new AnimatorSet();
            animatorSet3.playTogether(arrayList2);
            this.modeAnimator = animatorSet3;
            animatorSet3.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.bottomappbar.BottomAppBar.4
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator3) {
                    BottomAppBar.access$1600(BottomAppBar.this);
                    BottomAppBar.this.modeAnimator = null;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator3) {
                    BottomAppBar.this.animatingModeChangeCounter++;
                }
            });
            this.modeAnimator.start();
        }
        this.fabAlignmentMode = i;
    }

    public void setFabAnimationMode(int i) {
        this.fabAnimationMode = i;
    }

    public void setFabCradleMargin(float f) {
        if (f != getFabCradleMargin()) {
            getTopEdgeTreatment().fabMargin = f;
            throw null;
        }
    }

    public void setFabCradleRoundedCornerRadius(float f) {
        if (f != getFabCradleRoundedCornerRadius()) {
            getTopEdgeTreatment().roundedCornerRadius = f;
            throw null;
        }
    }

    public boolean setFabDiameter(int i) {
        float f = i;
        if (f == getTopEdgeTreatment().fabDiameter) {
            return false;
        }
        getTopEdgeTreatment().fabDiameter = f;
        throw null;
    }

    public void setHideOnScroll(boolean z) {
        this.hideOnScroll = z;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence charSequence) {
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence) {
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    public Behavior getBehavior() {
        if (this.behavior == null) {
            this.behavior = new Behavior();
        }
        return this.behavior;
    }

    /* loaded from: classes.dex */
    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {
        public int originalBottomMargin;
        public WeakReference<BottomAppBar> viewRef;
        public final View.OnLayoutChangeListener fabLayoutListener = new View.OnLayoutChangeListener() { // from class: com.google.android.material.bottomappbar.BottomAppBar.Behavior.1
            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                BottomAppBar bottomAppBar = Behavior.this.viewRef.get();
                if (bottomAppBar == null || !(view instanceof FloatingActionButton)) {
                    view.removeOnLayoutChangeListener(this);
                    return;
                }
                FloatingActionButton floatingActionButton = (FloatingActionButton) view;
                Rect rect = Behavior.this.fabContentRect;
                rect.set(0, 0, floatingActionButton.getMeasuredWidth(), floatingActionButton.getMeasuredHeight());
                floatingActionButton.offsetRectWithShadow(rect);
                int height = Behavior.this.fabContentRect.height();
                bottomAppBar.setFabDiameter(height);
                CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
                if (Behavior.this.originalBottomMargin == 0) {
                    ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = bottomAppBar.getBottomInset() + (bottomAppBar.getResources().getDimensionPixelOffset(R.dimen.mtrl_bottomappbar_fab_bottom_margin) - ((floatingActionButton.getMeasuredHeight() - height) / 2));
                    ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = bottomAppBar.getLeftInset();
                    ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = bottomAppBar.getRightInset();
                    if (R$style.isLayoutRtl(floatingActionButton)) {
                        ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin += 0;
                    } else {
                        ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin += 0;
                    }
                }
            }
        };
        public final Rect fabContentRect = new Rect();

        public Behavior() {
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
            final BottomAppBar bottomAppBar = (BottomAppBar) view;
            this.viewRef = new WeakReference<>(bottomAppBar);
            int i2 = BottomAppBar.$r8$clinit;
            View findDependentView = bottomAppBar.findDependentView();
            if (findDependentView != null) {
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                if (!findDependentView.isLaidOut()) {
                    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) findDependentView.getLayoutParams();
                    layoutParams.anchorGravity = 49;
                    this.originalBottomMargin = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                    if (findDependentView instanceof FloatingActionButton) {
                        FloatingActionButton floatingActionButton = (FloatingActionButton) findDependentView;
                        floatingActionButton.addOnLayoutChangeListener(this.fabLayoutListener);
                        floatingActionButton.addOnHideAnimationListener(null);
                        floatingActionButton.addOnShowAnimationListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.bottomappbar.BottomAppBar.9
                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public void onAnimationStart(Animator animator) {
                                Objects.requireNonNull(BottomAppBar.this);
                                throw null;
                            }
                        });
                        floatingActionButton.addTransformationCallback(null);
                    }
                    bottomAppBar.setCutoutState();
                    throw null;
                }
            }
            coordinatorLayout.onLayoutChild(bottomAppBar, i);
            this.height = bottomAppBar.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) bottomAppBar.getLayoutParams()).bottomMargin;
            return false;
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
            if (((BottomAppBar) view).getHideOnScroll()) {
                if (i == 2) {
                    return true;
                }
            }
            return false;
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    private float getFabTranslationX() {
        return getFabTranslationX(this.fabAlignmentMode);
    }
}
