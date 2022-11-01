package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.WritableMap;
/* loaded from: classes.dex */
public interface StateWrapper {
    ReadableNativeMap getState();

    void updateState(WritableMap writableMap);
}
