package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.listener.RequestListener;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public abstract class StatefulProducerRunnable<T> implements Runnable {
    public final Consumer<T> mConsumer;
    public final RequestListener mProducerListener;
    public final String mProducerName;
    public final String mRequestId;
    public final AtomicInteger mState = new AtomicInteger(0);

    public StatefulProducerRunnable(Consumer<T> consumer, RequestListener requestListener, String str, String str2) {
        this.mConsumer = consumer;
        this.mProducerListener = requestListener;
        this.mProducerName = str;
        this.mRequestId = str2;
        requestListener.onProducerStart(str2, str);
    }

    public void cancel() {
        if (this.mState.compareAndSet(0, 2)) {
            onCancellation();
        }
    }

    public abstract void disposeResult(T t);

    public Map<String, String> getExtraMapOnSuccess(T t) {
        return null;
    }

    public abstract T getResult() throws Exception;

    public void onCancellation() {
        RequestListener requestListener = this.mProducerListener;
        String str = this.mRequestId;
        String str2 = this.mProducerName;
        requestListener.requiresExtraMap(str);
        requestListener.onProducerFinishWithCancellation(str, str2, null);
        this.mConsumer.onCancellation();
    }

    public void onFailure(Exception exc) {
        RequestListener requestListener = this.mProducerListener;
        String str = this.mRequestId;
        String str2 = this.mProducerName;
        requestListener.requiresExtraMap(str);
        requestListener.onProducerFinishWithFailure(str, str2, exc, null);
        this.mConsumer.onFailure(exc);
    }

    public void onSuccess(T t) {
        RequestListener requestListener = this.mProducerListener;
        String str = this.mRequestId;
        requestListener.onProducerFinishWithSuccess(str, this.mProducerName, requestListener.requiresExtraMap(str) ? getExtraMapOnSuccess(t) : null);
        this.mConsumer.onNewResult(t, 1);
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.mState.compareAndSet(0, 1)) {
            try {
                T result = getResult();
                this.mState.set(3);
                try {
                    onSuccess(result);
                } finally {
                    disposeResult(result);
                }
            } catch (Exception e) {
                this.mState.set(4);
                onFailure(e);
            }
        }
    }
}
