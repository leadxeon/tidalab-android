package com.tidalab.v2board.clash.service.clash.module;

import com.tidalab.v2board.clash.core.Clash;
import com.tidalab.v2board.clash.core.bridge.Bridge;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: SuspendModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.module.SuspendModule$run$2", f = "SuspendModule.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class SuspendModule$run$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public SuspendModule$run$2(Continuation<? super SuspendModule$run$2> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SuspendModule$run$2(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        Continuation<? super Unit> continuation2 = continuation;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        Clash clash = Clash.INSTANCE;
        Bridge.INSTANCE.nativeSuspend(false);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        Clash clash = Clash.INSTANCE;
        Bridge.INSTANCE.nativeSuspend(false);
        return Unit.INSTANCE;
    }
}
