package com.tidalab.v2board.clash.core.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.core.util.Parcelizer$ParcelDecoder;
import com.tidalab.v2board.clash.core.util.Parcelizer$ParcelEncoder;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Objects;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;
/* compiled from: Provider.kt */
@Serializable
/* loaded from: classes.dex */
public final class Provider implements Parcelable, Comparable<Provider> {
    public static final CREATOR CREATOR = new CREATOR(null);
    public final String name;
    public final Type type;
    public final long updatedAt;
    public final VehicleType vehicleType;

    /* compiled from: Provider.kt */
    /* loaded from: classes.dex */
    public static final class CREATOR implements Parcelable.Creator<Provider> {
        public CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @Override // android.os.Parcelable.Creator
        public Provider createFromParcel(Parcel parcel) {
            return (Provider) Provider$$serializer.INSTANCE.deserialize(new Parcelizer$ParcelDecoder(parcel));
        }

        @Override // android.os.Parcelable.Creator
        public Provider[] newArray(int i) {
            return new Provider[i];
        }
    }

    /* compiled from: Provider.kt */
    /* loaded from: classes.dex */
    public enum Type {
        Proxy,
        Rule
    }

    /* compiled from: Provider.kt */
    /* loaded from: classes.dex */
    public enum VehicleType {
        HTTP,
        File,
        Compatible
    }

    public Provider(int i, String str, Type type, VehicleType vehicleType, long j) {
        if (15 == (i & 15)) {
            this.name = str;
            this.type = type;
            this.vehicleType = vehicleType;
            this.updatedAt = j;
            return;
        }
        Provider$$serializer provider$$serializer = Provider$$serializer.INSTANCE;
        InputKt.throwMissingFieldException(i, 15, Provider$$serializer.descriptor);
        throw null;
    }

    @Override // java.lang.Comparable
    public int compareTo(Provider provider) {
        return InputKt.compareValuesByImpl$ComparisonsKt__ComparisonsKt(this, provider, new Function1[]{Provider$compareTo$1.INSTANCE, Provider$compareTo$2.INSTANCE});
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Provider)) {
            return false;
        }
        Provider provider = (Provider) obj;
        return Intrinsics.areEqual(this.name, provider.name) && this.type == provider.type && this.vehicleType == provider.vehicleType && this.updatedAt == provider.updatedAt;
    }

    public int hashCode() {
        int hashCode = this.type.hashCode();
        int hashCode2 = this.vehicleType.hashCode();
        return Provider$$ExternalSynthetic0.m0(this.updatedAt) + ((hashCode2 + ((hashCode + (this.name.hashCode() * 31)) * 31)) * 31);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Provider(name=");
        outline13.append(this.name);
        outline13.append(", type=");
        outline13.append(this.type);
        outline13.append(", vehicleType=");
        outline13.append(this.vehicleType);
        outline13.append(", updatedAt=");
        outline13.append(this.updatedAt);
        outline13.append(')');
        return outline13.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Objects.requireNonNull(CREATOR);
        Provider$$serializer.INSTANCE.serialize(new Parcelizer$ParcelEncoder(parcel), this);
    }
}
