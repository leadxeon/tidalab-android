package com.facebook.datasource;

import androidx.recyclerview.R$dimen;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.internal.Objects$ToStringHelper;
import com.facebook.common.internal.Supplier;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class IncreasingQualityDataSourceSupplier<T> implements Supplier<DataSource<T>> {
    public final boolean mDataSourceLazy;
    public final List<Supplier<DataSource<T>>> mDataSourceSuppliers;

    /* loaded from: classes.dex */
    public class IncreasingQualityDataSource extends AbstractDataSource<T> {
        public ArrayList<DataSource<T>> mDataSources;
        public Throwable mDelayedError;
        public AtomicInteger mFinishedDataSources;
        public int mIndexOfDataSourceWithResult;
        public int mNumberOfDataSources;

        /* loaded from: classes.dex */
        public class InternalDataSubscriber implements DataSubscriber<T> {
            public int mIndex;

            public InternalDataSubscriber(int i) {
                this.mIndex = i;
            }

            @Override // com.facebook.datasource.DataSubscriber
            public void onCancellation(DataSource<T> dataSource) {
            }

            @Override // com.facebook.datasource.DataSubscriber
            public void onFailure(DataSource<T> dataSource) {
                IncreasingQualityDataSource.access$300(IncreasingQualityDataSource.this, this.mIndex, dataSource);
            }

            /* JADX WARN: Removed duplicated region for block: B:21:0x0037  */
            @Override // com.facebook.datasource.DataSubscriber
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onNewResult(com.facebook.datasource.DataSource<T> r7) {
                /*
                    r6 = this;
                    boolean r0 = r7.hasResult()
                    if (r0 == 0) goto L_0x006e
                    com.facebook.datasource.IncreasingQualityDataSourceSupplier$IncreasingQualityDataSource r0 = com.facebook.datasource.IncreasingQualityDataSourceSupplier.IncreasingQualityDataSource.this
                    int r1 = r6.mIndex
                    java.util.Objects.requireNonNull(r0)
                    r2 = r7
                    com.facebook.datasource.AbstractDataSource r2 = (com.facebook.datasource.AbstractDataSource) r2
                    boolean r3 = r2.isFinished()
                    monitor-enter(r0)
                    int r4 = r0.mIndexOfDataSourceWithResult     // Catch: all -> 0x006b
                    com.facebook.datasource.DataSource r5 = r0.getDataSource(r1)     // Catch: all -> 0x006b
                    if (r7 != r5) goto L_0x0043
                    int r5 = r0.mIndexOfDataSourceWithResult     // Catch: all -> 0x006b
                    if (r1 != r5) goto L_0x0022
                    goto L_0x0043
                L_0x0022:
                    com.facebook.datasource.DataSource r5 = r0.getDataSourceWithResult()     // Catch: all -> 0x006b
                    if (r5 == 0) goto L_0x0031
                    if (r3 == 0) goto L_0x002f
                    int r3 = r0.mIndexOfDataSourceWithResult     // Catch: all -> 0x006b
                    if (r1 >= r3) goto L_0x002f
                    goto L_0x0031
                L_0x002f:
                    r3 = r4
                    goto L_0x0034
                L_0x0031:
                    r0.mIndexOfDataSourceWithResult = r1     // Catch: all -> 0x006b
                    r3 = r1
                L_0x0034:
                    monitor-exit(r0)     // Catch: all -> 0x006b
                L_0x0035:
                    if (r4 <= r3) goto L_0x0044
                    com.facebook.datasource.DataSource r5 = r0.getAndClearDataSource(r4)
                    if (r5 == 0) goto L_0x0040
                    r5.close()
                L_0x0040:
                    int r4 = r4 + (-1)
                    goto L_0x0035
                L_0x0043:
                    monitor-exit(r0)     // Catch: all -> 0x006b
                L_0x0044:
                    com.facebook.datasource.DataSource r3 = r0.getDataSourceWithResult()
                    if (r7 != r3) goto L_0x0059
                    r7 = 0
                    if (r1 != 0) goto L_0x0055
                    boolean r1 = r2.isFinished()
                    if (r1 == 0) goto L_0x0055
                    r1 = 1
                    goto L_0x0056
                L_0x0055:
                    r1 = 0
                L_0x0056:
                    r0.setResult(r7, r1)
                L_0x0059:
                    java.util.concurrent.atomic.AtomicInteger r7 = r0.mFinishedDataSources
                    int r7 = r7.incrementAndGet()
                    int r1 = r0.mNumberOfDataSources
                    if (r7 != r1) goto L_0x007e
                    java.lang.Throwable r7 = r0.mDelayedError
                    if (r7 == 0) goto L_0x007e
                    r0.setFailure(r7)
                    goto L_0x007e
                L_0x006b:
                    r7 = move-exception
                    monitor-exit(r0)     // Catch: all -> 0x006b
                    throw r7
                L_0x006e:
                    r0 = r7
                    com.facebook.datasource.AbstractDataSource r0 = (com.facebook.datasource.AbstractDataSource) r0
                    boolean r0 = r0.isFinished()
                    if (r0 == 0) goto L_0x007e
                    com.facebook.datasource.IncreasingQualityDataSourceSupplier$IncreasingQualityDataSource r0 = com.facebook.datasource.IncreasingQualityDataSourceSupplier.IncreasingQualityDataSource.this
                    int r1 = r6.mIndex
                    com.facebook.datasource.IncreasingQualityDataSourceSupplier.IncreasingQualityDataSource.access$300(r0, r1, r7)
                L_0x007e:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.IncreasingQualityDataSourceSupplier.IncreasingQualityDataSource.InternalDataSubscriber.onNewResult(com.facebook.datasource.DataSource):void");
            }

            @Override // com.facebook.datasource.DataSubscriber
            public void onProgressUpdate(DataSource<T> dataSource) {
                if (this.mIndex == 0) {
                    IncreasingQualityDataSource.this.setProgress(((AbstractDataSource) dataSource).getProgress());
                }
            }
        }

        public IncreasingQualityDataSource() {
            if (!IncreasingQualityDataSourceSupplier.this.mDataSourceLazy) {
                ensureDataSourceInitialized();
            }
        }

        public static void access$300(IncreasingQualityDataSource increasingQualityDataSource, int i, DataSource dataSource) {
            DataSource dataSource2;
            Throwable th;
            synchronized (increasingQualityDataSource) {
                if (dataSource == increasingQualityDataSource.getDataSourceWithResult()) {
                    dataSource2 = null;
                } else {
                    dataSource2 = dataSource == increasingQualityDataSource.getDataSource(i) ? increasingQualityDataSource.getAndClearDataSource(i) : dataSource;
                }
            }
            if (dataSource2 != null) {
                dataSource2.close();
            }
            if (i == 0) {
                increasingQualityDataSource.mDelayedError = dataSource.getFailureCause();
            }
            if (increasingQualityDataSource.mFinishedDataSources.incrementAndGet() == increasingQualityDataSource.mNumberOfDataSources && (th = increasingQualityDataSource.mDelayedError) != null) {
                increasingQualityDataSource.setFailure(th);
            }
        }

        @Override // com.facebook.datasource.AbstractDataSource, com.facebook.datasource.DataSource
        public boolean close() {
            if (IncreasingQualityDataSourceSupplier.this.mDataSourceLazy) {
                ensureDataSourceInitialized();
            }
            synchronized (this) {
                if (!super.close()) {
                    return false;
                }
                ArrayList<DataSource<T>> arrayList = this.mDataSources;
                this.mDataSources = null;
                if (arrayList == null) {
                    return true;
                }
                for (int i = 0; i < arrayList.size(); i++) {
                    DataSource<T> dataSource = arrayList.get(i);
                    if (dataSource != null) {
                        dataSource.close();
                    }
                }
                return true;
            }
        }

        public final void ensureDataSourceInitialized() {
            if (this.mFinishedDataSources == null) {
                synchronized (this) {
                    if (this.mFinishedDataSources == null) {
                        this.mFinishedDataSources = new AtomicInteger(0);
                        int size = IncreasingQualityDataSourceSupplier.this.mDataSourceSuppliers.size();
                        this.mNumberOfDataSources = size;
                        this.mIndexOfDataSourceWithResult = size;
                        this.mDataSources = new ArrayList<>(size);
                        for (int i = 0; i < size; i++) {
                            DataSource<T> dataSource = IncreasingQualityDataSourceSupplier.this.mDataSourceSuppliers.get(i).get();
                            this.mDataSources.add(dataSource);
                            dataSource.subscribe(new InternalDataSubscriber(i), CallerThreadExecutor.sInstance);
                            if (dataSource.hasResult()) {
                                break;
                            }
                        }
                    }
                }
            }
        }

        public final synchronized DataSource<T> getAndClearDataSource(int i) {
            DataSource<T> dataSource;
            ArrayList<DataSource<T>> arrayList = this.mDataSources;
            dataSource = null;
            if (arrayList != null && i < arrayList.size()) {
                dataSource = this.mDataSources.set(i, null);
            }
            return dataSource;
        }

        public final synchronized DataSource<T> getDataSource(int i) {
            ArrayList<DataSource<T>> arrayList;
            arrayList = this.mDataSources;
            return (arrayList == null || i >= arrayList.size()) ? null : this.mDataSources.get(i);
        }

        public final synchronized DataSource<T> getDataSourceWithResult() {
            return getDataSource(this.mIndexOfDataSourceWithResult);
        }

        @Override // com.facebook.datasource.AbstractDataSource, com.facebook.datasource.DataSource
        public synchronized T getResult() {
            DataSource<T> dataSourceWithResult;
            if (IncreasingQualityDataSourceSupplier.this.mDataSourceLazy) {
                ensureDataSourceInitialized();
            }
            dataSourceWithResult = getDataSourceWithResult();
            return dataSourceWithResult != null ? dataSourceWithResult.getResult() : null;
        }

        @Override // com.facebook.datasource.AbstractDataSource, com.facebook.datasource.DataSource
        public synchronized boolean hasResult() {
            boolean z;
            if (IncreasingQualityDataSourceSupplier.this.mDataSourceLazy) {
                ensureDataSourceInitialized();
            }
            DataSource<T> dataSourceWithResult = getDataSourceWithResult();
            if (dataSourceWithResult != null) {
                if (dataSourceWithResult.hasResult()) {
                    z = true;
                }
            }
            z = false;
            return z;
        }
    }

    public IncreasingQualityDataSourceSupplier(List<Supplier<DataSource<T>>> list, boolean z) {
        R$dimen.checkArgument(!list.isEmpty(), "List of suppliers is empty!");
        this.mDataSourceSuppliers = list;
        this.mDataSourceLazy = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IncreasingQualityDataSourceSupplier)) {
            return false;
        }
        return R$dimen.equal(this.mDataSourceSuppliers, ((IncreasingQualityDataSourceSupplier) obj).mDataSourceSuppliers);
    }

    @Override // com.facebook.common.internal.Supplier
    public Object get() {
        return new IncreasingQualityDataSource();
    }

    public int hashCode() {
        return this.mDataSourceSuppliers.hashCode();
    }

    public String toString() {
        Objects$ToStringHelper stringHelper = R$dimen.toStringHelper(this);
        stringHelper.addHolder("list", this.mDataSourceSuppliers);
        return stringHelper.toString();
    }
}
