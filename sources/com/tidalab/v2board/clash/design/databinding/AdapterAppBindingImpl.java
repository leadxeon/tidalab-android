package com.tidalab.v2board.clash.design.databinding;

import android.graphics.drawable.Drawable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.AppOpsManagerCompat;
import com.tidalab.v2board.clash.design.model.AppInfo;
/* loaded from: classes.dex */
public class AdapterAppBindingImpl extends AdapterAppBinding {
    public long mDirtyFlags = -1;
    public final RelativeLayout mboundView0;
    public final TextView mboundView2;
    public final TextView mboundView3;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AdapterAppBindingImpl(androidx.databinding.DataBindingComponent r10, android.view.View r11) {
        /*
            r9 = this;
            r0 = 5
            r1 = 0
            java.lang.Object[] r0 = androidx.databinding.ViewDataBinding.mapBindings(r10, r11, r0, r1, r1)
            r2 = 1
            r2 = r0[r2]
            r7 = r2
            android.view.View r7 = (android.view.View) r7
            r2 = 4
            r2 = r0[r2]
            r8 = r2
            com.google.android.material.checkbox.MaterialCheckBox r8 = (com.google.android.material.checkbox.MaterialCheckBox) r8
            r6 = 0
            r3 = r9
            r4 = r10
            r5 = r11
            r3.<init>(r4, r5, r6, r7, r8)
            r2 = -1
            r9.mDirtyFlags = r2
            android.view.View r10 = r9.iconView
            r10.setTag(r1)
            r10 = 0
            r10 = r0[r10]
            android.widget.RelativeLayout r10 = (android.widget.RelativeLayout) r10
            r9.mboundView0 = r10
            r10.setTag(r1)
            r10 = 2
            r10 = r0[r10]
            android.widget.TextView r10 = (android.widget.TextView) r10
            r9.mboundView2 = r10
            r10.setTag(r1)
            r10 = 3
            r10 = r0[r10]
            android.widget.TextView r10 = (android.widget.TextView) r10
            r9.mboundView3 = r10
            r10.setTag(r1)
            com.google.android.material.checkbox.MaterialCheckBox r10 = r9.switchView
            r10.setTag(r1)
            r10 = 2131296397(0x7f09008d, float:1.821071E38)
            r11.setTag(r10, r9)
            r9.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.AdapterAppBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
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
        AppInfo appInfo = this.mApp;
        boolean z = this.mSelected;
        Drawable drawable = null;
        int i = ((5 & j) > 0L ? 1 : ((5 & j) == 0L ? 0 : -1));
        if (i == 0 || appInfo == null) {
            str2 = null;
            str = null;
        } else {
            drawable = appInfo.icon;
            str = appInfo.label;
            str2 = appInfo.packageName;
        }
        int i2 = ((j & 6) > 0L ? 1 : ((j & 6) == 0L ? 0 : -1));
        if (i != 0) {
            this.iconView.setBackground(drawable);
            AppOpsManagerCompat.setText(this.mboundView2, str);
            AppOpsManagerCompat.setText(this.mboundView3, str2);
        }
        if (i2 != 0) {
            AppOpsManagerCompat.setChecked(this.switchView, z);
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
            this.mDirtyFlags = 4L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.tidalab.v2board.clash.design.databinding.AdapterAppBinding
    public void setApp(AppInfo appInfo) {
        this.mApp = appInfo;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(1);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.AdapterAppBinding
    public void setSelected(boolean z) {
        this.mSelected = z;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(25);
        requestRebind();
    }
}
