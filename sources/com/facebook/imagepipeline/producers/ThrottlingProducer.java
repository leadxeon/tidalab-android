package com.facebook.imagepipeline.producers;

import android.util.Pair;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class ThrottlingProducer<T> implements Producer<T> {
    public final Executor mExecutor;
    public final Producer<T> mInputProducer;
    public final ConcurrentLinkedQueue<Pair<Consumer<T>, ProducerContext>> mPendingRequests = new ConcurrentLinkedQueue<>();
    public int mNumCurrentRequests = 0;

    /* loaded from: classes.dex */
    public class ThrottlerConsumer extends DelegatingConsumer<T, T> {
        public ThrottlerConsumer(Consumer consumer, AnonymousClass1 r3) {
            super(consumer);
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        public void onCancellationImpl() {
            this.mConsumer.onCancellation();
            onRequestFinished();
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        public void onFailureImpl(Throwable th) {
            this.mConsumer.onFailure(th);
            onRequestFinished();
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(T t, int i) {
            this.mConsumer.onNewResult(t, i);
            if (BaseConsumer.isLast(i)) {
                onRequestFinished();
            }
        }

        public final void onRequestFinished() {
            final Pair<Consumer<T>, ProducerContext> poll;
            synchronized (ThrottlingProducer.this) {
                poll = ThrottlingProducer.this.mPendingRequests.poll();
                if (poll == null) {
                    ThrottlingProducer throttlingProducer = ThrottlingProducer.this;
                    throttlingProducer.mNumCurrentRequests--;
                }
            }
            if (poll != null) {
                ThrottlingProducer.this.mExecutor.execute(new Runnable() { // from class: com.facebook.imagepipeline.producers.ThrottlingProducer.ThrottlerConsumer.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ThrottlingProducer throttlingProducer2 = ThrottlingProducer.this;
                        Pair pair = poll;
                        throttlingProducer2.produceResultsInternal((Consumer) pair.first, (ProducerContext) pair.second);
                    }
                });
            }
        }
    }

    public ThrottlingProducer(int i, Executor executor, Producer<T> producer) {
        Objects.requireNonNull(executor);
        this.mExecutor = executor;
        Objects.requireNonNull(producer);
        this.mInputProducer = producer;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<T> consumer, ProducerContext producerContext) {
        boolean z;
        producerContext.getListener().onProducerStart(producerContext.getId(), "ThrottlingProducer");
        synchronized (this) {
            int i = this.mNumCurrentRequests;
            z = true;
            if (i >= 5) {
                this.mPendingRequests.add(Pair.create(consumer, producerContext));
            } else {
                this.mNumCurrentRequests = i + 1;
                z = false;
            }
        }
        if (!z) {
            produceResultsInternal(consumer, producerContext);
        }
    }

    public void produceResultsInternal(Consumer<T> consumer, ProducerContext producerContext) {
        producerContext.getListener().onProducerFinishWithSuccess(producerContext.getId(), "ThrottlingProducer", null);
        this.mInputProducer.produceResults(new ThrottlerConsumer(consumer, null), producerContext);
    }
}
