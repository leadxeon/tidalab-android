package com.tidalab.v2board.clash.design.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.AppOpsManagerCompat;
import com.tidalab.v2board.clash.design.LogcatDesign;
import com.tidalab.v2board.clash.design.generated.callback.OnClickListener;
import com.tidalab.v2board.clash.design.ui.Insets;
import com.tidalab.v2board.clash.design.ui.Surface;
import com.tidalab.v2board.clash.foss.R;
import kotlinx.coroutines.channels.SendChannel;
/* loaded from: classes.dex */
public class DesignLogcatBindingImpl extends DesignLogcatBinding implements OnClickListener.Listener {
    public static final SparseIntArray sViewsWithIds;
    public final CoordinatorLayout mboundView0;
    public final RelativeLayout mboundView3;
    public final LinearLayout mboundView4;
    public final ImageView mboundView5;
    public final ImageView mboundView6;
    public final ImageView mboundView7;
    public long mDirtyFlags = -1;
    public final View.OnClickListener mCallback3 = new OnClickListener(this, 3);
    public final View.OnClickListener mCallback1 = new OnClickListener(this, 1);
    public final View.OnClickListener mCallback2 = new OnClickListener(this, 2);

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.action_layout, 8);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DesignLogcatBindingImpl(androidx.databinding.DataBindingComponent r12, android.view.View r13) {
        /*
            r11 = this;
            android.util.SparseIntArray r0 = com.tidalab.v2board.clash.design.databinding.DesignLogcatBindingImpl.sViewsWithIds
            r1 = 9
            r2 = 0
            java.lang.Object[] r0 = androidx.databinding.ViewDataBinding.mapBindings(r12, r13, r1, r2, r0)
            r1 = 8
            r1 = r0[r1]
            r7 = r1
            android.widget.FrameLayout r7 = (android.widget.FrameLayout) r7
            r1 = 2
            r3 = r0[r1]
            r8 = r3
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r8 = (com.tidalab.v2board.clash.design.view.ActivityBarLayout) r8
            r10 = 1
            r3 = r0[r10]
            r9 = r3
            com.tidalab.v2board.clash.design.view.AppRecyclerView r9 = (com.tidalab.v2board.clash.design.view.AppRecyclerView) r9
            r6 = 1
            r3 = r11
            r4 = r12
            r5 = r13
            r3.<init>(r4, r5, r6, r7, r8, r9)
            r3 = -1
            r11.mDirtyFlags = r3
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r12 = r11.activityBarLayout
            r12.setTag(r2)
            r12 = 0
            r12 = r0[r12]
            androidx.coordinatorlayout.widget.CoordinatorLayout r12 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r12
            r11.mboundView0 = r12
            r12.setTag(r2)
            r12 = 3
            r3 = r0[r12]
            android.widget.RelativeLayout r3 = (android.widget.RelativeLayout) r3
            r11.mboundView3 = r3
            r3.setTag(r2)
            r3 = 4
            r3 = r0[r3]
            android.widget.LinearLayout r3 = (android.widget.LinearLayout) r3
            r11.mboundView4 = r3
            r3.setTag(r2)
            r3 = 5
            r3 = r0[r3]
            android.widget.ImageView r3 = (android.widget.ImageView) r3
            r11.mboundView5 = r3
            r3.setTag(r2)
            r3 = 6
            r3 = r0[r3]
            android.widget.ImageView r3 = (android.widget.ImageView) r3
            r11.mboundView6 = r3
            r3.setTag(r2)
            r3 = 7
            r0 = r0[r3]
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            r11.mboundView7 = r0
            r0.setTag(r2)
            com.tidalab.v2board.clash.design.view.AppRecyclerView r0 = r11.recyclerList
            r0.setTag(r2)
            r0 = 2131296397(0x7f09008d, float:1.821071E38)
            r13.setTag(r0, r11)
            com.tidalab.v2board.clash.design.generated.callback.OnClickListener r13 = new com.tidalab.v2board.clash.design.generated.callback.OnClickListener
            r13.<init>(r11, r12)
            r11.mCallback3 = r13
            com.tidalab.v2board.clash.design.generated.callback.OnClickListener r12 = new com.tidalab.v2board.clash.design.generated.callback.OnClickListener
            r12.<init>(r11, r10)
            r11.mCallback1 = r12
            com.tidalab.v2board.clash.design.generated.callback.OnClickListener r12 = new com.tidalab.v2board.clash.design.generated.callback.OnClickListener
            r12.<init>(r11, r1)
            r11.mCallback2 = r12
            r11.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DesignLogcatBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
    }

    @Override // com.tidalab.v2board.clash.design.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        boolean z = false;
        if (i == 1) {
            LogcatDesign logcatDesign = this.mSelf;
            if (logcatDesign != null) {
                SendChannel sendChannel = logcatDesign.requests;
                if (sendChannel != null) {
                    z = true;
                }
                if (z) {
                    sendChannel.offer(LogcatDesign.Request.Delete);
                }
            }
        } else if (i == 2) {
            LogcatDesign logcatDesign2 = this.mSelf;
            if (logcatDesign2 != null) {
                SendChannel sendChannel2 = logcatDesign2.requests;
                if (sendChannel2 != null) {
                    z = true;
                }
                if (z) {
                    sendChannel2.offer(LogcatDesign.Request.Export);
                }
            }
        } else if (i == 3) {
            LogcatDesign logcatDesign3 = this.mSelf;
            if (logcatDesign3 != null) {
                SendChannel sendChannel3 = logcatDesign3.requests;
                if (sendChannel3 != null) {
                    z = true;
                }
                if (z) {
                    sendChannel3.offer(LogcatDesign.Request.Close);
                }
            }
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        long j2;
        long j3;
        int i6;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        LogcatDesign logcatDesign = this.mSelf;
        float f = 0.0f;
        boolean z = this.mStreaming;
        int i7 = 0;
        if ((j & 27) != 0) {
            Insets insets = null;
            Surface surface = logcatDesign != null ? logcatDesign.surface : null;
            updateRegistration(0, surface);
            if (surface != null) {
                insets = surface.insets;
            }
            if (insets != null) {
                i4 = insets.end;
                i2 = insets.bottom;
                i = insets.start;
                i6 = insets.top;
            } else {
                i4 = 0;
                i6 = 0;
                i2 = 0;
                i = 0;
            }
            f = i6 + this.recyclerList.getResources().getDimension(R.dimen.toolbar_height);
            i3 = i6;
        } else {
            i4 = 0;
            i3 = 0;
            i2 = 0;
            i = 0;
        }
        int i8 = ((j & 20) > 0L ? 1 : ((j & 20) == 0L ? 0 : -1));
        if (i8 != 0) {
            if (i8 != 0) {
                if (z) {
                    j3 = j | 64;
                    j2 = 256;
                } else {
                    j3 = j | 32;
                    j2 = 128;
                }
                j = j3 | j2;
            }
            i5 = z ? 0 : 8;
            if (z) {
                i7 = 8;
            }
        } else {
            i5 = 0;
        }
        if ((27 & j) != 0) {
            AppOpsManagerCompat.setPaddingTop(this.activityBarLayout, i3);
            AppOpsManagerCompat.setPaddingStart(this.mboundView0, i);
            AppOpsManagerCompat.setPaddingEnd(this.mboundView0, i4);
            AppOpsManagerCompat.setPaddingTop(this.recyclerList, f);
            AppOpsManagerCompat.setPaddingBottom(this.recyclerList, i2);
        }
        if ((j & 20) != 0) {
            this.mboundView4.setVisibility(i7);
            this.mboundView7.setVisibility(i5);
        }
        if ((j & 16) != 0) {
            this.mboundView5.setOnClickListener(this.mCallback1);
            this.mboundView6.setOnClickListener(this.mCallback2);
            this.mboundView7.setOnClickListener(this.mCallback3);
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
            this.mDirtyFlags = 16L;
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
                this.mDirtyFlags |= 8;
            }
        }
        return true;
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DesignLogcatBinding
    public void setSelf(LogcatDesign logcatDesign) {
        this.mSelf = logcatDesign;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(26);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DesignLogcatBinding
    public void setStreaming(boolean z) {
        this.mStreaming = z;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(28);
        requestRebind();
    }
}
