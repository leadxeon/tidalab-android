package com.tidalab.v2board.clash.service.document;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: Picker.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.document.Picker", f = "Picker.kt", l = {52, 55, 56}, m = "pick")
/* loaded from: classes.dex */
public final class Picker$pick$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public boolean Z$0;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ Picker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Picker$pick$1(Picker picker, Continuation<? super Picker$pick$1> continuation) {
        super(continuation);
        this.this$0 = picker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.pick(null, false, this);
    }
}
