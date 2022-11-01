package com.facebook.imagepipeline.producers;

import android.content.res.AssetManager;
import com.facebook.common.memory.PooledByteBufferFactory;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class LocalAssetFetchProducer extends LocalFetchProducer {
    public final AssetManager mAssetManager;

    public LocalAssetFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, AssetManager assetManager) {
        super(executor, pooledByteBufferFactory);
        this.mAssetManager = assetManager;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0031, code lost:
        if (r1 == null) goto L_0x0036;
     */
    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.facebook.imagepipeline.image.EncodedImage getEncodedImage(com.facebook.imagepipeline.request.ImageRequest r5) throws java.io.IOException {
        /*
            r4 = this;
            android.content.res.AssetManager r0 = r4.mAssetManager
            android.net.Uri r1 = r5.mSourceUri
            java.lang.String r1 = r1.getPath()
            r2 = 1
            java.lang.String r1 = r1.substring(r2)
            r3 = 2
            java.io.InputStream r0 = r0.open(r1, r3)
            r1 = 0
            android.content.res.AssetManager r3 = r4.mAssetManager     // Catch: all -> 0x0029, IOException -> 0x0030
            android.net.Uri r5 = r5.mSourceUri     // Catch: all -> 0x0029, IOException -> 0x0030
            java.lang.String r5 = r5.getPath()     // Catch: all -> 0x0029, IOException -> 0x0030
            java.lang.String r5 = r5.substring(r2)     // Catch: all -> 0x0029, IOException -> 0x0030
            android.content.res.AssetFileDescriptor r1 = r3.openFd(r5)     // Catch: all -> 0x0029, IOException -> 0x0030
            long r2 = r1.getLength()     // Catch: all -> 0x0029, IOException -> 0x0030
            int r5 = (int) r2
            goto L_0x0033
        L_0x0029:
            r5 = move-exception
            if (r1 == 0) goto L_0x002f
            r1.close()     // Catch: IOException -> 0x002f
        L_0x002f:
            throw r5
        L_0x0030:
            r5 = -1
            if (r1 == 0) goto L_0x0036
        L_0x0033:
            r1.close()     // Catch: IOException -> 0x0036
        L_0x0036:
            com.facebook.imagepipeline.image.EncodedImage r5 = r4.getByteBufferBackedEncodedImage(r0, r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.LocalAssetFetchProducer.getEncodedImage(com.facebook.imagepipeline.request.ImageRequest):com.facebook.imagepipeline.image.EncodedImage");
    }

    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    public String getProducerName() {
        return "LocalAssetFetchProducer";
    }
}
