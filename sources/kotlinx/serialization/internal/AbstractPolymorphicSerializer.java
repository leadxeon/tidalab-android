package kotlinx.serialization.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.PolymorphicSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: AbstractPolymorphicSerializer.kt */
/* loaded from: classes.dex */
public abstract class AbstractPolymorphicSerializer<T> implements KSerializer<T> {
    @Override // kotlinx.serialization.DeserializationStrategy
    public final T deserialize(Decoder decoder) {
        PolymorphicSerializer polymorphicSerializer = (PolymorphicSerializer) this;
        SerialDescriptor descriptor = polymorphicSerializer.getDescriptor();
        CompositeDecoder beginStructure = decoder.beginStructure(descriptor);
        try {
            if (beginStructure.decodeSequentially()) {
                PolymorphicSerializer polymorphicSerializer2 = (PolymorphicSerializer) this;
                T t = (T) InputKt.decodeSerializableElement$default(beginStructure, polymorphicSerializer2.getDescriptor(), 1, InputKt.findPolymorphicSerializer(this, beginStructure, beginStructure.decodeStringElement(polymorphicSerializer2.getDescriptor(), 0)), null, 8, null);
                beginStructure.endStructure(descriptor);
                return t;
            }
            T t2 = null;
            String str = null;
            while (true) {
                int decodeElementIndex = beginStructure.decodeElementIndex(polymorphicSerializer.getDescriptor());
                if (decodeElementIndex != -1) {
                    if (decodeElementIndex == 0) {
                        str = beginStructure.decodeStringElement(polymorphicSerializer.getDescriptor(), decodeElementIndex);
                    } else if (decodeElementIndex != 1) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Invalid index in polymorphic deserialization of ");
                        String str2 = str;
                        if (str2 == null) {
                            str2 = "unknown class";
                        }
                        sb.append(str2);
                        sb.append("\n Expected 0, 1 or DECODE_DONE(-1), but found ");
                        sb.append(decodeElementIndex);
                        throw new SerializationException(sb.toString());
                    } else if (str != null) {
                        t2 = (T) InputKt.decodeSerializableElement$default(beginStructure, polymorphicSerializer.getDescriptor(), decodeElementIndex, InputKt.findPolymorphicSerializer(this, beginStructure, str), null, 8, null);
                    } else {
                        throw new IllegalArgumentException("Cannot read polymorphic value before its type token".toString());
                    }
                } else if (t2 != null) {
                    beginStructure.endStructure(descriptor);
                    return t2;
                } else {
                    throw new IllegalArgumentException(Intrinsics.stringPlus("Polymorphic value has not been read for class ", str).toString());
                }
            }
        } finally {
            throw th;
        }
    }

    public abstract KClass<T> getBaseClass();

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, T t) {
        SerializationStrategy<? super T> findPolymorphicSerializer = InputKt.findPolymorphicSerializer(this, encoder, t);
        SerialDescriptor serialDescriptor = ((PolymorphicSerializer) this).descriptor;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
        try {
            beginStructure.encodeStringElement(((PolymorphicSerializer) this).descriptor, 0, findPolymorphicSerializer.getDescriptor().getSerialName());
            beginStructure.encodeSerializableElement(((PolymorphicSerializer) this).descriptor, 1, findPolymorphicSerializer, t);
            beginStructure.endStructure(serialDescriptor);
        } finally {
            throw th;
        }
    }
}
