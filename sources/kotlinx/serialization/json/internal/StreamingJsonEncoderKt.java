package kotlinx.serialization.json.internal;

import java.util.Set;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.internal.UByteSerializer;
import kotlinx.serialization.internal.UIntSerializer;
import kotlinx.serialization.internal.ULongSerializer;
import kotlinx.serialization.internal.UShortSerializer;
/* compiled from: StreamingJsonEncoder.kt */
/* loaded from: classes.dex */
public final class StreamingJsonEncoderKt {
    public static final Set<SerialDescriptor> unsignedNumberDescriptors = ArraysKt___ArraysKt.toSet(new SerialDescriptor[]{UIntSerializer.descriptor, ULongSerializer.descriptor, UByteSerializer.descriptor, UShortSerializer.descriptor});

    static {
        UIntSerializer uIntSerializer = UIntSerializer.INSTANCE;
        ULongSerializer uLongSerializer = ULongSerializer.INSTANCE;
        UByteSerializer uByteSerializer = UByteSerializer.INSTANCE;
        UShortSerializer uShortSerializer = UShortSerializer.INSTANCE;
    }

    public static final boolean isUnsignedNumber(SerialDescriptor serialDescriptor) {
        return serialDescriptor.isInline() && unsignedNumberDescriptors.contains(serialDescriptor);
    }
}
