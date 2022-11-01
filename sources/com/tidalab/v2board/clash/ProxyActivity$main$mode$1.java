package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.core.Clash;
import com.tidalab.v2board.clash.core.model.TunnelState;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: ProxyActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.ProxyActivity$main$mode$1", f = "ProxyActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProxyActivity$main$mode$1 extends SuspendLambda implements Function2<IClashManager, Continuation<? super TunnelState.Mode>, Object> {
    public /* synthetic */ Object L$0;

    public ProxyActivity$main$mode$1(Continuation<? super ProxyActivity$main$mode$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ProxyActivity$main$mode$1 proxyActivity$main$mode$1 = new ProxyActivity$main$mode$1(continuation);
        proxyActivity$main$mode$1.L$0 = obj;
        return proxyActivity$main$mode$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(IClashManager iClashManager, Continuation<? super TunnelState.Mode> continuation) {
        IClashManager iClashManager2 = iClashManager;
        Continuation<? super TunnelState.Mode> continuation2 = continuation;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return iClashManager2.queryOverride(Clash.OverrideSlot.Session).mode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return ((IClashManager) this.L$0).queryOverride(Clash.OverrideSlot.Session).mode;
    }
}
