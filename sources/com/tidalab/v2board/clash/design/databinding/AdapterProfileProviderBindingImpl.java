package com.tidalab.v2board.clash.design.databinding;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.AppOpsManagerCompat;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.model.ProfileProvider;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class AdapterProfileProviderBindingImpl extends AdapterProfileProviderBinding {
    public long mDirtyFlags = -1;
    public final LinearLayout mboundView0;
    public final View mboundView1;
    public final TextView mboundView2;
    public final TextView mboundView3;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdapterProfileProviderBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        super(dataBindingComponent, view, 0);
        Object[] mapBindings = ViewDataBinding.mapBindings(dataBindingComponent, view, 4, null, null);
        LinearLayout linearLayout = (LinearLayout) mapBindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        View view2 = (View) mapBindings[1];
        this.mboundView1 = view2;
        view2.setTag(null);
        TextView textView = (TextView) mapBindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) mapBindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
        view.setTag(R.id.dataBinding, this);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        String str;
        String str2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ProfileProvider profileProvider = this.mProvider;
        Drawable drawable = null;
        int i = ((j & 3) > 0L ? 1 : ((j & 3) == 0L ? 0 : -1));
        if (i == 0 || profileProvider == null) {
            str2 = null;
            str = null;
        } else {
            String name = profileProvider.getName();
            Drawable icon = profileProvider.getIcon();
            str = profileProvider.getSummary();
            drawable = icon;
            str2 = name;
        }
        if (i != 0) {
            this.mboundView1.setBackground(drawable);
            AppOpsManagerCompat.setText(this.mboundView2, str2);
            AppOpsManagerCompat.setText(this.mboundView3, str);
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

    @Override // com.tidalab.v2board.clash.design.databinding.AdapterProfileProviderBinding
    public void setProvider(ProfileProvider profileProvider) {
        this.mProvider = profileProvider;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(24);
        requestRebind();
    }
}
