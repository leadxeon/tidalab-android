package kotlinx.serialization.modules;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.List;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;
/* compiled from: SerializersModule.kt */
/* loaded from: classes.dex */
public final class SerialModuleImpl extends SerializersModule {
    public SerialModuleImpl(Map<KClass<?>, ?> map, Map<KClass<?>, ? extends Map<KClass<?>, ? extends KSerializer<?>>> map2, Map<KClass<?>, ? extends Map<String, ? extends KSerializer<?>>> map3, Map<KClass<?>, ? extends Function1<? super String, ? extends DeserializationStrategy<?>>> map4) {
        super(null);
    }

    @Override // kotlinx.serialization.modules.SerializersModule
    public void dumpTo(SerializersModuleCollector serializersModuleCollector) {
    }

    @Override // kotlinx.serialization.modules.SerializersModule
    public <T> KSerializer<T> getContextual(KClass<T> kClass, List<? extends KSerializer<?>> list) {
        return null;
    }

    @Override // kotlinx.serialization.modules.SerializersModule
    public <T> DeserializationStrategy<? extends T> getPolymorphic(KClass<? super T> kClass, String str) {
        TypeIntrinsics.isFunctionOfArity(null, 1);
        return null;
    }

    @Override // kotlinx.serialization.modules.SerializersModule
    public <T> SerializationStrategy<T> getPolymorphic(KClass<? super T> kClass, T t) {
        InputKt.getJavaObjectType(kClass).isInstance(t);
        return null;
    }
}
