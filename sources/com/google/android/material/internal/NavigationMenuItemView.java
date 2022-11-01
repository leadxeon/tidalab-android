package com.google.android.material.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.R$string;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.tidalab.v2board.clash.foss.R;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class NavigationMenuItemView extends ForegroundLinearLayout implements MenuView.ItemView {
    public static final int[] CHECKED_STATE_SET = {16842912};
    public final AccessibilityDelegateCompat accessibilityDelegate;
    public FrameLayout actionArea;
    public boolean checkable;
    public Drawable emptyDrawable;
    public boolean hasIconTintList;
    public int iconSize;
    public ColorStateList iconTintList;
    public MenuItemImpl itemData;
    public boolean needsEmptyIcon;
    public final CheckedTextView textView;

    public NavigationMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        AccessibilityDelegateCompat accessibilityDelegateCompat = new AccessibilityDelegateCompat() { // from class: com.google.android.material.internal.NavigationMenuItemView.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
                accessibilityNodeInfoCompat.mInfo.setCheckable(NavigationMenuItemView.this.checkable);
            }
        };
        this.accessibilityDelegate = accessibilityDelegateCompat;
        setOrientation(0);
        LayoutInflater.from(context).inflate(R.layout.design_navigation_menu_item, (ViewGroup) this, true);
        setIconSize(context.getResources().getDimensionPixelSize(R.dimen.design_navigation_icon_size));
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(R.id.design_menu_item_text);
        this.textView = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        ViewCompat.setAccessibilityDelegate(checkedTextView, accessibilityDelegateCompat);
    }

    private void setActionView(View view) {
        if (view != null) {
            if (this.actionArea == null) {
                this.actionArea = (FrameLayout) ((ViewStub) findViewById(R.id.design_menu_item_action_area_stub)).inflate();
            }
            this.actionArea.removeAllViews();
            this.actionArea.addView(view);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public MenuItemImpl getItemData() {
        return this.itemData;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void initialize(MenuItemImpl menuItemImpl, int i) {
        StateListDrawable stateListDrawable;
        this.itemData = menuItemImpl;
        int i2 = menuItemImpl.mId;
        if (i2 > 0) {
            setId(i2);
        }
        setVisibility(menuItemImpl.isVisible() ? 0 : 8);
        boolean z = true;
        if (getBackground() == null) {
            TypedValue typedValue = new TypedValue();
            if (getContext().getTheme().resolveAttribute(R.attr.colorControlHighlight, typedValue, true)) {
                stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(CHECKED_STATE_SET, new ColorDrawable(typedValue.data));
                stateListDrawable.addState(ViewGroup.EMPTY_STATE_SET, new ColorDrawable(0));
            } else {
                stateListDrawable = null;
            }
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            setBackground(stateListDrawable);
        }
        setCheckable(menuItemImpl.isCheckable());
        setChecked(menuItemImpl.isChecked());
        setEnabled(menuItemImpl.isEnabled());
        setTitle(menuItemImpl.mTitle);
        setIcon(menuItemImpl.getIcon());
        setActionView(menuItemImpl.getActionView());
        setContentDescription(menuItemImpl.mContentDescription);
        R$string.setTooltipText(this, menuItemImpl.mTooltipText);
        MenuItemImpl menuItemImpl2 = this.itemData;
        if (!(menuItemImpl2.mTitle == null && menuItemImpl2.getIcon() == null && this.itemData.getActionView() != null)) {
            z = false;
        }
        if (z) {
            this.textView.setVisibility(8);
            FrameLayout frameLayout = this.actionArea;
            if (frameLayout != null) {
                LinearLayoutCompat.LayoutParams layoutParams = (LinearLayoutCompat.LayoutParams) frameLayout.getLayoutParams();
                ((LinearLayout.LayoutParams) layoutParams).width = -1;
                this.actionArea.setLayoutParams(layoutParams);
                return;
            }
            return;
        }
        this.textView.setVisibility(0);
        FrameLayout frameLayout2 = this.actionArea;
        if (frameLayout2 != null) {
            LinearLayoutCompat.LayoutParams layoutParams2 = (LinearLayoutCompat.LayoutParams) frameLayout2.getLayoutParams();
            ((LinearLayout.LayoutParams) layoutParams2).width = -2;
            this.actionArea.setLayoutParams(layoutParams2);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        MenuItemImpl menuItemImpl = this.itemData;
        if (menuItemImpl != null && menuItemImpl.isCheckable() && this.itemData.isChecked()) {
            ViewGroup.mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    public void setCheckable(boolean z) {
        refreshDrawableState();
        if (this.checkable != z) {
            this.checkable = z;
            this.accessibilityDelegate.sendAccessibilityEvent(this.textView, 2048);
        }
    }

    public void setChecked(boolean z) {
        refreshDrawableState();
        this.textView.setChecked(z);
    }

    public void setHorizontalPadding(int i) {
        setPadding(i, 0, i, 0);
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            if (this.hasIconTintList) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                drawable = AppOpsManagerCompat.wrap(drawable).mutate();
                drawable.setTintList(this.iconTintList);
            }
            int i = this.iconSize;
            drawable.setBounds(0, 0, i, i);
        } else if (this.needsEmptyIcon) {
            if (this.emptyDrawable == null) {
                Resources resources = getResources();
                Resources.Theme theme = getContext().getTheme();
                ThreadLocal<TypedValue> threadLocal = ResourcesCompat.sTempTypedValue;
                Drawable drawable2 = resources.getDrawable(R.drawable.navigation_empty_icon, theme);
                this.emptyDrawable = drawable2;
                if (drawable2 != null) {
                    int i2 = this.iconSize;
                    drawable2.setBounds(0, 0, i2, i2);
                }
            }
            drawable = this.emptyDrawable;
        }
        this.textView.setCompoundDrawablesRelative(drawable, null, null, null);
    }

    public void setIconPadding(int i) {
        this.textView.setCompoundDrawablePadding(i);
    }

    public void setIconSize(int i) {
        this.iconSize = i;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.iconTintList = colorStateList;
        this.hasIconTintList = colorStateList != null;
        MenuItemImpl menuItemImpl = this.itemData;
        if (menuItemImpl != null) {
            setIcon(menuItemImpl.getIcon());
        }
    }

    public void setMaxLines(int i) {
        this.textView.setMaxLines(i);
    }

    public void setNeedsEmptyIcon(boolean z) {
        this.needsEmptyIcon = z;
    }

    public void setTextAppearance(int i) {
        AppOpsManagerCompat.setTextAppearance(this.textView, i);
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.textView.setTextColor(colorStateList);
    }

    public void setTitle(CharSequence charSequence) {
        this.textView.setText(charSequence);
    }
}
