package com.facebook.imagepipeline.core;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/* loaded from: classes.dex */
public class DefaultExecutorSupplier implements ExecutorSupplier {
    public final Executor mBackgroundExecutor;
    public final Executor mDecodeExecutor;
    public final Executor mIoBoundExecutor = Executors.newFixedThreadPool(2, new PriorityThreadFactory(10, "FrescoIoBoundExecutor", true));
    public final Executor mLightWeightBackgroundExecutor = Executors.newFixedThreadPool(1, new PriorityThreadFactory(10, "FrescoLightWeightBackgroundExecutor", true));

    public DefaultExecutorSupplier(int i) {
        this.mDecodeExecutor = Executors.newFixedThreadPool(i, new PriorityThreadFactory(10, "FrescoDecodeExecutor", true));
        this.mBackgroundExecutor = Executors.newFixedThreadPool(i, new PriorityThreadFactory(10, "FrescoBackgroundExecutor", true));
    }

    @Override // com.facebook.imagepipeline.core.ExecutorSupplier
    public Executor forBackgroundTasks() {
        return this.mBackgroundExecutor;
    }

    @Override // com.facebook.imagepipeline.core.ExecutorSupplier
    public Executor forDecode() {
        return this.mDecodeExecutor;
    }

    @Override // com.facebook.imagepipeline.core.ExecutorSupplier
    public Executor forLightweightBackgroundTasks() {
        return this.mLightWeightBackgroundExecutor;
    }

    @Override // com.facebook.imagepipeline.core.ExecutorSupplier
    public Executor forLocalStorageRead() {
        return this.mIoBoundExecutor;
    }

    @Override // com.facebook.imagepipeline.core.ExecutorSupplier
    public Executor forLocalStorageWrite() {
        return this.mIoBoundExecutor;
    }
}
