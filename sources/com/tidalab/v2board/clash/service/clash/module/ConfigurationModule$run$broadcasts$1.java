package com.tidalab.v2board.clash.service.clash.module;

import android.content.IntentFilter;
import com.tidalab.v2board.clash.common.constants.Intents;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: ConfigurationModule.kt */
/* loaded from: classes.dex */
public final class ConfigurationModule$run$broadcasts$1 extends Lambda implements Function1<IntentFilter, Unit> {
    public static final ConfigurationModule$run$broadcasts$1 INSTANCE = new ConfigurationModule$run$broadcasts$1();

    public ConfigurationModule$run$broadcasts$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(IntentFilter intentFilter) {
        IntentFilter intentFilter2 = intentFilter;
        Intents intents = Intents.INSTANCE;
        intentFilter2.addAction(Intents.ACTION_PROFILE_CHANGED);
        intentFilter2.addAction(Intents.ACTION_OVERRIDE_CHANGED);
        return Unit.INSTANCE;
    }
}
