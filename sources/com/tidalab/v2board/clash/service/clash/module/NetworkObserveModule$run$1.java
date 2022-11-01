package com.tidalab.v2board.clash.service.clash.module;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: NetworkObserveModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.module.NetworkObserveModule", f = "NetworkObserveModule.kt", l = {83, 86, 94, 97}, m = "run")
/* loaded from: classes.dex */
public final class NetworkObserveModule$run$1 extends ContinuationImpl {
    public Object L$0;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ NetworkObserveModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkObserveModule$run$1(NetworkObserveModule networkObserveModule, Continuation<? super NetworkObserveModule$run$1> continuation) {
        super(continuation);
        this.this$0 = networkObserveModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.run(this);
    }
}
