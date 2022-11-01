package com.tidalab.v2board.clash.service.clash.module;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: ConfigurationModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.module.ConfigurationModule$run$changed$1$2", f = "ConfigurationModule.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ConfigurationModule$run$changed$1$2 extends SuspendLambda implements Function2<Unit, Continuation<? super UUID>, Object> {
    public ConfigurationModule$run$changed$1$2(Continuation<? super ConfigurationModule$run$changed$1$2> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ConfigurationModule$run$changed$1$2(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Unit unit, Continuation<? super UUID> continuation) {
        Continuation<? super UUID> continuation2 = continuation;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return null;
    }
}
