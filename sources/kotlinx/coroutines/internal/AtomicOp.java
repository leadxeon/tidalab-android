package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
/* compiled from: Atomic.kt */
/* loaded from: classes.dex */
public abstract class AtomicOp<T> extends OpDescriptor {
    public static final /* synthetic */ AtomicReferenceFieldUpdater _consensus$FU = AtomicReferenceFieldUpdater.newUpdater(AtomicOp.class, Object.class, "_consensus");
    private volatile /* synthetic */ Object _consensus = AtomicKt.NO_DECISION;

    public abstract void complete(T t, Object obj);

    public final Object decide(Object obj) {
        Object obj2 = this._consensus;
        Object obj3 = AtomicKt.NO_DECISION;
        return obj2 != obj3 ? obj2 : _consensus$FU.compareAndSet(this, obj3, obj) ? obj : this._consensus;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.internal.OpDescriptor
    public AtomicOp<?> getAtomicOp() {
        return this;
    }

    public final Object getConsensus() {
        return this._consensus;
    }

    public long getOpSequence() {
        return 0L;
    }

    public final boolean isDecided() {
        return this._consensus != AtomicKt.NO_DECISION;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.internal.OpDescriptor
    public final Object perform(Object obj) {
        Object obj2 = this._consensus;
        if (obj2 == AtomicKt.NO_DECISION) {
            obj2 = decide(prepare(obj));
        }
        complete(obj, obj2);
        return obj2;
    }

    public abstract Object prepare(T t);
}
