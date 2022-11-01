package com.tidalab.v2board.clash;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: ProxyActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.ProxyActivity", f = "ProxyActivity.kt", l = {20, 21, 34, 47, 142}, m = "main")
/* loaded from: classes.dex */
public final class ProxyActivity$main$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public Object L$3;
    public Object L$4;
    public Object L$5;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ ProxyActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProxyActivity$main$1(ProxyActivity proxyActivity, Continuation<? super ProxyActivity$main$1> continuation) {
        super(continuation);
        this.this$0 = proxyActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.main(this);
    }
}
