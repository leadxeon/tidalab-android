package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.MainDesign;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: DashActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.DashActivity$main$3$2", f = "DashActivity.kt", l = {51, 66, 66}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class DashActivity$main$3$2 extends SuspendLambda implements Function2<MainDesign.Request, Continuation<? super Unit>, Object> {
    public final /* synthetic */ MainDesign $design;
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ DashActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DashActivity$main$3$2(DashActivity dashActivity, MainDesign mainDesign, Continuation<? super DashActivity$main$3$2> continuation) {
        super(2, continuation);
        this.this$0 = dashActivity;
        this.$design = mainDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DashActivity$main$3$2 dashActivity$main$3$2 = new DashActivity$main$3$2(this.this$0, this.$design, continuation);
        dashActivity$main$3$2.L$0 = obj;
        return dashActivity$main$3$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(MainDesign.Request request, Continuation<? super Unit> continuation) {
        DashActivity$main$3$2 dashActivity$main$3$2 = new DashActivity$main$3$2(this.this$0, this.$design, continuation);
        dashActivity$main$3$2.L$0 = request;
        return dashActivity$main$3$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0062 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r5.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0025
            if (r1 == r4) goto L_0x0020
            if (r1 == r3) goto L_0x0018
            if (r1 != r2) goto L_0x0010
            goto L_0x0020
        L_0x0010:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x0018:
            java.lang.Object r1 = r5.L$0
            com.tidalab.v2board.clash.design.MainDesign r1 = (com.tidalab.v2board.clash.design.MainDesign) r1
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r6)
            goto L_0x0044
        L_0x0020:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r6)
            goto L_0x00de
        L_0x0025:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r6)
            java.lang.Object r6 = r5.L$0
            com.tidalab.v2board.clash.design.MainDesign$Request r6 = (com.tidalab.v2board.clash.design.MainDesign.Request) r6
            int r6 = r6.ordinal()
            switch(r6) {
                case 0: goto L_0x00c3;
                case 1: goto L_0x00b3;
                case 2: goto L_0x00a3;
                case 3: goto L_0x0093;
                case 4: goto L_0x0083;
                case 5: goto L_0x0073;
                case 6: goto L_0x0063;
                case 7: goto L_0x0035;
                default: goto L_0x0033;
            }
        L_0x0033:
            goto L_0x00de
        L_0x0035:
            com.tidalab.v2board.clash.design.MainDesign r1 = r5.$design
            com.tidalab.v2board.clash.DashActivity r6 = r5.this$0
            r5.L$0 = r1
            r5.label = r3
            java.lang.Object r6 = com.tidalab.v2board.clash.DashActivity.access$queryAppVersionName(r6, r5)
            if (r6 != r0) goto L_0x0044
            return r0
        L_0x0044:
            java.lang.String r6 = (java.lang.String) r6
            r3 = 0
            r5.L$0 = r3
            r5.label = r2
            java.util.Objects.requireNonNull(r1)
            kotlinx.coroutines.Dispatchers r2 = kotlinx.coroutines.Dispatchers.INSTANCE
            kotlinx.coroutines.MainCoroutineDispatcher r2 = kotlinx.coroutines.internal.MainDispatcherLoader.dispatcher
            com.tidalab.v2board.clash.design.MainDesign$showAbout$2 r4 = new com.tidalab.v2board.clash.design.MainDesign$showAbout$2
            r4.<init>(r1, r6, r3)
            java.lang.Object r6 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r2, r4, r5)
            if (r6 != r0) goto L_0x005e
            goto L_0x0060
        L_0x005e:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
        L_0x0060:
            if (r6 != r0) goto L_0x00de
            return r0
        L_0x0063:
            com.tidalab.v2board.clash.DashActivity r6 = r5.this$0
            java.lang.Class<com.tidalab.v2board.clash.HelpActivity> r0 = com.tidalab.v2board.clash.HelpActivity.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            android.content.Intent r0 = com.horcrux.svg.PathParser.getIntent(r0)
            r6.startActivity(r0)
            goto L_0x00de
        L_0x0073:
            com.tidalab.v2board.clash.DashActivity r6 = r5.this$0
            java.lang.Class<com.tidalab.v2board.clash.SettingsActivity> r0 = com.tidalab.v2board.clash.SettingsActivity.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            android.content.Intent r0 = com.horcrux.svg.PathParser.getIntent(r0)
            r6.startActivity(r0)
            goto L_0x00de
        L_0x0083:
            com.tidalab.v2board.clash.DashActivity r6 = r5.this$0
            java.lang.Class<com.tidalab.v2board.clash.LogsActivity> r0 = com.tidalab.v2board.clash.LogsActivity.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            android.content.Intent r0 = com.horcrux.svg.PathParser.getIntent(r0)
            r6.startActivity(r0)
            goto L_0x00de
        L_0x0093:
            com.tidalab.v2board.clash.DashActivity r6 = r5.this$0
            java.lang.Class<com.tidalab.v2board.clash.ProvidersActivity> r0 = com.tidalab.v2board.clash.ProvidersActivity.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            android.content.Intent r0 = com.horcrux.svg.PathParser.getIntent(r0)
            r6.startActivity(r0)
            goto L_0x00de
        L_0x00a3:
            com.tidalab.v2board.clash.DashActivity r6 = r5.this$0
            java.lang.Class<com.tidalab.v2board.clash.ProfilesActivity> r0 = com.tidalab.v2board.clash.ProfilesActivity.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            android.content.Intent r0 = com.horcrux.svg.PathParser.getIntent(r0)
            r6.startActivity(r0)
            goto L_0x00de
        L_0x00b3:
            com.tidalab.v2board.clash.DashActivity r6 = r5.this$0
            java.lang.Class<com.tidalab.v2board.clash.ProxyActivity> r0 = com.tidalab.v2board.clash.ProxyActivity.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            android.content.Intent r0 = com.horcrux.svg.PathParser.getIntent(r0)
            r6.startActivity(r0)
            goto L_0x00de
        L_0x00c3:
            com.tidalab.v2board.clash.DashActivity r6 = r5.this$0
            boolean r6 = r6.getClashRunning()
            if (r6 == 0) goto L_0x00d1
            com.tidalab.v2board.clash.DashActivity r6 = r5.this$0
            com.tidalab.v2board.clash.design.dialog.InputKt.stopClashService(r6)
            goto L_0x00de
        L_0x00d1:
            com.tidalab.v2board.clash.DashActivity r6 = r5.this$0
            com.tidalab.v2board.clash.design.MainDesign r1 = r5.$design
            r5.label = r4
            java.lang.Object r6 = com.tidalab.v2board.clash.DashActivity.access$startClash(r6, r1, r5)
            if (r6 != r0) goto L_0x00de
            return r0
        L_0x00de:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.DashActivity$main$3$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
