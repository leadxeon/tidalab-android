package com.facebook.react.views.image;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes.dex */
public class ImageLoadEvent extends Event<ImageLoadEvent> {
    public final int mEventType;
    public final int mHeight;
    public final String mImageError;
    public final String mImageUri;
    public final int mWidth;

    public ImageLoadEvent(int i, int i2) {
        super(i);
        this.mEventType = i2;
        this.mImageUri = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mImageError = null;
    }

    public static String eventNameForType(int i) {
        if (i == 1) {
            return "topError";
        }
        if (i == 2) {
            return "topLoad";
        }
        if (i == 3) {
            return "topLoadEnd";
        }
        if (i == 4) {
            return "topLoadStart";
        }
        if (i == 5) {
            return "topProgress";
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Invalid image event: ");
        outline13.append(Integer.toString(i));
        throw new IllegalStateException(outline13.toString());
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        WritableMap writableMap;
        int i;
        if (this.mImageUri != null || (i = this.mEventType) == 2 || i == 1) {
            writableMap = Arguments.createMap();
            String str = this.mImageUri;
            if (str != null) {
                writableMap.putString("uri", str);
            }
            int i2 = this.mEventType;
            if (i2 == 2) {
                WritableMap createMap = Arguments.createMap();
                createMap.putDouble("width", this.mWidth);
                createMap.putDouble("height", this.mHeight);
                String str2 = this.mImageUri;
                if (str2 != null) {
                    createMap.putString("url", str2);
                }
                writableMap.putMap("source", createMap);
            } else if (i2 == 1) {
                writableMap.putString("error", this.mImageError);
            }
        } else {
            writableMap = null;
        }
        rCTEventEmitter.receiveEvent(this.mViewTag, getEventName(), writableMap);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return (short) this.mEventType;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return eventNameForType(this.mEventType);
    }

    public ImageLoadEvent(int i, int i2, String str, int i3, int i4) {
        super(i);
        this.mEventType = i2;
        this.mImageUri = str;
        this.mWidth = i3;
        this.mHeight = i4;
        this.mImageError = null;
    }

    public ImageLoadEvent(int i, int i2, boolean z, String str) {
        super(i);
        this.mEventType = i2;
        this.mImageUri = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mImageError = str;
    }
}
