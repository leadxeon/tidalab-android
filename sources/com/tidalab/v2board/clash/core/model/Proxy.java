package com.tidalab.v2board.clash.core.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.core.util.Parcelizer$ParcelDecoder;
import com.tidalab.v2board.clash.core.util.Parcelizer$ParcelEncoder;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;
/* compiled from: Proxy.kt */
@Serializable
/* loaded from: classes.dex */
public final class Proxy implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    public final int delay;
    public final String name;
    public final String subtitle;
    public final String title;
    public final Type type;

    /* compiled from: Proxy.kt */
    /* loaded from: classes.dex */
    public static final class CREATOR implements Parcelable.Creator<Proxy> {
        public CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @Override // android.os.Parcelable.Creator
        public Proxy createFromParcel(Parcel parcel) {
            return (Proxy) Proxy$$serializer.INSTANCE.deserialize(new Parcelizer$ParcelDecoder(parcel));
        }

        @Override // android.os.Parcelable.Creator
        public Proxy[] newArray(int i) {
            return new Proxy[i];
        }
    }

    /* compiled from: Proxy.kt */
    /* loaded from: classes.dex */
    public enum Type {
        Direct(false),
        Reject(false),
        Shadowsocks(false),
        ShadowsocksR(false),
        Snell(false),
        Socks5(false),
        Http(false),
        Vmess(false),
        Trojan(false),
        Relay(true),
        Selector(true),
        Fallback(true),
        URLTest(true),
        LoadBalance(true),
        Unknown(false);
        
        public final boolean group;

        Type(boolean z) {
            this.group = z;
        }
    }

    public Proxy(int i, String str, String str2, String str3, Type type, int i2) {
        if (31 != (i & 31)) {
            Proxy$$serializer proxy$$serializer = Proxy$$serializer.INSTANCE;
            InputKt.throwMissingFieldException(i, 31, Proxy$$serializer.descriptor);
        }
        this.name = str;
        this.title = str2;
        this.subtitle = str3;
        this.type = type;
        this.delay = i2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Proxy)) {
            return false;
        }
        Proxy proxy = (Proxy) obj;
        return Intrinsics.areEqual(this.name, proxy.name) && Intrinsics.areEqual(this.title, proxy.title) && Intrinsics.areEqual(this.subtitle, proxy.subtitle) && this.type == proxy.type && this.delay == proxy.delay;
    }

    public int hashCode() {
        return ((this.type.hashCode() + GeneratedOutlineSupport.outline1(this.subtitle, GeneratedOutlineSupport.outline1(this.title, this.name.hashCode() * 31, 31), 31)) * 31) + this.delay;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Proxy(name=");
        outline13.append(this.name);
        outline13.append(", title=");
        outline13.append(this.title);
        outline13.append(", subtitle=");
        outline13.append(this.subtitle);
        outline13.append(", type=");
        outline13.append(this.type);
        outline13.append(", delay=");
        outline13.append(this.delay);
        outline13.append(')');
        return outline13.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Objects.requireNonNull(CREATOR);
        Proxy$$serializer.INSTANCE.serialize(new Parcelizer$ParcelEncoder(parcel), this);
    }
}
