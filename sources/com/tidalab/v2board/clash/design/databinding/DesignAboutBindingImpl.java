package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.AppOpsManagerCompat;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class DesignAboutBindingImpl extends DesignAboutBinding {
    public long mDirtyFlags = -1;
    public final LinearLayout mboundView0;
    public final TextView mboundView1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DesignAboutBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        super(dataBindingComponent, view, 0);
        Object[] mapBindings = ViewDataBinding.mapBindings(dataBindingComponent, view, 2, null, null);
        LinearLayout linearLayout = (LinearLayout) mapBindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) mapBindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
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
        String str = this.mVersionName;
        if ((j & 3) != 0) {
            AppOpsManagerCompat.setText(this.mboundView1, str);
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

    @Override // com.tidalab.v2board.clash.design.databinding.DesignAboutBinding
    public void setVersionName(String str) {
        this.mVersionName = str;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(34);
        requestRebind();
    }
}
