package com.tidalab.v2board.clash;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import okhttp3.internal.ws.WebSocketProtocol;
/* compiled from: AppCrashedActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.AppCrashedActivity", f = "AppCrashedActivity.kt", l = {WebSocketProtocol.B0_MASK_OPCODE, 17, 23, 30}, m = "main")
/* loaded from: classes.dex */
public final class AppCrashedActivity$main$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ AppCrashedActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCrashedActivity$main$1(AppCrashedActivity appCrashedActivity, Continuation<? super AppCrashedActivity$main$1> continuation) {
        super(continuation);
        this.this$0 = appCrashedActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.main(this);
    }
}
