package com.tidalab.v2board.clash.service;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.clash.module.ConfigurationModule;
import com.tidalab.v2board.clash.service.clash.module.NetworkObserveModule;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: ClashService.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.ClashService$runtime$1$quit$1$4", f = "ClashService.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ClashService$runtime$1$quit$1$4 extends SuspendLambda implements Function2<NetworkObserveModule.NetworkChanged, Continuation<? super Boolean>, Object> {
    public final /* synthetic */ ConfigurationModule $config;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClashService$runtime$1$quit$1$4(ConfigurationModule configurationModule, Continuation<? super ClashService$runtime$1$quit$1$4> continuation) {
        super(2, continuation);
        this.$config = configurationModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ClashService$runtime$1$quit$1$4(this.$config, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(NetworkObserveModule.NetworkChanged networkChanged, Continuation<? super Boolean> continuation) {
        Continuation<? super Boolean> continuation2 = continuation;
        ConfigurationModule configurationModule = this.$config;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        configurationModule.reload.mo14trySendJP2dKIU(unit);
        return Boolean.FALSE;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        this.$config.reload.mo14trySendJP2dKIU(Unit.INSTANCE);
        return Boolean.FALSE;
    }
}
