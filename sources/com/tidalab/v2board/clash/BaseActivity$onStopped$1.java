package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.core.bridge.ClashException;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: BaseActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.BaseActivity$onStopped$1", f = "BaseActivity.kt", l = {196}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class BaseActivity$onStopped$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ String $cause;
    public int label;
    public final /* synthetic */ BaseActivity<D> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseActivity$onStopped$1(BaseActivity<D> baseActivity, String str, Continuation<? super BaseActivity$onStopped$1> continuation) {
        super(2, continuation);
        this.this$0 = baseActivity;
        this.$cause = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseActivity$onStopped$1(this.this$0, this.$cause, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new BaseActivity$onStopped$1(this.this$0, this.$cause, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            D d = this.this$0.design;
            if (d != 0) {
                ClashException clashException = new ClashException(this.$cause);
                this.label = 1;
                if (InputKt.showExceptionToast(d, clashException, this) == coroutineSingletons) {
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
