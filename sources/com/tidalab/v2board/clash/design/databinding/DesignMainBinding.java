package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.MainDesign;
/* loaded from: classes.dex */
public abstract class DesignMainBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public boolean mClashRunning;
    public int mColorClashStarted;
    public int mColorClashStopped;
    public String mForwarded;
    public boolean mHasProviders;
    public String mMode;
    public String mProfileName;
    public MainDesign mSelf;

    public DesignMainBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public abstract void setClashRunning(boolean z);

    public abstract void setColorClashStarted(int i);

    public abstract void setColorClashStopped(int i);

    public abstract void setForwarded(String str);

    public abstract void setHasProviders(boolean z);

    public abstract void setMode(String str);

    public abstract void setProfileName(String str);

    public abstract void setSelf(MainDesign mainDesign);
}
