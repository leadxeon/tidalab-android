package com.tidalab.v2board.clash.design.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.tidalab.v2board.clash.design.ProxyDesign;
import com.tidalab.v2board.clash.design.generated.callback.OnClickListener;
import com.tidalab.v2board.clash.design.ui.Surface;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class DesignProxyBindingImpl extends DesignProxyBinding implements OnClickListener.Listener {
    public static final SparseIntArray sViewsWithIds;
    public final CoordinatorLayout mboundView0;
    public final RelativeLayout mboundView2;
    public final FrameLayout mboundView4;
    public long mDirtyFlags = -1;
    public final View.OnClickListener mCallback10 = new OnClickListener(this, 2);
    public final View.OnClickListener mCallback9 = new OnClickListener(this, 1);

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.empty_view, 6);
        sparseIntArray.put(R.id.pages_view, 7);
        sparseIntArray.put(R.id.url_test_layout, 8);
        sparseIntArray.put(R.id.url_test_progress_view, 9);
        sparseIntArray.put(R.id.menu_view, 10);
        sparseIntArray.put(R.id.tab_layout_view, 11);
        sparseIntArray.put(R.id.elevation_view, 12);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DesignProxyBindingImpl(androidx.databinding.DataBindingComponent r20, android.view.View r21) {
        /*
            r19 = this;
            r14 = r19
            r15 = r21
            android.util.SparseIntArray r0 = com.tidalab.v2board.clash.design.databinding.DesignProxyBindingImpl.sViewsWithIds
            r1 = 13
            r13 = 0
            r2 = r20
            java.lang.Object[] r16 = androidx.databinding.ViewDataBinding.mapBindings(r2, r15, r1, r13, r0)
            r12 = 1
            r0 = r16[r12]
            r4 = r0
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r4 = (com.tidalab.v2board.clash.design.view.ActivityBarLayout) r4
            r0 = 12
            r0 = r16[r0]
            r5 = r0
            android.view.View r5 = (android.view.View) r5
            r0 = 6
            r0 = r16[r0]
            r6 = r0
            android.widget.TextView r6 = (android.widget.TextView) r6
            r0 = 10
            r0 = r16[r0]
            r7 = r0
            android.widget.ImageView r7 = (android.widget.ImageView) r7
            r0 = 7
            r0 = r16[r0]
            r8 = r0
            androidx.viewpager2.widget.ViewPager2 r8 = (androidx.viewpager2.widget.ViewPager2) r8
            r0 = 11
            r0 = r16[r0]
            r9 = r0
            com.google.android.material.tabs.TabLayout r9 = (com.google.android.material.tabs.TabLayout) r9
            r0 = 5
            r0 = r16[r0]
            r10 = r0
            com.google.android.material.floatingactionbutton.FloatingActionButton r10 = (com.google.android.material.floatingactionbutton.FloatingActionButton) r10
            r0 = 8
            r0 = r16[r0]
            r11 = r0
            android.widget.FrameLayout r11 = (android.widget.FrameLayout) r11
            r0 = 9
            r0 = r16[r0]
            r17 = r0
            android.widget.ProgressBar r17 = (android.widget.ProgressBar) r17
            r0 = 3
            r0 = r16[r0]
            r18 = r0
            android.widget.ImageView r18 = (android.widget.ImageView) r18
            r3 = 1
            r0 = r19
            r1 = r20
            r2 = r21
            r12 = r17
            r15 = r13
            r13 = r18
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            r0 = -1
            r14.mDirtyFlags = r0
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r0 = r14.activityBarLayout
            r0.setTag(r15)
            r0 = 0
            r0 = r16[r0]
            androidx.coordinatorlayout.widget.CoordinatorLayout r0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r0
            r14.mboundView0 = r0
            r0.setTag(r15)
            r0 = 2
            r1 = r16[r0]
            android.widget.RelativeLayout r1 = (android.widget.RelativeLayout) r1
            r14.mboundView2 = r1
            r1.setTag(r15)
            r1 = 4
            r1 = r16[r1]
            android.widget.FrameLayout r1 = (android.widget.FrameLayout) r1
            r14.mboundView4 = r1
            r1.setTag(r15)
            com.google.android.material.floatingactionbutton.FloatingActionButton r1 = r14.urlTestFloatView
            r1.setTag(r15)
            android.widget.ImageView r1 = r14.urlTestView
            r1.setTag(r15)
            r1 = 2131296397(0x7f09008d, float:1.821071E38)
            r2.setTag(r1, r14)
            com.tidalab.v2board.clash.design.generated.callback.OnClickListener r1 = new com.tidalab.v2board.clash.design.generated.callback.OnClickListener
            r1.<init>(r14, r0)
            r14.mCallback10 = r1
            com.tidalab.v2board.clash.design.generated.callback.OnClickListener r0 = new com.tidalab.v2board.clash.design.generated.callback.OnClickListener
            r1 = 1
            r0.<init>(r14, r1)
            r14.mCallback9 = r0
            r19.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DesignProxyBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
    }

    @Override // com.tidalab.v2board.clash.design.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        boolean z = false;
        if (i == 1) {
            ProxyDesign proxyDesign = this.mSelf;
            if (proxyDesign != null) {
                z = true;
            }
            if (z) {
                proxyDesign.requestUrlTesting();
            }
        } else if (i == 2) {
            ProxyDesign proxyDesign2 = this.mSelf;
            if (proxyDesign2 != null) {
                z = true;
            }
            if (z) {
                proxyDesign2.requestUrlTesting();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void executeBindings() {
        /*
            r10 = this;
            monitor-enter(r10)
            long r0 = r10.mDirtyFlags     // Catch: all -> 0x0062
            r2 = 0
            r10.mDirtyFlags = r2     // Catch: all -> 0x0062
            monitor-exit(r10)     // Catch: all -> 0x0062
            com.tidalab.v2board.clash.design.ProxyDesign r4 = r10.mSelf
            r5 = 15
            long r5 = r5 & r0
            r7 = 0
            int r8 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r8 == 0) goto L_0x002f
            r5 = 0
            if (r4 == 0) goto L_0x0018
            com.tidalab.v2board.clash.design.ui.Surface r4 = r4.surface
            goto L_0x0019
        L_0x0018:
            r4 = r5
        L_0x0019:
            r10.updateRegistration(r7, r4)
            if (r4 == 0) goto L_0x0020
            com.tidalab.v2board.clash.design.ui.Insets r5 = r4.insets
        L_0x0020:
            if (r5 == 0) goto L_0x002f
            int r7 = r5.end
            int r4 = r5.bottom
            int r6 = r5.start
            int r5 = r5.top
            r9 = r5
            r5 = r4
            r4 = r7
            r7 = r9
            goto L_0x0032
        L_0x002f:
            r4 = 0
            r5 = 0
            r6 = 0
        L_0x0032:
            if (r8 == 0) goto L_0x004c
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r8 = r10.activityBarLayout
            float r7 = (float) r7
            androidx.core.app.AppOpsManagerCompat.setPaddingTop(r8, r7)
            androidx.coordinatorlayout.widget.CoordinatorLayout r7 = r10.mboundView0
            float r6 = (float) r6
            androidx.core.app.AppOpsManagerCompat.setPaddingStart(r7, r6)
            androidx.coordinatorlayout.widget.CoordinatorLayout r6 = r10.mboundView0
            float r4 = (float) r4
            androidx.core.app.AppOpsManagerCompat.setPaddingEnd(r6, r4)
            android.widget.FrameLayout r4 = r10.mboundView4
            float r5 = (float) r5
            androidx.core.app.AppOpsManagerCompat.setPaddingBottom(r4, r5)
        L_0x004c:
            r4 = 8
            long r0 = r0 & r4
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0061
            com.google.android.material.floatingactionbutton.FloatingActionButton r0 = r10.urlTestFloatView
            android.view.View$OnClickListener r1 = r10.mCallback10
            r0.setOnClickListener(r1)
            android.widget.ImageView r0 = r10.urlTestView
            android.view.View$OnClickListener r1 = r10.mCallback9
            r0.setOnClickListener(r1)
        L_0x0061:
            return
        L_0x0062:
            r0 = move-exception
            monitor-exit(r10)     // Catch: all -> 0x0062
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DesignProxyBindingImpl.executeBindings():void");
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

    @Override // com.tidalab.v2board.clash.design.databinding.DesignProxyBinding
    public void setSelf(ProxyDesign proxyDesign) {
        this.mSelf = proxyDesign;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(26);
        requestRebind();
    }
}
