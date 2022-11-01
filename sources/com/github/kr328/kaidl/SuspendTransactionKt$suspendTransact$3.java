package com.github.kr328.kaidl;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: SuspendTransaction.kt */
@DebugMetadata(c = "com.github.kr328.kaidl.SuspendTransactionKt$suspendTransact$3", f = "SuspendTransaction.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class SuspendTransactionKt$suspendTransact$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Ref$ObjectRef<Function0<Unit>> $finalizer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SuspendTransactionKt$suspendTransact$3(Ref$ObjectRef<Function0<Unit>> ref$ObjectRef, Continuation<? super SuspendTransactionKt$suspendTransact$3> continuation) {
        super(2, continuation);
        this.$finalizer = ref$ObjectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SuspendTransactionKt$suspendTransact$3(this.$finalizer, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        Continuation<? super Unit> continuation2 = continuation;
        Ref$ObjectRef<Function0<Unit>> ref$ObjectRef = this.$finalizer;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        ref$ObjectRef.element.invoke();
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        this.$finalizer.element.invoke();
        return Unit.INSTANCE;
    }
}
