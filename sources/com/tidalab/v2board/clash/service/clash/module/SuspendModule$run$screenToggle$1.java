package com.tidalab.v2board.clash.service.clash.module;

import android.content.IntentFilter;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: SuspendModule.kt */
/* loaded from: classes.dex */
public final class SuspendModule$run$screenToggle$1 extends Lambda implements Function1<IntentFilter, Unit> {
    public static final SuspendModule$run$screenToggle$1 INSTANCE = new SuspendModule$run$screenToggle$1();

    public SuspendModule$run$screenToggle$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(IntentFilter intentFilter) {
        IntentFilter intentFilter2 = intentFilter;
        intentFilter2.addAction("android.intent.action.SCREEN_ON");
        intentFilter2.addAction("android.intent.action.SCREEN_OFF");
        return Unit.INSTANCE;
    }
}
