package com.facebook.react.uimanager.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
/* loaded from: classes.dex */
public class ContentSizeChangeEvent extends Event<ContentSizeChangeEvent> {
    public final int mHeight;
    public final int mWidth;

    public ContentSizeChangeEvent(int i, int i2, int i3) {
        super(i);
        this.mWidth = i2;
        this.mHeight = i3;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("width", PixelUtil.toDIPFromPixel(this.mWidth));
        createMap.putDouble("height", PixelUtil.toDIPFromPixel(this.mHeight));
        rCTEventEmitter.receiveEvent(this.mViewTag, "topContentSizeChange", createMap);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topContentSizeChange";
    }
}
