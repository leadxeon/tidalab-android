package com.swmansion.rnscreens;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import com.facebook.react.R$style;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.PixelUtil;
import com.google.android.material.appbar.AppBarLayout;
import com.swmansion.rnscreens.ScreenStackHeaderSubview;
import com.tidalab.v2board.clash.foss.R;
import java.util.ArrayList;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ScreenStackHeaderConfig.kt */
/* loaded from: classes.dex */
public final class ScreenStackHeaderConfig extends ViewGroup {
    public boolean mBackButtonInCustomView;
    public Integer mBackgroundColor;
    public final int mDefaultStartInset;
    public final int mDefaultStartInsetWithNavigation;
    public boolean mDestroyed;
    public String mDirection;
    public boolean mIsAttachedToWindow;
    public boolean mIsBackButtonHidden;
    public boolean mIsHidden;
    public boolean mIsShadowHidden;
    public boolean mIsTranslucent;
    public int mTintColor;
    public String mTitle;
    public int mTitleColor;
    public String mTitleFontFamily;
    public float mTitleFontSize;
    public int mTitleFontWeight;
    public final Toolbar toolbar;
    public final ArrayList<ScreenStackHeaderSubview> mConfigSubviews = new ArrayList<>(3);
    public boolean mIsTopInsetEnabled = true;
    public final View.OnClickListener mBackClickListener = new View.OnClickListener() { // from class: com.swmansion.rnscreens.-$$Lambda$ScreenStackHeaderConfig$AFP1oUwvwebnR4ajviSSKRn0OfY
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            ScreenStackHeaderConfig.lambda$AFP1oUwvwebnR4ajviSSKRn0OfY(ScreenStackHeaderConfig.this, view);
        }
    };

    public ScreenStackHeaderConfig(Context context) {
        super(context);
        setVisibility(8);
        Toolbar toolbar = new Toolbar(context, null);
        this.toolbar = toolbar;
        this.mDefaultStartInset = toolbar.getContentInsetStart();
        this.mDefaultStartInsetWithNavigation = toolbar.getContentInsetStartWithNavigation();
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true)) {
            toolbar.setBackgroundColor(typedValue.data);
        }
        toolbar.setClipChildren(false);
    }

    private final Screen getScreen() {
        ViewParent parent = getParent();
        if (parent instanceof Screen) {
            return (Screen) parent;
        }
        return null;
    }

    private final ScreenStackFragment getScreenFragment() {
        ViewParent parent = getParent();
        if (!(parent instanceof Screen)) {
            return null;
        }
        ScreenFragment fragment = ((Screen) parent).getFragment();
        if (fragment instanceof ScreenStackFragment) {
            return (ScreenStackFragment) fragment;
        }
        return null;
    }

    private final ScreenStack getScreenStack() {
        Screen screen = getScreen();
        if (screen == null) {
            return null;
        }
        ScreenContainer<?> container = screen.getContainer();
        if (container instanceof ScreenStack) {
            return (ScreenStack) container;
        }
        return null;
    }

    private final TextView getTitleTextView() {
        int childCount = this.toolbar.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.toolbar.getChildAt(i);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                if (Intrinsics.areEqual(textView.getText(), this.toolbar.getTitle())) {
                    return textView;
                }
            }
        }
        return null;
    }

    public static void lambda$AFP1oUwvwebnR4ajviSSKRn0OfY(ScreenStackHeaderConfig screenStackHeaderConfig, View view) {
        ScreenStackFragment screenFragment = screenStackHeaderConfig.getScreenFragment();
        if (screenFragment != null) {
            ScreenStack screenStack = screenStackHeaderConfig.getScreenStack();
            if (screenStack == null || !Intrinsics.areEqual(screenStack.getRootScreen(), screenFragment.getScreen())) {
                screenFragment.dismiss();
                return;
            }
            Fragment fragment = screenFragment.mParentFragment;
            if (fragment instanceof ScreenStackFragment) {
                ((ScreenStackFragment) fragment).dismiss();
            }
        }
    }

    public final int getConfigSubviewsCount() {
        return this.mConfigSubviews.size();
    }

    public final Toolbar getToolbar() {
        return this.toolbar;
    }

    public final void maybeUpdate() {
        if (getParent() != null && !this.mDestroyed) {
            onUpdate();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsAttachedToWindow = true;
        onUpdate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mIsAttachedToWindow = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    @SuppressLint({"ObsoleteSdkInt"})
    public final void onUpdate() {
        Drawable navigationIcon;
        boolean z;
        boolean z2;
        ScreenStackFragment screenFragment;
        ScreenStackFragment screenFragment2;
        Toolbar toolbar;
        ReactContext reactContext;
        ScreenStack screenStack = getScreenStack();
        boolean z3 = screenStack == null || Intrinsics.areEqual(screenStack.getTopScreen(), getParent());
        if (this.mIsAttachedToWindow && z3 && !this.mDestroyed) {
            ScreenStackFragment screenFragment3 = getScreenFragment();
            AppCompatActivity appCompatActivity = (AppCompatActivity) (screenFragment3 == null ? null : screenFragment3.getActivity());
            if (appCompatActivity != null) {
                int i = Build.VERSION.SDK_INT;
                String str = this.mDirection;
                if (str != null) {
                    if (Intrinsics.areEqual(str, "rtl")) {
                        this.toolbar.setLayoutDirection(1);
                    } else if (Intrinsics.areEqual(this.mDirection, "ltr")) {
                        this.toolbar.setLayoutDirection(0);
                    }
                }
                Screen screen = getScreen();
                if (screen != null) {
                    if (getContext() instanceof ReactContext) {
                        Context context = getContext();
                        Objects.requireNonNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
                        reactContext = (ReactContext) context;
                    } else {
                        ScreenFragment fragment = screen.getFragment();
                        reactContext = fragment == null ? null : fragment.tryGetContext();
                    }
                    ScreenWindowTraits.trySetWindowTraits$react_native_screens_release(screen, appCompatActivity, reactContext);
                }
                if (!this.mIsHidden) {
                    if (this.toolbar.getParent() == null && (screenFragment = getScreenFragment()) != null) {
                        Toolbar toolbar2 = this.toolbar;
                        AppBarLayout appBarLayout = screenFragment.mAppBarLayout;
                        if (appBarLayout != null) {
                            appBarLayout.addView(toolbar2);
                        }
                        AppBarLayout.LayoutParams layoutParams = new AppBarLayout.LayoutParams(-1, -2);
                        layoutParams.scrollFlags = 0;
                        toolbar2.setLayoutParams(layoutParams);
                        screenFragment.mToolbar = toolbar2;
                    }
                    if (this.mIsTopInsetEnabled) {
                        if (i >= 23) {
                            this.toolbar.setPadding(0, getRootWindowInsets().getSystemWindowInsetTop(), 0, 0);
                        } else {
                            this.toolbar.setPadding(0, (int) (25 * getResources().getDisplayMetrics().density), 0, 0);
                        }
                    } else if (this.toolbar.getPaddingTop() > 0) {
                        this.toolbar.setPadding(0, 0, 0, 0);
                    }
                    appCompatActivity.getDelegate().setSupportActionBar(this.toolbar);
                    ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
                    if (supportActionBar != null) {
                        this.toolbar.setContentInsetStartWithNavigation(this.mDefaultStartInsetWithNavigation);
                        Toolbar toolbar3 = this.toolbar;
                        int i2 = this.mDefaultStartInset;
                        toolbar3.ensureContentInsets();
                        toolbar3.mContentInsets.setRelative(i2, i2);
                        ScreenStackFragment screenFragment4 = getScreenFragment();
                        supportActionBar.setDisplayHomeAsUpEnabled(Intrinsics.areEqual(screenFragment4 == null ? null : Boolean.valueOf(screenFragment4.canNavigateBack()), Boolean.TRUE) && !this.mIsBackButtonHidden);
                        this.toolbar.setNavigationOnClickListener(this.mBackClickListener);
                        ScreenStackFragment screenFragment5 = getScreenFragment();
                        if (!(screenFragment5 == null || screenFragment5.mShadowHidden == (z2 = this.mIsShadowHidden))) {
                            AppBarLayout appBarLayout2 = screenFragment5.mAppBarLayout;
                            if (appBarLayout2 != null) {
                                appBarLayout2.setTargetElevation(z2 ? 0.0f : PixelUtil.toPixelFromDIP(4.0f));
                            }
                            screenFragment5.mShadowHidden = z2;
                        }
                        ScreenStackFragment screenFragment6 = getScreenFragment();
                        if (!(screenFragment6 == null || screenFragment6.mIsTranslucent == (z = this.mIsTranslucent))) {
                            ViewGroup.LayoutParams layoutParams2 = screenFragment6.getScreen().getLayoutParams();
                            Objects.requireNonNull(layoutParams2, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams");
                            ((CoordinatorLayout.LayoutParams) layoutParams2).setBehavior(z ? null : new AppBarLayout.ScrollingViewBehavior());
                            screenFragment6.mIsTranslucent = z;
                        }
                        supportActionBar.setTitle(this.mTitle);
                        if (TextUtils.isEmpty(this.mTitle)) {
                            this.toolbar.setContentInsetStartWithNavigation(0);
                        }
                        TextView titleTextView = getTitleTextView();
                        int i3 = this.mTitleColor;
                        if (i3 != 0) {
                            this.toolbar.setTitleTextColor(i3);
                        }
                        if (titleTextView != null) {
                            String str2 = this.mTitleFontFamily;
                            if (str2 != null || this.mTitleFontWeight > 0) {
                                titleTextView.setTypeface(R$style.applyStyles(null, 0, this.mTitleFontWeight, str2, getContext().getAssets()));
                            }
                            float f = this.mTitleFontSize;
                            if (f > 0.0f) {
                                titleTextView.setTextSize(f);
                            }
                        }
                        Integer num = this.mBackgroundColor;
                        if (num != null) {
                            getToolbar().setBackgroundColor(num.intValue());
                        }
                        if (!(this.mTintColor == 0 || (navigationIcon = this.toolbar.getNavigationIcon()) == null)) {
                            navigationIcon.setColorFilter(this.mTintColor, PorterDuff.Mode.SRC_ATOP);
                        }
                        int childCount = this.toolbar.getChildCount() - 1;
                        if (childCount >= 0) {
                            while (true) {
                                int i4 = childCount - 1;
                                if (this.toolbar.getChildAt(childCount) instanceof ScreenStackHeaderSubview) {
                                    this.toolbar.removeViewAt(childCount);
                                }
                                if (i4 < 0) {
                                    break;
                                }
                                childCount = i4;
                            }
                        }
                        int size = this.mConfigSubviews.size();
                        for (int i5 = 0; i5 < size; i5++) {
                            ScreenStackHeaderSubview screenStackHeaderSubview = this.mConfigSubviews.get(i5);
                            ScreenStackHeaderSubview.Type type = screenStackHeaderSubview.getType();
                            if (type == ScreenStackHeaderSubview.Type.BACK) {
                                View childAt = screenStackHeaderSubview.getChildAt(0);
                                ImageView imageView = childAt instanceof ImageView ? (ImageView) childAt : null;
                                if (imageView != null) {
                                    supportActionBar.setHomeAsUpIndicator(imageView.getDrawable());
                                } else {
                                    throw new JSApplicationIllegalArgumentException("Back button header config view should have Image as first child");
                                }
                            } else {
                                Toolbar.LayoutParams layoutParams3 = new Toolbar.LayoutParams(-2, -1);
                                int ordinal = type.ordinal();
                                if (ordinal == 0) {
                                    if (!this.mBackButtonInCustomView) {
                                        this.toolbar.setNavigationIcon((Drawable) null);
                                    }
                                    this.toolbar.setTitle((CharSequence) null);
                                    layoutParams3.gravity = 8388611;
                                } else if (ordinal == 1) {
                                    ((ViewGroup.MarginLayoutParams) layoutParams3).width = -1;
                                    layoutParams3.gravity = 1;
                                    this.toolbar.setTitle((CharSequence) null);
                                } else if (ordinal == 2) {
                                    layoutParams3.gravity = 8388613;
                                }
                                screenStackHeaderSubview.setLayoutParams(layoutParams3);
                                this.toolbar.addView(screenStackHeaderSubview);
                            }
                        }
                        return;
                    }
                    throw new IllegalArgumentException("Required value was null.".toString());
                } else if (this.toolbar.getParent() != null && (screenFragment2 = getScreenFragment()) != null) {
                    AppBarLayout appBarLayout3 = screenFragment2.mAppBarLayout;
                    if (!(appBarLayout3 == null || (toolbar = screenFragment2.mToolbar) == null || toolbar.getParent() != appBarLayout3)) {
                        appBarLayout3.removeView(toolbar);
                    }
                    screenFragment2.mToolbar = null;
                }
            }
        }
    }

    public final void setBackButtonInCustomView(boolean z) {
        this.mBackButtonInCustomView = z;
    }

    public final void setBackgroundColor(Integer num) {
        this.mBackgroundColor = num;
    }

    public final void setDirection(String str) {
        this.mDirection = str;
    }

    public final void setHidden(boolean z) {
        this.mIsHidden = z;
    }

    public final void setHideBackButton(boolean z) {
        this.mIsBackButtonHidden = z;
    }

    public final void setHideShadow(boolean z) {
        this.mIsShadowHidden = z;
    }

    public final void setTintColor(int i) {
        this.mTintColor = i;
    }

    public final void setTitle(String str) {
        this.mTitle = str;
    }

    public final void setTitleColor(int i) {
        this.mTitleColor = i;
    }

    public final void setTitleFontFamily(String str) {
        this.mTitleFontFamily = str;
    }

    public final void setTitleFontSize(float f) {
        this.mTitleFontSize = f;
    }

    public final void setTitleFontWeight(String str) {
        this.mTitleFontWeight = R$style.parseFontWeight(str);
    }

    public final void setTopInsetEnabled(boolean z) {
        this.mIsTopInsetEnabled = z;
    }

    public final void setTranslucent(boolean z) {
        this.mIsTranslucent = z;
    }
}
