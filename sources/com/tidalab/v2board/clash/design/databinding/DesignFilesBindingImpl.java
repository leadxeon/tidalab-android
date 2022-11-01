package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.RelativeLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.FilesDesign;
import com.tidalab.v2board.clash.design.generated.callback.OnClickListener;
import com.tidalab.v2board.clash.design.ui.Surface;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class DesignFilesBindingImpl extends DesignFilesBinding implements OnClickListener.Listener {
    public static final ViewDataBinding.IncludedLayouts sIncludes;
    public final View.OnClickListener mCallback25;
    public long mDirtyFlags = -1;
    public final CoordinatorLayout mboundView0;
    public final RelativeLayout mboundView2;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(5);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"common_recycler_list"}, new int[]{4}, new int[]{R.layout.common_recycler_list});
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DesignFilesBindingImpl(androidx.databinding.DataBindingComponent r12, android.view.View r13) {
        /*
            r11 = this;
            androidx.databinding.ViewDataBinding$IncludedLayouts r0 = com.tidalab.v2board.clash.design.databinding.DesignFilesBindingImpl.sIncludes
            r1 = 5
            r2 = 0
            java.lang.Object[] r0 = androidx.databinding.ViewDataBinding.mapBindings(r12, r13, r1, r0, r2)
            r1 = 1
            r3 = r0[r1]
            r8 = r3
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r8 = (com.tidalab.v2board.clash.design.view.ActivityBarLayout) r8
            r3 = 4
            r3 = r0[r3]
            r9 = r3
            com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding r9 = (com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding) r9
            r3 = 3
            r3 = r0[r3]
            r10 = r3
            android.widget.ImageView r10 = (android.widget.ImageView) r10
            r7 = 2
            r4 = r11
            r5 = r12
            r6 = r13
            r4.<init>(r5, r6, r7, r8, r9, r10)
            r3 = -1
            r11.mDirtyFlags = r3
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r12 = r11.activityBarLayout
            r12.setTag(r2)
            com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding r12 = r11.mainList
            if (r12 == 0) goto L_0x0030
            r12.mContainingBinding = r11
        L_0x0030:
            r12 = 0
            r12 = r0[r12]
            androidx.coordinatorlayout.widget.CoordinatorLayout r12 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r12
            r11.mboundView0 = r12
            r12.setTag(r2)
            r12 = 2
            r12 = r0[r12]
            android.widget.RelativeLayout r12 = (android.widget.RelativeLayout) r12
            r11.mboundView2 = r12
            r12.setTag(r2)
            android.widget.ImageView r12 = r11.newView
            r12.setTag(r2)
            r12 = 2131296397(0x7f09008d, float:1.821071E38)
            r13.setTag(r12, r11)
            com.tidalab.v2board.clash.design.generated.callback.OnClickListener r12 = new com.tidalab.v2board.clash.design.generated.callback.OnClickListener
            r12.<init>(r11, r1)
            r11.mCallback25 = r12
            r11.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DesignFilesBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
    }

    @Override // com.tidalab.v2board.clash.design.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        FilesDesign filesDesign = this.mSelf;
        if (filesDesign != null) {
            filesDesign.requests.mo14trySendJP2dKIU(new FilesDesign.Request.ImportFile(null));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0074  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void executeBindings() {
        /*
            r14 = this;
            monitor-enter(r14)
            long r0 = r14.mDirtyFlags     // Catch: all -> 0x007f
            r2 = 0
            r14.mDirtyFlags = r2     // Catch: all -> 0x007f
            monitor-exit(r14)     // Catch: all -> 0x007f
            boolean r4 = r14.mCurrentInBaseDir
            com.tidalab.v2board.clash.design.FilesDesign r5 = r14.mSelf
            r6 = 68
            long r8 = r0 & r6
            r10 = 0
            int r11 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r11 == 0) goto L_0x0024
            if (r11 == 0) goto L_0x001f
            if (r4 == 0) goto L_0x001c
            r8 = 256(0x100, double:1.265E-321)
            goto L_0x001e
        L_0x001c:
            r8 = 128(0x80, double:6.32E-322)
        L_0x001e:
            long r0 = r0 | r8
        L_0x001f:
            if (r4 == 0) goto L_0x0024
            r4 = 8
            goto L_0x0025
        L_0x0024:
            r4 = 0
        L_0x0025:
            r8 = 105(0x69, double:5.2E-322)
            long r8 = r8 & r0
            r11 = 0
            int r12 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r12 == 0) goto L_0x0046
            if (r5 == 0) goto L_0x0032
            com.tidalab.v2board.clash.design.ui.Surface r5 = r5.surface
            goto L_0x0033
        L_0x0032:
            r5 = r11
        L_0x0033:
            r14.updateRegistration(r10, r5)
            if (r5 == 0) goto L_0x003a
            com.tidalab.v2board.clash.design.ui.Insets r11 = r5.insets
        L_0x003a:
            if (r11 == 0) goto L_0x0046
            int r10 = r11.end
            int r5 = r11.start
            int r8 = r11.top
            r13 = r10
            r10 = r8
            r8 = r13
            goto L_0x0048
        L_0x0046:
            r5 = 0
            r8 = 0
        L_0x0048:
            if (r12 == 0) goto L_0x0061
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r9 = r14.activityBarLayout
            float r10 = (float) r10
            androidx.core.app.AppOpsManagerCompat.setPaddingTop(r9, r10)
            com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding r9 = r14.mainList
            r9.setInsets(r11)
            androidx.coordinatorlayout.widget.CoordinatorLayout r9 = r14.mboundView0
            float r5 = (float) r5
            androidx.core.app.AppOpsManagerCompat.setPaddingStart(r9, r5)
            androidx.coordinatorlayout.widget.CoordinatorLayout r5 = r14.mboundView0
            float r8 = (float) r8
            androidx.core.app.AppOpsManagerCompat.setPaddingEnd(r5, r8)
        L_0x0061:
            r8 = 64
            long r8 = r8 & r0
            int r5 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x006f
            android.widget.ImageView r5 = r14.newView
            android.view.View$OnClickListener r8 = r14.mCallback25
            r5.setOnClickListener(r8)
        L_0x006f:
            long r0 = r0 & r6
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x0079
            android.widget.ImageView r0 = r14.newView
            r0.setVisibility(r4)
        L_0x0079:
            com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding r0 = r14.mainList
            r0.executeBindingsInternal()
            return
        L_0x007f:
            r0 = move-exception
            monitor-exit(r14)     // Catch: all -> 0x007f
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DesignFilesBindingImpl.executeBindings():void");
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
            this.mDirtyFlags = 64L;
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
                    this.mDirtyFlags |= 32;
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

    @Override // com.tidalab.v2board.clash.design.databinding.DesignFilesBinding
    public void setConfigurationEditable(boolean z) {
        this.mConfigurationEditable = z;
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DesignFilesBinding
    public void setCurrentInBaseDir(boolean z) {
        this.mCurrentInBaseDir = z;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(9);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DesignFilesBinding
    public void setSelf(FilesDesign filesDesign) {
        this.mSelf = filesDesign;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(26);
        requestRebind();
    }
}
