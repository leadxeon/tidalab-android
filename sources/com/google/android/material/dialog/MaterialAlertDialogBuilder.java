package com.google.android.material.dialog;

import android.content.DialogInterface;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AlertController;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.ViewCompat;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class MaterialAlertDialogBuilder extends AlertDialog.Builder {
    public Drawable background;
    public final Rect backgroundInsets;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MaterialAlertDialogBuilder(android.content.Context r14) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.dialog.MaterialAlertDialogBuilder.<init>(android.content.Context):void");
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public AlertDialog create() {
        AlertDialog create = super.create();
        Window window = create.getWindow();
        View decorView = window.getDecorView();
        Drawable drawable = this.background;
        if (drawable instanceof MaterialShapeDrawable) {
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            ((MaterialShapeDrawable) drawable).setElevation(decorView.getElevation());
        }
        Drawable drawable2 = this.background;
        Rect rect = this.backgroundInsets;
        window.setBackgroundDrawable(new InsetDrawable(drawable2, rect.left, rect.top, rect.right, rect.bottom));
        decorView.setOnTouchListener(new InsetDialogOnTouchListener(create, this.backgroundInsets));
        return create;
    }

    public MaterialAlertDialogBuilder setMessage(int i) {
        AlertController.AlertParams alertParams = this.P;
        alertParams.mMessage = alertParams.mContext.getText(i);
        return this;
    }

    public MaterialAlertDialogBuilder setNegativeButton(int i, DialogInterface.OnClickListener onClickListener) {
        AlertController.AlertParams alertParams = this.P;
        alertParams.mNegativeButtonText = alertParams.mContext.getText(i);
        this.P.mNegativeButtonListener = onClickListener;
        return this;
    }

    public MaterialAlertDialogBuilder setPositiveButton(int i, DialogInterface.OnClickListener onClickListener) {
        AlertController.AlertParams alertParams = this.P;
        alertParams.mPositiveButtonText = alertParams.mContext.getText(i);
        this.P.mPositiveButtonListener = onClickListener;
        return this;
    }

    public MaterialAlertDialogBuilder setTitle(int i) {
        AlertController.AlertParams alertParams = this.P;
        alertParams.mTitle = alertParams.mContext.getText(i);
        return this;
    }

    public MaterialAlertDialogBuilder setView(View view) {
        this.P.mView = view;
        return this;
    }
}
