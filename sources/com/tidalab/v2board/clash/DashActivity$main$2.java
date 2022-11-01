package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.MainDesign;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: DashActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.DashActivity$main$2", f = "DashActivity.kt", l = {27}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class DashActivity$main$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ MainDesign $design;
    public int label;
    public final /* synthetic */ DashActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DashActivity$main$2(DashActivity dashActivity, MainDesign mainDesign, Continuation<? super DashActivity$main$2> continuation) {
        super(2, continuation);
        this.this$0 = dashActivity;
        this.$design = mainDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DashActivity$main$2(this.this$0, this.$design, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new DashActivity$main$2(this.this$0, this.$design, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x007a A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r7.label
            r2 = 1
            if (r1 == 0) goto L_0x0015
            if (r1 != r2) goto L_0x000d
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            goto L_0x007b
        L_0x000d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0015:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            com.tidalab.v2board.clash.DashActivity r8 = r7.this$0
            com.tidalab.v2board.clash.design.MainDesign r1 = r7.$design
            r7.label = r2
            int r3 = com.tidalab.v2board.clash.DashActivity.$r8$clinit
            java.util.Objects.requireNonNull(r8)
            com.tidalab.v2board.clash.store.TipsStore r3 = new com.tidalab.v2board.clash.store.TipsStore
            r3.<init>(r8)
            com.tidalab.v2board.clash.common.store.Store$Delegate r4 = r3.primaryVersion$delegate
            kotlin.reflect.KProperty<java.lang.Object>[] r5 = com.tidalab.v2board.clash.store.TipsStore.$$delegatedProperties
            r6 = r5[r2]
            java.lang.Object r4 = r4.getValue(r3, r6)
            java.lang.Number r4 = (java.lang.Number) r4
            int r4 = r4.intValue()
            if (r4 == r2) goto L_0x0076
            com.tidalab.v2board.clash.common.store.Store$Delegate r4 = r3.primaryVersion$delegate
            r5 = r5[r2]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r4.setValue(r3, r5, r2)
            android.content.pm.PackageManager r2 = r8.getPackageManager()
            java.lang.String r8 = r8.getPackageName()
            r3 = 0
            android.content.pm.PackageInfo r8 = r2.getPackageInfo(r8, r3)
            long r2 = r8.firstInstallTime
            long r4 = r8.lastUpdateTime
            int r8 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r8 == 0) goto L_0x0076
            java.util.Objects.requireNonNull(r1)
            kotlinx.coroutines.Dispatchers r8 = kotlinx.coroutines.Dispatchers.INSTANCE
            kotlinx.coroutines.MainCoroutineDispatcher r8 = kotlinx.coroutines.internal.MainDispatcherLoader.dispatcher
            com.tidalab.v2board.clash.design.MainDesign$showUpdatedTips$2 r2 = new com.tidalab.v2board.clash.design.MainDesign$showUpdatedTips$2
            r3 = 0
            r2.<init>(r1, r3)
            java.lang.Object r8 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r8, r2, r7)
            if (r8 != r0) goto L_0x006e
            goto L_0x0070
        L_0x006e:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
        L_0x0070:
            if (r8 != r0) goto L_0x0073
            goto L_0x0078
        L_0x0073:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            goto L_0x0078
        L_0x0076:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
        L_0x0078:
            if (r8 != r0) goto L_0x007b
            return r0
        L_0x007b:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.DashActivity$main$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
