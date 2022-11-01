package com.tidalab.v2board.clash.design.preference;

import com.tidalab.v2board.clash.design.dialog.FullScreenDialog;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: Overlay.kt */
/* loaded from: classes.dex */
public final class OverlayKt$requestEditableListOverlay$2$1$6 extends Lambda implements Function1<Throwable, Unit> {
    public final /* synthetic */ FullScreenDialog $dialog;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OverlayKt$requestEditableListOverlay$2$1$6(FullScreenDialog fullScreenDialog) {
        super(1);
        this.$dialog = fullScreenDialog;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Throwable th) {
        this.$dialog.dismiss();
        return Unit.INSTANCE;
    }
}
