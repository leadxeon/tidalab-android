package com.facebook.imagepipeline.producers;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class ThreadHandoffProducerQueue {
    public final Executor mExecutor;
    public final Deque<Runnable> mRunnableList = new ArrayDeque();

    public ThreadHandoffProducerQueue(Executor executor) {
        Objects.requireNonNull(executor);
        this.mExecutor = executor;
    }
}
