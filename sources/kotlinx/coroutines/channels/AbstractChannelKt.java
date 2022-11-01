package kotlinx.coroutines.channels;

import kotlinx.coroutines.internal.Symbol;
/* compiled from: AbstractChannel.kt */
/* loaded from: classes.dex */
public final class AbstractChannelKt {
    public static final Symbol EMPTY = new Symbol("EMPTY");
    public static final Symbol OFFER_SUCCESS = new Symbol("OFFER_SUCCESS");
    public static final Symbol OFFER_FAILED = new Symbol("OFFER_FAILED");
    public static final Symbol POLL_FAILED = new Symbol("POLL_FAILED");
    public static final Symbol ENQUEUE_FAILED = new Symbol("ENQUEUE_FAILED");
    public static final Symbol HANDLER_INVOKED = new Symbol("ON_CLOSE_HANDLER_INVOKED");
}
