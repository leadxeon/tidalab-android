package com.facebook.react;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.MapBuilder$Builder;
import java.util.Map;
/* loaded from: classes.dex */
public class ReactAndroidHWInputDeviceHelper {
    public static final Map<Integer, String> KEY_EVENTS_ACTIONS;
    public int mLastFocusedViewId = -1;
    public final ReactRootView mReactRootView;

    static {
        MapBuilder$Builder builder = R$style.builder();
        builder.put(23, "select");
        builder.put(66, "select");
        builder.put(62, "select");
        builder.put(85, "playPause");
        builder.put(89, "rewind");
        builder.put(90, "fastForward");
        builder.put(19, "up");
        builder.put(22, "right");
        builder.put(20, "down");
        builder.put(21, "left");
        KEY_EVENTS_ACTIONS = builder.build();
    }

    public ReactAndroidHWInputDeviceHelper(ReactRootView reactRootView) {
        this.mReactRootView = reactRootView;
    }

    public final void dispatchEvent(String str, int i, int i2) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("eventType", str);
        writableNativeMap.putInt("eventKeyAction", i2);
        if (i != -1) {
            writableNativeMap.putInt("tag", i);
        }
        this.mReactRootView.sendEvent("onHWKeyEvent", writableNativeMap);
    }
}
