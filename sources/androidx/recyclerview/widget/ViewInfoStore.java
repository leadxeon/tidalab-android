package androidx.recyclerview.widget;

import androidx.collection.LongSparseArray;
import androidx.collection.SimpleArrayMap;
import androidx.core.util.Pools$Pool;
import androidx.core.util.Pools$SimplePool;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes.dex */
public class ViewInfoStore {
    public final SimpleArrayMap<RecyclerView.ViewHolder, InfoRecord> mLayoutHolderMap = new SimpleArrayMap<>();
    public final LongSparseArray<RecyclerView.ViewHolder> mOldChangedHolders = new LongSparseArray<>();

    /* loaded from: classes.dex */
    public static class InfoRecord {
        public static Pools$Pool<InfoRecord> sPool = new Pools$SimplePool(20);
        public int flags;
        public RecyclerView.ItemAnimator.ItemHolderInfo postInfo;
        public RecyclerView.ItemAnimator.ItemHolderInfo preInfo;

        public static InfoRecord obtain() {
            InfoRecord acquire = sPool.acquire();
            return acquire == null ? new InfoRecord() : acquire;
        }

        public static void recycle(InfoRecord infoRecord) {
            infoRecord.flags = 0;
            infoRecord.preInfo = null;
            infoRecord.postInfo = null;
            sPool.release(infoRecord);
        }
    }

    /* loaded from: classes.dex */
    public interface ProcessCallback {
    }

    public void addToDisappearedInLayout(RecyclerView.ViewHolder viewHolder) {
        InfoRecord orDefault = this.mLayoutHolderMap.getOrDefault(viewHolder, null);
        if (orDefault == null) {
            orDefault = InfoRecord.obtain();
            this.mLayoutHolderMap.put(viewHolder, orDefault);
        }
        orDefault.flags |= 1;
    }

    public void addToPostLayout(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo) {
        InfoRecord orDefault = this.mLayoutHolderMap.getOrDefault(viewHolder, null);
        if (orDefault == null) {
            orDefault = InfoRecord.obtain();
            this.mLayoutHolderMap.put(viewHolder, orDefault);
        }
        orDefault.postInfo = itemHolderInfo;
        orDefault.flags |= 8;
    }

    public void addToPreLayout(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo) {
        InfoRecord orDefault = this.mLayoutHolderMap.getOrDefault(viewHolder, null);
        if (orDefault == null) {
            orDefault = InfoRecord.obtain();
            this.mLayoutHolderMap.put(viewHolder, orDefault);
        }
        orDefault.preInfo = itemHolderInfo;
        orDefault.flags |= 4;
    }

    public boolean isDisappearing(RecyclerView.ViewHolder viewHolder) {
        InfoRecord orDefault = this.mLayoutHolderMap.getOrDefault(viewHolder, null);
        return (orDefault == null || (orDefault.flags & 1) == 0) ? false : true;
    }

    public final RecyclerView.ItemAnimator.ItemHolderInfo popFromLayoutStep(RecyclerView.ViewHolder viewHolder, int i) {
        InfoRecord valueAt;
        RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo;
        int indexOfKey = this.mLayoutHolderMap.indexOfKey(viewHolder);
        if (indexOfKey >= 0 && (valueAt = this.mLayoutHolderMap.valueAt(indexOfKey)) != null) {
            int i2 = valueAt.flags;
            if ((i2 & i) != 0) {
                int i3 = (~i) & i2;
                valueAt.flags = i3;
                if (i == 4) {
                    itemHolderInfo = valueAt.preInfo;
                } else if (i == 8) {
                    itemHolderInfo = valueAt.postInfo;
                } else {
                    throw new IllegalArgumentException("Must provide flag PRE or POST");
                }
                if ((i3 & 12) == 0) {
                    this.mLayoutHolderMap.removeAt(indexOfKey);
                    InfoRecord.recycle(valueAt);
                }
                return itemHolderInfo;
            }
        }
        return null;
    }

    public void removeFromDisappearedInLayout(RecyclerView.ViewHolder viewHolder) {
        InfoRecord orDefault = this.mLayoutHolderMap.getOrDefault(viewHolder, null);
        if (orDefault != null) {
            orDefault.flags &= -2;
        }
    }

    public void removeViewHolder(RecyclerView.ViewHolder viewHolder) {
        int size = this.mOldChangedHolders.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            } else if (viewHolder == this.mOldChangedHolders.valueAt(size)) {
                LongSparseArray<RecyclerView.ViewHolder> longSparseArray = this.mOldChangedHolders;
                Object[] objArr = longSparseArray.mValues;
                Object obj = objArr[size];
                Object obj2 = LongSparseArray.DELETED;
                if (obj != obj2) {
                    objArr[size] = obj2;
                    longSparseArray.mGarbage = true;
                }
            } else {
                size--;
            }
        }
        InfoRecord remove = this.mLayoutHolderMap.remove(viewHolder);
        if (remove != null) {
            InfoRecord.recycle(remove);
        }
    }
}
