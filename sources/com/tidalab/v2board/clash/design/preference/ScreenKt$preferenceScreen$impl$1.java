package com.tidalab.v2board.clash.design.preference;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: Screen.kt */
/* loaded from: classes.dex */
public final class ScreenKt$preferenceScreen$impl$1 implements PreferenceScreen, CoroutineScope {
    public final /* synthetic */ CoroutineScope $$delegate_0;
    public final /* synthetic */ Context $context;
    public final /* synthetic */ LinearLayout $root;
    public final /* synthetic */ CoroutineScope $this_preferenceScreen;

    public ScreenKt$preferenceScreen$impl$1(CoroutineScope coroutineScope, Context context, LinearLayout linearLayout) {
        this.$this_preferenceScreen = coroutineScope;
        this.$context = context;
        this.$root = linearLayout;
        this.$$delegate_0 = coroutineScope;
    }

    @Override // com.tidalab.v2board.clash.design.preference.PreferenceScreen
    public Context getContext() {
        return this.$context;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    @Override // com.tidalab.v2board.clash.design.preference.PreferenceScreen
    public ViewGroup getRoot() {
        return this.$root;
    }
}
