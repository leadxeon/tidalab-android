package kotlin.coroutines;

import java.io.Serializable;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
/* compiled from: CoroutineContextImpl.kt */
/* loaded from: classes.dex */
public final class EmptyCoroutineContext implements CoroutineContext, Serializable {
    public static final EmptyCoroutineContext INSTANCE = new EmptyCoroutineContext();

    @Override // kotlin.coroutines.CoroutineContext
    public <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return r;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        return null;
    }

    public int hashCode() {
        return 0;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        return this;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext coroutineContext) {
        return coroutineContext;
    }

    public String toString() {
        return "EmptyCoroutineContext";
    }
}
