package com.tidalab.v2board.clash.service.clash.module;

import android.app.Service;
/* compiled from: CloseModule.kt */
/* loaded from: classes.dex */
public final class CloseModule extends Module<RequestClose> {

    /* compiled from: CloseModule.kt */
    /* loaded from: classes.dex */
    public static final class RequestClose {
        public static final RequestClose INSTANCE = new RequestClose();
    }

    public CloseModule(Service service) {
        super(service);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006a A[RETURN] */
    @Override // com.tidalab.v2board.clash.service.clash.module.Module
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object run(kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r11 = this;
            boolean r0 = r12 instanceof com.tidalab.v2board.clash.service.clash.module.CloseModule$run$1
            if (r0 == 0) goto L_0x0013
            r0 = r12
            com.tidalab.v2board.clash.service.clash.module.CloseModule$run$1 r0 = (com.tidalab.v2board.clash.service.clash.module.CloseModule$run$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.clash.module.CloseModule$run$1 r0 = new com.tidalab.v2board.clash.service.clash.module.CloseModule$run$1
            r0.<init>(r11, r12)
        L_0x0018:
            java.lang.Object r12 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            r4 = 2
            if (r2 == 0) goto L_0x003a
            if (r2 == r3) goto L_0x0032
            if (r2 != r4) goto L_0x002a
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r12)
            goto L_0x006b
        L_0x002a:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x0032:
            java.lang.Object r2 = r0.L$0
            com.tidalab.v2board.clash.service.clash.module.CloseModule r2 = (com.tidalab.v2board.clash.service.clash.module.CloseModule) r2
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r12)
            goto L_0x0056
        L_0x003a:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r12)
            r6 = 0
            r7 = 0
            com.tidalab.v2board.clash.service.clash.module.CloseModule$run$broadcasts$1 r8 = com.tidalab.v2board.clash.service.clash.module.CloseModule$run$broadcasts$1.INSTANCE
            r9 = 3
            r10 = 0
            r5 = r11
            kotlinx.coroutines.channels.ReceiveChannel r12 = com.tidalab.v2board.clash.service.clash.module.Module.receiveBroadcast$default(r5, r6, r7, r8, r9, r10)
            r0.L$0 = r11
            r0.label = r3
            kotlinx.coroutines.channels.AbstractChannel r12 = (kotlinx.coroutines.channels.AbstractChannel) r12
            java.lang.Object r12 = r12.receive(r0)
            if (r12 != r1) goto L_0x0055
            return r1
        L_0x0055:
            r2 = r11
        L_0x0056:
            r12 = 0
            java.lang.String r3 = "ClashForAndroid"
            java.lang.String r5 = "User request close"
            android.util.Log.d(r3, r5, r12)
            com.tidalab.v2board.clash.service.clash.module.CloseModule$RequestClose r3 = com.tidalab.v2board.clash.service.clash.module.CloseModule.RequestClose.INSTANCE
            r0.L$0 = r12
            r0.label = r4
            java.lang.Object r12 = r2.enqueueEvent(r3, r0)
            if (r12 != r1) goto L_0x006b
            return r1
        L_0x006b:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.clash.module.CloseModule.run(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
