package com.google.android.gms.common.wrappers;

import android.content.Context;
/* loaded from: classes.dex */
public class Wrappers {
    public static Wrappers zzhz = new Wrappers();
    public PackageManagerWrapper zzhy = null;

    public static PackageManagerWrapper packageManager(Context context) {
        PackageManagerWrapper packageManagerWrapper;
        Wrappers wrappers = zzhz;
        synchronized (wrappers) {
            if (wrappers.zzhy == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                wrappers.zzhy = new PackageManagerWrapper(context);
            }
            packageManagerWrapper = wrappers.zzhy;
        }
        return packageManagerWrapper;
    }
}
