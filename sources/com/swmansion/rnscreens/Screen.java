package com.swmansion.rnscreens;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.TextView;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.UIManagerModule;
import com.swmansion.rnscreens.ScreenFragment;
import java.util.Objects;
/* compiled from: Screen.kt */
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public final class Screen extends ViewGroup {
    public static final View.OnAttachStateChangeListener sShowSoftKeyboardOnAttach = new View.OnAttachStateChangeListener() { // from class: com.swmansion.rnscreens.Screen$Companion$sShowSoftKeyboardOnAttach$1
        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            Object systemService = view.getContext().getSystemService("input_method");
            Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
            ((InputMethodManager) systemService).showSoftInput(view, 0);
            view.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    };
    public ActivityState activityState;
    public ScreenContainer<?> container;
    public ScreenFragment fragment;
    public Boolean isStatusBarAnimated;
    public Integer mStatusBarColor;
    public Boolean mStatusBarHidden;
    public String mStatusBarStyle;
    public Boolean mStatusBarTranslucent;
    public boolean mTransitioning;
    public Integer screenOrientation;
    public StackPresentation stackPresentation = StackPresentation.PUSH;
    public ReplaceAnimation replaceAnimation = ReplaceAnimation.POP;
    public StackAnimation stackAnimation = StackAnimation.DEFAULT;
    public boolean isGestureEnabled = true;

    /* compiled from: Screen.kt */
    /* loaded from: classes.dex */
    public enum ActivityState {
        INACTIVE,
        TRANSITIONING_OR_BELOW_TOP,
        ON_TOP
    }

    /* compiled from: Screen.kt */
    /* loaded from: classes.dex */
    public enum ReplaceAnimation {
        PUSH,
        POP
    }

    /* compiled from: Screen.kt */
    /* loaded from: classes.dex */
    public enum StackAnimation {
        DEFAULT,
        NONE,
        FADE,
        SLIDE_FROM_BOTTOM,
        SLIDE_FROM_RIGHT,
        SLIDE_FROM_LEFT,
        FADE_FROM_BOTTOM
    }

    /* compiled from: Screen.kt */
    /* loaded from: classes.dex */
    public enum StackPresentation {
        PUSH,
        MODAL,
        TRANSPARENT_MODAL
    }

    /* compiled from: Screen.kt */
    /* loaded from: classes.dex */
    public enum WindowTraits {
        ORIENTATION,
        COLOR,
        STYLE,
        TRANSLUCENT,
        HIDDEN,
        ANIMATED
    }

    public Screen(ReactContext reactContext) {
        super(reactContext);
        setLayoutParams(new WindowManager.LayoutParams(2));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
    }

    public final ActivityState getActivityState() {
        return this.activityState;
    }

    public final ScreenContainer<?> getContainer() {
        return this.container;
    }

    public final ScreenFragment getFragment() {
        return this.fragment;
    }

    public final ScreenStackHeaderConfig getHeaderConfig() {
        View childAt = getChildAt(0);
        if (childAt instanceof ScreenStackHeaderConfig) {
            return (ScreenStackHeaderConfig) childAt;
        }
        return null;
    }

    public final ReplaceAnimation getReplaceAnimation() {
        return this.replaceAnimation;
    }

    public final Integer getScreenOrientation() {
        return this.screenOrientation;
    }

    public final StackAnimation getStackAnimation() {
        return this.stackAnimation;
    }

    public final StackPresentation getStackPresentation() {
        return this.stackPresentation;
    }

    public final Integer getStatusBarColor() {
        return this.mStatusBarColor;
    }

    public final String getStatusBarStyle() {
        return this.mStatusBarStyle;
    }

    public final boolean hasWebView(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        if (childCount > 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof WebView) {
                    return true;
                }
                if ((childAt instanceof ViewGroup) && hasWebView((ViewGroup) childAt)) {
                    return true;
                }
                if (i2 >= childCount) {
                    break;
                }
                i = i2;
            }
        }
        return false;
    }

    @Override // android.view.View
    public void onAnimationEnd() {
        super.onAnimationEnd();
        ScreenFragment screenFragment = this.fragment;
        if (screenFragment != null) {
            screenFragment.onViewAnimationEnd();
        }
    }

    @Override // android.view.View
    public void onAnimationStart() {
        super.onAnimationStart();
        ScreenFragment screenFragment = this.fragment;
        if (screenFragment != null) {
            if (screenFragment.isResumed()) {
                UiThreadUtil.runOnUiThread(new $$Lambda$ScreenFragment$yHXMMuAS8mqwKNxEUYE_hEkkivw(screenFragment));
            } else {
                screenFragment.dispatchEvent(ScreenFragment.ScreenLifecycleEvent.WillDisappear, screenFragment);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        View focusedChild = getFocusedChild();
        if (focusedChild != null) {
            while (focusedChild instanceof ViewGroup) {
                focusedChild = ((ViewGroup) focusedChild).getFocusedChild();
            }
            if ((focusedChild instanceof TextView) && ((TextView) focusedChild).getShowSoftInputOnFocus()) {
                focusedChild.addOnAttachStateChangeListener(sShowSoftKeyboardOnAttach);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            final int i5 = i3 - i;
            final int i6 = i4 - i2;
            Context context = getContext();
            Objects.requireNonNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
            final ReactContext reactContext = (ReactContext) context;
            reactContext.runOnNativeModulesQueueThread(new GuardedRunnable(this, i5, i6) { // from class: com.swmansion.rnscreens.Screen$onLayout$1
                public final /* synthetic */ int $height;
                public final /* synthetic */ int $width;
                public final /* synthetic */ Screen this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(ReactContext.this);
                    this.this$0 = this;
                    this.$width = i5;
                    this.$height = i6;
                }

                @Override // com.facebook.react.bridge.GuardedRunnable
                public void runGuarded() {
                    UIManagerModule uIManagerModule = (UIManagerModule) ReactContext.this.getNativeModule(UIManagerModule.class);
                    if (uIManagerModule != null) {
                        uIManagerModule.updateNodeSize(this.this$0.getId(), this.$width, this.$height);
                    }
                }
            });
        }
    }

    public final void setActivityState(ActivityState activityState) {
        if (activityState != this.activityState) {
            this.activityState = activityState;
            ScreenContainer<?> screenContainer = this.container;
            if (screenContainer != null) {
                screenContainer.markUpdated();
            }
        }
    }

    public final void setContainer(ScreenContainer<?> screenContainer) {
        this.container = screenContainer;
    }

    public final void setFragment(ScreenFragment screenFragment) {
        this.fragment = screenFragment;
    }

    public final void setGestureEnabled(boolean z) {
        this.isGestureEnabled = z;
    }

    @Override // android.view.View
    public void setLayerType(int i, Paint paint) {
    }

    public final void setReplaceAnimation(ReplaceAnimation replaceAnimation) {
        this.replaceAnimation = replaceAnimation;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void setScreenOrientation(String str) {
        int i;
        if (str == null) {
            this.screenOrientation = null;
            return;
        }
        ScreenWindowTraits.mDidSetOrientation = true;
        switch (str.hashCode()) {
            case -1894896954:
                if (str.equals("portrait_down")) {
                    i = 9;
                    break;
                }
                i = -1;
                break;
            case 96673:
                if (str.equals("all")) {
                    i = 10;
                    break;
                }
                i = -1;
                break;
            case 729267099:
                if (str.equals("portrait")) {
                    i = 7;
                    break;
                }
                i = -1;
                break;
            case 1430647483:
                if (str.equals("landscape")) {
                    i = 6;
                    break;
                }
                i = -1;
                break;
            case 1651658175:
                if (str.equals("portrait_up")) {
                    i = 1;
                    break;
                }
                i = -1;
                break;
            case 1730732811:
                if (str.equals("landscape_left")) {
                    i = 8;
                    break;
                }
                i = -1;
                break;
            case 2118770584:
                if (str.equals("landscape_right")) {
                    i = 0;
                    break;
                }
                i = -1;
                break;
            default:
                i = -1;
                break;
        }
        this.screenOrientation = i;
        ScreenFragment screenFragment = this.fragment;
        if (screenFragment != null) {
            ScreenWindowTraits.setOrientation$react_native_screens_release(this, screenFragment.tryGetActivity());
        }
    }

    public final void setStackAnimation(StackAnimation stackAnimation) {
        this.stackAnimation = stackAnimation;
    }

    public final void setStackPresentation(StackPresentation stackPresentation) {
        this.stackPresentation = stackPresentation;
    }

    public final void setStatusBarAnimated(Boolean bool) {
        this.isStatusBarAnimated = bool;
    }

    public final void setStatusBarColor(Integer num) {
        if (num != null) {
            ScreenWindowTraits.mDidSetStatusBarAppearance = true;
        }
        this.mStatusBarColor = num;
        ScreenFragment screenFragment = this.fragment;
        if (screenFragment != null) {
            ScreenWindowTraits.setColor$react_native_screens_release(this, screenFragment.tryGetActivity(), screenFragment.tryGetContext());
        }
    }

    public final void setStatusBarHidden(Boolean bool) {
        if (bool != null) {
            ScreenWindowTraits.mDidSetStatusBarAppearance = true;
        }
        this.mStatusBarHidden = bool;
        ScreenFragment screenFragment = this.fragment;
        if (screenFragment != null) {
            ScreenWindowTraits.setHidden$react_native_screens_release(this, screenFragment.tryGetActivity());
        }
    }

    public final void setStatusBarStyle(String str) {
        if (str != null) {
            ScreenWindowTraits.mDidSetStatusBarAppearance = true;
        }
        this.mStatusBarStyle = str;
        ScreenFragment screenFragment = this.fragment;
        if (screenFragment != null) {
            ScreenWindowTraits.setStyle$react_native_screens_release(this, screenFragment.tryGetActivity(), screenFragment.tryGetContext());
        }
    }

    public final void setStatusBarTranslucent(Boolean bool) {
        if (bool != null) {
            ScreenWindowTraits.mDidSetStatusBarAppearance = true;
        }
        this.mStatusBarTranslucent = bool;
        ScreenFragment screenFragment = this.fragment;
        if (screenFragment != null) {
            ScreenWindowTraits.setTranslucent$react_native_screens_release(this, screenFragment.tryGetActivity(), screenFragment.tryGetContext());
        }
    }

    public final void setTransitioning(boolean z) {
        if (this.mTransitioning != z) {
            this.mTransitioning = z;
            boolean hasWebView = hasWebView(this);
            int i = 2;
            if (!hasWebView || getLayerType() == 2) {
                if (!z || hasWebView) {
                    i = 0;
                }
                super.setLayerType(i, null);
            }
        }
    }
}
