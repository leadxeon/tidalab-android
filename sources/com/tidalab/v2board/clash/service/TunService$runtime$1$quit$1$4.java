package com.tidalab.v2board.clash.service;

import android.net.Network;
import android.os.Build;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.clash.module.ConfigurationModule;
import com.tidalab.v2board.clash.service.clash.module.NetworkObserveModule;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: TunService.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.TunService$runtime$1$quit$1$4", f = "TunService.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class TunService$runtime$1$quit$1$4 extends SuspendLambda implements Function2<NetworkObserveModule.NetworkChanged, Continuation<? super Boolean>, Object> {
    public final /* synthetic */ ConfigurationModule $config;
    public /* synthetic */ Object L$0;
    public final /* synthetic */ TunService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TunService$runtime$1$quit$1$4(TunService tunService, ConfigurationModule configurationModule, Continuation<? super TunService$runtime$1$quit$1$4> continuation) {
        super(2, continuation);
        this.this$0 = tunService;
        this.$config = configurationModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TunService$runtime$1$quit$1$4 tunService$runtime$1$quit$1$4 = new TunService$runtime$1$quit$1$4(this.this$0, this.$config, continuation);
        tunService$runtime$1$quit$1$4.L$0 = obj;
        return tunService$runtime$1$quit$1$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(NetworkObserveModule.NetworkChanged networkChanged, Continuation<? super Boolean> continuation) {
        NetworkObserveModule.NetworkChanged networkChanged2 = networkChanged;
        Continuation<? super Boolean> continuation2 = continuation;
        TunService tunService = this.this$0;
        ConfigurationModule configurationModule = this.$config;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        if (Build.VERSION.SDK_INT >= 22) {
            Network network = networkChanged2.network;
            tunService.setUnderlyingNetworks(network == null ? null : new Network[]{network});
        }
        configurationModule.reload.mo14trySendJP2dKIU(unit);
        return Boolean.FALSE;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        NetworkObserveModule.NetworkChanged networkChanged = (NetworkObserveModule.NetworkChanged) this.L$0;
        if (Build.VERSION.SDK_INT >= 22) {
            TunService tunService = this.this$0;
            Network network = networkChanged.network;
            tunService.setUnderlyingNetworks(network == null ? null : new Network[]{network});
        }
        this.$config.reload.mo14trySendJP2dKIU(Unit.INSTANCE);
        return Boolean.FALSE;
    }
}
