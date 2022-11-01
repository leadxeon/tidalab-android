package com.tidalab.v2board.clash.design.adapter;

import com.tidalab.v2board.clash.design.component.ProxyViewState;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: ProxyPageAdapter.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.adapter.ProxyPageAdapter$updateAdapter$2", f = "ProxyPageAdapter.kt", l = {46}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProxyPageAdapter$updateAdapter$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ int $position;
    public final /* synthetic */ boolean $selectable;
    public final /* synthetic */ List<ProxyViewState> $states;
    public Object L$0;
    public int label;
    public final /* synthetic */ ProxyPageAdapter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProxyPageAdapter$updateAdapter$2(ProxyPageAdapter proxyPageAdapter, int i, boolean z, List<ProxyViewState> list, Continuation<? super ProxyPageAdapter$updateAdapter$2> continuation) {
        super(2, continuation);
        this.this$0 = proxyPageAdapter;
        this.$position = i;
        this.$selectable = z;
        this.$states = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProxyPageAdapter$updateAdapter$2(this.this$0, this.$position, this.$selectable, this.$states, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new ProxyPageAdapter$updateAdapter$2(this.this$0, this.$position, this.$selectable, this.$states, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            ProxyAdapter proxyAdapter = this.this$0.adapters.get(this.$position);
            boolean z = this.$selectable;
            List<ProxyViewState> list = this.$states;
            final ProxyAdapter proxyAdapter2 = proxyAdapter;
            proxyAdapter2.selectable = z;
            MutablePropertyReference0Impl proxyPageAdapter$updateAdapter$2$1$1 = new MutablePropertyReference0Impl(proxyAdapter2) { // from class: com.tidalab.v2board.clash.design.adapter.ProxyPageAdapter$updateAdapter$2$1$1
                @Override // kotlin.reflect.KMutableProperty0
                public Object get() {
                    return ((ProxyAdapter) this.receiver).states;
                }

                @Override // kotlin.reflect.KMutableProperty0
                public void set(Object obj2) {
                    ((ProxyAdapter) this.receiver).states = (List) obj2;
                }
            };
            this.L$0 = proxyAdapter;
            this.label = 1;
            if (InputKt.swapDataSet(proxyAdapter2, proxyPageAdapter$updateAdapter$2$1$1, list, false, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.requestRedrawVisible();
        return Unit.INSTANCE;
    }
}
