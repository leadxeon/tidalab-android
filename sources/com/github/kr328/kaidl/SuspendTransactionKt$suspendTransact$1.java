package com.github.kr328.kaidl;

import com.facebook.react.R$style;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: SuspendTransaction.kt */
@DebugMetadata(c = "com.github.kr328.kaidl.SuspendTransactionKt", f = "SuspendTransaction.kt", l = {190, 114, 114}, m = "suspendTransact")
/* loaded from: classes.dex */
public final class SuspendTransactionKt$suspendTransact$1 extends ContinuationImpl {
    public int I$0;
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public Object L$3;
    public boolean Z$0;
    public int label;
    public /* synthetic */ Object result;

    public SuspendTransactionKt$suspendTransact$1(Continuation<? super SuspendTransactionKt$suspendTransact$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return R$style.suspendTransact(null, 0, null, null, this);
    }
}
