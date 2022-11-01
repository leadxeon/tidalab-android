package com.tidalab.v2board.clash.util;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: Application.kt */
/* loaded from: classes.dex */
public final class ApplicationObserver$visibleChanged$1 extends Lambda implements Function1<Boolean, Unit> {
    public static final ApplicationObserver$visibleChanged$1 INSTANCE = new ApplicationObserver$visibleChanged$1();

    public ApplicationObserver$visibleChanged$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Boolean bool) {
        bool.booleanValue();
        return Unit.INSTANCE;
    }
}
