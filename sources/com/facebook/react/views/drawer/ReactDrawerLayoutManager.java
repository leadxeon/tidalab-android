package com.facebook.react.views.drawer;

import android.view.View;
import androidx.drawerlayout.widget.DrawerLayout;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.R$style;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerDelegate;
import com.facebook.react.views.drawer.events.DrawerClosedEvent;
import com.facebook.react.views.drawer.events.DrawerOpenedEvent;
import com.facebook.react.views.drawer.events.DrawerSlideEvent;
import com.facebook.react.views.drawer.events.DrawerStateChangedEvent;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
@ReactModule(name = ReactDrawerLayoutManager.REACT_CLASS)
/* loaded from: classes.dex */
public class ReactDrawerLayoutManager extends ViewGroupManager<ReactDrawerLayout> {
    public static final int CLOSE_DRAWER = 2;
    public static final int OPEN_DRAWER = 1;
    public static final String REACT_CLASS = "AndroidDrawerLayout";
    private final ViewManagerDelegate<ReactDrawerLayout> mDelegate = new AndroidDrawerLayoutManagerDelegate(this);

    /* loaded from: classes.dex */
    public static class DrawerEventEmitter implements DrawerLayout.DrawerListener {
        public final DrawerLayout mDrawerLayout;
        public final EventDispatcher mEventDispatcher;

        public DrawerEventEmitter(DrawerLayout drawerLayout, EventDispatcher eventDispatcher) {
            this.mDrawerLayout = drawerLayout;
            this.mEventDispatcher = eventDispatcher;
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerClosed(View view) {
            this.mEventDispatcher.dispatchEvent(new DrawerClosedEvent(this.mDrawerLayout.getId()));
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerOpened(View view) {
            this.mEventDispatcher.dispatchEvent(new DrawerOpenedEvent(this.mDrawerLayout.getId()));
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerSlide(View view, float f) {
            this.mEventDispatcher.dispatchEvent(new DrawerSlideEvent(this.mDrawerLayout.getId(), f));
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerStateChanged(int i) {
            this.mEventDispatcher.dispatchEvent(new DrawerStateChangedEvent(this.mDrawerLayout.getId(), i));
        }
    }

    private void setDrawerPositionInternal(ReactDrawerLayout reactDrawerLayout, String str) {
        if (str.equals("left")) {
            reactDrawerLayout.mDrawerPosition = 8388611;
            reactDrawerLayout.setDrawerProperties();
        } else if (str.equals("right")) {
            reactDrawerLayout.mDrawerPosition = 8388613;
            reactDrawerLayout.setDrawerProperties();
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("drawerPosition must be 'left' or 'right', received", str));
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return R$style.of("openDrawer", 1, "closeDrawer", 2);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ViewManagerDelegate<ReactDrawerLayout> getDelegate() {
        return this.mDelegate;
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return R$style.of("topDrawerSlide", R$style.of("registrationName", "onDrawerSlide"), "topDrawerOpen", R$style.of("registrationName", "onDrawerOpen"), "topDrawerClose", R$style.of("registrationName", "onDrawerClose"), "topDrawerStateChanged", R$style.of("registrationName", "onDrawerStateChanged"));
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map getExportedViewConstants() {
        return R$style.of("DrawerPosition", R$style.of("Left", 8388611, "Right", 8388613));
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.IViewManagerWithChildren
    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    public void setDrawerBackgroundColor(ReactDrawerLayout reactDrawerLayout, Integer num) {
    }

    public void setKeyboardDismissMode(ReactDrawerLayout reactDrawerLayout, String str) {
    }

    public void setStatusBarBackgroundColor(ReactDrawerLayout reactDrawerLayout, Integer num) {
    }

    public void addEventEmitters(ThemedReactContext themedReactContext, ReactDrawerLayout reactDrawerLayout) {
        DrawerEventEmitter drawerEventEmitter = new DrawerEventEmitter(reactDrawerLayout, ((UIManagerModule) themedReactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher());
        Objects.requireNonNull(reactDrawerLayout);
        if (reactDrawerLayout.mListeners == null) {
            reactDrawerLayout.mListeners = new ArrayList();
        }
        reactDrawerLayout.mListeners.add(drawerEventEmitter);
    }

    public void addView(ReactDrawerLayout reactDrawerLayout, View view, int i) {
        if (getChildCount(reactDrawerLayout) >= 2) {
            throw new JSApplicationIllegalArgumentException("The Drawer cannot have more than two children");
        } else if (i == 0 || i == 1) {
            reactDrawerLayout.addView(view, i);
            reactDrawerLayout.setDrawerProperties();
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline4("The only valid indices for drawer's child are 0 or 1. Got ", i, " instead."));
        }
    }

    public void closeDrawer(ReactDrawerLayout reactDrawerLayout) {
        reactDrawerLayout.closeDrawer();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactDrawerLayout createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactDrawerLayout(themedReactContext);
    }

    public void openDrawer(ReactDrawerLayout reactDrawerLayout) {
        reactDrawerLayout.openDrawer();
    }

    @ReactProp(name = "drawerLockMode")
    public void setDrawerLockMode(ReactDrawerLayout reactDrawerLayout, String str) {
        if (str == null || "unlocked".equals(str)) {
            reactDrawerLayout.setDrawerLockMode(0);
        } else if ("locked-closed".equals(str)) {
            reactDrawerLayout.setDrawerLockMode(1);
        } else if ("locked-open".equals(str)) {
            reactDrawerLayout.setDrawerLockMode(2);
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("Unknown drawerLockMode ", str));
        }
    }

    public void setDrawerPosition(ReactDrawerLayout reactDrawerLayout, String str) {
        if (str == null) {
            reactDrawerLayout.mDrawerPosition = 8388611;
            reactDrawerLayout.setDrawerProperties();
            return;
        }
        setDrawerPositionInternal(reactDrawerLayout, str);
    }

    @ReactProp(defaultFloat = Float.NaN, name = "drawerWidth")
    public void setDrawerWidth(ReactDrawerLayout reactDrawerLayout, float f) {
        reactDrawerLayout.mDrawerWidth = Float.isNaN(f) ? -1 : Math.round(PixelUtil.toPixelFromDIP(f));
        reactDrawerLayout.setDrawerProperties();
    }

    public void setElevation(ReactDrawerLayout reactDrawerLayout, float f) {
        reactDrawerLayout.setDrawerElevation(PixelUtil.toPixelFromDIP(f));
    }

    public void receiveCommand(ReactDrawerLayout reactDrawerLayout, int i, ReadableArray readableArray) {
        if (i == 1) {
            reactDrawerLayout.openDrawer();
        } else if (i == 2) {
            reactDrawerLayout.closeDrawer();
        }
    }

    public void receiveCommand(ReactDrawerLayout reactDrawerLayout, String str, ReadableArray readableArray) {
        str.hashCode();
        if (str.equals("closeDrawer")) {
            reactDrawerLayout.closeDrawer();
        } else if (str.equals("openDrawer")) {
            reactDrawerLayout.openDrawer();
        }
    }

    @ReactProp(name = "drawerPosition")
    public void setDrawerPosition(ReactDrawerLayout reactDrawerLayout, Dynamic dynamic) {
        if (dynamic.isNull()) {
            reactDrawerLayout.mDrawerPosition = 8388611;
            reactDrawerLayout.setDrawerProperties();
        } else if (dynamic.getType() == ReadableType.Number) {
            int asInt = dynamic.asInt();
            if (8388611 == asInt || 8388613 == asInt) {
                reactDrawerLayout.mDrawerPosition = asInt;
                reactDrawerLayout.setDrawerProperties();
                return;
            }
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline3("Unknown drawerPosition ", asInt));
        } else if (dynamic.getType() == ReadableType.String) {
            setDrawerPositionInternal(reactDrawerLayout, dynamic.asString());
        } else {
            throw new JSApplicationIllegalArgumentException("drawerPosition must be a string or int");
        }
    }

    public void setDrawerWidth(ReactDrawerLayout reactDrawerLayout, Float f) {
        reactDrawerLayout.mDrawerWidth = f == null ? -1 : Math.round(PixelUtil.toPixelFromDIP(f.floatValue()));
        reactDrawerLayout.setDrawerProperties();
    }
}
