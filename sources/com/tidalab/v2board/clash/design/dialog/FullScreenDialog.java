package com.tidalab.v2board.clash.design.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.design.ui.Surface;
import com.tidalab.v2board.clash.foss.R;
/* compiled from: Dialogs.kt */
/* loaded from: classes.dex */
public final class FullScreenDialog extends Dialog {
    public final Surface surface = new Surface();

    public FullScreenDialog(Context context) {
        super(context, InputKt.resolveThemedResourceId(context, R.attr.fullScreenDialogTheme));
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        PathParser.setSystemBarsTranslucentCompat(window, true);
        window.setLayout(-1, -1);
        InputKt.setOnInsertsChangedListener$default(window.getDecorView(), false, new FullScreenDialog$onCreate$1$1(this), 1);
    }
}
