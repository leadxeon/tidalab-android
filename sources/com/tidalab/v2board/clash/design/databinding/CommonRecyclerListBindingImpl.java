package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import androidx.core.app.AppOpsManagerCompat;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.ui.Insets;
import com.tidalab.v2board.clash.design.view.AppRecyclerView;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class CommonRecyclerListBindingImpl extends CommonRecyclerListBinding {
    public long mDirtyFlags = -1;

    public CommonRecyclerListBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        super(dataBindingComponent, view, 0, (AppRecyclerView) ViewDataBinding.mapBindings(dataBindingComponent, view, 1, null, null)[0]);
        this.recyclerList.setTag(null);
        view.setTag(R.id.dataBinding, this);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        float f = 0.0f;
        Insets insets = this.mInsets;
        int i = 0;
        int i2 = ((j & 3) > 0L ? 1 : ((j & 3) == 0L ? 0 : -1));
        if (i2 != 0) {
            if (insets != null) {
                i = insets.bottom;
                i = insets.top;
            } else {
                i = 0;
            }
            f = this.recyclerList.getResources().getDimension(R.dimen.toolbar_height) + i;
        }
        if (i2 != 0) {
            AppOpsManagerCompat.setPaddingTop(this.recyclerList, f);
            AppOpsManagerCompat.setPaddingBottom(this.recyclerList, i);
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding
    public void setInsets(Insets insets) {
        this.mInsets = insets;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(14);
        requestRebind();
    }
}
