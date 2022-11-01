package com.tidalab.v2board.clash.design.dialog;

import com.tidalab.v2board.clash.design.ui.Insets;
import com.tidalab.v2board.clash.design.ui.Surface;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: Dialogs.kt */
/* loaded from: classes.dex */
public final class FullScreenDialog$onCreate$1$1 extends Lambda implements Function1<Insets, Unit> {
    public final /* synthetic */ FullScreenDialog this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FullScreenDialog$onCreate$1$1(FullScreenDialog fullScreenDialog) {
        super(1);
        this.this$0 = fullScreenDialog;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Insets insets) {
        Insets insets2 = insets;
        if (!Intrinsics.areEqual(this.this$0.surface.insets, insets2)) {
            Surface surface = this.this$0.surface;
            surface.insets = insets2;
            surface.notifyPropertyChanged(14);
        }
        return Unit.INSTANCE;
    }
}
