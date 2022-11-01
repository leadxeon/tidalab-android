package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.databinding.DesignSettingsBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
/* compiled from: SettingsDesign.kt */
/* loaded from: classes.dex */
public final class SettingsDesign extends Design<Request> {
    public final DesignSettingsBinding binding;

    /* compiled from: SettingsDesign.kt */
    /* loaded from: classes.dex */
    public enum Request {
        StartApp,
        StartNetwork,
        StartOverride
    }

    public SettingsDesign(Context context) {
        super(context);
        LayoutInflater from = LayoutInflater.from(context);
        ViewGroup root = InputKt.getRoot(context);
        int i = DesignSettingsBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        DesignSettingsBinding designSettingsBinding = (DesignSettingsBinding) ViewDataBinding.inflateInternal(from, R.layout.design_settings, root, false, null);
        this.binding = designSettingsBinding;
        designSettingsBinding.setSelf(this);
        InputKt.applyFrom(designSettingsBinding.activityBarLayout, context);
        InputKt.bindAppBarElevation(designSettingsBinding.scrollRoot, designSettingsBinding.activityBarLayout);
    }

    @Override // com.tidalab.v2board.clash.design.Design
    public View getRoot() {
        return this.binding.mRoot;
    }
}
