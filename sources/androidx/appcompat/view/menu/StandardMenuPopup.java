package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.MenuPopupWindow;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public final class StandardMenuPopup extends MenuPopup implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, MenuPresenter, View.OnKeyListener {
    public final MenuAdapter mAdapter;
    public View mAnchorView;
    public int mContentWidth;
    public final Context mContext;
    public boolean mHasContentWidth;
    public final MenuBuilder mMenu;
    public PopupWindow.OnDismissListener mOnDismissListener;
    public final boolean mOverflowOnly;
    public final MenuPopupWindow mPopup;
    public final int mPopupMaxWidth;
    public final int mPopupStyleAttr;
    public final int mPopupStyleRes;
    public MenuPresenter.Callback mPresenterCallback;
    public boolean mShowTitle;
    public View mShownAnchorView;
    public ViewTreeObserver mTreeObserver;
    public boolean mWasDismissed;
    public final ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: androidx.appcompat.view.menu.StandardMenuPopup.1
        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (StandardMenuPopup.this.isShowing()) {
                StandardMenuPopup standardMenuPopup = StandardMenuPopup.this;
                if (!standardMenuPopup.mPopup.mModal) {
                    View view = standardMenuPopup.mShownAnchorView;
                    if (view == null || !view.isShown()) {
                        StandardMenuPopup.this.dismiss();
                    } else {
                        StandardMenuPopup.this.mPopup.show();
                    }
                }
            }
        }
    };
    public final View.OnAttachStateChangeListener mAttachStateChangeListener = new View.OnAttachStateChangeListener() { // from class: androidx.appcompat.view.menu.StandardMenuPopup.2
        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = StandardMenuPopup.this.mTreeObserver;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    StandardMenuPopup.this.mTreeObserver = view.getViewTreeObserver();
                }
                StandardMenuPopup standardMenuPopup = StandardMenuPopup.this;
                standardMenuPopup.mTreeObserver.removeGlobalOnLayoutListener(standardMenuPopup.mGlobalLayoutListener);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    };
    public int mDropDownGravity = 0;

    public StandardMenuPopup(Context context, MenuBuilder menuBuilder, View view, int i, int i2, boolean z) {
        this.mContext = context;
        this.mMenu = menuBuilder;
        this.mOverflowOnly = z;
        this.mAdapter = new MenuAdapter(menuBuilder, LayoutInflater.from(context), z, R.layout.abc_popup_menu_item_layout);
        this.mPopupStyleAttr = i;
        this.mPopupStyleRes = i2;
        Resources resources = context.getResources();
        this.mPopupMaxWidth = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.mAnchorView = view;
        this.mPopup = new MenuPopupWindow(context, null, i, i2);
        menuBuilder.addMenuPresenter(this, context);
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void addMenu(MenuBuilder menuBuilder) {
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void dismiss() {
        if (isShowing()) {
            this.mPopup.dismiss();
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public ListView getListView() {
        return this.mPopup.mDropDownList;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public boolean isShowing() {
        return !this.mWasDismissed && this.mPopup.isShowing();
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        if (menuBuilder == this.mMenu) {
            dismiss();
            MenuPresenter.Callback callback = this.mPresenterCallback;
            if (callback != null) {
                callback.onCloseMenu(menuBuilder, z);
            }
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.mWasDismissed = true;
        this.mMenu.close(true);
        ViewTreeObserver viewTreeObserver = this.mTreeObserver;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.mTreeObserver = this.mShownAnchorView.getViewTreeObserver();
            }
            this.mTreeObserver.removeGlobalOnLayoutListener(this.mGlobalLayoutListener);
            this.mTreeObserver = null;
        }
        this.mShownAnchorView.removeOnAttachStateChangeListener(this.mAttachStateChangeListener);
        PopupWindow.OnDismissListener onDismissListener = this.mOnDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0070  */
    @Override // androidx.appcompat.view.menu.MenuPresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onSubMenuSelected(androidx.appcompat.view.menu.SubMenuBuilder r10) {
        /*
            r9 = this;
            boolean r0 = r10.hasVisibleItems()
            r1 = 0
            if (r0 == 0) goto L_0x0078
            androidx.appcompat.view.menu.MenuPopupHelper r0 = new androidx.appcompat.view.menu.MenuPopupHelper
            android.content.Context r3 = r9.mContext
            android.view.View r5 = r9.mShownAnchorView
            boolean r6 = r9.mOverflowOnly
            int r7 = r9.mPopupStyleAttr
            int r8 = r9.mPopupStyleRes
            r2 = r0
            r4 = r10
            r2.<init>(r3, r4, r5, r6, r7, r8)
            androidx.appcompat.view.menu.MenuPresenter$Callback r2 = r9.mPresenterCallback
            r0.setPresenterCallback(r2)
            boolean r2 = androidx.appcompat.view.menu.MenuPopup.shouldPreserveIconSpacing(r10)
            r0.mForceShowIcon = r2
            androidx.appcompat.view.menu.MenuPopup r3 = r0.mPopup
            if (r3 == 0) goto L_0x002a
            r3.setForceShowIcon(r2)
        L_0x002a:
            android.widget.PopupWindow$OnDismissListener r2 = r9.mOnDismissListener
            r0.mOnDismissListener = r2
            r2 = 0
            r9.mOnDismissListener = r2
            androidx.appcompat.view.menu.MenuBuilder r2 = r9.mMenu
            r2.close(r1)
            androidx.appcompat.widget.MenuPopupWindow r2 = r9.mPopup
            int r3 = r2.mDropDownHorizontalOffset
            boolean r4 = r2.mDropDownVerticalOffsetSet
            if (r4 != 0) goto L_0x0040
            r2 = 0
            goto L_0x0042
        L_0x0040:
            int r2 = r2.mDropDownVerticalOffset
        L_0x0042:
            int r4 = r9.mDropDownGravity
            android.view.View r5 = r9.mAnchorView
            java.util.concurrent.atomic.AtomicInteger r6 = androidx.core.view.ViewCompat.sNextGeneratedId
            int r5 = r5.getLayoutDirection()
            int r4 = android.view.Gravity.getAbsoluteGravity(r4, r5)
            r4 = r4 & 7
            r5 = 5
            if (r4 != r5) goto L_0x005c
            android.view.View r4 = r9.mAnchorView
            int r4 = r4.getWidth()
            int r3 = r3 + r4
        L_0x005c:
            boolean r4 = r0.isShowing()
            r5 = 1
            if (r4 == 0) goto L_0x0064
            goto L_0x006d
        L_0x0064:
            android.view.View r4 = r0.mAnchorView
            if (r4 != 0) goto L_0x006a
            r0 = 0
            goto L_0x006e
        L_0x006a:
            r0.showPopup(r3, r2, r5, r5)
        L_0x006d:
            r0 = 1
        L_0x006e:
            if (r0 == 0) goto L_0x0078
            androidx.appcompat.view.menu.MenuPresenter$Callback r0 = r9.mPresenterCallback
            if (r0 == 0) goto L_0x0077
            r0.onOpenSubMenu(r10)
        L_0x0077:
            return r5
        L_0x0078:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.StandardMenuPopup.onSubMenuSelected(androidx.appcompat.view.menu.SubMenuBuilder):boolean");
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void setAnchorView(View view) {
        this.mAnchorView = view;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback callback) {
        this.mPresenterCallback = callback;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void setForceShowIcon(boolean z) {
        this.mAdapter.mForceShowIcon = z;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void setGravity(int i) {
        this.mDropDownGravity = i;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void setHorizontalOffset(int i) {
        this.mPopup.mDropDownHorizontalOffset = i;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void setShowTitle(boolean z) {
        this.mShowTitle = z;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void setVerticalOffset(int i) {
        MenuPopupWindow menuPopupWindow = this.mPopup;
        menuPopupWindow.mDropDownVerticalOffset = i;
        menuPopupWindow.mDropDownVerticalOffsetSet = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00c9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ca  */
    @Override // androidx.appcompat.view.menu.ShowableListMenu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void show() {
        /*
            r7 = this;
            boolean r0 = r7.isShowing()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x000b
        L_0x0008:
            r1 = 1
            goto L_0x00c7
        L_0x000b:
            boolean r0 = r7.mWasDismissed
            if (r0 != 0) goto L_0x00c7
            android.view.View r0 = r7.mAnchorView
            if (r0 != 0) goto L_0x0015
            goto L_0x00c7
        L_0x0015:
            r7.mShownAnchorView = r0
            androidx.appcompat.widget.MenuPopupWindow r0 = r7.mPopup
            android.widget.PopupWindow r0 = r0.mPopup
            r0.setOnDismissListener(r7)
            androidx.appcompat.widget.MenuPopupWindow r0 = r7.mPopup
            r0.mItemClickListener = r7
            r0.setModal(r2)
            android.view.View r0 = r7.mShownAnchorView
            android.view.ViewTreeObserver r3 = r7.mTreeObserver
            if (r3 != 0) goto L_0x002d
            r3 = 1
            goto L_0x002e
        L_0x002d:
            r3 = 0
        L_0x002e:
            android.view.ViewTreeObserver r4 = r0.getViewTreeObserver()
            r7.mTreeObserver = r4
            if (r3 == 0) goto L_0x003b
            android.view.ViewTreeObserver$OnGlobalLayoutListener r3 = r7.mGlobalLayoutListener
            r4.addOnGlobalLayoutListener(r3)
        L_0x003b:
            android.view.View$OnAttachStateChangeListener r3 = r7.mAttachStateChangeListener
            r0.addOnAttachStateChangeListener(r3)
            androidx.appcompat.widget.MenuPopupWindow r3 = r7.mPopup
            r3.mDropDownAnchorView = r0
            int r0 = r7.mDropDownGravity
            r3.mDropDownGravity = r0
            boolean r0 = r7.mHasContentWidth
            r3 = 0
            if (r0 != 0) goto L_0x005b
            androidx.appcompat.view.menu.MenuAdapter r0 = r7.mAdapter
            android.content.Context r4 = r7.mContext
            int r5 = r7.mPopupMaxWidth
            int r0 = androidx.appcompat.view.menu.MenuPopup.measureIndividualMenuWidth(r0, r3, r4, r5)
            r7.mContentWidth = r0
            r7.mHasContentWidth = r2
        L_0x005b:
            androidx.appcompat.widget.MenuPopupWindow r0 = r7.mPopup
            int r4 = r7.mContentWidth
            r0.setContentWidth(r4)
            androidx.appcompat.widget.MenuPopupWindow r0 = r7.mPopup
            r4 = 2
            android.widget.PopupWindow r0 = r0.mPopup
            r0.setInputMethodMode(r4)
            androidx.appcompat.widget.MenuPopupWindow r0 = r7.mPopup
            android.graphics.Rect r4 = r7.mEpicenterBounds
            java.util.Objects.requireNonNull(r0)
            if (r4 == 0) goto L_0x0079
            android.graphics.Rect r5 = new android.graphics.Rect
            r5.<init>(r4)
            goto L_0x007a
        L_0x0079:
            r5 = r3
        L_0x007a:
            r0.mEpicenterBounds = r5
            androidx.appcompat.widget.MenuPopupWindow r0 = r7.mPopup
            r0.show()
            androidx.appcompat.widget.MenuPopupWindow r0 = r7.mPopup
            androidx.appcompat.widget.DropDownListView r0 = r0.mDropDownList
            r0.setOnKeyListener(r7)
            boolean r4 = r7.mShowTitle
            if (r4 == 0) goto L_0x00b9
            androidx.appcompat.view.menu.MenuBuilder r4 = r7.mMenu
            java.lang.CharSequence r4 = r4.mHeaderTitle
            if (r4 == 0) goto L_0x00b9
            android.content.Context r4 = r7.mContext
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r4)
            r5 = 2131492882(0x7f0c0012, float:1.8609228E38)
            android.view.View r4 = r4.inflate(r5, r0, r1)
            android.widget.FrameLayout r4 = (android.widget.FrameLayout) r4
            r5 = 16908310(0x1020016, float:2.387729E-38)
            android.view.View r5 = r4.findViewById(r5)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x00b3
            androidx.appcompat.view.menu.MenuBuilder r6 = r7.mMenu
            java.lang.CharSequence r6 = r6.mHeaderTitle
            r5.setText(r6)
        L_0x00b3:
            r4.setEnabled(r1)
            r0.addHeaderView(r4, r3, r1)
        L_0x00b9:
            androidx.appcompat.widget.MenuPopupWindow r0 = r7.mPopup
            androidx.appcompat.view.menu.MenuAdapter r1 = r7.mAdapter
            r0.setAdapter(r1)
            androidx.appcompat.widget.MenuPopupWindow r0 = r7.mPopup
            r0.show()
            goto L_0x0008
        L_0x00c7:
            if (r1 == 0) goto L_0x00ca
            return
        L_0x00ca:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "StandardMenuPopup cannot be used without an anchor"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.StandardMenuPopup.show():void");
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        this.mHasContentWidth = false;
        MenuAdapter menuAdapter = this.mAdapter;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }
}
