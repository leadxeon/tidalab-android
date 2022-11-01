package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$style;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.ImageMatrixProperty;
import com.google.android.material.animation.MatrixEvaluator;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.StateListAnimator;
import com.google.android.material.shadow.ShadowViewDelegate;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class FloatingActionButtonImpl {
    public BorderDrawable borderDrawable;
    public Drawable contentBackground;
    public Animator currentAnimator;
    public MotionSpec defaultHideMotionSpec;
    public MotionSpec defaultShowMotionSpec;
    public float elevation;
    public boolean ensureMinTouchTargetSize;
    public ArrayList<Animator.AnimatorListener> hideListeners;
    public MotionSpec hideMotionSpec;
    public float hoveredFocusedTranslationZ;
    public int maxImageSize;
    public int minTouchTargetSize;
    public ViewTreeObserver.OnPreDrawListener preDrawListener;
    public float pressedTranslationZ;
    public Drawable rippleDrawable;
    public float rotation;
    public final ShadowViewDelegate shadowViewDelegate;
    public ShapeAppearanceModel shapeAppearance;
    public MaterialShapeDrawable shapeDrawable;
    public ArrayList<Animator.AnimatorListener> showListeners;
    public MotionSpec showMotionSpec;
    public final StateListAnimator stateListAnimator;
    public ArrayList<InternalTransformationCallback> transformationCallbacks;
    public final FloatingActionButton view;
    public static final TimeInterpolator ELEVATION_ANIM_INTERPOLATOR = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
    public static final int[] PRESSED_ENABLED_STATE_SET = {16842919, 16842910};
    public static final int[] HOVERED_FOCUSED_ENABLED_STATE_SET = {16843623, 16842908, 16842910};
    public static final int[] FOCUSED_ENABLED_STATE_SET = {16842908, 16842910};
    public static final int[] HOVERED_ENABLED_STATE_SET = {16843623, 16842910};
    public static final int[] ENABLED_STATE_SET = {16842910};
    public static final int[] EMPTY_STATE_SET = new int[0];
    public boolean shadowPaddingEnabled = true;
    public float imageMatrixScale = 1.0f;
    public int animState = 0;
    public final Rect tmpRect = new Rect();
    public final RectF tmpRectF1 = new RectF();
    public final RectF tmpRectF2 = new RectF();
    public final Matrix tmpMatrix = new Matrix();

    /* loaded from: classes.dex */
    public class DisabledElevationAnimation extends ShadowAnimatorImpl {
        public DisabledElevationAnimation(FloatingActionButtonImpl floatingActionButtonImpl) {
            super(null);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.ShadowAnimatorImpl
        public float getTargetShadowSize() {
            return 0.0f;
        }
    }

    /* loaded from: classes.dex */
    public class ElevateToHoveredFocusedTranslationZAnimation extends ShadowAnimatorImpl {
        public ElevateToHoveredFocusedTranslationZAnimation() {
            super(null);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.ShadowAnimatorImpl
        public float getTargetShadowSize() {
            FloatingActionButtonImpl floatingActionButtonImpl = FloatingActionButtonImpl.this;
            return floatingActionButtonImpl.elevation + floatingActionButtonImpl.hoveredFocusedTranslationZ;
        }
    }

    /* loaded from: classes.dex */
    public class ElevateToPressedTranslationZAnimation extends ShadowAnimatorImpl {
        public ElevateToPressedTranslationZAnimation() {
            super(null);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.ShadowAnimatorImpl
        public float getTargetShadowSize() {
            FloatingActionButtonImpl floatingActionButtonImpl = FloatingActionButtonImpl.this;
            return floatingActionButtonImpl.elevation + floatingActionButtonImpl.pressedTranslationZ;
        }
    }

    /* loaded from: classes.dex */
    public interface InternalTransformationCallback {
        void onScaleChanged();

        void onTranslationChanged();
    }

    /* loaded from: classes.dex */
    public interface InternalVisibilityChangedListener {
    }

    /* loaded from: classes.dex */
    public class ResetElevationAnimation extends ShadowAnimatorImpl {
        public ResetElevationAnimation() {
            super(null);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.ShadowAnimatorImpl
        public float getTargetShadowSize() {
            return FloatingActionButtonImpl.this.elevation;
        }
    }

    /* loaded from: classes.dex */
    public abstract class ShadowAnimatorImpl extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        public float shadowSizeEnd;
        public float shadowSizeStart;
        public boolean validValues;

        public ShadowAnimatorImpl(AnonymousClass1 r2) {
        }

        public abstract float getTargetShadowSize();

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            FloatingActionButtonImpl.this.updateShapeElevation((int) this.shadowSizeEnd);
            this.validValues = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (!this.validValues) {
                MaterialShapeDrawable materialShapeDrawable = FloatingActionButtonImpl.this.shapeDrawable;
                this.shadowSizeStart = materialShapeDrawable == null ? 0.0f : materialShapeDrawable.drawableState.elevation;
                this.shadowSizeEnd = getTargetShadowSize();
                this.validValues = true;
            }
            FloatingActionButtonImpl floatingActionButtonImpl = FloatingActionButtonImpl.this;
            float f = this.shadowSizeStart;
            floatingActionButtonImpl.updateShapeElevation((int) ((valueAnimator.getAnimatedFraction() * (this.shadowSizeEnd - f)) + f));
        }
    }

    public FloatingActionButtonImpl(FloatingActionButton floatingActionButton, ShadowViewDelegate shadowViewDelegate) {
        this.view = floatingActionButton;
        this.shadowViewDelegate = shadowViewDelegate;
        StateListAnimator stateListAnimator = new StateListAnimator();
        this.stateListAnimator = stateListAnimator;
        stateListAnimator.addState(PRESSED_ENABLED_STATE_SET, createElevationAnimator(new ElevateToPressedTranslationZAnimation()));
        stateListAnimator.addState(HOVERED_FOCUSED_ENABLED_STATE_SET, createElevationAnimator(new ElevateToHoveredFocusedTranslationZAnimation()));
        stateListAnimator.addState(FOCUSED_ENABLED_STATE_SET, createElevationAnimator(new ElevateToHoveredFocusedTranslationZAnimation()));
        stateListAnimator.addState(HOVERED_ENABLED_STATE_SET, createElevationAnimator(new ElevateToHoveredFocusedTranslationZAnimation()));
        stateListAnimator.addState(ENABLED_STATE_SET, createElevationAnimator(new ResetElevationAnimation()));
        stateListAnimator.addState(EMPTY_STATE_SET, createElevationAnimator(new DisabledElevationAnimation(this)));
        this.rotation = floatingActionButton.getRotation();
    }

    public final void calculateImageMatrixFromScale(float f, Matrix matrix) {
        matrix.reset();
        Drawable drawable = this.view.getDrawable();
        if (drawable != null && this.maxImageSize != 0) {
            RectF rectF = this.tmpRectF1;
            RectF rectF2 = this.tmpRectF2;
            rectF.set(0.0f, 0.0f, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            int i = this.maxImageSize;
            rectF2.set(0.0f, 0.0f, i, i);
            matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
            int i2 = this.maxImageSize;
            matrix.postScale(f, f, i2 / 2.0f, i2 / 2.0f);
        }
    }

    public final AnimatorSet createAnimator(MotionSpec motionSpec, float f, float f2, float f3) {
        ArrayList arrayList = new ArrayList();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.view, View.ALPHA, f);
        motionSpec.getTiming("opacity").apply(ofFloat);
        arrayList.add(ofFloat);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.view, View.SCALE_X, f2);
        motionSpec.getTiming("scale").apply(ofFloat2);
        int i = Build.VERSION.SDK_INT;
        if (i == 26) {
            ofFloat2.setEvaluator(new TypeEvaluator<Float>(this) { // from class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.4
                public FloatEvaluator floatEvaluator = new FloatEvaluator();

                @Override // android.animation.TypeEvaluator
                public Float evaluate(float f4, Float f5, Float f6) {
                    float floatValue = this.floatEvaluator.evaluate(f4, (Number) f5, (Number) f6).floatValue();
                    if (floatValue < 0.1f) {
                        floatValue = 0.0f;
                    }
                    return Float.valueOf(floatValue);
                }
            });
        }
        arrayList.add(ofFloat2);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.view, View.SCALE_Y, f2);
        motionSpec.getTiming("scale").apply(ofFloat3);
        if (i == 26) {
            ofFloat3.setEvaluator(new TypeEvaluator<Float>(this) { // from class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.4
                public FloatEvaluator floatEvaluator = new FloatEvaluator();

                @Override // android.animation.TypeEvaluator
                public Float evaluate(float f4, Float f5, Float f6) {
                    float floatValue = this.floatEvaluator.evaluate(f4, (Number) f5, (Number) f6).floatValue();
                    if (floatValue < 0.1f) {
                        floatValue = 0.0f;
                    }
                    return Float.valueOf(floatValue);
                }
            });
        }
        arrayList.add(ofFloat3);
        calculateImageMatrixFromScale(f3, this.tmpMatrix);
        ObjectAnimator ofObject = ObjectAnimator.ofObject(this.view, new ImageMatrixProperty(), new MatrixEvaluator() { // from class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.3
            @Override // android.animation.TypeEvaluator
            public Matrix evaluate(float f4, Matrix matrix, Matrix matrix2) {
                FloatingActionButtonImpl.this.imageMatrixScale = f4;
                matrix.getValues(this.tempStartValues);
                matrix2.getValues(this.tempEndValues);
                for (int i2 = 0; i2 < 9; i2++) {
                    float[] fArr = this.tempEndValues;
                    float f5 = fArr[i2];
                    float[] fArr2 = this.tempStartValues;
                    fArr[i2] = ((f5 - fArr2[i2]) * f4) + fArr2[i2];
                }
                this.tempMatrix.setValues(this.tempEndValues);
                return this.tempMatrix;
            }
        }, new Matrix(this.tmpMatrix));
        motionSpec.getTiming("iconScale").apply(ofObject);
        arrayList.add(ofObject);
        AnimatorSet animatorSet = new AnimatorSet();
        R$style.playTogether(animatorSet, arrayList);
        return animatorSet;
    }

    public final ValueAnimator createElevationAnimator(ShadowAnimatorImpl shadowAnimatorImpl) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(ELEVATION_ANIM_INTERPOLATOR);
        valueAnimator.setDuration(100L);
        valueAnimator.addListener(shadowAnimatorImpl);
        valueAnimator.addUpdateListener(shadowAnimatorImpl);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        return valueAnimator;
    }

    public float getElevation() {
        throw null;
    }

    public void getPadding(Rect rect) {
        int sizeDimension = this.ensureMinTouchTargetSize ? (this.minTouchTargetSize - this.view.getSizeDimension()) / 2 : 0;
        float elevation = this.shadowPaddingEnabled ? getElevation() + this.pressedTranslationZ : 0.0f;
        int max = Math.max(sizeDimension, (int) Math.ceil(elevation));
        int max2 = Math.max(sizeDimension, (int) Math.ceil(elevation * 1.5f));
        rect.set(max, max2, max, max2);
    }

    public void initializeBackgroundDrawable(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i) {
        throw null;
    }

    public boolean isOrWillBeHidden() {
        return this.view.getVisibility() == 0 ? this.animState == 1 : this.animState != 2;
    }

    public boolean isOrWillBeShown() {
        return this.view.getVisibility() != 0 ? this.animState == 2 : this.animState != 1;
    }

    public void jumpDrawableToCurrentState() {
        throw null;
    }

    public void onCompatShadowChanged() {
        throw null;
    }

    public void onDrawableStateChanged(int[] iArr) {
        throw null;
    }

    public void onElevationsChanged(float f, float f2, float f3) {
        throw null;
    }

    public void onScaleChanged() {
        ArrayList<InternalTransformationCallback> arrayList = this.transformationCallbacks;
        if (arrayList != null) {
            Iterator<InternalTransformationCallback> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onScaleChanged();
            }
        }
    }

    public void onTranslationChanged() {
        ArrayList<InternalTransformationCallback> arrayList = this.transformationCallbacks;
        if (arrayList != null) {
            Iterator<InternalTransformationCallback> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onTranslationChanged();
            }
        }
    }

    public final void setImageMatrixScale(float f) {
        this.imageMatrixScale = f;
        Matrix matrix = this.tmpMatrix;
        calculateImageMatrixFromScale(f, matrix);
        this.view.setImageMatrix(matrix);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        throw null;
    }

    public final void setShapeAppearance(ShapeAppearanceModel shapeAppearanceModel) {
        this.shapeAppearance = shapeAppearanceModel;
        MaterialShapeDrawable materialShapeDrawable = this.shapeDrawable;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.drawableState.shapeAppearanceModel = shapeAppearanceModel;
            materialShapeDrawable.invalidateSelf();
        }
        Drawable drawable = this.rippleDrawable;
        if (drawable instanceof Shapeable) {
            ((Shapeable) drawable).setShapeAppearanceModel(shapeAppearanceModel);
        }
        BorderDrawable borderDrawable = this.borderDrawable;
        if (borderDrawable != null) {
            borderDrawable.shapeAppearanceModel = shapeAppearanceModel;
            borderDrawable.invalidateSelf();
        }
    }

    public boolean shouldAddPadding() {
        throw null;
    }

    public final boolean shouldAnimateVisibilityChange() {
        FloatingActionButton floatingActionButton = this.view;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        return floatingActionButton.isLaidOut() && !this.view.isInEditMode();
    }

    public final boolean shouldExpandBoundsForA11y() {
        return !this.ensureMinTouchTargetSize || this.view.getSizeDimension() >= this.minTouchTargetSize;
    }

    public void updateFromViewRotation() {
        throw null;
    }

    public final void updatePadding() {
        Rect rect = this.tmpRect;
        getPadding(rect);
        AppOpsManagerCompat.checkNotNull(this.contentBackground, "Didn't initialize content background");
        if (shouldAddPadding()) {
            InsetDrawable insetDrawable = new InsetDrawable(this.contentBackground, rect.left, rect.top, rect.right, rect.bottom);
            FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = (FloatingActionButton.ShadowDelegateImpl) this.shadowViewDelegate;
            Objects.requireNonNull(shadowDelegateImpl);
            FloatingActionButtonImpl.super.setBackgroundDrawable(insetDrawable);
        } else {
            ShadowViewDelegate shadowViewDelegate = this.shadowViewDelegate;
            Drawable drawable = this.contentBackground;
            FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl2 = (FloatingActionButton.ShadowDelegateImpl) shadowViewDelegate;
            Objects.requireNonNull(shadowDelegateImpl2);
            if (drawable != null) {
                FloatingActionButtonImpl.super.setBackgroundDrawable(drawable);
            }
        }
        ShadowViewDelegate shadowViewDelegate2 = this.shadowViewDelegate;
        int i = rect.left;
        int i2 = rect.top;
        int i3 = rect.right;
        int i4 = rect.bottom;
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl3 = (FloatingActionButton.ShadowDelegateImpl) shadowViewDelegate2;
        FloatingActionButton.this.shadowPadding.set(i, i2, i3, i4);
        FloatingActionButton floatingActionButton = FloatingActionButton.this;
        int i5 = floatingActionButton.imagePadding;
        floatingActionButton.setPadding(i + i5, i2 + i5, i3 + i5, i4 + i5);
    }

    public void updateShapeElevation(float f) {
        MaterialShapeDrawable materialShapeDrawable = this.shapeDrawable;
        if (materialShapeDrawable != null) {
            MaterialShapeDrawable.MaterialShapeDrawableState materialShapeDrawableState = materialShapeDrawable.drawableState;
            if (materialShapeDrawableState.elevation != f) {
                materialShapeDrawableState.elevation = f;
                materialShapeDrawable.updateZ();
            }
        }
    }
}
