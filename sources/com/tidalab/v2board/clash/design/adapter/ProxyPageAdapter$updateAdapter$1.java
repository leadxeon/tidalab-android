package com.tidalab.v2board.clash.design.adapter;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: ProxyPageAdapter.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.adapter.ProxyPageAdapter", f = "ProxyPageAdapter.kt", l = {35, 43}, m = "updateAdapter")
/* loaded from: classes.dex */
public final class ProxyPageAdapter$updateAdapter$1 extends ContinuationImpl {
    public int I$0;
    public Object L$0;
    public boolean Z$0;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ ProxyPageAdapter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProxyPageAdapter$updateAdapter$1(ProxyPageAdapter proxyPageAdapter, Continuation<? super ProxyPageAdapter$updateAdapter$1> continuation) {
        super(continuation);
        this.this$0 = proxyPageAdapter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.updateAdapter(0, null, false, null, null, this);
    }
}
