package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.OverrideSettingsDesign;
/* compiled from: OverrideSettingsActivity.kt */
/* loaded from: classes.dex */
public final class OverrideSettingsActivity extends BaseActivity<OverrideSettingsDesign> {
    public static final /* synthetic */ int $r8$clinit = 0;

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0081 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008a  */
    @Override // com.tidalab.v2board.clash.BaseActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object main(kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r9 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            boolean r1 = r10 instanceof com.tidalab.v2board.clash.OverrideSettingsActivity$main$1
            if (r1 == 0) goto L_0x0015
            r1 = r10
            com.tidalab.v2board.clash.OverrideSettingsActivity$main$1 r1 = (com.tidalab.v2board.clash.OverrideSettingsActivity$main$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.label = r2
            goto L_0x001a
        L_0x0015:
            com.tidalab.v2board.clash.OverrideSettingsActivity$main$1 r1 = new com.tidalab.v2board.clash.OverrideSettingsActivity$main$1
            r1.<init>(r9, r10)
        L_0x001a:
            java.lang.Object r10 = r1.result
            int r2 = r1.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L_0x004c
            if (r2 == r5) goto L_0x0043
            if (r2 == r4) goto L_0x0033
            if (r2 != r3) goto L_0x002b
            goto L_0x0033
        L_0x002b:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x0033:
            java.lang.Object r2 = r1.L$2
            com.tidalab.v2board.clash.design.OverrideSettingsDesign r2 = (com.tidalab.v2board.clash.design.OverrideSettingsDesign) r2
            java.lang.Object r4 = r1.L$1
            com.tidalab.v2board.clash.service.store.ServiceStore r4 = (com.tidalab.v2board.clash.service.store.ServiceStore) r4
            java.lang.Object r5 = r1.L$0
            com.tidalab.v2board.clash.OverrideSettingsActivity r5 = (com.tidalab.v2board.clash.OverrideSettingsActivity) r5
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r10)
            goto L_0x0084
        L_0x0043:
            java.lang.Object r2 = r1.L$0
            com.tidalab.v2board.clash.OverrideSettingsActivity r2 = (com.tidalab.v2board.clash.OverrideSettingsActivity) r2
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r10)
            r5 = r2
            goto L_0x0060
        L_0x004c:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r10)
            com.tidalab.v2board.clash.OverrideSettingsActivity$main$configuration$1 r10 = new com.tidalab.v2board.clash.OverrideSettingsActivity$main$configuration$1
            r10.<init>(r6)
            r1.L$0 = r9
            r1.label = r5
            java.lang.Object r10 = com.tidalab.v2board.clash.design.dialog.InputKt.withClash$default(r6, r10, r1, r5)
            if (r10 != r0) goto L_0x005f
            return r0
        L_0x005f:
            r5 = r9
        L_0x0060:
            com.tidalab.v2board.clash.core.model.ConfigurationOverride r10 = (com.tidalab.v2board.clash.core.model.ConfigurationOverride) r10
            com.tidalab.v2board.clash.service.store.ServiceStore r2 = new com.tidalab.v2board.clash.service.store.ServiceStore
            r2.<init>(r5)
            com.tidalab.v2board.clash.OverrideSettingsActivity$main$2 r7 = new com.tidalab.v2board.clash.OverrideSettingsActivity$main$2
            r7.<init>(r10, r6)
            r5.defer = r7
            com.tidalab.v2board.clash.design.OverrideSettingsDesign r7 = new com.tidalab.v2board.clash.design.OverrideSettingsDesign
            r7.<init>(r5, r10)
            r1.L$0 = r5
            r1.L$1 = r2
            r1.L$2 = r7
            r1.label = r4
            java.lang.Object r10 = r5.setContentDesign(r7, r1)
            if (r10 != r0) goto L_0x0082
            return r0
        L_0x0082:
            r4 = r2
            r2 = r7
        L_0x0084:
            boolean r10 = com.tidalab.v2board.clash.design.dialog.InputKt.isActive(r5)
            if (r10 == 0) goto L_0x00bf
            r1.L$0 = r5
            r1.L$1 = r4
            r1.L$2 = r2
            r1.label = r3
            kotlinx.coroutines.selects.SelectBuilderImpl r10 = new kotlinx.coroutines.selects.SelectBuilderImpl
            r10.<init>(r1)
            kotlinx.coroutines.channels.Channel<com.tidalab.v2board.clash.BaseActivity$Event> r7 = r5.events     // Catch: all -> 0x00b4
            kotlinx.coroutines.selects.SelectClause1 r7 = r7.getOnReceive()     // Catch: all -> 0x00b4
            com.tidalab.v2board.clash.OverrideSettingsActivity$main$3$1 r8 = new com.tidalab.v2board.clash.OverrideSettingsActivity$main$3$1     // Catch: all -> 0x00b4
            r8.<init>(r6)     // Catch: all -> 0x00b4
            r7.registerSelectClause1(r10, r8)     // Catch: all -> 0x00b4
            kotlinx.coroutines.channels.Channel<R> r7 = r2.requests     // Catch: all -> 0x00b4
            kotlinx.coroutines.selects.SelectClause1 r7 = r7.getOnReceive()     // Catch: all -> 0x00b4
            com.tidalab.v2board.clash.OverrideSettingsActivity$main$3$2 r8 = new com.tidalab.v2board.clash.OverrideSettingsActivity$main$3$2     // Catch: all -> 0x00b4
            r8.<init>(r2, r5, r4, r6)     // Catch: all -> 0x00b4
            r7.registerSelectClause1(r10, r8)     // Catch: all -> 0x00b4
            goto L_0x00b8
        L_0x00b4:
            r7 = move-exception
            r10.handleBuilderException(r7)
        L_0x00b8:
            java.lang.Object r10 = r10.getResult()
            if (r10 != r0) goto L_0x0084
            return r0
        L_0x00bf:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.OverrideSettingsActivity.main(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
