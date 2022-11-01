package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.ActionMenuPresenter;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.ForwardingListener;
/* loaded from: classes.dex */
public class ActionMenuItemView extends AppCompatTextView implements MenuView.ItemView, View.OnClickListener, ActionMenuView.ActionMenuChildView {
    public boolean mExpandedFormat;
    public ForwardingListener mForwardingListener;
    public Drawable mIcon;
    public MenuItemImpl mItemData;
    public MenuBuilder.ItemInvoker mItemInvoker;
    public int mMaxIconSize;
    public int mMinWidth;
    public PopupCallback mPopupCallback;
    public CharSequence mTitle;
    public boolean mAllowTextWithIcon = shouldAllowTextWithIcon();
    public int mSavedPaddingLeft = -1;

    /* loaded from: classes.dex */
    public class ActionMenuItemForwardingListener extends ForwardingListener {
        public ActionMenuItemForwardingListener() {
            super(ActionMenuItemView.this);
        }

        @Override // androidx.appcompat.widget.ForwardingListener
        public ShowableListMenu getPopup() {
            ActionMenuPresenter.ActionButtonSubmenu actionButtonSubmenu;
            PopupCallback popupCallback = ActionMenuItemView.this.mPopupCallback;
            if (popupCallback == null || (actionButtonSubmenu = ActionMenuPresenter.this.mActionButtonPopup) == null) {
                return null;
            }
            return actionButtonSubmenu.getPopup();
        }

        @Override // androidx.appcompat.widget.ForwardingListener
        public boolean onForwardingStarted() {
            ShowableListMenu popup;
            ActionMenuItemView actionMenuItemView = ActionMenuItemView.this;
            MenuBuilder.ItemInvoker itemInvoker = actionMenuItemView.mItemInvoker;
            return itemInvoker != null && itemInvoker.invokeItem(actionMenuItemView.mItemData) && (popup = getPopup()) != null && popup.isShowing();
        }
    }

    /* loaded from: classes.dex */
    public static abstract class PopupCallback {
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        Resources resources = context.getResources();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ActionMenuItemView, 0, 0);
        this.mMinWidth = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        obtainStyledAttributes.recycle();
        this.mMaxIconSize = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        setSaveEnabled(false);
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public MenuItemImpl getItemData() {
        return this.mItemData;
    }

    public boolean hasText() {
        return !TextUtils.isEmpty(getText());
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void initialize(MenuItemImpl menuItemImpl, int i) {
        this.mItemData = menuItemImpl;
        setIcon(menuItemImpl.getIcon());
        setTitle(menuItemImpl.getTitleCondensed());
        setId(menuItemImpl.mId);
        setVisibility(menuItemImpl.isVisible() ? 0 : 8);
        setEnabled(menuItemImpl.isEnabled());
        if (menuItemImpl.hasSubMenu() && this.mForwardingListener == null) {
            this.mForwardingListener = new ActionMenuItemForwardingListener();
        }
    }

    @Override // androidx.appcompat.widget.ActionMenuView.ActionMenuChildView
    public boolean needsDividerAfter() {
        return hasText();
    }

    @Override // androidx.appcompat.widget.ActionMenuView.ActionMenuChildView
    public boolean needsDividerBefore() {
        return hasText() && this.mItemData.getIcon() == null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        MenuBuilder.ItemInvoker itemInvoker = this.mItemInvoker;
        if (itemInvoker != null) {
            itemInvoker.invokeItem(this.mItemData);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mAllowTextWithIcon = shouldAllowTextWithIcon();
        updateTextButtonVisibility();
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        boolean hasText = hasText();
        if (hasText && (i4 = this.mSavedPaddingLeft) >= 0) {
            super.setPadding(i4, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int measuredWidth = getMeasuredWidth();
        if (mode == Integer.MIN_VALUE) {
            i3 = Math.min(size, this.mMinWidth);
        } else {
            i3 = this.mMinWidth;
        }
        if (mode != 1073741824 && this.mMinWidth > 0 && measuredWidth < i3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
        }
        if (!hasText && this.mIcon != null) {
            super.setPadding((getMeasuredWidth() - this.mIcon.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ForwardingListener forwardingListener;
        if (!this.mItemData.hasSubMenu() || (forwardingListener = this.mForwardingListener) == null || !forwardingListener.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public void setCheckable(boolean z) {
    }

    public void setChecked(boolean z) {
    }

    public void setExpandedFormat(boolean z) {
        if (this.mExpandedFormat != z) {
            this.mExpandedFormat = z;
            MenuItemImpl menuItemImpl = this.mItemData;
            if (menuItemImpl != null) {
                menuItemImpl.mMenu.onItemActionRequestChanged();
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.mIcon = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i = this.mMaxIconSize;
            if (intrinsicWidth > i) {
                intrinsicHeight = (int) (intrinsicHeight * (i / intrinsicWidth));
                intrinsicWidth = i;
            }
            if (intrinsicHeight > i) {
                intrinsicWidth = (int) (intrinsicWidth * (i / intrinsicHeight));
            } else {
                i = intrinsicHeight;
            }
            drawable.setBounds(0, 0, intrinsicWidth, i);
        }
        setCompoundDrawables(drawable, null, null, null);
        updateTextButtonVisibility();
    }

    public void setItemInvoker(MenuBuilder.ItemInvoker itemInvoker) {
        this.mItemInvoker = itemInvoker;
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        this.mSavedPaddingLeft = i;
        super.setPadding(i, i2, i3, i4);
    }

    public void setPopupCallback(PopupCallback popupCallback) {
        this.mPopupCallback = popupCallback;
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        updateTextButtonVisibility();
    }

    public final boolean shouldAllowTextWithIcon() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        return i >= 480 || (i >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    public final void updateTextButtonVisibility() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.mTitle);
        if (this.mIcon != null) {
            if (!((this.mItemData.mShowAsAction & 4) == 4) || (!this.mAllowTextWithIcon && !this.mExpandedFormat)) {
                z = false;
            }
        }
        boolean z3 = z2 & z;
        CharSequence charSequence = null;
        setText(z3 ? this.mTitle : null);
        CharSequence charSequence2 = this.mItemData.mContentDescription;
        if (TextUtils.isEmpty(charSequence2)) {
            setContentDescription(z3 ? null : this.mItemData.mTitle);
        } else {
            setContentDescription(charSequence2);
        }
        CharSequence charSequence3 = this.mItemData.mTooltipText;
        if (TextUtils.isEmpty(charSequence3)) {
            if (!z3) {
                charSequence = this.mItemData.mTitle;
            }
            R$string.setTooltipText(this, charSequence);
            return;
        }
        R$string.setTooltipText(this, charSequence3);
    }
}
