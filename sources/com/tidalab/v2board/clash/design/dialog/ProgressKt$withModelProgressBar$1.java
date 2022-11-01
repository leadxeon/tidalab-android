package com.tidalab.v2board.clash.design.dialog;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: Progress.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.dialog.ProgressKt", f = "Progress.kt", l = {61}, m = "withModelProgressBar")
/* loaded from: classes.dex */
public final class ProgressKt$withModelProgressBar$1 extends ContinuationImpl {
    public Object L$0;
    public int label;
    public /* synthetic */ Object result;

    public ProgressKt$withModelProgressBar$1(Continuation<? super ProgressKt$withModelProgressBar$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return InputKt.withModelProgressBar(null, null, this);
    }
}
