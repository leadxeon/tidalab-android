package kotlinx.serialization.json.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.EmptySet;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.internal.Platform_commonKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonObject;
/* compiled from: TreeJsonDecoder.kt */
/* loaded from: classes.dex */
public class JsonTreeDecoder extends AbstractJsonTreeDecoder {
    public final SerialDescriptor polyDescriptor;
    public final String polyDiscriminator;
    public int position;
    public final JsonObject value;

    public JsonTreeDecoder(Json json, JsonObject jsonObject, String str, SerialDescriptor serialDescriptor) {
        super(json, jsonObject, null);
        this.value = jsonObject;
        this.polyDiscriminator = str;
        this.polyDescriptor = serialDescriptor;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder, kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor serialDescriptor) {
        return serialDescriptor == this.polyDescriptor ? this : super.beginStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder
    public JsonElement currentElement(String str) {
        return (JsonElement) ArraysKt___ArraysKt.getValue(mo15getValue(), str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x006f, code lost:
        if (kotlinx.serialization.json.internal.JsonNamesMapKt.getJsonNameIndex(r1, r6.json, r5) != (-3)) goto L_0x0072;
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0074 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0000 A[SYNTHETIC] */
    @Override // kotlinx.serialization.encoding.CompositeDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int decodeElementIndex(kotlinx.serialization.descriptors.SerialDescriptor r7) {
        /*
            r6 = this;
        L_0x0000:
            int r0 = r6.position
            int r1 = r7.getElementsCount()
            if (r0 >= r1) goto L_0x0078
            int r0 = r6.position
            int r1 = r0 + 1
            r6.position = r1
            java.lang.String r0 = r6.elementName(r7, r0)
            java.lang.Object r1 = r6.getCurrentTagOrNull()
            java.lang.String r1 = (java.lang.String) r1
            kotlinx.serialization.json.JsonObject r1 = r6.mo15getValue()
            boolean r1 = r1.containsKey(r0)
            if (r1 == 0) goto L_0x0000
            kotlinx.serialization.json.JsonConfiguration r1 = r6.configuration
            boolean r1 = r1.coerceInputValues
            r2 = 1
            if (r1 == 0) goto L_0x0074
            int r1 = r6.position
            int r1 = r1 - r2
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r7.getElementDescriptor(r1)
            kotlinx.serialization.json.JsonElement r3 = r6.currentElement(r0)
            boolean r3 = r3 instanceof kotlinx.serialization.json.JsonNull
            if (r3 == 0) goto L_0x003f
            boolean r3 = r1.isNullable()
            if (r3 != 0) goto L_0x003f
            goto L_0x0071
        L_0x003f:
            kotlinx.serialization.descriptors.SerialKind r3 = r1.getKind()
            kotlinx.serialization.descriptors.SerialKind$ENUM r4 = kotlinx.serialization.descriptors.SerialKind.ENUM.INSTANCE
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r4)
            r4 = 0
            if (r3 == 0) goto L_0x0072
            kotlinx.serialization.json.JsonElement r0 = r6.currentElement(r0)
            boolean r3 = r0 instanceof kotlinx.serialization.json.JsonPrimitive
            r5 = 0
            if (r3 == 0) goto L_0x0058
            kotlinx.serialization.json.JsonPrimitive r0 = (kotlinx.serialization.json.JsonPrimitive) r0
            goto L_0x0059
        L_0x0058:
            r0 = r5
        L_0x0059:
            if (r0 != 0) goto L_0x005c
            goto L_0x0065
        L_0x005c:
            boolean r3 = r0 instanceof kotlinx.serialization.json.JsonNull
            if (r3 == 0) goto L_0x0061
            goto L_0x0065
        L_0x0061:
            java.lang.String r5 = r0.getContent()
        L_0x0065:
            if (r5 != 0) goto L_0x0068
            goto L_0x0072
        L_0x0068:
            kotlinx.serialization.json.Json r0 = r6.json
            int r0 = kotlinx.serialization.json.internal.JsonNamesMapKt.getJsonNameIndex(r1, r0, r5)
            r1 = -3
            if (r0 != r1) goto L_0x0072
        L_0x0071:
            r4 = 1
        L_0x0072:
            if (r4 != 0) goto L_0x0000
        L_0x0074:
            int r7 = r6.position
            int r7 = r7 - r2
            return r7
        L_0x0078:
            r7 = -1
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.JsonTreeDecoder.decodeElementIndex(kotlinx.serialization.descriptors.SerialDescriptor):int");
    }

    @Override // kotlinx.serialization.internal.NamedValueDecoder
    public String elementName(SerialDescriptor serialDescriptor, int i) {
        Object obj;
        boolean z;
        String elementName = serialDescriptor.getElementName(i);
        if (!this.configuration.useAlternativeNames || mo15getValue().keySet().contains(elementName)) {
            return elementName;
        }
        Map map = (Map) this.json.schemaCache.getOrPut(serialDescriptor, JsonNamesMapKt.JsonAlternativeNamesKey, new JsonTreeDecoder$elementName$alternativeNamesMap$1(serialDescriptor));
        Iterator<T> it = mo15getValue().keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            Integer num = (Integer) map.get((String) obj);
            if (num != null && num.intValue() == i) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (z) {
                break;
            }
        }
        String str = (String) obj;
        return str == null ? elementName : str;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(SerialDescriptor serialDescriptor) {
        Set<String> set;
        if (!(this.configuration.ignoreUnknownKeys || (serialDescriptor.getKind() instanceof PolymorphicKind))) {
            if (!this.configuration.useAlternativeNames) {
                set = Platform_commonKt.cachedSerialNames(serialDescriptor);
            } else {
                Set<String> cachedSerialNames = Platform_commonKt.cachedSerialNames(serialDescriptor);
                Map map = (Map) this.json.schemaCache.get(serialDescriptor, JsonNamesMapKt.JsonAlternativeNamesKey);
                Collection keySet = map == null ? null : map.keySet();
                if (keySet == null) {
                    keySet = EmptySet.INSTANCE;
                }
                Integer valueOf = Integer.valueOf(keySet.size());
                LinkedHashSet linkedHashSet = new LinkedHashSet(InputKt.mapCapacity(valueOf != null ? cachedSerialNames.size() + valueOf.intValue() : cachedSerialNames.size() * 2));
                linkedHashSet.addAll(cachedSerialNames);
                ArraysKt___ArraysKt.addAll(linkedHashSet, keySet);
                set = linkedHashSet;
            }
            for (String str : mo15getValue().keySet()) {
                if (!(set.contains(str) || Intrinsics.areEqual(str, this.polyDiscriminator))) {
                    String jsonObject = mo15getValue().toString();
                    StringBuilder outline16 = GeneratedOutlineSupport.outline16("Encountered unknown key '", str, "'.\nUse 'ignoreUnknownKeys = true' in 'Json {}' builder to ignore unknown keys.\nCurrent input: ");
                    outline16.append(InputKt.minify(jsonObject, -1));
                    throw InputKt.JsonDecodingException(-1, outline16.toString());
                }
            }
        }
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder
    /* renamed from: getValue */
    public JsonObject mo15getValue() {
        return this.value;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonTreeDecoder(Json json, JsonObject jsonObject, String str, SerialDescriptor serialDescriptor, int i) {
        super(json, jsonObject, null);
        int i2 = i & 4;
        int i3 = i & 8;
        this.value = jsonObject;
        this.polyDiscriminator = null;
        this.polyDescriptor = null;
    }
}
