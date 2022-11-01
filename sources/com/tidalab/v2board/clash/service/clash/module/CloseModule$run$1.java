package com.tidalab.v2board.clash.service.clash.module;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import okhttp3.internal.ws.WebSocketProtocol;
/* compiled from: CloseModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.module.CloseModule", f = "CloseModule.kt", l = {WebSocketProtocol.B0_MASK_OPCODE, 19}, m = "run")
/* loaded from: classes.dex */
public final class CloseModule$run$1 extends ContinuationImpl {
    public Object L$0;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ CloseModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CloseModule$run$1(CloseModule closeModule, Continuation<? super CloseModule$run$1> continuation) {
        super(continuation);
        this.this$0 = closeModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.run(this);
    }
}
