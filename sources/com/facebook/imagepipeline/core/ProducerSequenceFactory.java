package com.facebook.imagepipeline.core;

import android.content.ContentResolver;
import android.net.Uri;
import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.media.MediaUtils;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.AddImageTransformMetaDataProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheGetProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheKeyMultiplexProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheProducer;
import com.facebook.imagepipeline.producers.BitmapPrepareProducer;
import com.facebook.imagepipeline.producers.BranchOnSeparateImagesProducer;
import com.facebook.imagepipeline.producers.DataFetchProducer;
import com.facebook.imagepipeline.producers.DecodeProducer;
import com.facebook.imagepipeline.producers.DiskCacheReadProducer;
import com.facebook.imagepipeline.producers.DiskCacheWriteProducer;
import com.facebook.imagepipeline.producers.EncodedCacheKeyMultiplexProducer;
import com.facebook.imagepipeline.producers.EncodedMemoryCacheProducer;
import com.facebook.imagepipeline.producers.LocalAssetFetchProducer;
import com.facebook.imagepipeline.producers.LocalContentUriFetchProducer;
import com.facebook.imagepipeline.producers.LocalContentUriThumbnailFetchProducer;
import com.facebook.imagepipeline.producers.LocalExifThumbnailProducer;
import com.facebook.imagepipeline.producers.LocalFileFetchProducer;
import com.facebook.imagepipeline.producers.LocalResourceFetchProducer;
import com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer;
import com.facebook.imagepipeline.producers.NetworkFetchProducer;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.producers.PartialDiskCacheProducer;
import com.facebook.imagepipeline.producers.PostprocessedBitmapMemoryCacheProducer;
import com.facebook.imagepipeline.producers.PostprocessorProducer;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.QualifiedResourceFetchProducer;
import com.facebook.imagepipeline.producers.SwallowResultProducer;
import com.facebook.imagepipeline.producers.ThreadHandoffProducer;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.producers.ThrottlingProducer;
import com.facebook.imagepipeline.producers.ThumbnailBranchProducer;
import com.facebook.imagepipeline.producers.ThumbnailProducer;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public class ProducerSequenceFactory {
    public Producer<EncodedImage> mBackgroundLocalFileFetchToEncodedMemorySequence;
    public Producer<EncodedImage> mBackgroundNetworkFetchToEncodedMemorySequence;
    public Producer<EncodedImage> mCommonNetworkFetchToEncodedMemorySequence;
    public final ContentResolver mContentResolver;
    public Producer<CloseableReference<CloseableImage>> mDataFetchSequence;
    public final boolean mDiskCacheEnabled;
    public final boolean mDownsampleEnabled;
    public final ImageTranscoderFactory mImageTranscoderFactory;
    public Producer<CloseableReference<CloseableImage>> mLocalAssetFetchSequence;
    public Producer<CloseableReference<CloseableImage>> mLocalContentUriFetchSequence;
    public Producer<Void> mLocalFileFetchToEncodedMemoryPrefetchSequence;
    public Producer<CloseableReference<CloseableImage>> mLocalImageFileFetchSequence;
    public Producer<CloseableReference<CloseableImage>> mLocalResourceFetchSequence;
    public Producer<CloseableReference<CloseableImage>> mLocalVideoFileFetchSequence;
    public Producer<CloseableReference<CloseableImage>> mNetworkFetchSequence;
    public Producer<Void> mNetworkFetchToEncodedMemoryPrefetchSequence;
    public final NetworkFetcher mNetworkFetcher;
    public final boolean mPartialImageCachingEnabled;
    public final ProducerFactory mProducerFactory;
    public Producer<CloseableReference<CloseableImage>> mQualifiedResourceFetchSequence;
    public final boolean mResizeAndRotateEnabledForNetwork;
    public final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;
    public final boolean mUseBitmapPrepareToDraw;
    public Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> mPostprocessorSequences = new HashMap();
    public Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> mBitmapPrepareSequences = new HashMap();

    public ProducerSequenceFactory(ContentResolver contentResolver, ProducerFactory producerFactory, NetworkFetcher networkFetcher, boolean z, boolean z2, ThreadHandoffProducerQueue threadHandoffProducerQueue, boolean z3, boolean z4, boolean z5, boolean z6, ImageTranscoderFactory imageTranscoderFactory) {
        this.mContentResolver = contentResolver;
        this.mProducerFactory = producerFactory;
        this.mNetworkFetcher = networkFetcher;
        this.mResizeAndRotateEnabledForNetwork = z;
        new HashMap();
        this.mThreadHandoffProducerQueue = threadHandoffProducerQueue;
        this.mDownsampleEnabled = z3;
        this.mUseBitmapPrepareToDraw = z4;
        this.mPartialImageCachingEnabled = z5;
        this.mDiskCacheEnabled = z6;
        this.mImageTranscoderFactory = imageTranscoderFactory;
    }

    public static String getShortenedUriString(Uri uri) {
        String valueOf = String.valueOf(uri);
        if (valueOf.length() <= 30) {
            return valueOf;
        }
        return valueOf.substring(0, 30) + "...";
    }

    public final synchronized Producer<EncodedImage> getBackgroundLocalFileFetchToEncodeMemorySequence() {
        FrescoSystrace.isTracing();
        if (this.mBackgroundLocalFileFetchToEncodedMemorySequence == null) {
            FrescoSystrace.isTracing();
            ProducerFactory producerFactory = this.mProducerFactory;
            Producer<EncodedImage> newEncodedCacheMultiplexToTranscodeSequence = newEncodedCacheMultiplexToTranscodeSequence(new LocalFileFetchProducer(producerFactory.mExecutorSupplier.forLocalStorageRead(), producerFactory.mPooledByteBufferFactory));
            ProducerFactory producerFactory2 = this.mProducerFactory;
            ThreadHandoffProducerQueue threadHandoffProducerQueue = this.mThreadHandoffProducerQueue;
            Objects.requireNonNull(producerFactory2);
            this.mBackgroundLocalFileFetchToEncodedMemorySequence = new ThreadHandoffProducer(newEncodedCacheMultiplexToTranscodeSequence, threadHandoffProducerQueue);
            FrescoSystrace.isTracing();
        }
        FrescoSystrace.isTracing();
        return this.mBackgroundLocalFileFetchToEncodedMemorySequence;
    }

    public final synchronized Producer<EncodedImage> getBackgroundNetworkFetchToEncodedMemorySequence() {
        FrescoSystrace.isTracing();
        if (this.mBackgroundNetworkFetchToEncodedMemorySequence == null) {
            FrescoSystrace.isTracing();
            ProducerFactory producerFactory = this.mProducerFactory;
            Producer<EncodedImage> commonNetworkFetchToEncodedMemorySequence = getCommonNetworkFetchToEncodedMemorySequence();
            ThreadHandoffProducerQueue threadHandoffProducerQueue = this.mThreadHandoffProducerQueue;
            Objects.requireNonNull(producerFactory);
            this.mBackgroundNetworkFetchToEncodedMemorySequence = new ThreadHandoffProducer(commonNetworkFetchToEncodedMemorySequence, threadHandoffProducerQueue);
            FrescoSystrace.isTracing();
        }
        FrescoSystrace.isTracing();
        return this.mBackgroundNetworkFetchToEncodedMemorySequence;
    }

    public final synchronized Producer<EncodedImage> getCommonNetworkFetchToEncodedMemorySequence() {
        FrescoSystrace.isTracing();
        if (this.mCommonNetworkFetchToEncodedMemorySequence == null) {
            FrescoSystrace.isTracing();
            ProducerFactory producerFactory = this.mProducerFactory;
            AddImageTransformMetaDataProducer addImageTransformMetaDataProducer = new AddImageTransformMetaDataProducer(newEncodedCacheMultiplexToTranscodeSequence(new NetworkFetchProducer(producerFactory.mPooledByteBufferFactory, producerFactory.mByteArrayPool, this.mNetworkFetcher)));
            this.mCommonNetworkFetchToEncodedMemorySequence = addImageTransformMetaDataProducer;
            this.mCommonNetworkFetchToEncodedMemorySequence = this.mProducerFactory.newResizeAndRotateProducer(addImageTransformMetaDataProducer, this.mResizeAndRotateEnabledForNetwork && !this.mDownsampleEnabled, this.mImageTranscoderFactory);
            FrescoSystrace.isTracing();
        }
        FrescoSystrace.isTracing();
        return this.mCommonNetworkFetchToEncodedMemorySequence;
    }

    public final synchronized Producer<CloseableReference<CloseableImage>> getDataFetchSequence() {
        if (this.mDataFetchSequence == null) {
            DataFetchProducer dataFetchProducer = new DataFetchProducer(this.mProducerFactory.mPooledByteBufferFactory);
            byte[] bArr = WebpSupportStatus.WEBP_RIFF_BYTES;
            this.mDataFetchSequence = newBitmapCacheGetToDecodeSequence(this.mProducerFactory.newResizeAndRotateProducer(new AddImageTransformMetaDataProducer(dataFetchProducer), true, this.mImageTranscoderFactory));
        }
        return this.mDataFetchSequence;
    }

    public Producer<CloseableReference<CloseableImage>> getDecodedImageProducerSequence(ImageRequest imageRequest) {
        Producer<CloseableReference<CloseableImage>> producer;
        FrescoSystrace.isTracing();
        try {
            FrescoSystrace.isTracing();
            Objects.requireNonNull(imageRequest);
            Uri uri = imageRequest.mSourceUri;
            R$dimen.checkNotNull(uri, "Uri is null.");
            int i = imageRequest.mSourceUriType;
            if (i != 0) {
                switch (i) {
                    case 2:
                        producer = getLocalVideoFileFetchSequence();
                        break;
                    case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                        synchronized (this) {
                            if (this.mLocalImageFileFetchSequence == null) {
                                ProducerFactory producerFactory = this.mProducerFactory;
                                this.mLocalImageFileFetchSequence = newBitmapCacheGetToLocalTransformSequence(new LocalFileFetchProducer(producerFactory.mExecutorSupplier.forLocalStorageRead(), producerFactory.mPooledByteBufferFactory));
                            }
                            producer = this.mLocalImageFileFetchSequence;
                        }
                        break;
                    case 4:
                        String type = this.mContentResolver.getType(uri);
                        Map<String, String> map = MediaUtils.ADDITIONAL_ALLOWED_MIME_TYPES;
                        if (!(type != null && type.startsWith("video/"))) {
                            producer = getLocalContentUriFetchSequence();
                            break;
                        } else {
                            producer = getLocalVideoFileFetchSequence();
                            break;
                        }
                        break;
                    case 5:
                        producer = getLocalAssetFetchSequence();
                        break;
                    case 6:
                        producer = getLocalResourceFetchSequence();
                        break;
                    case 7:
                        producer = getDataFetchSequence();
                        break;
                    case 8:
                        producer = getQualifiedResourceFetchSequence();
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported uri scheme! Uri is: " + getShortenedUriString(uri));
                }
            } else {
                synchronized (this) {
                    FrescoSystrace.isTracing();
                    if (this.mNetworkFetchSequence == null) {
                        FrescoSystrace.isTracing();
                        this.mNetworkFetchSequence = newBitmapCacheGetToDecodeSequence(getCommonNetworkFetchToEncodedMemorySequence());
                        FrescoSystrace.isTracing();
                    }
                    FrescoSystrace.isTracing();
                    producer = this.mNetworkFetchSequence;
                }
            }
            if (imageRequest.mPostprocessor != null) {
                synchronized (this) {
                    if (!this.mPostprocessorSequences.containsKey(producer)) {
                        ProducerFactory producerFactory2 = this.mProducerFactory;
                        PostprocessorProducer postprocessorProducer = new PostprocessorProducer(producer, producerFactory2.mPlatformBitmapFactory, producerFactory2.mExecutorSupplier.forBackgroundTasks());
                        ProducerFactory producerFactory3 = this.mProducerFactory;
                        this.mPostprocessorSequences.put(producer, new PostprocessedBitmapMemoryCacheProducer(producerFactory3.mBitmapMemoryCache, producerFactory3.mCacheKeyFactory, postprocessorProducer));
                    }
                    producer = this.mPostprocessorSequences.get(producer);
                }
            }
            if (this.mUseBitmapPrepareToDraw) {
                synchronized (this) {
                    Producer<CloseableReference<CloseableImage>> producer2 = this.mBitmapPrepareSequences.get(producer);
                    if (producer2 == null) {
                        ProducerFactory producerFactory4 = this.mProducerFactory;
                        BitmapPrepareProducer bitmapPrepareProducer = new BitmapPrepareProducer(producer, producerFactory4.mBitmapPrepareToDrawMinSizeBytes, producerFactory4.mBitmapPrepareToDrawMaxSizeBytes, producerFactory4.mBitmapPrepareToDrawForPrefetch);
                        this.mBitmapPrepareSequences.put(producer, bitmapPrepareProducer);
                        producer = bitmapPrepareProducer;
                    } else {
                        producer = producer2;
                    }
                }
            }
            return producer;
        } finally {
            FrescoSystrace.isTracing();
        }
    }

    public Producer<Void> getEncodedImagePrefetchProducerSequence(ImageRequest imageRequest) {
        Producer<Void> producer;
        Producer<Void> producer2;
        Objects.requireNonNull(imageRequest);
        R$dimen.checkArgument(imageRequest.mLowestPermittedRequestLevel.mValue <= 3);
        int i = imageRequest.mSourceUriType;
        if (i == 0) {
            synchronized (this) {
                FrescoSystrace.isTracing();
                if (this.mNetworkFetchToEncodedMemoryPrefetchSequence == null) {
                    FrescoSystrace.isTracing();
                    this.mNetworkFetchToEncodedMemoryPrefetchSequence = new SwallowResultProducer(getBackgroundNetworkFetchToEncodedMemorySequence());
                    FrescoSystrace.isTracing();
                }
                FrescoSystrace.isTracing();
                producer = this.mNetworkFetchToEncodedMemoryPrefetchSequence;
            }
            return producer;
        } else if (i == 2 || i == 3) {
            synchronized (this) {
                FrescoSystrace.isTracing();
                if (this.mLocalFileFetchToEncodedMemoryPrefetchSequence == null) {
                    FrescoSystrace.isTracing();
                    this.mLocalFileFetchToEncodedMemoryPrefetchSequence = new SwallowResultProducer(getBackgroundLocalFileFetchToEncodeMemorySequence());
                    FrescoSystrace.isTracing();
                }
                FrescoSystrace.isTracing();
                producer2 = this.mLocalFileFetchToEncodedMemoryPrefetchSequence;
            }
            return producer2;
        } else {
            Uri uri = imageRequest.mSourceUri;
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unsupported uri scheme for encoded image fetch! Uri is: ");
            outline13.append(getShortenedUriString(uri));
            throw new IllegalArgumentException(outline13.toString());
        }
    }

    public final synchronized Producer<CloseableReference<CloseableImage>> getLocalAssetFetchSequence() {
        if (this.mLocalAssetFetchSequence == null) {
            ProducerFactory producerFactory = this.mProducerFactory;
            this.mLocalAssetFetchSequence = newBitmapCacheGetToLocalTransformSequence(new LocalAssetFetchProducer(producerFactory.mExecutorSupplier.forLocalStorageRead(), producerFactory.mPooledByteBufferFactory, producerFactory.mAssetManager));
        }
        return this.mLocalAssetFetchSequence;
    }

    public final synchronized Producer<CloseableReference<CloseableImage>> getLocalContentUriFetchSequence() {
        if (this.mLocalContentUriFetchSequence == null) {
            ProducerFactory producerFactory = this.mProducerFactory;
            LocalContentUriFetchProducer localContentUriFetchProducer = new LocalContentUriFetchProducer(producerFactory.mExecutorSupplier.forLocalStorageRead(), producerFactory.mPooledByteBufferFactory, producerFactory.mContentResolver);
            ProducerFactory producerFactory2 = this.mProducerFactory;
            Objects.requireNonNull(producerFactory2);
            ProducerFactory producerFactory3 = this.mProducerFactory;
            this.mLocalContentUriFetchSequence = newBitmapCacheGetToLocalTransformSequence(localContentUriFetchProducer, new ThumbnailProducer[]{new LocalContentUriThumbnailFetchProducer(producerFactory2.mExecutorSupplier.forLocalStorageRead(), producerFactory2.mPooledByteBufferFactory, producerFactory2.mContentResolver), new LocalExifThumbnailProducer(producerFactory3.mExecutorSupplier.forLocalStorageRead(), producerFactory3.mPooledByteBufferFactory, producerFactory3.mContentResolver)});
        }
        return this.mLocalContentUriFetchSequence;
    }

    public final synchronized Producer<CloseableReference<CloseableImage>> getLocalResourceFetchSequence() {
        if (this.mLocalResourceFetchSequence == null) {
            ProducerFactory producerFactory = this.mProducerFactory;
            this.mLocalResourceFetchSequence = newBitmapCacheGetToLocalTransformSequence(new LocalResourceFetchProducer(producerFactory.mExecutorSupplier.forLocalStorageRead(), producerFactory.mPooledByteBufferFactory, producerFactory.mResources));
        }
        return this.mLocalResourceFetchSequence;
    }

    public final synchronized Producer<CloseableReference<CloseableImage>> getLocalVideoFileFetchSequence() {
        if (this.mLocalVideoFileFetchSequence == null) {
            ProducerFactory producerFactory = this.mProducerFactory;
            this.mLocalVideoFileFetchSequence = newBitmapCacheGetToBitmapCacheSequence(new LocalVideoThumbnailProducer(producerFactory.mExecutorSupplier.forLocalStorageRead(), producerFactory.mContentResolver));
        }
        return this.mLocalVideoFileFetchSequence;
    }

    public final synchronized Producer<CloseableReference<CloseableImage>> getQualifiedResourceFetchSequence() {
        if (this.mQualifiedResourceFetchSequence == null) {
            ProducerFactory producerFactory = this.mProducerFactory;
            this.mQualifiedResourceFetchSequence = newBitmapCacheGetToLocalTransformSequence(new QualifiedResourceFetchProducer(producerFactory.mExecutorSupplier.forLocalStorageRead(), producerFactory.mPooledByteBufferFactory, producerFactory.mContentResolver));
        }
        return this.mQualifiedResourceFetchSequence;
    }

    public final Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToBitmapCacheSequence(Producer<CloseableReference<CloseableImage>> producer) {
        ProducerFactory producerFactory = this.mProducerFactory;
        MemoryCache<CacheKey, CloseableImage> memoryCache = producerFactory.mBitmapMemoryCache;
        CacheKeyFactory cacheKeyFactory = producerFactory.mCacheKeyFactory;
        BitmapMemoryCacheKeyMultiplexProducer bitmapMemoryCacheKeyMultiplexProducer = new BitmapMemoryCacheKeyMultiplexProducer(cacheKeyFactory, new BitmapMemoryCacheProducer(memoryCache, cacheKeyFactory, producer));
        ProducerFactory producerFactory2 = this.mProducerFactory;
        ThreadHandoffProducerQueue threadHandoffProducerQueue = this.mThreadHandoffProducerQueue;
        Objects.requireNonNull(producerFactory2);
        ThreadHandoffProducer threadHandoffProducer = new ThreadHandoffProducer(bitmapMemoryCacheKeyMultiplexProducer, threadHandoffProducerQueue);
        ProducerFactory producerFactory3 = this.mProducerFactory;
        return new BitmapMemoryCacheGetProducer(producerFactory3.mBitmapMemoryCache, producerFactory3.mCacheKeyFactory, threadHandoffProducer);
    }

    public final Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToDecodeSequence(Producer<EncodedImage> producer) {
        FrescoSystrace.isTracing();
        ProducerFactory producerFactory = this.mProducerFactory;
        Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToBitmapCacheSequence = newBitmapCacheGetToBitmapCacheSequence(new DecodeProducer(producerFactory.mByteArrayPool, producerFactory.mExecutorSupplier.forDecode(), producerFactory.mImageDecoder, producerFactory.mProgressiveJpegConfig, producerFactory.mDownsampleEnabled, producerFactory.mResizeAndRotateEnabledForNetwork, producerFactory.mDecodeCancellationEnabled, producer, producerFactory.mMaxBitmapSize, producerFactory.mCloseableReferenceFactory));
        FrescoSystrace.isTracing();
        return newBitmapCacheGetToBitmapCacheSequence;
    }

    public final Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToLocalTransformSequence(Producer<EncodedImage> producer) {
        ProducerFactory producerFactory = this.mProducerFactory;
        return newBitmapCacheGetToLocalTransformSequence(producer, new ThumbnailProducer[]{new LocalExifThumbnailProducer(producerFactory.mExecutorSupplier.forLocalStorageRead(), producerFactory.mPooledByteBufferFactory, producerFactory.mContentResolver)});
    }

    public final Producer<EncodedImage> newEncodedCacheMultiplexToTranscodeSequence(Producer<EncodedImage> producer) {
        DiskCacheWriteProducer diskCacheWriteProducer;
        byte[] bArr = WebpSupportStatus.WEBP_RIFF_BYTES;
        if (this.mDiskCacheEnabled) {
            FrescoSystrace.isTracing();
            if (this.mPartialImageCachingEnabled) {
                ProducerFactory producerFactory = this.mProducerFactory;
                BufferedDiskCache bufferedDiskCache = producerFactory.mDefaultBufferedDiskCache;
                CacheKeyFactory cacheKeyFactory = producerFactory.mCacheKeyFactory;
                diskCacheWriteProducer = new DiskCacheWriteProducer(bufferedDiskCache, producerFactory.mSmallImageBufferedDiskCache, cacheKeyFactory, new PartialDiskCacheProducer(bufferedDiskCache, cacheKeyFactory, producerFactory.mPooledByteBufferFactory, producerFactory.mByteArrayPool, producer));
            } else {
                ProducerFactory producerFactory2 = this.mProducerFactory;
                diskCacheWriteProducer = new DiskCacheWriteProducer(producerFactory2.mDefaultBufferedDiskCache, producerFactory2.mSmallImageBufferedDiskCache, producerFactory2.mCacheKeyFactory, producer);
            }
            ProducerFactory producerFactory3 = this.mProducerFactory;
            DiskCacheReadProducer diskCacheReadProducer = new DiskCacheReadProducer(producerFactory3.mDefaultBufferedDiskCache, producerFactory3.mSmallImageBufferedDiskCache, producerFactory3.mCacheKeyFactory, diskCacheWriteProducer);
            FrescoSystrace.isTracing();
            producer = diskCacheReadProducer;
        }
        ProducerFactory producerFactory4 = this.mProducerFactory;
        MemoryCache<CacheKey, PooledByteBuffer> memoryCache = producerFactory4.mEncodedMemoryCache;
        CacheKeyFactory cacheKeyFactory2 = producerFactory4.mCacheKeyFactory;
        return new EncodedCacheKeyMultiplexProducer(cacheKeyFactory2, new EncodedMemoryCacheProducer(memoryCache, cacheKeyFactory2, producer));
    }

    public final Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToLocalTransformSequence(Producer<EncodedImage> producer, ThumbnailProducer<EncodedImage>[] thumbnailProducerArr) {
        ThrottlingProducer throttlingProducer = new ThrottlingProducer(5, this.mProducerFactory.mExecutorSupplier.forLightweightBackgroundTasks(), this.mProducerFactory.newResizeAndRotateProducer(new AddImageTransformMetaDataProducer(newEncodedCacheMultiplexToTranscodeSequence(producer)), true, this.mImageTranscoderFactory));
        Objects.requireNonNull(this.mProducerFactory);
        return newBitmapCacheGetToDecodeSequence(new BranchOnSeparateImagesProducer(this.mProducerFactory.newResizeAndRotateProducer(new ThumbnailBranchProducer(thumbnailProducerArr), true, this.mImageTranscoderFactory), throttlingProducer));
    }
}
