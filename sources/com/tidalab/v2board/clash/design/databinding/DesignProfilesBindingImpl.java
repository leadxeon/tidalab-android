package com.tidalab.v2board.clash.design.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.ProfilesDesign;
import com.tidalab.v2board.clash.design.generated.callback.OnClickListener;
import com.tidalab.v2board.clash.design.ui.Surface;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class DesignProfilesBindingImpl extends DesignProfilesBinding implements OnClickListener.Listener {
    public static final ViewDataBinding.IncludedLayouts sIncludes;
    public static final SparseIntArray sViewsWithIds;
    public final View.OnClickListener mCallback26;
    public final View.OnClickListener mCallback27;
    public long mDirtyFlags = -1;
    public final CoordinatorLayout mboundView0;
    public final RelativeLayout mboundView2;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(7);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"common_recycler_list"}, new int[]{5}, new int[]{R.layout.common_recycler_list});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.update_layout, 6);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DesignProfilesBindingImpl(androidx.databinding.DataBindingComponent r13, android.view.View r14) {
        /*
            r12 = this;
            androidx.databinding.ViewDataBinding$IncludedLayouts r0 = com.tidalab.v2board.clash.design.databinding.DesignProfilesBindingImpl.sIncludes
            android.util.SparseIntArray r1 = com.tidalab.v2board.clash.design.databinding.DesignProfilesBindingImpl.sViewsWithIds
            r2 = 7
            java.lang.Object[] r0 = androidx.databinding.ViewDataBinding.mapBindings(r13, r14, r2, r0, r1)
            r1 = 1
            r2 = r0[r1]
            r7 = r2
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r7 = (com.tidalab.v2board.clash.design.view.ActivityBarLayout) r7
            r2 = 4
            r2 = r0[r2]
            r8 = r2
            android.widget.ImageView r8 = (android.widget.ImageView) r8
            r2 = 5
            r2 = r0[r2]
            r9 = r2
            com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding r9 = (com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding) r9
            r2 = 6
            r2 = r0[r2]
            r10 = r2
            android.widget.FrameLayout r10 = (android.widget.FrameLayout) r10
            r2 = 3
            r2 = r0[r2]
            r11 = r2
            android.widget.ImageView r11 = (android.widget.ImageView) r11
            r6 = 2
            r3 = r12
            r4 = r13
            r5 = r14
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
            r2 = -1
            r12.mDirtyFlags = r2
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r13 = r12.activityBarLayout
            r2 = 0
            r13.setTag(r2)
            android.widget.ImageView r13 = r12.addView
            r13.setTag(r2)
            com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding r13 = r12.mainList
            if (r13 == 0) goto L_0x0043
            r13.mContainingBinding = r12
        L_0x0043:
            r13 = 0
            r13 = r0[r13]
            androidx.coordinatorlayout.widget.CoordinatorLayout r13 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r13
            r12.mboundView0 = r13
            r13.setTag(r2)
            r13 = 2
            r0 = r0[r13]
            android.widget.RelativeLayout r0 = (android.widget.RelativeLayout) r0
            r12.mboundView2 = r0
            r0.setTag(r2)
            android.widget.ImageView r0 = r12.updateView
            r0.setTag(r2)
            r0 = 2131296397(0x7f09008d, float:1.821071E38)
            r14.setTag(r0, r12)
            com.tidalab.v2board.clash.design.generated.callback.OnClickListener r14 = new com.tidalab.v2board.clash.design.generated.callback.OnClickListener
            r14.<init>(r12, r13)
            r12.mCallback27 = r14
            com.tidalab.v2board.clash.design.generated.callback.OnClickListener r13 = new com.tidalab.v2board.clash.design.generated.callback.OnClickListener
            r13.<init>(r12, r1)
            r12.mCallback26 = r13
            r12.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DesignProfilesBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
    }

    @Override // com.tidalab.v2board.clash.design.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        boolean z = false;
        if (i == 1) {
            ProfilesDesign profilesDesign = this.mSelf;
            if (profilesDesign != null) {
                z = true;
            }
            if (z) {
                profilesDesign.requests.mo14trySendJP2dKIU(ProfilesDesign.Request.UpdateAll.INSTANCE);
            }
        } else if (i == 2) {
            ProfilesDesign profilesDesign2 = this.mSelf;
            if (profilesDesign2 != null) {
                z = true;
            }
            if (z) {
                profilesDesign2.requests.mo14trySendJP2dKIU(ProfilesDesign.Request.Create.INSTANCE);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004e  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void executeBindings() {
        /*
            r11 = this;
            monitor-enter(r11)
            long r0 = r11.mDirtyFlags     // Catch: all -> 0x0062
            r2 = 0
            r11.mDirtyFlags = r2     // Catch: all -> 0x0062
            monitor-exit(r11)     // Catch: all -> 0x0062
            com.tidalab.v2board.clash.design.ProfilesDesign r4 = r11.mSelf
            r5 = 29
            long r5 = r5 & r0
            r7 = 0
            r8 = 0
            int r9 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r9 == 0) goto L_0x002c
            if (r4 == 0) goto L_0x0018
            com.tidalab.v2board.clash.design.ui.Surface r4 = r4.surface
            goto L_0x0019
        L_0x0018:
            r4 = r7
        L_0x0019:
            r11.updateRegistration(r8, r4)
            if (r4 == 0) goto L_0x0020
            com.tidalab.v2board.clash.design.ui.Insets r7 = r4.insets
        L_0x0020:
            if (r7 == 0) goto L_0x002c
            int r8 = r7.end
            int r4 = r7.start
            int r5 = r7.top
            r10 = r8
            r8 = r5
            r5 = r10
            goto L_0x002e
        L_0x002c:
            r4 = 0
            r5 = 0
        L_0x002e:
            if (r9 == 0) goto L_0x0047
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r6 = r11.activityBarLayout
            float r8 = (float) r8
            androidx.core.app.AppOpsManagerCompat.setPaddingTop(r6, r8)
            com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding r6 = r11.mainList
            r6.setInsets(r7)
            androidx.coordinatorlayout.widget.CoordinatorLayout r6 = r11.mboundView0
            float r4 = (float) r4
            androidx.core.app.AppOpsManagerCompat.setPaddingStart(r6, r4)
            androidx.coordinatorlayout.widget.CoordinatorLayout r4 = r11.mboundView0
            float r5 = (float) r5
            androidx.core.app.AppOpsManagerCompat.setPaddingEnd(r4, r5)
        L_0x0047:
            r4 = 16
            long r0 = r0 & r4
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x005c
            android.widget.ImageView r0 = r11.addView
            android.view.View$OnClickListener r1 = r11.mCallback27
            r0.setOnClickListener(r1)
            android.widget.ImageView r0 = r11.updateView
            android.view.View$OnClickListener r1 = r11.mCallback26
            r0.setOnClickListener(r1)
        L_0x005c:
            com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding r0 = r11.mainList
            r0.executeBindingsInternal()
            return
        L_0x0062:
            r0 = move-exception
            monitor-exit(r11)     // Catch: all -> 0x0062
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DesignProfilesBindingImpl.executeBindings():void");
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

    @Override // com.tidalab.v2board.clash.design.databinding.DesignProfilesBinding
    public void setSelf(ProfilesDesign profilesDesign) {
        this.mSelf = profilesDesign;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(26);
        requestRebind();
    }
}
