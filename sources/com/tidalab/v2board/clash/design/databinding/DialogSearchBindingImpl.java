package com.tidalab.v2board.clash.design.databinding;

import android.util.SparseIntArray;
import android.widget.LinearLayout;
import com.tidalab.v2board.clash.design.ui.Surface;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class DialogSearchBindingImpl extends DialogSearchBinding {
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags = -1;
    public final LinearLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.close_view, 1);
        sparseIntArray.put(R.id.keyword_view, 2);
        sparseIntArray.put(R.id.main_list, 3);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DialogSearchBindingImpl(androidx.databinding.DataBindingComponent r11, android.view.View r12) {
        /*
            r10 = this;
            android.util.SparseIntArray r0 = com.tidalab.v2board.clash.design.databinding.DialogSearchBindingImpl.sViewsWithIds
            r1 = 4
            r2 = 0
            java.lang.Object[] r0 = androidx.databinding.ViewDataBinding.mapBindings(r11, r12, r1, r2, r0)
            r1 = 1
            r1 = r0[r1]
            r7 = r1
            android.widget.ImageView r7 = (android.widget.ImageView) r7
            r1 = 2
            r1 = r0[r1]
            r8 = r1
            androidx.appcompat.widget.AppCompatEditText r8 = (androidx.appcompat.widget.AppCompatEditText) r8
            r1 = 3
            r1 = r0[r1]
            r9 = r1
            com.tidalab.v2board.clash.design.view.AppRecyclerView r9 = (com.tidalab.v2board.clash.design.view.AppRecyclerView) r9
            r6 = 1
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
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DialogSearchBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void executeBindings() {
        /*
            r8 = this;
            monitor-enter(r8)
            long r0 = r8.mDirtyFlags     // Catch: all -> 0x0040
            r2 = 0
            r8.mDirtyFlags = r2     // Catch: all -> 0x0040
            monitor-exit(r8)     // Catch: all -> 0x0040
            r4 = 0
            com.tidalab.v2board.clash.design.ui.Surface r5 = r8.mSurface
            r6 = 7
            long r0 = r0 & r6
            r6 = 0
            int r7 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r7 == 0) goto L_0x0022
            if (r5 == 0) goto L_0x0017
            com.tidalab.v2board.clash.design.ui.Insets r4 = r5.insets
        L_0x0017:
            if (r4 == 0) goto L_0x0022
            int r6 = r4.start
            int r0 = r4.bottom
            int r1 = r4.top
            int r2 = r4.end
            goto L_0x0025
        L_0x0022:
            r0 = 0
            r1 = 0
            r2 = 0
        L_0x0025:
            if (r7 == 0) goto L_0x003f
            android.widget.LinearLayout r3 = r8.mboundView0
            float r4 = (float) r6
            androidx.core.app.AppOpsManagerCompat.setPaddingStart(r3, r4)
            android.widget.LinearLayout r3 = r8.mboundView0
            float r1 = (float) r1
            androidx.core.app.AppOpsManagerCompat.setPaddingTop(r3, r1)
            android.widget.LinearLayout r1 = r8.mboundView0
            float r2 = (float) r2
            androidx.core.app.AppOpsManagerCompat.setPaddingEnd(r1, r2)
            android.widget.LinearLayout r1 = r8.mboundView0
            float r0 = (float) r0
            androidx.core.app.AppOpsManagerCompat.setPaddingBottom(r1, r0)
        L_0x003f:
            return
        L_0x0040:
            r0 = move-exception
            monitor-exit(r8)     // Catch: all -> 0x0040
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DialogSearchBindingImpl.executeBindings():void");
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
        if (i != 0) {
            return false;
        }
        Surface surface = (Surface) obj;
        if (i2 == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
        } else if (i2 != 14) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
        }
        return true;
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DialogSearchBinding
    public void setSurface(Surface surface) {
        updateRegistration(0, surface);
        this.mSurface = surface;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(29);
        requestRebind();
    }
}
