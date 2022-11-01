package kotlinx.serialization.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: CollectionSerializers.kt */
/* loaded from: classes.dex */
public abstract class ListLikeSerializer<Element, Collection, Builder> extends AbstractCollectionSerializer<Element, Collection, Builder> {
    public final KSerializer<Element> elementSerializer;

    public ListLikeSerializer(KSerializer kSerializer, DefaultConstructorMarker defaultConstructorMarker) {
        super(null);
        this.elementSerializer = kSerializer;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public abstract SerialDescriptor getDescriptor();

    public abstract void insert(Builder builder, int i, Element element);

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public final void readAll(CompositeDecoder compositeDecoder, Builder builder, int i, int i2) {
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException("Size must be known in advance when using READ_ALL".toString());
        } else if (i2 > 0) {
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                readElement(compositeDecoder, i3 + i, builder, false);
                if (i4 < i2) {
                    i3 = i4;
                } else {
                    return;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public void readElement(CompositeDecoder compositeDecoder, int i, Builder builder, boolean z) {
        insert(builder, i, InputKt.decodeSerializableElement$default(compositeDecoder, getDescriptor(), i, this.elementSerializer, null, 8, null));
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Collection collection) {
        int collectionSize = collectionSize(collection);
        CompositeEncoder beginCollection = encoder.beginCollection(getDescriptor(), collectionSize);
        Iterator<Element> collectionIterator = collectionIterator(collection);
        if (collectionSize > 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                beginCollection.encodeSerializableElement(getDescriptor(), i, this.elementSerializer, collectionIterator.next());
                if (i2 >= collectionSize) {
                    break;
                }
                i = i2;
            }
        }
        beginCollection.endStructure(getDescriptor());
    }
}
