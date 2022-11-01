package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import androidx.appcompat.app.ToolbarActionBar;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.ActionMenuPresenter;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
/* loaded from: classes.dex */
public class ActionMenuView extends LinearLayoutCompat implements MenuBuilder.ItemInvoker, MenuView {
    public MenuPresenter.Callback mActionMenuPresenterCallback;
    public boolean mFormatItems;
    public int mFormatItemsWidth;
    public int mGeneratedItemPadding;
    public MenuBuilder mMenu;
    public MenuBuilder.Callback mMenuBuilderCallback;
    public int mMinCellSize;
    public OnMenuItemClickListener mOnMenuItemClickListener;
    public Context mPopupContext;
    public int mPopupTheme = 0;
    public ActionMenuPresenter mPresenter;
    public boolean mReserveOverflow;

    /* loaded from: classes.dex */
    public interface ActionMenuChildView {
        boolean needsDividerAfter();

        boolean needsDividerBefore();
    }

    /* loaded from: classes.dex */
    public static class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static class LayoutParams extends LinearLayoutCompat.LayoutParams {
        @ViewDebug.ExportedProperty
        public int cellsUsed;
        @ViewDebug.ExportedProperty
        public boolean expandable;
        public boolean expanded;
        @ViewDebug.ExportedProperty
        public int extraPixels;
        @ViewDebug.ExportedProperty
        public boolean isOverflowButton;
        @ViewDebug.ExportedProperty
        public boolean preventEdgeOffset;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.isOverflowButton = layoutParams.isOverflowButton;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.isOverflowButton = false;
        }
    }

    /* loaded from: classes.dex */
    public class MenuBuilderCallback implements MenuBuilder.Callback {
        public MenuBuilderCallback() {
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            OnMenuItemClickListener onMenuItemClickListener = ActionMenuView.this.mOnMenuItemClickListener;
            if (onMenuItemClickListener == null) {
                return false;
            }
            Toolbar.OnMenuItemClickListener onMenuItemClickListener2 = Toolbar.this.mOnMenuItemClickListener;
            return onMenuItemClickListener2 != null ? ToolbarActionBar.this.mWindowCallback.onMenuItemSelected(0, menuItem) : false;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public void onMenuModeChange(MenuBuilder menuBuilder) {
            MenuBuilder.Callback callback = ActionMenuView.this.mMenuBuilderCallback;
            if (callback != null) {
                callback.onMenuModeChange(menuBuilder);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface OnMenuItemClickListener {
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f = context.getResources().getDisplayMetrics().density;
        this.mMinCellSize = (int) (56.0f * f);
        this.mGeneratedItemPadding = (int) (f * 4.0f);
        this.mPopupContext = context;
    }

    public static int measureChildForCells(View view, int i, int i2, int i3, int i4) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i3) - i4, View.MeasureSpec.getMode(i3));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        boolean z = false;
        boolean z2 = actionMenuItemView != null && actionMenuItemView.hasText();
        int i5 = 2;
        if (i2 <= 0 || (z2 && i2 < 2)) {
            i5 = 0;
        } else {
            view.measure(View.MeasureSpec.makeMeasureSpec(i2 * i, Integer.MIN_VALUE), makeMeasureSpec);
            int measuredWidth = view.getMeasuredWidth();
            int i6 = measuredWidth / i;
            if (measuredWidth % i != 0) {
                i6++;
            }
            if (!z2 || i6 >= 2) {
                i5 = i6;
            }
        }
        if (!layoutParams.isOverflowButton && z2) {
            z = true;
        }
        layoutParams.expandable = z;
        layoutParams.cellsUsed = i5;
        view.measure(View.MeasureSpec.makeMeasureSpec(i * i5, 1073741824), makeMeasureSpec);
        return i5;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public Menu getMenu() {
        if (this.mMenu == null) {
            Context context = getContext();
            MenuBuilder menuBuilder = new MenuBuilder(context);
            this.mMenu = menuBuilder;
            menuBuilder.mCallback = new MenuBuilderCallback();
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(context);
            this.mPresenter = actionMenuPresenter;
            actionMenuPresenter.mReserveOverflow = true;
            actionMenuPresenter.mReserveOverflowSet = true;
            MenuPresenter.Callback callback = this.mActionMenuPresenterCallback;
            if (callback == null) {
                callback = new ActionMenuPresenterCallback();
            }
            actionMenuPresenter.mCallback = callback;
            this.mMenu.addMenuPresenter(actionMenuPresenter, this.mPopupContext);
            ActionMenuPresenter actionMenuPresenter2 = this.mPresenter;
            actionMenuPresenter2.mMenuView = this;
            this.mMenu = actionMenuPresenter2.mMenu;
        }
        return this.mMenu;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        ActionMenuPresenter.OverflowMenuButton overflowMenuButton = actionMenuPresenter.mOverflowButton;
        if (overflowMenuButton != null) {
            return overflowMenuButton.getDrawable();
        }
        if (actionMenuPresenter.mPendingOverflowIconSet) {
            return actionMenuPresenter.mPendingOverflowIcon;
        }
        return null;
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    public int getWindowAnimations() {
        return 0;
    }

    public boolean hasSupportDividerBeforeChildAt(int i) {
        boolean z = false;
        if (i == 0) {
            return false;
        }
        View childAt = getChildAt(i - 1);
        View childAt2 = getChildAt(i);
        if (i < getChildCount() && (childAt instanceof ActionMenuChildView)) {
            z = false | ((ActionMenuChildView) childAt).needsDividerAfter();
        }
        return (i <= 0 || !(childAt2 instanceof ActionMenuChildView)) ? z : z | ((ActionMenuChildView) childAt2).needsDividerBefore();
    }

    @Override // androidx.appcompat.view.menu.MenuView
    public void initialize(MenuBuilder menuBuilder) {
        this.mMenu = menuBuilder;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.ItemInvoker
    public boolean invokeItem(MenuItemImpl menuItemImpl) {
        return this.mMenu.performItemAction(menuItemImpl, null, 0);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.updateMenuView(false);
            if (this.mPresenter.isOverflowMenuShowing()) {
                this.mPresenter.hideOverflowMenu();
                this.mPresenter.showOverflowMenu();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.dismissPopupMenus();
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        if (!this.mFormatItems) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        int childCount = getChildCount();
        int i7 = (i4 - i2) / 2;
        int dividerWidth = getDividerWidth();
        int i8 = i3 - i;
        int paddingRight = (i8 - getPaddingRight()) - getPaddingLeft();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int i9 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.isOverflowButton) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (hasSupportDividerBeforeChildAt(i11)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (isLayoutRtl) {
                        i5 = getPaddingLeft() + ((LinearLayout.LayoutParams) layoutParams).leftMargin;
                        i6 = i5 + measuredWidth;
                    } else {
                        i6 = (getWidth() - getPaddingRight()) - ((LinearLayout.LayoutParams) layoutParams).rightMargin;
                        i5 = i6 - measuredWidth;
                    }
                    int i12 = i7 - (measuredHeight / 2);
                    childAt.layout(i5, i12, i6, measuredHeight + i12);
                    paddingRight -= measuredWidth;
                    i9 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((LinearLayout.LayoutParams) layoutParams).leftMargin) + ((LinearLayout.LayoutParams) layoutParams).rightMargin;
                    hasSupportDividerBeforeChildAt(i11);
                    i10++;
                }
            }
        }
        if (childCount == 1 && i9 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i13 = (i8 / 2) - (measuredWidth2 / 2);
            int i14 = i7 - (measuredHeight2 / 2);
            childAt2.layout(i13, i14, measuredWidth2 + i13, measuredHeight2 + i14);
            return;
        }
        int i15 = i10 - (i9 ^ 1);
        int max = Math.max(0, i15 > 0 ? paddingRight / i15 : 0);
        if (isLayoutRtl) {
            int width = getWidth() - getPaddingRight();
            for (int i16 = 0; i16 < childCount; i16++) {
                View childAt3 = getChildAt(i16);
                LayoutParams layoutParams2 = (LayoutParams) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !layoutParams2.isOverflowButton) {
                    int i17 = width - ((LinearLayout.LayoutParams) layoutParams2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i18 = i7 - (measuredHeight3 / 2);
                    childAt3.layout(i17 - measuredWidth3, i18, i17, measuredHeight3 + i18);
                    width = i17 - ((measuredWidth3 + ((LinearLayout.LayoutParams) layoutParams2).leftMargin) + max);
                }
            }
            return;
        }
        int paddingLeft = getPaddingLeft();
        for (int i19 = 0; i19 < childCount; i19++) {
            View childAt4 = getChildAt(i19);
            LayoutParams layoutParams3 = (LayoutParams) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !layoutParams3.isOverflowButton) {
                int i20 = paddingLeft + ((LinearLayout.LayoutParams) layoutParams3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i21 = i7 - (measuredHeight4 / 2);
                childAt4.layout(i20, i21, i20 + measuredWidth4, measuredHeight4 + i21);
                paddingLeft = measuredWidth4 + ((LinearLayout.LayoutParams) layoutParams3).rightMargin + max + i20;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r3v32, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r3v38 */
    /* JADX WARN: Type inference failed for: r3v39 */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r30, int r31) {
        /*
            Method dump skipped, instructions count: 685
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionMenuView.onMeasure(int, int):void");
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.mPresenter.mExpandedActionViewsExclusive = z;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.mOnMenuItemClickListener = onMenuItemClickListener;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        ActionMenuPresenter.OverflowMenuButton overflowMenuButton = actionMenuPresenter.mOverflowButton;
        if (overflowMenuButton != null) {
            overflowMenuButton.setImageDrawable(drawable);
            return;
        }
        actionMenuPresenter.mPendingOverflowIconSet = true;
        actionMenuPresenter.mPendingOverflowIcon = drawable;
    }

    public void setOverflowReserved(boolean z) {
        this.mReserveOverflow = z;
    }

    public void setPopupTheme(int i) {
        if (this.mPopupTheme != i) {
            this.mPopupTheme = i;
            if (i == 0) {
                this.mPopupContext = getContext();
            } else {
                this.mPopupContext = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.mPresenter = actionMenuPresenter;
        actionMenuPresenter.mMenuView = this;
        this.mMenu = actionMenuPresenter.mMenu;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        ((LinearLayout.LayoutParams) layoutParams).gravity = 16;
        return layoutParams;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    /* renamed from: generateLayoutParams */
    public ViewGroup.LayoutParams mo5generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    /* renamed from: generateLayoutParams  reason: collision with other method in class */
    public LinearLayoutCompat.LayoutParams mo5generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutParams layoutParams2;
        if (layoutParams == null) {
            return generateDefaultLayoutParams();
        }
        if (layoutParams instanceof LayoutParams) {
            layoutParams2 = new LayoutParams((LayoutParams) layoutParams);
        } else {
            layoutParams2 = new LayoutParams(layoutParams);
        }
        if (((LinearLayout.LayoutParams) layoutParams2).gravity <= 0) {
            ((LinearLayout.LayoutParams) layoutParams2).gravity = 16;
        }
        return layoutParams2;
    }
}
