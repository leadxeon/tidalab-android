package com.tidalab.v2board.clash.service.remote;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: IClashManager.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.remote.IClashManagerProxy", f = "IClashManager.kt", l = {329}, m = "healthCheck")
/* loaded from: classes.dex */
public final class IClashManagerProxy$healthCheck$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ IClashManagerProxy this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IClashManagerProxy$healthCheck$1(IClashManagerProxy iClashManagerProxy, Continuation<? super IClashManagerProxy$healthCheck$1> continuation) {
        super(continuation);
        this.this$0 = iClashManagerProxy;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.healthCheck(null, this);
    }
}
