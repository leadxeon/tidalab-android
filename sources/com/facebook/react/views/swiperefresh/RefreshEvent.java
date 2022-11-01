package com.facebook.react.views.swiperefresh;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes.dex */
public class RefreshEvent extends Event<RefreshEvent> {
    public RefreshEvent(int i) {
        super(i);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(this.mViewTag, "topRefresh", null);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topRefresh";
    }
}
