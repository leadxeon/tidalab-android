package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.core.Clash;
import com.tidalab.v2board.clash.core.model.ConfigurationOverride;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
/* compiled from: OverrideSettingsActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.OverrideSettingsActivity$main$2", f = "OverrideSettingsActivity.kt", l = {23}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class OverrideSettingsActivity$main$2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    public final /* synthetic */ ConfigurationOverride $configuration;
    public int label;

    /* compiled from: OverrideSettingsActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.OverrideSettingsActivity$main$2$1", f = "OverrideSettingsActivity.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.OverrideSettingsActivity$main$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<IClashManager, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ConfigurationOverride $configuration;
        public /* synthetic */ Object L$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ConfigurationOverride configurationOverride, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$configuration = configurationOverride;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$configuration, continuation);
            r0.L$0 = obj;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(IClashManager iClashManager, Continuation<? super Unit> continuation) {
            IClashManager iClashManager2 = iClashManager;
            Continuation<? super Unit> continuation2 = continuation;
            ConfigurationOverride configurationOverride = this.$configuration;
            if (continuation2 != null) {
                continuation2.getContext();
            }
            Unit unit = Unit.INSTANCE;
            InputKt.throwOnFailure(unit);
            iClashManager2.patchOverride(Clash.OverrideSlot.Persist, configurationOverride);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            InputKt.throwOnFailure(obj);
            ((IClashManager) this.L$0).patchOverride(Clash.OverrideSlot.Persist, this.$configuration);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OverrideSettingsActivity$main$2(ConfigurationOverride configurationOverride, Continuation<? super OverrideSettingsActivity$main$2> continuation) {
        super(1, continuation);
        this.$configuration = configurationOverride;
    }

    @Override // kotlin.jvm.functions.Function1
    public Object invoke(Continuation<? super Unit> continuation) {
        return new OverrideSettingsActivity$main$2(this.$configuration, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            AnonymousClass1 r5 = new AnonymousClass1(this.$configuration, null);
            this.label = 1;
            if (InputKt.withClash$default(null, r5, this, 1) == coroutineSingletons) {
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
