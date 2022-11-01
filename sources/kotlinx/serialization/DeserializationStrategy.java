package kotlinx.serialization;

import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
/* compiled from: KSerializer.kt */
/* loaded from: classes.dex */
public interface DeserializationStrategy<T> {
    T deserialize(Decoder decoder);

    SerialDescriptor getDescriptor();
}
