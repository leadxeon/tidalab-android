package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.dialog.ModelProgressBarConfigure;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: LogcatActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.LogcatActivity$writeLogTo$2$1$1$2$1$1", f = "LogcatActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class LogcatActivity$writeLogTo$2$1$1$2$1$1 extends SuspendLambda implements Function2<ModelProgressBarConfigure, Continuation<? super Unit>, Object> {
    public final /* synthetic */ int $idx;
    public /* synthetic */ Object L$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogcatActivity$writeLogTo$2$1$1$2$1$1(int i, Continuation<? super LogcatActivity$writeLogTo$2$1$1$2$1$1> continuation) {
        super(2, continuation);
        this.$idx = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        LogcatActivity$writeLogTo$2$1$1$2$1$1 logcatActivity$writeLogTo$2$1$1$2$1$1 = new LogcatActivity$writeLogTo$2$1$1$2$1$1(this.$idx, continuation);
        logcatActivity$writeLogTo$2$1$1$2$1$1.L$0 = obj;
        return logcatActivity$writeLogTo$2$1$1$2$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(ModelProgressBarConfigure modelProgressBarConfigure, Continuation<? super Unit> continuation) {
        ModelProgressBarConfigure modelProgressBarConfigure2 = modelProgressBarConfigure;
        Continuation<? super Unit> continuation2 = continuation;
        int i = this.$idx;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        modelProgressBarConfigure2.setIndeterminate(false);
        modelProgressBarConfigure2.setProgress(i);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        ModelProgressBarConfigure modelProgressBarConfigure = (ModelProgressBarConfigure) this.L$0;
        modelProgressBarConfigure.setIndeterminate(false);
        modelProgressBarConfigure.setProgress(this.$idx);
        return Unit.INSTANCE;
    }
}
