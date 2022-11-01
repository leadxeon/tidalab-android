package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.WindowCallbackWrapper;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.DecorToolbar;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class ToolbarActionBar extends ActionBar {
    public DecorToolbar mDecorToolbar;
    public boolean mLastMenuVisibility;
    public boolean mMenuCallbackSet;
    public final Toolbar.OnMenuItemClickListener mMenuClicker;
    public boolean mToolbarMenuPrepared;
    public Window.Callback mWindowCallback;
    public ArrayList<ActionBar.OnMenuVisibilityListener> mMenuVisibilityListeners = new ArrayList<>();
    public final Runnable mMenuInvalidator = new Runnable() { // from class: androidx.appcompat.app.ToolbarActionBar.1
        @Override // java.lang.Runnable
        public void run() {
            ToolbarActionBar toolbarActionBar = ToolbarActionBar.this;
            Menu menu = toolbarActionBar.getMenu();
            MenuBuilder menuBuilder = menu instanceof MenuBuilder ? (MenuBuilder) menu : null;
            if (menuBuilder != null) {
                menuBuilder.stopDispatchingItemsChanged();
            }
            try {
                menu.clear();
                if (!toolbarActionBar.mWindowCallback.onCreatePanelMenu(0, menu) || !toolbarActionBar.mWindowCallback.onPreparePanel(0, null, menu)) {
                    menu.clear();
                }
            } finally {
                if (menuBuilder != null) {
                    menuBuilder.startDispatchingItemsChanged();
                }
            }
        }
    };

    /* renamed from: androidx.appcompat.app.ToolbarActionBar$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass2 implements Toolbar.OnMenuItemClickListener {
        public AnonymousClass2() {
        }
    }

    /* loaded from: classes.dex */
    public final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        public boolean mClosingActionMenu;

        public ActionMenuPresenterCallback() {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            if (!this.mClosingActionMenu) {
                this.mClosingActionMenu = true;
                ToolbarActionBar.this.mDecorToolbar.dismissPopupMenus();
                Window.Callback callback = ToolbarActionBar.this.mWindowCallback;
                if (callback != null) {
                    callback.onPanelClosed(108, menuBuilder);
                }
                this.mClosingActionMenu = false;
            }
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            Window.Callback callback = ToolbarActionBar.this.mWindowCallback;
            if (callback == null) {
                return false;
            }
            callback.onMenuOpened(108, menuBuilder);
            return true;
        }
    }

    /* loaded from: classes.dex */
    public final class MenuBuilderCallback implements MenuBuilder.Callback {
        public MenuBuilderCallback() {
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public void onMenuModeChange(MenuBuilder menuBuilder) {
            ToolbarActionBar toolbarActionBar = ToolbarActionBar.this;
            if (toolbarActionBar.mWindowCallback == null) {
                return;
            }
            if (toolbarActionBar.mDecorToolbar.isOverflowMenuShowing()) {
                ToolbarActionBar.this.mWindowCallback.onPanelClosed(108, menuBuilder);
            } else if (ToolbarActionBar.this.mWindowCallback.onPreparePanel(0, null, menuBuilder)) {
                ToolbarActionBar.this.mWindowCallback.onMenuOpened(108, menuBuilder);
            }
        }
    }

    /* loaded from: classes.dex */
    public class ToolbarCallbackWrapper extends WindowCallbackWrapper {
        public ToolbarCallbackWrapper(Window.Callback callback) {
            super(callback);
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public View onCreatePanelView(int i) {
            if (i == 0) {
                return new View(ToolbarActionBar.this.mDecorToolbar.getContext());
            }
            return this.mWrapped.onCreatePanelView(i);
        }

        @Override // android.view.Window.Callback
        public boolean onPreparePanel(int i, View view, Menu menu) {
            boolean onPreparePanel = this.mWrapped.onPreparePanel(i, view, menu);
            if (onPreparePanel) {
                ToolbarActionBar toolbarActionBar = ToolbarActionBar.this;
                if (!toolbarActionBar.mToolbarMenuPrepared) {
                    toolbarActionBar.mDecorToolbar.setMenuPrepared();
                    ToolbarActionBar.this.mToolbarMenuPrepared = true;
                }
            }
            return onPreparePanel;
        }
    }

    public ToolbarActionBar(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        AnonymousClass2 r0 = new AnonymousClass2();
        this.mMenuClicker = r0;
        this.mDecorToolbar = new ToolbarWidgetWrapper(toolbar, false);
        ToolbarCallbackWrapper toolbarCallbackWrapper = new ToolbarCallbackWrapper(callback);
        this.mWindowCallback = toolbarCallbackWrapper;
        this.mDecorToolbar.setWindowCallback(toolbarCallbackWrapper);
        toolbar.setOnMenuItemClickListener(r0);
        this.mDecorToolbar.setWindowTitle(charSequence);
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean closeOptionsMenu() {
        return this.mDecorToolbar.hideOverflowMenu();
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean collapseActionView() {
        if (!this.mDecorToolbar.hasExpandedActionView()) {
            return false;
        }
        this.mDecorToolbar.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void dispatchMenuVisibilityChanged(boolean z) {
        if (z != this.mLastMenuVisibility) {
            this.mLastMenuVisibility = z;
            int size = this.mMenuVisibilityListeners.size();
            for (int i = 0; i < size; i++) {
                this.mMenuVisibilityListeners.get(i).onMenuVisibilityChanged(z);
            }
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public int getDisplayOptions() {
        return this.mDecorToolbar.getDisplayOptions();
    }

    public final Menu getMenu() {
        if (!this.mMenuCallbackSet) {
            this.mDecorToolbar.setMenuCallbacks(new ActionMenuPresenterCallback(), new MenuBuilderCallback());
            this.mMenuCallbackSet = true;
        }
        return this.mDecorToolbar.getMenu();
    }

    @Override // androidx.appcompat.app.ActionBar
    public Context getThemedContext() {
        return this.mDecorToolbar.getContext();
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean invalidateOptionsMenu() {
        this.mDecorToolbar.getViewGroup().removeCallbacks(this.mMenuInvalidator);
        ViewGroup viewGroup = this.mDecorToolbar.getViewGroup();
        Runnable runnable = this.mMenuInvalidator;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        viewGroup.postOnAnimation(runnable);
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // androidx.appcompat.app.ActionBar
    public void onDestroy() {
        this.mDecorToolbar.getViewGroup().removeCallbacks(this.mMenuInvalidator);
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        Menu menu = getMenu();
        if (menu == null) {
            return false;
        }
        boolean z = true;
        if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() == 1) {
            z = false;
        }
        menu.setQwertyMode(z);
        return menu.performShortcut(i, keyEvent, 0);
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean onMenuKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            this.mDecorToolbar.showOverflowMenu();
        }
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean openOptionsMenu() {
        return this.mDecorToolbar.showOverflowMenu();
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDefaultDisplayHomeAsUpEnabled(boolean z) {
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDisplayHomeAsUpEnabled(boolean z) {
        this.mDecorToolbar.setDisplayOptions(((z ? 4 : 0) & 4) | ((-5) & this.mDecorToolbar.getDisplayOptions()));
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setHomeAsUpIndicator(Drawable drawable) {
        this.mDecorToolbar.setNavigationIcon(drawable);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setShowHideAnimationEnabled(boolean z) {
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setTitle(CharSequence charSequence) {
        this.mDecorToolbar.setTitle(charSequence);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setWindowTitle(CharSequence charSequence) {
        this.mDecorToolbar.setWindowTitle(charSequence);
    }
}
