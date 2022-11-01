package androidx.viewpager2.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.R$styleable;
import androidx.viewpager2.adapter.StatefulAdapter;
import androidx.viewpager2.widget.ScrollEventAdapter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public final class ViewPager2 extends ViewGroup {
    public int mCurrentItem;
    public FakeDrag mFakeDragger;
    public LinearLayoutManager mLayoutManager;
    public CompositeOnPageChangeCallback mPageChangeEventDispatcher;
    public PageTransformerAdapter mPageTransformerAdapter;
    public PagerSnapHelper mPagerSnapHelper;
    public Parcelable mPendingAdapterState;
    public RecyclerView mRecyclerView;
    public ScrollEventAdapter mScrollEventAdapter;
    public final Rect mTmpContainerRect = new Rect();
    public final Rect mTmpChildRect = new Rect();
    public CompositeOnPageChangeCallback mExternalPageChangeCallbacks = new CompositeOnPageChangeCallback(3);
    public boolean mCurrentItemDirty = false;
    public RecyclerView.AdapterDataObserver mCurrentItemDataSetChangeObserver = new DataSetChangeObserver() { // from class: androidx.viewpager2.widget.ViewPager2.1
        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            ViewPager2 viewPager2 = ViewPager2.this;
            viewPager2.mCurrentItemDirty = true;
            viewPager2.mScrollEventAdapter.mDataSetChangeHappened = true;
        }
    };
    public int mPendingCurrentItem = -1;
    public RecyclerView.ItemAnimator mSavedItemAnimator = null;
    public boolean mSavedItemAnimatorPresent = false;
    public boolean mUserInputEnabled = true;
    public int mOffscreenPageLimit = -1;
    public AccessibilityProvider mAccessibilityProvider = new PageAwareAccessibilityProvider();

    /* loaded from: classes.dex */
    public abstract class AccessibilityProvider {
        public AccessibilityProvider(ViewPager2 viewPager2, AnonymousClass1 r2) {
        }

        public abstract void onInitialize(CompositeOnPageChangeCallback compositeOnPageChangeCallback, RecyclerView recyclerView);

        public abstract void onSetNewCurrentItem();
    }

    /* loaded from: classes.dex */
    public static abstract class DataSetChangeObserver extends RecyclerView.AdapterDataObserver {
        public DataSetChangeObserver(AnonymousClass1 r1) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeChanged(int i, int i2) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeInserted(int i, int i2) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeMoved(int i, int i2, int i3) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeRemoved(int i, int i2) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeChanged(int i, int i2, Object obj) {
            onChanged();
        }
    }

    /* loaded from: classes.dex */
    public class LinearLayoutManagerImpl extends LinearLayoutManager {
        public LinearLayoutManagerImpl(Context context) {
            super(1, false);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public void calculateExtraLayoutSpace(RecyclerView.State state, int[] iArr) {
            int offscreenPageLimit = ViewPager2.this.getOffscreenPageLimit();
            if (offscreenPageLimit == -1) {
                super.calculateExtraLayoutSpace(state, iArr);
                return;
            }
            int pageSize = ViewPager2.this.getPageSize() * offscreenPageLimit;
            iArr[0] = pageSize;
            iArr[1] = pageSize;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onInitializeAccessibilityNodeInfo(RecyclerView.Recycler recycler, RecyclerView.State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(recycler, state, accessibilityNodeInfoCompat);
            Objects.requireNonNull(ViewPager2.this.mAccessibilityProvider);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean performAccessibilityAction(RecyclerView.Recycler recycler, RecyclerView.State state, int i, Bundle bundle) {
            Objects.requireNonNull(ViewPager2.this.mAccessibilityProvider);
            return super.performAccessibilityAction(recycler, state, i, bundle);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class OnPageChangeCallback {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
        }
    }

    /* loaded from: classes.dex */
    public class PageAwareAccessibilityProvider extends AccessibilityProvider {
        public RecyclerView.AdapterDataObserver mAdapterDataObserver;
        public final AccessibilityViewCommand mActionPageForward = new AccessibilityViewCommand() { // from class: androidx.viewpager2.widget.ViewPager2.PageAwareAccessibilityProvider.1
            @Override // androidx.core.view.accessibility.AccessibilityViewCommand
            public boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
                PageAwareAccessibilityProvider.this.setCurrentItemFromAccessibilityCommand(((ViewPager2) view).getCurrentItem() + 1);
                return true;
            }
        };
        public final AccessibilityViewCommand mActionPageBackward = new AccessibilityViewCommand() { // from class: androidx.viewpager2.widget.ViewPager2.PageAwareAccessibilityProvider.2
            @Override // androidx.core.view.accessibility.AccessibilityViewCommand
            public boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
                PageAwareAccessibilityProvider.this.setCurrentItemFromAccessibilityCommand(((ViewPager2) view).getCurrentItem() - 1);
                return true;
            }
        };

        public PageAwareAccessibilityProvider() {
            super(ViewPager2.this, null);
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public void onInitialize(CompositeOnPageChangeCallback compositeOnPageChangeCallback, RecyclerView recyclerView) {
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            recyclerView.setImportantForAccessibility(2);
            this.mAdapterDataObserver = new DataSetChangeObserver() { // from class: androidx.viewpager2.widget.ViewPager2.PageAwareAccessibilityProvider.3
                @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
                public void onChanged() {
                    PageAwareAccessibilityProvider.this.updatePageAccessibilityActions();
                }
            };
            if (ViewPager2.this.getImportantForAccessibility() == 0) {
                ViewPager2.this.setImportantForAccessibility(1);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.AccessibilityProvider
        public void onSetNewCurrentItem() {
            updatePageAccessibilityActions();
        }

        public void setCurrentItemFromAccessibilityCommand(int i) {
            ViewPager2 viewPager2 = ViewPager2.this;
            if (viewPager2.mUserInputEnabled) {
                viewPager2.setCurrentItemInternal(i, true);
            }
        }

        public void updatePageAccessibilityActions() {
            int itemCount;
            ViewPager2 viewPager2 = ViewPager2.this;
            int i = 16908360;
            ViewCompat.removeAccessibilityAction(viewPager2, 16908360);
            ViewCompat.removeAccessibilityAction(viewPager2, 16908361);
            ViewCompat.removeAccessibilityAction(viewPager2, 16908358);
            ViewCompat.removeAccessibilityAction(viewPager2, 16908359);
            if (ViewPager2.this.getAdapter() != null && (itemCount = ViewPager2.this.getAdapter().getItemCount()) != 0) {
                ViewPager2 viewPager22 = ViewPager2.this;
                if (viewPager22.mUserInputEnabled) {
                    if (viewPager22.getOrientation() == 0) {
                        boolean isRtl = ViewPager2.this.isRtl();
                        int i2 = isRtl ? 16908360 : 16908361;
                        if (isRtl) {
                            i = 16908361;
                        }
                        if (ViewPager2.this.mCurrentItem < itemCount - 1) {
                            ViewCompat.replaceAccessibilityAction(viewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(i2, null), null, this.mActionPageForward);
                        }
                        if (ViewPager2.this.mCurrentItem > 0) {
                            ViewCompat.replaceAccessibilityAction(viewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(i, null), null, this.mActionPageBackward);
                            return;
                        }
                        return;
                    }
                    if (ViewPager2.this.mCurrentItem < itemCount - 1) {
                        ViewCompat.replaceAccessibilityAction(viewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(16908359, null), null, this.mActionPageForward);
                    }
                    if (ViewPager2.this.mCurrentItem > 0) {
                        ViewCompat.replaceAccessibilityAction(viewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(16908358, null), null, this.mActionPageBackward);
                    }
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public interface PageTransformer {
        void transformPage(View view, float f);
    }

    /* loaded from: classes.dex */
    public class PagerSnapHelperImpl extends PagerSnapHelper {
        public PagerSnapHelperImpl() {
        }

        @Override // androidx.recyclerview.widget.PagerSnapHelper, androidx.recyclerview.widget.SnapHelper
        public View findSnapView(RecyclerView.LayoutManager layoutManager) {
            if (ViewPager2.this.mFakeDragger.mScrollEventAdapter.mFakeDragging) {
                return null;
            }
            return super.findSnapView(layoutManager);
        }
    }

    /* loaded from: classes.dex */
    public class RecyclerViewImpl extends RecyclerView {
        public RecyclerViewImpl(Context context) {
            super(context, null);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public CharSequence getAccessibilityClassName() {
            Objects.requireNonNull(ViewPager2.this.mAccessibilityProvider);
            return super.getAccessibilityClassName();
        }

        @Override // android.view.View
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setFromIndex(ViewPager2.this.mCurrentItem);
            accessibilityEvent.setToIndex(ViewPager2.this.mCurrentItem);
            accessibilityEvent.setSource(ViewPager2.this);
            accessibilityEvent.setClassName("androidx.viewpager.widget.ViewPager");
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            return ViewPager2.this.mUserInputEnabled && super.onInterceptTouchEvent(motionEvent);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent motionEvent) {
            return ViewPager2.this.mUserInputEnabled && super.onTouchEvent(motionEvent);
        }
    }

    /* loaded from: classes.dex */
    public static class SmoothScrollToPosition implements Runnable {
        public final int mPosition;
        public final RecyclerView mRecyclerView;

        public SmoothScrollToPosition(int i, RecyclerView recyclerView) {
            this.mPosition = i;
            this.mRecyclerView = recyclerView;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mRecyclerView.smoothScrollToPosition(this.mPosition);
        }
    }

    /* JADX WARN: Finally extract failed */
    public ViewPager2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        RecyclerViewImpl recyclerViewImpl = new RecyclerViewImpl(context);
        this.mRecyclerView = recyclerViewImpl;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        recyclerViewImpl.setId(View.generateViewId());
        this.mRecyclerView.setDescendantFocusability(131072);
        LinearLayoutManagerImpl linearLayoutManagerImpl = new LinearLayoutManagerImpl(context);
        this.mLayoutManager = linearLayoutManagerImpl;
        this.mRecyclerView.setLayoutManager(linearLayoutManagerImpl);
        this.mRecyclerView.setScrollingTouchSlop(1);
        int[] iArr = R$styleable.ViewPager2;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        if (Build.VERSION.SDK_INT >= 29) {
            saveAttributeDataForStyleable(context, iArr, attributeSet, obtainStyledAttributes, 0, 0);
        }
        try {
            setOrientation(obtainStyledAttributes.getInt(0, 0));
            obtainStyledAttributes.recycle();
            this.mRecyclerView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            RecyclerView recyclerView = this.mRecyclerView;
            RecyclerView.OnChildAttachStateChangeListener onChildAttachStateChangeListener = new RecyclerView.OnChildAttachStateChangeListener(this) { // from class: androidx.viewpager2.widget.ViewPager2.4
                @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
                public void onChildViewAttachedToWindow(View view) {
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                    if (((ViewGroup.MarginLayoutParams) layoutParams).width != -1 || ((ViewGroup.MarginLayoutParams) layoutParams).height != -1) {
                        throw new IllegalStateException("Pages must fill the whole ViewPager2 (use match_parent)");
                    }
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
                public void onChildViewDetachedFromWindow(View view) {
                }
            };
            if (recyclerView.mOnChildAttachStateListeners == null) {
                recyclerView.mOnChildAttachStateListeners = new ArrayList();
            }
            recyclerView.mOnChildAttachStateListeners.add(onChildAttachStateChangeListener);
            ScrollEventAdapter scrollEventAdapter = new ScrollEventAdapter(this);
            this.mScrollEventAdapter = scrollEventAdapter;
            this.mFakeDragger = new FakeDrag(this, scrollEventAdapter, this.mRecyclerView);
            PagerSnapHelperImpl pagerSnapHelperImpl = new PagerSnapHelperImpl();
            this.mPagerSnapHelper = pagerSnapHelperImpl;
            pagerSnapHelperImpl.attachToRecyclerView(this.mRecyclerView);
            this.mRecyclerView.addOnScrollListener(this.mScrollEventAdapter);
            CompositeOnPageChangeCallback compositeOnPageChangeCallback = new CompositeOnPageChangeCallback(3);
            this.mPageChangeEventDispatcher = compositeOnPageChangeCallback;
            this.mScrollEventAdapter.mCallback = compositeOnPageChangeCallback;
            OnPageChangeCallback onPageChangeCallback = new OnPageChangeCallback() { // from class: androidx.viewpager2.widget.ViewPager2.2
                @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
                public void onPageScrollStateChanged(int i) {
                    if (i == 0) {
                        ViewPager2.this.updateCurrentItem();
                    }
                }

                @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
                public void onPageSelected(int i) {
                    ViewPager2 viewPager2 = ViewPager2.this;
                    if (viewPager2.mCurrentItem != i) {
                        viewPager2.mCurrentItem = i;
                        viewPager2.mAccessibilityProvider.onSetNewCurrentItem();
                    }
                }
            };
            OnPageChangeCallback onPageChangeCallback2 = new OnPageChangeCallback() { // from class: androidx.viewpager2.widget.ViewPager2.3
                @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
                public void onPageSelected(int i) {
                    ViewPager2.this.clearFocus();
                    if (ViewPager2.this.hasFocus()) {
                        ViewPager2.this.mRecyclerView.requestFocus(2);
                    }
                }
            };
            compositeOnPageChangeCallback.mCallbacks.add(onPageChangeCallback);
            this.mPageChangeEventDispatcher.mCallbacks.add(onPageChangeCallback2);
            this.mAccessibilityProvider.onInitialize(this.mPageChangeEventDispatcher, this.mRecyclerView);
            CompositeOnPageChangeCallback compositeOnPageChangeCallback2 = this.mPageChangeEventDispatcher;
            compositeOnPageChangeCallback2.mCallbacks.add(this.mExternalPageChangeCallbacks);
            PageTransformerAdapter pageTransformerAdapter = new PageTransformerAdapter(this.mLayoutManager);
            this.mPageTransformerAdapter = pageTransformerAdapter;
            this.mPageChangeEventDispatcher.mCallbacks.add(pageTransformerAdapter);
            RecyclerView recyclerView2 = this.mRecyclerView;
            attachViewToParent(recyclerView2, 0, recyclerView2.getLayoutParams());
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i) {
        return this.mRecyclerView.canScrollHorizontally(i);
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i) {
        return this.mRecyclerView.canScrollVertically(i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        Parcelable parcelable = sparseArray.get(getId());
        if (parcelable instanceof SavedState) {
            int i = ((SavedState) parcelable).mRecyclerViewId;
            sparseArray.put(this.mRecyclerView.getId(), sparseArray.get(i));
            sparseArray.remove(i);
        }
        super.dispatchRestoreInstanceState(sparseArray);
        restorePendingState();
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        AccessibilityProvider accessibilityProvider = this.mAccessibilityProvider;
        Objects.requireNonNull(accessibilityProvider);
        if (!(accessibilityProvider instanceof PageAwareAccessibilityProvider)) {
            return super.getAccessibilityClassName();
        }
        Objects.requireNonNull(this.mAccessibilityProvider);
        return "androidx.viewpager.widget.ViewPager";
    }

    public RecyclerView.Adapter getAdapter() {
        return this.mRecyclerView.getAdapter();
    }

    public int getCurrentItem() {
        return this.mCurrentItem;
    }

    public int getItemDecorationCount() {
        return this.mRecyclerView.getItemDecorationCount();
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public int getOrientation() {
        return this.mLayoutManager.mOrientation;
    }

    public int getPageSize() {
        int i;
        int i2;
        RecyclerView recyclerView = this.mRecyclerView;
        if (getOrientation() == 0) {
            i = recyclerView.getWidth() - recyclerView.getPaddingLeft();
            i2 = recyclerView.getPaddingRight();
        } else {
            i = recyclerView.getHeight() - recyclerView.getPaddingTop();
            i2 = recyclerView.getPaddingBottom();
        }
        return i - i2;
    }

    public int getScrollState() {
        return this.mScrollEventAdapter.mScrollState;
    }

    public boolean isRtl() {
        return this.mLayoutManager.getLayoutDirection() == 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onInitializeAccessibilityNodeInfo(android.view.accessibility.AccessibilityNodeInfo r6) {
        /*
            r5 = this;
            super.onInitializeAccessibilityNodeInfo(r6)
            androidx.viewpager2.widget.ViewPager2$AccessibilityProvider r0 = r5.mAccessibilityProvider
            androidx.viewpager2.widget.ViewPager2$PageAwareAccessibilityProvider r0 = (androidx.viewpager2.widget.ViewPager2.PageAwareAccessibilityProvider) r0
            androidx.viewpager2.widget.ViewPager2 r1 = androidx.viewpager2.widget.ViewPager2.this
            androidx.recyclerview.widget.RecyclerView$Adapter r1 = r1.getAdapter()
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0031
            androidx.viewpager2.widget.ViewPager2 r1 = androidx.viewpager2.widget.ViewPager2.this
            int r1 = r1.getOrientation()
            if (r1 != r2) goto L_0x0024
            androidx.viewpager2.widget.ViewPager2 r1 = androidx.viewpager2.widget.ViewPager2.this
            androidx.recyclerview.widget.RecyclerView$Adapter r1 = r1.getAdapter()
            int r1 = r1.getItemCount()
            goto L_0x0032
        L_0x0024:
            androidx.viewpager2.widget.ViewPager2 r1 = androidx.viewpager2.widget.ViewPager2.this
            androidx.recyclerview.widget.RecyclerView$Adapter r1 = r1.getAdapter()
            int r1 = r1.getItemCount()
            r4 = r1
            r1 = 0
            goto L_0x0033
        L_0x0031:
            r1 = 0
        L_0x0032:
            r4 = 0
        L_0x0033:
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$CollectionInfoCompat r1 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(r1, r4, r3, r3)
            java.lang.Object r1 = r1.mInfo
            android.view.accessibility.AccessibilityNodeInfo$CollectionInfo r1 = (android.view.accessibility.AccessibilityNodeInfo.CollectionInfo) r1
            r6.setCollectionInfo(r1)
            androidx.viewpager2.widget.ViewPager2 r1 = androidx.viewpager2.widget.ViewPager2.this
            androidx.recyclerview.widget.RecyclerView$Adapter r1 = r1.getAdapter()
            if (r1 != 0) goto L_0x0047
            goto L_0x006c
        L_0x0047:
            int r1 = r1.getItemCount()
            if (r1 == 0) goto L_0x006c
            androidx.viewpager2.widget.ViewPager2 r3 = androidx.viewpager2.widget.ViewPager2.this
            boolean r4 = r3.mUserInputEnabled
            if (r4 != 0) goto L_0x0054
            goto L_0x006c
        L_0x0054:
            int r3 = r3.mCurrentItem
            if (r3 <= 0) goto L_0x005d
            r3 = 8192(0x2000, float:1.14794E-41)
            r6.addAction(r3)
        L_0x005d:
            androidx.viewpager2.widget.ViewPager2 r0 = androidx.viewpager2.widget.ViewPager2.this
            int r0 = r0.mCurrentItem
            int r1 = r1 - r2
            if (r0 >= r1) goto L_0x0069
            r0 = 4096(0x1000, float:5.74E-42)
            r6.addAction(r0)
        L_0x0069:
            r6.setScrollable(r2)
        L_0x006c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager2.widget.ViewPager2.onInitializeAccessibilityNodeInfo(android.view.accessibility.AccessibilityNodeInfo):void");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = this.mRecyclerView.getMeasuredWidth();
        int measuredHeight = this.mRecyclerView.getMeasuredHeight();
        this.mTmpContainerRect.left = getPaddingLeft();
        this.mTmpContainerRect.right = (i3 - i) - getPaddingRight();
        this.mTmpContainerRect.top = getPaddingTop();
        this.mTmpContainerRect.bottom = (i4 - i2) - getPaddingBottom();
        Gravity.apply(8388659, measuredWidth, measuredHeight, this.mTmpContainerRect, this.mTmpChildRect);
        RecyclerView recyclerView = this.mRecyclerView;
        Rect rect = this.mTmpChildRect;
        recyclerView.layout(rect.left, rect.top, rect.right, rect.bottom);
        if (this.mCurrentItemDirty) {
            updateCurrentItem();
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        measureChild(this.mRecyclerView, i, i2);
        int measuredWidth = this.mRecyclerView.getMeasuredWidth();
        int measuredHeight = this.mRecyclerView.getMeasuredHeight();
        int measuredState = this.mRecyclerView.getMeasuredState();
        int paddingRight = getPaddingRight() + getPaddingLeft() + measuredWidth;
        int paddingTop = getPaddingTop();
        setMeasuredDimension(ViewGroup.resolveSizeAndState(Math.max(paddingRight, getSuggestedMinimumWidth()), i, measuredState), ViewGroup.resolveSizeAndState(Math.max(getPaddingBottom() + paddingTop + measuredHeight, getSuggestedMinimumHeight()), i2, measuredState << 16));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mPendingCurrentItem = savedState.mCurrentItem;
        this.mPendingAdapterState = savedState.mAdapterState;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.mRecyclerViewId = this.mRecyclerView.getId();
        int i = this.mPendingCurrentItem;
        if (i == -1) {
            i = this.mCurrentItem;
        }
        savedState.mCurrentItem = i;
        Parcelable parcelable = this.mPendingAdapterState;
        if (parcelable != null) {
            savedState.mAdapterState = parcelable;
        } else {
            RecyclerView.Adapter adapter = this.mRecyclerView.getAdapter();
            if (adapter instanceof StatefulAdapter) {
                savedState.mAdapterState = ((StatefulAdapter) adapter).saveState();
            }
        }
        return savedState;
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        throw new IllegalStateException(ViewPager2.class.getSimpleName() + " does not support direct child views");
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int i, Bundle bundle) {
        int i2;
        Objects.requireNonNull((PageAwareAccessibilityProvider) this.mAccessibilityProvider);
        boolean z = false;
        if (!(i == 8192 || i == 4096)) {
            return super.performAccessibilityAction(i, bundle);
        }
        PageAwareAccessibilityProvider pageAwareAccessibilityProvider = (PageAwareAccessibilityProvider) this.mAccessibilityProvider;
        Objects.requireNonNull(pageAwareAccessibilityProvider);
        if (i == 8192 || i == 4096) {
            z = true;
        }
        if (z) {
            if (i == 8192) {
                i2 = ViewPager2.this.getCurrentItem() - 1;
            } else {
                i2 = ViewPager2.this.getCurrentItem() + 1;
            }
            pageAwareAccessibilityProvider.setCurrentItemFromAccessibilityCommand(i2);
            return true;
        }
        throw new IllegalStateException();
    }

    public final void restorePendingState() {
        RecyclerView.Adapter adapter;
        if (this.mPendingCurrentItem != -1 && (adapter = getAdapter()) != null) {
            Parcelable parcelable = this.mPendingAdapterState;
            if (parcelable != null) {
                if (adapter instanceof StatefulAdapter) {
                    ((StatefulAdapter) adapter).restoreState(parcelable);
                }
                this.mPendingAdapterState = null;
            }
            int max = Math.max(0, Math.min(this.mPendingCurrentItem, adapter.getItemCount() - 1));
            this.mCurrentItem = max;
            this.mPendingCurrentItem = -1;
            this.mRecyclerView.scrollToPosition(max);
            ((PageAwareAccessibilityProvider) this.mAccessibilityProvider).updatePageAccessibilityActions();
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        RecyclerView.Adapter adapter2 = this.mRecyclerView.getAdapter();
        PageAwareAccessibilityProvider pageAwareAccessibilityProvider = (PageAwareAccessibilityProvider) this.mAccessibilityProvider;
        Objects.requireNonNull(pageAwareAccessibilityProvider);
        if (adapter2 != null) {
            adapter2.mObservable.unregisterObserver(pageAwareAccessibilityProvider.mAdapterDataObserver);
        }
        if (adapter2 != null) {
            adapter2.mObservable.unregisterObserver(this.mCurrentItemDataSetChangeObserver);
        }
        this.mRecyclerView.setAdapter(adapter);
        this.mCurrentItem = 0;
        restorePendingState();
        PageAwareAccessibilityProvider pageAwareAccessibilityProvider2 = (PageAwareAccessibilityProvider) this.mAccessibilityProvider;
        pageAwareAccessibilityProvider2.updatePageAccessibilityActions();
        if (adapter != null) {
            adapter.mObservable.registerObserver(pageAwareAccessibilityProvider2.mAdapterDataObserver);
        }
        if (adapter != null) {
            adapter.mObservable.registerObserver(this.mCurrentItemDataSetChangeObserver);
        }
    }

    public void setCurrentItem(int i) {
        setCurrentItem(i, true);
    }

    public void setCurrentItemInternal(int i, boolean z) {
        RecyclerView.Adapter adapter = getAdapter();
        boolean z2 = false;
        if (adapter == null) {
            if (this.mPendingCurrentItem != -1) {
                this.mPendingCurrentItem = Math.max(i, 0);
            }
        } else if (adapter.getItemCount() > 0) {
            int min = Math.min(Math.max(i, 0), adapter.getItemCount() - 1);
            int i2 = this.mCurrentItem;
            if (min == i2) {
                if (this.mScrollEventAdapter.mScrollState == 0) {
                    return;
                }
            }
            if (min != i2 || !z) {
                double d = i2;
                this.mCurrentItem = min;
                ((PageAwareAccessibilityProvider) this.mAccessibilityProvider).updatePageAccessibilityActions();
                ScrollEventAdapter scrollEventAdapter = this.mScrollEventAdapter;
                if (!(scrollEventAdapter.mScrollState == 0)) {
                    scrollEventAdapter.updateScrollEventValues();
                    ScrollEventAdapter.ScrollEventValues scrollEventValues = scrollEventAdapter.mScrollValues;
                    d = scrollEventValues.mPosition + scrollEventValues.mOffset;
                }
                ScrollEventAdapter scrollEventAdapter2 = this.mScrollEventAdapter;
                scrollEventAdapter2.mAdapterState = z ? 2 : 3;
                scrollEventAdapter2.mFakeDragging = false;
                if (scrollEventAdapter2.mTarget != min) {
                    z2 = true;
                }
                scrollEventAdapter2.mTarget = min;
                scrollEventAdapter2.dispatchStateChanged(2);
                if (z2) {
                    scrollEventAdapter2.dispatchSelected(min);
                }
                if (!z) {
                    this.mRecyclerView.scrollToPosition(min);
                    return;
                }
                double d2 = min;
                if (Math.abs(d2 - d) > 3.0d) {
                    this.mRecyclerView.scrollToPosition(d2 > d ? min - 3 : min + 3);
                    RecyclerView recyclerView = this.mRecyclerView;
                    recyclerView.post(new SmoothScrollToPosition(min, recyclerView));
                    return;
                }
                this.mRecyclerView.smoothScrollToPosition(min);
            }
        }
    }

    @Override // android.view.View
    public void setLayoutDirection(int i) {
        super.setLayoutDirection(i);
        ((PageAwareAccessibilityProvider) this.mAccessibilityProvider).updatePageAccessibilityActions();
    }

    public void setOffscreenPageLimit(int i) {
        if (i >= 1 || i == -1) {
            this.mOffscreenPageLimit = i;
            this.mRecyclerView.requestLayout();
            return;
        }
        throw new IllegalArgumentException("Offscreen page limit must be OFFSCREEN_PAGE_LIMIT_DEFAULT or a number > 0");
    }

    public void setOrientation(int i) {
        this.mLayoutManager.setOrientation(i);
        ((PageAwareAccessibilityProvider) this.mAccessibilityProvider).updatePageAccessibilityActions();
    }

    public void setPageTransformer(PageTransformer pageTransformer) {
        if (pageTransformer != null) {
            if (!this.mSavedItemAnimatorPresent) {
                this.mSavedItemAnimator = this.mRecyclerView.getItemAnimator();
                this.mSavedItemAnimatorPresent = true;
            }
            this.mRecyclerView.setItemAnimator(null);
        } else if (this.mSavedItemAnimatorPresent) {
            this.mRecyclerView.setItemAnimator(this.mSavedItemAnimator);
            this.mSavedItemAnimator = null;
            this.mSavedItemAnimatorPresent = false;
        }
        PageTransformerAdapter pageTransformerAdapter = this.mPageTransformerAdapter;
        if (pageTransformer != pageTransformerAdapter.mPageTransformer) {
            pageTransformerAdapter.mPageTransformer = pageTransformer;
            if (pageTransformer != null) {
                ScrollEventAdapter scrollEventAdapter = this.mScrollEventAdapter;
                scrollEventAdapter.updateScrollEventValues();
                ScrollEventAdapter.ScrollEventValues scrollEventValues = scrollEventAdapter.mScrollValues;
                double d = scrollEventValues.mPosition + scrollEventValues.mOffset;
                int i = (int) d;
                float f = (float) (d - i);
                this.mPageTransformerAdapter.onPageScrolled(i, f, Math.round(getPageSize() * f));
            }
        }
    }

    public void setUserInputEnabled(boolean z) {
        this.mUserInputEnabled = z;
        ((PageAwareAccessibilityProvider) this.mAccessibilityProvider).updatePageAccessibilityActions();
    }

    public void updateCurrentItem() {
        PagerSnapHelper pagerSnapHelper = this.mPagerSnapHelper;
        if (pagerSnapHelper != null) {
            View findSnapView = pagerSnapHelper.findSnapView(this.mLayoutManager);
            if (findSnapView != null) {
                int position = this.mLayoutManager.getPosition(findSnapView);
                if (position != this.mCurrentItem && getScrollState() == 0) {
                    this.mPageChangeEventDispatcher.onPageSelected(position);
                }
                this.mCurrentItemDirty = false;
                return;
            }
            return;
        }
        throw new IllegalStateException("Design assumption violated.");
    }

    public void setCurrentItem(int i, boolean z) {
        if (!this.mFakeDragger.mScrollEventAdapter.mFakeDragging) {
            setCurrentItemInternal(i, z);
            return;
        }
        throw new IllegalStateException("Cannot change current item when ViewPager2 is fake dragging");
    }

    /* loaded from: classes.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: androidx.viewpager2.widget.ViewPager2.SavedState.1
            @Override // android.os.Parcelable.Creator
            public Object createFromParcel(Parcel parcel) {
                return Build.VERSION.SDK_INT >= 24 ? new SavedState(parcel, null) : new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return Build.VERSION.SDK_INT >= 24 ? new SavedState(parcel, classLoader) : new SavedState(parcel);
            }
        };
        public Parcelable mAdapterState;
        public int mCurrentItem;
        public int mRecyclerViewId;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.mRecyclerViewId = parcel.readInt();
            this.mCurrentItem = parcel.readInt();
            this.mAdapterState = parcel.readParcelable(classLoader);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.mRecyclerViewId);
            parcel.writeInt(this.mCurrentItem);
            parcel.writeParcelable(this.mAdapterState, i);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.mRecyclerViewId = parcel.readInt();
            this.mCurrentItem = parcel.readInt();
            this.mAdapterState = parcel.readParcelable(null);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
