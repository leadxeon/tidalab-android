package com.tidalab.v2board.clash.design.util;

import android.view.View;
import androidx.appcompat.app.AlertController;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: Toast.kt */
/* loaded from: classes.dex */
public final class ToastKt$showExceptionToast$2 extends Lambda implements Function1<Snackbar, Unit> {
    public final /* synthetic */ CharSequence $message;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ToastKt$showExceptionToast$2(CharSequence charSequence) {
        super(1);
        this.$message = charSequence;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Snackbar snackbar) {
        final CharSequence charSequence = this.$message;
        snackbar.setAction(R.string.detail, new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.util.-$$Lambda$ToastKt$showExceptionToast$2$vh5K9B3DWS7n4X3wH-VNMeC2lBs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CharSequence charSequence2 = charSequence;
                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(view.getContext());
                materialAlertDialogBuilder.setTitle(R.string.error);
                AlertController.AlertParams alertParams = materialAlertDialogBuilder.P;
                alertParams.mMessage = charSequence2;
                alertParams.mCancelable = true;
                materialAlertDialogBuilder.setPositiveButton(R.string.ok, $$Lambda$ToastKt$showExceptionToast$2$ZlA4MfS4zUifPxgoT3RcuYEZ0OY.INSTANCE);
                materialAlertDialogBuilder.show();
            }
        });
        return Unit.INSTANCE;
    }
}
