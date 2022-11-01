package kotlin.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;
/* compiled from: AbstractIterator.kt */
/* loaded from: classes.dex */
public abstract class AbstractIterator<T> implements Iterator<T>, KMappedMarker {
    public T nextValue;
    public int state = 2;

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0058, code lost:
        r0.nextValue = (T) r4;
        r0.state = 1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [kotlin.io.FileTreeWalk$FileTreeWalkIterator, kotlin.collections.AbstractIterator] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [T] */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.lang.Object, java.io.File] */
    /* JADX WARN: Unknown variable types count: 2 */
    @Override // java.util.Iterator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean hasNext() {
        /*
            r6 = this;
            int r0 = r6.state
            r1 = 0
            r2 = 1
            r3 = 4
            if (r0 == r3) goto L_0x0009
            r4 = 1
            goto L_0x000a
        L_0x0009:
            r4 = 0
        L_0x000a:
            if (r4 == 0) goto L_0x0066
            int r0 = androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(r0)
            if (r0 == 0) goto L_0x0064
            r4 = 2
            if (r0 == r4) goto L_0x0065
            r6.state = r3
            r0 = r6
            kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = (kotlin.io.FileTreeWalk.FileTreeWalkIterator) r0
        L_0x001a:
            java.util.ArrayDeque<kotlin.io.FileTreeWalk$WalkState> r3 = r0.state
            java.lang.Object r3 = r3.peek()
            kotlin.io.FileTreeWalk$WalkState r3 = (kotlin.io.FileTreeWalk.WalkState) r3
            if (r3 == 0) goto L_0x0055
            java.io.File r4 = r3.step()
            if (r4 != 0) goto L_0x0030
            java.util.ArrayDeque<kotlin.io.FileTreeWalk$WalkState> r3 = r0.state
            r3.pop()
            goto L_0x001a
        L_0x0030:
            java.io.File r3 = r3.root
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r3)
            if (r3 != 0) goto L_0x0056
            boolean r3 = r4.isDirectory()
            if (r3 == 0) goto L_0x0056
            java.util.ArrayDeque<kotlin.io.FileTreeWalk$WalkState> r3 = r0.state
            int r3 = r3.size()
            kotlin.io.FileTreeWalk r5 = kotlin.io.FileTreeWalk.this
            int r5 = r5.maxDepth
            if (r3 < r5) goto L_0x004b
            goto L_0x0056
        L_0x004b:
            java.util.ArrayDeque<kotlin.io.FileTreeWalk$WalkState> r3 = r0.state
            kotlin.io.FileTreeWalk$DirectoryState r4 = r0.directoryState(r4)
            r3.push(r4)
            goto L_0x001a
        L_0x0055:
            r4 = 0
        L_0x0056:
            if (r4 == 0) goto L_0x005d
            r0.nextValue = r4
            r0.state = r2
            goto L_0x0060
        L_0x005d:
            r3 = 3
            r0.state = r3
        L_0x0060:
            int r0 = r6.state
            if (r0 != r2) goto L_0x0065
        L_0x0064:
            r1 = 1
        L_0x0065:
            return r1
        L_0x0066:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Failed requirement."
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.AbstractIterator.hasNext():boolean");
    }

    @Override // java.util.Iterator
    public T next() {
        if (hasNext()) {
            this.state = 2;
            return this.nextValue;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
