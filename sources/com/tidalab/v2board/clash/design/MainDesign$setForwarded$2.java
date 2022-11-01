package com.tidalab.v2board.clash.design;

import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.design.databinding.DesignMainBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: MainDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.MainDesign$setForwarded$2", f = "MainDesign.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class MainDesign$setForwarded$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ long $value;
    public final /* synthetic */ MainDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainDesign$setForwarded$2(MainDesign mainDesign, long j, Continuation<? super MainDesign$setForwarded$2> continuation) {
        super(2, continuation);
        this.this$0 = mainDesign;
        this.$value = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainDesign$setForwarded$2(this.this$0, this.$value, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        Continuation<? super Unit> continuation2 = continuation;
        MainDesign mainDesign = this.this$0;
        long j = this.$value;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        mainDesign.binding.setForwarded(PathParser.trafficString(PathParser.scaleTraffic(j & 4294967295L) + PathParser.scaleTraffic(j >>> 32)));
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        DesignMainBinding designMainBinding = this.this$0.binding;
        long j = this.$value;
        designMainBinding.setForwarded(PathParser.trafficString(PathParser.scaleTraffic(j & 4294967295L) + PathParser.scaleTraffic(j >>> 32)));
        return Unit.INSTANCE;
    }
}
