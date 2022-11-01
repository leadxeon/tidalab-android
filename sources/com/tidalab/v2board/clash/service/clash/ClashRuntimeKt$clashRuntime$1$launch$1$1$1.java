package com.tidalab.v2board.clash.service.clash;

import android.util.Log;
import com.tidalab.v2board.clash.core.Clash;
import com.tidalab.v2board.clash.core.bridge.Bridge;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: ClashRuntime.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.ClashRuntimeKt$clashRuntime$1$launch$1$1$1", f = "ClashRuntime.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ClashRuntimeKt$clashRuntime$1$launch$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    public ClashRuntimeKt$clashRuntime$1$launch$1$1$1(Continuation<? super ClashRuntimeKt$clashRuntime$1$launch$1$1$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ClashRuntimeKt$clashRuntime$1$launch$1$1$1(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        Continuation<? super Integer> continuation2 = continuation;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        Clash clash = Clash.INSTANCE;
        Bridge.INSTANCE.nativeReset();
        clash.clearOverride(Clash.OverrideSlot.Session);
        return new Integer(Log.d("ClashForAndroid", "ClashRuntime: destroyed", null));
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        Clash clash = Clash.INSTANCE;
        Bridge.INSTANCE.nativeReset();
        clash.clearOverride(Clash.OverrideSlot.Session);
        return new Integer(Log.d("ClashForAndroid", "ClashRuntime: destroyed", null));
    }
}
