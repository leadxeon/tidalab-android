package com.facebook.imagepipeline.datasource;

import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
/* loaded from: classes.dex */
public class CloseableProducerToDataSourceAdapter<T> extends AbstractProducerToDataSourceAdapter<CloseableReference<T>> {
    public CloseableProducerToDataSourceAdapter(Producer<CloseableReference<T>> producer, SettableProducerContext settableProducerContext, RequestListener requestListener) {
        super(producer, settableProducerContext, requestListener);
    }

    @Override // com.facebook.datasource.AbstractDataSource
    public void closeResult(Object obj) {
        Class<CloseableReference> cls = CloseableReference.TAG;
        ((CloseableReference) obj).close();
    }

    @Override // com.facebook.datasource.AbstractDataSource, com.facebook.datasource.DataSource
    public Object getResult() {
        return CloseableReference.cloneOrNull((CloseableReference) super.getResult());
    }

    @Override // com.facebook.imagepipeline.datasource.AbstractProducerToDataSourceAdapter
    public void onNewResultImpl(Object obj, int i) {
        super.onNewResultImpl(CloseableReference.cloneOrNull((CloseableReference) obj), i);
    }
}
