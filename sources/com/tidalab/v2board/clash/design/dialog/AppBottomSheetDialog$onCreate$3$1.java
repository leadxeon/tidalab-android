package com.tidalab.v2board.clash.design.dialog;

import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.tidalab.v2board.clash.design.ui.Insets;
import com.tidalab.v2board.clash.foss.R;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: Dialogs.kt */
/* loaded from: classes.dex */
public final class AppBottomSheetDialog$onCreate$3$1 extends Lambda implements Function1<Insets, Unit> {
    public final /* synthetic */ FrameLayout $this_apply;
    public final /* synthetic */ AppBottomSheetDialog this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppBottomSheetDialog$onCreate$3$1(AppBottomSheetDialog appBottomSheetDialog, FrameLayout frameLayout) {
        super(1);
        this.this$0 = appBottomSheetDialog;
        this.$this_apply = frameLayout;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Insets insets) {
        Insets insets2 = insets;
        if (!Intrinsics.areEqual(this.this$0.insets, insets2)) {
            this.this$0.insets = insets2;
            ViewGroup.LayoutParams layoutParams = this.$this_apply.getLayoutParams();
            Objects.requireNonNull(layoutParams, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams");
            CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) layoutParams;
            FrameLayout frameLayout = this.$this_apply;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            if (frameLayout.getLayoutDirection() == 0) {
                layoutParams2.setMargins(insets2.start, 0, insets2.end, 0);
            } else {
                layoutParams2.setMargins(insets2.end, 0, insets2.start, 0);
            }
            frameLayout.setPaddingRelative(0, (InputKt.getPixels(frameLayout.getContext(), R.dimen.bottom_sheet_background_padding_top) * 2) + InputKt.getPixels(frameLayout.getContext(), R.dimen.bottom_sheet_header_height), 0, insets2.bottom);
        }
        return Unit.INSTANCE;
    }
}
