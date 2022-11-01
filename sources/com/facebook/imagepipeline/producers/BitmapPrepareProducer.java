package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import androidx.recyclerview.R$dimen;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import java.util.Objects;
/* loaded from: classes.dex */
public class BitmapPrepareProducer implements Producer<CloseableReference<CloseableImage>> {
    public final Producer<CloseableReference<CloseableImage>> mInputProducer;
    public final int mMaxBitmapSizeBytes;
    public final int mMinBitmapSizeBytes;
    public final boolean mPreparePrefetch;

    /* loaded from: classes.dex */
    public static class BitmapPrepareConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
        public final int mMaxBitmapSizeBytes;
        public final int mMinBitmapSizeBytes;

        public BitmapPrepareConsumer(Consumer<CloseableReference<CloseableImage>> consumer, int i, int i2) {
            super(consumer);
            this.mMinBitmapSizeBytes = i;
            this.mMaxBitmapSizeBytes = i2;
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(Object obj, int i) {
            CloseableImage closeableImage;
            Bitmap bitmap;
            CloseableReference closeableReference = (CloseableReference) obj;
            if (closeableReference != null && closeableReference.isValid() && (closeableImage = (CloseableImage) closeableReference.get()) != null && !closeableImage.isClosed() && (closeableImage instanceof CloseableStaticBitmap) && (bitmap = ((CloseableStaticBitmap) closeableImage).mBitmap) != null) {
                int height = bitmap.getHeight() * bitmap.getRowBytes();
                if (height >= this.mMinBitmapSizeBytes && height <= this.mMaxBitmapSizeBytes) {
                    bitmap.prepareToDraw();
                }
            }
            this.mConsumer.onNewResult(closeableReference, i);
        }
    }

    public BitmapPrepareProducer(Producer<CloseableReference<CloseableImage>> producer, int i, int i2, boolean z) {
        R$dimen.checkArgument(i <= i2);
        Objects.requireNonNull(producer);
        this.mInputProducer = producer;
        this.mMinBitmapSizeBytes = i;
        this.mMaxBitmapSizeBytes = i2;
        this.mPreparePrefetch = z;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        if (!producerContext.isPrefetch() || this.mPreparePrefetch) {
            this.mInputProducer.produceResults(new BitmapPrepareConsumer(consumer, this.mMinBitmapSizeBytes, this.mMaxBitmapSizeBytes), producerContext);
        } else {
            this.mInputProducer.produceResults(consumer, producerContext);
        }
    }
}
