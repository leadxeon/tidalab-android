package kotlinx.coroutines.channels;

import com.tidalab.v2board.clash.design.dialog.InputKt;
/* compiled from: Channel.kt */
/* loaded from: classes.dex */
public interface Channel<E> extends SendChannel<E>, ReceiveChannel<E> {
    public static final Factory Factory = Factory.$$INSTANCE;

    /* compiled from: Channel.kt */
    /* loaded from: classes.dex */
    public static final class Factory {
        public static final /* synthetic */ Factory $$INSTANCE = new Factory();
        public static final int CHANNEL_DEFAULT_CAPACITY = (int) InputKt.systemProp("kotlinx.coroutines.channels.defaultBuffer", 64, 1, 2147483646);
    }
}
