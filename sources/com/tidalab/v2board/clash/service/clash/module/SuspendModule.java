package com.tidalab.v2board.clash.service.clash.module;

import android.app.Service;
import kotlin.Unit;
/* compiled from: SuspendModule.kt */
/* loaded from: classes.dex */
public final class SuspendModule extends Module<Unit> {
    public SuspendModule(Service service) {
        super(service);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0081 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0092 A[Catch: all -> 0x0041, TRY_ENTER, TryCatch #0 {all -> 0x0041, blocks: (B:16:0x003d, B:26:0x0077, B:29:0x0082, B:32:0x0092, B:33:0x009f, B:35:0x00a7, B:36:0x00b4), top: B:41:0x003d }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009f A[Catch: all -> 0x0041, TryCatch #0 {all -> 0x0041, blocks: (B:16:0x003d, B:26:0x0077, B:29:0x0082, B:32:0x0092, B:33:0x009f, B:35:0x00a7, B:36:0x00b4), top: B:41:0x003d }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x007f -> B:29:0x0082). Please submit an issue!!! */
    @Override // com.tidalab.v2board.clash.service.clash.module.Module
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object run(kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof com.tidalab.v2board.clash.service.clash.module.SuspendModule$run$1
            if (r0 == 0) goto L_0x0013
            r0 = r11
            com.tidalab.v2board.clash.service.clash.module.SuspendModule$run$1 r0 = (com.tidalab.v2board.clash.service.clash.module.SuspendModule$run$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.clash.module.SuspendModule$run$1 r0 = new com.tidalab.v2board.clash.service.clash.module.SuspendModule$run$1
            r0.<init>(r10, r11)
        L_0x0018:
            java.lang.Object r11 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 0
            r6 = 1
            if (r2 == 0) goto L_0x0047
            if (r2 == r6) goto L_0x0039
            if (r2 == r4) goto L_0x0030
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x0030:
            java.lang.Object r0 = r0.L$0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r11)
            goto L_0x00ce
        L_0x0039:
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r11)     // Catch: all -> 0x0041
            goto L_0x0082
        L_0x0041:
            r11 = move-exception
            r9 = r0
            r0 = r11
            r11 = r9
            goto L_0x00bc
        L_0x0047:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r11)
            android.app.Service r11 = r10.service
            java.lang.Class<android.os.PowerManager> r2 = android.os.PowerManager.class
            java.lang.Object r11 = androidx.core.content.ContextCompat.getSystemService(r11, r2)
            android.os.PowerManager r11 = (android.os.PowerManager) r11
            if (r11 != 0) goto L_0x0057
            goto L_0x0061
        L_0x0057:
            boolean r11 = r11.isInteractive()
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r11)
            if (r11 != 0) goto L_0x0063
        L_0x0061:
            r11 = 1
            goto L_0x0067
        L_0x0063:
            boolean r11 = r11.booleanValue()
        L_0x0067:
            com.tidalab.v2board.clash.core.Clash r2 = com.tidalab.v2board.clash.core.Clash.INSTANCE
            r11 = r11 ^ r6
            com.tidalab.v2board.clash.core.bridge.Bridge r2 = com.tidalab.v2board.clash.core.bridge.Bridge.INSTANCE
            r2.nativeSuspend(r11)
            r11 = -1
            com.tidalab.v2board.clash.service.clash.module.SuspendModule$run$screenToggle$1 r2 = com.tidalab.v2board.clash.service.clash.module.SuspendModule$run$screenToggle$1.INSTANCE
            kotlinx.coroutines.channels.ReceiveChannel r11 = r10.receiveBroadcast(r5, r11, r2)
            r2 = r11
        L_0x0077:
            r0.L$0 = r2     // Catch: all -> 0x0041
            r0.label = r6     // Catch: all -> 0x0041
            java.lang.Object r11 = r2.receive(r0)     // Catch: all -> 0x0041
            if (r11 != r1) goto L_0x0082
            return r1
        L_0x0082:
            android.content.Intent r11 = (android.content.Intent) r11     // Catch: all -> 0x0041
            java.lang.String r11 = r11.getAction()     // Catch: all -> 0x0041
            java.lang.String r7 = "android.intent.action.SCREEN_ON"
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r11, r7)     // Catch: all -> 0x0041
            java.lang.String r8 = "ClashForAndroid"
            if (r7 == 0) goto L_0x009f
            com.tidalab.v2board.clash.core.Clash r11 = com.tidalab.v2board.clash.core.Clash.INSTANCE     // Catch: all -> 0x0041
            com.tidalab.v2board.clash.core.bridge.Bridge r11 = com.tidalab.v2board.clash.core.bridge.Bridge.INSTANCE     // Catch: all -> 0x0041
            r11.nativeSuspend(r5)     // Catch: all -> 0x0041
            java.lang.String r11 = "Clash resumed"
            android.util.Log.d(r8, r11, r3)     // Catch: all -> 0x0041
            goto L_0x0077
        L_0x009f:
            java.lang.String r7 = "android.intent.action.SCREEN_OFF"
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual(r11, r7)     // Catch: all -> 0x0041
            if (r11 == 0) goto L_0x00b4
            com.tidalab.v2board.clash.core.Clash r11 = com.tidalab.v2board.clash.core.Clash.INSTANCE     // Catch: all -> 0x0041
            com.tidalab.v2board.clash.core.bridge.Bridge r11 = com.tidalab.v2board.clash.core.bridge.Bridge.INSTANCE     // Catch: all -> 0x0041
            r11.nativeSuspend(r6)     // Catch: all -> 0x0041
            java.lang.String r11 = "Clash suspended"
            android.util.Log.d(r8, r11, r3)     // Catch: all -> 0x0041
            goto L_0x0077
        L_0x00b4:
            com.tidalab.v2board.clash.core.Clash r11 = com.tidalab.v2board.clash.core.Clash.INSTANCE     // Catch: all -> 0x0041
            com.tidalab.v2board.clash.core.bridge.Bridge r11 = com.tidalab.v2board.clash.core.bridge.Bridge.INSTANCE     // Catch: all -> 0x0041
            r11.nativeHealthCheckAll()     // Catch: all -> 0x0041
            goto L_0x0077
        L_0x00bc:
            kotlinx.coroutines.NonCancellable r2 = kotlinx.coroutines.NonCancellable.INSTANCE
            com.tidalab.v2board.clash.service.clash.module.SuspendModule$run$2 r5 = new com.tidalab.v2board.clash.service.clash.module.SuspendModule$run$2
            r5.<init>(r3)
            r11.L$0 = r0
            r11.label = r4
            java.lang.Object r11 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r2, r5, r11)
            if (r11 != r1) goto L_0x00ce
            return r1
        L_0x00ce:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.clash.module.SuspendModule.run(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
