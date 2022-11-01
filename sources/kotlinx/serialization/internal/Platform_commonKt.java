package kotlinx.serialization.internal;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlinx.serialization.descriptors.SerialDescriptor;
/* compiled from: Platform.common.kt */
/* loaded from: classes.dex */
public final class Platform_commonKt {
    public static final SerialDescriptor[] EMPTY_DESCRIPTOR_ARRAY = new SerialDescriptor[0];

    public static final Set<String> cachedSerialNames(SerialDescriptor serialDescriptor) {
        if (serialDescriptor instanceof CachedNames) {
            return ((CachedNames) serialDescriptor).getSerialNames();
        }
        HashSet hashSet = new HashSet(serialDescriptor.getElementsCount());
        int i = 0;
        int elementsCount = serialDescriptor.getElementsCount();
        if (elementsCount > 0) {
            while (true) {
                int i2 = i + 1;
                hashSet.add(serialDescriptor.getElementName(i));
                if (i2 >= elementsCount) {
                    break;
                }
                i = i2;
            }
        }
        return hashSet;
    }

    public static final SerialDescriptor[] compactArray(List<? extends SerialDescriptor> list) {
        SerialDescriptor[] serialDescriptorArr = null;
        if (list == null || list.isEmpty()) {
            list = null;
        }
        if (list != null) {
            Object[] array = list.toArray(new SerialDescriptor[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
            serialDescriptorArr = (SerialDescriptor[]) array;
        }
        return serialDescriptorArr == null ? EMPTY_DESCRIPTOR_ARRAY : serialDescriptorArr;
    }

    public static final KClass<Object> kclass(KType kType) {
        KClass<Object> classifier = kType.getClassifier();
        if (classifier instanceof KClass) {
            return classifier;
        }
        throw new IllegalStateException(Intrinsics.stringPlus("Only KClass supported as classifier, got ", classifier).toString());
    }
}
