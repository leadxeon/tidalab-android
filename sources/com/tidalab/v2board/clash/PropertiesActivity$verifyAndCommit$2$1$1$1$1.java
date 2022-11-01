package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.core.model.FetchStatus;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: PropertiesActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.PropertiesActivity$verifyAndCommit$2$1$1$1$1", f = "PropertiesActivity.kt", l = {98}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class PropertiesActivity$verifyAndCommit$2$1$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ FetchStatus $it;
    public final /* synthetic */ Function2<FetchStatus, Continuation<? super Unit>, Object> $updateStatus;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PropertiesActivity$verifyAndCommit$2$1$1$1$1(Function2<? super FetchStatus, ? super Continuation<? super Unit>, ? extends Object> function2, FetchStatus fetchStatus, Continuation<? super PropertiesActivity$verifyAndCommit$2$1$1$1$1> continuation) {
        super(2, continuation);
        this.$updateStatus = function2;
        this.$it = fetchStatus;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PropertiesActivity$verifyAndCommit$2$1$1$1$1(this.$updateStatus, this.$it, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new PropertiesActivity$verifyAndCommit$2$1$1$1$1(this.$updateStatus, this.$it, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            Function2<FetchStatus, Continuation<? super Unit>, Object> function2 = this.$updateStatus;
            FetchStatus fetchStatus = this.$it;
            this.label = 1;
            if (function2.invoke(fetchStatus, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
