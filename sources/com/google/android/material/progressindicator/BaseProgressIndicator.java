package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.reactnativecommunity.webview.RNCWebViewManager;
import com.tidalab.v2board.clash.foss.R;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes.dex */
public abstract class BaseProgressIndicator<S extends BaseProgressIndicatorSpec> extends ProgressBar {
    public final int minHideDelay;
    public S spec;
    public int storedProgress;
    public boolean storedProgressAnimated;
    public boolean isIndeterminateModeChangeRequested = false;
    public int visibilityAfterHide = 4;
    public final Runnable delayedShow = new Runnable() { // from class: com.google.android.material.progressindicator.BaseProgressIndicator.1
        @Override // java.lang.Runnable
        public void run() {
            BaseProgressIndicator baseProgressIndicator = BaseProgressIndicator.this;
            if (baseProgressIndicator.minHideDelay > 0) {
                SystemClock.uptimeMillis();
            }
            baseProgressIndicator.setVisibility(0);
        }
    };
    public final Runnable delayedHide = new Runnable() { // from class: com.google.android.material.progressindicator.BaseProgressIndicator.2
        @Override // java.lang.Runnable
        public void run() {
            BaseProgressIndicator baseProgressIndicator = BaseProgressIndicator.this;
            boolean z = false;
            ((DrawableWithAnimatedVisibilityChange) baseProgressIndicator.getCurrentDrawable()).setVisible(false, false, true);
            if ((baseProgressIndicator.getProgressDrawable() == null || !baseProgressIndicator.getProgressDrawable().isVisible()) && (baseProgressIndicator.getIndeterminateDrawable() == null || !baseProgressIndicator.getIndeterminateDrawable().isVisible())) {
                z = true;
            }
            if (z) {
                baseProgressIndicator.setVisibility(4);
            }
            Objects.requireNonNull(BaseProgressIndicator.this);
        }
    };
    public final Animatable2Compat$AnimationCallback switchIndeterminateModeCallback = new Animatable2Compat$AnimationCallback() { // from class: com.google.android.material.progressindicator.BaseProgressIndicator.3
        @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback
        public void onAnimationEnd(Drawable drawable) {
            BaseProgressIndicator.this.setIndeterminate(false);
            BaseProgressIndicator.this.setProgressCompat(0, false);
            BaseProgressIndicator baseProgressIndicator = BaseProgressIndicator.this;
            baseProgressIndicator.setProgressCompat(baseProgressIndicator.storedProgress, baseProgressIndicator.storedProgressAnimated);
        }
    };
    public final Animatable2Compat$AnimationCallback hideAnimationCallback = new Animatable2Compat$AnimationCallback() { // from class: com.google.android.material.progressindicator.BaseProgressIndicator.4
        @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback
        public void onAnimationEnd(Drawable drawable) {
            BaseProgressIndicator baseProgressIndicator = BaseProgressIndicator.this;
            if (!baseProgressIndicator.isIndeterminateModeChangeRequested) {
                baseProgressIndicator.setVisibility(baseProgressIndicator.visibilityAfterHide);
            }
        }
    };
    public AnimatorDurationScaleProvider animatorDurationScaleProvider = new AnimatorDurationScaleProvider();
    public boolean isParentDoneInitializing = true;

    public BaseProgressIndicator(Context context, AttributeSet attributeSet, int i, int i2) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i, 2131886797), attributeSet, i);
        Context context2 = getContext();
        this.spec = new LinearProgressIndicatorSpec(context2, attributeSet);
        int[] iArr = R$styleable.BaseProgressIndicator;
        ThemeEnforcement.checkCompatibleTheme(context2, attributeSet, i, i2);
        ThemeEnforcement.checkTextAppearance(context2, attributeSet, iArr, i, i2, new int[0]);
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, i, i2);
        obtainStyledAttributes.getInt(5, -1);
        this.minHideDelay = Math.min(obtainStyledAttributes.getInt(3, -1), (int) RNCWebViewManager.COMMAND_CLEAR_FORM_DATA);
        obtainStyledAttributes.recycle();
    }

    private DrawingDelegate<S> getCurrentDrawingDelegate() {
        if (isIndeterminate()) {
            if (getIndeterminateDrawable() == null) {
                return null;
            }
            return getIndeterminateDrawable().drawingDelegate;
        } else if (getProgressDrawable() == null) {
            return null;
        } else {
            return getProgressDrawable().drawingDelegate;
        }
    }

    public void applyNewVisibility(boolean z) {
        if (this.isParentDoneInitializing) {
            ((DrawableWithAnimatedVisibilityChange) getCurrentDrawable()).setVisible(visibleToUser(), false, z);
        }
    }

    @Override // android.widget.ProgressBar
    public Drawable getCurrentDrawable() {
        return isIndeterminate() ? getIndeterminateDrawable() : getProgressDrawable();
    }

    public int getHideAnimationBehavior() {
        return this.spec.hideAnimationBehavior;
    }

    public int[] getIndicatorColor() {
        return this.spec.indicatorColors;
    }

    public int getShowAnimationBehavior() {
        return this.spec.showAnimationBehavior;
    }

    public int getTrackColor() {
        return this.spec.trackColor;
    }

    public int getTrackCornerRadius() {
        return this.spec.trackCornerRadius;
    }

    public int getTrackThickness() {
        return this.spec.trackThickness;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        if (getCurrentDrawable() != null) {
            getCurrentDrawable().invalidateSelf();
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!(getProgressDrawable() == null || getIndeterminateDrawable() == null)) {
            getIndeterminateDrawable().animatorDelegate.registerAnimatorsCompleteCallback(this.switchIndeterminateModeCallback);
        }
        if (getProgressDrawable() != null) {
            getProgressDrawable().registerAnimationCallback(this.hideAnimationCallback);
        }
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().registerAnimationCallback(this.hideAnimationCallback);
        }
        if (visibleToUser()) {
            if (this.minHideDelay > 0) {
                SystemClock.uptimeMillis();
            }
            setVisibility(0);
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.delayedHide);
        removeCallbacks(this.delayedShow);
        ((DrawableWithAnimatedVisibilityChange) getCurrentDrawable()).hideNow();
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().unregisterAnimationCallback(this.hideAnimationCallback);
            getIndeterminateDrawable().animatorDelegate.unregisterAnimatorsCompleteCallback();
        }
        if (getProgressDrawable() != null) {
            getProgressDrawable().unregisterAnimationCallback(this.hideAnimationCallback);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public synchronized void onDraw(Canvas canvas) {
        int save = canvas.save();
        if (!(getPaddingLeft() == 0 && getPaddingTop() == 0)) {
            canvas.translate(getPaddingLeft(), getPaddingTop());
        }
        if (!(getPaddingRight() == 0 && getPaddingBottom() == 0)) {
            canvas.clipRect(0, 0, getWidth() - (getPaddingLeft() + getPaddingRight()), getHeight() - (getPaddingTop() + getPaddingBottom()));
        }
        getCurrentDrawable().draw(canvas);
        canvas.restoreToCount(save);
    }

    @Override // android.widget.ProgressBar, android.view.View
    public synchronized void onMeasure(int i, int i2) {
        int i3;
        super.onMeasure(i, i2);
        DrawingDelegate<S> currentDrawingDelegate = getCurrentDrawingDelegate();
        if (currentDrawingDelegate != null) {
            int preferredHeight = currentDrawingDelegate.getPreferredHeight();
            int measuredWidth = getMeasuredWidth();
            if (preferredHeight < 0) {
                i3 = getMeasuredHeight();
            } else {
                i3 = preferredHeight + getPaddingTop() + getPaddingBottom();
            }
            setMeasuredDimension(measuredWidth, i3);
        }
    }

    @Override // android.view.View
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        applyNewVisibility(i == 0);
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        applyNewVisibility(false);
    }

    public void setAnimatorDurationScaleProvider(AnimatorDurationScaleProvider animatorDurationScaleProvider) {
        this.animatorDurationScaleProvider = animatorDurationScaleProvider;
        if (getProgressDrawable() != null) {
            getProgressDrawable().animatorDurationScaleProvider = animatorDurationScaleProvider;
        }
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().animatorDurationScaleProvider = animatorDurationScaleProvider;
        }
    }

    public void setHideAnimationBehavior(int i) {
        this.spec.hideAnimationBehavior = i;
        invalidate();
    }

    @Override // android.widget.ProgressBar
    public synchronized void setIndeterminate(boolean z) {
        if (z != isIndeterminate()) {
            if (visibleToUser() && z) {
                throw new IllegalStateException("Cannot switch to indeterminate mode while the progress indicator is visible.");
            }
            DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange = (DrawableWithAnimatedVisibilityChange) getCurrentDrawable();
            if (drawableWithAnimatedVisibilityChange != null) {
                drawableWithAnimatedVisibilityChange.hideNow();
            }
            super.setIndeterminate(z);
            DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange2 = (DrawableWithAnimatedVisibilityChange) getCurrentDrawable();
            if (drawableWithAnimatedVisibilityChange2 != null) {
                drawableWithAnimatedVisibilityChange2.setVisible(visibleToUser(), false, false);
            }
            this.isIndeterminateModeChangeRequested = false;
        }
    }

    @Override // android.widget.ProgressBar
    public void setIndeterminateDrawable(Drawable drawable) {
        if (drawable == null) {
            super.setIndeterminateDrawable(null);
        } else if (drawable instanceof IndeterminateDrawable) {
            ((DrawableWithAnimatedVisibilityChange) drawable).hideNow();
            super.setIndeterminateDrawable(drawable);
        } else {
            throw new IllegalArgumentException("Cannot set framework drawable as indeterminate drawable.");
        }
    }

    public void setIndicatorColor(int... iArr) {
        if (iArr.length == 0) {
            iArr = new int[]{R$style.getColor(getContext(), R.attr.colorPrimary, -1)};
        }
        if (!Arrays.equals(getIndicatorColor(), iArr)) {
            this.spec.indicatorColors = iArr;
            getIndeterminateDrawable().animatorDelegate.invalidateSpecValues();
            invalidate();
        }
    }

    @Override // android.widget.ProgressBar
    public synchronized void setProgress(int i) {
        if (!isIndeterminate()) {
            setProgressCompat(i, false);
        }
    }

    public void setProgressCompat(int i, boolean z) {
        if (!isIndeterminate()) {
            super.setProgress(i);
            if (getProgressDrawable() != null && !z) {
                getProgressDrawable().jumpToCurrentState();
            }
        } else if (getProgressDrawable() != null) {
            this.storedProgress = i;
            this.storedProgressAnimated = z;
            this.isIndeterminateModeChangeRequested = true;
            if (!getIndeterminateDrawable().isVisible() || this.animatorDurationScaleProvider.getSystemAnimatorDurationScale(getContext().getContentResolver()) == 0.0f) {
                this.switchIndeterminateModeCallback.onAnimationEnd(getIndeterminateDrawable());
            } else {
                getIndeterminateDrawable().animatorDelegate.requestCancelAnimatorAfterCurrentCycle();
            }
        }
    }

    @Override // android.widget.ProgressBar
    public void setProgressDrawable(Drawable drawable) {
        if (drawable == null) {
            super.setProgressDrawable(null);
        } else if (drawable instanceof DeterminateDrawable) {
            DeterminateDrawable determinateDrawable = (DeterminateDrawable) drawable;
            determinateDrawable.hideNow();
            super.setProgressDrawable(determinateDrawable);
            determinateDrawable.setLevel((int) ((getProgress() / getMax()) * 10000.0f));
        } else {
            throw new IllegalArgumentException("Cannot set framework drawable as progress drawable.");
        }
    }

    public void setShowAnimationBehavior(int i) {
        this.spec.showAnimationBehavior = i;
        invalidate();
    }

    public void setTrackColor(int i) {
        S s = this.spec;
        if (s.trackColor != i) {
            s.trackColor = i;
            invalidate();
        }
    }

    public void setTrackCornerRadius(int i) {
        S s = this.spec;
        if (s.trackCornerRadius != i) {
            s.trackCornerRadius = Math.min(i, s.trackThickness / 2);
        }
    }

    public void setTrackThickness(int i) {
        S s = this.spec;
        if (s.trackThickness != i) {
            s.trackThickness = i;
            requestLayout();
        }
    }

    public void setVisibilityAfterHide(int i) {
        if (i == 0 || i == 4 || i == 8) {
            this.visibilityAfterHide = i;
            return;
        }
        throw new IllegalArgumentException("The component's visibility must be one of VISIBLE, INVISIBLE, and GONE defined in View.");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0022, code lost:
        if (getWindowVisibility() == 0) goto L_0x0024;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0024, code lost:
        r0 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean visibleToUser() {
        /*
            r4 = this;
            java.util.concurrent.atomic.AtomicInteger r0 = androidx.core.view.ViewCompat.sNextGeneratedId
            boolean r0 = r4.isAttachedToWindow()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0034
            int r0 = r4.getWindowVisibility()
            if (r0 != 0) goto L_0x0034
            r0 = r4
        L_0x0011:
            int r3 = r0.getVisibility()
            if (r3 == 0) goto L_0x0018
            goto L_0x0026
        L_0x0018:
            android.view.ViewParent r0 = r0.getParent()
            if (r0 != 0) goto L_0x0028
            int r0 = r4.getWindowVisibility()
            if (r0 != 0) goto L_0x0026
        L_0x0024:
            r0 = 1
            goto L_0x002d
        L_0x0026:
            r0 = 0
            goto L_0x002d
        L_0x0028:
            boolean r3 = r0 instanceof android.view.View
            if (r3 != 0) goto L_0x0031
            goto L_0x0024
        L_0x002d:
            if (r0 == 0) goto L_0x0034
            r1 = 1
            goto L_0x0034
        L_0x0031:
            android.view.View r0 = (android.view.View) r0
            goto L_0x0011
        L_0x0034:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.progressindicator.BaseProgressIndicator.visibleToUser():boolean");
    }

    @Override // android.widget.ProgressBar
    public IndeterminateDrawable<S> getIndeterminateDrawable() {
        return (IndeterminateDrawable) super.getIndeterminateDrawable();
    }

    @Override // android.widget.ProgressBar
    public DeterminateDrawable<S> getProgressDrawable() {
        return (DeterminateDrawable) super.getProgressDrawable();
    }
}
