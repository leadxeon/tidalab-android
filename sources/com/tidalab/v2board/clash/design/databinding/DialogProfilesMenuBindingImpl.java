package com.tidalab.v2board.clash.design.databinding;

import android.app.Dialog;
import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.ProfilesDesign;
import com.tidalab.v2board.clash.design.generated.callback.OnClickListener;
import com.tidalab.v2board.clash.design.view.LargeActionLabel;
import com.tidalab.v2board.clash.foss.R;
import com.tidalab.v2board.clash.service.model.Profile;
/* loaded from: classes.dex */
public class DialogProfilesMenuBindingImpl extends DialogProfilesMenuBinding implements OnClickListener.Listener {
    public final LinearLayout mboundView0;
    public final LargeActionLabel mboundView1;
    public final LargeActionLabel mboundView2;
    public final LargeActionLabel mboundView3;
    public final LargeActionLabel mboundView4;
    public long mDirtyFlags = -1;
    public final View.OnClickListener mCallback23 = new OnClickListener(this, 3);
    public final View.OnClickListener mCallback24 = new OnClickListener(this, 4);
    public final View.OnClickListener mCallback21 = new OnClickListener(this, 1);
    public final View.OnClickListener mCallback22 = new OnClickListener(this, 2);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DialogProfilesMenuBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        super(dataBindingComponent, view, 0);
        Object[] mapBindings = ViewDataBinding.mapBindings(dataBindingComponent, view, 5, null, null);
        LinearLayout linearLayout = (LinearLayout) mapBindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        LargeActionLabel largeActionLabel = (LargeActionLabel) mapBindings[1];
        this.mboundView1 = largeActionLabel;
        largeActionLabel.setTag(null);
        LargeActionLabel largeActionLabel2 = (LargeActionLabel) mapBindings[2];
        this.mboundView2 = largeActionLabel2;
        largeActionLabel2.setTag(null);
        LargeActionLabel largeActionLabel3 = (LargeActionLabel) mapBindings[3];
        this.mboundView3 = largeActionLabel3;
        largeActionLabel3.setTag(null);
        LargeActionLabel largeActionLabel4 = (LargeActionLabel) mapBindings[4];
        this.mboundView4 = largeActionLabel4;
        largeActionLabel4.setTag(null);
        view.setTag(R.id.dataBinding, this);
        invalidateAll();
    }

    @Override // com.tidalab.v2board.clash.design.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        boolean z = false;
        if (i == 1) {
            ProfilesDesign profilesDesign = this.mMaster;
            Profile profile = this.mProfile;
            Dialog dialog = this.mSelf;
            if (profilesDesign != null) {
                z = true;
            }
            if (z) {
                profilesDesign.requests.mo14trySendJP2dKIU(new ProfilesDesign.Request.Update(profile));
                dialog.dismiss();
            }
        } else if (i == 2) {
            ProfilesDesign profilesDesign2 = this.mMaster;
            Profile profile2 = this.mProfile;
            Dialog dialog2 = this.mSelf;
            if (profilesDesign2 != null) {
                z = true;
            }
            if (z) {
                profilesDesign2.requests.mo14trySendJP2dKIU(new ProfilesDesign.Request.Edit(profile2));
                dialog2.dismiss();
            }
        } else if (i == 3) {
            ProfilesDesign profilesDesign3 = this.mMaster;
            Profile profile3 = this.mProfile;
            Dialog dialog3 = this.mSelf;
            if (profilesDesign3 != null) {
                z = true;
            }
            if (z) {
                profilesDesign3.requests.mo14trySendJP2dKIU(new ProfilesDesign.Request.Duplicate(profile3));
                dialog3.dismiss();
            }
        } else if (i == 4) {
            ProfilesDesign profilesDesign4 = this.mMaster;
            Profile profile4 = this.mProfile;
            Dialog dialog4 = this.mSelf;
            if (profilesDesign4 != null) {
                z = true;
            }
            if (z) {
                profilesDesign4.requests.mo14trySendJP2dKIU(new ProfilesDesign.Request.Delete(profile4));
                dialog4.dismiss();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void executeBindings() {
        /*
            r17 = this;
            r1 = r17
            monitor-enter(r17)
            long r2 = r1.mDirtyFlags     // Catch: all -> 0x0091
            r4 = 0
            r1.mDirtyFlags = r4     // Catch: all -> 0x0091
            monitor-exit(r17)     // Catch: all -> 0x0091
            com.tidalab.v2board.clash.service.model.Profile r0 = r1.mProfile
            r6 = 0
            r7 = 10
            long r9 = r2 & r7
            r11 = 8
            r12 = 32
            r14 = 0
            int r15 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x0034
            if (r0 == 0) goto L_0x001f
            boolean r9 = r0.imported
            goto L_0x0020
        L_0x001f:
            r9 = 0
        L_0x0020:
            if (r15 == 0) goto L_0x002e
            if (r9 == 0) goto L_0x0028
            long r2 = r2 | r12
            r15 = 128(0x80, double:6.32E-322)
            goto L_0x002d
        L_0x0028:
            r15 = 16
            long r2 = r2 | r15
            r15 = 64
        L_0x002d:
            long r2 = r2 | r15
        L_0x002e:
            if (r9 == 0) goto L_0x0031
            goto L_0x0035
        L_0x0031:
            r10 = 8
            goto L_0x0036
        L_0x0034:
            r9 = 0
        L_0x0035:
            r10 = 0
        L_0x0036:
            long r12 = r12 & r2
            int r15 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x0045
            if (r0 == 0) goto L_0x003f
            com.tidalab.v2board.clash.service.model.Profile$Type r6 = r0.type
        L_0x003f:
            com.tidalab.v2board.clash.service.model.Profile$Type r0 = com.tidalab.v2board.clash.service.model.Profile.Type.File
            if (r6 == r0) goto L_0x0045
            r0 = 1
            goto L_0x0046
        L_0x0045:
            r0 = 0
        L_0x0046:
            long r12 = r2 & r7
            int r6 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r6 == 0) goto L_0x005e
            if (r9 == 0) goto L_0x004f
            goto L_0x0050
        L_0x004f:
            r0 = 0
        L_0x0050:
            if (r6 == 0) goto L_0x005a
            if (r0 == 0) goto L_0x0057
            r12 = 512(0x200, double:2.53E-321)
            goto L_0x0059
        L_0x0057:
            r12 = 256(0x100, double:1.265E-321)
        L_0x0059:
            long r2 = r2 | r12
        L_0x005a:
            if (r0 == 0) goto L_0x005d
            r11 = 0
        L_0x005d:
            r14 = r11
        L_0x005e:
            r11 = 8
            long r11 = r11 & r2
            int r0 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0081
            com.tidalab.v2board.clash.design.view.LargeActionLabel r0 = r1.mboundView1
            android.view.View$OnClickListener r6 = r1.mCallback21
            r0.setOnClickListener(r6)
            com.tidalab.v2board.clash.design.view.LargeActionLabel r0 = r1.mboundView2
            android.view.View$OnClickListener r6 = r1.mCallback22
            r0.setOnClickListener(r6)
            com.tidalab.v2board.clash.design.view.LargeActionLabel r0 = r1.mboundView3
            android.view.View$OnClickListener r6 = r1.mCallback23
            r0.setOnClickListener(r6)
            com.tidalab.v2board.clash.design.view.LargeActionLabel r0 = r1.mboundView4
            android.view.View$OnClickListener r6 = r1.mCallback24
            r0.setOnClickListener(r6)
        L_0x0081:
            long r2 = r2 & r7
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0090
            com.tidalab.v2board.clash.design.view.LargeActionLabel r0 = r1.mboundView1
            r0.setVisibility(r14)
            com.tidalab.v2board.clash.design.view.LargeActionLabel r0 = r1.mboundView3
            r0.setVisibility(r10)
        L_0x0090:
            return
        L_0x0091:
            r0 = move-exception
            monitor-exit(r17)     // Catch: all -> 0x0091
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DialogProfilesMenuBindingImpl.executeBindings():void");
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
        return false;
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DialogProfilesMenuBinding
    public void setMaster(ProfilesDesign profilesDesign) {
        this.mMaster = profilesDesign;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(15);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DialogProfilesMenuBinding
    public void setProfile(Profile profile) {
        this.mProfile = profile;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(22);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DialogProfilesMenuBinding
    public void setSelf(Dialog dialog) {
        this.mSelf = dialog;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(26);
        requestRebind();
    }
}
