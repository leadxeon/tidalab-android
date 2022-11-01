package com.swmansion.rnscreens;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.swmansion.rnscreens.Screen;
import com.swmansion.rnscreens.ScreenFragment;
import com.swmansion.rnscreens.events.ScreenAppearEvent;
import com.swmansion.rnscreens.events.ScreenDisappearEvent;
import com.swmansion.rnscreens.events.ScreenDismissedEvent;
import com.swmansion.rnscreens.events.ScreenWillAppearEvent;
import com.swmansion.rnscreens.events.ScreenWillDisappearEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ScreenFragment.kt */
/* loaded from: classes.dex */
public class ScreenFragment extends Fragment {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final List<ScreenContainer<?>> mChildScreenContainers = new ArrayList();
    public Screen screen;
    public boolean shouldUpdateOnResume;

    /* compiled from: ScreenFragment.kt */
    /* loaded from: classes.dex */
    public enum ScreenLifecycleEvent {
        Appear,
        WillAppear,
        Disappear,
        WillDisappear
    }

    public ScreenFragment() {
        throw new IllegalStateException("Screen fragments should never be restored. Follow instructions from https://github.com/software-mansion/react-native-screens/issues/17#issuecomment-424704067 to properly configure your main activity.");
    }

    public static final View recycleView(View view) {
        ViewParent parent = view.getParent();
        if (parent != null) {
            ViewGroup viewGroup = (ViewGroup) parent;
            viewGroup.endViewTransition(view);
            viewGroup.removeView(view);
        }
        view.setVisibility(0);
        return view;
    }

    public final void dispatchEvent(ScreenLifecycleEvent screenLifecycleEvent, ScreenFragment screenFragment) {
        Event event;
        Screen topScreen;
        ScreenFragment fragment;
        EventDispatcher eventDispatcher;
        Screen screen = screenFragment.getScreen();
        int ordinal = screenLifecycleEvent.ordinal();
        if (ordinal == 0) {
            event = new ScreenAppearEvent(screen.getId());
        } else if (ordinal == 1) {
            event = new ScreenWillAppearEvent(screen.getId());
        } else if (ordinal == 2) {
            event = new ScreenDisappearEvent(screen.getId());
        } else if (ordinal == 3) {
            event = new ScreenWillDisappearEvent(screen.getId());
        } else {
            throw new NoWhenBranchMatchedException();
        }
        Context context = screen.getContext();
        Objects.requireNonNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        UIManagerModule uIManagerModule = (UIManagerModule) ((ReactContext) context).getNativeModule(UIManagerModule.class);
        if (!(uIManagerModule == null || (eventDispatcher = uIManagerModule.getEventDispatcher()) == null)) {
            eventDispatcher.dispatchEvent(event);
        }
        for (ScreenContainer<?> screenContainer : screenFragment.mChildScreenContainers) {
            if (screenContainer.getScreenCount() > 0 && (topScreen = screenContainer.getTopScreen()) != null && (topScreen.getStackAnimation() != Screen.StackAnimation.NONE || screenFragment.mRemoving)) {
                Screen topScreen2 = screenContainer.getTopScreen();
                if (!(topScreen2 == null || (fragment = topScreen2.getFragment()) == null)) {
                    screenFragment.dispatchEvent(screenLifecycleEvent, fragment);
                }
            }
        }
    }

    public final Screen getScreen() {
        Screen screen = this.screen;
        if (screen != null) {
            return screen;
        }
        Intrinsics.throwUninitializedPropertyAccessException("screen");
        throw null;
    }

    public void onContainerUpdate() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            this.shouldUpdateOnResume = true;
        } else {
            ScreenWindowTraits.trySetWindowTraits$react_native_screens_release(getScreen(), activity, tryGetContext());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Context context = getContext();
        FrameLayout frameLayout = context == null ? null : new FrameLayout(context);
        getScreen().setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        if (frameLayout != null) {
            Screen screen = getScreen();
            recycleView(screen);
            frameLayout.addView(screen);
        }
        return frameLayout;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        EventDispatcher eventDispatcher;
        this.mCalled = true;
        ScreenContainer<?> container = getScreen().getContainer();
        if ((container == null || !container.hasScreen(this)) && (getScreen().getContext() instanceof ReactContext)) {
            Context context = getScreen().getContext();
            Objects.requireNonNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
            UIManagerModule uIManagerModule = (UIManagerModule) ((ReactContext) context).getNativeModule(UIManagerModule.class);
            if (!(uIManagerModule == null || (eventDispatcher = uIManagerModule.getEventDispatcher()) == null)) {
                eventDispatcher.dispatchEvent(new ScreenDismissedEvent(getScreen().getId()));
            }
        }
        this.mChildScreenContainers.clear();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        this.mCalled = true;
        if (this.shouldUpdateOnResume) {
            this.shouldUpdateOnResume = false;
            ScreenWindowTraits.trySetWindowTraits$react_native_screens_release(getScreen(), tryGetActivity(), tryGetContext());
        }
    }

    public void onViewAnimationEnd() {
        if (isResumed()) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.swmansion.rnscreens.-$$Lambda$ScreenFragment$Rb1798XrK1QrZqvu52HkFb8Go2Y
                @Override // java.lang.Runnable
                public final void run() {
                    ScreenFragment screenFragment = ScreenFragment.this;
                    int i = ScreenFragment.$r8$clinit;
                    screenFragment.dispatchEvent(ScreenFragment.ScreenLifecycleEvent.Appear, screenFragment);
                }
            });
        } else {
            dispatchEvent(ScreenLifecycleEvent.Disappear, this);
        }
    }

    public final Activity tryGetActivity() {
        ScreenFragment fragment;
        FragmentActivity activity;
        FragmentActivity activity2 = getActivity();
        if (activity2 != null) {
            return activity2;
        }
        Context context = getScreen().getContext();
        if (context instanceof ReactContext) {
            ReactContext reactContext = (ReactContext) context;
            if (reactContext.getCurrentActivity() != null) {
                return reactContext.getCurrentActivity();
            }
        }
        for (ViewParent container = getScreen().getContainer(); container != null; container = container.getParent()) {
            if (!(!(container instanceof Screen) || (fragment = ((Screen) container).getFragment()) == null || (activity = fragment.getActivity()) == null)) {
                return activity;
            }
        }
        return null;
    }

    public final ReactContext tryGetContext() {
        if (getContext() instanceof ReactContext) {
            Context context = getContext();
            Objects.requireNonNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
            return (ReactContext) context;
        } else if (getScreen().getContext() instanceof ReactContext) {
            Context context2 = getScreen().getContext();
            Objects.requireNonNull(context2, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
            return (ReactContext) context2;
        } else {
            for (ViewParent container = getScreen().getContainer(); container != null; container = container.getParent()) {
                if (container instanceof Screen) {
                    Screen screen = (Screen) container;
                    if (screen.getContext() instanceof ReactContext) {
                        Context context3 = screen.getContext();
                        Objects.requireNonNull(context3, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
                        return (ReactContext) context3;
                    }
                }
            }
            return null;
        }
    }

    @SuppressLint({"ValidFragment"})
    public ScreenFragment(Screen screen) {
        this.screen = screen;
    }
}
