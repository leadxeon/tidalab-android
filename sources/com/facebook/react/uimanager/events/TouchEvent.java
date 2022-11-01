package com.facebook.react.uimanager.events;

import android.os.SystemClock;
import android.view.MotionEvent;
import androidx.core.util.Pools$SynchronizedPool;
import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import okhttp3.internal.http2.Settings;
/* loaded from: classes.dex */
public class TouchEvent extends Event<TouchEvent> {
    public static final Pools$SynchronizedPool<TouchEvent> EVENTS_POOL = new Pools$SynchronizedPool<>(3);
    public short mCoalescingKey;
    public MotionEvent mMotionEvent;
    public TouchEventType mTouchEventType;
    public float mViewX;
    public float mViewY;

    public static TouchEvent obtain(int i, TouchEventType touchEventType, MotionEvent motionEvent, long j, float f, float f2, TouchEventCoalescingKeyHelper touchEventCoalescingKeyHelper) {
        TouchEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new TouchEvent();
        }
        acquire.mViewTag = i;
        acquire.mTimestampMs = SystemClock.uptimeMillis();
        acquire.mInitialized = true;
        short s = 0;
        SoftAssertions.assertCondition(j != Long.MIN_VALUE, "Gesture start time must be initialized");
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            touchEventCoalescingKeyHelper.mDownTimeToCoalescingKey.put((int) j, 0);
        } else if (action == 1) {
            touchEventCoalescingKeyHelper.mDownTimeToCoalescingKey.delete((int) j);
        } else if (action == 2) {
            int i2 = touchEventCoalescingKeyHelper.mDownTimeToCoalescingKey.get((int) j, -1);
            if (i2 != -1) {
                s = (short) (i2 & Settings.DEFAULT_INITIAL_WINDOW_SIZE);
            } else {
                throw new RuntimeException("Tried to get non-existent cookie");
            }
        } else if (action == 3) {
            touchEventCoalescingKeyHelper.mDownTimeToCoalescingKey.delete((int) j);
        } else if (action == 5 || action == 6) {
            int i3 = (int) j;
            int i4 = touchEventCoalescingKeyHelper.mDownTimeToCoalescingKey.get(i3, -1);
            if (i4 != -1) {
                touchEventCoalescingKeyHelper.mDownTimeToCoalescingKey.put(i3, i4 + 1);
            } else {
                throw new RuntimeException("Tried to increment non-existent cookie");
            }
        } else {
            throw new RuntimeException(GeneratedOutlineSupport.outline3("Unhandled MotionEvent action: ", action));
        }
        acquire.mTouchEventType = touchEventType;
        acquire.mMotionEvent = MotionEvent.obtain(motionEvent);
        acquire.mCoalescingKey = s;
        acquire.mViewX = f;
        acquire.mViewY = f2;
        return acquire;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        TouchEventType touchEventType = this.mTouchEventType;
        R$dimen.assertNotNull(touchEventType);
        int ordinal = touchEventType.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            return false;
        }
        if (ordinal == 2) {
            return true;
        }
        if (ordinal == 3) {
            return false;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unknown touch event type: ");
        outline13.append(this.mTouchEventType);
        throw new RuntimeException(outline13.toString());
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        TouchEventType touchEventType = this.mTouchEventType;
        R$dimen.assertNotNull(touchEventType);
        TouchEventType touchEventType2 = touchEventType;
        int i = this.mViewTag;
        WritableArray createArray = Arguments.createArray();
        R$dimen.assertNotNull(this.mMotionEvent);
        MotionEvent motionEvent = this.mMotionEvent;
        float x = motionEvent.getX() - this.mViewX;
        float y = motionEvent.getY() - this.mViewY;
        for (int i2 = 0; i2 < motionEvent.getPointerCount(); i2++) {
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("pageX", PixelUtil.toDIPFromPixel(motionEvent.getX(i2)));
            createMap.putDouble("pageY", PixelUtil.toDIPFromPixel(motionEvent.getY(i2)));
            createMap.putDouble("locationX", PixelUtil.toDIPFromPixel(motionEvent.getX(i2) - x));
            createMap.putDouble("locationY", PixelUtil.toDIPFromPixel(motionEvent.getY(i2) - y));
            createMap.putInt("target", i);
            createMap.putDouble("timestamp", this.mTimestampMs);
            createMap.putDouble("identifier", motionEvent.getPointerId(i2));
            createArray.pushMap(createMap);
        }
        R$dimen.assertNotNull(this.mMotionEvent);
        MotionEvent motionEvent2 = this.mMotionEvent;
        WritableArray createArray2 = Arguments.createArray();
        if (touchEventType2 == TouchEventType.MOVE || touchEventType2 == TouchEventType.CANCEL) {
            for (int i3 = 0; i3 < motionEvent2.getPointerCount(); i3++) {
                createArray2.pushInt(i3);
            }
        } else if (touchEventType2 == TouchEventType.START || touchEventType2 == TouchEventType.END) {
            createArray2.pushInt(motionEvent2.getActionIndex());
        } else {
            throw new RuntimeException("Unknown touch type: " + touchEventType2);
        }
        rCTEventEmitter.receiveTouches(TouchEventType.getJSEventName(touchEventType2), createArray, createArray2);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return this.mCoalescingKey;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        TouchEventType touchEventType = this.mTouchEventType;
        R$dimen.assertNotNull(touchEventType);
        return TouchEventType.getJSEventName(touchEventType);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void onDispose() {
        MotionEvent motionEvent = this.mMotionEvent;
        R$dimen.assertNotNull(motionEvent);
        motionEvent.recycle();
        this.mMotionEvent = null;
        EVENTS_POOL.release(this);
    }
}
