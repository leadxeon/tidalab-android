package com.facebook.imagepipeline.platform;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import androidx.core.util.Pools$SynchronizedPool;
import com.facebook.imagepipeline.memory.BitmapPool;
import com.facebook.imageutils.BitmapUtil;
@TargetApi(26)
/* loaded from: classes.dex */
public class OreoDecoder extends DefaultDecoder {
    public OreoDecoder(BitmapPool bitmapPool, int i, Pools$SynchronizedPool pools$SynchronizedPool) {
        super(bitmapPool, i, pools$SynchronizedPool);
    }

    @Override // com.facebook.imagepipeline.platform.DefaultDecoder
    public int getBitmapSize(int i, int i2, BitmapFactory.Options options) {
        ColorSpace colorSpace = options.outColorSpace;
        return colorSpace != null && colorSpace.isWideGamut() && options.inPreferredConfig != Bitmap.Config.RGBA_F16 ? i * i2 * 8 : BitmapUtil.getSizeInByteForBitmap(i, i2, options.inPreferredConfig);
    }
}
