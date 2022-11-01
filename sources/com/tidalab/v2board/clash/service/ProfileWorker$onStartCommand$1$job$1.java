package com.tidalab.v2board.clash.service;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: ProfileWorker.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.ProfileWorker$onStartCommand$1$job$1", f = "ProfileWorker.kt", l = {61}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProfileWorker$onStartCommand$1$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ UUID $it;
    public int label;
    public final /* synthetic */ ProfileWorker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileWorker$onStartCommand$1$job$1(ProfileWorker profileWorker, UUID uuid, Continuation<? super ProfileWorker$onStartCommand$1$job$1> continuation) {
        super(2, continuation);
        this.this$0 = profileWorker;
        this.$it = uuid;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProfileWorker$onStartCommand$1$job$1(this.this$0, this.$it, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new ProfileWorker$onStartCommand$1$job$1(this.this$0, this.$it, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            ProfileWorker profileWorker = this.this$0;
            UUID uuid = this.$it;
            this.label = 1;
            if (ProfileWorker.access$run(profileWorker, uuid, this) == coroutineSingletons) {
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
