package com.tidalab.v2board.clash.design;

import android.view.LayoutInflater;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.databinding.DesignAboutBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: MainDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.MainDesign$showAbout$2", f = "MainDesign.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class MainDesign$showAbout$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AlertDialog>, Object> {
    public final /* synthetic */ String $versionName;
    public final /* synthetic */ MainDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainDesign$showAbout$2(MainDesign mainDesign, String str, Continuation<? super MainDesign$showAbout$2> continuation) {
        super(2, continuation);
        this.this$0 = mainDesign;
        this.$versionName = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainDesign$showAbout$2(this.this$0, this.$versionName, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super AlertDialog> continuation) {
        Continuation<? super AlertDialog> continuation2 = continuation;
        MainDesign mainDesign = this.this$0;
        String str = this.$versionName;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        LayoutInflater from = LayoutInflater.from(mainDesign.context);
        int i = DesignAboutBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        DesignAboutBinding designAboutBinding = (DesignAboutBinding) ViewDataBinding.inflateInternal(from, R.layout.design_about, null, false, null);
        designAboutBinding.setVersionName(str);
        AlertDialog.Builder builder = new AlertDialog.Builder(mainDesign.context);
        builder.P.mView = designAboutBinding.mRoot;
        return builder.show();
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        LayoutInflater from = LayoutInflater.from(this.this$0.context);
        int i = DesignAboutBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        DesignAboutBinding designAboutBinding = (DesignAboutBinding) ViewDataBinding.inflateInternal(from, R.layout.design_about, null, false, null);
        designAboutBinding.setVersionName(this.$versionName);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.this$0.context);
        builder.P.mView = designAboutBinding.mRoot;
        return builder.show();
    }
}
