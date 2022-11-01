package com.github.kr328.kaidl;

import android.os.IBinder;
import android.os.Parcel;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: SuspendTransaction.kt */
/* loaded from: classes.dex */
public final class SuspendTransactionKt$suspendTransact$2$2 extends Lambda implements Function1<Throwable, Unit> {
    public final /* synthetic */ IBinder $context;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SuspendTransactionKt$suspendTransact$2$2(IBinder iBinder) {
        super(1);
        this.$context = iBinder;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Throwable th) {
        Parcel obtain = Parcel.obtain();
        try {
            this.$context.transact(1, obtain, null, 0);
        } catch (Exception unused) {
        } catch (Throwable th2) {
            obtain.recycle();
            throw th2;
        }
        obtain.recycle();
        return Unit.INSTANCE;
    }
}
