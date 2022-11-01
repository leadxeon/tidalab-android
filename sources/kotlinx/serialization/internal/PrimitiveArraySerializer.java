package kotlinx.serialization.internal;

import java.util.Iterator;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PrimitiveArrayBuilder;
/* compiled from: CollectionSerializers.kt */
/* loaded from: classes.dex */
public abstract class PrimitiveArraySerializer<Element, Array, Builder extends PrimitiveArrayBuilder<Array>> extends ListLikeSerializer<Element, Array, Builder> {
    public final SerialDescriptor descriptor;

    public PrimitiveArraySerializer(KSerializer<Element> kSerializer) {
        super(kSerializer, null);
        this.descriptor = new PrimitiveArrayDescriptor(kSerializer.getDescriptor());
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object builder() {
        return (PrimitiveArrayBuilder) toBuilder(empty());
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int builderSize(Object obj) {
        return ((PrimitiveArrayBuilder) obj).getPosition$kotlinx_serialization_core();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public void checkCapacity(Object obj, int i) {
        ((PrimitiveArrayBuilder) obj).ensureCapacity$kotlinx_serialization_core(i);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public final Iterator<Element> collectionIterator(Array array) {
        throw new IllegalStateException("This method lead to boxing and must not be used, use writeContents instead".toString());
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer, kotlinx.serialization.DeserializationStrategy
    public final Array deserialize(Decoder decoder) {
        return merge(decoder, null);
    }

    public abstract Array empty();

    @Override // kotlinx.serialization.internal.ListLikeSerializer, kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.internal.ListLikeSerializer
    public void insert(Object obj, int i, Object obj2) {
        PrimitiveArrayBuilder primitiveArrayBuilder = (PrimitiveArrayBuilder) obj;
        throw new IllegalStateException("This method lead to boxing and must not be used, use Builder.append instead".toString());
    }

    @Override // kotlinx.serialization.internal.ListLikeSerializer, kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, Array array) {
        int collectionSize = collectionSize(array);
        CompositeEncoder beginCollection = encoder.beginCollection(this.descriptor, collectionSize);
        writeContent(beginCollection, array, collectionSize);
        beginCollection.endStructure(this.descriptor);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toResult(Object obj) {
        return ((PrimitiveArrayBuilder) obj).build$kotlinx_serialization_core();
    }

    public abstract void writeContent(CompositeEncoder compositeEncoder, Array array, int i);
}
