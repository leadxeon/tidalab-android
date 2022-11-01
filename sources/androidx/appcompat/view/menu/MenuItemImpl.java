package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.internal.view.SupportMenuItem;
import androidx.core.view.ActionProvider;
import java.util.Objects;
/* loaded from: classes.dex */
public final class MenuItemImpl implements SupportMenuItem {
    public ActionProvider mActionProvider;
    public View mActionView;
    public final int mCategoryOrder;
    public MenuItem.OnMenuItemClickListener mClickListener;
    public CharSequence mContentDescription;
    public final int mGroup;
    public Drawable mIconDrawable;
    public final int mId;
    public Intent mIntent;
    public MenuBuilder mMenu;
    public ContextMenu.ContextMenuInfo mMenuInfo;
    public MenuItem.OnActionExpandListener mOnActionExpandListener;
    public final int mOrdering;
    public char mShortcutAlphabeticChar;
    public char mShortcutNumericChar;
    public int mShowAsAction;
    public SubMenuBuilder mSubMenu;
    public CharSequence mTitle;
    public CharSequence mTitleCondensed;
    public CharSequence mTooltipText;
    public int mShortcutNumericModifiers = 4096;
    public int mShortcutAlphabeticModifiers = 4096;
    public int mIconResId = 0;
    public ColorStateList mIconTintList = null;
    public PorterDuff.Mode mIconTintMode = null;
    public boolean mHasIconTint = false;
    public boolean mHasIconTintMode = false;
    public boolean mNeedToApplyIconTint = false;
    public int mFlags = 16;
    public boolean mIsActionViewExpanded = false;

    /* renamed from: androidx.appcompat.view.menu.MenuItemImpl$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements ActionProvider.VisibilityListener {
        public AnonymousClass1() {
        }
    }

    public MenuItemImpl(MenuBuilder menuBuilder, int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        this.mShowAsAction = 0;
        this.mMenu = menuBuilder;
        this.mId = i2;
        this.mGroup = i;
        this.mCategoryOrder = i3;
        this.mOrdering = i4;
        this.mTitle = charSequence;
        this.mShowAsAction = i5;
    }

    public static void appendModifier(StringBuilder sb, int i, int i2, String str) {
        if ((i & i2) == i2) {
            sb.append(str);
        }
    }

    public final Drawable applyIconTintIfNecessary(Drawable drawable) {
        if (drawable != null && this.mNeedToApplyIconTint && (this.mHasIconTint || this.mHasIconTintMode)) {
            drawable = AppOpsManagerCompat.wrap(drawable).mutate();
            if (this.mHasIconTint) {
                drawable.setTintList(this.mIconTintList);
            }
            if (this.mHasIconTintMode) {
                drawable.setTintMode(this.mIconTintMode);
            }
            this.mNeedToApplyIconTint = false;
        }
        return drawable;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public boolean collapseActionView() {
        if ((this.mShowAsAction & 8) == 0) {
            return false;
        }
        if (this.mActionView == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.mOnActionExpandListener;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(this)) {
            return this.mMenu.collapseItemActionView(this);
        }
        return false;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public boolean expandActionView() {
        if (!hasCollapsibleActionView()) {
            return false;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.mOnActionExpandListener;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionExpand(this)) {
            return this.mMenu.expandItemActionView(this);
        }
        return false;
    }

    @Override // android.view.MenuItem
    public android.view.ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public View getActionView() {
        View view = this.mActionView;
        if (view != null) {
            return view;
        }
        ActionProvider actionProvider = this.mActionProvider;
        if (actionProvider == null) {
            return null;
        }
        View onCreateActionView = actionProvider.onCreateActionView(this);
        this.mActionView = onCreateActionView;
        return onCreateActionView;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.mShortcutAlphabeticModifiers;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.mShortcutAlphabeticChar;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.mGroup;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        Drawable drawable = this.mIconDrawable;
        if (drawable != null) {
            return applyIconTintIfNecessary(drawable);
        }
        int i = this.mIconResId;
        if (i == 0) {
            return null;
        }
        Drawable drawable2 = AppCompatResources.getDrawable(this.mMenu.mContext, i);
        this.mIconResId = 0;
        this.mIconDrawable = drawable2;
        return applyIconTintIfNecessary(drawable2);
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.mIconTintList;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.mIconTintMode;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.mIntent;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public int getItemId() {
        return this.mId;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.mMenuInfo;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public int getNumericModifiers() {
        return this.mShortcutNumericModifiers;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.mShortcutNumericChar;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.mCategoryOrder;
    }

    public char getShortcut() {
        return this.mMenu.isQwertyMode() ? this.mShortcutAlphabeticChar : this.mShortcutNumericChar;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return this.mSubMenu;
    }

    @Override // androidx.core.internal.view.SupportMenuItem
    public ActionProvider getSupportActionProvider() {
        return this.mActionProvider;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public CharSequence getTitle() {
        return this.mTitle;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.mTitleCondensed;
        return charSequence != null ? charSequence : this.mTitle;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.mTooltipText;
    }

    public boolean hasCollapsibleActionView() {
        ActionProvider actionProvider;
        if ((this.mShowAsAction & 8) == 0) {
            return false;
        }
        if (this.mActionView == null && (actionProvider = this.mActionProvider) != null) {
            this.mActionView = actionProvider.onCreateActionView(this);
        }
        return this.mActionView != null;
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return this.mSubMenu != null;
    }

    public boolean isActionButton() {
        return (this.mFlags & 32) == 32;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return this.mIsActionViewExpanded;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.mFlags & 1) == 1;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.mFlags & 2) == 2;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.mFlags & 16) != 0;
    }

    public boolean isExclusiveCheckable() {
        return (this.mFlags & 4) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        ActionProvider actionProvider = this.mActionProvider;
        return (actionProvider == null || !actionProvider.overridesItemVisibility()) ? (this.mFlags & 8) == 0 : (this.mFlags & 8) == 0 && this.mActionProvider.isVisible();
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(android.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c) {
        if (this.mShortcutAlphabeticChar == c) {
            return this;
        }
        this.mShortcutAlphabeticChar = Character.toLowerCase(c);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z) {
        int i = this.mFlags;
        int i2 = (z ? 1 : 0) | (i & (-2));
        this.mFlags = i2;
        if (i != i2) {
            this.mMenu.onItemsChanged(false);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z) {
        if ((this.mFlags & 4) != 0) {
            MenuBuilder menuBuilder = this.mMenu;
            Objects.requireNonNull(menuBuilder);
            int groupId = getGroupId();
            int size = menuBuilder.mItems.size();
            menuBuilder.stopDispatchingItemsChanged();
            for (int i = 0; i < size; i++) {
                MenuItemImpl menuItemImpl = menuBuilder.mItems.get(i);
                if (menuItemImpl.mGroup == groupId && menuItemImpl.isExclusiveCheckable() && menuItemImpl.isCheckable()) {
                    menuItemImpl.setCheckedInt(menuItemImpl == this);
                }
            }
            menuBuilder.startDispatchingItemsChanged();
        } else {
            setCheckedInt(z);
        }
        return this;
    }

    public void setCheckedInt(boolean z) {
        int i = this.mFlags;
        int i2 = (z ? 2 : 0) | (i & (-3));
        this.mFlags = i2;
        if (i != i2) {
            this.mMenu.onItemsChanged(false);
        }
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    /* renamed from: setContentDescription */
    public MenuItem mo3setContentDescription(CharSequence charSequence) {
        this.mContentDescription = charSequence;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z) {
        if (z) {
            this.mFlags |= 16;
        } else {
            this.mFlags &= -17;
        }
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.mIconResId = 0;
        this.mIconDrawable = drawable;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.mIconTintList = colorStateList;
        this.mHasIconTint = true;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.mIconTintMode = mode;
        this.mHasIconTintMode = true;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.mIntent = intent;
        return this;
    }

    public void setIsActionButton(boolean z) {
        if (z) {
            this.mFlags |= 32;
        } else {
            this.mFlags &= -33;
        }
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c) {
        if (this.mShortcutNumericChar == c) {
            return this;
        }
        this.mShortcutNumericChar = c;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.mOnActionExpandListener = onActionExpandListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.mClickListener = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c, char c2) {
        this.mShortcutNumericChar = c;
        this.mShortcutAlphabeticChar = Character.toLowerCase(c2);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public void setShowAsAction(int i) {
        int i2 = i & 3;
        if (i2 == 0 || i2 == 1 || i2 == 2) {
            this.mShowAsAction = i;
            this.mMenu.onItemActionRequestChanged();
            return;
        }
        throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setShowAsActionFlags(int i) {
        setShowAsAction(i);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem
    public SupportMenuItem setSupportActionProvider(ActionProvider actionProvider) {
        ActionProvider actionProvider2 = this.mActionProvider;
        if (actionProvider2 != null) {
            actionProvider2.mVisibilityListener = null;
        }
        this.mActionView = null;
        this.mActionProvider = actionProvider;
        this.mMenu.onItemsChanged(true);
        ActionProvider actionProvider3 = this.mActionProvider;
        if (actionProvider3 != null) {
            actionProvider3.setVisibilityListener(new AnonymousClass1());
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        this.mMenu.onItemsChanged(false);
        SubMenuBuilder subMenuBuilder = this.mSubMenu;
        if (subMenuBuilder != null) {
            subMenuBuilder.setHeaderTitle(charSequence);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.mTitleCondensed = charSequence;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    /* renamed from: setTooltipText */
    public MenuItem mo4setTooltipText(CharSequence charSequence) {
        this.mTooltipText = charSequence;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z) {
        if (setVisibleInt(z)) {
            MenuBuilder menuBuilder = this.mMenu;
            menuBuilder.mIsVisibleItemsStale = true;
            menuBuilder.onItemsChanged(true);
        }
        return this;
    }

    public boolean setVisibleInt(boolean z) {
        int i = this.mFlags;
        int i2 = (z ? 0 : 8) | (i & (-9));
        this.mFlags = i2;
        return i != i2;
    }

    public boolean shouldShowShortcut() {
        return this.mMenu.isShortcutsVisible() && getShortcut() != 0;
    }

    public String toString() {
        CharSequence charSequence = this.mTitle;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public SupportMenuItem setActionView(View view) {
        int i;
        this.mActionView = view;
        this.mActionProvider = null;
        if (view != null && view.getId() == -1 && (i = this.mId) > 0) {
            view.setId(i);
        }
        this.mMenu.onItemActionRequestChanged();
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    /* renamed from: setContentDescription  reason: collision with other method in class */
    public SupportMenuItem mo3setContentDescription(CharSequence charSequence) {
        this.mContentDescription = charSequence;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    /* renamed from: setTooltipText  reason: collision with other method in class */
    public SupportMenuItem mo4setTooltipText(CharSequence charSequence) {
        this.mTooltipText = charSequence;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c, int i) {
        if (this.mShortcutAlphabeticChar == c && this.mShortcutAlphabeticModifiers == i) {
            return this;
        }
        this.mShortcutAlphabeticChar = Character.toLowerCase(c);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(i);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setNumericShortcut(char c, int i) {
        if (this.mShortcutNumericChar == c && this.mShortcutNumericModifiers == i) {
            return this;
        }
        this.mShortcutNumericChar = c;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(i);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setShortcut(char c, char c2, int i, int i2) {
        this.mShortcutNumericChar = c;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(i);
        this.mShortcutAlphabeticChar = Character.toLowerCase(c2);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(i2);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i) {
        this.mIconDrawable = null;
        this.mIconResId = i;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i) {
        setTitle(this.mMenu.mContext.getString(i));
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setActionView(int i) {
        Context context = this.mMenu.mContext;
        setActionView(LayoutInflater.from(context).inflate(i, (ViewGroup) new LinearLayout(context), false));
        return this;
    }
}
