package com.tidalab.v2board.clash;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import okhttp3.internal.ws.WebSocketProtocol;
/* compiled from: ApkBrokenActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.ApkBrokenActivity", f = "ApkBrokenActivity.kt", l = {12, WebSocketProtocol.B0_MASK_OPCODE}, m = "main")
/* loaded from: classes.dex */
public final class ApkBrokenActivity$main$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ ApkBrokenActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ApkBrokenActivity$main$1(ApkBrokenActivity apkBrokenActivity, Continuation<? super ApkBrokenActivity$main$1> continuation) {
        super(continuation);
        this.this$0 = apkBrokenActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.main(this);
    }
}
