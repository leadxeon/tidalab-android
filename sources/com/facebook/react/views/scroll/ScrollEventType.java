package com.facebook.react.views.scroll;
/* loaded from: classes.dex */
public enum ScrollEventType {
    BEGIN_DRAG,
    END_DRAG,
    SCROLL,
    MOMENTUM_BEGIN,
    MOMENTUM_END;

    public static String getJSEventName(ScrollEventType scrollEventType) {
        int ordinal = scrollEventType.ordinal();
        if (ordinal == 0) {
            return "topScrollBeginDrag";
        }
        if (ordinal == 1) {
            return "topScrollEndDrag";
        }
        if (ordinal == 2) {
            return "topScroll";
        }
        if (ordinal == 3) {
            return "topMomentumScrollBegin";
        }
        if (ordinal == 4) {
            return "topMomentumScrollEnd";
        }
        throw new IllegalArgumentException("Unsupported ScrollEventType: " + scrollEventType);
    }
}
