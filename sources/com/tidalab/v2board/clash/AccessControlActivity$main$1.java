package com.tidalab.v2board.clash;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: AccessControlActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.AccessControlActivity", f = "AccessControlActivity.kt", l = {23, 35, 37, 146}, m = "main")
/* loaded from: classes.dex */
public final class AccessControlActivity$main$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ AccessControlActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessControlActivity$main$1(AccessControlActivity accessControlActivity, Continuation<? super AccessControlActivity$main$1> continuation) {
        super(continuation);
        this.this$0 = accessControlActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.main(this);
    }
}
