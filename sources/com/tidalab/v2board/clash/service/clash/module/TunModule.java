package com.tidalab.v2board.clash.service.clash.module;

import android.net.ConnectivityManager;
import android.net.VpnService;
import androidx.core.content.ContextCompat;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.security.SecureRandom;
import kotlin.Unit;
import kotlinx.coroutines.channels.Channel;
/* compiled from: TunModule.kt */
/* loaded from: classes.dex */
public final class TunModule extends Module<Unit> {
    public static final TunModule Companion = null;
    public static final SecureRandom random = new SecureRandom();
    public final Channel<Unit> close = InputKt.Channel$default(-1, null, null, 6);
    public final ConnectivityManager connectivity;
    public final VpnService vpn;

    public TunModule(VpnService vpnService) {
        super(vpnService);
        this.vpn = vpnService;
        this.connectivity = (ConnectivityManager) ContextCompat.getSystemService(vpnService, ConnectivityManager.class);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0067 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0068  */
    @Override // com.tidalab.v2board.clash.service.clash.module.Module
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object run(kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.tidalab.v2board.clash.service.clash.module.TunModule$run$1
            if (r0 == 0) goto L_0x0013
            r0 = r8
            com.tidalab.v2board.clash.service.clash.module.TunModule$run$1 r0 = (com.tidalab.v2board.clash.service.clash.module.TunModule$run$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.clash.module.TunModule$run$1 r0 = new com.tidalab.v2board.clash.service.clash.module.TunModule$run$1
            r0.<init>(r7, r8)
        L_0x0018:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L_0x0046
            if (r2 == r6) goto L_0x0042
            if (r2 == r5) goto L_0x003a
            if (r2 == r4) goto L_0x0032
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0032:
            java.lang.Object r0 = r0.L$0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            goto L_0x007e
        L_0x003a:
            java.lang.Object r0 = r0.L$0
            kotlin.Unit r0 = (kotlin.Unit) r0
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            goto L_0x0069
        L_0x0042:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)     // Catch: all -> 0x006a
            goto L_0x0054
        L_0x0046:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.Channel<kotlin.Unit> r8 = r7.close     // Catch: all -> 0x006a
            r0.label = r6     // Catch: all -> 0x006a
            java.lang.Object r8 = r8.receive(r0)     // Catch: all -> 0x006a
            if (r8 != r1) goto L_0x0054
            return r1
        L_0x0054:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch: all -> 0x006a
            kotlinx.coroutines.NonCancellable r2 = kotlinx.coroutines.NonCancellable.INSTANCE
            com.tidalab.v2board.clash.service.clash.module.TunModule$run$2 r4 = new com.tidalab.v2board.clash.service.clash.module.TunModule$run$2
            r4.<init>(r3)
            r0.L$0 = r8
            r0.label = r5
            java.lang.Object r0 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r2, r4, r0)
            if (r0 != r1) goto L_0x0068
            return r1
        L_0x0068:
            r0 = r8
        L_0x0069:
            return r0
        L_0x006a:
            r8 = move-exception
            kotlinx.coroutines.NonCancellable r2 = kotlinx.coroutines.NonCancellable.INSTANCE
            com.tidalab.v2board.clash.service.clash.module.TunModule$run$2 r5 = new com.tidalab.v2board.clash.service.clash.module.TunModule$run$2
            r5.<init>(r3)
            r0.L$0 = r8
            r0.label = r4
            java.lang.Object r0 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r2, r5, r0)
            if (r0 != r1) goto L_0x007d
            return r1
        L_0x007d:
            r0 = r8
        L_0x007e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.clash.module.TunModule.run(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
