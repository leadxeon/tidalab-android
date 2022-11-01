package com.tidalab.v2board.clash.service;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.clash.module.CloseModule;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: TunService.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.TunService$runtime$1$quit$1$1", f = "TunService.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class TunService$runtime$1$quit$1$1 extends SuspendLambda implements Function2<CloseModule.RequestClose, Continuation<? super Boolean>, Object> {
    public TunService$runtime$1$quit$1$1(Continuation<? super TunService$runtime$1$quit$1$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TunService$runtime$1$quit$1$1(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CloseModule.RequestClose requestClose, Continuation<? super Boolean> continuation) {
        Continuation<? super Boolean> continuation2 = continuation;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return Boolean.TRUE;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return Boolean.TRUE;
    }
}
