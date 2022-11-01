package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: ProxyActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.ProxyActivity$main$names$1", f = "ProxyActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProxyActivity$main$names$1 extends SuspendLambda implements Function2<IClashManager, Continuation<? super List<? extends String>>, Object> {
    public /* synthetic */ Object L$0;
    public final /* synthetic */ ProxyActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProxyActivity$main$names$1(ProxyActivity proxyActivity, Continuation<? super ProxyActivity$main$names$1> continuation) {
        super(2, continuation);
        this.this$0 = proxyActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ProxyActivity$main$names$1 proxyActivity$main$names$1 = new ProxyActivity$main$names$1(this.this$0, continuation);
        proxyActivity$main$names$1.L$0 = obj;
        return proxyActivity$main$names$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(IClashManager iClashManager, Continuation<? super List<? extends String>> continuation) {
        IClashManager iClashManager2 = iClashManager;
        Continuation<? super List<? extends String>> continuation2 = continuation;
        ProxyActivity proxyActivity = this.this$0;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return iClashManager2.queryProxyGroupNames(proxyActivity.getUiStore().getProxyExcludeNotSelectable());
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return ((IClashManager) this.L$0).queryProxyGroupNames(this.this$0.getUiStore().getProxyExcludeNotSelectable());
    }
}
