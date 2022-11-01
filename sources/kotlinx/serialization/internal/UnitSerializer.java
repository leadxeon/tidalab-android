package kotlinx.serialization.internal;

import kotlin.Unit;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: Primitives.kt */
/* loaded from: classes.dex */
public final class UnitSerializer implements KSerializer<Unit> {
    public static final UnitSerializer INSTANCE = new UnitSerializer();
    public final /* synthetic */ ObjectSerializer<Unit> $$delegate_0 = new ObjectSerializer<>("kotlin.Unit", Unit.INSTANCE);

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        ObjectSerializer<Unit> objectSerializer = this.$$delegate_0;
        decoder.beginStructure(objectSerializer.descriptor).endStructure(objectSerializer.descriptor);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.$$delegate_0.descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        Unit unit = (Unit) obj;
        ObjectSerializer<Unit> objectSerializer = this.$$delegate_0;
        encoder.beginStructure(objectSerializer.descriptor).endStructure(objectSerializer.descriptor);
    }
}
