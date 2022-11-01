package androidx.viewpager2.widget;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
/* loaded from: classes.dex */
public final class ScrollEventAdapter extends RecyclerView.OnScrollListener {
    public int mAdapterState;
    public ViewPager2.OnPageChangeCallback mCallback;
    public boolean mDataSetChangeHappened;
    public boolean mDispatchSelected;
    public int mDragStartPosition;
    public boolean mFakeDragging;
    public final LinearLayoutManager mLayoutManager;
    public final RecyclerView mRecyclerView;
    public boolean mScrollHappened;
    public int mScrollState;
    public ScrollEventValues mScrollValues = new ScrollEventValues();
    public int mTarget;
    public final ViewPager2 mViewPager;

    /* loaded from: classes.dex */
    public static final class ScrollEventValues {
        public float mOffset;
        public int mOffsetPx;
        public int mPosition;

        public void reset() {
            this.mPosition = -1;
            this.mOffset = 0.0f;
            this.mOffsetPx = 0;
        }
    }

    public ScrollEventAdapter(ViewPager2 viewPager2) {
        this.mViewPager = viewPager2;
        RecyclerView recyclerView = viewPager2.mRecyclerView;
        this.mRecyclerView = recyclerView;
        this.mLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        resetState();
    }

    public final void dispatchSelected(int i) {
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.mCallback;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.onPageSelected(i);
        }
    }

    public final void dispatchStateChanged(int i) {
        if ((this.mAdapterState != 3 || this.mScrollState != 0) && this.mScrollState != i) {
            this.mScrollState = i;
            ViewPager2.OnPageChangeCallback onPageChangeCallback = this.mCallback;
            if (onPageChangeCallback != null) {
                onPageChangeCallback.onPageScrollStateChanged(i);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        ViewPager2.OnPageChangeCallback onPageChangeCallback;
        int i2 = this.mAdapterState;
        boolean z = true;
        if (!(i2 == 1 && this.mScrollState == 1) && i == 1) {
            this.mFakeDragging = false;
            this.mAdapterState = 1;
            int i3 = this.mTarget;
            if (i3 != -1) {
                this.mDragStartPosition = i3;
                this.mTarget = -1;
            } else if (this.mDragStartPosition == -1) {
                this.mDragStartPosition = this.mLayoutManager.findFirstVisibleItemPosition();
            }
            dispatchStateChanged(1);
            return;
        }
        if (!(i2 == 1 || i2 == 4) || i != 2) {
            if ((i2 == 1 || i2 == 4) && i == 0) {
                updateScrollEventValues();
                if (!this.mScrollHappened) {
                    int i4 = this.mScrollValues.mPosition;
                    if (!(i4 == -1 || (onPageChangeCallback = this.mCallback) == null)) {
                        onPageChangeCallback.onPageScrolled(i4, 0.0f, 0);
                    }
                } else {
                    ScrollEventValues scrollEventValues = this.mScrollValues;
                    if (scrollEventValues.mOffsetPx == 0) {
                        int i5 = this.mDragStartPosition;
                        int i6 = scrollEventValues.mPosition;
                        if (i5 != i6) {
                            dispatchSelected(i6);
                        }
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    dispatchStateChanged(0);
                    resetState();
                }
            }
            if (this.mAdapterState == 2 && i == 0 && this.mDataSetChangeHappened) {
                updateScrollEventValues();
                ScrollEventValues scrollEventValues2 = this.mScrollValues;
                if (scrollEventValues2.mOffsetPx == 0) {
                    int i7 = this.mTarget;
                    int i8 = scrollEventValues2.mPosition;
                    if (i7 != i8) {
                        if (i8 == -1) {
                            i8 = 0;
                        }
                        dispatchSelected(i8);
                    }
                    dispatchStateChanged(0);
                    resetState();
                }
            }
        } else if (this.mScrollHappened) {
            dispatchStateChanged(2);
            this.mDispatchSelected = true;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001d, code lost:
        if ((r6 < 0) == r4.mViewPager.isRtl()) goto L_0x0022;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0039  */
    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onScrolled(androidx.recyclerview.widget.RecyclerView r5, int r6, int r7) {
        /*
            r4 = this;
            r5 = 1
            r4.mScrollHappened = r5
            r4.updateScrollEventValues()
            boolean r0 = r4.mDispatchSelected
            r1 = 0
            r2 = -1
            if (r0 == 0) goto L_0x003d
            r4.mDispatchSelected = r1
            if (r7 > 0) goto L_0x0022
            if (r7 != 0) goto L_0x0020
            if (r6 >= 0) goto L_0x0016
            r6 = 1
            goto L_0x0017
        L_0x0016:
            r6 = 0
        L_0x0017:
            androidx.viewpager2.widget.ViewPager2 r7 = r4.mViewPager
            boolean r7 = r7.isRtl()
            if (r6 != r7) goto L_0x0020
            goto L_0x0022
        L_0x0020:
            r6 = 0
            goto L_0x0023
        L_0x0022:
            r6 = 1
        L_0x0023:
            if (r6 == 0) goto L_0x002f
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r6 = r4.mScrollValues
            int r7 = r6.mOffsetPx
            if (r7 == 0) goto L_0x002f
            int r6 = r6.mPosition
            int r6 = r6 + r5
            goto L_0x0033
        L_0x002f:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r6 = r4.mScrollValues
            int r6 = r6.mPosition
        L_0x0033:
            r4.mTarget = r6
            int r7 = r4.mDragStartPosition
            if (r7 == r6) goto L_0x004b
            r4.dispatchSelected(r6)
            goto L_0x004b
        L_0x003d:
            int r6 = r4.mAdapterState
            if (r6 != 0) goto L_0x004b
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r6 = r4.mScrollValues
            int r6 = r6.mPosition
            if (r6 != r2) goto L_0x0048
            r6 = 0
        L_0x0048:
            r4.dispatchSelected(r6)
        L_0x004b:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r6 = r4.mScrollValues
            int r7 = r6.mPosition
            if (r7 != r2) goto L_0x0052
            r7 = 0
        L_0x0052:
            float r0 = r6.mOffset
            int r6 = r6.mOffsetPx
            androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback r3 = r4.mCallback
            if (r3 == 0) goto L_0x005d
            r3.onPageScrolled(r7, r0, r6)
        L_0x005d:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r6 = r4.mScrollValues
            int r7 = r6.mPosition
            int r0 = r4.mTarget
            if (r7 == r0) goto L_0x0067
            if (r0 != r2) goto L_0x0075
        L_0x0067:
            int r6 = r6.mOffsetPx
            if (r6 != 0) goto L_0x0075
            int r6 = r4.mScrollState
            if (r6 == r5) goto L_0x0075
            r4.dispatchStateChanged(r1)
            r4.resetState()
        L_0x0075:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager2.widget.ScrollEventAdapter.onScrolled(androidx.recyclerview.widget.RecyclerView, int, int):void");
    }

    public final void resetState() {
        this.mAdapterState = 0;
        this.mScrollState = 0;
        this.mScrollValues.reset();
        this.mDragStartPosition = -1;
        this.mTarget = -1;
        this.mDispatchSelected = false;
        this.mScrollHappened = false;
        this.mFakeDragging = false;
        this.mDataSetChangeHappened = false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x0151, code lost:
        if (r4[r2 - 1][1] >= r3) goto L_0x0154;
     */
    /* JADX WARN: Removed duplicated region for block: B:66:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x017b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateScrollEventValues() {
        /*
            Method dump skipped, instructions count: 429
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager2.widget.ScrollEventAdapter.updateScrollEventValues():void");
    }
}
