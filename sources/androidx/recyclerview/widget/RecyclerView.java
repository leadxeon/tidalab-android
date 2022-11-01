package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility;
import androidx.core.os.TraceCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import androidx.recyclerview.R$styleable;
import androidx.recyclerview.widget.AdapterHelper;
import androidx.recyclerview.widget.ChildHelper;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GapWorker;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import androidx.recyclerview.widget.ViewBoundsCheck;
import androidx.recyclerview.widget.ViewInfoStore;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.foss.R;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements NestedScrollingChild {
    public static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC;
    public static final boolean ALLOW_THREAD_GAP_WORK;
    public static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
    public static final int[] NESTED_SCROLLING_ATTRS = {16843830};
    public static final boolean POST_UPDATES_ON_ANIMATION;
    public static final Interpolator sQuinticInterpolator;
    public RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
    public final AccessibilityManager mAccessibilityManager;
    public Adapter mAdapter;
    public AdapterHelper mAdapterHelper;
    public boolean mAdapterUpdateDuringMeasure;
    public EdgeEffect mBottomGlow;
    public ChildDrawingOrderCallback mChildDrawingOrderCallback;
    public ChildHelper mChildHelper;
    public boolean mClipToPadding;
    public boolean mDataSetHasChangedAfterLayout;
    public boolean mDispatchItemsChangedEvent;
    public int mDispatchScrollCounter;
    public int mEatenAccessibilityChangeFlags;
    public EdgeEffectFactory mEdgeEffectFactory;
    public boolean mEnableFastScroller;
    public boolean mFirstLayoutComplete;
    public GapWorker mGapWorker;
    public boolean mHasFixedSize;
    public boolean mIgnoreMotionEventTillDown;
    public int mInitialTouchX;
    public int mInitialTouchY;
    public int mInterceptRequestLayoutDepth;
    public OnItemTouchListener mInterceptingOnItemTouchListener;
    public boolean mIsAttached;
    public ItemAnimator mItemAnimator;
    public ItemAnimator.ItemAnimatorListener mItemAnimatorListener;
    public Runnable mItemAnimatorRunner;
    public final ArrayList<ItemDecoration> mItemDecorations;
    public boolean mItemsAddedOrRemoved;
    public boolean mItemsChanged;
    public int mLastAutoMeasureNonExactMeasuredHeight;
    public int mLastAutoMeasureNonExactMeasuredWidth;
    public boolean mLastAutoMeasureSkippedDueToExact;
    public int mLastTouchX;
    public int mLastTouchY;
    public LayoutManager mLayout;
    public int mLayoutOrScrollCounter;
    public boolean mLayoutSuppressed;
    public boolean mLayoutWasDefered;
    public EdgeEffect mLeftGlow;
    public final int mMaxFlingVelocity;
    public final int mMinFlingVelocity;
    public final int[] mMinMaxLayoutPositions;
    public final int[] mNestedOffsets;
    public final RecyclerViewDataObserver mObserver;
    public List<OnChildAttachStateChangeListener> mOnChildAttachStateListeners;
    public OnFlingListener mOnFlingListener;
    public final ArrayList<OnItemTouchListener> mOnItemTouchListeners;
    public final List<ViewHolder> mPendingAccessibilityImportanceChange;
    public SavedState mPendingSavedState;
    public boolean mPostedAnimatorRunner;
    public GapWorker.LayoutPrefetchRegistryImpl mPrefetchRegistry;
    public boolean mPreserveFocusAfterLayout;
    public final Recycler mRecycler;
    public RecyclerListener mRecyclerListener;
    public final List<RecyclerListener> mRecyclerListeners;
    public final int[] mReusableIntPair;
    public EdgeEffect mRightGlow;
    public float mScaledHorizontalScrollFactor;
    public float mScaledVerticalScrollFactor;
    public OnScrollListener mScrollListener;
    public List<OnScrollListener> mScrollListeners;
    public final int[] mScrollOffset;
    public int mScrollPointerId;
    public int mScrollState;
    public NestedScrollingChildHelper mScrollingChildHelper;
    public final State mState;
    public final Rect mTempRect;
    public final Rect mTempRect2;
    public final RectF mTempRectF;
    public EdgeEffect mTopGlow;
    public int mTouchSlop;
    public final Runnable mUpdateChildViewsRunnable;
    public VelocityTracker mVelocityTracker;
    public final ViewFlinger mViewFlinger;
    public final ViewInfoStore.ProcessCallback mViewInfoProcessCallback;
    public final ViewInfoStore mViewInfoStore;

    /* renamed from: androidx.recyclerview.widget.RecyclerView$4  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass4 implements ViewInfoStore.ProcessCallback {
        public AnonymousClass4() {
        }

        public void processAppeared(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo, ItemAnimator.ItemHolderInfo itemHolderInfo2) {
            boolean z;
            int i;
            int i2;
            RecyclerView recyclerView = RecyclerView.this;
            Objects.requireNonNull(recyclerView);
            viewHolder.setIsRecyclable(false);
            SimpleItemAnimator simpleItemAnimator = (SimpleItemAnimator) recyclerView.mItemAnimator;
            Objects.requireNonNull(simpleItemAnimator);
            if (itemHolderInfo == null || ((i = itemHolderInfo.left) == (i2 = itemHolderInfo2.left) && itemHolderInfo.top == itemHolderInfo2.top)) {
                DefaultItemAnimator defaultItemAnimator = (DefaultItemAnimator) simpleItemAnimator;
                defaultItemAnimator.resetAnimation(viewHolder);
                viewHolder.itemView.setAlpha(0.0f);
                defaultItemAnimator.mPendingAdditions.add(viewHolder);
                z = true;
            } else {
                z = simpleItemAnimator.animateMove(viewHolder, i, itemHolderInfo.top, i2, itemHolderInfo2.top);
            }
            if (z) {
                recyclerView.postAnimationRunner();
            }
        }

        public void processDisappeared(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo, ItemAnimator.ItemHolderInfo itemHolderInfo2) {
            boolean z;
            RecyclerView.this.mRecycler.unscrapView(viewHolder);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.addAnimatingView(viewHolder);
            viewHolder.setIsRecyclable(false);
            SimpleItemAnimator simpleItemAnimator = (SimpleItemAnimator) recyclerView.mItemAnimator;
            Objects.requireNonNull(simpleItemAnimator);
            int i = itemHolderInfo.left;
            int i2 = itemHolderInfo.top;
            View view = viewHolder.itemView;
            int left = itemHolderInfo2 == null ? view.getLeft() : itemHolderInfo2.left;
            int top = itemHolderInfo2 == null ? view.getTop() : itemHolderInfo2.top;
            if (viewHolder.isRemoved() || (i == left && i2 == top)) {
                DefaultItemAnimator defaultItemAnimator = (DefaultItemAnimator) simpleItemAnimator;
                defaultItemAnimator.resetAnimation(viewHolder);
                defaultItemAnimator.mPendingRemovals.add(viewHolder);
                z = true;
            } else {
                view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                z = simpleItemAnimator.animateMove(viewHolder, i, i2, left, top);
            }
            if (z) {
                recyclerView.postAnimationRunner();
            }
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$5  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass5 implements ChildHelper.Callback {
        public AnonymousClass5() {
        }

        public View getChildAt(int i) {
            return RecyclerView.this.getChildAt(i);
        }

        public int getChildCount() {
            return RecyclerView.this.getChildCount();
        }

        public void removeViewAt(int i) {
            View childAt = RecyclerView.this.getChildAt(i);
            if (childAt != null) {
                RecyclerView.this.dispatchChildDetached(childAt);
                childAt.clearAnimation();
            }
            RecyclerView.this.removeViewAt(i);
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$6  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass6 implements AdapterHelper.Callback {
        public AnonymousClass6() {
        }

        public void dispatchUpdate(AdapterHelper.UpdateOp updateOp) {
            int i = updateOp.cmd;
            if (i == 1) {
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.mLayout.onItemsAdded(recyclerView, updateOp.positionStart, updateOp.itemCount);
            } else if (i == 2) {
                RecyclerView recyclerView2 = RecyclerView.this;
                recyclerView2.mLayout.onItemsRemoved(recyclerView2, updateOp.positionStart, updateOp.itemCount);
            } else if (i == 4) {
                RecyclerView recyclerView3 = RecyclerView.this;
                recyclerView3.mLayout.onItemsUpdated(recyclerView3, updateOp.positionStart, updateOp.itemCount, updateOp.payload);
            } else if (i == 8) {
                RecyclerView recyclerView4 = RecyclerView.this;
                recyclerView4.mLayout.onItemsMoved(recyclerView4, updateOp.positionStart, updateOp.itemCount, 1);
            }
        }

        public ViewHolder findViewHolder(int i) {
            RecyclerView recyclerView = RecyclerView.this;
            int unfilteredChildCount = recyclerView.mChildHelper.getUnfilteredChildCount();
            int i2 = 0;
            ViewHolder viewHolder = null;
            while (true) {
                if (i2 >= unfilteredChildCount) {
                    break;
                }
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.getUnfilteredChildAt(i2));
                if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && childViewHolderInt.mPosition == i) {
                    if (!recyclerView.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                        viewHolder = childViewHolderInt;
                        break;
                    }
                    viewHolder = childViewHolderInt;
                }
                i2++;
            }
            if (viewHolder != null && !RecyclerView.this.mChildHelper.isHidden(viewHolder.itemView)) {
                return viewHolder;
            }
            return null;
        }

        public void markViewHoldersUpdated(int i, int i2, Object obj) {
            int i3;
            int i4;
            RecyclerView recyclerView = RecyclerView.this;
            int unfilteredChildCount = recyclerView.mChildHelper.getUnfilteredChildCount();
            int i5 = i2 + i;
            for (int i6 = 0; i6 < unfilteredChildCount; i6++) {
                View unfilteredChildAt = recyclerView.mChildHelper.getUnfilteredChildAt(i6);
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(unfilteredChildAt);
                if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && (i4 = childViewHolderInt.mPosition) >= i && i4 < i5) {
                    childViewHolderInt.addFlags(2);
                    childViewHolderInt.addChangePayload(obj);
                    ((LayoutParams) unfilteredChildAt.getLayoutParams()).mInsetsDirty = true;
                }
            }
            Recycler recycler = recyclerView.mRecycler;
            int size = recycler.mCachedViews.size();
            while (true) {
                size--;
                if (size >= 0) {
                    ViewHolder viewHolder = recycler.mCachedViews.get(size);
                    if (viewHolder != null && (i3 = viewHolder.mPosition) >= i && i3 < i5) {
                        viewHolder.addFlags(2);
                        recycler.recycleCachedViewAt(size);
                    }
                } else {
                    RecyclerView.this.mItemsChanged = true;
                    return;
                }
            }
        }

        public void offsetPositionsForAdd(int i, int i2) {
            RecyclerView recyclerView = RecyclerView.this;
            int unfilteredChildCount = recyclerView.mChildHelper.getUnfilteredChildCount();
            for (int i3 = 0; i3 < unfilteredChildCount; i3++) {
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.getUnfilteredChildAt(i3));
                if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.mPosition >= i) {
                    childViewHolderInt.offsetPosition(i2, false);
                    recyclerView.mState.mStructureChanged = true;
                }
            }
            Recycler recycler = recyclerView.mRecycler;
            int size = recycler.mCachedViews.size();
            for (int i4 = 0; i4 < size; i4++) {
                ViewHolder viewHolder = recycler.mCachedViews.get(i4);
                if (viewHolder != null && viewHolder.mPosition >= i) {
                    viewHolder.offsetPosition(i2, false);
                }
            }
            recyclerView.requestLayout();
            RecyclerView.this.mItemsAddedOrRemoved = true;
        }

        public void offsetPositionsForMove(int i, int i2) {
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            int i8;
            int i9;
            RecyclerView recyclerView = RecyclerView.this;
            int unfilteredChildCount = recyclerView.mChildHelper.getUnfilteredChildCount();
            int i10 = -1;
            if (i < i2) {
                i5 = i;
                i4 = i2;
                i3 = -1;
            } else {
                i4 = i;
                i5 = i2;
                i3 = 1;
            }
            for (int i11 = 0; i11 < unfilteredChildCount; i11++) {
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.getUnfilteredChildAt(i11));
                if (childViewHolderInt != null && (i9 = childViewHolderInt.mPosition) >= i5 && i9 <= i4) {
                    if (i9 == i) {
                        childViewHolderInt.offsetPosition(i2 - i, false);
                    } else {
                        childViewHolderInt.offsetPosition(i3, false);
                    }
                    recyclerView.mState.mStructureChanged = true;
                }
            }
            Recycler recycler = recyclerView.mRecycler;
            if (i < i2) {
                i7 = i;
                i6 = i2;
            } else {
                i6 = i;
                i7 = i2;
                i10 = 1;
            }
            int size = recycler.mCachedViews.size();
            for (int i12 = 0; i12 < size; i12++) {
                ViewHolder viewHolder = recycler.mCachedViews.get(i12);
                if (viewHolder != null && (i8 = viewHolder.mPosition) >= i7 && i8 <= i6) {
                    if (i8 == i) {
                        viewHolder.offsetPosition(i2 - i, false);
                    } else {
                        viewHolder.offsetPosition(i10, false);
                    }
                }
            }
            recyclerView.requestLayout();
            RecyclerView.this.mItemsAddedOrRemoved = true;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Adapter<VH extends ViewHolder> {
        public final AdapterDataObservable mObservable = new AdapterDataObservable();
        public boolean mHasStableIds = false;
        public int mStateRestorationPolicy = 1;

        public abstract int getItemCount();

        public long getItemId(int i) {
            return -1L;
        }

        public final void notifyItemChanged(int i) {
            this.mObservable.notifyItemRangeChanged(i, 1, null);
        }

        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        }

        public abstract void onBindViewHolder(VH vh, int i);

        public abstract VH onCreateViewHolder(ViewGroup viewGroup, int i);

        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        }
    }

    /* loaded from: classes.dex */
    public static class AdapterDataObservable extends Observable<AdapterDataObserver> {
        public boolean hasObservers() {
            return !((Observable) this).mObservers.isEmpty();
        }

        public void notifyChanged() {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onChanged();
            }
        }

        public void notifyItemMoved(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onItemRangeMoved(i, i2, 1);
            }
        }

        public void notifyItemRangeChanged(int i, int i2, Object obj) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onItemRangeChanged(i, i2, obj);
            }
        }

        public void notifyItemRangeInserted(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onItemRangeInserted(i, i2);
            }
        }

        public void notifyItemRangeRemoved(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onItemRangeRemoved(i, i2);
            }
        }
    }

    /* loaded from: classes.dex */
    public static abstract class AdapterDataObserver {
        public void onChanged() {
        }

        public void onItemRangeChanged(int i, int i2) {
        }

        public void onItemRangeChanged(int i, int i2, Object obj) {
            onItemRangeChanged(i, i2);
        }

        public void onItemRangeInserted(int i, int i2) {
        }

        public void onItemRangeMoved(int i, int i2, int i3) {
        }

        public void onItemRangeRemoved(int i, int i2) {
        }
    }

    /* loaded from: classes.dex */
    public interface ChildDrawingOrderCallback {
        int onGetChildDrawingOrder(int i, int i2);
    }

    /* loaded from: classes.dex */
    public static class EdgeEffectFactory {
        public EdgeEffect createEdgeEffect(RecyclerView recyclerView) {
            return new EdgeEffect(recyclerView.getContext());
        }
    }

    /* loaded from: classes.dex */
    public static abstract class ItemAnimator {
        public ItemAnimatorListener mListener = null;
        public ArrayList<ItemAnimatorFinishedListener> mFinishedListeners = new ArrayList<>();
        public long mAddDuration = 120;
        public long mRemoveDuration = 120;
        public long mMoveDuration = 250;
        public long mChangeDuration = 250;

        /* loaded from: classes.dex */
        public interface ItemAnimatorFinishedListener {
            void onAnimationsFinished();
        }

        /* loaded from: classes.dex */
        public interface ItemAnimatorListener {
        }

        /* loaded from: classes.dex */
        public static class ItemHolderInfo {
            public int left;
            public int top;
        }

        public static int buildAdapterChangeFlagsForAnimations(ViewHolder viewHolder) {
            int i = viewHolder.mFlags & 14;
            if (viewHolder.isInvalid()) {
                return 4;
            }
            if ((i & 4) != 0) {
                return i;
            }
            int i2 = viewHolder.mOldPosition;
            RecyclerView recyclerView = viewHolder.mOwnerRecyclerView;
            int adapterPositionInRecyclerView = recyclerView == null ? -1 : recyclerView.getAdapterPositionInRecyclerView(viewHolder);
            return (i2 == -1 || adapterPositionInRecyclerView == -1 || i2 == adapterPositionInRecyclerView) ? i : i | 2048;
        }

        public abstract boolean animateChange(ViewHolder viewHolder, ViewHolder viewHolder2, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);

        public final void dispatchAnimationFinished(ViewHolder viewHolder) {
            ItemAnimatorListener itemAnimatorListener = this.mListener;
            if (itemAnimatorListener != null) {
                ItemAnimatorRestoreListener itemAnimatorRestoreListener = (ItemAnimatorRestoreListener) itemAnimatorListener;
                Objects.requireNonNull(itemAnimatorRestoreListener);
                boolean z = true;
                viewHolder.setIsRecyclable(true);
                if (viewHolder.mShadowedHolder != null && viewHolder.mShadowingHolder == null) {
                    viewHolder.mShadowedHolder = null;
                }
                viewHolder.mShadowingHolder = null;
                if (!((viewHolder.mFlags & 16) != 0)) {
                    RecyclerView recyclerView = RecyclerView.this;
                    View view = viewHolder.itemView;
                    recyclerView.startInterceptRequestLayout();
                    ChildHelper childHelper = recyclerView.mChildHelper;
                    int indexOfChild = RecyclerView.this.indexOfChild(view);
                    if (indexOfChild == -1) {
                        childHelper.unhideViewInternal(view);
                    } else if (childHelper.mBucket.get(indexOfChild)) {
                        childHelper.mBucket.remove(indexOfChild);
                        childHelper.unhideViewInternal(view);
                        ((AnonymousClass5) childHelper.mCallback).removeViewAt(indexOfChild);
                    } else {
                        z = false;
                    }
                    if (z) {
                        ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                        recyclerView.mRecycler.unscrapView(childViewHolderInt);
                        recyclerView.mRecycler.recycleViewHolderInternal(childViewHolderInt);
                    }
                    recyclerView.stopInterceptRequestLayout(!z);
                    if (!z && viewHolder.isTmpDetached()) {
                        RecyclerView.this.removeDetachedView(viewHolder.itemView, false);
                    }
                }
            }
        }

        public final void dispatchAnimationsFinished() {
            int size = this.mFinishedListeners.size();
            for (int i = 0; i < size; i++) {
                this.mFinishedListeners.get(i).onAnimationsFinished();
            }
            this.mFinishedListeners.clear();
        }

        public abstract void endAnimation(ViewHolder viewHolder);

        public abstract void endAnimations();

        public abstract boolean isRunning();

        public ItemHolderInfo recordPreLayoutInformation(ViewHolder viewHolder) {
            ItemHolderInfo itemHolderInfo = new ItemHolderInfo();
            View view = viewHolder.itemView;
            itemHolderInfo.left = view.getLeft();
            itemHolderInfo.top = view.getTop();
            view.getRight();
            view.getBottom();
            return itemHolderInfo;
        }
    }

    /* loaded from: classes.dex */
    public class ItemAnimatorRestoreListener implements ItemAnimator.ItemAnimatorListener {
        public ItemAnimatorRestoreListener() {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class ItemDecoration {
        public void onDraw(Canvas canvas, RecyclerView recyclerView, State state) {
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, State state) {
        }
    }

    /* loaded from: classes.dex */
    public interface OnChildAttachStateChangeListener {
        void onChildViewAttachedToWindow(View view);

        void onChildViewDetachedFromWindow(View view);
    }

    /* loaded from: classes.dex */
    public static abstract class OnFlingListener {
    }

    /* loaded from: classes.dex */
    public interface OnItemTouchListener {
        boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent);

        void onRequestDisallowInterceptTouchEvent(boolean z);

        void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent);
    }

    /* loaded from: classes.dex */
    public static abstract class OnScrollListener {
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        }
    }

    /* loaded from: classes.dex */
    public static class RecycledViewPool {
        public SparseArray<ScrapData> mScrap = new SparseArray<>();
        public int mAttachCount = 0;

        /* loaded from: classes.dex */
        public static class ScrapData {
            public final ArrayList<ViewHolder> mScrapHeap = new ArrayList<>();
            public int mMaxScrap = 5;
            public long mCreateRunningAverageNs = 0;
            public long mBindRunningAverageNs = 0;
        }

        public final ScrapData getScrapDataForType(int i) {
            ScrapData scrapData = this.mScrap.get(i);
            if (scrapData != null) {
                return scrapData;
            }
            ScrapData scrapData2 = new ScrapData();
            this.mScrap.put(i, scrapData2);
            return scrapData2;
        }

        public long runningAverage(long j, long j2) {
            if (j == 0) {
                return j2;
            }
            return (j2 / 4) + ((j / 4) * 3);
        }
    }

    /* loaded from: classes.dex */
    public final class Recycler {
        public final ArrayList<ViewHolder> mAttachedScrap;
        public RecycledViewPool mRecyclerPool;
        public final List<ViewHolder> mUnmodifiableAttachedScrap;
        public ArrayList<ViewHolder> mChangedScrap = null;
        public final ArrayList<ViewHolder> mCachedViews = new ArrayList<>();
        public int mRequestedCacheMax = 2;
        public int mViewCacheMax = 2;

        public Recycler() {
            ArrayList<ViewHolder> arrayList = new ArrayList<>();
            this.mAttachedScrap = arrayList;
            this.mUnmodifiableAttachedScrap = Collections.unmodifiableList(arrayList);
        }

        public void addViewHolderToRecycledViewPool(ViewHolder viewHolder, boolean z) {
            RecyclerView.clearNestedRecyclerViewIfNotNested(viewHolder);
            View view = viewHolder.itemView;
            RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate = RecyclerView.this.mAccessibilityDelegate;
            if (recyclerViewAccessibilityDelegate != null) {
                RecyclerViewAccessibilityDelegate.ItemDelegate itemDelegate = recyclerViewAccessibilityDelegate.mItemDelegate;
                ViewCompat.setAccessibilityDelegate(view, itemDelegate instanceof RecyclerViewAccessibilityDelegate.ItemDelegate ? itemDelegate.mOriginalItemDelegates.remove(view) : null);
            }
            if (z) {
                RecyclerListener recyclerListener = RecyclerView.this.mRecyclerListener;
                if (recyclerListener != null) {
                    recyclerListener.onViewRecycled(viewHolder);
                }
                int size = RecyclerView.this.mRecyclerListeners.size();
                for (int i = 0; i < size; i++) {
                    RecyclerView.this.mRecyclerListeners.get(i).onViewRecycled(viewHolder);
                }
                RecyclerView recyclerView = RecyclerView.this;
                Adapter adapter = recyclerView.mAdapter;
                if (recyclerView.mState != null) {
                    recyclerView.mViewInfoStore.removeViewHolder(viewHolder);
                }
            }
            viewHolder.mBindingAdapter = null;
            viewHolder.mOwnerRecyclerView = null;
            RecycledViewPool recycledViewPool = getRecycledViewPool();
            Objects.requireNonNull(recycledViewPool);
            int i2 = viewHolder.mItemViewType;
            ArrayList<ViewHolder> arrayList = recycledViewPool.getScrapDataForType(i2).mScrapHeap;
            if (recycledViewPool.mScrap.get(i2).mMaxScrap > arrayList.size()) {
                viewHolder.resetInternal();
                arrayList.add(viewHolder);
            }
        }

        public void clear() {
            this.mAttachedScrap.clear();
            recycleAndClearCachedViews();
        }

        public int convertPreLayoutPositionToPostLayout(int i) {
            if (i < 0 || i >= RecyclerView.this.mState.getItemCount()) {
                StringBuilder outline15 = GeneratedOutlineSupport.outline15("invalid position ", i, ". State item count is ");
                outline15.append(RecyclerView.this.mState.getItemCount());
                throw new IndexOutOfBoundsException(GeneratedOutlineSupport.outline2(RecyclerView.this, outline15));
            }
            RecyclerView recyclerView = RecyclerView.this;
            return !recyclerView.mState.mInPreLayout ? i : recyclerView.mAdapterHelper.findPositionOffset(i, 0);
        }

        public RecycledViewPool getRecycledViewPool() {
            if (this.mRecyclerPool == null) {
                this.mRecyclerPool = new RecycledViewPool();
            }
            return this.mRecyclerPool;
        }

        public void recycleAndClearCachedViews() {
            for (int size = this.mCachedViews.size() - 1; size >= 0; size--) {
                recycleCachedViewAt(size);
            }
            this.mCachedViews.clear();
            if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl = RecyclerView.this.mPrefetchRegistry;
                int[] iArr = layoutPrefetchRegistryImpl.mPrefetchArray;
                if (iArr != null) {
                    Arrays.fill(iArr, -1);
                }
                layoutPrefetchRegistryImpl.mCount = 0;
            }
        }

        public void recycleCachedViewAt(int i) {
            addViewHolderToRecycledViewPool(this.mCachedViews.get(i), true);
            this.mCachedViews.remove(i);
        }

        public void recycleView(View view) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (childViewHolderInt.isScrap()) {
                childViewHolderInt.mScrapContainer.unscrapView(childViewHolderInt);
            } else if (childViewHolderInt.wasReturnedFromScrap()) {
                childViewHolderInt.clearReturnedFromScrapFlag();
            }
            recycleViewHolderInternal(childViewHolderInt);
            if (RecyclerView.this.mItemAnimator != null && !childViewHolderInt.isRecyclable()) {
                RecyclerView.this.mItemAnimator.endAnimation(childViewHolderInt);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0091  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x009c A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:59:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void recycleViewHolderInternal(androidx.recyclerview.widget.RecyclerView.ViewHolder r6) {
            /*
                Method dump skipped, instructions count: 252
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.Recycler.recycleViewHolderInternal(androidx.recyclerview.widget.RecyclerView$ViewHolder):void");
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x003d  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0043  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void scrapView(android.view.View r5) {
            /*
                r4 = this;
                androidx.recyclerview.widget.RecyclerView$ViewHolder r5 = androidx.recyclerview.widget.RecyclerView.getChildViewHolderInt(r5)
                r0 = 12
                boolean r0 = r5.hasAnyOfTheFlags(r0)
                r1 = 0
                if (r0 != 0) goto L_0x0058
                boolean r0 = r5.isUpdated()
                if (r0 == 0) goto L_0x0058
                androidx.recyclerview.widget.RecyclerView r0 = androidx.recyclerview.widget.RecyclerView.this
                androidx.recyclerview.widget.RecyclerView$ItemAnimator r0 = r0.mItemAnimator
                r2 = 1
                if (r0 == 0) goto L_0x003f
                java.util.List r3 = r5.getUnmodifiedPayloads()
                androidx.recyclerview.widget.DefaultItemAnimator r0 = (androidx.recyclerview.widget.DefaultItemAnimator) r0
                boolean r3 = r3.isEmpty()
                if (r3 == 0) goto L_0x0039
                boolean r0 = r0.mSupportsChangeAnimations
                if (r0 == 0) goto L_0x0033
                boolean r0 = r5.isInvalid()
                if (r0 == 0) goto L_0x0031
                goto L_0x0033
            L_0x0031:
                r0 = 0
                goto L_0x0034
            L_0x0033:
                r0 = 1
            L_0x0034:
                if (r0 == 0) goto L_0x0037
                goto L_0x0039
            L_0x0037:
                r0 = 0
                goto L_0x003a
            L_0x0039:
                r0 = 1
            L_0x003a:
                if (r0 == 0) goto L_0x003d
                goto L_0x003f
            L_0x003d:
                r0 = 0
                goto L_0x0040
            L_0x003f:
                r0 = 1
            L_0x0040:
                if (r0 == 0) goto L_0x0043
                goto L_0x0058
            L_0x0043:
                java.util.ArrayList<androidx.recyclerview.widget.RecyclerView$ViewHolder> r0 = r4.mChangedScrap
                if (r0 != 0) goto L_0x004e
                java.util.ArrayList r0 = new java.util.ArrayList
                r0.<init>()
                r4.mChangedScrap = r0
            L_0x004e:
                r5.mScrapContainer = r4
                r5.mInChangeScrap = r2
                java.util.ArrayList<androidx.recyclerview.widget.RecyclerView$ViewHolder> r0 = r4.mChangedScrap
                r0.add(r5)
                goto L_0x0088
            L_0x0058:
                boolean r0 = r5.isInvalid()
                if (r0 == 0) goto L_0x007f
                boolean r0 = r5.isRemoved()
                if (r0 != 0) goto L_0x007f
                androidx.recyclerview.widget.RecyclerView r0 = androidx.recyclerview.widget.RecyclerView.this
                androidx.recyclerview.widget.RecyclerView$Adapter r0 = r0.mAdapter
                boolean r0 = r0.mHasStableIds
                if (r0 == 0) goto L_0x006d
                goto L_0x007f
            L_0x006d:
                java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
                java.lang.String r0 = "Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool."
                java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport.outline13(r0)
                androidx.recyclerview.widget.RecyclerView r1 = androidx.recyclerview.widget.RecyclerView.this
                java.lang.String r0 = com.android.tools.r8.GeneratedOutlineSupport.outline2(r1, r0)
                r5.<init>(r0)
                throw r5
            L_0x007f:
                r5.mScrapContainer = r4
                r5.mInChangeScrap = r1
                java.util.ArrayList<androidx.recyclerview.widget.RecyclerView$ViewHolder> r0 = r4.mAttachedScrap
                r0.add(r5)
            L_0x0088:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.Recycler.scrapView(android.view.View):void");
        }

        /* JADX WARN: Code restructure failed: missing block: B:104:0x01e6, code lost:
            if (r7.mItemViewType != 0) goto L_0x01fe;
         */
        /* JADX WARN: Code restructure failed: missing block: B:162:0x02fb, code lost:
            r7 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:229:0x044a, code lost:
            if (r7.isInvalid() == false) goto L_0x0482;
         */
        /* JADX WARN: Code restructure failed: missing block: B:238:0x0480, code lost:
            if ((r10 == 0 || r10 + r8 < r19) == false) goto L_0x0482;
         */
        /* JADX WARN: Removed duplicated region for block: B:126:0x0248  */
        /* JADX WARN: Removed duplicated region for block: B:215:0x0412  */
        /* JADX WARN: Removed duplicated region for block: B:223:0x043c  */
        /* JADX WARN: Removed duplicated region for block: B:232:0x0469  */
        /* JADX WARN: Removed duplicated region for block: B:242:0x0490  */
        /* JADX WARN: Removed duplicated region for block: B:243:0x0492  */
        /* JADX WARN: Removed duplicated region for block: B:245:0x0495  */
        /* JADX WARN: Removed duplicated region for block: B:251:0x04b8  */
        /* JADX WARN: Removed duplicated region for block: B:265:0x0501  */
        /* JADX WARN: Removed duplicated region for block: B:279:0x0537  */
        /* JADX WARN: Removed duplicated region for block: B:283:0x0542  */
        /* JADX WARN: Removed duplicated region for block: B:284:0x0550  */
        /* JADX WARN: Removed duplicated region for block: B:290:0x056c A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0089  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x0090  */
        /* JADX WARN: Removed duplicated region for block: B:94:0x01bc  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public androidx.recyclerview.widget.RecyclerView.ViewHolder tryGetViewHolderForPositionByDeadline(int r17, boolean r18, long r19) {
            /*
                Method dump skipped, instructions count: 1443
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.Recycler.tryGetViewHolderForPositionByDeadline(int, boolean, long):androidx.recyclerview.widget.RecyclerView$ViewHolder");
        }

        public void unscrapView(ViewHolder viewHolder) {
            if (viewHolder.mInChangeScrap) {
                this.mChangedScrap.remove(viewHolder);
            } else {
                this.mAttachedScrap.remove(viewHolder);
            }
            viewHolder.mScrapContainer = null;
            viewHolder.mInChangeScrap = false;
            viewHolder.clearReturnedFromScrapFlag();
        }

        public void updateViewCacheSize() {
            LayoutManager layoutManager = RecyclerView.this.mLayout;
            this.mViewCacheMax = this.mRequestedCacheMax + (layoutManager != null ? layoutManager.mPrefetchMaxCountObserved : 0);
            for (int size = this.mCachedViews.size() - 1; size >= 0 && this.mCachedViews.size() > this.mViewCacheMax; size--) {
                recycleCachedViewAt(size);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface RecyclerListener {
        void onViewRecycled(ViewHolder viewHolder);
    }

    /* loaded from: classes.dex */
    public class RecyclerViewDataObserver extends AdapterDataObserver {
        public RecyclerViewDataObserver() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.mState.mStructureChanged = true;
            recyclerView.processDataSetCompletelyChanged(true);
            if (!RecyclerView.this.mAdapterHelper.hasPendingUpdates()) {
                RecyclerView.this.requestLayout();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2, Object obj) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            AdapterHelper adapterHelper = RecyclerView.this.mAdapterHelper;
            Objects.requireNonNull(adapterHelper);
            boolean z = false;
            if (i2 >= 1) {
                adapterHelper.mPendingUpdates.add(adapterHelper.obtainUpdateOp(4, i, i2, obj));
                adapterHelper.mExistingUpdateTypes |= 4;
                if (adapterHelper.mPendingUpdates.size() == 1) {
                    z = true;
                }
            }
            if (z) {
                triggerUpdateProcessor();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int i, int i2) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            AdapterHelper adapterHelper = RecyclerView.this.mAdapterHelper;
            Objects.requireNonNull(adapterHelper);
            boolean z = false;
            if (i2 >= 1) {
                adapterHelper.mPendingUpdates.add(adapterHelper.obtainUpdateOp(1, i, i2, null));
                adapterHelper.mExistingUpdateTypes |= 1;
                if (adapterHelper.mPendingUpdates.size() == 1) {
                    z = true;
                }
            }
            if (z) {
                triggerUpdateProcessor();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int i, int i2, int i3) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            AdapterHelper adapterHelper = RecyclerView.this.mAdapterHelper;
            Objects.requireNonNull(adapterHelper);
            boolean z = false;
            if (i != i2) {
                if (i3 == 1) {
                    adapterHelper.mPendingUpdates.add(adapterHelper.obtainUpdateOp(8, i, i2, null));
                    adapterHelper.mExistingUpdateTypes |= 8;
                    if (adapterHelper.mPendingUpdates.size() == 1) {
                        z = true;
                    }
                } else {
                    throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
                }
            }
            if (z) {
                triggerUpdateProcessor();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int i, int i2) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            AdapterHelper adapterHelper = RecyclerView.this.mAdapterHelper;
            Objects.requireNonNull(adapterHelper);
            boolean z = false;
            if (i2 >= 1) {
                adapterHelper.mPendingUpdates.add(adapterHelper.obtainUpdateOp(2, i, i2, null));
                adapterHelper.mExistingUpdateTypes |= 2;
                if (adapterHelper.mPendingUpdates.size() == 1) {
                    z = true;
                }
            }
            if (z) {
                triggerUpdateProcessor();
            }
        }

        public void triggerUpdateProcessor() {
            if (RecyclerView.POST_UPDATES_ON_ANIMATION) {
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.mHasFixedSize && recyclerView.mIsAttached) {
                    Runnable runnable = recyclerView.mUpdateChildViewsRunnable;
                    AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                    recyclerView.postOnAnimation(runnable);
                    return;
                }
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            recyclerView2.mAdapterUpdateDuringMeasure = true;
            recyclerView2.requestLayout();
        }
    }

    /* loaded from: classes.dex */
    public static abstract class SmoothScroller {
        public LayoutManager mLayoutManager;
        public boolean mPendingInitialRun;
        public RecyclerView mRecyclerView;
        public boolean mRunning;
        public boolean mStarted;
        public View mTargetView;
        public int mTargetPosition = -1;
        public final Action mRecyclingAction = new Action(0, 0);

        /* loaded from: classes.dex */
        public static class Action {
            public int mDx;
            public int mDy;
            public int mJumpToPosition = -1;
            public boolean mChanged = false;
            public int mConsecutiveUpdates = 0;
            public int mDuration = Integer.MIN_VALUE;
            public Interpolator mInterpolator = null;

            public Action(int i, int i2) {
                this.mDx = i;
                this.mDy = i2;
            }

            public void runIfNecessary(RecyclerView recyclerView) {
                int i = this.mJumpToPosition;
                if (i >= 0) {
                    this.mJumpToPosition = -1;
                    recyclerView.jumpToPositionForSmoothScroller(i);
                    this.mChanged = false;
                } else if (this.mChanged) {
                    Interpolator interpolator = this.mInterpolator;
                    if (interpolator == null || this.mDuration >= 1) {
                        int i2 = this.mDuration;
                        if (i2 >= 1) {
                            recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, i2, interpolator);
                            int i3 = this.mConsecutiveUpdates + 1;
                            this.mConsecutiveUpdates = i3;
                            if (i3 > 10) {
                                Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                            }
                            this.mChanged = false;
                            return;
                        }
                        throw new IllegalStateException("Scroll duration must be a positive number");
                    }
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                } else {
                    this.mConsecutiveUpdates = 0;
                }
            }

            public void update(int i, int i2, int i3, Interpolator interpolator) {
                this.mDx = i;
                this.mDy = i2;
                this.mDuration = i3;
                this.mInterpolator = interpolator;
                this.mChanged = true;
            }
        }

        /* loaded from: classes.dex */
        public interface ScrollVectorProvider {
            PointF computeScrollVectorForPosition(int i);
        }

        public PointF computeScrollVectorForPosition(int i) {
            LayoutManager layoutManager = this.mLayoutManager;
            if (layoutManager instanceof ScrollVectorProvider) {
                return ((ScrollVectorProvider) layoutManager).computeScrollVectorForPosition(i);
            }
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("You should override computeScrollVectorForPosition when the LayoutManager does not implement ");
            outline13.append(ScrollVectorProvider.class.getCanonicalName());
            Log.w("RecyclerView", outline13.toString());
            return null;
        }

        public void onAnimation(int i, int i2) {
            PointF computeScrollVectorForPosition;
            RecyclerView recyclerView = this.mRecyclerView;
            int i3 = -1;
            if (this.mTargetPosition == -1 || recyclerView == null) {
                stop();
            }
            if (this.mPendingInitialRun && this.mTargetView == null && this.mLayoutManager != null && (computeScrollVectorForPosition = computeScrollVectorForPosition(this.mTargetPosition)) != null) {
                float f = computeScrollVectorForPosition.x;
                if (!(f == 0.0f && computeScrollVectorForPosition.y == 0.0f)) {
                    recyclerView.scrollStep((int) Math.signum(f), (int) Math.signum(computeScrollVectorForPosition.y), null);
                }
            }
            boolean z = false;
            this.mPendingInitialRun = false;
            View view = this.mTargetView;
            if (view != null) {
                Objects.requireNonNull(this.mRecyclerView);
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (childViewHolderInt != null) {
                    i3 = childViewHolderInt.getLayoutPosition();
                }
                if (i3 == this.mTargetPosition) {
                    onTargetFound(this.mTargetView, recyclerView.mState, this.mRecyclingAction);
                    this.mRecyclingAction.runIfNecessary(recyclerView);
                    stop();
                } else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    this.mTargetView = null;
                }
            }
            if (this.mRunning) {
                State state = recyclerView.mState;
                Action action = this.mRecyclingAction;
                LinearSmoothScroller linearSmoothScroller = (LinearSmoothScroller) this;
                if (linearSmoothScroller.mRecyclerView.mLayout.getChildCount() == 0) {
                    linearSmoothScroller.stop();
                } else {
                    int i4 = linearSmoothScroller.mInterimTargetDx;
                    int i5 = i4 - i;
                    if (i4 * i5 <= 0) {
                        i5 = 0;
                    }
                    linearSmoothScroller.mInterimTargetDx = i5;
                    int i6 = linearSmoothScroller.mInterimTargetDy;
                    int i7 = i6 - i2;
                    if (i6 * i7 <= 0) {
                        i7 = 0;
                    }
                    linearSmoothScroller.mInterimTargetDy = i7;
                    if (i5 == 0 && i7 == 0) {
                        PointF computeScrollVectorForPosition2 = linearSmoothScroller.computeScrollVectorForPosition(linearSmoothScroller.mTargetPosition);
                        if (computeScrollVectorForPosition2 != null) {
                            float f2 = computeScrollVectorForPosition2.x;
                            if (!(f2 == 0.0f && computeScrollVectorForPosition2.y == 0.0f)) {
                                float f3 = computeScrollVectorForPosition2.y;
                                float sqrt = (float) Math.sqrt((f3 * f3) + (f2 * f2));
                                float f4 = computeScrollVectorForPosition2.x / sqrt;
                                computeScrollVectorForPosition2.x = f4;
                                float f5 = computeScrollVectorForPosition2.y / sqrt;
                                computeScrollVectorForPosition2.y = f5;
                                linearSmoothScroller.mTargetVector = computeScrollVectorForPosition2;
                                linearSmoothScroller.mInterimTargetDx = (int) (f4 * 10000.0f);
                                linearSmoothScroller.mInterimTargetDy = (int) (f5 * 10000.0f);
                                action.update((int) (linearSmoothScroller.mInterimTargetDx * 1.2f), (int) (linearSmoothScroller.mInterimTargetDy * 1.2f), (int) (linearSmoothScroller.calculateTimeForScrolling(10000) * 1.2f), linearSmoothScroller.mLinearInterpolator);
                            }
                        }
                        action.mJumpToPosition = linearSmoothScroller.mTargetPosition;
                        linearSmoothScroller.stop();
                    }
                }
                Action action2 = this.mRecyclingAction;
                if (action2.mJumpToPosition >= 0) {
                    z = true;
                }
                action2.runIfNecessary(recyclerView);
                if (z && this.mRunning) {
                    this.mPendingInitialRun = true;
                    recyclerView.mViewFlinger.postOnAnimation();
                }
            }
        }

        public abstract void onTargetFound(View view, State state, Action action);

        public final void stop() {
            if (this.mRunning) {
                this.mRunning = false;
                LinearSmoothScroller linearSmoothScroller = (LinearSmoothScroller) this;
                linearSmoothScroller.mInterimTargetDy = 0;
                linearSmoothScroller.mInterimTargetDx = 0;
                linearSmoothScroller.mTargetVector = null;
                this.mRecyclerView.mState.mTargetPosition = -1;
                this.mTargetView = null;
                this.mTargetPosition = -1;
                this.mPendingInitialRun = false;
                LayoutManager layoutManager = this.mLayoutManager;
                if (layoutManager.mSmoothScroller == this) {
                    layoutManager.mSmoothScroller = null;
                }
                this.mLayoutManager = null;
                this.mRecyclerView = null;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class State {
        public long mFocusedItemId;
        public int mFocusedItemPosition;
        public int mFocusedSubChildId;
        public int mTargetPosition = -1;
        public int mPreviousLayoutItemCount = 0;
        public int mDeletedInvisibleItemCountSincePreviousLayout = 0;
        public int mLayoutStep = 1;
        public int mItemCount = 0;
        public boolean mStructureChanged = false;
        public boolean mInPreLayout = false;
        public boolean mTrackOldChangeHolders = false;
        public boolean mIsMeasuring = false;
        public boolean mRunSimpleAnimations = false;
        public boolean mRunPredictiveAnimations = false;

        public void assertLayoutStep(int i) {
            if ((this.mLayoutStep & i) == 0) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Layout state should be one of ");
                outline13.append(Integer.toBinaryString(i));
                outline13.append(" but it is ");
                outline13.append(Integer.toBinaryString(this.mLayoutStep));
                throw new IllegalStateException(outline13.toString());
            }
        }

        public int getItemCount() {
            if (this.mInPreLayout) {
                return this.mPreviousLayoutItemCount - this.mDeletedInvisibleItemCountSincePreviousLayout;
            }
            return this.mItemCount;
        }

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("State{mTargetPosition=");
            outline13.append(this.mTargetPosition);
            outline13.append(", mData=");
            outline13.append((Object) null);
            outline13.append(", mItemCount=");
            outline13.append(this.mItemCount);
            outline13.append(", mIsMeasuring=");
            outline13.append(this.mIsMeasuring);
            outline13.append(", mPreviousLayoutItemCount=");
            outline13.append(this.mPreviousLayoutItemCount);
            outline13.append(", mDeletedInvisibleItemCountSincePreviousLayout=");
            outline13.append(this.mDeletedInvisibleItemCountSincePreviousLayout);
            outline13.append(", mStructureChanged=");
            outline13.append(this.mStructureChanged);
            outline13.append(", mInPreLayout=");
            outline13.append(this.mInPreLayout);
            outline13.append(", mRunSimpleAnimations=");
            outline13.append(this.mRunSimpleAnimations);
            outline13.append(", mRunPredictiveAnimations=");
            outline13.append(this.mRunPredictiveAnimations);
            outline13.append('}');
            return outline13.toString();
        }
    }

    /* loaded from: classes.dex */
    public static abstract class ViewCacheExtension {
    }

    /* loaded from: classes.dex */
    public class ViewFlinger implements Runnable {
        public Interpolator mInterpolator;
        public int mLastFlingX;
        public int mLastFlingY;
        public OverScroller mOverScroller;
        public boolean mEatRunOnAnimationRequest = false;
        public boolean mReSchedulePostAnimationCallback = false;

        public ViewFlinger() {
            Interpolator interpolator = RecyclerView.sQuinticInterpolator;
            this.mInterpolator = interpolator;
            this.mOverScroller = new OverScroller(RecyclerView.this.getContext(), interpolator);
        }

        public void postOnAnimation() {
            if (this.mEatRunOnAnimationRequest) {
                this.mReSchedulePostAnimationCallback = true;
                return;
            }
            RecyclerView.this.removeCallbacks(this);
            RecyclerView recyclerView = RecyclerView.this;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            recyclerView.postOnAnimation(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            int i;
            int i2;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.mLayout == null) {
                stop();
                return;
            }
            this.mReSchedulePostAnimationCallback = false;
            this.mEatRunOnAnimationRequest = true;
            recyclerView.consumePendingUpdateOperations();
            OverScroller overScroller = this.mOverScroller;
            if (overScroller.computeScrollOffset()) {
                int currX = overScroller.getCurrX();
                int currY = overScroller.getCurrY();
                int i3 = currX - this.mLastFlingX;
                int i4 = currY - this.mLastFlingY;
                this.mLastFlingX = currX;
                this.mLastFlingY = currY;
                RecyclerView recyclerView2 = RecyclerView.this;
                int[] iArr = recyclerView2.mReusableIntPair;
                iArr[0] = 0;
                iArr[1] = 0;
                if (recyclerView2.dispatchNestedPreScroll(i3, i4, iArr, null, 1)) {
                    int[] iArr2 = RecyclerView.this.mReusableIntPair;
                    i3 -= iArr2[0];
                    i4 -= iArr2[1];
                }
                if (RecyclerView.this.getOverScrollMode() != 2) {
                    RecyclerView.this.considerReleasingGlowsOnScroll(i3, i4);
                }
                RecyclerView recyclerView3 = RecyclerView.this;
                if (recyclerView3.mAdapter != null) {
                    int[] iArr3 = recyclerView3.mReusableIntPair;
                    iArr3[0] = 0;
                    iArr3[1] = 0;
                    recyclerView3.scrollStep(i3, i4, iArr3);
                    RecyclerView recyclerView4 = RecyclerView.this;
                    int[] iArr4 = recyclerView4.mReusableIntPair;
                    i = iArr4[0];
                    i2 = iArr4[1];
                    i3 -= i;
                    i4 -= i2;
                    SmoothScroller smoothScroller = recyclerView4.mLayout.mSmoothScroller;
                    if (smoothScroller != null && !smoothScroller.mPendingInitialRun && smoothScroller.mRunning) {
                        int itemCount = recyclerView4.mState.getItemCount();
                        if (itemCount == 0) {
                            smoothScroller.stop();
                        } else if (smoothScroller.mTargetPosition >= itemCount) {
                            smoothScroller.mTargetPosition = itemCount - 1;
                            smoothScroller.onAnimation(i, i2);
                        } else {
                            smoothScroller.onAnimation(i, i2);
                        }
                    }
                } else {
                    i2 = 0;
                    i = 0;
                }
                if (!RecyclerView.this.mItemDecorations.isEmpty()) {
                    RecyclerView.this.invalidate();
                }
                RecyclerView recyclerView5 = RecyclerView.this;
                int[] iArr5 = recyclerView5.mReusableIntPair;
                iArr5[0] = 0;
                iArr5[1] = 0;
                recyclerView5.dispatchNestedScroll(i, i2, i3, i4, null, 1, iArr5);
                RecyclerView recyclerView6 = RecyclerView.this;
                int[] iArr6 = recyclerView6.mReusableIntPair;
                int i5 = i3 - iArr6[0];
                int i6 = i4 - iArr6[1];
                if (!(i == 0 && i2 == 0)) {
                    recyclerView6.dispatchOnScrolled(i, i2);
                }
                if (!RecyclerView.this.awakenScrollBars()) {
                    RecyclerView.this.invalidate();
                }
                boolean z = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i5 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i6 != 0));
                RecyclerView recyclerView7 = RecyclerView.this;
                SmoothScroller smoothScroller2 = recyclerView7.mLayout.mSmoothScroller;
                if ((smoothScroller2 != null && smoothScroller2.mPendingInitialRun) || !z) {
                    postOnAnimation();
                    RecyclerView recyclerView8 = RecyclerView.this;
                    GapWorker gapWorker = recyclerView8.mGapWorker;
                    if (gapWorker != null) {
                        gapWorker.postFromTraversal(recyclerView8, i, i2);
                    }
                } else {
                    if (recyclerView7.getOverScrollMode() != 2) {
                        int currVelocity = (int) overScroller.getCurrVelocity();
                        int i7 = i5 < 0 ? -currVelocity : i5 > 0 ? currVelocity : 0;
                        if (i6 < 0) {
                            currVelocity = -currVelocity;
                        } else if (i6 <= 0) {
                            currVelocity = 0;
                        }
                        RecyclerView recyclerView9 = RecyclerView.this;
                        Objects.requireNonNull(recyclerView9);
                        if (i7 < 0) {
                            recyclerView9.ensureLeftGlow();
                            if (recyclerView9.mLeftGlow.isFinished()) {
                                recyclerView9.mLeftGlow.onAbsorb(-i7);
                            }
                        } else if (i7 > 0) {
                            recyclerView9.ensureRightGlow();
                            if (recyclerView9.mRightGlow.isFinished()) {
                                recyclerView9.mRightGlow.onAbsorb(i7);
                            }
                        }
                        if (currVelocity < 0) {
                            recyclerView9.ensureTopGlow();
                            if (recyclerView9.mTopGlow.isFinished()) {
                                recyclerView9.mTopGlow.onAbsorb(-currVelocity);
                            }
                        } else if (currVelocity > 0) {
                            recyclerView9.ensureBottomGlow();
                            if (recyclerView9.mBottomGlow.isFinished()) {
                                recyclerView9.mBottomGlow.onAbsorb(currVelocity);
                            }
                        }
                        if (!(i7 == 0 && currVelocity == 0)) {
                            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                            recyclerView9.postInvalidateOnAnimation();
                        }
                    }
                    if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                        GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl = RecyclerView.this.mPrefetchRegistry;
                        int[] iArr7 = layoutPrefetchRegistryImpl.mPrefetchArray;
                        if (iArr7 != null) {
                            Arrays.fill(iArr7, -1);
                        }
                        layoutPrefetchRegistryImpl.mCount = 0;
                    }
                }
            }
            SmoothScroller smoothScroller3 = RecyclerView.this.mLayout.mSmoothScroller;
            if (smoothScroller3 != null && smoothScroller3.mPendingInitialRun) {
                smoothScroller3.onAnimation(0, 0);
            }
            this.mEatRunOnAnimationRequest = false;
            if (this.mReSchedulePostAnimationCallback) {
                RecyclerView.this.removeCallbacks(this);
                RecyclerView recyclerView10 = RecyclerView.this;
                AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
                recyclerView10.postOnAnimation(this);
                return;
            }
            RecyclerView.this.setScrollState(0);
            RecyclerView.this.stopNestedScroll(1);
        }

        public void smoothScrollBy(int i, int i2, int i3, Interpolator interpolator) {
            if (i3 == Integer.MIN_VALUE) {
                int abs = Math.abs(i);
                int abs2 = Math.abs(i2);
                boolean z = abs > abs2;
                RecyclerView recyclerView = RecyclerView.this;
                int width = z ? recyclerView.getWidth() : recyclerView.getHeight();
                if (!z) {
                    abs = abs2;
                }
                i3 = Math.min((int) (((abs / width) + 1.0f) * 300.0f), 2000);
            }
            if (interpolator == null) {
                interpolator = RecyclerView.sQuinticInterpolator;
            }
            if (this.mInterpolator != interpolator) {
                this.mInterpolator = interpolator;
                this.mOverScroller = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            RecyclerView.this.setScrollState(2);
            this.mOverScroller.startScroll(0, 0, i, i2, i3);
            if (Build.VERSION.SDK_INT < 23) {
                this.mOverScroller.computeScrollOffset();
            }
            postOnAnimation();
        }

        public void stop() {
            RecyclerView.this.removeCallbacks(this);
            this.mOverScroller.abortAnimation();
        }
    }

    /* loaded from: classes.dex */
    public static abstract class ViewHolder {
        public static final List<Object> FULLUPDATE_PAYLOADS = Collections.emptyList();
        public final View itemView;
        public Adapter<? extends ViewHolder> mBindingAdapter;
        public int mFlags;
        public WeakReference<RecyclerView> mNestedRecyclerView;
        public RecyclerView mOwnerRecyclerView;
        public int mPosition = -1;
        public int mOldPosition = -1;
        public long mItemId = -1;
        public int mItemViewType = -1;
        public int mPreLayoutPosition = -1;
        public ViewHolder mShadowedHolder = null;
        public ViewHolder mShadowingHolder = null;
        public List<Object> mPayloads = null;
        public List<Object> mUnmodifiedPayloads = null;
        public int mIsRecyclableCount = 0;
        public Recycler mScrapContainer = null;
        public boolean mInChangeScrap = false;
        public int mWasImportantForAccessibilityBeforeHidden = 0;
        public int mPendingAccessibilityState = -1;

        public ViewHolder(View view) {
            if (view != null) {
                this.itemView = view;
                return;
            }
            throw new IllegalArgumentException("itemView may not be null");
        }

        public void addChangePayload(Object obj) {
            if (obj == null) {
                addFlags(1024);
            } else if ((1024 & this.mFlags) == 0) {
                if (this.mPayloads == null) {
                    ArrayList arrayList = new ArrayList();
                    this.mPayloads = arrayList;
                    this.mUnmodifiedPayloads = Collections.unmodifiableList(arrayList);
                }
                this.mPayloads.add(obj);
            }
        }

        public void addFlags(int i) {
            this.mFlags = i | this.mFlags;
        }

        public void clearOldPosition() {
            this.mOldPosition = -1;
            this.mPreLayoutPosition = -1;
        }

        public void clearReturnedFromScrapFlag() {
            this.mFlags &= -33;
        }

        public final int getLayoutPosition() {
            int i = this.mPreLayoutPosition;
            return i == -1 ? this.mPosition : i;
        }

        public List<Object> getUnmodifiedPayloads() {
            if ((this.mFlags & 1024) != 0) {
                return FULLUPDATE_PAYLOADS;
            }
            List<Object> list = this.mPayloads;
            if (list == null || list.size() == 0) {
                return FULLUPDATE_PAYLOADS;
            }
            return this.mUnmodifiedPayloads;
        }

        public boolean hasAnyOfTheFlags(int i) {
            return (i & this.mFlags) != 0;
        }

        public boolean isAttachedToTransitionOverlay() {
            return (this.itemView.getParent() == null || this.itemView.getParent() == this.mOwnerRecyclerView) ? false : true;
        }

        public boolean isBound() {
            return (this.mFlags & 1) != 0;
        }

        public boolean isInvalid() {
            return (this.mFlags & 4) != 0;
        }

        public final boolean isRecyclable() {
            if ((this.mFlags & 16) == 0) {
                View view = this.itemView;
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                if (!view.hasTransientState()) {
                    return true;
                }
            }
            return false;
        }

        public boolean isRemoved() {
            return (this.mFlags & 8) != 0;
        }

        public boolean isScrap() {
            return this.mScrapContainer != null;
        }

        public boolean isTmpDetached() {
            return (this.mFlags & 256) != 0;
        }

        public boolean isUpdated() {
            return (this.mFlags & 2) != 0;
        }

        public void offsetPosition(int i, boolean z) {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
            if (this.mPreLayoutPosition == -1) {
                this.mPreLayoutPosition = this.mPosition;
            }
            if (z) {
                this.mPreLayoutPosition += i;
            }
            this.mPosition += i;
            if (this.itemView.getLayoutParams() != null) {
                ((LayoutParams) this.itemView.getLayoutParams()).mInsetsDirty = true;
            }
        }

        public void resetInternal() {
            this.mFlags = 0;
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1L;
            this.mPreLayoutPosition = -1;
            this.mIsRecyclableCount = 0;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            List<Object> list = this.mPayloads;
            if (list != null) {
                list.clear();
            }
            this.mFlags &= -1025;
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            this.mPendingAccessibilityState = -1;
            RecyclerView.clearNestedRecyclerViewIfNotNested(this);
        }

        public void setFlags(int i, int i2) {
            this.mFlags = (i & i2) | (this.mFlags & (~i2));
        }

        public final void setIsRecyclable(boolean z) {
            int i = this.mIsRecyclableCount;
            int i2 = z ? i - 1 : i + 1;
            this.mIsRecyclableCount = i2;
            if (i2 < 0) {
                this.mIsRecyclableCount = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
            } else if (!z && i2 == 1) {
                this.mFlags |= 16;
            } else if (z && i2 == 0) {
                this.mFlags &= -17;
            }
        }

        public boolean shouldIgnore() {
            return (this.mFlags & 128) != 0;
        }

        public String toString() {
            String simpleName = getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName();
            StringBuilder sb = new StringBuilder(simpleName + "{" + Integer.toHexString(hashCode()) + " position=" + this.mPosition + " id=" + this.mItemId + ", oldPos=" + this.mOldPosition + ", pLpos:" + this.mPreLayoutPosition);
            if (isScrap()) {
                sb.append(" scrap ");
                sb.append(this.mInChangeScrap ? "[changeScrap]" : "[attachedScrap]");
            }
            if (isInvalid()) {
                sb.append(" invalid");
            }
            if (!isBound()) {
                sb.append(" unbound");
            }
            boolean z = false;
            if ((this.mFlags & 2) != 0) {
                sb.append(" update");
            }
            if (isRemoved()) {
                sb.append(" removed");
            }
            if (shouldIgnore()) {
                sb.append(" ignored");
            }
            if (isTmpDetached()) {
                sb.append(" tmpDetached");
            }
            if (!isRecyclable()) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13(" not recyclable(");
                outline13.append(this.mIsRecyclableCount);
                outline13.append(")");
                sb.append(outline13.toString());
            }
            if ((this.mFlags & 512) != 0 || isInvalid()) {
                z = true;
            }
            if (z) {
                sb.append(" undefined adapter position");
            }
            if (this.itemView.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        public boolean wasReturnedFromScrap() {
            return (this.mFlags & 32) != 0;
        }
    }

    static {
        ALLOW_SIZE_IN_UNSPECIFIED_SPEC = Build.VERSION.SDK_INT >= 23;
        POST_UPDATES_ON_ANIMATION = true;
        ALLOW_THREAD_GAP_WORK = true;
        Class<?> cls = Integer.TYPE;
        LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class, cls, cls};
        sQuinticInterpolator = new Interpolator() { // from class: androidx.recyclerview.widget.RecyclerView.3
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f) {
                float f2 = f - 1.0f;
                return (f2 * f2 * f2 * f2 * f2) + 1.0f;
            }
        };
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.recyclerViewStyle);
    }

    public static void clearNestedRecyclerViewIfNotNested(ViewHolder viewHolder) {
        WeakReference<RecyclerView> weakReference = viewHolder.mNestedRecyclerView;
        if (weakReference != null) {
            RecyclerView recyclerView = weakReference.get();
            while (recyclerView != null) {
                if (recyclerView != viewHolder.itemView) {
                    ViewParent parent = recyclerView.getParent();
                    recyclerView = parent instanceof View ? (View) parent : null;
                } else {
                    return;
                }
            }
            viewHolder.mNestedRecyclerView = null;
        }
    }

    public static RecyclerView findNestedRecyclerView(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            RecyclerView findNestedRecyclerView = findNestedRecyclerView(viewGroup.getChildAt(i));
            if (findNestedRecyclerView != null) {
                return findNestedRecyclerView;
            }
        }
        return null;
    }

    public static ViewHolder getChildViewHolderInt(View view) {
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).mViewHolder;
    }

    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (this.mScrollingChildHelper == null) {
            this.mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return this.mScrollingChildHelper;
    }

    public final void addAnimatingView(ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        boolean z = view.getParent() == this;
        this.mRecycler.unscrapView(getChildViewHolder(view));
        if (viewHolder.isTmpDetached()) {
            this.mChildHelper.attachViewToParent(view, -1, view.getLayoutParams(), true);
        } else if (!z) {
            this.mChildHelper.addView(view, -1, true);
        } else {
            ChildHelper childHelper = this.mChildHelper;
            int indexOfChild = RecyclerView.this.indexOfChild(view);
            if (indexOfChild >= 0) {
                childHelper.mBucket.set(indexOfChild);
                childHelper.hideViewInternal(view);
                return;
            }
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null || !layoutManager.onAddFocusables()) {
            super.addFocusables(arrayList, i, i2);
        }
    }

    public void addItemDecoration(ItemDecoration itemDecoration) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(false);
        }
        this.mItemDecorations.add(itemDecoration);
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void addOnScrollListener(OnScrollListener onScrollListener) {
        if (this.mScrollListeners == null) {
            this.mScrollListeners = new ArrayList();
        }
        this.mScrollListeners.add(onScrollListener);
    }

    public void assertNotInLayoutOrScroll(String str) {
        if (isComputingLayout()) {
            if (str == null) {
                throw new IllegalStateException(GeneratedOutlineSupport.outline2(this, GeneratedOutlineSupport.outline13("Cannot call this method while RecyclerView is computing a layout or scrolling")));
            }
            throw new IllegalStateException(str);
        } else if (this.mDispatchScrollCounter > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(GeneratedOutlineSupport.outline2(this, GeneratedOutlineSupport.outline13(HttpUrl.FRAGMENT_ENCODE_SET))));
        }
    }

    public final void cancelScroll() {
        resetScroll();
        setScrollState(0);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && this.mLayout.checkLayoutParams((LayoutParams) layoutParams);
    }

    public void clearOldPositions() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < unfilteredChildCount; i++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.clearOldPosition();
            }
        }
        Recycler recycler = this.mRecycler;
        int size = recycler.mCachedViews.size();
        for (int i2 = 0; i2 < size; i2++) {
            recycler.mCachedViews.get(i2).clearOldPosition();
        }
        int size2 = recycler.mAttachedScrap.size();
        for (int i3 = 0; i3 < size2; i3++) {
            recycler.mAttachedScrap.get(i3).clearOldPosition();
        }
        ArrayList<ViewHolder> arrayList = recycler.mChangedScrap;
        if (arrayList != null) {
            int size3 = arrayList.size();
            for (int i4 = 0; i4 < size3; i4++) {
                recycler.mChangedScrap.get(i4).clearOldPosition();
            }
        }
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollExtent(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollOffset(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollRange(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollExtent(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollOffset(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollRange(this.mState);
        }
        return 0;
    }

    public void considerReleasingGlowsOnScroll(int i, int i2) {
        boolean z;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect == null || edgeEffect.isFinished() || i <= 0) {
            z = false;
        } else {
            this.mLeftGlow.onRelease();
            z = this.mLeftGlow.isFinished();
        }
        EdgeEffect edgeEffect2 = this.mRightGlow;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i < 0) {
            this.mRightGlow.onRelease();
            z |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i2 > 0) {
            this.mTopGlow.onRelease();
            z |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i2 < 0) {
            this.mBottomGlow.onRelease();
            z |= this.mBottomGlow.isFinished();
        }
        if (z) {
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            postInvalidateOnAnimation();
        }
    }

    public void consumePendingUpdateOperations() {
        if (!this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout) {
            int i = TraceCompat.$r8$clinit;
            Trace.beginSection("RV FullInvalidate");
            dispatchLayout();
            Trace.endSection();
        } else if (this.mAdapterHelper.hasPendingUpdates()) {
            AdapterHelper adapterHelper = this.mAdapterHelper;
            int i2 = adapterHelper.mExistingUpdateTypes;
            boolean z = false;
            if ((i2 & 4) != 0) {
                if (!((i2 & 11) != 0)) {
                    int i3 = TraceCompat.$r8$clinit;
                    Trace.beginSection("RV PartialInvalidate");
                    startInterceptRequestLayout();
                    onEnterLayoutOrScroll();
                    this.mAdapterHelper.preProcess();
                    if (!this.mLayoutWasDefered) {
                        int childCount = this.mChildHelper.getChildCount();
                        int i4 = 0;
                        while (true) {
                            if (i4 < childCount) {
                                ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i4));
                                if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.isUpdated()) {
                                    z = true;
                                    break;
                                }
                                i4++;
                            } else {
                                break;
                            }
                        }
                        if (z) {
                            dispatchLayout();
                        } else {
                            this.mAdapterHelper.consumePostponedUpdates();
                        }
                    }
                    stopInterceptRequestLayout(true);
                    onExitLayoutOrScroll(true);
                    Trace.endSection();
                    return;
                }
            }
            if (adapterHelper.hasPendingUpdates()) {
                int i5 = TraceCompat.$r8$clinit;
                Trace.beginSection("RV FullInvalidate");
                dispatchLayout();
                Trace.endSection();
            }
        }
    }

    public void defaultOnMeasure(int i, int i2) {
        int paddingRight = getPaddingRight() + getPaddingLeft();
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        setMeasuredDimension(LayoutManager.chooseSize(i, paddingRight, getMinimumWidth()), LayoutManager.chooseSize(i2, getPaddingBottom() + getPaddingTop(), getMinimumHeight()));
    }

    public void dispatchChildDetached(View view) {
        ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        onChildDetachedFromWindow();
        Adapter adapter = this.mAdapter;
        if (!(adapter == null || childViewHolderInt == null)) {
            Objects.requireNonNull(adapter);
        }
        List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mOnChildAttachStateListeners.get(size).onChildViewDetachedFromWindow(view);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:145:0x0350, code lost:
        if (r15.mChildHelper.isHidden(getFocusedChild()) == false) goto L_0x0413;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void dispatchLayout() {
        /*
            Method dump skipped, instructions count: 1053
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.dispatchLayout():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x00a1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0085 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void dispatchLayoutStep1() {
        /*
            Method dump skipped, instructions count: 491
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.dispatchLayoutStep1():void");
    }

    public final void dispatchLayoutStep2() {
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        this.mState.assertLayoutStep(6);
        this.mAdapterHelper.consumeUpdatesInOnePass();
        this.mState.mItemCount = this.mAdapter.getItemCount();
        this.mState.mDeletedInvisibleItemCountSincePreviousLayout = 0;
        if (this.mPendingSavedState != null) {
            Adapter adapter = this.mAdapter;
            int $enumboxing$ordinal = SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(adapter.mStateRestorationPolicy);
            if ($enumboxing$ordinal == 1 ? adapter.getItemCount() > 0 : $enumboxing$ordinal != 2) {
                Parcelable parcelable = this.mPendingSavedState.mLayoutState;
                if (parcelable != null) {
                    this.mLayout.onRestoreInstanceState(parcelable);
                }
                this.mPendingSavedState = null;
            }
        }
        State state = this.mState;
        state.mInPreLayout = false;
        this.mLayout.onLayoutChildren(this.mRecycler, state);
        State state2 = this.mState;
        state2.mStructureChanged = false;
        state2.mRunSimpleAnimations = state2.mRunSimpleAnimations && this.mItemAnimator != null;
        state2.mLayoutStep = 4;
        onExitLayoutOrScroll(true);
        stopInterceptRequestLayout(false);
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return getScrollingChildHelper().dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f, float f2) {
        return getScrollingChildHelper().dispatchNestedPreFling(f, f2);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().dispatchNestedPreScroll(i, i2, iArr, iArr2, 0);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return getScrollingChildHelper().dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    public void dispatchOnScrolled(int i, int i2) {
        this.mDispatchScrollCounter++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i, scrollY - i2);
        onScrolled();
        OnScrollListener onScrollListener = this.mScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScrolled(this, i, i2);
        }
        List<OnScrollListener> list = this.mScrollListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mScrollListeners.get(size).onScrolled(this, i, i2);
            }
        }
        this.mDispatchScrollCounter--;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z;
        super.draw(canvas);
        int size = this.mItemDecorations.size();
        boolean z2 = false;
        for (int i = 0; i < size; i++) {
            this.mItemDecorations.get(i).onDrawOver(canvas, this, this.mState);
        }
        EdgeEffect edgeEffect = this.mLeftGlow;
        boolean z3 = true;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z = false;
        } else {
            int save = canvas.save();
            int paddingBottom = this.mClipToPadding ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.mLeftGlow;
            z = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(save);
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int save2 = canvas.save();
            if (this.mClipToPadding) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.mTopGlow;
            z |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(save2);
        }
        EdgeEffect edgeEffect5 = this.mRightGlow;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int save3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.mClipToPadding ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(paddingTop, -width);
            EdgeEffect edgeEffect6 = this.mRightGlow;
            z |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(save3);
        }
        EdgeEffect edgeEffect7 = this.mBottomGlow;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int save4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.mClipToPadding) {
                canvas.translate(getPaddingRight() + (-getWidth()), getPaddingBottom() + (-getHeight()));
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.mBottomGlow;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z2 = true;
            }
            z |= z2;
            canvas.restoreToCount(save4);
        }
        if (z || this.mItemAnimator == null || this.mItemDecorations.size() <= 0 || !this.mItemAnimator.isRunning()) {
            z3 = z;
        }
        if (z3) {
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            postInvalidateOnAnimation();
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        return super.drawChild(canvas, view, j);
    }

    public void ensureBottomGlow() {
        if (this.mBottomGlow == null) {
            EdgeEffect createEdgeEffect = this.mEdgeEffectFactory.createEdgeEffect(this);
            this.mBottomGlow = createEdgeEffect;
            if (this.mClipToPadding) {
                createEdgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                createEdgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    public void ensureLeftGlow() {
        if (this.mLeftGlow == null) {
            EdgeEffect createEdgeEffect = this.mEdgeEffectFactory.createEdgeEffect(this);
            this.mLeftGlow = createEdgeEffect;
            if (this.mClipToPadding) {
                createEdgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                createEdgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    public void ensureRightGlow() {
        if (this.mRightGlow == null) {
            EdgeEffect createEdgeEffect = this.mEdgeEffectFactory.createEdgeEffect(this);
            this.mRightGlow = createEdgeEffect;
            if (this.mClipToPadding) {
                createEdgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                createEdgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    public void ensureTopGlow() {
        if (this.mTopGlow == null) {
            EdgeEffect createEdgeEffect = this.mEdgeEffectFactory.createEdgeEffect(this);
            this.mTopGlow = createEdgeEffect;
            if (this.mClipToPadding) {
                createEdgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                createEdgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    public String exceptionLabel() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13(" ");
        outline13.append(super.toString());
        outline13.append(", adapter:");
        outline13.append(this.mAdapter);
        outline13.append(", layout:");
        outline13.append(this.mLayout);
        outline13.append(", context:");
        outline13.append(getContext());
        return outline13.toString();
    }

    public final void fillRemainingScrollValues(State state) {
        if (getScrollState() == 2) {
            OverScroller overScroller = this.mViewFlinger.mOverScroller;
            overScroller.getFinalX();
            overScroller.getCurrX();
            Objects.requireNonNull(state);
            overScroller.getFinalY();
            overScroller.getCurrY();
            return;
        }
        Objects.requireNonNull(state);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:?, code lost:
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View findContainingItemView(android.view.View r3) {
        /*
            r2 = this;
            android.view.ViewParent r0 = r3.getParent()
        L_0x0004:
            if (r0 == 0) goto L_0x0014
            if (r0 == r2) goto L_0x0014
            boolean r1 = r0 instanceof android.view.View
            if (r1 == 0) goto L_0x0014
            r3 = r0
            android.view.View r3 = (android.view.View) r3
            android.view.ViewParent r0 = r3.getParent()
            goto L_0x0004
        L_0x0014:
            if (r0 != r2) goto L_0x0017
            goto L_0x0018
        L_0x0017:
            r3 = 0
        L_0x0018:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.findContainingItemView(android.view.View):android.view.View");
    }

    public final boolean findInterceptingOnItemTouchListener(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int size = this.mOnItemTouchListeners.size();
        for (int i = 0; i < size; i++) {
            OnItemTouchListener onItemTouchListener = this.mOnItemTouchListeners.get(i);
            if (onItemTouchListener.onInterceptTouchEvent(this, motionEvent) && action != 3) {
                this.mInterceptingOnItemTouchListener = onItemTouchListener;
                return true;
            }
        }
        return false;
    }

    public final void findMinMaxChildLayoutPositions(int[] iArr) {
        int childCount = this.mChildHelper.getChildCount();
        if (childCount == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        for (int i3 = 0; i3 < childCount; i3++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i3));
            if (!childViewHolderInt.shouldIgnore()) {
                int layoutPosition = childViewHolderInt.getLayoutPosition();
                if (layoutPosition < i) {
                    i = layoutPosition;
                }
                if (layoutPosition > i2) {
                    i2 = layoutPosition;
                }
            }
        }
        iArr[0] = i;
        iArr[1] = i2;
    }

    public ViewHolder findViewHolderForAdapterPosition(int i) {
        ViewHolder viewHolder = null;
        if (this.mDataSetHasChangedAfterLayout) {
            return null;
        }
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i2 = 0; i2 < unfilteredChildCount; i2++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i2));
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && getAdapterPositionInRecyclerView(childViewHolderInt) == i) {
                if (!this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                    return childViewHolderInt;
                }
                viewHolder = childViewHolderInt;
            }
        }
        return viewHolder;
    }

    /* JADX WARN: Code restructure failed: missing block: B:115:0x016b, code lost:
        if (r3 > 0) goto L_0x019f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0185, code lost:
        if (r6 > 0) goto L_0x019f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0188, code lost:
        if (r3 < 0) goto L_0x019f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x018b, code lost:
        if (r6 < 0) goto L_0x019f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x0194, code lost:
        if ((r6 * r1) <= 0) goto L_0x01a1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x019d, code lost:
        if ((r6 * r1) >= 0) goto L_0x01a1;
     */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:134:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0075  */
    @Override // android.view.ViewGroup, android.view.ViewParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View focusSearch(android.view.View r14, int r15) {
        /*
            Method dump skipped, instructions count: 426
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.focusSearch(android.view.View, int):android.view.View");
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.generateDefaultLayoutParams();
        }
        throw new IllegalStateException(GeneratedOutlineSupport.outline2(this, GeneratedOutlineSupport.outline13("RecyclerView has no LayoutManager")));
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.generateLayoutParams(getContext(), attributeSet);
        }
        throw new IllegalStateException(GeneratedOutlineSupport.outline2(this, GeneratedOutlineSupport.outline13("RecyclerView has no LayoutManager")));
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public Adapter getAdapter() {
        return this.mAdapter;
    }

    public int getAdapterPositionInRecyclerView(ViewHolder viewHolder) {
        if (!viewHolder.hasAnyOfTheFlags(524) && viewHolder.isBound()) {
            AdapterHelper adapterHelper = this.mAdapterHelper;
            int i = viewHolder.mPosition;
            int size = adapterHelper.mPendingUpdates.size();
            for (int i2 = 0; i2 < size; i2++) {
                AdapterHelper.UpdateOp updateOp = adapterHelper.mPendingUpdates.get(i2);
                int i3 = updateOp.cmd;
                if (i3 != 1) {
                    if (i3 == 2) {
                        int i4 = updateOp.positionStart;
                        if (i4 <= i) {
                            int i5 = updateOp.itemCount;
                            if (i4 + i5 <= i) {
                                i -= i5;
                            }
                        } else {
                            continue;
                        }
                    } else if (i3 == 8) {
                        int i6 = updateOp.positionStart;
                        if (i6 == i) {
                            i = updateOp.itemCount;
                        } else {
                            if (i6 < i) {
                                i--;
                            }
                            if (updateOp.itemCount <= i) {
                                i++;
                            }
                        }
                    }
                } else if (updateOp.positionStart <= i) {
                    i += updateOp.itemCount;
                }
            }
            return i;
        }
        return -1;
    }

    @Override // android.view.View
    public int getBaseline() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            return super.getBaseline();
        }
        Objects.requireNonNull(layoutManager);
        return -1;
    }

    public long getChangedHolderKey(ViewHolder viewHolder) {
        if (this.mAdapter.mHasStableIds) {
            return viewHolder.mItemId;
        }
        return viewHolder.mPosition;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i, int i2) {
        ChildDrawingOrderCallback childDrawingOrderCallback = this.mChildDrawingOrderCallback;
        if (childDrawingOrderCallback == null) {
            return super.getChildDrawingOrder(i, i2);
        }
        return childDrawingOrderCallback.onGetChildDrawingOrder(i, i2);
    }

    public ViewHolder getChildViewHolder(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return getChildViewHolderInt(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.mClipToPadding;
    }

    public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate() {
        return this.mAccessibilityDelegate;
    }

    public EdgeEffectFactory getEdgeEffectFactory() {
        return this.mEdgeEffectFactory;
    }

    public ItemAnimator getItemAnimator() {
        return this.mItemAnimator;
    }

    public Rect getItemDecorInsetsForChild(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!layoutParams.mInsetsDirty) {
            return layoutParams.mDecorInsets;
        }
        if (this.mState.mInPreLayout && (layoutParams.isItemChanged() || layoutParams.mViewHolder.isInvalid())) {
            return layoutParams.mDecorInsets;
        }
        Rect rect = layoutParams.mDecorInsets;
        rect.set(0, 0, 0, 0);
        int size = this.mItemDecorations.size();
        for (int i = 0; i < size; i++) {
            this.mTempRect.set(0, 0, 0, 0);
            Rect rect2 = this.mTempRect;
            Objects.requireNonNull(this.mItemDecorations.get(i));
            ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
            rect2.set(0, 0, 0, 0);
            int i2 = rect.left;
            Rect rect3 = this.mTempRect;
            rect.left = i2 + rect3.left;
            rect.top += rect3.top;
            rect.right += rect3.right;
            rect.bottom += rect3.bottom;
        }
        layoutParams.mInsetsDirty = false;
        return rect;
    }

    public int getItemDecorationCount() {
        return this.mItemDecorations.size();
    }

    public LayoutManager getLayoutManager() {
        return this.mLayout;
    }

    public int getMaxFlingVelocity() {
        return this.mMaxFlingVelocity;
    }

    public int getMinFlingVelocity() {
        return this.mMinFlingVelocity;
    }

    public long getNanoTime() {
        if (ALLOW_THREAD_GAP_WORK) {
            return System.nanoTime();
        }
        return 0L;
    }

    public OnFlingListener getOnFlingListener() {
        return this.mOnFlingListener;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.mPreserveFocusAfterLayout;
    }

    public RecycledViewPool getRecycledViewPool() {
        return this.mRecycler.getRecycledViewPool();
    }

    public int getScrollState() {
        return this.mScrollState;
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().hasNestedScrollingParent(0);
    }

    public boolean hasPendingAdapterUpdates() {
        return !this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.hasPendingUpdates();
    }

    public void invalidateGlows() {
        this.mBottomGlow = null;
        this.mTopGlow = null;
        this.mRightGlow = null;
        this.mLeftGlow = null;
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.mIsAttached;
    }

    public boolean isComputingLayout() {
        return this.mLayoutOrScrollCounter > 0;
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.mLayoutSuppressed;
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().mIsNestedScrollingEnabled;
    }

    public void jumpToPositionForSmoothScroller(int i) {
        if (this.mLayout != null) {
            setScrollState(2);
            this.mLayout.scrollToPosition(i);
            awakenScrollBars();
        }
    }

    public void markItemDecorInsetsDirty() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < unfilteredChildCount; i++) {
            ((LayoutParams) this.mChildHelper.getUnfilteredChildAt(i).getLayoutParams()).mInsetsDirty = true;
        }
        Recycler recycler = this.mRecycler;
        int size = recycler.mCachedViews.size();
        for (int i2 = 0; i2 < size; i2++) {
            LayoutParams layoutParams = (LayoutParams) recycler.mCachedViews.get(i2).itemView.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.mInsetsDirty = true;
            }
        }
    }

    public void offsetPositionRecordsForRemove(int i, int i2, boolean z) {
        int i3 = i + i2;
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i4 = 0; i4 < unfilteredChildCount; i4++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i4));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                int i5 = childViewHolderInt.mPosition;
                if (i5 >= i3) {
                    childViewHolderInt.offsetPosition(-i2, z);
                    this.mState.mStructureChanged = true;
                } else if (i5 >= i) {
                    childViewHolderInt.addFlags(8);
                    childViewHolderInt.offsetPosition(-i2, z);
                    childViewHolderInt.mPosition = i - 1;
                    this.mState.mStructureChanged = true;
                }
            }
        }
        Recycler recycler = this.mRecycler;
        int size = recycler.mCachedViews.size();
        while (true) {
            size--;
            if (size >= 0) {
                ViewHolder viewHolder = recycler.mCachedViews.get(size);
                if (viewHolder != null) {
                    int i6 = viewHolder.mPosition;
                    if (i6 >= i3) {
                        viewHolder.offsetPosition(-i2, z);
                    } else if (i6 >= i) {
                        viewHolder.addFlags(8);
                        recycler.recycleCachedViewAt(size);
                    }
                }
            } else {
                requestLayout();
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mLayoutOrScrollCounter = 0;
        this.mIsAttached = true;
        this.mFirstLayoutComplete = this.mFirstLayoutComplete && !isLayoutRequested();
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.mIsAttachedToWindow = true;
            layoutManager.onAttachedToWindow();
        }
        this.mPostedAnimatorRunner = false;
        if (ALLOW_THREAD_GAP_WORK) {
            ThreadLocal<GapWorker> threadLocal = GapWorker.sGapWorker;
            GapWorker gapWorker = threadLocal.get();
            this.mGapWorker = gapWorker;
            if (gapWorker == null) {
                this.mGapWorker = new GapWorker();
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                Display display = getDisplay();
                float f = 60.0f;
                if (!isInEditMode() && display != null) {
                    float refreshRate = display.getRefreshRate();
                    if (refreshRate >= 30.0f) {
                        f = refreshRate;
                    }
                }
                GapWorker gapWorker2 = this.mGapWorker;
                gapWorker2.mFrameIntervalNs = 1.0E9f / f;
                threadLocal.set(gapWorker2);
            }
            this.mGapWorker.mRecyclerViews.add(this);
        }
    }

    public void onChildAttachedToWindow() {
    }

    public void onChildDetachedFromWindow() {
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        GapWorker gapWorker;
        super.onDetachedFromWindow();
        ItemAnimator itemAnimator = this.mItemAnimator;
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
        stopScroll();
        this.mIsAttached = false;
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            Recycler recycler = this.mRecycler;
            layoutManager.mIsAttachedToWindow = false;
            layoutManager.onDetachedFromWindow(this, recycler);
        }
        this.mPendingAccessibilityImportanceChange.clear();
        removeCallbacks(this.mItemAnimatorRunner);
        Objects.requireNonNull(this.mViewInfoStore);
        do {
        } while (ViewInfoStore.InfoRecord.sPool.acquire() != null);
        if (ALLOW_THREAD_GAP_WORK && (gapWorker = this.mGapWorker) != null) {
            gapWorker.mRecyclerViews.remove(this);
            this.mGapWorker = null;
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.mItemDecorations.size();
        for (int i = 0; i < size; i++) {
            this.mItemDecorations.get(i).onDraw(canvas, this, this.mState);
        }
    }

    public void onEnterLayoutOrScroll() {
        this.mLayoutOrScrollCounter++;
    }

    public void onExitLayoutOrScroll(boolean z) {
        int i;
        boolean z2 = true;
        int i2 = this.mLayoutOrScrollCounter - 1;
        this.mLayoutOrScrollCounter = i2;
        if (i2 < 1) {
            this.mLayoutOrScrollCounter = 0;
            if (z) {
                int i3 = this.mEatenAccessibilityChangeFlags;
                this.mEatenAccessibilityChangeFlags = 0;
                if (i3 != 0) {
                    AccessibilityManager accessibilityManager = this.mAccessibilityManager;
                    if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
                        z2 = false;
                    }
                    if (z2) {
                        AccessibilityEvent obtain = AccessibilityEvent.obtain();
                        obtain.setEventType(2048);
                        obtain.setContentChangeTypes(i3);
                        sendAccessibilityEventUnchecked(obtain);
                    }
                }
                for (int size = this.mPendingAccessibilityImportanceChange.size() - 1; size >= 0; size--) {
                    ViewHolder viewHolder = this.mPendingAccessibilityImportanceChange.get(size);
                    if (viewHolder.itemView.getParent() == this && !viewHolder.shouldIgnore() && (i = viewHolder.mPendingAccessibilityState) != -1) {
                        View view = viewHolder.itemView;
                        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                        view.setImportantForAccessibility(i);
                        viewHolder.mPendingAccessibilityState = -1;
                    }
                }
                this.mPendingAccessibilityImportanceChange.clear();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0081  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onGenericMotionEvent(android.view.MotionEvent r14) {
        /*
            Method dump skipped, instructions count: 222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        if (this.mLayoutSuppressed) {
            return false;
        }
        this.mInterceptingOnItemTouchListener = null;
        if (findInterceptingOnItemTouchListener(motionEvent)) {
            cancelScroll();
            return true;
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            return false;
        }
        boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
        boolean canScrollVertically = this.mLayout.canScrollVertically();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            if (this.mIgnoreMotionEventTillDown) {
                this.mIgnoreMotionEventTillDown = false;
            }
            this.mScrollPointerId = motionEvent.getPointerId(0);
            int x = (int) (motionEvent.getX() + 0.5f);
            this.mLastTouchX = x;
            this.mInitialTouchX = x;
            int y = (int) (motionEvent.getY() + 0.5f);
            this.mLastTouchY = y;
            this.mInitialTouchY = y;
            if (this.mScrollState == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                setScrollState(1);
                stopNestedScroll(1);
            }
            int[] iArr = this.mNestedOffsets;
            iArr[1] = 0;
            iArr[0] = 0;
            if (canScrollVertically) {
                boolean z2 = canScrollHorizontally ? 1 : 0;
                char c = canScrollHorizontally ? 1 : 0;
                canScrollHorizontally = z2 | true;
            }
            int i = canScrollHorizontally ? 1 : 0;
            int i2 = canScrollHorizontally ? 1 : 0;
            int i3 = canScrollHorizontally ? 1 : 0;
            startNestedScroll(i, 0);
        } else if (actionMasked == 1) {
            this.mVelocityTracker.clear();
            stopNestedScroll(0);
        } else if (actionMasked == 2) {
            int findPointerIndex = motionEvent.findPointerIndex(this.mScrollPointerId);
            if (findPointerIndex < 0) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Error processing scroll; pointer index for id ");
                outline13.append(this.mScrollPointerId);
                outline13.append(" not found. Did any MotionEvents get skipped?");
                Log.e("RecyclerView", outline13.toString());
                return false;
            }
            int x2 = (int) (motionEvent.getX(findPointerIndex) + 0.5f);
            int y2 = (int) (motionEvent.getY(findPointerIndex) + 0.5f);
            if (this.mScrollState != 1) {
                int i4 = x2 - this.mInitialTouchX;
                int i5 = y2 - this.mInitialTouchY;
                if (!canScrollHorizontally || Math.abs(i4) <= this.mTouchSlop) {
                    z = false;
                } else {
                    this.mLastTouchX = x2;
                    z = true;
                }
                if (canScrollVertically && Math.abs(i5) > this.mTouchSlop) {
                    this.mLastTouchY = y2;
                    z = true;
                }
                if (z) {
                    setScrollState(1);
                }
            }
        } else if (actionMasked == 3) {
            cancelScroll();
        } else if (actionMasked == 5) {
            this.mScrollPointerId = motionEvent.getPointerId(actionIndex);
            int x3 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.mLastTouchX = x3;
            this.mInitialTouchX = x3;
            int y3 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.mLastTouchY = y3;
            this.mInitialTouchY = y3;
        } else if (actionMasked == 6) {
            onPointerUp(motionEvent);
        }
        return this.mScrollState == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = TraceCompat.$r8$clinit;
        Trace.beginSection("RV OnLayout");
        dispatchLayout();
        Trace.endSection();
        this.mFirstLayoutComplete = true;
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            defaultOnMeasure(i, i2);
            return;
        }
        boolean z = false;
        if (layoutManager.isAutoMeasureEnabled()) {
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            this.mLayout.onMeasure(i, i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z = true;
            }
            this.mLastAutoMeasureSkippedDueToExact = z;
            if (!z && this.mAdapter != null) {
                if (this.mState.mLayoutStep == 1) {
                    dispatchLayoutStep1();
                }
                this.mLayout.setMeasureSpecs(i, i2);
                this.mState.mIsMeasuring = true;
                dispatchLayoutStep2();
                this.mLayout.setMeasuredDimensionFromChildren(i, i2);
                if (this.mLayout.shouldMeasureTwice()) {
                    this.mLayout.setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                    this.mState.mIsMeasuring = true;
                    dispatchLayoutStep2();
                    this.mLayout.setMeasuredDimensionFromChildren(i, i2);
                }
                this.mLastAutoMeasureNonExactMeasuredWidth = getMeasuredWidth();
                this.mLastAutoMeasureNonExactMeasuredHeight = getMeasuredHeight();
            }
        } else if (this.mHasFixedSize) {
            this.mLayout.onMeasure(i, i2);
        } else {
            if (this.mAdapterUpdateDuringMeasure) {
                startInterceptRequestLayout();
                onEnterLayoutOrScroll();
                processAdapterUpdatesAndSetAnimationFlags();
                onExitLayoutOrScroll(true);
                State state = this.mState;
                if (state.mRunPredictiveAnimations) {
                    state.mInPreLayout = true;
                } else {
                    this.mAdapterHelper.consumeUpdatesInOnePass();
                    this.mState.mInPreLayout = false;
                }
                this.mAdapterUpdateDuringMeasure = false;
                stopInterceptRequestLayout(false);
            } else if (this.mState.mRunPredictiveAnimations) {
                setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
                return;
            }
            Adapter adapter = this.mAdapter;
            if (adapter != null) {
                this.mState.mItemCount = adapter.getItemCount();
            } else {
                this.mState.mItemCount = 0;
            }
            startInterceptRequestLayout();
            this.mLayout.onMeasure(i, i2);
            stopInterceptRequestLayout(false);
            this.mState.mInPreLayout = false;
        }
    }

    public final void onPointerUp(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mScrollPointerId) {
            int i = actionIndex == 0 ? 1 : 0;
            this.mScrollPointerId = motionEvent.getPointerId(i);
            int x = (int) (motionEvent.getX(i) + 0.5f);
            this.mLastTouchX = x;
            this.mInitialTouchX = x;
            int y = (int) (motionEvent.getY(i) + 0.5f);
            this.mLastTouchY = y;
            this.mInitialTouchY = y;
        }
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i, Rect rect) {
        if (isComputingLayout()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i, rect);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.mPendingSavedState = savedState;
        super.onRestoreInstanceState(savedState.mSuperState);
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.mPendingSavedState;
        if (savedState2 != null) {
            savedState.mLayoutState = savedState2.mLayoutState;
        } else {
            LayoutManager layoutManager = this.mLayout;
            if (layoutManager != null) {
                savedState.mLayoutState = layoutManager.onSaveInstanceState();
            } else {
                savedState.mLayoutState = null;
            }
        }
        return savedState;
    }

    public void onScrollStateChanged() {
    }

    public void onScrolled() {
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3 || i2 != i4) {
            invalidateGlows();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:198:0x0321, code lost:
        if (r3 < r8) goto L_0x0324;
     */
    /* JADX WARN: Code restructure failed: missing block: B:221:0x03a4, code lost:
        if (r8 != false) goto L_0x03aa;
     */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0327  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x03e0  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0110  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r20) {
        /*
            Method dump skipped, instructions count: 1002
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void postAnimationRunner() {
        if (!this.mPostedAnimatorRunner && this.mIsAttached) {
            Runnable runnable = this.mItemAnimatorRunner;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            postOnAnimation(runnable);
            this.mPostedAnimatorRunner = true;
        }
    }

    public final void processAdapterUpdatesAndSetAnimationFlags() {
        boolean z;
        boolean z2 = false;
        if (this.mDataSetHasChangedAfterLayout) {
            AdapterHelper adapterHelper = this.mAdapterHelper;
            adapterHelper.recycleUpdateOpsAndClearList(adapterHelper.mPendingUpdates);
            adapterHelper.recycleUpdateOpsAndClearList(adapterHelper.mPostponedList);
            adapterHelper.mExistingUpdateTypes = 0;
            if (this.mDispatchItemsChangedEvent) {
                this.mLayout.onItemsChanged(this);
            }
        }
        if (this.mItemAnimator != null && this.mLayout.supportsPredictiveItemAnimations()) {
            this.mAdapterHelper.preProcess();
        } else {
            this.mAdapterHelper.consumeUpdatesInOnePass();
        }
        boolean z3 = this.mItemsAddedOrRemoved || this.mItemsChanged;
        State state = this.mState;
        boolean z4 = this.mFirstLayoutComplete && this.mItemAnimator != null && ((z = this.mDataSetHasChangedAfterLayout) || z3 || this.mLayout.mRequestedSimpleAnimations) && (!z || this.mAdapter.mHasStableIds);
        state.mRunSimpleAnimations = z4;
        if (z4 && z3 && !this.mDataSetHasChangedAfterLayout) {
            if (this.mItemAnimator != null && this.mLayout.supportsPredictiveItemAnimations()) {
                z2 = true;
            }
        }
        state.mRunPredictiveAnimations = z2;
    }

    public void processDataSetCompletelyChanged(boolean z) {
        this.mDispatchItemsChangedEvent = z | this.mDispatchItemsChangedEvent;
        this.mDataSetHasChangedAfterLayout = true;
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < unfilteredChildCount; i++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.addFlags(6);
            }
        }
        markItemDecorInsetsDirty();
        Recycler recycler = this.mRecycler;
        int size = recycler.mCachedViews.size();
        for (int i2 = 0; i2 < size; i2++) {
            ViewHolder viewHolder = recycler.mCachedViews.get(i2);
            if (viewHolder != null) {
                viewHolder.addFlags(6);
                viewHolder.addChangePayload(null);
            }
        }
        Adapter adapter = RecyclerView.this.mAdapter;
        if (adapter == null || !adapter.mHasStableIds) {
            recycler.recycleAndClearCachedViews();
        }
    }

    public void recordAnimationInfoIfBouncedHiddenView(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo) {
        viewHolder.setFlags(0, 8192);
        if (this.mState.mTrackOldChangeHolders && viewHolder.isUpdated() && !viewHolder.isRemoved() && !viewHolder.shouldIgnore()) {
            this.mViewInfoStore.mOldChangedHolders.put(getChangedHolderKey(viewHolder), viewHolder);
        }
        this.mViewInfoStore.addToPreLayout(viewHolder, itemHolderInfo);
    }

    public void removeAndRecycleViews() {
        ItemAnimator itemAnimator = this.mItemAnimator;
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.removeAndRecycleAllViews(this.mRecycler);
            this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
        }
        this.mRecycler.clear();
    }

    @Override // android.view.ViewGroup
    public void removeDetachedView(View view, boolean z) {
        ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (childViewHolderInt.isTmpDetached()) {
                childViewHolderInt.mFlags &= -257;
            } else if (!childViewHolderInt.shouldIgnore()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Called removeDetachedView with a view which is not flagged as tmp detached.");
                sb.append(childViewHolderInt);
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline2(this, sb));
            }
        }
        view.clearAnimation();
        dispatchChildDetached(view);
        super.removeDetachedView(view, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.mLayout.onRequestChildFocus1(this, view, view2) && view2 != null) {
            requestChildOnScreen(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    public final void requestChildOnScreen(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        this.mTempRect.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            if (!layoutParams2.mInsetsDirty) {
                Rect rect = layoutParams2.mDecorInsets;
                Rect rect2 = this.mTempRect;
                rect2.left -= rect.left;
                rect2.right += rect.right;
                rect2.top -= rect.top;
                rect2.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.mTempRect);
            offsetRectIntoDescendantCoords(view, this.mTempRect);
        }
        this.mLayout.requestChildRectangleOnScreen(this, view, this.mTempRect, !this.mFirstLayoutComplete, view2 == null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.mLayout.requestChildRectangleOnScreen(this, view, rect, z, false);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        int size = this.mOnItemTouchListeners.size();
        for (int i = 0; i < size; i++) {
            this.mOnItemTouchListeners.get(i).onRequestDisallowInterceptTouchEvent(z);
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.mInterceptRequestLayoutDepth != 0 || this.mLayoutSuppressed) {
            this.mLayoutWasDefered = true;
        } else {
            super.requestLayout();
        }
    }

    public final void resetScroll() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        boolean z = false;
        stopNestedScroll(0);
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            z = this.mLeftGlow.isFinished();
        }
        EdgeEffect edgeEffect2 = this.mTopGlow;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            z |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mRightGlow;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            z |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            z |= this.mBottomGlow.isFinished();
        }
        if (z) {
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            postInvalidateOnAnimation();
        }
    }

    @Override // android.view.View
    public void scrollBy(int i, int i2) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.mLayoutSuppressed) {
            boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
            boolean canScrollVertically = this.mLayout.canScrollVertically();
            if (canScrollHorizontally || canScrollVertically) {
                if (!canScrollHorizontally) {
                    i = 0;
                }
                if (!canScrollVertically) {
                    i2 = 0;
                }
                scrollByInternal(i, i2, null, 0);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0114  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean scrollByInternal(int r18, int r19, android.view.MotionEvent r20, int r21) {
        /*
            Method dump skipped, instructions count: 319
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.scrollByInternal(int, int, android.view.MotionEvent, int):boolean");
    }

    public void scrollStep(int i, int i2, int[] iArr) {
        ViewHolder viewHolder;
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        int i3 = TraceCompat.$r8$clinit;
        Trace.beginSection("RV Scroll");
        fillRemainingScrollValues(this.mState);
        int scrollHorizontallyBy = i != 0 ? this.mLayout.scrollHorizontallyBy(i, this.mRecycler, this.mState) : 0;
        int scrollVerticallyBy = i2 != 0 ? this.mLayout.scrollVerticallyBy(i2, this.mRecycler, this.mState) : 0;
        Trace.endSection();
        int childCount = this.mChildHelper.getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = this.mChildHelper.getChildAt(i4);
            ViewHolder childViewHolder = getChildViewHolder(childAt);
            if (!(childViewHolder == null || (viewHolder = childViewHolder.mShadowingHolder) == null)) {
                View view = viewHolder.itemView;
                int left = childAt.getLeft();
                int top = childAt.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
        onExitLayoutOrScroll(true);
        stopInterceptRequestLayout(false);
        if (iArr != null) {
            iArr[0] = scrollHorizontallyBy;
            iArr[1] = scrollVerticallyBy;
        }
    }

    @Override // android.view.View
    public void scrollTo(int i, int i2) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollToPosition(int i) {
        if (!this.mLayoutSuppressed) {
            stopScroll();
            LayoutManager layoutManager = this.mLayout;
            if (layoutManager == null) {
                Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
                return;
            }
            layoutManager.scrollToPosition(i);
            awakenScrollBars();
        }
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        int i = 0;
        if (isComputingLayout()) {
            int contentChangeTypes = accessibilityEvent != null ? accessibilityEvent.getContentChangeTypes() : 0;
            if (contentChangeTypes != 0) {
                i = contentChangeTypes;
            }
            this.mEatenAccessibilityChangeFlags |= i;
            i = 1;
        }
        if (i == 0) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        }
    }

    public void setAccessibilityDelegateCompat(RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate) {
        this.mAccessibilityDelegate = recyclerViewAccessibilityDelegate;
        ViewCompat.setAccessibilityDelegate(this, recyclerViewAccessibilityDelegate);
    }

    public void setAdapter(Adapter adapter) {
        setLayoutFrozen(false);
        setAdapterInternal(adapter, false, true);
        processDataSetCompletelyChanged(false);
        requestLayout();
    }

    public final void setAdapterInternal(Adapter adapter, boolean z, boolean z2) {
        Adapter adapter2 = this.mAdapter;
        if (adapter2 != null) {
            adapter2.mObservable.unregisterObserver(this.mObserver);
            this.mAdapter.onDetachedFromRecyclerView(this);
        }
        if (!z || z2) {
            removeAndRecycleViews();
        }
        AdapterHelper adapterHelper = this.mAdapterHelper;
        adapterHelper.recycleUpdateOpsAndClearList(adapterHelper.mPendingUpdates);
        adapterHelper.recycleUpdateOpsAndClearList(adapterHelper.mPostponedList);
        adapterHelper.mExistingUpdateTypes = 0;
        Adapter adapter3 = this.mAdapter;
        this.mAdapter = adapter;
        if (adapter != null) {
            adapter.mObservable.registerObserver(this.mObserver);
            adapter.onAttachedToRecyclerView(this);
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.onAdapterChanged(adapter3, this.mAdapter);
        }
        Recycler recycler = this.mRecycler;
        Adapter adapter4 = this.mAdapter;
        recycler.clear();
        RecycledViewPool recycledViewPool = recycler.getRecycledViewPool();
        Objects.requireNonNull(recycledViewPool);
        if (adapter3 != null) {
            recycledViewPool.mAttachCount--;
        }
        if (!z && recycledViewPool.mAttachCount == 0) {
            for (int i = 0; i < recycledViewPool.mScrap.size(); i++) {
                recycledViewPool.mScrap.valueAt(i).mScrapHeap.clear();
            }
        }
        if (adapter4 != null) {
            recycledViewPool.mAttachCount++;
        }
        this.mState.mStructureChanged = true;
    }

    public void setChildDrawingOrderCallback(ChildDrawingOrderCallback childDrawingOrderCallback) {
        if (childDrawingOrderCallback != this.mChildDrawingOrderCallback) {
            this.mChildDrawingOrderCallback = childDrawingOrderCallback;
            setChildrenDrawingOrderEnabled(childDrawingOrderCallback != null);
        }
    }

    public boolean setChildImportantForAccessibilityInternal(ViewHolder viewHolder, int i) {
        if (isComputingLayout()) {
            viewHolder.mPendingAccessibilityState = i;
            this.mPendingAccessibilityImportanceChange.add(viewHolder);
            return false;
        }
        View view = viewHolder.itemView;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        view.setImportantForAccessibility(i);
        return true;
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z) {
        if (z != this.mClipToPadding) {
            invalidateGlows();
        }
        this.mClipToPadding = z;
        super.setClipToPadding(z);
        if (this.mFirstLayoutComplete) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(EdgeEffectFactory edgeEffectFactory) {
        Objects.requireNonNull(edgeEffectFactory);
        this.mEdgeEffectFactory = edgeEffectFactory;
        invalidateGlows();
    }

    public void setHasFixedSize(boolean z) {
        this.mHasFixedSize = z;
    }

    public void setItemAnimator(ItemAnimator itemAnimator) {
        ItemAnimator itemAnimator2 = this.mItemAnimator;
        if (itemAnimator2 != null) {
            itemAnimator2.endAnimations();
            this.mItemAnimator.mListener = null;
        }
        this.mItemAnimator = itemAnimator;
        if (itemAnimator != null) {
            itemAnimator.mListener = this.mItemAnimatorListener;
        }
    }

    public void setItemViewCacheSize(int i) {
        Recycler recycler = this.mRecycler;
        recycler.mRequestedCacheMax = i;
        recycler.updateViewCacheSize();
    }

    @Deprecated
    public void setLayoutFrozen(boolean z) {
        suppressLayout(z);
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        if (layoutManager != this.mLayout) {
            stopScroll();
            if (this.mLayout != null) {
                ItemAnimator itemAnimator = this.mItemAnimator;
                if (itemAnimator != null) {
                    itemAnimator.endAnimations();
                }
                this.mLayout.removeAndRecycleAllViews(this.mRecycler);
                this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
                this.mRecycler.clear();
                if (this.mIsAttached) {
                    LayoutManager layoutManager2 = this.mLayout;
                    Recycler recycler = this.mRecycler;
                    layoutManager2.mIsAttachedToWindow = false;
                    layoutManager2.onDetachedFromWindow(this, recycler);
                }
                this.mLayout.setRecyclerView(null);
                this.mLayout = null;
            } else {
                this.mRecycler.clear();
            }
            ChildHelper childHelper = this.mChildHelper;
            ChildHelper.Bucket bucket = childHelper.mBucket;
            bucket.mData = 0L;
            ChildHelper.Bucket bucket2 = bucket.mNext;
            if (bucket2 != null) {
                bucket2.reset();
            }
            int size = childHelper.mHiddenViews.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                AnonymousClass5 r3 = (AnonymousClass5) childHelper.mCallback;
                Objects.requireNonNull(r3);
                ViewHolder childViewHolderInt = getChildViewHolderInt(childHelper.mHiddenViews.get(size));
                if (childViewHolderInt != null) {
                    RecyclerView.this.setChildImportantForAccessibilityInternal(childViewHolderInt, childViewHolderInt.mWasImportantForAccessibilityBeforeHidden);
                    childViewHolderInt.mWasImportantForAccessibilityBeforeHidden = 0;
                }
                childHelper.mHiddenViews.remove(size);
            }
            AnonymousClass5 r0 = (AnonymousClass5) childHelper.mCallback;
            int childCount = r0.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = r0.getChildAt(i);
                RecyclerView.this.dispatchChildDetached(childAt);
                childAt.clearAnimation();
            }
            RecyclerView.this.removeAllViews();
            this.mLayout = layoutManager;
            if (layoutManager != null) {
                if (layoutManager.mRecyclerView == null) {
                    layoutManager.setRecyclerView(this);
                    if (this.mIsAttached) {
                        LayoutManager layoutManager3 = this.mLayout;
                        layoutManager3.mIsAttachedToWindow = true;
                        layoutManager3.onAttachedToWindow();
                    }
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("LayoutManager ");
                    sb.append(layoutManager);
                    sb.append(" is already attached to a RecyclerView:");
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline2(layoutManager.mRecyclerView, sb));
                }
            }
            this.mRecycler.updateViewCacheSize();
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup
    @Deprecated
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        if (layoutTransition == null) {
            super.setLayoutTransition(null);
            return;
        }
        throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        NestedScrollingChildHelper scrollingChildHelper = getScrollingChildHelper();
        if (scrollingChildHelper.mIsNestedScrollingEnabled) {
            View view = scrollingChildHelper.mView;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            view.stopNestedScroll();
        }
        scrollingChildHelper.mIsNestedScrollingEnabled = z;
    }

    public void setOnFlingListener(OnFlingListener onFlingListener) {
        this.mOnFlingListener = onFlingListener;
    }

    @Deprecated
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mScrollListener = onScrollListener;
    }

    public void setPreserveFocusAfterLayout(boolean z) {
        this.mPreserveFocusAfterLayout = z;
    }

    public void setRecycledViewPool(RecycledViewPool recycledViewPool) {
        RecycledViewPool recycledViewPool2;
        Recycler recycler = this.mRecycler;
        if (recycler.mRecyclerPool != null) {
            recycledViewPool2.mAttachCount--;
        }
        recycler.mRecyclerPool = recycledViewPool;
        if (recycledViewPool != null && RecyclerView.this.getAdapter() != null) {
            recycler.mRecyclerPool.mAttachCount++;
        }
    }

    @Deprecated
    public void setRecyclerListener(RecyclerListener recyclerListener) {
        this.mRecyclerListener = recyclerListener;
    }

    public void setScrollState(int i) {
        SmoothScroller smoothScroller;
        if (i != this.mScrollState) {
            this.mScrollState = i;
            if (i != 2) {
                this.mViewFlinger.stop();
                LayoutManager layoutManager = this.mLayout;
                if (!(layoutManager == null || (smoothScroller = layoutManager.mSmoothScroller) == null)) {
                    smoothScroller.stop();
                }
            }
            LayoutManager layoutManager2 = this.mLayout;
            if (layoutManager2 != null) {
                layoutManager2.onScrollStateChanged(i);
            }
            onScrollStateChanged();
            OnScrollListener onScrollListener = this.mScrollListener;
            if (onScrollListener != null) {
                onScrollListener.onScrollStateChanged(this, i);
            }
            List<OnScrollListener> list = this.mScrollListeners;
            if (list != null) {
                int size = list.size();
                while (true) {
                    size--;
                    if (size >= 0) {
                        this.mScrollListeners.get(size).onScrollStateChanged(this, i);
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public void setScrollingTouchSlop(int i) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i != 0) {
            if (i != 1) {
                Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + i + "; using default value");
            } else {
                this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
                return;
            }
        }
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
    }

    public void setViewCacheExtension(ViewCacheExtension viewCacheExtension) {
        Objects.requireNonNull(this.mRecycler);
    }

    public void smoothScrollBy(int i, int i2, Interpolator interpolator, int i3, boolean z) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.mLayoutSuppressed) {
            int i4 = 0;
            if (!layoutManager.canScrollHorizontally()) {
                i = 0;
            }
            if (!this.mLayout.canScrollVertically()) {
                i2 = 0;
            }
            if (i != 0 || i2 != 0) {
                if (i3 == Integer.MIN_VALUE || i3 > 0) {
                    if (z) {
                        if (i != 0) {
                            i4 = 1;
                        }
                        if (i2 != 0) {
                            i4 |= 2;
                        }
                        startNestedScroll(i4, 1);
                    }
                    this.mViewFlinger.smoothScrollBy(i, i2, i3, interpolator);
                    return;
                }
                scrollBy(i, i2);
            }
        }
    }

    public void smoothScrollToPosition(int i) {
        if (!this.mLayoutSuppressed) {
            LayoutManager layoutManager = this.mLayout;
            if (layoutManager == null) {
                Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            } else {
                layoutManager.smoothScrollToPosition(this, this.mState, i);
            }
        }
    }

    public void startInterceptRequestLayout() {
        int i = this.mInterceptRequestLayoutDepth + 1;
        this.mInterceptRequestLayoutDepth = i;
        if (i == 1 && !this.mLayoutSuppressed) {
            this.mLayoutWasDefered = false;
        }
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i) {
        return getScrollingChildHelper().startNestedScroll(i, 0);
    }

    public void stopInterceptRequestLayout(boolean z) {
        if (this.mInterceptRequestLayoutDepth < 1) {
            this.mInterceptRequestLayoutDepth = 1;
        }
        if (!z && !this.mLayoutSuppressed) {
            this.mLayoutWasDefered = false;
        }
        if (this.mInterceptRequestLayoutDepth == 1) {
            if (z && this.mLayoutWasDefered && !this.mLayoutSuppressed && this.mLayout != null && this.mAdapter != null) {
                dispatchLayout();
            }
            if (!this.mLayoutSuppressed) {
                this.mLayoutWasDefered = false;
            }
        }
        this.mInterceptRequestLayoutDepth--;
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        getScrollingChildHelper().stopNestedScroll(0);
    }

    public void stopScroll() {
        SmoothScroller smoothScroller;
        setScrollState(0);
        this.mViewFlinger.stop();
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && (smoothScroller = layoutManager.mSmoothScroller) != null) {
            smoothScroller.stop();
        }
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z) {
        if (z != this.mLayoutSuppressed) {
            assertNotInLayoutOrScroll("Do not suppressLayout in layout or scroll");
            if (!z) {
                this.mLayoutSuppressed = false;
                if (!(!this.mLayoutWasDefered || this.mLayout == null || this.mAdapter == null)) {
                    requestLayout();
                }
                this.mLayoutWasDefered = false;
                return;
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
            this.mLayoutSuppressed = true;
            this.mIgnoreMotionEventTillDown = true;
            stopScroll();
        }
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        float f;
        float f2;
        char c;
        ClassLoader classLoader;
        Object[] objArr;
        Constructor constructor;
        this.mObserver = new RecyclerViewDataObserver();
        this.mRecycler = new Recycler();
        this.mViewInfoStore = new ViewInfoStore();
        this.mUpdateChildViewsRunnable = new Runnable() { // from class: androidx.recyclerview.widget.RecyclerView.1
            @Override // java.lang.Runnable
            public void run() {
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.mFirstLayoutComplete && !recyclerView.isLayoutRequested()) {
                    RecyclerView recyclerView2 = RecyclerView.this;
                    if (!recyclerView2.mIsAttached) {
                        recyclerView2.requestLayout();
                    } else if (recyclerView2.mLayoutSuppressed) {
                        recyclerView2.mLayoutWasDefered = true;
                    } else {
                        recyclerView2.consumePendingUpdateOperations();
                    }
                }
            }
        };
        this.mTempRect = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRectF = new RectF();
        this.mRecyclerListeners = new ArrayList();
        this.mItemDecorations = new ArrayList<>();
        this.mOnItemTouchListeners = new ArrayList<>();
        this.mInterceptRequestLayoutDepth = 0;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        this.mLayoutOrScrollCounter = 0;
        this.mDispatchScrollCounter = 0;
        this.mEdgeEffectFactory = new EdgeEffectFactory();
        this.mItemAnimator = new DefaultItemAnimator();
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mScaledHorizontalScrollFactor = Float.MIN_VALUE;
        this.mScaledVerticalScrollFactor = Float.MIN_VALUE;
        this.mPreserveFocusAfterLayout = true;
        this.mViewFlinger = new ViewFlinger();
        this.mPrefetchRegistry = ALLOW_THREAD_GAP_WORK ? new GapWorker.LayoutPrefetchRegistryImpl() : null;
        this.mState = new State();
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        this.mItemAnimatorListener = new ItemAnimatorRestoreListener();
        this.mPostedAnimatorRunner = false;
        this.mMinMaxLayoutPositions = new int[2];
        this.mScrollOffset = new int[2];
        this.mNestedOffsets = new int[2];
        this.mReusableIntPair = new int[2];
        this.mPendingAccessibilityImportanceChange = new ArrayList();
        this.mItemAnimatorRunner = new Runnable() { // from class: androidx.recyclerview.widget.RecyclerView.2
            @Override // java.lang.Runnable
            public void run() {
                ItemAnimator itemAnimator = RecyclerView.this.mItemAnimator;
                if (itemAnimator != null) {
                    final DefaultItemAnimator defaultItemAnimator = (DefaultItemAnimator) itemAnimator;
                    boolean z = !defaultItemAnimator.mPendingRemovals.isEmpty();
                    boolean z2 = !defaultItemAnimator.mPendingMoves.isEmpty();
                    boolean z3 = !defaultItemAnimator.mPendingChanges.isEmpty();
                    boolean z4 = !defaultItemAnimator.mPendingAdditions.isEmpty();
                    if (z || z2 || z4 || z3) {
                        Iterator<ViewHolder> it = defaultItemAnimator.mPendingRemovals.iterator();
                        while (it.hasNext()) {
                            final ViewHolder next = it.next();
                            final View view = next.itemView;
                            final ViewPropertyAnimator animate = view.animate();
                            defaultItemAnimator.mRemoveAnimations.add(next);
                            animate.setDuration(defaultItemAnimator.mRemoveDuration).alpha(0.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.4
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator) {
                                    animate.setListener(null);
                                    view.setAlpha(1.0f);
                                    DefaultItemAnimator.this.dispatchAnimationFinished(next);
                                    DefaultItemAnimator.this.mRemoveAnimations.remove(next);
                                    DefaultItemAnimator.this.dispatchFinishedWhenDone();
                                }

                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationStart(Animator animator) {
                                    Objects.requireNonNull(DefaultItemAnimator.this);
                                }
                            }).start();
                        }
                        defaultItemAnimator.mPendingRemovals.clear();
                        if (z2) {
                            final ArrayList<DefaultItemAnimator.MoveInfo> arrayList = new ArrayList<>();
                            arrayList.addAll(defaultItemAnimator.mPendingMoves);
                            defaultItemAnimator.mMovesList.add(arrayList);
                            defaultItemAnimator.mPendingMoves.clear();
                            Runnable runnable = new Runnable() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    Iterator it2 = arrayList.iterator();
                                    while (it2.hasNext()) {
                                        MoveInfo moveInfo = (MoveInfo) it2.next();
                                        final DefaultItemAnimator defaultItemAnimator2 = DefaultItemAnimator.this;
                                        final RecyclerView.ViewHolder viewHolder = moveInfo.holder;
                                        int i2 = moveInfo.fromX;
                                        int i3 = moveInfo.fromY;
                                        int i4 = moveInfo.toX;
                                        int i5 = moveInfo.toY;
                                        Objects.requireNonNull(defaultItemAnimator2);
                                        final View view2 = viewHolder.itemView;
                                        final int i6 = i4 - i2;
                                        final int i7 = i5 - i3;
                                        if (i6 != 0) {
                                            view2.animate().translationX(0.0f);
                                        }
                                        if (i7 != 0) {
                                            view2.animate().translationY(0.0f);
                                        }
                                        final ViewPropertyAnimator animate2 = view2.animate();
                                        defaultItemAnimator2.mMoveAnimations.add(viewHolder);
                                        animate2.setDuration(defaultItemAnimator2.mMoveDuration).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.6
                                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                            public void onAnimationCancel(Animator animator) {
                                                if (i6 != 0) {
                                                    view2.setTranslationX(0.0f);
                                                }
                                                if (i7 != 0) {
                                                    view2.setTranslationY(0.0f);
                                                }
                                            }

                                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                            public void onAnimationEnd(Animator animator) {
                                                animate2.setListener(null);
                                                DefaultItemAnimator.this.dispatchAnimationFinished(viewHolder);
                                                DefaultItemAnimator.this.mMoveAnimations.remove(viewHolder);
                                                DefaultItemAnimator.this.dispatchFinishedWhenDone();
                                            }

                                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                            public void onAnimationStart(Animator animator) {
                                                Objects.requireNonNull(DefaultItemAnimator.this);
                                            }
                                        }).start();
                                    }
                                    arrayList.clear();
                                    DefaultItemAnimator.this.mMovesList.remove(arrayList);
                                }
                            };
                            if (z) {
                                View view2 = arrayList.get(0).holder.itemView;
                                long j = defaultItemAnimator.mRemoveDuration;
                                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                                view2.postOnAnimationDelayed(runnable, j);
                            } else {
                                runnable.run();
                            }
                        }
                        if (z3) {
                            final ArrayList<DefaultItemAnimator.ChangeInfo> arrayList2 = new ArrayList<>();
                            arrayList2.addAll(defaultItemAnimator.mPendingChanges);
                            defaultItemAnimator.mChangesList.add(arrayList2);
                            defaultItemAnimator.mPendingChanges.clear();
                            Runnable runnable2 = new Runnable() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    Iterator it2 = arrayList2.iterator();
                                    while (it2.hasNext()) {
                                        final ChangeInfo changeInfo = (ChangeInfo) it2.next();
                                        final DefaultItemAnimator defaultItemAnimator2 = DefaultItemAnimator.this;
                                        Objects.requireNonNull(defaultItemAnimator2);
                                        RecyclerView.ViewHolder viewHolder = changeInfo.oldHolder;
                                        final View view3 = null;
                                        final View view4 = viewHolder == null ? null : viewHolder.itemView;
                                        RecyclerView.ViewHolder viewHolder2 = changeInfo.newHolder;
                                        if (viewHolder2 != null) {
                                            view3 = viewHolder2.itemView;
                                        }
                                        if (view4 != null) {
                                            final ViewPropertyAnimator duration = view4.animate().setDuration(defaultItemAnimator2.mChangeDuration);
                                            defaultItemAnimator2.mChangeAnimations.add(changeInfo.oldHolder);
                                            duration.translationX(changeInfo.toX - changeInfo.fromX);
                                            duration.translationY(changeInfo.toY - changeInfo.fromY);
                                            duration.alpha(0.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.7
                                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                public void onAnimationEnd(Animator animator) {
                                                    duration.setListener(null);
                                                    view4.setAlpha(1.0f);
                                                    view4.setTranslationX(0.0f);
                                                    view4.setTranslationY(0.0f);
                                                    DefaultItemAnimator.this.dispatchAnimationFinished(changeInfo.oldHolder);
                                                    DefaultItemAnimator.this.mChangeAnimations.remove(changeInfo.oldHolder);
                                                    DefaultItemAnimator.this.dispatchFinishedWhenDone();
                                                }

                                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                public void onAnimationStart(Animator animator) {
                                                    DefaultItemAnimator defaultItemAnimator3 = DefaultItemAnimator.this;
                                                    RecyclerView.ViewHolder viewHolder3 = changeInfo.oldHolder;
                                                    Objects.requireNonNull(defaultItemAnimator3);
                                                }
                                            }).start();
                                        }
                                        if (view3 != null) {
                                            final ViewPropertyAnimator animate2 = view3.animate();
                                            defaultItemAnimator2.mChangeAnimations.add(changeInfo.newHolder);
                                            animate2.translationX(0.0f).translationY(0.0f).setDuration(defaultItemAnimator2.mChangeDuration).alpha(1.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.8
                                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                public void onAnimationEnd(Animator animator) {
                                                    animate2.setListener(null);
                                                    view3.setAlpha(1.0f);
                                                    view3.setTranslationX(0.0f);
                                                    view3.setTranslationY(0.0f);
                                                    DefaultItemAnimator.this.dispatchAnimationFinished(changeInfo.newHolder);
                                                    DefaultItemAnimator.this.mChangeAnimations.remove(changeInfo.newHolder);
                                                    DefaultItemAnimator.this.dispatchFinishedWhenDone();
                                                }

                                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                public void onAnimationStart(Animator animator) {
                                                    DefaultItemAnimator defaultItemAnimator3 = DefaultItemAnimator.this;
                                                    RecyclerView.ViewHolder viewHolder3 = changeInfo.newHolder;
                                                    Objects.requireNonNull(defaultItemAnimator3);
                                                }
                                            }).start();
                                        }
                                    }
                                    arrayList2.clear();
                                    DefaultItemAnimator.this.mChangesList.remove(arrayList2);
                                }
                            };
                            if (z) {
                                View view3 = arrayList2.get(0).oldHolder.itemView;
                                long j2 = defaultItemAnimator.mRemoveDuration;
                                AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
                                view3.postOnAnimationDelayed(runnable2, j2);
                            } else {
                                runnable2.run();
                            }
                        }
                        if (z4) {
                            final ArrayList<ViewHolder> arrayList3 = new ArrayList<>();
                            arrayList3.addAll(defaultItemAnimator.mPendingAdditions);
                            defaultItemAnimator.mAdditionsList.add(arrayList3);
                            defaultItemAnimator.mPendingAdditions.clear();
                            Runnable runnable3 = new Runnable() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    Iterator it2 = arrayList3.iterator();
                                    while (it2.hasNext()) {
                                        final RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) it2.next();
                                        final DefaultItemAnimator defaultItemAnimator2 = DefaultItemAnimator.this;
                                        Objects.requireNonNull(defaultItemAnimator2);
                                        final View view4 = viewHolder.itemView;
                                        final ViewPropertyAnimator animate2 = view4.animate();
                                        defaultItemAnimator2.mAddAnimations.add(viewHolder);
                                        animate2.alpha(1.0f).setDuration(defaultItemAnimator2.mAddDuration).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.5
                                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                            public void onAnimationCancel(Animator animator) {
                                                view4.setAlpha(1.0f);
                                            }

                                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                            public void onAnimationEnd(Animator animator) {
                                                animate2.setListener(null);
                                                DefaultItemAnimator.this.dispatchAnimationFinished(viewHolder);
                                                DefaultItemAnimator.this.mAddAnimations.remove(viewHolder);
                                                DefaultItemAnimator.this.dispatchFinishedWhenDone();
                                            }

                                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                            public void onAnimationStart(Animator animator) {
                                                Objects.requireNonNull(DefaultItemAnimator.this);
                                            }
                                        }).start();
                                    }
                                    arrayList3.clear();
                                    DefaultItemAnimator.this.mAdditionsList.remove(arrayList3);
                                }
                            };
                            if (z || z2 || z3) {
                                long j3 = 0;
                                long j4 = z ? defaultItemAnimator.mRemoveDuration : 0L;
                                long j5 = z2 ? defaultItemAnimator.mMoveDuration : 0L;
                                if (z3) {
                                    j3 = defaultItemAnimator.mChangeDuration;
                                }
                                View view4 = arrayList3.get(0).itemView;
                                AtomicInteger atomicInteger3 = ViewCompat.sNextGeneratedId;
                                view4.postOnAnimationDelayed(runnable3, Math.max(j5, j3) + j4);
                            } else {
                                runnable3.run();
                            }
                        }
                    }
                }
                RecyclerView.this.mPostedAnimatorRunner = false;
            }
        };
        this.mLastAutoMeasureNonExactMeasuredWidth = 0;
        this.mLastAutoMeasureNonExactMeasuredHeight = 0;
        this.mViewInfoProcessCallback = new AnonymousClass4();
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        Method method = ViewConfigurationCompat.sGetScaledScrollFactorMethod;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            f = viewConfiguration.getScaledHorizontalScrollFactor();
        } else {
            f = ViewConfigurationCompat.getLegacyScrollFactor(viewConfiguration, context);
        }
        this.mScaledHorizontalScrollFactor = f;
        if (i2 >= 26) {
            f2 = viewConfiguration.getScaledVerticalScrollFactor();
        } else {
            f2 = ViewConfigurationCompat.getLegacyScrollFactor(viewConfiguration, context);
        }
        this.mScaledVerticalScrollFactor = f2;
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.mItemAnimator.mListener = this.mItemAnimatorListener;
        this.mAdapterHelper = new AdapterHelper(new AnonymousClass6());
        this.mChildHelper = new ChildHelper(new AnonymousClass5());
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        if ((i2 >= 26 ? getImportantForAutofill() : 0) == 0 && i2 >= 26) {
            setImportantForAutofill(8);
        }
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
        int[] iArr = R$styleable.RecyclerView;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context, iArr, attributeSet, obtainStyledAttributes, i, 0);
        String string = obtainStyledAttributes.getString(8);
        if (obtainStyledAttributes.getInt(2, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.mClipToPadding = obtainStyledAttributes.getBoolean(1, true);
        boolean z = obtainStyledAttributes.getBoolean(3, false);
        this.mEnableFastScroller = z;
        if (z) {
            StateListDrawable stateListDrawable = (StateListDrawable) obtainStyledAttributes.getDrawable(6);
            Drawable drawable = obtainStyledAttributes.getDrawable(7);
            StateListDrawable stateListDrawable2 = (StateListDrawable) obtainStyledAttributes.getDrawable(4);
            Drawable drawable2 = obtainStyledAttributes.getDrawable(5);
            if (stateListDrawable == null || drawable == null || stateListDrawable2 == null || drawable2 == null) {
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline2(this, GeneratedOutlineSupport.outline13("Trying to set fast scroller without both required drawables.")));
            }
            Resources resources = getContext().getResources();
            c = 2;
            new FastScroller(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(R.dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(R.dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(R.dimen.fastscroll_margin));
        } else {
            c = 2;
        }
        obtainStyledAttributes.recycle();
        if (string != null) {
            String trim = string.trim();
            if (!trim.isEmpty()) {
                if (trim.charAt(0) == '.') {
                    trim = context.getPackageName() + trim;
                } else if (!trim.contains(".")) {
                    trim = RecyclerView.class.getPackage().getName() + '.' + trim;
                }
                try {
                    if (isInEditMode()) {
                        classLoader = getClass().getClassLoader();
                    } else {
                        classLoader = context.getClassLoader();
                    }
                    Class<? extends U> asSubclass = Class.forName(trim, false, classLoader).asSubclass(LayoutManager.class);
                    try {
                        constructor = asSubclass.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
                        Object[] objArr2 = new Object[4];
                        objArr2[0] = context;
                        objArr2[1] = attributeSet;
                        objArr2[c] = Integer.valueOf(i);
                        objArr2[3] = 0;
                        objArr = objArr2;
                    } catch (NoSuchMethodException e) {
                        try {
                            constructor = asSubclass.getConstructor(new Class[0]);
                            objArr = null;
                        } catch (NoSuchMethodException e2) {
                            e2.initCause(e);
                            throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + trim, e2);
                        }
                    }
                    constructor.setAccessible(true);
                    setLayoutManager((LayoutManager) constructor.newInstance(objArr));
                } catch (ClassCastException e3) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + trim, e3);
                } catch (ClassNotFoundException e4) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + trim, e4);
                } catch (IllegalAccessException e5) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + trim, e5);
                } catch (InstantiationException e6) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + trim, e6);
                } catch (InvocationTargetException e7) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + trim, e7);
                }
            }
        }
        int[] iArr2 = NESTED_SCROLLING_ATTRS;
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr2, i, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context, iArr2, attributeSet, obtainStyledAttributes2, i, 0);
        boolean z2 = obtainStyledAttributes2.getBoolean(0, true);
        obtainStyledAttributes2.recycle();
        setNestedScrollingEnabled(z2);
    }

    public final void dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        getScrollingChildHelper().dispatchNestedScrollInternal(i, i2, i3, i4, iArr, i5, iArr2);
    }

    /* loaded from: classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: androidx.recyclerview.widget.RecyclerView.SavedState.1
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            public Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        };
        public Parcelable mLayoutState;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.mLayoutState = parcel.readParcelable(classLoader == null ? LayoutManager.class.getClassLoader() : classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mSuperState, i);
            parcel.writeParcelable(this.mLayoutState, 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        return getScrollingChildHelper().dispatchNestedPreScroll(i, i2, iArr, iArr2, i3);
    }

    public boolean startNestedScroll(int i, int i2) {
        return getScrollingChildHelper().startNestedScroll(i, i2);
    }

    public void stopNestedScroll(int i) {
        getScrollingChildHelper().stopNestedScroll(i);
    }

    /* loaded from: classes.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public final Rect mDecorInsets = new Rect();
        public boolean mInsetsDirty = true;
        public boolean mPendingInvalidate = false;
        public ViewHolder mViewHolder;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public int getViewLayoutPosition() {
            return this.mViewHolder.getLayoutPosition();
        }

        public boolean isItemChanged() {
            return this.mViewHolder.isUpdated();
        }

        public boolean isItemRemoved() {
            return this.mViewHolder.isRemoved();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.LayoutParams) layoutParams);
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.generateLayoutParams(layoutParams);
        }
        throw new IllegalStateException(GeneratedOutlineSupport.outline2(this, GeneratedOutlineSupport.outline13("RecyclerView has no LayoutManager")));
    }

    /* loaded from: classes.dex */
    public static abstract class LayoutManager {
        public ChildHelper mChildHelper;
        public int mHeight;
        public int mHeightMode;
        public ViewBoundsCheck mHorizontalBoundCheck;
        public final ViewBoundsCheck.Callback mHorizontalBoundCheckCallback;
        public int mPrefetchMaxCountObserved;
        public boolean mPrefetchMaxObservedInInitialPrefetch;
        public RecyclerView mRecyclerView;
        public SmoothScroller mSmoothScroller;
        public ViewBoundsCheck mVerticalBoundCheck;
        public final ViewBoundsCheck.Callback mVerticalBoundCheckCallback;
        public int mWidth;
        public int mWidthMode;
        public boolean mRequestedSimpleAnimations = false;
        public boolean mIsAttachedToWindow = false;
        public boolean mMeasurementCacheEnabled = true;
        public boolean mItemPrefetchEnabled = true;

        /* loaded from: classes.dex */
        public interface LayoutPrefetchRegistry {
        }

        /* loaded from: classes.dex */
        public static class Properties {
            public int orientation;
            public boolean reverseLayout;
            public int spanCount;
            public boolean stackFromEnd;
        }

        public LayoutManager() {
            ViewBoundsCheck.Callback callback = new ViewBoundsCheck.Callback() { // from class: androidx.recyclerview.widget.RecyclerView.LayoutManager.1
                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public View getChildAt(int i) {
                    return LayoutManager.this.getChildAt(i);
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int getChildEnd(View view) {
                    return LayoutManager.this.getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).rightMargin;
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int getChildStart(View view) {
                    return LayoutManager.this.getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).leftMargin;
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int getParentEnd() {
                    LayoutManager layoutManager = LayoutManager.this;
                    return layoutManager.mWidth - layoutManager.getPaddingRight();
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int getParentStart() {
                    return LayoutManager.this.getPaddingLeft();
                }
            };
            this.mHorizontalBoundCheckCallback = callback;
            ViewBoundsCheck.Callback callback2 = new ViewBoundsCheck.Callback() { // from class: androidx.recyclerview.widget.RecyclerView.LayoutManager.2
                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public View getChildAt(int i) {
                    return LayoutManager.this.getChildAt(i);
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int getChildEnd(View view) {
                    return LayoutManager.this.getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).bottomMargin;
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int getChildStart(View view) {
                    return LayoutManager.this.getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).topMargin;
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int getParentEnd() {
                    LayoutManager layoutManager = LayoutManager.this;
                    return layoutManager.mHeight - layoutManager.getPaddingBottom();
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int getParentStart() {
                    return LayoutManager.this.getPaddingTop();
                }
            };
            this.mVerticalBoundCheckCallback = callback2;
            this.mHorizontalBoundCheck = new ViewBoundsCheck(callback);
            this.mVerticalBoundCheck = new ViewBoundsCheck(callback2);
        }

        public static int chooseSize(int i, int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            if (mode != Integer.MIN_VALUE) {
                return mode != 1073741824 ? Math.max(i2, i3) : size;
            }
            return Math.min(size, Math.max(i2, i3));
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x0017, code lost:
            if (r5 == 1073741824) goto L_0x0021;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static int getChildMeasureSpec(int r4, int r5, int r6, int r7, boolean r8) {
            /*
                int r4 = r4 - r6
                r6 = 0
                int r4 = java.lang.Math.max(r6, r4)
                r0 = -2
                r1 = -1
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = 1073741824(0x40000000, float:2.0)
                if (r8 == 0) goto L_0x001a
                if (r7 < 0) goto L_0x0011
                goto L_0x001c
            L_0x0011:
                if (r7 != r1) goto L_0x002f
                if (r5 == r2) goto L_0x0021
                if (r5 == 0) goto L_0x002f
                if (r5 == r3) goto L_0x0021
                goto L_0x002f
            L_0x001a:
                if (r7 < 0) goto L_0x001f
            L_0x001c:
                r5 = 1073741824(0x40000000, float:2.0)
                goto L_0x0031
            L_0x001f:
                if (r7 != r1) goto L_0x0023
            L_0x0021:
                r7 = r4
                goto L_0x0031
            L_0x0023:
                if (r7 != r0) goto L_0x002f
                if (r5 == r2) goto L_0x002c
                if (r5 != r3) goto L_0x002a
                goto L_0x002c
            L_0x002a:
                r5 = 0
                goto L_0x0021
            L_0x002c:
                r5 = -2147483648(0xffffffff80000000, float:-0.0)
                goto L_0x0021
            L_0x002f:
                r5 = 0
                r7 = 0
            L_0x0031:
                int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r5)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.LayoutManager.getChildMeasureSpec(int, int, int, int, boolean):int");
        }

        public static Properties getProperties(Context context, AttributeSet attributeSet, int i, int i2) {
            Properties properties = new Properties();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RecyclerView, i, i2);
            properties.orientation = obtainStyledAttributes.getInt(0, 1);
            properties.spanCount = obtainStyledAttributes.getInt(10, 1);
            properties.reverseLayout = obtainStyledAttributes.getBoolean(9, false);
            properties.stackFromEnd = obtainStyledAttributes.getBoolean(11, false);
            obtainStyledAttributes.recycle();
            return properties;
        }

        public static boolean isMeasurementUpToDate(int i, int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            if (i3 > 0 && i != i3) {
                return false;
            }
            if (mode == Integer.MIN_VALUE) {
                return size >= i;
            }
            if (mode != 0) {
                return mode == 1073741824 && size == i;
            }
            return true;
        }

        public void addView(View view) {
            addViewInt(view, -1, false);
        }

        public final void addViewInt(View view, int i, boolean z) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (z || childViewHolderInt.isRemoved()) {
                this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(childViewHolderInt);
            } else {
                this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(childViewHolderInt);
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (childViewHolderInt.wasReturnedFromScrap() || childViewHolderInt.isScrap()) {
                if (childViewHolderInt.isScrap()) {
                    childViewHolderInt.mScrapContainer.unscrapView(childViewHolderInt);
                } else {
                    childViewHolderInt.clearReturnedFromScrapFlag();
                }
                this.mChildHelper.attachViewToParent(view, i, view.getLayoutParams(), false);
            } else {
                int i2 = -1;
                if (view.getParent() == this.mRecyclerView) {
                    int indexOfChild = this.mChildHelper.indexOfChild(view);
                    if (i == -1) {
                        i = this.mChildHelper.getChildCount();
                    }
                    if (indexOfChild == -1) {
                        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:");
                        outline13.append(this.mRecyclerView.indexOfChild(view));
                        throw new IllegalStateException(GeneratedOutlineSupport.outline2(this.mRecyclerView, outline13));
                    } else if (indexOfChild != i) {
                        LayoutManager layoutManager = this.mRecyclerView.mLayout;
                        View childAt = layoutManager.getChildAt(indexOfChild);
                        if (childAt != null) {
                            layoutManager.getChildAt(indexOfChild);
                            layoutManager.detachViewInternal(indexOfChild);
                            LayoutParams layoutParams2 = (LayoutParams) childAt.getLayoutParams();
                            ViewHolder childViewHolderInt2 = RecyclerView.getChildViewHolderInt(childAt);
                            if (childViewHolderInt2.isRemoved()) {
                                layoutManager.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(childViewHolderInt2);
                            } else {
                                layoutManager.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(childViewHolderInt2);
                            }
                            layoutManager.mChildHelper.attachViewToParent(childAt, i, layoutParams2, childViewHolderInt2.isRemoved());
                        } else {
                            throw new IllegalArgumentException("Cannot move a child from non-existing index:" + indexOfChild + layoutManager.mRecyclerView.toString());
                        }
                    }
                } else {
                    this.mChildHelper.addView(view, i, false);
                    layoutParams.mInsetsDirty = true;
                    SmoothScroller smoothScroller = this.mSmoothScroller;
                    if (smoothScroller != null && smoothScroller.mRunning) {
                        Objects.requireNonNull(smoothScroller.mRecyclerView);
                        ViewHolder childViewHolderInt3 = RecyclerView.getChildViewHolderInt(view);
                        if (childViewHolderInt3 != null) {
                            i2 = childViewHolderInt3.getLayoutPosition();
                        }
                        if (i2 == smoothScroller.mTargetPosition) {
                            smoothScroller.mTargetView = view;
                        }
                    }
                }
            }
            if (layoutParams.mPendingInvalidate) {
                childViewHolderInt.itemView.invalidate();
                layoutParams.mPendingInvalidate = false;
            }
        }

        public void assertNotInLayoutOrScroll(String str) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.assertNotInLayoutOrScroll(str);
            }
        }

        public boolean canScrollHorizontally() {
            return false;
        }

        public boolean canScrollVertically() {
            return false;
        }

        public boolean checkLayoutParams(LayoutParams layoutParams) {
            return layoutParams != null;
        }

        public void collectAdjacentPrefetchPositions(int i, int i2, State state, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        }

        public void collectInitialPrefetchPositions(int i, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        }

        public int computeHorizontalScrollExtent(State state) {
            return 0;
        }

        public int computeHorizontalScrollOffset(State state) {
            return 0;
        }

        public int computeHorizontalScrollRange(State state) {
            return 0;
        }

        public int computeVerticalScrollExtent(State state) {
            return 0;
        }

        public int computeVerticalScrollOffset(State state) {
            return 0;
        }

        public int computeVerticalScrollRange(State state) {
            return 0;
        }

        public void detachAndScrapAttachedViews(Recycler recycler) {
            int childCount = getChildCount();
            while (true) {
                childCount--;
                if (childCount >= 0) {
                    View childAt = getChildAt(childCount);
                    ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt);
                    if (!childViewHolderInt.shouldIgnore()) {
                        if (!childViewHolderInt.isInvalid() || childViewHolderInt.isRemoved() || this.mRecyclerView.mAdapter.mHasStableIds) {
                            getChildAt(childCount);
                            detachViewInternal(childCount);
                            recycler.scrapView(childAt);
                            this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(childViewHolderInt);
                        } else {
                            removeViewAt(childCount);
                            recycler.recycleViewHolderInternal(childViewHolderInt);
                        }
                    }
                } else {
                    return;
                }
            }
        }

        public final void detachViewInternal(int i) {
            this.mChildHelper.detachViewFromParent(i);
        }

        public View findContainingItemView(View view) {
            View findContainingItemView;
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null || (findContainingItemView = recyclerView.findContainingItemView(view)) == null || this.mChildHelper.mHiddenViews.contains(findContainingItemView)) {
                return null;
            }
            return findContainingItemView;
        }

        public View findViewByPosition(int i) {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt);
                if (childViewHolderInt != null && childViewHolderInt.getLayoutPosition() == i && !childViewHolderInt.shouldIgnore() && (this.mRecyclerView.mState.mInPreLayout || !childViewHolderInt.isRemoved())) {
                    return childAt;
                }
            }
            return null;
        }

        public abstract LayoutParams generateDefaultLayoutParams();

        public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
            if (layoutParams instanceof LayoutParams) {
                return new LayoutParams((LayoutParams) layoutParams);
            }
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
            }
            return new LayoutParams(layoutParams);
        }

        public View getChildAt(int i) {
            ChildHelper childHelper = this.mChildHelper;
            if (childHelper == null) {
                return null;
            }
            return ((AnonymousClass5) childHelper.mCallback).getChildAt(childHelper.getOffset(i));
        }

        public int getChildCount() {
            ChildHelper childHelper = this.mChildHelper;
            if (childHelper != null) {
                return childHelper.getChildCount();
            }
            return 0;
        }

        public int getColumnCountForAccessibility(Recycler recycler, State state) {
            return -1;
        }

        public int getDecoratedBottom(View view) {
            return view.getBottom() + ((LayoutParams) view.getLayoutParams()).mDecorInsets.bottom;
        }

        public void getDecoratedBoundsWithMargins(View view, Rect rect) {
            int[] iArr = RecyclerView.NESTED_SCROLLING_ATTRS;
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect rect2 = layoutParams.mDecorInsets;
            rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
        }

        public int getDecoratedLeft(View view) {
            return view.getLeft() - ((LayoutParams) view.getLayoutParams()).mDecorInsets.left;
        }

        public int getDecoratedMeasuredHeight(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).mDecorInsets;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public int getDecoratedMeasuredWidth(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).mDecorInsets;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public int getDecoratedRight(View view) {
            return view.getRight() + ((LayoutParams) view.getLayoutParams()).mDecorInsets.right;
        }

        public int getDecoratedTop(View view) {
            return view.getTop() - ((LayoutParams) view.getLayoutParams()).mDecorInsets.top;
        }

        public View getFocusedChild() {
            View focusedChild;
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.mChildHelper.mHiddenViews.contains(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        public int getItemCount() {
            RecyclerView recyclerView = this.mRecyclerView;
            Adapter adapter = recyclerView != null ? recyclerView.getAdapter() : null;
            if (adapter != null) {
                return adapter.getItemCount();
            }
            return 0;
        }

        public int getLayoutDirection() {
            RecyclerView recyclerView = this.mRecyclerView;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            return recyclerView.getLayoutDirection();
        }

        public int getMinimumHeight() {
            RecyclerView recyclerView = this.mRecyclerView;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            return recyclerView.getMinimumHeight();
        }

        public int getMinimumWidth() {
            RecyclerView recyclerView = this.mRecyclerView;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            return recyclerView.getMinimumWidth();
        }

        public int getPaddingBottom() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        public int getPaddingLeft() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        public int getPaddingRight() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        public int getPaddingTop() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        public int getPosition(View view) {
            return ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        }

        public int getRowCountForAccessibility(Recycler recycler, State state) {
            return -1;
        }

        public int getSelectionModeForAccessibility() {
            return 0;
        }

        public void getTransformedBoundingBox(View view, boolean z, Rect rect) {
            Matrix matrix;
            if (z) {
                Rect rect2 = ((LayoutParams) view.getLayoutParams()).mDecorInsets;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (!(this.mRecyclerView == null || (matrix = view.getMatrix()) == null || matrix.isIdentity())) {
                RectF rectF = this.mRecyclerView.mTempRectF;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public boolean isAutoMeasureEnabled() {
            return false;
        }

        public boolean isLayoutHierarchical() {
            return false;
        }

        public boolean isViewPartiallyVisible(View view, boolean z) {
            boolean z2 = this.mHorizontalBoundCheck.isViewWithinBoundFlags(view, 24579) && this.mVerticalBoundCheck.isViewWithinBoundFlags(view, 24579);
            return z ? z2 : !z2;
        }

        public void layoutDecoratedWithMargins(View view, int i, int i2, int i3, int i4) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect rect = layoutParams.mDecorInsets;
            view.layout(i + rect.left + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, i2 + rect.top + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, (i3 - rect.right) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, (i4 - rect.bottom) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
        }

        public void offsetChildrenHorizontal(int i) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                int childCount = recyclerView.mChildHelper.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    recyclerView.mChildHelper.getChildAt(i2).offsetLeftAndRight(i);
                }
            }
        }

        public void offsetChildrenVertical(int i) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                int childCount = recyclerView.mChildHelper.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    recyclerView.mChildHelper.getChildAt(i2).offsetTopAndBottom(i);
                }
            }
        }

        public void onAdapterChanged(Adapter adapter, Adapter adapter2) {
        }

        public boolean onAddFocusables() {
            return false;
        }

        public void onAttachedToWindow() {
        }

        @Deprecated
        public void onDetachedFromWindow() {
        }

        public void onDetachedFromWindow(RecyclerView recyclerView, Recycler recycler) {
            onDetachedFromWindow();
        }

        public View onFocusSearchFailed(View view, int i, Recycler recycler, State state) {
            return null;
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.mRecyclerView;
            Recycler recycler = recyclerView.mRecycler;
            State state = recyclerView.mState;
            onInitializeAccessibilityEvent1(accessibilityEvent);
        }

        public void onInitializeAccessibilityEvent1(AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null && accessibilityEvent != null) {
                boolean z = true;
                if (!recyclerView.canScrollVertically(1) && !this.mRecyclerView.canScrollVertically(-1) && !this.mRecyclerView.canScrollHorizontally(-1) && !this.mRecyclerView.canScrollHorizontally(1)) {
                    z = false;
                }
                accessibilityEvent.setScrollable(z);
                Adapter adapter = this.mRecyclerView.mAdapter;
                if (adapter != null) {
                    accessibilityEvent.setItemCount(adapter.getItemCount());
                }
            }
        }

        public void onInitializeAccessibilityNodeInfo(Recycler recycler, State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (this.mRecyclerView.canScrollVertically(-1) || this.mRecyclerView.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.mInfo.addAction(8192);
                accessibilityNodeInfoCompat.mInfo.setScrollable(true);
            }
            if (this.mRecyclerView.canScrollVertically(1) || this.mRecyclerView.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.mInfo.addAction(4096);
                accessibilityNodeInfoCompat.mInfo.setScrollable(true);
            }
            accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(getRowCountForAccessibility(recycler, state), getColumnCountForAccessibility(recycler, state), isLayoutHierarchical(), getSelectionModeForAccessibility()));
        }

        public void onInitializeAccessibilityNodeInfoForItem(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && !this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                RecyclerView recyclerView = this.mRecyclerView;
                onInitializeAccessibilityNodeInfoForItem(recyclerView.mRecycler, recyclerView.mState, view, accessibilityNodeInfoCompat);
            }
        }

        public void onInitializeAccessibilityNodeInfoForItem(Recycler recycler, State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        }

        public View onInterceptFocusSearch() {
            return null;
        }

        public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        }

        public void onItemsChanged(RecyclerView recyclerView) {
        }

        public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        }

        public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        }

        public void onItemsUpdated() {
        }

        public void onItemsUpdated(RecyclerView recyclerView, int i, int i2, Object obj) {
            onItemsUpdated();
        }

        public void onLayoutChildren(Recycler recycler, State state) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public void onLayoutCompleted(State state) {
        }

        public void onMeasure(int i, int i2) {
            this.mRecyclerView.defaultOnMeasure(i, i2);
        }

        @Deprecated
        public boolean onRequestChildFocus(RecyclerView recyclerView) {
            SmoothScroller smoothScroller = this.mSmoothScroller;
            return (smoothScroller != null && smoothScroller.mRunning) || recyclerView.isComputingLayout();
        }

        public boolean onRequestChildFocus1(RecyclerView recyclerView, View view, View view2) {
            return onRequestChildFocus(recyclerView);
        }

        public void onRestoreInstanceState(Parcelable parcelable) {
        }

        public Parcelable onSaveInstanceState() {
            return null;
        }

        public void onScrollStateChanged(int i) {
        }

        public boolean performAccessibilityAction(Recycler recycler, State state, int i, Bundle bundle) {
            int i2;
            int i3;
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null) {
                return false;
            }
            if (i == 4096) {
                i2 = recyclerView.canScrollVertically(1) ? (this.mHeight - getPaddingTop()) - getPaddingBottom() : 0;
                if (this.mRecyclerView.canScrollHorizontally(1)) {
                    i3 = (this.mWidth - getPaddingLeft()) - getPaddingRight();
                }
                i3 = 0;
            } else if (i != 8192) {
                i3 = 0;
                i2 = 0;
            } else {
                i2 = recyclerView.canScrollVertically(-1) ? -((this.mHeight - getPaddingTop()) - getPaddingBottom()) : 0;
                if (this.mRecyclerView.canScrollHorizontally(-1)) {
                    i3 = -((this.mWidth - getPaddingLeft()) - getPaddingRight());
                }
                i3 = 0;
            }
            if (i2 == 0 && i3 == 0) {
                return false;
            }
            this.mRecyclerView.smoothScrollBy(i3, i2, null, Integer.MIN_VALUE, true);
            return true;
        }

        public boolean performAccessibilityActionForItem() {
            return false;
        }

        public void removeAndRecycleAllViews(Recycler recycler) {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                if (!RecyclerView.getChildViewHolderInt(getChildAt(childCount)).shouldIgnore()) {
                    removeAndRecycleViewAt(childCount, recycler);
                }
            }
        }

        public void removeAndRecycleScrapInt(Recycler recycler) {
            int size = recycler.mAttachedScrap.size();
            for (int i = size - 1; i >= 0; i--) {
                View view = recycler.mAttachedScrap.get(i).itemView;
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (!childViewHolderInt.shouldIgnore()) {
                    childViewHolderInt.setIsRecyclable(false);
                    if (childViewHolderInt.isTmpDetached()) {
                        this.mRecyclerView.removeDetachedView(view, false);
                    }
                    ItemAnimator itemAnimator = this.mRecyclerView.mItemAnimator;
                    if (itemAnimator != null) {
                        itemAnimator.endAnimation(childViewHolderInt);
                    }
                    childViewHolderInt.setIsRecyclable(true);
                    ViewHolder childViewHolderInt2 = RecyclerView.getChildViewHolderInt(view);
                    childViewHolderInt2.mScrapContainer = null;
                    childViewHolderInt2.mInChangeScrap = false;
                    childViewHolderInt2.clearReturnedFromScrapFlag();
                    recycler.recycleViewHolderInternal(childViewHolderInt2);
                }
            }
            recycler.mAttachedScrap.clear();
            ArrayList<ViewHolder> arrayList = recycler.mChangedScrap;
            if (arrayList != null) {
                arrayList.clear();
            }
            if (size > 0) {
                this.mRecyclerView.invalidate();
            }
        }

        public void removeAndRecycleView(View view, Recycler recycler) {
            ChildHelper childHelper = this.mChildHelper;
            int indexOfChild = RecyclerView.this.indexOfChild(view);
            if (indexOfChild >= 0) {
                if (childHelper.mBucket.remove(indexOfChild)) {
                    childHelper.unhideViewInternal(view);
                }
                ((AnonymousClass5) childHelper.mCallback).removeViewAt(indexOfChild);
            }
            recycler.recycleView(view);
        }

        public void removeAndRecycleViewAt(int i, Recycler recycler) {
            View childAt = getChildAt(i);
            removeViewAt(i);
            recycler.recycleView(childAt);
        }

        public void removeViewAt(int i) {
            ChildHelper childHelper;
            int offset;
            View childAt;
            if (getChildAt(i) != null && (childAt = ((AnonymousClass5) childHelper.mCallback).getChildAt((offset = (childHelper = this.mChildHelper).getOffset(i)))) != null) {
                if (childHelper.mBucket.remove(offset)) {
                    childHelper.unhideViewInternal(childAt);
                }
                ((AnonymousClass5) childHelper.mCallback).removeViewAt(offset);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:25:0x00b8, code lost:
            if (r1 == false) goto L_0x00bf;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean requestChildRectangleOnScreen(androidx.recyclerview.widget.RecyclerView r19, android.view.View r20, android.graphics.Rect r21, boolean r22, boolean r23) {
            /*
                r18 = this;
                r0 = r18
                r1 = r21
                r2 = 2
                int[] r2 = new int[r2]
                int r3 = r18.getPaddingLeft()
                int r4 = r18.getPaddingTop()
                int r5 = r0.mWidth
                int r6 = r18.getPaddingRight()
                int r5 = r5 - r6
                int r6 = r0.mHeight
                int r7 = r18.getPaddingBottom()
                int r6 = r6 - r7
                int r7 = r20.getLeft()
                int r8 = r1.left
                int r7 = r7 + r8
                int r8 = r20.getScrollX()
                int r7 = r7 - r8
                int r8 = r20.getTop()
                int r9 = r1.top
                int r8 = r8 + r9
                int r9 = r20.getScrollY()
                int r8 = r8 - r9
                int r9 = r21.width()
                int r9 = r9 + r7
                int r1 = r21.height()
                int r1 = r1 + r8
                int r7 = r7 - r3
                r3 = 0
                int r10 = java.lang.Math.min(r3, r7)
                int r8 = r8 - r4
                int r4 = java.lang.Math.min(r3, r8)
                int r9 = r9 - r5
                int r5 = java.lang.Math.max(r3, r9)
                int r1 = r1 - r6
                int r1 = java.lang.Math.max(r3, r1)
                int r6 = r18.getLayoutDirection()
                r11 = 1
                if (r6 != r11) goto L_0x0063
                if (r5 == 0) goto L_0x005e
                goto L_0x006b
            L_0x005e:
                int r5 = java.lang.Math.max(r10, r9)
                goto L_0x006b
            L_0x0063:
                if (r10 == 0) goto L_0x0066
                goto L_0x006a
            L_0x0066:
                int r10 = java.lang.Math.min(r7, r5)
            L_0x006a:
                r5 = r10
            L_0x006b:
                if (r4 == 0) goto L_0x006e
                goto L_0x0072
            L_0x006e:
                int r4 = java.lang.Math.min(r8, r1)
            L_0x0072:
                r2[r3] = r5
                r2[r11] = r4
                r13 = r2[r3]
                r14 = r2[r11]
                if (r23 == 0) goto L_0x00ba
                android.view.View r1 = r19.getFocusedChild()
                if (r1 != 0) goto L_0x0083
                goto L_0x00b7
            L_0x0083:
                int r2 = r18.getPaddingLeft()
                int r4 = r18.getPaddingTop()
                int r5 = r0.mWidth
                int r6 = r18.getPaddingRight()
                int r5 = r5 - r6
                int r6 = r0.mHeight
                int r7 = r18.getPaddingBottom()
                int r6 = r6 - r7
                androidx.recyclerview.widget.RecyclerView r7 = r0.mRecyclerView
                android.graphics.Rect r7 = r7.mTempRect
                r0.getDecoratedBoundsWithMargins(r1, r7)
                int r1 = r7.left
                int r1 = r1 - r13
                if (r1 >= r5) goto L_0x00b7
                int r1 = r7.right
                int r1 = r1 - r13
                if (r1 <= r2) goto L_0x00b7
                int r1 = r7.top
                int r1 = r1 - r14
                if (r1 >= r6) goto L_0x00b7
                int r1 = r7.bottom
                int r1 = r1 - r14
                if (r1 > r4) goto L_0x00b5
                goto L_0x00b7
            L_0x00b5:
                r1 = 1
                goto L_0x00b8
            L_0x00b7:
                r1 = 0
            L_0x00b8:
                if (r1 == 0) goto L_0x00bf
            L_0x00ba:
                if (r13 != 0) goto L_0x00c0
                if (r14 == 0) goto L_0x00bf
                goto L_0x00c0
            L_0x00bf:
                return r3
            L_0x00c0:
                if (r22 == 0) goto L_0x00c8
                r1 = r19
                r1.scrollBy(r13, r14)
                goto L_0x00d4
            L_0x00c8:
                r1 = r19
                r17 = 0
                r16 = -2147483648(0xffffffff80000000, float:-0.0)
                r15 = 0
                r12 = r19
                r12.smoothScrollBy(r13, r14, r15, r16, r17)
            L_0x00d4:
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.LayoutManager.requestChildRectangleOnScreen(androidx.recyclerview.widget.RecyclerView, android.view.View, android.graphics.Rect, boolean, boolean):boolean");
        }

        public void requestLayout() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        public int scrollHorizontallyBy(int i, Recycler recycler, State state) {
            return 0;
        }

        public void scrollToPosition(int i) {
        }

        public int scrollVerticallyBy(int i, Recycler recycler, State state) {
            return 0;
        }

        public void setExactMeasureSpecsFrom(RecyclerView recyclerView) {
            setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
        }

        public void setMeasureSpecs(int i, int i2) {
            this.mWidth = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            this.mWidthMode = mode;
            if (mode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.mWidth = 0;
            }
            this.mHeight = View.MeasureSpec.getSize(i2);
            int mode2 = View.MeasureSpec.getMode(i2);
            this.mHeightMode = mode2;
            if (mode2 == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.mHeight = 0;
            }
        }

        public void setMeasuredDimension(Rect rect, int i, int i2) {
            int paddingRight = getPaddingRight() + getPaddingLeft() + rect.width();
            int paddingBottom = getPaddingBottom() + getPaddingTop() + rect.height();
            this.mRecyclerView.setMeasuredDimension(chooseSize(i, paddingRight, getMinimumWidth()), chooseSize(i2, paddingBottom, getMinimumHeight()));
        }

        public void setMeasuredDimensionFromChildren(int i, int i2) {
            int childCount = getChildCount();
            if (childCount == 0) {
                this.mRecyclerView.defaultOnMeasure(i, i2);
                return;
            }
            int i3 = Integer.MIN_VALUE;
            int i4 = Integer.MIN_VALUE;
            int i5 = Integer.MAX_VALUE;
            int i6 = Integer.MAX_VALUE;
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = getChildAt(i7);
                Rect rect = this.mRecyclerView.mTempRect;
                getDecoratedBoundsWithMargins(childAt, rect);
                int i8 = rect.left;
                if (i8 < i5) {
                    i5 = i8;
                }
                int i9 = rect.right;
                if (i9 > i3) {
                    i3 = i9;
                }
                int i10 = rect.top;
                if (i10 < i6) {
                    i6 = i10;
                }
                int i11 = rect.bottom;
                if (i11 > i4) {
                    i4 = i11;
                }
            }
            this.mRecyclerView.mTempRect.set(i5, i6, i3, i4);
            setMeasuredDimension(this.mRecyclerView.mTempRect, i, i2);
        }

        public void setRecyclerView(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.mRecyclerView = null;
                this.mChildHelper = null;
                this.mWidth = 0;
                this.mHeight = 0;
            } else {
                this.mRecyclerView = recyclerView;
                this.mChildHelper = recyclerView.mChildHelper;
                this.mWidth = recyclerView.getWidth();
                this.mHeight = recyclerView.getHeight();
            }
            this.mWidthMode = 1073741824;
            this.mHeightMode = 1073741824;
        }

        public boolean shouldMeasureChild(View view, int i, int i2, LayoutParams layoutParams) {
            return view.isLayoutRequested() || !this.mMeasurementCacheEnabled || !isMeasurementUpToDate(view.getWidth(), i, ((ViewGroup.MarginLayoutParams) layoutParams).width) || !isMeasurementUpToDate(view.getHeight(), i2, ((ViewGroup.MarginLayoutParams) layoutParams).height);
        }

        public boolean shouldMeasureTwice() {
            return false;
        }

        public boolean shouldReMeasureChild(View view, int i, int i2, LayoutParams layoutParams) {
            return !this.mMeasurementCacheEnabled || !isMeasurementUpToDate(view.getMeasuredWidth(), i, ((ViewGroup.MarginLayoutParams) layoutParams).width) || !isMeasurementUpToDate(view.getMeasuredHeight(), i2, ((ViewGroup.MarginLayoutParams) layoutParams).height);
        }

        public void smoothScrollToPosition(RecyclerView recyclerView, State state, int i) {
            Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public void startSmoothScroll(SmoothScroller smoothScroller) {
            SmoothScroller smoothScroller2 = this.mSmoothScroller;
            if (!(smoothScroller2 == null || smoothScroller == smoothScroller2 || !smoothScroller2.mRunning)) {
                smoothScroller2.stop();
            }
            this.mSmoothScroller = smoothScroller;
            RecyclerView recyclerView = this.mRecyclerView;
            recyclerView.mViewFlinger.stop();
            if (smoothScroller.mStarted) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("An instance of ");
                outline13.append(smoothScroller.getClass().getSimpleName());
                outline13.append(" was started more than once. Each instance of");
                outline13.append(smoothScroller.getClass().getSimpleName());
                outline13.append(" is intended to only be used once. You should create a new instance for each use.");
                Log.w("RecyclerView", outline13.toString());
            }
            smoothScroller.mRecyclerView = recyclerView;
            smoothScroller.mLayoutManager = this;
            int i = smoothScroller.mTargetPosition;
            if (i != -1) {
                recyclerView.mState.mTargetPosition = i;
                smoothScroller.mRunning = true;
                smoothScroller.mPendingInitialRun = true;
                smoothScroller.mTargetView = recyclerView.mLayout.findViewByPosition(i);
                smoothScroller.mRecyclerView.mViewFlinger.postOnAnimation();
                smoothScroller.mStarted = true;
                return;
            }
            throw new IllegalArgumentException("Invalid target position");
        }

        public boolean supportsPredictiveItemAnimations() {
            return false;
        }

        public LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
            return new LayoutParams(context, attributeSet);
        }
    }
}
