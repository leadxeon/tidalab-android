package com.swmansion.rnscreens;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import androidx.core.view.ViewCompat;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.swmansion.rnscreens.Screen;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ScreenWindowTraits.kt */
/* loaded from: classes.dex */
public final class ScreenWindowTraits {
    public static Integer mDefaultStatusBarColor;
    public static boolean mDidSetOrientation;
    public static boolean mDidSetStatusBarAppearance;

    public static final boolean checkTraitForScreen(Screen screen, Screen.WindowTraits windowTraits) {
        int ordinal = windowTraits.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal != 2) {
                    if (ordinal != 3) {
                        if (ordinal != 4) {
                            if (ordinal != 5) {
                                throw new NoWhenBranchMatchedException();
                            } else if (screen.isStatusBarAnimated != null) {
                                return true;
                            }
                        } else if (screen.mStatusBarHidden != null) {
                            return true;
                        }
                    } else if (screen.mStatusBarTranslucent != null) {
                        return true;
                    }
                } else if (screen.getStatusBarStyle() != null) {
                    return true;
                }
            } else if (screen.getStatusBarColor() != null) {
                return true;
            }
        } else if (screen.getScreenOrientation() != null) {
            return true;
        }
        return false;
    }

    public static final Screen childScreenWithTraitSet(Screen screen, Screen.WindowTraits windowTraits) {
        ScreenFragment fragment;
        if (screen == null || (fragment = screen.getFragment()) == null) {
            return null;
        }
        for (ScreenContainer<?> screenContainer : fragment.mChildScreenContainers) {
            Screen topScreen = screenContainer.getTopScreen();
            Screen childScreenWithTraitSet = childScreenWithTraitSet(topScreen, windowTraits);
            if (childScreenWithTraitSet != null) {
                return childScreenWithTraitSet;
            }
            if (topScreen != null && checkTraitForScreen(topScreen, windowTraits)) {
                return topScreen;
            }
        }
        return null;
    }

    public static final Screen findScreenForTrait(Screen screen, Screen.WindowTraits windowTraits) {
        Screen childScreenWithTraitSet = childScreenWithTraitSet(screen, windowTraits);
        if (childScreenWithTraitSet != null) {
            return childScreenWithTraitSet;
        }
        if (checkTraitForScreen(screen, windowTraits)) {
            return screen;
        }
        for (ViewParent container = screen.getContainer(); container != null; container = container.getParent()) {
            if (container instanceof Screen) {
                Screen screen2 = (Screen) container;
                if (checkTraitForScreen(screen2, windowTraits)) {
                    return screen2;
                }
            }
        }
        return null;
    }

    @SuppressLint({"ObsoleteSdkInt"})
    public static final void setColor$react_native_screens_release(Screen screen, final Activity activity, ReactContext reactContext) {
        Boolean bool;
        if (activity != null && reactContext != null) {
            if (mDefaultStatusBarColor == null) {
                mDefaultStatusBarColor = Integer.valueOf(activity.getWindow().getStatusBarColor());
            }
            Screen findScreenForTrait = findScreenForTrait(screen, Screen.WindowTraits.COLOR);
            Screen findScreenForTrait2 = findScreenForTrait(screen, Screen.WindowTraits.ANIMATED);
            final Integer statusBarColor = findScreenForTrait == null ? null : findScreenForTrait.getStatusBarColor();
            if (statusBarColor == null) {
                statusBarColor = mDefaultStatusBarColor;
            }
            final boolean z = false;
            if (!(findScreenForTrait2 == null || (bool = findScreenForTrait2.isStatusBarAnimated) == null)) {
                z = bool.booleanValue();
            }
            UiThreadUtil.runOnUiThread(new GuardedRunnable(reactContext) { // from class: com.swmansion.rnscreens.ScreenWindowTraits$setColor$1
                @Override // com.facebook.react.bridge.GuardedRunnable
                public void runGuarded() {
                    activity.getWindow().addFlags(Integer.MIN_VALUE);
                    ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), Integer.valueOf(activity.getWindow().getStatusBarColor()), statusBarColor);
                    final Activity activity2 = activity;
                    ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.swmansion.rnscreens.-$$Lambda$ScreenWindowTraits$setColor$1$v7UMwMaWn_EPQFJHQAQBQUSC2HI
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                            Window window = activity2.getWindow();
                            Object animatedValue = valueAnimator.getAnimatedValue();
                            Objects.requireNonNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
                            window.setStatusBarColor(((Integer) animatedValue).intValue());
                        }
                    });
                    if (z) {
                        ofObject.setDuration(300L).setStartDelay(0L);
                    } else {
                        ofObject.setDuration(0L).setStartDelay(300L);
                    }
                    ofObject.start();
                }
            });
        }
    }

    public static final void setHidden$react_native_screens_release(Screen screen, final Activity activity) {
        Boolean bool;
        if (activity != null) {
            Screen findScreenForTrait = findScreenForTrait(screen, Screen.WindowTraits.HIDDEN);
            final boolean z = false;
            if (!(findScreenForTrait == null || (bool = findScreenForTrait.mStatusBarHidden) == null)) {
                z = bool.booleanValue();
            }
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.swmansion.rnscreens.-$$Lambda$ScreenWindowTraits$UWtZ_mTkvEXx_Jq6HZa14th6LqI
                @Override // java.lang.Runnable
                public final void run() {
                    boolean z2 = z;
                    Activity activity2 = activity;
                    if (z2) {
                        activity2.getWindow().addFlags(1024);
                        activity2.getWindow().clearFlags(2048);
                        return;
                    }
                    activity2.getWindow().addFlags(2048);
                    activity2.getWindow().clearFlags(1024);
                }
            });
        }
    }

    public static final void setOrientation$react_native_screens_release(Screen screen, Activity activity) {
        Integer screenOrientation;
        if (activity != null) {
            Screen findScreenForTrait = findScreenForTrait(screen, Screen.WindowTraits.ORIENTATION);
            int i = -1;
            if (!(findScreenForTrait == null || (screenOrientation = findScreenForTrait.getScreenOrientation()) == null)) {
                i = screenOrientation.intValue();
            }
            activity.setRequestedOrientation(i);
        }
    }

    public static final void setStyle$react_native_screens_release(Screen screen, final Activity activity, ReactContext reactContext) {
        final String str;
        if (activity != null && reactContext != null) {
            Screen findScreenForTrait = findScreenForTrait(screen, Screen.WindowTraits.STYLE);
            if (findScreenForTrait == null || (str = findScreenForTrait.getStatusBarStyle()) == null) {
                str = "light";
            }
            if (Build.VERSION.SDK_INT >= 23) {
                UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.swmansion.rnscreens.-$$Lambda$ScreenWindowTraits$TEGIHZCW64o1mWubs3LXp4FuMkI
                    @Override // java.lang.Runnable
                    public final void run() {
                        Activity activity2 = activity;
                        String str2 = str;
                        View decorView = activity2.getWindow().getDecorView();
                        int systemUiVisibility = decorView.getSystemUiVisibility();
                        decorView.setSystemUiVisibility(Intrinsics.areEqual("dark", str2) ? systemUiVisibility | 8192 : systemUiVisibility & (-8193));
                    }
                });
            }
        }
    }

    public static final void setTranslucent$react_native_screens_release(Screen screen, final Activity activity, ReactContext reactContext) {
        Boolean bool;
        if (activity != null && reactContext != null) {
            Screen findScreenForTrait = findScreenForTrait(screen, Screen.WindowTraits.TRANSLUCENT);
            final boolean z = false;
            if (!(findScreenForTrait == null || (bool = findScreenForTrait.mStatusBarTranslucent) == null)) {
                z = bool.booleanValue();
            }
            UiThreadUtil.runOnUiThread(new GuardedRunnable(reactContext) { // from class: com.swmansion.rnscreens.ScreenWindowTraits$setTranslucent$1
                @Override // com.facebook.react.bridge.GuardedRunnable
                @TargetApi(21)
                public void runGuarded() {
                    View decorView = activity.getWindow().getDecorView();
                    if (z) {
                        decorView.setOnApplyWindowInsetsListener($$Lambda$ScreenWindowTraits$setTranslucent$1$4adh2LsVA4CRkhXI5B2hKhuAepI.INSTANCE);
                    } else {
                        decorView.setOnApplyWindowInsetsListener(null);
                    }
                    AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                    decorView.requestApplyInsets();
                }
            });
        }
    }

    public static final void trySetWindowTraits$react_native_screens_release(Screen screen, Activity activity, ReactContext reactContext) {
        if (mDidSetOrientation) {
            setOrientation$react_native_screens_release(screen, activity);
        }
        if (mDidSetStatusBarAppearance) {
            setColor$react_native_screens_release(screen, activity, reactContext);
            setStyle$react_native_screens_release(screen, activity, reactContext);
            setTranslucent$react_native_screens_release(screen, activity, reactContext);
            setHidden$react_native_screens_release(screen, activity);
        }
    }
}
