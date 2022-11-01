package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
/* compiled from: CompletionState.kt */
/* loaded from: classes.dex */
public class CompletedExceptionally {
    public static final /* synthetic */ AtomicIntegerFieldUpdater _handled$FU = AtomicIntegerFieldUpdater.newUpdater(CompletedExceptionally.class, "_handled");
    private volatile /* synthetic */ int _handled;
    public final Throwable cause;

    public CompletedExceptionally(Throwable th, boolean z) {
        this.cause = th;
        this._handled = z ? 1 : 0;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [int, boolean] */
    public final boolean getHandled() {
        return this._handled;
    }

    public String toString() {
        return getClass().getSimpleName() + '[' + this.cause + ']';
    }

    public CompletedExceptionally(Throwable th, boolean z, int i) {
        z = (i & 2) != 0 ? false : z;
        this.cause = th;
        int i2 = z ? 1 : 0;
        int i3 = z ? 1 : 0;
        int i4 = z ? 1 : 0;
        this._handled = i2;
    }
}
