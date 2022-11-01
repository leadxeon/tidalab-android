package kotlinx.serialization.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.EmptyList;
import kotlin.collections.EmptyMap;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
/* compiled from: PluginGeneratedSerialDescriptor.kt */
/* loaded from: classes.dex */
public class PluginGeneratedSerialDescriptor implements SerialDescriptor, CachedNames {
    public final Lazy _hashCode$delegate;
    public int added = -1;
    public final Lazy childSerializers$delegate;
    public final int elementsCount;
    public final boolean[] elementsOptionality;
    public final GeneratedSerializer<?> generatedSerializer;
    public Map<String, Integer> indices;
    public final String[] names;
    public final List<Annotation>[] propertiesAnnotations;
    public final String serialName;
    public final Lazy typeParameterDescriptors$delegate;

    public PluginGeneratedSerialDescriptor(String str, GeneratedSerializer<?> generatedSerializer, int i) {
        this.serialName = str;
        this.generatedSerializer = generatedSerializer;
        this.elementsCount = i;
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            strArr[i2] = "[UNINITIALIZED]";
        }
        this.names = strArr;
        int i3 = this.elementsCount;
        this.propertiesAnnotations = new List[i3];
        this.elementsOptionality = new boolean[i3];
        this.indices = EmptyMap.INSTANCE;
        this.childSerializers$delegate = InputKt.lazy(new PluginGeneratedSerialDescriptor$childSerializers$2(this));
        this.typeParameterDescriptors$delegate = InputKt.lazy(new PluginGeneratedSerialDescriptor$typeParameterDescriptors$2(this));
        this._hashCode$delegate = InputKt.lazy(new PluginGeneratedSerialDescriptor$_hashCode$2(this));
    }

    public final void addElement(String str, boolean z) {
        String[] strArr = this.names;
        int i = this.added + 1;
        this.added = i;
        strArr[i] = str;
        this.elementsOptionality[i] = z;
        this.propertiesAnnotations[i] = null;
        if (i == this.elementsCount - 1) {
            HashMap hashMap = new HashMap();
            int length = this.names.length - 1;
            if (length >= 0) {
                int i2 = 0;
                while (true) {
                    int i3 = i2 + 1;
                    hashMap.put(this.names[i2], Integer.valueOf(i2));
                    if (i3 > length) {
                        break;
                    }
                    i2 = i3;
                }
            }
            this.indices = hashMap;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PluginGeneratedSerialDescriptor) {
            SerialDescriptor serialDescriptor = (SerialDescriptor) obj;
            if (Intrinsics.areEqual(getSerialName(), serialDescriptor.getSerialName()) && Arrays.equals(getTypeParameterDescriptors$kotlinx_serialization_core(), ((PluginGeneratedSerialDescriptor) obj).getTypeParameterDescriptors$kotlinx_serialization_core()) && getElementsCount() == serialDescriptor.getElementsCount()) {
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
        List<Annotation> list = this.propertiesAnnotations[i];
        return list == null ? EmptyList.INSTANCE : list;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public SerialDescriptor getElementDescriptor(int i) {
        return ((KSerializer[]) this.childSerializers$delegate.getValue())[i].getDescriptor();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public int getElementIndex(String str) {
        Integer num = this.indices.get(str);
        if (num == null) {
            return -3;
        }
        return num.intValue();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public String getElementName(int i) {
        return this.names[i];
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public final int getElementsCount() {
        return this.elementsCount;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public SerialKind getKind() {
        return StructureKind.CLASS.INSTANCE;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public String getSerialName() {
        return this.serialName;
    }

    @Override // kotlinx.serialization.internal.CachedNames
    public Set<String> getSerialNames() {
        return this.indices.keySet();
    }

    public final SerialDescriptor[] getTypeParameterDescriptors$kotlinx_serialization_core() {
        return (SerialDescriptor[]) this.typeParameterDescriptors$delegate.getValue();
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
        return ArraysKt___ArraysKt.joinToString$default(RangesKt___RangesKt.until(0, this.elementsCount), ", ", Intrinsics.stringPlus(this.serialName, "("), ")", 0, null, new PluginGeneratedSerialDescriptor$toString$1(this), 24);
    }
}
