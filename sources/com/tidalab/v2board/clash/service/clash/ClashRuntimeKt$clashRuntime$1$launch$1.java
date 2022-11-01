package com.tidalab.v2board.clash.service.clash;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: ClashRuntime.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.ClashRuntimeKt$clashRuntime$1$launch$1", f = "ClashRuntime.kt", l = {71, 46, 50, 50}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ClashRuntimeKt$clashRuntime$1$launch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Function2<ClashRuntimeScope, Continuation<? super Unit>, Object> $block;
    public /* synthetic */ Object L$0;
    public Object L$1;
    public Object L$2;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ClashRuntimeKt$clashRuntime$1$launch$1(Function2<? super ClashRuntimeScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super ClashRuntimeKt$clashRuntime$1$launch$1> continuation) {
        super(2, continuation);
        this.$block = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ClashRuntimeKt$clashRuntime$1$launch$1 clashRuntimeKt$clashRuntime$1$launch$1 = new ClashRuntimeKt$clashRuntime$1$launch$1(this.$block, continuation);
        clashRuntimeKt$clashRuntime$1$launch$1.L$0 = obj;
        return clashRuntimeKt$clashRuntime$1$launch$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        ClashRuntimeKt$clashRuntime$1$launch$1 clashRuntimeKt$clashRuntime$1$launch$1 = new ClashRuntimeKt$clashRuntime$1$launch$1(this.$block, continuation);
        clashRuntimeKt$clashRuntime$1$launch$1.L$0 = coroutineScope;
        return clashRuntimeKt$clashRuntime$1$launch$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00b2 A[RETURN] */
    /* JADX WARN: Type inference failed for: r1v0, types: [int, kotlinx.coroutines.sync.Mutex] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            Method dump skipped, instructions count: 214
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.clash.ClashRuntimeKt$clashRuntime$1$launch$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
