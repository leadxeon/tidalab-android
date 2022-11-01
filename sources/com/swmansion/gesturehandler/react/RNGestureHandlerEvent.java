package com.swmansion.gesturehandler.react;

import androidx.core.util.Pools$SynchronizedPool;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes.dex */
public class RNGestureHandlerEvent extends Event<RNGestureHandlerEvent> {
    public static final Pools$SynchronizedPool<RNGestureHandlerEvent> EVENTS_POOL = new Pools$SynchronizedPool<>(7);
    public short mCoalescingKey;
    public WritableMap mExtraData;

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return true;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(this.mViewTag, "onGestureHandlerEvent", this.mExtraData);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return this.mCoalescingKey;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "onGestureHandlerEvent";
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void onDispose() {
        this.mExtraData = null;
        EVENTS_POOL.release(this);
    }
}
