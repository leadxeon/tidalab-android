package com.tidalab.v2board.clash.util;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: Remote.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.util.RemoteKt$withClash$2", f = "Remote.kt", l = {21}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class RemoteKt$withClash$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
    public final /* synthetic */ Function2<IClashManager, Continuation<? super T>, Object> $block;
    public final /* synthetic */ IClashManager $client;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public RemoteKt$withClash$2(Function2<? super IClashManager, ? super Continuation<? super T>, ? extends Object> function2, IClashManager iClashManager, Continuation<? super RemoteKt$withClash$2> continuation) {
        super(2, continuation);
        this.$block = function2;
        this.$client = iClashManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteKt$withClash$2(this.$block, this.$client, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Object obj) {
        return new RemoteKt$withClash$2(this.$block, this.$client, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object obj2 = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            Function2<IClashManager, Continuation<? super T>, Object> function2 = this.$block;
            IClashManager iClashManager = this.$client;
            this.label = 1;
            obj = function2.invoke(iClashManager, this);
            if (obj == obj2) {
                return obj2;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
