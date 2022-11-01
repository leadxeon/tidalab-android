package androidx.recyclerview.widget;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes.dex */
public abstract class SimpleItemAnimator extends RecyclerView.ItemAnimator {
    public boolean mSupportsChangeAnimations = true;

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        int i;
        int i2;
        int i3 = itemHolderInfo.left;
        int i4 = itemHolderInfo.top;
        if (viewHolder2.shouldIgnore()) {
            int i5 = itemHolderInfo.left;
            i = itemHolderInfo.top;
            i2 = i5;
        } else {
            i2 = itemHolderInfo2.left;
            i = itemHolderInfo2.top;
        }
        DefaultItemAnimator defaultItemAnimator = (DefaultItemAnimator) this;
        if (viewHolder == viewHolder2) {
            return defaultItemAnimator.animateMove(viewHolder, i3, i4, i2, i);
        }
        float translationX = viewHolder.itemView.getTranslationX();
        float translationY = viewHolder.itemView.getTranslationY();
        float alpha = viewHolder.itemView.getAlpha();
        defaultItemAnimator.resetAnimation(viewHolder);
        viewHolder.itemView.setTranslationX(translationX);
        viewHolder.itemView.setTranslationY(translationY);
        viewHolder.itemView.setAlpha(alpha);
        defaultItemAnimator.resetAnimation(viewHolder2);
        viewHolder2.itemView.setTranslationX(-((int) ((i2 - i3) - translationX)));
        viewHolder2.itemView.setTranslationY(-((int) ((i - i4) - translationY)));
        viewHolder2.itemView.setAlpha(0.0f);
        defaultItemAnimator.mPendingChanges.add(new DefaultItemAnimator.ChangeInfo(viewHolder, viewHolder2, i3, i4, i2, i));
        return true;
    }

    public abstract boolean animateMove(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4);
}
