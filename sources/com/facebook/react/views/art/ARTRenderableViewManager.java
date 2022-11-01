package com.facebook.react.views.art;

import android.view.View;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManager;
/* loaded from: classes.dex */
public class ARTRenderableViewManager extends ViewManager<View, ReactShadowNode> {
    public static final String CLASS_GROUP = "ARTGroup";
    public static final String CLASS_SHAPE = "ARTShape";
    public static final String CLASS_TEXT = "ARTText";
    private final String mClassName;

    public ARTRenderableViewManager(String str) {
        this.mClassName = str;
    }

    public static ARTRenderableViewManager createARTGroupViewManager() {
        return new ARTGroupViewManager();
    }

    public static ARTRenderableViewManager createARTShapeViewManager() {
        return new ARTShapeViewManager();
    }

    public static ARTRenderableViewManager createARTTextViewManager() {
        return new ARTTextViewManager();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactShadowNode createShadowNodeInstance() {
        if (CLASS_GROUP.equals(this.mClassName)) {
            return new ARTGroupShadowNode();
        }
        if (CLASS_SHAPE.equals(this.mClassName)) {
            return new ARTShapeShadowNode();
        }
        if (CLASS_TEXT.equals(this.mClassName)) {
            return new ARTTextShadowNode();
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unexpected type ");
        outline13.append(this.mClassName);
        throw new IllegalStateException(outline13.toString());
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public View createViewInstance(ThemedReactContext themedReactContext) {
        throw new IllegalStateException("ARTShape does not map into a native view");
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return this.mClassName;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<? extends ReactShadowNode> getShadowNodeClass() {
        if (CLASS_GROUP.equals(this.mClassName)) {
            return ARTGroupShadowNode.class;
        }
        if (CLASS_SHAPE.equals(this.mClassName)) {
            return ARTShapeShadowNode.class;
        }
        if (CLASS_TEXT.equals(this.mClassName)) {
            return ARTTextShadowNode.class;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unexpected type ");
        outline13.append(this.mClassName);
        throw new IllegalStateException(outline13.toString());
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void updateExtraData(View view, Object obj) {
        throw new IllegalStateException("ARTShape does not map into a native view");
    }
}
