package com.tidalab.v2board.clash.design;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: PropertiesDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.PropertiesDesign", f = "PropertiesDesign.kt", l = {45}, m = "withProcessing")
/* loaded from: classes.dex */
public final class PropertiesDesign$withProcessing$1 extends ContinuationImpl {
    public Object L$0;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ PropertiesDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PropertiesDesign$withProcessing$1(PropertiesDesign propertiesDesign, Continuation<? super PropertiesDesign$withProcessing$1> continuation) {
        super(continuation);
        this.this$0 = propertiesDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.withProcessing(null, this);
    }
}
