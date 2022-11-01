package com.tidalab.v2board.clash.service.clash.module;

import android.content.IntentFilter;
import com.tidalab.v2board.clash.common.constants.Intents;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: StaticNotificationModule.kt */
/* loaded from: classes.dex */
public final class StaticNotificationModule$run$loaded$1 extends Lambda implements Function1<IntentFilter, Unit> {
    public static final StaticNotificationModule$run$loaded$1 INSTANCE = new StaticNotificationModule$run$loaded$1();

    public StaticNotificationModule$run$loaded$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(IntentFilter intentFilter) {
        Intents intents = Intents.INSTANCE;
        intentFilter.addAction(Intents.ACTION_PROFILE_LOADED);
        return Unit.INSTANCE;
    }
}
