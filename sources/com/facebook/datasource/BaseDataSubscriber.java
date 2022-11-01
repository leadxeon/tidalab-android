package com.facebook.datasource;
/* loaded from: classes.dex */
public abstract class BaseDataSubscriber<T> implements DataSubscriber<T> {
    @Override // com.facebook.datasource.DataSubscriber
    public void onCancellation(DataSource<T> dataSource) {
    }

    @Override // com.facebook.datasource.DataSubscriber
    public void onFailure(DataSource<T> dataSource) {
        try {
            onFailureImpl(dataSource);
        } finally {
            dataSource.close();
        }
    }

    public abstract void onFailureImpl(DataSource<T> dataSource);

    @Override // com.facebook.datasource.DataSubscriber
    public void onNewResult(DataSource<T> dataSource) {
        AbstractDataSource abstractDataSource = (AbstractDataSource) dataSource;
        boolean isFinished = abstractDataSource.isFinished();
        try {
            onNewResultImpl(abstractDataSource);
        } finally {
            if (isFinished) {
                abstractDataSource.close();
            }
        }
    }

    public abstract void onNewResultImpl(DataSource<T> dataSource);

    @Override // com.facebook.datasource.DataSubscriber
    public void onProgressUpdate(DataSource<T> dataSource) {
    }
}
