package com.tidalab.v2board.clash.service.clash.module;

import com.tidalab.v2board.clash.core.Clash;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: NetworkObserveModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.module.NetworkObserveModule$run$2", f = "NetworkObserveModule.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class NetworkObserveModule$run$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ NetworkObserveModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkObserveModule$run$2(NetworkObserveModule networkObserveModule, Continuation<? super NetworkObserveModule$run$2> continuation) {
        super(2, continuation);
        this.this$0 = networkObserveModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NetworkObserveModule$run$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        Continuation<? super Unit> continuation2 = continuation;
        NetworkObserveModule networkObserveModule = this.this$0;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        networkObserveModule.connectivity.unregisterNetworkCallback(networkObserveModule.callback);
        Clash.INSTANCE.notifyDnsChanged(EmptyList.INSTANCE);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        NetworkObserveModule networkObserveModule = this.this$0;
        networkObserveModule.connectivity.unregisterNetworkCallback(networkObserveModule.callback);
        Clash.INSTANCE.notifyDnsChanged(EmptyList.INSTANCE);
        return Unit.INSTANCE;
    }
}
