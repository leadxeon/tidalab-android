package kotlinx.coroutines.channels;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
/* compiled from: RendezvousChannel.kt */
/* loaded from: classes.dex */
public class RendezvousChannel<E> extends AbstractChannel<E> {
    public RendezvousChannel(Function1<? super E, Unit> function1) {
        super(function1);
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferAlwaysEmpty() {
        return true;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferAlwaysFull() {
        return true;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferEmpty() {
        return true;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferFull() {
        return true;
    }
}
