package com.tidalab.v2board.clash.service.clash.module;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import androidx.core.app.NotificationCompat$Builder;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.common.constants.Components;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
/* compiled from: DynamicNotificationModule.kt */
/* loaded from: classes.dex */
public final class DynamicNotificationModule extends Module<Unit> {
    public final NotificationCompat$Builder builder;

    public DynamicNotificationModule(Service service) {
        super(service);
        NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(service, "clash_status_channel");
        notificationCompat$Builder.mNotification.icon = R.drawable.ic_logo_service;
        notificationCompat$Builder.setFlag(2, true);
        notificationCompat$Builder.mColor = PathParser.getColorCompat(service, R.color.color_clash);
        notificationCompat$Builder.setFlag(8, true);
        notificationCompat$Builder.mShowWhen = false;
        notificationCompat$Builder.setContentTitle("Not Selected");
        Intent intent = new Intent();
        Components components = Components.INSTANCE;
        notificationCompat$Builder.mContentIntent = PendingIntent.getActivity(service, R.id.nf_clash_status, intent.setComponent(Components.MAIN_ACTIVITY).setFlags(872415232), PathParser.pendingIntentFlags$default(134217728, false, 2));
        this.builder = notificationCompat$Builder;
    }

    @Override // com.tidalab.v2board.clash.service.clash.module.Module
    public Object run(Continuation<? super Unit> continuation) {
        Object coroutineScope = InputKt.coroutineScope(new DynamicNotificationModule$run$2(this, null), continuation);
        return coroutineScope == CoroutineSingletons.COROUTINE_SUSPENDED ? coroutineScope : Unit.INSTANCE;
    }
}
