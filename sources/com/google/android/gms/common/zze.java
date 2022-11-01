package com.google.android.gms.common;

import android.os.RemoteException;
import android.util.Log;
import com.facebook.react.R$style;
import com.google.android.gms.common.internal.zzi;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
/* loaded from: classes.dex */
public abstract class zze extends zzj {
    public int zzt;

    public zze(byte[] bArr) {
        R$style.checkArgument(bArr.length == 25);
        this.zzt = Arrays.hashCode(bArr);
    }

    public static byte[] zza(String str) {
        try {
            return str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public boolean equals(Object obj) {
        IObjectWrapper zzb;
        if (obj != null && (obj instanceof zzi)) {
            try {
                zzi zziVar = (zzi) obj;
                if (zziVar.zzc() == this.zzt && (zzb = zziVar.zzb()) != null) {
                    return Arrays.equals(getBytes(), (byte[]) ObjectWrapper.unwrap(zzb));
                }
                return false;
            } catch (RemoteException e) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
            }
        }
        return false;
    }

    public abstract byte[] getBytes();

    public int hashCode() {
        return this.zzt;
    }

    @Override // com.google.android.gms.common.internal.zzi
    public final IObjectWrapper zzb() {
        return new ObjectWrapper(getBytes());
    }

    @Override // com.google.android.gms.common.internal.zzi
    public final int zzc() {
        return this.zzt;
    }
}
