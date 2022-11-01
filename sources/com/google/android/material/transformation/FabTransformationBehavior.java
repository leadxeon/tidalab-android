package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.Property;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.android.material.R$style;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.ArgbEvaluatorCompat;
import com.google.android.material.animation.ChildrenAlphaProperty;
import com.google.android.material.animation.DrawableAlphaProperty;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.animation.MotionTiming;
import com.google.android.material.animation.Positioning;
import com.google.android.material.circularreveal.CircularRevealWidget;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tidalab.v2board.clash.foss.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
@Deprecated
/* loaded from: classes.dex */
public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {
    public float dependencyOriginalTranslationX;
    public float dependencyOriginalTranslationY;
    public final int[] tmpArray;
    public final Rect tmpRect;
    public final RectF tmpRectF1;
    public final RectF tmpRectF2;

    /* loaded from: classes.dex */
    public static class FabTransformationSpec {
        public Positioning positioning;
        public MotionSpec timings;
    }

    public FabTransformationBehavior() {
        this.tmpRect = new Rect();
        this.tmpRectF1 = new RectF();
        this.tmpRectF2 = new RectF();
        this.tmpArray = new int[2];
    }

    public final Pair<MotionTiming, MotionTiming> calculateMotionTiming(float f, float f2, boolean z, FabTransformationSpec fabTransformationSpec) {
        MotionTiming motionTiming;
        MotionTiming motionTiming2;
        int i;
        if (f == 0.0f || f2 == 0.0f) {
            motionTiming2 = fabTransformationSpec.timings.getTiming("translationXLinear");
            motionTiming = fabTransformationSpec.timings.getTiming("translationYLinear");
        } else if ((!z || f2 >= 0.0f) && (z || i <= 0)) {
            motionTiming2 = fabTransformationSpec.timings.getTiming("translationXCurveDownwards");
            motionTiming = fabTransformationSpec.timings.getTiming("translationYCurveDownwards");
        } else {
            motionTiming2 = fabTransformationSpec.timings.getTiming("translationXCurveUpwards");
            motionTiming = fabTransformationSpec.timings.getTiming("translationYCurveUpwards");
        }
        return new Pair<>(motionTiming2, motionTiming);
    }

    public final float calculateTranslationX(View view, View view2, Positioning positioning) {
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateWindowBounds(view, rectF);
        rectF.offset(this.dependencyOriginalTranslationX, this.dependencyOriginalTranslationY);
        calculateWindowBounds(view2, rectF2);
        Objects.requireNonNull(positioning);
        return (rectF2.centerX() - rectF.centerX()) + 0.0f;
    }

    public final float calculateTranslationY(View view, View view2, Positioning positioning) {
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateWindowBounds(view, rectF);
        rectF.offset(this.dependencyOriginalTranslationX, this.dependencyOriginalTranslationY);
        calculateWindowBounds(view2, rectF2);
        Objects.requireNonNull(positioning);
        return (rectF2.centerY() - rectF.centerY()) + 0.0f;
    }

    public final float calculateValueOfAnimationAtEndOfExpansion(FabTransformationSpec fabTransformationSpec, MotionTiming motionTiming, float f, float f2) {
        long j = motionTiming.delay;
        long j2 = motionTiming.duration;
        MotionTiming timing = fabTransformationSpec.timings.getTiming("expansion");
        float interpolation = motionTiming.getInterpolator().getInterpolation(((float) (((timing.delay + timing.duration) + 17) - j)) / ((float) j2));
        TimeInterpolator timeInterpolator = AnimationUtils.LINEAR_INTERPOLATOR;
        return GeneratedOutlineSupport.outline0(f2, f, interpolation, f);
    }

    public final void calculateWindowBounds(View view, RectF rectF) {
        rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        int[] iArr = this.tmpArray;
        view.getLocationInWindow(iArr);
        rectF.offsetTo(iArr[0], iArr[1]);
        rectF.offset((int) (-view.getTranslationX()), (int) (-view.getTranslationY()));
    }

    public final void createChildrenFadeAnimation(View view, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List list) {
        ViewGroup viewGroup;
        ObjectAnimator objectAnimator;
        if (view instanceof ViewGroup) {
            boolean z3 = view instanceof CircularRevealWidget;
            View findViewById = view.findViewById(R.id.mtrl_child_content_container);
            if (findViewById != null) {
                viewGroup = toViewGroupOrNull(findViewById);
            } else if ((view instanceof TransformationChildLayout) || (view instanceof TransformationChildCard)) {
                viewGroup = toViewGroupOrNull(((ViewGroup) view).getChildAt(0));
            } else {
                viewGroup = toViewGroupOrNull(view);
            }
            if (viewGroup != null) {
                if (z) {
                    if (!z2) {
                        ChildrenAlphaProperty.CHILDREN_ALPHA.set(viewGroup, Float.valueOf(0.0f));
                    }
                    objectAnimator = ObjectAnimator.ofFloat(viewGroup, ChildrenAlphaProperty.CHILDREN_ALPHA, 1.0f);
                } else {
                    objectAnimator = ObjectAnimator.ofFloat(viewGroup, ChildrenAlphaProperty.CHILDREN_ALPHA, 0.0f);
                }
                fabTransformationSpec.timings.getTiming("contentFade").apply(objectAnimator);
                list.add(objectAnimator);
            }
        }
    }

    public final void createColorAnimation(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List list) {
        ObjectAnimator objectAnimator;
        if (view2 instanceof CircularRevealWidget) {
            CircularRevealWidget circularRevealWidget = (CircularRevealWidget) view2;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            ColorStateList backgroundTintList = view.getBackgroundTintList();
            int colorForState = backgroundTintList != null ? backgroundTintList.getColorForState(view.getDrawableState(), backgroundTintList.getDefaultColor()) : 0;
            int i = 16777215 & colorForState;
            if (z) {
                if (!z2) {
                    circularRevealWidget.setCircularRevealScrimColor(colorForState);
                }
                objectAnimator = ObjectAnimator.ofInt(circularRevealWidget, CircularRevealWidget.CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR, i);
            } else {
                objectAnimator = ObjectAnimator.ofInt(circularRevealWidget, CircularRevealWidget.CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR, colorForState);
            }
            objectAnimator.setEvaluator(ArgbEvaluatorCompat.instance);
            fabTransformationSpec.timings.getTiming("color").apply(objectAnimator);
            list.add(objectAnimator);
        }
    }

    @TargetApi(21)
    public final void createElevationAnimation(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List list) {
        ObjectAnimator objectAnimator;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        float elevation = view2.getElevation() - view.getElevation();
        if (z) {
            if (!z2) {
                view2.setTranslationZ(-elevation);
            }
            objectAnimator = ObjectAnimator.ofFloat(view2, View.TRANSLATION_Z, 0.0f);
        } else {
            objectAnimator = ObjectAnimator.ofFloat(view2, View.TRANSLATION_Z, -elevation);
        }
        fabTransformationSpec.timings.getTiming("elevation").apply(objectAnimator);
        list.add(objectAnimator);
    }

    public final void createTranslationAnimation(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List list, RectF rectF) {
        ObjectAnimator objectAnimator;
        ObjectAnimator objectAnimator2;
        float calculateTranslationX = calculateTranslationX(view, view2, fabTransformationSpec.positioning);
        float calculateTranslationY = calculateTranslationY(view, view2, fabTransformationSpec.positioning);
        Pair<MotionTiming, MotionTiming> calculateMotionTiming = calculateMotionTiming(calculateTranslationX, calculateTranslationY, z, fabTransformationSpec);
        MotionTiming motionTiming = (MotionTiming) calculateMotionTiming.first;
        MotionTiming motionTiming2 = (MotionTiming) calculateMotionTiming.second;
        if (z) {
            if (!z2) {
                view2.setTranslationX(-calculateTranslationX);
                view2.setTranslationY(-calculateTranslationY);
            }
            objectAnimator2 = ObjectAnimator.ofFloat(view2, View.TRANSLATION_X, 0.0f);
            objectAnimator = ObjectAnimator.ofFloat(view2, View.TRANSLATION_Y, 0.0f);
            float calculateValueOfAnimationAtEndOfExpansion = calculateValueOfAnimationAtEndOfExpansion(fabTransformationSpec, motionTiming, -calculateTranslationX, 0.0f);
            float calculateValueOfAnimationAtEndOfExpansion2 = calculateValueOfAnimationAtEndOfExpansion(fabTransformationSpec, motionTiming2, -calculateTranslationY, 0.0f);
            Rect rect = this.tmpRect;
            view2.getWindowVisibleDisplayFrame(rect);
            RectF rectF2 = this.tmpRectF1;
            rectF2.set(rect);
            RectF rectF3 = this.tmpRectF2;
            calculateWindowBounds(view2, rectF3);
            rectF3.offset(calculateValueOfAnimationAtEndOfExpansion, calculateValueOfAnimationAtEndOfExpansion2);
            rectF3.intersect(rectF2);
            rectF.set(rectF3);
        } else {
            objectAnimator2 = ObjectAnimator.ofFloat(view2, View.TRANSLATION_X, -calculateTranslationX);
            objectAnimator = ObjectAnimator.ofFloat(view2, View.TRANSLATION_Y, -calculateTranslationY);
        }
        motionTiming.apply(objectAnimator2);
        motionTiming2.apply(objectAnimator);
        list.add(objectAnimator2);
        list.add(objectAnimator);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (view.getVisibility() == 8) {
            throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
        } else if (!(view2 instanceof FloatingActionButton)) {
            return false;
        } else {
            int expandedComponentIdHint = ((FloatingActionButton) view2).getExpandedComponentIdHint();
            return expandedComponentIdHint == 0 || expandedComponentIdHint == view.getId();
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
        if (layoutParams.dodgeInsetEdges == 0) {
            layoutParams.dodgeInsetEdges = 80;
        }
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior
    public AnimatorSet onCreateExpandedStateChangeAnimation(final View view, final View view2, final boolean z, boolean z2) {
        FabTransformationSpec fabTransformationSpec;
        ArrayList arrayList;
        final CircularRevealWidget circularRevealWidget;
        Animator animator;
        ObjectAnimator objectAnimator;
        FabTransformationSpec onCreateMotionSpec = onCreateMotionSpec(view2.getContext(), z);
        if (z) {
            this.dependencyOriginalTranslationX = view.getTranslationX();
            this.dependencyOriginalTranslationY = view.getTranslationY();
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        createElevationAnimation(view, view2, z, z2, onCreateMotionSpec, arrayList2);
        RectF rectF = this.tmpRectF1;
        createTranslationAnimation(view, view2, z, z2, onCreateMotionSpec, arrayList2, rectF);
        float width = rectF.width();
        float height = rectF.height();
        float calculateTranslationX = calculateTranslationX(view, view2, onCreateMotionSpec.positioning);
        float calculateTranslationY = calculateTranslationY(view, view2, onCreateMotionSpec.positioning);
        Pair<MotionTiming, MotionTiming> calculateMotionTiming = calculateMotionTiming(calculateTranslationX, calculateTranslationY, z, onCreateMotionSpec);
        MotionTiming motionTiming = (MotionTiming) calculateMotionTiming.first;
        MotionTiming motionTiming2 = (MotionTiming) calculateMotionTiming.second;
        Property property = View.TRANSLATION_X;
        float[] fArr = new float[1];
        if (!z) {
            calculateTranslationX = this.dependencyOriginalTranslationX;
        }
        fArr[0] = calculateTranslationX;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, property, fArr);
        Property property2 = View.TRANSLATION_Y;
        float[] fArr2 = new float[1];
        if (!z) {
            calculateTranslationY = this.dependencyOriginalTranslationY;
        }
        fArr2[0] = calculateTranslationY;
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, property2, fArr2);
        motionTiming.apply(ofFloat);
        motionTiming2.apply(ofFloat2);
        arrayList2.add(ofFloat);
        arrayList2.add(ofFloat2);
        boolean z3 = view2 instanceof CircularRevealWidget;
        if (z3 && (view instanceof ImageView)) {
            final CircularRevealWidget circularRevealWidget2 = (CircularRevealWidget) view2;
            final Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable != null) {
                drawable.mutate();
                if (z) {
                    if (!z2) {
                        drawable.setAlpha(255);
                    }
                    objectAnimator = ObjectAnimator.ofInt(drawable, DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT, 0);
                } else {
                    objectAnimator = ObjectAnimator.ofInt(drawable, DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT, 255);
                }
                objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.google.android.material.transformation.FabTransformationBehavior.2
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        view2.invalidate();
                    }
                });
                onCreateMotionSpec.timings.getTiming("iconFade").apply(objectAnimator);
                arrayList2.add(objectAnimator);
                arrayList3.add(new AnimatorListenerAdapter(this) { // from class: com.google.android.material.transformation.FabTransformationBehavior.3
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator2) {
                        circularRevealWidget2.setCircularRevealOverlayDrawable(null);
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationStart(Animator animator2) {
                        circularRevealWidget2.setCircularRevealOverlayDrawable(drawable);
                    }
                });
            }
        }
        if (!z3) {
            fabTransformationSpec = onCreateMotionSpec;
            arrayList = arrayList3;
        } else {
            final CircularRevealWidget circularRevealWidget3 = (CircularRevealWidget) view2;
            Positioning positioning = onCreateMotionSpec.positioning;
            RectF rectF2 = this.tmpRectF1;
            RectF rectF3 = this.tmpRectF2;
            calculateWindowBounds(view, rectF2);
            rectF2.offset(this.dependencyOriginalTranslationX, this.dependencyOriginalTranslationY);
            calculateWindowBounds(view2, rectF3);
            rectF3.offset(-calculateTranslationX(view, view2, positioning), 0.0f);
            float centerX = rectF2.centerX() - rectF3.left;
            Positioning positioning2 = onCreateMotionSpec.positioning;
            RectF rectF4 = this.tmpRectF1;
            RectF rectF5 = this.tmpRectF2;
            calculateWindowBounds(view, rectF4);
            rectF4.offset(this.dependencyOriginalTranslationX, this.dependencyOriginalTranslationY);
            calculateWindowBounds(view2, rectF5);
            rectF5.offset(0.0f, -calculateTranslationY(view, view2, positioning2));
            float centerY = rectF4.centerY() - rectF5.top;
            ((FloatingActionButton) view).getContentRect(this.tmpRect);
            float width2 = this.tmpRect.width() / 2.0f;
            MotionTiming timing = onCreateMotionSpec.timings.getTiming("expansion");
            if (z) {
                if (!z2) {
                    circularRevealWidget3.setRevealInfo(new CircularRevealWidget.RevealInfo(centerX, centerY, width2));
                }
                if (z2) {
                    width2 = circularRevealWidget3.getRevealInfo().radius;
                }
                float dist = R$style.dist(centerX, centerY, 0.0f, 0.0f);
                float dist2 = R$style.dist(centerX, centerY, width, 0.0f);
                float dist3 = R$style.dist(centerX, centerY, width, height);
                float dist4 = R$style.dist(centerX, centerY, 0.0f, height);
                if (dist <= dist2 || dist <= dist3 || dist <= dist4) {
                    dist = (dist2 <= dist3 || dist2 <= dist4) ? dist3 > dist4 ? dist3 : dist4 : dist2;
                }
                animator = R$style.createCircularReveal(circularRevealWidget3, centerX, centerY, dist);
                animator.addListener(new AnimatorListenerAdapter(this) { // from class: com.google.android.material.transformation.FabTransformationBehavior.4
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator2) {
                        CircularRevealWidget.RevealInfo revealInfo = circularRevealWidget3.getRevealInfo();
                        revealInfo.radius = Float.MAX_VALUE;
                        circularRevealWidget3.setRevealInfo(revealInfo);
                    }
                });
                long j = timing.delay;
                int i = (int) centerX;
                int i2 = (int) centerY;
                if (j > 0) {
                    Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(view2, i, i2, width2, width2);
                    createCircularReveal.setStartDelay(0L);
                    createCircularReveal.setDuration(j);
                    arrayList2.add(createCircularReveal);
                }
                circularRevealWidget = circularRevealWidget3;
                arrayList = arrayList3;
                fabTransformationSpec = onCreateMotionSpec;
            } else {
                float f = circularRevealWidget3.getRevealInfo().radius;
                Animator createCircularReveal2 = R$style.createCircularReveal(circularRevealWidget3, centerX, centerY, width2);
                long j2 = timing.delay;
                int i3 = (int) centerX;
                int i4 = (int) centerY;
                if (j2 > 0) {
                    Animator createCircularReveal3 = ViewAnimationUtils.createCircularReveal(view2, i3, i4, f, f);
                    createCircularReveal3.setStartDelay(0L);
                    createCircularReveal3.setDuration(j2);
                    arrayList2.add(createCircularReveal3);
                }
                long j3 = timing.delay;
                long j4 = timing.duration;
                MotionSpec motionSpec = onCreateMotionSpec.timings;
                int i5 = motionSpec.timings.mSize;
                arrayList = arrayList3;
                fabTransformationSpec = onCreateMotionSpec;
                int i6 = 0;
                long j5 = 0;
                while (i6 < i5) {
                    MotionTiming valueAt = motionSpec.timings.valueAt(i6);
                    j5 = Math.max(j5, valueAt.delay + valueAt.duration);
                    i6++;
                    i5 = i5;
                    circularRevealWidget3 = circularRevealWidget3;
                    i3 = i3;
                    motionSpec = motionSpec;
                }
                circularRevealWidget = circularRevealWidget3;
                long j6 = j3 + j4;
                if (j6 < j5) {
                    Animator createCircularReveal4 = ViewAnimationUtils.createCircularReveal(view2, i3, i4, width2, width2);
                    createCircularReveal4.setStartDelay(j6);
                    createCircularReveal4.setDuration(j5 - j6);
                    arrayList2.add(createCircularReveal4);
                }
                animator = createCircularReveal2;
            }
            timing.apply(animator);
            arrayList2.add(animator);
            arrayList.add(new AnimatorListenerAdapter() { // from class: com.google.android.material.circularreveal.CircularRevealCompat$1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator2) {
                    CircularRevealWidget.this.destroyCircularRevealCache();
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator2) {
                    CircularRevealWidget.this.buildCircularRevealCache();
                }
            });
        }
        createColorAnimation(view, view2, z, z2, fabTransformationSpec, arrayList2);
        createChildrenFadeAnimation(view2, z, z2, fabTransformationSpec, arrayList2);
        AnimatorSet animatorSet = new AnimatorSet();
        R$style.playTogether(animatorSet, arrayList2);
        animatorSet.addListener(new AnimatorListenerAdapter(this) { // from class: com.google.android.material.transformation.FabTransformationBehavior.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                if (!z) {
                    view2.setVisibility(4);
                    view.setAlpha(1.0f);
                    view.setVisibility(0);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator2) {
                if (z) {
                    view2.setVisibility(0);
                    view.setAlpha(0.0f);
                    view.setVisibility(4);
                }
            }
        });
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            animatorSet.addListener((Animator.AnimatorListener) arrayList.get(i7));
        }
        return animatorSet;
    }

    public abstract FabTransformationSpec onCreateMotionSpec(Context context, boolean z);

    public final ViewGroup toViewGroupOrNull(View view) {
        if (view instanceof ViewGroup) {
            return (ViewGroup) view;
        }
        return null;
    }

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.tmpRect = new Rect();
        this.tmpRectF1 = new RectF();
        this.tmpRectF2 = new RectF();
        this.tmpArray = new int[2];
    }
}
