package com.github.kr328.kaidl;

import android.os.Binder;
import android.os.Parcel;
import com.tidalab.v2board.clash.design.dialog.InputKt;
/* compiled from: SuspendTransaction.kt */
/* loaded from: classes.dex */
public abstract class TransactionContext extends Binder {
    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            InputKt.cancel$default(((SuspendTransactionKt$suspendTransaction$context$1) this).$job, null, 1, null);
        } else {
            super.onTransact(i, parcel, parcel2, i2);
        }
        return true;
    }
}
