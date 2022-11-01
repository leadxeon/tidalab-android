package com.facebook.datasource;

import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public interface DataSource<T> {
    boolean close();

    Throwable getFailureCause();

    float getProgress();

    T getResult();

    boolean hasMultipleResults();

    boolean hasResult();

    boolean isFinished();

    void subscribe(DataSubscriber<T> dataSubscriber, Executor executor);
}
