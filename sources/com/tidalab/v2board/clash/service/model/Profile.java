package com.tidalab.v2board.clash.service.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.core.model.Provider$$ExternalSynthetic0;
import com.tidalab.v2board.clash.core.util.Parcelizer$ParcelDecoder;
import com.tidalab.v2board.clash.core.util.Parcelizer$ParcelEncoder;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Objects;
import java.util.UUID;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;
/* compiled from: Profile.kt */
@Serializable
/* loaded from: classes.dex */
public final class Profile implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    public final boolean active;
    public final boolean imported;
    public final long interval;
    public final String name;
    public final boolean pending;
    public final String source;
    public final Type type;
    public final long updatedAt;
    public final UUID uuid;

    /* compiled from: Profile.kt */
    /* loaded from: classes.dex */
    public static final class CREATOR implements Parcelable.Creator<Profile> {
        public CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Profile createFromParcel(Parcel parcel) {
            return (Profile) Profile$$serializer.INSTANCE.deserialize(new Parcelizer$ParcelDecoder(parcel));
        }

        @Override // android.os.Parcelable.Creator
        public Profile[] newArray(int i) {
            return new Profile[i];
        }
    }

    /* compiled from: Profile.kt */
    /* loaded from: classes.dex */
    public enum Type {
        File,
        Url,
        External
    }

    public Profile(int i, UUID uuid, String str, Type type, String str2, boolean z, long j, long j2, boolean z2, boolean z3) {
        if (511 != (i & 511)) {
            Profile$$serializer profile$$serializer = Profile$$serializer.INSTANCE;
            InputKt.throwMissingFieldException(i, 511, Profile$$serializer.descriptor);
        }
        this.uuid = uuid;
        this.name = str;
        this.type = type;
        this.source = str2;
        this.active = z;
        this.interval = j;
        this.updatedAt = j2;
        this.imported = z2;
        this.pending = z3;
    }

    public static Profile copy$default(Profile profile, UUID uuid, String str, Type type, String str2, boolean z, long j, long j2, boolean z2, boolean z3, int i) {
        Type type2 = null;
        UUID uuid2 = (i & 1) != 0 ? profile.uuid : null;
        String str3 = (i & 2) != 0 ? profile.name : str;
        if ((i & 4) != 0) {
            type2 = profile.type;
        }
        return new Profile(uuid2, str3, type2, (i & 8) != 0 ? profile.source : str2, (i & 16) != 0 ? profile.active : z, (i & 32) != 0 ? profile.interval : j, (i & 64) != 0 ? profile.updatedAt : j2, (i & 128) != 0 ? profile.imported : z2, (i & 256) != 0 ? profile.pending : z3);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Profile)) {
            return false;
        }
        Profile profile = (Profile) obj;
        return Intrinsics.areEqual(this.uuid, profile.uuid) && Intrinsics.areEqual(this.name, profile.name) && this.type == profile.type && Intrinsics.areEqual(this.source, profile.source) && this.active == profile.active && this.interval == profile.interval && this.updatedAt == profile.updatedAt && this.imported == profile.imported && this.pending == profile.pending;
    }

    public int hashCode() {
        int outline1 = GeneratedOutlineSupport.outline1(this.name, this.uuid.hashCode() * 31, 31);
        int outline12 = GeneratedOutlineSupport.outline1(this.source, (this.type.hashCode() + outline1) * 31, 31);
        boolean z = this.active;
        int i = 1;
        if (z) {
            z = true;
        }
        int i2 = z ? 1 : 0;
        int i3 = z ? 1 : 0;
        int i4 = z ? 1 : 0;
        int m0 = Provider$$ExternalSynthetic0.m0(this.interval);
        int m02 = (Provider$$ExternalSynthetic0.m0(this.updatedAt) + ((m0 + ((outline12 + i2) * 31)) * 31)) * 31;
        boolean z2 = this.imported;
        if (z2) {
            z2 = true;
        }
        int i5 = z2 ? 1 : 0;
        int i6 = z2 ? 1 : 0;
        int i7 = z2 ? 1 : 0;
        int i8 = (m02 + i5) * 31;
        boolean z3 = this.pending;
        if (!z3) {
            i = z3 ? 1 : 0;
        }
        return i8 + i;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Profile(uuid=");
        outline13.append(this.uuid);
        outline13.append(", name=");
        outline13.append(this.name);
        outline13.append(", type=");
        outline13.append(this.type);
        outline13.append(", source=");
        outline13.append(this.source);
        outline13.append(", active=");
        outline13.append(this.active);
        outline13.append(", interval=");
        outline13.append(this.interval);
        outline13.append(", updatedAt=");
        outline13.append(this.updatedAt);
        outline13.append(", imported=");
        outline13.append(this.imported);
        outline13.append(", pending=");
        outline13.append(this.pending);
        outline13.append(')');
        return outline13.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Objects.requireNonNull(CREATOR);
        Profile$$serializer.INSTANCE.serialize(new Parcelizer$ParcelEncoder(parcel), this);
    }

    public Profile(UUID uuid, String str, Type type, String str2, boolean z, long j, long j2, boolean z2, boolean z3) {
        this.uuid = uuid;
        this.name = str;
        this.type = type;
        this.source = str2;
        this.active = z;
        this.interval = j;
        this.updatedAt = j2;
        this.imported = z2;
        this.pending = z3;
    }
}
