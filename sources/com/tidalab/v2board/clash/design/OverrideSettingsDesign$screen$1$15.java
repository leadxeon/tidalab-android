package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.preference.Preference;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.AdaptedFunctionReference;
/* compiled from: OverrideSettingsDesign.kt */
/* loaded from: classes.dex */
public final /* synthetic */ class OverrideSettingsDesign$screen$1$15 extends AdaptedFunctionReference implements Function1<Preference, Unit> {
    public OverrideSettingsDesign$screen$1$15(List<Preference> list) {
        super(1, list, List.class, "add", "add(Ljava/lang/Object;)Z", 8);
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Preference preference) {
        ((List) this.receiver).add(preference);
        return Unit.INSTANCE;
    }
}
