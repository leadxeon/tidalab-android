package com.tidalab.v2board.clash.design.databinding;

import android.util.SparseIntArray;
import android.widget.LinearLayout;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class ComponentActionLabelBindingImpl extends ComponentActionLabelBinding {
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags = -1;
    public final LinearLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.icon_view, 1);
        sparseIntArray.put(R.id.text_view, 2);
        sparseIntArray.put(R.id.subtext_view, 3);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ComponentActionLabelBindingImpl(androidx.databinding.DataBindingComponent r11, android.view.View r12) {
        /*
            r10 = this;
            android.util.SparseIntArray r0 = com.tidalab.v2board.clash.design.databinding.ComponentActionLabelBindingImpl.sViewsWithIds
            r1 = 4
            r2 = 0
            java.lang.Object[] r0 = androidx.databinding.ViewDataBinding.mapBindings(r11, r12, r1, r2, r0)
            r1 = 1
            r1 = r0[r1]
            r7 = r1
            android.view.View r7 = (android.view.View) r7
            r1 = 3
            r1 = r0[r1]
            r8 = r1
            android.widget.TextView r8 = (android.widget.TextView) r8
            r1 = 2
            r1 = r0[r1]
            r9 = r1
            android.widget.TextView r9 = (android.widget.TextView) r9
            r6 = 0
            r3 = r10
            r4 = r11
            r5 = r12
            r3.<init>(r4, r5, r6, r7, r8, r9)
            r3 = -1
            r10.mDirtyFlags = r3
            r11 = 0
            r11 = r0[r11]
            android.widget.LinearLayout r11 = (android.widget.LinearLayout) r11
            r10.mboundView0 = r11
            r11.setTag(r2)
            r11 = 2131296397(0x7f09008d, float:1.821071E38)
            r12.setTag(r11, r10)
            r10.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.ComponentActionLabelBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
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
