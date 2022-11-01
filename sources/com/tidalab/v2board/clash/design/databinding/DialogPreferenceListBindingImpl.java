package com.tidalab.v2board.clash.design.databinding;

import android.util.SparseIntArray;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.tidalab.v2board.clash.design.ui.Surface;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class DialogPreferenceListBindingImpl extends DialogPreferenceListBinding {
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags = -1;
    public final CoordinatorLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.title_view, 1);
        sparseIntArray.put(R.id.new_view, 2);
        sparseIntArray.put(R.id.main_list, 3);
        sparseIntArray.put(R.id.reset_view, 4);
        sparseIntArray.put(R.id.cancel_view, 5);
        sparseIntArray.put(R.id.ok_view, 6);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DialogPreferenceListBindingImpl(androidx.databinding.DataBindingComponent r14, android.view.View r15) {
        /*
            r13 = this;
            android.util.SparseIntArray r0 = com.tidalab.v2board.clash.design.databinding.DialogPreferenceListBindingImpl.sViewsWithIds
            r1 = 7
            r2 = 0
            java.lang.Object[] r0 = androidx.databinding.ViewDataBinding.mapBindings(r14, r15, r1, r2, r0)
            r1 = 5
            r1 = r0[r1]
            r7 = r1
            android.widget.Button r7 = (android.widget.Button) r7
            r1 = 3
            r1 = r0[r1]
            r8 = r1
            androidx.recyclerview.widget.RecyclerView r8 = (androidx.recyclerview.widget.RecyclerView) r8
            r1 = 2
            r1 = r0[r1]
            r9 = r1
            android.widget.ImageView r9 = (android.widget.ImageView) r9
            r1 = 6
            r1 = r0[r1]
            r10 = r1
            android.widget.Button r10 = (android.widget.Button) r10
            r1 = 4
            r1 = r0[r1]
            r11 = r1
            android.widget.Button r11 = (android.widget.Button) r11
            r1 = 1
            r1 = r0[r1]
            r12 = r1
            android.widget.TextView r12 = (android.widget.TextView) r12
            r6 = 1
            r3 = r13
            r4 = r14
            r5 = r15
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12)
            r3 = -1
            r13.mDirtyFlags = r3
            r14 = 0
            r14 = r0[r14]
            androidx.coordinatorlayout.widget.CoordinatorLayout r14 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r14
            r13.mboundView0 = r14
            r14.setTag(r2)
            r14 = 2131296397(0x7f09008d, float:1.821071E38)
            r15.setTag(r14, r13)
            r13.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DialogPreferenceListBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
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
            int r0 = r4.top
            int r1 = r4.bottom
            int r2 = r4.end
            goto L_0x0025
        L_0x0022:
            r0 = 0
            r1 = 0
            r2 = 0
        L_0x0025:
            if (r7 == 0) goto L_0x003f
            androidx.coordinatorlayout.widget.CoordinatorLayout r3 = r8.mboundView0
            float r4 = (float) r6
            androidx.core.app.AppOpsManagerCompat.setPaddingStart(r3, r4)
            androidx.coordinatorlayout.widget.CoordinatorLayout r3 = r8.mboundView0
            float r0 = (float) r0
            androidx.core.app.AppOpsManagerCompat.setPaddingTop(r3, r0)
            androidx.coordinatorlayout.widget.CoordinatorLayout r0 = r8.mboundView0
            float r2 = (float) r2
            androidx.core.app.AppOpsManagerCompat.setPaddingEnd(r0, r2)
            androidx.coordinatorlayout.widget.CoordinatorLayout r0 = r8.mboundView0
            float r1 = (float) r1
            androidx.core.app.AppOpsManagerCompat.setPaddingBottom(r0, r1)
        L_0x003f:
            return
        L_0x0040:
            r0 = move-exception
            monitor-exit(r8)     // Catch: all -> 0x0040
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DialogPreferenceListBindingImpl.executeBindings():void");
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

    @Override // com.tidalab.v2board.clash.design.databinding.DialogPreferenceListBinding
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
