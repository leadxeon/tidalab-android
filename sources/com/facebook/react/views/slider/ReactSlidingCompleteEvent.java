package com.facebook.react.views.slider;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes.dex */
public class ReactSlidingCompleteEvent extends Event<ReactSlidingCompleteEvent> {
    public final double mValue;

    public ReactSlidingCompleteEvent(int i, double d) {
        super(i);
        this.mValue = d;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        int i = this.mViewTag;
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("target", this.mViewTag);
        createMap.putDouble("value", this.mValue);
        rCTEventEmitter.receiveEvent(i, "topSlidingComplete", createMap);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return (short) 0;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topSlidingComplete";
    }
}
