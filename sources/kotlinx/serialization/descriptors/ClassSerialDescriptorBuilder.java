package kotlinx.serialization.descriptors;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.EmptyList;
/* compiled from: SerialDescriptors.kt */
/* loaded from: classes.dex */
public final class ClassSerialDescriptorBuilder {
    public final List<String> elementNames = new ArrayList();
    public final Set<String> uniqueNames = new HashSet();
    public final List<SerialDescriptor> elementDescriptors = new ArrayList();
    public final List<List<Annotation>> elementAnnotations = new ArrayList();
    public final List<Boolean> elementOptionality = new ArrayList();

    public ClassSerialDescriptorBuilder(String str) {
    }

    public static void element$default(ClassSerialDescriptorBuilder classSerialDescriptorBuilder, String str, SerialDescriptor serialDescriptor, List list, boolean z, int i) {
        EmptyList emptyList = (i & 4) != 0 ? EmptyList.INSTANCE : null;
        if ((i & 8) != 0) {
            z = false;
        }
        if (classSerialDescriptorBuilder.uniqueNames.add(str)) {
            classSerialDescriptorBuilder.elementNames.add(str);
            classSerialDescriptorBuilder.elementDescriptors.add(serialDescriptor);
            classSerialDescriptorBuilder.elementAnnotations.add(emptyList);
            classSerialDescriptorBuilder.elementOptionality.add(Boolean.valueOf(z));
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport.outline9("Element with name '", str, "' is already registered").toString());
    }
}
