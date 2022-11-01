package com.tidalab.v2board.clash.design.preference;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
/* compiled from: Clickable.kt */
/* loaded from: classes.dex */
public interface ClickablePreference extends Preference {
    void clicked(Function0<Unit> function0);

    CharSequence getTitle();

    void setSummary(CharSequence charSequence);
}
