package androidx.appcompat.widget;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import com.tidalab.v2board.clash.foss.R;
import java.util.Objects;
/* loaded from: classes.dex */
public class PopupMenu {
    public final View mAnchor;
    public final Context mContext;
    public final MenuBuilder mMenu;
    public OnMenuItemClickListener mMenuItemClickListener;
    public final MenuPopupHelper mPopup;

    /* loaded from: classes.dex */
    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public PopupMenu(Context context, View view) {
        this.mContext = context;
        this.mAnchor = view;
        MenuBuilder menuBuilder = new MenuBuilder(context);
        this.mMenu = menuBuilder;
        menuBuilder.mCallback = new MenuBuilder.Callback() { // from class: androidx.appcompat.widget.PopupMenu.1
            @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
            public boolean onMenuItemSelected(MenuBuilder menuBuilder2, MenuItem menuItem) {
                OnMenuItemClickListener onMenuItemClickListener = PopupMenu.this.mMenuItemClickListener;
                if (onMenuItemClickListener != null) {
                    return onMenuItemClickListener.onMenuItemClick(menuItem);
                }
                return false;
            }

            @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
            public void onMenuModeChange(MenuBuilder menuBuilder2) {
            }
        };
        MenuPopupHelper menuPopupHelper = new MenuPopupHelper(context, menuBuilder, view, false, R.attr.popupMenuStyle, 0);
        this.mPopup = menuPopupHelper;
        menuPopupHelper.mDropDownGravity = 0;
        menuPopupHelper.mOnDismissListener = new PopupWindow.OnDismissListener() { // from class: androidx.appcompat.widget.PopupMenu.2
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                Objects.requireNonNull(PopupMenu.this);
            }
        };
    }
}
