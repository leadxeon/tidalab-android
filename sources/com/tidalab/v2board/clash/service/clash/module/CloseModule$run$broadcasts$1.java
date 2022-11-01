package com.tidalab.v2board.clash.service.clash.module;

import android.content.IntentFilter;
import com.tidalab.v2board.clash.common.constants.Intents;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: CloseModule.kt */
/* loaded from: classes.dex */
public final class CloseModule$run$broadcasts$1 extends Lambda implements Function1<IntentFilter, Unit> {
    public static final CloseModule$run$broadcasts$1 INSTANCE = new CloseModule$run$broadcasts$1();

    public CloseModule$run$broadcasts$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(IntentFilter intentFilter) {
        Intents intents = Intents.INSTANCE;
        intentFilter.addAction(Intents.ACTION_CLASH_REQUEST_STOP);
        return Unit.INSTANCE;
    }
}
