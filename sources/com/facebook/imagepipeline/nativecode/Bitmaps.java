package com.facebook.imagepipeline.nativecode;

import android.graphics.Bitmap;
import androidx.recyclerview.R$dimen;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.soloader.SoLoader;
import java.util.List;
@DoNotStrip
/* loaded from: classes.dex */
public class Bitmaps {
    static {
        List<String> list = ImagePipelineNativeLoader.DEPENDENCIES;
        SoLoader.loadLibrary("imagepipeline");
    }

    public static void copyBitmap(Bitmap bitmap, Bitmap bitmap2) {
        boolean z = false;
        R$dimen.checkArgument(bitmap2.getConfig() == bitmap.getConfig());
        R$dimen.checkArgument(bitmap.isMutable());
        R$dimen.checkArgument(bitmap.getWidth() == bitmap2.getWidth());
        if (bitmap.getHeight() == bitmap2.getHeight()) {
            z = true;
        }
        R$dimen.checkArgument(z);
        nativeCopyBitmap(bitmap, bitmap.getRowBytes(), bitmap2, bitmap2.getRowBytes(), bitmap.getHeight());
    }

    @DoNotStrip
    private static native void nativeCopyBitmap(Bitmap bitmap, int i, Bitmap bitmap2, int i2, int i3);
}
