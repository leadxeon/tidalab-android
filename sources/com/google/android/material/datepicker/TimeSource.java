package com.google.android.material.datepicker;

import java.util.TimeZone;
/* loaded from: classes.dex */
public class TimeSource {
    public static final TimeSource SYSTEM_TIME_SOURCE = new TimeSource(null, null);
    public final Long fixedTimeMs = null;
    public final TimeZone fixedTimeZone = null;

    public TimeSource(Long l, TimeZone timeZone) {
    }
}
