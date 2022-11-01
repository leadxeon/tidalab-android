package com.tidalab.v2board.clash.design.databinding;

import android.util.SparseIntArray;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.AppOpsManagerCompat;
import com.tidalab.v2board.clash.design.AppCrashedDesign;
import com.tidalab.v2board.clash.design.ui.Insets;
import com.tidalab.v2board.clash.design.ui.Surface;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class DesignAppCrashedBindingImpl extends DesignAppCrashedBinding {
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags = -1;
    public final CoordinatorLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.logs_view, 3);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DesignAppCrashedBindingImpl(androidx.databinding.DataBindingComponent r11, android.view.View r12) {
        /*
            r10 = this;
            android.util.SparseIntArray r0 = com.tidalab.v2board.clash.design.databinding.DesignAppCrashedBindingImpl.sViewsWithIds
            r1 = 4
            r2 = 0
            java.lang.Object[] r0 = androidx.databinding.ViewDataBinding.mapBindings(r11, r12, r1, r2, r0)
            r1 = 2
            r1 = r0[r1]
            r7 = r1
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r7 = (com.tidalab.v2board.clash.design.view.ActivityBarLayout) r7
            r1 = 3
            r1 = r0[r1]
            r8 = r1
            android.widget.TextView r8 = (android.widget.TextView) r8
            r1 = 1
            r1 = r0[r1]
            r9 = r1
            com.tidalab.v2board.clash.design.view.ObservableScrollView r9 = (com.tidalab.v2board.clash.design.view.ObservableScrollView) r9
            r6 = 1
            r3 = r10
            r4 = r11
            r5 = r12
            r3.<init>(r4, r5, r6, r7, r8, r9)
            r3 = -1
            r10.mDirtyFlags = r3
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r11 = r10.activityBarLayout
            r11.setTag(r2)
            r11 = 0
            r11 = r0[r11]
            androidx.coordinatorlayout.widget.CoordinatorLayout r11 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r11
            r10.mboundView0 = r11
            r11.setTag(r2)
            com.tidalab.v2board.clash.design.view.ObservableScrollView r11 = r10.scrollRoot
            r11.setTag(r2)
            r11 = 2131296397(0x7f09008d, float:1.821071E38)
            r12.setTag(r11, r10)
            r10.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DesignAppCrashedBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        int i;
        int i2;
        int i3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        float f = 0.0f;
        AppCrashedDesign appCrashedDesign = this.mSelf;
        int i4 = 0;
        int i5 = ((j & 15) > 0L ? 1 : ((j & 15) == 0L ? 0 : -1));
        if (i5 != 0) {
            Insets insets = null;
            Surface surface = appCrashedDesign != null ? appCrashedDesign.surface : null;
            updateRegistration(0, surface);
            if (surface != null) {
                insets = surface.insets;
            }
            if (insets != null) {
                i3 = insets.end;
                i2 = insets.bottom;
                i = insets.start;
                i4 = insets.top;
            } else {
                i3 = 0;
                i2 = 0;
                i = 0;
            }
            f = this.scrollRoot.getResources().getDimension(R.dimen.toolbar_height) + i4;
        } else {
            i3 = 0;
            i2 = 0;
            i = 0;
        }
        if (i5 != 0) {
            AppOpsManagerCompat.setPaddingTop(this.activityBarLayout, i4);
            AppOpsManagerCompat.setPaddingStart(this.mboundView0, i);
            AppOpsManagerCompat.setPaddingEnd(this.mboundView0, i3);
            AppOpsManagerCompat.setPaddingTop(this.scrollRoot, f);
            AppOpsManagerCompat.setPaddingBottom(this.scrollRoot, i2);
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
            this.mDirtyFlags = 8L;
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
                this.mDirtyFlags |= 4;
            }
        }
        return true;
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DesignAppCrashedBinding
    public void setSelf(AppCrashedDesign appCrashedDesign) {
        this.mSelf = appCrashedDesign;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(26);
        requestRebind();
    }
}
