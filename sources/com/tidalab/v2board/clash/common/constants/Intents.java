package com.tidalab.v2board.clash.common.constants;

import com.tidalab.v2board.clash.common.util.GlobalKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Intents.kt */
/* loaded from: classes.dex */
public final class Intents {
    public static final String ACTION_CLASH_REQUEST_STOP;
    public static final String ACTION_CLASH_STARTED;
    public static final String ACTION_CLASH_STOPPED;
    public static final String ACTION_OVERRIDE_CHANGED;
    public static final String ACTION_PROFILE_CHANGED;
    public static final String ACTION_PROFILE_LOADED;
    public static final String ACTION_PROFILE_REQUEST_UPDATE;
    public static final String ACTION_PROFILE_SCHEDULE_UPDATES;
    public static final String ACTION_PROVIDE_URL;
    public static final String ACTION_SERVICE_RECREATED;
    public static final Intents INSTANCE = null;

    static {
        String str = GlobalKt.packageName;
        ACTION_PROVIDE_URL = Intrinsics.stringPlus(str, ".action.PROVIDE_URL");
        ACTION_SERVICE_RECREATED = Intrinsics.stringPlus(str, ".intent.action.CLASH_RECREATED");
        ACTION_CLASH_STARTED = Intrinsics.stringPlus(str, ".intent.action.CLASH_STARTED");
        ACTION_CLASH_STOPPED = Intrinsics.stringPlus(str, ".intent.action.CLASH_STOPPED");
        ACTION_CLASH_REQUEST_STOP = Intrinsics.stringPlus(str, ".intent.action.CLASH_REQUEST_STOP");
        ACTION_PROFILE_CHANGED = Intrinsics.stringPlus(str, ".intent.action.PROFILE_CHANGED");
        ACTION_PROFILE_REQUEST_UPDATE = Intrinsics.stringPlus(str, ".intent.action.REQUEST_UPDATE");
        ACTION_PROFILE_SCHEDULE_UPDATES = Intrinsics.stringPlus(str, ".intent.action.SCHEDULE_UPDATES");
        ACTION_PROFILE_LOADED = Intrinsics.stringPlus(str, ".intent.action.PROFILE_LOADED");
        ACTION_OVERRIDE_CHANGED = Intrinsics.stringPlus(str, ".intent.action.OVERRIDE_CHANGED");
    }
}
