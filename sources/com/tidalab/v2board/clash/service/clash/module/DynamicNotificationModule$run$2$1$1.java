package com.tidalab.v2board.clash.service.clash.module;

import android.content.Intent;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;
/* compiled from: DynamicNotificationModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.module.DynamicNotificationModule$run$2$1$1", f = "DynamicNotificationModule.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class DynamicNotificationModule$run$2$1$1 extends SuspendLambda implements Function2<Intent, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Ref$BooleanRef $shouldUpdate;
    public /* synthetic */ Object L$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicNotificationModule$run$2$1$1(Ref$BooleanRef ref$BooleanRef, Continuation<? super DynamicNotificationModule$run$2$1$1> continuation) {
        super(2, continuation);
        this.$shouldUpdate = ref$BooleanRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DynamicNotificationModule$run$2$1$1 dynamicNotificationModule$run$2$1$1 = new DynamicNotificationModule$run$2$1$1(this.$shouldUpdate, continuation);
        dynamicNotificationModule$run$2$1$1.L$0 = obj;
        return dynamicNotificationModule$run$2$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Intent intent, Continuation<? super Unit> continuation) {
        Intent intent2 = intent;
        Continuation<? super Unit> continuation2 = continuation;
        Ref$BooleanRef ref$BooleanRef = this.$shouldUpdate;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        String action = intent2.getAction();
        if (Intrinsics.areEqual(action, "android.intent.action.SCREEN_ON")) {
            ref$BooleanRef.element = true;
        } else if (Intrinsics.areEqual(action, "android.intent.action.SCREEN_OFF")) {
            ref$BooleanRef.element = false;
        }
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        String action = ((Intent) this.L$0).getAction();
        if (Intrinsics.areEqual(action, "android.intent.action.SCREEN_ON")) {
            this.$shouldUpdate.element = true;
        } else if (Intrinsics.areEqual(action, "android.intent.action.SCREEN_OFF")) {
            this.$shouldUpdate.element = false;
        }
        return Unit.INSTANCE;
    }
}
