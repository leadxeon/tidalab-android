package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.preference.SwitchPreference;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: AppSettingsDesign.kt */
/* loaded from: classes.dex */
public final class AppSettingsDesign$screen$1$5 extends Lambda implements Function1<SwitchPreference, Unit> {
    public final /* synthetic */ boolean $running;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppSettingsDesign$screen$1$5(boolean z) {
        super(1);
        this.$running = z;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(SwitchPreference switchPreference) {
        switchPreference.setEnabled(!this.$running);
        return Unit.INSTANCE;
    }
}
