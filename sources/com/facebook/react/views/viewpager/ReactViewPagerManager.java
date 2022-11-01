package com.facebook.react.views.viewpager;

import android.view.View;
import androidx.recyclerview.R$dimen;
import com.facebook.react.R$style;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.AndroidViewPagerManagerDelegate;
import com.facebook.react.views.viewpager.ReactViewPager;
import java.util.Map;
@ReactModule(name = ReactViewPagerManager.REACT_CLASS)
/* loaded from: classes.dex */
public class ReactViewPagerManager extends ViewGroupManager<ReactViewPager> {
    public static final int COMMAND_SET_PAGE = 1;
    public static final int COMMAND_SET_PAGE_WITHOUT_ANIMATION = 2;
    public static final String REACT_CLASS = "AndroidViewPager";
    private final ViewManagerDelegate<ReactViewPager> mDelegate = new AndroidViewPagerManagerDelegate(this);

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return R$style.of("setPage", 1, "setPageWithoutAnimation", 2);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ViewManagerDelegate<ReactViewPager> getDelegate() {
        return this.mDelegate;
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return R$style.of("topPageScroll", R$style.of("registrationName", "onPageScroll"), "topPageScrollStateChanged", R$style.of("registrationName", "onPageScrollStateChanged"), "topPageSelected", R$style.of("registrationName", "onPageSelected"));
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.IViewManagerWithChildren
    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    public void setInitialPage(ReactViewPager reactViewPager, int i) {
    }

    public void setKeyboardDismissMode(ReactViewPager reactViewPager, String str) {
    }

    public void setPage(ReactViewPager reactViewPager, int i) {
    }

    public void setPageWithoutAnimation(ReactViewPager reactViewPager, int i) {
    }

    public void addView(ReactViewPager reactViewPager, View view, int i) {
        ReactViewPager.Adapter adapter = reactViewPager.getAdapter();
        adapter.mViews.add(i, view);
        adapter.notifyDataSetChanged();
        ReactViewPager.this.setOffscreenPageLimit(adapter.mViews.size());
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactViewPager createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactViewPager(themedReactContext);
    }

    public View getChildAt(ReactViewPager reactViewPager, int i) {
        return reactViewPager.getAdapter().mViews.get(i);
    }

    public int getChildCount(ReactViewPager reactViewPager) {
        return reactViewPager.getViewCountInAdapter();
    }

    public void removeViewAt(ReactViewPager reactViewPager, int i) {
        ReactViewPager.Adapter adapter = reactViewPager.getAdapter();
        adapter.mViews.remove(i);
        adapter.notifyDataSetChanged();
        ReactViewPager.this.setOffscreenPageLimit(adapter.mViews.size());
    }

    @ReactProp(defaultInt = 0, name = "pageMargin")
    public void setPageMargin(ReactViewPager reactViewPager, int i) {
        reactViewPager.setPageMargin((int) PixelUtil.toPixelFromDIP(i));
    }

    @ReactProp(defaultBoolean = false, name = "peekEnabled")
    public void setPeekEnabled(ReactViewPager reactViewPager, boolean z) {
        reactViewPager.setClipToPadding(!z);
    }

    @ReactProp(defaultBoolean = true, name = "scrollEnabled")
    public void setScrollEnabled(ReactViewPager reactViewPager, boolean z) {
        reactViewPager.setScrollEnabled(z);
    }

    public void receiveCommand(ReactViewPager reactViewPager, int i, ReadableArray readableArray) {
        R$dimen.assertNotNull(reactViewPager);
        R$dimen.assertNotNull(readableArray);
        if (i == 1) {
            reactViewPager.setCurrentItemFromJs(readableArray.getInt(0), true);
        } else if (i == 2) {
            reactViewPager.setCurrentItemFromJs(readableArray.getInt(0), false);
        } else {
            throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", Integer.valueOf(i), getClass().getSimpleName()));
        }
    }

    public void receiveCommand(ReactViewPager reactViewPager, String str, ReadableArray readableArray) {
        R$dimen.assertNotNull(reactViewPager);
        R$dimen.assertNotNull(readableArray);
        str.hashCode();
        if (str.equals("setPageWithoutAnimation")) {
            reactViewPager.setCurrentItemFromJs(readableArray.getInt(0), false);
        } else if (!str.equals("setPage")) {
            throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", str, getClass().getSimpleName()));
        } else {
            reactViewPager.setCurrentItemFromJs(readableArray.getInt(0), true);
        }
    }
}
