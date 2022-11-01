package com.facebook.react.modules.fresco;

import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes.dex */
public class ReactNetworkImageRequest extends ImageRequest {
    public final ReadableMap mHeaders;

    public ReactNetworkImageRequest(ImageRequestBuilder imageRequestBuilder, ReadableMap readableMap) {
        super(imageRequestBuilder);
        this.mHeaders = readableMap;
    }
}
