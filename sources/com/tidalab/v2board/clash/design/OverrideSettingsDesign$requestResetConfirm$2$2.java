package com.tidalab.v2board.clash.design;

import androidx.appcompat.app.AlertDialog;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: OverrideSettingsDesign.kt */
/* loaded from: classes.dex */
public final class OverrideSettingsDesign$requestResetConfirm$2$2 extends Lambda implements Function1<Throwable, Unit> {
    public final /* synthetic */ AlertDialog $dialog;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OverrideSettingsDesign$requestResetConfirm$2$2(AlertDialog alertDialog) {
        super(1);
        this.$dialog = alertDialog;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Throwable th) {
        this.$dialog.dismiss();
        return Unit.INSTANCE;
    }
}
