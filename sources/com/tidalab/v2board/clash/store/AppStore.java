package com.tidalab.v2board.clash.store;

import android.content.Context;
import com.tidalab.v2board.clash.common.store.SharedPreferenceProvider;
import com.tidalab.v2board.clash.common.store.Store;
import java.util.Objects;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
/* compiled from: AppStore.kt */
/* loaded from: classes.dex */
public final class AppStore {
    public static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    public final Store store;
    public final Store.Delegate updatedAt$delegate;

    static {
        MutablePropertyReference1Impl mutablePropertyReference1Impl = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(AppStore.class), "updatedAt", "getUpdatedAt()J");
        Objects.requireNonNull(Reflection.factory);
        $$delegatedProperties = new KProperty[]{mutablePropertyReference1Impl};
    }

    public AppStore(Context context) {
        final Store store = new Store(new SharedPreferenceProvider(context.getSharedPreferences("app", 0)));
        this.store = store;
        this.updatedAt$delegate = new Store.Delegate<Long>() { // from class: com.tidalab.v2board.clash.common.store.Store$long$1
            @Override // com.tidalab.v2board.clash.common.store.Store.Delegate
            public Long getValue(Object obj, KProperty kProperty) {
                return Long.valueOf(Store.this.provider.getLong(r2, r3));
            }

            @Override // com.tidalab.v2board.clash.common.store.Store.Delegate
            public void setValue(Object obj, KProperty kProperty, Long l) {
                Store.this.provider.setLong(r2, l.longValue());
            }
        };
    }
}
