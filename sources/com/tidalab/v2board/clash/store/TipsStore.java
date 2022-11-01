package com.tidalab.v2board.clash.store;

import android.content.Context;
import com.tidalab.v2board.clash.common.store.SharedPreferenceProvider;
import com.tidalab.v2board.clash.common.store.Store;
import java.util.Objects;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.ReflectionFactory;
import kotlin.reflect.KProperty;
/* compiled from: TipsStore.kt */
/* loaded from: classes.dex */
public final class TipsStore {
    public static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    public final Store.Delegate primaryVersion$delegate;
    public final Store store;

    static {
        MutablePropertyReference1Impl mutablePropertyReference1Impl = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(TipsStore.class), "requestDonate", "getRequestDonate()Z");
        ReflectionFactory reflectionFactory = Reflection.factory;
        Objects.requireNonNull(reflectionFactory);
        MutablePropertyReference1Impl mutablePropertyReference1Impl2 = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(TipsStore.class), "primaryVersion", "getPrimaryVersion()I");
        Objects.requireNonNull(reflectionFactory);
        $$delegatedProperties = new KProperty[]{mutablePropertyReference1Impl, mutablePropertyReference1Impl2};
    }

    public TipsStore(Context context) {
        final Store store = new Store(new SharedPreferenceProvider(context.getSharedPreferences("tips", 0)));
        this.store = store;
        this.primaryVersion$delegate = new Store.Delegate<Integer>() { // from class: com.tidalab.v2board.clash.common.store.Store$int$1
            @Override // com.tidalab.v2board.clash.common.store.Store.Delegate
            public Integer getValue(Object obj, KProperty kProperty) {
                return Integer.valueOf(Store.this.provider.getInt(r2, r3));
            }

            @Override // com.tidalab.v2board.clash.common.store.Store.Delegate
            public void setValue(Object obj, KProperty kProperty, Integer num) {
                Store.this.provider.setInt(r2, num.intValue());
            }
        };
    }
}
