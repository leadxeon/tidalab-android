package com.tidalab.v2board.clash.service.clash.module;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: SuspendModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.module.SuspendModule", f = "SuspendModule.kt", l = {26, 45}, m = "run")
/* loaded from: classes.dex */
public final class SuspendModule$run$1 extends ContinuationImpl {
    public Object L$0;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ SuspendModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SuspendModule$run$1(SuspendModule suspendModule, Continuation<? super SuspendModule$run$1> continuation) {
        super(continuation);
        this.this$0 = suspendModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.run(this);
    }
}
