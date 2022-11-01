package kotlinx.serialization;

import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: KSerializer.kt */
/* loaded from: classes.dex */
public interface SerializationStrategy<T> {
    SerialDescriptor getDescriptor();

    void serialize(Encoder encoder, T t);
}
