package com.tidalab.v2board.clash.design.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.AppOpsManagerCompat;
import com.tidalab.v2board.clash.design.OverrideSettingsDesign;
import com.tidalab.v2board.clash.design.generated.callback.OnClickListener;
import com.tidalab.v2board.clash.design.ui.Insets;
import com.tidalab.v2board.clash.design.ui.Surface;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class DesignSettingsOverideBindingImpl extends DesignSettingsOverideBinding implements OnClickListener.Listener {
    public static final SparseIntArray sViewsWithIds;
    public final CoordinatorLayout mboundView0;
    public final RelativeLayout mboundView3;
    public long mDirtyFlags = -1;
    public final View.OnClickListener mCallback36 = new OnClickListener(this, 1);

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.scroll_root, 5);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DesignSettingsOverideBindingImpl(androidx.databinding.DataBindingComponent r12, android.view.View r13) {
        /*
            r11 = this;
            android.util.SparseIntArray r0 = com.tidalab.v2board.clash.design.databinding.DesignSettingsOverideBindingImpl.sViewsWithIds
            r1 = 6
            r2 = 0
            java.lang.Object[] r0 = androidx.databinding.ViewDataBinding.mapBindings(r12, r13, r1, r2, r0)
            r1 = 2
            r1 = r0[r1]
            r7 = r1
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r7 = (com.tidalab.v2board.clash.design.view.ActivityBarLayout) r7
            r1 = 4
            r1 = r0[r1]
            r8 = r1
            android.widget.ImageView r8 = (android.widget.ImageView) r8
            r1 = 1
            r3 = r0[r1]
            r9 = r3
            android.widget.FrameLayout r9 = (android.widget.FrameLayout) r9
            r3 = 5
            r3 = r0[r3]
            r10 = r3
            com.tidalab.v2board.clash.design.view.ObservableScrollView r10 = (com.tidalab.v2board.clash.design.view.ObservableScrollView) r10
            r6 = 1
            r3 = r11
            r4 = r12
            r5 = r13
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            r3 = -1
            r11.mDirtyFlags = r3
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r12 = r11.activityBarLayout
            r12.setTag(r2)
            android.widget.ImageView r12 = r11.clearView
            r12.setTag(r2)
            android.widget.FrameLayout r12 = r11.content
            r12.setTag(r2)
            r12 = 0
            r12 = r0[r12]
            androidx.coordinatorlayout.widget.CoordinatorLayout r12 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r12
            r11.mboundView0 = r12
            r12.setTag(r2)
            r12 = 3
            r12 = r0[r12]
            android.widget.RelativeLayout r12 = (android.widget.RelativeLayout) r12
            r11.mboundView3 = r12
            r12.setTag(r2)
            r12 = 2131296397(0x7f09008d, float:1.821071E38)
            r13.setTag(r12, r11)
            com.tidalab.v2board.clash.design.generated.callback.OnClickListener r12 = new com.tidalab.v2board.clash.design.generated.callback.OnClickListener
            r12.<init>(r11, r1)
            r11.mCallback36 = r12
            r11.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DesignSettingsOverideBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
    }

    @Override // com.tidalab.v2board.clash.design.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        OverrideSettingsDesign overrideSettingsDesign = this.mSelf;
        if (overrideSettingsDesign != null) {
            overrideSettingsDesign.requests.mo14trySendJP2dKIU(OverrideSettingsDesign.Request.ResetOverride);
        }
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
        OverrideSettingsDesign overrideSettingsDesign = this.mSelf;
        int i4 = 0;
        int i5 = ((15 & j) > 0L ? 1 : ((15 & j) == 0L ? 0 : -1));
        if (i5 != 0) {
            Insets insets = null;
            Surface surface = overrideSettingsDesign != null ? overrideSettingsDesign.surface : null;
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
            f = i4 + this.content.getResources().getDimension(R.dimen.toolbar_height);
        } else {
            i3 = 0;
            i2 = 0;
            i = 0;
        }
        if (i5 != 0) {
            AppOpsManagerCompat.setPaddingTop(this.activityBarLayout, i4);
            AppOpsManagerCompat.setPaddingTop(this.content, f);
            AppOpsManagerCompat.setPaddingBottom(this.content, i2);
            AppOpsManagerCompat.setPaddingStart(this.mboundView0, i);
            AppOpsManagerCompat.setPaddingEnd(this.mboundView0, i3);
        }
        if ((j & 8) != 0) {
            this.clearView.setOnClickListener(this.mCallback36);
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

    @Override // com.tidalab.v2board.clash.design.databinding.DesignSettingsOverideBinding
    public void setSelf(OverrideSettingsDesign overrideSettingsDesign) {
        this.mSelf = overrideSettingsDesign;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(26);
        requestRebind();
    }
}
