package com.swmansion.rnscreens;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: ScreenStackViewManager.kt */
@ReactModule(name = ScreenStackViewManager.REACT_CLASS)
/* loaded from: classes.dex */
public final class ScreenStackViewManager extends ViewGroupManager<ScreenStack> {
    public static final Companion Companion = new Companion(null);
    public static final String REACT_CLASS = "RNSScreenStack";

    /* compiled from: ScreenStackViewManager.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    private final void prepareOutTransition(Screen screen) {
        startTransitionRecursive(screen);
    }

    private final void startTransitionRecursive(ViewGroup viewGroup) {
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt != null) {
                    viewGroup.startViewTransition(childAt);
                }
                if (childAt instanceof ScreenStackHeaderConfig) {
                    startTransitionRecursive(((ScreenStackHeaderConfig) childAt).getToolbar());
                }
                if (childAt instanceof ViewGroup) {
                    startTransitionRecursive((ViewGroup) childAt);
                }
            }
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.IViewManagerWithChildren
    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    public void addView(ScreenStack screenStack, View view, int i) {
        if (view instanceof Screen) {
            Screen screen = (Screen) view;
            Objects.requireNonNull(screenStack);
            ScreenStackFragment screenStackFragment = new ScreenStackFragment(screen);
            screen.setFragment(screenStackFragment);
            screenStack.mScreenFragments.add(i, screenStackFragment);
            screen.setContainer(screenStack);
            screenStack.markUpdated();
            return;
        }
        throw new IllegalArgumentException("Attempt attach child that is not of type RNScreen".toString());
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ScreenStack createViewInstance(ThemedReactContext themedReactContext) {
        return new ScreenStack(themedReactContext);
    }

    public View getChildAt(ScreenStack screenStack, int i) {
        return ((ScreenFragment) screenStack.mScreenFragments.get(i)).getScreen();
    }

    public int getChildCount(ScreenStack screenStack) {
        return screenStack.getScreenCount();
    }

    public void removeViewAt(ScreenStack screenStack, int i) {
        prepareOutTransition(((ScreenFragment) screenStack.mScreenFragments.get(i)).getScreen());
        screenStack.removeScreenAt(i);
    }
}
