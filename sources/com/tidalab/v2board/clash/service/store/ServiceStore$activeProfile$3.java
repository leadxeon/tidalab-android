package com.tidalab.v2board.clash.service.store;

import java.util.UUID;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import okhttp3.HttpUrl;
/* compiled from: ServiceStore.kt */
/* loaded from: classes.dex */
public final class ServiceStore$activeProfile$3 extends Lambda implements Function1<UUID, String> {
    public static final ServiceStore$activeProfile$3 INSTANCE = new ServiceStore$activeProfile$3();

    public ServiceStore$activeProfile$3() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public String invoke(UUID uuid) {
        String uuid2;
        UUID uuid3 = uuid;
        return (uuid3 == null || (uuid2 = uuid3.toString()) == null) ? HttpUrl.FRAGMENT_ENCODE_SET : uuid2;
    }
}
