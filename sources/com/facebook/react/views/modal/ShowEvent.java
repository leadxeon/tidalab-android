package com.facebook.react.views.modal;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes.dex */
public class ShowEvent extends Event<ShowEvent> {
    public ShowEvent(int i) {
        super(i);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(this.mViewTag, "topShow", null);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topShow";
    }
}
