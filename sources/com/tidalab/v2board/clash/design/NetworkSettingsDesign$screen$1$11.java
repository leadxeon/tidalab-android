package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.NetworkSettingsDesign;
import com.tidalab.v2board.clash.design.preference.ClickablePreference;
import com.tidalab.v2board.clash.design.preference.Preference;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: NetworkSettingsDesign.kt */
/* loaded from: classes.dex */
public final class NetworkSettingsDesign$screen$1$11 extends Lambda implements Function1<ClickablePreference, Unit> {
    public final /* synthetic */ List<Preference> $vpnDependencies;
    public final /* synthetic */ NetworkSettingsDesign this$0;

    /* compiled from: NetworkSettingsDesign.kt */
    /* renamed from: com.tidalab.v2board.clash.design.NetworkSettingsDesign$screen$1$11$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        public final /* synthetic */ NetworkSettingsDesign this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(NetworkSettingsDesign networkSettingsDesign) {
            super(0);
            this.this$0 = networkSettingsDesign;
        }

        @Override // kotlin.jvm.functions.Function0
        public Unit invoke() {
            this.this$0.requests.mo14trySendJP2dKIU(NetworkSettingsDesign.Request.StartAccessControlList);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkSettingsDesign$screen$1$11(List<Preference> list, NetworkSettingsDesign networkSettingsDesign) {
        super(1);
        this.$vpnDependencies = list;
        this.this$0 = networkSettingsDesign;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(ClickablePreference clickablePreference) {
        ClickablePreference clickablePreference2 = clickablePreference;
        clickablePreference2.clicked(new AnonymousClass1(this.this$0));
        this.$vpnDependencies.add(clickablePreference2);
        return Unit.INSTANCE;
    }
}
