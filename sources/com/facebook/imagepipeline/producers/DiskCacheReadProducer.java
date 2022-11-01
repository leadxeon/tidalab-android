package com.facebook.imagepipeline.producers;

import bolts.Continuation;
import bolts.Task;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public class DiskCacheReadProducer implements Producer<EncodedImage> {
    public final CacheKeyFactory mCacheKeyFactory;
    public final BufferedDiskCache mDefaultBufferedDiskCache;
    public final Producer<EncodedImage> mInputProducer;
    public final BufferedDiskCache mSmallImageBufferedDiskCache;

    public DiskCacheReadProducer(BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory, Producer<EncodedImage> producer) {
        this.mDefaultBufferedDiskCache = bufferedDiskCache;
        this.mSmallImageBufferedDiskCache = bufferedDiskCache2;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = producer;
    }

    public static Map<String, String> getExtraMap(RequestListener requestListener, String str, boolean z, int i) {
        if (!requestListener.requiresExtraMap(str)) {
            return null;
        }
        if (z) {
            return ImmutableMap.of("cached_value_found", String.valueOf(z), "encodedImageSize", String.valueOf(i));
        }
        return ImmutableMap.of("cached_value_found", String.valueOf(z));
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        ImageRequest imageRequest = producerContext.getImageRequest();
        boolean z = true;
        if (imageRequest.mIsDiskCacheEnabled) {
            producerContext.getListener().onProducerStart(producerContext.getId(), "DiskCacheProducer");
            CacheKey encodedCacheKey = ((DefaultCacheKeyFactory) this.mCacheKeyFactory).getEncodedCacheKey(imageRequest, producerContext.getCallerContext());
            if (imageRequest.mCacheChoice != ImageRequest.CacheChoice.SMALL) {
                z = false;
            }
            BufferedDiskCache bufferedDiskCache = z ? this.mSmallImageBufferedDiskCache : this.mDefaultBufferedDiskCache;
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            Task<EncodedImage> task = bufferedDiskCache.get(encodedCacheKey, atomicBoolean);
            final String id = producerContext.getId();
            final RequestListener listener = producerContext.getListener();
            task.continueWith(new Continuation<EncodedImage, Void>() { // from class: com.facebook.imagepipeline.producers.DiskCacheReadProducer.1
                @Override // bolts.Continuation
                public Void then(Task<EncodedImage> task2) throws Exception {
                    boolean z2;
                    EncodedImage encodedImage;
                    synchronized (task2.lock) {
                        z2 = task2.cancelled;
                    }
                    if (z2 || (task2.isFaulted() && (task2.getError() instanceof CancellationException))) {
                        listener.onProducerFinishWithCancellation(id, "DiskCacheProducer", null);
                        consumer.onCancellation();
                    } else if (task2.isFaulted()) {
                        listener.onProducerFinishWithFailure(id, "DiskCacheProducer", task2.getError(), null);
                        DiskCacheReadProducer.this.mInputProducer.produceResults(consumer, producerContext);
                    } else {
                        synchronized (task2.lock) {
                            encodedImage = task2.result;
                        }
                        EncodedImage encodedImage2 = encodedImage;
                        if (encodedImage2 != null) {
                            RequestListener requestListener = listener;
                            String str = id;
                            requestListener.onProducerFinishWithSuccess(str, "DiskCacheProducer", DiskCacheReadProducer.getExtraMap(requestListener, str, true, encodedImage2.getSize()));
                            listener.onUltimateProducerReached(id, "DiskCacheProducer", true);
                            consumer.onProgressUpdate(1.0f);
                            consumer.onNewResult(encodedImage2, 1);
                            encodedImage2.close();
                        } else {
                            RequestListener requestListener2 = listener;
                            String str2 = id;
                            requestListener2.onProducerFinishWithSuccess(str2, "DiskCacheProducer", DiskCacheReadProducer.getExtraMap(requestListener2, str2, false, 0));
                            DiskCacheReadProducer.this.mInputProducer.produceResults(consumer, producerContext);
                        }
                    }
                    return null;
                }
            });
            producerContext.addCallbacks(new BaseProducerContextCallbacks(this) { // from class: com.facebook.imagepipeline.producers.DiskCacheReadProducer.2
                @Override // com.facebook.imagepipeline.producers.ProducerContextCallbacks
                public void onCancellationRequested() {
                    atomicBoolean.set(true);
                }
            });
        } else if (producerContext.getLowestPermittedRequestLevel().mValue >= 2) {
            consumer.onNewResult(null, 1);
        } else {
            this.mInputProducer.produceResults(consumer, producerContext);
        }
    }
}
