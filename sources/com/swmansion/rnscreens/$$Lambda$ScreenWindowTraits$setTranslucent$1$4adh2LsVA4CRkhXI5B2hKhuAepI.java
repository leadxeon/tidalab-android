package com.swmansion.rnscreens;

import android.view.View;
import android.view.WindowInsets;
/* compiled from: lambda */
/* renamed from: com.swmansion.rnscreens.-$$Lambda$ScreenWindowTraits$setTranslucent$1$4adh2LsVA4CRkhXI5B2hKhuAepI  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$ScreenWindowTraits$setTranslucent$1$4adh2LsVA4CRkhXI5B2hKhuAepI implements View.OnApplyWindowInsetsListener {
    public static final /* synthetic */ $$Lambda$ScreenWindowTraits$setTranslucent$1$4adh2LsVA4CRkhXI5B2hKhuAepI INSTANCE = new $$Lambda$ScreenWindowTraits$setTranslucent$1$4adh2LsVA4CRkhXI5B2hKhuAepI();

    @Override // android.view.View.OnApplyWindowInsetsListener
    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(windowInsets);
        return onApplyWindowInsets.replaceSystemWindowInsets(onApplyWindowInsets.getSystemWindowInsetLeft(), 0, onApplyWindowInsets.getSystemWindowInsetRight(), onApplyWindowInsets.getSystemWindowInsetBottom());
    }
}
