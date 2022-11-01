package com.tidalab.v2board.clash.design.dialog;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: Progress.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.dialog.ProgressKt$withModelProgressBar$scopeImpl$1$configure$2", f = "Progress.kt", l = {55}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProgressKt$withModelProgressBar$scopeImpl$1$configure$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Function2<ModelProgressBarConfigure, Continuation<? super Unit>, Object> $block;
    public final /* synthetic */ ProgressKt$withModelProgressBar$configureImpl$1 $configureImpl;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ProgressKt$withModelProgressBar$scopeImpl$1$configure$2(Function2<? super ModelProgressBarConfigure, ? super Continuation<? super Unit>, ? extends Object> function2, ProgressKt$withModelProgressBar$configureImpl$1 progressKt$withModelProgressBar$configureImpl$1, Continuation<? super ProgressKt$withModelProgressBar$scopeImpl$1$configure$2> continuation) {
        super(2, continuation);
        this.$block = function2;
        this.$configureImpl = progressKt$withModelProgressBar$configureImpl$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProgressKt$withModelProgressBar$scopeImpl$1$configure$2(this.$block, this.$configureImpl, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new ProgressKt$withModelProgressBar$scopeImpl$1$configure$2(this.$block, this.$configureImpl, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            Function2<ModelProgressBarConfigure, Continuation<? super Unit>, Object> function2 = this.$block;
            ProgressKt$withModelProgressBar$configureImpl$1 progressKt$withModelProgressBar$configureImpl$1 = this.$configureImpl;
            this.label = 1;
            if (function2.invoke(progressKt$withModelProgressBar$configureImpl$1, this) == coroutineSingletons) {
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
