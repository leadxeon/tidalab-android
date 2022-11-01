package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
/* compiled from: BaseActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.BaseActivity$defer$1", f = "BaseActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class BaseActivity$defer$1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    public BaseActivity$defer$1(Continuation<? super BaseActivity$defer$1> continuation) {
        super(1, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public Object invoke(Continuation<? super Unit> continuation) {
        Continuation<? super Unit> continuation2 = continuation;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return Unit.INSTANCE;
    }
}
