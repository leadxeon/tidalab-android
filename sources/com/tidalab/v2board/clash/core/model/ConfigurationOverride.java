package com.tidalab.v2board.clash.core.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.core.model.LogMessage;
import com.tidalab.v2board.clash.core.model.TunnelState;
import com.tidalab.v2board.clash.core.util.Parcelizer$ParcelDecoder;
import com.tidalab.v2board.clash.core.util.Parcelizer$ParcelEncoder;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;
/* compiled from: ConfigurationOverride.kt */
@Serializable
/* loaded from: classes.dex */
public final class ConfigurationOverride implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    public Boolean allowLan;
    public final App app;
    public List<String> authentication;
    public String bindAddress;
    public final Dns dns;
    public Map<String, String> hosts;
    public Integer httpPort;
    public Boolean ipv6;
    public LogMessage.Level logLevel;
    public Integer mixedPort;
    public TunnelState.Mode mode;
    public Integer redirectPort;
    public Integer socksPort;
    public Integer tproxyPort;

    /* compiled from: ConfigurationOverride.kt */
    /* loaded from: classes.dex */
    public static final class CREATOR implements Parcelable.Creator<ConfigurationOverride> {
        public CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ConfigurationOverride createFromParcel(Parcel parcel) {
            return ConfigurationOverride$$serializer.INSTANCE.deserialize(new Parcelizer$ParcelDecoder(parcel));
        }

        @Override // android.os.Parcelable.Creator
        public ConfigurationOverride[] newArray(int i) {
            return new ConfigurationOverride[i];
        }
    }

    /* compiled from: ConfigurationOverride.kt */
    @Serializable
    /* loaded from: classes.dex */
    public enum DnsEnhancedMode {
        None,
        Mapping,
        FakeIp
    }

    public ConfigurationOverride() {
        this((Integer) null, (Integer) null, (Integer) null, (Integer) null, (Integer) null, (List) null, (Boolean) null, (String) null, (TunnelState.Mode) null, (LogMessage.Level) null, (Boolean) null, (Map) null, (Dns) null, (App) null, 16383);
    }

    public ConfigurationOverride(int i, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, List list, Boolean bool, String str, TunnelState.Mode mode, LogMessage.Level level, Boolean bool2, Map map, Dns dns, App app) {
        if ((i & 0) != 0) {
            ConfigurationOverride$$serializer configurationOverride$$serializer = ConfigurationOverride$$serializer.INSTANCE;
            InputKt.throwMissingFieldException(i, 0, ConfigurationOverride$$serializer.descriptor);
        }
        if ((i & 1) == 0) {
            this.httpPort = null;
        } else {
            this.httpPort = num;
        }
        if ((i & 2) == 0) {
            this.socksPort = null;
        } else {
            this.socksPort = num2;
        }
        if ((i & 4) == 0) {
            this.redirectPort = null;
        } else {
            this.redirectPort = num3;
        }
        if ((i & 8) == 0) {
            this.tproxyPort = null;
        } else {
            this.tproxyPort = num4;
        }
        if ((i & 16) == 0) {
            this.mixedPort = null;
        } else {
            this.mixedPort = num5;
        }
        if ((i & 32) == 0) {
            this.authentication = null;
        } else {
            this.authentication = list;
        }
        if ((i & 64) == 0) {
            this.allowLan = null;
        } else {
            this.allowLan = bool;
        }
        if ((i & 128) == 0) {
            this.bindAddress = null;
        } else {
            this.bindAddress = str;
        }
        if ((i & 256) == 0) {
            this.mode = null;
        } else {
            this.mode = mode;
        }
        if ((i & 512) == 0) {
            this.logLevel = null;
        } else {
            this.logLevel = level;
        }
        if ((i & 1024) == 0) {
            this.ipv6 = null;
        } else {
            this.ipv6 = bool2;
        }
        if ((i & 2048) == 0) {
            this.hosts = null;
        } else {
            this.hosts = map;
        }
        this.dns = (i & 4096) == 0 ? new Dns((Boolean) null, (String) null, (Boolean) null, (Boolean) null, (DnsEnhancedMode) null, (List) null, (List) null, (List) null, (List) null, (DnsFallbackFilter) null, (Map) null, 2047) : dns;
        this.app = (i & 8192) == 0 ? new App((Boolean) null, 1) : app;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ConfigurationOverride)) {
            return false;
        }
        ConfigurationOverride configurationOverride = (ConfigurationOverride) obj;
        return Intrinsics.areEqual(this.httpPort, configurationOverride.httpPort) && Intrinsics.areEqual(this.socksPort, configurationOverride.socksPort) && Intrinsics.areEqual(this.redirectPort, configurationOverride.redirectPort) && Intrinsics.areEqual(this.tproxyPort, configurationOverride.tproxyPort) && Intrinsics.areEqual(this.mixedPort, configurationOverride.mixedPort) && Intrinsics.areEqual(this.authentication, configurationOverride.authentication) && Intrinsics.areEqual(this.allowLan, configurationOverride.allowLan) && Intrinsics.areEqual(this.bindAddress, configurationOverride.bindAddress) && this.mode == configurationOverride.mode && this.logLevel == configurationOverride.logLevel && Intrinsics.areEqual(this.ipv6, configurationOverride.ipv6) && Intrinsics.areEqual(this.hosts, configurationOverride.hosts) && Intrinsics.areEqual(this.dns, configurationOverride.dns) && Intrinsics.areEqual(this.app, configurationOverride.app);
    }

    public int hashCode() {
        Integer num = this.httpPort;
        int i = 0;
        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.socksPort;
        int hashCode2 = (hashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.redirectPort;
        int hashCode3 = (hashCode2 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.tproxyPort;
        int hashCode4 = (hashCode3 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Integer num5 = this.mixedPort;
        int hashCode5 = (hashCode4 + (num5 == null ? 0 : num5.hashCode())) * 31;
        List<String> list = this.authentication;
        int hashCode6 = (hashCode5 + (list == null ? 0 : list.hashCode())) * 31;
        Boolean bool = this.allowLan;
        int hashCode7 = (hashCode6 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str = this.bindAddress;
        int hashCode8 = (hashCode7 + (str == null ? 0 : str.hashCode())) * 31;
        TunnelState.Mode mode = this.mode;
        int hashCode9 = (hashCode8 + (mode == null ? 0 : mode.hashCode())) * 31;
        LogMessage.Level level = this.logLevel;
        int hashCode10 = (hashCode9 + (level == null ? 0 : level.hashCode())) * 31;
        Boolean bool2 = this.ipv6;
        int hashCode11 = (hashCode10 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Map<String, String> map = this.hosts;
        if (map != null) {
            i = map.hashCode();
        }
        return this.app.hashCode() + ((this.dns.hashCode() + ((hashCode11 + i) * 31)) * 31);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("ConfigurationOverride(httpPort=");
        outline13.append(this.httpPort);
        outline13.append(", socksPort=");
        outline13.append(this.socksPort);
        outline13.append(", redirectPort=");
        outline13.append(this.redirectPort);
        outline13.append(", tproxyPort=");
        outline13.append(this.tproxyPort);
        outline13.append(", mixedPort=");
        outline13.append(this.mixedPort);
        outline13.append(", authentication=");
        outline13.append(this.authentication);
        outline13.append(", allowLan=");
        outline13.append(this.allowLan);
        outline13.append(", bindAddress=");
        outline13.append((Object) this.bindAddress);
        outline13.append(", mode=");
        outline13.append(this.mode);
        outline13.append(", logLevel=");
        outline13.append(this.logLevel);
        outline13.append(", ipv6=");
        outline13.append(this.ipv6);
        outline13.append(", hosts=");
        outline13.append(this.hosts);
        outline13.append(", dns=");
        outline13.append(this.dns);
        outline13.append(", app=");
        outline13.append(this.app);
        outline13.append(')');
        return outline13.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Objects.requireNonNull(CREATOR);
        ConfigurationOverride$$serializer.INSTANCE.serialize(new Parcelizer$ParcelEncoder(parcel), this);
    }

    /* compiled from: ConfigurationOverride.kt */
    @Serializable
    /* loaded from: classes.dex */
    public static final class App {
        public Boolean appendSystemDns;

        public App() {
            this((Boolean) null, 1);
        }

        public App(int i, Boolean bool) {
            if ((i & 0) != 0) {
                ConfigurationOverride$App$$serializer configurationOverride$App$$serializer = ConfigurationOverride$App$$serializer.INSTANCE;
                InputKt.throwMissingFieldException(i, 0, ConfigurationOverride$App$$serializer.descriptor);
            }
            if ((i & 1) == 0) {
                this.appendSystemDns = null;
            } else {
                this.appendSystemDns = bool;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof App) && Intrinsics.areEqual(this.appendSystemDns, ((App) obj).appendSystemDns);
        }

        public int hashCode() {
            Boolean bool = this.appendSystemDns;
            if (bool == null) {
                return 0;
            }
            return bool.hashCode();
        }

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("App(appendSystemDns=");
            outline13.append(this.appendSystemDns);
            outline13.append(')');
            return outline13.toString();
        }

        public App(Boolean bool, int i) {
            int i2 = i & 1;
            this.appendSystemDns = null;
        }
    }

    /* compiled from: ConfigurationOverride.kt */
    @Serializable
    /* loaded from: classes.dex */
    public static final class DnsFallbackFilter {
        public List<String> domain;
        public Boolean geoIp;
        public List<String> ipcidr;

        public DnsFallbackFilter() {
            this((Boolean) null, (List) null, (List) null, 7);
        }

        public DnsFallbackFilter(int i, Boolean bool, List list, List list2) {
            if ((i & 0) != 0) {
                ConfigurationOverride$DnsFallbackFilter$$serializer configurationOverride$DnsFallbackFilter$$serializer = ConfigurationOverride$DnsFallbackFilter$$serializer.INSTANCE;
                InputKt.throwMissingFieldException(i, 0, ConfigurationOverride$DnsFallbackFilter$$serializer.descriptor);
            }
            if ((i & 1) == 0) {
                this.geoIp = null;
            } else {
                this.geoIp = bool;
            }
            if ((i & 2) == 0) {
                this.ipcidr = null;
            } else {
                this.ipcidr = list;
            }
            if ((i & 4) == 0) {
                this.domain = null;
            } else {
                this.domain = list2;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DnsFallbackFilter)) {
                return false;
            }
            DnsFallbackFilter dnsFallbackFilter = (DnsFallbackFilter) obj;
            return Intrinsics.areEqual(this.geoIp, dnsFallbackFilter.geoIp) && Intrinsics.areEqual(this.ipcidr, dnsFallbackFilter.ipcidr) && Intrinsics.areEqual(this.domain, dnsFallbackFilter.domain);
        }

        public int hashCode() {
            Boolean bool = this.geoIp;
            int i = 0;
            int hashCode = (bool == null ? 0 : bool.hashCode()) * 31;
            List<String> list = this.ipcidr;
            int hashCode2 = (hashCode + (list == null ? 0 : list.hashCode())) * 31;
            List<String> list2 = this.domain;
            if (list2 != null) {
                i = list2.hashCode();
            }
            return hashCode2 + i;
        }

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("DnsFallbackFilter(geoIp=");
            outline13.append(this.geoIp);
            outline13.append(", ipcidr=");
            outline13.append(this.ipcidr);
            outline13.append(", domain=");
            outline13.append(this.domain);
            outline13.append(')');
            return outline13.toString();
        }

        public DnsFallbackFilter(Boolean bool, List list, List list2, int i) {
            int i2 = i & 1;
            int i3 = i & 2;
            int i4 = i & 4;
            this.geoIp = null;
            this.ipcidr = null;
            this.domain = null;
        }
    }

    /* compiled from: ConfigurationOverride.kt */
    @Serializable
    /* loaded from: classes.dex */
    public static final class Dns {
        public List<String> defaultServer;
        public Boolean enable;
        public DnsEnhancedMode enhancedMode;
        public List<String> fakeIpFilter;
        public List<String> fallback;
        public final DnsFallbackFilter fallbackFilter;
        public Boolean ipv6;
        public String listen;
        public List<String> nameServer;
        public Map<String, String> nameserverPolicy;
        public Boolean useHosts;

        public Dns() {
            this((Boolean) null, (String) null, (Boolean) null, (Boolean) null, (DnsEnhancedMode) null, (List) null, (List) null, (List) null, (List) null, (DnsFallbackFilter) null, (Map) null, 2047);
        }

        public Dns(int i, Boolean bool, String str, Boolean bool2, Boolean bool3, DnsEnhancedMode dnsEnhancedMode, List list, List list2, List list3, List list4, DnsFallbackFilter dnsFallbackFilter, Map map) {
            if ((i & 0) != 0) {
                ConfigurationOverride$Dns$$serializer configurationOverride$Dns$$serializer = ConfigurationOverride$Dns$$serializer.INSTANCE;
                InputKt.throwMissingFieldException(i, 0, ConfigurationOverride$Dns$$serializer.descriptor);
            }
            if ((i & 1) == 0) {
                this.enable = null;
            } else {
                this.enable = bool;
            }
            if ((i & 2) == 0) {
                this.listen = null;
            } else {
                this.listen = str;
            }
            if ((i & 4) == 0) {
                this.ipv6 = null;
            } else {
                this.ipv6 = bool2;
            }
            if ((i & 8) == 0) {
                this.useHosts = null;
            } else {
                this.useHosts = bool3;
            }
            if ((i & 16) == 0) {
                this.enhancedMode = null;
            } else {
                this.enhancedMode = dnsEnhancedMode;
            }
            if ((i & 32) == 0) {
                this.nameServer = null;
            } else {
                this.nameServer = list;
            }
            if ((i & 64) == 0) {
                this.fallback = null;
            } else {
                this.fallback = list2;
            }
            if ((i & 128) == 0) {
                this.defaultServer = null;
            } else {
                this.defaultServer = list3;
            }
            if ((i & 256) == 0) {
                this.fakeIpFilter = null;
            } else {
                this.fakeIpFilter = list4;
            }
            if ((i & 512) == 0) {
                this.fallbackFilter = new DnsFallbackFilter((Boolean) null, (List) null, (List) null, 7);
            } else {
                this.fallbackFilter = dnsFallbackFilter;
            }
            if ((i & 1024) == 0) {
                this.nameserverPolicy = null;
            } else {
                this.nameserverPolicy = map;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Dns)) {
                return false;
            }
            Dns dns = (Dns) obj;
            return Intrinsics.areEqual(this.enable, dns.enable) && Intrinsics.areEqual(this.listen, dns.listen) && Intrinsics.areEqual(this.ipv6, dns.ipv6) && Intrinsics.areEqual(this.useHosts, dns.useHosts) && this.enhancedMode == dns.enhancedMode && Intrinsics.areEqual(this.nameServer, dns.nameServer) && Intrinsics.areEqual(this.fallback, dns.fallback) && Intrinsics.areEqual(this.defaultServer, dns.defaultServer) && Intrinsics.areEqual(this.fakeIpFilter, dns.fakeIpFilter) && Intrinsics.areEqual(this.fallbackFilter, dns.fallbackFilter) && Intrinsics.areEqual(this.nameserverPolicy, dns.nameserverPolicy);
        }

        public int hashCode() {
            Boolean bool = this.enable;
            int i = 0;
            int hashCode = (bool == null ? 0 : bool.hashCode()) * 31;
            String str = this.listen;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            Boolean bool2 = this.ipv6;
            int hashCode3 = (hashCode2 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
            Boolean bool3 = this.useHosts;
            int hashCode4 = (hashCode3 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
            DnsEnhancedMode dnsEnhancedMode = this.enhancedMode;
            int hashCode5 = (hashCode4 + (dnsEnhancedMode == null ? 0 : dnsEnhancedMode.hashCode())) * 31;
            List<String> list = this.nameServer;
            int hashCode6 = (hashCode5 + (list == null ? 0 : list.hashCode())) * 31;
            List<String> list2 = this.fallback;
            int hashCode7 = (hashCode6 + (list2 == null ? 0 : list2.hashCode())) * 31;
            List<String> list3 = this.defaultServer;
            int hashCode8 = (hashCode7 + (list3 == null ? 0 : list3.hashCode())) * 31;
            List<String> list4 = this.fakeIpFilter;
            int hashCode9 = (this.fallbackFilter.hashCode() + ((hashCode8 + (list4 == null ? 0 : list4.hashCode())) * 31)) * 31;
            Map<String, String> map = this.nameserverPolicy;
            if (map != null) {
                i = map.hashCode();
            }
            return hashCode9 + i;
        }

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Dns(enable=");
            outline13.append(this.enable);
            outline13.append(", listen=");
            outline13.append((Object) this.listen);
            outline13.append(", ipv6=");
            outline13.append(this.ipv6);
            outline13.append(", useHosts=");
            outline13.append(this.useHosts);
            outline13.append(", enhancedMode=");
            outline13.append(this.enhancedMode);
            outline13.append(", nameServer=");
            outline13.append(this.nameServer);
            outline13.append(", fallback=");
            outline13.append(this.fallback);
            outline13.append(", defaultServer=");
            outline13.append(this.defaultServer);
            outline13.append(", fakeIpFilter=");
            outline13.append(this.fakeIpFilter);
            outline13.append(", fallbackFilter=");
            outline13.append(this.fallbackFilter);
            outline13.append(", nameserverPolicy=");
            outline13.append(this.nameserverPolicy);
            outline13.append(')');
            return outline13.toString();
        }

        public Dns(Boolean bool, String str, Boolean bool2, Boolean bool3, DnsEnhancedMode dnsEnhancedMode, List list, List list2, List list3, List list4, DnsFallbackFilter dnsFallbackFilter, Map map, int i) {
            int i2 = i & 1;
            int i3 = i & 2;
            int i4 = i & 4;
            int i5 = i & 8;
            int i6 = i & 16;
            int i7 = i & 32;
            int i8 = i & 64;
            int i9 = i & 128;
            int i10 = i & 256;
            DnsFallbackFilter dnsFallbackFilter2 = (i & 512) != 0 ? new DnsFallbackFilter((Boolean) null, (List) null, (List) null, 7) : null;
            int i11 = i & 1024;
            this.enable = null;
            this.listen = null;
            this.ipv6 = null;
            this.useHosts = null;
            this.enhancedMode = null;
            this.nameServer = null;
            this.fallback = null;
            this.defaultServer = null;
            this.fakeIpFilter = null;
            this.fallbackFilter = dnsFallbackFilter2;
            this.nameserverPolicy = null;
        }
    }

    public ConfigurationOverride(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, List list, Boolean bool, String str, TunnelState.Mode mode, LogMessage.Level level, Boolean bool2, Map map, Dns dns, App app, int i) {
        int i2 = i & 1;
        int i3 = i & 2;
        int i4 = i & 4;
        int i5 = i & 8;
        int i6 = i & 16;
        int i7 = i & 32;
        int i8 = i & 64;
        int i9 = i & 128;
        int i10 = i & 256;
        int i11 = i & 512;
        int i12 = i & 1024;
        int i13 = i & 2048;
        Dns dns2 = (i & 4096) != 0 ? new Dns((Boolean) null, (String) null, (Boolean) null, (Boolean) null, (DnsEnhancedMode) null, (List) null, (List) null, (List) null, (List) null, (DnsFallbackFilter) null, (Map) null, 2047) : null;
        App app2 = (i & 8192) != 0 ? new App((Boolean) null, 1) : null;
        this.httpPort = null;
        this.socksPort = null;
        this.redirectPort = null;
        this.tproxyPort = null;
        this.mixedPort = null;
        this.authentication = null;
        this.allowLan = null;
        this.bindAddress = null;
        this.mode = null;
        this.logLevel = null;
        this.ipv6 = null;
        this.hosts = null;
        this.dns = dns2;
        this.app = app2;
    }
}
