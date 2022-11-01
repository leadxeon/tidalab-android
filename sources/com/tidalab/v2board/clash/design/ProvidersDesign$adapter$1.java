package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.core.model.Provider;
import com.tidalab.v2board.clash.design.ProvidersDesign;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
/* compiled from: ProvidersDesign.kt */
/* loaded from: classes.dex */
public final class ProvidersDesign$adapter$1 extends Lambda implements Function2<Integer, Provider, Unit> {
    public final /* synthetic */ ProvidersDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProvidersDesign$adapter$1(ProvidersDesign providersDesign) {
        super(2);
        this.this$0 = providersDesign;
    }

    @Override // kotlin.jvm.functions.Function2
    public Unit invoke(Integer num, Provider provider) {
        this.this$0.requests.mo14trySendJP2dKIU(new ProvidersDesign.Request.Update(num.intValue(), provider));
        return Unit.INSTANCE;
    }
}
