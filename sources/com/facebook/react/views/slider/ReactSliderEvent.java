package com.facebook.react.views.slider;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes.dex */
public class ReactSliderEvent extends Event<ReactSliderEvent> {
    public final boolean mFromUser;
    public final double mValue;

    public ReactSliderEvent(int i, double d, boolean z) {
        super(i);
        this.mValue = d;
        this.mFromUser = z;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        int i = this.mViewTag;
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("target", this.mViewTag);
        createMap.putDouble("value", this.mValue);
        createMap.putBoolean("fromUser", this.mFromUser);
        rCTEventEmitter.receiveEvent(i, "topChange", createMap);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return (short) 0;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topChange";
    }
}
