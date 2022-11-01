package com.tidalab.v2board.clash;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: LogcatActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.LogcatActivity", f = "LogcatActivity.kt", l = {158}, m = "writeLogTo")
/* loaded from: classes.dex */
public final class LogcatActivity$writeLogTo$1 extends ContinuationImpl {
    public Object L$0;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ LogcatActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogcatActivity$writeLogTo$1(LogcatActivity logcatActivity, Continuation<? super LogcatActivity$writeLogTo$1> continuation) {
        super(continuation);
        this.this$0 = logcatActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return LogcatActivity.access$writeLogTo(this.this$0, null, null, null, this);
    }
}
