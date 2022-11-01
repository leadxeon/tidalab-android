package com.tidalab.v2board.clash.common.constants;

import com.tidalab.v2board.clash.common.util.GlobalKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Authorities.kt */
/* loaded from: classes.dex */
public final class Authorities {
    public static final String FILES_PROVIDER;
    public static final Authorities INSTANCE = null;
    public static final String SETTINGS_PROVIDER;
    public static final String STATUS_PROVIDER;

    static {
        String str = GlobalKt.packageName;
        STATUS_PROVIDER = Intrinsics.stringPlus(str, ".status");
        SETTINGS_PROVIDER = Intrinsics.stringPlus(str, ".settings");
        FILES_PROVIDER = Intrinsics.stringPlus(str, ".files");
    }
}
