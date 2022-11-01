package com.tidalab.v2board.clash.service.clash.module;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import androidx.core.app.NotificationCompat$Builder;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.common.constants.Components;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
/* compiled from: StaticNotificationModule.kt */
/* loaded from: classes.dex */
public final class StaticNotificationModule extends Module<Unit> {
    public final NotificationCompat$Builder builder;

    public StaticNotificationModule(Service service) {
        super(service);
        NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(service, "clash_status_channel");
        notificationCompat$Builder.mNotification.icon = R.drawable.ic_logo_service;
        notificationCompat$Builder.setFlag(2, true);
        notificationCompat$Builder.mColor = PathParser.getColorCompat(service, R.color.color_clash);
        notificationCompat$Builder.setFlag(8, true);
        notificationCompat$Builder.mShowWhen = false;
        Intent intent = new Intent();
        Components components = Components.INSTANCE;
        notificationCompat$Builder.mContentIntent = PendingIntent.getActivity(service, R.id.nf_clash_status, intent.setComponent(Components.MAIN_ACTIVITY).setFlags(872415232), PathParser.pendingIntentFlags$default(134217728, false, 2));
        this.builder = notificationCompat$Builder;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0052 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0057  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0050 -> B:18:0x0053). Please submit an issue!!! */
    @Override // com.tidalab.v2board.clash.service.clash.module.Module
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object run(kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof com.tidalab.v2board.clash.service.clash.module.StaticNotificationModule$run$1
            if (r0 == 0) goto L_0x0013
            r0 = r11
            com.tidalab.v2board.clash.service.clash.module.StaticNotificationModule$run$1 r0 = (com.tidalab.v2board.clash.service.clash.module.StaticNotificationModule$run$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.clash.module.StaticNotificationModule$run$1 r0 = new com.tidalab.v2board.clash.service.clash.module.StaticNotificationModule$run$1
            r0.<init>(r10, r11)
        L_0x0018:
            java.lang.Object r11 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0037
            if (r2 != r3) goto L_0x002f
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$0
            com.tidalab.v2board.clash.service.clash.module.StaticNotificationModule r4 = (com.tidalab.v2board.clash.service.clash.module.StaticNotificationModule) r4
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r11)
            goto L_0x0053
        L_0x002f:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x0037:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r11)
            r5 = 0
            r6 = -1
            com.tidalab.v2board.clash.service.clash.module.StaticNotificationModule$run$loaded$1 r7 = com.tidalab.v2board.clash.service.clash.module.StaticNotificationModule$run$loaded$1.INSTANCE
            r8 = 1
            r9 = 0
            r4 = r10
            kotlinx.coroutines.channels.ReceiveChannel r11 = com.tidalab.v2board.clash.service.clash.module.Module.receiveBroadcast$default(r4, r5, r6, r7, r8, r9)
            r2 = r11
        L_0x0046:
            r0.L$0 = r4
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r11 = r2.receive(r0)
            if (r11 != r1) goto L_0x0053
            return r1
        L_0x0053:
            java.lang.String r11 = com.tidalab.v2board.clash.service.StatusProvider.currentProfile
            if (r11 != 0) goto L_0x0059
            java.lang.String r11 = "Not selected"
        L_0x0059:
            androidx.core.app.NotificationCompat$Builder r5 = r4.builder
            r5.setContentTitle(r11)
            android.app.Service r11 = r4.service
            r6 = 2131820894(0x7f11015e, float:1.9274516E38)
            java.lang.CharSequence r11 = r11.getText(r6)
            r5.setContentText(r11)
            android.app.Notification r11 = r5.build()
            android.app.Service r5 = r4.service
            r6 = 2131296571(0x7f09013b, float:1.8211062E38)
            r5.startForeground(r6, r11)
            goto L_0x0046
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.clash.module.StaticNotificationModule.run(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
