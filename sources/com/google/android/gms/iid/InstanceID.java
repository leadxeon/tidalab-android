package com.google.android.gms.iid;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
@Deprecated
/* loaded from: classes.dex */
public class InstanceID {
    static {
        zzac zzacVar;
        synchronized (zzac.class) {
            if (zzac.zzdd == null) {
                zzac.zzdd = new zzac();
            }
            zzacVar = zzac.zzdd;
        }
        Objects.requireNonNull(zzacVar);
        TimeUnit.DAYS.toMillis(7L);
    }
}
