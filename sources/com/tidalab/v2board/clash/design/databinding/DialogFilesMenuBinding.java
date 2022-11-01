package com.tidalab.v2board.clash.design.databinding;

import android.app.Dialog;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.FilesDesign;
import com.tidalab.v2board.clash.design.model.File;
/* loaded from: classes.dex */
public abstract class DialogFilesMenuBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public boolean mConfigurationEditable;
    public boolean mCurrentInBase;
    public File mFile;
    public FilesDesign mMaster;
    public Dialog mSelf;

    public DialogFilesMenuBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public abstract void setConfigurationEditable(boolean z);

    public abstract void setCurrentInBase(boolean z);

    public abstract void setFile(File file);

    public abstract void setMaster(FilesDesign filesDesign);

    public abstract void setSelf(Dialog dialog);
}
