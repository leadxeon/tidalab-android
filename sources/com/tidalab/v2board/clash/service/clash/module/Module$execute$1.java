package com.tidalab.v2board.clash.service.clash.module;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: Module.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.module.Module", f = "Module.kt", l = {63, 65, 65}, m = "execute")
/* loaded from: classes.dex */
public final class Module$execute$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ Module<E> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Module$execute$1(Module<E> module, Continuation<? super Module$execute$1> continuation) {
        super(continuation);
        this.this$0 = module;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.execute(this);
    }
}
