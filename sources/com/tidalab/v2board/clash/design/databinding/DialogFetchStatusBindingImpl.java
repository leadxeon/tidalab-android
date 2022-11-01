package com.tidalab.v2board.clash.design.databinding;

import android.util.SparseIntArray;
import android.widget.LinearLayout;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class DialogFetchStatusBindingImpl extends DialogFetchStatusBinding {
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags = -1;
    public final LinearLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.progress_indicator, 1);
        sparseIntArray.put(R.id.text, 2);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DialogFetchStatusBindingImpl(androidx.databinding.DataBindingComponent r10, android.view.View r11) {
        /*
            r9 = this;
            android.util.SparseIntArray r0 = com.tidalab.v2board.clash.design.databinding.DialogFetchStatusBindingImpl.sViewsWithIds
            r1 = 3
            r2 = 0
            java.lang.Object[] r0 = androidx.databinding.ViewDataBinding.mapBindings(r10, r11, r1, r2, r0)
            r1 = 1
            r1 = r0[r1]
            r7 = r1
            com.google.android.material.progressindicator.LinearProgressIndicator r7 = (com.google.android.material.progressindicator.LinearProgressIndicator) r7
            r1 = 2
            r1 = r0[r1]
            r8 = r1
            android.widget.TextView r8 = (android.widget.TextView) r8
            r6 = 0
            r3 = r9
            r4 = r10
            r5 = r11
            r3.<init>(r4, r5, r6, r7, r8)
            r3 = -1
            r9.mDirtyFlags = r3
            r10 = 0
            r10 = r0[r10]
            android.widget.LinearLayout r10 = (android.widget.LinearLayout) r10
            r9.mboundView0 = r10
            r10.setTag(r2)
            r10 = 2131296397(0x7f09008d, float:1.821071E38)
            r11.setTag(r10, r9)
            r9.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DialogFetchStatusBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
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
