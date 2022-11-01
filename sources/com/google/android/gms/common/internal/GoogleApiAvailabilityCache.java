package com.google.android.gms.common.internal;

import android.util.SparseIntArray;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import java.util.Objects;
/* loaded from: classes.dex */
public class GoogleApiAvailabilityCache {
    public final SparseIntArray zaos = new SparseIntArray();
    public GoogleApiAvailabilityLight zaot;

    public GoogleApiAvailabilityCache(GoogleApiAvailabilityLight googleApiAvailabilityLight) {
        Objects.requireNonNull(googleApiAvailabilityLight, "null reference");
        this.zaot = googleApiAvailabilityLight;
    }
}
