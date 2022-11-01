package com.tidalab.v2board.clash.design;

import androidx.appcompat.app.AlertDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: MainDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.MainDesign$showUpdatedTips$2", f = "MainDesign.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class MainDesign$showUpdatedTips$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AlertDialog>, Object> {
    public final /* synthetic */ MainDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainDesign$showUpdatedTips$2(MainDesign mainDesign, Continuation<? super MainDesign$showUpdatedTips$2> continuation) {
        super(2, continuation);
        this.this$0 = mainDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainDesign$showUpdatedTips$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super AlertDialog> continuation) {
        Continuation<? super AlertDialog> continuation2 = continuation;
        MainDesign mainDesign = this.this$0;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(mainDesign.context);
        materialAlertDialogBuilder.setTitle(R.string.version_updated);
        materialAlertDialogBuilder.setMessage(R.string.version_updated_tips);
        materialAlertDialogBuilder.setPositiveButton(R.string.ok, $$Lambda$MainDesign$showUpdatedTips$2$hNUvQ_uE1WfN4nucVNoCuDWmUM.INSTANCE);
        return materialAlertDialogBuilder.show();
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this.this$0.context);
        materialAlertDialogBuilder.setTitle(R.string.version_updated);
        materialAlertDialogBuilder.setMessage(R.string.version_updated_tips);
        materialAlertDialogBuilder.setPositiveButton(R.string.ok, $$Lambda$MainDesign$showUpdatedTips$2$hNUvQ_uE1WfN4nucVNoCuDWmUM.INSTANCE);
        return materialAlertDialogBuilder.show();
    }
}
