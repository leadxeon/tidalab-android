package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.preference.OnChangedListener;
import com.tidalab.v2board.clash.design.preference.Preference;
import com.tidalab.v2board.clash.design.preference.SwitchPreference;
import com.tidalab.v2board.clash.design.store.UiStore;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: NetworkSettingsDesign.kt */
/* loaded from: classes.dex */
public final class NetworkSettingsDesign$screen$1$vpn$2 extends Lambda implements Function1<SwitchPreference, Unit> {
    public final /* synthetic */ UiStore $uiStore;
    public final /* synthetic */ List<Preference> $vpnDependencies;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkSettingsDesign$screen$1$vpn$2(List<Preference> list, UiStore uiStore) {
        super(1);
        this.$vpnDependencies = list;
        this.$uiStore = uiStore;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(SwitchPreference switchPreference) {
        final List<Preference> list = this.$vpnDependencies;
        final UiStore uiStore = this.$uiStore;
        switchPreference.setListener(new OnChangedListener() { // from class: com.tidalab.v2board.clash.design.-$$Lambda$NetworkSettingsDesign$screen$1$vpn$2$rdhR_q86cSxExEH6G6w_lcU-7Lk
            @Override // com.tidalab.v2board.clash.design.preference.OnChangedListener
            public final void onChanged() {
                List<Preference> list2 = list;
                UiStore uiStore2 = uiStore;
                for (Preference preference : list2) {
                    preference.setEnabled(uiStore2.getEnableVpn());
                }
            }
        });
        return Unit.INSTANCE;
    }
}
