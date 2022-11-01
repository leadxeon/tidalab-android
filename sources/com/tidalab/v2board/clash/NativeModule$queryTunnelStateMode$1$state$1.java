package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.core.model.TunnelState;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: NativeModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.NativeModule$queryTunnelStateMode$1$state$1", f = "NativeModule.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class NativeModule$queryTunnelStateMode$1$state$1 extends SuspendLambda implements Function2<IClashManager, Continuation<? super TunnelState>, Object> {
    public /* synthetic */ Object L$0;

    public NativeModule$queryTunnelStateMode$1$state$1(Continuation<? super NativeModule$queryTunnelStateMode$1$state$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NativeModule$queryTunnelStateMode$1$state$1 nativeModule$queryTunnelStateMode$1$state$1 = new NativeModule$queryTunnelStateMode$1$state$1(continuation);
        nativeModule$queryTunnelStateMode$1$state$1.L$0 = obj;
        return nativeModule$queryTunnelStateMode$1$state$1;
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
