package kotlinx.serialization.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import kotlin.jvm.internal.ArrayIterator;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
/* compiled from: CollectionSerializers.kt */
/* loaded from: classes.dex */
public final class ReferenceArraySerializer<ElementKlass, Element extends ElementKlass> extends ListLikeSerializer<Element, Element[], ArrayList<Element>> {
    public final SerialDescriptor descriptor;
    public final KClass<ElementKlass> kClass;

    public ReferenceArraySerializer(KClass<ElementKlass> kClass, KSerializer<Element> kSerializer) {
        super(kSerializer, null);
        this.kClass = kClass;
        this.descriptor = new ArrayClassDesc(kSerializer.getDescriptor());
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object builder() {
        return new ArrayList();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int builderSize(Object obj) {
        return ((ArrayList) obj).size();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public void checkCapacity(Object obj, int i) {
        ((ArrayList) obj).ensureCapacity(i);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Iterator collectionIterator(Object obj) {
        return new ArrayIterator((Object[]) obj);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int collectionSize(Object obj) {
        return ((Object[]) obj).length;
    }

    @Override // kotlinx.serialization.internal.ListLikeSerializer, kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.internal.ListLikeSerializer
    public void insert(Object obj, int i, Object obj2) {
        ((ArrayList) obj).add(i, obj2);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toBuilder(Object obj) {
        return new ArrayList(Arrays.asList((Object[]) obj));
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toResult(Object obj) {
        ArrayList arrayList = (ArrayList) obj;
        Object newInstance = Array.newInstance(InputKt.getJavaClass(this.kClass), arrayList.size());
        Objects.requireNonNull(newInstance, "null cannot be cast to non-null type kotlin.Array<E of kotlinx.serialization.internal.PlatformKt.toNativeArrayImpl>");
        return arrayList.toArray((Object[]) newInstance);
    }
}
