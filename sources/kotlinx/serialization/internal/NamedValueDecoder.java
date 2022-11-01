package kotlinx.serialization.internal;

import kotlinx.serialization.descriptors.SerialDescriptor;
/* compiled from: Tagged.kt */
/* loaded from: classes.dex */
public abstract class NamedValueDecoder extends TaggedDecoder<String> {
    public String elementName(SerialDescriptor serialDescriptor, int i) {
        return serialDescriptor.getElementName(i);
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public String getTag(SerialDescriptor serialDescriptor, int i) {
        String elementName = elementName(serialDescriptor, i);
        getCurrentTagOrNull();
        return elementName;
    }
}
