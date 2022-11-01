package kotlinx.serialization.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: NullableSerializer.kt */
/* loaded from: classes.dex */
public final class NullableSerializer<T> implements KSerializer<T> {
    public final SerialDescriptor descriptor;
    public final KSerializer<T> serializer;

    public NullableSerializer(KSerializer<T> kSerializer) {
        this.serializer = kSerializer;
        this.descriptor = new SerialDescriptorForNullable(kSerializer.getDescriptor());
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public T deserialize(Decoder decoder) {
        return decoder.decodeNotNullMark() ? (T) decoder.decodeSerializableValue(this.serializer) : (T) decoder.decodeNull();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(NullableSerializer.class), Reflection.getOrCreateKotlinClass(obj.getClass())) && Intrinsics.areEqual(this.serializer, ((NullableSerializer) obj).serializer);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    public int hashCode() {
        return this.serializer.hashCode();
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, T t) {
        if (t != null) {
            encoder.encodeNotNullMark();
            encoder.encodeSerializableValue(this.serializer, t);
            return;
        }
        encoder.encodeNull();
    }
}
