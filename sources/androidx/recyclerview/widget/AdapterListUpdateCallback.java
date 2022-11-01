package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes.dex */
public final class AdapterListUpdateCallback implements ListUpdateCallback {
    public final RecyclerView.Adapter mAdapter;

    public AdapterListUpdateCallback(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onChanged(int i, int i2, Object obj) {
        this.mAdapter.mObservable.notifyItemRangeChanged(i, i2, obj);
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onInserted(int i, int i2) {
        this.mAdapter.mObservable.notifyItemRangeInserted(i, i2);
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onMoved(int i, int i2) {
        this.mAdapter.mObservable.notifyItemMoved(i, i2);
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onRemoved(int i, int i2) {
        this.mAdapter.mObservable.notifyItemRangeRemoved(i, i2);
    }
}
