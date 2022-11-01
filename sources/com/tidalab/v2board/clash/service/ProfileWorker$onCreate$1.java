package com.tidalab.v2board.clash.service;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: ProfileWorker.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.ProfileWorker$onCreate$1", f = "ProfileWorker.kt", l = {38, 41}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProfileWorker$onCreate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ ProfileWorker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileWorker$onCreate$1(ProfileWorker profileWorker, Continuation<? super ProfileWorker$onCreate$1> continuation) {
        super(2, continuation);
        this.this$0 = profileWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProfileWorker$onCreate$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new ProfileWorker$onCreate$1(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0057  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0048 -> B:25:0x0055). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0050 -> B:24:0x0053). Please submit an issue!!! */
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
            r2 = 0
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x001e
            if (r1 == r4) goto L_0x001a
            if (r1 != r3) goto L_0x0012
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            r8 = r7
            goto L_0x0053
        L_0x0012:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x001a:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            goto L_0x0032
        L_0x001e:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.SECONDS
            r5 = 10
            long r5 = r8.toMillis(r5)
            r7.label = r4
            java.lang.Object r8 = com.tidalab.v2board.clash.design.dialog.InputKt.delay(r5, r7)
            if (r8 != r0) goto L_0x0032
            return r0
        L_0x0032:
            r8 = r7
        L_0x0033:
            com.tidalab.v2board.clash.service.ProfileWorker r1 = r8.this$0
            java.util.List<kotlinx.coroutines.Job> r1 = r1.jobs
            boolean r4 = r1.isEmpty()
            if (r4 == 0) goto L_0x003f
            r1 = r2
            goto L_0x0044
        L_0x003f:
            r4 = 0
            java.lang.Object r1 = r1.remove(r4)
        L_0x0044:
            kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
            if (r1 != 0) goto L_0x004a
            r1 = r2
            goto L_0x0055
        L_0x004a:
            r8.label = r3
            java.lang.Object r1 = r1.join(r8)
            if (r1 != r0) goto L_0x0053
            return r0
        L_0x0053:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
        L_0x0055:
            if (r1 != 0) goto L_0x0033
            com.tidalab.v2board.clash.service.ProfileWorker r8 = r8.this$0
            r8.stopSelf()
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.ProfileWorker$onCreate$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
