package com.tidalab.v2board.clash.design.preference;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.reflect.KMutableProperty0;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: Switch.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.SwitchKt$switch$2$initialValue$1", f = "Switch.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class SwitchKt$switch$2$initialValue$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    public final /* synthetic */ KMutableProperty0<Boolean> $value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SwitchKt$switch$2$initialValue$1(KMutableProperty0<Boolean> kMutableProperty0, Continuation<? super SwitchKt$switch$2$initialValue$1> continuation) {
        super(2, continuation);
        this.$value = kMutableProperty0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SwitchKt$switch$2$initialValue$1(this.$value, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        Continuation<? super Boolean> continuation2 = continuation;
        KMutableProperty0<Boolean> kMutableProperty0 = this.$value;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return kMutableProperty0.get();
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return this.$value.get();
    }
}
