package com.tidalab.v2board.clash.design;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: ProxyDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.ProxyDesign", f = "ProxyDesign.kt", l = {74}, m = "updateGroup")
/* loaded from: classes.dex */
public final class ProxyDesign$updateGroup$1 extends ContinuationImpl {
    public int I$0;
    public Object L$0;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ ProxyDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProxyDesign$updateGroup$1(ProxyDesign proxyDesign, Continuation<? super ProxyDesign$updateGroup$1> continuation) {
        super(continuation);
        this.this$0 = proxyDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.updateGroup(0, null, false, null, null, this);
    }
}
