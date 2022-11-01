package com.tidalab.v2board.clash.design.preference;

import android.view.View;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.tidalab.v2board.clash.design.databinding.PreferenceSwitchBinding;
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
import kotlinx.coroutines.internal.MainDispatcherLoader;
/* compiled from: Switch.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.SwitchKt$switch$2", f = "Switch.kt", l = {79}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class SwitchKt$switch$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ PreferenceSwitchBinding $binding;
    public final /* synthetic */ SwitchKt$switch$impl$1 $impl;
    public final /* synthetic */ PreferenceScreen $this_switch;
    public final /* synthetic */ KMutableProperty0<Boolean> $value;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SwitchKt$switch$2(PreferenceSwitchBinding preferenceSwitchBinding, KMutableProperty0<Boolean> kMutableProperty0, PreferenceScreen preferenceScreen, SwitchKt$switch$impl$1 switchKt$switch$impl$1, Continuation<? super SwitchKt$switch$2> continuation) {
        super(2, continuation);
        this.$binding = preferenceSwitchBinding;
        this.$value = kMutableProperty0;
        this.$this_switch = preferenceScreen;
        this.$impl = switchKt$switch$impl$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SwitchKt$switch$2(this.$binding, this.$value, this.$this_switch, this.$impl, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new SwitchKt$switch$2(this.$binding, this.$value, this.$this_switch, this.$impl, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            CoroutineDispatcher coroutineDispatcher = Dispatchers.IO;
            SwitchKt$switch$2$initialValue$1 switchKt$switch$2$initialValue$1 = new SwitchKt$switch$2$initialValue$1(this.$value, null);
            this.label = 1;
            obj = InputKt.withContext(coroutineDispatcher, switchKt$switch$2$initialValue$1, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        PreferenceSwitchBinding preferenceSwitchBinding = this.$binding;
        final SwitchMaterial switchMaterial = preferenceSwitchBinding.switchView;
        final PreferenceScreen preferenceScreen = this.$this_switch;
        final SwitchKt$switch$impl$1 switchKt$switch$impl$1 = this.$impl;
        final KMutableProperty0<Boolean> kMutableProperty0 = this.$value;
        switchMaterial.setChecked(booleanValue);
        preferenceSwitchBinding.mRoot.setOnClickListener(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.preference.-$$Lambda$SwitchKt$switch$2$5W21IjLhAJ1s5pBumpKXGjDLSqc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SwitchMaterial switchMaterial2 = SwitchMaterial.this;
                PreferenceScreen preferenceScreen2 = preferenceScreen;
                SwitchKt$switch$impl$1 switchKt$switch$impl$12 = switchKt$switch$impl$1;
                KMutableProperty0 kMutableProperty02 = kMutableProperty0;
                switchMaterial2.setChecked(!switchMaterial2.isChecked());
                Dispatchers dispatchers2 = Dispatchers.INSTANCE;
                InputKt.launch$default(preferenceScreen2, MainDispatcherLoader.dispatcher, null, new SwitchKt$switch$2$1$1$1(switchKt$switch$impl$12, kMutableProperty02, switchMaterial2, null), 2, null);
            }
        });
        return Unit.INSTANCE;
    }
}
