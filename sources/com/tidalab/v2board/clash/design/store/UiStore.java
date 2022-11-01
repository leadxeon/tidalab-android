package com.tidalab.v2board.clash.design.store;

import android.content.Context;
import com.tidalab.v2board.clash.common.store.SharedPreferenceProvider;
import com.tidalab.v2board.clash.common.store.Store;
import com.tidalab.v2board.clash.common.store.Store$boolean$1;
import com.tidalab.v2board.clash.common.store.Store$enum$1;
import com.tidalab.v2board.clash.common.store.Store$string$1;
import com.tidalab.v2board.clash.core.model.ProxySort;
import com.tidalab.v2board.clash.design.model.AppInfoSort;
import com.tidalab.v2board.clash.design.model.DarkMode;
import java.util.Objects;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.ReflectionFactory;
import kotlin.reflect.KProperty;
import okhttp3.HttpUrl;
/* compiled from: UiStore.kt */
/* loaded from: classes.dex */
public final class UiStore {
    public static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    public final Store.Delegate accessControlReverse$delegate;
    public final Store.Delegate accessControlSort$delegate;
    public final Store.Delegate accessControlSystemApp$delegate;
    public final Store.Delegate darkMode$delegate;
    public final Store.Delegate enableVpn$delegate;
    public final Store.Delegate proxyExcludeNotSelectable$delegate;
    public final Store.Delegate proxyLastGroup$delegate;
    public final Store.Delegate proxySingleLine$delegate;
    public final Store.Delegate proxySort$delegate;
    public final Store store;

    static {
        MutablePropertyReference1Impl mutablePropertyReference1Impl = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(UiStore.class), "enableVpn", "getEnableVpn()Z");
        ReflectionFactory reflectionFactory = Reflection.factory;
        Objects.requireNonNull(reflectionFactory);
        MutablePropertyReference1Impl mutablePropertyReference1Impl2 = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(UiStore.class), "darkMode", "getDarkMode()Lcom/tidalab/v2board/clash/design/model/DarkMode;");
        Objects.requireNonNull(reflectionFactory);
        MutablePropertyReference1Impl mutablePropertyReference1Impl3 = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(UiStore.class), "proxyExcludeNotSelectable", "getProxyExcludeNotSelectable()Z");
        Objects.requireNonNull(reflectionFactory);
        MutablePropertyReference1Impl mutablePropertyReference1Impl4 = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(UiStore.class), "proxySingleLine", "getProxySingleLine()Z");
        Objects.requireNonNull(reflectionFactory);
        MutablePropertyReference1Impl mutablePropertyReference1Impl5 = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(UiStore.class), "proxySort", "getProxySort()Lcom/tidalab/v2board/clash/core/model/ProxySort;");
        Objects.requireNonNull(reflectionFactory);
        MutablePropertyReference1Impl mutablePropertyReference1Impl6 = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(UiStore.class), "proxyLastGroup", "getProxyLastGroup()Ljava/lang/String;");
        Objects.requireNonNull(reflectionFactory);
        MutablePropertyReference1Impl mutablePropertyReference1Impl7 = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(UiStore.class), "accessControlSort", "getAccessControlSort()Lcom/tidalab/v2board/clash/design/model/AppInfoSort;");
        Objects.requireNonNull(reflectionFactory);
        MutablePropertyReference1Impl mutablePropertyReference1Impl8 = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(UiStore.class), "accessControlReverse", "getAccessControlReverse()Z");
        Objects.requireNonNull(reflectionFactory);
        MutablePropertyReference1Impl mutablePropertyReference1Impl9 = new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(UiStore.class), "accessControlSystemApp", "getAccessControlSystemApp()Z");
        Objects.requireNonNull(reflectionFactory);
        $$delegatedProperties = new KProperty[]{mutablePropertyReference1Impl, mutablePropertyReference1Impl2, mutablePropertyReference1Impl3, mutablePropertyReference1Impl4, mutablePropertyReference1Impl5, mutablePropertyReference1Impl6, mutablePropertyReference1Impl7, mutablePropertyReference1Impl8, mutablePropertyReference1Impl9};
    }

    public UiStore(Context context) {
        Store store = new Store(new SharedPreferenceProvider(context.getSharedPreferences("ui", 0)));
        this.store = store;
        this.enableVpn$delegate = new Store$boolean$1(store, "enable_vpn", true);
        this.darkMode$delegate = new Store$enum$1(store, "dark_mode", DarkMode.ForceLight, DarkMode.values());
        this.proxyExcludeNotSelectable$delegate = new Store$boolean$1(store, "proxy_exclude_not_selectable", false);
        this.proxySingleLine$delegate = new Store$boolean$1(store, "proxy_single_line", false);
        this.proxySort$delegate = new Store$enum$1(store, "proxy_sort", ProxySort.Default, ProxySort.values());
        this.proxyLastGroup$delegate = new Store$string$1(store, "proxy_last_group", HttpUrl.FRAGMENT_ENCODE_SET);
        this.accessControlSort$delegate = new Store$enum$1(store, "access_control_sort", AppInfoSort.Label, AppInfoSort.values());
        this.accessControlReverse$delegate = new Store$boolean$1(store, "access_control_reverse", false);
        this.accessControlSystemApp$delegate = new Store$boolean$1(store, "access_control_system_app", false);
    }

    public final boolean getEnableVpn() {
        return ((Boolean) this.enableVpn$delegate.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    public final boolean getProxyExcludeNotSelectable() {
        return ((Boolean) this.proxyExcludeNotSelectable$delegate.getValue(this, $$delegatedProperties[2])).booleanValue();
    }

    public final boolean getProxySingleLine() {
        return ((Boolean) this.proxySingleLine$delegate.getValue(this, $$delegatedProperties[3])).booleanValue();
    }
}
