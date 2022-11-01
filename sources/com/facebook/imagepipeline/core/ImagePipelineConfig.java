package com.facebook.imagepipeline.core;

import android.content.Context;
import android.graphics.Bitmap;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imagepipeline.cache.BitmapMemoryCacheTrimStrategy;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineExperiments;
import com.facebook.imagepipeline.debug.NoOpCloseableReferenceLeakTracker;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
public class ImagePipelineConfig {
    public static DefaultImageRequestConfig sDefaultImageRequestConfig = new DefaultImageRequestConfig(null);
    public final Supplier<MemoryCacheParams> mBitmapMemoryCacheParamsSupplier;
    public final CacheKeyFactory mCacheKeyFactory;
    public final NoOpCloseableReferenceLeakTracker mCloseableReferenceLeakTracker;
    public final Context mContext;
    public final boolean mDiskCacheEnabled;
    public final boolean mDownsampleEnabled;
    public final Supplier<MemoryCacheParams> mEncodedMemoryCacheParamsSupplier;
    public final ExecutorSupplier mExecutorSupplier;
    public final FileCacheFactory mFileCacheFactory;
    public final int mHttpNetworkTimeout;
    public final ImageCacheStatsTracker mImageCacheStatsTracker;
    public final ImagePipelineExperiments mImagePipelineExperiments;
    public final Supplier<Boolean> mIsPrefetchEnabledSupplier;
    public final DiskCacheConfig mMainDiskCacheConfig;
    public final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    public final NetworkFetcher mNetworkFetcher;
    public final PoolFactory mPoolFactory;
    public final ProgressiveJpegConfig mProgressiveJpegConfig;
    public final Set<RequestListener> mRequestListeners;
    public final boolean mResizeAndRotateEnabledForNetwork;
    public final DiskCacheConfig mSmallImageDiskCacheConfig;
    public final CountingMemoryCache.CacheTrimStrategy mBitmapMemoryCacheTrimStrategy = new BitmapMemoryCacheTrimStrategy();
    public final Bitmap.Config mBitmapConfig = Bitmap.Config.ARGB_8888;

    /* loaded from: classes.dex */
    public static class Builder {
        public final Context mContext;
        public NetworkFetcher mNetworkFetcher;
        public Set<RequestListener> mRequestListeners;
        public boolean mDownsampleEnabled = false;
        public final ImagePipelineExperiments.Builder mExperimentsBuilder = new ImagePipelineExperiments.Builder(this);
        public NoOpCloseableReferenceLeakTracker mCloseableReferenceLeakTracker = new NoOpCloseableReferenceLeakTracker();

        public Builder(Context context, AnonymousClass1 r2) {
            Objects.requireNonNull(context);
            this.mContext = context;
        }
    }

    /* loaded from: classes.dex */
    public static class DefaultImageRequestConfig {
        public DefaultImageRequestConfig(AnonymousClass1 r1) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ImagePipelineConfig(com.facebook.imagepipeline.core.ImagePipelineConfig.Builder r6, com.facebook.imagepipeline.core.ImagePipelineConfig.AnonymousClass1 r7) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.core.ImagePipelineConfig.<init>(com.facebook.imagepipeline.core.ImagePipelineConfig$Builder, com.facebook.imagepipeline.core.ImagePipelineConfig$1):void");
    }
}
