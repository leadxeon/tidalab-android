package com.github.kr328.kaidl;

import android.os.Binder;
import android.os.Parcel;
/* compiled from: SuspendTransaction.kt */
/* loaded from: classes.dex */
public abstract class CompletableBinder extends Binder {
    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            SuspendTransactionKt$suspendTransact$2$completable$1 suspendTransactionKt$suspendTransact$2$completable$1 = (SuspendTransactionKt$suspendTransact$2$completable$1) this;
            suspendTransactionKt$suspendTransact$2$completable$1.$reply.appendFrom(parcel, 0, parcel.dataAvail());
            suspendTransactionKt$suspendTransact$2$completable$1.$reply.setDataPosition(0);
            suspendTransactionKt$suspendTransact$2$completable$1.$it.resumeWith(Boolean.TRUE);
        } else if (i != 2) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            ((SuspendTransactionKt$suspendTransact$2$completable$1) this).$it.cancel(null);
        }
        return true;
    }
}
