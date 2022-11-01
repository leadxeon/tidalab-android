package kotlinx.coroutines.internal;
/* compiled from: Atomic.kt */
/* loaded from: classes.dex */
public abstract class AtomicDesc {
    public AtomicOp<?> atomicOp;

    public abstract void complete(AtomicOp<?> atomicOp, Object obj);

    public abstract Object prepare(AtomicOp<?> atomicOp);
}
