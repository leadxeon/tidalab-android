package com.facebook.common.time;

import android.os.SystemClock;
import com.facebook.common.internal.DoNotStrip;
@DoNotStrip
/* loaded from: classes.dex */
public class RealtimeSinceBootClock implements MonotonicClock {
    public static final RealtimeSinceBootClock INSTANCE = new RealtimeSinceBootClock();

    private RealtimeSinceBootClock() {
    }

    @DoNotStrip
    public static RealtimeSinceBootClock get() {
        return INSTANCE;
    }

    @Override // com.facebook.common.time.MonotonicClock
    public long now() {
        return SystemClock.elapsedRealtime();
    }
}
