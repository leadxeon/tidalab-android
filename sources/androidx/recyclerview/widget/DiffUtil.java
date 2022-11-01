package androidx.recyclerview.widget;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public class DiffUtil {
    public static final Comparator<Diagonal> DIAGONAL_COMPARATOR = new Comparator<Diagonal>() { // from class: androidx.recyclerview.widget.DiffUtil.1
        @Override // java.util.Comparator
        public int compare(Diagonal diagonal, Diagonal diagonal2) {
            return diagonal.x - diagonal2.x;
        }
    };

    /* loaded from: classes.dex */
    public static abstract class Callback {
        public abstract boolean areContentsTheSame(int i, int i2);

        public abstract boolean areItemsTheSame(int i, int i2);

        public abstract int getNewListSize();

        public abstract int getOldListSize();
    }

    /* loaded from: classes.dex */
    public static class Diagonal {
        public final int size;
        public final int x;
        public final int y;

        public Diagonal(int i, int i2, int i3) {
            this.x = i;
            this.y = i2;
            this.size = i3;
        }
    }

    /* loaded from: classes.dex */
    public static class DiffResult {
        public final Callback mCallback;
        public final boolean mDetectMoves;
        public final List<Diagonal> mDiagonals;
        public final int[] mNewItemStatuses;
        public final int mNewListSize;
        public final int[] mOldItemStatuses;
        public final int mOldListSize;

        public DiffResult(Callback callback, List<Diagonal> list, int[] iArr, int[] iArr2, boolean z) {
            int i;
            Diagonal diagonal;
            int i2;
            this.mDiagonals = list;
            this.mOldItemStatuses = iArr;
            this.mNewItemStatuses = iArr2;
            Arrays.fill(iArr, 0);
            Arrays.fill(iArr2, 0);
            this.mCallback = callback;
            int oldListSize = callback.getOldListSize();
            this.mOldListSize = oldListSize;
            int newListSize = callback.getNewListSize();
            this.mNewListSize = newListSize;
            this.mDetectMoves = z;
            Diagonal diagonal2 = list.isEmpty() ? null : list.get(0);
            if (!(diagonal2 != null && diagonal2.x == 0 && diagonal2.y == 0)) {
                list.add(0, new Diagonal(0, 0, 0));
            }
            list.add(new Diagonal(oldListSize, newListSize, 0));
            for (Diagonal diagonal3 : list) {
                for (int i3 = 0; i3 < diagonal3.size; i3++) {
                    int i4 = diagonal3.x + i3;
                    int i5 = diagonal3.y + i3;
                    int i6 = this.mCallback.areContentsTheSame(i4, i5) ? 1 : 2;
                    this.mOldItemStatuses[i4] = (i5 << 4) | i6;
                    this.mNewItemStatuses[i5] = (i4 << 4) | i6;
                }
            }
            if (this.mDetectMoves) {
                int i7 = 0;
                for (Diagonal diagonal4 : this.mDiagonals) {
                    while (true) {
                        i = diagonal4.x;
                        if (i7 < i) {
                            if (this.mOldItemStatuses[i7] == 0) {
                                int size = this.mDiagonals.size();
                                int i8 = 0;
                                int i9 = 0;
                                while (true) {
                                    if (i8 < size) {
                                        diagonal = this.mDiagonals.get(i8);
                                        while (true) {
                                            i2 = diagonal.y;
                                            if (i9 < i2) {
                                                if (this.mNewItemStatuses[i9] != 0 || !this.mCallback.areItemsTheSame(i7, i9)) {
                                                    i9++;
                                                } else {
                                                    int i10 = this.mCallback.areContentsTheSame(i7, i9) ? 8 : 4;
                                                    this.mOldItemStatuses[i7] = (i9 << 4) | i10;
                                                    this.mNewItemStatuses[i9] = i10 | (i7 << 4);
                                                }
                                            }
                                        }
                                    }
                                    i9 = diagonal.size + i2;
                                    i8++;
                                }
                            }
                            i7++;
                        }
                    }
                    i7 = diagonal4.size + i;
                }
            }
        }

        public static PostponedUpdate getPostponedUpdate(Collection<PostponedUpdate> collection, int i, boolean z) {
            PostponedUpdate postponedUpdate;
            Iterator<PostponedUpdate> it = collection.iterator();
            while (true) {
                if (!it.hasNext()) {
                    postponedUpdate = null;
                    break;
                }
                postponedUpdate = it.next();
                if (postponedUpdate.posInOwnerList == i && postponedUpdate.removal == z) {
                    it.remove();
                    break;
                }
            }
            while (it.hasNext()) {
                PostponedUpdate next = it.next();
                if (z) {
                    next.currentPos--;
                } else {
                    next.currentPos++;
                }
            }
            return postponedUpdate;
        }
    }

    /* loaded from: classes.dex */
    public static class PostponedUpdate {
        public int currentPos;
        public int posInOwnerList;
        public boolean removal;

        public PostponedUpdate(int i, int i2, boolean z) {
            this.posInOwnerList = i;
            this.currentPos = i2;
            this.removal = z;
        }
    }

    /* loaded from: classes.dex */
    public static class Range {
        public int newListEnd;
        public int newListStart;
        public int oldListEnd;
        public int oldListStart;

        public Range() {
        }

        public int newSize() {
            return this.newListEnd - this.newListStart;
        }

        public int oldSize() {
            return this.oldListEnd - this.oldListStart;
        }

        public Range(int i, int i2, int i3, int i4) {
            this.oldListStart = i;
            this.oldListEnd = i2;
            this.newListStart = i3;
            this.newListEnd = i4;
        }
    }

    /* loaded from: classes.dex */
    public static class Snake {
        public int endX;
        public int endY;
        public boolean reverse;
        public int startX;
        public int startY;

        public int diagonalSize() {
            return Math.min(this.endX - this.startX, this.endY - this.startY);
        }
    }
}
