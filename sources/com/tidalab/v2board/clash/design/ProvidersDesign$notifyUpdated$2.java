package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.adapter.ProviderAdapter;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: ProvidersDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.ProvidersDesign$notifyUpdated$2", f = "ProvidersDesign.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProvidersDesign$notifyUpdated$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ int $index;
    public final /* synthetic */ ProvidersDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProvidersDesign$notifyUpdated$2(ProvidersDesign providersDesign, int i, Continuation<? super ProvidersDesign$notifyUpdated$2> continuation) {
        super(2, continuation);
        this.this$0 = providersDesign;
        this.$index = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProvidersDesign$notifyUpdated$2(this.this$0, this.$index, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        Continuation<? super Unit> continuation2 = continuation;
        ProvidersDesign providersDesign = this.this$0;
        int i = this.$index;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        ProviderAdapter providerAdapter = providersDesign.adapter;
        providerAdapter.states.get(i).setUpdating(false);
        providerAdapter.mObservable.notifyItemRangeChanged(i, 1, null);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        ProviderAdapter providerAdapter = this.this$0.adapter;
        int i = this.$index;
        providerAdapter.states.get(i).setUpdating(false);
        providerAdapter.mObservable.notifyItemRangeChanged(i, 1, null);
        return Unit.INSTANCE;
    }
}
