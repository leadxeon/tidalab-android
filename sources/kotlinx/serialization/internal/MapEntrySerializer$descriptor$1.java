package kotlinx.serialization.internal;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
/* compiled from: Tuples.kt */
/* loaded from: classes.dex */
public final class MapEntrySerializer$descriptor$1 extends Lambda implements Function1<ClassSerialDescriptorBuilder, Unit> {
    public final /* synthetic */ KSerializer<K> $keySerializer;
    public final /* synthetic */ KSerializer<V> $valueSerializer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapEntrySerializer$descriptor$1(KSerializer<K> kSerializer, KSerializer<V> kSerializer2) {
        super(1);
        this.$keySerializer = kSerializer;
        this.$valueSerializer = kSerializer2;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(ClassSerialDescriptorBuilder classSerialDescriptorBuilder) {
        ClassSerialDescriptorBuilder classSerialDescriptorBuilder2 = classSerialDescriptorBuilder;
        ClassSerialDescriptorBuilder.element$default(classSerialDescriptorBuilder2, "key", this.$keySerializer.getDescriptor(), null, false, 12);
        ClassSerialDescriptorBuilder.element$default(classSerialDescriptorBuilder2, "value", this.$valueSerializer.getDescriptor(), null, false, 12);
        return Unit.INSTANCE;
    }
}
