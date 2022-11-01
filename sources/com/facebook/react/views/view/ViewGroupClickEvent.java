package com.facebook.react.views.view;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes.dex */
public class ViewGroupClickEvent extends Event<ViewGroupClickEvent> {
    public ViewGroupClickEvent(int i) {
        super(i);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(this.mViewTag, "topClick", Arguments.createMap());
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topClick";
    }
}
