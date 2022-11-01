package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.widget.ImageView;
import com.tidalab.v2board.clash.core.model.TunnelState;
import com.tidalab.v2board.clash.design.component.ProxyMenu;
import com.tidalab.v2board.clash.design.store.UiStore;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* compiled from: ProxyDesign.kt */
/* loaded from: classes.dex */
public final class ProxyDesign$menu$2 extends Lambda implements Function0<ProxyMenu> {
    public final /* synthetic */ Context $context;
    public final /* synthetic */ TunnelState.Mode $overrideMode;
    public final /* synthetic */ UiStore $uiStore;
    public final /* synthetic */ ProxyDesign this$0;

    /* compiled from: ProxyDesign.kt */
    /* renamed from: com.tidalab.v2board.clash.design.ProxyDesign$menu$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        public final /* synthetic */ UiStore $uiStore;
        public final /* synthetic */ ProxyDesign this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ProxyDesign proxyDesign, UiStore uiStore) {
            super(0);
            this.this$0 = proxyDesign;
            this.$uiStore = uiStore;
        }

        @Override // kotlin.jvm.functions.Function0
        public Unit invoke() {
            this.this$0.config.singleLine = this.$uiStore.getProxySingleLine();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProxyDesign$menu$2(Context context, ProxyDesign proxyDesign, TunnelState.Mode mode, UiStore uiStore) {
        super(0);
        this.$context = context;
        this.this$0 = proxyDesign;
        this.$overrideMode = mode;
        this.$uiStore = uiStore;
    }

    @Override // kotlin.jvm.functions.Function0
    public ProxyMenu invoke() {
        Context context = this.$context;
        ProxyDesign proxyDesign = this.this$0;
        ImageView imageView = proxyDesign.binding.menuView;
        TunnelState.Mode mode = this.$overrideMode;
        UiStore uiStore = this.$uiStore;
        return new ProxyMenu(context, imageView, mode, uiStore, proxyDesign.requests, new AnonymousClass1(proxyDesign, uiStore));
    }
}
