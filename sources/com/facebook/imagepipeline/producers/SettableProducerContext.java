package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
/* loaded from: classes.dex */
public class SettableProducerContext extends BaseProducerContext {
    public SettableProducerContext(ImageRequest imageRequest, ProducerContext producerContext) {
        super(imageRequest, producerContext.getId(), producerContext.getListener(), producerContext.getCallerContext(), producerContext.getLowestPermittedRequestLevel(), producerContext.isPrefetch(), producerContext.isIntermediateResultExpected(), producerContext.getPriority());
    }

    public SettableProducerContext(ImageRequest imageRequest, String str, RequestListener requestListener, Object obj, ImageRequest.RequestLevel requestLevel, boolean z, boolean z2, Priority priority) {
        super(imageRequest, str, requestListener, obj, requestLevel, z, z2, priority);
    }
}
