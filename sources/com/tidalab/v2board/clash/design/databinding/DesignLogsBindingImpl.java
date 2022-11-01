package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.tidalab.v2board.clash.design.LogsDesign;
import com.tidalab.v2board.clash.design.generated.callback.OnClickListener;
import com.tidalab.v2board.clash.design.ui.Surface;
import com.tidalab.v2board.clash.design.view.ActionLabel;
import kotlinx.coroutines.channels.SendChannel;
/* loaded from: classes.dex */
public class DesignLogsBindingImpl extends DesignLogsBinding implements OnClickListener.Listener {
    public final CoordinatorLayout mboundView0;
    public final RelativeLayout mboundView2;
    public final ActionLabel mboundView4;
    public final TextView mboundView5;
    public long mDirtyFlags = -1;
    public final View.OnClickListener mCallback12 = new OnClickListener(this, 2);
    public final View.OnClickListener mCallback11 = new OnClickListener(this, 1);

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DesignLogsBindingImpl(androidx.databinding.DataBindingComponent r12, android.view.View r13) {
        /*
            r11 = this;
            r0 = 7
            r1 = 0
            java.lang.Object[] r0 = androidx.databinding.ViewDataBinding.mapBindings(r12, r13, r0, r1, r1)
            r2 = 1
            r3 = r0[r2]
            r8 = r3
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r8 = (com.tidalab.v2board.clash.design.view.ActivityBarLayout) r8
            r3 = 3
            r3 = r0[r3]
            r9 = r3
            android.widget.ImageView r9 = (android.widget.ImageView) r9
            r3 = 6
            r3 = r0[r3]
            r10 = r3
            androidx.recyclerview.widget.RecyclerView r10 = (androidx.recyclerview.widget.RecyclerView) r10
            r7 = 1
            r4 = r11
            r5 = r12
            r6 = r13
            r4.<init>(r5, r6, r7, r8, r9, r10)
            r3 = -1
            r11.mDirtyFlags = r3
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r12 = r11.activityBarLayout
            r12.setTag(r1)
            android.widget.ImageView r12 = r11.deleteView
            r12.setTag(r1)
            r12 = 0
            r12 = r0[r12]
            androidx.coordinatorlayout.widget.CoordinatorLayout r12 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r12
            r11.mboundView0 = r12
            r12.setTag(r1)
            r12 = 2
            r3 = r0[r12]
            android.widget.RelativeLayout r3 = (android.widget.RelativeLayout) r3
            r11.mboundView2 = r3
            r3.setTag(r1)
            r3 = 4
            r3 = r0[r3]
            com.tidalab.v2board.clash.design.view.ActionLabel r3 = (com.tidalab.v2board.clash.design.view.ActionLabel) r3
            r11.mboundView4 = r3
            r3.setTag(r1)
            r3 = 5
            r0 = r0[r3]
            android.widget.TextView r0 = (android.widget.TextView) r0
            r11.mboundView5 = r0
            r0.setTag(r1)
            androidx.recyclerview.widget.RecyclerView r0 = r11.recyclerList
            r0.setTag(r1)
            r0 = 2131296397(0x7f09008d, float:1.821071E38)
            r13.setTag(r0, r11)
            com.tidalab.v2board.clash.design.generated.callback.OnClickListener r13 = new com.tidalab.v2board.clash.design.generated.callback.OnClickListener
            r13.<init>(r11, r12)
            r11.mCallback12 = r13
            com.tidalab.v2board.clash.design.generated.callback.OnClickListener r12 = new com.tidalab.v2board.clash.design.generated.callback.OnClickListener
            r12.<init>(r11, r2)
            r11.mCallback11 = r12
            r11.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DesignLogsBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
    }

    @Override // com.tidalab.v2board.clash.design.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        boolean z = false;
        if (i == 1) {
            LogsDesign logsDesign = this.mSelf;
            if (logsDesign != null) {
                SendChannel sendChannel = logsDesign.requests;
                if (sendChannel != null) {
                    z = true;
                }
                if (z) {
                    sendChannel.offer(LogsDesign.Request.DeleteAll.INSTANCE);
                }
            }
        } else if (i == 2) {
            LogsDesign logsDesign2 = this.mSelf;
            if (logsDesign2 != null) {
                SendChannel sendChannel2 = logsDesign2.requests;
                if (sendChannel2 != null) {
                    z = true;
                }
                if (z) {
                    sendChannel2.offer(LogsDesign.Request.StartLogcat.INSTANCE);
                }
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
            long r0 = r10.mDirtyFlags     // Catch: all -> 0x0084
            r2 = 0
            r10.mDirtyFlags = r2     // Catch: all -> 0x0084
            monitor-exit(r10)     // Catch: all -> 0x0084
            com.tidalab.v2board.clash.design.LogsDesign r4 = r10.mSelf
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
            androidx.recyclerview.widget.RecyclerView r4 = r10.recyclerList
            float r5 = (float) r5
            androidx.core.app.AppOpsManagerCompat.setPaddingBottom(r4, r5)
        L_0x004c:
            r4 = 8
            long r0 = r0 & r4
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0083
            android.widget.ImageView r0 = r10.deleteView
            android.view.View$OnClickListener r1 = r10.mCallback11
            r0.setOnClickListener(r1)
            com.tidalab.v2board.clash.design.view.ActionLabel r0 = r10.mboundView4
            android.view.View$OnClickListener r1 = r10.mCallback12
            r0.setOnClickListener(r1)
            android.widget.TextView r0 = r10.mboundView5
            android.content.res.Resources r1 = r0.getResources()
            r2 = 2131165347(0x7f0700a3, float:1.7944909E38)
            float r1 = r1.getDimension(r2)
            android.widget.TextView r2 = r10.mboundView5
            android.content.res.Resources r2 = r2.getResources()
            r3 = 2131165348(0x7f0700a4, float:1.794491E38)
            float r2 = r2.getDimension(r3)
            r3 = 1073741824(0x40000000, float:2.0)
            float r2 = r2 * r3
            float r2 = r2 + r1
            androidx.core.app.AppOpsManagerCompat.setPaddingStart(r0, r2)
        L_0x0083:
            return
        L_0x0084:
            r0 = move-exception
            monitor-exit(r10)     // Catch: all -> 0x0084
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DesignLogsBindingImpl.executeBindings():void");
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

    @Override // com.tidalab.v2board.clash.design.databinding.DesignLogsBinding
    public void setSelf(LogsDesign logsDesign) {
        this.mSelf = logsDesign;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(26);
        requestRebind();
    }
}
