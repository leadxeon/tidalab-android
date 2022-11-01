package com.tidalab.v2board.clash.remote;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: Remote.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.remote.Remote$launch$2", f = "Remote.kt", l = {35}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class Remote$launch$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;

    public Remote$launch$2(Continuation<? super Remote$launch$2> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new Remote$launch$2(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new Remote$launch$2(continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            Remote remote = Remote.INSTANCE;
            this.label = 1;
            if (Remote.access$run(remote, this) == coroutineSingletons) {
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
