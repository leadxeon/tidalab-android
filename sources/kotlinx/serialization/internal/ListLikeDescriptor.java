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
public abstract class ListLikeDescriptor implements SerialDescriptor {
    public final SerialDescriptor elementDescriptor;
    public final int elementsCount = 1;

    public ListLikeDescriptor(SerialDescriptor serialDescriptor, DefaultConstructorMarker defaultConstructorMarker) {
        this.elementDescriptor = serialDescriptor;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ListLikeDescriptor)) {
            return false;
        }
        ListLikeDescriptor listLikeDescriptor = (ListLikeDescriptor) obj;
        return Intrinsics.areEqual(this.elementDescriptor, listLikeDescriptor.elementDescriptor) && Intrinsics.areEqual(getSerialName(), listLikeDescriptor.getSerialName());
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public List<Annotation> getElementAnnotations(int i) {
        if (i >= 0) {
            return EmptyList.INSTANCE;
        }
        StringBuilder outline15 = GeneratedOutlineSupport.outline15("Illegal index ", i, ", ");
        outline15.append(getSerialName());
        outline15.append(" expects only non-negative indices");
        throw new IllegalArgumentException(outline15.toString().toString());
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public SerialDescriptor getElementDescriptor(int i) {
        if (i >= 0) {
            return this.elementDescriptor;
        }
        StringBuilder outline15 = GeneratedOutlineSupport.outline15("Illegal index ", i, ", ");
        outline15.append(getSerialName());
        outline15.append(" expects only non-negative indices");
        throw new IllegalArgumentException(outline15.toString().toString());
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public int getElementIndex(String str) {
        Integer intOrNull = StringsKt__IndentKt.toIntOrNull(str);
        if (intOrNull != null) {
            return intOrNull.intValue();
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus(str, " is not a valid list index"));
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
        return StructureKind.LIST.INSTANCE;
    }

    public int hashCode() {
        return getSerialName().hashCode() + (this.elementDescriptor.hashCode() * 31);
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
        return getSerialName() + '(' + this.elementDescriptor + ')';
    }
}
