package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.view.ViewCompat;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class ListPopupWindow implements ShowableListMenu {
    public static Method sGetMaxAvailableHeightMethod;
    public static Method sSetClipToWindowEnabledMethod;
    public static Method sSetEpicenterBoundsMethod;
    public ListAdapter mAdapter;
    public Context mContext;
    public View mDropDownAnchorView;
    public int mDropDownHorizontalOffset;
    public DropDownListView mDropDownList;
    public int mDropDownVerticalOffset;
    public boolean mDropDownVerticalOffsetSet;
    public Rect mEpicenterBounds;
    public final Handler mHandler;
    public AdapterView.OnItemClickListener mItemClickListener;
    public boolean mModal;
    public DataSetObserver mObserver;
    public boolean mOverlapAnchor;
    public boolean mOverlapAnchorSet;
    public PopupWindow mPopup;
    public int mDropDownHeight = -2;
    public int mDropDownWidth = -2;
    public int mDropDownWindowLayoutType = RNCWebViewManager.COMMAND_CLEAR_HISTORY;
    public int mDropDownGravity = 0;
    public int mListItemExpandMaximum = Integer.MAX_VALUE;
    public int mPromptPosition = 0;
    public final ResizePopupRunnable mResizePopupRunnable = new ResizePopupRunnable();
    public final PopupTouchInterceptor mTouchInterceptor = new PopupTouchInterceptor();
    public final PopupScrollListener mScrollListener = new PopupScrollListener();
    public final ListSelectorHider mHideSelector = new ListSelectorHider();
    public final Rect mTempRect = new Rect();

    /* loaded from: classes.dex */
    public class ListSelectorHider implements Runnable {
        public ListSelectorHider() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DropDownListView dropDownListView = ListPopupWindow.this.mDropDownList;
            if (dropDownListView != null) {
                dropDownListView.setListSelectionHidden(true);
                dropDownListView.requestLayout();
            }
        }
    }

    /* loaded from: classes.dex */
    public class PopupDataSetObserver extends DataSetObserver {
        public PopupDataSetObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.show();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }

    /* loaded from: classes.dex */
    public class PopupScrollListener implements AbsListView.OnScrollListener {
        public PopupScrollListener() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            boolean z = true;
            if (i == 1) {
                if (ListPopupWindow.this.mPopup.getInputMethodMode() != 2) {
                    z = false;
                }
                if (!z && ListPopupWindow.this.mPopup.getContentView() != null) {
                    ListPopupWindow listPopupWindow = ListPopupWindow.this;
                    listPopupWindow.mHandler.removeCallbacks(listPopupWindow.mResizePopupRunnable);
                    ListPopupWindow.this.mResizePopupRunnable.run();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class PopupTouchInterceptor implements View.OnTouchListener {
        public PopupTouchInterceptor() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = ListPopupWindow.this.mPopup) != null && popupWindow.isShowing() && x >= 0 && x < ListPopupWindow.this.mPopup.getWidth() && y >= 0 && y < ListPopupWindow.this.mPopup.getHeight()) {
                ListPopupWindow listPopupWindow = ListPopupWindow.this;
                listPopupWindow.mHandler.postDelayed(listPopupWindow.mResizePopupRunnable, 250L);
                return false;
            } else if (action != 1) {
                return false;
            } else {
                ListPopupWindow listPopupWindow2 = ListPopupWindow.this;
                listPopupWindow2.mHandler.removeCallbacks(listPopupWindow2.mResizePopupRunnable);
                return false;
            }
        }
    }

    /* loaded from: classes.dex */
    public class ResizePopupRunnable implements Runnable {
        public ResizePopupRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DropDownListView dropDownListView = ListPopupWindow.this.mDropDownList;
            if (dropDownListView != null) {
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                if (dropDownListView.isAttachedToWindow() && ListPopupWindow.this.mDropDownList.getCount() > ListPopupWindow.this.mDropDownList.getChildCount()) {
                    int childCount = ListPopupWindow.this.mDropDownList.getChildCount();
                    ListPopupWindow listPopupWindow = ListPopupWindow.this;
                    if (childCount <= listPopupWindow.mListItemExpandMaximum) {
                        listPopupWindow.mPopup.setInputMethodMode(2);
                        ListPopupWindow.this.show();
                    }
                }
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                sSetClipToWindowEnabledMethod = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                sSetEpicenterBoundsMethod = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
        if (Build.VERSION.SDK_INT <= 23) {
            try {
                sGetMaxAvailableHeightMethod = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
            } catch (NoSuchMethodException unused3) {
                Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
            }
        }
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mContext = context;
        this.mHandler = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ListPopupWindow, i, i2);
        this.mDropDownHorizontalOffset = obtainStyledAttributes.getDimensionPixelOffset(0, 0);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(1, 0);
        this.mDropDownVerticalOffset = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.mDropDownVerticalOffsetSet = true;
        }
        obtainStyledAttributes.recycle();
        AppCompatPopupWindow appCompatPopupWindow = new AppCompatPopupWindow(context, attributeSet, i, i2);
        this.mPopup = appCompatPopupWindow;
        appCompatPopupWindow.setInputMethodMode(1);
    }

    public DropDownListView createDropDownListView(Context context, boolean z) {
        return new DropDownListView(context, z);
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void dismiss() {
        this.mPopup.dismiss();
        this.mPopup.setContentView(null);
        this.mDropDownList = null;
        this.mHandler.removeCallbacks(this.mResizePopupRunnable);
    }

    public Drawable getBackground() {
        return this.mPopup.getBackground();
    }

    public int getHorizontalOffset() {
        return this.mDropDownHorizontalOffset;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public ListView getListView() {
        return this.mDropDownList;
    }

    public int getVerticalOffset() {
        if (!this.mDropDownVerticalOffsetSet) {
            return 0;
        }
        return this.mDropDownVerticalOffset;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public boolean isShowing() {
        return this.mPopup.isShowing();
    }

    public void setAdapter(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.mObserver;
        if (dataSetObserver == null) {
            this.mObserver = new PopupDataSetObserver();
        } else {
            ListAdapter listAdapter2 = this.mAdapter;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.mAdapter = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.mObserver);
        }
        DropDownListView dropDownListView = this.mDropDownList;
        if (dropDownListView != null) {
            dropDownListView.setAdapter(this.mAdapter);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.mPopup.setBackgroundDrawable(drawable);
    }

    public void setContentWidth(int i) {
        Drawable background = this.mPopup.getBackground();
        if (background != null) {
            background.getPadding(this.mTempRect);
            Rect rect = this.mTempRect;
            this.mDropDownWidth = rect.left + rect.right + i;
            return;
        }
        this.mDropDownWidth = i;
    }

    public void setHorizontalOffset(int i) {
        this.mDropDownHorizontalOffset = i;
    }

    public void setModal(boolean z) {
        this.mModal = z;
        this.mPopup.setFocusable(z);
    }

    public void setVerticalOffset(int i) {
        this.mDropDownVerticalOffset = i;
        this.mDropDownVerticalOffsetSet = true;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void show() {
        int i;
        int i2;
        int i3;
        DropDownListView dropDownListView;
        int i4;
        if (this.mDropDownList == null) {
            DropDownListView createDropDownListView = createDropDownListView(this.mContext, !this.mModal);
            this.mDropDownList = createDropDownListView;
            createDropDownListView.setAdapter(this.mAdapter);
            this.mDropDownList.setOnItemClickListener(this.mItemClickListener);
            this.mDropDownList.setFocusable(true);
            this.mDropDownList.setFocusableInTouchMode(true);
            this.mDropDownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: androidx.appcompat.widget.ListPopupWindow.3
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> adapterView, View view, int i5, long j) {
                    DropDownListView dropDownListView2;
                    if (i5 != -1 && (dropDownListView2 = ListPopupWindow.this.mDropDownList) != null) {
                        dropDownListView2.setListSelectionHidden(false);
                    }
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            this.mDropDownList.setOnScrollListener(this.mScrollListener);
            this.mPopup.setContentView(this.mDropDownList);
        } else {
            ViewGroup viewGroup = (ViewGroup) this.mPopup.getContentView();
        }
        Drawable background = this.mPopup.getBackground();
        int i5 = 0;
        if (background != null) {
            background.getPadding(this.mTempRect);
            Rect rect = this.mTempRect;
            int i6 = rect.top;
            i = rect.bottom + i6;
            if (!this.mDropDownVerticalOffsetSet) {
                this.mDropDownVerticalOffset = -i6;
            }
        } else {
            this.mTempRect.setEmpty();
            i = 0;
        }
        boolean z = this.mPopup.getInputMethodMode() == 2;
        View view = this.mDropDownAnchorView;
        int i7 = this.mDropDownVerticalOffset;
        if (Build.VERSION.SDK_INT <= 23) {
            Method method = sGetMaxAvailableHeightMethod;
            if (method != null) {
                try {
                    i2 = ((Integer) method.invoke(this.mPopup, view, Integer.valueOf(i7), Boolean.valueOf(z))).intValue();
                } catch (Exception unused) {
                    Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
                }
            }
            i2 = this.mPopup.getMaxAvailableHeight(view, i7);
        } else {
            i2 = this.mPopup.getMaxAvailableHeight(view, i7, z);
        }
        if (this.mDropDownHeight == -1) {
            i3 = i2 + i;
        } else {
            int i8 = this.mDropDownWidth;
            if (i8 == -2) {
                int i9 = this.mContext.getResources().getDisplayMetrics().widthPixels;
                Rect rect2 = this.mTempRect;
                i4 = View.MeasureSpec.makeMeasureSpec(i9 - (rect2.left + rect2.right), Integer.MIN_VALUE);
            } else if (i8 != -1) {
                i4 = View.MeasureSpec.makeMeasureSpec(i8, 1073741824);
            } else {
                int i10 = this.mContext.getResources().getDisplayMetrics().widthPixels;
                Rect rect3 = this.mTempRect;
                i4 = View.MeasureSpec.makeMeasureSpec(i10 - (rect3.left + rect3.right), 1073741824);
            }
            int measureHeightOfChildrenCompat = this.mDropDownList.measureHeightOfChildrenCompat(i4, i2 - 0, -1);
            i3 = measureHeightOfChildrenCompat + (measureHeightOfChildrenCompat > 0 ? this.mDropDownList.getPaddingBottom() + this.mDropDownList.getPaddingTop() + i + 0 : 0);
        }
        boolean z2 = this.mPopup.getInputMethodMode() == 2;
        AppOpsManagerCompat.setWindowLayoutType(this.mPopup, this.mDropDownWindowLayoutType);
        if (this.mPopup.isShowing()) {
            View view2 = this.mDropDownAnchorView;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            if (view2.isAttachedToWindow()) {
                int i11 = this.mDropDownWidth;
                if (i11 == -1) {
                    i11 = -1;
                } else if (i11 == -2) {
                    i11 = this.mDropDownAnchorView.getWidth();
                }
                int i12 = this.mDropDownHeight;
                if (i12 == -1) {
                    if (!z2) {
                        i3 = -1;
                    }
                    if (z2) {
                        this.mPopup.setWidth(this.mDropDownWidth == -1 ? -1 : 0);
                        this.mPopup.setHeight(0);
                    } else {
                        PopupWindow popupWindow = this.mPopup;
                        if (this.mDropDownWidth == -1) {
                            i5 = -1;
                        }
                        popupWindow.setWidth(i5);
                        this.mPopup.setHeight(-1);
                    }
                } else if (i12 != -2) {
                    i3 = i12;
                }
                this.mPopup.setOutsideTouchable(true);
                this.mPopup.update(this.mDropDownAnchorView, this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, i11 < 0 ? -1 : i11, i3 < 0 ? -1 : i3);
                return;
            }
            return;
        }
        int i13 = this.mDropDownWidth;
        if (i13 == -1) {
            i13 = -1;
        } else if (i13 == -2) {
            i13 = this.mDropDownAnchorView.getWidth();
        }
        int i14 = this.mDropDownHeight;
        if (i14 == -1) {
            i3 = -1;
        } else if (i14 != -2) {
            i3 = i14;
        }
        this.mPopup.setWidth(i13);
        this.mPopup.setHeight(i3);
        if (Build.VERSION.SDK_INT <= 28) {
            Method method2 = sSetClipToWindowEnabledMethod;
            if (method2 != null) {
                try {
                    method2.invoke(this.mPopup, Boolean.TRUE);
                } catch (Exception unused2) {
                    Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
                }
            }
        } else {
            this.mPopup.setIsClippedToScreen(true);
        }
        this.mPopup.setOutsideTouchable(true);
        this.mPopup.setTouchInterceptor(this.mTouchInterceptor);
        if (this.mOverlapAnchorSet) {
            AppOpsManagerCompat.setOverlapAnchor(this.mPopup, this.mOverlapAnchor);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method3 = sSetEpicenterBoundsMethod;
            if (method3 != null) {
                try {
                    method3.invoke(this.mPopup, this.mEpicenterBounds);
                } catch (Exception e) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e);
                }
            }
        } else {
            this.mPopup.setEpicenterBounds(this.mEpicenterBounds);
        }
        this.mPopup.showAsDropDown(this.mDropDownAnchorView, this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownGravity);
        this.mDropDownList.setSelection(-1);
        if ((!this.mModal || this.mDropDownList.isInTouchMode()) && (dropDownListView = this.mDropDownList) != null) {
            dropDownListView.setListSelectionHidden(true);
            dropDownListView.requestLayout();
        }
        if (!this.mModal) {
            this.mHandler.post(this.mHideSelector);
        }
    }
}
