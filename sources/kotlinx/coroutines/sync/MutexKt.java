package kotlinx.coroutines.sync;

import kotlinx.coroutines.internal.Symbol;
/* compiled from: Mutex.kt */
/* loaded from: classes.dex */
public final class MutexKt {
    public static final Empty EMPTY_LOCKED;
    public static final Empty EMPTY_UNLOCKED;
    public static final Symbol LOCKED;
    public static final Symbol UNLOCKED;
    public static final Symbol UNLOCK_FAIL = new Symbol("UNLOCK_FAIL");

    static {
        Symbol symbol = new Symbol("LOCKED");
        LOCKED = symbol;
        Symbol symbol2 = new Symbol("UNLOCKED");
        UNLOCKED = symbol2;
        EMPTY_LOCKED = new Empty(symbol);
        EMPTY_UNLOCKED = new Empty(symbol2);
    }

    public static Mutex Mutex$default(boolean z, int i) {
        if ((i & 1) != 0) {
            z = false;
        }
        return new MutexImpl(z);
    }
}
