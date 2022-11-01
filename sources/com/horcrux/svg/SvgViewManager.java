package com.horcrux.svg;

import android.util.SparseArray;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewGroup;
import com.facebook.react.views.view.ReactViewManager;
/* loaded from: classes.dex */
public class SvgViewManager extends ReactViewManager {
    private static final String REACT_CLASS = "RNSVGSvgView";
    private static final SparseArray<SvgView> mTagToSvgView = new SparseArray<>();
    private static final SparseArray<Runnable> mTagToRunnable = new SparseArray<>();

    public static SvgView getSvgViewByTag(int i) {
        return mTagToSvgView.get(i);
    }

    public static void runWhenViewIsAvailable(int i, Runnable runnable) {
        mTagToRunnable.put(i, runnable);
    }

    public static void setSvgView(int i, SvgView svgView) {
        mTagToSvgView.put(i, svgView);
        SparseArray<Runnable> sparseArray = mTagToRunnable;
        Runnable runnable = sparseArray.get(i);
        if (runnable != null) {
            runnable.run();
            sparseArray.delete(i);
        }
    }

    @Override // com.facebook.react.views.view.ReactViewManager, com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.IViewManagerWithChildren
    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    @ReactProp(name = "align")
    public void setAlign(SvgView svgView, String str) {
        svgView.setAlign(str);
    }

    @ReactProp(name = "bbHeight")
    public void setBbHeight(SvgView svgView, Dynamic dynamic) {
        svgView.setBbHeight(dynamic);
    }

    @ReactProp(name = "bbWidth")
    public void setBbWidth(SvgView svgView, Dynamic dynamic) {
        svgView.setBbWidth(dynamic);
    }

    @ReactProp(name = "color")
    public void setColor(SvgView svgView, Integer num) {
        svgView.setTintColor(num);
    }

    @ReactProp(name = "meetOrSlice")
    public void setMeetOrSlice(SvgView svgView, int i) {
        svgView.setMeetOrSlice(i);
    }

    @ReactProp(name = "minX")
    public void setMinX(SvgView svgView, float f) {
        svgView.setMinX(f);
    }

    @ReactProp(name = "minY")
    public void setMinY(SvgView svgView, float f) {
        svgView.setMinY(f);
    }

    @ReactProp(name = "tintColor")
    public void setTintColor(SvgView svgView, Integer num) {
        svgView.setTintColor(num);
    }

    @ReactProp(name = "vbHeight")
    public void setVbHeight(SvgView svgView, float f) {
        svgView.setVbHeight(f);
    }

    @ReactProp(name = "vbWidth")
    public void setVbWidth(SvgView svgView, float f) {
        svgView.setVbWidth(f);
    }

    public void onDropViewInstance(ReactViewGroup reactViewGroup) {
        super.onDropViewInstance((SvgViewManager) reactViewGroup);
        mTagToSvgView.remove(reactViewGroup.getId());
    }

    @Override // com.facebook.react.views.view.ReactViewManager, com.facebook.react.uimanager.ViewManager
    public SvgView createViewInstance(ThemedReactContext themedReactContext) {
        return new SvgView(themedReactContext);
    }

    public void updateExtraData(ReactViewGroup reactViewGroup, Object obj) {
        super.updateExtraData((SvgViewManager) reactViewGroup, obj);
        reactViewGroup.invalidate();
    }
}
