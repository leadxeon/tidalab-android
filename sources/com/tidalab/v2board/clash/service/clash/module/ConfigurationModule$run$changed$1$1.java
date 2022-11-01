package com.tidalab.v2board.clash.service.clash.module;

import android.content.Intent;
import com.tidalab.v2board.clash.common.constants.Intents;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ConfigurationModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.module.ConfigurationModule$run$changed$1$1", f = "ConfigurationModule.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ConfigurationModule$run$changed$1$1 extends SuspendLambda implements Function2<Intent, Continuation<? super UUID>, Object> {
    public /* synthetic */ Object L$0;

    public ConfigurationModule$run$changed$1$1(Continuation<? super ConfigurationModule$run$changed$1$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ConfigurationModule$run$changed$1$1 configurationModule$run$changed$1$1 = new ConfigurationModule$run$changed$1$1(continuation);
        configurationModule$run$changed$1$1.L$0 = obj;
        return configurationModule$run$changed$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Intent intent, Continuation<? super UUID> continuation) {
        Intent intent2 = intent;
        Continuation<? super UUID> continuation2 = continuation;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        String action = intent2.getAction();
        Intents intents = Intents.INSTANCE;
        if (Intrinsics.areEqual(action, Intents.ACTION_PROFILE_CHANGED)) {
            return UUID.fromString(intent2.getStringExtra("uuid"));
        }
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        Intent intent = (Intent) this.L$0;
        String action = intent.getAction();
        Intents intents = Intents.INSTANCE;
        if (Intrinsics.areEqual(action, Intents.ACTION_PROFILE_CHANGED)) {
            return UUID.fromString(intent.getStringExtra("uuid"));
        }
        return null;
    }
}
