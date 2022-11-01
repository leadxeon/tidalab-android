package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.IProfileManager;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
/* compiled from: PropertiesActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.PropertiesActivity$main$2", f = "PropertiesActivity.kt", l = {34}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class PropertiesActivity$main$2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    public final /* synthetic */ UUID $uuid;
    public int label;
    public final /* synthetic */ PropertiesActivity this$0;

    /* compiled from: PropertiesActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.PropertiesActivity$main$2$1", f = "PropertiesActivity.kt", l = {34}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.PropertiesActivity$main$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<IProfileManager, Continuation<? super Unit>, Object> {
        public final /* synthetic */ UUID $uuid;
        public /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(UUID uuid, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$uuid = uuid;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$uuid, continuation);
            r0.L$0 = obj;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(IProfileManager iProfileManager, Continuation<? super Unit> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$uuid, continuation);
            r0.L$0 = iProfileManager;
            return r0.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                UUID uuid = this.$uuid;
                this.label = 1;
                if (((IProfileManager) this.L$0).release(uuid, this) == coroutineSingletons) {
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PropertiesActivity$main$2(PropertiesActivity propertiesActivity, UUID uuid, Continuation<? super PropertiesActivity$main$2> continuation) {
        super(1, continuation);
        this.this$0 = propertiesActivity;
        this.$uuid = uuid;
    }

    @Override // kotlin.jvm.functions.Function1
    public Object invoke(Continuation<? super Unit> continuation) {
        return new PropertiesActivity$main$2(this.this$0, this.$uuid, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            this.this$0.canceled = true;
            AnonymousClass1 r5 = new AnonymousClass1(this.$uuid, null);
            this.label = 1;
            if (InputKt.withProfile$default(null, r5, this, 1) == coroutineSingletons) {
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
