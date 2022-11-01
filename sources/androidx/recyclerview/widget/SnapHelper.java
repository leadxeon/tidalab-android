package androidx.recyclerview.widget;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
/* loaded from: classes.dex */
public abstract class SnapHelper extends RecyclerView.OnFlingListener {
    public RecyclerView mRecyclerView;
    public final RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() { // from class: androidx.recyclerview.widget.SnapHelper.1
        public boolean mScrolled = false;

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            if (i == 0 && this.mScrolled) {
                this.mScrolled = false;
                SnapHelper.this.snapToTargetExistingView();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            if (i != 0 || i2 != 0) {
                this.mScrolled = true;
            }
        }
    };

    public void attachToRecyclerView(RecyclerView recyclerView) throws IllegalStateException {
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 != recyclerView) {
            if (recyclerView2 != null) {
                RecyclerView.OnScrollListener onScrollListener = this.mScrollListener;
                List<RecyclerView.OnScrollListener> list = recyclerView2.mScrollListeners;
                if (list != null) {
                    list.remove(onScrollListener);
                }
                this.mRecyclerView.setOnFlingListener(null);
            }
            this.mRecyclerView = recyclerView;
            if (recyclerView == null) {
                return;
            }
            if (recyclerView.getOnFlingListener() == null) {
                this.mRecyclerView.addOnScrollListener(this.mScrollListener);
                this.mRecyclerView.setOnFlingListener(this);
                new Scroller(this.mRecyclerView.getContext(), new DecelerateInterpolator());
                snapToTargetExistingView();
                return;
            }
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
    }

    public abstract int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View view);

    public abstract View findSnapView(RecyclerView.LayoutManager layoutManager);

    public void snapToTargetExistingView() {
        RecyclerView.LayoutManager layoutManager;
        View findSnapView;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null && (layoutManager = recyclerView.getLayoutManager()) != null && (findSnapView = findSnapView(layoutManager)) != null) {
            int[] calculateDistanceToFinalSnap = calculateDistanceToFinalSnap(layoutManager, findSnapView);
            if (calculateDistanceToFinalSnap[0] != 0 || calculateDistanceToFinalSnap[1] != 0) {
                this.mRecyclerView.smoothScrollBy(calculateDistanceToFinalSnap[0], calculateDistanceToFinalSnap[1], null, Integer.MIN_VALUE, false);
            }
        }
    }
}
