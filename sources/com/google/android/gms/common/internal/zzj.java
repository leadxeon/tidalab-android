package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.zze;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.common.zzb;
import com.google.android.gms.internal.common.zzc;
/* loaded from: classes.dex */
public abstract class zzj extends zzb implements zzi {
    public zzj() {
        super("com.google.android.gms.common.internal.ICertData");
    }

    @Override // com.google.android.gms.internal.common.zzb
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            ObjectWrapper objectWrapper = new ObjectWrapper(((zze) this).getBytes());
            parcel2.writeNoException();
            int i3 = zzc.$r8$clinit;
            parcel2.writeStrongBinder(objectWrapper);
        } else if (i != 2) {
            return false;
        } else {
            int i4 = ((zze) this).zzt;
            parcel2.writeNoException();
            parcel2.writeInt(i4);
        }
        return true;
    }
}
