package com.tidalab.v2board.clash.service.store;

import android.content.Context;
import android.content.SharedPreferences;
import com.tidalab.v2board.clash.common.constants.Authorities;
import com.tidalab.v2board.clash.common.store.SharedPreferenceProvider;
import com.tidalab.v2board.clash.common.store.Store;
import com.tidalab.v2board.clash.common.store.Store$boolean$1;
import com.tidalab.v2board.clash.common.store.Store$enum$1;
import com.tidalab.v2board.clash.common.store.Store$string$1;
import com.tidalab.v2board.clash.service.BaseService;
import com.tidalab.v2board.clash.service.TunService;
import com.tidalab.v2board.clash.service.model.AccessControlMode;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import kotlin.collections.EmptySet;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.ReflectionFactory;
import kotlin.reflect.KProperty;
import okhttp3.HttpUrl;
import rikka.preference.MultiProcessPreference;
/* compiled from: ServiceStore.kt */
/* loaded from: classes.dex */
public final class ServiceStore {
    public static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    public final Store.Delegate accessControlMode$delegate;
    public final Store.Delegate accessControlPackages$delegate;
    public final Store.Delegate activeProfile$delegate;
    public final Store.Delegate blockLoopback$delegate;
    public final Store.Delegate bypassPrivateNetwork$delegate;
    public final Store.Delegate dnsHijacking$delegate;
    public final Store.Delegate dynamicNotification$delegate;
    public final Store.Delegate sideloadGeoip$delegate;
    public final Store store;
    public final Store.Delegate systemProxy$delegate;

    static {
        MutablePropertyReference1Impl mutablePropertyReference1Impl = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(ServiceStore.class), "activeProfile", "getActiveProfile()Ljava/util/UUID;");
        ReflectionFactory reflectionFactory = Reflection.factory;
        Objects.requireNonNull(reflectionFactory);
        MutablePropertyReference1Impl mutablePropertyReference1Impl2 = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(ServiceStore.class), "bypassPrivateNetwork", "getBypassPrivateNetwork()Z");
        Objects.requireNonNull(reflectionFactory);
        MutablePropertyReference1Impl mutablePropertyReference1Impl3 = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(ServiceStore.class), "accessControlMode", "getAccessControlMode()Lcom/tidalab/v2board/clash/service/model/AccessControlMode;");
        Objects.requireNonNull(reflectionFactory);
        MutablePropertyReference1Impl mutablePropertyReference1Impl4 = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(ServiceStore.class), "accessControlPackages", "getAccessControlPackages()Ljava/util/Set;");
        Objects.requireNonNull(reflectionFactory);
        MutablePropertyReference1Impl mutablePropertyReference1Impl5 = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(ServiceStore.class), "dnsHijacking", "getDnsHijacking()Z");
        Objects.requireNonNull(reflectionFactory);
        MutablePropertyReference1Impl mutablePropertyReference1Impl6 = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(ServiceStore.class), "systemProxy", "getSystemProxy()Z");
        Objects.requireNonNull(reflectionFactory);
        MutablePropertyReference1Impl mutablePropertyReference1Impl7 = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(ServiceStore.class), "blockLoopback", "getBlockLoopback()Z");
        Objects.requireNonNull(reflectionFactory);
        MutablePropertyReference1Impl mutablePropertyReference1Impl8 = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(ServiceStore.class), "dynamicNotification", "getDynamicNotification()Z");
        Objects.requireNonNull(reflectionFactory);
        MutablePropertyReference1Impl mutablePropertyReference1Impl9 = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(ServiceStore.class), "sideloadGeoip", "getSideloadGeoip()Ljava/lang/String;");
        Objects.requireNonNull(reflectionFactory);
        $$delegatedProperties = new KProperty[]{mutablePropertyReference1Impl, mutablePropertyReference1Impl2, mutablePropertyReference1Impl3, mutablePropertyReference1Impl4, mutablePropertyReference1Impl5, mutablePropertyReference1Impl6, mutablePropertyReference1Impl7, mutablePropertyReference1Impl8, mutablePropertyReference1Impl9};
    }

    public ServiceStore(Context context) {
        SharedPreferences sharedPreferences;
        if (context instanceof BaseService ? true : context instanceof TunService) {
            sharedPreferences = context.getSharedPreferences("service", 0);
        } else {
            Authorities authorities = Authorities.INSTANCE;
            sharedPreferences = new MultiProcessPreference(context, Authorities.SETTINGS_PROVIDER);
        }
        final Store store = new Store(new SharedPreferenceProvider(sharedPreferences));
        this.store = store;
        final ServiceStore$activeProfile$2 serviceStore$activeProfile$2 = ServiceStore$activeProfile$2.INSTANCE;
        final ServiceStore$activeProfile$3 serviceStore$activeProfile$3 = ServiceStore$activeProfile$3.INSTANCE;
        this.activeProfile$delegate = new Store.Delegate<T>() { // from class: com.tidalab.v2board.clash.common.store.Store$typedString$1
            /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.Object] */
            @Override // com.tidalab.v2board.clash.common.store.Store.Delegate
            public T getValue(Object obj, KProperty<?> kProperty) {
                return serviceStore$activeProfile$2.invoke(Store.this.provider.getString(r2, serviceStore$activeProfile$3.invoke(null)));
            }

            @Override // com.tidalab.v2board.clash.common.store.Store.Delegate
            public void setValue(Object obj, KProperty<?> kProperty, T t) {
                Store.this.provider.setString(r2, serviceStore$activeProfile$3.invoke(t));
            }
        };
        this.bypassPrivateNetwork$delegate = new Store$boolean$1(store, "bypass_private_network", true);
        this.accessControlMode$delegate = new Store$enum$1(store, "access_control_mode", AccessControlMode.AcceptAll, AccessControlMode.values());
        final EmptySet emptySet = EmptySet.INSTANCE;
        this.accessControlPackages$delegate = new Store.Delegate<Set<? extends String>>() { // from class: com.tidalab.v2board.clash.common.store.Store$stringSet$1
            @Override // com.tidalab.v2board.clash.common.store.Store.Delegate
            public Set<? extends String> getValue(Object obj, KProperty kProperty) {
                return Store.this.provider.getStringSet(r2, emptySet);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.tidalab.v2board.clash.common.store.Store.Delegate
            public void setValue(Object obj, KProperty kProperty, Set<? extends String> set) {
                Store.this.provider.setStringSet(r2, set);
            }
        };
        this.dnsHijacking$delegate = new Store$boolean$1(store, "dns_hijacking", true);
        this.systemProxy$delegate = new Store$boolean$1(store, "system_proxy", false);
        this.blockLoopback$delegate = new Store$boolean$1(store, "block_loopback", true);
        this.dynamicNotification$delegate = new Store$boolean$1(store, "dynamic_notification", true);
        this.sideloadGeoip$delegate = new Store$string$1(store, "sideload_geoip", HttpUrl.FRAGMENT_ENCODE_SET);
    }

    public final Set<String> getAccessControlPackages() {
        return (Set) this.accessControlPackages$delegate.getValue(this, $$delegatedProperties[3]);
    }

    public final UUID getActiveProfile() {
        return (UUID) this.activeProfile$delegate.getValue(this, $$delegatedProperties[0]);
    }

    public final boolean getBypassPrivateNetwork() {
        return ((Boolean) this.bypassPrivateNetwork$delegate.getValue(this, $$delegatedProperties[1])).booleanValue();
    }

    public final boolean getDynamicNotification() {
        return ((Boolean) this.dynamicNotification$delegate.getValue(this, $$delegatedProperties[7])).booleanValue();
    }

    public final String getSideloadGeoip() {
        return (String) this.sideloadGeoip$delegate.getValue(this, $$delegatedProperties[8]);
    }
}
