package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
/* loaded from: classes.dex */
public class DiskCacheWriteProducer implements Producer<EncodedImage> {
    public final CacheKeyFactory mCacheKeyFactory;
    public final BufferedDiskCache mDefaultBufferedDiskCache;
    public final Producer<EncodedImage> mInputProducer;
    public final BufferedDiskCache mSmallImageBufferedDiskCache;

    /* loaded from: classes.dex */
    public static class DiskCacheWriteConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        public final CacheKeyFactory mCacheKeyFactory;
        public final BufferedDiskCache mDefaultBufferedDiskCache;
        public final ProducerContext mProducerContext;
        public final BufferedDiskCache mSmallImageBufferedDiskCache;

        public DiskCacheWriteConsumer(Consumer consumer, ProducerContext producerContext, BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory, AnonymousClass1 r6) {
            super(consumer);
            this.mProducerContext = producerContext;
            this.mDefaultBufferedDiskCache = bufferedDiskCache;
            this.mSmallImageBufferedDiskCache = bufferedDiskCache2;
            this.mCacheKeyFactory = cacheKeyFactory;
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(Object obj, int i) {
            EncodedImage encodedImage = (EncodedImage) obj;
            if (!BaseConsumer.isNotLast(i) && encodedImage != null) {
                if (!((i & 10) != 0)) {
                    encodedImage.parseMetaDataIfNeeded();
                    if (encodedImage.mImageFormat != ImageFormat.UNKNOWN) {
                        ImageRequest imageRequest = this.mProducerContext.getImageRequest();
                        CacheKey encodedCacheKey = ((DefaultCacheKeyFactory) this.mCacheKeyFactory).getEncodedCacheKey(imageRequest, this.mProducerContext.getCallerContext());
                        if (imageRequest.mCacheChoice == ImageRequest.CacheChoice.SMALL) {
                            this.mSmallImageBufferedDiskCache.put(encodedCacheKey, encodedImage);
                        } else {
                            this.mDefaultBufferedDiskCache.put(encodedCacheKey, encodedImage);
                        }
                        this.mConsumer.onNewResult(encodedImage, i);
                        return;
                    }
                }
            }
            this.mConsumer.onNewResult(encodedImage, i);
        }
    }

    public DiskCacheWriteProducer(BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory, Producer<EncodedImage> producer) {
        this.mDefaultBufferedDiskCache = bufferedDiskCache;
        this.mSmallImageBufferedDiskCache = bufferedDiskCache2;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = producer;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        if (producerContext.getLowestPermittedRequestLevel().mValue >= 2) {
            consumer.onNewResult(null, 1);
            return;
        }
        if (producerContext.getImageRequest().mIsDiskCacheEnabled) {
            consumer = new DiskCacheWriteConsumer(consumer, producerContext, this.mDefaultBufferedDiskCache, this.mSmallImageBufferedDiskCache, this.mCacheKeyFactory, null);
        }
        this.mInputProducer.produceResults(consumer, producerContext);
    }
}
