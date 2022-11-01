package androidx.room;

import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RoomDatabase.kt */
/* loaded from: classes.dex */
public final class TransactionElement implements CoroutineContext.Element {
    public static final Key Key = new Key(null);

    /* compiled from: RoomDatabase.kt */
    /* loaded from: classes.dex */
    public static final class Key implements CoroutineContext.Key<TransactionElement> {
        public Key(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return (R) function2.invoke(r, this);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        return (E) CoroutineContext.Element.DefaultImpls.get(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public CoroutineContext.Key<TransactionElement> getKey() {
        return Key;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        return Intrinsics.areEqual(Key, key) ? EmptyCoroutineContext.INSTANCE : this;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext coroutineContext) {
        return CoroutineContext.Element.DefaultImpls.plus(this, coroutineContext);
    }
}
