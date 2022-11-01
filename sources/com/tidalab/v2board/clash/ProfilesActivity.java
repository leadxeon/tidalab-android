package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.ProfilesDesign;
/* compiled from: ProfilesActivity.kt */
/* loaded from: classes.dex */
public final class ProfilesActivity extends BaseActivity<ProfilesDesign> {
    public static final /* synthetic */ int $r8$clinit = 0;

    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0075  */
    @Override // com.tidalab.v2board.clash.BaseActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object main(kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r9 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            boolean r1 = r10 instanceof com.tidalab.v2board.clash.ProfilesActivity$main$1
            if (r1 == 0) goto L_0x0015
            r1 = r10
            com.tidalab.v2board.clash.ProfilesActivity$main$1 r1 = (com.tidalab.v2board.clash.ProfilesActivity$main$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.label = r2
            goto L_0x001a
        L_0x0015:
            com.tidalab.v2board.clash.ProfilesActivity$main$1 r1 = new com.tidalab.v2board.clash.ProfilesActivity$main$1
            r1.<init>(r9, r10)
        L_0x001a:
            java.lang.Object r10 = r1.result
            int r2 = r1.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x004c
            if (r2 == r4) goto L_0x003e
            if (r2 != r3) goto L_0x0036
            java.lang.Object r2 = r1.L$2
            kotlinx.coroutines.channels.Channel r2 = (kotlinx.coroutines.channels.Channel) r2
            java.lang.Object r4 = r1.L$1
            com.tidalab.v2board.clash.design.ProfilesDesign r4 = (com.tidalab.v2board.clash.design.ProfilesDesign) r4
            java.lang.Object r5 = r1.L$0
            com.tidalab.v2board.clash.ProfilesActivity r5 = (com.tidalab.v2board.clash.ProfilesActivity) r5
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r10)
            goto L_0x006f
        L_0x0036:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x003e:
            java.lang.Object r2 = r1.L$1
            com.tidalab.v2board.clash.design.ProfilesDesign r2 = (com.tidalab.v2board.clash.design.ProfilesDesign) r2
            java.lang.Object r4 = r1.L$0
            com.tidalab.v2board.clash.ProfilesActivity r4 = (com.tidalab.v2board.clash.ProfilesActivity) r4
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r10)
            r5 = r4
            r4 = r2
            goto L_0x0063
        L_0x004c:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r10)
            com.tidalab.v2board.clash.design.ProfilesDesign r10 = new com.tidalab.v2board.clash.design.ProfilesDesign
            r10.<init>(r9)
            r1.L$0 = r9
            r1.L$1 = r10
            r1.label = r4
            java.lang.Object r2 = r9.setContentDesign(r10, r1)
            if (r2 != r0) goto L_0x0061
            return r0
        L_0x0061:
            r5 = r9
            r4 = r10
        L_0x0063:
            java.util.concurrent.TimeUnit r10 = java.util.concurrent.TimeUnit.MINUTES
            r6 = 1
            long r6 = r10.toMillis(r6)
            kotlinx.coroutines.channels.Channel r2 = com.horcrux.svg.PathParser.ticker(r5, r6)
        L_0x006f:
            boolean r10 = com.tidalab.v2board.clash.design.dialog.InputKt.isActive(r5)
            if (r10 == 0) goto L_0x00bb
            r1.L$0 = r5
            r1.L$1 = r4
            r1.L$2 = r2
            r1.label = r3
            kotlinx.coroutines.selects.SelectBuilderImpl r10 = new kotlinx.coroutines.selects.SelectBuilderImpl
            r10.<init>(r1)
            kotlinx.coroutines.channels.Channel<com.tidalab.v2board.clash.BaseActivity$Event> r6 = r5.events     // Catch: all -> 0x00b0
            kotlinx.coroutines.selects.SelectClause1 r6 = r6.getOnReceive()     // Catch: all -> 0x00b0
            com.tidalab.v2board.clash.ProfilesActivity$main$2$1 r7 = new com.tidalab.v2board.clash.ProfilesActivity$main$2$1     // Catch: all -> 0x00b0
            r8 = 0
            r7.<init>(r5, r4, r8)     // Catch: all -> 0x00b0
            r6.registerSelectClause1(r10, r7)     // Catch: all -> 0x00b0
            kotlinx.coroutines.channels.Channel<R> r6 = r4.requests     // Catch: all -> 0x00b0
            kotlinx.coroutines.selects.SelectClause1 r6 = r6.getOnReceive()     // Catch: all -> 0x00b0
            com.tidalab.v2board.clash.ProfilesActivity$main$2$2 r7 = new com.tidalab.v2board.clash.ProfilesActivity$main$2$2     // Catch: all -> 0x00b0
            r7.<init>(r5, r4, r8)     // Catch: all -> 0x00b0
            r6.registerSelectClause1(r10, r7)     // Catch: all -> 0x00b0
            boolean r6 = r5.activityStarted     // Catch: all -> 0x00b0
            if (r6 == 0) goto L_0x00b4
            kotlinx.coroutines.selects.SelectClause1 r6 = r2.getOnReceive()     // Catch: all -> 0x00b0
            com.tidalab.v2board.clash.ProfilesActivity$main$2$3 r7 = new com.tidalab.v2board.clash.ProfilesActivity$main$2$3     // Catch: all -> 0x00b0
            r7.<init>(r4, r8)     // Catch: all -> 0x00b0
            r6.registerSelectClause1(r10, r7)     // Catch: all -> 0x00b0
            goto L_0x00b4
        L_0x00b0:
            r6 = move-exception
            r10.handleBuilderException(r6)
        L_0x00b4:
            java.lang.Object r10 = r10.getResult()
            if (r10 != r0) goto L_0x006f
            return r0
        L_0x00bb:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.ProfilesActivity.main(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
