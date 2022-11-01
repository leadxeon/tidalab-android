package com.tidalab.v2board.clash.design.adapter;

import com.tidalab.v2board.clash.core.model.Proxy;
import com.tidalab.v2board.clash.design.component.ProxyViewState;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.ProxyState;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: ProxyPageAdapter.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.adapter.ProxyPageAdapter$updateAdapter$states$1", f = "ProxyPageAdapter.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProxyPageAdapter$updateAdapter$states$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends ProxyViewState>>, Object> {
    public final /* synthetic */ Map<String, ProxyState> $links;
    public final /* synthetic */ ProxyState $parent;
    public final /* synthetic */ List<Proxy> $proxies;
    public final /* synthetic */ ProxyPageAdapter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProxyPageAdapter$updateAdapter$states$1(List<Proxy> list, Map<String, ProxyState> map, ProxyPageAdapter proxyPageAdapter, ProxyState proxyState, Continuation<? super ProxyPageAdapter$updateAdapter$states$1> continuation) {
        super(2, continuation);
        this.$proxies = list;
        this.$links = map;
        this.this$0 = proxyPageAdapter;
        this.$parent = proxyState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProxyPageAdapter$updateAdapter$states$1(this.$proxies, this.$links, this.this$0, this.$parent, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends ProxyViewState>> continuation) {
        Continuation<? super List<? extends ProxyViewState>> continuation2 = continuation;
        List<Proxy> list = this.$proxies;
        Map<String, ProxyState> map = this.$links;
        ProxyPageAdapter proxyPageAdapter = this.this$0;
        ProxyState proxyState = this.$parent;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        ArrayList arrayList = new ArrayList(InputKt.collectionSizeOrDefault(list, 10));
        for (Proxy proxy : list) {
            arrayList.add(new ProxyViewState(proxyPageAdapter.config, proxy, proxyState, proxy.type.group ? map.get(proxy.name) : null));
        }
        return arrayList;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        List<Proxy> list = this.$proxies;
        Map<String, ProxyState> map = this.$links;
        ProxyPageAdapter proxyPageAdapter = this.this$0;
        ProxyState proxyState = this.$parent;
        ArrayList arrayList = new ArrayList(InputKt.collectionSizeOrDefault(list, 10));
        for (Proxy proxy : list) {
            arrayList.add(new ProxyViewState(proxyPageAdapter.config, proxy, proxyState, proxy.type.group ? map.get(proxy.name) : null));
        }
        return arrayList;
    }
}
