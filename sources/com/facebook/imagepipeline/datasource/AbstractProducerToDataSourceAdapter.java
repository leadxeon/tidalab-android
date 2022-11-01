package com.facebook.imagepipeline.datasource;

import androidx.recyclerview.R$dimen;
import com.facebook.datasource.AbstractDataSource;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.producers.BaseConsumer;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
/* loaded from: classes.dex */
public abstract class AbstractProducerToDataSourceAdapter<T> extends AbstractDataSource<T> {
    public final RequestListener mRequestListener;
    public final SettableProducerContext mSettableProducerContext;

    public AbstractProducerToDataSourceAdapter(Producer<T> producer, SettableProducerContext settableProducerContext, RequestListener requestListener) {
        FrescoSystrace.isTracing();
        this.mSettableProducerContext = settableProducerContext;
        this.mRequestListener = requestListener;
        FrescoSystrace.isTracing();
        requestListener.onRequestStart(settableProducerContext.mImageRequest, settableProducerContext.mCallerContext, settableProducerContext.mId, settableProducerContext.isPrefetch());
        FrescoSystrace.isTracing();
        FrescoSystrace.isTracing();
        producer.produceResults(new BaseConsumer<T>() { // from class: com.facebook.imagepipeline.datasource.AbstractProducerToDataSourceAdapter.1
            @Override // com.facebook.imagepipeline.producers.BaseConsumer
            public void onCancellationImpl() {
                AbstractProducerToDataSourceAdapter abstractProducerToDataSourceAdapter = AbstractProducerToDataSourceAdapter.this;
                synchronized (abstractProducerToDataSourceAdapter) {
                    R$dimen.checkState(abstractProducerToDataSourceAdapter.isClosed());
                }
            }

            @Override // com.facebook.imagepipeline.producers.BaseConsumer
            public void onFailureImpl(Throwable th) {
                AbstractProducerToDataSourceAdapter abstractProducerToDataSourceAdapter = AbstractProducerToDataSourceAdapter.this;
                if (abstractProducerToDataSourceAdapter.setFailure(th)) {
                    RequestListener requestListener2 = abstractProducerToDataSourceAdapter.mRequestListener;
                    SettableProducerContext settableProducerContext2 = abstractProducerToDataSourceAdapter.mSettableProducerContext;
                    requestListener2.onRequestFailure(settableProducerContext2.mImageRequest, settableProducerContext2.mId, th, settableProducerContext2.isPrefetch());
                }
            }

            @Override // com.facebook.imagepipeline.producers.BaseConsumer
            public void onNewResultImpl(T t, int i) {
                AbstractProducerToDataSourceAdapter.this.onNewResultImpl(t, i);
            }

            @Override // com.facebook.imagepipeline.producers.BaseConsumer
            public void onProgressUpdateImpl(float f) {
                AbstractProducerToDataSourceAdapter.this.setProgress(f);
            }
        }, settableProducerContext);
        FrescoSystrace.isTracing();
        FrescoSystrace.isTracing();
    }

    @Override // com.facebook.datasource.AbstractDataSource, com.facebook.datasource.DataSource
    public boolean close() {
        if (!super.close()) {
            return false;
        }
        if (isFinished()) {
            return true;
        }
        this.mRequestListener.onRequestCancellation(this.mSettableProducerContext.mId);
        this.mSettableProducerContext.cancel();
        return true;
    }

    public void onNewResultImpl(T t, int i) {
        boolean isLast = BaseConsumer.isLast(i);
        if (setResult(t, isLast) && isLast) {
            RequestListener requestListener = this.mRequestListener;
            SettableProducerContext settableProducerContext = this.mSettableProducerContext;
            requestListener.onRequestSuccess(settableProducerContext.mImageRequest, settableProducerContext.mId, settableProducerContext.isPrefetch());
        }
    }
}
