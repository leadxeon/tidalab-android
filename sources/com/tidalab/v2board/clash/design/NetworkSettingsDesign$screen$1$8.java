package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.preference.Preference;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.AdaptedFunctionReference;
/* compiled from: NetworkSettingsDesign.kt */
/* loaded from: classes.dex */
public final /* synthetic */ class NetworkSettingsDesign$screen$1$8 extends AdaptedFunctionReference implements Function1<Preference, Unit> {
    public NetworkSettingsDesign$screen$1$8(List<Preference> list) {
        super(1, list, List.class, "add", "add(Ljava/lang/Object;)Z", 8);
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Preference preference) {
        ((List) this.receiver).add(preference);
        return Unit.INSTANCE;
    }
}
