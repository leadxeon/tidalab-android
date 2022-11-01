package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.core.model.TunnelState;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: DashActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.DashActivity$fetch$state$1", f = "DashActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class DashActivity$fetch$state$1 extends SuspendLambda implements Function2<IClashManager, Continuation<? super TunnelState>, Object> {
    public /* synthetic */ Object L$0;

    public DashActivity$fetch$state$1(Continuation<? super DashActivity$fetch$state$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DashActivity$fetch$state$1 dashActivity$fetch$state$1 = new DashActivity$fetch$state$1(continuation);
        dashActivity$fetch$state$1.L$0 = obj;
        return dashActivity$fetch$state$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(IClashManager iClashManager, Continuation<? super TunnelState> continuation) {
        IClashManager iClashManager2 = iClashManager;
        Continuation<? super TunnelState> continuation2 = continuation;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return iClashManager2.queryTunnelState();
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return ((IClashManager) this.L$0).queryTunnelState();
    }
}
