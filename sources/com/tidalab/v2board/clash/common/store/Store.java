package com.tidalab.v2board.clash.common.store;

import kotlin.reflect.KProperty;
/* compiled from: Store.kt */
/* loaded from: classes.dex */
public final class Store {
    public final StoreProvider provider;

    /* compiled from: Store.kt */
    /* loaded from: classes.dex */
    public interface Delegate<T> {
        T getValue(Object obj, KProperty<?> kProperty);

        void setValue(Object obj, KProperty<?> kProperty, T t);
    }

    public Store(StoreProvider storeProvider) {
        this.provider = storeProvider;
    }
}
