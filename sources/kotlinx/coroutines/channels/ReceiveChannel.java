package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.selects.SelectClause1;
/* compiled from: Channel.kt */
/* loaded from: classes.dex */
public interface ReceiveChannel<E> {
    void cancel(CancellationException cancellationException);

    SelectClause1<E> getOnReceive();

    Object receive(Continuation<? super E> continuation);
}
