package com.tidalab.v2board.clash;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: LogcatActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.LogcatActivity", f = "LogcatActivity.kt", l = {55, 57, 60, 62, 69, 76, 80, 82}, m = "mainLocalFile")
/* loaded from: classes.dex */
public final class LogcatActivity$mainLocalFile$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public Object L$3;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ LogcatActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogcatActivity$mainLocalFile$1(LogcatActivity logcatActivity, Continuation<? super LogcatActivity$mainLocalFile$1> continuation) {
        super(continuation);
        this.this$0 = logcatActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        LogcatActivity logcatActivity = this.this$0;
        int i = LogcatActivity.$r8$clinit;
        return logcatActivity.mainLocalFile(null, this);
    }
}
