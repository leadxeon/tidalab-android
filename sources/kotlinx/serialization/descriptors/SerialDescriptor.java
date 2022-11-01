package kotlinx.serialization.descriptors;

import java.lang.annotation.Annotation;
import java.util.List;
/* compiled from: SerialDescriptor.kt */
/* loaded from: classes.dex */
public interface SerialDescriptor {
    List<Annotation> getElementAnnotations(int i);

    SerialDescriptor getElementDescriptor(int i);

    int getElementIndex(String str);

    String getElementName(int i);

    int getElementsCount();

    SerialKind getKind();

    String getSerialName();

    boolean isInline();

    boolean isNullable();
}
