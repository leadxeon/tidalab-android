package com.facebook.react.views.picker.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes.dex */
public class PickerItemSelectEvent extends Event<PickerItemSelectEvent> {
    public final int mPosition;

    public PickerItemSelectEvent(int i, int i2) {
        super(i);
        this.mPosition = i2;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        int i = this.mViewTag;
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("position", this.mPosition);
        rCTEventEmitter.receiveEvent(i, "topSelect", createMap);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topSelect";
    }
}
