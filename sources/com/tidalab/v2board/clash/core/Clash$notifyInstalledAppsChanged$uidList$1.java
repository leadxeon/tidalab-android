package com.tidalab.v2board.clash.core;

import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: Clash.kt */
/* loaded from: classes.dex */
public final class Clash$notifyInstalledAppsChanged$uidList$1 extends Lambda implements Function1<Pair<? extends Integer, ? extends String>, CharSequence> {
    public static final Clash$notifyInstalledAppsChanged$uidList$1 INSTANCE = new Clash$notifyInstalledAppsChanged$uidList$1();

    public Clash$notifyInstalledAppsChanged$uidList$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public CharSequence invoke(Pair<? extends Integer, ? extends String> pair) {
        Pair<? extends Integer, ? extends String> pair2 = pair;
        return ((Number) pair2.first).intValue() + ':' + ((String) pair2.second);
    }
}
