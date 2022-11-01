package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.log.SystemLogcat;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: AppCrashedActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.AppCrashedActivity$main$logs$1", f = "AppCrashedActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class AppCrashedActivity$main$logs$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    public AppCrashedActivity$main$logs$1(Continuation<? super AppCrashedActivity$main$logs$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AppCrashedActivity$main$logs$1(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        Continuation<? super String> continuation2 = continuation;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return SystemLogcat.dumpCrash();
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return SystemLogcat.dumpCrash();
    }
}
