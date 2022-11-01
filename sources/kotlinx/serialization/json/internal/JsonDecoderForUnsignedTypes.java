package kotlinx.serialization.json.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.text.StringsKt__IndentKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.AbstractDecoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.modules.SerializersModule;
/* compiled from: StreamingJsonDecoder.kt */
/* loaded from: classes.dex */
public final class JsonDecoderForUnsignedTypes extends AbstractDecoder {
    public final JsonLexer lexer;
    public final SerializersModule serializersModule;

    public JsonDecoderForUnsignedTypes(JsonLexer jsonLexer, Json json) {
        this.lexer = jsonLexer;
        this.serializersModule = json.serializersModule;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0024 A[Catch: IllegalArgumentException -> 0x002b, TryCatch #0 {IllegalArgumentException -> 0x002b, blocks: (B:3:0x0008, B:5:0x000f, B:8:0x001a, B:11:0x0024, B:13:0x0027, B:14:0x002a), top: B:17:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0027 A[Catch: IllegalArgumentException -> 0x002b, TryCatch #0 {IllegalArgumentException -> 0x002b, blocks: (B:3:0x0008, B:5:0x000f, B:8:0x001a, B:11:0x0024, B:13:0x0027, B:14:0x002a), top: B:17:0x0008 }] */
    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public byte decodeByte() {
        /*
            r5 = this;
            kotlinx.serialization.json.internal.JsonLexer r0 = r5.lexer
            java.lang.String r1 = r0.consumeStringLenient()
            r2 = 10
            kotlin.UInt r2 = kotlin.text.StringsKt__IndentKt.toUIntOrNull(r1, r2)     // Catch: IllegalArgumentException -> 0x002b
            r3 = 0
            if (r2 == 0) goto L_0x0021
            int r2 = r2.data     // Catch: IllegalArgumentException -> 0x002b
            r4 = 255(0xff, float:3.57E-43)
            int r4 = com.tidalab.v2board.clash.design.dialog.InputKt.uintCompare(r2, r4)     // Catch: IllegalArgumentException -> 0x002b
            if (r4 <= 0) goto L_0x001a
            goto L_0x0021
        L_0x001a:
            byte r2 = (byte) r2     // Catch: IllegalArgumentException -> 0x002b
            kotlin.UByte r4 = new kotlin.UByte     // Catch: IllegalArgumentException -> 0x002b
            r4.<init>(r2)     // Catch: IllegalArgumentException -> 0x002b
            goto L_0x0022
        L_0x0021:
            r4 = r3
        L_0x0022:
            if (r4 == 0) goto L_0x0027
            byte r0 = r4.data     // Catch: IllegalArgumentException -> 0x002b
            return r0
        L_0x0027:
            kotlin.text.StringsKt__IndentKt.numberFormatError(r1)     // Catch: IllegalArgumentException -> 0x002b
            throw r3     // Catch: IllegalArgumentException -> 0x002b
        L_0x002b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to parse type '"
            r2.append(r3)
            java.lang.String r3 = "UByte"
            r2.append(r3)
            java.lang.String r3 = "' for input '"
            r2.append(r3)
            r2.append(r1)
            r1 = 39
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            int r2 = r0.currentPosition
            java.lang.String r0 = r0.source
            kotlinx.serialization.json.internal.JsonDecodingException r0 = com.tidalab.v2board.clash.design.dialog.InputKt.JsonDecodingException(r2, r1, r0)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.JsonDecoderForUnsignedTypes.decodeByte():byte");
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeElementIndex(SerialDescriptor serialDescriptor) {
        throw new IllegalStateException("unsupported".toString());
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public int decodeInt() {
        JsonLexer jsonLexer = this.lexer;
        String consumeStringLenient = jsonLexer.consumeStringLenient();
        try {
            UInt uIntOrNull = StringsKt__IndentKt.toUIntOrNull(consumeStringLenient, 10);
            if (uIntOrNull != null) {
                return uIntOrNull.data;
            }
            StringsKt__IndentKt.numberFormatError(consumeStringLenient);
            throw null;
        } catch (IllegalArgumentException unused) {
            throw InputKt.JsonDecodingException(jsonLexer.currentPosition, "Failed to parse type 'UInt' for input '" + consumeStringLenient + '\'', jsonLexer.source);
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public long decodeLong() {
        JsonLexer jsonLexer = this.lexer;
        String consumeStringLenient = jsonLexer.consumeStringLenient();
        try {
            ULong uLongOrNull = StringsKt__IndentKt.toULongOrNull(consumeStringLenient);
            if (uLongOrNull != null) {
                return uLongOrNull.data;
            }
            StringsKt__IndentKt.numberFormatError(consumeStringLenient);
            throw null;
        } catch (IllegalArgumentException unused) {
            throw InputKt.JsonDecodingException(jsonLexer.currentPosition, "Failed to parse type 'ULong' for input '" + consumeStringLenient + '\'', jsonLexer.source);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0025 A[Catch: IllegalArgumentException -> 0x002c, TryCatch #0 {IllegalArgumentException -> 0x002c, blocks: (B:3:0x0008, B:5:0x000f, B:8:0x001b, B:11:0x0025, B:13:0x0028, B:14:0x002b), top: B:17:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0028 A[Catch: IllegalArgumentException -> 0x002c, TryCatch #0 {IllegalArgumentException -> 0x002c, blocks: (B:3:0x0008, B:5:0x000f, B:8:0x001b, B:11:0x0025, B:13:0x0028, B:14:0x002b), top: B:17:0x0008 }] */
    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public short decodeShort() {
        /*
            r5 = this;
            kotlinx.serialization.json.internal.JsonLexer r0 = r5.lexer
            java.lang.String r1 = r0.consumeStringLenient()
            r2 = 10
            kotlin.UInt r2 = kotlin.text.StringsKt__IndentKt.toUIntOrNull(r1, r2)     // Catch: IllegalArgumentException -> 0x002c
            r3 = 0
            if (r2 == 0) goto L_0x0022
            int r2 = r2.data     // Catch: IllegalArgumentException -> 0x002c
            r4 = 65535(0xffff, float:9.1834E-41)
            int r4 = com.tidalab.v2board.clash.design.dialog.InputKt.uintCompare(r2, r4)     // Catch: IllegalArgumentException -> 0x002c
            if (r4 <= 0) goto L_0x001b
            goto L_0x0022
        L_0x001b:
            short r2 = (short) r2     // Catch: IllegalArgumentException -> 0x002c
            kotlin.UShort r4 = new kotlin.UShort     // Catch: IllegalArgumentException -> 0x002c
            r4.<init>(r2)     // Catch: IllegalArgumentException -> 0x002c
            goto L_0x0023
        L_0x0022:
            r4 = r3
        L_0x0023:
            if (r4 == 0) goto L_0x0028
            short r0 = r4.data     // Catch: IllegalArgumentException -> 0x002c
            return r0
        L_0x0028:
            kotlin.text.StringsKt__IndentKt.numberFormatError(r1)     // Catch: IllegalArgumentException -> 0x002c
            throw r3     // Catch: IllegalArgumentException -> 0x002c
        L_0x002c:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to parse type '"
            r2.append(r3)
            java.lang.String r3 = "UShort"
            r2.append(r3)
            java.lang.String r3 = "' for input '"
            r2.append(r3)
            r2.append(r1)
            r1 = 39
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            int r2 = r0.currentPosition
            java.lang.String r0 = r0.source
            kotlinx.serialization.json.internal.JsonDecodingException r0 = com.tidalab.v2board.clash.design.dialog.InputKt.JsonDecodingException(r2, r1, r0)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.JsonDecoderForUnsignedTypes.decodeShort():short");
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }
}
