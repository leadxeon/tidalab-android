package com.facebook.imagepipeline.producers;

import android.net.Uri;
import android.util.Base64;
import androidx.recyclerview.R$dimen;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
/* loaded from: classes.dex */
public class DataFetchProducer extends LocalFetchProducer {
    public DataFetchProducer(PooledByteBufferFactory pooledByteBufferFactory) {
        super(CallerThreadExecutor.sInstance, pooledByteBufferFactory);
    }

    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    public EncodedImage getEncodedImage(ImageRequest imageRequest) throws IOException {
        boolean z;
        byte[] bArr;
        String uri = imageRequest.mSourceUri.toString();
        R$dimen.checkArgument(uri.substring(0, 5).equals("data:"));
        int indexOf = uri.indexOf(44);
        String substring = uri.substring(indexOf + 1, uri.length());
        String substring2 = uri.substring(0, indexOf);
        if (!substring2.contains(";")) {
            z = false;
        } else {
            String[] split = substring2.split(";");
            z = split[split.length - 1].equals("base64");
        }
        if (z) {
            bArr = Base64.decode(substring, 0);
        } else {
            bArr = Uri.decode(substring).getBytes();
        }
        return getByteBufferBackedEncodedImage(new ByteArrayInputStream(bArr), bArr.length);
    }

    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    public String getProducerName() {
        return "DataFetchProducer";
    }
}
