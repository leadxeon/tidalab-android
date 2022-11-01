package com.swmansion.rnscreens;

import android.view.View;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: ScreenContainerViewManager.kt */
@ReactModule(name = ScreenContainerViewManager.REACT_CLASS)
/* loaded from: classes.dex */
public final class ScreenContainerViewManager extends ViewGroupManager<ScreenContainer<?>> {
    public static final Companion Companion = new Companion(null);
    public static final String REACT_CLASS = "RNSScreenContainer";

    /* compiled from: ScreenContainerViewManager.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
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

    public void addView(ScreenContainer<?> screenContainer, View view, int i) {
        if (view instanceof Screen) {
            Screen screen = (Screen) view;
            ScreenFragment adapt = screenContainer.adapt(screen);
            screen.setFragment(adapt);
            screenContainer.mScreenFragments.add(i, adapt);
            screen.setContainer(screenContainer);
            screenContainer.markUpdated();
            return;
        }
        throw new IllegalArgumentException("Attempt attach child that is not of type RNScreens".toString());
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ScreenContainer<ScreenFragment> createViewInstance(ThemedReactContext themedReactContext) {
        return new ScreenContainer<>(themedReactContext);
    }

    public View getChildAt(ScreenContainer<?> screenContainer, int i) {
        return ((ScreenFragment) screenContainer.mScreenFragments.get(i)).getScreen();
    }

    public int getChildCount(ScreenContainer<?> screenContainer) {
        return screenContainer.getScreenCount();
    }

    public void removeAllViews(ScreenContainer<?> screenContainer) {
        screenContainer.removeAllScreens();
    }

    public void removeViewAt(ScreenContainer<?> screenContainer, int i) {
        screenContainer.removeScreenAt(i);
    }
}
