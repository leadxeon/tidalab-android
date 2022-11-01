package com.tidalab.v2board.clash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tidalab.v2board.clash.common.Global;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RestartReceiver.kt */
/* loaded from: classes.dex */
public final class RestartReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ((Intrinsics.areEqual(action, "android.intent.action.BOOT_COMPLETED") ? true : Intrinsics.areEqual(action, "android.intent.action.MY_PACKAGE_REPLACED")) && FilesKt__UtilsKt.resolve(Global.INSTANCE.getApplication().getFilesDir(), "service_running.lock").exists()) {
            InputKt.startClashService(context);
        }
    }
}
