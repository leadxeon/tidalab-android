package kotlinx.serialization.descriptors;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
/* compiled from: ContextAware.kt */
/* loaded from: classes.dex */
public final class ContextDescriptor implements SerialDescriptor {
    public final KClass<?> kClass;
    public final SerialDescriptor original;
    public final String serialName;

    public ContextDescriptor(SerialDescriptor serialDescriptor, KClass<?> kClass) {
        this.original = serialDescriptor;
        this.kClass = kClass;
        this.serialName = serialDescriptor.getSerialName() + '<' + ((Object) kClass.getSimpleName()) + '>';
    }

    public boolean equals(Object obj) {
        ContextDescriptor contextDescriptor = obj instanceof ContextDescriptor ? (ContextDescriptor) obj : null;
        return contextDescriptor != null && Intrinsics.areEqual(this.original, contextDescriptor.original) && Intrinsics.areEqual(contextDescriptor.kClass, this.kClass);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public List<Annotation> getElementAnnotations(int i) {
        return this.original.getElementAnnotations(i);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public SerialDescriptor getElementDescriptor(int i) {
        return this.original.getElementDescriptor(i);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public int getElementIndex(String str) {
        return this.original.getElementIndex(str);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public String getElementName(int i) {
        return this.original.getElementName(i);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public int getElementsCount() {
        return this.original.getElementsCount();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public SerialKind getKind() {
        return this.original.getKind();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public String getSerialName() {
        return this.serialName;
    }

    public int hashCode() {
        return this.serialName.hashCode() + (this.kClass.hashCode() * 31);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public boolean isInline() {
        return this.original.isInline();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public boolean isNullable() {
        return this.original.isNullable();
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("ContextDescriptor(kClass: ");
        outline13.append(this.kClass);
        outline13.append(", original: ");
        outline13.append(this.original);
        outline13.append(')');
        return outline13.toString();
    }
}
