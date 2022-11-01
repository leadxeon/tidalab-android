package com.tidalab.v2board.clash.design.component;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
/* compiled from: ProxyPageFactory.kt */
/* loaded from: classes.dex */
public final class ProxyPageFactory {
    public final RecyclerView.RecycledViewPool childrenPool = new RecyclerView.RecycledViewPool();
    public final ProxyViewConfig config;

    /* compiled from: ProxyPageFactory.kt */
    /* loaded from: classes.dex */
    public static final class Holder extends RecyclerView.ViewHolder {
        public final RecyclerView recyclerView;
        public final View root;

        public Holder(RecyclerView recyclerView, View view) {
            super(view);
            this.recyclerView = recyclerView;
            this.root = view;
        }
    }

    public ProxyPageFactory(ProxyViewConfig proxyViewConfig) {
        this.config = proxyViewConfig;
    }
}
