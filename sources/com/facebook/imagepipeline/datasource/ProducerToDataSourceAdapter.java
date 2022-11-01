package com.facebook.imagepipeline.datasource;

import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
/* loaded from: classes.dex */
public class ProducerToDataSourceAdapter<T> extends AbstractProducerToDataSourceAdapter<T> {
    public ProducerToDataSourceAdapter(Producer<T> producer, SettableProducerContext settableProducerContext, RequestListener requestListener) {
        super(producer, settableProducerContext, requestListener);
    }
}
