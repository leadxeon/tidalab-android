package androidx.room;

import android.os.CancellationSignal;
import androidx.recyclerview.R$dimen;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.concurrent.Callable;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.GlobalScope;
/* compiled from: CoroutinesRoom.kt */
/* loaded from: classes.dex */
public final class CoroutinesRoom {
    public static final <R> Object execute(RoomDatabase roomDatabase, boolean z, Callable<R> callable, Continuation<? super R> continuation) {
        if (roomDatabase.isOpen() && roomDatabase.inTransaction()) {
            return callable.call();
        }
        TransactionElement transactionElement = (TransactionElement) continuation.getContext().get(TransactionElement.Key);
        return InputKt.withContext(z ? R$dimen.getTransactionDispatcher(roomDatabase) : R$dimen.getQueryDispatcher(roomDatabase), new CoroutinesRoom$Companion$execute$2(callable, null), continuation);
    }

    public static final <R> Object execute(RoomDatabase roomDatabase, boolean z, CancellationSignal cancellationSignal, Callable<R> callable, Continuation<? super R> continuation) {
        if (roomDatabase.isOpen() && roomDatabase.inTransaction()) {
            return callable.call();
        }
        TransactionElement transactionElement = (TransactionElement) continuation.getContext().get(TransactionElement.Key);
        CoroutineDispatcher transactionDispatcher = z ? R$dimen.getTransactionDispatcher(roomDatabase) : R$dimen.getQueryDispatcher(roomDatabase);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(InputKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        cancellableContinuationImpl.invokeOnCancellation(new CoroutinesRoom$Companion$execute$$inlined$suspendCancellableCoroutine$lambda$2(InputKt.launch$default(GlobalScope.INSTANCE, transactionDispatcher, null, new CoroutinesRoom$Companion$execute$$inlined$suspendCancellableCoroutine$lambda$1(cancellableContinuationImpl, null, transactionDispatcher, callable, cancellationSignal), 2, null), transactionDispatcher, callable, cancellationSignal));
        return cancellableContinuationImpl.getResult();
    }
}
