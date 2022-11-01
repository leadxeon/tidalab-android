package com.facebook.imagepipeline.nativecode;

import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.soloader.SoLoader;
@DoNotStrip
/* loaded from: classes.dex */
public class NativeRoundingFilter {
    static {
        SoLoader.loadLibrary("native-filters");
    }

    @DoNotStrip
    private static native void nativeToCircleFilter(Bitmap bitmap, boolean z);

    @DoNotStrip
    private static native void nativeToCircleWithBorderFilter(Bitmap bitmap, int i, int i2, boolean z);
}
