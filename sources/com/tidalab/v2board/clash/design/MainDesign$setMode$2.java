package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.core.model.TunnelState;
import com.tidalab.v2board.clash.design.databinding.DesignMainBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: MainDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.MainDesign$setMode$2", f = "MainDesign.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class MainDesign$setMode$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ TunnelState.Mode $mode;
    public final /* synthetic */ MainDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainDesign$setMode$2(MainDesign mainDesign, TunnelState.Mode mode, Continuation<? super MainDesign$setMode$2> continuation) {
        super(2, continuation);
        this.this$0 = mainDesign;
        this.$mode = mode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainDesign$setMode$2(this.this$0, this.$mode, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        String str;
        Continuation<? super Unit> continuation2 = continuation;
        MainDesign mainDesign = this.this$0;
        TunnelState.Mode mode = this.$mode;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        DesignMainBinding designMainBinding = mainDesign.binding;
        int ordinal = mode.ordinal();
        if (ordinal == 0) {
            str = mainDesign.context.getString(R.string.direct_mode);
        } else if (ordinal == 1) {
            str = mainDesign.context.getString(R.string.global_mode);
        } else if (ordinal == 2) {
            str = mainDesign.context.getString(R.string.rule_mode);
        } else if (ordinal == 3) {
            str = mainDesign.context.getString(R.string.script_mode);
        } else {
            throw new NoWhenBranchMatchedException();
        }
        designMainBinding.setMode(str);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        InputKt.throwOnFailure(obj);
        DesignMainBinding designMainBinding = this.this$0.binding;
        int ordinal = this.$mode.ordinal();
        if (ordinal == 0) {
            str = this.this$0.context.getString(R.string.direct_mode);
        } else if (ordinal == 1) {
            str = this.this$0.context.getString(R.string.global_mode);
        } else if (ordinal == 2) {
            str = this.this$0.context.getString(R.string.rule_mode);
        } else if (ordinal == 3) {
            str = this.this$0.context.getString(R.string.script_mode);
        } else {
            throw new NoWhenBranchMatchedException();
        }
        designMainBinding.setMode(str);
        return Unit.INSTANCE;
    }
}
