package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.AppSettingsDesign;
import com.tidalab.v2board.clash.design.model.DarkMode;
import com.tidalab.v2board.clash.design.preference.OnChangedListener;
import com.tidalab.v2board.clash.design.preference.SelectableListPreference;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: AppSettingsDesign.kt */
/* loaded from: classes.dex */
public final class AppSettingsDesign$screen$1$3 extends Lambda implements Function1<SelectableListPreference<DarkMode>, Unit> {
    public final /* synthetic */ AppSettingsDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppSettingsDesign$screen$1$3(AppSettingsDesign appSettingsDesign) {
        super(1);
        this.this$0 = appSettingsDesign;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(SelectableListPreference<DarkMode> selectableListPreference) {
        final AppSettingsDesign appSettingsDesign = this.this$0;
        selectableListPreference.setListener(new OnChangedListener() { // from class: com.tidalab.v2board.clash.design.-$$Lambda$AppSettingsDesign$screen$1$3$gZuypqXVShIqdLNz9fK1hM2APr8
            @Override // com.tidalab.v2board.clash.design.preference.OnChangedListener
            public final void onChanged() {
                AppSettingsDesign.this.requests.mo14trySendJP2dKIU(AppSettingsDesign.Request.ReCreateAllActivities);
            }
        });
        return Unit.INSTANCE;
    }
}
