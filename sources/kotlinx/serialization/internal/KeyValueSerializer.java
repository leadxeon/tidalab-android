package kotlinx.serialization.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: Tuples.kt */
/* loaded from: classes.dex */
public abstract class KeyValueSerializer<K, V, R> implements KSerializer<R> {
    public final KSerializer<K> keySerializer;
    public final KSerializer<V> valueSerializer;

    public KeyValueSerializer(KSerializer kSerializer, KSerializer kSerializer2, DefaultConstructorMarker defaultConstructorMarker) {
        this.keySerializer = kSerializer;
        this.valueSerializer = kSerializer2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.DeserializationStrategy
    public R deserialize(Decoder decoder) {
        CompositeDecoder beginStructure = decoder.beginStructure(getDescriptor());
        if (beginStructure.decodeSequentially()) {
            return (R) toResult(InputKt.decodeSerializableElement$default(beginStructure, getDescriptor(), 0, this.keySerializer, null, 8, null), InputKt.decodeSerializableElement$default(beginStructure, getDescriptor(), 1, this.valueSerializer, null, 8, null));
        }
        Object obj = TuplesKt.NULL;
        Object obj2 = TuplesKt.NULL;
        Object obj3 = obj2;
        while (true) {
            int decodeElementIndex = beginStructure.decodeElementIndex(getDescriptor());
            if (decodeElementIndex == -1) {
                beginStructure.endStructure(getDescriptor());
                Object obj4 = TuplesKt.NULL;
                Object obj5 = TuplesKt.NULL;
                if (obj2 == obj5) {
                    throw new SerializationException("Element 'key' is missing");
                } else if (obj3 != obj5) {
                    return (R) toResult(obj2, obj3);
                } else {
                    throw new SerializationException("Element 'value' is missing");
                }
            } else if (decodeElementIndex == 0) {
                obj2 = InputKt.decodeSerializableElement$default(beginStructure, getDescriptor(), 0, this.keySerializer, null, 8, null);
            } else if (decodeElementIndex == 1) {
                obj3 = InputKt.decodeSerializableElement$default(beginStructure, getDescriptor(), 1, this.valueSerializer, null, 8, null);
            } else {
                throw new SerializationException(Intrinsics.stringPlus("Invalid index: ", Integer.valueOf(decodeElementIndex)));
            }
        }
    }

    public abstract K getKey(R r);

    public abstract V getValue(R r);

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, R r) {
        CompositeEncoder beginStructure = encoder.beginStructure(getDescriptor());
        beginStructure.encodeSerializableElement(getDescriptor(), 0, this.keySerializer, getKey(r));
        beginStructure.encodeSerializableElement(getDescriptor(), 1, this.valueSerializer, getValue(r));
        beginStructure.endStructure(getDescriptor());
    }

    public abstract R toResult(K k, V v);
}
