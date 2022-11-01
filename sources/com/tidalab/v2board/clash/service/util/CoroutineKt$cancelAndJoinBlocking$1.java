package com.tidalab.v2board.clash.service.util;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
/* compiled from: Coroutine.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.util.CoroutineKt$cancelAndJoinBlocking$1", f = "Coroutine.kt", l = {12}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class CoroutineKt$cancelAndJoinBlocking$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ CoroutineScope $scope;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineKt$cancelAndJoinBlocking$1(CoroutineScope coroutineScope, Continuation<? super CoroutineKt$cancelAndJoinBlocking$1> continuation) {
        super(2, continuation);
        this.$scope = coroutineScope;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CoroutineKt$cancelAndJoinBlocking$1(this.$scope, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new CoroutineKt$cancelAndJoinBlocking$1(this.$scope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            InputKt.cancel$default(InputKt.getJob(this.$scope.getCoroutineContext()), null, 1, null);
            Job job = InputKt.getJob(this.$scope.getCoroutineContext());
            this.label = 1;
            if (job.join(this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
