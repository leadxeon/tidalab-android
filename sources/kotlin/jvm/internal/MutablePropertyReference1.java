package kotlin.jvm.internal;

import java.util.Objects;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.reflect.KCallable;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KProperty;
import kotlin.reflect.KProperty1;
/* loaded from: classes.dex */
public abstract class MutablePropertyReference1 extends MutablePropertyReference implements KMutableProperty1 {
    public MutablePropertyReference1(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KCallable computeReflected() {
        Objects.requireNonNull(Reflection.factory);
        return this;
    }

    @Override // kotlin.reflect.KProperty1
    public KProperty1.Getter getGetter() {
        KCallable compute = compute();
        if (compute != this) {
            return ((KMutableProperty1) ((KProperty) compute)).getGetter();
        }
        throw new KotlinReflectionNotSupportedError();
    }

    @Override // kotlin.jvm.functions.Function1
    public Object invoke(Object obj) {
        return ((MutablePropertyReference1Impl) this).getGetter().call(obj);
    }
}
