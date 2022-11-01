package kotlinx.coroutines.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
/* compiled from: Atomic.kt */
/* loaded from: classes.dex */
public abstract class OpDescriptor {
    public abstract AtomicOp<?> getAtomicOp();

    public final boolean isEarlierThan(OpDescriptor opDescriptor) {
        AtomicOp<?> atomicOp;
        AtomicOp<?> atomicOp2 = getAtomicOp();
        return (atomicOp2 == null || (atomicOp = opDescriptor.getAtomicOp()) == null || atomicOp2.getOpSequence() >= atomicOp.getOpSequence()) ? false : true;
    }

    public abstract Object perform(Object obj);

    public String toString() {
        return getClass().getSimpleName() + '@' + InputKt.getHexAddress(this);
    }
}
