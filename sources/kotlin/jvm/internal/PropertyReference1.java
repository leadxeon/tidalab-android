package kotlin.jvm.internal;

import java.util.Objects;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty1;
/* loaded from: classes.dex */
public abstract class PropertyReference1 extends PropertyReference implements KProperty1 {
    public PropertyReference1(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KCallable computeReflected() {
        Objects.requireNonNull(Reflection.factory);
        return this;
    }

    @Override // kotlin.jvm.functions.Function1
    public Object invoke(Object obj) {
        return get(obj);
    }
}
