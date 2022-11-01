package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.listener.RequestListener;
import java.util.Objects;
/* loaded from: classes.dex */
public class ThreadHandoffProducer<T> implements Producer<T> {
    public final Producer<T> mInputProducer;
    public final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;

    public ThreadHandoffProducer(Producer<T> producer, ThreadHandoffProducerQueue threadHandoffProducerQueue) {
        Objects.requireNonNull(producer);
        this.mInputProducer = producer;
        this.mThreadHandoffProducerQueue = threadHandoffProducerQueue;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(final Consumer<T> consumer, final ProducerContext producerContext) {
        final RequestListener listener = producerContext.getListener();
        final String id = producerContext.getId();
        final StatefulProducerRunnable<T> statefulProducerRunnable = new StatefulProducerRunnable<T>(consumer, listener, "BackgroundThreadHandoffProducer", id) { // from class: com.facebook.imagepipeline.producers.ThreadHandoffProducer.1
            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable
            public void disposeResult(T t) {
            }

            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable
            public T getResult() throws Exception {
                return null;
            }

            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable
            public void onSuccess(T t) {
                listener.onProducerFinishWithSuccess(id, "BackgroundThreadHandoffProducer", null);
                ThreadHandoffProducer.this.mInputProducer.produceResults(consumer, producerContext);
            }
        };
        producerContext.addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.ThreadHandoffProducer.2
            @Override // com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                statefulProducerRunnable.cancel();
                ThreadHandoffProducerQueue threadHandoffProducerQueue = ThreadHandoffProducer.this.mThreadHandoffProducerQueue;
                StatefulProducerRunnable statefulProducerRunnable2 = statefulProducerRunnable;
                synchronized (threadHandoffProducerQueue) {
                    threadHandoffProducerQueue.mRunnableList.remove(statefulProducerRunnable2);
                }
            }
        });
        ThreadHandoffProducerQueue threadHandoffProducerQueue = this.mThreadHandoffProducerQueue;
        synchronized (threadHandoffProducerQueue) {
            threadHandoffProducerQueue.mExecutor.execute(statefulProducerRunnable);
        }
    }
}
