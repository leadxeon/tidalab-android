package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.tidalab.v2board.clash.design.model.AppInfo;
/* loaded from: classes.dex */
public abstract class AdapterAppBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final View iconView;
    public AppInfo mApp;
    public boolean mSelected;
    public final MaterialCheckBox switchView;

    public AdapterAppBinding(Object obj, View view, int i, View view2, MaterialCheckBox materialCheckBox) {
        super(obj, view, i);
        this.iconView = view2;
        this.switchView = materialCheckBox;
    }

    public abstract void setApp(AppInfo appInfo);

    public abstract void setSelected(boolean z);
}
