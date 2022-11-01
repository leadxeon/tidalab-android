package com.facebook.imagepipeline.core;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import androidx.core.util.Pools$SynchronizedPool;
import androidx.recyclerview.R$dimen;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.disk.FileCache;
import com.facebook.common.internal.Supplier;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.animated.factory.AnimatedFactory;
import com.facebook.imagepipeline.animated.factory.AnimatedFactoryProvider;
import com.facebook.imagepipeline.bitmaps.ArtBitmapFactory;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker;
import com.facebook.imagepipeline.cache.InstrumentedMemoryCache;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.cache.MemoryCacheTracker;
import com.facebook.imagepipeline.cache.NativeMemoryCacheTrimStrategy;
import com.facebook.imagepipeline.cache.NoOpImageCacheStatsTracker;
import com.facebook.imagepipeline.cache.ValueDescriptor;
import com.facebook.imagepipeline.core.ImagePipelineExperiments;
import com.facebook.imagepipeline.decoder.DefaultImageDecoder;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.platform.ArtDecoder;
import com.facebook.imagepipeline.platform.OreoDecoder;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
import com.facebook.imagepipeline.transcoder.MultiImageTranscoderFactory;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
public class ImagePipelineFactory {
    public static ImagePipelineFactory sInstance;
    public AnimatedFactory mAnimatedFactory;
    public CountingMemoryCache<CacheKey, CloseableImage> mBitmapCountingMemoryCache;
    public InstrumentedMemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache;
    public final CloseableReferenceFactory mCloseableReferenceFactory;
    public final ImagePipelineConfig mConfig;
    public CountingMemoryCache<CacheKey, PooledByteBuffer> mEncodedCountingMemoryCache;
    public InstrumentedMemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;
    public ImageDecoder mImageDecoder;
    public ImagePipeline mImagePipeline;
    public ImageTranscoderFactory mImageTranscoderFactory;
    public BufferedDiskCache mMainBufferedDiskCache;
    public FileCache mMainFileCache;
    public PlatformBitmapFactory mPlatformBitmapFactory;
    public PlatformDecoder mPlatformDecoder;
    public ProducerFactory mProducerFactory;
    public ProducerSequenceFactory mProducerSequenceFactory;
    public BufferedDiskCache mSmallImageBufferedDiskCache;
    public FileCache mSmallImageFileCache;
    public final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;

    public ImagePipelineFactory(ImagePipelineConfig imagePipelineConfig) {
        FrescoSystrace.isTracing();
        Objects.requireNonNull(imagePipelineConfig);
        this.mConfig = imagePipelineConfig;
        this.mThreadHandoffProducerQueue = new ThreadHandoffProducerQueue(imagePipelineConfig.mExecutorSupplier.forLightweightBackgroundTasks());
        this.mCloseableReferenceFactory = new CloseableReferenceFactory(imagePipelineConfig.mCloseableReferenceLeakTracker);
        FrescoSystrace.isTracing();
    }

    public static ImagePipelineFactory getInstance() {
        ImagePipelineFactory imagePipelineFactory = sInstance;
        R$dimen.checkNotNull(imagePipelineFactory, "ImagePipelineFactory was not initialized!");
        return imagePipelineFactory;
    }

    public static synchronized void initialize(ImagePipelineConfig imagePipelineConfig) {
        synchronized (ImagePipelineFactory.class) {
            if (sInstance != null) {
                FLog.w(ImagePipelineFactory.class, "ImagePipelineFactory has already been initialized! `ImagePipelineFactory.initialize(...)` should only be called once to avoid unexpected behavior.");
            }
            sInstance = new ImagePipelineFactory(imagePipelineConfig);
        }
    }

    public final AnimatedFactory getAnimatedFactory() {
        if (this.mAnimatedFactory == null) {
            PlatformBitmapFactory platformBitmapFactory = getPlatformBitmapFactory();
            ExecutorSupplier executorSupplier = this.mConfig.mExecutorSupplier;
            CountingMemoryCache<CacheKey, CloseableImage> bitmapCountingMemoryCache = getBitmapCountingMemoryCache();
            Objects.requireNonNull(this.mConfig.mImagePipelineExperiments);
            if (!AnimatedFactoryProvider.sImplLoaded) {
                try {
                    AnimatedFactoryProvider.sImpl = (AnimatedFactory) Class.forName("com.facebook.fresco.animation.factory.AnimatedFactoryV2Impl").getConstructor(PlatformBitmapFactory.class, ExecutorSupplier.class, CountingMemoryCache.class, Boolean.TYPE).newInstance(platformBitmapFactory, executorSupplier, bitmapCountingMemoryCache, Boolean.FALSE);
                } catch (Throwable unused) {
                }
                if (AnimatedFactoryProvider.sImpl != null) {
                    AnimatedFactoryProvider.sImplLoaded = true;
                }
            }
            this.mAnimatedFactory = AnimatedFactoryProvider.sImpl;
        }
        return this.mAnimatedFactory;
    }

    public CountingMemoryCache<CacheKey, CloseableImage> getBitmapCountingMemoryCache() {
        if (this.mBitmapCountingMemoryCache == null) {
            ImagePipelineConfig imagePipelineConfig = this.mConfig;
            Supplier<MemoryCacheParams> supplier = imagePipelineConfig.mBitmapMemoryCacheParamsSupplier;
            MemoryTrimmableRegistry memoryTrimmableRegistry = imagePipelineConfig.mMemoryTrimmableRegistry;
            CountingMemoryCache<CacheKey, CloseableImage> countingMemoryCache = new CountingMemoryCache<>(new ValueDescriptor<CloseableImage>() { // from class: com.facebook.imagepipeline.cache.BitmapCountingMemoryCacheFactory$1
                @Override // com.facebook.imagepipeline.cache.ValueDescriptor
                public int getSizeInBytes(CloseableImage closeableImage) {
                    return closeableImage.getSizeInBytes();
                }
            }, imagePipelineConfig.mBitmapMemoryCacheTrimStrategy, supplier);
            memoryTrimmableRegistry.registerMemoryTrimmable(countingMemoryCache);
            this.mBitmapCountingMemoryCache = countingMemoryCache;
        }
        return this.mBitmapCountingMemoryCache;
    }

    public InstrumentedMemoryCache<CacheKey, CloseableImage> getBitmapMemoryCache() {
        if (this.mBitmapMemoryCache == null) {
            CountingMemoryCache<CacheKey, CloseableImage> bitmapCountingMemoryCache = getBitmapCountingMemoryCache();
            final ImageCacheStatsTracker imageCacheStatsTracker = this.mConfig.mImageCacheStatsTracker;
            Objects.requireNonNull((NoOpImageCacheStatsTracker) imageCacheStatsTracker);
            this.mBitmapMemoryCache = new InstrumentedMemoryCache<>(bitmapCountingMemoryCache, new MemoryCacheTracker<CacheKey>() { // from class: com.facebook.imagepipeline.cache.BitmapMemoryCacheFactory$1
                @Override // com.facebook.imagepipeline.cache.MemoryCacheTracker
                public void onCacheHit(CacheKey cacheKey) {
                    Objects.requireNonNull((NoOpImageCacheStatsTracker) ImageCacheStatsTracker.this);
                }

                @Override // com.facebook.imagepipeline.cache.MemoryCacheTracker
                public void onCacheMiss() {
                    Objects.requireNonNull((NoOpImageCacheStatsTracker) ImageCacheStatsTracker.this);
                }

                @Override // com.facebook.imagepipeline.cache.MemoryCacheTracker
                public void onCachePut() {
                    Objects.requireNonNull((NoOpImageCacheStatsTracker) ImageCacheStatsTracker.this);
                }
            });
        }
        return this.mBitmapMemoryCache;
    }

    public InstrumentedMemoryCache<CacheKey, PooledByteBuffer> getEncodedMemoryCache() {
        if (this.mEncodedMemoryCache == null) {
            if (this.mEncodedCountingMemoryCache == null) {
                ImagePipelineConfig imagePipelineConfig = this.mConfig;
                Supplier<MemoryCacheParams> supplier = imagePipelineConfig.mEncodedMemoryCacheParamsSupplier;
                MemoryTrimmableRegistry memoryTrimmableRegistry = imagePipelineConfig.mMemoryTrimmableRegistry;
                CountingMemoryCache<CacheKey, PooledByteBuffer> countingMemoryCache = new CountingMemoryCache<>(new ValueDescriptor<PooledByteBuffer>() { // from class: com.facebook.imagepipeline.cache.EncodedCountingMemoryCacheFactory$1
                    @Override // com.facebook.imagepipeline.cache.ValueDescriptor
                    public int getSizeInBytes(PooledByteBuffer pooledByteBuffer) {
                        return pooledByteBuffer.size();
                    }
                }, new NativeMemoryCacheTrimStrategy(), supplier);
                memoryTrimmableRegistry.registerMemoryTrimmable(countingMemoryCache);
                this.mEncodedCountingMemoryCache = countingMemoryCache;
            }
            CountingMemoryCache<CacheKey, PooledByteBuffer> countingMemoryCache2 = this.mEncodedCountingMemoryCache;
            final ImageCacheStatsTracker imageCacheStatsTracker = this.mConfig.mImageCacheStatsTracker;
            Objects.requireNonNull((NoOpImageCacheStatsTracker) imageCacheStatsTracker);
            this.mEncodedMemoryCache = new InstrumentedMemoryCache<>(countingMemoryCache2, new MemoryCacheTracker<CacheKey>() { // from class: com.facebook.imagepipeline.cache.EncodedMemoryCacheFactory$1
                @Override // com.facebook.imagepipeline.cache.MemoryCacheTracker
                public void onCacheHit(CacheKey cacheKey) {
                    Objects.requireNonNull((NoOpImageCacheStatsTracker) ImageCacheStatsTracker.this);
                }

                @Override // com.facebook.imagepipeline.cache.MemoryCacheTracker
                public void onCacheMiss() {
                    Objects.requireNonNull((NoOpImageCacheStatsTracker) ImageCacheStatsTracker.this);
                }

                @Override // com.facebook.imagepipeline.cache.MemoryCacheTracker
                public void onCachePut() {
                    Objects.requireNonNull((NoOpImageCacheStatsTracker) ImageCacheStatsTracker.this);
                }
            });
        }
        return this.mEncodedMemoryCache;
    }

    public ImagePipeline getImagePipeline() {
        ImageDecoder imageDecoder;
        ImageDecoder imageDecoder2;
        if (this.mImagePipeline == null) {
            if (Build.VERSION.SDK_INT >= 24) {
                Objects.requireNonNull(this.mConfig.mImagePipelineExperiments);
            }
            if (this.mProducerSequenceFactory == null) {
                ContentResolver contentResolver = this.mConfig.mContext.getApplicationContext().getContentResolver();
                if (this.mProducerFactory == null) {
                    ImagePipelineConfig imagePipelineConfig = this.mConfig;
                    ImagePipelineExperiments.ProducerFactoryMethod producerFactoryMethod = imagePipelineConfig.mImagePipelineExperiments.mProducerFactoryMethod;
                    Context context = imagePipelineConfig.mContext;
                    ByteArrayPool smallByteArrayPool = imagePipelineConfig.mPoolFactory.getSmallByteArrayPool();
                    if (this.mImageDecoder == null) {
                        Objects.requireNonNull(this.mConfig);
                        AnimatedFactory animatedFactory = getAnimatedFactory();
                        if (animatedFactory != null) {
                            imageDecoder = animatedFactory.getGifDecoder(this.mConfig.mBitmapConfig);
                            imageDecoder2 = animatedFactory.getWebPDecoder(this.mConfig.mBitmapConfig);
                        } else {
                            imageDecoder2 = null;
                            imageDecoder = null;
                        }
                        Objects.requireNonNull(this.mConfig);
                        this.mImageDecoder = new DefaultImageDecoder(imageDecoder, imageDecoder2, getPlatformDecoder());
                    }
                    ImageDecoder imageDecoder3 = this.mImageDecoder;
                    ImagePipelineConfig imagePipelineConfig2 = this.mConfig;
                    ProgressiveJpegConfig progressiveJpegConfig = imagePipelineConfig2.mProgressiveJpegConfig;
                    boolean z = imagePipelineConfig2.mDownsampleEnabled;
                    boolean z2 = imagePipelineConfig2.mResizeAndRotateEnabledForNetwork;
                    Objects.requireNonNull(imagePipelineConfig2.mImagePipelineExperiments);
                    ImagePipelineConfig imagePipelineConfig3 = this.mConfig;
                    ExecutorSupplier executorSupplier = imagePipelineConfig3.mExecutorSupplier;
                    PooledByteBufferFactory pooledByteBufferFactory = imagePipelineConfig3.mPoolFactory.getPooledByteBufferFactory(0);
                    InstrumentedMemoryCache<CacheKey, CloseableImage> bitmapMemoryCache = getBitmapMemoryCache();
                    InstrumentedMemoryCache<CacheKey, PooledByteBuffer> encodedMemoryCache = getEncodedMemoryCache();
                    BufferedDiskCache mainBufferedDiskCache = getMainBufferedDiskCache();
                    BufferedDiskCache smallImageBufferedDiskCache = getSmallImageBufferedDiskCache();
                    CacheKeyFactory cacheKeyFactory = this.mConfig.mCacheKeyFactory;
                    PlatformBitmapFactory platformBitmapFactory = getPlatformBitmapFactory();
                    Objects.requireNonNull(this.mConfig.mImagePipelineExperiments);
                    Objects.requireNonNull(this.mConfig.mImagePipelineExperiments);
                    Objects.requireNonNull(this.mConfig.mImagePipelineExperiments);
                    int i = this.mConfig.mImagePipelineExperiments.mMaxBitmapSize;
                    CloseableReferenceFactory closeableReferenceFactory = this.mCloseableReferenceFactory;
                    Objects.requireNonNull((ImagePipelineExperiments.DefaultProducerFactoryMethod) producerFactoryMethod);
                    this.mProducerFactory = new ProducerFactory(context, smallByteArrayPool, imageDecoder3, progressiveJpegConfig, z, z2, false, executorSupplier, pooledByteBufferFactory, bitmapMemoryCache, encodedMemoryCache, mainBufferedDiskCache, smallImageBufferedDiskCache, cacheKeyFactory, platformBitmapFactory, 0, 0, false, i, closeableReferenceFactory);
                }
                ProducerFactory producerFactory = this.mProducerFactory;
                ImagePipelineConfig imagePipelineConfig4 = this.mConfig;
                NetworkFetcher networkFetcher = imagePipelineConfig4.mNetworkFetcher;
                boolean z3 = imagePipelineConfig4.mResizeAndRotateEnabledForNetwork;
                Objects.requireNonNull(imagePipelineConfig4.mImagePipelineExperiments);
                ThreadHandoffProducerQueue threadHandoffProducerQueue = this.mThreadHandoffProducerQueue;
                ImagePipelineConfig imagePipelineConfig5 = this.mConfig;
                boolean z4 = imagePipelineConfig5.mDownsampleEnabled;
                Objects.requireNonNull(imagePipelineConfig5.mImagePipelineExperiments);
                ImagePipelineConfig imagePipelineConfig6 = this.mConfig;
                boolean z5 = imagePipelineConfig6.mDiskCacheEnabled;
                if (this.mImageTranscoderFactory == null) {
                    Objects.requireNonNull(imagePipelineConfig6.mImagePipelineExperiments);
                    this.mImageTranscoderFactory = new MultiImageTranscoderFactory(this.mConfig.mImagePipelineExperiments.mMaxBitmapSize, false, null, null);
                }
                this.mProducerSequenceFactory = new ProducerSequenceFactory(contentResolver, producerFactory, networkFetcher, z3, false, threadHandoffProducerQueue, z4, false, false, z5, this.mImageTranscoderFactory);
            }
            ProducerSequenceFactory producerSequenceFactory = this.mProducerSequenceFactory;
            Set unmodifiableSet = Collections.unmodifiableSet(this.mConfig.mRequestListeners);
            Supplier<Boolean> supplier = this.mConfig.mIsPrefetchEnabledSupplier;
            InstrumentedMemoryCache<CacheKey, CloseableImage> bitmapMemoryCache2 = getBitmapMemoryCache();
            InstrumentedMemoryCache<CacheKey, PooledByteBuffer> encodedMemoryCache2 = getEncodedMemoryCache();
            BufferedDiskCache mainBufferedDiskCache2 = getMainBufferedDiskCache();
            BufferedDiskCache smallImageBufferedDiskCache2 = getSmallImageBufferedDiskCache();
            ImagePipelineConfig imagePipelineConfig7 = this.mConfig;
            CacheKeyFactory cacheKeyFactory2 = imagePipelineConfig7.mCacheKeyFactory;
            ThreadHandoffProducerQueue threadHandoffProducerQueue2 = this.mThreadHandoffProducerQueue;
            final Boolean bool = Boolean.FALSE;
            Supplier<T> suppliers$1 = new Supplier<T>() { // from class: com.facebook.common.internal.Suppliers$1
                /* JADX WARN: Type inference failed for: r0v0, types: [T, java.lang.Object] */
                @Override // com.facebook.common.internal.Supplier
                public T get() {
                    return bool;
                }
            };
            Objects.requireNonNull(imagePipelineConfig7.mImagePipelineExperiments);
            Objects.requireNonNull(this.mConfig);
            this.mImagePipeline = new ImagePipeline(producerSequenceFactory, unmodifiableSet, supplier, bitmapMemoryCache2, encodedMemoryCache2, mainBufferedDiskCache2, smallImageBufferedDiskCache2, cacheKeyFactory2, threadHandoffProducerQueue2, suppliers$1, null, null);
        }
        return this.mImagePipeline;
    }

    public BufferedDiskCache getMainBufferedDiskCache() {
        if (this.mMainBufferedDiskCache == null) {
            if (this.mMainFileCache == null) {
                ImagePipelineConfig imagePipelineConfig = this.mConfig;
                this.mMainFileCache = ((DiskStorageCacheFactory) imagePipelineConfig.mFileCacheFactory).get(imagePipelineConfig.mMainDiskCacheConfig);
            }
            FileCache fileCache = this.mMainFileCache;
            ImagePipelineConfig imagePipelineConfig2 = this.mConfig;
            PoolFactory poolFactory = imagePipelineConfig2.mPoolFactory;
            Objects.requireNonNull(imagePipelineConfig2);
            this.mMainBufferedDiskCache = new BufferedDiskCache(fileCache, poolFactory.getPooledByteBufferFactory(0), this.mConfig.mPoolFactory.getPooledByteStreams(), this.mConfig.mExecutorSupplier.forLocalStorageRead(), this.mConfig.mExecutorSupplier.forLocalStorageWrite(), this.mConfig.mImageCacheStatsTracker);
        }
        return this.mMainBufferedDiskCache;
    }

    public PlatformBitmapFactory getPlatformBitmapFactory() {
        if (this.mPlatformBitmapFactory == null) {
            PoolFactory poolFactory = this.mConfig.mPoolFactory;
            getPlatformDecoder();
            this.mPlatformBitmapFactory = new ArtBitmapFactory(poolFactory.getBitmapPool(), this.mCloseableReferenceFactory);
        }
        return this.mPlatformBitmapFactory;
    }

    public PlatformDecoder getPlatformDecoder() {
        PlatformDecoder platformDecoder;
        if (this.mPlatformDecoder == null) {
            ImagePipelineConfig imagePipelineConfig = this.mConfig;
            PoolFactory poolFactory = imagePipelineConfig.mPoolFactory;
            Objects.requireNonNull(imagePipelineConfig.mImagePipelineExperiments);
            if (Build.VERSION.SDK_INT >= 26) {
                int flexByteArrayPoolMaxNumThreads = poolFactory.getFlexByteArrayPoolMaxNumThreads();
                platformDecoder = new OreoDecoder(poolFactory.getBitmapPool(), flexByteArrayPoolMaxNumThreads, new Pools$SynchronizedPool(flexByteArrayPoolMaxNumThreads));
            } else {
                int flexByteArrayPoolMaxNumThreads2 = poolFactory.getFlexByteArrayPoolMaxNumThreads();
                platformDecoder = new ArtDecoder(poolFactory.getBitmapPool(), flexByteArrayPoolMaxNumThreads2, new Pools$SynchronizedPool(flexByteArrayPoolMaxNumThreads2));
            }
            this.mPlatformDecoder = platformDecoder;
        }
        return this.mPlatformDecoder;
    }

    public final BufferedDiskCache getSmallImageBufferedDiskCache() {
        if (this.mSmallImageBufferedDiskCache == null) {
            if (this.mSmallImageFileCache == null) {
                ImagePipelineConfig imagePipelineConfig = this.mConfig;
                this.mSmallImageFileCache = ((DiskStorageCacheFactory) imagePipelineConfig.mFileCacheFactory).get(imagePipelineConfig.mSmallImageDiskCacheConfig);
            }
            FileCache fileCache = this.mSmallImageFileCache;
            ImagePipelineConfig imagePipelineConfig2 = this.mConfig;
            PoolFactory poolFactory = imagePipelineConfig2.mPoolFactory;
            Objects.requireNonNull(imagePipelineConfig2);
            this.mSmallImageBufferedDiskCache = new BufferedDiskCache(fileCache, poolFactory.getPooledByteBufferFactory(0), this.mConfig.mPoolFactory.getPooledByteStreams(), this.mConfig.mExecutorSupplier.forLocalStorageRead(), this.mConfig.mExecutorSupplier.forLocalStorageWrite(), this.mConfig.mImageCacheStatsTracker);
        }
        return this.mSmallImageBufferedDiskCache;
    }
}
