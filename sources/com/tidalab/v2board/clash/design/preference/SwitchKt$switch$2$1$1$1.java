package com.tidalab.v2board.clash.design.preference;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.reflect.KMutableProperty0;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
/* compiled from: Switch.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.SwitchKt$switch$2$1$1$1", f = "Switch.kt", l = {90}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class SwitchKt$switch$2$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ SwitchKt$switch$impl$1 $impl;
    public final /* synthetic */ SwitchMaterial $this_apply;
    public final /* synthetic */ KMutableProperty0<Boolean> $value;
    public int label;

    /* compiled from: Switch.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.SwitchKt$switch$2$1$1$1$1", f = "Switch.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.design.preference.SwitchKt$switch$2$1$1$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ SwitchMaterial $this_apply;
        public final /* synthetic */ KMutableProperty0<Boolean> $value;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(KMutableProperty0<Boolean> kMutableProperty0, SwitchMaterial switchMaterial, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$value = kMutableProperty0;
            this.$this_apply = switchMaterial;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$value, this.$this_apply, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            Continuation<? super Unit> continuation2 = continuation;
            KMutableProperty0<Boolean> kMutableProperty0 = this.$value;
            SwitchMaterial switchMaterial = this.$this_apply;
            if (continuation2 != null) {
                continuation2.getContext();
            }
            Unit unit = Unit.INSTANCE;
            InputKt.throwOnFailure(unit);
            kMutableProperty0.set(Boolean.valueOf(switchMaterial.isChecked()));
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            InputKt.throwOnFailure(obj);
            this.$value.set(Boolean.valueOf(this.$this_apply.isChecked()));
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SwitchKt$switch$2$1$1$1(SwitchKt$switch$impl$1 switchKt$switch$impl$1, KMutableProperty0<Boolean> kMutableProperty0, SwitchMaterial switchMaterial, Continuation<? super SwitchKt$switch$2$1$1$1> continuation) {
        super(2, continuation);
        this.$impl = switchKt$switch$impl$1;
        this.$value = kMutableProperty0;
        this.$this_apply = switchMaterial;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SwitchKt$switch$2$1$1$1(this.$impl, this.$value, this.$this_apply, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new SwitchKt$switch$2$1$1$1(this.$impl, this.$value, this.$this_apply, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            CoroutineDispatcher coroutineDispatcher = Dispatchers.IO;
            AnonymousClass1 r1 = new AnonymousClass1(this.$value, this.$this_apply, null);
            this.label = 1;
            if (InputKt.withContext(coroutineDispatcher, r1, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        OnChangedListener onChangedListener = this.$impl.listener;
        if (onChangedListener != null) {
            onChangedListener.onChanged();
        }
        return Unit.INSTANCE;
    }
}
