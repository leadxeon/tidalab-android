package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes.dex */
public class ReactTextChangedEvent extends Event<ReactTextChangedEvent> {
    public int mEventCount;
    public String mText;

    public ReactTextChangedEvent(int i, String str, int i2) {
        super(i);
        this.mText = str;
        this.mEventCount = i2;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        int i = this.mViewTag;
        WritableMap createMap = Arguments.createMap();
        createMap.putString("text", this.mText);
        createMap.putInt("eventCount", this.mEventCount);
        createMap.putInt("target", this.mViewTag);
        rCTEventEmitter.receiveEvent(i, "topChange", createMap);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topChange";
    }
}
