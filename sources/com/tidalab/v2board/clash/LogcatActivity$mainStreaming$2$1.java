package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.BaseActivity;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: LogcatActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.LogcatActivity$mainStreaming$2$1", f = "LogcatActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class LogcatActivity$mainStreaming$2$1 extends SuspendLambda implements Function2<BaseActivity.Event, Continuation<? super Unit>, Object> {
    public LogcatActivity$mainStreaming$2$1(Continuation<? super LogcatActivity$mainStreaming$2$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LogcatActivity$mainStreaming$2$1(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(BaseActivity.Event event, Continuation<? super Unit> continuation) {
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
