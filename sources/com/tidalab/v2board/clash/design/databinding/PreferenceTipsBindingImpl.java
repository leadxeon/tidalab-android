package com.tidalab.v2board.clash.design.databinding;

import android.util.SparseIntArray;
import android.widget.LinearLayout;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class PreferenceTipsBindingImpl extends PreferenceTipsBinding {
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags = -1;
    public final LinearLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tips, 1);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public PreferenceTipsBindingImpl(androidx.databinding.DataBindingComponent r7, android.view.View r8) {
        /*
            r6 = this;
            android.util.SparseIntArray r0 = com.tidalab.v2board.clash.design.databinding.PreferenceTipsBindingImpl.sViewsWithIds
            r1 = 2
            r2 = 0
            java.lang.Object[] r0 = androidx.databinding.ViewDataBinding.mapBindings(r7, r8, r1, r2, r0)
            r1 = 1
            r1 = r0[r1]
            android.widget.TextView r1 = (android.widget.TextView) r1
            r3 = 0
            r6.<init>(r7, r8, r3, r1)
            r4 = -1
            r6.mDirtyFlags = r4
            r7 = r0[r3]
            android.widget.LinearLayout r7 = (android.widget.LinearLayout) r7
            r6.mboundView0 = r7
            r7.setTag(r2)
            r7 = 2131296397(0x7f09008d, float:1.821071E38)
            r8.setTag(r7, r6)
            r6.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.PreferenceTipsBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
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
