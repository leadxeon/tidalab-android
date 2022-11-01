package com.google.android.gms.common;

import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public abstract class zzg extends zze {
    public static final WeakReference<byte[]> zzw = new WeakReference<>(null);
    public WeakReference<byte[]> zzv = zzw;

    public zzg(byte[] bArr) {
        super(bArr);
    }

    @Override // com.google.android.gms.common.zze
    public final byte[] getBytes() {
        byte[] bArr;
        synchronized (this) {
            bArr = this.zzv.get();
            if (bArr == null) {
                bArr = zzd();
                this.zzv = new WeakReference<>(bArr);
            }
        }
        return bArr;
    }

    public abstract byte[] zzd();
}
