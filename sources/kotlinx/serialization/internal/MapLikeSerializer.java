package kotlinx.serialization.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Iterator;
import java.util.Map;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: CollectionSerializers.kt */
/* loaded from: classes.dex */
public abstract class MapLikeSerializer<Key, Value, Collection, Builder extends Map<Key, Value>> extends AbstractCollectionSerializer<Map.Entry<? extends Key, ? extends Value>, Collection, Builder> {
    public final KSerializer<Key> keySerializer;
    public final KSerializer<Value> valueSerializer;

    public MapLikeSerializer(KSerializer kSerializer, KSerializer kSerializer2, DefaultConstructorMarker defaultConstructorMarker) {
        super(null);
        this.keySerializer = kSerializer;
        this.valueSerializer = kSerializer2;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public abstract SerialDescriptor getDescriptor();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public void readAll(CompositeDecoder compositeDecoder, Object obj, int i, int i2) {
        Map map = (Map) obj;
        if (i2 >= 0) {
            int i3 = 2;
            IntRange until = RangesKt___RangesKt.until(0, i2 * 2);
            int i4 = until.first;
            int i5 = until.last;
            if (until.step <= 0) {
                i3 = -2;
            }
            IntProgression intProgression = new IntProgression(i4, i5, i3);
            int i6 = intProgression.last;
            int i7 = intProgression.step;
            if ((i7 > 0 && i4 <= i6) || (i7 < 0 && i6 <= i4)) {
                while (true) {
                    i4 += i7;
                    readElement(compositeDecoder, i + i4, (int) map, false);
                    if (i4 == i6) {
                        return;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Size must be known in advance when using READ_ALL".toString());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* bridge */ /* synthetic */ void readElement(CompositeDecoder compositeDecoder, int i, Object obj, boolean z) {
        readElement(compositeDecoder, i, (int) ((Map) obj), z);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Collection collection) {
        CompositeEncoder beginCollection = encoder.beginCollection(getDescriptor(), collectionSize(collection));
        Iterator<Map.Entry<? extends Key, ? extends Value>> collectionIterator = collectionIterator(collection);
        int i = 0;
        while (collectionIterator.hasNext()) {
            Map.Entry<? extends Key, ? extends Value> next = collectionIterator.next();
            Object key = next.getKey();
            Object value = next.getValue();
            int i2 = i + 1;
            beginCollection.encodeSerializableElement(getDescriptor(), i, this.keySerializer, key);
            i = i2 + 1;
            beginCollection.encodeSerializableElement(getDescriptor(), i2, this.valueSerializer, value);
        }
        beginCollection.endStructure(getDescriptor());
    }

    public final void readElement(CompositeDecoder compositeDecoder, int i, Builder builder, boolean z) {
        int i2;
        Object obj;
        Object decodeSerializableElement$default = InputKt.decodeSerializableElement$default(compositeDecoder, getDescriptor(), i, this.keySerializer, null, 8, null);
        boolean z2 = true;
        if (z) {
            i2 = compositeDecoder.decodeElementIndex(getDescriptor());
            if (i2 != i + 1) {
                z2 = false;
            }
            if (!z2) {
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline5("Value must follow key in a map, index for key: ", i, ", returned index for value: ", i2).toString());
            }
        } else {
            i2 = i + 1;
        }
        if (!builder.containsKey(decodeSerializableElement$default) || (this.valueSerializer.getDescriptor().getKind() instanceof PrimitiveKind)) {
            obj = InputKt.decodeSerializableElement$default(compositeDecoder, getDescriptor(), i2, this.valueSerializer, null, 8, null);
        } else {
            obj = compositeDecoder.decodeSerializableElement(getDescriptor(), i2, this.valueSerializer, ArraysKt___ArraysKt.getValue(builder, decodeSerializableElement$default));
        }
        builder.put(decodeSerializableElement$default, obj);
    }
}
