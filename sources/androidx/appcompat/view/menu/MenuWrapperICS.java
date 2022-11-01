package androidx.appcompat.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.collection.SimpleArrayMap;
import androidx.core.internal.view.SupportMenu;
import androidx.core.internal.view.SupportMenuItem;
import androidx.core.internal.view.SupportSubMenu;
/* loaded from: classes.dex */
public class MenuWrapperICS extends BaseMenuWrapper implements Menu {
    public final SupportMenu mWrappedObject;

    public MenuWrapperICS(Context context, SupportMenu supportMenu) {
        super(context);
        if (supportMenu != null) {
            this.mWrappedObject = supportMenu;
            return;
        }
        throw new IllegalArgumentException("Wrapped Object can not be null.");
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return getMenuItemWrapper(this.mWrappedObject.add(charSequence));
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2 = menuItemArr != null ? new MenuItem[menuItemArr.length] : null;
        int addIntentOptions = this.mWrappedObject.addIntentOptions(i, i2, i3, componentName, intentArr, intent, i4, menuItemArr2);
        if (menuItemArr2 != null) {
            int length = menuItemArr2.length;
            for (int i5 = 0; i5 < length; i5++) {
                menuItemArr[i5] = getMenuItemWrapper(menuItemArr2[i5]);
            }
        }
        return addIntentOptions;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return getSubMenuWrapper(this.mWrappedObject.addSubMenu(charSequence));
    }

    @Override // android.view.Menu
    public void clear() {
        SimpleArrayMap<SupportMenuItem, MenuItem> simpleArrayMap = this.mMenuItems;
        if (simpleArrayMap != null) {
            simpleArrayMap.clear();
        }
        SimpleArrayMap<SupportSubMenu, SubMenu> simpleArrayMap2 = this.mSubMenus;
        if (simpleArrayMap2 != null) {
            simpleArrayMap2.clear();
        }
        this.mWrappedObject.clear();
    }

    @Override // android.view.Menu
    public void close() {
        this.mWrappedObject.close();
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i) {
        return getMenuItemWrapper(this.mWrappedObject.findItem(i));
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i) {
        return getMenuItemWrapper(this.mWrappedObject.getItem(i));
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        return this.mWrappedObject.hasVisibleItems();
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return this.mWrappedObject.isShortcutKey(i, keyEvent);
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i, int i2) {
        return this.mWrappedObject.performIdentifierAction(i, i2);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        return this.mWrappedObject.performShortcut(i, keyEvent, i2);
    }

    @Override // android.view.Menu
    public void removeGroup(int i) {
        if (this.mMenuItems != null) {
            int i2 = 0;
            while (true) {
                SimpleArrayMap<SupportMenuItem, MenuItem> simpleArrayMap = this.mMenuItems;
                if (i2 >= simpleArrayMap.mSize) {
                    break;
                }
                if (simpleArrayMap.keyAt(i2).getGroupId() == i) {
                    this.mMenuItems.removeAt(i2);
                    i2--;
                }
                i2++;
            }
        }
        this.mWrappedObject.removeGroup(i);
    }

    @Override // android.view.Menu
    public void removeItem(int i) {
        if (this.mMenuItems != null) {
            int i2 = 0;
            while (true) {
                SimpleArrayMap<SupportMenuItem, MenuItem> simpleArrayMap = this.mMenuItems;
                if (i2 >= simpleArrayMap.mSize) {
                    break;
                } else if (simpleArrayMap.keyAt(i2).getItemId() == i) {
                    this.mMenuItems.removeAt(i2);
                    break;
                } else {
                    i2++;
                }
            }
        }
        this.mWrappedObject.removeItem(i);
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i, boolean z, boolean z2) {
        this.mWrappedObject.setGroupCheckable(i, z, z2);
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i, boolean z) {
        this.mWrappedObject.setGroupEnabled(i, z);
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i, boolean z) {
        this.mWrappedObject.setGroupVisible(i, z);
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z) {
        this.mWrappedObject.setQwertyMode(z);
    }

    @Override // android.view.Menu
    public int size() {
        return this.mWrappedObject.size();
    }

    @Override // android.view.Menu
    public MenuItem add(int i) {
        return getMenuItemWrapper(this.mWrappedObject.add(i));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i) {
        return getSubMenuWrapper(this.mWrappedObject.addSubMenu(i));
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return getMenuItemWrapper(this.mWrappedObject.add(i, i2, i3, charSequence));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        return getSubMenuWrapper(this.mWrappedObject.addSubMenu(i, i2, i3, charSequence));
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, int i4) {
        return getMenuItemWrapper(this.mWrappedObject.add(i, i2, i3, i4));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return getSubMenuWrapper(this.mWrappedObject.addSubMenu(i, i2, i3, i4));
    }
}
