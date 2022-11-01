package kotlin.jvm.internal;

import java.util.Objects;
import kotlin.reflect.KCallable;
import kotlin.reflect.KMutableProperty0;
/* loaded from: classes.dex */
public class MutablePropertyReference0Impl extends MutablePropertyReference implements KMutableProperty0 {
    public MutablePropertyReference0Impl(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KCallable computeReflected() {
        Objects.requireNonNull(Reflection.factory);
        return this;
    }

    @Override // kotlin.jvm.functions.Function0
    public Object invoke() {
        return get();
    }
}
