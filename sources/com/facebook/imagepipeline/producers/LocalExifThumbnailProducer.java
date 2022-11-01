package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import androidx.recyclerview.R$dimen;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class LocalExifThumbnailProducer implements ThumbnailProducer<EncodedImage> {
    public final ContentResolver mContentResolver;
    public final Executor mExecutor;
    public final PooledByteBufferFactory mPooledByteBufferFactory;

    public LocalExifThumbnailProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver) {
        this.mExecutor = executor;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mContentResolver = contentResolver;
    }

    @Override // com.facebook.imagepipeline.producers.ThumbnailProducer
    public boolean canProvideImageForSize(ResizeOptions resizeOptions) {
        return R$dimen.isImageBigEnough(512, 512, resizeOptions);
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        RequestListener listener = producerContext.getListener();
        String id = producerContext.getId();
        final ImageRequest imageRequest = producerContext.getImageRequest();
        final StatefulProducerRunnable<EncodedImage> statefulProducerRunnable = new StatefulProducerRunnable<EncodedImage>(consumer, listener, "LocalExifThumbnailProducer", id) { // from class: com.facebook.imagepipeline.producers.LocalExifThumbnailProducer.1
            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable
            public void disposeResult(EncodedImage encodedImage) {
                EncodedImage encodedImage2 = encodedImage;
                if (encodedImage2 != null) {
                    encodedImage2.close();
                }
            }

            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable
            public Map getExtraMapOnSuccess(EncodedImage encodedImage) {
                return ImmutableMap.of("createdThumbnail", Boolean.toString(encodedImage != null));
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0033  */
            /* JADX WARN: Removed duplicated region for block: B:41:0x009d  */
            /* JADX WARN: Removed duplicated region for block: B:51:0x00e0  */
            /* JADX WARN: Removed duplicated region for block: B:52:0x00e9  */
            /* JADX WARN: Removed duplicated region for block: B:54:0x00ec  */
            /* JADX WARN: Removed duplicated region for block: B:58:0x00ff A[DONT_GENERATE] */
            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.facebook.imagepipeline.image.EncodedImage getResult() throws java.lang.Exception {
                /*
                    Method dump skipped, instructions count: 284
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.LocalExifThumbnailProducer.AnonymousClass1.getResult():java.lang.Object");
            }
        };
        producerContext.addCallbacks(new BaseProducerContextCallbacks(this) { // from class: com.facebook.imagepipeline.producers.LocalExifThumbnailProducer.2
            @Override // com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                statefulProducerRunnable.cancel();
            }
        });
        this.mExecutor.execute(statefulProducerRunnable);
    }
}
