package com.tidalab.v2board.clash.design.databinding;

import android.util.SparseIntArray;
import android.widget.RelativeLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.AccessControlDesign;
import com.tidalab.v2board.clash.design.ui.Surface;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class DesignAccessControlBindingImpl extends DesignAccessControlBinding {
    public static final ViewDataBinding.IncludedLayouts sIncludes;
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags = -1;
    public final CoordinatorLayout mboundView0;
    public final RelativeLayout mboundView2;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(6);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"common_recycler_list"}, new int[]{3}, new int[]{R.layout.common_recycler_list});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.search_view, 4);
        sparseIntArray.put(R.id.menu_view, 5);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DesignAccessControlBindingImpl(androidx.databinding.DataBindingComponent r11, android.view.View r12) {
        /*
            r10 = this;
            androidx.databinding.ViewDataBinding$IncludedLayouts r0 = com.tidalab.v2board.clash.design.databinding.DesignAccessControlBindingImpl.sIncludes
            android.util.SparseIntArray r1 = com.tidalab.v2board.clash.design.databinding.DesignAccessControlBindingImpl.sViewsWithIds
            r2 = 6
            java.lang.Object[] r0 = androidx.databinding.ViewDataBinding.mapBindings(r11, r12, r2, r0, r1)
            r1 = 1
            r1 = r0[r1]
            r6 = r1
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r6 = (com.tidalab.v2board.clash.design.view.ActivityBarLayout) r6
            r1 = 3
            r1 = r0[r1]
            r7 = r1
            com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding r7 = (com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding) r7
            r1 = 5
            r1 = r0[r1]
            r8 = r1
            android.widget.ImageView r8 = (android.widget.ImageView) r8
            r1 = 4
            r1 = r0[r1]
            r9 = r1
            android.widget.ImageView r9 = (android.widget.ImageView) r9
            r5 = 2
            r2 = r10
            r3 = r11
            r4 = r12
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)
            r1 = -1
            r10.mDirtyFlags = r1
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r11 = r10.activityBarLayout
            r1 = 0
            r11.setTag(r1)
            com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding r11 = r10.mainList
            if (r11 == 0) goto L_0x0038
            r11.mContainingBinding = r10
        L_0x0038:
            r11 = 0
            r11 = r0[r11]
            androidx.coordinatorlayout.widget.CoordinatorLayout r11 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r11
            r10.mboundView0 = r11
            r11.setTag(r1)
            r11 = 2
            r11 = r0[r11]
            android.widget.RelativeLayout r11 = (android.widget.RelativeLayout) r11
            r10.mboundView2 = r11
            r11.setTag(r1)
            r11 = 2131296397(0x7f09008d, float:1.821071E38)
            r12.setTag(r11, r10)
            r10.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DesignAccessControlBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0030  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void executeBindings() {
        /*
            r9 = this;
            monitor-enter(r9)
            long r0 = r9.mDirtyFlags     // Catch: all -> 0x004d
            r2 = 0
            r9.mDirtyFlags = r2     // Catch: all -> 0x004d
            monitor-exit(r9)     // Catch: all -> 0x004d
            com.tidalab.v2board.clash.design.AccessControlDesign r4 = r9.mSelf
            r5 = 29
            long r0 = r0 & r5
            r5 = 0
            r6 = 0
            int r7 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r7 == 0) goto L_0x002c
            if (r4 == 0) goto L_0x0018
            com.tidalab.v2board.clash.design.ui.Surface r0 = r4.surface
            goto L_0x0019
        L_0x0018:
            r0 = r5
        L_0x0019:
            r9.updateRegistration(r6, r0)
            if (r0 == 0) goto L_0x0020
            com.tidalab.v2board.clash.design.ui.Insets r5 = r0.insets
        L_0x0020:
            if (r5 == 0) goto L_0x002c
            int r6 = r5.end
            int r0 = r5.start
            int r1 = r5.top
            r8 = r6
            r6 = r1
            r1 = r8
            goto L_0x002e
        L_0x002c:
            r0 = 0
            r1 = 0
        L_0x002e:
            if (r7 == 0) goto L_0x0047
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r2 = r9.activityBarLayout
            float r3 = (float) r6
            androidx.core.app.AppOpsManagerCompat.setPaddingTop(r2, r3)
            com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding r2 = r9.mainList
            r2.setInsets(r5)
            androidx.coordinatorlayout.widget.CoordinatorLayout r2 = r9.mboundView0
            float r0 = (float) r0
            androidx.core.app.AppOpsManagerCompat.setPaddingStart(r2, r0)
            androidx.coordinatorlayout.widget.CoordinatorLayout r0 = r9.mboundView0
            float r1 = (float) r1
            androidx.core.app.AppOpsManagerCompat.setPaddingEnd(r0, r1)
        L_0x0047:
            com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding r0 = r9.mainList
            r0.executeBindingsInternal()
            return
        L_0x004d:
            r0 = move-exception
            monitor-exit(r9)     // Catch: all -> 0x004d
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DesignAccessControlBindingImpl.executeBindings():void");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.mainList.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
        }
        this.mainList.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            Surface surface = (Surface) obj;
            if (i2 == 0) {
                synchronized (this) {
                    this.mDirtyFlags |= 1;
                }
            } else if (i2 != 14) {
                return false;
            } else {
                synchronized (this) {
                    this.mDirtyFlags |= 8;
                }
            }
            return true;
        } else if (i != 1) {
            return false;
        } else {
            CommonRecyclerListBinding commonRecyclerListBinding = (CommonRecyclerListBinding) obj;
            if (i2 != 0) {
                return false;
            }
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DesignAccessControlBinding
    public void setSelf(AccessControlDesign accessControlDesign) {
        this.mSelf = accessControlDesign;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(26);
        requestRebind();
    }
}
