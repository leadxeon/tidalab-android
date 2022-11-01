package com.facebook.yoga;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.reactnativecommunity.webview.RNCWebViewManager;
/* loaded from: classes.dex */
public enum YogaEdge {
    LEFT(0),
    TOP(1),
    RIGHT(2),
    BOTTOM(3),
    START(4),
    END(5),
    HORIZONTAL(6),
    VERTICAL(7),
    ALL(8);
    
    public final int mIntValue;

    YogaEdge(int i) {
        this.mIntValue = i;
    }

    public static YogaEdge fromInt(int i) {
        switch (i) {
            case 0:
                return LEFT;
            case 1:
                return TOP;
            case 2:
                return RIGHT;
            case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                return BOTTOM;
            case 4:
                return START;
            case 5:
                return END;
            case 6:
                return HORIZONTAL;
            case 7:
                return VERTICAL;
            case 8:
                return ALL;
            default:
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline3("Unknown enum value: ", i));
        }
    }
}
