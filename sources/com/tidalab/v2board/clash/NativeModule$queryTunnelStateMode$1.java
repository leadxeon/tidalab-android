package com.tidalab.v2board.clash;

import com.facebook.react.bridge.Callback;
import com.tidalab.v2board.clash.core.model.TunnelState;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: NativeModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.NativeModule$queryTunnelStateMode$1", f = "NativeModule.kt", l = {132}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class NativeModule$queryTunnelStateMode$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Callback $successCallback;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NativeModule$queryTunnelStateMode$1(Callback callback, Continuation<? super NativeModule$queryTunnelStateMode$1> continuation) {
        super(2, continuation);
        this.$successCallback = callback;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NativeModule$queryTunnelStateMode$1(this.$successCallback, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new NativeModule$queryTunnelStateMode$1(this.$successCallback, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            NativeModule$queryTunnelStateMode$1$state$1 nativeModule$queryTunnelStateMode$1$state$1 = new NativeModule$queryTunnelStateMode$1$state$1(null);
            this.label = 1;
            obj = InputKt.withClash$default(null, nativeModule$queryTunnelStateMode$1$state$1, this, 1);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.$successCallback.invoke(((TunnelState) obj).mode.toString());
        return Unit.INSTANCE;
    }
}
