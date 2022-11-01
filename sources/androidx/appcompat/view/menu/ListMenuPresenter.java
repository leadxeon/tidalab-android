package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.app.AlertController;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import com.tidalab.v2board.clash.foss.R;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public class ListMenuPresenter implements MenuPresenter, AdapterView.OnItemClickListener {
    public MenuAdapter mAdapter;
    public MenuPresenter.Callback mCallback;
    public Context mContext;
    public LayoutInflater mInflater;
    public MenuBuilder mMenu;
    public ExpandedMenuView mMenuView;

    /* loaded from: classes.dex */
    public class MenuAdapter extends BaseAdapter {
        public int mExpandedIndex = -1;

        public MenuAdapter() {
            findExpandedIndex();
        }

        public void findExpandedIndex() {
            MenuBuilder menuBuilder = ListMenuPresenter.this.mMenu;
            MenuItemImpl menuItemImpl = menuBuilder.mExpandedItem;
            if (menuItemImpl != null) {
                menuBuilder.flagActionItems();
                ArrayList<MenuItemImpl> arrayList = menuBuilder.mNonActionItems;
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    if (arrayList.get(i) == menuItemImpl) {
                        this.mExpandedIndex = i;
                        return;
                    }
                }
            }
            this.mExpandedIndex = -1;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            MenuBuilder menuBuilder = ListMenuPresenter.this.mMenu;
            menuBuilder.flagActionItems();
            int size = menuBuilder.mNonActionItems.size();
            Objects.requireNonNull(ListMenuPresenter.this);
            int i = size + 0;
            return this.mExpandedIndex < 0 ? i : i - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = ListMenuPresenter.this.mInflater.inflate(R.layout.abc_list_menu_item_layout, viewGroup, false);
            }
            ((MenuView.ItemView) view).initialize(getItem(i), 0);
            return view;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            findExpandedIndex();
            super.notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public MenuItemImpl getItem(int i) {
            MenuBuilder menuBuilder = ListMenuPresenter.this.mMenu;
            menuBuilder.flagActionItems();
            ArrayList<MenuItemImpl> arrayList = menuBuilder.mNonActionItems;
            Objects.requireNonNull(ListMenuPresenter.this);
            int i2 = i + 0;
            int i3 = this.mExpandedIndex;
            if (i3 >= 0 && i2 >= i3) {
                i2++;
            }
            return arrayList.get(i2);
        }
    }

    public ListMenuPresenter(Context context, int i) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    public ListAdapter getAdapter() {
        if (this.mAdapter == null) {
            this.mAdapter = new MenuAdapter();
        }
        return this.mAdapter;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void initForMenu(Context context, MenuBuilder menuBuilder) {
        if (this.mContext != null) {
            this.mContext = context;
            if (this.mInflater == null) {
                this.mInflater = LayoutInflater.from(context);
            }
        }
        this.mMenu = menuBuilder;
        MenuAdapter menuAdapter = this.mAdapter;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        MenuPresenter.Callback callback = this.mCallback;
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, z);
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.mMenu.performItemAction(this.mAdapter.getItem(i), this, 0);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        MenuDialogHelper menuDialogHelper = new MenuDialogHelper(subMenuBuilder);
        AlertDialog.Builder builder = new AlertDialog.Builder(subMenuBuilder.mContext);
        ListMenuPresenter listMenuPresenter = new ListMenuPresenter(builder.P.mContext, R.layout.abc_list_menu_item_layout);
        menuDialogHelper.mPresenter = listMenuPresenter;
        listMenuPresenter.mCallback = menuDialogHelper;
        MenuBuilder menuBuilder = menuDialogHelper.mMenu;
        menuBuilder.addMenuPresenter(listMenuPresenter, menuBuilder.mContext);
        ListAdapter adapter = menuDialogHelper.mPresenter.getAdapter();
        AlertController.AlertParams alertParams = builder.P;
        alertParams.mAdapter = adapter;
        alertParams.mOnClickListener = menuDialogHelper;
        View view = subMenuBuilder.mHeaderView;
        if (view != null) {
            alertParams.mCustomTitleView = view;
        } else {
            alertParams.mIcon = subMenuBuilder.mHeaderIcon;
            alertParams.mTitle = subMenuBuilder.mHeaderTitle;
        }
        alertParams.mOnKeyListener = menuDialogHelper;
        AlertDialog create = builder.create();
        menuDialogHelper.mDialog = create;
        create.setOnDismissListener(menuDialogHelper);
        WindowManager.LayoutParams attributes = menuDialogHelper.mDialog.getWindow().getAttributes();
        attributes.type = 1003;
        attributes.flags |= 131072;
        menuDialogHelper.mDialog.show();
        MenuPresenter.Callback callback = this.mCallback;
        if (callback == null) {
            return true;
        }
        callback.onOpenSubMenu(subMenuBuilder);
        return true;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback callback) {
        this.mCallback = callback;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        MenuAdapter menuAdapter = this.mAdapter;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }
}
