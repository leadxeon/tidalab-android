package com.facebook.imagepipeline.core;

import android.net.Uri;
import androidx.recyclerview.R$dimen;
import com.facebook.cache.common.CacheKey;
import com.facebook.callercontext.CallerContextVerifier;
import com.facebook.common.internal.Predicate;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.datasource.CloseableProducerToDataSourceAdapter;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes.dex */
public class ImagePipeline {
    public static final CancellationException PREFETCH_EXCEPTION = new CancellationException("Prefetching is not enabled");
    public final MemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache;
    public final CacheKeyFactory mCacheKeyFactory;
    public final MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;
    public final Supplier<Boolean> mIsPrefetchEnabledSupplier;
    public final BufferedDiskCache mMainBufferedDiskCache;
    public final ProducerSequenceFactory mProducerSequenceFactory;
    public final RequestListener mRequestListener;
    public final BufferedDiskCache mSmallImageBufferedDiskCache;
    public final Supplier<Boolean> mSuppressBitmapPrefetchingSupplier;
    public AtomicLong mIdCounter = new AtomicLong();
    public final Supplier<Boolean> mLazyDataSource = null;
    public final CallerContextVerifier mCallerContextVerifier = null;

    /* renamed from: com.facebook.imagepipeline.core.ImagePipeline$4  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass4 implements Predicate<CacheKey> {
        public AnonymousClass4(ImagePipeline imagePipeline) {
        }

        @Override // com.facebook.common.internal.Predicate
        public /* bridge */ /* synthetic */ boolean apply(CacheKey cacheKey) {
            return true;
        }
    }

    /* renamed from: com.facebook.imagepipeline.core.ImagePipeline$7  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass7 implements Predicate<CacheKey> {
        public final /* synthetic */ Uri val$uri;

        public AnonymousClass7(ImagePipeline imagePipeline, Uri uri) {
            this.val$uri = uri;
        }

        @Override // com.facebook.common.internal.Predicate
        public boolean apply(CacheKey cacheKey) {
            return cacheKey.containsUri(this.val$uri);
        }
    }

    public ImagePipeline(ProducerSequenceFactory producerSequenceFactory, Set<RequestListener> set, Supplier<Boolean> supplier, MemoryCache<CacheKey, CloseableImage> memoryCache, MemoryCache<CacheKey, PooledByteBuffer> memoryCache2, BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory, ThreadHandoffProducerQueue threadHandoffProducerQueue, Supplier<Boolean> supplier2, Supplier<Boolean> supplier3, CallerContextVerifier callerContextVerifier) {
        this.mProducerSequenceFactory = producerSequenceFactory;
        this.mRequestListener = new ForwardingRequestListener(set);
        this.mIsPrefetchEnabledSupplier = supplier;
        this.mBitmapMemoryCache = memoryCache;
        this.mEncodedMemoryCache = memoryCache2;
        this.mMainBufferedDiskCache = bufferedDiskCache;
        this.mSmallImageBufferedDiskCache = bufferedDiskCache2;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mSuppressBitmapPrefetchingSupplier = supplier2;
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object obj, ImageRequest.RequestLevel requestLevel, RequestListener requestListener) {
        try {
            return submitFetchRequest(this.mProducerSequenceFactory.getDecodedImageProducerSequence(imageRequest), imageRequest, requestLevel, obj, requestListener);
        } catch (Exception e) {
            return R$dimen.immediateFailedDataSource(e);
        }
    }

    public RequestListener getRequestListenerForRequest(ImageRequest imageRequest, RequestListener requestListener) {
        if (requestListener == null) {
            RequestListener requestListener2 = imageRequest.mRequestListener;
            if (requestListener2 == null) {
                return this.mRequestListener;
            }
            return new ForwardingRequestListener(this.mRequestListener, requestListener2);
        }
        RequestListener requestListener3 = imageRequest.mRequestListener;
        if (requestListener3 == null) {
            return new ForwardingRequestListener(this.mRequestListener, requestListener);
        }
        return new ForwardingRequestListener(this.mRequestListener, requestListener, requestListener3);
    }

    public boolean isInDiskCacheSync(Uri uri) {
        return isInDiskCacheSync(uri, ImageRequest.CacheChoice.SMALL) || isInDiskCacheSync(uri, ImageRequest.CacheChoice.DEFAULT);
    }

    public final <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producer, ImageRequest imageRequest, ImageRequest.RequestLevel requestLevel, Object obj, RequestListener requestListener) {
        boolean z;
        FrescoSystrace.isTracing();
        RequestListener requestListenerForRequest = getRequestListenerForRequest(imageRequest, requestListener);
        CallerContextVerifier callerContextVerifier = this.mCallerContextVerifier;
        if (callerContextVerifier != null) {
            callerContextVerifier.verifyCallerContext(obj);
        }
        try {
            ImageRequest.RequestLevel requestLevel2 = imageRequest.mLowestPermittedRequestLevel;
            ImageRequest.RequestLevel requestLevel3 = requestLevel2.mValue > requestLevel.mValue ? requestLevel2 : requestLevel;
            String valueOf = String.valueOf(this.mIdCounter.getAndIncrement());
            if (!imageRequest.mProgressiveRenderingEnabled && UriUtil.isNetworkUri(imageRequest.mSourceUri)) {
                z = false;
                SettableProducerContext settableProducerContext = new SettableProducerContext(imageRequest, valueOf, requestListenerForRequest, obj, requestLevel3, false, z, imageRequest.mRequestPriority);
                FrescoSystrace.isTracing();
                CloseableProducerToDataSourceAdapter closeableProducerToDataSourceAdapter = new CloseableProducerToDataSourceAdapter(producer, settableProducerContext, requestListenerForRequest);
                FrescoSystrace.isTracing();
                return closeableProducerToDataSourceAdapter;
            }
            z = true;
            SettableProducerContext settableProducerContext2 = new SettableProducerContext(imageRequest, valueOf, requestListenerForRequest, obj, requestLevel3, false, z, imageRequest.mRequestPriority);
            FrescoSystrace.isTracing();
            CloseableProducerToDataSourceAdapter closeableProducerToDataSourceAdapter2 = new CloseableProducerToDataSourceAdapter(producer, settableProducerContext2, requestListenerForRequest);
            FrescoSystrace.isTracing();
            return closeableProducerToDataSourceAdapter2;
        } catch (Exception e) {
            return R$dimen.immediateFailedDataSource(e);
        } finally {
            FrescoSystrace.isTracing();
        }
    }

    public boolean isInDiskCacheSync(Uri uri, ImageRequest.CacheChoice cacheChoice) {
        ImageRequestBuilder newBuilderWithSource = ImageRequestBuilder.newBuilderWithSource(uri);
        newBuilderWithSource.mCacheChoice = cacheChoice;
        ImageRequest build = newBuilderWithSource.build();
        CacheKey encodedCacheKey = ((DefaultCacheKeyFactory) this.mCacheKeyFactory).getEncodedCacheKey(build, null);
        int ordinal = build.mCacheChoice.ordinal();
        if (ordinal == 0) {
            return this.mSmallImageBufferedDiskCache.diskCheckSync(encodedCacheKey);
        }
        if (ordinal != 1) {
            return false;
        }
        return this.mMainBufferedDiskCache.diskCheckSync(encodedCacheKey);
    }
}
