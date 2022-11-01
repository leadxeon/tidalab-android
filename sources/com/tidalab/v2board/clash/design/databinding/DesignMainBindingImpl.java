package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.LinearLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.reactnativecommunity.webview.RNCWebViewManager;
import com.tidalab.v2board.clash.design.MainDesign;
import com.tidalab.v2board.clash.design.generated.callback.OnClickListener;
import com.tidalab.v2board.clash.design.ui.Surface;
import com.tidalab.v2board.clash.design.view.LargeActionCard;
import com.tidalab.v2board.clash.design.view.LargeActionLabel;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class DesignMainBindingImpl extends DesignMainBinding implements OnClickListener.Listener {
    public final CoordinatorLayout mboundView0;
    public final LinearLayout mboundView1;
    public final LargeActionCard mboundView2;
    public final LargeActionCard mboundView3;
    public final LargeActionCard mboundView4;
    public final LargeActionLabel mboundView5;
    public final LargeActionLabel mboundView6;
    public final LargeActionLabel mboundView7;
    public final LargeActionLabel mboundView8;
    public final LargeActionLabel mboundView9;
    public long mDirtyFlags = -1;
    public final View.OnClickListener mCallback17 = new OnClickListener(this, 5);
    public final View.OnClickListener mCallback15 = new OnClickListener(this, 3);
    public final View.OnClickListener mCallback13 = new OnClickListener(this, 1);
    public final View.OnClickListener mCallback19 = new OnClickListener(this, 7);
    public final View.OnClickListener mCallback20 = new OnClickListener(this, 8);
    public final View.OnClickListener mCallback16 = new OnClickListener(this, 4);
    public final View.OnClickListener mCallback14 = new OnClickListener(this, 2);
    public final View.OnClickListener mCallback18 = new OnClickListener(this, 6);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DesignMainBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        super(dataBindingComponent, view, 1);
        Object[] mapBindings = ViewDataBinding.mapBindings(dataBindingComponent, view, 10, null, null);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) mapBindings[0];
        this.mboundView0 = coordinatorLayout;
        coordinatorLayout.setTag(null);
        LinearLayout linearLayout = (LinearLayout) mapBindings[1];
        this.mboundView1 = linearLayout;
        linearLayout.setTag(null);
        LargeActionCard largeActionCard = (LargeActionCard) mapBindings[2];
        this.mboundView2 = largeActionCard;
        largeActionCard.setTag(null);
        LargeActionCard largeActionCard2 = (LargeActionCard) mapBindings[3];
        this.mboundView3 = largeActionCard2;
        largeActionCard2.setTag(null);
        LargeActionCard largeActionCard3 = (LargeActionCard) mapBindings[4];
        this.mboundView4 = largeActionCard3;
        largeActionCard3.setTag(null);
        LargeActionLabel largeActionLabel = (LargeActionLabel) mapBindings[5];
        this.mboundView5 = largeActionLabel;
        largeActionLabel.setTag(null);
        LargeActionLabel largeActionLabel2 = (LargeActionLabel) mapBindings[6];
        this.mboundView6 = largeActionLabel2;
        largeActionLabel2.setTag(null);
        LargeActionLabel largeActionLabel3 = (LargeActionLabel) mapBindings[7];
        this.mboundView7 = largeActionLabel3;
        largeActionLabel3.setTag(null);
        LargeActionLabel largeActionLabel4 = (LargeActionLabel) mapBindings[8];
        this.mboundView8 = largeActionLabel4;
        largeActionLabel4.setTag(null);
        LargeActionLabel largeActionLabel5 = (LargeActionLabel) mapBindings[9];
        this.mboundView9 = largeActionLabel5;
        largeActionLabel5.setTag(null);
        view.setTag(R.id.dataBinding, this);
        invalidateAll();
    }

    @Override // com.tidalab.v2board.clash.design.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        boolean z = true;
        switch (i) {
            case 1:
                MainDesign mainDesign = this.mSelf;
                if (mainDesign == null) {
                    z = false;
                }
                if (z) {
                    mainDesign.requests.mo14trySendJP2dKIU(MainDesign.Request.ToggleStatus);
                    return;
                }
                return;
            case 2:
                MainDesign mainDesign2 = this.mSelf;
                if (mainDesign2 == null) {
                    z = false;
                }
                if (z) {
                    mainDesign2.requests.mo14trySendJP2dKIU(MainDesign.Request.OpenProxy);
                    return;
                }
                return;
            case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                MainDesign mainDesign3 = this.mSelf;
                if (mainDesign3 == null) {
                    z = false;
                }
                if (z) {
                    mainDesign3.requests.mo14trySendJP2dKIU(MainDesign.Request.OpenProfiles);
                    return;
                }
                return;
            case 4:
                MainDesign mainDesign4 = this.mSelf;
                if (mainDesign4 == null) {
                    z = false;
                }
                if (z) {
                    mainDesign4.requests.mo14trySendJP2dKIU(MainDesign.Request.OpenProviders);
                    return;
                }
                return;
            case 5:
                MainDesign mainDesign5 = this.mSelf;
                if (mainDesign5 == null) {
                    z = false;
                }
                if (z) {
                    mainDesign5.requests.mo14trySendJP2dKIU(MainDesign.Request.OpenLogs);
                    return;
                }
                return;
            case 6:
                MainDesign mainDesign6 = this.mSelf;
                if (mainDesign6 == null) {
                    z = false;
                }
                if (z) {
                    mainDesign6.requests.mo14trySendJP2dKIU(MainDesign.Request.OpenSettings);
                    return;
                }
                return;
            case 7:
                MainDesign mainDesign7 = this.mSelf;
                if (mainDesign7 == null) {
                    z = false;
                }
                if (z) {
                    mainDesign7.requests.mo14trySendJP2dKIU(MainDesign.Request.OpenHelp);
                    return;
                }
                return;
            case 8:
                MainDesign mainDesign8 = this.mSelf;
                if (mainDesign8 == null) {
                    z = false;
                }
                if (z) {
                    mainDesign8.requests.mo14trySendJP2dKIU(MainDesign.Request.OpenAbout);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x01b5 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0255  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:135:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0194  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void executeBindings() {
        /*
            Method dump skipped, instructions count: 644
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DesignMainBindingImpl.executeBindings():void");
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
            this.mDirtyFlags = 1024L;
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
                this.mDirtyFlags |= 512;
            }
        }
        return true;
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DesignMainBinding
    public void setClashRunning(boolean z) {
        this.mClashRunning = z;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(3);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DesignMainBinding
    public void setColorClashStarted(int i) {
        this.mColorClashStarted = i;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(5);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DesignMainBinding
    public void setColorClashStopped(int i) {
        this.mColorClashStopped = i;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(6);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DesignMainBinding
    public void setForwarded(String str) {
        this.mForwarded = str;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(12);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DesignMainBinding
    public void setHasProviders(boolean z) {
        this.mHasProviders = z;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(13);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DesignMainBinding
    public void setMode(String str) {
        this.mMode = str;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(18);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DesignMainBinding
    public void setProfileName(String str) {
        this.mProfileName = str;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(23);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DesignMainBinding
    public void setSelf(MainDesign mainDesign) {
        this.mSelf = mainDesign;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(26);
        requestRebind();
    }
}
