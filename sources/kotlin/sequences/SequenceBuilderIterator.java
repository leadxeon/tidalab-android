package kotlin.sequences;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.internal.markers.KMappedMarker;
/* compiled from: SequenceBuilder.kt */
/* loaded from: classes.dex */
public final class SequenceBuilderIterator<T> extends SequenceScope<T> implements Iterator<T>, Continuation<Unit>, KMappedMarker {
    public Continuation<? super Unit> nextStep;
    public T nextValue;
    public int state;

    public final Throwable exceptionalState() {
        int i = this.state;
        if (i == 4) {
            return new NoSuchElementException();
        }
        if (i == 5) {
            return new IllegalStateException("Iterator has failed.");
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unexpected state of the iterator: ");
        outline13.append(this.state);
        return new IllegalStateException(outline13.toString());
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        int i;
        while (true) {
            i = this.state;
            if (i != 0) {
                break;
            }
            this.state = 5;
            Continuation<? super Unit> continuation = this.nextStep;
            this.nextStep = null;
            continuation.resumeWith(Unit.INSTANCE);
        }
        if (i == 1) {
            throw null;
        } else if (i == 2 || i == 3) {
            return true;
        } else {
            if (i == 4) {
                return false;
            }
            throw exceptionalState();
        }
    }

    @Override // java.util.Iterator
    public T next() {
        int i = this.state;
        if (i != 0 && i != 1) {
            Iterator it = null;
            if (i == 2) {
                this.state = 1;
                return (T) it.next();
            } else if (i == 3) {
                this.state = 0;
                T t = this.nextValue;
                this.nextValue = null;
                return t;
            } else {
                throw exceptionalState();
            }
        } else if (hasNext()) {
            return next();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object obj) {
        InputKt.throwOnFailure(obj);
        this.state = 4;
    }

    @Override // kotlin.sequences.SequenceScope
    public Object yield(T t, Continuation<? super Unit> continuation) {
        this.nextValue = t;
        this.state = 3;
        this.nextStep = continuation;
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }
}
