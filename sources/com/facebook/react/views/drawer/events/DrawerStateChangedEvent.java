package com.facebook.react.views.drawer.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes.dex */
public class DrawerStateChangedEvent extends Event<DrawerStateChangedEvent> {
    public final int mDrawerState;

    public DrawerStateChangedEvent(int i, int i2) {
        super(i);
        this.mDrawerState = i2;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        int i = this.mViewTag;
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("drawerState", this.mDrawerState);
        rCTEventEmitter.receiveEvent(i, "topDrawerStateChanged", createMap);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return (short) 0;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topDrawerStateChanged";
    }
}
