package com.github.kr328.kaidl;

import android.os.IBinder;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* compiled from: SuspendTransaction.kt */
/* loaded from: classes.dex */
public final class SuspendTransactionKt$suspendTransact$2$1 extends Lambda implements Function0<Unit> {
    public final /* synthetic */ IBinder $context;
    public final /* synthetic */ IBinder.DeathRecipient $link;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SuspendTransactionKt$suspendTransact$2$1(IBinder iBinder, IBinder.DeathRecipient deathRecipient) {
        super(0);
        this.$context = iBinder;
        this.$link = deathRecipient;
    }

    @Override // kotlin.jvm.functions.Function0
    public Unit invoke() {
        try {
            this.$context.unlinkToDeath(this.$link, 0);
        } catch (Exception unused) {
        }
        return Unit.INSTANCE;
    }
}
