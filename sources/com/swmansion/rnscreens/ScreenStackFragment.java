package com.swmansion.rnscreens;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import com.facebook.react.bridge.UiThreadUtil;
import com.google.android.material.appbar.AppBarLayout;
import com.swmansion.rnscreens.Screen;
import com.swmansion.rnscreens.ScreenFragment;
import com.swmansion.rnscreens.ScreenStackFragment;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ScreenStackFragment.kt */
/* loaded from: classes.dex */
public final class ScreenStackFragment extends ScreenFragment {
    public static final /* synthetic */ int $r8$clinit = 0;
    public AppBarLayout mAppBarLayout;
    public boolean mIsTranslucent;
    public boolean mShadowHidden;
    public Toolbar mToolbar;

    /* compiled from: ScreenStackFragment.kt */
    /* loaded from: classes.dex */
    public static final class NotifyingCoordinatorLayout extends CoordinatorLayout {
        public final Animation.AnimationListener mAnimationListener = new Animation.AnimationListener() { // from class: com.swmansion.rnscreens.ScreenStackFragment$NotifyingCoordinatorLayout$mAnimationListener$1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                ScreenStackFragment.NotifyingCoordinatorLayout.this.mFragment.onViewAnimationEnd();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                ScreenFragment screenFragment = ScreenStackFragment.NotifyingCoordinatorLayout.this.mFragment;
                if (screenFragment.isResumed()) {
                    UiThreadUtil.runOnUiThread(new $$Lambda$ScreenFragment$yHXMMuAS8mqwKNxEUYE_hEkkivw(screenFragment));
                } else {
                    screenFragment.dispatchEvent(ScreenFragment.ScreenLifecycleEvent.WillDisappear, screenFragment);
                }
            }
        };
        public final ScreenFragment mFragment;

        public NotifyingCoordinatorLayout(Context context, ScreenFragment screenFragment) {
            super(context, null);
            this.mFragment = screenFragment;
        }

        @Override // android.view.View
        public void startAnimation(Animation animation) {
            if (!(animation instanceof AnimationSet) || this.mFragment.mRemoving) {
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.addAnimation(animation);
                animationSet.setAnimationListener(this.mAnimationListener);
                super.startAnimation(animationSet);
                return;
            }
            animation.setAnimationListener(this.mAnimationListener);
            super.startAnimation(animation);
        }
    }

    @SuppressLint({"ValidFragment"})
    public ScreenStackFragment(Screen screen) {
        super(screen);
    }

    public final boolean canNavigateBack() {
        ScreenContainer<?> container = getScreen().getContainer();
        if (!(container instanceof ScreenStack)) {
            throw new IllegalStateException("ScreenStackFragment added into a non-stack container".toString());
        } else if (!Intrinsics.areEqual(((ScreenStack) container).getRootScreen(), getScreen())) {
            return true;
        } else {
            Fragment fragment = this.mParentFragment;
            if (fragment instanceof ScreenStackFragment) {
                return ((ScreenStackFragment) fragment).canNavigateBack();
            }
            return false;
        }
    }

    public final void dismiss() {
        ScreenContainer<?> container = getScreen().getContainer();
        if (container instanceof ScreenStack) {
            ScreenStack screenStack = (ScreenStack) container;
            screenStack.mDismissed.add(this);
            screenStack.markUpdated();
            return;
        }
        throw new IllegalStateException("ScreenStackFragment added into a non-stack container".toString());
    }

    public final void notifyViewAppearTransitionEnd() {
        View view = this.mView;
        ViewParent parent = view == null ? null : view.getParent();
        if (parent instanceof ScreenStack) {
            ScreenStack screenStack = (ScreenStack) parent;
            if (!screenStack.mRemovalTransitionStarted) {
                screenStack.dispatchOnFinishTransitioning();
            }
        }
    }

    @Override // com.swmansion.rnscreens.ScreenFragment
    public void onContainerUpdate() {
        ScreenStackHeaderConfig headerConfig = getScreen().getHeaderConfig();
        if (headerConfig != null) {
            headerConfig.onUpdate();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public Animation onCreateAnimation(int i, boolean z, int i2) {
        if (i != 0 || this.mHidden || getScreen().getStackAnimation() != Screen.StackAnimation.NONE) {
            return null;
        }
        if (z) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.swmansion.rnscreens.-$$Lambda$ScreenStackFragment$obhM3v_UxVjrRw4z9Uca731BhAY
                @Override // java.lang.Runnable
                public final void run() {
                    ScreenFragment screenFragment = ScreenStackFragment.this;
                    int i3 = ScreenStackFragment.$r8$clinit;
                    screenFragment.dispatchEvent(ScreenFragment.ScreenLifecycleEvent.WillAppear, screenFragment);
                    screenFragment.dispatchEvent(ScreenFragment.ScreenLifecycleEvent.Appear, screenFragment);
                }
            });
            return null;
        }
        dispatchEvent(ScreenFragment.ScreenLifecycleEvent.WillDisappear, this);
        dispatchEvent(ScreenFragment.ScreenLifecycleEvent.Disappear, this);
        notifyViewAppearTransitionEnd();
        return null;
    }

    @Override // com.swmansion.rnscreens.ScreenFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AppBarLayout appBarLayout;
        AppBarLayout appBarLayout2;
        Context context = getContext();
        AppBarLayout appBarLayout3 = null;
        NotifyingCoordinatorLayout notifyingCoordinatorLayout = context == null ? null : new NotifyingCoordinatorLayout(context, this);
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(-1, -1);
        layoutParams.setBehavior(this.mIsTranslucent ? null : new AppBarLayout.ScrollingViewBehavior());
        getScreen().setLayoutParams(layoutParams);
        if (notifyingCoordinatorLayout != null) {
            Screen screen = getScreen();
            ScreenFragment.recycleView(screen);
            notifyingCoordinatorLayout.addView(screen);
        }
        Context context2 = getContext();
        if (context2 != null) {
            appBarLayout3 = new AppBarLayout(context2);
        }
        this.mAppBarLayout = appBarLayout3;
        if (appBarLayout3 != null) {
            appBarLayout3.setBackgroundColor(0);
        }
        AppBarLayout appBarLayout4 = this.mAppBarLayout;
        if (appBarLayout4 != null) {
            appBarLayout4.setLayoutParams(new AppBarLayout.LayoutParams(-1, -2));
        }
        if (notifyingCoordinatorLayout != null) {
            notifyingCoordinatorLayout.addView(this.mAppBarLayout);
        }
        if (this.mShadowHidden && (appBarLayout2 = this.mAppBarLayout) != null) {
            appBarLayout2.setTargetElevation(0.0f);
        }
        Toolbar toolbar = this.mToolbar;
        if (!(toolbar == null || (appBarLayout = this.mAppBarLayout) == null)) {
            ScreenFragment.recycleView(toolbar);
            appBarLayout.addView(toolbar);
        }
        return notifyingCoordinatorLayout;
    }

    @Override // com.swmansion.rnscreens.ScreenFragment
    public void onViewAnimationEnd() {
        super.onViewAnimationEnd();
        notifyViewAppearTransitionEnd();
    }

    public ScreenStackFragment() {
        throw new IllegalStateException("ScreenStack fragments should never be restored. Follow instructions from https://github.com/software-mansion/react-native-screens/issues/17#issuecomment-424704067 to properly configure your main activity.");
    }
}
