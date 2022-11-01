package com.tidalab.v2board.clash;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: PropertiesActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.PropertiesActivity", f = "PropertiesActivity.kt", l = {84, 87, 91, 109}, m = "verifyAndCommit")
/* loaded from: classes.dex */
public final class PropertiesActivity$verifyAndCommit$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ PropertiesActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PropertiesActivity$verifyAndCommit$1(PropertiesActivity propertiesActivity, Continuation<? super PropertiesActivity$verifyAndCommit$1> continuation) {
        super(continuation);
        this.this$0 = propertiesActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return PropertiesActivity.access$verifyAndCommit(this.this$0, null, this);
    }
}
