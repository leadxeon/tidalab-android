package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.OverrideSettingsDesign;
import com.tidalab.v2board.clash.design.preference.ClickablePreference;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: OverrideSettingsDesign.kt */
/* loaded from: classes.dex */
public final class OverrideSettingsDesign$screen$1$13 extends Lambda implements Function1<ClickablePreference, Unit> {
    public final /* synthetic */ OverrideSettingsDesign this$0;

    /* compiled from: OverrideSettingsDesign.kt */
    /* renamed from: com.tidalab.v2board.clash.design.OverrideSettingsDesign$screen$1$13$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        public final /* synthetic */ OverrideSettingsDesign this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(OverrideSettingsDesign overrideSettingsDesign) {
            super(0);
            this.this$0 = overrideSettingsDesign;
        }

        @Override // kotlin.jvm.functions.Function0
        public Unit invoke() {
            this.this$0.requests.mo14trySendJP2dKIU(OverrideSettingsDesign.Request.EditSideloadGeoip);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OverrideSettingsDesign$screen$1$13(OverrideSettingsDesign overrideSettingsDesign) {
        super(1);
        this.this$0 = overrideSettingsDesign;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(ClickablePreference clickablePreference) {
        clickablePreference.clicked(new AnonymousClass1(this.this$0));
        return Unit.INSTANCE;
    }
}
