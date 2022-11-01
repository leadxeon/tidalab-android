package com.tidalab.v2board.clash.service;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.ProfileReceiver;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: ProfileWorker.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.ProfileWorker$onStartCommand$job$1", f = "ProfileWorker.kt", l = {69, 71}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProfileWorker$onStartCommand$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ ProfileWorker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileWorker$onStartCommand$job$1(ProfileWorker profileWorker, Continuation<? super ProfileWorker$onStartCommand$job$1> continuation) {
        super(2, continuation);
        this.this$0 = profileWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProfileWorker$onStartCommand$job$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new ProfileWorker$onStartCommand$job$1(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            ProfileReceiver.Companion companion = ProfileReceiver.Companion;
            ProfileWorker profileWorker = this.this$0;
            int i2 = ProfileWorker.$r8$clinit;
            Objects.requireNonNull(profileWorker);
            this.label = 1;
            if (companion.rescheduleAll(profileWorker, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else if (i == 2) {
            InputKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        long millis = TimeUnit.SECONDS.toMillis(30L);
        this.label = 2;
        if (InputKt.delay(millis, this) == coroutineSingletons) {
            return coroutineSingletons;
        }
        return Unit.INSTANCE;
    }
}
