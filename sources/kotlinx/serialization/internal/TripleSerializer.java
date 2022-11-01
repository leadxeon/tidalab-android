package kotlinx.serialization.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Triple;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: Tuples.kt */
/* loaded from: classes.dex */
public final class TripleSerializer<A, B, C> implements KSerializer<Triple<? extends A, ? extends B, ? extends C>> {
    public final KSerializer<A> aSerializer;
    public final KSerializer<B> bSerializer;
    public final KSerializer<C> cSerializer;
    public final SerialDescriptor descriptor = InputKt.buildClassSerialDescriptor("kotlin.Triple", new SerialDescriptor[0], new TripleSerializer$descriptor$1(this));

    public TripleSerializer(KSerializer<A> kSerializer, KSerializer<B> kSerializer2, KSerializer<C> kSerializer3) {
        this.aSerializer = kSerializer;
        this.bSerializer = kSerializer2;
        this.cSerializer = kSerializer3;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        CompositeDecoder beginStructure = decoder.beginStructure(this.descriptor);
        if (beginStructure.decodeSequentially()) {
            Object decodeSerializableElement$default = InputKt.decodeSerializableElement$default(beginStructure, this.descriptor, 0, this.aSerializer, null, 8, null);
            Object decodeSerializableElement$default2 = InputKt.decodeSerializableElement$default(beginStructure, this.descriptor, 1, this.bSerializer, null, 8, null);
            Object decodeSerializableElement$default3 = InputKt.decodeSerializableElement$default(beginStructure, this.descriptor, 2, this.cSerializer, null, 8, null);
            beginStructure.endStructure(this.descriptor);
            return new Triple(decodeSerializableElement$default, decodeSerializableElement$default2, decodeSerializableElement$default3);
        }
        Object obj = TuplesKt.NULL;
        Object obj2 = TuplesKt.NULL;
        Object obj3 = obj2;
        Object obj4 = obj3;
        while (true) {
            int decodeElementIndex = beginStructure.decodeElementIndex(this.descriptor);
            if (decodeElementIndex == -1) {
                beginStructure.endStructure(this.descriptor);
                Object obj5 = TuplesKt.NULL;
                Object obj6 = TuplesKt.NULL;
                if (obj2 == obj6) {
                    throw new SerializationException("Element 'first' is missing");
                } else if (obj3 == obj6) {
                    throw new SerializationException("Element 'second' is missing");
                } else if (obj4 != obj6) {
                    return new Triple(obj2, obj3, obj4);
                } else {
                    throw new SerializationException("Element 'third' is missing");
                }
            } else if (decodeElementIndex == 0) {
                obj2 = InputKt.decodeSerializableElement$default(beginStructure, this.descriptor, 0, this.aSerializer, null, 8, null);
            } else if (decodeElementIndex == 1) {
                obj3 = InputKt.decodeSerializableElement$default(beginStructure, this.descriptor, 1, this.bSerializer, null, 8, null);
            } else if (decodeElementIndex == 2) {
                obj4 = InputKt.decodeSerializableElement$default(beginStructure, this.descriptor, 2, this.cSerializer, null, 8, null);
            } else {
                throw new SerializationException(Intrinsics.stringPlus("Unexpected index ", Integer.valueOf(decodeElementIndex)));
            }
        }
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        Triple triple = (Triple) obj;
        CompositeEncoder beginStructure = encoder.beginStructure(this.descriptor);
        beginStructure.encodeSerializableElement(this.descriptor, 0, this.aSerializer, triple.first);
        beginStructure.encodeSerializableElement(this.descriptor, 1, this.bSerializer, triple.second);
        beginStructure.encodeSerializableElement(this.descriptor, 2, this.cSerializer, triple.third);
        beginStructure.endStructure(this.descriptor);
    }
}
