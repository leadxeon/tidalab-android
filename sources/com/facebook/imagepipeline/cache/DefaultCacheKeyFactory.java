package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.Postprocessor;
/* loaded from: classes.dex */
public class DefaultCacheKeyFactory implements CacheKeyFactory {
    public static DefaultCacheKeyFactory sInstance;

    public CacheKey getBitmapCacheKey(ImageRequest imageRequest, Object obj) {
        return new BitmapMemoryCacheKey(imageRequest.mSourceUri.toString(), imageRequest.mResizeOptions, imageRequest.mRotationOptions, imageRequest.mImageDecodeOptions, null, null, obj);
    }

    public CacheKey getEncodedCacheKey(ImageRequest imageRequest, Object obj) {
        return new SimpleCacheKey(imageRequest.mSourceUri.toString());
    }

    public CacheKey getPostprocessedBitmapCacheKey(ImageRequest imageRequest, Object obj) {
        String str;
        Postprocessor postprocessor = imageRequest.mPostprocessor;
        CacheKey cacheKey = null;
        if (postprocessor != null) {
            CacheKey postprocessorCacheKey = postprocessor.getPostprocessorCacheKey();
            str = postprocessor.getClass().getName();
            cacheKey = postprocessorCacheKey;
        } else {
            str = null;
        }
        return new BitmapMemoryCacheKey(imageRequest.mSourceUri.toString(), imageRequest.mResizeOptions, imageRequest.mRotationOptions, imageRequest.mImageDecodeOptions, cacheKey, str, obj);
    }
}
