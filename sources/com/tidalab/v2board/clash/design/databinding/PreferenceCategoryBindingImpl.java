package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.TextView;
import androidx.core.app.AppOpsManagerCompat;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class PreferenceCategoryBindingImpl extends PreferenceCategoryBinding {
    public long mDirtyFlags = -1;

    public PreferenceCategoryBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        super(dataBindingComponent, view, 0, (TextView) ViewDataBinding.mapBindings(dataBindingComponent, view, 1, null, null)[0]);
        this.textView.setTag(null);
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
        if ((j & 1) != 0) {
            TextView textView = this.textView;
            AppOpsManagerCompat.setPaddingStart(textView, (this.textView.getResources().getDimension(R.dimen.item_header_margin) * 2.0f) + textView.getResources().getDimension(R.dimen.item_header_component_size));
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
            this.mDirtyFlags = 1L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }
}
