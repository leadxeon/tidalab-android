package com.tidalab.v2board.clash.design.dialog;

import androidx.appcompat.app.AlertDialog;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: Input.kt */
/* loaded from: classes.dex */
public final class InputKt$requestModelTextInput$3$2 extends Lambda implements Function1<Throwable, Unit> {
    public final /* synthetic */ AlertDialog $dialog;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InputKt$requestModelTextInput$3$2(AlertDialog alertDialog) {
        super(1);
        this.$dialog = alertDialog;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Throwable th) {
        this.$dialog.dismiss();
        return Unit.INSTANCE;
    }
}
