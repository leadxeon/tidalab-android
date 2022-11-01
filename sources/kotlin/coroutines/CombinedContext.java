package kotlin.coroutines;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.Serializable;
import java.util.Objects;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
/* compiled from: CoroutineContextImpl.kt */
/* loaded from: classes.dex */
public final class CombinedContext implements CoroutineContext, Serializable {
    public final CoroutineContext.Element element;
    public final CoroutineContext left;

    public CombinedContext(CoroutineContext coroutineContext, CoroutineContext.Element element) {
        this.left = coroutineContext;
        this.element = element;
    }

    public boolean equals(Object obj) {
        boolean z;
        if (this != obj) {
            if (!(obj instanceof CombinedContext)) {
                return false;
            }
            CombinedContext combinedContext = (CombinedContext) obj;
            if (combinedContext.size() != size()) {
                return false;
            }
            Objects.requireNonNull(combinedContext);
            CombinedContext combinedContext2 = this;
            while (true) {
                CoroutineContext.Element element = combinedContext2.element;
                if (Intrinsics.areEqual(combinedContext.get(element.getKey()), element)) {
                    CoroutineContext coroutineContext = combinedContext2.left;
                    if (!(coroutineContext instanceof CombinedContext)) {
                        Objects.requireNonNull(coroutineContext, "null cannot be cast to non-null type kotlin.coroutines.CoroutineContext.Element");
                        CoroutineContext.Element element2 = (CoroutineContext.Element) coroutineContext;
                        z = Intrinsics.areEqual(combinedContext.get(element2.getKey()), element2);
                        break;
                    }
                    combinedContext2 = (CombinedContext) coroutineContext;
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return (R) function2.invoke((Object) this.left.fold(r, function2), this.element);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        CombinedContext combinedContext = this;
        while (true) {
            E e = (E) combinedContext.element.get(key);
            if (e != null) {
                return e;
            }
            CoroutineContext coroutineContext = combinedContext.left;
            if (!(coroutineContext instanceof CombinedContext)) {
                return (E) coroutineContext.get(key);
            }
            combinedContext = (CombinedContext) coroutineContext;
        }
    }

    public int hashCode() {
        return this.element.hashCode() + this.left.hashCode();
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        if (this.element.get(key) != null) {
            return this.left;
        }
        CoroutineContext minusKey = this.left.minusKey(key);
        return minusKey == this.left ? this : minusKey == EmptyCoroutineContext.INSTANCE ? this.element : new CombinedContext(minusKey, this.element);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext coroutineContext) {
        return coroutineContext == EmptyCoroutineContext.INSTANCE ? this : (CoroutineContext) coroutineContext.fold(this, CoroutineContext$plus$1.INSTANCE);
    }

    public final int size() {
        int i = 2;
        CombinedContext combinedContext = this;
        while (true) {
            CoroutineContext coroutineContext = combinedContext.left;
            if (!(coroutineContext instanceof CombinedContext)) {
                coroutineContext = null;
            }
            combinedContext = (CombinedContext) coroutineContext;
            if (combinedContext == null) {
                return i;
            }
            i++;
        }
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("[");
        CombinedContext$toString$1 combinedContext$toString$1 = CombinedContext$toString$1.INSTANCE;
        return GeneratedOutlineSupport.outline11(outline13, combinedContext$toString$1.invoke(this.left.fold(HttpUrl.FRAGMENT_ENCODE_SET, combinedContext$toString$1), this.element), "]");
    }
}
