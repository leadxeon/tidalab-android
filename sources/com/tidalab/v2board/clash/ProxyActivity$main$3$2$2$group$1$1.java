package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.core.model.ProxyGroup;
import com.tidalab.v2board.clash.core.model.ProxySort;
import com.tidalab.v2board.clash.design.ProxyDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.store.UiStore;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: ProxyActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.ProxyActivity$main$3$2$2$group$1$1", f = "ProxyActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProxyActivity$main$3$2$2$group$1$1 extends SuspendLambda implements Function2<IClashManager, Continuation<? super ProxyGroup>, Object> {
    public final /* synthetic */ ProxyDesign.Request $it;
    public final /* synthetic */ List<String> $names;
    public /* synthetic */ Object L$0;
    public final /* synthetic */ ProxyActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProxyActivity$main$3$2$2$group$1$1(List<String> list, ProxyDesign.Request request, ProxyActivity proxyActivity, Continuation<? super ProxyActivity$main$3$2$2$group$1$1> continuation) {
        super(2, continuation);
        this.$names = list;
        this.$it = request;
        this.this$0 = proxyActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ProxyActivity$main$3$2$2$group$1$1 proxyActivity$main$3$2$2$group$1$1 = new ProxyActivity$main$3$2$2$group$1$1(this.$names, this.$it, this.this$0, continuation);
        proxyActivity$main$3$2$2$group$1$1.L$0 = obj;
        return proxyActivity$main$3$2$2$group$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(IClashManager iClashManager, Continuation<? super ProxyGroup> continuation) {
        IClashManager iClashManager2 = iClashManager;
        Continuation<? super ProxyGroup> continuation2 = continuation;
        List<String> list = this.$names;
        ProxyDesign.Request request = this.$it;
        ProxyActivity proxyActivity = this.this$0;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        UiStore uiStore = proxyActivity.getUiStore();
        return iClashManager2.queryProxyGroup(list.get(((ProxyDesign.Request.Reload) request).index), (ProxySort) uiStore.proxySort$delegate.getValue(uiStore, UiStore.$$delegatedProperties[4]));
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        UiStore uiStore = this.this$0.getUiStore();
        return ((IClashManager) this.L$0).queryProxyGroup(this.$names.get(((ProxyDesign.Request.Reload) this.$it).index), (ProxySort) uiStore.proxySort$delegate.getValue(uiStore, UiStore.$$delegatedProperties[4]));
    }
}
