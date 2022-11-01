package kotlinx.serialization.json.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.EmptyMap;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonNames;
import kotlinx.serialization.json.internal.DescriptorSchemaCache;
/* compiled from: JsonNamesMap.kt */
/* loaded from: classes.dex */
public final class JsonNamesMapKt {
    public static final DescriptorSchemaCache.Key<Map<String, Integer>> JsonAlternativeNamesKey = new DescriptorSchemaCache.Key<>();

    public static final Map<String, Integer> buildAlternativeNamesMap(SerialDescriptor serialDescriptor) {
        String[] names;
        int elementsCount = serialDescriptor.getElementsCount();
        ConcurrentHashMap concurrentHashMap = null;
        if (elementsCount > 0) {
            int i = 0;
            ConcurrentHashMap concurrentHashMap2 = null;
            while (true) {
                int i2 = i + 1;
                List<Annotation> elementAnnotations = serialDescriptor.getElementAnnotations(i);
                ArrayList arrayList = new ArrayList();
                for (Object obj : elementAnnotations) {
                    if (obj instanceof JsonNames) {
                        arrayList.add(obj);
                    }
                }
                JsonNames jsonNames = (JsonNames) (arrayList.size() == 1 ? arrayList.get(0) : null);
                if (!(jsonNames == null || (names = jsonNames.names()) == null)) {
                    for (String str : names) {
                        if (concurrentHashMap2 == null) {
                            concurrentHashMap2 = new ConcurrentHashMap(serialDescriptor.getElementsCount());
                        }
                        if (!concurrentHashMap2.containsKey(str)) {
                            concurrentHashMap2.put(str, Integer.valueOf(i));
                        } else {
                            StringBuilder outline16 = GeneratedOutlineSupport.outline16("The suggested name '", str, "' for property ");
                            outline16.append(serialDescriptor.getElementName(i));
                            outline16.append(" is already one of the names for property ");
                            outline16.append(serialDescriptor.getElementName(((Number) ArraysKt___ArraysKt.getValue(concurrentHashMap2, str)).intValue()));
                            outline16.append(" in ");
                            outline16.append(serialDescriptor);
                            throw new JsonException(outline16.toString());
                        }
                    }
                }
                if (i2 >= elementsCount) {
                    concurrentHashMap = concurrentHashMap2;
                    break;
                }
                i = i2;
            }
        }
        return concurrentHashMap == null ? EmptyMap.INSTANCE : concurrentHashMap;
    }

    public static final int getJsonNameIndex(SerialDescriptor serialDescriptor, Json json, String str) {
        int elementIndex = serialDescriptor.getElementIndex(str);
        if (elementIndex != -3 || !json.configuration.useAlternativeNames) {
            return elementIndex;
        }
        Integer num = (Integer) ((Map) json.schemaCache.getOrPut(serialDescriptor, JsonAlternativeNamesKey, new JsonNamesMapKt$getJsonNameIndex$alternativeNamesMap$1(serialDescriptor))).get(str);
        if (num == null) {
            return -3;
        }
        return num.intValue();
    }

    public static final int getJsonNameIndexOrThrow(SerialDescriptor serialDescriptor, Json json, String str) {
        int jsonNameIndex = getJsonNameIndex(serialDescriptor, json, str);
        if (jsonNameIndex != -3) {
            return jsonNameIndex;
        }
        throw new SerializationException(serialDescriptor.getSerialName() + " does not contain element with name '" + str + '\'');
    }
}
