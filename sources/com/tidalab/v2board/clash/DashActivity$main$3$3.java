package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.MainDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Objects;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: DashActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.DashActivity$main$3$3", f = "DashActivity.kt", l = {71}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class DashActivity$main$3$3 extends SuspendLambda implements Function2<Long, Continuation<? super Unit>, Object> {
    public final /* synthetic */ MainDesign $design;
    public int label;
    public final /* synthetic */ DashActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DashActivity$main$3$3(DashActivity dashActivity, MainDesign mainDesign, Continuation<? super DashActivity$main$3$3> continuation) {
        super(2, continuation);
        this.this$0 = dashActivity;
        this.$design = mainDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DashActivity$main$3$3(this.this$0, this.$design, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Long l, Continuation<? super Unit> continuation) {
        l.longValue();
        return new DashActivity$main$3$3(this.this$0, this.$design, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object obj2 = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            DashActivity dashActivity = this.this$0;
            MainDesign mainDesign = this.$design;
            this.label = 1;
            int i2 = DashActivity.$r8$clinit;
            Objects.requireNonNull(dashActivity);
            Object withClash$default = InputKt.withClash$default(null, new DashActivity$fetchTraffic$2(mainDesign, null), this, 1);
            if (withClash$default != obj2) {
                withClash$default = Unit.INSTANCE;
            }
            if (withClash$default == obj2) {
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
