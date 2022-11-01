package com.facebook.react.uimanager;

import com.facebook.react.uimanager.ReactShadowNode;
/* loaded from: classes.dex */
public interface ReactShadowNode<T extends ReactShadowNode> {
    void addChildAt(T t, int i);

    void addNativeChildAt(T t, int i);

    void calculateLayout();

    void calculateLayout(float f, float f2);

    Iterable<? extends ReactShadowNode> calculateLayoutOnChildren();

    void dirty();

    boolean dispatchUpdates(float f, float f2, UIViewOperationQueue uIViewOperationQueue, NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer);

    void dispose();

    T getChildAt(int i);

    int getChildCount();

    Integer getHeightMeasureSpec();

    T getLayoutParent();

    float getLayoutX();

    float getLayoutY();

    int getNativeChildCount();

    int getNativeKind$enumunboxing$();

    int getNativeOffsetForChild(T t);

    T getNativeParent();

    T getParent();

    int getReactTag();

    int getRootTag();

    int getScreenHeight();

    int getScreenWidth();

    int getScreenX();

    int getScreenY();

    ThemedReactContext getThemedContext();

    String getViewClass();

    Integer getWidthMeasureSpec();

    boolean hasUpdates();

    int indexOf(T t);

    int indexOfNativeChild(T t);

    boolean isDescendantOf(T t);

    boolean isLayoutOnly();

    boolean isVirtual();

    void markUpdateSeen();

    void onBeforeLayout(NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer);

    void removeAllNativeChildren();

    void removeAndDisposeAllChildren();

    T removeChildAt(int i);

    T removeNativeChildAt(int i);

    void setIsLayoutOnly(boolean z);

    void setLayoutParent(T t);

    void setLocalData(Object obj);

    void setMeasureSpecs(int i, int i2);

    void setReactTag(int i);

    void setRootTag(int i);

    void setStyleHeight(float f);

    void setStyleWidth(float f);

    void setThemedContext(ThemedReactContext themedReactContext);

    void setViewClassName(String str);

    boolean shouldNotifyOnLayout();

    void updateProperties(ReactStylesDiffMap reactStylesDiffMap);
}
