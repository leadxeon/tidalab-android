package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.IOException;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class QualifiedResourceFetchProducer extends LocalFetchProducer {
    public final ContentResolver mContentResolver;

    public QualifiedResourceFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver) {
        super(executor, pooledByteBufferFactory);
        this.mContentResolver = contentResolver;
    }

    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    public EncodedImage getEncodedImage(ImageRequest imageRequest) throws IOException {
        return getByteBufferBackedEncodedImage(this.mContentResolver.openInputStream(imageRequest.mSourceUri), -1);
    }

    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    public String getProducerName() {
        return "QualifiedResourceFetchProducer";
    }
}
