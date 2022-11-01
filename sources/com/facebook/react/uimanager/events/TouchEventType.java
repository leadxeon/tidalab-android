package com.facebook.react.uimanager.events;
/* loaded from: classes.dex */
public enum TouchEventType {
    START,
    END,
    MOVE,
    CANCEL;

    public static String getJSEventName(TouchEventType touchEventType) {
        int ordinal = touchEventType.ordinal();
        if (ordinal == 0) {
            return "topTouchStart";
        }
        if (ordinal == 1) {
            return "topTouchEnd";
        }
        if (ordinal == 2) {
            return "topTouchMove";
        }
        if (ordinal == 3) {
            return "topTouchCancel";
        }
        throw new IllegalArgumentException("Unexpected type " + touchEventType);
    }
}
