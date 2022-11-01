package com.facebook.imagepipeline.producers;

import android.content.res.Resources;
import com.facebook.common.memory.PooledByteBufferFactory;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class LocalResourceFetchProducer extends LocalFetchProducer {
    public final Resources mResources;

    public LocalResourceFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, Resources resources) {
        super(executor, pooledByteBufferFactory);
        this.mResources = resources;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0038, code lost:
        if (r1 == null) goto L_0x003d;
     */
    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.facebook.imagepipeline.image.EncodedImage getEncodedImage(com.facebook.imagepipeline.request.ImageRequest r5) throws java.io.IOException {
        /*
            r4 = this;
            android.content.res.Resources r0 = r4.mResources
            android.net.Uri r1 = r5.mSourceUri
            java.lang.String r1 = r1.getPath()
            r2 = 1
            java.lang.String r1 = r1.substring(r2)
            int r1 = java.lang.Integer.parseInt(r1)
            java.io.InputStream r0 = r0.openRawResource(r1)
            r1 = 0
            android.content.res.Resources r3 = r4.mResources     // Catch: all -> 0x0030, NotFoundException -> 0x0037
            android.net.Uri r5 = r5.mSourceUri     // Catch: all -> 0x0030, NotFoundException -> 0x0037
            java.lang.String r5 = r5.getPath()     // Catch: all -> 0x0030, NotFoundException -> 0x0037
            java.lang.String r5 = r5.substring(r2)     // Catch: all -> 0x0030, NotFoundException -> 0x0037
            int r5 = java.lang.Integer.parseInt(r5)     // Catch: all -> 0x0030, NotFoundException -> 0x0037
            android.content.res.AssetFileDescriptor r1 = r3.openRawResourceFd(r5)     // Catch: all -> 0x0030, NotFoundException -> 0x0037
            long r2 = r1.getLength()     // Catch: all -> 0x0030, NotFoundException -> 0x0037
            int r5 = (int) r2
            goto L_0x003a
        L_0x0030:
            r5 = move-exception
            if (r1 == 0) goto L_0x0036
            r1.close()     // Catch: IOException -> 0x0036
        L_0x0036:
            throw r5
        L_0x0037:
            r5 = -1
            if (r1 == 0) goto L_0x003d
        L_0x003a:
            r1.close()     // Catch: IOException -> 0x003d
        L_0x003d:
            com.facebook.imagepipeline.image.EncodedImage r5 = r4.getByteBufferBackedEncodedImage(r0, r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.LocalResourceFetchProducer.getEncodedImage(com.facebook.imagepipeline.request.ImageRequest):com.facebook.imagepipeline.image.EncodedImage");
    }

    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    public String getProducerName() {
        return "LocalResourceFetchProducer";
    }
}
