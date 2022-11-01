package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.MainDesign;
import com.tidalab.v2board.clash.design.MainDesign$setForwarded$2;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import java.util.Objects;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.internal.MainDispatcherLoader;
/* compiled from: DashActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.DashActivity$fetchTraffic$2", f = "DashActivity.kt", l = {112}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class DashActivity$fetchTraffic$2 extends SuspendLambda implements Function2<IClashManager, Continuation<? super Unit>, Object> {
    public final /* synthetic */ MainDesign $this_fetchTraffic;
    public /* synthetic */ Object L$0;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DashActivity$fetchTraffic$2(MainDesign mainDesign, Continuation<? super DashActivity$fetchTraffic$2> continuation) {
        super(2, continuation);
        this.$this_fetchTraffic = mainDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DashActivity$fetchTraffic$2 dashActivity$fetchTraffic$2 = new DashActivity$fetchTraffic$2(this.$this_fetchTraffic, continuation);
        dashActivity$fetchTraffic$2.L$0 = obj;
        return dashActivity$fetchTraffic$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(IClashManager iClashManager, Continuation<? super Unit> continuation) {
        DashActivity$fetchTraffic$2 dashActivity$fetchTraffic$2 = new DashActivity$fetchTraffic$2(this.$this_fetchTraffic, continuation);
        dashActivity$fetchTraffic$2.L$0 = iClashManager;
        return dashActivity$fetchTraffic$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object obj2 = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            MainDesign mainDesign = this.$this_fetchTraffic;
            long queryTrafficTotal = ((IClashManager) this.L$0).queryTrafficTotal();
            this.label = 1;
            Objects.requireNonNull(mainDesign);
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            Object withContext = InputKt.withContext(MainDispatcherLoader.dispatcher, new MainDesign$setForwarded$2(mainDesign, queryTrafficTotal, null), this);
            if (withContext != obj2) {
                withContext = Unit.INSTANCE;
            }
            if (withContext == obj2) {
                return obj2;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
