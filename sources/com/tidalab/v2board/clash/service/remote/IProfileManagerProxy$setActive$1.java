package com.tidalab.v2board.clash.service.remote;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: IProfileManager.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.remote.IProfileManagerProxy", f = "IProfileManager.kt", l = {416}, m = "setActive")
/* loaded from: classes.dex */
public final class IProfileManagerProxy$setActive$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ IProfileManagerProxy this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IProfileManagerProxy$setActive$1(IProfileManagerProxy iProfileManagerProxy, Continuation<? super IProfileManagerProxy$setActive$1> continuation) {
        super(continuation);
        this.this$0 = iProfileManagerProxy;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.setActive(null, this);
    }
}
