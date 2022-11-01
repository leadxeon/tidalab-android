package com.swmansion.gesturehandler.react;

import com.facebook.react.bridge.WritableMap;
import com.swmansion.gesturehandler.GestureHandler;
/* loaded from: classes.dex */
public interface RNGestureHandlerEventDataExtractor<T extends GestureHandler> {
    void extractEventData(T t, WritableMap writableMap);
}
