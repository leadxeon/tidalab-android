package com.tidalab.v2board.clash.design.databinding;

import android.graphics.drawable.Drawable;
import android.widget.RelativeLayout;
import androidx.core.app.AppOpsManagerCompat;
import com.tidalab.v2board.clash.design.model.AppInfo;
/* loaded from: classes.dex */
public class AdapterSideloadProviderBindingImpl extends AdapterSideloadProviderBinding {
    public long mDirtyFlags = -1;
    public final RelativeLayout mboundView0;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AdapterSideloadProviderBindingImpl(androidx.databinding.DataBindingComponent r12, android.view.View r13) {
        /*
            r11 = this;
            r0 = 5
            r1 = 0
            java.lang.Object[] r0 = androidx.databinding.ViewDataBinding.mapBindings(r12, r13, r0, r1, r1)
            r2 = 1
            r2 = r0[r2]
            r7 = r2
            android.view.View r7 = (android.view.View) r7
            r2 = 2
            r2 = r0[r2]
            r8 = r2
            android.widget.TextView r8 = (android.widget.TextView) r8
            r2 = 3
            r2 = r0[r2]
            r9 = r2
            android.widget.TextView r9 = (android.widget.TextView) r9
            r2 = 4
            r2 = r0[r2]
            r10 = r2
            com.google.android.material.radiobutton.MaterialRadioButton r10 = (com.google.android.material.radiobutton.MaterialRadioButton) r10
            r6 = 0
            r3 = r11
            r4 = r12
            r5 = r13
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            r2 = -1
            r11.mDirtyFlags = r2
            android.view.View r12 = r11.iconView
            r12.setTag(r1)
            android.widget.TextView r12 = r11.labelView
            r12.setTag(r1)
            r12 = 0
            r12 = r0[r12]
            android.widget.RelativeLayout r12 = (android.widget.RelativeLayout) r12
            r11.mboundView0 = r12
            r12.setTag(r1)
            android.widget.TextView r12 = r11.packageNameView
            r12.setTag(r1)
            com.google.android.material.radiobutton.MaterialRadioButton r12 = r11.selectedView
            r12.setTag(r1)
            r12 = 2131296397(0x7f09008d, float:1.821071E38)
            r13.setTag(r12, r11)
            r11.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.AdapterSideloadProviderBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
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
        AppInfo appInfo = this.mAppInfo;
        boolean z = this.mSelected;
        Drawable drawable = null;
        String str3 = null;
        int i = 0;
        int i2 = ((j & 5) > 0L ? 1 : ((j & 5) == 0L ? 0 : -1));
        if (i2 != 0) {
            if (appInfo != null) {
                str = appInfo.label;
                str3 = appInfo.packageName;
                drawable = appInfo.icon;
            } else {
                drawable = null;
                str = null;
            }
            boolean z2 = (str3 != null ? str3.length() : 0) == 0;
            if (i2 != 0) {
                j |= z2 ? 16L : 8L;
            }
            if (z2) {
                i = 8;
            }
            str2 = str3;
        } else {
            str2 = null;
            str = null;
        }
        int i3 = ((6 & j) > 0L ? 1 : ((6 & j) == 0L ? 0 : -1));
        if ((j & 5) != 0) {
            this.iconView.setBackground(drawable);
            AppOpsManagerCompat.setText(this.labelView, str);
            AppOpsManagerCompat.setText(this.packageNameView, str2);
            this.packageNameView.setVisibility(i);
        }
        if (i3 != 0) {
            AppOpsManagerCompat.setChecked(this.selectedView, z);
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

    @Override // com.tidalab.v2board.clash.design.databinding.AdapterSideloadProviderBinding
    public void setAppInfo(AppInfo appInfo) {
        this.mAppInfo = appInfo;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(2);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.AdapterSideloadProviderBinding
    public void setSelected(boolean z) {
        this.mSelected = z;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(25);
        requestRebind();
    }
}
