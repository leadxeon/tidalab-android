package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.react.R$style;
/* loaded from: classes.dex */
public final class zzb implements Parcelable.Creator<Status> {
    @Override // android.os.Parcelable.Creator
    public final Status createFromParcel(Parcel parcel) {
        int validateObjectHeader = R$style.validateObjectHeader(parcel);
        int i = 0;
        String str = null;
        PendingIntent pendingIntent = null;
        int i2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                i2 = R$style.readInt(parcel, readInt);
            } else if (i3 == 2) {
                str = R$style.createString(parcel, readInt);
            } else if (i3 == 3) {
                pendingIntent = (PendingIntent) R$style.createParcelable(parcel, readInt, PendingIntent.CREATOR);
            } else if (i3 != 1000) {
                R$style.skipUnknownField(parcel, readInt);
            } else {
                i = R$style.readInt(parcel, readInt);
            }
        }
        R$style.ensureAtEnd(parcel, validateObjectHeader);
        return new Status(i, i2, str, pendingIntent);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Status[] newArray(int i) {
        return new Status[i];
    }
}
