package kotlinx.serialization.internal;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
/* compiled from: InlineClassDescriptor.kt */
/* loaded from: classes.dex */
public final class InlineClassDescriptor extends PluginGeneratedSerialDescriptor {
    public final boolean isInline = true;

    public InlineClassDescriptor(String str, GeneratedSerializer<?> generatedSerializer) {
        super(str, generatedSerializer, 1);
    }

    @Override // kotlinx.serialization.internal.PluginGeneratedSerialDescriptor
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof InlineClassDescriptor) {
            SerialDescriptor serialDescriptor = (SerialDescriptor) obj;
            if (Intrinsics.areEqual(getSerialName(), serialDescriptor.getSerialName())) {
                InlineClassDescriptor inlineClassDescriptor = (InlineClassDescriptor) obj;
                if ((inlineClassDescriptor.isInline && Arrays.equals(getTypeParameterDescriptors$kotlinx_serialization_core(), inlineClassDescriptor.getTypeParameterDescriptors$kotlinx_serialization_core())) && getElementsCount() == serialDescriptor.getElementsCount()) {
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
        }
        return false;
    }

    @Override // kotlinx.serialization.internal.PluginGeneratedSerialDescriptor
    public int hashCode() {
        return super.hashCode() * 31;
    }

    @Override // kotlinx.serialization.internal.PluginGeneratedSerialDescriptor, kotlinx.serialization.descriptors.SerialDescriptor
    public boolean isInline() {
        return this.isInline;
    }
}
