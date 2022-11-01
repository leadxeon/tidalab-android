package com.facebook.imagepipeline.cache;

import android.app.ActivityManager;
import com.facebook.common.internal.Supplier;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class DefaultBitmapMemoryCacheParamsSupplier implements Supplier<MemoryCacheParams> {
    public static final long PARAMS_CHECK_INTERVAL_MS = TimeUnit.MINUTES.toMillis(5);
    public final ActivityManager mActivityManager;

    public DefaultBitmapMemoryCacheParamsSupplier(ActivityManager activityManager) {
        this.mActivityManager = activityManager;
    }

    @Override // com.facebook.common.internal.Supplier
    public MemoryCacheParams get() {
        int i;
        int min = Math.min(this.mActivityManager.getMemoryClass() * 1048576, Integer.MAX_VALUE);
        if (min < 33554432) {
            i = 4194304;
        } else {
            i = min < 67108864 ? 6291456 : min / 4;
        }
        return new MemoryCacheParams(i, 256, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, PARAMS_CHECK_INTERVAL_MS);
    }
}
