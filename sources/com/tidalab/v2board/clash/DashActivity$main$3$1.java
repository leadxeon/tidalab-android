package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.BaseActivity;
import com.tidalab.v2board.clash.design.MainDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: DashActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.DashActivity$main$3$1", f = "DashActivity.kt", l = {41}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class DashActivity$main$3$1 extends SuspendLambda implements Function2<BaseActivity.Event, Continuation<? super Unit>, Object> {
    public final /* synthetic */ MainDesign $design;
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ DashActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DashActivity$main$3$1(DashActivity dashActivity, MainDesign mainDesign, Continuation<? super DashActivity$main$3$1> continuation) {
        super(2, continuation);
        this.this$0 = dashActivity;
        this.$design = mainDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DashActivity$main$3$1 dashActivity$main$3$1 = new DashActivity$main$3$1(this.this$0, this.$design, continuation);
        dashActivity$main$3$1.L$0 = obj;
        return dashActivity$main$3$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(BaseActivity.Event event, Continuation<? super Unit> continuation) {
        DashActivity$main$3$1 dashActivity$main$3$1 = new DashActivity$main$3$1(this.this$0, this.$design, continuation);
        dashActivity$main$3$1.L$0 = event;
        return dashActivity$main$3$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            int ordinal = ((BaseActivity.Event) this.L$0).ordinal();
            if (ordinal == 0 || ordinal == 1 || ordinal == 3 || ordinal == 4 || ordinal == 5 || ordinal == 6) {
                DashActivity dashActivity = this.this$0;
                MainDesign mainDesign = this.$design;
                this.label = 1;
                int i2 = DashActivity.$r8$clinit;
                if (dashActivity.fetch(mainDesign, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
