package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes.dex */
public class ReactTextInputBlurEvent extends Event<ReactTextInputBlurEvent> {
    public ReactTextInputBlurEvent(int i) {
        super(i);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        int i = this.mViewTag;
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("target", this.mViewTag);
        rCTEventEmitter.receiveEvent(i, "topBlur", createMap);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topBlur";
    }
}
