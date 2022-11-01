package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.ApkBrokenDesign;
/* compiled from: ApkBrokenActivity.kt */
/* loaded from: classes.dex */
public final class ApkBrokenActivity extends BaseActivity<ApkBrokenDesign> {
    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0088  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x006e -> B:25:0x0071). Please submit an issue!!! */
    @Override // com.tidalab.v2board.clash.BaseActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object main(kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.tidalab.v2board.clash.ApkBrokenActivity$main$1
            if (r0 == 0) goto L_0x0013
            r0 = r8
            com.tidalab.v2board.clash.ApkBrokenActivity$main$1 r0 = (com.tidalab.v2board.clash.ApkBrokenActivity$main$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.ApkBrokenActivity$main$1 r0 = new com.tidalab.v2board.clash.ApkBrokenActivity$main$1
            r0.<init>(r7, r8)
        L_0x0018:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0046
            if (r2 == r4) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r2 = r0.L$1
            com.tidalab.v2board.clash.design.ApkBrokenDesign r2 = (com.tidalab.v2board.clash.design.ApkBrokenDesign) r2
            java.lang.Object r4 = r0.L$0
            com.tidalab.v2board.clash.ApkBrokenActivity r4 = (com.tidalab.v2board.clash.ApkBrokenActivity) r4
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            goto L_0x0071
        L_0x0032:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x003a:
            java.lang.Object r2 = r0.L$1
            com.tidalab.v2board.clash.design.ApkBrokenDesign r2 = (com.tidalab.v2board.clash.design.ApkBrokenDesign) r2
            java.lang.Object r4 = r0.L$0
            com.tidalab.v2board.clash.ApkBrokenActivity r4 = (com.tidalab.v2board.clash.ApkBrokenActivity) r4
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            goto L_0x005c
        L_0x0046:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            com.tidalab.v2board.clash.design.ApkBrokenDesign r2 = new com.tidalab.v2board.clash.design.ApkBrokenDesign
            r2.<init>(r7)
            r0.L$0 = r7
            r0.L$1 = r2
            r0.label = r4
            java.lang.Object r8 = r7.setContentDesign(r2, r0)
            if (r8 != r1) goto L_0x005b
            return r1
        L_0x005b:
            r4 = r7
        L_0x005c:
            boolean r8 = com.tidalab.v2board.clash.design.dialog.InputKt.isActive(r4)
            if (r8 == 0) goto L_0x0088
            kotlinx.coroutines.channels.Channel<R> r8 = r2.requests
            r0.L$0 = r4
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r8 = r8.receive(r0)
            if (r8 != r1) goto L_0x0071
            return r1
        L_0x0071:
            com.tidalab.v2board.clash.design.ApkBrokenDesign$Request r8 = (com.tidalab.v2board.clash.design.ApkBrokenDesign.Request) r8
            android.content.Intent r5 = new android.content.Intent
            java.lang.String r6 = "android.intent.action.VIEW"
            r5.<init>(r6)
            java.lang.String r8 = r8.url
            android.net.Uri r8 = android.net.Uri.parse(r8)
            android.content.Intent r8 = r5.setData(r8)
            r4.startActivity(r8)
            goto L_0x005c
        L_0x0088:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.ApkBrokenActivity.main(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
