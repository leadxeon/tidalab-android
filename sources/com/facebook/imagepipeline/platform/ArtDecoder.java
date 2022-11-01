package com.facebook.imagepipeline.platform;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory;
import androidx.core.util.Pools$SynchronizedPool;
import com.facebook.imagepipeline.memory.BitmapPool;
import com.facebook.imageutils.BitmapUtil;
@TargetApi(21)
/* loaded from: classes.dex */
public class ArtDecoder extends DefaultDecoder {
    public ArtDecoder(BitmapPool bitmapPool, int i, Pools$SynchronizedPool pools$SynchronizedPool) {
        super(bitmapPool, i, pools$SynchronizedPool);
    }

    @Override // com.facebook.imagepipeline.platform.DefaultDecoder
    public int getBitmapSize(int i, int i2, BitmapFactory.Options options) {
        return BitmapUtil.getSizeInByteForBitmap(i, i2, options.inPreferredConfig);
    }
}
