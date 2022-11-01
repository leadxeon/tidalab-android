package kotlinx.serialization.json.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.AbstractDecoder;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.modules.SerializersModule;
/* compiled from: StreamingJsonDecoder.kt */
/* loaded from: classes.dex */
public class StreamingJsonDecoder extends AbstractDecoder implements JsonDecoder {
    public final JsonConfiguration configuration;
    public int currentIndex = -1;
    public final Json json;
    public final JsonLexer lexer;
    public final WriteMode mode;
    public final SerializersModule serializersModule;

    public StreamingJsonDecoder(Json json, WriteMode writeMode, JsonLexer jsonLexer) {
        this.json = json;
        this.mode = writeMode;
        this.lexer = jsonLexer;
        this.serializersModule = json.serializersModule;
        this.configuration = json.configuration;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor serialDescriptor) {
        WriteMode switchMode = PolymorphicKt.switchMode(this.json, serialDescriptor);
        this.lexer.consumeNextToken(switchMode.begin);
        if (this.lexer.peekNextToken() != 4) {
            int ordinal = switchMode.ordinal();
            if (ordinal == 1 || ordinal == 2 || ordinal == 3) {
                return new StreamingJsonDecoder(this.json, switchMode, this.lexer);
            }
            return this.mode == switchMode ? this : new StreamingJsonDecoder(this.json, switchMode, this.lexer);
        }
        JsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, 2);
        throw null;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeBoolean() {
        boolean z;
        if (this.configuration.isLenient) {
            JsonLexer jsonLexer = this.lexer;
            int skipWhitespaces = jsonLexer.skipWhitespaces();
            if (skipWhitespaces != jsonLexer.source.length()) {
                if (jsonLexer.source.charAt(skipWhitespaces) == '\"') {
                    skipWhitespaces++;
                    z = true;
                } else {
                    z = false;
                }
                boolean consumeBoolean = jsonLexer.consumeBoolean(skipWhitespaces);
                if (!z) {
                    return consumeBoolean;
                }
                if (jsonLexer.currentPosition == jsonLexer.source.length()) {
                    throw InputKt.JsonDecodingException(jsonLexer.currentPosition, "EOF", jsonLexer.source);
                } else if (jsonLexer.source.charAt(jsonLexer.currentPosition) == '\"') {
                    jsonLexer.currentPosition++;
                    return consumeBoolean;
                } else {
                    throw InputKt.JsonDecodingException(jsonLexer.currentPosition, "Expected closing quotation mark", jsonLexer.source);
                }
            } else {
                throw InputKt.JsonDecodingException(jsonLexer.currentPosition, "EOF", jsonLexer.source);
            }
        } else {
            JsonLexer jsonLexer2 = this.lexer;
            return jsonLexer2.consumeBoolean(jsonLexer2.skipWhitespaces());
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public byte decodeByte() {
        long consumeNumericLiteral = this.lexer.consumeNumericLiteral();
        byte b = (byte) consumeNumericLiteral;
        if (consumeNumericLiteral == b) {
            return b;
        }
        JsonLexer jsonLexer = this.lexer;
        JsonLexer.fail$default(jsonLexer, "Failed to parse byte for input '" + consumeNumericLiteral + '\'', 0, 2);
        throw null;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public char decodeChar() {
        String consumeStringLenient = this.lexer.consumeStringLenient();
        if (consumeStringLenient.length() == 1) {
            return consumeStringLenient.charAt(0);
        }
        JsonLexer jsonLexer = this.lexer;
        JsonLexer.fail$default(jsonLexer, "Expected single char, but got '" + consumeStringLenient + '\'', 0, 2);
        throw null;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public double decodeDouble() {
        JsonLexer jsonLexer = this.lexer;
        String consumeStringLenient = jsonLexer.consumeStringLenient();
        try {
            double parseDouble = Double.parseDouble(consumeStringLenient);
            if (!this.json.configuration.allowSpecialFloatingPointValues) {
                if (!(!Double.isInfinite(parseDouble) && !Double.isNaN(parseDouble))) {
                    InputKt.throwInvalidFloatingPointDecoded(this.lexer, Double.valueOf(parseDouble));
                    throw null;
                }
            }
            return parseDouble;
        } catch (IllegalArgumentException unused) {
            throw InputKt.JsonDecodingException(jsonLexer.currentPosition, "Failed to parse type 'double' for input '" + consumeStringLenient + '\'', jsonLexer.source);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x012c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0124  */
    @Override // kotlinx.serialization.encoding.CompositeDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int decodeElementIndex(kotlinx.serialization.descriptors.SerialDescriptor r15) {
        /*
            Method dump skipped, instructions count: 525
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.StreamingJsonDecoder.decodeElementIndex(kotlinx.serialization.descriptors.SerialDescriptor):int");
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public int decodeEnum(SerialDescriptor serialDescriptor) {
        return JsonNamesMapKt.getJsonNameIndexOrThrow(serialDescriptor, this.json, decodeString());
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public float decodeFloat() {
        JsonLexer jsonLexer = this.lexer;
        String consumeStringLenient = jsonLexer.consumeStringLenient();
        try {
            float parseFloat = Float.parseFloat(consumeStringLenient);
            if (!this.json.configuration.allowSpecialFloatingPointValues) {
                if (!(!Float.isInfinite(parseFloat) && !Float.isNaN(parseFloat))) {
                    InputKt.throwInvalidFloatingPointDecoded(this.lexer, Float.valueOf(parseFloat));
                    throw null;
                }
            }
            return parseFloat;
        } catch (IllegalArgumentException unused) {
            throw InputKt.JsonDecodingException(jsonLexer.currentPosition, "Failed to parse type 'float' for input '" + consumeStringLenient + '\'', jsonLexer.source);
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public Decoder decodeInline(SerialDescriptor serialDescriptor) {
        return StreamingJsonEncoderKt.isUnsignedNumber(serialDescriptor) ? new JsonDecoderForUnsignedTypes(this.lexer, this.json) : this;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public int decodeInt() {
        long consumeNumericLiteral = this.lexer.consumeNumericLiteral();
        int i = (int) consumeNumericLiteral;
        if (consumeNumericLiteral == i) {
            return i;
        }
        JsonLexer jsonLexer = this.lexer;
        JsonLexer.fail$default(jsonLexer, "Failed to parse int for input '" + consumeNumericLiteral + '\'', 0, 2);
        throw null;
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    public JsonElement decodeJsonElement() {
        return new JsonTreeReader(this.json.configuration, this.lexer).read();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public long decodeLong() {
        return this.lexer.consumeNumericLiteral();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        return this.lexer.tryConsumeNotNull();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public Void decodeNull() {
        return null;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public <T> T decodeSerializableValue(DeserializationStrategy<T> deserializationStrategy) {
        return (T) PolymorphicKt.decodeSerializableValuePolymorphic(this, deserializationStrategy);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public short decodeShort() {
        long consumeNumericLiteral = this.lexer.consumeNumericLiteral();
        short s = (short) consumeNumericLiteral;
        if (consumeNumericLiteral == s) {
            return s;
        }
        JsonLexer jsonLexer = this.lexer;
        JsonLexer.fail$default(jsonLexer, "Failed to parse short for input '" + consumeNumericLiteral + '\'', 0, 2);
        throw null;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public String decodeString() {
        if (this.configuration.isLenient) {
            return this.lexer.consumeStringLenient();
        }
        return this.lexer.consumeString();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(SerialDescriptor serialDescriptor) {
        this.lexer.consumeNextToken(this.mode.end);
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    public final Json getJson() {
        return this.json;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }
}
