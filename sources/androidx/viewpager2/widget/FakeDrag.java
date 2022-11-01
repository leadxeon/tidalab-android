package androidx.viewpager2.widget;

import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes.dex */
public final class FakeDrag {
    public final ScrollEventAdapter mScrollEventAdapter;

    public FakeDrag(ViewPager2 viewPager2, ScrollEventAdapter scrollEventAdapter, RecyclerView recyclerView) {
        this.mScrollEventAdapter = scrollEventAdapter;
    }
}
