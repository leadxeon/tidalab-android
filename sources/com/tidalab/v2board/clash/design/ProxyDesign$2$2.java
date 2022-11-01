package com.tidalab.v2board.clash.design;

import androidx.viewpager2.widget.ViewPager2;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: ProxyDesign.kt */
/* loaded from: classes.dex */
public final class ProxyDesign$2$2 extends Lambda implements Function1<Integer, Unit> {
    public final /* synthetic */ ViewPager2 $this_apply;
    public final /* synthetic */ ProxyDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProxyDesign$2$2(ViewPager2 viewPager2, ProxyDesign proxyDesign) {
        super(1);
        this.$this_apply = viewPager2;
        this.this$0 = proxyDesign;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Integer num) {
        if (num.intValue() == this.$this_apply.getCurrentItem()) {
            this.this$0.updateUrlTestButtonStatus();
        }
        return Unit.INSTANCE;
    }
}
