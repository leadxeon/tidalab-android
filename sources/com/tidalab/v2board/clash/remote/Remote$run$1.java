package com.tidalab.v2board.clash.remote;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: Remote.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.remote.Remote", f = "Remote.kt", l = {58}, m = "run")
/* loaded from: classes.dex */
public final class Remote$run$1 extends ContinuationImpl {
    public Object L$0;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ Remote this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Remote$run$1(Remote remote, Continuation<? super Remote$run$1> continuation) {
        super(continuation);
        this.this$0 = remote;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return Remote.access$run(this.this$0, this);
    }
}
