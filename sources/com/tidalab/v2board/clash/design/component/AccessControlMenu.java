package com.tidalab.v2board.clash.design.component;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.widget.PopupMenu;
import com.tidalab.v2board.clash.common.store.Store;
import com.tidalab.v2board.clash.design.AccessControlDesign;
import com.tidalab.v2board.clash.design.model.AppInfoSort;
import com.tidalab.v2board.clash.design.store.UiStore;
import com.tidalab.v2board.clash.foss.R;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.channels.Channel;
/* compiled from: AccessControlMenu.kt */
/* loaded from: classes.dex */
public final class AccessControlMenu implements PopupMenu.OnMenuItemClickListener {
    public final PopupMenu menu;
    public final Channel<AccessControlDesign.Request> requests;
    public final UiStore uiStore;

    public AccessControlMenu(Context context, View view, UiStore uiStore, Channel<AccessControlDesign.Request> channel) {
        this.uiStore = uiStore;
        this.requests = channel;
        PopupMenu popupMenu = new PopupMenu(context, view);
        this.menu = popupMenu;
        new SupportMenuInflater(context).inflate(R.menu.menu_access_control, popupMenu.mMenu);
        Store.Delegate delegate = uiStore.accessControlSort$delegate;
        KProperty<?>[] kPropertyArr = UiStore.$$delegatedProperties;
        int ordinal = ((AppInfoSort) delegate.getValue(uiStore, kPropertyArr[6])).ordinal();
        if (ordinal == 0) {
            popupMenu.mMenu.findItem(R.id.name).setChecked(true);
        } else if (ordinal == 1) {
            popupMenu.mMenu.findItem(R.id.package_name).setChecked(true);
        } else if (ordinal == 2) {
            popupMenu.mMenu.findItem(R.id.install_time).setChecked(true);
        } else if (ordinal == 3) {
            popupMenu.mMenu.findItem(R.id.update_time).setChecked(true);
        }
        popupMenu.mMenu.findItem(R.id.system_apps).setChecked(true ^ ((Boolean) uiStore.accessControlSystemApp$delegate.getValue(uiStore, kPropertyArr[8])).booleanValue());
        popupMenu.mMenu.findItem(R.id.reverse).setChecked(((Boolean) uiStore.accessControlReverse$delegate.getValue(uiStore, kPropertyArr[7])).booleanValue());
        popupMenu.mMenuItemClickListener = this;
    }

    @Override // androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener
    public boolean onMenuItemClick(MenuItem menuItem) {
        AccessControlDesign.Request request = AccessControlDesign.Request.ReloadApps;
        if (menuItem.isCheckable()) {
            menuItem.setChecked(!menuItem.isChecked());
        }
        int itemId = menuItem.getItemId();
        if (itemId == R.id.select_all) {
            this.requests.mo14trySendJP2dKIU(AccessControlDesign.Request.SelectAll);
        } else if (itemId == R.id.select_none) {
            this.requests.mo14trySendJP2dKIU(AccessControlDesign.Request.SelectNone);
        } else if (itemId == R.id.select_invert) {
            this.requests.mo14trySendJP2dKIU(AccessControlDesign.Request.SelectInvert);
        } else if (itemId == R.id.system_apps) {
            UiStore uiStore = this.uiStore;
            uiStore.accessControlSystemApp$delegate.setValue(uiStore, UiStore.$$delegatedProperties[8], Boolean.valueOf(!menuItem.isChecked()));
            this.requests.mo14trySendJP2dKIU(request);
        } else if (itemId == R.id.name) {
            UiStore uiStore2 = this.uiStore;
            uiStore2.accessControlSort$delegate.setValue(uiStore2, UiStore.$$delegatedProperties[6], AppInfoSort.Label);
            this.requests.mo14trySendJP2dKIU(request);
        } else if (itemId == R.id.package_name) {
            UiStore uiStore3 = this.uiStore;
            uiStore3.accessControlSort$delegate.setValue(uiStore3, UiStore.$$delegatedProperties[6], AppInfoSort.PackageName);
            this.requests.mo14trySendJP2dKIU(request);
        } else if (itemId == R.id.install_time) {
            UiStore uiStore4 = this.uiStore;
            uiStore4.accessControlSort$delegate.setValue(uiStore4, UiStore.$$delegatedProperties[6], AppInfoSort.InstallTime);
            this.requests.mo14trySendJP2dKIU(request);
        } else if (itemId == R.id.update_time) {
            UiStore uiStore5 = this.uiStore;
            uiStore5.accessControlSort$delegate.setValue(uiStore5, UiStore.$$delegatedProperties[6], AppInfoSort.UpdateTime);
            this.requests.mo14trySendJP2dKIU(request);
        } else if (itemId == R.id.reverse) {
            UiStore uiStore6 = this.uiStore;
            uiStore6.accessControlReverse$delegate.setValue(uiStore6, UiStore.$$delegatedProperties[7], Boolean.valueOf(menuItem.isChecked()));
            this.requests.mo14trySendJP2dKIU(request);
        } else if (itemId == R.id.import_from_clipboard) {
            this.requests.mo14trySendJP2dKIU(AccessControlDesign.Request.Import);
        } else if (itemId != R.id.export_to_clipboard) {
            return false;
        } else {
            this.requests.mo14trySendJP2dKIU(AccessControlDesign.Request.Export);
        }
        return true;
    }
}
