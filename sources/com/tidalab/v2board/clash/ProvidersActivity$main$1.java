package com.tidalab.v2board.clash;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import okhttp3.internal.ws.WebSocketProtocol;
/* compiled from: ProvidersActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.ProvidersActivity", f = "ProvidersActivity.kt", l = {WebSocketProtocol.B0_MASK_OPCODE, 18, 75}, m = "main")
/* loaded from: classes.dex */
public final class ProvidersActivity$main$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public Object L$3;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ ProvidersActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProvidersActivity$main$1(ProvidersActivity providersActivity, Continuation<? super ProvidersActivity$main$1> continuation) {
        super(continuation);
        this.this$0 = providersActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.main(this);
    }
}
