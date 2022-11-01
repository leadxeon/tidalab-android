package com.tidalab.v2board.clash.service.clash.module;

import android.content.Intent;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: SideloadDatabaseModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.module.SideloadDatabaseModule$run$2$2", f = "SideloadDatabaseModule.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class SideloadDatabaseModule$run$2$2 extends SuspendLambda implements Function2<Intent, Continuation<? super Pair<? extends Boolean, ? extends Boolean>>, Object> {
    public SideloadDatabaseModule$run$2$2(Continuation<? super SideloadDatabaseModule$run$2$2> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SideloadDatabaseModule$run$2$2(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Intent intent, Continuation<? super Pair<? extends Boolean, ? extends Boolean>> continuation) {
        Continuation<? super Pair<? extends Boolean, ? extends Boolean>> continuation2 = continuation;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return new Pair(Boolean.TRUE, Boolean.FALSE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return new Pair(Boolean.TRUE, Boolean.FALSE);
    }
}
