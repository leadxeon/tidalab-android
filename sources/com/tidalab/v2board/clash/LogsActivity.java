package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.LogsDesign;
/* compiled from: LogsActivity.kt */
/* loaded from: classes.dex */
public final class LogsActivity extends BaseActivity<LogsDesign> {
    public static final /* synthetic */ int $r8$clinit = 0;

    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006b  */
    @Override // com.tidalab.v2board.clash.BaseActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object main(kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r8 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            boolean r1 = r9 instanceof com.tidalab.v2board.clash.LogsActivity$main$1
            if (r1 == 0) goto L_0x0015
            r1 = r9
            com.tidalab.v2board.clash.LogsActivity$main$1 r1 = (com.tidalab.v2board.clash.LogsActivity$main$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.label = r2
            goto L_0x001a
        L_0x0015:
            com.tidalab.v2board.clash.LogsActivity$main$1 r1 = new com.tidalab.v2board.clash.LogsActivity$main$1
            r1.<init>(r8, r9)
        L_0x001a:
            java.lang.Object r9 = r1.result
            int r2 = r1.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x003b
            if (r2 == r4) goto L_0x002f
            if (r2 != r3) goto L_0x0027
            goto L_0x002f
        L_0x0027:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x002f:
            java.lang.Object r2 = r1.L$1
            com.tidalab.v2board.clash.design.LogsDesign r2 = (com.tidalab.v2board.clash.design.LogsDesign) r2
            java.lang.Object r4 = r1.L$0
            com.tidalab.v2board.clash.LogsActivity r4 = (com.tidalab.v2board.clash.LogsActivity) r4
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r9)
            goto L_0x0065
        L_0x003b:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r9)
            boolean r9 = com.tidalab.v2board.clash.LogcatService.running
            if (r9 == 0) goto L_0x0052
            java.lang.Class<com.tidalab.v2board.clash.LogcatActivity> r9 = com.tidalab.v2board.clash.LogcatActivity.class
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)
            android.content.Intent r9 = com.horcrux.svg.PathParser.getIntent(r9)
            r8.startActivity(r9)
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x0052:
            com.tidalab.v2board.clash.design.LogsDesign r2 = new com.tidalab.v2board.clash.design.LogsDesign
            r2.<init>(r8)
            r1.L$0 = r8
            r1.L$1 = r2
            r1.label = r4
            java.lang.Object r9 = r8.setContentDesign(r2, r1)
            if (r9 != r0) goto L_0x0064
            return r0
        L_0x0064:
            r4 = r8
        L_0x0065:
            boolean r9 = com.tidalab.v2board.clash.design.dialog.InputKt.isActive(r4)
            if (r9 == 0) goto L_0x009f
            r1.L$0 = r4
            r1.L$1 = r2
            r1.label = r3
            kotlinx.coroutines.selects.SelectBuilderImpl r9 = new kotlinx.coroutines.selects.SelectBuilderImpl
            r9.<init>(r1)
            kotlinx.coroutines.channels.Channel<com.tidalab.v2board.clash.BaseActivity$Event> r5 = r4.events     // Catch: all -> 0x0094
            kotlinx.coroutines.selects.SelectClause1 r5 = r5.getOnReceive()     // Catch: all -> 0x0094
            com.tidalab.v2board.clash.LogsActivity$main$2$1 r6 = new com.tidalab.v2board.clash.LogsActivity$main$2$1     // Catch: all -> 0x0094
            r7 = 0
            r6.<init>(r2, r4, r7)     // Catch: all -> 0x0094
            r5.registerSelectClause1(r9, r6)     // Catch: all -> 0x0094
            kotlinx.coroutines.channels.Channel<R> r5 = r2.requests     // Catch: all -> 0x0094
            kotlinx.coroutines.selects.SelectClause1 r5 = r5.getOnReceive()     // Catch: all -> 0x0094
            com.tidalab.v2board.clash.LogsActivity$main$2$2 r6 = new com.tidalab.v2board.clash.LogsActivity$main$2$2     // Catch: all -> 0x0094
            r6.<init>(r4, r2, r7)     // Catch: all -> 0x0094
            r5.registerSelectClause1(r9, r6)     // Catch: all -> 0x0094
            goto L_0x0098
        L_0x0094:
            r5 = move-exception
            r9.handleBuilderException(r5)
        L_0x0098:
            java.lang.Object r9 = r9.getResult()
            if (r9 != r0) goto L_0x0065
            return r0
        L_0x009f:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.LogsActivity.main(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
