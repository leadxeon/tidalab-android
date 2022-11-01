package com.google.android.material.snackbar;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.SnackbarManager;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.tidalab.v2board.clash.foss.R;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public abstract class BaseTransientBottomBar<B extends BaseTransientBottomBar<B>> {
    public static final String TAG = "BaseTransientBottomBar";
    public final AccessibilityManager accessibilityManager;
    public final ContentViewCallback contentViewCallback;
    public final Context context;
    public int duration;
    public int extraBottomMarginAnchorView;
    public int extraBottomMarginGestureInset;
    public int extraBottomMarginWindowInset;
    public int extraLeftMarginWindowInset;
    public int extraRightMarginWindowInset;
    public Rect originalMargins;
    public final ViewGroup targetParent;
    public final SnackbarBaseLayout view;
    public static final int[] SNACKBAR_STYLE_ATTR = {R.attr.snackbarStyle};
    public static final Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                BaseTransientBottomBar baseTransientBottomBar = (BaseTransientBottomBar) message.obj;
                baseTransientBottomBar.view.setOnAttachStateChangeListener(new AnonymousClass7());
                if (baseTransientBottomBar.view.getParent() == null) {
                    ViewGroup.LayoutParams layoutParams = baseTransientBottomBar.view.getLayoutParams();
                    if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                        CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) layoutParams;
                        Behavior behavior = new Behavior();
                        BehaviorDelegate behaviorDelegate = behavior.delegate;
                        Objects.requireNonNull(behaviorDelegate);
                        behaviorDelegate.managerCallback = baseTransientBottomBar.managerCallback;
                        behavior.listener = new AnonymousClass9();
                        layoutParams2.setBehavior(behavior);
                        layoutParams2.insetEdge = 80;
                    }
                    baseTransientBottomBar.extraBottomMarginAnchorView = 0;
                    baseTransientBottomBar.updateMargins();
                    baseTransientBottomBar.view.setVisibility(4);
                    baseTransientBottomBar.targetParent.addView(baseTransientBottomBar.view);
                }
                SnackbarBaseLayout snackbarBaseLayout = baseTransientBottomBar.view;
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                if (snackbarBaseLayout.isLaidOut()) {
                    baseTransientBottomBar.showViewImpl();
                } else {
                    baseTransientBottomBar.view.setOnLayoutChangeListener(new AnonymousClass8());
                }
                return true;
            } else if (i != 1) {
                return false;
            } else {
                final BaseTransientBottomBar baseTransientBottomBar2 = (BaseTransientBottomBar) message.obj;
                final int i2 = message.arg1;
                if (!baseTransientBottomBar2.shouldAnimate() || baseTransientBottomBar2.view.getVisibility() != 0) {
                    baseTransientBottomBar2.onViewHidden(i2);
                } else if (baseTransientBottomBar2.view.getAnimationMode() == 1) {
                    ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
                    ofFloat.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
                    ofFloat.addUpdateListener(new AnonymousClass13());
                    ofFloat.setDuration(75L);
                    ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.12
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            BaseTransientBottomBar.this.onViewHidden(i2);
                        }
                    });
                    ofFloat.start();
                } else {
                    ValueAnimator valueAnimator = new ValueAnimator();
                    valueAnimator.setIntValues(0, baseTransientBottomBar2.getTranslationYBottom());
                    valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                    valueAnimator.setDuration(250L);
                    valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.17
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            BaseTransientBottomBar.this.onViewHidden(i2);
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationStart(Animator animator) {
                            SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) BaseTransientBottomBar.this.contentViewCallback;
                            snackbarContentLayout.messageView.setAlpha(1.0f);
                            long j = 180;
                            long j2 = 0;
                            snackbarContentLayout.messageView.animate().alpha(0.0f).setDuration(j).setStartDelay(j2).start();
                            if (snackbarContentLayout.actionView.getVisibility() == 0) {
                                snackbarContentLayout.actionView.setAlpha(1.0f);
                                snackbarContentLayout.actionView.animate().alpha(0.0f).setDuration(j).setStartDelay(j2).start();
                            }
                        }
                    });
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.18
                        public int previousAnimatedIntValue = 0;

                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                            int intValue = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                            Handler handler2 = BaseTransientBottomBar.handler;
                            BaseTransientBottomBar.this.view.setTranslationY(intValue);
                            this.previousAnimatedIntValue = intValue;
                        }
                    });
                    valueAnimator.start();
                }
                return true;
            }
        }
    });
    public final Runnable bottomMarginGestureInsetRunnable = new Runnable() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.3
        @Override // java.lang.Runnable
        public void run() {
            Context context;
            BaseTransientBottomBar baseTransientBottomBar = BaseTransientBottomBar.this;
            if (baseTransientBottomBar.view != null && (context = baseTransientBottomBar.context) != null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
                int i = displayMetrics.heightPixels;
                BaseTransientBottomBar baseTransientBottomBar2 = BaseTransientBottomBar.this;
                int[] iArr = new int[2];
                baseTransientBottomBar2.view.getLocationOnScreen(iArr);
                int height = (i - (baseTransientBottomBar2.view.getHeight() + iArr[1])) + ((int) BaseTransientBottomBar.this.view.getTranslationY());
                BaseTransientBottomBar baseTransientBottomBar3 = BaseTransientBottomBar.this;
                if (height < baseTransientBottomBar3.extraBottomMarginGestureInset) {
                    ViewGroup.LayoutParams layoutParams = baseTransientBottomBar3.view.getLayoutParams();
                    if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                        Handler handler2 = BaseTransientBottomBar.handler;
                        Log.w(BaseTransientBottomBar.TAG, "Unable to apply gesture inset because layout params are not MarginLayoutParams");
                        return;
                    }
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    int i2 = marginLayoutParams.bottomMargin;
                    BaseTransientBottomBar baseTransientBottomBar4 = BaseTransientBottomBar.this;
                    marginLayoutParams.bottomMargin = (baseTransientBottomBar4.extraBottomMarginGestureInset - height) + i2;
                    baseTransientBottomBar4.view.requestLayout();
                }
            }
        }
    };
    public SnackbarManager.Callback managerCallback = new SnackbarManager.Callback() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.6
        @Override // com.google.android.material.snackbar.SnackbarManager.Callback
        public void dismiss(int i) {
            Handler handler2 = BaseTransientBottomBar.handler;
            handler2.sendMessage(handler2.obtainMessage(1, i, 0, BaseTransientBottomBar.this));
        }

        @Override // com.google.android.material.snackbar.SnackbarManager.Callback
        public void show() {
            Handler handler2 = BaseTransientBottomBar.handler;
            handler2.sendMessage(handler2.obtainMessage(0, BaseTransientBottomBar.this));
        }
    };

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$13  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass13 implements ValueAnimator.AnimatorUpdateListener {
        public AnonymousClass13() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            BaseTransientBottomBar.this.view.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$7  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass7 implements OnAttachStateChangeListener {
        public AnonymousClass7() {
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$8  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass8 implements OnLayoutChangeListener {
        public AnonymousClass8() {
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$9  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass9 implements SwipeDismissBehavior.OnDismissListener {
        public AnonymousClass9() {
        }

        public void onDismiss(View view) {
            if (view.getParent() != null) {
                view.setVisibility(8);
            }
            BaseTransientBottomBar.this.dispatchDismiss(0);
        }
    }

    /* loaded from: classes.dex */
    public static class Behavior extends SwipeDismissBehavior<View> {
        public final BehaviorDelegate delegate = new BehaviorDelegate(this);

        @Override // com.google.android.material.behavior.SwipeDismissBehavior
        public boolean canSwipeDismissView(View view) {
            Objects.requireNonNull(this.delegate);
            return view instanceof SnackbarBaseLayout;
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            BehaviorDelegate behaviorDelegate = this.delegate;
            Objects.requireNonNull(behaviorDelegate);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 0) {
                if (actionMasked == 1 || actionMasked == 3) {
                    SnackbarManager.getInstance().restoreTimeoutIfPaused(behaviorDelegate.managerCallback);
                }
            } else if (coordinatorLayout.isPointInChildBounds(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                SnackbarManager.getInstance().pauseTimeout(behaviorDelegate.managerCallback);
            }
            return super.onInterceptTouchEvent(coordinatorLayout, view, motionEvent);
        }
    }

    /* loaded from: classes.dex */
    public static class BehaviorDelegate {
        public SnackbarManager.Callback managerCallback;

        public BehaviorDelegate(SwipeDismissBehavior<?> swipeDismissBehavior) {
            swipeDismissBehavior.alphaStartSwipeDistance = SwipeDismissBehavior.clamp(0.0f, 0.1f, 1.0f);
            swipeDismissBehavior.alphaEndSwipeDistance = SwipeDismissBehavior.clamp(0.0f, 0.6f, 1.0f);
            swipeDismissBehavior.swipeDirection = 0;
        }
    }

    /* loaded from: classes.dex */
    public interface OnAttachStateChangeListener {
    }

    /* loaded from: classes.dex */
    public interface OnLayoutChangeListener {
    }

    /* loaded from: classes.dex */
    public static class SnackbarBaseLayout extends FrameLayout {
        public static final View.OnTouchListener consumeAllTouchListener = new View.OnTouchListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout.1
            @Override // android.view.View.OnTouchListener
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        };
        public final float actionTextColorAlpha;
        public int animationMode;
        public final float backgroundOverlayColorAlpha;
        public ColorStateList backgroundTint;
        public PorterDuff.Mode backgroundTintMode;
        public OnAttachStateChangeListener onAttachStateChangeListener;
        public OnLayoutChangeListener onLayoutChangeListener;

        public SnackbarBaseLayout(Context context, AttributeSet attributeSet) {
            super(MaterialThemeOverlay.wrap(context, attributeSet, 0, 0), attributeSet);
            Drawable drawable;
            Context context2 = getContext();
            TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R$styleable.SnackbarLayout);
            if (obtainStyledAttributes.hasValue(6)) {
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                setElevation(obtainStyledAttributes.getDimensionPixelSize(6, 0));
            }
            this.animationMode = obtainStyledAttributes.getInt(2, 0);
            this.backgroundOverlayColorAlpha = obtainStyledAttributes.getFloat(3, 1.0f);
            setBackgroundTintList(R$style.getColorStateList(context2, obtainStyledAttributes, 4));
            setBackgroundTintMode(R$style.parseTintMode(obtainStyledAttributes.getInt(5, -1), PorterDuff.Mode.SRC_IN));
            this.actionTextColorAlpha = obtainStyledAttributes.getFloat(1, 1.0f);
            obtainStyledAttributes.recycle();
            setOnTouchListener(consumeAllTouchListener);
            setFocusable(true);
            if (getBackground() == null) {
                float dimension = getResources().getDimension(R.dimen.mtrl_snackbar_background_corner_radius);
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setShape(0);
                gradientDrawable.setCornerRadius(dimension);
                gradientDrawable.setColor(R$style.layer(R$style.getColor(this, R.attr.colorSurface), R$style.getColor(this, R.attr.colorOnSurface), getBackgroundOverlayColorAlpha()));
                if (this.backgroundTint != null) {
                    drawable = AppOpsManagerCompat.wrap(gradientDrawable);
                    drawable.setTintList(this.backgroundTint);
                } else {
                    drawable = AppOpsManagerCompat.wrap(gradientDrawable);
                }
                AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
                setBackground(drawable);
            }
        }

        public float getActionTextColorAlpha() {
            return this.actionTextColorAlpha;
        }

        public int getAnimationMode() {
            return this.animationMode;
        }

        public float getBackgroundOverlayColorAlpha() {
            return this.backgroundOverlayColorAlpha;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            WindowInsets rootWindowInsets;
            super.onAttachedToWindow();
            OnAttachStateChangeListener onAttachStateChangeListener = this.onAttachStateChangeListener;
            if (onAttachStateChangeListener != null) {
                AnonymousClass7 r0 = (AnonymousClass7) onAttachStateChangeListener;
                Objects.requireNonNull(r0);
                if (Build.VERSION.SDK_INT >= 29 && (rootWindowInsets = BaseTransientBottomBar.this.view.getRootWindowInsets()) != null) {
                    BaseTransientBottomBar.this.extraBottomMarginGestureInset = rootWindowInsets.getMandatorySystemGestureInsets().bottom;
                    BaseTransientBottomBar.this.updateMargins();
                }
            }
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            requestApplyInsets();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            boolean z;
            super.onDetachedFromWindow();
            OnAttachStateChangeListener onAttachStateChangeListener = this.onAttachStateChangeListener;
            if (onAttachStateChangeListener != null) {
                final AnonymousClass7 r0 = (AnonymousClass7) onAttachStateChangeListener;
                BaseTransientBottomBar baseTransientBottomBar = BaseTransientBottomBar.this;
                Objects.requireNonNull(baseTransientBottomBar);
                SnackbarManager instance = SnackbarManager.getInstance();
                SnackbarManager.Callback callback = baseTransientBottomBar.managerCallback;
                synchronized (instance.lock) {
                    if (!instance.isCurrentSnackbarLocked(callback) && !instance.isNextSnackbarLocked(callback)) {
                        z = false;
                    }
                    z = true;
                }
                if (z) {
                    BaseTransientBottomBar.handler.post(new Runnable() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.7.1
                        @Override // java.lang.Runnable
                        public void run() {
                            BaseTransientBottomBar.this.onViewHidden(3);
                        }
                    });
                }
            }
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            OnLayoutChangeListener onLayoutChangeListener = this.onLayoutChangeListener;
            if (onLayoutChangeListener != null) {
                AnonymousClass8 r1 = (AnonymousClass8) onLayoutChangeListener;
                BaseTransientBottomBar.this.view.setOnLayoutChangeListener(null);
                BaseTransientBottomBar.this.showViewImpl();
            }
        }

        public void setAnimationMode(int i) {
            this.animationMode = i;
        }

        @Override // android.view.View
        public void setBackground(Drawable drawable) {
            setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public void setBackgroundDrawable(Drawable drawable) {
            if (!(drawable == null || this.backgroundTint == null)) {
                drawable = AppOpsManagerCompat.wrap(drawable.mutate());
                drawable.setTintList(this.backgroundTint);
                drawable.setTintMode(this.backgroundTintMode);
            }
            super.setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public void setBackgroundTintList(ColorStateList colorStateList) {
            this.backgroundTint = colorStateList;
            if (getBackground() != null) {
                Drawable wrap = AppOpsManagerCompat.wrap(getBackground().mutate());
                wrap.setTintList(colorStateList);
                wrap.setTintMode(this.backgroundTintMode);
                if (wrap != getBackground()) {
                    super.setBackgroundDrawable(wrap);
                }
            }
        }

        @Override // android.view.View
        public void setBackgroundTintMode(PorterDuff.Mode mode) {
            this.backgroundTintMode = mode;
            if (getBackground() != null) {
                Drawable wrap = AppOpsManagerCompat.wrap(getBackground().mutate());
                wrap.setTintMode(mode);
                if (wrap != getBackground()) {
                    super.setBackgroundDrawable(wrap);
                }
            }
        }

        public void setOnAttachStateChangeListener(OnAttachStateChangeListener onAttachStateChangeListener) {
            this.onAttachStateChangeListener = onAttachStateChangeListener;
        }

        @Override // android.view.View
        public void setOnClickListener(View.OnClickListener onClickListener) {
            setOnTouchListener(onClickListener != null ? null : consumeAllTouchListener);
            super.setOnClickListener(onClickListener);
        }

        public void setOnLayoutChangeListener(OnLayoutChangeListener onLayoutChangeListener) {
            this.onLayoutChangeListener = onLayoutChangeListener;
        }
    }

    public BaseTransientBottomBar(Context context, ViewGroup viewGroup, View view, ContentViewCallback contentViewCallback) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null parent");
        } else if (view == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null content");
        } else if (contentViewCallback != null) {
            this.targetParent = viewGroup;
            this.contentViewCallback = contentViewCallback;
            this.context = context;
            ThemeEnforcement.checkTheme(context, ThemeEnforcement.APPCOMPAT_CHECK_ATTRS, "Theme.AppCompat");
            LayoutInflater from = LayoutInflater.from(context);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(SNACKBAR_STYLE_ATTR);
            int resourceId = obtainStyledAttributes.getResourceId(0, -1);
            obtainStyledAttributes.recycle();
            SnackbarBaseLayout snackbarBaseLayout = (SnackbarBaseLayout) from.inflate(resourceId != -1 ? R.layout.mtrl_layout_snackbar : R.layout.design_layout_snackbar, viewGroup, false);
            this.view = snackbarBaseLayout;
            if (view instanceof SnackbarContentLayout) {
                SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) view;
                float actionTextColorAlpha = snackbarBaseLayout.getActionTextColorAlpha();
                if (actionTextColorAlpha != 1.0f) {
                    snackbarContentLayout.actionView.setTextColor(R$style.layer(R$style.getColor(snackbarContentLayout, R.attr.colorSurface), snackbarContentLayout.actionView.getCurrentTextColor(), actionTextColorAlpha));
                }
            }
            snackbarBaseLayout.addView(view);
            ViewGroup.LayoutParams layoutParams = snackbarBaseLayout.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                this.originalMargins = new Rect(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            }
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            snackbarBaseLayout.setAccessibilityLiveRegion(1);
            snackbarBaseLayout.setImportantForAccessibility(1);
            snackbarBaseLayout.setFitsSystemWindows(true);
            ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(snackbarBaseLayout, new OnApplyWindowInsetsListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.4
                @Override // androidx.core.view.OnApplyWindowInsetsListener
                public WindowInsetsCompat onApplyWindowInsets(View view2, WindowInsetsCompat windowInsetsCompat) {
                    BaseTransientBottomBar.this.extraBottomMarginWindowInset = windowInsetsCompat.getSystemWindowInsetBottom();
                    BaseTransientBottomBar.this.extraLeftMarginWindowInset = windowInsetsCompat.getSystemWindowInsetLeft();
                    BaseTransientBottomBar.this.extraRightMarginWindowInset = windowInsetsCompat.getSystemWindowInsetRight();
                    BaseTransientBottomBar.this.updateMargins();
                    return windowInsetsCompat;
                }
            });
            ViewCompat.setAccessibilityDelegate(snackbarBaseLayout, new AccessibilityDelegateCompat() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.5
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfoCompat.mInfo);
                    accessibilityNodeInfoCompat.mInfo.addAction(1048576);
                    accessibilityNodeInfoCompat.mInfo.setDismissable(true);
                }

                @Override // androidx.core.view.AccessibilityDelegateCompat
                public boolean performAccessibilityAction(View view2, int i, Bundle bundle) {
                    if (i != 1048576) {
                        return super.performAccessibilityAction(view2, i, bundle);
                    }
                    BaseTransientBottomBar.this.dismiss();
                    return true;
                }
            });
            this.accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        } else {
            throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
        }
    }

    public void dismiss() {
        dispatchDismiss(3);
    }

    public void dispatchDismiss(int i) {
        SnackbarManager instance = SnackbarManager.getInstance();
        SnackbarManager.Callback callback = this.managerCallback;
        synchronized (instance.lock) {
            if (instance.isCurrentSnackbarLocked(callback)) {
                instance.cancelSnackbarLocked(instance.currentSnackbar, i);
            } else if (instance.isNextSnackbarLocked(callback)) {
                instance.cancelSnackbarLocked(instance.nextSnackbar, i);
            }
        }
    }

    public final int getTranslationYBottom() {
        int height = this.view.getHeight();
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? height + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin : height;
    }

    public void onViewHidden(int i) {
        SnackbarManager instance = SnackbarManager.getInstance();
        SnackbarManager.Callback callback = this.managerCallback;
        synchronized (instance.lock) {
            if (instance.isCurrentSnackbarLocked(callback)) {
                instance.currentSnackbar = null;
                if (instance.nextSnackbar != null) {
                    instance.showNextSnackbarLocked();
                }
            }
        }
        ViewParent parent = this.view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.view);
        }
    }

    public void onViewShown() {
        SnackbarManager instance = SnackbarManager.getInstance();
        SnackbarManager.Callback callback = this.managerCallback;
        synchronized (instance.lock) {
            if (instance.isCurrentSnackbarLocked(callback)) {
                instance.scheduleTimeoutLocked(instance.currentSnackbar);
            }
        }
    }

    public boolean shouldAnimate() {
        AccessibilityManager accessibilityManager = this.accessibilityManager;
        if (accessibilityManager == null) {
            return true;
        }
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(1);
        return enabledAccessibilityServiceList != null && enabledAccessibilityServiceList.isEmpty();
    }

    public final void showViewImpl() {
        if (shouldAnimate()) {
            this.view.post(new Runnable() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.10
                @Override // java.lang.Runnable
                public void run() {
                    SnackbarBaseLayout snackbarBaseLayout = BaseTransientBottomBar.this.view;
                    if (snackbarBaseLayout != null) {
                        if (snackbarBaseLayout.getParent() != null) {
                            BaseTransientBottomBar.this.view.setVisibility(0);
                        }
                        if (BaseTransientBottomBar.this.view.getAnimationMode() == 1) {
                            final BaseTransientBottomBar baseTransientBottomBar = BaseTransientBottomBar.this;
                            Objects.requireNonNull(baseTransientBottomBar);
                            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                            ofFloat.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
                            ofFloat.addUpdateListener(new AnonymousClass13());
                            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.8f, 1.0f);
                            ofFloat2.setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
                            ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.14
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                                    BaseTransientBottomBar.this.view.setScaleX(floatValue);
                                    BaseTransientBottomBar.this.view.setScaleY(floatValue);
                                }
                            });
                            AnimatorSet animatorSet = new AnimatorSet();
                            animatorSet.playTogether(ofFloat, ofFloat2);
                            animatorSet.setDuration(150L);
                            animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.11
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator) {
                                    BaseTransientBottomBar.this.onViewShown();
                                }
                            });
                            animatorSet.start();
                            return;
                        }
                        final BaseTransientBottomBar baseTransientBottomBar2 = BaseTransientBottomBar.this;
                        int translationYBottom = baseTransientBottomBar2.getTranslationYBottom();
                        baseTransientBottomBar2.view.setTranslationY(translationYBottom);
                        ValueAnimator valueAnimator = new ValueAnimator();
                        valueAnimator.setIntValues(translationYBottom, 0);
                        valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                        valueAnimator.setDuration(250L);
                        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.15
                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public void onAnimationEnd(Animator animator) {
                                BaseTransientBottomBar.this.onViewShown();
                            }

                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public void onAnimationStart(Animator animator) {
                                SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) BaseTransientBottomBar.this.contentViewCallback;
                                snackbarContentLayout.messageView.setAlpha(0.0f);
                                long j = 180;
                                long j2 = 70;
                                snackbarContentLayout.messageView.animate().alpha(1.0f).setDuration(j).setStartDelay(j2).start();
                                if (snackbarContentLayout.actionView.getVisibility() == 0) {
                                    snackbarContentLayout.actionView.setAlpha(0.0f);
                                    snackbarContentLayout.actionView.animate().alpha(1.0f).setDuration(j).setStartDelay(j2).start();
                                }
                            }
                        });
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(translationYBottom) { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.16
                            public int previousAnimatedIntValue;
                            public final /* synthetic */ int val$translationYBottom;

                            {
                                this.val$translationYBottom = translationYBottom;
                                this.previousAnimatedIntValue = translationYBottom;
                            }

                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                                int intValue = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                                Handler handler2 = BaseTransientBottomBar.handler;
                                BaseTransientBottomBar.this.view.setTranslationY(intValue);
                                this.previousAnimatedIntValue = intValue;
                            }
                        });
                        valueAnimator.start();
                    }
                }
            });
            return;
        }
        if (this.view.getParent() != null) {
            this.view.setVisibility(0);
        }
        onViewShown();
    }

    public final void updateMargins() {
        Rect rect;
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams) || (rect = this.originalMargins) == null) {
            Log.w(TAG, "Unable to update margins because layout params are not MarginLayoutParams");
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.bottomMargin = rect.bottom + this.extraBottomMarginWindowInset;
        marginLayoutParams.leftMargin = rect.left + this.extraLeftMarginWindowInset;
        marginLayoutParams.rightMargin = rect.right + this.extraRightMarginWindowInset;
        this.view.requestLayout();
        if (Build.VERSION.SDK_INT >= 29) {
            boolean z = false;
            if (this.extraBottomMarginGestureInset > 0) {
                ViewGroup.LayoutParams layoutParams2 = this.view.getLayoutParams();
                if ((layoutParams2 instanceof CoordinatorLayout.LayoutParams) && (((CoordinatorLayout.LayoutParams) layoutParams2).mBehavior instanceof SwipeDismissBehavior)) {
                    z = true;
                }
            }
            if (z) {
                this.view.removeCallbacks(this.bottomMarginGestureInsetRunnable);
                this.view.post(this.bottomMarginGestureInsetRunnable);
            }
        }
    }
}
