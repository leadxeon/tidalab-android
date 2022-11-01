package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.core.model.ConfigurationOverride;
import com.tidalab.v2board.clash.design.preference.OnChangedListener;
import com.tidalab.v2board.clash.design.preference.Preference;
import com.tidalab.v2board.clash.design.preference.SelectableListPreference;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: OverrideSettingsDesign.kt */
/* loaded from: classes.dex */
public final class OverrideSettingsDesign$screen$1$dns$2 extends Lambda implements Function1<SelectableListPreference<Boolean>, Unit> {
    public final /* synthetic */ ConfigurationOverride $configuration;
    public final /* synthetic */ List<Preference> $dnsDependencies;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OverrideSettingsDesign$screen$1$dns$2(ConfigurationOverride configurationOverride, List<Preference> list) {
        super(1);
        this.$configuration = configurationOverride;
        this.$dnsDependencies = list;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(SelectableListPreference<Boolean> selectableListPreference) {
        final ConfigurationOverride configurationOverride = this.$configuration;
        final List<Preference> list = this.$dnsDependencies;
        selectableListPreference.setListener(new OnChangedListener() { // from class: com.tidalab.v2board.clash.design.-$$Lambda$OverrideSettingsDesign$screen$1$dns$2$17HdwnD5sRHGu5V0EjR9udMp85w
            @Override // com.tidalab.v2board.clash.design.preference.OnChangedListener
            public final void onChanged() {
                ConfigurationOverride configurationOverride2 = ConfigurationOverride.this;
                List<Preference> list2 = list;
                if (Intrinsics.areEqual(configurationOverride2.dns.enable, Boolean.FALSE)) {
                    for (Preference preference : list2) {
                        preference.setEnabled(false);
                    }
                    return;
                }
                for (Preference preference2 : list2) {
                    preference2.setEnabled(true);
                }
            }
        });
        return Unit.INSTANCE;
    }
}
