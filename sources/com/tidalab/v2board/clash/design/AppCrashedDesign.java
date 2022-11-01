package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.databinding.DesignAppCrashedBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
/* compiled from: AppCrashedDesign.kt */
/* loaded from: classes.dex */
public final class AppCrashedDesign extends Design<Unit> {
    public final DesignAppCrashedBinding binding;

    public AppCrashedDesign(Context context) {
        super(context);
        LayoutInflater from = LayoutInflater.from(context);
        ViewGroup root = InputKt.getRoot(context);
        int i = DesignAppCrashedBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        DesignAppCrashedBinding designAppCrashedBinding = (DesignAppCrashedBinding) ViewDataBinding.inflateInternal(from, R.layout.design_app_crashed, root, false, null);
        this.binding = designAppCrashedBinding;
        designAppCrashedBinding.setSelf(this);
        InputKt.applyFrom(designAppCrashedBinding.activityBarLayout, context);
        InputKt.bindAppBarElevation(designAppCrashedBinding.scrollRoot, designAppCrashedBinding.activityBarLayout);
    }

    @Override // com.tidalab.v2board.clash.design.Design
    public View getRoot() {
        return this.binding.mRoot;
    }
}
