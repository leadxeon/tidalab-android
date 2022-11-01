package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.model.Profile;
import com.tidalab.v2board.clash.service.remote.IProfileManager;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: DashActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.DashActivity$startClash$active$1", f = "DashActivity.kt", l = {117}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class DashActivity$startClash$active$1 extends SuspendLambda implements Function2<IProfileManager, Continuation<? super Profile>, Object> {
    public /* synthetic */ Object L$0;
    public int label;

    public DashActivity$startClash$active$1(Continuation<? super DashActivity$startClash$active$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DashActivity$startClash$active$1 dashActivity$startClash$active$1 = new DashActivity$startClash$active$1(continuation);
        dashActivity$startClash$active$1.L$0 = obj;
        return dashActivity$startClash$active$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(IProfileManager iProfileManager, Continuation<? super Profile> continuation) {
        DashActivity$startClash$active$1 dashActivity$startClash$active$1 = new DashActivity$startClash$active$1(continuation);
        dashActivity$startClash$active$1.L$0 = iProfileManager;
        return dashActivity$startClash$active$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            this.label = 1;
            obj = ((IProfileManager) this.L$0).queryActive(this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
