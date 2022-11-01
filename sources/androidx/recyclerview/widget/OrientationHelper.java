package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes.dex */
public abstract class OrientationHelper {
    public final RecyclerView.LayoutManager mLayoutManager;
    public int mLastTotalSpace = Integer.MIN_VALUE;
    public final Rect mTmpRect = new Rect();

    /* renamed from: androidx.recyclerview.widget.OrientationHelper$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 extends OrientationHelper {
        public AnonymousClass1(RecyclerView.LayoutManager layoutManager) {
            super(layoutManager, null);
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedEnd(View view) {
            return this.mLayoutManager.getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).rightMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedMeasurement(View view) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return this.mLayoutManager.getDecoratedMeasuredWidth(view) + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedMeasurementInOther(View view) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return this.mLayoutManager.getDecoratedMeasuredHeight(view) + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedStart(View view) {
            return this.mLayoutManager.getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).leftMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getEnd() {
            return this.mLayoutManager.mWidth;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getEndAfterPadding() {
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            return layoutManager.mWidth - layoutManager.getPaddingRight();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getEndPadding() {
            return this.mLayoutManager.getPaddingRight();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getMode() {
            return this.mLayoutManager.mWidthMode;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getModeInOther() {
            return this.mLayoutManager.mHeightMode;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getStartAfterPadding() {
            return this.mLayoutManager.getPaddingLeft();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getTotalSpace() {
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            return (layoutManager.mWidth - layoutManager.getPaddingLeft()) - this.mLayoutManager.getPaddingRight();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getTransformedEndWithDecoration(View view) {
            this.mLayoutManager.getTransformedBoundingBox(view, true, this.mTmpRect);
            return this.mTmpRect.right;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getTransformedStartWithDecoration(View view) {
            this.mLayoutManager.getTransformedBoundingBox(view, true, this.mTmpRect);
            return this.mTmpRect.left;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public void offsetChildren(int i) {
            this.mLayoutManager.offsetChildrenHorizontal(i);
        }
    }

    /* renamed from: androidx.recyclerview.widget.OrientationHelper$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass2 extends OrientationHelper {
        public AnonymousClass2(RecyclerView.LayoutManager layoutManager) {
            super(layoutManager, null);
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedEnd(View view) {
            return this.mLayoutManager.getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedMeasurement(View view) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return this.mLayoutManager.getDecoratedMeasuredHeight(view) + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedMeasurementInOther(View view) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return this.mLayoutManager.getDecoratedMeasuredWidth(view) + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedStart(View view) {
            return this.mLayoutManager.getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).topMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getEnd() {
            return this.mLayoutManager.mHeight;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getEndAfterPadding() {
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            return layoutManager.mHeight - layoutManager.getPaddingBottom();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getEndPadding() {
            return this.mLayoutManager.getPaddingBottom();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getMode() {
            return this.mLayoutManager.mHeightMode;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getModeInOther() {
            return this.mLayoutManager.mWidthMode;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getStartAfterPadding() {
            return this.mLayoutManager.getPaddingTop();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getTotalSpace() {
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            return (layoutManager.mHeight - layoutManager.getPaddingTop()) - this.mLayoutManager.getPaddingBottom();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getTransformedEndWithDecoration(View view) {
            this.mLayoutManager.getTransformedBoundingBox(view, true, this.mTmpRect);
            return this.mTmpRect.bottom;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getTransformedStartWithDecoration(View view) {
            this.mLayoutManager.getTransformedBoundingBox(view, true, this.mTmpRect);
            return this.mTmpRect.top;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public void offsetChildren(int i) {
            this.mLayoutManager.offsetChildrenVertical(i);
        }
    }

    public OrientationHelper(RecyclerView.LayoutManager layoutManager, AnonymousClass1 r2) {
        this.mLayoutManager = layoutManager;
    }

    public static OrientationHelper createOrientationHelper(RecyclerView.LayoutManager layoutManager, int i) {
        if (i == 0) {
            return new AnonymousClass1(layoutManager);
        }
        if (i == 1) {
            return new AnonymousClass2(layoutManager);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public abstract int getDecoratedEnd(View view);

    public abstract int getDecoratedMeasurement(View view);

    public abstract int getDecoratedMeasurementInOther(View view);

    public abstract int getDecoratedStart(View view);

    public abstract int getEnd();

    public abstract int getEndAfterPadding();

    public abstract int getEndPadding();

    public abstract int getMode();

    public abstract int getModeInOther();

    public abstract int getStartAfterPadding();

    public abstract int getTotalSpace();

    public int getTotalSpaceChange() {
        if (Integer.MIN_VALUE == this.mLastTotalSpace) {
            return 0;
        }
        return getTotalSpace() - this.mLastTotalSpace;
    }

    public abstract int getTransformedEndWithDecoration(View view);

    public abstract int getTransformedStartWithDecoration(View view);

    public abstract void offsetChildren(int i);
}
