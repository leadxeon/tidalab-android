package com.tidalab.v2board.clash.log;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: LogcatCache.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.log.LogcatCache", f = "LogcatCache.kt", l = {59}, m = "snapshot")
/* loaded from: classes.dex */
public final class LogcatCache$snapshot$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public boolean Z$0;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ LogcatCache this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogcatCache$snapshot$1(LogcatCache logcatCache, Continuation<? super LogcatCache$snapshot$1> continuation) {
        super(continuation);
        this.this$0 = logcatCache;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.snapshot(false, this);
    }
}
