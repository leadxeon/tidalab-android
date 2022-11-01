package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.CloseableImage;
/* loaded from: classes.dex */
public class BitmapMemoryCacheGetProducer extends BitmapMemoryCacheProducer {
    public BitmapMemoryCacheGetProducer(MemoryCache<CacheKey, CloseableImage> memoryCache, CacheKeyFactory cacheKeyFactory, Producer<CloseableReference<CloseableImage>> producer) {
        super(memoryCache, cacheKeyFactory, producer);
    }

    @Override // com.facebook.imagepipeline.producers.BitmapMemoryCacheProducer
    public String getProducerName() {
        return "BitmapMemoryCacheGetProducer";
    }

    @Override // com.facebook.imagepipeline.producers.BitmapMemoryCacheProducer
    public Consumer<CloseableReference<CloseableImage>> wrapConsumer(Consumer<CloseableReference<CloseableImage>> consumer, CacheKey cacheKey, boolean z) {
        return consumer;
    }
}
