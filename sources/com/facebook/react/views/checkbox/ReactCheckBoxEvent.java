package com.facebook.react.views.checkbox;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes.dex */
public class ReactCheckBoxEvent extends Event<ReactCheckBoxEvent> {
    public final boolean mIsChecked;

    public ReactCheckBoxEvent(int i, boolean z) {
        super(i);
        this.mIsChecked = z;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        int i = this.mViewTag;
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("target", this.mViewTag);
        createMap.putBoolean("value", this.mIsChecked);
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
