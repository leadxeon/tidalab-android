package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Supplier;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class DefaultEncodedMemoryCacheParamsSupplier implements Supplier<MemoryCacheParams> {
    public static final long PARAMS_CHECK_INTERVAL_MS = TimeUnit.MINUTES.toMillis(5);

    @Override // com.facebook.common.internal.Supplier
    public MemoryCacheParams get() {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        int i = min < 16777216 ? 1048576 : min < 33554432 ? 2097152 : 4194304;
        return new MemoryCacheParams(i, Integer.MAX_VALUE, i, Integer.MAX_VALUE, i / 8, PARAMS_CHECK_INTERVAL_MS);
    }
}
