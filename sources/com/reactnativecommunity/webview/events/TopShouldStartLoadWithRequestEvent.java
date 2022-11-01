package com.reactnativecommunity.webview.events;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* compiled from: TopShouldStartLoadWithRequestEvent.kt */
/* loaded from: classes.dex */
public final class TopShouldStartLoadWithRequestEvent extends Event<TopShouldStartLoadWithRequestEvent> {
    public final WritableMap mData;

    public TopShouldStartLoadWithRequestEvent(int i, WritableMap writableMap) {
        super(i);
        this.mData = writableMap;
        writableMap.putString("navigationType", "other");
        writableMap.putBoolean("isTopFrame", true);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(this.mViewTag, "topShouldStartLoadWithRequest", this.mData);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return (short) 0;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topShouldStartLoadWithRequest";
    }
}
