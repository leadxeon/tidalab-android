package com.tidalab.v2board.clash.design.dialog;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: Input.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.dialog.InputKt", f = "Input.kt", l = {20}, m = "requestModelTextInput")
/* loaded from: classes.dex */
public final class InputKt$requestModelTextInput$1 extends ContinuationImpl {
    public int label;
    public /* synthetic */ Object result;

    public InputKt$requestModelTextInput$1(Continuation<? super InputKt$requestModelTextInput$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return InputKt.requestModelTextInput(null, null, null, null, null, null, this);
    }
}
