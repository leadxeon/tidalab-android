package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.ProvidersDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: ProvidersActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.ProvidersActivity$main$2$3", f = "ProvidersActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProvidersActivity$main$2$3 extends SuspendLambda implements Function2<Long, Continuation<? super Unit>, Object> {
    public final /* synthetic */ ProvidersDesign $design;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProvidersActivity$main$2$3(ProvidersDesign providersDesign, Continuation<? super ProvidersActivity$main$2$3> continuation) {
        super(2, continuation);
        this.$design = providersDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProvidersActivity$main$2$3(this.$design, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Long l, Continuation<? super Unit> continuation) {
        l.longValue();
        Continuation<? super Unit> continuation2 = continuation;
        ProvidersDesign providersDesign = this.$design;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        providersDesign.adapter.currentTime.update();
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        this.$design.adapter.currentTime.update();
        return Unit.INSTANCE;
    }
}
