package com.tidalab.v2board.clash.design.adapter;

import androidx.recyclerview.widget.RecyclerView;
import com.tidalab.v2board.clash.design.model.ProxyPageState;
import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
/* compiled from: ProxyPageAdapter.kt */
/* loaded from: classes.dex */
public final class ProxyPageAdapter$onCreateViewHolder$1 extends Lambda implements Function2<RecyclerView, Boolean, Unit> {
    public final /* synthetic */ ProxyPageAdapter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProxyPageAdapter$onCreateViewHolder$1(ProxyPageAdapter proxyPageAdapter) {
        super(2);
        this.this$0 = proxyPageAdapter;
    }

    @Override // kotlin.jvm.functions.Function2
    public Unit invoke(RecyclerView recyclerView, Boolean bool) {
        boolean booleanValue = bool.booleanValue();
        Objects.requireNonNull(this.this$0);
        Object tag = recyclerView.getTag();
        Integer num = tag instanceof Integer ? (Integer) tag : null;
        int intValue = num == null ? -1 : num.intValue();
        ProxyPageState proxyPageState = this.this$0.states.get(intValue);
        if (proxyPageState.bottom != booleanValue) {
            proxyPageState.bottom = booleanValue;
            this.this$0.stateChanged.invoke(Integer.valueOf(intValue));
        }
        return Unit.INSTANCE;
    }
}
