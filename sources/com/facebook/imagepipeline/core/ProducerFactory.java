package com.facebook.imagepipeline.core;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.ResizeAndRotateProducer;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
/* loaded from: classes.dex */
public class ProducerFactory {
    public AssetManager mAssetManager;
    public final MemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache;
    public boolean mBitmapPrepareToDrawForPrefetch;
    public final int mBitmapPrepareToDrawMaxSizeBytes;
    public final int mBitmapPrepareToDrawMinSizeBytes;
    public final ByteArrayPool mByteArrayPool;
    public final CacheKeyFactory mCacheKeyFactory;
    public final CloseableReferenceFactory mCloseableReferenceFactory;
    public ContentResolver mContentResolver;
    public final boolean mDecodeCancellationEnabled;
    public final BufferedDiskCache mDefaultBufferedDiskCache;
    public final boolean mDownsampleEnabled;
    public final MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;
    public final ExecutorSupplier mExecutorSupplier;
    public final ImageDecoder mImageDecoder;
    public final int mMaxBitmapSize;
    public final PlatformBitmapFactory mPlatformBitmapFactory;
    public final PooledByteBufferFactory mPooledByteBufferFactory;
    public final ProgressiveJpegConfig mProgressiveJpegConfig;
    public final boolean mResizeAndRotateEnabledForNetwork;
    public Resources mResources;
    public final BufferedDiskCache mSmallImageBufferedDiskCache;

    public ProducerFactory(Context context, ByteArrayPool byteArrayPool, ImageDecoder imageDecoder, ProgressiveJpegConfig progressiveJpegConfig, boolean z, boolean z2, boolean z3, ExecutorSupplier executorSupplier, PooledByteBufferFactory pooledByteBufferFactory, MemoryCache<CacheKey, CloseableImage> memoryCache, MemoryCache<CacheKey, PooledByteBuffer> memoryCache2, BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory, PlatformBitmapFactory platformBitmapFactory, int i, int i2, boolean z4, int i3, CloseableReferenceFactory closeableReferenceFactory) {
        this.mContentResolver = context.getApplicationContext().getContentResolver();
        this.mResources = context.getApplicationContext().getResources();
        this.mAssetManager = context.getApplicationContext().getAssets();
        this.mByteArrayPool = byteArrayPool;
        this.mImageDecoder = imageDecoder;
        this.mProgressiveJpegConfig = progressiveJpegConfig;
        this.mDownsampleEnabled = z;
        this.mResizeAndRotateEnabledForNetwork = z2;
        this.mDecodeCancellationEnabled = z3;
        this.mExecutorSupplier = executorSupplier;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mBitmapMemoryCache = memoryCache;
        this.mEncodedMemoryCache = memoryCache2;
        this.mDefaultBufferedDiskCache = bufferedDiskCache;
        this.mSmallImageBufferedDiskCache = bufferedDiskCache2;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mPlatformBitmapFactory = platformBitmapFactory;
        this.mBitmapPrepareToDrawMinSizeBytes = i;
        this.mBitmapPrepareToDrawMaxSizeBytes = i2;
        this.mBitmapPrepareToDrawForPrefetch = z4;
        this.mMaxBitmapSize = i3;
        this.mCloseableReferenceFactory = closeableReferenceFactory;
    }

    public ResizeAndRotateProducer newResizeAndRotateProducer(Producer<EncodedImage> producer, boolean z, ImageTranscoderFactory imageTranscoderFactory) {
        return new ResizeAndRotateProducer(this.mExecutorSupplier.forBackgroundTasks(), this.mPooledByteBufferFactory, producer, z, imageTranscoderFactory);
    }
}
