package com.tidalab.v2board.clash.design.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.AppOpsManagerCompat;
import com.tidalab.v2board.clash.design.SettingsDesign;
import com.tidalab.v2board.clash.design.generated.callback.OnClickListener;
import com.tidalab.v2board.clash.design.ui.Insets;
import com.tidalab.v2board.clash.design.ui.Surface;
import com.tidalab.v2board.clash.design.view.ActionLabel;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class DesignSettingsBindingImpl extends DesignSettingsBinding implements OnClickListener.Listener {
    public static final SparseIntArray sViewsWithIds;
    public final CoordinatorLayout mboundView0;
    public final LinearLayout mboundView1;
    public final ActionLabel mboundView2;
    public final ActionLabel mboundView3;
    public final ActionLabel mboundView4;
    public long mDirtyFlags = -1;
    public final View.OnClickListener mCallback34 = new OnClickListener(this, 2);
    public final View.OnClickListener mCallback35 = new OnClickListener(this, 3);
    public final View.OnClickListener mCallback33 = new OnClickListener(this, 1);

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.scroll_root, 6);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DesignSettingsBindingImpl(androidx.databinding.DataBindingComponent r10, android.view.View r11) {
        /*
            r9 = this;
            android.util.SparseIntArray r0 = com.tidalab.v2board.clash.design.databinding.DesignSettingsBindingImpl.sViewsWithIds
            r1 = 7
            r2 = 0
            java.lang.Object[] r0 = androidx.databinding.ViewDataBinding.mapBindings(r10, r11, r1, r2, r0)
            r1 = 5
            r1 = r0[r1]
            r7 = r1
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r7 = (com.tidalab.v2board.clash.design.view.ActivityBarLayout) r7
            r1 = 6
            r1 = r0[r1]
            r8 = r1
            com.tidalab.v2board.clash.design.view.ObservableScrollView r8 = (com.tidalab.v2board.clash.design.view.ObservableScrollView) r8
            r6 = 1
            r3 = r9
            r4 = r10
            r5 = r11
            r3.<init>(r4, r5, r6, r7, r8)
            r3 = -1
            r9.mDirtyFlags = r3
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r10 = r9.activityBarLayout
            r10.setTag(r2)
            r10 = 0
            r10 = r0[r10]
            androidx.coordinatorlayout.widget.CoordinatorLayout r10 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r10
            r9.mboundView0 = r10
            r10.setTag(r2)
            r10 = 1
            r1 = r0[r10]
            android.widget.LinearLayout r1 = (android.widget.LinearLayout) r1
            r9.mboundView1 = r1
            r1.setTag(r2)
            r1 = 2
            r3 = r0[r1]
            com.tidalab.v2board.clash.design.view.ActionLabel r3 = (com.tidalab.v2board.clash.design.view.ActionLabel) r3
            r9.mboundView2 = r3
            r3.setTag(r2)
            r3 = 3
            r4 = r0[r3]
            com.tidalab.v2board.clash.design.view.ActionLabel r4 = (com.tidalab.v2board.clash.design.view.ActionLabel) r4
            r9.mboundView3 = r4
            r4.setTag(r2)
            r4 = 4
            r0 = r0[r4]
            com.tidalab.v2board.clash.design.view.ActionLabel r0 = (com.tidalab.v2board.clash.design.view.ActionLabel) r0
            r9.mboundView4 = r0
            r0.setTag(r2)
            r0 = 2131296397(0x7f09008d, float:1.821071E38)
            r11.setTag(r0, r9)
            com.tidalab.v2board.clash.design.generated.callback.OnClickListener r11 = new com.tidalab.v2board.clash.design.generated.callback.OnClickListener
            r11.<init>(r9, r1)
            r9.mCallback34 = r11
            com.tidalab.v2board.clash.design.generated.callback.OnClickListener r11 = new com.tidalab.v2board.clash.design.generated.callback.OnClickListener
            r11.<init>(r9, r3)
            r9.mCallback35 = r11
            com.tidalab.v2board.clash.design.generated.callback.OnClickListener r11 = new com.tidalab.v2board.clash.design.generated.callback.OnClickListener
            r11.<init>(r9, r10)
            r9.mCallback33 = r11
            r9.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DesignSettingsBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
    }

    @Override // com.tidalab.v2board.clash.design.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        boolean z = false;
        if (i == 1) {
            SettingsDesign settingsDesign = this.mSelf;
            if (settingsDesign != null) {
                z = true;
            }
            if (z) {
                settingsDesign.requests.mo14trySendJP2dKIU(SettingsDesign.Request.StartApp);
            }
        } else if (i == 2) {
            SettingsDesign settingsDesign2 = this.mSelf;
            if (settingsDesign2 != null) {
                z = true;
            }
            if (z) {
                settingsDesign2.requests.mo14trySendJP2dKIU(SettingsDesign.Request.StartNetwork);
            }
        } else if (i == 3) {
            SettingsDesign settingsDesign3 = this.mSelf;
            if (settingsDesign3 != null) {
                z = true;
            }
            if (z) {
                settingsDesign3.requests.mo14trySendJP2dKIU(SettingsDesign.Request.StartOverride);
            }
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
        SettingsDesign settingsDesign = this.mSelf;
        float f = 0.0f;
        int i4 = 0;
        int i5 = ((15 & j) > 0L ? 1 : ((15 & j) == 0L ? 0 : -1));
        if (i5 != 0) {
            Insets insets = null;
            Surface surface = settingsDesign != null ? settingsDesign.surface : null;
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
            f = i4 + this.mboundView1.getResources().getDimension(R.dimen.toolbar_height);
        } else {
            i3 = 0;
            i2 = 0;
            i = 0;
        }
        if (i5 != 0) {
            AppOpsManagerCompat.setPaddingTop(this.activityBarLayout, i4);
            AppOpsManagerCompat.setPaddingStart(this.mboundView0, i);
            AppOpsManagerCompat.setPaddingEnd(this.mboundView0, i3);
            AppOpsManagerCompat.setPaddingTop(this.mboundView1, f);
            AppOpsManagerCompat.setPaddingBottom(this.mboundView1, i2);
        }
        if ((j & 8) != 0) {
            this.mboundView2.setOnClickListener(this.mCallback33);
            this.mboundView3.setOnClickListener(this.mCallback34);
            this.mboundView4.setOnClickListener(this.mCallback35);
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

    @Override // com.tidalab.v2board.clash.design.databinding.DesignSettingsBinding
    public void setSelf(SettingsDesign settingsDesign) {
        this.mSelf = settingsDesign;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(26);
        requestRebind();
    }
}
