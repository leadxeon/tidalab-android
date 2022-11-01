package com.tidalab.v2board.clash.service;

import android.content.Context;
import android.content.Intent;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.common.constants.Intents;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.ProfileReceiver;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: ProfileReceiver.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.ProfileReceiver$onReceive$1", f = "ProfileReceiver.kt", l = {31}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProfileReceiver$onReceive$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Context $context;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileReceiver$onReceive$1(Context context, Continuation<? super ProfileReceiver$onReceive$1> continuation) {
        super(2, continuation);
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProfileReceiver$onReceive$1(this.$context, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new ProfileReceiver$onReceive$1(this.$context, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            ProfileReceiver.Companion companion = ProfileReceiver.Companion;
            this.label = 1;
            if (ProfileReceiver.Companion.access$reset(companion, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Intents intents = Intents.INSTANCE;
        PathParser.startForegroundServiceCompat(this.$context, new Intent(Intents.ACTION_PROFILE_SCHEDULE_UPDATES).setComponent(PathParser.getComponentName(Reflection.getOrCreateKotlinClass(ProfileWorker.class))));
        return Unit.INSTANCE;
    }
}
