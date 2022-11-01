package kotlinx.serialization.json.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.internal.NamedValueDecoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import kotlinx.serialization.modules.SerializersModule;
/* compiled from: TreeJsonDecoder.kt */
/* loaded from: classes.dex */
public abstract class AbstractJsonTreeDecoder extends NamedValueDecoder implements JsonDecoder {
    public final JsonConfiguration configuration;
    public final Json json;
    public final JsonElement value;

    public AbstractJsonTreeDecoder(Json json, JsonElement jsonElement, DefaultConstructorMarker defaultConstructorMarker) {
        this.json = json;
        this.value = jsonElement;
        this.configuration = json.configuration;
    }

    public static final Void access$unparsedPrimitive(AbstractJsonTreeDecoder abstractJsonTreeDecoder, String str) {
        throw InputKt.JsonDecodingException(-1, "Failed to parse '" + str + '\'', abstractJsonTreeDecoder.currentObject().toString());
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor serialDescriptor) {
        JsonElement currentObject = currentObject();
        SerialKind kind = serialDescriptor.getKind();
        if (Intrinsics.areEqual(kind, StructureKind.LIST.INSTANCE) ? true : kind instanceof PolymorphicKind) {
            Json json = this.json;
            if (currentObject instanceof JsonArray) {
                return new JsonTreeListDecoder(json, (JsonArray) currentObject);
            }
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Expected ");
            outline13.append(Reflection.getOrCreateKotlinClass(JsonArray.class));
            outline13.append(" as the serialized body of ");
            outline13.append(serialDescriptor.getSerialName());
            outline13.append(", but had ");
            outline13.append(Reflection.getOrCreateKotlinClass(currentObject.getClass()));
            throw InputKt.JsonDecodingException(-1, outline13.toString());
        } else if (Intrinsics.areEqual(kind, StructureKind.MAP.INSTANCE)) {
            Json json2 = this.json;
            SerialDescriptor elementDescriptor = serialDescriptor.getElementDescriptor(0);
            if (elementDescriptor.isInline()) {
                elementDescriptor = elementDescriptor.getElementDescriptor(0);
            }
            SerialKind kind2 = elementDescriptor.getKind();
            if ((kind2 instanceof PrimitiveKind) || Intrinsics.areEqual(kind2, SerialKind.ENUM.INSTANCE)) {
                Json json3 = this.json;
                if (currentObject instanceof JsonObject) {
                    return new JsonTreeMapDecoder(json3, (JsonObject) currentObject);
                }
                StringBuilder outline132 = GeneratedOutlineSupport.outline13("Expected ");
                outline132.append(Reflection.getOrCreateKotlinClass(JsonObject.class));
                outline132.append(" as the serialized body of ");
                outline132.append(serialDescriptor.getSerialName());
                outline132.append(", but had ");
                outline132.append(Reflection.getOrCreateKotlinClass(currentObject.getClass()));
                throw InputKt.JsonDecodingException(-1, outline132.toString());
            } else if (json2.configuration.allowStructuredMapKeys) {
                Json json4 = this.json;
                if (currentObject instanceof JsonArray) {
                    return new JsonTreeListDecoder(json4, (JsonArray) currentObject);
                }
                StringBuilder outline133 = GeneratedOutlineSupport.outline13("Expected ");
                outline133.append(Reflection.getOrCreateKotlinClass(JsonArray.class));
                outline133.append(" as the serialized body of ");
                outline133.append(serialDescriptor.getSerialName());
                outline133.append(", but had ");
                outline133.append(Reflection.getOrCreateKotlinClass(currentObject.getClass()));
                throw InputKt.JsonDecodingException(-1, outline133.toString());
            } else {
                throw InputKt.InvalidKeyKindException(elementDescriptor);
            }
        } else {
            Json json5 = this.json;
            if (currentObject instanceof JsonObject) {
                return new JsonTreeDecoder(json5, (JsonObject) currentObject, null, null, 12);
            }
            StringBuilder outline134 = GeneratedOutlineSupport.outline13("Expected ");
            outline134.append(Reflection.getOrCreateKotlinClass(JsonObject.class));
            outline134.append(" as the serialized body of ");
            outline134.append(serialDescriptor.getSerialName());
            outline134.append(", but had ");
            outline134.append(Reflection.getOrCreateKotlinClass(currentObject.getClass()));
            throw InputKt.JsonDecodingException(-1, outline134.toString());
        }
    }

    public abstract JsonElement currentElement(String str);

    public final JsonElement currentObject() {
        String currentTagOrNull = getCurrentTagOrNull();
        JsonElement currentElement = currentTagOrNull == null ? null : currentElement(currentTagOrNull);
        return currentElement == null ? mo15getValue() : currentElement;
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    public JsonElement decodeJsonElement() {
        return currentObject();
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        return !(currentObject() instanceof JsonNull);
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    public <T> T decodeSerializableValue(DeserializationStrategy<T> deserializationStrategy) {
        return (T) PolymorphicKt.decodeSerializableValuePolymorphic(this, deserializationStrategy);
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public boolean decodeTaggedBoolean(String str) {
        String str2 = str;
        JsonPrimitive value = getValue(str2);
        if (this.json.configuration.isLenient || !((JsonLiteral) value).isString) {
            try {
                Boolean booleanOrNull = InputKt.getBooleanOrNull(value);
                if (booleanOrNull != null) {
                    return booleanOrNull.booleanValue();
                }
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException unused) {
                access$unparsedPrimitive(this, "boolean");
                throw null;
            }
        } else {
            throw InputKt.JsonDecodingException(-1, GeneratedOutlineSupport.outline9("Boolean literal for key '", str2, "' should be unquoted.\nUse 'isLenient = true' in 'Json {}` builder to accept non-compliant JSON."), currentObject().toString());
        }
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public byte decodeTaggedByte(String str) {
        try {
            int i = InputKt.getInt(getValue(str));
            boolean z = false;
            if (-128 <= i && i <= 127) {
                z = true;
            }
            Byte valueOf = z ? Byte.valueOf((byte) i) : null;
            if (valueOf != null) {
                return valueOf.byteValue();
            }
            access$unparsedPrimitive(this, "byte");
            throw null;
        } catch (IllegalArgumentException unused) {
            access$unparsedPrimitive(this, "byte");
            throw null;
        }
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public char decodeTaggedChar(String str) {
        try {
            String content = getValue(str).getContent();
            int length = content.length();
            if (length == 0) {
                throw new NoSuchElementException("Char sequence is empty.");
            } else if (length == 1) {
                return content.charAt(0);
            } else {
                throw new IllegalArgumentException("Char sequence has more than one element.");
            }
        } catch (IllegalArgumentException unused) {
            access$unparsedPrimitive(this, "char");
            throw null;
        }
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public double decodeTaggedDouble(String str) {
        String str2 = str;
        try {
            double parseDouble = Double.parseDouble(getValue(str2).getContent());
            if (!this.json.configuration.allowSpecialFloatingPointValues) {
                if (!(!Double.isInfinite(parseDouble) && !Double.isNaN(parseDouble))) {
                    throw InputKt.InvalidFloatingPointDecoded(Double.valueOf(parseDouble), str2, currentObject().toString());
                }
            }
            return parseDouble;
        } catch (IllegalArgumentException unused) {
            access$unparsedPrimitive(this, "double");
            throw null;
        }
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public int decodeTaggedEnum(String str, SerialDescriptor serialDescriptor) {
        return JsonNamesMapKt.getJsonNameIndexOrThrow(serialDescriptor, this.json, getValue(str).getContent());
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public float decodeTaggedFloat(String str) {
        String str2 = str;
        try {
            float parseFloat = Float.parseFloat(getValue(str2).getContent());
            if (!this.json.configuration.allowSpecialFloatingPointValues) {
                if (!(!Float.isInfinite(parseFloat) && !Float.isNaN(parseFloat))) {
                    throw InputKt.InvalidFloatingPointDecoded(Float.valueOf(parseFloat), str2, currentObject().toString());
                }
            }
            return parseFloat;
        } catch (IllegalArgumentException unused) {
            access$unparsedPrimitive(this, "float");
            throw null;
        }
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public Decoder decodeTaggedInline(String str, SerialDescriptor serialDescriptor) {
        String str2 = str;
        if (StreamingJsonEncoderKt.isUnsignedNumber(serialDescriptor)) {
            return new JsonDecoderForUnsignedTypes(new JsonLexer(getValue(str2).getContent()), this.json);
        }
        this.tagStack.add(str2);
        return this;
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public int decodeTaggedInt(String str) {
        try {
            return InputKt.getInt(getValue(str));
        } catch (IllegalArgumentException unused) {
            access$unparsedPrimitive(this, "int");
            throw null;
        }
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public long decodeTaggedLong(String str) {
        try {
            return Long.parseLong(getValue(str).getContent());
        } catch (IllegalArgumentException unused) {
            access$unparsedPrimitive(this, "long");
            throw null;
        }
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public short decodeTaggedShort(String str) {
        try {
            int i = InputKt.getInt(getValue(str));
            boolean z = false;
            if (-32768 <= i && i <= 32767) {
                z = true;
            }
            Short valueOf = z ? Short.valueOf((short) i) : null;
            if (valueOf != null) {
                return valueOf.shortValue();
            }
            access$unparsedPrimitive(this, "short");
            throw null;
        } catch (IllegalArgumentException unused) {
            access$unparsedPrimitive(this, "short");
            throw null;
        }
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public String decodeTaggedString(String str) {
        String str2 = str;
        JsonPrimitive value = getValue(str2);
        if (this.json.configuration.isLenient || ((JsonLiteral) value).isString) {
            return value.getContent();
        }
        throw InputKt.JsonDecodingException(-1, GeneratedOutlineSupport.outline9("String literal for key '", str2, "' should be quoted.\nUse 'isLenient = true' in 'Json {}` builder to accept non-compliant JSON."), currentObject().toString());
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(SerialDescriptor serialDescriptor) {
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    public Json getJson() {
        return this.json;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public SerializersModule getSerializersModule() {
        return this.json.serializersModule;
    }

    /* renamed from: getValue */
    public abstract JsonElement mo15getValue();

    public JsonPrimitive getValue(String str) {
        JsonElement currentElement = currentElement(str);
        JsonPrimitive jsonPrimitive = currentElement instanceof JsonPrimitive ? (JsonPrimitive) currentElement : null;
        if (jsonPrimitive != null) {
            return jsonPrimitive;
        }
        throw InputKt.JsonDecodingException(-1, "Expected JsonPrimitive at " + str + ", found " + currentElement, currentObject().toString());
    }
}
