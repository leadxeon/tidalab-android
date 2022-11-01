package com.facebook.react.views.viewpager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes.dex */
public class PageScrollEvent extends Event<PageScrollEvent> {
    public final float mOffset;
    public final int mPosition;

    public PageScrollEvent(int i, int i2, float f) {
        super(i);
        this.mPosition = i2;
        this.mOffset = (Float.isInfinite(f) || Float.isNaN(f)) ? 0.0f : f;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        int i = this.mViewTag;
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("position", this.mPosition);
        createMap.putDouble("offset", this.mOffset);
        rCTEventEmitter.receiveEvent(i, "topPageScroll", createMap);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topPageScroll";
    }
}
