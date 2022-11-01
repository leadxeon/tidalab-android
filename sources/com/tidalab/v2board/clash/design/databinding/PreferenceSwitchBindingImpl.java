package com.tidalab.v2board.clash.design.databinding;

import android.util.SparseIntArray;
import android.widget.RelativeLayout;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class PreferenceSwitchBindingImpl extends PreferenceSwitchBinding {
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags = -1;
    public final RelativeLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.icon_view, 1);
        sparseIntArray.put(R.id.title_view, 2);
        sparseIntArray.put(R.id.summary_view, 3);
        sparseIntArray.put(R.id.switch_view, 4);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public PreferenceSwitchBindingImpl(androidx.databinding.DataBindingComponent r12, android.view.View r13) {
        /*
            r11 = this;
            android.util.SparseIntArray r0 = com.tidalab.v2board.clash.design.databinding.PreferenceSwitchBindingImpl.sViewsWithIds
            r1 = 5
            r2 = 0
            java.lang.Object[] r0 = androidx.databinding.ViewDataBinding.mapBindings(r12, r13, r1, r2, r0)
            r1 = 1
            r1 = r0[r1]
            r7 = r1
            android.view.View r7 = (android.view.View) r7
            r1 = 3
            r1 = r0[r1]
            r8 = r1
            android.widget.TextView r8 = (android.widget.TextView) r8
            r1 = 4
            r1 = r0[r1]
            r9 = r1
            com.google.android.material.switchmaterial.SwitchMaterial r9 = (com.google.android.material.switchmaterial.SwitchMaterial) r9
            r1 = 2
            r1 = r0[r1]
            r10 = r1
            android.widget.TextView r10 = (android.widget.TextView) r10
            r6 = 0
            r3 = r11
            r4 = r12
            r5 = r13
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            r3 = -1
            r11.mDirtyFlags = r3
            r12 = 0
            r12 = r0[r12]
            android.widget.RelativeLayout r12 = (android.widget.RelativeLayout) r12
            r11.mboundView0 = r12
            r12.setTag(r2)
            r12 = 2131296397(0x7f09008d, float:1.821071E38)
            r13.setTag(r12, r11)
            r11.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.PreferenceSwitchBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0L;
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
