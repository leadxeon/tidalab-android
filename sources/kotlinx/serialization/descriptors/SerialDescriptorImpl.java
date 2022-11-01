package kotlinx.serialization.descriptors;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import kotlin.Lazy;
import kotlin.Pair;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.ArraysKt___ArraysKt$withIndex$1;
import kotlin.collections.IndexedValue;
import kotlin.collections.IndexingIterable;
import kotlin.collections.IndexingIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.serialization.internal.CachedNames;
import kotlinx.serialization.internal.Platform_commonKt;
/* compiled from: SerialDescriptors.kt */
/* loaded from: classes.dex */
public final class SerialDescriptorImpl implements SerialDescriptor, CachedNames {
    public final Lazy _hashCode$delegate;
    public final List<Annotation>[] elementAnnotations;
    public final SerialDescriptor[] elementDescriptors;
    public final String[] elementNames;
    public final int elementsCount;
    public final SerialKind kind;
    public final Map<String, Integer> name2Index;
    public final String serialName;
    public final Set<String> serialNames;
    public final SerialDescriptor[] typeParametersDescriptors;

    public SerialDescriptorImpl(String str, SerialKind serialKind, int i, List<? extends SerialDescriptor> list, ClassSerialDescriptorBuilder classSerialDescriptorBuilder) {
        this.serialName = str;
        this.kind = serialKind;
        this.elementsCount = i;
        this.serialNames = ArraysKt___ArraysKt.toHashSet(classSerialDescriptorBuilder.elementNames);
        int i2 = 0;
        Object[] array = classSerialDescriptorBuilder.elementNames.toArray(new String[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
        this.elementNames = (String[]) array;
        this.elementDescriptors = Platform_commonKt.compactArray(classSerialDescriptorBuilder.elementDescriptors);
        Object[] array2 = classSerialDescriptorBuilder.elementAnnotations.toArray(new List[0]);
        Objects.requireNonNull(array2, "null cannot be cast to non-null type kotlin.Array<T>");
        this.elementAnnotations = (List[]) array2;
        List<Boolean> list2 = classSerialDescriptorBuilder.elementOptionality;
        boolean[] zArr = new boolean[list2.size()];
        for (Boolean bool : list2) {
            i2++;
            zArr[i2] = bool.booleanValue();
        }
        IndexingIterable indexingIterable = new IndexingIterable(new ArraysKt___ArraysKt$withIndex$1(this.elementNames));
        ArrayList arrayList = new ArrayList(InputKt.collectionSizeOrDefault(indexingIterable, 10));
        Iterator it = indexingIterable.iterator();
        while (true) {
            IndexingIterator indexingIterator = (IndexingIterator) it;
            if (indexingIterator.hasNext()) {
                IndexedValue indexedValue = (IndexedValue) indexingIterator.next();
                arrayList.add(new Pair(indexedValue.value, Integer.valueOf(indexedValue.index)));
            } else {
                this.name2Index = ArraysKt___ArraysKt.toMap(arrayList);
                this.typeParametersDescriptors = Platform_commonKt.compactArray(list);
                this._hashCode$delegate = InputKt.lazy(new SerialDescriptorImpl$_hashCode$2(this));
                return;
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SerialDescriptorImpl) {
            SerialDescriptor serialDescriptor = (SerialDescriptor) obj;
            if (Intrinsics.areEqual(getSerialName(), serialDescriptor.getSerialName()) && Arrays.equals(this.typeParametersDescriptors, ((SerialDescriptorImpl) obj).typeParametersDescriptors) && getElementsCount() == serialDescriptor.getElementsCount()) {
                int elementsCount = getElementsCount();
                if (elementsCount > 0) {
                    int i = 0;
                    while (true) {
                        int i2 = i + 1;
                        if (!Intrinsics.areEqual(getElementDescriptor(i).getSerialName(), serialDescriptor.getElementDescriptor(i).getSerialName()) || !Intrinsics.areEqual(getElementDescriptor(i).getKind(), serialDescriptor.getElementDescriptor(i).getKind())) {
                            break;
                        } else if (i2 >= elementsCount) {
                            return true;
                        } else {
                            i = i2;
                        }
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public List<Annotation> getElementAnnotations(int i) {
        return this.elementAnnotations[i];
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public SerialDescriptor getElementDescriptor(int i) {
        return this.elementDescriptors[i];
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public int getElementIndex(String str) {
        Integer num = this.name2Index.get(str);
        if (num == null) {
            return -3;
        }
        return num.intValue();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public String getElementName(int i) {
        return this.elementNames[i];
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public int getElementsCount() {
        return this.elementsCount;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public SerialKind getKind() {
        return this.kind;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public String getSerialName() {
        return this.serialName;
    }

    @Override // kotlinx.serialization.internal.CachedNames
    public Set<String> getSerialNames() {
        return this.serialNames;
    }

    public int hashCode() {
        return ((Number) this._hashCode$delegate.getValue()).intValue();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public boolean isInline() {
        return false;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public boolean isNullable() {
        return false;
    }

    public String toString() {
        return ArraysKt___ArraysKt.joinToString$default(RangesKt___RangesKt.until(0, this.elementsCount), ", ", Intrinsics.stringPlus(this.serialName, "("), ")", 0, null, new SerialDescriptorImpl$toString$1(this), 24);
    }
}
