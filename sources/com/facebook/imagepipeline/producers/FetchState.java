package com.facebook.imagepipeline.producers;

import android.net.Uri;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.listener.RequestListener;
/* loaded from: classes.dex */
public class FetchState {
    public final Consumer<EncodedImage> mConsumer;
    public final ProducerContext mContext;
    public long mLastIntermediateResultTimeMs = 0;
    public int mOnNewResultStatusFlags;
    public BytesRange mResponseBytesRange;

    public FetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        this.mConsumer = consumer;
        this.mContext = producerContext;
    }

    public String getId() {
        return this.mContext.getId();
    }

    public RequestListener getListener() {
        return this.mContext.getListener();
    }

    public Uri getUri() {
        return this.mContext.getImageRequest().mSourceUri;
    }
}
