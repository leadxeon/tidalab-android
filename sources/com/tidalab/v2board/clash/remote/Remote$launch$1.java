package com.tidalab.v2board.clash.remote;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: Remote.kt */
/* loaded from: classes.dex */
public final class Remote$launch$1 extends Lambda implements Function1<Boolean, Unit> {
    public static final Remote$launch$1 INSTANCE = new Remote$launch$1();

    public Remote$launch$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Boolean bool) {
        Remote.visible.mo14trySendJP2dKIU(Boolean.valueOf(bool.booleanValue()));
        return Unit.INSTANCE;
    }
}
