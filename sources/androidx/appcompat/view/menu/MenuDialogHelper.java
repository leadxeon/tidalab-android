package androidx.appcompat.view.menu;

import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.ListMenuPresenter;
import androidx.appcompat.view.menu.MenuPresenter;
/* loaded from: classes.dex */
public class MenuDialogHelper implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, MenuPresenter.Callback {
    public AlertDialog mDialog;
    public MenuBuilder mMenu;
    public ListMenuPresenter mPresenter;

    public MenuDialogHelper(MenuBuilder menuBuilder) {
        this.mMenu = menuBuilder;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.mMenu.performItemAction(((ListMenuPresenter.MenuAdapter) this.mPresenter.getAdapter()).getItem(i), 0);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        AlertDialog alertDialog;
        if ((z || menuBuilder == this.mMenu) && (alertDialog = this.mDialog) != null) {
            alertDialog.dismiss();
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        ListMenuPresenter listMenuPresenter = this.mPresenter;
        MenuBuilder menuBuilder = this.mMenu;
        MenuPresenter.Callback callback = listMenuPresenter.mCallback;
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, true);
        }
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i == 82 || i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.mDialog.getWindow();
                if (!(window2 == null || (decorView2 = window2.getDecorView()) == null || (keyDispatcherState2 = decorView2.getKeyDispatcherState()) == null)) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.mDialog.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.mMenu.close(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.mMenu.performShortcut(i, keyEvent, 0);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
    public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
        return false;
    }
}
