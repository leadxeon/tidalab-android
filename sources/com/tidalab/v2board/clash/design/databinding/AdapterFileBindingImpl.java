package com.tidalab.v2board.clash.design.databinding;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.AppOpsManagerCompat;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.File;
import com.tidalab.v2board.clash.design.ui.ObservableCurrentTime;
import com.tidalab.v2board.clash.foss.R;
import java.util.Arrays;
/* loaded from: classes.dex */
public class AdapterFileBindingImpl extends AdapterFileBinding {
    public long mDirtyFlags = -1;
    public final TextView mboundView2;
    public final TextView mboundView3;
    public final View mboundView5;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AdapterFileBindingImpl(androidx.databinding.DataBindingComponent r12, android.view.View r13) {
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
            android.view.View r8 = (android.view.View) r8
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
            android.view.View r12 = r11.iconView
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
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.AdapterFileBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        boolean z;
        String str;
        int i;
        Drawable drawable;
        String str2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        View.OnClickListener onClickListener = this.mMore;
        ObservableCurrentTime observableCurrentTime = this.mCurrentTime;
        View.OnClickListener onClickListener2 = this.mOpen;
        File file = this.mFile;
        String str3 = null;
        if ((j & 57) != 0) {
            int i2 = ((j & 40) > 0L ? 1 : ((j & 40) == 0L ? 0 : -1));
            str = (i2 == 0 || file == null) ? null : file.name;
            z = file != null ? file.isDirectory : false;
            if (i2 != 0) {
                j = z ? j | 128 | 512 | 2048 : j | 64 | 256 | 1024;
            }
            if ((j & 57) != 0) {
                j = z ? j | 8192 : j | 4096;
            }
            if ((j & 40) != 0) {
                i = z ? 8 : 0;
                drawable = AppCompatResources.getDrawable(this.iconView.getContext(), z ? R.drawable.ic_outline_folder : R.drawable.ic_outline_article);
            } else {
                drawable = null;
                i = 0;
            }
        } else {
            drawable = null;
            str = null;
            i = 0;
            z = false;
        }
        if ((j & 4096) != 0) {
            str3 = InputKt.elapsedIntervalString((observableCurrentTime != null ? observableCurrentTime.value : 0L) - (file != null ? file.lastModified : 0L), this.mRoot.getContext());
        } else {
            str3 = null;
        }
        if ((j & 64) != 0) {
            long j2 = file != null ? file.size : 0L;
            if (j2 > 1073741824) {
                double d = 1024;
                str2 = String.format("%.2f GiB", Arrays.copyOf(new Object[]{Double.valueOf(((j2 / d) / d) / d)}, 1));
            } else if (j2 > 1048576) {
                double d2 = 1024;
                str2 = String.format("%.2f MiB", Arrays.copyOf(new Object[]{Double.valueOf((j2 / d2) / d2)}, 1));
            } else if (j2 > 1024) {
                str2 = String.format("%.2f KiB", Arrays.copyOf(new Object[]{Double.valueOf(j2 / 1024)}, 1));
            } else {
                str2 = j2 + " Bytes";
            }
        } else {
            str2 = null;
        }
        int i3 = ((j & 40) > 0L ? 1 : ((j & 40) == 0L ? 0 : -1));
        if (i3 == 0 || z) {
            str2 = null;
        }
        int i4 = ((57 & j) > 0L ? 1 : ((57 & j) == 0L ? 0 : -1));
        if (i4 != 0 && !z) {
        }
        if (i4 != 0) {
            AppOpsManagerCompat.setText(this.elapsedView, str3);
        }
        if (i3 != 0) {
            this.elapsedView.setVisibility(i);
            this.iconView.setBackground(drawable);
            AppOpsManagerCompat.setText(this.mboundView2, str);
            AppOpsManagerCompat.setText(this.mboundView3, str2);
            this.mboundView3.setVisibility(i);
        }
        if ((32 & j) != 0) {
            View view = this.mboundView5;
            view.setMinimumHeight((int) (view.getResources().getDimension(R.dimen.item_tailing_component_size) * 1.5f));
        }
        if ((34 & j) != 0) {
            this.menuView.setOnClickListener(onClickListener);
        }
        if ((j & 36) != 0) {
            this.rootView.setOnClickListener(onClickListener2);
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

    @Override // com.tidalab.v2board.clash.design.databinding.AdapterFileBinding
    public void setCurrentTime(ObservableCurrentTime observableCurrentTime) {
        updateRegistration(0, observableCurrentTime);
        this.mCurrentTime = observableCurrentTime;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(10);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.AdapterFileBinding
    public void setFile(File file) {
        this.mFile = file;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(11);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.AdapterFileBinding
    public void setMore(View.OnClickListener onClickListener) {
        this.mMore = onClickListener;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(19);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.AdapterFileBinding
    public void setOpen(View.OnClickListener onClickListener) {
        this.mOpen = onClickListener;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(20);
        requestRebind();
    }
}
