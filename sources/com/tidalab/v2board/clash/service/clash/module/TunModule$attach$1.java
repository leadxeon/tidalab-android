package com.tidalab.v2board.clash.service.clash.module;

import android.net.VpnService;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
/* compiled from: TunModule.kt */
/* loaded from: classes.dex */
public final /* synthetic */ class TunModule$attach$1 extends FunctionReferenceImpl implements Function1<Integer, Boolean> {
    public TunModule$attach$1(VpnService vpnService) {
        super(1, vpnService, VpnService.class, "protect", "protect(I)Z", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public Boolean invoke(Integer num) {
        return Boolean.valueOf(((VpnService) this.receiver).protect(num.intValue()));
    }
}
