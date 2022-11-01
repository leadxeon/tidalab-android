package com.th3rdwave.safeareacontext;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewManager;
import java.util.EnumSet;
/* loaded from: classes.dex */
public class SafeAreaViewManager extends ReactViewManager {
    @Override // com.facebook.react.views.view.ReactViewManager, com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNCSafeAreaView";
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return SafeAreaViewShadowNode.class;
    }

    @ReactProp(name = "edges")
    public void setEdges(SafeAreaView safeAreaView, ReadableArray readableArray) {
        EnumSet<SafeAreaViewEdges> noneOf = EnumSet.noneOf(SafeAreaViewEdges.class);
        if (readableArray != null) {
            for (int i = 0; i < readableArray.size(); i++) {
                String string = readableArray.getString(i);
                if ("top".equals(string)) {
                    noneOf.add(SafeAreaViewEdges.TOP);
                } else if ("right".equals(string)) {
                    noneOf.add(SafeAreaViewEdges.RIGHT);
                } else if ("bottom".equals(string)) {
                    noneOf.add(SafeAreaViewEdges.BOTTOM);
                } else if ("left".equals(string)) {
                    noneOf.add(SafeAreaViewEdges.LEFT);
                }
            }
        }
        safeAreaView.setEdges(noneOf);
    }

    @ReactProp(name = "mode")
    public void setMode(SafeAreaView safeAreaView, String str) {
        if ("padding".equals(str)) {
            safeAreaView.setMode(SafeAreaViewMode.PADDING);
        } else if ("margin".equals(str)) {
            safeAreaView.setMode(SafeAreaViewMode.MARGIN);
        }
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public SafeAreaViewShadowNode createShadowNodeInstance() {
        return new SafeAreaViewShadowNode();
    }

    @Override // com.facebook.react.views.view.ReactViewManager, com.facebook.react.uimanager.ViewManager
    public SafeAreaView createViewInstance(ThemedReactContext themedReactContext) {
        return new SafeAreaView(themedReactContext);
    }
}
