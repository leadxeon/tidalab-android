package com.facebook.imagepipeline.producers;
/* loaded from: classes.dex */
public interface Producer<T> {
    void produceResults(Consumer<T> consumer, ProducerContext producerContext);
}
