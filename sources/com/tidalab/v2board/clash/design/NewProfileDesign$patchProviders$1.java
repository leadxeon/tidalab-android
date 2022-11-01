package com.tidalab.v2board.clash.design;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: NewProfileDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.NewProfileDesign", f = "NewProfileDesign.kt", l = {25}, m = "patchProviders")
/* loaded from: classes.dex */
public final class NewProfileDesign$patchProviders$1 extends ContinuationImpl {
    public Object L$0;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ NewProfileDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NewProfileDesign$patchProviders$1(NewProfileDesign newProfileDesign, Continuation<? super NewProfileDesign$patchProviders$1> continuation) {
        super(continuation);
        this.this$0 = newProfileDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.patchProviders(null, this);
    }
}
