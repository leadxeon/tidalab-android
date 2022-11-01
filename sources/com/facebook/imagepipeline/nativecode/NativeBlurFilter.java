package com.facebook.imagepipeline.nativecode;

import android.graphics.Bitmap;
import androidx.recyclerview.R$dimen;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.soloader.SoLoader;
import java.util.Objects;
@DoNotStrip
/* loaded from: classes.dex */
public class NativeBlurFilter {
    static {
        SoLoader.loadLibrary("native-filters");
    }

    public static void iterativeBoxBlur(Bitmap bitmap, int i, int i2) {
        Objects.requireNonNull(bitmap);
        boolean z = true;
        R$dimen.checkArgument(i > 0);
        if (i2 <= 0) {
            z = false;
        }
        R$dimen.checkArgument(z);
        nativeIterativeBoxBlur(bitmap, i, i2);
    }

    @DoNotStrip
    private static native void nativeIterativeBoxBlur(Bitmap bitmap, int i, int i2);
}
