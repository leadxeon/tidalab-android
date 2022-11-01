package kotlinx.serialization.internal;

import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
/* compiled from: CollectionSerializers.kt */
/* loaded from: classes.dex */
public abstract class AbstractCollectionSerializer<Element, Collection, Builder> implements KSerializer<Collection> {
    public AbstractCollectionSerializer(DefaultConstructorMarker defaultConstructorMarker) {
    }

    public abstract Builder builder();

    public abstract int builderSize(Builder builder);

    public abstract void checkCapacity(Builder builder, int i);

    public abstract Iterator<Element> collectionIterator(Collection collection);

    public abstract int collectionSize(Collection collection);

    @Override // kotlinx.serialization.DeserializationStrategy
    public Collection deserialize(Decoder decoder) {
        return merge(decoder, null);
    }

    public final Collection merge(Decoder decoder, Collection collection) {
        Builder builder = builder();
        int builderSize = builderSize(builder);
        CompositeDecoder beginStructure = decoder.beginStructure(getDescriptor());
        if (beginStructure.decodeSequentially()) {
            int decodeCollectionSize = beginStructure.decodeCollectionSize(getDescriptor());
            checkCapacity(builder, decodeCollectionSize);
            readAll(beginStructure, builder, builderSize, decodeCollectionSize);
        } else {
            while (true) {
                int decodeElementIndex = beginStructure.decodeElementIndex(getDescriptor());
                if (decodeElementIndex == -1) {
                    break;
                }
                readElement(beginStructure, decodeElementIndex + builderSize, builder, true);
            }
        }
        beginStructure.endStructure(getDescriptor());
        return toResult(builder);
    }

    public abstract void readAll(CompositeDecoder compositeDecoder, Builder builder, int i, int i2);

    public abstract void readElement(CompositeDecoder compositeDecoder, int i, Builder builder, boolean z);

    public abstract Builder toBuilder(Collection collection);

    public abstract Collection toResult(Builder builder);
}
