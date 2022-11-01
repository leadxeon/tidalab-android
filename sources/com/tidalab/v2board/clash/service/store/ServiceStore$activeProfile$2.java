package com.tidalab.v2board.clash.service.store;

import java.util.UUID;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__IndentKt;
/* compiled from: ServiceStore.kt */
/* loaded from: classes.dex */
public final class ServiceStore$activeProfile$2 extends Lambda implements Function1<String, UUID> {
    public static final ServiceStore$activeProfile$2 INSTANCE = new ServiceStore$activeProfile$2();

    public ServiceStore$activeProfile$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public UUID invoke(String str) {
        String str2 = str;
        if (StringsKt__IndentKt.isBlank(str2)) {
            return null;
        }
        return UUID.fromString(str2);
    }
}
