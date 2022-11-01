package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.HelpDesign;
/* compiled from: HelpActivity.kt */
/* loaded from: classes.dex */
public final class HelpActivity extends BaseActivity<HelpDesign> {
    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0056  */
    @Override // com.tidalab.v2board.clash.BaseActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object main(kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.tidalab.v2board.clash.HelpActivity$main$1
            if (r0 == 0) goto L_0x0013
            r0 = r6
            com.tidalab.v2board.clash.HelpActivity$main$1 r0 = (com.tidalab.v2board.clash.HelpActivity$main$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.HelpActivity$main$1 r0 = new com.tidalab.v2board.clash.HelpActivity$main$1
            r0.<init>(r5, r6)
        L_0x0018:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0037
            if (r2 == r4) goto L_0x002f
            if (r2 != r3) goto L_0x0027
            goto L_0x002f
        L_0x0027:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x002f:
            java.lang.Object r2 = r0.L$0
            com.tidalab.v2board.clash.HelpActivity r2 = (com.tidalab.v2board.clash.HelpActivity) r2
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r6)
            goto L_0x0050
        L_0x0037:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r6)
            com.tidalab.v2board.clash.design.HelpDesign r6 = new com.tidalab.v2board.clash.design.HelpDesign
            com.tidalab.v2board.clash.HelpActivity$main$design$1 r2 = new com.tidalab.v2board.clash.HelpActivity$main$design$1
            r2.<init>(r5)
            r6.<init>(r5, r2)
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r5.setContentDesign(r6, r0)
            if (r6 != r1) goto L_0x004f
            return r1
        L_0x004f:
            r2 = r5
        L_0x0050:
            boolean r6 = com.tidalab.v2board.clash.design.dialog.InputKt.isActive(r2)
            if (r6 == 0) goto L_0x0063
            kotlinx.coroutines.channels.Channel<com.tidalab.v2board.clash.BaseActivity$Event> r6 = r2.events
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r6 = r6.receive(r0)
            if (r6 != r1) goto L_0x0050
            return r1
        L_0x0063:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.HelpActivity.main(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
