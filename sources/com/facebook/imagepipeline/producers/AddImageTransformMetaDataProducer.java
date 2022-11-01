package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.EncodedImage;
/* loaded from: classes.dex */
public class AddImageTransformMetaDataProducer implements Producer<EncodedImage> {
    public final Producer<EncodedImage> mInputProducer;

    /* loaded from: classes.dex */
    public static class AddImageTransformMetaDataConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        public AddImageTransformMetaDataConsumer(Consumer consumer, AnonymousClass1 r2) {
            super(consumer);
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(Object obj, int i) {
            EncodedImage encodedImage = (EncodedImage) obj;
            if (encodedImage == null) {
                this.mConsumer.onNewResult(null, i);
                return;
            }
            if (!EncodedImage.isMetaDataAvailable(encodedImage)) {
                encodedImage.parseMetaData();
            }
            this.mConsumer.onNewResult(encodedImage, i);
        }
    }

    public AddImageTransformMetaDataProducer(Producer<EncodedImage> producer) {
        this.mInputProducer = producer;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        this.mInputProducer.produceResults(new AddImageTransformMetaDataConsumer(consumer, null), producerContext);
    }
}
