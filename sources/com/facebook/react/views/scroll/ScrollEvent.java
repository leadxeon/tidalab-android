package com.facebook.react.views.scroll;

import android.os.SystemClock;
import androidx.core.util.Pools$SynchronizedPool;
import androidx.recyclerview.R$dimen;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes.dex */
public class ScrollEvent extends Event<ScrollEvent> {
    public static final Pools$SynchronizedPool<ScrollEvent> EVENTS_POOL = new Pools$SynchronizedPool<>(3);
    public int mContentHeight;
    public int mContentWidth;
    public ScrollEventType mScrollEventType;
    public int mScrollViewHeight;
    public int mScrollViewWidth;
    public int mScrollX;
    public int mScrollY;
    public double mXVelocity;
    public double mYVelocity;

    public static ScrollEvent obtain(int i, ScrollEventType scrollEventType, int i2, int i3, float f, float f2, int i4, int i5, int i6, int i7) {
        ScrollEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new ScrollEvent();
        }
        acquire.mViewTag = i;
        acquire.mTimestampMs = SystemClock.uptimeMillis();
        acquire.mInitialized = true;
        acquire.mScrollEventType = scrollEventType;
        acquire.mScrollX = i2;
        acquire.mScrollY = i3;
        acquire.mXVelocity = f;
        acquire.mYVelocity = f2;
        acquire.mContentWidth = i4;
        acquire.mContentHeight = i5;
        acquire.mScrollViewWidth = i6;
        acquire.mScrollViewHeight = i7;
        return acquire;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return this.mScrollEventType == ScrollEventType.SCROLL;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        int i = this.mViewTag;
        String eventName = getEventName();
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("top", 0.0d);
        createMap.putDouble("bottom", 0.0d);
        createMap.putDouble("left", 0.0d);
        createMap.putDouble("right", 0.0d);
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putDouble("x", PixelUtil.toDIPFromPixel(this.mScrollX));
        createMap2.putDouble("y", PixelUtil.toDIPFromPixel(this.mScrollY));
        WritableMap createMap3 = Arguments.createMap();
        createMap3.putDouble("width", PixelUtil.toDIPFromPixel(this.mContentWidth));
        createMap3.putDouble("height", PixelUtil.toDIPFromPixel(this.mContentHeight));
        WritableMap createMap4 = Arguments.createMap();
        createMap4.putDouble("width", PixelUtil.toDIPFromPixel(this.mScrollViewWidth));
        createMap4.putDouble("height", PixelUtil.toDIPFromPixel(this.mScrollViewHeight));
        WritableMap createMap5 = Arguments.createMap();
        createMap5.putDouble("x", this.mXVelocity);
        createMap5.putDouble("y", this.mYVelocity);
        WritableMap createMap6 = Arguments.createMap();
        createMap6.putMap("contentInset", createMap);
        createMap6.putMap("contentOffset", createMap2);
        createMap6.putMap("contentSize", createMap3);
        createMap6.putMap("layoutMeasurement", createMap4);
        createMap6.putMap("velocity", createMap5);
        createMap6.putInt("target", this.mViewTag);
        createMap6.putBoolean("responderIgnoreScroll", true);
        rCTEventEmitter.receiveEvent(i, eventName, createMap6);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return (short) 0;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        ScrollEventType scrollEventType = this.mScrollEventType;
        R$dimen.assertNotNull(scrollEventType);
        return ScrollEventType.getJSEventName(scrollEventType);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void onDispose() {
        EVENTS_POOL.release(this);
    }
}
