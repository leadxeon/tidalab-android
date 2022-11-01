package com.tidalab.v2board.clash.design.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.design.ui.Insets;
import com.tidalab.v2board.clash.foss.R;
/* compiled from: Dialogs.kt */
/* loaded from: classes.dex */
public final class AppBottomSheetDialog extends BottomSheetDialog {
    public Insets insets = Insets.EMPTY;

    public AppBottomSheetDialog(Context context) {
        super(context);
    }

    @Override // com.google.android.material.bottomsheet.BottomSheetDialog, androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCancelable(true);
        Window window = getWindow();
        PathParser.setSystemBarsTranslucentCompat(window, true);
        if (Build.VERSION.SDK_INT >= 29) {
            window.getDecorView().setForceDarkAllowed(false);
        }
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.container);
        if (viewGroup != null) {
            viewGroup.setFitsSystemWindows(false);
        }
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.design_bottom_sheet);
        if (frameLayout != null) {
            InputKt.setOnInsertsChangedListener$default(frameLayout, false, new AppBottomSheetDialog$onCreate$3$1(this, frameLayout), 1);
        }
        setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.tidalab.v2board.clash.design.dialog.-$$Lambda$AppBottomSheetDialog$z9Hoy3KhWqbSQUs8ED9DxksY8zU
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                AppBottomSheetDialog appBottomSheetDialog = AppBottomSheetDialog.this;
                appBottomSheetDialog.getBehavior().setHalfExpandedRatio(0.99f);
                appBottomSheetDialog.getBehavior().setState(3);
            }
        });
    }
}
