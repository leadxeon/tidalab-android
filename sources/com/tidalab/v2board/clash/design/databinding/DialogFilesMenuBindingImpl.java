package com.tidalab.v2board.clash.design.databinding;

import android.app.Dialog;
import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.FilesDesign;
import com.tidalab.v2board.clash.design.generated.callback.OnClickListener;
import com.tidalab.v2board.clash.design.model.File;
import com.tidalab.v2board.clash.design.view.LargeActionLabel;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class DialogFilesMenuBindingImpl extends DialogFilesMenuBinding implements OnClickListener.Listener {
    public final LinearLayout mboundView0;
    public final LargeActionLabel mboundView1;
    public final LargeActionLabel mboundView2;
    public final LargeActionLabel mboundView3;
    public final LargeActionLabel mboundView4;
    public long mDirtyFlags = -1;
    public final View.OnClickListener mCallback6 = new OnClickListener(this, 3);
    public final View.OnClickListener mCallback4 = new OnClickListener(this, 1);
    public final View.OnClickListener mCallback7 = new OnClickListener(this, 4);
    public final View.OnClickListener mCallback5 = new OnClickListener(this, 2);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DialogFilesMenuBindingImpl(DataBindingComponent dataBindingComponent, View view) {
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
            FilesDesign filesDesign = this.mMaster;
            File file = this.mFile;
            Dialog dialog = this.mSelf;
            if (filesDesign != null) {
                z = true;
            }
            if (z) {
                filesDesign.requests.mo14trySendJP2dKIU(new FilesDesign.Request.ImportFile(file));
                dialog.dismiss();
            }
        } else if (i == 2) {
            FilesDesign filesDesign2 = this.mMaster;
            File file2 = this.mFile;
            Dialog dialog2 = this.mSelf;
            if (filesDesign2 != null) {
                z = true;
            }
            if (z) {
                filesDesign2.requests.mo14trySendJP2dKIU(new FilesDesign.Request.ExportFile(file2));
                dialog2.dismiss();
            }
        } else if (i == 3) {
            FilesDesign filesDesign3 = this.mMaster;
            File file3 = this.mFile;
            Dialog dialog3 = this.mSelf;
            if (filesDesign3 != null) {
                z = true;
            }
            if (z) {
                filesDesign3.requests.mo14trySendJP2dKIU(new FilesDesign.Request.RenameFile(file3));
                dialog3.dismiss();
            }
        } else if (i == 4) {
            FilesDesign filesDesign4 = this.mMaster;
            File file4 = this.mFile;
            Dialog dialog4 = this.mSelf;
            if (filesDesign4 != null) {
                z = true;
            }
            if (z) {
                filesDesign4.requests.mo14trySendJP2dKIU(new FilesDesign.Request.DeleteFile(file4));
                dialog4.dismiss();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:108:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0124  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void executeBindings() {
        /*
            Method dump skipped, instructions count: 316
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DialogFilesMenuBindingImpl.executeBindings():void");
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
        return false;
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DialogFilesMenuBinding
    public void setConfigurationEditable(boolean z) {
        this.mConfigurationEditable = z;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(7);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DialogFilesMenuBinding
    public void setCurrentInBase(boolean z) {
        this.mCurrentInBase = z;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(8);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DialogFilesMenuBinding
    public void setFile(File file) {
        this.mFile = file;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(11);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DialogFilesMenuBinding
    public void setMaster(FilesDesign filesDesign) {
        this.mMaster = filesDesign;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(15);
        requestRebind();
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DialogFilesMenuBinding
    public void setSelf(Dialog dialog) {
        this.mSelf = dialog;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(26);
        requestRebind();
    }
}
