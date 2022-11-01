package kotlin.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.jvm.functions.Function1;
/* compiled from: CoroutineContextImpl.kt */
/* loaded from: classes.dex */
public abstract class AbstractCoroutineContextKey<B extends CoroutineContext.Element, E extends B> implements CoroutineContext.Key<E> {
    public final Function1<CoroutineContext.Element, E> safeCast;
    public final CoroutineContext.Key<?> topmostKey;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [kotlin.jvm.functions.Function1<? super kotlin.coroutines.CoroutineContext$Element, ? extends E extends B>, kotlin.jvm.functions.Function1<kotlin.coroutines.CoroutineContext$Element, E extends B>] */
    public AbstractCoroutineContextKey(CoroutineContext.Key<B> key, Function1<? super CoroutineContext.Element, ? extends E> function1) {
        this.safeCast = function1;
        this.topmostKey = key instanceof AbstractCoroutineContextKey ? (CoroutineContext.Key<B>) ((AbstractCoroutineContextKey) key).topmostKey : key;
    }
}
