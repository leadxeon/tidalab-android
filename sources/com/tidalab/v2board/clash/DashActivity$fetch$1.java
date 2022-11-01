package com.tidalab.v2board.clash;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: DashActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.DashActivity", f = "DashActivity.kt", l = {93, 95, 98, 102, 103, 105}, m = "fetch")
/* loaded from: classes.dex */
public final class DashActivity$fetch$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ DashActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DashActivity$fetch$1(DashActivity dashActivity, Continuation<? super DashActivity$fetch$1> continuation) {
        super(continuation);
        this.this$0 = dashActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        DashActivity dashActivity = this.this$0;
        int i = DashActivity.$r8$clinit;
        return dashActivity.fetch(null, this);
    }
}
