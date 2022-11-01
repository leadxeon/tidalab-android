package kotlinx.serialization.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.lang.annotation.Annotation;
import java.util.List;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
/* compiled from: Primitives.kt */
/* loaded from: classes.dex */
public final class PrimitiveSerialDescriptor implements SerialDescriptor {
    public final PrimitiveKind kind;
    public final String serialName;

    public PrimitiveSerialDescriptor(String str, PrimitiveKind primitiveKind) {
        this.serialName = str;
        this.kind = primitiveKind;
    }

    public final Void error() {
        throw new IllegalStateException("Primitive descriptor does not have elements");
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public List<Annotation> getElementAnnotations(int i) {
        error();
        throw null;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public SerialDescriptor getElementDescriptor(int i) {
        error();
        throw null;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public int getElementIndex(String str) {
        error();
        throw null;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public String getElementName(int i) {
        error();
        throw null;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public int getElementsCount() {
        return 0;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public SerialKind getKind() {
        return this.kind;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public String getSerialName() {
        return this.serialName;
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
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("PrimitiveDescriptor(");
        outline13.append(this.serialName);
        outline13.append(')');
        return outline13.toString();
    }
}
