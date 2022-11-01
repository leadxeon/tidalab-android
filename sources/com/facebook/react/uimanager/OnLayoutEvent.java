package com.facebook.react.uimanager;

import android.os.SystemClock;
import androidx.core.util.Pools$SynchronizedPool;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes.dex */
public class OnLayoutEvent extends Event<OnLayoutEvent> {
    public static final Pools$SynchronizedPool<OnLayoutEvent> EVENTS_POOL = new Pools$SynchronizedPool<>(20);
    public int mHeight;
    public int mWidth;
    public int mX;
    public int mY;

    public static OnLayoutEvent obtain(int i, int i2, int i3, int i4, int i5) {
        OnLayoutEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new OnLayoutEvent();
        }
        acquire.mViewTag = i;
        acquire.mTimestampMs = SystemClock.uptimeMillis();
        acquire.mInitialized = true;
        acquire.mX = i2;
        acquire.mY = i3;
        acquire.mWidth = i4;
        acquire.mHeight = i5;
        return acquire;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("x", PixelUtil.toDIPFromPixel(this.mX));
        createMap.putDouble("y", PixelUtil.toDIPFromPixel(this.mY));
        createMap.putDouble("width", PixelUtil.toDIPFromPixel(this.mWidth));
        createMap.putDouble("height", PixelUtil.toDIPFromPixel(this.mHeight));
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putMap("layout", createMap);
        createMap2.putInt("target", this.mViewTag);
        rCTEventEmitter.receiveEvent(this.mViewTag, "topLayout", createMap2);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topLayout";
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void onDispose() {
        EVENTS_POOL.release(this);
    }
}
