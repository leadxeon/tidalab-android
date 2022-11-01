package com.facebook.imagepipeline.producers;

import androidx.recyclerview.R$dimen;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
/* loaded from: classes.dex */
public class BranchOnSeparateImagesProducer implements Producer<EncodedImage> {
    public final Producer<EncodedImage> mInputProducer1;
    public final Producer<EncodedImage> mInputProducer2;

    /* loaded from: classes.dex */
    public class OnFirstImageConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        public ProducerContext mProducerContext;

        public OnFirstImageConsumer(Consumer consumer, ProducerContext producerContext, AnonymousClass1 r4) {
            super(consumer);
            this.mProducerContext = producerContext;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        public void onFailureImpl(Throwable th) {
            BranchOnSeparateImagesProducer.this.mInputProducer2.produceResults(this.mConsumer, this.mProducerContext);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(Object obj, int i) {
            EncodedImage encodedImage = (EncodedImage) obj;
            ImageRequest imageRequest = this.mProducerContext.getImageRequest();
            boolean isLast = BaseConsumer.isLast(i);
            boolean isImageBigEnough = R$dimen.isImageBigEnough(encodedImage, imageRequest.mResizeOptions);
            if (encodedImage != null && (isImageBigEnough || imageRequest.mLocalThumbnailPreviewsEnabled)) {
                if (!isLast || !isImageBigEnough) {
                    this.mConsumer.onNewResult(encodedImage, i & (-2));
                } else {
                    this.mConsumer.onNewResult(encodedImage, i);
                }
            }
            if (isLast && !isImageBigEnough) {
                if (encodedImage != null) {
                    encodedImage.close();
                }
                BranchOnSeparateImagesProducer.this.mInputProducer2.produceResults(this.mConsumer, this.mProducerContext);
            }
        }
    }

    public BranchOnSeparateImagesProducer(Producer<EncodedImage> producer, Producer<EncodedImage> producer2) {
        this.mInputProducer1 = producer;
        this.mInputProducer2 = producer2;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        this.mInputProducer1.produceResults(new OnFirstImageConsumer(consumer, producerContext, null), producerContext);
    }
}
