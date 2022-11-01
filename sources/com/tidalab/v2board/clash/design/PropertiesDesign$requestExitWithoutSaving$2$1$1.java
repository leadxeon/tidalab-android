package com.tidalab.v2board.clash.design;

import androidx.appcompat.app.AlertDialog;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: PropertiesDesign.kt */
/* loaded from: classes.dex */
public final class PropertiesDesign$requestExitWithoutSaving$2$1$1 extends Lambda implements Function1<Throwable, Unit> {
    public final /* synthetic */ AlertDialog $dialog;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PropertiesDesign$requestExitWithoutSaving$2$1$1(AlertDialog alertDialog) {
        super(1);
        this.$dialog = alertDialog;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Throwable th) {
        this.$dialog.dismiss();
        return Unit.INSTANCE;
    }
}
