package com.tidalab.v2board.clash.design.util;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.core.content.ContextCompat;
/* compiled from: lambda */
/* renamed from: com.tidalab.v2board.clash.design.util.-$$Lambda$ViewKt$Leq_OYUMGGHh0dLHMsbHrbNfgyY  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$ViewKt$Leq_OYUMGGHh0dLHMsbHrbNfgyY implements Runnable {
    public final /* synthetic */ View f$0;

    public /* synthetic */ $$Lambda$ViewKt$Leq_OYUMGGHh0dLHMsbHrbNfgyY(View view) {
        this.f$0 = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        final View view = this.f$0;
        view.requestFocus();
        view.postDelayed(new Runnable() { // from class: com.tidalab.v2board.clash.design.util.-$$Lambda$ViewKt$WePdDowcgn8Ickb2h_YR1JHeVIQ
            @Override // java.lang.Runnable
            public final void run() {
                View view2 = view;
                InputMethodManager inputMethodManager = (InputMethodManager) ContextCompat.getSystemService(view2.getContext(), InputMethodManager.class);
                if (inputMethodManager != null) {
                    inputMethodManager.showSoftInput(view2, 0);
                }
            }
        }, 300L);
    }
}
