package com.facebook.react.views.modal;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes.dex */
public class RequestCloseEvent extends Event<RequestCloseEvent> {
    public RequestCloseEvent(int i) {
        super(i);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(this.mViewTag, "topRequestClose", null);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topRequestClose";
    }
}
