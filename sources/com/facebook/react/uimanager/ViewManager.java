package com.facebook.react.uimanager;

import android.content.Context;
import android.view.View;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.touch.ReactInterceptingViewGroup;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ViewManagerPropertyUpdater;
import com.facebook.yoga.YogaMeasureMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class ViewManager<T extends View, C extends ReactShadowNode> extends BaseJavaModule {
    private final T createView(ThemedReactContext themedReactContext, JSResponderHandler jSResponderHandler) {
        return createView(themedReactContext, null, null, jSResponderHandler);
    }

    public void addEventEmitters(ThemedReactContext themedReactContext, T t) {
    }

    public C createShadowNodeInstance() {
        throw new RuntimeException("ViewManager subclasses must implement createShadowNodeInstance()");
    }

    public abstract T createViewInstance(ThemedReactContext themedReactContext);

    public T createViewInstance(ThemedReactContext themedReactContext, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper) {
        Object updateState;
        T createViewInstance = createViewInstance(themedReactContext);
        addEventEmitters(themedReactContext, createViewInstance);
        if (reactStylesDiffMap != null) {
            updateProperties(createViewInstance, reactStylesDiffMap);
        }
        if (!(stateWrapper == null || (updateState = updateState(createViewInstance, reactStylesDiffMap, stateWrapper)) == null)) {
            updateExtraData(createViewInstance, updateState);
        }
        return createViewInstance;
    }

    public Map<String, Integer> getCommandsMap() {
        return null;
    }

    public ViewManagerDelegate<T> getDelegate() {
        return null;
    }

    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return null;
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return null;
    }

    public Map<String, Object> getExportedViewConstants() {
        return null;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public abstract String getName();

    public Map<String, String> getNativeProps() {
        Class<?> cls = getClass();
        Class<? extends C> shadowNodeClass = getShadowNodeClass();
        Map<Class<?>, ViewManagerPropertyUpdater.ViewManagerSetter<?, ?>> map = ViewManagerPropertyUpdater.VIEW_MANAGER_SETTER_MAP;
        HashMap hashMap = new HashMap();
        ViewManagerPropertyUpdater.findManagerSetter(cls).getProperties(hashMap);
        ViewManagerPropertyUpdater.findNodeSetter(shadowNodeClass).getProperties(hashMap);
        return hashMap;
    }

    public abstract Class<? extends C> getShadowNodeClass();

    public long measure(Context context, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
        return 0L;
    }

    public void onAfterUpdateTransaction(T t) {
    }

    public void onDropViewInstance(T t) {
    }

    @Deprecated
    public void receiveCommand(T t, int i, ReadableArray readableArray) {
    }

    public void receiveCommand(T t, String str, ReadableArray readableArray) {
    }

    public void setPadding(T t, int i, int i2, int i3, int i4) {
    }

    public abstract void updateExtraData(T t, Object obj);

    public Object updateLocalData(T t, ReactStylesDiffMap reactStylesDiffMap, ReactStylesDiffMap reactStylesDiffMap2) {
        return null;
    }

    public void updateProperties(T t, ReactStylesDiffMap reactStylesDiffMap) {
        Map<Class<?>, ViewManagerPropertyUpdater.ViewManagerSetter<?, ?>> map = ViewManagerPropertyUpdater.VIEW_MANAGER_SETTER_MAP;
        ViewManagerPropertyUpdater.ViewManagerSetter findManagerSetter = ViewManagerPropertyUpdater.findManagerSetter(getClass());
        Iterator<Map.Entry<String, Object>> entryIterator = reactStylesDiffMap.mBackingMap.getEntryIterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Object> next = entryIterator.next();
            findManagerSetter.setProperty(this, t, next.getKey(), next.getValue());
        }
        onAfterUpdateTransaction(t);
    }

    public Object updateState(T t, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper) {
        return null;
    }

    public C createShadowNodeInstance(ReactApplicationContext reactApplicationContext) {
        return createShadowNodeInstance();
    }

    public T createView(ThemedReactContext themedReactContext, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper, JSResponderHandler jSResponderHandler) {
        T createViewInstance = createViewInstance(themedReactContext, reactStylesDiffMap, stateWrapper);
        if (createViewInstance instanceof ReactInterceptingViewGroup) {
            ((ReactInterceptingViewGroup) createViewInstance).setOnInterceptTouchEventListener(jSResponderHandler);
        }
        return createViewInstance;
    }
}
