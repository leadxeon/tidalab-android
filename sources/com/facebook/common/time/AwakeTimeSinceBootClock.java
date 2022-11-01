package com.facebook.common.time;

import android.os.SystemClock;
import com.facebook.common.internal.DoNotStrip;
@DoNotStrip
/* loaded from: classes.dex */
public class AwakeTimeSinceBootClock implements MonotonicClock {
    @DoNotStrip
    private static final AwakeTimeSinceBootClock INSTANCE = new AwakeTimeSinceBootClock();

    private AwakeTimeSinceBootClock() {
    }

    @DoNotStrip
    public static AwakeTimeSinceBootClock get() {
        return INSTANCE;
    }

    @Override // com.facebook.common.time.MonotonicClock
    @DoNotStrip
    public long now() {
        return SystemClock.uptimeMillis();
    }
}
