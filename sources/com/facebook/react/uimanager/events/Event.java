package com.facebook.react.uimanager.events;

import android.os.SystemClock;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.views.slider.ReactSlidingCompleteEvent;
/* loaded from: classes.dex */
public abstract class Event<T extends Event> {
    public static int sUniqueID;
    public boolean mInitialized;
    public long mTimestampMs;
    public int mUniqueID;
    public int mViewTag;

    public Event() {
        int i = sUniqueID;
        sUniqueID = i + 1;
        this.mUniqueID = i;
    }

    public boolean canCoalesce() {
        return !(this instanceof ReactSlidingCompleteEvent);
    }

    public abstract void dispatch(RCTEventEmitter rCTEventEmitter);

    public short getCoalescingKey() {
        return (short) 0;
    }

    public abstract String getEventName();

    public void init(int i) {
        this.mViewTag = i;
        this.mTimestampMs = SystemClock.uptimeMillis();
        this.mInitialized = true;
    }

    public void onDispose() {
    }

    public Event(int i) {
        int i2 = sUniqueID;
        sUniqueID = i2 + 1;
        this.mUniqueID = i2;
        this.mViewTag = i;
        this.mTimestampMs = SystemClock.uptimeMillis();
        this.mInitialized = true;
    }
}
