package com.tidalab.v2board.clash;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: DashActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.DashActivity", f = "DashActivity.kt", l = {117, 120, 133, 142}, m = "startClash")
/* loaded from: classes.dex */
public final class DashActivity$startClash$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ DashActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DashActivity$startClash$1(DashActivity dashActivity, Continuation<? super DashActivity$startClash$1> continuation) {
        super(continuation);
        this.this$0 = dashActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return DashActivity.access$startClash(this.this$0, null, this);
    }
}
