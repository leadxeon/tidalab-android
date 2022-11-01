package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.BaseActivity;
import com.tidalab.v2board.clash.design.PropertiesDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.model.Profile;
import com.tidalab.v2board.clash.service.remote.IProfileManager;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: PropertiesActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.PropertiesActivity$main$3$1", f = "PropertiesActivity.kt", l = {45}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class PropertiesActivity$main$3$1 extends SuspendLambda implements Function2<BaseActivity.Event, Continuation<? super Unit>, Object> {
    public final /* synthetic */ PropertiesDesign $design;
    public final /* synthetic */ Profile $original;
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ PropertiesActivity this$0;

    /* compiled from: PropertiesActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.PropertiesActivity$main$3$1$1", f = "PropertiesActivity.kt", l = {46}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.PropertiesActivity$main$3$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<IProfileManager, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Profile $profile;
        public /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Profile profile, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$profile = profile;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$profile, continuation);
            r0.L$0 = obj;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(IProfileManager iProfileManager, Continuation<? super Unit> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$profile, continuation);
            r0.L$0 = iProfileManager;
            return r0.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                IProfileManager iProfileManager = (IProfileManager) this.L$0;
                Profile profile = this.$profile;
                UUID uuid = profile.uuid;
                String str = profile.name;
                String str2 = profile.source;
                long j = profile.interval;
                this.label = 1;
                if (iProfileManager.patch(uuid, str, str2, j, this) == coroutineSingletons) {
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
    public PropertiesActivity$main$3$1(PropertiesDesign propertiesDesign, PropertiesActivity propertiesActivity, Profile profile, Continuation<? super PropertiesActivity$main$3$1> continuation) {
        super(2, continuation);
        this.$design = propertiesDesign;
        this.this$0 = propertiesActivity;
        this.$original = profile;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PropertiesActivity$main$3$1 propertiesActivity$main$3$1 = new PropertiesActivity$main$3$1(this.$design, this.this$0, this.$original, continuation);
        propertiesActivity$main$3$1.L$0 = obj;
        return propertiesActivity$main$3$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(BaseActivity.Event event, Continuation<? super Unit> continuation) {
        PropertiesActivity$main$3$1 propertiesActivity$main$3$1 = new PropertiesActivity$main$3$1(this.$design, this.this$0, this.$original, continuation);
        propertiesActivity$main$3$1.L$0 = event;
        return propertiesActivity$main$3$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            int ordinal = ((BaseActivity.Event) this.L$0).ordinal();
            if (ordinal == 0) {
                this.this$0.finish();
            } else if (ordinal == 2) {
                Profile profile = this.$design.binding.mProfile;
                if (!this.this$0.canceled && !Intrinsics.areEqual(profile, this.$original)) {
                    AnonymousClass1 r1 = new AnonymousClass1(profile, null);
                    this.label = 1;
                    if (InputKt.withProfile$default(null, r1, this, 1) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
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
