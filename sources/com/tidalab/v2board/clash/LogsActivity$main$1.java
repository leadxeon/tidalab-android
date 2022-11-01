package com.tidalab.v2board.clash;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: LogsActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.LogsActivity", f = "LogsActivity.kt", l = {21, 75}, m = "main")
/* loaded from: classes.dex */
public final class LogsActivity$main$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ LogsActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogsActivity$main$1(LogsActivity logsActivity, Continuation<? super LogsActivity$main$1> continuation) {
        super(continuation);
        this.this$0 = logsActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.main(this);
    }
}
