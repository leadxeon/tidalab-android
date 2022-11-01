package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.core.model.ConfigurationOverride;
import com.tidalab.v2board.clash.core.model.LogMessage;
import com.tidalab.v2board.clash.core.model.TunnelState;
import com.tidalab.v2board.clash.design.databinding.DesignSettingsOverideBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.preference.NullableTextAdapter;
import com.tidalab.v2board.clash.design.preference.OnChangedListener;
import com.tidalab.v2board.clash.design.preference.ScreenKt$preferenceScreen$impl$1;
import com.tidalab.v2board.clash.design.preference.SelectableListKt$selectableList$impl$1;
import com.tidalab.v2board.clash.design.preference.SelectableListPreference;
import com.tidalab.v2board.clash.design.preference.TextAdapter;
import com.tidalab.v2board.clash.foss.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
/* compiled from: OverrideSettingsDesign.kt */
/* loaded from: classes.dex */
public final class OverrideSettingsDesign extends Design<Request> {
    public final DesignSettingsOverideBinding binding;

    /* compiled from: OverrideSettingsDesign.kt */
    /* loaded from: classes.dex */
    public enum Request {
        ResetOverride,
        EditSideloadGeoip
    }

    public OverrideSettingsDesign(Context context, final ConfigurationOverride configurationOverride) {
        super(context);
        LayoutInflater from = LayoutInflater.from(context);
        ViewGroup root = InputKt.getRoot(context);
        int i = DesignSettingsOverideBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        DesignSettingsOverideBinding designSettingsOverideBinding = (DesignSettingsOverideBinding) ViewDataBinding.inflateInternal(from, R.layout.design_settings_overide, root, false, null);
        this.binding = designSettingsOverideBinding;
        designSettingsOverideBinding.setSelf(this);
        InputKt.applyFrom(designSettingsOverideBinding.activityBarLayout, context);
        InputKt.bindAppBarElevation(designSettingsOverideBinding.scrollRoot, designSettingsOverideBinding.activityBarLayout);
        Boolean[] boolArr = {null, Boolean.TRUE, Boolean.FALSE};
        Integer valueOf = Integer.valueOf((int) R.string.dont_modify);
        Integer valueOf2 = Integer.valueOf((int) R.string.disabled);
        Integer[] numArr = {valueOf, Integer.valueOf((int) R.string.enabled), valueOf2};
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        ScreenKt$preferenceScreen$impl$1 screenKt$preferenceScreen$impl$1 = new ScreenKt$preferenceScreen$impl$1(this, context, linearLayout);
        InputKt.category(screenKt$preferenceScreen$impl$1, R.string.general);
        MutablePropertyReference0Impl overrideSettingsDesign$screen$1$1 = new MutablePropertyReference0Impl(configurationOverride) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$1
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride) this.receiver).httpPort;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride) this.receiver).httpPort = (Integer) obj;
            }
        };
        Objects.requireNonNull(NullableTextAdapter.Companion);
        NullableTextAdapter<Integer> nullableTextAdapter = NullableTextAdapter.Companion.Port;
        InputKt.editableText$default(screenKt$preferenceScreen$impl$1, overrideSettingsDesign$screen$1$1, nullableTextAdapter, R.string.http_port, null, valueOf, valueOf2, null, 72);
        InputKt.editableText$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(configurationOverride) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$2
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride) this.receiver).socksPort;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride) this.receiver).socksPort = (Integer) obj;
            }
        }, nullableTextAdapter, R.string.socks_port, null, valueOf, valueOf2, null, 72);
        InputKt.editableText$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(configurationOverride) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$3
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride) this.receiver).redirectPort;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride) this.receiver).redirectPort = (Integer) obj;
            }
        }, nullableTextAdapter, R.string.redirect_port, null, valueOf, valueOf2, null, 72);
        InputKt.editableText$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(configurationOverride) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$4
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride) this.receiver).tproxyPort;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride) this.receiver).tproxyPort = (Integer) obj;
            }
        }, nullableTextAdapter, R.string.tproxy_port, null, valueOf, valueOf2, null, 72);
        InputKt.editableText$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(configurationOverride) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$5
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride) this.receiver).mixedPort;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride) this.receiver).mixedPort = (Integer) obj;
            }
        }, nullableTextAdapter, R.string.mixed_port, null, valueOf, valueOf2, null, 72);
        MutablePropertyReference0Impl overrideSettingsDesign$screen$1$6 = new MutablePropertyReference0Impl(configurationOverride) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$6
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride) this.receiver).authentication;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride) this.receiver).authentication = (List) obj;
            }
        };
        Objects.requireNonNull(TextAdapter.Companion);
        TextAdapter<String> textAdapter = TextAdapter.Companion.String;
        InputKt.editableTextList$default(screenKt$preferenceScreen$impl$1, overrideSettingsDesign$screen$1$6, textAdapter, R.string.authentication, null, valueOf, null, 40);
        InputKt.selectableList$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(configurationOverride) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$7
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride) this.receiver).allowLan;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride) this.receiver).allowLan = (Boolean) obj;
            }
        }, boolArr, numArr, R.string.allow_lan, null, null, 48);
        InputKt.selectableList$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(configurationOverride) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$8
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride) this.receiver).ipv6;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride) this.receiver).ipv6 = (Boolean) obj;
            }
        }, boolArr, numArr, R.string.ipv6, null, null, 48);
        MutablePropertyReference0Impl overrideSettingsDesign$screen$1$9 = new MutablePropertyReference0Impl(configurationOverride) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$9
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride) this.receiver).bindAddress;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride) this.receiver).bindAddress = (String) obj;
            }
        };
        NullableTextAdapter<String> nullableTextAdapter2 = NullableTextAdapter.Companion.String;
        InputKt.editableText$default(screenKt$preferenceScreen$impl$1, overrideSettingsDesign$screen$1$9, nullableTextAdapter2, R.string.bind_address, null, valueOf, Integer.valueOf((int) R.string.default_), null, 72);
        InputKt.selectableList$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(configurationOverride) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$10
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride) this.receiver).mode;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride) this.receiver).mode = (TunnelState.Mode) obj;
            }
        }, new TunnelState.Mode[]{null, TunnelState.Mode.Direct, TunnelState.Mode.Global, TunnelState.Mode.Rule}, new Integer[]{valueOf, Integer.valueOf((int) R.string.direct_mode), Integer.valueOf((int) R.string.global_mode), Integer.valueOf((int) R.string.rule_mode)}, R.string.mode, null, null, 48);
        InputKt.selectableList$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(configurationOverride) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$11
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride) this.receiver).logLevel;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride) this.receiver).logLevel = (LogMessage.Level) obj;
            }
        }, new LogMessage.Level[]{null, LogMessage.Level.Info, LogMessage.Level.Warning, LogMessage.Level.Error, LogMessage.Level.Debug, LogMessage.Level.Silent}, new Integer[]{valueOf, Integer.valueOf((int) R.string.info), Integer.valueOf((int) R.string.warning), Integer.valueOf((int) R.string.error), Integer.valueOf((int) R.string.debug), Integer.valueOf((int) R.string.silent)}, R.string.log_level, null, null, 48);
        InputKt.editableTextMap$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(configurationOverride) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$12
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride) this.receiver).hosts;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride) this.receiver).hosts = (Map) obj;
            }
        }, textAdapter, textAdapter, R.string.hosts, null, valueOf, null, 80);
        InputKt.clickable$default(screenKt$preferenceScreen$impl$1, R.string.sideload_geoip, null, Integer.valueOf((int) R.string.sideload_geoip_summary), new OverrideSettingsDesign$screen$1$13(this), 2);
        InputKt.category(screenKt$preferenceScreen$impl$1, R.string.dns);
        ArrayList arrayList = new ArrayList();
        final ConfigurationOverride.Dns dns = configurationOverride.dns;
        SelectableListPreference selectableList$default = InputKt.selectableList$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(dns) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$dns$1
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride.Dns) this.receiver).enable;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride.Dns) this.receiver).enable = (Boolean) obj;
            }
        }, new Boolean[]{null, Boolean.TRUE, Boolean.FALSE}, new Integer[]{valueOf, Integer.valueOf((int) R.string.force_enable), Integer.valueOf((int) R.string.use_built_in)}, R.string.strategy, null, new OverrideSettingsDesign$screen$1$dns$2(configurationOverride, arrayList), 16);
        final ConfigurationOverride.Dns dns2 = configurationOverride.dns;
        InputKt.editableText$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(dns2) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$14
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride.Dns) this.receiver).listen;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride.Dns) this.receiver).listen = (String) obj;
            }
        }, nullableTextAdapter2, R.string.listen, null, valueOf, valueOf2, new OverrideSettingsDesign$screen$1$15(arrayList), 8);
        final ConfigurationOverride.App app = configurationOverride.app;
        InputKt.selectableList$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(app) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$16
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride.App) this.receiver).appendSystemDns;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride.App) this.receiver).appendSystemDns = (Boolean) obj;
            }
        }, boolArr, numArr, R.string.append_system_dns, null, new OverrideSettingsDesign$screen$1$17(arrayList), 16);
        final ConfigurationOverride.Dns dns3 = configurationOverride.dns;
        InputKt.selectableList$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(dns3) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$18
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride.Dns) this.receiver).ipv6;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride.Dns) this.receiver).ipv6 = (Boolean) obj;
            }
        }, boolArr, numArr, R.string.ipv6, null, new OverrideSettingsDesign$screen$1$19(arrayList), 16);
        final ConfigurationOverride.Dns dns4 = configurationOverride.dns;
        InputKt.selectableList$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(dns4) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$20
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride.Dns) this.receiver).useHosts;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride.Dns) this.receiver).useHosts = (Boolean) obj;
            }
        }, boolArr, numArr, R.string.use_hosts, null, new OverrideSettingsDesign$screen$1$21(arrayList), 16);
        final ConfigurationOverride.Dns dns5 = configurationOverride.dns;
        InputKt.selectableList$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(dns5) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$22
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride.Dns) this.receiver).enhancedMode;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride.Dns) this.receiver).enhancedMode = (ConfigurationOverride.DnsEnhancedMode) obj;
            }
        }, new ConfigurationOverride.DnsEnhancedMode[]{null, ConfigurationOverride.DnsEnhancedMode.None, ConfigurationOverride.DnsEnhancedMode.FakeIp, ConfigurationOverride.DnsEnhancedMode.Mapping}, new Integer[]{valueOf, valueOf2, Integer.valueOf((int) R.string.fakeip), Integer.valueOf((int) R.string.mapping)}, R.string.enhanced_mode, null, new OverrideSettingsDesign$screen$1$23(arrayList), 16);
        final ConfigurationOverride.Dns dns6 = configurationOverride.dns;
        InputKt.editableTextList$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(dns6) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$24
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride.Dns) this.receiver).nameServer;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride.Dns) this.receiver).nameServer = (List) obj;
            }
        }, textAdapter, R.string.name_server, null, valueOf, new OverrideSettingsDesign$screen$1$25(arrayList), 8);
        final ConfigurationOverride.Dns dns7 = configurationOverride.dns;
        InputKt.editableTextList$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(dns7) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$26
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride.Dns) this.receiver).fallback;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride.Dns) this.receiver).fallback = (List) obj;
            }
        }, textAdapter, R.string.fallback, null, valueOf, new OverrideSettingsDesign$screen$1$27(arrayList), 8);
        final ConfigurationOverride.Dns dns8 = configurationOverride.dns;
        InputKt.editableTextList$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(dns8) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$28
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride.Dns) this.receiver).defaultServer;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride.Dns) this.receiver).defaultServer = (List) obj;
            }
        }, textAdapter, R.string.default_name_server, null, valueOf, new OverrideSettingsDesign$screen$1$29(arrayList), 8);
        final ConfigurationOverride.Dns dns9 = configurationOverride.dns;
        InputKt.editableTextList$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(dns9) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$30
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride.Dns) this.receiver).fakeIpFilter;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride.Dns) this.receiver).fakeIpFilter = (List) obj;
            }
        }, textAdapter, R.string.fakeip_filter, null, valueOf, new OverrideSettingsDesign$screen$1$31(arrayList), 8);
        final ConfigurationOverride.DnsFallbackFilter dnsFallbackFilter = configurationOverride.dns.fallbackFilter;
        InputKt.selectableList$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(dnsFallbackFilter) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$32
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride.DnsFallbackFilter) this.receiver).geoIp;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride.DnsFallbackFilter) this.receiver).geoIp = (Boolean) obj;
            }
        }, boolArr, numArr, R.string.geoip_fallback, null, new OverrideSettingsDesign$screen$1$33(arrayList), 16);
        final ConfigurationOverride.DnsFallbackFilter dnsFallbackFilter2 = configurationOverride.dns.fallbackFilter;
        InputKt.editableTextList$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(dnsFallbackFilter2) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$34
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride.DnsFallbackFilter) this.receiver).domain;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride.DnsFallbackFilter) this.receiver).domain = (List) obj;
            }
        }, textAdapter, R.string.domain_fallback, null, valueOf, new OverrideSettingsDesign$screen$1$35(arrayList), 8);
        final ConfigurationOverride.DnsFallbackFilter dnsFallbackFilter3 = configurationOverride.dns.fallbackFilter;
        InputKt.editableTextList$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(dnsFallbackFilter3) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$36
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride.DnsFallbackFilter) this.receiver).ipcidr;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride.DnsFallbackFilter) this.receiver).ipcidr = (List) obj;
            }
        }, textAdapter, R.string.ipcidr_fallback, null, valueOf, new OverrideSettingsDesign$screen$1$37(arrayList), 8);
        final ConfigurationOverride.Dns dns10 = configurationOverride.dns;
        InputKt.editableTextMap$default(screenKt$preferenceScreen$impl$1, new MutablePropertyReference0Impl(dns10) { // from class: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$38
            @Override // kotlin.reflect.KMutableProperty0
            public Object get() {
                return ((ConfigurationOverride.Dns) this.receiver).nameserverPolicy;
            }

            @Override // kotlin.reflect.KMutableProperty0
            public void set(Object obj) {
                ((ConfigurationOverride.Dns) this.receiver).nameserverPolicy = (Map) obj;
            }
        }, textAdapter, textAdapter, R.string.name_server_policy, null, valueOf, new OverrideSettingsDesign$screen$1$39(arrayList), 16);
        OnChangedListener onChangedListener = ((SelectableListKt$selectableList$impl$1) selectableList$default).listener;
        if (onChangedListener != null) {
            onChangedListener.onChanged();
        }
        Unit unit = Unit.INSTANCE;
        designSettingsOverideBinding.content.addView(screenKt$preferenceScreen$impl$1.$root);
    }

    @Override // com.tidalab.v2board.clash.design.Design
    public View getRoot() {
        return this.binding.mRoot;
    }
}
