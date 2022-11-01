package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: NativeModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.NativeModule$querySelectProxy$1$names$1", f = "NativeModule.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class NativeModule$querySelectProxy$1$names$1 extends SuspendLambda implements Function2<IClashManager, Continuation<? super List<? extends String>>, Object> {
    public /* synthetic */ Object L$0;

    public NativeModule$querySelectProxy$1$names$1(Continuation<? super NativeModule$querySelectProxy$1$names$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NativeModule$querySelectProxy$1$names$1 nativeModule$querySelectProxy$1$names$1 = new NativeModule$querySelectProxy$1$names$1(continuation);
        nativeModule$querySelectProxy$1$names$1.L$0 = obj;
        return nativeModule$querySelectProxy$1$names$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(IClashManager iClashManager, Continuation<? super List<? extends String>> continuation) {
        IClashManager iClashManager2 = iClashManager;
        Continuation<? super List<? extends String>> continuation2 = continuation;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return iClashManager2.queryProxyGroupNames(false);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return ((IClashManager) this.L$0).queryProxyGroupNames(false);
    }
}
