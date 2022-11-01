package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.core.model.Provider;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: ProvidersActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.ProvidersActivity$main$2$1$newList$1", f = "ProvidersActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProvidersActivity$main$2$1$newList$1 extends SuspendLambda implements Function2<IClashManager, Continuation<? super List<? extends Provider>>, Object> {
    public /* synthetic */ Object L$0;

    public ProvidersActivity$main$2$1$newList$1(Continuation<? super ProvidersActivity$main$2$1$newList$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ProvidersActivity$main$2$1$newList$1 providersActivity$main$2$1$newList$1 = new ProvidersActivity$main$2$1$newList$1(continuation);
        providersActivity$main$2$1$newList$1.L$0 = obj;
        return providersActivity$main$2$1$newList$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(IClashManager iClashManager, Continuation<? super List<? extends Provider>> continuation) {
        IClashManager iClashManager2 = iClashManager;
        Continuation<? super List<? extends Provider>> continuation2 = continuation;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return ArraysKt___ArraysKt.sorted(iClashManager2.queryProviders());
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return ArraysKt___ArraysKt.sorted(((IClashManager) this.L$0).queryProviders());
    }
}
