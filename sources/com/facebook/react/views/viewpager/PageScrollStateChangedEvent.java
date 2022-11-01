package com.facebook.react.views.viewpager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes.dex */
public class PageScrollStateChangedEvent extends Event<PageScrollStateChangedEvent> {
    public final String mPageScrollState;

    public PageScrollStateChangedEvent(int i, String str) {
        super(i);
        this.mPageScrollState = str;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        int i = this.mViewTag;
        WritableMap createMap = Arguments.createMap();
        createMap.putString("pageScrollState", this.mPageScrollState);
        rCTEventEmitter.receiveEvent(i, "topPageScrollStateChanged", createMap);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topPageScrollStateChanged";
    }
}
