package kotlinx.serialization.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt$buildSerialDescriptor$1;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: ObjectSerializer.kt */
/* loaded from: classes.dex */
public final class ObjectSerializer<T> implements KSerializer<T> {
    public final SerialDescriptor descriptor;
    public final T objectInstance;

    public ObjectSerializer(String str, T t) {
        SerialDescriptor buildSerialDescriptor;
        this.objectInstance = t;
        buildSerialDescriptor = InputKt.buildSerialDescriptor(str, StructureKind.OBJECT.INSTANCE, new SerialDescriptor[0], (r4 & 8) != 0 ? SerialDescriptorsKt$buildSerialDescriptor$1.INSTANCE : null);
        this.descriptor = buildSerialDescriptor;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public T deserialize(Decoder decoder) {
        decoder.beginStructure(this.descriptor).endStructure(this.descriptor);
        return this.objectInstance;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, T t) {
        encoder.beginStructure(this.descriptor).endStructure(this.descriptor);
    }
}
