package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public class BitmapMemoryCacheProducer implements Producer<CloseableReference<CloseableImage>> {
    public final CacheKeyFactory mCacheKeyFactory;
    public final Producer<CloseableReference<CloseableImage>> mInputProducer;
    public final MemoryCache<CacheKey, CloseableImage> mMemoryCache;

    public BitmapMemoryCacheProducer(MemoryCache<CacheKey, CloseableImage> memoryCache, CacheKeyFactory cacheKeyFactory, Producer<CloseableReference<CloseableImage>> producer) {
        this.mMemoryCache = memoryCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = producer;
    }

    public String getProducerName() {
        return "BitmapMemoryCacheProducer";
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        try {
            FrescoSystrace.isTracing();
            RequestListener listener = producerContext.getListener();
            String id = producerContext.getId();
            listener.onProducerStart(id, getProducerName());
            CacheKey bitmapCacheKey = ((DefaultCacheKeyFactory) this.mCacheKeyFactory).getBitmapCacheKey(producerContext.getImageRequest(), producerContext.getCallerContext());
            CloseableReference<CloseableImage> closeableReference = this.mMemoryCache.get(bitmapCacheKey);
            Map<String, String> map = null;
            if (closeableReference != null) {
                boolean z = ((ImmutableQualityInfo) closeableReference.get().getQualityInfo()).mIsOfFullQuality;
                if (z) {
                    listener.onProducerFinishWithSuccess(id, getProducerName(), listener.requiresExtraMap(id) ? ImmutableMap.of("cached_value_found", "true") : null);
                    listener.onUltimateProducerReached(id, getProducerName(), true);
                    consumer.onProgressUpdate(1.0f);
                }
                consumer.onNewResult(closeableReference, z ? 1 : 0);
                closeableReference.close();
                if (z) {
                    return;
                }
            }
            if (producerContext.getLowestPermittedRequestLevel().mValue >= 4) {
                listener.onProducerFinishWithSuccess(id, getProducerName(), listener.requiresExtraMap(id) ? ImmutableMap.of("cached_value_found", "false") : null);
                listener.onUltimateProducerReached(id, getProducerName(), false);
                consumer.onNewResult(null, 1);
                return;
            }
            Consumer<CloseableReference<CloseableImage>> wrapConsumer = wrapConsumer(consumer, bitmapCacheKey, producerContext.getImageRequest().mIsMemoryCacheEnabled);
            String producerName = getProducerName();
            if (listener.requiresExtraMap(id)) {
                map = ImmutableMap.of("cached_value_found", "false");
            }
            listener.onProducerFinishWithSuccess(id, producerName, map);
            FrescoSystrace.isTracing();
            this.mInputProducer.produceResults(wrapConsumer, producerContext);
            FrescoSystrace.isTracing();
        } finally {
            FrescoSystrace.isTracing();
        }
    }

    public Consumer<CloseableReference<CloseableImage>> wrapConsumer(Consumer<CloseableReference<CloseableImage>> consumer, final CacheKey cacheKey, final boolean z) {
        return new DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>>(consumer) { // from class: com.facebook.imagepipeline.producers.BitmapMemoryCacheProducer.1
            @Override // com.facebook.imagepipeline.producers.BaseConsumer
            public void onNewResultImpl(Object obj, int i) {
                CloseableReference<CloseableImage> closeableReference;
                CloseableReference<CloseableImage> closeableReference2 = (CloseableReference) obj;
                try {
                    FrescoSystrace.isTracing();
                    boolean isLast = BaseConsumer.isLast(i);
                    CloseableReference<CloseableImage> closeableReference3 = null;
                    if (closeableReference2 != null) {
                        Objects.requireNonNull(closeableReference2.get());
                        if (BaseConsumer.statusHasFlag(i, 8)) {
                            this.mConsumer.onNewResult(closeableReference2, i);
                        } else {
                            if (!isLast && (closeableReference = BitmapMemoryCacheProducer.this.mMemoryCache.get(cacheKey)) != null) {
                                QualityInfo qualityInfo = closeableReference2.get().getQualityInfo();
                                QualityInfo qualityInfo2 = closeableReference.get().getQualityInfo();
                                if (((ImmutableQualityInfo) qualityInfo2).mIsOfFullQuality || ((ImmutableQualityInfo) qualityInfo2).mQuality >= ((ImmutableQualityInfo) qualityInfo).mQuality) {
                                    this.mConsumer.onNewResult(closeableReference, i);
                                    closeableReference.close();
                                } else {
                                    closeableReference.close();
                                }
                            }
                            if (z) {
                                closeableReference3 = BitmapMemoryCacheProducer.this.mMemoryCache.cache(cacheKey, closeableReference2);
                            }
                            if (isLast) {
                                this.mConsumer.onProgressUpdate(1.0f);
                            }
                            Consumer<O> consumer2 = this.mConsumer;
                            if (closeableReference3 != null) {
                                closeableReference2 = closeableReference3;
                            }
                            consumer2.onNewResult(closeableReference2, i);
                            if (closeableReference3 != null) {
                                closeableReference3.close();
                            }
                        }
                    } else if (isLast) {
                        this.mConsumer.onNewResult(null, i);
                    }
                } finally {
                    FrescoSystrace.isTracing();
                }
            }
        };
    }
}
