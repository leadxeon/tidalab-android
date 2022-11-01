package kotlin.coroutines;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: CoroutineContext.kt */
/* loaded from: classes.dex */
public interface CoroutineContext {

    /* compiled from: CoroutineContext.kt */
    /* loaded from: classes.dex */
    public interface Element extends CoroutineContext {

        /* compiled from: CoroutineContext.kt */
        /* loaded from: classes.dex */
        public static final class DefaultImpls {
            /* JADX WARN: Multi-variable type inference failed */
            public static <E extends Element> E get(Element element, Key<E> key) {
                if (Intrinsics.areEqual(element.getKey(), key)) {
                    return element;
                }
                return null;
            }

            public static CoroutineContext minusKey(Element element, Key<?> key) {
                return Intrinsics.areEqual(element.getKey(), key) ? EmptyCoroutineContext.INSTANCE : element;
            }

            public static CoroutineContext plus(Element element, CoroutineContext coroutineContext) {
                return coroutineContext == EmptyCoroutineContext.INSTANCE ? element : (CoroutineContext) coroutineContext.fold(element, CoroutineContext$plus$1.INSTANCE);
            }
        }

        @Override // kotlin.coroutines.CoroutineContext
        <E extends Element> E get(Key<E> key);

        Key<?> getKey();
    }

    /* compiled from: CoroutineContext.kt */
    /* loaded from: classes.dex */
    public interface Key<E extends Element> {
    }

    <R> R fold(R r, Function2<? super R, ? super Element, ? extends R> function2);

    <E extends Element> E get(Key<E> key);

    CoroutineContext minusKey(Key<?> key);

    CoroutineContext plus(CoroutineContext coroutineContext);
}
