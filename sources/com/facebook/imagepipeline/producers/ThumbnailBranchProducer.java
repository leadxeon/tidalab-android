package com.facebook.imagepipeline.producers;

import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import java.util.Objects;
/* loaded from: classes.dex */
public class ThumbnailBranchProducer implements Producer<EncodedImage> {
    public final ThumbnailProducer<EncodedImage>[] mThumbnailProducers;

    /* loaded from: classes.dex */
    public class ThumbnailConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        public final ProducerContext mProducerContext;
        public final int mProducerIndex;
        public final ResizeOptions mResizeOptions;

        public ThumbnailConsumer(Consumer<EncodedImage> consumer, ProducerContext producerContext, int i) {
            super(consumer);
            this.mProducerContext = producerContext;
            this.mProducerIndex = i;
            this.mResizeOptions = producerContext.getImageRequest().mResizeOptions;
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        public void onFailureImpl(Throwable th) {
            if (!ThumbnailBranchProducer.this.produceResultsFromThumbnailProducer(this.mProducerIndex + 1, this.mConsumer, this.mProducerContext)) {
                this.mConsumer.onFailure(th);
            }
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(Object obj, int i) {
            EncodedImage encodedImage = (EncodedImage) obj;
            if (encodedImage != null && (BaseConsumer.isNotLast(i) || R$dimen.isImageBigEnough(encodedImage, this.mResizeOptions))) {
                this.mConsumer.onNewResult(encodedImage, i);
            } else if (BaseConsumer.isLast(i)) {
                if (encodedImage != null) {
                    encodedImage.close();
                }
                if (!ThumbnailBranchProducer.this.produceResultsFromThumbnailProducer(this.mProducerIndex + 1, this.mConsumer, this.mProducerContext)) {
                    this.mConsumer.onNewResult(null, 1);
                }
            }
        }
    }

    public ThumbnailBranchProducer(ThumbnailProducer<EncodedImage>... thumbnailProducerArr) {
        Objects.requireNonNull(thumbnailProducerArr);
        this.mThumbnailProducers = thumbnailProducerArr;
        int length = thumbnailProducerArr.length;
        if (length > 0) {
            return;
        }
        if (length >= 0) {
            throw new IndexOutOfBoundsException(R$dimen.format("%s (%s) must be less than size (%s)", "index", 0, Integer.valueOf(length)));
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport.outline3("negative size: ", length));
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        if (producerContext.getImageRequest().mResizeOptions == null) {
            consumer.onNewResult(null, 1);
        } else if (!produceResultsFromThumbnailProducer(0, consumer, producerContext)) {
            consumer.onNewResult(null, 1);
        }
    }

    public final boolean produceResultsFromThumbnailProducer(int i, Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        ResizeOptions resizeOptions = producerContext.getImageRequest().mResizeOptions;
        while (true) {
            ThumbnailProducer<EncodedImage>[] thumbnailProducerArr = this.mThumbnailProducers;
            if (i >= thumbnailProducerArr.length) {
                i = -1;
                break;
            } else if (thumbnailProducerArr[i].canProvideImageForSize(resizeOptions)) {
                break;
            } else {
                i++;
            }
        }
        if (i == -1) {
            return false;
        }
        this.mThumbnailProducers[i].produceResults(new ThumbnailConsumer(consumer, producerContext, i), producerContext);
        return true;
    }
}
