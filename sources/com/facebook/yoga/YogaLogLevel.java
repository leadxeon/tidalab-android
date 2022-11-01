package com.facebook.yoga;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.proguard.annotations.DoNotStrip;
@DoNotStrip
/* loaded from: classes.dex */
public enum YogaLogLevel {
    ERROR(0),
    WARN(1),
    INFO(2),
    DEBUG(3),
    VERBOSE(4),
    FATAL(5);

    YogaLogLevel(int i) {
    }

    @DoNotStrip
    public static YogaLogLevel fromInt(int i) {
        if (i == 0) {
            return ERROR;
        }
        if (i == 1) {
            return WARN;
        }
        if (i == 2) {
            return INFO;
        }
        if (i == 3) {
            return DEBUG;
        }
        if (i == 4) {
            return VERBOSE;
        }
        if (i == 5) {
            return FATAL;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport.outline3("Unknown enum value: ", i));
    }
}
