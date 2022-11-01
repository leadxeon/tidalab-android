package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.R$string;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.BaseMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPopup;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.appcompat.widget.ActionMenuView;
import androidx.core.view.ActionProvider;
import com.tidalab.v2board.clash.foss.R;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public class ActionMenuPresenter extends BaseMenuPresenter {
    public ActionButtonSubmenu mActionButtonPopup;
    public int mActionItemWidthLimit;
    public boolean mExpandedActionViewsExclusive;
    public int mMaxItems;
    public int mMinCellSize;
    public OverflowMenuButton mOverflowButton;
    public OverflowPopup mOverflowPopup;
    public Drawable mPendingOverflowIcon;
    public boolean mPendingOverflowIconSet;
    public ActionMenuPopupCallback mPopupCallback;
    public OpenOverflowRunnable mPostedOpenRunnable;
    public boolean mReserveOverflow;
    public boolean mReserveOverflowSet;
    public int mWidthLimit;
    public final SparseBooleanArray mActionButtonGroups = new SparseBooleanArray();
    public final PopupPresenterCallback mPopupPresenterCallback = new PopupPresenterCallback();

    /* loaded from: classes.dex */
    public class ActionButtonSubmenu extends MenuPopupHelper {
        public ActionButtonSubmenu(Context context, SubMenuBuilder subMenuBuilder, View view) {
            super(context, subMenuBuilder, view, false, R.attr.actionOverflowMenuStyle, 0);
            if (!subMenuBuilder.mItem.isActionButton()) {
                View view2 = ActionMenuPresenter.this.mOverflowButton;
                this.mAnchorView = view2 == null ? (View) ActionMenuPresenter.this.mMenuView : view2;
            }
            setPresenterCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
        }

        @Override // androidx.appcompat.view.menu.MenuPopupHelper
        public void onDismiss() {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            actionMenuPresenter.mActionButtonPopup = null;
            Objects.requireNonNull(actionMenuPresenter);
            super.onDismiss();
        }
    }

    /* loaded from: classes.dex */
    public class ActionMenuPopupCallback extends ActionMenuItemView.PopupCallback {
        public ActionMenuPopupCallback() {
        }
    }

    /* loaded from: classes.dex */
    public class OpenOverflowRunnable implements Runnable {
        public OverflowPopup mPopup;

        public OpenOverflowRunnable(OverflowPopup overflowPopup) {
            this.mPopup = overflowPopup;
        }

        @Override // java.lang.Runnable
        public void run() {
            MenuBuilder.Callback callback;
            MenuBuilder menuBuilder = ActionMenuPresenter.this.mMenu;
            if (!(menuBuilder == null || (callback = menuBuilder.mCallback) == null)) {
                callback.onMenuModeChange(menuBuilder);
            }
            View view = (View) ActionMenuPresenter.this.mMenuView;
            if (!(view == null || view.getWindowToken() == null || !this.mPopup.tryShow())) {
                ActionMenuPresenter.this.mOverflowPopup = this.mPopup;
            }
            ActionMenuPresenter.this.mPostedOpenRunnable = null;
        }
    }

    /* loaded from: classes.dex */
    public class OverflowMenuButton extends AppCompatImageView implements ActionMenuView.ActionMenuChildView {
        public OverflowMenuButton(Context context) {
            super(context, null, R.attr.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            R$string.setTooltipText(this, getContentDescription());
            setOnTouchListener(new ForwardingListener(this, ActionMenuPresenter.this) { // from class: androidx.appcompat.widget.ActionMenuPresenter.OverflowMenuButton.1
                @Override // androidx.appcompat.widget.ForwardingListener
                public ShowableListMenu getPopup() {
                    OverflowPopup overflowPopup = ActionMenuPresenter.this.mOverflowPopup;
                    if (overflowPopup == null) {
                        return null;
                    }
                    return overflowPopup.getPopup();
                }

                @Override // androidx.appcompat.widget.ForwardingListener
                public boolean onForwardingStarted() {
                    ActionMenuPresenter.this.showOverflowMenu();
                    return true;
                }

                @Override // androidx.appcompat.widget.ForwardingListener
                public boolean onForwardingStopped() {
                    ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
                    if (actionMenuPresenter.mPostedOpenRunnable != null) {
                        return false;
                    }
                    actionMenuPresenter.hideOverflowMenu();
                    return true;
                }
            });
        }

        @Override // androidx.appcompat.widget.ActionMenuView.ActionMenuChildView
        public boolean needsDividerAfter() {
            return false;
        }

        @Override // androidx.appcompat.widget.ActionMenuView.ActionMenuChildView
        public boolean needsDividerBefore() {
            return false;
        }

        @Override // android.view.View
        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            ActionMenuPresenter.this.showOverflowMenu();
            return true;
        }

        @Override // android.widget.ImageView
        public boolean setFrame(int i, int i2, int i3, int i4) {
            boolean frame = super.setFrame(i, i2, i3, i4);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (!(drawable == null || background == null)) {
                int width = getWidth();
                int height = getHeight();
                int max = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                background.setHotspotBounds(paddingLeft - max, paddingTop - max, paddingLeft + max, paddingTop + max);
            }
            return frame;
        }
    }

    /* loaded from: classes.dex */
    public class OverflowPopup extends MenuPopupHelper {
        public OverflowPopup(Context context, MenuBuilder menuBuilder, View view, boolean z) {
            super(context, menuBuilder, view, z, R.attr.actionOverflowMenuStyle, 0);
            this.mDropDownGravity = 8388613;
            setPresenterCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
        }

        @Override // androidx.appcompat.view.menu.MenuPopupHelper
        public void onDismiss() {
            MenuBuilder menuBuilder = ActionMenuPresenter.this.mMenu;
            if (menuBuilder != null) {
                menuBuilder.close(true);
            }
            ActionMenuPresenter.this.mOverflowPopup = null;
            super.onDismiss();
        }
    }

    /* loaded from: classes.dex */
    public class PopupPresenterCallback implements MenuPresenter.Callback {
        public PopupPresenterCallback() {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            if (menuBuilder instanceof SubMenuBuilder) {
                menuBuilder.getRootMenu().close(false);
            }
            MenuPresenter.Callback callback = ActionMenuPresenter.this.mCallback;
            if (callback != null) {
                callback.onCloseMenu(menuBuilder, z);
            }
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            if (menuBuilder == actionMenuPresenter.mMenu) {
                return false;
            }
            int i = ((SubMenuBuilder) menuBuilder).mItem.mId;
            MenuPresenter.Callback callback = actionMenuPresenter.mCallback;
            if (callback != null) {
                return callback.onOpenSubMenu(menuBuilder);
            }
            return false;
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, R.layout.abc_action_menu_layout, R.layout.abc_action_menu_item_layout);
    }

    public boolean dismissPopupMenus() {
        return hideOverflowMenu() | hideSubMenus();
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        int i;
        ArrayList<MenuItemImpl> arrayList;
        int i2;
        boolean z;
        MenuBuilder menuBuilder = this.mMenu;
        if (menuBuilder != null) {
            arrayList = menuBuilder.getVisibleItems();
            i = arrayList.size();
        } else {
            arrayList = null;
            i = 0;
        }
        int i3 = this.mMaxItems;
        int i4 = this.mActionItemWidthLimit;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) this.mMenuView;
        int i5 = 0;
        boolean z2 = false;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            i2 = 2;
            z = true;
            if (i5 >= i) {
                break;
            }
            MenuItemImpl menuItemImpl = arrayList.get(i5);
            int i8 = menuItemImpl.mShowAsAction;
            if ((i8 & 2) == 2) {
                i7++;
            } else if ((i8 & 1) == 1) {
                i6++;
            } else {
                z2 = true;
            }
            if (this.mExpandedActionViewsExclusive && menuItemImpl.mIsActionViewExpanded) {
                i3 = 0;
            }
            i5++;
        }
        if (this.mReserveOverflow && (z2 || i6 + i7 > i3)) {
            i3--;
        }
        int i9 = i3 - i7;
        SparseBooleanArray sparseBooleanArray = this.mActionButtonGroups;
        sparseBooleanArray.clear();
        int i10 = 0;
        int i11 = 0;
        while (i10 < i) {
            MenuItemImpl menuItemImpl2 = arrayList.get(i10);
            int i12 = menuItemImpl2.mShowAsAction;
            if ((i12 & 2) == i2) {
                View itemView = getItemView(menuItemImpl2, null, viewGroup);
                itemView.measure(makeMeasureSpec, makeMeasureSpec);
                int measuredWidth = itemView.getMeasuredWidth();
                i4 -= measuredWidth;
                if (i11 == 0) {
                    i11 = measuredWidth;
                }
                int i13 = menuItemImpl2.mGroup;
                if (i13 != 0) {
                    sparseBooleanArray.put(i13, z);
                }
                menuItemImpl2.setIsActionButton(z);
            } else if ((i12 & 1) == z) {
                int i14 = menuItemImpl2.mGroup;
                boolean z3 = sparseBooleanArray.get(i14);
                boolean z4 = (i9 > 0 || z3) && i4 > 0;
                if (z4) {
                    View itemView2 = getItemView(menuItemImpl2, null, viewGroup);
                    itemView2.measure(makeMeasureSpec, makeMeasureSpec);
                    int measuredWidth2 = itemView2.getMeasuredWidth();
                    i4 -= measuredWidth2;
                    if (i11 == 0) {
                        i11 = measuredWidth2;
                    }
                    z4 &= i4 + i11 > 0;
                }
                if (z4 && i14 != 0) {
                    sparseBooleanArray.put(i14, true);
                } else if (z3) {
                    sparseBooleanArray.put(i14, false);
                    for (int i15 = 0; i15 < i10; i15++) {
                        MenuItemImpl menuItemImpl3 = arrayList.get(i15);
                        if (menuItemImpl3.mGroup == i14) {
                            if (menuItemImpl3.isActionButton()) {
                                i9++;
                            }
                            menuItemImpl3.setIsActionButton(false);
                        }
                    }
                }
                if (z4) {
                    i9--;
                }
                menuItemImpl2.setIsActionButton(z4);
            } else {
                menuItemImpl2.setIsActionButton(false);
                i10++;
                i2 = 2;
                z = true;
            }
            i10++;
            i2 = 2;
            z = true;
        }
        return true;
    }

    public View getItemView(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        MenuView.ItemView itemView;
        View actionView = menuItemImpl.getActionView();
        int i = 0;
        if (actionView == null || menuItemImpl.hasCollapsibleActionView()) {
            if (view instanceof MenuView.ItemView) {
                itemView = (MenuView.ItemView) view;
            } else {
                itemView = (MenuView.ItemView) this.mSystemInflater.inflate(this.mItemLayoutRes, viewGroup, false);
            }
            itemView.initialize(menuItemImpl, 0);
            ActionMenuItemView actionMenuItemView = (ActionMenuItemView) itemView;
            actionMenuItemView.setItemInvoker((ActionMenuView) this.mMenuView);
            if (this.mPopupCallback == null) {
                this.mPopupCallback = new ActionMenuPopupCallback();
            }
            actionMenuItemView.setPopupCallback(this.mPopupCallback);
            actionView = (View) itemView;
        }
        if (menuItemImpl.mIsActionViewExpanded) {
            i = 8;
        }
        actionView.setVisibility(i);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    public boolean hideOverflowMenu() {
        MenuView menuView;
        OpenOverflowRunnable openOverflowRunnable = this.mPostedOpenRunnable;
        if (openOverflowRunnable == null || (menuView = this.mMenuView) == null) {
            OverflowPopup overflowPopup = this.mOverflowPopup;
            if (overflowPopup == null) {
                return false;
            }
            if (overflowPopup.isShowing()) {
                overflowPopup.mPopup.dismiss();
            }
            return true;
        }
        ((View) menuView).removeCallbacks(openOverflowRunnable);
        this.mPostedOpenRunnable = null;
        return true;
    }

    public boolean hideSubMenus() {
        ActionButtonSubmenu actionButtonSubmenu = this.mActionButtonPopup;
        if (actionButtonSubmenu == null) {
            return false;
        }
        if (!actionButtonSubmenu.isShowing()) {
            return true;
        }
        actionButtonSubmenu.mPopup.dismiss();
        return true;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void initForMenu(Context context, MenuBuilder menuBuilder) {
        this.mContext = context;
        LayoutInflater.from(context);
        this.mMenu = menuBuilder;
        Resources resources = context.getResources();
        if (!this.mReserveOverflowSet) {
            this.mReserveOverflow = true;
        }
        int i = 2;
        this.mWidthLimit = context.getResources().getDisplayMetrics().widthPixels / 2;
        Configuration configuration = context.getResources().getConfiguration();
        int i2 = configuration.screenWidthDp;
        int i3 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i2 > 600 || ((i2 > 960 && i3 > 720) || (i2 > 720 && i3 > 960))) {
            i = 5;
        } else if (i2 >= 500 || ((i2 > 640 && i3 > 480) || (i2 > 480 && i3 > 640))) {
            i = 4;
        } else if (i2 >= 360) {
            i = 3;
        }
        this.mMaxItems = i;
        int i4 = this.mWidthLimit;
        if (this.mReserveOverflow) {
            if (this.mOverflowButton == null) {
                OverflowMenuButton overflowMenuButton = new OverflowMenuButton(this.mSystemContext);
                this.mOverflowButton = overflowMenuButton;
                if (this.mPendingOverflowIconSet) {
                    overflowMenuButton.setImageDrawable(this.mPendingOverflowIcon);
                    this.mPendingOverflowIcon = null;
                    this.mPendingOverflowIconSet = false;
                }
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.mOverflowButton.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i4 -= this.mOverflowButton.getMeasuredWidth();
        } else {
            this.mOverflowButton = null;
        }
        this.mActionItemWidthLimit = i4;
        this.mMinCellSize = (int) (resources.getDisplayMetrics().density * 56.0f);
    }

    public boolean isOverflowMenuShowing() {
        OverflowPopup overflowPopup = this.mOverflowPopup;
        return overflowPopup != null && overflowPopup.isShowing();
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        dismissPopupMenus();
        MenuPresenter.Callback callback = this.mCallback;
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, z);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        boolean z = false;
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        SubMenuBuilder subMenuBuilder2 = subMenuBuilder;
        while (true) {
            MenuBuilder menuBuilder = subMenuBuilder2.mParentMenu;
            if (menuBuilder == this.mMenu) {
                break;
            }
            subMenuBuilder2 = (SubMenuBuilder) menuBuilder;
        }
        MenuItemImpl menuItemImpl = subMenuBuilder2.mItem;
        ViewGroup viewGroup = (ViewGroup) this.mMenuView;
        View view = null;
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    break;
                }
                View childAt = viewGroup.getChildAt(i);
                if ((childAt instanceof MenuView.ItemView) && ((MenuView.ItemView) childAt).getItemData() == menuItemImpl) {
                    view = childAt;
                    break;
                }
                i++;
            }
        }
        if (view == null) {
            return false;
        }
        int i2 = subMenuBuilder.mItem.mId;
        int size = subMenuBuilder.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                break;
            }
            MenuItem item = subMenuBuilder.getItem(i3);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
            i3++;
        }
        ActionButtonSubmenu actionButtonSubmenu = new ActionButtonSubmenu(this.mContext, subMenuBuilder, view);
        this.mActionButtonPopup = actionButtonSubmenu;
        actionButtonSubmenu.mForceShowIcon = z;
        MenuPopup menuPopup = actionButtonSubmenu.mPopup;
        if (menuPopup != null) {
            menuPopup.setForceShowIcon(z);
        }
        if (this.mActionButtonPopup.tryShow()) {
            MenuPresenter.Callback callback = this.mCallback;
            if (callback != null) {
                callback.onOpenSubMenu(subMenuBuilder);
            }
            return true;
        }
        throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
    }

    public boolean showOverflowMenu() {
        MenuBuilder menuBuilder;
        if (!this.mReserveOverflow || isOverflowMenuShowing() || (menuBuilder = this.mMenu) == null || this.mMenuView == null || this.mPostedOpenRunnable != null) {
            return false;
        }
        menuBuilder.flagActionItems();
        if (menuBuilder.mNonActionItems.isEmpty()) {
            return false;
        }
        OpenOverflowRunnable openOverflowRunnable = new OpenOverflowRunnable(new OverflowPopup(this.mContext, this.mMenu, this.mOverflowButton, true));
        this.mPostedOpenRunnable = openOverflowRunnable;
        ((View) this.mMenuView).post(openOverflowRunnable);
        return true;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        int i;
        boolean z2;
        ViewGroup viewGroup = (ViewGroup) this.mMenuView;
        ArrayList<MenuItemImpl> arrayList = null;
        boolean z3 = false;
        if (viewGroup != null) {
            MenuBuilder menuBuilder = this.mMenu;
            if (menuBuilder != null) {
                menuBuilder.flagActionItems();
                ArrayList<MenuItemImpl> visibleItems = this.mMenu.getVisibleItems();
                int size = visibleItems.size();
                i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    MenuItemImpl menuItemImpl = visibleItems.get(i2);
                    if (menuItemImpl.isActionButton()) {
                        View childAt = viewGroup.getChildAt(i);
                        MenuItemImpl itemData = childAt instanceof MenuView.ItemView ? ((MenuView.ItemView) childAt).getItemData() : null;
                        View itemView = getItemView(menuItemImpl, childAt, viewGroup);
                        if (menuItemImpl != itemData) {
                            itemView.setPressed(false);
                            itemView.jumpDrawablesToCurrentState();
                        }
                        if (itemView != childAt) {
                            ViewGroup viewGroup2 = (ViewGroup) itemView.getParent();
                            if (viewGroup2 != null) {
                                viewGroup2.removeView(itemView);
                            }
                            ((ViewGroup) this.mMenuView).addView(itemView, i);
                        }
                        i++;
                    }
                }
            } else {
                i = 0;
            }
            while (i < viewGroup.getChildCount()) {
                if (viewGroup.getChildAt(i) == this.mOverflowButton) {
                    z2 = false;
                } else {
                    viewGroup.removeViewAt(i);
                    z2 = true;
                }
                if (!z2) {
                    i++;
                }
            }
        }
        ((View) this.mMenuView).requestLayout();
        MenuBuilder menuBuilder2 = this.mMenu;
        if (menuBuilder2 != null) {
            menuBuilder2.flagActionItems();
            ArrayList<MenuItemImpl> arrayList2 = menuBuilder2.mActionItems;
            int size2 = arrayList2.size();
            for (int i3 = 0; i3 < size2; i3++) {
                ActionProvider actionProvider = arrayList2.get(i3).mActionProvider;
            }
        }
        MenuBuilder menuBuilder3 = this.mMenu;
        if (menuBuilder3 != null) {
            menuBuilder3.flagActionItems();
            arrayList = menuBuilder3.mNonActionItems;
        }
        if (this.mReserveOverflow && arrayList != null) {
            int size3 = arrayList.size();
            if (size3 == 1) {
                z3 = !arrayList.get(0).mIsActionViewExpanded;
            } else if (size3 > 0) {
                z3 = true;
            }
        }
        if (z3) {
            if (this.mOverflowButton == null) {
                this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
            }
            ViewGroup viewGroup3 = (ViewGroup) this.mOverflowButton.getParent();
            if (viewGroup3 != this.mMenuView) {
                if (viewGroup3 != null) {
                    viewGroup3.removeView(this.mOverflowButton);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.mMenuView;
                OverflowMenuButton overflowMenuButton = this.mOverflowButton;
                ActionMenuView.LayoutParams generateDefaultLayoutParams = actionMenuView.generateDefaultLayoutParams();
                generateDefaultLayoutParams.isOverflowButton = true;
                actionMenuView.addView(overflowMenuButton, generateDefaultLayoutParams);
            }
        } else {
            OverflowMenuButton overflowMenuButton2 = this.mOverflowButton;
            if (overflowMenuButton2 != null) {
                ViewParent parent = overflowMenuButton2.getParent();
                MenuView menuView = this.mMenuView;
                if (parent == menuView) {
                    ((ViewGroup) menuView).removeView(this.mOverflowButton);
                }
            }
        }
        ((ActionMenuView) this.mMenuView).setOverflowReserved(this.mReserveOverflow);
    }
}
