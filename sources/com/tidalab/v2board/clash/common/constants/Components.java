package com.tidalab.v2board.clash.common.constants;

import android.content.ComponentName;
import com.tidalab.v2board.clash.common.util.GlobalKt;
/* compiled from: Components.kt */
/* loaded from: classes.dex */
public final class Components {
    public static final Components INSTANCE = null;
    public static final ComponentName MAIN_ACTIVITY;
    public static final ComponentName PROPERTIES_ACTIVITY;

    static {
        String str = GlobalKt.packageName;
        MAIN_ACTIVITY = new ComponentName(str, "com.tidalab.v2board.clash.MainActivity");
        PROPERTIES_ACTIVITY = new ComponentName(str, "com.tidalab.v2board.clash.PropertiesActivity");
    }
}
