package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.util.Log;
/* loaded from: classes.dex */
public class GoogleSignatureVerifier {
    public static GoogleSignatureVerifier zzam;
    public final Context mContext;

    public GoogleSignatureVerifier(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static boolean zza(PackageInfo packageInfo, boolean z) {
        if (!(packageInfo == null || packageInfo.signatures == null)) {
            if ((z ? zza(packageInfo, zzh.zzx) : zza(packageInfo, zzh.zzx[0])) != null) {
                return true;
            }
        }
        return false;
    }

    public static zze zza(PackageInfo packageInfo, zze... zzeVarArr) {
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr == null) {
            return null;
        }
        if (signatureArr.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        zzf zzfVar = new zzf(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < zzeVarArr.length; i++) {
            if (zzeVarArr[i].equals(zzfVar)) {
                return zzeVarArr[i];
            }
        }
        return null;
    }
}
