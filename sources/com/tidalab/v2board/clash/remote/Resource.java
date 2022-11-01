package com.tidalab.v2board.clash.remote;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CancellableContinuationImpl;
/* compiled from: Resource.kt */
/* loaded from: classes.dex */
public final class Resource<T> {
    public final Set<Callback<T>> pending = new LinkedHashSet();
    public T value;

    /* compiled from: Resource.kt */
    /* loaded from: classes.dex */
    public interface Callback<T> {
        void accept(T t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Object, com.tidalab.v2board.clash.remote.Resource$get$2$callback$1] */
    public final Object get(Continuation<? super T> continuation) {
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(InputKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        Callback<T> resource$get$2$callback$1 = new Callback<T>() { // from class: com.tidalab.v2board.clash.remote.Resource$get$2$callback$1
            @Override // com.tidalab.v2board.clash.remote.Resource.Callback
            public void accept(T t) {
                cancellableContinuationImpl.resumeWith(t);
            }
        };
        cancellableContinuationImpl.invokeOnCancellation(new Resource$get$2$1(this, resource$get$2$callback$1));
        synchronized (this) {
            T t = this.value;
            if (t == null) {
                this.pending.add(resource$get$2$callback$1);
            } else {
                cancellableContinuationImpl.resumeWith(t);
            }
        }
        return cancellableContinuationImpl.getResult();
    }

    public final void set(T t) {
        synchronized (this) {
            this.value = t;
            if (t != null) {
                Iterator<T> it = this.pending.iterator();
                while (it.hasNext()) {
                    ((Callback) it.next()).accept(t);
                }
                this.pending.clear();
            }
        }
    }
}
