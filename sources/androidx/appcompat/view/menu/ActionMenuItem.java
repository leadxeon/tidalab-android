package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.internal.view.SupportMenuItem;
/* loaded from: classes.dex */
public class ActionMenuItem implements SupportMenuItem {
    public CharSequence mContentDescription;
    public Context mContext;
    public Drawable mIconDrawable;
    public Intent mIntent;
    public char mShortcutAlphabeticChar;
    public char mShortcutNumericChar;
    public CharSequence mTitle;
    public CharSequence mTitleCondensed;
    public CharSequence mTooltipText;
    public int mShortcutNumericModifiers = 4096;
    public int mShortcutAlphabeticModifiers = 4096;
    public ColorStateList mIconTintList = null;
    public PorterDuff.Mode mIconTintMode = null;
    public boolean mHasIconTint = false;
    public boolean mHasIconTintMode = false;
    public int mFlags = 16;

    public ActionMenuItem(Context context, int i, int i2, int i3, CharSequence charSequence) {
        this.mContext = context;
        this.mTitle = charSequence;
    }

    public final void applyIconTint() {
        Drawable drawable = this.mIconDrawable;
        if (drawable == null) {
            return;
        }
        if (this.mHasIconTint || this.mHasIconTintMode) {
            Drawable wrap = AppOpsManagerCompat.wrap(drawable);
            this.mIconDrawable = wrap;
            Drawable mutate = wrap.mutate();
            this.mIconDrawable = mutate;
            if (this.mHasIconTint) {
                mutate.setTintList(this.mIconTintList);
            }
            if (this.mHasIconTintMode) {
                this.mIconDrawable.setTintMode(this.mIconTintMode);
            }
        }
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public boolean collapseActionView() {
        return false;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public boolean expandActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public View getActionView() {
        return null;
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
        return 0;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return this.mIconDrawable;
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
    public int getItemId() {
        return 16908332;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
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
        return 0;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return null;
    }

    @Override // androidx.core.internal.view.SupportMenuItem
    public androidx.core.view.ActionProvider getSupportActionProvider() {
        return null;
    }

    @Override // android.view.MenuItem
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

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return false;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.mFlags & 1) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.mFlags & 2) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.mFlags & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return (this.mFlags & 8) == 0;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c) {
        this.mShortcutAlphabeticChar = Character.toLowerCase(c);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z) {
        this.mFlags = (z ? 1 : 0) | (this.mFlags & (-2));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z) {
        this.mFlags = (z ? 2 : 0) | (this.mFlags & (-3));
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    /* renamed from: setContentDescription */
    public MenuItem mo3setContentDescription(CharSequence charSequence) {
        this.mContentDescription = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z) {
        this.mFlags = (z ? 16 : 0) | (this.mFlags & (-17));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.mIconDrawable = drawable;
        applyIconTint();
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.mIconTintList = colorStateList;
        this.mHasIconTint = true;
        applyIconTint();
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.mIconTintMode = mode;
        this.mHasIconTintMode = true;
        applyIconTint();
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.mIntent = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c) {
        this.mShortcutNumericChar = c;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c, char c2) {
        this.mShortcutNumericChar = c;
        this.mShortcutAlphabeticChar = Character.toLowerCase(c2);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public void setShowAsAction(int i) {
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setShowAsActionFlags(int i) {
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem
    public SupportMenuItem setSupportActionProvider(androidx.core.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.mTitleCondensed = charSequence;
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    /* renamed from: setTooltipText */
    public MenuItem mo4setTooltipText(CharSequence charSequence) {
        this.mTooltipText = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z) {
        int i = 8;
        int i2 = this.mFlags & 8;
        if (z) {
            i = 0;
        }
        this.mFlags = i2 | i;
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setActionView(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c, int i) {
        this.mShortcutAlphabeticChar = Character.toLowerCase(c);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(i);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    /* renamed from: setContentDescription  reason: collision with other method in class */
    public SupportMenuItem mo3setContentDescription(CharSequence charSequence) {
        this.mContentDescription = charSequence;
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setNumericShortcut(char c, int i) {
        this.mShortcutNumericChar = c;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i) {
        this.mTitle = this.mContext.getResources().getString(i);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    /* renamed from: setTooltipText  reason: collision with other method in class */
    public SupportMenuItem mo4setTooltipText(CharSequence charSequence) {
        this.mTooltipText = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i) {
        Context context = this.mContext;
        Object obj = ContextCompat.sLock;
        this.mIconDrawable = ContextCompat.Api21Impl.getDrawable(context, i);
        applyIconTint();
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setShortcut(char c, char c2, int i, int i2) {
        this.mShortcutNumericChar = c;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(i);
        this.mShortcutAlphabeticChar = Character.toLowerCase(c2);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(i2);
        return this;
    }
}
