package com.swmansion.rnscreens.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* compiled from: ScreenDisappearEvent.kt */
/* loaded from: classes.dex */
public final class ScreenDisappearEvent extends Event<ScreenDisappearEvent> {
    public ScreenDisappearEvent(int i) {
        super(i);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(this.mViewTag, "topDisappear", Arguments.createMap());
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return (short) 0;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topDisappear";
    }
}
