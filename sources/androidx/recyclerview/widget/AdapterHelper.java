package androidx.recyclerview.widget;

import androidx.core.util.Pools$Pool;
import androidx.core.util.Pools$SimplePool;
import androidx.recyclerview.widget.OpReorderer;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class AdapterHelper implements OpReorderer.Callback {
    public final Callback mCallback;
    public Pools$Pool<UpdateOp> mUpdateOpPool = new Pools$SimplePool(30);
    public final ArrayList<UpdateOp> mPendingUpdates = new ArrayList<>();
    public final ArrayList<UpdateOp> mPostponedList = new ArrayList<>();
    public int mExistingUpdateTypes = 0;
    public final OpReorderer mOpReorderer = new OpReorderer(this);

    /* loaded from: classes.dex */
    public interface Callback {
    }

    /* loaded from: classes.dex */
    public static final class UpdateOp {
        public int cmd;
        public int itemCount;
        public Object payload;
        public int positionStart;

        public UpdateOp(int i, int i2, int i3, Object obj) {
            this.cmd = i;
            this.positionStart = i2;
            this.itemCount = i3;
            this.payload = obj;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof UpdateOp)) {
                return false;
            }
            UpdateOp updateOp = (UpdateOp) obj;
            int i = this.cmd;
            if (i != updateOp.cmd) {
                return false;
            }
            if (i == 8 && Math.abs(this.itemCount - this.positionStart) == 1 && this.itemCount == updateOp.positionStart && this.positionStart == updateOp.itemCount) {
                return true;
            }
            if (this.itemCount != updateOp.itemCount || this.positionStart != updateOp.positionStart) {
                return false;
            }
            Object obj2 = this.payload;
            if (obj2 != null) {
                if (!obj2.equals(updateOp.payload)) {
                    return false;
                }
            } else if (updateOp.payload != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.cmd * 31) + this.positionStart) * 31) + this.itemCount;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append("[");
            int i = this.cmd;
            sb.append(i != 1 ? i != 2 ? i != 4 ? i != 8 ? "??" : "mv" : "up" : "rm" : "add");
            sb.append(",s:");
            sb.append(this.positionStart);
            sb.append("c:");
            sb.append(this.itemCount);
            sb.append(",p:");
            sb.append(this.payload);
            sb.append("]");
            return sb.toString();
        }
    }

    public AdapterHelper(Callback callback) {
        this.mCallback = callback;
    }

    public final boolean canFindInPreLayout(int i) {
        int size = this.mPostponedList.size();
        for (int i2 = 0; i2 < size; i2++) {
            UpdateOp updateOp = this.mPostponedList.get(i2);
            int i3 = updateOp.cmd;
            if (i3 == 8) {
                if (findPositionOffset(updateOp.itemCount, i2 + 1) == i) {
                    return true;
                }
            } else if (i3 == 1) {
                int i4 = updateOp.positionStart;
                int i5 = updateOp.itemCount + i4;
                while (i4 < i5) {
                    if (findPositionOffset(i4, i2 + 1) == i) {
                        return true;
                    }
                    i4++;
                }
                continue;
            } else {
                continue;
            }
        }
        return false;
    }

    public void consumePostponedUpdates() {
        int size = this.mPostponedList.size();
        for (int i = 0; i < size; i++) {
            ((RecyclerView.AnonymousClass6) this.mCallback).dispatchUpdate(this.mPostponedList.get(i));
        }
        recycleUpdateOpsAndClearList(this.mPostponedList);
        this.mExistingUpdateTypes = 0;
    }

    public void consumeUpdatesInOnePass() {
        consumePostponedUpdates();
        int size = this.mPendingUpdates.size();
        for (int i = 0; i < size; i++) {
            UpdateOp updateOp = this.mPendingUpdates.get(i);
            int i2 = updateOp.cmd;
            if (i2 == 1) {
                ((RecyclerView.AnonymousClass6) this.mCallback).dispatchUpdate(updateOp);
                ((RecyclerView.AnonymousClass6) this.mCallback).offsetPositionsForAdd(updateOp.positionStart, updateOp.itemCount);
            } else if (i2 == 2) {
                ((RecyclerView.AnonymousClass6) this.mCallback).dispatchUpdate(updateOp);
                Callback callback = this.mCallback;
                int i3 = updateOp.positionStart;
                int i4 = updateOp.itemCount;
                RecyclerView.AnonymousClass6 r4 = (RecyclerView.AnonymousClass6) callback;
                RecyclerView.this.offsetPositionRecordsForRemove(i3, i4, true);
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.mItemsAddedOrRemoved = true;
                recyclerView.mState.mDeletedInvisibleItemCountSincePreviousLayout += i4;
            } else if (i2 == 4) {
                ((RecyclerView.AnonymousClass6) this.mCallback).dispatchUpdate(updateOp);
                ((RecyclerView.AnonymousClass6) this.mCallback).markViewHoldersUpdated(updateOp.positionStart, updateOp.itemCount, updateOp.payload);
            } else if (i2 == 8) {
                ((RecyclerView.AnonymousClass6) this.mCallback).dispatchUpdate(updateOp);
                ((RecyclerView.AnonymousClass6) this.mCallback).offsetPositionsForMove(updateOp.positionStart, updateOp.itemCount);
            }
        }
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
        this.mExistingUpdateTypes = 0;
    }

    public final void dispatchAndUpdateViewHolders(UpdateOp updateOp) {
        int i;
        int i2 = updateOp.cmd;
        if (i2 == 1 || i2 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int updatePositionWithPostponed = updatePositionWithPostponed(updateOp.positionStart, i2);
        int i3 = updateOp.positionStart;
        int i4 = updateOp.cmd;
        if (i4 == 2) {
            i = 0;
        } else if (i4 == 4) {
            i = 1;
        } else {
            throw new IllegalArgumentException("op should be remove or update." + updateOp);
        }
        int i5 = 1;
        for (int i6 = 1; i6 < updateOp.itemCount; i6++) {
            int updatePositionWithPostponed2 = updatePositionWithPostponed((i * i6) + updateOp.positionStart, updateOp.cmd);
            int i7 = updateOp.cmd;
            if (i7 == 2 ? updatePositionWithPostponed2 == updatePositionWithPostponed : i7 == 4 && updatePositionWithPostponed2 == updatePositionWithPostponed + 1) {
                i5++;
            } else {
                UpdateOp obtainUpdateOp = obtainUpdateOp(i7, updatePositionWithPostponed, i5, updateOp.payload);
                dispatchFirstPassAndUpdateViewHolders(obtainUpdateOp, i3);
                recycleUpdateOp(obtainUpdateOp);
                if (updateOp.cmd == 4) {
                    i3 += i5;
                }
                updatePositionWithPostponed = updatePositionWithPostponed2;
                i5 = 1;
            }
        }
        Object obj = updateOp.payload;
        recycleUpdateOp(updateOp);
        if (i5 > 0) {
            UpdateOp obtainUpdateOp2 = obtainUpdateOp(updateOp.cmd, updatePositionWithPostponed, i5, obj);
            dispatchFirstPassAndUpdateViewHolders(obtainUpdateOp2, i3);
            recycleUpdateOp(obtainUpdateOp2);
        }
    }

    public void dispatchFirstPassAndUpdateViewHolders(UpdateOp updateOp, int i) {
        ((RecyclerView.AnonymousClass6) this.mCallback).dispatchUpdate(updateOp);
        int i2 = updateOp.cmd;
        if (i2 == 2) {
            Callback callback = this.mCallback;
            int i3 = updateOp.itemCount;
            RecyclerView.AnonymousClass6 r0 = (RecyclerView.AnonymousClass6) callback;
            RecyclerView.this.offsetPositionRecordsForRemove(i, i3, true);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.mItemsAddedOrRemoved = true;
            recyclerView.mState.mDeletedInvisibleItemCountSincePreviousLayout += i3;
        } else if (i2 == 4) {
            ((RecyclerView.AnonymousClass6) this.mCallback).markViewHoldersUpdated(i, updateOp.itemCount, updateOp.payload);
        } else {
            throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        }
    }

    public int findPositionOffset(int i, int i2) {
        int size = this.mPostponedList.size();
        while (i2 < size) {
            UpdateOp updateOp = this.mPostponedList.get(i2);
            int i3 = updateOp.cmd;
            if (i3 == 8) {
                int i4 = updateOp.positionStart;
                if (i4 == i) {
                    i = updateOp.itemCount;
                } else {
                    if (i4 < i) {
                        i--;
                    }
                    if (updateOp.itemCount <= i) {
                        i++;
                    }
                }
            } else {
                int i5 = updateOp.positionStart;
                if (i5 > i) {
                    continue;
                } else if (i3 == 2) {
                    int i6 = updateOp.itemCount;
                    if (i < i5 + i6) {
                        return -1;
                    }
                    i -= i6;
                } else if (i3 == 1) {
                    i += updateOp.itemCount;
                }
            }
            i2++;
        }
        return i;
    }

    public boolean hasPendingUpdates() {
        return this.mPendingUpdates.size() > 0;
    }

    public UpdateOp obtainUpdateOp(int i, int i2, int i3, Object obj) {
        UpdateOp acquire = this.mUpdateOpPool.acquire();
        if (acquire == null) {
            return new UpdateOp(i, i2, i3, obj);
        }
        acquire.cmd = i;
        acquire.positionStart = i2;
        acquire.itemCount = i3;
        acquire.payload = obj;
        return acquire;
    }

    public final void postponeAndUpdateViewHolders(UpdateOp updateOp) {
        this.mPostponedList.add(updateOp);
        int i = updateOp.cmd;
        if (i == 1) {
            ((RecyclerView.AnonymousClass6) this.mCallback).offsetPositionsForAdd(updateOp.positionStart, updateOp.itemCount);
        } else if (i == 2) {
            Callback callback = this.mCallback;
            RecyclerView.AnonymousClass6 r0 = (RecyclerView.AnonymousClass6) callback;
            RecyclerView.this.offsetPositionRecordsForRemove(updateOp.positionStart, updateOp.itemCount, false);
            RecyclerView.this.mItemsAddedOrRemoved = true;
        } else if (i == 4) {
            ((RecyclerView.AnonymousClass6) this.mCallback).markViewHoldersUpdated(updateOp.positionStart, updateOp.itemCount, updateOp.payload);
        } else if (i == 8) {
            ((RecyclerView.AnonymousClass6) this.mCallback).offsetPositionsForMove(updateOp.positionStart, updateOp.itemCount);
        } else {
            throw new IllegalArgumentException("Unknown update op type for " + updateOp);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:175:0x00a3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:176:0x00d2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0127 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0118 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0009 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void preProcess() {
        /*
            Method dump skipped, instructions count: 671
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.AdapterHelper.preProcess():void");
    }

    public void recycleUpdateOp(UpdateOp updateOp) {
        updateOp.payload = null;
        this.mUpdateOpPool.release(updateOp);
    }

    public void recycleUpdateOpsAndClearList(List<UpdateOp> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            recycleUpdateOp(list.get(i));
        }
        list.clear();
    }

    public final int updatePositionWithPostponed(int i, int i2) {
        int i3;
        int i4;
        for (int size = this.mPostponedList.size() - 1; size >= 0; size--) {
            UpdateOp updateOp = this.mPostponedList.get(size);
            int i5 = updateOp.cmd;
            if (i5 == 8) {
                int i6 = updateOp.positionStart;
                int i7 = updateOp.itemCount;
                if (i6 < i7) {
                    i4 = i6;
                    i3 = i7;
                } else {
                    i3 = i6;
                    i4 = i7;
                }
                if (i < i4 || i > i3) {
                    if (i < i6) {
                        if (i2 == 1) {
                            updateOp.positionStart = i6 + 1;
                            updateOp.itemCount = i7 + 1;
                        } else if (i2 == 2) {
                            updateOp.positionStart = i6 - 1;
                            updateOp.itemCount = i7 - 1;
                        }
                    }
                } else if (i4 == i6) {
                    if (i2 == 1) {
                        updateOp.itemCount = i7 + 1;
                    } else if (i2 == 2) {
                        updateOp.itemCount = i7 - 1;
                    }
                    i++;
                } else {
                    if (i2 == 1) {
                        updateOp.positionStart = i6 + 1;
                    } else if (i2 == 2) {
                        updateOp.positionStart = i6 - 1;
                    }
                    i--;
                }
            } else {
                int i8 = updateOp.positionStart;
                if (i8 <= i) {
                    if (i5 == 1) {
                        i -= updateOp.itemCount;
                    } else if (i5 == 2) {
                        i += updateOp.itemCount;
                    }
                } else if (i2 == 1) {
                    updateOp.positionStart = i8 + 1;
                } else if (i2 == 2) {
                    updateOp.positionStart = i8 - 1;
                }
            }
        }
        for (int size2 = this.mPostponedList.size() - 1; size2 >= 0; size2--) {
            UpdateOp updateOp2 = this.mPostponedList.get(size2);
            if (updateOp2.cmd == 8) {
                int i9 = updateOp2.itemCount;
                if (i9 == updateOp2.positionStart || i9 < 0) {
                    this.mPostponedList.remove(size2);
                    recycleUpdateOp(updateOp2);
                }
            } else if (updateOp2.itemCount <= 0) {
                this.mPostponedList.remove(size2);
                recycleUpdateOp(updateOp2);
            }
        }
        return i;
    }
}
