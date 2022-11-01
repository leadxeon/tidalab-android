package kotlinx.coroutines;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.AbstractCoroutineContextKey;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.internal.DispatchedContinuation;
/* compiled from: CoroutineDispatcher.kt */
/* loaded from: classes.dex */
public abstract class CoroutineDispatcher extends AbstractCoroutineContextElement implements ContinuationInterceptor {
    public static final Key Key = new Key(null);

    /* compiled from: CoroutineDispatcher.kt */
    /* loaded from: classes.dex */
    public static final class Key extends AbstractCoroutineContextKey<ContinuationInterceptor, CoroutineDispatcher> {

        /* compiled from: CoroutineDispatcher.kt */
        /* renamed from: kotlinx.coroutines.CoroutineDispatcher$Key$1  reason: invalid class name */
        /* loaded from: classes.dex */
        public final class AnonymousClass1 extends Lambda implements Function1<CoroutineContext.Element, CoroutineDispatcher> {
            public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

            public AnonymousClass1() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public CoroutineDispatcher invoke(CoroutineContext.Element element) {
                CoroutineContext.Element element2 = element;
                if (element2 instanceof CoroutineDispatcher) {
                    return (CoroutineDispatcher) element2;
                }
                return null;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Key(DefaultConstructorMarker defaultConstructorMarker) {
            super(ContinuationInterceptor.Key.$$INSTANCE, AnonymousClass1.INSTANCE);
            int i = ContinuationInterceptor.$r8$clinit;
        }
    }

    public CoroutineDispatcher() {
        super(ContinuationInterceptor.Key.$$INSTANCE);
    }

    public abstract void dispatch(CoroutineContext coroutineContext, Runnable runnable);

    @Override // kotlin.coroutines.AbstractCoroutineContextElement, kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        if (key instanceof AbstractCoroutineContextKey) {
            AbstractCoroutineContextKey abstractCoroutineContextKey = (AbstractCoroutineContextKey) key;
            CoroutineContext.Key<?> key2 = getKey();
            if (key2 == abstractCoroutineContextKey || abstractCoroutineContextKey.topmostKey == key2) {
                E e = (E) ((CoroutineContext.Element) abstractCoroutineContextKey.safeCast.invoke(this));
                if (e instanceof CoroutineContext.Element) {
                    return e;
                }
            }
        } else if (ContinuationInterceptor.Key.$$INSTANCE == key) {
            return this;
        }
        return null;
    }

    @Override // kotlin.coroutines.ContinuationInterceptor
    public final <T> Continuation<T> interceptContinuation(Continuation<? super T> continuation) {
        return new DispatchedContinuation(this, continuation);
    }

    public boolean isDispatchNeeded(CoroutineContext coroutineContext) {
        return !(this instanceof Unconfined);
    }

    @Override // kotlin.coroutines.AbstractCoroutineContextElement, kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        if (key instanceof AbstractCoroutineContextKey) {
            AbstractCoroutineContextKey abstractCoroutineContextKey = (AbstractCoroutineContextKey) key;
            CoroutineContext.Key<?> key2 = getKey();
            if ((key2 == abstractCoroutineContextKey || abstractCoroutineContextKey.topmostKey == key2) && ((CoroutineContext.Element) abstractCoroutineContextKey.safeCast.invoke(this)) != null) {
                return EmptyCoroutineContext.INSTANCE;
            }
        } else if (ContinuationInterceptor.Key.$$INSTANCE == key) {
            return EmptyCoroutineContext.INSTANCE;
        }
        return this;
    }

    @Override // kotlin.coroutines.ContinuationInterceptor
    public void releaseInterceptedContinuation(Continuation<?> continuation) {
        ((DispatchedContinuation) continuation).release();
    }

    public String toString() {
        return getClass().getSimpleName() + '@' + InputKt.getHexAddress(this);
    }
}
