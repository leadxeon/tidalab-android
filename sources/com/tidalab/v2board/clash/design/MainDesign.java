package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.databinding.DesignMainBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
/* compiled from: MainDesign.kt */
/* loaded from: classes.dex */
public final class MainDesign extends Design<Request> {
    public final DesignMainBinding binding;

    /* compiled from: MainDesign.kt */
    /* loaded from: classes.dex */
    public enum Request {
        ToggleStatus,
        OpenProxy,
        OpenProfiles,
        OpenProviders,
        OpenLogs,
        OpenSettings,
        OpenHelp,
        OpenAbout
    }

    public MainDesign(Context context) {
        super(context);
        LayoutInflater from = LayoutInflater.from(context);
        ViewGroup root = InputKt.getRoot(context);
        int i = DesignMainBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        DesignMainBinding designMainBinding = (DesignMainBinding) ViewDataBinding.inflateInternal(from, R.layout.design_main, root, false, null);
        this.binding = designMainBinding;
        designMainBinding.setSelf(this);
        designMainBinding.setColorClashStarted(InputKt.resolveThemedColor(context, R.attr.colorPrimary));
        designMainBinding.setColorClashStopped(InputKt.resolveThemedColor(context, R.attr.colorClashStopped));
    }

    @Override // com.tidalab.v2board.clash.design.Design
    public View getRoot() {
        return this.binding.mRoot;
    }
}
