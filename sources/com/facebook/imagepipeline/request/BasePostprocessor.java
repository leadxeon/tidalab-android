package com.facebook.imagepipeline.request;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.nativecode.Bitmaps;
/* loaded from: classes.dex */
public abstract class BasePostprocessor implements Postprocessor {
    public static final Bitmap.Config FALLBACK_BITMAP_CONFIGURATION = Bitmap.Config.ARGB_8888;

    @Override // com.facebook.imagepipeline.request.Postprocessor
    public String getName() {
        return "Unknown postprocessor";
    }

    @Override // com.facebook.imagepipeline.request.Postprocessor
    public CacheKey getPostprocessorCacheKey() {
        return null;
    }

    @Override // com.facebook.imagepipeline.request.Postprocessor
    public CloseableReference<Bitmap> process(Bitmap bitmap, PlatformBitmapFactory platformBitmapFactory) {
        Bitmap.Config config = bitmap.getConfig();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (config == null) {
            config = FALLBACK_BITMAP_CONFIGURATION;
        }
        CloseableReference<Bitmap> createBitmapInternal = platformBitmapFactory.createBitmapInternal(width, height, config);
        try {
            process(createBitmapInternal.get(), bitmap);
            CloseableReference<Bitmap> cloneOrNull = CloseableReference.cloneOrNull(createBitmapInternal);
            createBitmapInternal.close();
            return cloneOrNull;
        } catch (Throwable th) {
            Class<CloseableReference> cls = CloseableReference.TAG;
            if (createBitmapInternal != null) {
                createBitmapInternal.close();
            }
            throw th;
        }
    }

    public void process(Bitmap bitmap) {
    }

    public void process(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap.getConfig() == bitmap2.getConfig()) {
            Bitmaps.copyBitmap(bitmap, bitmap2);
        } else {
            new Canvas(bitmap).drawBitmap(bitmap2, 0.0f, 0.0f, (Paint) null);
        }
        process(bitmap);
    }
}
