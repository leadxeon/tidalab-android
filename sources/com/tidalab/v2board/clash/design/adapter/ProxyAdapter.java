package com.tidalab.v2board.clash.design.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.tidalab.v2board.clash.design.component.ProxyView;
import com.tidalab.v2board.clash.design.component.ProxyViewConfig;
import com.tidalab.v2board.clash.design.component.ProxyViewState;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
/* compiled from: ProxyAdapter.kt */
/* loaded from: classes.dex */
public final class ProxyAdapter extends RecyclerView.Adapter<Holder> {
    public final Function1<String, Unit> clicked;
    public final ProxyViewConfig config;
    public boolean selectable;
    public List<ProxyViewState> states = EmptyList.INSTANCE;

    /* compiled from: ProxyAdapter.kt */
    /* loaded from: classes.dex */
    public static final class Holder extends RecyclerView.ViewHolder {
        public final ProxyView view;

        public Holder(ProxyView proxyView) {
            super(proxyView);
            this.view = proxyView;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ProxyAdapter(ProxyViewConfig proxyViewConfig, Function1<? super String, Unit> function1) {
        this.config = proxyViewConfig;
        this.clicked = function1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.states.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Holder holder, int i) {
        final ProxyViewState proxyViewState = this.states.get(i);
        ProxyView proxyView = holder.view;
        proxyView.setState(proxyViewState);
        proxyView.setOnClickListener(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.adapter.-$$Lambda$ProxyAdapter$muwOHnUI4HeMRYTdiBAAfLX5yO8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProxyAdapter.this.clicked.invoke(proxyViewState.proxy.name);
            }
        });
        boolean z = this.selectable;
        proxyView.setFocusable(z);
        proxyView.setClickable(z);
        proxyViewState.update(true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ProxyViewConfig proxyViewConfig = this.config;
        return new Holder(new ProxyView(proxyViewConfig.context, proxyViewConfig));
    }
}
