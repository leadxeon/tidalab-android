package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.react.R$style;
import com.google.android.gms.common.internal.Objects$ToStringHelper;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
/* loaded from: classes.dex */
public class Feature extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Feature> CREATOR = new zzb();
    public final String name;
    @Deprecated
    public final int zzk;
    public final long zzl;

    public Feature(String str, int i, long j) {
        this.name = str;
        this.zzk = i;
        this.zzl = j;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Feature) {
            Feature feature = (Feature) obj;
            String str = this.name;
            if (((str != null && str.equals(feature.name)) || (this.name == null && feature.name == null)) && getVersion() == feature.getVersion()) {
                return true;
            }
        }
        return false;
    }

    public long getVersion() {
        long j = this.zzl;
        return j == -1 ? this.zzk : j;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.name, Long.valueOf(getVersion())});
    }

    public String toString() {
        Objects$ToStringHelper objects$ToStringHelper = new Objects$ToStringHelper(this, null);
        objects$ToStringHelper.add("name", this.name);
        objects$ToStringHelper.add("version", Long.valueOf(getVersion()));
        return objects$ToStringHelper.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int zza = R$style.zza(parcel, 20293);
        R$style.writeString(parcel, 1, this.name, false);
        int i2 = this.zzk;
        R$style.zzb(parcel, 2, 4);
        parcel.writeInt(i2);
        long version = getVersion();
        R$style.zzb(parcel, 3, 8);
        parcel.writeLong(version);
        R$style.zzb(parcel, zza);
    }
}
