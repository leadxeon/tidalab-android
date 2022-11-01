package com.swmansion.rnscreens.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* compiled from: StackFinishTransitioningEvent.kt */
/* loaded from: classes.dex */
public final class StackFinishTransitioningEvent extends Event<StackFinishTransitioningEvent> {
    public StackFinishTransitioningEvent(int i) {
        super(i);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(this.mViewTag, "topFinishTransitioning", Arguments.createMap());
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return (short) 0;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topFinishTransitioning";
    }
}
