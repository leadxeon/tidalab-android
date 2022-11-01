package com.th3rdwave.safeareacontext;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes.dex */
public class InsetsChangeEvent extends Event<InsetsChangeEvent> {
    public Rect mFrame;
    public EdgeInsets mInsets;

    public InsetsChangeEvent(int i, EdgeInsets edgeInsets, Rect rect) {
        super(i);
        this.mInsets = edgeInsets;
        this.mFrame = rect;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        WritableMap createMap = Arguments.createMap();
        EdgeInsets edgeInsets = this.mInsets;
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putDouble("top", PixelUtil.toDIPFromPixel(edgeInsets.top));
        createMap2.putDouble("right", PixelUtil.toDIPFromPixel(edgeInsets.right));
        createMap2.putDouble("bottom", PixelUtil.toDIPFromPixel(edgeInsets.bottom));
        createMap2.putDouble("left", PixelUtil.toDIPFromPixel(edgeInsets.left));
        createMap.putMap("insets", createMap2);
        Rect rect = this.mFrame;
        WritableMap createMap3 = Arguments.createMap();
        createMap3.putDouble("x", PixelUtil.toDIPFromPixel(rect.x));
        createMap3.putDouble("y", PixelUtil.toDIPFromPixel(rect.y));
        createMap3.putDouble("width", PixelUtil.toDIPFromPixel(rect.width));
        createMap3.putDouble("height", PixelUtil.toDIPFromPixel(rect.height));
        createMap.putMap("frame", createMap3);
        rCTEventEmitter.receiveEvent(this.mViewTag, "topInsetsChange", createMap);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topInsetsChange";
    }
}
