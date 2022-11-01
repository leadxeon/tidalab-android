package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatImageHelper;
import androidx.collection.SimpleArrayMap;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.animation.TransformationCallback;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.expandable.ExpandableWidget;
import com.google.android.material.expandable.ExpandableWidgetHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButtonImpl;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.VisibilityAwareImageButton;
import com.google.android.material.shadow.ShadowViewDelegate;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.stateful.ExtendableSavedState;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.tidalab.v2board.clash.foss.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class FloatingActionButton extends VisibilityAwareImageButton implements ExpandableWidget, Shapeable, CoordinatorLayout.AttachedBehavior {
    public ColorStateList backgroundTint;
    public PorterDuff.Mode backgroundTintMode;
    public int borderWidth;
    public boolean compatPadding;
    public int customSize;
    public final AppCompatImageHelper imageHelper;
    public PorterDuff.Mode imageMode;
    public int imagePadding;
    public ColorStateList imageTint;
    public FloatingActionButtonImpl impl;
    public int maxImageSize;
    public ColorStateList rippleColor;
    public int size;
    public final Rect shadowPadding = new Rect();
    public final Rect touchArea = new Rect();
    public final ExpandableWidgetHelper expandableWidgetHelper = new ExpandableWidgetHelper(this);

    /* renamed from: com.google.android.material.floatingactionbutton.FloatingActionButton$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements FloatingActionButtonImpl.InternalVisibilityChangedListener {
        public final /* synthetic */ OnVisibilityChangedListener val$listener;

        public AnonymousClass1(OnVisibilityChangedListener onVisibilityChangedListener) {
            this.val$listener = onVisibilityChangedListener;
        }
    }

    /* loaded from: classes.dex */
    public static class BaseBehavior<T extends FloatingActionButton> extends CoordinatorLayout.Behavior<T> {
        public boolean autoHideEnabled;
        public Rect tmpRect;

        public BaseBehavior() {
            this.autoHideEnabled = true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout, View view, Rect rect) {
            return getInsetDodgeRect((FloatingActionButton) view, rect);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
            if (layoutParams.dodgeInsetEdges == 0) {
                layoutParams.dodgeInsetEdges = 80;
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
            FloatingActionButton floatingActionButton = (FloatingActionButton) view;
            if (view2 instanceof AppBarLayout) {
                updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view2, floatingActionButton);
            } else {
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                if (layoutParams instanceof CoordinatorLayout.LayoutParams ? ((CoordinatorLayout.LayoutParams) layoutParams).mBehavior instanceof BottomSheetBehavior : false) {
                    updateFabVisibilityForBottomSheet(view2, floatingActionButton);
                }
            }
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
            int i2;
            FloatingActionButton floatingActionButton = (FloatingActionButton) view;
            List<View> dependencies = coordinatorLayout.getDependencies(floatingActionButton);
            int size = dependencies.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                View view2 = dependencies.get(i4);
                if (!(view2 instanceof AppBarLayout)) {
                    ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                    if ((layoutParams instanceof CoordinatorLayout.LayoutParams ? ((CoordinatorLayout.LayoutParams) layoutParams).mBehavior instanceof BottomSheetBehavior : false) && updateFabVisibilityForBottomSheet(view2, floatingActionButton)) {
                        break;
                    }
                } else if (updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view2, floatingActionButton)) {
                    break;
                }
            }
            coordinatorLayout.onLayoutChild(floatingActionButton, i);
            Rect rect = floatingActionButton.shadowPadding;
            if (rect == null || rect.centerX() <= 0 || rect.centerY() <= 0) {
                return true;
            }
            CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) floatingActionButton.getLayoutParams();
            if (floatingActionButton.getRight() >= coordinatorLayout.getWidth() - ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin) {
                i2 = rect.right;
            } else {
                i2 = floatingActionButton.getLeft() <= ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin ? -rect.left : 0;
            }
            if (floatingActionButton.getBottom() >= coordinatorLayout.getHeight() - ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin) {
                i3 = rect.bottom;
            } else if (floatingActionButton.getTop() <= ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin) {
                i3 = -rect.top;
            }
            if (i3 != 0) {
                ViewCompat.offsetTopAndBottom(floatingActionButton, i3);
            }
            if (i2 == 0) {
                return true;
            }
            ViewCompat.offsetLeftAndRight(floatingActionButton, i2);
            return true;
        }

        public final boolean shouldUpdateVisibility(View view, FloatingActionButton floatingActionButton) {
            return this.autoHideEnabled && ((CoordinatorLayout.LayoutParams) floatingActionButton.getLayoutParams()).mAnchorId == view.getId() && floatingActionButton.getUserSetVisibility() == 0;
        }

        public final boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, FloatingActionButton floatingActionButton) {
            if (!shouldUpdateVisibility(appBarLayout, floatingActionButton)) {
                return false;
            }
            if (this.tmpRect == null) {
                this.tmpRect = new Rect();
            }
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                floatingActionButton.hide(null, false);
                return true;
            }
            floatingActionButton.show(null, false);
            return true;
        }

        public final boolean updateFabVisibilityForBottomSheet(View view, FloatingActionButton floatingActionButton) {
            if (!shouldUpdateVisibility(view, floatingActionButton)) {
                return false;
            }
            if (view.getTop() < (floatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.LayoutParams) floatingActionButton.getLayoutParams())).topMargin) {
                floatingActionButton.hide(null, false);
                return true;
            }
            floatingActionButton.show(null, false);
            return true;
        }

        public boolean getInsetDodgeRect(FloatingActionButton floatingActionButton, Rect rect) {
            Rect rect2 = floatingActionButton.shadowPadding;
            rect.set(floatingActionButton.getLeft() + rect2.left, floatingActionButton.getTop() + rect2.top, floatingActionButton.getRight() - rect2.right, floatingActionButton.getBottom() - rect2.bottom);
            return true;
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.FloatingActionButton_Behavior_Layout);
            this.autoHideEnabled = obtainStyledAttributes.getBoolean(0, true);
            obtainStyledAttributes.recycle();
        }
    }

    /* loaded from: classes.dex */
    public static class Behavior extends BaseBehavior<FloatingActionButton> {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class OnVisibilityChangedListener {
        public void onHidden(FloatingActionButton floatingActionButton) {
        }

        public void onShown(FloatingActionButton floatingActionButton) {
        }
    }

    /* loaded from: classes.dex */
    public class ShadowDelegateImpl implements ShadowViewDelegate {
        public ShadowDelegateImpl() {
        }
    }

    /* loaded from: classes.dex */
    public class TransformationCallbackWrapper<T extends FloatingActionButton> implements FloatingActionButtonImpl.InternalTransformationCallback {
        public final TransformationCallback<T> listener;

        public TransformationCallbackWrapper(TransformationCallback<T> transformationCallback) {
            this.listener = transformationCallback;
        }

        public boolean equals(Object obj) {
            return (obj instanceof TransformationCallbackWrapper) && ((TransformationCallbackWrapper) obj).listener.equals(this.listener);
        }

        public int hashCode() {
            return this.listener.hashCode();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.InternalTransformationCallback
        public void onScaleChanged() {
            this.listener.onScaleChanged(FloatingActionButton.this);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.InternalTransformationCallback
        public void onTranslationChanged() {
            this.listener.onTranslationChanged(FloatingActionButton.this);
        }
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, R.attr.floatingActionButtonStyle, 2131886710), attributeSet, R.attr.floatingActionButtonStyle);
        Context context2 = getContext();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R$styleable.FloatingActionButton, R.attr.floatingActionButtonStyle, 2131886710, new int[0]);
        this.backgroundTint = R$style.getColorStateList(context2, obtainStyledAttributes, 1);
        this.backgroundTintMode = R$style.parseTintMode(obtainStyledAttributes.getInt(2, -1), null);
        this.rippleColor = R$style.getColorStateList(context2, obtainStyledAttributes, 12);
        this.size = obtainStyledAttributes.getInt(7, -1);
        this.customSize = obtainStyledAttributes.getDimensionPixelSize(6, 0);
        this.borderWidth = obtainStyledAttributes.getDimensionPixelSize(3, 0);
        float dimension = obtainStyledAttributes.getDimension(4, 0.0f);
        float dimension2 = obtainStyledAttributes.getDimension(9, 0.0f);
        float dimension3 = obtainStyledAttributes.getDimension(11, 0.0f);
        this.compatPadding = obtainStyledAttributes.getBoolean(16, false);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.mtrl_fab_min_touch_target);
        this.maxImageSize = obtainStyledAttributes.getDimensionPixelSize(10, 0);
        MotionSpec createFromAttribute = MotionSpec.createFromAttribute(context2, obtainStyledAttributes, 15);
        MotionSpec createFromAttribute2 = MotionSpec.createFromAttribute(context2, obtainStyledAttributes, 8);
        ShapeAppearanceModel build = ShapeAppearanceModel.builder(context2, attributeSet, R.attr.floatingActionButtonStyle, 2131886710, ShapeAppearanceModel.PILL).build();
        boolean z = obtainStyledAttributes.getBoolean(5, false);
        setEnabled(obtainStyledAttributes.getBoolean(0, true));
        obtainStyledAttributes.recycle();
        AppCompatImageHelper appCompatImageHelper = new AppCompatImageHelper(this);
        this.imageHelper = appCompatImageHelper;
        appCompatImageHelper.loadFromAttributes(attributeSet, R.attr.floatingActionButtonStyle);
        getImpl().setShapeAppearance(build);
        getImpl().initializeBackgroundDrawable(this.backgroundTint, this.backgroundTintMode, this.rippleColor, this.borderWidth);
        getImpl().minTouchTargetSize = dimensionPixelSize;
        FloatingActionButtonImpl impl = getImpl();
        if (impl.elevation != dimension) {
            impl.elevation = dimension;
            impl.onElevationsChanged(dimension, impl.hoveredFocusedTranslationZ, impl.pressedTranslationZ);
        }
        FloatingActionButtonImpl impl2 = getImpl();
        if (impl2.hoveredFocusedTranslationZ != dimension2) {
            impl2.hoveredFocusedTranslationZ = dimension2;
            impl2.onElevationsChanged(impl2.elevation, dimension2, impl2.pressedTranslationZ);
        }
        FloatingActionButtonImpl impl3 = getImpl();
        if (impl3.pressedTranslationZ != dimension3) {
            impl3.pressedTranslationZ = dimension3;
            impl3.onElevationsChanged(impl3.elevation, impl3.hoveredFocusedTranslationZ, dimension3);
        }
        FloatingActionButtonImpl impl4 = getImpl();
        int i = this.maxImageSize;
        if (impl4.maxImageSize != i) {
            impl4.maxImageSize = i;
            impl4.setImageMatrixScale(impl4.imageMatrixScale);
        }
        getImpl().showMotionSpec = createFromAttribute;
        getImpl().hideMotionSpec = createFromAttribute2;
        getImpl().ensureMinTouchTargetSize = z;
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    private FloatingActionButtonImpl getImpl() {
        if (this.impl == null) {
            this.impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        return this.impl;
    }

    public static int resolveAdjustedSize(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            return Math.min(i, size);
        }
        if (mode == 0) {
            return i;
        }
        if (mode == 1073741824) {
            return size;
        }
        throw new IllegalArgumentException();
    }

    public void addOnHideAnimationListener(Animator.AnimatorListener animatorListener) {
        FloatingActionButtonImpl impl = getImpl();
        if (impl.hideListeners == null) {
            impl.hideListeners = new ArrayList<>();
        }
        impl.hideListeners.add(null);
    }

    public void addOnShowAnimationListener(Animator.AnimatorListener animatorListener) {
        FloatingActionButtonImpl impl = getImpl();
        if (impl.showListeners == null) {
            impl.showListeners = new ArrayList<>();
        }
        impl.showListeners.add(animatorListener);
    }

    public void addTransformationCallback(TransformationCallback<? extends FloatingActionButton> transformationCallback) {
        FloatingActionButtonImpl impl = getImpl();
        TransformationCallbackWrapper transformationCallbackWrapper = new TransformationCallbackWrapper(null);
        if (impl.transformationCallbacks == null) {
            impl.transformationCallbacks = new ArrayList<>();
        }
        impl.transformationCallbacks.add(transformationCallbackWrapper);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        getImpl().onDrawableStateChanged(getDrawableState());
    }

    @Override // android.view.View
    public ColorStateList getBackgroundTintList() {
        return this.backgroundTint;
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return this.backgroundTintMode;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    public CoordinatorLayout.Behavior<FloatingActionButton> getBehavior() {
        return new Behavior();
    }

    public float getCompatElevation() {
        return getImpl().getElevation();
    }

    public float getCompatHoveredFocusedTranslationZ() {
        return getImpl().hoveredFocusedTranslationZ;
    }

    public float getCompatPressedTranslationZ() {
        return getImpl().pressedTranslationZ;
    }

    public Drawable getContentBackground() {
        return getImpl().contentBackground;
    }

    @Deprecated
    public boolean getContentRect(Rect rect) {
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        if (!isLaidOut()) {
            return false;
        }
        rect.set(0, 0, getWidth(), getHeight());
        offsetRectWithShadow(rect);
        return true;
    }

    public int getCustomSize() {
        return this.customSize;
    }

    public int getExpandedComponentIdHint() {
        return this.expandableWidgetHelper.expandedComponentIdHint;
    }

    public MotionSpec getHideMotionSpec() {
        return getImpl().hideMotionSpec;
    }

    @Deprecated
    public int getRippleColor() {
        ColorStateList colorStateList = this.rippleColor;
        if (colorStateList != null) {
            return colorStateList.getDefaultColor();
        }
        return 0;
    }

    public ColorStateList getRippleColorStateList() {
        return this.rippleColor;
    }

    public ShapeAppearanceModel getShapeAppearanceModel() {
        ShapeAppearanceModel shapeAppearanceModel = getImpl().shapeAppearance;
        Objects.requireNonNull(shapeAppearanceModel);
        return shapeAppearanceModel;
    }

    public MotionSpec getShowMotionSpec() {
        return getImpl().showMotionSpec;
    }

    public int getSize() {
        return this.size;
    }

    public int getSizeDimension() {
        return getSizeDimension(this.size);
    }

    public ColorStateList getSupportBackgroundTintList() {
        return getBackgroundTintList();
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return getBackgroundTintMode();
    }

    public ColorStateList getSupportImageTintList() {
        return this.imageTint;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        return this.imageMode;
    }

    public boolean getUseCompatPadding() {
        return this.compatPadding;
    }

    public void hide(OnVisibilityChangedListener onVisibilityChangedListener, final boolean z) {
        final FloatingActionButtonImpl impl = getImpl();
        final AnonymousClass1 r4 = onVisibilityChangedListener == null ? null : new AnonymousClass1(onVisibilityChangedListener);
        if (!impl.isOrWillBeHidden()) {
            Animator animator = impl.currentAnimator;
            if (animator != null) {
                animator.cancel();
            }
            if (impl.shouldAnimateVisibilityChange()) {
                MotionSpec motionSpec = impl.hideMotionSpec;
                if (motionSpec == null) {
                    if (impl.defaultHideMotionSpec == null) {
                        impl.defaultHideMotionSpec = MotionSpec.createFromResource(impl.view.getContext(), R.animator.design_fab_hide_motion_spec);
                    }
                    motionSpec = impl.defaultHideMotionSpec;
                    Objects.requireNonNull(motionSpec);
                }
                AnimatorSet createAnimator = impl.createAnimator(motionSpec, 0.0f, 0.0f, 0.0f);
                createAnimator.addListener(
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0049: INVOKE  
                      (r1v8 'createAnimator' android.animation.AnimatorSet)
                      (wrap: android.animation.AnimatorListenerAdapter : 0x0046: CONSTRUCTOR  (r2v1 android.animation.AnimatorListenerAdapter A[REMOVE]) = 
                      (r0v0 'impl' com.google.android.material.floatingactionbutton.FloatingActionButtonImpl A[DONT_INLINE])
                      (r5v0 'z' boolean A[DONT_INLINE])
                      (r4v1 'r4' com.google.android.material.floatingactionbutton.FloatingActionButton$1 A[DONT_INLINE])
                     call: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.1.<init>(com.google.android.material.floatingactionbutton.FloatingActionButtonImpl, boolean, com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$InternalVisibilityChangedListener):void type: CONSTRUCTOR)
                     type: VIRTUAL call: android.animation.AnimatorSet.addListener(android.animation.Animator$AnimatorListener):void in method: com.google.android.material.floatingactionbutton.FloatingActionButton.hide(com.google.android.material.floatingactionbutton.FloatingActionButton$OnVisibilityChangedListener, boolean):void, file: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:278)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:241)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:90)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:79)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:122)
                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:79)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:122)
                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:267)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:260)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:369)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:304)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:270)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl, state: NOT_LOADED
                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:299)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:676)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:386)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:140)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:116)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:996)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:807)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:390)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:271)
                    	... 31 more
                    */
                /*
                    this = this;
                    com.google.android.material.floatingactionbutton.FloatingActionButtonImpl r0 = r3.getImpl()
                    if (r4 != 0) goto L_0x0008
                    r4 = 0
                    goto L_0x000e
                L_0x0008:
                    com.google.android.material.floatingactionbutton.FloatingActionButton$1 r1 = new com.google.android.material.floatingactionbutton.FloatingActionButton$1
                    r1.<init>(r4)
                    r4 = r1
                L_0x000e:
                    boolean r1 = r0.isOrWillBeHidden()
                    if (r1 == 0) goto L_0x0015
                    goto L_0x007c
                L_0x0015:
                    android.animation.Animator r1 = r0.currentAnimator
                    if (r1 == 0) goto L_0x001c
                    r1.cancel()
                L_0x001c:
                    boolean r1 = r0.shouldAnimateVisibilityChange()
                    if (r1 == 0) goto L_0x0068
                    com.google.android.material.animation.MotionSpec r1 = r0.hideMotionSpec
                    if (r1 == 0) goto L_0x0027
                    goto L_0x003f
                L_0x0027:
                    com.google.android.material.animation.MotionSpec r1 = r0.defaultHideMotionSpec
                    if (r1 != 0) goto L_0x003a
                    com.google.android.material.floatingactionbutton.FloatingActionButton r1 = r0.view
                    android.content.Context r1 = r1.getContext()
                    r2 = 2130837505(0x7f020001, float:1.7279966E38)
                    com.google.android.material.animation.MotionSpec r1 = com.google.android.material.animation.MotionSpec.createFromResource(r1, r2)
                    r0.defaultHideMotionSpec = r1
                L_0x003a:
                    com.google.android.material.animation.MotionSpec r1 = r0.defaultHideMotionSpec
                    java.util.Objects.requireNonNull(r1)
                L_0x003f:
                    r2 = 0
                    android.animation.AnimatorSet r1 = r0.createAnimator(r1, r2, r2, r2)
                    com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$1 r2 = new com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$1
                    r2.<init>(r0, r5, r4)
                    r1.addListener(r2)
                    java.util.ArrayList<android.animation.Animator$AnimatorListener> r4 = r0.hideListeners
                    if (r4 == 0) goto L_0x0064
                    java.util.Iterator r4 = r4.iterator()
                L_0x0054:
                    boolean r5 = r4.hasNext()
                    if (r5 == 0) goto L_0x0064
                    java.lang.Object r5 = r4.next()
                    android.animation.Animator$AnimatorListener r5 = (android.animation.Animator.AnimatorListener) r5
                    r1.addListener(r5)
                    goto L_0x0054
                L_0x0064:
                    r1.start()
                    goto L_0x007c
                L_0x0068:
                    com.google.android.material.floatingactionbutton.FloatingActionButton r0 = r0.view
                    if (r5 == 0) goto L_0x006f
                    r1 = 8
                    goto L_0x0070
                L_0x006f:
                    r1 = 4
                L_0x0070:
                    r0.internalSetVisibility(r1, r5)
                    if (r4 == 0) goto L_0x007c
                    com.google.android.material.floatingactionbutton.FloatingActionButton$OnVisibilityChangedListener r5 = r4.val$listener
                    com.google.android.material.floatingactionbutton.FloatingActionButton r4 = com.google.android.material.floatingactionbutton.FloatingActionButton.this
                    r5.onHidden(r4)
                L_0x007c:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.floatingactionbutton.FloatingActionButton.hide(com.google.android.material.floatingactionbutton.FloatingActionButton$OnVisibilityChangedListener, boolean):void");
            }

            @Override // com.google.android.material.expandable.ExpandableWidget
            public boolean isExpanded() {
                return this.expandableWidgetHelper.expanded;
            }

            public boolean isOrWillBeHidden() {
                return getImpl().isOrWillBeHidden();
            }

            public boolean isOrWillBeShown() {
                return getImpl().isOrWillBeShown();
            }

            @Override // android.widget.ImageView, android.view.View
            public void jumpDrawablesToCurrentState() {
                super.jumpDrawablesToCurrentState();
                getImpl().jumpDrawableToCurrentState();
            }

            public final void offsetRectWithShadow(Rect rect) {
                int i = rect.left;
                Rect rect2 = this.shadowPadding;
                rect.left = i + rect2.left;
                rect.top += rect2.top;
                rect.right -= rect2.right;
                rect.bottom -= rect2.bottom;
            }

            public final void onApplySupportImageTint() {
                Drawable drawable = getDrawable();
                if (drawable != null) {
                    ColorStateList colorStateList = this.imageTint;
                    if (colorStateList == null) {
                        AppOpsManagerCompat.clearColorFilter(drawable);
                        return;
                    }
                    int colorForState = colorStateList.getColorForState(getDrawableState(), 0);
                    PorterDuff.Mode mode = this.imageMode;
                    if (mode == null) {
                        mode = PorterDuff.Mode.SRC_IN;
                    }
                    drawable.mutate().setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(colorForState, mode));
                }
            }

            @Override // android.widget.ImageView, android.view.View
            public void onAttachedToWindow() {
                super.onAttachedToWindow();
                final FloatingActionButtonImpl impl = getImpl();
                MaterialShapeDrawable materialShapeDrawable = impl.shapeDrawable;
                if (materialShapeDrawable != null) {
                    R$style.setParentAbsoluteElevation(impl.view, materialShapeDrawable);
                }
                if (!(impl instanceof FloatingActionButtonImplLollipop)) {
                    ViewTreeObserver viewTreeObserver = impl.view.getViewTreeObserver();
                    if (impl.preDrawListener == null) {
                        impl.preDrawListener = 
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0025: IPUT  
                              (wrap: android.view.ViewTreeObserver$OnPreDrawListener : 0x0022: CONSTRUCTOR  (r2v1 android.view.ViewTreeObserver$OnPreDrawListener A[REMOVE]) = (r0v0 'impl' com.google.android.material.floatingactionbutton.FloatingActionButtonImpl A[DONT_INLINE]) call: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.5.<init>(com.google.android.material.floatingactionbutton.FloatingActionButtonImpl):void type: CONSTRUCTOR)
                              (r0v0 'impl' com.google.android.material.floatingactionbutton.FloatingActionButtonImpl)
                             com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.preDrawListener android.view.ViewTreeObserver$OnPreDrawListener in method: com.google.android.material.floatingactionbutton.FloatingActionButton.onAttachedToWindow():void, file: classes.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:278)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:241)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:90)
                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:79)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:122)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:79)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:122)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:267)
                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:260)
                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:369)
                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:304)
                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:270)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl, state: NOT_LOADED
                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:299)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:676)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:386)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:140)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:116)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:455)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:271)
                            	... 29 more
                            */
                        /*
                            this = this;
                            super.onAttachedToWindow()
                            com.google.android.material.floatingactionbutton.FloatingActionButtonImpl r0 = r3.getImpl()
                            com.google.android.material.shape.MaterialShapeDrawable r1 = r0.shapeDrawable
                            if (r1 == 0) goto L_0x0010
                            com.google.android.material.floatingactionbutton.FloatingActionButton r2 = r0.view
                            com.google.android.material.R$style.setParentAbsoluteElevation(r2, r1)
                        L_0x0010:
                            boolean r1 = r0 instanceof com.google.android.material.floatingactionbutton.FloatingActionButtonImplLollipop
                            r1 = r1 ^ 1
                            if (r1 == 0) goto L_0x002c
                            com.google.android.material.floatingactionbutton.FloatingActionButton r1 = r0.view
                            android.view.ViewTreeObserver r1 = r1.getViewTreeObserver()
                            android.view.ViewTreeObserver$OnPreDrawListener r2 = r0.preDrawListener
                            if (r2 != 0) goto L_0x0027
                            com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$5 r2 = new com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$5
                            r2.<init>(r0)
                            r0.preDrawListener = r2
                        L_0x0027:
                            android.view.ViewTreeObserver$OnPreDrawListener r0 = r0.preDrawListener
                            r1.addOnPreDrawListener(r0)
                        L_0x002c:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.floatingactionbutton.FloatingActionButton.onAttachedToWindow():void");
                    }

                    @Override // android.widget.ImageView, android.view.View
                    public void onDetachedFromWindow() {
                        super.onDetachedFromWindow();
                        FloatingActionButtonImpl impl = getImpl();
                        ViewTreeObserver viewTreeObserver = impl.view.getViewTreeObserver();
                        ViewTreeObserver.OnPreDrawListener onPreDrawListener = impl.preDrawListener;
                        if (onPreDrawListener != null) {
                            viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
                            impl.preDrawListener = null;
                        }
                    }

                    @Override // android.widget.ImageView, android.view.View
                    public void onMeasure(int i, int i2) {
                        int sizeDimension = getSizeDimension();
                        this.imagePadding = (sizeDimension - this.maxImageSize) / 2;
                        getImpl().updatePadding();
                        int min = Math.min(resolveAdjustedSize(sizeDimension, i), resolveAdjustedSize(sizeDimension, i2));
                        Rect rect = this.shadowPadding;
                        setMeasuredDimension(rect.left + min + rect.right, min + rect.top + rect.bottom);
                    }

                    @Override // android.view.View
                    public void onRestoreInstanceState(Parcelable parcelable) {
                        if (!(parcelable instanceof ExtendableSavedState)) {
                            super.onRestoreInstanceState(parcelable);
                            return;
                        }
                        ExtendableSavedState extendableSavedState = (ExtendableSavedState) parcelable;
                        super.onRestoreInstanceState(extendableSavedState.mSuperState);
                        ExpandableWidgetHelper expandableWidgetHelper = this.expandableWidgetHelper;
                        Bundle orDefault = extendableSavedState.extendableStates.getOrDefault("expandableWidgetHelper", null);
                        Objects.requireNonNull(orDefault);
                        Bundle bundle = orDefault;
                        Objects.requireNonNull(expandableWidgetHelper);
                        expandableWidgetHelper.expanded = bundle.getBoolean("expanded", false);
                        expandableWidgetHelper.expandedComponentIdHint = bundle.getInt("expandedComponentIdHint", 0);
                        if (expandableWidgetHelper.expanded) {
                            ViewParent parent = expandableWidgetHelper.widget.getParent();
                            if (parent instanceof CoordinatorLayout) {
                                ((CoordinatorLayout) parent).dispatchDependentViewsChanged(expandableWidgetHelper.widget);
                            }
                        }
                    }

                    @Override // android.view.View
                    public Parcelable onSaveInstanceState() {
                        Parcelable onSaveInstanceState = super.onSaveInstanceState();
                        if (onSaveInstanceState == null) {
                            onSaveInstanceState = new Bundle();
                        }
                        ExtendableSavedState extendableSavedState = new ExtendableSavedState(onSaveInstanceState);
                        SimpleArrayMap<String, Bundle> simpleArrayMap = extendableSavedState.extendableStates;
                        ExpandableWidgetHelper expandableWidgetHelper = this.expandableWidgetHelper;
                        Objects.requireNonNull(expandableWidgetHelper);
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("expanded", expandableWidgetHelper.expanded);
                        bundle.putInt("expandedComponentIdHint", expandableWidgetHelper.expandedComponentIdHint);
                        simpleArrayMap.put("expandableWidgetHelper", bundle);
                        return extendableSavedState;
                    }

                    @Override // android.view.View
                    public boolean onTouchEvent(MotionEvent motionEvent) {
                        if (motionEvent.getAction() != 0 || !getContentRect(this.touchArea) || this.touchArea.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                            return super.onTouchEvent(motionEvent);
                        }
                        return false;
                    }

                    @Override // android.view.View
                    public void setBackgroundColor(int i) {
                        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
                    }

                    @Override // android.view.View
                    public void setBackgroundDrawable(Drawable drawable) {
                        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
                    }

                    @Override // android.view.View
                    public void setBackgroundResource(int i) {
                        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
                    }

                    @Override // android.view.View
                    public void setBackgroundTintList(ColorStateList colorStateList) {
                        if (this.backgroundTint != colorStateList) {
                            this.backgroundTint = colorStateList;
                            FloatingActionButtonImpl impl = getImpl();
                            MaterialShapeDrawable materialShapeDrawable = impl.shapeDrawable;
                            if (materialShapeDrawable != null) {
                                materialShapeDrawable.setTintList(colorStateList);
                            }
                            BorderDrawable borderDrawable = impl.borderDrawable;
                            if (borderDrawable != null) {
                                borderDrawable.setBorderTint(colorStateList);
                            }
                        }
                    }

                    @Override // android.view.View
                    public void setBackgroundTintMode(PorterDuff.Mode mode) {
                        if (this.backgroundTintMode != mode) {
                            this.backgroundTintMode = mode;
                            MaterialShapeDrawable materialShapeDrawable = getImpl().shapeDrawable;
                            if (materialShapeDrawable != null) {
                                materialShapeDrawable.setTintMode(mode);
                            }
                        }
                    }

                    public void setCompatElevation(float f) {
                        FloatingActionButtonImpl impl = getImpl();
                        if (impl.elevation != f) {
                            impl.elevation = f;
                            impl.onElevationsChanged(f, impl.hoveredFocusedTranslationZ, impl.pressedTranslationZ);
                        }
                    }

                    public void setCompatElevationResource(int i) {
                        setCompatElevation(getResources().getDimension(i));
                    }

                    public void setCompatHoveredFocusedTranslationZ(float f) {
                        FloatingActionButtonImpl impl = getImpl();
                        if (impl.hoveredFocusedTranslationZ != f) {
                            impl.hoveredFocusedTranslationZ = f;
                            impl.onElevationsChanged(impl.elevation, f, impl.pressedTranslationZ);
                        }
                    }

                    public void setCompatHoveredFocusedTranslationZResource(int i) {
                        setCompatHoveredFocusedTranslationZ(getResources().getDimension(i));
                    }

                    public void setCompatPressedTranslationZ(float f) {
                        FloatingActionButtonImpl impl = getImpl();
                        if (impl.pressedTranslationZ != f) {
                            impl.pressedTranslationZ = f;
                            impl.onElevationsChanged(impl.elevation, impl.hoveredFocusedTranslationZ, f);
                        }
                    }

                    public void setCompatPressedTranslationZResource(int i) {
                        setCompatPressedTranslationZ(getResources().getDimension(i));
                    }

                    public void setCustomSize(int i) {
                        if (i < 0) {
                            throw new IllegalArgumentException("Custom size must be non-negative");
                        } else if (i != this.customSize) {
                            this.customSize = i;
                            requestLayout();
                        }
                    }

                    @Override // android.view.View
                    public void setElevation(float f) {
                        super.setElevation(f);
                        getImpl().updateShapeElevation(f);
                    }

                    public void setEnsureMinTouchTargetSize(boolean z) {
                        if (z != getImpl().ensureMinTouchTargetSize) {
                            getImpl().ensureMinTouchTargetSize = z;
                            requestLayout();
                        }
                    }

                    public void setExpandedComponentIdHint(int i) {
                        this.expandableWidgetHelper.expandedComponentIdHint = i;
                    }

                    public void setHideMotionSpec(MotionSpec motionSpec) {
                        getImpl().hideMotionSpec = motionSpec;
                    }

                    public void setHideMotionSpecResource(int i) {
                        setHideMotionSpec(MotionSpec.createFromResource(getContext(), i));
                    }

                    @Override // android.widget.ImageView
                    public void setImageDrawable(Drawable drawable) {
                        if (getDrawable() != drawable) {
                            super.setImageDrawable(drawable);
                            FloatingActionButtonImpl impl = getImpl();
                            impl.setImageMatrixScale(impl.imageMatrixScale);
                            if (this.imageTint != null) {
                                onApplySupportImageTint();
                            }
                        }
                    }

                    @Override // android.widget.ImageView
                    public void setImageResource(int i) {
                        this.imageHelper.setImageResource(i);
                        onApplySupportImageTint();
                    }

                    public void setRippleColor(int i) {
                        setRippleColor(ColorStateList.valueOf(i));
                    }

                    @Override // android.view.View
                    public void setScaleX(float f) {
                        super.setScaleX(f);
                        getImpl().onScaleChanged();
                    }

                    @Override // android.view.View
                    public void setScaleY(float f) {
                        super.setScaleY(f);
                        getImpl().onScaleChanged();
                    }

                    public void setShadowPaddingEnabled(boolean z) {
                        FloatingActionButtonImpl impl = getImpl();
                        impl.shadowPaddingEnabled = z;
                        impl.updatePadding();
                    }

                    @Override // com.google.android.material.shape.Shapeable
                    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
                        getImpl().setShapeAppearance(shapeAppearanceModel);
                    }

                    public void setShowMotionSpec(MotionSpec motionSpec) {
                        getImpl().showMotionSpec = motionSpec;
                    }

                    public void setShowMotionSpecResource(int i) {
                        setShowMotionSpec(MotionSpec.createFromResource(getContext(), i));
                    }

                    public void setSize(int i) {
                        this.customSize = 0;
                        if (i != this.size) {
                            this.size = i;
                            requestLayout();
                        }
                    }

                    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
                        setBackgroundTintList(colorStateList);
                    }

                    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
                        setBackgroundTintMode(mode);
                    }

                    public void setSupportImageTintList(ColorStateList colorStateList) {
                        if (this.imageTint != colorStateList) {
                            this.imageTint = colorStateList;
                            onApplySupportImageTint();
                        }
                    }

                    public void setSupportImageTintMode(PorterDuff.Mode mode) {
                        if (this.imageMode != mode) {
                            this.imageMode = mode;
                            onApplySupportImageTint();
                        }
                    }

                    @Override // android.view.View
                    public void setTranslationX(float f) {
                        super.setTranslationX(f);
                        getImpl().onTranslationChanged();
                    }

                    @Override // android.view.View
                    public void setTranslationY(float f) {
                        super.setTranslationY(f);
                        getImpl().onTranslationChanged();
                    }

                    @Override // android.view.View
                    public void setTranslationZ(float f) {
                        super.setTranslationZ(f);
                        getImpl().onTranslationChanged();
                    }

                    public void setUseCompatPadding(boolean z) {
                        if (this.compatPadding != z) {
                            this.compatPadding = z;
                            getImpl().onCompatShadowChanged();
                        }
                    }

                    @Override // com.google.android.material.internal.VisibilityAwareImageButton, android.widget.ImageView, android.view.View
                    public void setVisibility(int i) {
                        super.setVisibility(i);
                    }

                    public void show(OnVisibilityChangedListener onVisibilityChangedListener, final boolean z) {
                        final FloatingActionButtonImpl impl = getImpl();
                        final AnonymousClass1 r5 = onVisibilityChangedListener == null ? null : new AnonymousClass1(onVisibilityChangedListener);
                        if (!impl.isOrWillBeShown()) {
                            Animator animator = impl.currentAnimator;
                            if (animator != null) {
                                animator.cancel();
                            }
                            if (impl.shouldAnimateVisibilityChange()) {
                                if (impl.view.getVisibility() != 0) {
                                    impl.view.setAlpha(0.0f);
                                    impl.view.setScaleY(0.0f);
                                    impl.view.setScaleX(0.0f);
                                    impl.setImageMatrixScale(0.0f);
                                }
                                MotionSpec motionSpec = impl.showMotionSpec;
                                if (motionSpec == null) {
                                    if (impl.defaultShowMotionSpec == null) {
                                        impl.defaultShowMotionSpec = MotionSpec.createFromResource(impl.view.getContext(), R.animator.design_fab_show_motion_spec);
                                    }
                                    motionSpec = impl.defaultShowMotionSpec;
                                    Objects.requireNonNull(motionSpec);
                                }
                                AnimatorSet createAnimator = impl.createAnimator(motionSpec, 1.0f, 1.0f, 1.0f);
                                createAnimator.addListener(
                                /*  JADX ERROR: Method code generation error
                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0066: INVOKE  
                                      (r1v8 'createAnimator' android.animation.AnimatorSet)
                                      (wrap: android.animation.AnimatorListenerAdapter : 0x0063: CONSTRUCTOR  (r2v1 android.animation.AnimatorListenerAdapter A[REMOVE]) = 
                                      (r0v0 'impl' com.google.android.material.floatingactionbutton.FloatingActionButtonImpl A[DONT_INLINE])
                                      (r6v0 'z' boolean A[DONT_INLINE])
                                      (r5v1 'r5' com.google.android.material.floatingactionbutton.FloatingActionButton$1 A[DONT_INLINE])
                                     call: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.2.<init>(com.google.android.material.floatingactionbutton.FloatingActionButtonImpl, boolean, com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$InternalVisibilityChangedListener):void type: CONSTRUCTOR)
                                     type: VIRTUAL call: android.animation.AnimatorSet.addListener(android.animation.Animator$AnimatorListener):void in method: com.google.android.material.floatingactionbutton.FloatingActionButton.show(com.google.android.material.floatingactionbutton.FloatingActionButton$OnVisibilityChangedListener, boolean):void, file: classes.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:278)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:241)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:90)
                                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:79)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:122)
                                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:79)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:122)
                                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:267)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:260)
                                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:369)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:304)
                                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:270)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl, state: NOT_LOADED
                                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:299)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:676)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:386)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:140)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:116)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:996)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:807)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:390)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:271)
                                    	... 31 more
                                    */
                                /*
                                    this = this;
                                    com.google.android.material.floatingactionbutton.FloatingActionButtonImpl r0 = r4.getImpl()
                                    if (r5 != 0) goto L_0x0008
                                    r5 = 0
                                    goto L_0x000e
                                L_0x0008:
                                    com.google.android.material.floatingactionbutton.FloatingActionButton$1 r1 = new com.google.android.material.floatingactionbutton.FloatingActionButton$1
                                    r1.<init>(r5)
                                    r5 = r1
                                L_0x000e:
                                    boolean r1 = r0.isOrWillBeShown()
                                    if (r1 == 0) goto L_0x0016
                                    goto L_0x00a6
                                L_0x0016:
                                    android.animation.Animator r1 = r0.currentAnimator
                                    if (r1 == 0) goto L_0x001d
                                    r1.cancel()
                                L_0x001d:
                                    boolean r1 = r0.shouldAnimateVisibilityChange()
                                    r2 = 1065353216(0x3f800000, float:1.0)
                                    if (r1 == 0) goto L_0x0085
                                    com.google.android.material.floatingactionbutton.FloatingActionButton r1 = r0.view
                                    int r1 = r1.getVisibility()
                                    if (r1 == 0) goto L_0x0040
                                    com.google.android.material.floatingactionbutton.FloatingActionButton r1 = r0.view
                                    r3 = 0
                                    r1.setAlpha(r3)
                                    com.google.android.material.floatingactionbutton.FloatingActionButton r1 = r0.view
                                    r1.setScaleY(r3)
                                    com.google.android.material.floatingactionbutton.FloatingActionButton r1 = r0.view
                                    r1.setScaleX(r3)
                                    r0.setImageMatrixScale(r3)
                                L_0x0040:
                                    com.google.android.material.animation.MotionSpec r1 = r0.showMotionSpec
                                    if (r1 == 0) goto L_0x0045
                                    goto L_0x005d
                                L_0x0045:
                                    com.google.android.material.animation.MotionSpec r1 = r0.defaultShowMotionSpec
                                    if (r1 != 0) goto L_0x0058
                                    com.google.android.material.floatingactionbutton.FloatingActionButton r1 = r0.view
                                    android.content.Context r1 = r1.getContext()
                                    r3 = 2130837506(0x7f020002, float:1.7279968E38)
                                    com.google.android.material.animation.MotionSpec r1 = com.google.android.material.animation.MotionSpec.createFromResource(r1, r3)
                                    r0.defaultShowMotionSpec = r1
                                L_0x0058:
                                    com.google.android.material.animation.MotionSpec r1 = r0.defaultShowMotionSpec
                                    java.util.Objects.requireNonNull(r1)
                                L_0x005d:
                                    android.animation.AnimatorSet r1 = r0.createAnimator(r1, r2, r2, r2)
                                    com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$2 r2 = new com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$2
                                    r2.<init>(r0, r6, r5)
                                    r1.addListener(r2)
                                    java.util.ArrayList<android.animation.Animator$AnimatorListener> r5 = r0.showListeners
                                    if (r5 == 0) goto L_0x0081
                                    java.util.Iterator r5 = r5.iterator()
                                L_0x0071:
                                    boolean r6 = r5.hasNext()
                                    if (r6 == 0) goto L_0x0081
                                    java.lang.Object r6 = r5.next()
                                    android.animation.Animator$AnimatorListener r6 = (android.animation.Animator.AnimatorListener) r6
                                    r1.addListener(r6)
                                    goto L_0x0071
                                L_0x0081:
                                    r1.start()
                                    goto L_0x00a6
                                L_0x0085:
                                    com.google.android.material.floatingactionbutton.FloatingActionButton r1 = r0.view
                                    r3 = 0
                                    r1.internalSetVisibility(r3, r6)
                                    com.google.android.material.floatingactionbutton.FloatingActionButton r6 = r0.view
                                    r6.setAlpha(r2)
                                    com.google.android.material.floatingactionbutton.FloatingActionButton r6 = r0.view
                                    r6.setScaleY(r2)
                                    com.google.android.material.floatingactionbutton.FloatingActionButton r6 = r0.view
                                    r6.setScaleX(r2)
                                    r0.setImageMatrixScale(r2)
                                    if (r5 == 0) goto L_0x00a6
                                    com.google.android.material.floatingactionbutton.FloatingActionButton$OnVisibilityChangedListener r6 = r5.val$listener
                                    com.google.android.material.floatingactionbutton.FloatingActionButton r5 = com.google.android.material.floatingactionbutton.FloatingActionButton.this
                                    r6.onShown(r5)
                                L_0x00a6:
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.floatingactionbutton.FloatingActionButton.show(com.google.android.material.floatingactionbutton.FloatingActionButton$OnVisibilityChangedListener, boolean):void");
                            }

                            public final int getSizeDimension(int i) {
                                int i2 = this.customSize;
                                if (i2 != 0) {
                                    return i2;
                                }
                                Resources resources = getResources();
                                if (i != -1) {
                                    if (i != 1) {
                                        return resources.getDimensionPixelSize(R.dimen.design_fab_size_normal);
                                    }
                                    return resources.getDimensionPixelSize(R.dimen.design_fab_size_mini);
                                } else if (Math.max(resources.getConfiguration().screenWidthDp, resources.getConfiguration().screenHeightDp) < 470) {
                                    return getSizeDimension(1);
                                } else {
                                    return getSizeDimension(0);
                                }
                            }

                            public void setRippleColor(ColorStateList colorStateList) {
                                if (this.rippleColor != colorStateList) {
                                    this.rippleColor = colorStateList;
                                    getImpl().setRippleColor(this.rippleColor);
                                }
                            }
                        }
