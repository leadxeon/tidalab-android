package com.facebook.imagepipeline.platform;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import com.facebook.soloader.DoNotOptimize;
@DoNotOptimize
/* loaded from: classes.dex */
public class PreverificationHelper {
    @DoNotOptimize
    @TargetApi(26)
    public boolean shouldUseHardwareBitmapConfig(Bitmap.Config config) {
        return config == Bitmap.Config.HARDWARE;
    }
}
