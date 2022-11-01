package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Closeables;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public abstract class LocalFetchProducer implements Producer<EncodedImage> {
    public final Executor mExecutor;
    public final PooledByteBufferFactory mPooledByteBufferFactory;

    public LocalFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory) {
        this.mExecutor = executor;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
    }

    public EncodedImage getByteBufferBackedEncodedImage(InputStream inputStream, int i) throws IOException {
        CloseableReference closeableReference = null;
        try {
            if (i <= 0) {
                closeableReference = CloseableReference.of(this.mPooledByteBufferFactory.newByteBuffer(inputStream));
            } else {
                closeableReference = CloseableReference.of(this.mPooledByteBufferFactory.newByteBuffer(inputStream, i));
            }
            EncodedImage encodedImage = new EncodedImage(closeableReference);
            Closeables.closeQuietly(inputStream);
            if (closeableReference != null) {
                closeableReference.close();
            }
            return encodedImage;
        } catch (Throwable th) {
            Closeables.closeQuietly(inputStream);
            Class<CloseableReference> cls = CloseableReference.TAG;
            if (closeableReference != null) {
                closeableReference.close();
            }
            throw th;
        }
    }

    public abstract EncodedImage getEncodedImage(ImageRequest imageRequest) throws IOException;

    public abstract String getProducerName();

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        final RequestListener listener = producerContext.getListener();
        final String id = producerContext.getId();
        final ImageRequest imageRequest = producerContext.getImageRequest();
        final StatefulProducerRunnable<EncodedImage> statefulProducerRunnable = new StatefulProducerRunnable<EncodedImage>(consumer, listener, getProducerName(), id) { // from class: com.facebook.imagepipeline.producers.LocalFetchProducer.1
            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable
            public void disposeResult(EncodedImage encodedImage) {
                EncodedImage encodedImage2 = encodedImage;
                if (encodedImage2 != null) {
                    encodedImage2.close();
                }
            }

            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable
            public EncodedImage getResult() throws Exception {
                EncodedImage encodedImage = LocalFetchProducer.this.getEncodedImage(imageRequest);
                if (encodedImage == null) {
                    listener.onUltimateProducerReached(id, LocalFetchProducer.this.getProducerName(), false);
                    return null;
                }
                encodedImage.parseMetaData();
                listener.onUltimateProducerReached(id, LocalFetchProducer.this.getProducerName(), true);
                return encodedImage;
            }
        };
        producerContext.addCallbacks(new BaseProducerContextCallbacks(this) { // from class: com.facebook.imagepipeline.producers.LocalFetchProducer.2
            @Override // com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                statefulProducerRunnable.cancel();
            }
        });
        this.mExecutor.execute(statefulProducerRunnable);
    }
}
