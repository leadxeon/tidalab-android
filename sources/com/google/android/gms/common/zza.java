package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.react.R$style;
/* loaded from: classes.dex */
public final class zza implements Parcelable.Creator<ConnectionResult> {
    @Override // android.os.Parcelable.Creator
    public final ConnectionResult createFromParcel(Parcel parcel) {
        int validateObjectHeader = R$style.validateObjectHeader(parcel);
        int i = 0;
        PendingIntent pendingIntent = null;
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                i = R$style.readInt(parcel, readInt);
            } else if (i3 == 2) {
                i2 = R$style.readInt(parcel, readInt);
            } else if (i3 == 3) {
                pendingIntent = (PendingIntent) R$style.createParcelable(parcel, readInt, PendingIntent.CREATOR);
            } else if (i3 != 4) {
                R$style.skipUnknownField(parcel, readInt);
            } else {
                str = R$style.createString(parcel, readInt);
            }
        }
        R$style.ensureAtEnd(parcel, validateObjectHeader);
        return new ConnectionResult(i, i2, pendingIntent, str);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ConnectionResult[] newArray(int i) {
        return new ConnectionResult[i];
    }
}
