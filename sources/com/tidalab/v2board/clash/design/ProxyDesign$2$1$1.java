package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.ProxyDesign;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: ProxyDesign.kt */
/* loaded from: classes.dex */
public final class ProxyDesign$2$1$1 extends Lambda implements Function1<String, Unit> {
    public final /* synthetic */ int $index;
    public final /* synthetic */ ProxyDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProxyDesign$2$1$1(ProxyDesign proxyDesign, int i) {
        super(1);
        this.this$0 = proxyDesign;
        this.$index = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(String str) {
        this.this$0.requests.mo14trySendJP2dKIU(new ProxyDesign.Request.Select(this.$index, str));
        return Unit.INSTANCE;
    }
}
