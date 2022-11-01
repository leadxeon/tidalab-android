package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Map;
/* loaded from: classes.dex */
public class EncodedMemoryCacheProducer implements Producer<EncodedImage> {
    public final CacheKeyFactory mCacheKeyFactory;
    public final Producer<EncodedImage> mInputProducer;
    public final MemoryCache<CacheKey, PooledByteBuffer> mMemoryCache;

    /* loaded from: classes.dex */
    public static class EncodedMemoryCacheConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        public final boolean mIsMemoryCacheEnabled;
        public final MemoryCache<CacheKey, PooledByteBuffer> mMemoryCache;
        public final CacheKey mRequestedCacheKey;

        public EncodedMemoryCacheConsumer(Consumer<EncodedImage> consumer, MemoryCache<CacheKey, PooledByteBuffer> memoryCache, CacheKey cacheKey, boolean z) {
            super(consumer);
            this.mMemoryCache = memoryCache;
            this.mRequestedCacheKey = cacheKey;
            this.mIsMemoryCacheEnabled = z;
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(Object obj, int i) {
            EncodedImage encodedImage = (EncodedImage) obj;
            try {
                FrescoSystrace.isTracing();
                if (!BaseConsumer.isNotLast(i) && encodedImage != null) {
                    if (!((i & 10) != 0)) {
                        encodedImage.parseMetaDataIfNeeded();
                        if (encodedImage.mImageFormat != ImageFormat.UNKNOWN) {
                            CloseableReference<PooledByteBuffer> byteBufferRef = encodedImage.getByteBufferRef();
                            if (byteBufferRef != null) {
                                CloseableReference<PooledByteBuffer> closeableReference = null;
                                if (this.mIsMemoryCacheEnabled) {
                                    closeableReference = this.mMemoryCache.cache(this.mRequestedCacheKey, byteBufferRef);
                                }
                                byteBufferRef.close();
                                if (closeableReference != null) {
                                    EncodedImage encodedImage2 = new EncodedImage(closeableReference);
                                    encodedImage2.copyMetaDataFrom(encodedImage);
                                    closeableReference.close();
                                    this.mConsumer.onProgressUpdate(1.0f);
                                    this.mConsumer.onNewResult(encodedImage2, i);
                                    encodedImage2.close();
                                }
                            }
                            this.mConsumer.onNewResult(encodedImage, i);
                        }
                    }
                }
                this.mConsumer.onNewResult(encodedImage, i);
            } finally {
                FrescoSystrace.isTracing();
            }
        }
    }

    public EncodedMemoryCacheProducer(MemoryCache<CacheKey, PooledByteBuffer> memoryCache, CacheKeyFactory cacheKeyFactory, Producer<EncodedImage> producer) {
        this.mMemoryCache = memoryCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = producer;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        try {
            FrescoSystrace.isTracing();
            String id = producerContext.getId();
            RequestListener listener = producerContext.getListener();
            listener.onProducerStart(id, "EncodedMemoryCacheProducer");
            CacheKey encodedCacheKey = ((DefaultCacheKeyFactory) this.mCacheKeyFactory).getEncodedCacheKey(producerContext.getImageRequest(), producerContext.getCallerContext());
            CloseableReference<PooledByteBuffer> closeableReference = this.mMemoryCache.get(encodedCacheKey);
            Map<String, String> map = null;
            if (closeableReference != null) {
                EncodedImage encodedImage = new EncodedImage(closeableReference);
                if (listener.requiresExtraMap(id)) {
                    map = ImmutableMap.of("cached_value_found", "true");
                }
                listener.onProducerFinishWithSuccess(id, "EncodedMemoryCacheProducer", map);
                listener.onUltimateProducerReached(id, "EncodedMemoryCacheProducer", true);
                consumer.onProgressUpdate(1.0f);
                consumer.onNewResult(encodedImage, 1);
                encodedImage.close();
                closeableReference.close();
            } else if (producerContext.getLowestPermittedRequestLevel().mValue >= 3) {
                listener.onProducerFinishWithSuccess(id, "EncodedMemoryCacheProducer", listener.requiresExtraMap(id) ? ImmutableMap.of("cached_value_found", "false") : null);
                listener.onUltimateProducerReached(id, "EncodedMemoryCacheProducer", false);
                consumer.onNewResult(null, 1);
                Class<CloseableReference> cls = CloseableReference.TAG;
                if (closeableReference != null) {
                    closeableReference.close();
                }
            } else {
                EncodedMemoryCacheConsumer encodedMemoryCacheConsumer = new EncodedMemoryCacheConsumer(consumer, this.mMemoryCache, encodedCacheKey, producerContext.getImageRequest().mIsMemoryCacheEnabled);
                if (listener.requiresExtraMap(id)) {
                    map = ImmutableMap.of("cached_value_found", "false");
                }
                listener.onProducerFinishWithSuccess(id, "EncodedMemoryCacheProducer", map);
                this.mInputProducer.produceResults(encodedMemoryCacheConsumer, producerContext);
                Class<CloseableReference> cls2 = CloseableReference.TAG;
                if (closeableReference != null) {
                    closeableReference.close();
                }
            }
        } finally {
            FrescoSystrace.isTracing();
        }
    }
}
