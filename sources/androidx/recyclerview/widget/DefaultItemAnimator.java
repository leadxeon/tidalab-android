package androidx.recyclerview.widget;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class DefaultItemAnimator extends SimpleItemAnimator {
    public static TimeInterpolator sDefaultInterpolator;
    public ArrayList<RecyclerView.ViewHolder> mPendingRemovals = new ArrayList<>();
    public ArrayList<RecyclerView.ViewHolder> mPendingAdditions = new ArrayList<>();
    public ArrayList<MoveInfo> mPendingMoves = new ArrayList<>();
    public ArrayList<ChangeInfo> mPendingChanges = new ArrayList<>();
    public ArrayList<ArrayList<RecyclerView.ViewHolder>> mAdditionsList = new ArrayList<>();
    public ArrayList<ArrayList<MoveInfo>> mMovesList = new ArrayList<>();
    public ArrayList<ArrayList<ChangeInfo>> mChangesList = new ArrayList<>();
    public ArrayList<RecyclerView.ViewHolder> mAddAnimations = new ArrayList<>();
    public ArrayList<RecyclerView.ViewHolder> mMoveAnimations = new ArrayList<>();
    public ArrayList<RecyclerView.ViewHolder> mRemoveAnimations = new ArrayList<>();
    public ArrayList<RecyclerView.ViewHolder> mChangeAnimations = new ArrayList<>();

    /* loaded from: classes.dex */
    public static class ChangeInfo {
        public int fromX;
        public int fromY;
        public RecyclerView.ViewHolder newHolder;
        public RecyclerView.ViewHolder oldHolder;
        public int toX;
        public int toY;

        public ChangeInfo(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
            this.oldHolder = viewHolder;
            this.newHolder = viewHolder2;
            this.fromX = i;
            this.fromY = i2;
            this.toX = i3;
            this.toY = i4;
        }

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("ChangeInfo{oldHolder=");
            outline13.append(this.oldHolder);
            outline13.append(", newHolder=");
            outline13.append(this.newHolder);
            outline13.append(", fromX=");
            outline13.append(this.fromX);
            outline13.append(", fromY=");
            outline13.append(this.fromY);
            outline13.append(", toX=");
            outline13.append(this.toX);
            outline13.append(", toY=");
            outline13.append(this.toY);
            outline13.append('}');
            return outline13.toString();
        }
    }

    /* loaded from: classes.dex */
    public static class MoveInfo {
        public int fromX;
        public int fromY;
        public RecyclerView.ViewHolder holder;
        public int toX;
        public int toY;

        public MoveInfo(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
            this.holder = viewHolder;
            this.fromX = i;
            this.fromY = i2;
            this.toX = i3;
            this.toY = i4;
        }
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateMove(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        View view = viewHolder.itemView;
        int translationX = i + ((int) view.getTranslationX());
        int translationY = i2 + ((int) viewHolder.itemView.getTranslationY());
        resetAnimation(viewHolder);
        int i5 = i3 - translationX;
        int i6 = i4 - translationY;
        if (i5 == 0 && i6 == 0) {
            dispatchAnimationFinished(viewHolder);
            return false;
        }
        if (i5 != 0) {
            view.setTranslationX(-i5);
        }
        if (i6 != 0) {
            view.setTranslationY(-i6);
        }
        this.mPendingMoves.add(new MoveInfo(viewHolder, translationX, translationY, i3, i4));
        return true;
    }

    public void cancelAll(List<RecyclerView.ViewHolder> list) {
        int size = list.size();
        while (true) {
            size--;
            if (size >= 0) {
                list.get(size).itemView.animate().cancel();
            } else {
                return;
            }
        }
    }

    public void dispatchFinishedWhenDone() {
        if (!isRunning()) {
            dispatchAnimationsFinished();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void endAnimation(RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        view.animate().cancel();
        int size = this.mPendingMoves.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            } else if (this.mPendingMoves.get(size).holder == viewHolder) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                dispatchAnimationFinished(viewHolder);
                this.mPendingMoves.remove(size);
            }
        }
        endChangeAnimation(this.mPendingChanges, viewHolder);
        if (this.mPendingRemovals.remove(viewHolder)) {
            view.setAlpha(1.0f);
            dispatchAnimationFinished(viewHolder);
        }
        if (this.mPendingAdditions.remove(viewHolder)) {
            view.setAlpha(1.0f);
            dispatchAnimationFinished(viewHolder);
        }
        int size2 = this.mChangesList.size();
        while (true) {
            size2--;
            if (size2 < 0) {
                break;
            }
            ArrayList<ChangeInfo> arrayList = this.mChangesList.get(size2);
            endChangeAnimation(arrayList, viewHolder);
            if (arrayList.isEmpty()) {
                this.mChangesList.remove(size2);
            }
        }
        int size3 = this.mMovesList.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            ArrayList<MoveInfo> arrayList2 = this.mMovesList.get(size3);
            int size4 = arrayList2.size();
            while (true) {
                size4--;
                if (size4 < 0) {
                    break;
                } else if (arrayList2.get(size4).holder == viewHolder) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    dispatchAnimationFinished(viewHolder);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.mMovesList.remove(size3);
                    }
                }
            }
        }
        int size5 = this.mAdditionsList.size();
        while (true) {
            size5--;
            if (size5 >= 0) {
                ArrayList<RecyclerView.ViewHolder> arrayList3 = this.mAdditionsList.get(size5);
                if (arrayList3.remove(viewHolder)) {
                    view.setAlpha(1.0f);
                    dispatchAnimationFinished(viewHolder);
                    if (arrayList3.isEmpty()) {
                        this.mAdditionsList.remove(size5);
                    }
                }
            } else {
                this.mRemoveAnimations.remove(viewHolder);
                this.mAddAnimations.remove(viewHolder);
                this.mChangeAnimations.remove(viewHolder);
                this.mMoveAnimations.remove(viewHolder);
                dispatchFinishedWhenDone();
                return;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void endAnimations() {
        int size = this.mPendingMoves.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            MoveInfo moveInfo = this.mPendingMoves.get(size);
            View view = moveInfo.holder.itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            dispatchAnimationFinished(moveInfo.holder);
            this.mPendingMoves.remove(size);
        }
        int size2 = this.mPendingRemovals.size();
        while (true) {
            size2--;
            if (size2 < 0) {
                break;
            }
            dispatchAnimationFinished(this.mPendingRemovals.get(size2));
            this.mPendingRemovals.remove(size2);
        }
        int size3 = this.mPendingAdditions.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.ViewHolder viewHolder = this.mPendingAdditions.get(size3);
            viewHolder.itemView.setAlpha(1.0f);
            dispatchAnimationFinished(viewHolder);
            this.mPendingAdditions.remove(size3);
        }
        int size4 = this.mPendingChanges.size();
        while (true) {
            size4--;
            if (size4 < 0) {
                break;
            }
            ChangeInfo changeInfo = this.mPendingChanges.get(size4);
            RecyclerView.ViewHolder viewHolder2 = changeInfo.oldHolder;
            if (viewHolder2 != null) {
                endChangeAnimationIfNecessary(changeInfo, viewHolder2);
            }
            RecyclerView.ViewHolder viewHolder3 = changeInfo.newHolder;
            if (viewHolder3 != null) {
                endChangeAnimationIfNecessary(changeInfo, viewHolder3);
            }
        }
        this.mPendingChanges.clear();
        if (isRunning()) {
            int size5 = this.mMovesList.size();
            while (true) {
                size5--;
                if (size5 < 0) {
                    break;
                }
                ArrayList<MoveInfo> arrayList = this.mMovesList.get(size5);
                int size6 = arrayList.size();
                while (true) {
                    size6--;
                    if (size6 >= 0) {
                        MoveInfo moveInfo2 = arrayList.get(size6);
                        View view2 = moveInfo2.holder.itemView;
                        view2.setTranslationY(0.0f);
                        view2.setTranslationX(0.0f);
                        dispatchAnimationFinished(moveInfo2.holder);
                        arrayList.remove(size6);
                        if (arrayList.isEmpty()) {
                            this.mMovesList.remove(arrayList);
                        }
                    }
                }
            }
            int size7 = this.mAdditionsList.size();
            while (true) {
                size7--;
                if (size7 < 0) {
                    break;
                }
                ArrayList<RecyclerView.ViewHolder> arrayList2 = this.mAdditionsList.get(size7);
                int size8 = arrayList2.size();
                while (true) {
                    size8--;
                    if (size8 >= 0) {
                        RecyclerView.ViewHolder viewHolder4 = arrayList2.get(size8);
                        viewHolder4.itemView.setAlpha(1.0f);
                        dispatchAnimationFinished(viewHolder4);
                        arrayList2.remove(size8);
                        if (arrayList2.isEmpty()) {
                            this.mAdditionsList.remove(arrayList2);
                        }
                    }
                }
            }
            int size9 = this.mChangesList.size();
            while (true) {
                size9--;
                if (size9 >= 0) {
                    ArrayList<ChangeInfo> arrayList3 = this.mChangesList.get(size9);
                    int size10 = arrayList3.size();
                    while (true) {
                        size10--;
                        if (size10 >= 0) {
                            ChangeInfo changeInfo2 = arrayList3.get(size10);
                            RecyclerView.ViewHolder viewHolder5 = changeInfo2.oldHolder;
                            if (viewHolder5 != null) {
                                endChangeAnimationIfNecessary(changeInfo2, viewHolder5);
                            }
                            RecyclerView.ViewHolder viewHolder6 = changeInfo2.newHolder;
                            if (viewHolder6 != null) {
                                endChangeAnimationIfNecessary(changeInfo2, viewHolder6);
                            }
                            if (arrayList3.isEmpty()) {
                                this.mChangesList.remove(arrayList3);
                            }
                        }
                    }
                } else {
                    cancelAll(this.mRemoveAnimations);
                    cancelAll(this.mMoveAnimations);
                    cancelAll(this.mAddAnimations);
                    cancelAll(this.mChangeAnimations);
                    dispatchAnimationsFinished();
                    return;
                }
            }
        }
    }

    public final void endChangeAnimation(List<ChangeInfo> list, RecyclerView.ViewHolder viewHolder) {
        int size = list.size();
        while (true) {
            size--;
            if (size >= 0) {
                ChangeInfo changeInfo = list.get(size);
                if (endChangeAnimationIfNecessary(changeInfo, viewHolder) && changeInfo.oldHolder == null && changeInfo.newHolder == null) {
                    list.remove(changeInfo);
                }
            } else {
                return;
            }
        }
    }

    public final boolean endChangeAnimationIfNecessary(ChangeInfo changeInfo, RecyclerView.ViewHolder viewHolder) {
        if (changeInfo.newHolder == viewHolder) {
            changeInfo.newHolder = null;
        } else if (changeInfo.oldHolder != viewHolder) {
            return false;
        } else {
            changeInfo.oldHolder = null;
        }
        viewHolder.itemView.setAlpha(1.0f);
        viewHolder.itemView.setTranslationX(0.0f);
        viewHolder.itemView.setTranslationY(0.0f);
        dispatchAnimationFinished(viewHolder);
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean isRunning() {
        return !this.mPendingAdditions.isEmpty() || !this.mPendingChanges.isEmpty() || !this.mPendingMoves.isEmpty() || !this.mPendingRemovals.isEmpty() || !this.mMoveAnimations.isEmpty() || !this.mRemoveAnimations.isEmpty() || !this.mAddAnimations.isEmpty() || !this.mChangeAnimations.isEmpty() || !this.mMovesList.isEmpty() || !this.mAdditionsList.isEmpty() || !this.mChangesList.isEmpty();
    }

    public final void resetAnimation(RecyclerView.ViewHolder viewHolder) {
        if (sDefaultInterpolator == null) {
            sDefaultInterpolator = new ValueAnimator().getInterpolator();
        }
        viewHolder.itemView.animate().setInterpolator(sDefaultInterpolator);
        endAnimation(viewHolder);
    }
}
