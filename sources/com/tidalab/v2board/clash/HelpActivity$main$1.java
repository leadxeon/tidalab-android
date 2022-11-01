package com.tidalab.v2board.clash;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import okhttp3.internal.ws.WebSocketProtocol;
/* compiled from: HelpActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.HelpActivity", f = "HelpActivity.kt", l = {13, WebSocketProtocol.B0_FLAG_RSV3}, m = "main")
/* loaded from: classes.dex */
public final class HelpActivity$main$1 extends ContinuationImpl {
    public Object L$0;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ HelpActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HelpActivity$main$1(HelpActivity helpActivity, Continuation<? super HelpActivity$main$1> continuation) {
        super(continuation);
        this.this$0 = helpActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.main(this);
    }
}
