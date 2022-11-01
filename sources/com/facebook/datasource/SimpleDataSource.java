package com.facebook.datasource;

import java.util.Objects;
/* loaded from: classes.dex */
public class SimpleDataSource<T> extends AbstractDataSource<T> {
    @Override // com.facebook.datasource.AbstractDataSource
    public boolean setFailure(Throwable th) {
        Objects.requireNonNull(th);
        return super.setFailure(th);
    }
}
