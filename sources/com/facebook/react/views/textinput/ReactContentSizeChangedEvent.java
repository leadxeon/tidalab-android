package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes.dex */
public class ReactContentSizeChangedEvent extends Event<ReactTextChangedEvent> {
    public float mContentHeight;
    public float mContentWidth;

    public ReactContentSizeChangedEvent(int i, float f, float f2) {
        super(i);
        this.mContentWidth = f;
        this.mContentHeight = f2;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        int i = this.mViewTag;
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putDouble("width", this.mContentWidth);
        createMap2.putDouble("height", this.mContentHeight);
        createMap.putMap("contentSize", createMap2);
        createMap.putInt("target", this.mViewTag);
        rCTEventEmitter.receiveEvent(i, "topContentSizeChange", createMap);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topContentSizeChange";
    }
}
