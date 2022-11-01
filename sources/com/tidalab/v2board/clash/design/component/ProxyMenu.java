package com.tidalab.v2board.clash.design.component;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.PopupMenu;
import com.tidalab.v2board.clash.core.model.ProxySort;
import com.tidalab.v2board.clash.core.model.TunnelState;
import com.tidalab.v2board.clash.design.ProxyDesign;
import com.tidalab.v2board.clash.design.store.UiStore;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.channels.Channel;
/* compiled from: ProxyMenu.kt */
/* loaded from: classes.dex */
public final class ProxyMenu implements PopupMenu.OnMenuItemClickListener {
    public final PopupMenu menu;
    public final Channel<ProxyDesign.Request> requests;
    public final UiStore uiStore;
    public final Function0<Unit> updateConfig;

    /* compiled from: ProxyMenu.kt */
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = {1, 2, 3};
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = {1, 2, 3, 4};

        static {
            ProxySort.values();
            TunnelState.Mode.values();
        }
    }

    public ProxyMenu(Context context, View view, TunnelState.Mode mode, UiStore uiStore, Channel<ProxyDesign.Request> channel, Function0<Unit> function0) {
        this.uiStore = uiStore;
        this.requests = channel;
        this.updateConfig = function0;
        PopupMenu popupMenu = new PopupMenu(context, view);
        this.menu = popupMenu;
        new SupportMenuInflater(context).inflate(R.menu.menu_proxy, popupMenu.mMenu);
        MenuBuilder menuBuilder = popupMenu.mMenu;
        menuBuilder.findItem(R.id.not_selectable).setChecked(uiStore.getProxyExcludeNotSelectable());
        if (uiStore.getProxySingleLine()) {
            menuBuilder.findItem(R.id.single).setChecked(true);
        } else {
            menuBuilder.findItem(R.id.multiple).setChecked(true);
        }
        int ordinal = ((ProxySort) uiStore.proxySort$delegate.getValue(uiStore, UiStore.$$delegatedProperties[4])).ordinal();
        if (ordinal == 0) {
            menuBuilder.findItem(R.id.default_).setChecked(true);
        } else if (ordinal == 1) {
            menuBuilder.findItem(R.id.name).setChecked(true);
        } else if (ordinal == 2) {
            menuBuilder.findItem(R.id.delay).setChecked(true);
        }
        int i = mode == null ? -1 : WhenMappings.$EnumSwitchMapping$1[mode.ordinal()];
        if (i == -1) {
            menuBuilder.findItem(R.id.dont_modify).setChecked(true);
        } else if (i == 1) {
            menuBuilder.findItem(R.id.direct_mode).setChecked(true);
        } else if (i == 2) {
            menuBuilder.findItem(R.id.global_mode).setChecked(true);
        } else if (i == 3) {
            menuBuilder.findItem(R.id.rule_mode).setChecked(true);
        } else if (i == 4) {
            throw new IllegalStateException("invalid mode");
        }
        popupMenu.mMenuItemClickListener = this;
    }

    @Override // androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener
    public boolean onMenuItemClick(MenuItem menuItem) {
        menuItem.setChecked(!menuItem.isChecked());
        int itemId = menuItem.getItemId();
        if (itemId == R.id.not_selectable) {
            UiStore uiStore = this.uiStore;
            uiStore.proxyExcludeNotSelectable$delegate.setValue(uiStore, UiStore.$$delegatedProperties[2], Boolean.valueOf(menuItem.isChecked()));
            this.requests.mo14trySendJP2dKIU(ProxyDesign.Request.ReLaunch.INSTANCE);
        } else if (itemId == R.id.single) {
            UiStore uiStore2 = this.uiStore;
            uiStore2.proxySingleLine$delegate.setValue(uiStore2, UiStore.$$delegatedProperties[3], Boolean.TRUE);
            this.updateConfig.invoke();
            this.requests.mo14trySendJP2dKIU(ProxyDesign.Request.ReloadAll.INSTANCE);
        } else if (itemId == R.id.multiple) {
            UiStore uiStore3 = this.uiStore;
            uiStore3.proxySingleLine$delegate.setValue(uiStore3, UiStore.$$delegatedProperties[3], Boolean.FALSE);
            this.updateConfig.invoke();
            this.requests.mo14trySendJP2dKIU(ProxyDesign.Request.ReloadAll.INSTANCE);
        } else if (itemId == R.id.default_) {
            UiStore uiStore4 = this.uiStore;
            uiStore4.proxySort$delegate.setValue(uiStore4, UiStore.$$delegatedProperties[4], ProxySort.Default);
            this.requests.mo14trySendJP2dKIU(ProxyDesign.Request.ReloadAll.INSTANCE);
        } else if (itemId == R.id.name) {
            UiStore uiStore5 = this.uiStore;
            uiStore5.proxySort$delegate.setValue(uiStore5, UiStore.$$delegatedProperties[4], ProxySort.Title);
            this.requests.mo14trySendJP2dKIU(ProxyDesign.Request.ReloadAll.INSTANCE);
        } else if (itemId == R.id.delay) {
            UiStore uiStore6 = this.uiStore;
            uiStore6.proxySort$delegate.setValue(uiStore6, UiStore.$$delegatedProperties[4], ProxySort.Delay);
            this.requests.mo14trySendJP2dKIU(ProxyDesign.Request.ReloadAll.INSTANCE);
        } else if (itemId == R.id.dont_modify) {
            this.requests.mo14trySendJP2dKIU(new ProxyDesign.Request.PatchMode(null));
        } else if (itemId == R.id.direct_mode) {
            this.requests.mo14trySendJP2dKIU(new ProxyDesign.Request.PatchMode(TunnelState.Mode.Direct));
        } else if (itemId == R.id.global_mode) {
            this.requests.mo14trySendJP2dKIU(new ProxyDesign.Request.PatchMode(TunnelState.Mode.Global));
        } else if (itemId != R.id.rule_mode) {
            return false;
        } else {
            this.requests.mo14trySendJP2dKIU(new ProxyDesign.Request.PatchMode(TunnelState.Mode.Rule));
        }
        return true;
    }
}
