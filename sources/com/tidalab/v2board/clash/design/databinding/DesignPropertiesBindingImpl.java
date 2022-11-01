package com.tidalab.v2board.clash.design.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.AppOpsManagerCompat;
import com.tidalab.v2board.clash.design.PropertiesDesign;
import com.tidalab.v2board.clash.design.PropertiesDesign$inputInterval$1;
import com.tidalab.v2board.clash.design.PropertiesDesign$inputName$1;
import com.tidalab.v2board.clash.design.PropertiesDesign$inputUrl$1;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.generated.callback.OnClickListener;
import com.tidalab.v2board.clash.design.ui.Insets;
import com.tidalab.v2board.clash.design.ui.Surface;
import com.tidalab.v2board.clash.design.view.ActionLabel;
import com.tidalab.v2board.clash.design.view.ActionTextField;
import com.tidalab.v2board.clash.foss.R;
import com.tidalab.v2board.clash.service.model.Profile;
import java.util.Objects;
/* loaded from: classes.dex */
public class DesignPropertiesBindingImpl extends DesignPropertiesBinding implements OnClickListener.Listener {
    public static final SparseIntArray sViewsWithIds;
    public final CoordinatorLayout mboundView0;
    public final LinearLayout mboundView1;
    public final ActionTextField mboundView2;
    public final ActionTextField mboundView3;
    public final ActionTextField mboundView4;
    public final ActionLabel mboundView5;
    public final RelativeLayout mboundView7;
    public final ProgressBar mboundView8;
    public final ImageView mboundView9;
    public long mDirtyFlags = -1;
    public final View.OnClickListener mCallback29 = new OnClickListener(this, 2);
    public final View.OnClickListener mCallback30 = new OnClickListener(this, 3);
    public final View.OnClickListener mCallback28 = new OnClickListener(this, 1);
    public final View.OnClickListener mCallback32 = new OnClickListener(this, 5);
    public final View.OnClickListener mCallback31 = new OnClickListener(this, 4);

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.scroll_root, 10);
        sparseIntArray.put(R.id.tips, 11);
        sparseIntArray.put(R.id.action_layout, 12);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DesignPropertiesBindingImpl(androidx.databinding.DataBindingComponent r12, android.view.View r13) {
        /*
            r11 = this;
            android.util.SparseIntArray r0 = com.tidalab.v2board.clash.design.databinding.DesignPropertiesBindingImpl.sViewsWithIds
            r1 = 13
            r2 = 0
            java.lang.Object[] r0 = androidx.databinding.ViewDataBinding.mapBindings(r12, r13, r1, r2, r0)
            r1 = 12
            r1 = r0[r1]
            r7 = r1
            android.widget.FrameLayout r7 = (android.widget.FrameLayout) r7
            r1 = 6
            r1 = r0[r1]
            r8 = r1
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r8 = (com.tidalab.v2board.clash.design.view.ActivityBarLayout) r8
            r1 = 10
            r1 = r0[r1]
            r9 = r1
            com.tidalab.v2board.clash.design.view.ObservableScrollView r9 = (com.tidalab.v2board.clash.design.view.ObservableScrollView) r9
            r1 = 11
            r1 = r0[r1]
            r10 = r1
            android.widget.TextView r10 = (android.widget.TextView) r10
            r6 = 1
            r3 = r11
            r4 = r12
            r5 = r13
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            r3 = -1
            r11.mDirtyFlags = r3
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r12 = r11.activityBarLayout
            r12.setTag(r2)
            r12 = 0
            r12 = r0[r12]
            androidx.coordinatorlayout.widget.CoordinatorLayout r12 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r12
            r11.mboundView0 = r12
            r12.setTag(r2)
            r12 = 1
            r1 = r0[r12]
            android.widget.LinearLayout r1 = (android.widget.LinearLayout) r1
            r11.mboundView1 = r1
            r1.setTag(r2)
            r1 = 2
            r3 = r0[r1]
            com.tidalab.v2board.clash.design.view.ActionTextField r3 = (com.tidalab.v2board.clash.design.view.ActionTextField) r3
            r11.mboundView2 = r3
            r3.setTag(r2)
            r3 = 3
            r4 = r0[r3]
            com.tidalab.v2board.clash.design.view.ActionTextField r4 = (com.tidalab.v2board.clash.design.view.ActionTextField) r4
            r11.mboundView3 = r4
            r4.setTag(r2)
            r4 = 4
            r5 = r0[r4]
            com.tidalab.v2board.clash.design.view.ActionTextField r5 = (com.tidalab.v2board.clash.design.view.ActionTextField) r5
            r11.mboundView4 = r5
            r5.setTag(r2)
            r5 = 5
            r6 = r0[r5]
            com.tidalab.v2board.clash.design.view.ActionLabel r6 = (com.tidalab.v2board.clash.design.view.ActionLabel) r6
            r11.mboundView5 = r6
            r6.setTag(r2)
            r6 = 7
            r6 = r0[r6]
            android.widget.RelativeLayout r6 = (android.widget.RelativeLayout) r6
            r11.mboundView7 = r6
            r6.setTag(r2)
            r6 = 8
            r6 = r0[r6]
            android.widget.ProgressBar r6 = (android.widget.ProgressBar) r6
            r11.mboundView8 = r6
            r6.setTag(r2)
            r6 = 9
            r0 = r0[r6]
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            r11.mboundView9 = r0
            r0.setTag(r2)
            r0 = 2131296397(0x7f09008d, float:1.821071E38)
            r13.setTag(r0, r11)
            com.tidalab.v2board.clash.design.generated.callback.OnClickListener r13 = new com.tidalab.v2board.clash.design.generated.callback.OnClickListener
            r13.<init>(r11, r1)
            r11.mCallback29 = r13
            com.tidalab.v2board.clash.design.generated.callback.OnClickListener r13 = new com.tidalab.v2board.clash.design.generated.callback.OnClickListener
            r13.<init>(r11, r3)
            r11.mCallback30 = r13
            com.tidalab.v2board.clash.design.generated.callback.OnClickListener r13 = new com.tidalab.v2board.clash.design.generated.callback.OnClickListener
            r13.<init>(r11, r12)
            r11.mCallback28 = r13
            com.tidalab.v2board.clash.design.generated.callback.OnClickListener r12 = new com.tidalab.v2board.clash.design.generated.callback.OnClickListener
            r12.<init>(r11, r5)
            r11.mCallback32 = r12
            com.tidalab.v2board.clash.design.generated.callback.OnClickListener r12 = new com.tidalab.v2board.clash.design.generated.callback.OnClickListener
            r12.<init>(r11, r4)
            r11.mCallback31 = r12
            r11.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DesignPropertiesBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
    }

    @Override // com.tidalab.v2board.clash.design.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        boolean z = false;
        if (i == 1) {
            PropertiesDesign propertiesDesign = this.mSelf;
            if (propertiesDesign != null) {
                z = true;
            }
            if (z) {
                Objects.requireNonNull(propertiesDesign);
                InputKt.launch$default(propertiesDesign, null, null, new PropertiesDesign$inputName$1(propertiesDesign, null), 3, null);
            }
        } else if (i == 2) {
            PropertiesDesign propertiesDesign2 = this.mSelf;
            if (propertiesDesign2 != null) {
                z = true;
            }
            if (z && propertiesDesign2.binding.mProfile.type != Profile.Type.External) {
                InputKt.launch$default(propertiesDesign2, null, null, new PropertiesDesign$inputUrl$1(propertiesDesign2, null), 3, null);
            }
        } else if (i == 3) {
            PropertiesDesign propertiesDesign3 = this.mSelf;
            if (propertiesDesign3 != null) {
                z = true;
            }
            if (z) {
                Objects.requireNonNull(propertiesDesign3);
                InputKt.launch$default(propertiesDesign3, null, null, new PropertiesDesign$inputInterval$1(propertiesDesign3, null), 3, null);
            }
        } else if (i == 4) {
            PropertiesDesign propertiesDesign4 = this.mSelf;
            if (propertiesDesign4 != null) {
                z = true;
            }
            if (z) {
                propertiesDesign4.requests.mo14trySendJP2dKIU(PropertiesDesign.Request.BrowseFiles.INSTANCE);
            }
        } else if (i == 5) {
            PropertiesDesign propertiesDesign5 = this.mSelf;
            if (propertiesDesign5 != null) {
                z = true;
            }
            if (z) {
                propertiesDesign5.requests.mo14trySendJP2dKIU(PropertiesDesign.Request.Commit.INSTANCE);
            }
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        boolean z;
        long j2;
        boolean z2;
        String str;
        String str2;
        int i;
        int i2;
        int i3;
        int i4;
        float f;
        int i5;
        int i6;
        int i7;
        long j3;
        long j4;
        Profile.Type type;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        Profile profile = this.mProfile;
        PropertiesDesign propertiesDesign = this.mSelf;
        boolean z3 = this.mProcessing;
        String str3 = null;
        int i8 = ((j & 34) > 0L ? 1 : ((j & 34) == 0L ? 0 : -1));
        if (i8 != 0) {
            if (profile != null) {
                j2 = profile.interval;
                str = profile.source;
                type = profile.type;
                str2 = profile.name;
            } else {
                j2 = 0;
                str2 = null;
                str = null;
                type = null;
            }
            z = j2 == 0;
            z2 = type != Profile.Type.File;
            if (i8 != 0) {
                j = z ? j | 2048 : j | 1024;
            }
        } else {
            j2 = 0;
            str2 = null;
            str = null;
            z2 = false;
            z = false;
        }
        if ((j & 53) != 0) {
            Surface surface = propertiesDesign != null ? propertiesDesign.surface : null;
            updateRegistration(0, surface);
            Insets insets = surface != null ? surface.insets : null;
            if (insets != null) {
                i = insets.start;
                i3 = insets.top;
                i2 = insets.end;
                i4 = insets.bottom;
            } else {
                i4 = 0;
                i3 = 0;
                i2 = 0;
                i = 0;
            }
            f = this.mboundView1.getResources().getDimension(R.dimen.toolbar_height) + i3;
        } else {
            f = 0.0f;
            i4 = 0;
            i3 = 0;
            i2 = 0;
            i = 0;
        }
        int i9 = ((j & 40) > 0L ? 1 : ((j & 40) == 0L ? 0 : -1));
        if (i9 != 0) {
            if (i9 != 0) {
                if (z3) {
                    j4 = j | 128;
                    j3 = 512;
                } else {
                    j4 = j | 64;
                    j3 = 256;
                }
                j = j4 | j3;
            }
            i6 = 8;
            i5 = z3 ? 0 : 8;
            if (!z3) {
                i6 = 0;
            }
        } else {
            i6 = 0;
            i5 = 0;
        }
        if ((j & 1024) != 0) {
            i7 = i6;
            str3 = this.mboundView4.getResources().getString(R.string.format_minutes, Long.valueOf((j2 / 1000) / 60));
        } else {
            i7 = i6;
            str3 = null;
        }
        int i10 = ((34 & j) > 0L ? 1 : ((34 & j) == 0L ? 0 : -1));
        if (i10 != 0) {
            if (z) {
                str3 = this.mboundView4.getResources().getString(R.string.disabled);
            }
        }
        if ((j & 53) != 0) {
            AppOpsManagerCompat.setPaddingTop(this.activityBarLayout, i3);
            AppOpsManagerCompat.setPaddingStart(this.mboundView0, i);
            AppOpsManagerCompat.setPaddingEnd(this.mboundView0, i2);
            AppOpsManagerCompat.setPaddingTop(this.mboundView1, f);
            AppOpsManagerCompat.setPaddingBottom(this.mboundView1, i4);
        }
        if ((32 & j) != 0) {
            this.mboundView2.setOnClickListener(this.mCallback28);
            this.mboundView3.setOnClickListener(this.mCallback29);
            this.mboundView4.setOnClickListener(this.mCallback30);
            this.mboundView5.setOnClickListener(this.mCallback31);
            this.mboundView9.setOnClickListener(this.mCallback32);
        }
        if (i10 != 0) {
            this.mboundView2.setText(str2);
            this.mboundView3.setEnabled(z2);
            this.mboundView3.setText(str);
            this.mboundView4.setEnabled(z2);
            this.mboundView4.setText(str3);
        }
        if ((j & 40) != 0) {
            this.mboundView8.setVisibility(i5);
            this.mboundView9.setVisibility(i7);
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
            this.mDirtyFlags = 32L;
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
                this.mDirtyFlags |= 16;
            }
        }
        return true;
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DesignPropertiesBinding
    public void setProcessing(boolean z) {
        this.mProcessing = z;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(21);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DesignPropertiesBinding
    public void setProfile(Profile profile) {
        this.mProfile = profile;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(22);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DesignPropertiesBinding
    public void setSelf(PropertiesDesign propertiesDesign) {
        this.mSelf = propertiesDesign;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(26);
        requestRebind();
    }
}
