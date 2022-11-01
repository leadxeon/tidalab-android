package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.core.model.ProviderList;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: DashActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.DashActivity$fetch$providers$1", f = "DashActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class DashActivity$fetch$providers$1 extends SuspendLambda implements Function2<IClashManager, Continuation<? super ProviderList>, Object> {
    public /* synthetic */ Object L$0;

    public DashActivity$fetch$providers$1(Continuation<? super DashActivity$fetch$providers$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DashActivity$fetch$providers$1 dashActivity$fetch$providers$1 = new DashActivity$fetch$providers$1(continuation);
        dashActivity$fetch$providers$1.L$0 = obj;
        return dashActivity$fetch$providers$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(IClashManager iClashManager, Continuation<? super ProviderList> continuation) {
        IClashManager iClashManager2 = iClashManager;
        Continuation<? super ProviderList> continuation2 = continuation;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return iClashManager2.queryProviders();
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return ((IClashManager) this.L$0).queryProviders();
    }
}
