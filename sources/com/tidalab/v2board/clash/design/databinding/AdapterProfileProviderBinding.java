package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.model.ProfileProvider;
/* loaded from: classes.dex */
public abstract class AdapterProfileProviderBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public ProfileProvider mProvider;

    public AdapterProfileProviderBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public abstract void setProvider(ProfileProvider profileProvider);
}
