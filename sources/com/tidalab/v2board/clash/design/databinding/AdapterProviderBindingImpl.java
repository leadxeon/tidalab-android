package com.tidalab.v2board.clash.design.databinding;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.AppOpsManagerCompat;
import com.tidalab.v2board.clash.core.model.Provider;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.ProviderState;
import com.tidalab.v2board.clash.design.ui.ObservableCurrentTime;
import com.tidalab.v2board.clash.foss.R;
import kotlin.NoWhenBranchMatchedException;
/* loaded from: classes.dex */
public class AdapterProviderBindingImpl extends AdapterProviderBinding {
    public long mDirtyFlags = -1;
    public final RelativeLayout mboundView0;
    public final TextView mboundView1;
    public final TextView mboundView2;
    public final View mboundView4;
    public final ProgressBar mboundView7;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AdapterProviderBindingImpl(androidx.databinding.DataBindingComponent r11, android.view.View r12) {
        /*
            r10 = this;
            r0 = 8
            r1 = 0
            java.lang.Object[] r0 = androidx.databinding.ViewDataBinding.mapBindings(r11, r12, r0, r1, r1)
            r2 = 3
            r2 = r0[r2]
            r7 = r2
            android.widget.TextView r7 = (android.widget.TextView) r7
            r2 = 5
            r2 = r0[r2]
            r8 = r2
            android.widget.FrameLayout r8 = (android.widget.FrameLayout) r8
            r2 = 6
            r2 = r0[r2]
            r9 = r2
            android.widget.ImageView r9 = (android.widget.ImageView) r9
            r6 = 2
            r3 = r10
            r4 = r11
            r5 = r12
            r3.<init>(r4, r5, r6, r7, r8, r9)
            r2 = -1
            r10.mDirtyFlags = r2
            android.widget.TextView r11 = r10.elapsedView
            r11.setTag(r1)
            android.widget.FrameLayout r11 = r10.endView
            r11.setTag(r1)
            r11 = 0
            r11 = r0[r11]
            android.widget.RelativeLayout r11 = (android.widget.RelativeLayout) r11
            r10.mboundView0 = r11
            r11.setTag(r1)
            r11 = 1
            r11 = r0[r11]
            android.widget.TextView r11 = (android.widget.TextView) r11
            r10.mboundView1 = r11
            r11.setTag(r1)
            r11 = 2
            r11 = r0[r11]
            android.widget.TextView r11 = (android.widget.TextView) r11
            r10.mboundView2 = r11
            r11.setTag(r1)
            r11 = 4
            r11 = r0[r11]
            android.view.View r11 = (android.view.View) r11
            r10.mboundView4 = r11
            r11.setTag(r1)
            r11 = 7
            r11 = r0[r11]
            android.widget.ProgressBar r11 = (android.widget.ProgressBar) r11
            r10.mboundView7 = r11
            r11.setTag(r1)
            android.widget.ImageView r11 = r10.updateView
            r11.setTag(r1)
            r11 = 2131296397(0x7f09008d, float:1.821071E38)
            r12.setTag(r11, r10)
            r10.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.AdapterProviderBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        String str;
        String str2;
        int i;
        String str3;
        long j2;
        long j3;
        String str4;
        String str5;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ObservableCurrentTime observableCurrentTime = this.mCurrentTime;
        View.OnClickListener onClickListener = this.mUpdate;
        Provider provider = this.mProvider;
        ProviderState providerState = this.mState;
        int i2 = ((j & 179) > 0L ? 1 : ((j & 179) == 0L ? 0 : -1));
        long j4 = (i2 == 0 || observableCurrentTime == null) ? 0L : observableCurrentTime.value;
        boolean z = false;
        r18 = 0;
        int i3 = 0;
        int i4 = ((j & 198) > 0L ? 1 : ((j & 198) == 0L ? 0 : -1));
        if ((j & 136) != 0) {
            Context context = this.mRoot.getContext();
            int ordinal = provider.type.ordinal();
            if (ordinal == 0) {
                str4 = context.getString(R.string.proxy);
            } else if (ordinal == 1) {
                str4 = context.getString(R.string.rule);
            } else {
                throw new NoWhenBranchMatchedException();
            }
            int ordinal2 = provider.vehicleType.ordinal();
            if (ordinal2 == 0) {
                str5 = context.getString(R.string.http);
            } else if (ordinal2 == 1) {
                str5 = context.getString(R.string.file);
            } else if (ordinal2 == 2) {
                str5 = context.getString(R.string.compatible);
            } else {
                throw new NoWhenBranchMatchedException();
            }
            str2 = context.getString(R.string.format_provider_type, str4, str5);
            str = provider.name;
        } else {
            str2 = null;
            str = null;
        }
        if ((247 & j) != 0) {
            if (i2 != 0) {
                str3 = InputKt.elapsedIntervalString(j4 - (providerState != null ? providerState.updatedAt : 0L), this.mRoot.getContext());
            } else {
                str3 = null;
            }
            if (i4 != 0) {
                boolean z2 = providerState != null ? providerState.updating : false;
                if ((j & 194) != 0) {
                    if (z2) {
                        j3 = j | 512;
                        j2 = 2048;
                    } else {
                        j3 = j | 256;
                        j2 = 1024;
                    }
                    j = j3 | j2;
                }
                if ((j & 194) != 0) {
                    i = z2 ? 0 : 8;
                    if (z2) {
                        i3 = 8;
                    }
                } else {
                    i = 0;
                }
                z = true ^ z2;
            } else {
                i3 = 0;
                i = 0;
            }
        } else {
            str3 = null;
            z = false;
            i = 0;
            i3 = 0;
        }
        if ((179 & j) != 0) {
            AppOpsManagerCompat.setText(this.elapsedView, str3);
        }
        if ((j & 194) != 0) {
            this.endView.setFocusable(z);
            this.mboundView7.setVisibility(i);
            this.updateView.setVisibility(i3);
        }
        if ((198 & j) != 0) {
            FrameLayout frameLayout = this.endView;
            frameLayout.setOnClickListener(onClickListener);
            frameLayout.setClickable(z);
        }
        if ((j & 136) != 0) {
            AppOpsManagerCompat.setText(this.mboundView1, str);
            AppOpsManagerCompat.setText(this.mboundView2, str2);
        }
        if ((j & 128) != 0) {
            View view = this.mboundView4;
            view.setMinimumHeight((int) (view.getResources().getDimension(R.dimen.item_tailing_component_size) * 1.5f));
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
            this.mDirtyFlags = 128L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
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
        } else if (i != 1) {
            return false;
        } else {
            ProviderState providerState = (ProviderState) obj;
            if (i2 == 0) {
                synchronized (this) {
                    this.mDirtyFlags |= 2;
                }
            } else if (i2 == 31) {
                synchronized (this) {
                    this.mDirtyFlags |= 32;
                }
            } else if (i2 != 32) {
                return false;
            } else {
                synchronized (this) {
                    this.mDirtyFlags |= 64;
                }
            }
            return true;
        }
    }

    @Override // com.tidalab.v2board.clash.design.databinding.AdapterProviderBinding
    public void setCurrentTime(ObservableCurrentTime observableCurrentTime) {
        updateRegistration(0, observableCurrentTime);
        this.mCurrentTime = observableCurrentTime;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(10);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.AdapterProviderBinding
    public void setProvider(Provider provider) {
        this.mProvider = provider;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(24);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.AdapterProviderBinding
    public void setState(ProviderState providerState) {
        updateRegistration(1, providerState);
        this.mState = providerState;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(27);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.AdapterProviderBinding
    public void setUpdate(View.OnClickListener onClickListener) {
        this.mUpdate = onClickListener;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(30);
        requestRebind();
    }
}
