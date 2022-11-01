package com.tidalab.v2board.clash.service.clash.module;

import android.os.Build;
import java.net.InetSocketAddress;
import java.util.Objects;
import kotlin.Result;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
/* compiled from: TunModule.kt */
/* loaded from: classes.dex */
public final /* synthetic */ class TunModule$attach$2 extends FunctionReferenceImpl implements Function3<Integer, InetSocketAddress, InetSocketAddress, Integer> {
    public TunModule$attach$2(TunModule tunModule) {
        super(3, tunModule, TunModule.class, "queryUid", "queryUid(ILjava/net/InetSocketAddress;Ljava/net/InetSocketAddress;)I", 0);
    }

    @Override // kotlin.jvm.functions.Function3
    public Integer invoke(Integer num, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2) {
        Object obj;
        int intValue = num.intValue();
        InetSocketAddress inetSocketAddress3 = inetSocketAddress;
        InetSocketAddress inetSocketAddress4 = inetSocketAddress2;
        TunModule tunModule = (TunModule) this.receiver;
        Objects.requireNonNull(tunModule);
        int i = -1;
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                obj = Integer.valueOf(tunModule.connectivity.getConnectionOwnerUid(intValue, inetSocketAddress3, inetSocketAddress4));
            } catch (Throwable th) {
                obj = new Result.Failure(th);
            }
            if (Result.m11exceptionOrNullimpl(obj) != null) {
                obj = -1;
            }
            i = ((Number) obj).intValue();
        }
        return Integer.valueOf(i);
    }
}
