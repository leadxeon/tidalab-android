package com.facebook.imagepipeline.bitmaps;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import androidx.recyclerview.R$dimen;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.core.CloseableReferenceFactory;
import com.facebook.imagepipeline.memory.BitmapPool;
import com.facebook.imageutils.BitmapUtil;
@TargetApi(21)
/* loaded from: classes.dex */
public class ArtBitmapFactory extends PlatformBitmapFactory {
    public final BitmapPool mBitmapPool;
    public final CloseableReferenceFactory mCloseableReferenceFactory;

    public ArtBitmapFactory(BitmapPool bitmapPool, CloseableReferenceFactory closeableReferenceFactory) {
        this.mBitmapPool = bitmapPool;
        this.mCloseableReferenceFactory = closeableReferenceFactory;
    }

    @Override // com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory
    public CloseableReference<Bitmap> createBitmapInternal(int i, int i2, Bitmap.Config config) {
        Bitmap bitmap = this.mBitmapPool.get(BitmapUtil.getSizeInByteForBitmap(i, i2, config));
        R$dimen.checkArgument(bitmap.getAllocationByteCount() >= BitmapUtil.getPixelSizeForBitmapConfig(config) * (i * i2));
        bitmap.reconfigure(i, i2, config);
        return CloseableReference.of(bitmap, this.mBitmapPool, this.mCloseableReferenceFactory.mLeakHandler);
    }
}
