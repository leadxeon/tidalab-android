package kotlinx.serialization.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__IndentKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
/* compiled from: CollectionDescriptors.kt */
/* loaded from: classes.dex */
public abstract class MapLikeDescriptor implements SerialDescriptor {
    public final int elementsCount = 2;
    public final SerialDescriptor keyDescriptor;
    public final String serialName;
    public final SerialDescriptor valueDescriptor;

    public MapLikeDescriptor(String str, SerialDescriptor serialDescriptor, SerialDescriptor serialDescriptor2, DefaultConstructorMarker defaultConstructorMarker) {
        this.serialName = str;
        this.keyDescriptor = serialDescriptor;
        this.valueDescriptor = serialDescriptor2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MapLikeDescriptor)) {
            return false;
        }
        MapLikeDescriptor mapLikeDescriptor = (MapLikeDescriptor) obj;
        return Intrinsics.areEqual(this.serialName, mapLikeDescriptor.serialName) && Intrinsics.areEqual(this.keyDescriptor, mapLikeDescriptor.keyDescriptor) && Intrinsics.areEqual(this.valueDescriptor, mapLikeDescriptor.valueDescriptor);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public List<Annotation> getElementAnnotations(int i) {
        if (i >= 0) {
            return EmptyList.INSTANCE;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport.outline11(GeneratedOutlineSupport.outline15("Illegal index ", i, ", "), this.serialName, " expects only non-negative indices").toString());
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public SerialDescriptor getElementDescriptor(int i) {
        if (i >= 0) {
            int i2 = i % 2;
            if (i2 == 0) {
                return this.keyDescriptor;
            }
            if (i2 == 1) {
                return this.valueDescriptor;
            }
            throw new IllegalStateException("Unreached".toString());
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport.outline11(GeneratedOutlineSupport.outline15("Illegal index ", i, ", "), this.serialName, " expects only non-negative indices").toString());
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public int getElementIndex(String str) {
        Integer intOrNull = StringsKt__IndentKt.toIntOrNull(str);
        if (intOrNull != null) {
            return intOrNull.intValue();
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus(str, " is not a valid map index"));
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public String getElementName(int i) {
        return String.valueOf(i);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public int getElementsCount() {
        return this.elementsCount;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public SerialKind getKind() {
        return StructureKind.MAP.INSTANCE;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public String getSerialName() {
        return this.serialName;
    }

    public int hashCode() {
        int hashCode = this.keyDescriptor.hashCode();
        return this.valueDescriptor.hashCode() + ((hashCode + (this.serialName.hashCode() * 31)) * 31);
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
        return this.serialName + '(' + this.keyDescriptor + ", " + this.valueDescriptor + ')';
    }
}
