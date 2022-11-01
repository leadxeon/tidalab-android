package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes.dex */
public class ReactTextInputKeyPressEvent extends Event<ReactTextInputEvent> {
    public String mKey;

    public ReactTextInputKeyPressEvent(int i, String str) {
        super(i);
        this.mKey = str;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        int i = this.mViewTag;
        WritableMap createMap = Arguments.createMap();
        createMap.putString("key", this.mKey);
        rCTEventEmitter.receiveEvent(i, "topKeyPress", createMap);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topKeyPress";
    }
}
