package com.tidalab.v2board.clash.util;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: Activity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.util.ActivityResultLifecycle", f = "Activity.kt", l = {24, 26, 26}, m = "use")
/* loaded from: classes.dex */
public final class ActivityResultLifecycle$use$1<T> extends ContinuationImpl {
    public Object L$0;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ ActivityResultLifecycle this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityResultLifecycle$use$1(ActivityResultLifecycle activityResultLifecycle, Continuation<? super ActivityResultLifecycle$use$1> continuation) {
        super(continuation);
        this.this$0 = activityResultLifecycle;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.use(null, this);
    }
}
