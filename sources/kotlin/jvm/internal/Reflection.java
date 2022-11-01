package kotlin.jvm.internal;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
/* loaded from: classes.dex */
public class Reflection {
    public static final KClass[] EMPTY_K_CLASS_ARRAY;
    public static final ReflectionFactory factory;

    static {
        ReflectionFactory reflectionFactory = null;
        try {
            reflectionFactory = (ReflectionFactory) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (reflectionFactory == null) {
            reflectionFactory = new ReflectionFactory();
        }
        factory = reflectionFactory;
        EMPTY_K_CLASS_ARRAY = new KClass[0];
    }

    public static KClass getOrCreateKotlinClass(Class cls) {
        Objects.requireNonNull(factory);
        return new ClassReference(cls);
    }

    public static KType typeOf(Class cls) {
        ReflectionFactory reflectionFactory = factory;
        KClass orCreateKotlinClass = getOrCreateKotlinClass(cls);
        List emptyList = Collections.emptyList();
        Objects.requireNonNull(reflectionFactory);
        return new TypeReference(orCreateKotlinClass, emptyList, false);
    }
}
