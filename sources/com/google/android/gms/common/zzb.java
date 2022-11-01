package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.react.R$style;
/* loaded from: classes.dex */
public final class zzb implements Parcelable.Creator<Feature> {
    @Override // android.os.Parcelable.Creator
    public final Feature createFromParcel(Parcel parcel) {
        int validateObjectHeader = R$style.validateObjectHeader(parcel);
        String str = null;
        int i = 0;
        long j = -1;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                str = R$style.createString(parcel, readInt);
            } else if (i2 == 2) {
                i = R$style.readInt(parcel, readInt);
            } else if (i2 != 3) {
                R$style.skipUnknownField(parcel, readInt);
            } else {
                R$style.zza(parcel, readInt, 8);
                j = parcel.readLong();
            }
        }
        R$style.ensureAtEnd(parcel, validateObjectHeader);
        return new Feature(str, i, j);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Feature[] newArray(int i) {
        return new Feature[i];
    }
}
