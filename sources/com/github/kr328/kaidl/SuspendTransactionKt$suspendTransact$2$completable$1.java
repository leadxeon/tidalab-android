package com.github.kr328.kaidl;

import android.os.Parcel;
import kotlinx.coroutines.CancellableContinuation;
/* compiled from: SuspendTransaction.kt */
/* loaded from: classes.dex */
public final class SuspendTransactionKt$suspendTransact$2$completable$1 extends CompletableBinder {
    public final /* synthetic */ CancellableContinuation<Boolean> $it;
    public final /* synthetic */ Parcel $reply;

    /* JADX WARN: Multi-variable type inference failed */
    public SuspendTransactionKt$suspendTransact$2$completable$1(Parcel parcel, CancellableContinuation<? super Boolean> cancellableContinuation) {
        this.$reply = parcel;
        this.$it = cancellableContinuation;
    }
}
