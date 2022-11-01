package kotlinx.serialization.json;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
/* compiled from: JsonElement.kt */
@Serializable(with = JsonArraySerializer.class)
/* loaded from: classes.dex */
public final class JsonArray extends JsonElement implements List<JsonElement>, KMappedMarker {
    public static final Companion Companion = new Companion(null);
    public final List<JsonElement> content;

    /* compiled from: JsonElement.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion() {
        }

        public final KSerializer<JsonArray> serializer() {
            return JsonArraySerializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public JsonArray(List<? extends JsonElement> list) {
        super(null);
        this.content = list;
    }

    @Override // java.util.List
    public void add(int i, JsonElement jsonElement) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends JsonElement> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends JsonElement> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        if (!(obj instanceof JsonElement)) {
            return false;
        }
        return this.content.contains((JsonElement) obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<? extends Object> collection) {
        return this.content.containsAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        return Intrinsics.areEqual(this.content, obj);
    }

    @Override // java.util.List
    public JsonElement get(int i) {
        return this.content.get(i);
    }

    @Override // java.util.List
    /* renamed from: get  reason: avoid collision after fix types in other method */
    public JsonElement get2(int i) {
        return this.content.get(i);
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        return this.content.hashCode();
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof JsonElement)) {
            return -1;
        }
        return this.content.indexOf((JsonElement) obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.content.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<JsonElement> iterator() {
        return this.content.iterator();
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof JsonElement)) {
            return -1;
        }
        return this.content.lastIndexOf((JsonElement) obj);
    }

    @Override // java.util.List
    public ListIterator<JsonElement> listIterator() {
        return this.content.listIterator();
    }

    @Override // java.util.List
    public ListIterator<JsonElement> listIterator(int i) {
        return this.content.listIterator(i);
    }

    @Override // java.util.List
    public /* bridge */ /* synthetic */ JsonElement remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public void replaceAll(UnaryOperator<JsonElement> unaryOperator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public JsonElement set(int i, JsonElement jsonElement) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return this.content.size();
    }

    @Override // java.util.List
    public void sort(Comparator<? super JsonElement> comparator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public List<JsonElement> subList(int i, int i2) {
        return this.content.subList(i, i2);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) CollectionToArray.toArray(this, tArr);
    }

    public String toString() {
        return ArraysKt___ArraysKt.joinToString$default(this.content, ",", "[", "]", 0, null, null, 56);
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(Object obj) {
        JsonElement jsonElement = (JsonElement) obj;
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
