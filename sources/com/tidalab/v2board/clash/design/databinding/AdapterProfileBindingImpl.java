package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.TextView;
import com.tidalab.v2board.clash.design.ui.ObservableCurrentTime;
import com.tidalab.v2board.clash.service.model.Profile;
/* loaded from: classes.dex */
public class AdapterProfileBindingImpl extends AdapterProfileBinding {
    public long mDirtyFlags = -1;
    public final TextView mboundView2;
    public final TextView mboundView3;
    public final View mboundView5;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AdapterProfileBindingImpl(androidx.databinding.DataBindingComponent r12, android.view.View r13) {
        /*
            r11 = this;
            r0 = 7
            r1 = 0
            java.lang.Object[] r0 = androidx.databinding.ViewDataBinding.mapBindings(r12, r13, r0, r1, r1)
            r2 = 4
            r2 = r0[r2]
            r7 = r2
            android.widget.TextView r7 = (android.widget.TextView) r7
            r2 = 1
            r2 = r0[r2]
            r8 = r2
            android.widget.RadioButton r8 = (android.widget.RadioButton) r8
            r2 = 6
            r2 = r0[r2]
            r9 = r2
            android.widget.FrameLayout r9 = (android.widget.FrameLayout) r9
            r2 = 0
            r2 = r0[r2]
            r10 = r2
            android.widget.RelativeLayout r10 = (android.widget.RelativeLayout) r10
            r6 = 1
            r3 = r11
            r4 = r12
            r5 = r13
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            r2 = -1
            r11.mDirtyFlags = r2
            android.widget.TextView r12 = r11.elapsedView
            r12.setTag(r1)
            android.widget.RadioButton r12 = r11.iconView
            r12.setTag(r1)
            r12 = 2
            r12 = r0[r12]
            android.widget.TextView r12 = (android.widget.TextView) r12
            r11.mboundView2 = r12
            r12.setTag(r1)
            r12 = 3
            r12 = r0[r12]
            android.widget.TextView r12 = (android.widget.TextView) r12
            r11.mboundView3 = r12
            r12.setTag(r1)
            r12 = 5
            r12 = r0[r12]
            android.view.View r12 = (android.view.View) r12
            r11.mboundView5 = r12
            r12.setTag(r1)
            android.widget.FrameLayout r12 = r11.menuView
            r12.setTag(r1)
            android.widget.RelativeLayout r12 = r11.rootView
            r12.setTag(r1)
            r12 = 2131296397(0x7f09008d, float:1.821071E38)
            r13.setTag(r12, r11)
            r11.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.AdapterProfileBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void executeBindings() {
        /*
            Method dump skipped, instructions count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.AdapterProfileBindingImpl.executeBindings():void");
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
            this.mDirtyFlags = 32L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        ObservableCurrentTime observableCurrentTime = (ObservableCurrentTime) obj;
        if (i2 == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
        } else if (i2 != 33) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
        }
        return true;
    }

    @Override // com.tidalab.v2board.clash.design.databinding.AdapterProfileBinding
    public void setClicked(View.OnClickListener onClickListener) {
        this.mClicked = onClickListener;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(4);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.AdapterProfileBinding
    public void setCurrentTime(ObservableCurrentTime observableCurrentTime) {
        updateRegistration(0, observableCurrentTime);
        this.mCurrentTime = observableCurrentTime;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(10);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.AdapterProfileBinding
    public void setMenu(View.OnClickListener onClickListener) {
        this.mMenu = onClickListener;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(16);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.AdapterProfileBinding
    public void setProfile(Profile profile) {
        this.mProfile = profile;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(22);
        requestRebind();
    }
}
