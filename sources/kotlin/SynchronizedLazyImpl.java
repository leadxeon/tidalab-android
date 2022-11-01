package kotlin;

import java.io.Serializable;
import kotlin.jvm.functions.Function0;
/* compiled from: LazyJVM.kt */
/* loaded from: classes.dex */
public final class SynchronizedLazyImpl<T> implements Lazy<T>, Serializable {
    public Function0<? extends T> initializer;
    public volatile Object _value = UNINITIALIZED_VALUE.INSTANCE;
    public final Object lock = this;

    public SynchronizedLazyImpl(Function0 function0, Object obj, int i) {
        int i2 = i & 2;
        this.initializer = function0;
    }

    @Override // kotlin.Lazy
    public T getValue() {
        T t;
        T t2 = (T) this._value;
        UNINITIALIZED_VALUE uninitialized_value = UNINITIALIZED_VALUE.INSTANCE;
        if (t2 != uninitialized_value) {
            return t2;
        }
        synchronized (this.lock) {
            t = (T) this._value;
            if (t == uninitialized_value) {
                t = (T) this.initializer.invoke();
                this._value = t;
                this.initializer = null;
            }
        }
        return t;
    }

    public String toString() {
        return this._value != UNINITIALIZED_VALUE.INSTANCE ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
