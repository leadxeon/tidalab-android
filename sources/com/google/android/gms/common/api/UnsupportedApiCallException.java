package com.google.android.gms.common.api;

import com.google.android.gms.common.Feature;
/* loaded from: classes.dex */
public final class UnsupportedApiCallException extends UnsupportedOperationException {
    public final Feature zzas;

    public UnsupportedApiCallException(Feature feature) {
        this.zzas = feature;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        String valueOf = String.valueOf(this.zzas);
        StringBuilder sb = new StringBuilder(valueOf.length() + 8);
        sb.append("Missing ");
        sb.append(valueOf);
        return sb.toString();
    }
}
