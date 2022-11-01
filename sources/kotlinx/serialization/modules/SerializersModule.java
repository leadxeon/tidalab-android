package kotlinx.serialization.modules;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;
/* compiled from: SerializersModule.kt */
/* loaded from: classes.dex */
public abstract class SerializersModule {
    public SerializersModule(DefaultConstructorMarker defaultConstructorMarker) {
    }

    public abstract void dumpTo(SerializersModuleCollector serializersModuleCollector);

    public abstract <T> KSerializer<T> getContextual(KClass<T> kClass, List<? extends KSerializer<?>> list);

    public abstract <T> DeserializationStrategy<? extends T> getPolymorphic(KClass<? super T> kClass, String str);

    public abstract <T> SerializationStrategy<T> getPolymorphic(KClass<? super T> kClass, T t);
}
